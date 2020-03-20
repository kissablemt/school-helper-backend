package cn.edu.dgut.school_helper.mapper;

import cn.edu.dgut.school_helper.pojo.Report;
import cn.edu.dgut.school_helper.pojo.dto.ReportDTO;

import java.util.List;

/**
* 通用 Mapper 代码生成器
*
* @author mapper-generator
*/
public interface ReportMapper extends tk.mybatis.mapper.common.Mapper<Report> {
	List<ReportDTO> selectAllReportByOpenId(String openId);
}




