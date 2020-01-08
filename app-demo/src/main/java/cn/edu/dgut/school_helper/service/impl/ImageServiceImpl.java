package cn.edu.dgut.school_helper.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.edu.dgut.school_helper.mapper.ImageMapper;
import cn.edu.dgut.school_helper.pojo.Image;
import cn.edu.dgut.school_helper.service.ImageService;
import cn.edu.dgut.school_helper.util.CommonResponse;


@Service
public class ImageServiceImpl implements ImageService {
	
	@Autowired
	private ImageMapper imageMapper;
	
	@Override
	public CommonResponse selectImageById(Image image) {
		return null;
	}
	
	@Override
	public CommonResponse addImage(Image image) {
		int row = imageMapper.insertSelective(image);
		if(row == 1) {
			return CommonResponse.isOk(row);
		}
		return CommonResponse.error("插入失败");
	}

	@Override
	public CommonResponse updateImage(Image image) {
		int row = imageMapper.updateByPrimaryKeySelective(image);
		if(row == 1) {
			return CommonResponse.isOk(row);
		}
		return CommonResponse.error("更新失败");
	}

	@Override
	public CommonResponse deleteImageById(Image image) {
		int row = imageMapper.deleteByPrimaryKey(image);
		if(row == 1) {
			return CommonResponse.isOk(row);
		}
		return CommonResponse.error("删除失败");
	}

	

}
