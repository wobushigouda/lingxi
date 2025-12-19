package com.lingxi.isi.domain.service;

/**
 * 库存服务接口
 * 领域层接口，定义库存业务操作的契约
 * 
 * 实现在基础设施层（InventoryAdapter）
 * 这是适配器模式和依赖倒置原则的应用
 */
public interface InventoryService {
    
    /**
     * 检查库存是否充足
     * 
     * @param productId 商品ID
     * @param quantity 需要的数量
     * @return true 表示库存充足，false 表示库存不足
     */
    boolean hasStock(String productId, int quantity);
    
    /**
     * 预留库存
     * 
     * @param productId 商品ID
     * @param quantity 预留的数量
     * @return true 表示预留成功，false 表示预留失败
     */
    boolean reserveStock(String productId, int quantity);
}
