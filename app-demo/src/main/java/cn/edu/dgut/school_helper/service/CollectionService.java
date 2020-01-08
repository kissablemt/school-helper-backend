package cn.edu.dgut.school_helper.service;

import cn.edu.dgut.school_helper.pojo.Collection;
import cn.edu.dgut.school_helper.util.CommonResponse;

public interface CollectionService{
	
		
		public CommonResponse addCollection(Collection collection);
		public CommonResponse updateCollection(Collection collection);
		public CommonResponse deleteCollectionById(Collection collection);
		public CommonResponse selectCollectionByOpenId(Collection collection);
		//public CommonResponse selectAllCollection();
}




