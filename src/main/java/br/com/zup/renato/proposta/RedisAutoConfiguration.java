import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.opentracing.Tracer;
import io.opentracing.contrib.spring.cloud.redis.RedisAspect;
import io.opentracing.contrib.spring.tracer.configuration.TracerRegisterAutoConfiguration;

/**
 * Loads the integration with OpenTracing Redis if it's included in the classpath.
 *
 * @author Daniel del Castillo
 * @author Luram Archanjo
 */
@Configuration
@AutoConfigureAfter({TracerRegisterAutoConfiguration.class, org.springframework.boot.autoconfigure.data.redis.RedisAutoConfiguration.class})
@ConditionalOnBean(RedisConnectionFactory.class)
@ConditionalOnProperty(name = "opentracing.spring.cloud.redis.enabled", havingValue = "true", matchIfMissing = true)
@EnableConfigurationProperties(RedisTracingProperties.class)
public class RedisAutoConfiguration {

  @Bean
  public RedisAspect openTracingRedisAspect(Tracer tracer, RedisTracingProperties properties) {
    return new RedisAspect(tracer, properties);
  }

}
