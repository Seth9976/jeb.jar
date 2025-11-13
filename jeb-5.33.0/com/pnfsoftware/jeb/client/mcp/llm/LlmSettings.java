package com.pnfsoftware.jeb.client.mcp.llm;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class LlmSettings {
   public String baseUrl;
   public String modelName;
   public String apikey;
   public String reasoningEffort;
   public String systemPrompt;
   public List tooldefs = new ArrayList();
   public Map customHeaders = new LinkedHashMap();

   public LlmSettings(String var1, String var2, String var3) {
      this.baseUrl = var2;
      this.modelName = var3;
      this.apikey = var1;
   }
}
