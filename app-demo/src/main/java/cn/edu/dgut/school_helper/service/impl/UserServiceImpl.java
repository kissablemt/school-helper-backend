package cn.edu.dgut.school_helper.service.impl;

import cn.edu.dgut.school_helper.config.response.ServiceRuntimeExecption;
import cn.edu.dgut.school_helper.util.Base64Utils;
import cn.edu.dgut.school_helper.util.CommonResponse;
import cn.edu.dgut.school_helper.util.FastDFSClientWrapper;
import org.omg.CORBA.COMM_FAILURE;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.edu.dgut.school_helper.mapper.SchoolMapper;
import cn.edu.dgut.school_helper.mapper.UserMapper;
import cn.edu.dgut.school_helper.pojo.User;
import cn.edu.dgut.school_helper.service.UserService;
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
    public Boolean addUser(User user) {
        //查看是否有该学校
        if (schoolMapper.selectByPrimaryKey(user.getSchoolId()) == null) {
            return false;
        }
        //插入数据
        int row = userMapper.insertSelective(user);
        if (row != 1) {
            return false;
        }
        return true;
    }

    @Override
    public User getUserInfo(User user) {
        return userMapper.selectByPrimaryKey(user.getOpenId());
    }

    @Override
    public Boolean checkUserExistByOpenId(User user) {
        User user2 = userMapper.selectByPrimaryKey(user.getOpenId());
        if (user2 == null) {
            return false;
        }
        return true;
    }

    @Override
	@Transactional
    public CommonResponse updateHeadPortrait(String openId, String headPortrait) {
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
				return CommonResponse.error("更新头像失败");
			}
			// 删除图片
			fastDFSClient.deleteFile(user.getHeadPortraitUrl());
			return CommonResponse.isOk(path);

        } catch (Exception e) {
        	if(path != null){
				//删除已上传的图片
				fastDFSClient.deleteFile(path);
			}
			throw new ServiceRuntimeExecption("上传头像失败");
        }
    }

    @Override
    public CommonResponse updateUser(User user) {
        //不更新头像地址
        user.setHeadPortraitUrl(null);
        int row = userMapper.updateByPrimaryKeySelective(user);
        if (row != 1) {
            return CommonResponse.error("更新用户信息失败");
        }
        return CommonResponse.isOk("更新用户信息成功");
    }

}
