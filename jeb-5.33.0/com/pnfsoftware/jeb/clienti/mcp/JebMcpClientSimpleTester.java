package com.pnfsoftware.jeb.clienti.mcp;

import io.modelcontextprotocol.client.McpClient;
import io.modelcontextprotocol.client.McpSyncClient;
import io.modelcontextprotocol.client.transport.HttpClientSseClientTransport;
import io.modelcontextprotocol.spec.McpSchema.ClientCapabilities;
import io.modelcontextprotocol.spec.McpSchema.Implementation;
import io.modelcontextprotocol.spec.McpSchema.ListPromptsResult;
import io.modelcontextprotocol.spec.McpSchema.ListResourcesResult;
import io.modelcontextprotocol.spec.McpSchema.ListToolsResult;
import io.modelcontextprotocol.spec.McpSchema.Prompt;
import io.modelcontextprotocol.spec.McpSchema.Resource;
import io.modelcontextprotocol.spec.McpSchema.Tool;
import java.time.Duration;

public class JebMcpClientSimpleTester {
   static void pC(String var0, Object... var1) {
      System.out.format(var0, var1);
      System.out.println();
   }

   public static void main(String[] var0) {
      try {
         pC();
      } catch (Exception var2) {
         System.err.println("Exception: " + var2.getMessage());
         var2.printStackTrace();
      }
   }

   public static void pC() throws Exception {
      pC("Connecting to HTTP MCP server...");
      String var0 = "http://localhost:8080/mcp";
      HttpClientSseClientTransport var1 = HttpClientSseClientTransport.builder("http://localhost:8080").sseEndpoint("/mcp").build();
      McpSyncClient var2 = McpClient.sync(var1)
         .requestTimeout(Duration.ofMinutes(10L))
         .capabilities(ClientCapabilities.builder().roots(true).sampling().build())
         .build();
      pC("Initializing connection to " + var0 + "...");
      var2.initialize();
      pC("Connected successfully to HTTP MCP server!");
      pC(var2);
      var2.close();
      pC("Connection closed.");
   }

   static void pC(McpSyncClient var0) throws Exception {
      pC("\n=== Testing Connection ===");
      Implementation var1 = var0.getServerInfo();
      pC("Server: " + var1.name() + " v" + var1.version());
      ListToolsResult var2 = var0.listTools();
      pC("Available tools:");

      for (Tool var4 : var2.tools()) {
         pC("- %s: %s", var4.name(), var4.description());
      }

      ListPromptsResult var7 = var0.listPrompts();
      pC("Available prompts:");

      for (Prompt var5 : var7.prompts()) {
         pC("- %s: %s", var5.name(), var5.description());
      }

      pC("Available resources:");
      ListResourcesResult var9 = var0.listResources();

      for (Resource var6 : var9.resources()) {
         pC("- %s: %s", var6.name(), var6.description());
      }
   }
}
