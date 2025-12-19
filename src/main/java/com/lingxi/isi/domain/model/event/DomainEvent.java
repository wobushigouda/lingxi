package com.lingxi.isi.domain.model.event;

import java.time.LocalDateTime;
import java.util.UUID;

/**
 * 领域事件基础类
 * 所有领域事件的抽象基类，定义事件的通用属性和行为
 */
public abstract class DomainEvent {
    
    /**
     * 事件唯一ID
     */
    private final String eventId;
    
    /**
     * 事件发生时间
     */
    private final LocalDateTime occurredAt;
    
    /**
     * 事件创建者
     */
    protected DomainEvent() {
        this.eventId = UUID.randomUUID().toString();
        this.occurredAt = LocalDateTime.now();
    }
    
    /**
     * 获取事件ID
     */
    public String getEventId() {
        return eventId;
    }
    
    /**
     * 获取事件发生时间
     */
    public LocalDateTime getOccurredAt() {
        return occurredAt;
    }
    
    /**
     * 获取事件类型名称（用于序列化和日志记录）
     */
    public String getEventTypeName() {
        return this.getClass().getSimpleName();
    }
}
