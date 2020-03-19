package cn.edu.dgut.school_helper.controller;

import cn.edu.dgut.school_helper.service.MiniAppService;
import cn.edu.dgut.school_helper.service.impl.MiniAppServiceImpl;
import cn.edu.dgut.school_helper.util.OnlineUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import cn.edu.dgut.school_helper.constant.JwtRequestConstant;
import cn.edu.dgut.school_helper.pojo.Reply;
import cn.edu.dgut.school_helper.service.ReplyService;
import cn.edu.dgut.school_helper.util.CommonResponse;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/reply")
public class ReplyController {

    @Autowired
    private ReplyService replyService;

    @Autowired
    private MiniAppService miniAppService;

    @PostMapping
    public CommonResponse addReply(@RequestBody Reply reply,
                                   @RequestAttribute(JwtRequestConstant.OPEN_ID) String openId) {
        //敏感词验证不通过
        if(!miniAppService.validateSensitiveWords(reply.getContent())){
            return CommonResponse.error("含有敏感词");
        }
        return replyService.addReply(reply.setFromOpenId(openId));
    }

    @DeleteMapping("/{replyId}")
    public CommonResponse deleteReplyById(@PathVariable(name = "replyId") Integer replyId,
                                          @RequestAttribute(JwtRequestConstant.OPEN_ID) String openId) {
        return replyService.deleteReplyById(new Reply().setReplyId(replyId).setFromOpenId(openId));
    }

    @GetMapping("/selectAll/{postId}")
    public CommonResponse selectReplyList(@PathVariable("postId") Integer postId, @RequestParam Integer pageNum,
                                          @RequestParam Integer pageSize) {
        return replyService.selectReplyByPostId(postId, pageNum, pageSize);
    }
}
