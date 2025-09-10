package cn.niuren.params;

import lombok.Data;

/**
 * @author: niuRen
 * @time: 2025-09-09 11:04
 */
@Data
public class RegisterParams {
    /**
     * 手机号
     */
    // todo 增加注解判断
    private String telephone;

    /**
     * 验证码
     */
    // todo 增加判空
    private String captcha;

    /**
     * 邀请码
     */
    private String inviteCode;
}
