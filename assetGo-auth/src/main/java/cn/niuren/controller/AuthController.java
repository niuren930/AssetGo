package cn.niuren.controller;

import cn.niuren.limiter.WindowRateLimiter;
import cn.niuren.params.RegisterParams;
import cn.niuren.service.notice.NoticeService;
import cn.niuren.vo.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author: niuRen
 * @time: 2025-09-08 14:11
 */
@Slf4j
@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private NoticeService noticeService;

    // todo 登录
    public Result login() {
        return Result.success("登录成功");
    }

    // todo 注册
    @PostMapping("/register")
    public Result register(@RequestBody RegisterParams registerParam) {

        // 校验验证码
        // 根据手机号查询缓存中的验证码是否和获取的一致

        //  注册

        return Result.success("登录成功");
    }

    // todo 发送验证码
    public Result sendCheckCode(String telephone) {

        noticeService.generateCaptchaAndSendNotice(telephone);

        return Result.success("发送成功");
    }

}
