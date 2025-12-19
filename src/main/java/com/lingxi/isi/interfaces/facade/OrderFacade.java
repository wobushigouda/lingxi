package com.lingxi.isi.interfaces.facade;

import com.lingxi.isi.application.command.CreateOrderCommand;
import com.lingxi.isi.application.dto.OrderDTO;
import com.lingxi.isi.application.service.OrderApplicationService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * 订单门面服务
 * 接口层门面组件，为外部系统提供简化的订单操作入口
 * 
 * 职责：
 * - 封装复杂的内部服务调用
 * - 提供统一的业务操作接口
 * - 简化客户端调用逻辑
 */
@Service
@RequiredArgsConstructor
@Slf4j
public class OrderFacade {
    
    private final OrderApplicationService orderApplicationService;
    
    /**
     * 创建订单
     * 
     * @param command 创建订单命令
     * @return 订单信息
     */
    public OrderDTO createOrder(CreateOrderCommand command) {
        log.info("【门面】创建订单，客户ID: {}", command.getCustomerId());
        return orderApplicationService.createOrder(command);
    }
    
    /**
     * 确认订单
     * 
     * @param orderNumber 订单号
     */
    public void confirmOrder(String orderNumber) {
        log.info("【门面】确认订单，订单号: {}", orderNumber);
        orderApplicationService.confirmOrder(orderNumber);
    }
    
    /**
     * 根据订单号查询订单详情
     * 
     * @param orderNumber 订单号
     * @return 订单信息
     */
    public OrderDTO getOrderDetails(String orderNumber) {
        log.info("【门面】查询订单详情，订单号: {}", orderNumber);
        // 这里可以调用应用服务的查询方法（如果存在的话）
        // 目前仅作示例，实际需要在应用服务中实现对应方法
        throw new UnsupportedOperationException("查询订单详情功能尚未实现");
    }
}