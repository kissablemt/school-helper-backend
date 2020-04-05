package cn.edu.dgut.school_helper.service.impl;

import cn.edu.dgut.school_helper.constant.MessageConstant;
import cn.edu.dgut.school_helper.constant.PostConstant;
import cn.edu.dgut.school_helper.constant.ReportConstant;
import cn.edu.dgut.school_helper.exception.ServiceRuntimeException;
import cn.edu.dgut.school_helper.mapper.MessageMapper;
import cn.edu.dgut.school_helper.mapper.PostMapper;
import cn.edu.dgut.school_helper.mapper.ReportMapper;
import cn.edu.dgut.school_helper.mapper.UserMapper;
import cn.edu.dgut.school_helper.pojo.Message;
import cn.edu.dgut.school_helper.pojo.Post;
import cn.edu.dgut.school_helper.pojo.Report;
import cn.edu.dgut.school_helper.pojo.User;
import cn.edu.dgut.school_helper.service.ReportService;
import cn.edu.dgut.school_helper.util.IntegerCompareUtils;
import cn.edu.dgut.school_helper.util.JsonResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import springfox.documentation.spring.web.json.Json;

import java.util.Date;
import java.util.List;

@Service
public class ReportServiceImpl implements ReportService {

    @Autowired
    private ReportMapper reportMapper;

    @Autowired
    private PostMapper postMapper;

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private MessageMapper messageMapper;

    @Override
    public JsonResult addReport(Report report) {
        // 检查帖子是否存在
        Post post = postMapper.selectByPrimaryKey(report.getPostId());
        if (post == null) {
            return JsonResult.errorMsg("该举报的帖子不存在");
        }
        report.setStatus(ReportConstant.UNDO);
        int row = reportMapper.insertSelective(report);
        if (row == 1) {
            return JsonResult.ok(row);
        }
        return JsonResult.errorMsg("插入失败");
    }

    /**
     * 举报超过两人，帖子封禁,扣除用户信誉分2，发送消息通知用户
     * 由于该功能是临时的，有许多魔法值没有用常量记录
     * constant:发帖人扣除信誉积分 2 点
     *
     * @param report
     * @return
     */
    @Transactional
    @Override
    public JsonResult addReportAutoDealPost(Report report) {
        // 检查帖子是否存在
        Post post = postMapper.selectByPrimaryKey(report.getPostId());
        if (post == null) {
            return JsonResult.errorMsg("该举报的帖子不存在");
        }
        // 查询是否重复举报帖子
        int repeatCount = reportMapper.selectCount(new Report().setReporterId(report.getReporterId()).setPostId(report.getPostId()));
        if (repeatCount >= 1) {
            return JsonResult.errorMsg("不允许重复举报帖子");
        }
        report.setStatus(ReportConstant.UNDO);
        // 满足两人,封禁,扣除用户信誉分，发送消息
        int count = 1 + reportMapper.selectCount(new Report().setPostId(report.getPostId()));
        if (count >= 2) {
			// 当前举报设置为已处理
			report.setStatus(ReportConstant.DONE);
            //封禁帖子
        	postMapper.updateByPrimaryKeySelective(new Post().setPostId(report.getPostId()).setStatus(PostConstant.BAN));
            // 扣除信誉分
            User user = userMapper.selectByPrimaryKey(post.getOpenId());
            userMapper.updateByPrimaryKeySelective(user.setFaithValue(user.getFaithValue() - 2));
            // 发送信息通知发帖人
            Message message = new Message()
					.setOpenId(user.getOpenId())
					.setContent("帖子由于多人举报被封禁")
                    .setDate(new Date())
                    .setStatus(MessageConstant.UNREAD);
            messageMapper.insertSelective(message);

        }
        // 把之前的举报更新为已处理
        List<Report> reports = reportMapper.select(new Report().setPostId(report.getPostId()));
        for (Report rp : reports) {
            reportMapper.updateByPrimaryKeySelective(rp.setStatus(ReportConstant.DONE));
        }
        // 插入当前举报
        int row = reportMapper.insertSelective(report);
        if (row == 1) {
            return JsonResult.ok(row);
        }
        return JsonResult.errorMsg("插入失败");
    }

    @Override
    public JsonResult updateReport(Report report) {
        Report report2 = reportMapper.selectByPrimaryKey(report.getReportId());
        if (report2 == null) {
            return JsonResult.errorMsg("没有该举报");
        }
        if (!IntegerCompareUtils.equals(report2.getReportId(), report2.getReportId())) {
            return JsonResult.errorMsg("不是本人的举报，不可更新");
        }
        // 处理完毕的帖子不允许修改
        if (report2.getStatus() != ReportConstant.UNDO) {
            return JsonResult.errorMsg("该举报已经处理完毕，不允许修改");
        }
        // 只允许更新举报内容
        int row = reportMapper.updateByPrimaryKeySelective(
                new Report().setReportId(report.getReportId()).setRemark(report.getRemark()));
        if (row == 1) {
            return JsonResult.ok(row);
        }
        return JsonResult.errorMsg("更新失败");
    }

    @Override
    public JsonResult selectReportByOpenId(Report report) {
        return JsonResult.ok(reportMapper.selectAllReportByOpenId(report.getReporterId()));
    }
}
