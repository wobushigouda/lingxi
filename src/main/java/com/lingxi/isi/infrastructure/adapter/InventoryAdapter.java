package com.lingxi.isi.infrastructure.adapter;

import com.lingxi.isi.domain.service.InventoryService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

/**
 * 库存服务适配器
 * 基础设施层组件，负责调用外部库存服务
 * 
 * 这是适配器模式的实现：
 * - 定义在领域层：InventoryService（接口）
 * - 实现在基础设施层：InventoryAdapter（实现）
 * - 解耦领域逻辑与外部服务
 */
@Service
@RequiredArgsConstructor
@Slf4j
public class InventoryAdapter implements InventoryService {
    
    private final RestTemplate restTemplate;
    
    /**
     * 库存服务的基础 URL（可配置）
     */
    private static final String INVENTORY_SERVICE_URL = "http://inventory-service/api/inventory";

    @Override
    public boolean hasStock(String productId, int quantity) {
        // 调用库存服务 API 检查库存
        String url = INVENTORY_SERVICE_URL + "/check?productId=" + productId + "&quantity=" + quantity;
        try {
            Boolean result = restTemplate.getForObject(url, Boolean.class);
            return result != null && result;
        } catch (Exception e) {
            log.error("检查库存失败: productId={}, quantity={}", productId, quantity, e);
            // 库存服务不可用时，默认返回 false 以保守处理
            return false;
        }
    }

    @Override
    public boolean reserveStock(String productId, int quantity) {
        // 调用库存服务 API 预留库存
        String url = INVENTORY_SERVICE_URL + "/reserve";
        try {
            // POST 请求预留库存
            InventoryReserveRequest request = new InventoryReserveRequest(productId, quantity);
            Boolean result = restTemplate.postForObject(url, request, Boolean.class);
            return result != null && result;
        } catch (Exception e) {
            log.error("预留库存失败: productId={}, quantity={}", productId, quantity, e);
            return false;
        }
    }
    
    /**
     * 库存预留请求内部类
     */
    static class InventoryReserveRequest {
        public String productId;
        public int quantity;
        
        public InventoryReserveRequest(String productId, int quantity) {
            this.productId = productId;
            this.quantity = quantity;
        }
    }
}
