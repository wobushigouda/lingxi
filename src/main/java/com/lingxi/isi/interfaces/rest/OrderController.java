package com.lingxi.isi.interfaces.rest;

import com.lingxi.isi.application.command.CreateOrderCommand;
import com.lingxi.isi.application.dto.OrderDTO;
import com.lingxi.isi.application.service.OrderApplicationService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * 订单 REST API 控制器
 * 接口层组件，处理订单相关的 HTTP 请求
 * 
 * 职责：
 * - 接收 HTTP 请求
 * - 调用应用服务处理业务
 * - 返回 JSON 响应
 */
@RestController
@RequestMapping("/api/orders")
@RequiredArgsConstructor
@Slf4j
public class OrderController {
    
    private final OrderApplicationService orderApplicationService;
    
    /**
     * 创建订单
     * 
     * HTTP POST /api/orders
     * 
     * @param command 创建订单命令（包含客户ID和订单项）
     * @return 订单 DTO（包含订单号、状态等）
     */
    @PostMapping
    public ResponseEntity<OrderDTO> createOrder(@Valid @RequestBody CreateOrderCommand command) {
        log.info("【REST】创建订单，客户ID: {}", command.getCustomerId());
        
        OrderDTO orderDTO = orderApplicationService.createOrder(command);
        
        return ResponseEntity.status(HttpStatus.CREATED).body(orderDTO);
    }
    
    /**
     * 确认订单
     * 
     * HTTP PUT /api/orders/{orderNumber}/confirm
     * 
     * @param orderNumber 订单号
     * @return 成功消息
     */
    @PutMapping("/{orderNumber}/confirm")
    public ResponseEntity<String> confirmOrder(@PathVariable String orderNumber) {
        log.info("【REST】确认订单，订单号: {}", orderNumber);
        
        orderApplicationService.confirmOrder(orderNumber);
        
        return ResponseEntity.ok("订单确认成功");
    }
}
