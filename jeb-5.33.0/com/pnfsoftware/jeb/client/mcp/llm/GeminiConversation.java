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

class GeminiConversation extends LlmConversationBuilder.AbstractConversation {
   private static final ILogger logger = GlobalLog.getLogger(GeminiConversation.class);

   @Override
   public List listModels() throws Exception {
      Builder var1 = new Builder();
      var1.header("x-goog-api-key", this.resolveApiKey());
      this.addCustomHeaders(var1);
      var1.url(this.settings.baseUrl + "/v1beta/models");
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

      for (Object var8 : (List)var12.get("models")) {
         String var9 = (String)((Map)var8).get("name");
         var6.add(var9);
      }

      return var6;
   }

   @Override
   public List send(List var1) throws Exception {
      Assert.a(var1 != null && !var1.isEmpty());
      LinkedHashMap var2 = new LinkedHashMap();
      var2.put("role", "user");
      ArrayList var3 = new ArrayList();
      var2.put("parts", var3);

      for (LlmConversationBuilder.Content var5 : var1) {
         if (var5 instanceof LlmConversationBuilder.ContentText var7) {
            var3.add(JsonUtil.map("text", var7.text));
         } else {
            if (!(var5 instanceof LlmConversationBuilder.ContentToolResponse var6)) {
               throw new RuntimeException();
            }

            var3.add(JsonUtil.map("functionResponse", JsonUtil.map("id", var6.id, "name", var6.name, "response", var6.response)));
         }
      }

      this.aggrMessages.add(var2);
      LinkedHashMap var32 = new LinkedHashMap();
      if (!Strings.isBlank(this.settings.systemPrompt)) {
         var32.put("system_instruction", JsonUtil.map("parts", List.of(JsonUtil.map("text", this.settings.systemPrompt))));
      }

      if (Strings.startsWith(this.settings.modelName, "models/gemini-2.5-flash", "models/gemini-2.5-pro")) {
         var32.put("generationConfig", JsonUtil.map("thinkingConfig", JsonUtil.map("thinkingBudget", 0)));
      }

      if (this.settings.tooldefs != null && !this.settings.tooldefs.isEmpty()) {
         ArrayList var33 = new ArrayList();

         for (LlmConversationBuilder.ToolDef var37 : this.settings.tooldefs) {
            Map var8 = JsonUtil.map("name", var37.name, "description", var37.description, "parameters", var37.input_schema);
            var33.add(var8);
         }

         var32.put("tools", List.of(JsonUtil.map("functionDeclarations", var33)));
      }

      var32.put("contents", this.aggrMessages);
      String var34 = JsonUtil.objectToJson(var32);
      MediaType var36 = MediaType.parse("application/json; charset=utf-8");
      RequestBody var38 = RequestBody.create(var36, var34);
      Builder var39 = new Builder();
      var39.header("x-goog-api-key", this.resolveApiKey());
      this.addCustomHeaders(var39);
      var39.url(this.settings.baseUrl + "/v1beta/" + this.settings.modelName + ":generateContent");
      var39.post(var38);
      Request var9 = var39.build();
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
         } catch (Throwable var30) {
            if (var14 != null) {
               try {
                  var14.close();
               } catch (Throwable var29) {
                  var30.addSuppressed(var29);
               }
            }

            throw var30;
         }

         if (var14 != null) {
            var14.close();
         }
      } finally {
         this.aggrRespTimeMs = this.aggrRespTimeMs + (System.currentTimeMillis() - var10);
      }

      var10000 = new Object[]{JsonUtil.formatJson(var12)};
      Map var40 = JsonUtil.jsonToMap(var12);
      Map var15 = (Map)var40.get("usageMetadata");
      this.lastTokensIn = (Integer)var15.get("promptTokenCount");
      this.lastTokensOut = (Integer)var15.get("totalTokenCount") - this.lastTokensIn;
      this.lastTokensInCacheHit = (Integer)var15.getOrDefault("cachedContentTokenCount", 0);
      this.totalTokensIn = this.totalTokensIn + this.lastTokensIn;
      this.totalTokensOut = this.totalTokensOut + this.lastTokensOut;
      this.totalTokensInCacheHit = this.totalTokensInCacheHit + this.lastTokensInCacheHit;
      Map var16 = (Map)((List)var40.get("candidates")).get(0);
      this.lastStopReason = (String)var16.get("finishReason");
      String var17 = this.lastStopReason;
      switch (var17) {
         case "FINISH_REASON_UNSPECIFIED":
         case "STOP":
         case "MAX_TOKENS":
         case "SAFETY":
         case "RECITATION":
         case "LANGUAGE":
         case "OTHER":
         case "BLOCKLIST":
         case "PROHIBITED_CONTENT":
         case "SPII":
         case "MALFORMED_FUNCTION_CALL":
         case "IMAGE_SAFETY":
         case "UNEXPECTED_TOOL_CALL":
         case "TOO_MANY_TOOL_CALLS":
            Map var41 = (Map)var16.get("content");
            if (!var41.get("role").equals("model")) {
               throw new RuntimeException("Unsupported response (was expecting: role:model)");
            } else {
               this.aggrMessages.add(var41);
               ArrayList var42 = new ArrayList();

               for (Object var20 : (List)var41.get("parts")) {
                  String var21 = (String)((Map)var20).get("text");
                  if (var21 != null) {
                     var42.add(new LlmConversationBuilder.ContentText(var21));
                  } else {
                     Object var22 = ((Map)var20).get("functionCall");
                     if (var22 == null) {
                        throw new RuntimeException("Unsupported content part");
                     }

                     String var23 = (String)((Map)var22).get("id");
                     String var24 = (String)((Map)var22).get("name");
                     Map var25 = (Map)((Map)var22).get("args");
                     var42.add(new LlmConversationBuilder.ContentToolUse(var23, var24, var25));
                  }
               }

               return var42;
            }
         default:
            throw new RuntimeException("The LLM stopped for an unsupported reason: " + this.lastStopReason);
      }
   }
}
