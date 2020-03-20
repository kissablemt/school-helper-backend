package cn.edu.dgut.school_helper.controller;

import cn.edu.dgut.school_helper.constant.JwtRequestConstant;
import cn.edu.dgut.school_helper.pojo.Collection;
import cn.edu.dgut.school_helper.service.CollectionService;
import cn.edu.dgut.school_helper.util.JsonResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/collection")
public class CollectionController {
	
	@Autowired
	private CollectionService collectionService;
	
	@PostMapping
	public JsonResult addCollection(@RequestBody Collection collection, @RequestAttribute(JwtRequestConstant.OPEN_ID) String openId) {
		return collectionService.addCollection(collection.setOpenId(openId));
	}

	
	@DeleteMapping("/{id}")
	public JsonResult deleteCollectionById(@PathVariable(name = "id") Integer collectionId,@RequestAttribute(JwtRequestConstant.OPEN_ID) String openId) {
		return collectionService.deleteCollectionById(new Collection().setCollectionId(collectionId).setOpenId(openId));
	}
	
	
	@GetMapping("/selectAll")
	public JsonResult selectAllCollectionByOpenId(@RequestAttribute(JwtRequestConstant.OPEN_ID)String openId) {
		return collectionService.selectCollectionByOpenId(new Collection().setOpenId(openId));
	}
}
