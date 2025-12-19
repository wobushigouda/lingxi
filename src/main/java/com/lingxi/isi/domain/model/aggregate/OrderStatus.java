package com.lingxi.isi.domain.model.aggregate;

/**
 * 订单状态枚举
 * 领域层值对象，定义订单的生命周期状态
 */
public enum OrderStatus {
    
    /**
     * 已创建
     */
    CREATED("已创建"),
    
    /**
     * 已确认
     */
    CONFIRMED("已确认"),
    
    /**
     * 已发货
     */
    SHIPPED("已发货"),
    
    /**
     * 已完成
     */
    COMPLETED("已完成"),
    
    /**
     * 已取消
     */
    CANCELLED("已取消");
    
    private final String description;
    
    OrderStatus(String description) {
        this.description = description;
    }
    
    public String getDescription() {
        return description;
    }
}
