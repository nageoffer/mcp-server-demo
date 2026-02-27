package com.nageoffer.ai.mcp.tools;

import org.springframework.ai.tool.annotation.Tool;
import org.springframework.ai.tool.annotation.ToolParam;
import org.springframework.stereotype.Service;

/**
 * 企业知识库助手工具集
 * 每个 @Tool 方法会自动注册为一个 MCP 工具
 */
@Service
public class EnterpriseTools {

    /**
     * 查询用户年假余额
     */
    @Tool(description = "查询用户的年假余额，包括总天数、已使用天数、剩余天数。当用户询问年假、假期余额、还有多少天假等问题时使用此工具。")
    public String getUserAnnualLeave(
            @ToolParam(description = "用户 ID，例如 user_12345") String userId) {

        // 实际项目中这里调用 HR 系统 API
        // 这里用 mock 数据演示
        return String.format("""
                {
                    "userId": "%s",
                    "remainingDays": 5,
                    "totalDays": 10,
                    "usedDays": 5,
                    "year": 2026
                }
                """, userId);
    }

    /**
     * 查询订单状态
     */
    @Tool(description = "查询订单的物流状态和详细信息。当用户询问订单状态、物流进度、快递到哪了等问题时使用此工具。")
    public String getOrderStatus(
            @ToolParam(description = "订单号，例如 ORD-12345") String orderId) {

        // 实际项目中这里调用订单系统 API
        return String.format("""
                {
                    "orderId": "%s",
                    "status": "运输中",
                    "location": "北京市朝阳区分拨中心",
                    "estimatedDelivery": "2026-03-01",
                    "carrier": "顺丰速运",
                    "trackingNumber": "SF1234567890"
                }
                """, orderId);
    }
}