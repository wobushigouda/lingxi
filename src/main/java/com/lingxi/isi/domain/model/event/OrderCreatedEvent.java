package com.lingxi.isi.domain.model.event;

import com.lingxi.isi.domain.model.aggregate.Order;

/**
 * 订单已创建事件
 * 当订单创建时发起的领域事件
 * 
 * 可用于：
 * - 发送确认邮件
 * - 更新库存
 * - 记录事件日志
 * - 触发其他业务流程
 */
public class OrderCreatedEvent extends DomainEvent {
    
    /**
     * 订单信息
     */
    private final Order order;
    
    public OrderCreatedEvent(Order order) {
        super();
        this.order = order;
    }
    
    public Order getOrder() {
        return order;
    }
}
