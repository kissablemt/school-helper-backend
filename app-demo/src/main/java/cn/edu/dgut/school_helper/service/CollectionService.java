package cn.edu.dgut.school_helper.service;

import cn.edu.dgut.school_helper.pojo.Collection;
import cn.edu.dgut.school_helper.util.JsonResult;

public interface CollectionService{
	
		
		public JsonResult addCollection(Collection collection);
		public JsonResult deleteCollectionById(Collection collection);
		public JsonResult selectCollectionByOpenId(Collection collection);
		//public JsonResult selectAllCollection();
}




