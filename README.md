# MCP Server Demo

基于 Spring AI 的 MCP（Model Context Protocol）服务端示例项目，使用 `stdio` 传输方式对外提供企业工具能力。

## 项目特性

- Spring Boot 3.5 + Spring AI MCP Server
- 非 Web 服务（`web-application-type: none`），通过标准输入输出与 MCP 客户端通信
- 已内置 2 个示例工具：
- `getUserAnnualLeave`：查询用户年假余额
- `getOrderStatus`：查询订单物流状态

## 技术栈

- Java 17
- Maven Wrapper（`./mvnw`）
- Spring Boot `3.5.3`
- Spring AI MCP Server Starter `1.0.0`

## 目录结构

```text
.
├── pom.xml
├── src/main/java/com/nageoffer/ai/mcp
│   ├── MCPServerApplication.java
│   ├── config/MCPServerConfig.java
│   └── tools/EnterpriseTools.java
└── src/main/resources/application.yaml
```

## 环境要求

- JDK 17 及以上（必须）
- 建议使用 Maven Wrapper（无需单独安装 Maven）

检查版本：

```bash
java -version
./mvnw -v
```

## 快速开始

1. 使用 JDK 17+

```bash
export JAVA_HOME=/path/to/jdk-17
export PATH="$JAVA_HOME/bin:$PATH"
```

2. 编译打包

```bash
./mvnw clean package
```

3. 启动服务（任选其一）

```bash
./mvnw spring-boot:run
```

```bash
java -jar target/mcp-server-demo-1.0.0.jar
```

日志文件默认输出到：

```text
./logs/mcp-server.log
```

## MCP 客户端接入示例

在支持 MCP `stdio` 的客户端配置中加入：

```json
{
  "mcpServers": {
    "enterprise-assistant": {
      "command": "java",
      "args": [
        "-jar",
        "/absolute/path/to/mcp-server-demo/target/mcp-server-demo-1.0.0.jar"
      ]
    }
  }
}
```

## 已实现工具

### 1) `getUserAnnualLeave`

- 入参：`userId`（如 `user_12345`）
- 作用：返回用户年假总天数、已使用天数、剩余天数

调用参数示例：

```json
{
  "userId": "user_12345"
}
```

返回示例：

```json
{
  "userId": "user_12345",
  "remainingDays": 5,
  "totalDays": 10,
  "usedDays": 5,
  "year": 2026
}
```

### 2) `getOrderStatus`

- 入参：`orderId`（如 `ORD-12345`）
- 作用：返回订单物流状态、当前位置、预计送达时间等

调用参数示例：

```json
{
  "orderId": "ORD-12345"
}
```

返回示例：

```json
{
  "orderId": "ORD-12345",
  "status": "运输中",
  "location": "北京市朝阳区分拨中心",
  "estimatedDelivery": "2026-03-01",
  "carrier": "顺丰速运",
  "trackingNumber": "SF1234567890"
}
```

## 开发说明

新增 MCP 工具时，在 `EnterpriseTools` 中添加 `@Tool` 方法即可，`MCPServerConfig` 会通过 `MethodToolCallbackProvider` 自动注册。

## 常见问题

报错示例：

```text
... has been compiled by a more recent version of the Java Runtime (class file version 61.0)
```

原因：当前 Java 版本低于 17（例如 Java 8）。

解决：切换到 JDK 17+ 后重新执行构建与启动命令。
