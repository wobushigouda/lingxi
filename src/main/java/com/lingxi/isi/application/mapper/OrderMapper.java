package com.lingxi.isi.application.mapper;

import com.lingxi.isi.application.command.OrderItemCommand;
import com.lingxi.isi.application.dto.OrderDTO;
import com.lingxi.isi.application.dto.OrderItemDTO;
import com.lingxi.isi.domain.model.aggregate.Order;
import com.lingxi.isi.domain.model.aggregate.OrderStatus;
import com.lingxi.isi.domain.model.entity.OrderItem;
import com.lingxi.isi.domain.model.vo.Money;
import com.lingxi.isi.application.command.CreateOrderCommand;
import org.springframework.stereotype.Component;

import java.util.Currency;
import java.util.stream.Collectors;

/**
 * 订单映射器
 * 应用层组件，负责在领域对象和 DTO 之间进行转换
 * 
 * 职责：
 * - Command → Domain（命令转换为领域对象）
 * - Domain → DTO（领域对象转换为数据传输对象）
 */
@Component
public class OrderMapper {
    
    /**
     * 将命令对象转换为领域聚合根
     * 
     * CreateOrderCommand → Order
     */
    public Order toDomain(CreateOrderCommand command) {
        Order order = new Order();
        order.setCustomerId(command.getCustomerId());
        
        if (command.getItems() != null) {
            for (OrderItemCommand itemCmd : command.getItems()) {
                OrderItem orderItem = new OrderItem();
                orderItem.setProductId(itemCmd.getProductId());
                orderItem.setProductName(itemCmd.getProductName());
                orderItem.setQuantity(itemCmd.getQuantity());
                orderItem.setUnitPrice(new Money(itemCmd.getUnitPrice(), Currency.getInstance("CNY")));
                order.addItem(orderItem);
            }
        }
        
        return order;
    }
    
    /**
     * 将领域聚合根转换为 DTO
     * 
     * Order → OrderDTO
     */
    public OrderDTO toDTO(Order order) {
        OrderDTO dto = new OrderDTO();
        dto.setId(order.getId());
        dto.setOrderNumber(order.getOrderNumber());
        dto.setCustomerId(order.getCustomerId());
        dto.setStatus(order.getStatus() != null ? order.getStatus().name() : null);
        
        if (order.getTotalAmount() != null) {
            dto.setTotalAmount(order.getTotalAmount().getAmount());
            dto.setCurrency(order.getTotalAmount().getCurrency().getCurrencyCode());
        }
        
        if (order.getItems() != null) {
            dto.setItems(order.getItems().stream()
                .map(this::toItemDTO)
                .collect(Collectors.toList()));
        }
        
        return dto;
    }
    
    /**
     * 将订单项实体转换为 DTO
     * 
     * OrderItem → OrderItemDTO
     */
    private OrderItemDTO toItemDTO(OrderItem item) {
        OrderItemDTO dto = new OrderItemDTO();
        dto.setId(item.getId());
        dto.setProductId(item.getProductId());
        dto.setProductName(item.getProductName());
        dto.setQuantity(item.getQuantity());
        
        if (item.getUnitPrice() != null) {
            dto.setUnitPrice(item.getUnitPrice().getAmount());
            dto.setCurrency(item.getUnitPrice().getCurrency().getCurrencyCode());
            dto.setTotalPrice(item.calculateTotal().getAmount());
        }
        
        return dto;
    }
}
