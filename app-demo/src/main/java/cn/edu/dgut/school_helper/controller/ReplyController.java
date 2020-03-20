package cn.edu.dgut.school_helper.controller;

import cn.edu.dgut.school_helper.constant.JwtRequestConstant;
import cn.edu.dgut.school_helper.pojo.Reply;
import cn.edu.dgut.school_helper.service.MiniAppService;
import cn.edu.dgut.school_helper.service.ReplyService;
import cn.edu.dgut.school_helper.util.JsonResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/reply")
public class ReplyController {

    @Autowired
    private ReplyService replyService;

    @Autowired
    private MiniAppService miniAppService;

    @PostMapping
    public JsonResult addReply(@RequestBody Reply reply,
                               @RequestAttribute(JwtRequestConstant.OPEN_ID) String openId) {
        //敏感词验证不通过
        if (!miniAppService.validateSensitiveWords(reply.getContent())) {
            return JsonResult.errorSensitiveContent("含有敏感词");
        }
        return replyService.addReply(reply.setFromOpenId(openId));
    }

    @DeleteMapping("/{replyId}")
    public JsonResult deleteReplyById(@PathVariable(name = "replyId") Integer replyId,
                                      @RequestAttribute(JwtRequestConstant.OPEN_ID) String openId) {
        return replyService.deleteReplyById(new Reply().setReplyId(replyId).setFromOpenId(openId));
    }

    @GetMapping("/selectAll/{postId}")
    public JsonResult selectReplyList(@PathVariable("postId") Integer postId, @RequestParam Integer pageNum,
                                      @RequestParam Integer pageSize) {
        return replyService.selectReplyByPostId(postId, pageNum, pageSize);
    }
}
