package cn.niuren.service.notice;

import cn.hutool.core.util.RandomUtil;
import cn.niuren.limiter.WindowRateLimiter;
import cn.niuren.vo.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

import static cn.niuren.service.notice.NoticeConstant.CAPTCHA_KEY_PREFIX;

/**
 * @author: niuRen
 * @time: 2025-09-10 14:52
 */
@Service
public class NoticeServiceImpl implements NoticeService {
    @Autowired
    private WindowRateLimiter windowRateLimiter;

    @Autowired
    private StringRedisTemplate redisTemplate;

    @Override
    public String generateCaptchaAndSendNotice(String telephone) {

        // 限流，60秒内只能发送一次
        Boolean access = windowRateLimiter.tryAcquire(telephone, 1, 60);

        if (!access) {
            throw new RuntimeException("请勿频繁发送验证码");
        }

        // 生成验证码
        String captcha = RandomUtil.randomNumbers(4);

        // 存入缓存
        redisTemplate.opsForValue().set(CAPTCHA_KEY_PREFIX + telephone, captcha,5, TimeUnit.MINUTES);

        // todo 发送短信
        Thread.ofVirtual().start(() -> {
            // 发送短信
            System.out.println("发送验证码：" + captcha);
        });


        return null;
    }
}
