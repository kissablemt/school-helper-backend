package cn.edu.dgut.school_helper.service.impl;

import cn.edu.dgut.school_helper.constant.ReportConstant;
import cn.edu.dgut.school_helper.mapper.PostMapper;
import cn.edu.dgut.school_helper.mapper.ReportMapper;
import cn.edu.dgut.school_helper.pojo.Post;
import cn.edu.dgut.school_helper.pojo.Report;
import cn.edu.dgut.school_helper.service.ReportService;
import cn.edu.dgut.school_helper.util.IntegerCompareUtils;
import cn.edu.dgut.school_helper.util.JsonResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ReportServiceImpl implements ReportService {

	@Autowired
	private ReportMapper reportMapper;

	@Autowired
	private PostMapper postMapper;

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

	@Override
	public JsonResult updateReport(Report report) {
		Report report2 = reportMapper.selectByPrimaryKey(report.getReportId());
		if(report2 == null) {
			return JsonResult.errorMsg("没有该举报");
		}
		if(!IntegerCompareUtils.equals(report2.getReportId(), report2.getReportId())) {
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
