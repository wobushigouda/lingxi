package com.lingxi.isi.application.command;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

/**
 * 订单项命令对象
 * 应用层数据传输对象，用于接收和转换订单项的客户端请求
 */
@Data
@NoArgsConstructor
public class OrderItemCommand {
    
    /**
     * 商品ID
     */
    @NotBlank(message = "商品ID不能为空")
    private String productId;
    
    /**
     * 商品名称
     */
    @NotBlank(message = "商品名称不能为空")
    private String productName;
    
    /**
     * 购买数量
     */
    @NotNull(message = "购买数量不能为空")
    @Positive(message = "购买数量必须大于0")
    private Integer quantity;
    
    /**
     * 单价
     */
    @NotNull(message = "单价不能为空")
    @Positive(message = "单价必须大于0")
    private BigDecimal unitPrice;
    
    public OrderItemCommand(String productId, String productName, Integer quantity, BigDecimal unitPrice) {
        this.productId = productId;
        this.productName = productName;
        this.quantity = quantity;
        this.unitPrice = unitPrice;
    }
}
