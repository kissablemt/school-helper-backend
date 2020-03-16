package cn.edu.dgut.school_helper.service;


import cn.edu.dgut.school_helper.pojo.Post;
import cn.edu.dgut.school_helper.pojo.dto.PostQueryDTO;
import cn.edu.dgut.school_helper.util.CommonResponse;

public interface PostService {


    public CommonResponse addPost(Post post, String[] imageStrs);
    public CommonResponse updatePost(Post post, String[] imageStrs);
    public CommonResponse deletePostById(Post post);
    public CommonResponse selectAllPostByOpenId(Post post);
    public CommonResponse selectPostListPaging(PostQueryDTO postQueryDTO);
    public CommonResponse selectSecondHandPostListPaging(PostQueryDTO postQueryDTO);

}




