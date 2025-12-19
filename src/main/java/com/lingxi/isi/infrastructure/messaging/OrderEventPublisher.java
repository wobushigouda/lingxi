package com.lingxi.isi.infrastructure.messaging;

import com.lingxi.isi.domain.model.event.DomainEvent;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

/**
 * 领域事件发布器
 * 基础设施层组件，负责发布和处理领域事件
 * 
 * 当前实现为日志输出，可扩展为消息队列（Kafka、RabbitMQ）
 */
@Component
@Slf4j
public class OrderEventPublisher {
    
    /**
     * 发布领域事件
     * 
     * @param event 领域事件
     */
    public void publish(DomainEvent event) {
        // 当前实现为日志输出，可扩展为消息队列实现
        log.info("发布事件: {}, 事件ID: {}, 发生时间: {}", 
            event.getEventTypeName(), 
            event.getEventId(), 
            event.getOccurredAt());
        
        // TODO: 后续可集成消息队列 (Kafka, RabbitMQ 等)
        // kafkaTemplate.send("order-events", JsonUtils.toJson(event));
    }
}
