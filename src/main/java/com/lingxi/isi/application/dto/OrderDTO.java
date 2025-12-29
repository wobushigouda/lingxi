package com.lingxi.isi.application.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.List;

/**
 * 订单数据传输对象（DTO）
 * 应用层数据传输对象，用于向客户端返回订单信息
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderDTO {
    
    /**
     * 订单ID
     */
    private Long id;
    
    /**
     * 订单号
     */
    private String orderNumber;
    
    /**
     * 客户ID
     */
    private String customerId;
    
    /**
     * 订单状态
     */
    private String status;
    
    /**
     * 订单总金额
     */
    private BigDecimal totalAmount;
    
    /**
     * 币种
     */
    private String currency;
    
    /**
     * 订单项列表
     */
    private List<OrderItemDTO> items;
}
