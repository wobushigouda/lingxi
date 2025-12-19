package com.lingxi.isi.domain.exception;

/**
 * 订单未找到异常
 * 业务异常：当根据条件找不到订单时抛出
 */
public class OrderNotFoundException extends RuntimeException {
    
    public OrderNotFoundException(String message) {
        super(message);
    }
    
    public OrderNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }
}
