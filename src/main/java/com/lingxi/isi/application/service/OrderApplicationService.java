package com.lingxi.isi.application.service;

import com.lingxi.isi.application.command.CreateOrderCommand;
import com.lingxi.isi.application.dto.OrderDTO;
import com.lingxi.isi.application.mapper.OrderMapper;
import com.lingxi.isi.domain.exception.InsufficientInventoryException;
import com.lingxi.isi.domain.exception.OrderNotFoundException;
import com.lingxi.isi.domain.model.aggregate.Order;
import com.lingxi.isi.domain.model.aggregate.OrderStatus;
import com.lingxi.isi.domain.model.event.OrderCreatedEvent;
import com.lingxi.isi.domain.repository.OrderRepository;
import com.lingxi.isi.infrastructure.adapter.OrderAdapter;
import com.lingxi.isi.infrastructure.messaging.OrderEventPublisher;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * 订单应用服务
 * 应用层核心服务，负责编排业务流程和协调各个领域组件
 * 职责：
 * - 接收命令和查询请求
 * - 调用领域服务执行业务逻辑
 * - 管理事务
 * - 发布领域事件
 * - 数据转换（Command ↔ Domain ↔ DTO）
 */
@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
public class OrderApplicationService {
   
    private final OrderRepository orderRepository;
    private final OrderAdapter orderAdapter;
    private final OrderMapper orderMapper;
    private final OrderEventPublisher orderEventPublisher;

    /**
     * 创建订单用例
     * 
     * 流程：
     * 1. 命令 → 领域对象（使用 Mapper）
     * 2. 生成订单号
     * 3. 业务规则校验（检查库存）
     * 4. 保存订单（调用仓储）
     * 5. 发布领域事件
     * 6. 领域对象 → DTO（使用 Mapper）
     */
    public OrderDTO createOrder(CreateOrderCommand command) {
        log.info("【应用服务】创建订单，客户ID: {}", command.getCustomerId());
        
        // 1. 转换命令为领域对象
        Order order = orderMapper.toDomain(command);

        // 2. 生成订单号
        order.setOrderNumber(generateOrderNumber());
        order.setStatus(OrderStatus.CREATED);
        log.debug("生成订单号: {}", order.getOrderNumber());

        // 3. 业务规则校验
        if (!orderAdapter.checkInventory(order)) {
            throw new InsufficientInventoryException("商品库存不足");
        }
        log.debug("库存检查通过");

        // 4. 保存订单
        Order savedOrder = orderRepository.save(order);
        log.info("订单已保存到数据库，订单ID: {}", savedOrder.getId());

        // 5. 发布领域事件
        OrderCreatedEvent event = new OrderCreatedEvent(savedOrder);
        savedOrder.addDomainEvent(event);
        orderEventPublisher.publish(event);
        log.info("订单创建事件已发布");

        // 6. 转换为 DTO 返回
        return orderMapper.toDTO(savedOrder);
    }

    /**
     * 确认订单用例
     * 
     * 流程：
     * 1. 根据订单号查询订单
     * 2. 调用聚合根的确认方法
     * 3. 保存修改
     */
    public void confirmOrder(String orderNumber) {
        log.info("【应用服务】确认订单，订单号: {}", orderNumber);
        
        Order order = orderRepository.findByOrderNumber(orderNumber)
            .orElseThrow(() -> new OrderNotFoundException("订单不存在: " + orderNumber));

        order.confirm();
        orderRepository.save(order);
        log.info("订单已确认");
    }

    /**
     * 生成唯一订单号
     * 
     * 格式：ORD + 时间戳 + 随机数
     * 例：ORD202412141234567890
     */
    private String generateOrderNumber() {
        return "ORD" + LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMddHHmmss")) 
            + String.format("%04d", (int)(Math.random() * 10000));
    }
}