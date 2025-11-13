package com.pnfsoftware.jeb.client.mcp.llm;

import com.pnfsoftware.jeb.client.mcp.JsonUtil;
import com.pnfsoftware.jeb.util.base.Assert;
import com.pnfsoftware.jeb.util.format.Strings;
import com.pnfsoftware.jeb.util.logging.GlobalLog;
import com.pnfsoftware.jeb.util.logging.ILogger;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import okhttp3.Call;
import okhttp3.MediaType;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import okhttp3.Request.Builder;

class ClaudeConversation extends LlmConversationBuilder.AbstractConversation {
   private static final ILogger logger = GlobalLog.getLogger(ClaudeConversation.class);

   @Override
   public List listModels() throws Exception {
      Builder var1 = new Builder();
      var1.header("x-api-key", this.resolveApiKey());
      var1.header("anthropic-version", "2023-06-01");
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
      ArrayList var6 = new ArrayList();

      for (Object var8 : (List)var12.get("data")) {
         String var9 = (String)((Map)var8).get("id");
         var6.add(var9);
      }

      return var6;
   }

   @Override
   public List send(List var1) throws Exception {
      Assert.a(var1 != null && !var1.isEmpty());
      LinkedHashMap var2 = new LinkedHashMap();
      var2.put("role", "user");
      if (var1.size() == 1 && var1.get(0) instanceof LlmConversationBuilder.ContentText var3) {
         var2.put("content", var3.text);
      } else {
         ArrayList var30 = new ArrayList();

         for (LlmConversationBuilder.Content var6 : var1) {
            if (var6 instanceof LlmConversationBuilder.ContentText var8) {
               var30.add(JsonUtil.map("type", "text", "text", var8.text));
            } else {
               if (!(var6 instanceof LlmConversationBuilder.ContentToolResponse var7)) {
                  throw new RuntimeException();
               }

               var30.add(JsonUtil.map("type", "tool_result", "tool_use_id", var7.id, "content", var7.response));
            }
         }

         var2.put("content", var30);
      }

      this.aggrMessages.add(var2);
      Map var29 = JsonUtil.map("model", this.settings.modelName);
      var29.put("max_tokens", 20000);
      if (!Strings.isBlank(this.settings.systemPrompt)) {
         var29.put("system", this.settings.systemPrompt);
      }

      if (this.settings.tooldefs != null && !this.settings.tooldefs.isEmpty()) {
         ArrayList var31 = new ArrayList();

         for (LlmConversationBuilder.ToolDef var35 : this.settings.tooldefs) {
            Map var37 = JsonUtil.map("name", var35.name, "description", var35.description, "input_schema", var35.input_schema);
            if (var31.size() + 1 == this.settings.tooldefs.size()) {
               var37.put("cache_control", JsonUtil.map("type", "ephemeral"));
            }

            var31.add(var37);
         }

         var29.put("tools", var31);
      }

      var29.put("messages", this.aggrMessages);
      String var32 = JsonUtil.objectToJson(var29);
      MediaType var34 = MediaType.parse("application/json; charset=utf-8");
      RequestBody var36 = RequestBody.create(var34, var32);
      Builder var38 = new Builder();
      var38.header("x-api-key", this.resolveApiKey());
      var38.header("anthropic-version", "2023-06-01");
      this.addCustomHeaders(var38);
      var38.url(this.settings.baseUrl + "/v1/messages");
      var38.post(var36);
      Request var39 = var38.build();
      Object[] var10000 = new Object[]{JsonUtil.objectToJson(this.aggrMessages)};
      long var9 = System.currentTimeMillis();
      Call var12 = this.httpClient.newCall(var39);

      String var11;
      try {
         Response var13 = var12.execute();

         try {
            if (!var13.isSuccessful()) {
               throw new IOException(Strings.ff("Query failed with response code %d: %s", var13.code(), var13.body().string()));
            }

            var11 = var13.body().string();
         } catch (Throwable var27) {
            if (var13 != null) {
               try {
                  var13.close();
               } catch (Throwable var26) {
                  var27.addSuppressed(var26);
               }
            }

            throw var27;
         }

         if (var13 != null) {
            var13.close();
         }
      } finally {
         this.aggrRespTimeMs = this.aggrRespTimeMs + (System.currentTimeMillis() - var9);
      }

      var10000 = new Object[]{JsonUtil.formatJson(var11)};
      Map var40 = JsonUtil.jsonToMap(var11);
      Map var14 = (Map)var40.get("usage");
      this.lastTokensInCacheHit = (Integer)var14.get("cache_read_input_tokens");
      this.lastTokensIn = (Integer)var14.get("input_tokens") + (Integer)var14.get("cache_creation_input_tokens") + this.lastTokensInCacheHit;
      this.lastTokensOut = (Integer)var14.get("output_tokens");
      this.totalTokensInCacheHit = this.totalTokensInCacheHit + this.lastTokensInCacheHit;
      this.totalTokensIn = this.totalTokensIn + this.lastTokensIn;
      this.totalTokensOut = this.totalTokensOut + this.lastTokensOut;
      this.lastStopReason = (String)var40.get("stop_reason");
      String var15 = this.lastStopReason;
      switch (var15) {
         case "end_turn":
         case "stop_sequence":
         case "tool_use":
         case "pause_turn":
         case "refusal":
         case "max_tokens":
            if (var40.get("type").equals("message") && var40.get("role").equals("assistant")) {
               List var41 = (List)var40.get("content");
               this.aggrMessages.add(JsonUtil.map("role", "assistant", "content", var41));
               ArrayList var42 = new ArrayList();

               for (Object var18 : var41) {
                  String var19 = (String)((Map)var18).get("type");
                  if (var19.equals("text")) {
                     String var20 = (String)((Map)var18).get("text");
                     var42.add(new LlmConversationBuilder.ContentText(var20));
                  } else {
                     if (!var19.equals("tool_use")) {
                        throw new RuntimeException("Unsupported message type: " + var19);
                     }

                     String var43 = (String)((Map)var18).get("id");
                     String var21 = (String)((Map)var18).get("name");
                     Map var22 = (Map)((Map)var18).get("input");
                     var42.add(new LlmConversationBuilder.ContentToolUse(var43, var21, var22));
                  }
               }

               return var42;
            }

            throw new RuntimeException("Unsupported response (was expecting: type:message, role:assistant)");
         default:
            throw new RuntimeException("The LLM stopped for an unsupported reason: " + this.lastStopReason);
      }
   }
}
