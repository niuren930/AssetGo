package cn.niuren.limiter;

import org.redisson.api.RRateLimiter;
import org.redisson.api.RateIntervalUnit;
import org.redisson.api.RateType;
import org.redisson.api.RedissonClient;
import org.springframework.stereotype.Component;

/**
 * 窗口限流服务
 *
 * @author: niuRen
 * @time: 2025-09-10 14:29
 */
@Component
public class WindowRateLimiter implements RateLimiter {
    private static final String LIMIT_KEY_PREFIX = "assetGo:limit:";

    private RedissonClient redissonClient;

    public WindowRateLimiter(RedissonClient redissonClient) {
        this.redissonClient = redissonClient;
    }

    public Boolean tryAcquire(String key, int limit, int windowSize) {
        RRateLimiter rateLimiter = redissonClient.getRateLimiter(LIMIT_KEY_PREFIX + key);

        if (!rateLimiter.isExists()) {
            // 尝试设置速率
            // RateType.OVERALL - 所有客户端实例共享的总速率
            // RateType.PER_CLIENT - 每个客户端实例独立的速率
            rateLimiter.trySetRate(RateType.OVERALL,    // 速率类型（是总速率还是每个客户端的速率？通常是总速率
                    limit, // 速率：单位时间内产生的令牌数
                    windowSize, // 时间间隔
                    RateIntervalUnit.SECONDS);
        }
        // 尝试获取1个令牌，立即返回成功或失败
        return rateLimiter.tryAcquire();
    }
}
