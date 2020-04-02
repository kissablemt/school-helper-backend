package cn.edu.dgut.school_helper.service.impl;

import cn.edu.dgut.school_helper.exception.ServiceRuntimeException;
import cn.edu.dgut.school_helper.mapper.SchoolMapper;
import cn.edu.dgut.school_helper.mapper.UserMapper;
import cn.edu.dgut.school_helper.pojo.User;
import cn.edu.dgut.school_helper.service.UserService;
import cn.edu.dgut.school_helper.util.Base64Utils;
import cn.edu.dgut.school_helper.util.FastDFSClientWrapper;
import cn.edu.dgut.school_helper.util.JsonResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private SchoolMapper schoolMapper;

    @Autowired
    private FastDFSClientWrapper fastDFSClient;

    @Override
    public JsonResult addUser(User user) {
        //查看是否有该学校
        if (schoolMapper.selectByPrimaryKey(user.getSchoolId()) == null) {
            return JsonResult.errorMsg("不存在该学校id");
        }
        //插入数据
        int row = userMapper.insertSelective(user);
        if (row != 1) {
            return JsonResult.errorMsg("插入数据失败");
        }
        return JsonResult.ok(row);
    }

    @Override
    public JsonResult getUserInfo(User user) {
        User user2 = userMapper.selectByPrimaryKey(user.getOpenId());
        if(user2 == null){
            return JsonResult.errorMsg("没有该用户");
        }
        return JsonResult.ok(user2);
    }

    @Override
    public JsonResult checkUserExistByOpenId(User user) {
        User user2 = userMapper.selectByPrimaryKey(user.getOpenId());
        if (user2 == null) {
            return JsonResult.errorMsg("该用户不存在");
        }
        return JsonResult.ok(user2);
    }

    @Override
	@Transactional
    public JsonResult updateHeadPortrait(String openId, String headPortrait) {
		// 获取未更新前的图片地址
		User user = userMapper.selectByPrimaryKey(openId);
		String path = null;
        try {
			// 上传图片
			byte[] bytes = Base64Utils.decode(headPortrait);
			path = fastDFSClient.uploadFile(bytes, bytes.length, "jpg");
			// 更新数据库
            int row = userMapper.updateByPrimaryKeySelective(new User().setHeadPortraitUrl(path).setOpenId(openId));
			if (row != 1) {
				return JsonResult.errorMsg("更新头像失败");
			}
			// 删除图片
			fastDFSClient.deleteFile(user.getHeadPortraitUrl());
			return JsonResult.ok(path);

        } catch (Exception e) {
        	if(path != null){
				//删除已上传的图片
				fastDFSClient.deleteFile(path);
			}
			throw new ServiceRuntimeException("上传头像失败");
        }
    }

    @Override
    public JsonResult updateUser(User user) {
        //不更新头像地址
        user.setHeadPortraitUrl(null);
        int row = userMapper.updateByPrimaryKeySelective(user);
        if (row != 1) {
            return JsonResult.errorMsg("更新用户信息失败");
        }
        return JsonResult.ok(row);
    }

}
