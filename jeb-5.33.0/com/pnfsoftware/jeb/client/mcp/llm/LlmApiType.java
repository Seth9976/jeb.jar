package com.pnfsoftware.jeb.client.mcp.llm;

import com.pnfsoftware.jeb.client.S;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public enum LlmApiType {
   UNKNOWN("", "", new String[0]),
   OPENAI("OpenAI", S.L("OpenAI chat completions and compatible"), new String[]{"/v1/chat/completions"}),
   ANTHROPIC("Anthropic", S.L("Anthropic Claude"), new String[]{"/v1/messages"}),
   GEMINI("Gemini", S.L("Google Gemini"), new String[]{"/v1beta/$MODEL:generateContent"});

   private final String name;
   private final String description;
   private final String[] supportedEndpoints;

   private LlmApiType(String var3, String var4, String[] var5) {
      this.name = var3;
      this.description = var4;
      this.supportedEndpoints = var5;
   }

   public String getName() {
      return this.name;
   }

   public String getDescription() {
      return this.description;
   }

   public static LlmApiType fromName(String var0) {
      for (LlmApiType var4 : values()) {
         if (var4.name.equalsIgnoreCase(var0)) {
            return var4;
         }
      }

      return UNKNOWN;
   }

   public List getSupportedEndpoints() {
      return Collections.unmodifiableList(Arrays.asList(this.supportedEndpoints));
   }

   @Override
   public String toString() {
      return this.name;
   }
}
