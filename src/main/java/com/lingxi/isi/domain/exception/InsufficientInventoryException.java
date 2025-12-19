package com.lingxi.isi.domain.exception;

/**
 * 库存不足异常
 * 业务异常：当商品库存不足时抛出
 */
public class InsufficientInventoryException extends RuntimeException {
    
    public InsufficientInventoryException(String message) {
        super(message);
    }
    
    public InsufficientInventoryException(String message, Throwable cause) {
        super(message, cause);
    }
}
