package com.lingxi.isi.infrastructure.adapter;

import com.lingxi.isi.domain.model.aggregate.Order;
import com.lingxi.isi.domain.service.InventoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

/**
 * 订单领域服务
 * 领域层服务，处理跨聚合根的业务逻辑
 * 职责：
 * - 协调多个聚合根之间的业务逻辑
 * - 检查库存等跨边界操作
 * 注意：一般情况下，业务逻辑应该在聚合根中
 * 只有当操作涉及多个聚合根时，才需要领域服务
 */
@Service
@RequiredArgsConstructor
public class OrderAdapter {

    private final InventoryService inventoryService;

    /**
     * 检查订单商品库存是否充足
     * @param order 订单对象
     * @return true 表示库存充足，false 表示库存不足
     */
    public boolean checkInventory(Order order) {

        return order.getItems().stream()
                .allMatch(item -> inventoryService.hasStock(item.getProductId(), item.getQuantity()));
    }
}
