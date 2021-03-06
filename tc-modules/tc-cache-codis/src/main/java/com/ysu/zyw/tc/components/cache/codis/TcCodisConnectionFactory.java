package com.ysu.zyw.tc.components.cache.codis;

import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import redis.clients.jedis.Jedis;
import redis.clients.util.Pool;

import java.util.Objects;

import static com.google.common.base.Preconditions.checkNotNull;

@Slf4j
public class TcCodisConnectionFactory extends JedisConnectionFactory {

    @Getter
    @Setter
    protected TcCodisPool tcCodisPool;

    @Getter
    @Setter
    protected String zkAddr;

    @Getter
    @Setter
    protected int zkSessionTimeoutMs;

    @Getter
    @Setter
    protected String zkProxyDir;

    @Override
    public void destroy() {
        super.destroy();
        if (Objects.nonNull(tcCodisPool)) {
            try {
                tcCodisPool.close();
                log.info("success close codis exhandler ...");
            } catch (Exception ex) {
                log.error("Cannot properly close codis exhandler", ex);
            }
            tcCodisPool = null;
        }
    }

    @Override
    protected Pool<Jedis> createRedisPool() {
        tcCodisPool = new TcCodisPool(
                zkAddr,
                zkSessionTimeoutMs,
                zkProxyDir,
                getPoolConfig(),
                getTimeout(),
                StringUtils.isNotEmpty(getPassword()) ? getPassword() : null);
        checkNotNull(tcCodisPool, "codis exhandler creation failed");
        log.info("success create codis exhandler ...");
        return tcCodisPool;
    }

}
