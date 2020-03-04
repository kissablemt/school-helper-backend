package cn.edu.dgut.school_helper.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import cn.edu.dgut.school_helper.constant.JwtRequestConstant;
import cn.edu.dgut.school_helper.pojo.Collection;
import cn.edu.dgut.school_helper.service.CollectionService;
import cn.edu.dgut.school_helper.util.CommonResponse;

@RestController
@RequestMapping("/api/collection")
public class CollectionController {
	
	@Autowired
	private CollectionService collectionService;
	
	@PostMapping
	public CommonResponse addCollection(@RequestBody Collection collection,@RequestAttribute(JwtRequestConstant.OPEN_ID) String openId) {
		return collectionService.addCollection(collection.setOpenId(openId));
	}

	
	@DeleteMapping("/{id}")
	public CommonResponse deleteCollectionById(@PathVariable(name = "id") Integer collectionId,@RequestAttribute(JwtRequestConstant.OPEN_ID) String openId) {
		return collectionService.deleteCollectionById(new Collection().setCollectionId(collectionId).setOpenId(openId));
	}
	
	
	@GetMapping("/selectAll")
	public CommonResponse selectAllCollectionByOpenId(@RequestAttribute(JwtRequestConstant.OPEN_ID)String openId) {
		return collectionService.selectCollectionByOpenId(new Collection().setOpenId(openId));
	}
}
