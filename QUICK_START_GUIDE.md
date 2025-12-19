# 🎯 项目重构 - 快速上手指南

## 📌 你现在有什么？

一个**完全按照 DDD（领域驱动设计）标准构建**的 Spring Boot 订单管理系统。

---

## 🗂️ 项目结构一图通（最重要！）

```
你的项目（ISI）
│
├── 🎪 顾客端
│   ├─ 发送请求：POST /api/orders
│   └─ 收到响应：订单信息 JSON
│       ↓
├── 🛣️ interfaces/rest（接口层 - 前台）
│   └─ OrderController
│       职责：接待顾客，处理请求
│       ↓
├── 🧠 application（应用层 - 店长）
│   ├─ OrderApplicationService（协调所有事）
│   ├─ OrderMapper（数据翻译）
│   ├─ Command（接收的订单单据）
│   └─ DTO（给顾客看的订单单据）
│       ↓
├── 💼 domain（领域层 - 老板的规则）
│   ├─ Order（订单对象）
│   ├─ OrderItem（订单项）
│   ├─ Money（金钱）
│   ├─ OrderDomainService（订单规则）
│   └─ OrderRepository（订单的存取方式）
│       ↓
├── 🔧 infrastructure（基础设施层 - 技术实现）
│   ├─ persistence/（数据库）
│   │   └─ JpaOrderRepository（真正操作数据库）
│   ├─ messaging/（消息）
│   │   └─ OrderEventPublisher（发送事件）
│   ├─ service/（外部服务）
│   │   └─ InventoryAdapter（调用库存系统）
│   └─ config/（配置）
│
└─ 💾 数据库/消息队列/外部系统
```

---

## 📚 各层简单对应关系

### **假如你想...**

| 需求 | 去哪个文件夹 | 例子 |
|-----|-----------|------|
| 📱 添加新的 HTTP 接口 | `interfaces/rest/` | 在 OrderController 中添加新方法 |
| 🔄 修改业务流程 | `application/service/` | 修改 OrderApplicationService |
| 📋 修改订单规则 | `domain/` | 修改 Order 或 OrderDomainService |
| 💾 修改数据库操作 | `infrastructure/persistence/` | 修改 JpaOrderRepository |
| 🌐 调用外部服务 | `infrastructure/service/` | 修改或新增 Adapter |
| 📢 处理事件 | `infrastructure/messaging/` | 修改 OrderEventPublisher |

---

## 🔀 一个请求的完整旅程

```
【请求来临】
用户：POST /api/orders，创建订单
   ↓
【第1站：接口层】
OrderController（接待员）
   听取：CreateOrderCommand（订单单据）
   做法：调用应用服务
   ↓
【第2站：应用层】
OrderApplicationService（店长）
   做法1：用 OrderMapper 把单据转成"订单对象"
   做法2：检查库存（调用 OrderDomainService）
   做法3：保存订单（调用 OrderRepository）
   做法4：发送事件（调用 OrderEventPublisher）
   做法5：用 OrderMapper 把"订单对象"转成"响应单据"
   ↓
【第3站：领域层】
Order（真正的订单对象）
   做什么：包含业务规则
   特点：不会操作数据库
   ↓
【第4站：基础设施层】
JpaOrderRepository（真正操作数据库）
InventoryAdapter（真正调用库存系统）
OrderEventPublisher（真正发送消息）
   ↓
【最后】返回 OrderDTO（响应数据）给用户
```

---

## 🎁 你拥有的核心类

### 最重要的 5 个类

1. **Order.java** - 订单本身（所有订单逻辑都在这）
2. **OrderApplicationService.java** - 处理流程的大脑
3. **OrderController.java** - 用户请求的入口
4. **JpaOrderRepository.java** - 数据库操作
5. **InventoryAdapter.java** - 调用库存系统

### 数据流转的 3 个转换

1. **CreateOrderCommand** → **Order**（OrderMapper 做的）
2. **Order** → **OrderDTO**（OrderMapper 做的）
3 **String**（JSON）← → **OrderDTO**（Spring 自动做）

---

## 🚀 启动项目三步走

### 第 1 步：修改数据库连接（重要！）
编辑：`src/main/resources/application.properties`

```properties
# 改这几行为你的 MySQL 信息
spring.datasource.username=root        # 你的 MySQL 用户名
spring.datasource.password=你的密码      # 你的 MySQL 密码
```

### 第 2 步：创建数据库
```bash
mysql -u root -p
CREATE DATABASE order_system;
```

### 第 3 步：启动应用
```bash
mvn clean spring-boot:run
# 或在 IDE 中右键 → Run
```

启动成功后，你会看到：
```
Started IsiApplication in X.XXX seconds
```

---

## 🧪 测试 API 的最简单方式

### 使用 curl（复制粘贴运行）

```bash
# 创建订单
curl -X POST http://localhost:8080/api/orders \
  -H "Content-Type: application/json" \
  -d '{"customerId":"CUST001","items":[{"productId":"P1","productName":"手机","quantity":1,"unitPrice":3999}]}'

# 预期看到：
# {"id":1,"orderNumber":"ORD...","customerId":"CUST001","status":"CREATED",...}
```

### 使用 Postman（图形界面）

1. 打开 Postman
2. 选择 POST 方法
3. URL：`http://localhost:8080/api/orders`
4. Headers：`Content-Type: application/json`
5. Body：
```json
{
  "customerId": "CUST001",
  "items": [
    {
      "productId": "P1",
      "productName": "手机",
      "quantity": 1,
      "unitPrice": 3999
    }
  ]
}
```
6. 点击 Send

---

## 📊 理解"四层"的最简单比喻

```
想象一家餐厅：

🎪 interfaces/rest  = 前台（接待顾客）
   ↓
🧠 application      = 店长（协调和组织）
   ↓
💼 domain           = 厨房规则（怎样做菜才合格）
   ↓
🔧 infrastructure   = 技术支持（冰箱、炉灶、配送）
```

- **前台** 只负责接待，不懂做菜
- **店长** 协调前台和厨房，但自己不做菜
- **厨房规则** 定义做菜的标准，但不操作工具
- **技术支持** 提供工具，但不决定怎样做菜

---

## 🔑 记住最重要的 3 点

### 1️⃣ **依赖方向是单向的**
```
Request →  interfaces/rest 
       →  application 
       →  domain 
       →  infrastructure 
       →  Response
```
**不会反向！** infrastructure 不会调用 application

### 2️⃣ **每层有专门的职责**
```
interfaces     处理 HTTP
application    编排流程
domain         业务规则
infrastructure 技术实现
```
**不会混杂！** 业务规则只在 domain，数据库操作只在 infrastructure

### 3️⃣ **数据会被转换**
```
JSON → Command → Order → OrderDTO → JSON
```
**Command** 和 **OrderDTO** 是 DTO（数据传输对象），用来在层与层间传递

---

## 📖 推荐阅读顺序

1. **本文件** ← 你正在读（5分钟理解整体）
2. **RESTRUCTURE_COMPLETE.md** ← 详细的架构解释（15分钟深入）
3. **FOLDER_EXPLANATION.md** ← 通俗易懂的文件夹说明（10分钟）
4. **PROJECT_STRUCTURE.md** ← 专业的架构设计（20分钟专业）
5. **代码本身** ← 查看具体实现（随时查阅）

---

## 🎓 DDD 这四个字什么意思？

**DDD = Domain-Driven Design = 领域驱动设计**

简单说：
- **Domain** = 业务领域（订单系统）
- **Driven** = 以...为驱动
- **Design** = 设计方式

意思就是：**设计要以业务为中心，而不是以技术为中心**

你的项目就是这样设计的：
- 📋 核心业务（订单、库存）在 domain 层最清晰
- 🔧 技术细节（数据库、HTTP）在 infrastructure 层被隐藏
- 🧠 流程协调（怎样处理一个订单）在 application 层明确

---

## ❓ 常见问题速答

### Q: 为什么要分这么多层？
A: 这样做的好处是：
- 代码更清晰（知道每个文件放在哪）
- 修改更安全（修改一层不会影响其他层）
- 测试更容易（可以单独测试每层）
- 复用更方便（domain 层可以被多个 application 使用）

### Q: 我想添加一个新的 API，应该怎么做？
A: 4 步：
1. 在 `interfaces/rest/OrderController.java` 中添加新方法
2. 在 `application/service/OrderApplicationService.java` 中添加业务逻辑
3. 如果涉及数据库，在 `infrastructure/persistence/` 中添加查询
4. 测试！

### Q: 数据库操作为什么在 infrastructure？
A: 因为数据库是一种"技术选择"。如果你将来想从 MySQL 换成 MongoDB，只需要修改 infrastructure 层，domain 层完全不受影响。

### Q: DTO 和 Domain Model 有什么区别？
A: 
- **Domain Model**（Order）= 业务对象，包含业务规则和逻辑
- **DTO**（OrderDTO）= 数据传输对象，只包含数据，用于传输

一个有"行为"，一个只有"数据"。

---

## 🎯 现在就可以做的事

✅ 启动应用  
✅ 调用 API  
✅ 查看日志  
✅ 修改业务规则（改 domain 层）  
✅ 添加新的数据库字段（改 domain/model 和 infrastructure）  
✅ 添加新的 API（改 interfaces 和 application）  

---

## 💡 提示和最佳实践

### ✅ 要这样做

```
【有新的业务规则】
→ 放在 domain 层（Order.java 或 OrderDomainService.java）

【有新的 API】
→ 在 interfaces/rest 添加接口
→ 在 application/service 添加逻辑

【要调用外部服务】
→ 在 infrastructure/service 创建 Adapter
→ 在 domain/service 中定义接口

【有新的异常】
→ 放在 domain/exception
```

### ❌ 不要这样做

```
❌ 在 infrastructure 中写业务逻辑
❌ 在 domain 中操作数据库
❌ 在 interfaces 中处理业务规则
❌ 让 infrastructure 直接调用 interfaces
```

---

## 🏁 总结

你现在有一个：
- ✅ **清晰的项目结构** - 知道代码放在哪
- ✅ **完整的 DDD 架构** - 符合企业级标准
- ✅ **易于维护的代码** - 修改安全、添加方便
- ✅ **可扩展的框架** - 为未来功能预留空间
- ✅ **完整的文档** - 新成员能快速上手

**现在可以：**
1. 🚀 启动应用测试
2. 📝 查看日志理解流程
3. 💪 添加新功能（知道怎么放）
4. 🧪 编写测试（清晰的职责）
5. 📈 扩展系统（完善的架构）

---

**恭喜！你拥有了一个专业级的 DDD 项目！** 🎉

有任何问题，查看其他文档或代码中的注释就能找到答案。

**开始编码吧！** 💪
