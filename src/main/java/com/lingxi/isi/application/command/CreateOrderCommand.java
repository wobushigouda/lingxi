package com.lingxi.isi.application.command;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import lombok.Data;

import java.util.List;

// 命令对象（封装创建订单的请求参数）
@Data
public class CreateOrderCommand {
   
    @NotBlank(message = "客户ID不能为空")
    private String customerId;

    @NotEmpty(message = "订单项不能为空")
    private List<OrderItemCommand> items;
}
