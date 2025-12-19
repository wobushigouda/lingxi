package com.lingxi.isi.application.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

/**
 * 订单项数据传输对象（DTO）
 * 应用层数据传输对象，用于向客户端返回订单项信息
 */
@Data
@NoArgsConstructor
public class OrderItemDTO {
    
    /**
     * 订单项ID
     */
    private Long id;
    
    /**
     * 商品ID
     */
    private String productId;
    
    /**
     * 商品名称
     */
    private String productName;
    
    /**
     * 购买数量
     */
    private Integer quantity;
    
    /**
     * 单价
     */
    private BigDecimal unitPrice;
    
    /**
     * 币种
     */
    private String currency;
    
    /**
     * 总价（单价 × 数量）
     */
    private BigDecimal totalPrice;
}
