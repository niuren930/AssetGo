package cn.niuren.controller;

import cn.niuren.vo.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author: niuRen
 * @time: 2025-09-08 14:11
 */
@Slf4j
@RestController
@RequestMapping("/auth")
public class AuthController {

    // todo 登录
    public Result login() {
        return Result.success("登录成功");
    }

    // todo 注册
    public Result register() {
        return Result.success("登录成功");
    }

    // todo 发送验证码
    public Result sendCheckCode() {
        return Result.success("登录成功");
    }

}
