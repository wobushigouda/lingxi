package com.lingxi.isi.domain.model.entity;

import com.lingxi.isi.domain.model.vo.Money;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

/**
 * 订单项实体
 * 属于 Order 聚合根，不能单独存在
 * 
 * 职责：
 * - 存储订单项的商品信息
 * - 计算订单项的总价
 */
@Entity
@Table(name = "order_item")
@Data
@NoArgsConstructor
public class OrderItem {
   
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String productId;
    private String productName;
    private int quantity;

    @Embedded
    private Money unitPrice;

    /**
     * 计算订单项总价
     * @return 总价 = 单价 × 数量
     */
    public Money calculateTotal() {
   
        return new Money(
            unitPrice.getAmount().multiply(BigDecimal.valueOf(quantity)),
            unitPrice.getCurrency()
        );
    }
}
