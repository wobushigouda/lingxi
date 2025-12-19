package com.lingxi.isi.infrastructure.repository;

import com.lingxi.isi.domain.model.aggregate.Order;
import com.lingxi.isi.domain.repository.OrderRepository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * 订单仓储 JPA 实现
 * 基础设施层的数据持久化实现，负责数据库操作
 * 
 * 通过继承 JpaRepository 和 OrderRepository 接口，
 * 提供完整的 CRUD 和自定义查询功能
 */
@Repository
public interface JpaOrderRepository extends OrderRepository, JpaRepository<Order, Long> {
    
    /**
     * 根据订单号查找订单
     * 
     * @param orderNumber 订单号
     * @return 订单可选值
     */
    @Override
    Optional<Order> findByOrderNumber(String orderNumber);

    @Override
    Optional<Order> findById(Long id);
}
