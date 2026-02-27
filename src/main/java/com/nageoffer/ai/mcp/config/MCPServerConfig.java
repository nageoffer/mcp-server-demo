package com.nageoffer.ai.mcp.config;

import com.nageoffer.ai.mcp.tools.EnterpriseTools;
import org.springframework.ai.tool.ToolCallbackProvider;
import org.springframework.ai.tool.method.MethodToolCallbackProvider;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MCPServerConfig {

    /**
     * 注册工具提供者
     * MethodToolCallbackProvider 会扫描 EnterpriseTools 中所有 @Tool 注解的方法，
     * 自动注册为 MCP 工具
     */
    @Bean
    public ToolCallbackProvider enterpriseToolProvider(EnterpriseTools enterpriseTools) {
        return MethodToolCallbackProvider.builder()
                .toolObjects(enterpriseTools)
                .build();
    }
}