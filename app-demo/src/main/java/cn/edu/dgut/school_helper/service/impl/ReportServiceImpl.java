package cn.edu.dgut.school_helper.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.edu.dgut.school_helper.constanst.ReportConstant;
import cn.edu.dgut.school_helper.mapper.PostMapper;
import cn.edu.dgut.school_helper.mapper.ReportMapper;
import cn.edu.dgut.school_helper.mapper.UserMapper;
import cn.edu.dgut.school_helper.pojo.Post;
import cn.edu.dgut.school_helper.pojo.Report;
import cn.edu.dgut.school_helper.pojo.User;
import cn.edu.dgut.school_helper.service.ReportService;
import cn.edu.dgut.school_helper.util.CommonResponse;

@Service
public class ReportServiceImpl implements ReportService {

	@Autowired
	private ReportMapper reportMapper;

	@Autowired
	private UserMapper userMapper;

	@Autowired
	private PostMapper postMapper;

	@Override
	public CommonResponse addReport(Report report) {
		// 检查帖子是否存在
		Post post = postMapper.selectByPrimaryKey(report.getPostId());
		if (post == null) {
			return CommonResponse.error("该举报的帖子不存在");
		}
		report.setStatus(ReportConstant.UNDO);
		int row = reportMapper.insertSelective(report);
		if (row == 1) {
			return CommonResponse.isOk(row);
		}
		return CommonResponse.error("插入失败");
	}

	@Override
	public CommonResponse updateReport(Report report) {
		// 处理完毕的帖子不允许修改
		Report report2 = reportMapper.selectByPrimaryKey(report.getReportId());
		if (report2.getStatus() == ReportConstant.DONE) {
			return CommonResponse.error("该举报已经处理完毕，不允许修改");
		}
		// 只允许更新举报内容
		int row = reportMapper.updateByPrimaryKeySelective(
				new Report().setReportId(report.getReportId()).setRemark(report.getRemark()));
		if (row == 1) {
			return CommonResponse.isOk(row);
		}
		return CommonResponse.error("更新失败");
	}

	@Override
	public CommonResponse deleteReportById(Report report) {
		int row = reportMapper.updateByPrimaryKeySelective(report.setStatus(ReportConstant.DELETED));
		if (row == 1) {
			return CommonResponse.isOk(row);
		}
		return CommonResponse.error("删除失败");
	}

	@Override
	public CommonResponse selectReportByOpenId(Report report) {
		return CommonResponse.isOk(reportMapper.selectAll());
	}
}
