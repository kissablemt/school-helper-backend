package cn.edu.dgut.school_helper.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import cn.edu.dgut.school_helper.pojo.Image;
import cn.edu.dgut.school_helper.service.ImageService;
import cn.edu.dgut.school_helper.util.CommonResponse;

@RestController
@RequestMapping("/api/image")
public class ImageController {
	
	@Autowired
	private ImageService imageService;
	
	@PostMapping
	public CommonResponse addImage(@RequestBody Image image) {
		return imageService.addImage(image);
	}

	@PutMapping
	public CommonResponse updateImage(@RequestBody Image image) {
		return imageService.updateImage(image);
	}
	
	@DeleteMapping("/{id}")
	public CommonResponse deleteImageById(@PathVariable(name = "id") Integer imageId) {
		return imageService.deleteImageById(new Image().setImageId(imageId));
	}
	
	
	@GetMapping("/selectAll")
	public CommonResponse selectAllImage() {
		return imageService.selectImageById(null);
	}
}
