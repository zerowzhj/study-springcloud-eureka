package study.springcloud.eureka.support.listener;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.netflix.eureka.server.event.*;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
public class EurekaListener {

    private static final Logger LOGGER = LoggerFactory.getLogger(EurekaListener.class);

    /**
     * 服务下线
     */
    @EventListener
    public void listen(EurekaInstanceCanceledEvent event) {
        event.getServerId();
        event.getAppName();
        LOGGER.info("EurekaInstanceCanceledEvent {}", event.getAppName());
    }

    /**
     * 服务注册
     */
    @EventListener
    public void listen(EurekaInstanceRegisteredEvent event) {
        event.getInstanceInfo();
        event.getLeaseDuration();
        LOGGER.info("EurekaInstanceRegisteredEvent {}", event.getInstanceInfo());
    }

    /**
     * 服务续约
     */
    @EventListener
    public void listen(EurekaInstanceRenewedEvent event) {
        event.getServerId();
        event.getAppName();
        event.getInstanceInfo();
        LOGGER.info("EurekaInstanceRegisteredEvent {}", event.getAppName());
    }

    /**
     * 服务端启动事件
     */
    @EventListener
    public void listen(EurekaRegistryAvailableEvent event) {
        LOGGER.info("EurekaInstanceRegisteredEvent {}", event.getSource());
    }

    /**
     * 服务端可用事件
     */
    @EventListener
    public void listen(EurekaServerStartedEvent event) {
        LOGGER.info("EurekaInstanceRegisteredEvent {}", event.getSource());
    }
}
