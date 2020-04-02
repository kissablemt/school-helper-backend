package cn.edu.dgut.school_helper.service;


import cn.edu.dgut.school_helper.pojo.Post;
import cn.edu.dgut.school_helper.pojo.dto.PostQueryDTO;
import cn.edu.dgut.school_helper.util.JsonResult;

public interface PostService {


    public JsonResult addPost(Post post, String[] imageStrs);
    public JsonResult updatePost(Post post, String[] imageStrs);
    public JsonResult deletePostById(Post post);
    public JsonResult selectPostByPostId(Post post);
    public JsonResult selectAllPostByOpenId(Post post);
    public JsonResult selectPostListPaging(PostQueryDTO postQueryDTO);
    public JsonResult selectSecondHandPostListPaging(PostQueryDTO postQueryDTO);

}




