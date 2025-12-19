package com.lingxi.isi.domain.model.aggregate;

import com.lingxi.isi.domain.model.entity.OrderItem;
import com.lingxi.isi.domain.model.vo.Money;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Currency;
import java.util.List;

/**
 * 订单聚合根
 * 领域模型核心，管理订单的整个生命周期
 * 
 * 职责：
 * - 维护订单的一致性边界
 * - 执行订单相关的业务规则
 * - 发起领域事件
 */
@Entity
@Table(name = "orders")
@Data
@NoArgsConstructor
public class Order {
   
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String orderNumber;
    private String customerId;

    @Enumerated(EnumType.STRING)
    private OrderStatus status;

    @Embedded
    private Money totalAmount;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "order_id")
    private List<OrderItem> items = new ArrayList<>();

    /**
     * 领域行为：添加订单项
     */
    public void addItem(OrderItem item) {
   
        this.items.add(item);
        recalculateTotal();
    }

    /**
     * 领域行为：重新计算订单总价
     */
    private void recalculateTotal() {
   
        this.totalAmount = items.stream()
            .map(OrderItem::calculateTotal)
            .reduce(new Money(BigDecimal.ZERO, Currency.getInstance("CNY")), Money::add);
    }

    /**
     * 领域行为：确认订单
     */
    public void confirm() {
   
        if (this.status != OrderStatus.CREATED) {
   
            throw new IllegalStateException("只有创建状态的订单可以确认");
        }
        this.status = OrderStatus.CONFIRMED;
    }
    
    /**
     * 添加领域事件
     */
    public void addDomainEvent(Object event) {
        // 实现事件存储逻辑（可扩展）
    }
}
