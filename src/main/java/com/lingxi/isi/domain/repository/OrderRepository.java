package com.lingxi.isi.domain.repository;

import com.lingxi.isi.domain.model.aggregate.Order;

import java.util.Optional;

/**
 * 订单仓储接口
 * 领域层接口，定义订单的持久化操作契约
 * 
 * 实现在基础设施层（JpaOrderRepository）
 */
public interface OrderRepository {
   
    /**
     * 保存订单
     * @param order 订单对象
     * @return 保存后的订单对象
     */
    Order save(Order order);
    
    /**
     * 根据ID查找订单
     * @param id 订单ID
     * @return 订单可选值
     */
    Optional<Order> findById(Long id);
    
    /**
     * 根据订单号查找订单
     * @param orderNumber 订单号
     * @return 订单可选值
     */
    Optional<Order> findByOrderNumber(String orderNumber);
}
