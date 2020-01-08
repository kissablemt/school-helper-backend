package cn.edu.dgut.school_helper.service;

import cn.edu.dgut.school_helper.pojo.Image;
import cn.edu.dgut.school_helper.util.CommonResponse;

public interface ImageService{
	
		public CommonResponse selectImageById(Image image);
		public CommonResponse addImage(Image image);
		public CommonResponse updateImage(Image image);
		public CommonResponse deleteImageById(Image image);
		//public CommonResponse selectAllImage();
}




