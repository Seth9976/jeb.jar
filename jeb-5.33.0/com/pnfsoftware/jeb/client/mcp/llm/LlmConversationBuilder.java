package com.pnfsoftware.jeb.client.mcp.llm;

import com.pnfsoftware.jeb.client.S;
import com.pnfsoftware.jeb.util.base.Assert;
import com.pnfsoftware.jeb.util.format.Strings;
import com.pnfsoftware.jeb.util.net.Net;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import okhttp3.OkHttpClient;
import okhttp3.Request.Builder;

public class LlmConversationBuilder {
   private Net net;

   public LlmConversationBuilder() {
      this(null);
   }

   public LlmConversationBuilder(Net var1) {
      if (var1 == null) {
         var1 = new Net();
      }

      this.net = var1;
   }

   public ILlmConversation newConversation(LlmApiType var1, LlmSettings var2) {
      return this.newConversation(var1, var2, null);
   }

   public ILlmConversation newConversation(LlmApiType var1, LlmSettings var2, int[] var3) {
      Assert.a(var1 != null && var2 != null);

      Object var4 = switch (var1) {
         case OPENAI -> new GptConversation();
         case ANTHROPIC -> new ClaudeConversation();
         case GEMINI -> new GeminiConversation();
         default -> throw new RuntimeException("Unsupported API type: " + var1);
      };
      ((LlmConversationBuilder.AbstractConversation)var4).httpClient = this.net.buildClient(var2.baseUrl, null, var3);
      ((LlmConversationBuilder.AbstractConversation)var4).settings = var2;
      return (ILlmConversation)var4;
   }

   abstract static class AbstractConversation implements ILlmConversation {
      protected LlmSettings settings;
      protected OkHttpClient httpClient;
      protected int totalTokensInCacheHit;
      protected int totalTokensIn;
      protected int totalTokensOut;
      protected int lastTokensInCacheHit;
      protected int lastTokensIn;
      protected int lastTokensOut;
      protected String lastStopReason;
      protected long aggrRespTimeMs;
      protected List aggrMessages = new ArrayList();

      @Override
      public void close() {
         this.httpClient.dispatcher().executorService().shutdown();
         this.httpClient.connectionPool().evictAll();
         if (this.httpClient.cache() != null) {
            try {
               this.httpClient.cache().close();
            } catch (IOException var1) {
            }
         }
      }

      protected void addCustomHeaders(Builder var1) {
         if (this.settings.customHeaders != null) {
            for (Entry var3 : this.settings.customHeaders.entrySet()) {
               var1.header((String)var3.getKey(), (String)var3.getValue());
            }
         }
      }

      protected String resolveApiKey() {
         if (this.settings.apikey == null) {
            return null;
         } else if (this.settings.apikey.startsWith("$")) {
            String var1 = System.getenv(this.settings.apikey.substring(1));
            if (var1 == null) {
               throw new RuntimeException(Strings.ff(S.L("The environment variable '%s' was not resolved"), this.settings.apikey));
            } else {
               return var1;
            }
         } else {
            return this.settings.apikey;
         }
      }

      @Override
      public LlmConversationBuilder.Information getInformation() {
         return new LlmConversationBuilder.Information(
            this.totalTokensInCacheHit,
            this.totalTokensIn,
            this.totalTokensOut,
            this.lastTokensInCacheHit,
            this.lastTokensIn,
            this.lastTokensOut,
            this.aggrRespTimeMs
         );
      }

      @Override
      public List send(String var1) throws Exception {
         return this.send(List.of(new LlmConversationBuilder.ContentText(var1)));
      }

      @Override
      public List getMessages() {
         return Collections.unmodifiableList(this.aggrMessages);
      }

      @Override
      public void clearMessages() {
         this.aggrMessages.clear();
      }
   }

   public abstract static class Content {
   }

   public static class ContentText extends LlmConversationBuilder.Content {
      public String text;

      public ContentText(String var1) {
         this.text = var1;
      }

      @Override
      public String toString() {
         return "ContentText [text=" + this.text + "]";
      }
   }

   public static class ContentToolResponse extends LlmConversationBuilder.Content {
      public String id;
      public String name;
      public Object response;

      public ContentToolResponse(String var1, String var2, Object var3) {
         this.id = var1;
         this.name = var2;
         this.response = var3;
      }

      @Override
      public String toString() {
         return "ContentToolResponse [name=" + this.name + ", response=" + this.response + "]";
      }
   }

   public static class ContentToolUse extends LlmConversationBuilder.Content {
      public String id;
      public String name;
      public Map input_map;

      public ContentToolUse(String var1, String var2, Map var3) {
         this.id = var1;
         this.name = var2;
         this.input_map = var3;
      }

      @Override
      public String toString() {
         return "ContentToolUse [name=" + this.name + ", input_map=" + this.input_map + "]";
      }
   }

   public record Information() {
      private final int totalTokensInCacheHit;
      private final int totalTokensIn;
      private final int totalTokensOut;
      private final int lastTokensInCacheHit;
      private final int lastTokensIn;
      private final int lastTokensOut;
      private final long aggrRespTimeMs;

      public Information(int var1, int var2, int var3, int var4, int var5, int var6, long var7) {
         this.totalTokensInCacheHit = var1;
         this.totalTokensIn = var2;
         this.totalTokensOut = var3;
         this.lastTokensInCacheHit = var4;
         this.lastTokensIn = var5;
         this.lastTokensOut = var6;
         this.aggrRespTimeMs = var7;
      }
   }

   public static class ToolDef {
      public String name;
      public String description;
      public Object input_schema;

      public ToolDef(String var1, String var2, Object var3) {
         this.name = var1;
         this.description = var2;
         this.input_schema = var3;
      }
   }
}
