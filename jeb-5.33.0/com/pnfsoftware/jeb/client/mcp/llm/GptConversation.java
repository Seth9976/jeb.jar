package com.pnfsoftware.jeb.client.mcp.llm;

import com.pnfsoftware.jeb.client.mcp.JsonUtil;
import com.pnfsoftware.jeb.util.base.Assert;
import com.pnfsoftware.jeb.util.format.Strings;
import com.pnfsoftware.jeb.util.logging.GlobalLog;
import com.pnfsoftware.jeb.util.logging.ILogger;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import okhttp3.Call;
import okhttp3.MediaType;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import okhttp3.Request.Builder;

class GptConversation extends LlmConversationBuilder.AbstractConversation {
   private static final ILogger logger = GlobalLog.getLogger(GptConversation.class);

   @Override
   public List listModels() throws Exception {
      Builder var1 = new Builder();
      var1.header("Content-Type", "application/json");
      if (!Strings.isBlank(this.settings.apikey)) {
         var1.header("Authorization", "Bearer " + this.resolveApiKey());
      }

      this.addCustomHeaders(var1);
      var1.url(this.settings.baseUrl + "/v1/models");
      Request var2 = var1.build();
      Call var4 = this.httpClient.newCall(var2);
      Response var5 = var4.execute();

      String var3;
      try {
         if (!var5.isSuccessful()) {
            throw new IOException(Strings.ff("Query failed with response code %d: %s", var5.code(), var5.body().string()));
         }

         var3 = var5.body().string();
      } catch (Throwable var11) {
         if (var5 != null) {
            try {
               var5.close();
            } catch (Throwable var10) {
               var11.addSuppressed(var10);
            }
         }

         throw var11;
      }

      if (var5 != null) {
         var5.close();
      }

      Map var12 = JsonUtil.jsonToMap(var3);
      if (!"list".equals(var12.get("object"))) {
         throw new IOException("Unexpected response structure");
      } else {
         ArrayList var6 = new ArrayList();

         for (Object var8 : (List)var12.get("data")) {
            String var9 = (String)((Map)var8).get("id");
            var6.add(var9);
         }

         return var6;
      }
   }

   protected String getMaxTokensKey() {
      return this.settings.baseUrl.contains("openai.com/") ? "max_completion_tokens" : "max_tokens";
   }

   protected String getSystemRoleName() {
      return this.settings.baseUrl.contains("openai.com/") ? "developer" : "system";
   }

   @Override
   public List send(List var1) throws Exception {
      Assert.a(var1 != null && !var1.isEmpty());
      if (this.aggrMessages.isEmpty() && !Strings.isBlank(this.settings.systemPrompt)) {
         this.aggrMessages.add(JsonUtil.map("role", this.getSystemRoleName(), "content", this.settings.systemPrompt));
      }

      ArrayList var2 = new ArrayList();

      for (LlmConversationBuilder.Content var4 : var1) {
         if (var4 instanceof LlmConversationBuilder.ContentText var6) {
            var2.add(JsonUtil.map("role", "user", "content", var6.text));
         } else {
            if (!(var4 instanceof LlmConversationBuilder.ContentToolResponse var5)) {
               throw new RuntimeException();
            }

            var2.add(JsonUtil.map("role", "tool", "tool_call_id", var5.id, "content", var5.response));
         }
      }

      this.aggrMessages.addAll(var2);
      Map var35 = JsonUtil.map("model", this.settings.modelName);
      String var36 = this.settings.reasoningEffort;
      if (var36 == null && this.settings.modelName.startsWith("gpt-5")) {
         var36 = "minimal";
      }

      if (!Strings.isBlank(var36)) {
         var35.put("reasoning_effort", var36);
      }

      if (this.settings.tooldefs != null && !this.settings.tooldefs.isEmpty()) {
         ArrayList var37 = new ArrayList();

         for (LlmConversationBuilder.ToolDef var7 : this.settings.tooldefs) {
            var37.add(
               JsonUtil.map("type", "function", "function", JsonUtil.map("name", var7.name, "description", var7.description, "parameters", var7.input_schema))
            );
         }

         var35.put("tools", var37);
      }

      var35.put("messages", this.aggrMessages);
      String var38 = JsonUtil.objectToJson(var35);
      MediaType var40 = MediaType.parse("application/json; charset=utf-8");
      RequestBody var41 = RequestBody.create(var40, var38);
      Builder var8 = new Builder();
      var8.header("Content-Type", "application/json");
      if (!Strings.isBlank(this.settings.apikey)) {
         var8.header("Authorization", "Bearer " + this.resolveApiKey());
      }

      this.addCustomHeaders(var8);
      var8.url(this.settings.baseUrl + "/v1/chat/completions");
      var8.post(var41);
      Request var9 = var8.build();
      Object[] var10000 = new Object[]{JsonUtil.objectToJson(this.aggrMessages)};
      long var10 = System.currentTimeMillis();
      Call var13 = this.httpClient.newCall(var9);

      String var12;
      try {
         Response var14 = var13.execute();

         try {
            if (!var14.isSuccessful()) {
               throw new IOException(Strings.ff("Query failed with response code %d: %s", var14.code(), var14.body().string()));
            }

            var12 = var14.body().string();
         } catch (Throwable var33) {
            if (var14 != null) {
               try {
                  var14.close();
               } catch (Throwable var32) {
                  var33.addSuppressed(var32);
               }
            }

            throw var33;
         }

         if (var14 != null) {
            var14.close();
         }
      } finally {
         this.aggrRespTimeMs = this.aggrRespTimeMs + (System.currentTimeMillis() - var10);
      }

      var10000 = new Object[]{JsonUtil.formatJson(var12)};
      Map var42 = JsonUtil.jsonToMap(var12);
      Map var15 = (Map)var42.get("usage");
      this.lastTokensInCacheHit = 0;
      Map var16 = (Map)var15.get("prompt_tokens_details");
      if (var16 != null) {
         this.lastTokensInCacheHit = (Integer)var16.getOrDefault("cached_tokens", 0);
      }

      this.lastTokensIn = (Integer)var15.get("prompt_tokens");
      this.lastTokensOut = (Integer)var15.get("completion_tokens");
      this.totalTokensInCacheHit = this.totalTokensInCacheHit + this.lastTokensInCacheHit;
      this.totalTokensIn = this.totalTokensIn + this.lastTokensIn;
      this.totalTokensOut = this.totalTokensOut + this.lastTokensOut;
      Map var17 = (Map)((List)var42.get("choices")).get(0);
      this.lastStopReason = (String)var17.get("finish_reason");
      String var18 = this.lastStopReason;
      switch (var18) {
         case "stop":
         case "length":
         case "content_filter":
         case "tool_calls":
         case "model_length":
         case "error":
            Map var43 = (Map)var17.get("message");
            if (!var43.get("role").equals("assistant")) {
               throw new RuntimeException("Unsupported response (was expecting: type:message, role:assistant)");
            } else {
               this.aggrMessages.add(var43);
               ArrayList var44 = new ArrayList();
               String var20 = (String)var43.get("content");
               if (var20 != null) {
                  var44.add(new LlmConversationBuilder.ContentText(var20));
               }

               List var21 = (List)var43.get("tool_calls");
               if (var21 != null) {
                  for (Object var23 : var21) {
                     String var24 = (String)((Map)var23).get("id");
                     String var25 = (String)((Map)var23).get("type");
                     Assert.a(var25 == null || var25.equals("function"));
                     Map var26 = (Map)((Map)var23).get("function");
                     String var27 = (String)var26.get("name");
                     String var28 = (String)var26.get("arguments");
                     var44.add(new LlmConversationBuilder.ContentToolUse(var24, var27, JsonUtil.jsonToMap(var28)));
                  }
               }

               return var44;
            }
         default:
            throw new RuntimeException("The LLM stopped for an unsupported reason: " + this.lastStopReason);
      }
   }
}
