package com.pnfsoftware.jeb.client.mcp;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import java.util.LinkedHashMap;
import java.util.Map;

public class JsonUtil {
   private static ObjectMapper mapper = new ObjectMapper();

   public static String formatJson(String var0) throws JsonProcessingException {
      Object var1 = mapper.readValue(var0, Object.class);
      return mapper.writerWithDefaultPrettyPrinter().writeValueAsString(var1);
   }

   public static String objectToJson(Object var0) throws JsonProcessingException {
      return mapper.writerWithDefaultPrettyPrinter().writeValueAsString(var0);
   }

   public static Map jsonToMap(String var0) throws JsonProcessingException {
      return (Map)mapper.readValue(var0, new JsonUtil$1());
   }

   public static Object jsonToObject(String var0) throws JsonProcessingException {
      return mapper.readValue(var0, Object.class);
   }

   public static Map pojoToMap(Object var0) {
      return (Map)mapper.convertValue(var0, new JsonUtil$2());
   }

   public static Object mapToPojo(Map var0, Class var1) {
      return mapper.convertValue(var0, var1);
   }

   public static Object jsonToPojo(String var0, Class var1) throws JsonProcessingException {
      Map var2 = (Map)mapper.readValue(var0, Map.class);
      return mapper.convertValue(var2, var1);
   }

   public static Map map(String var0, Object var1) {
      LinkedHashMap var2 = new LinkedHashMap();
      var2.put(var0, var1);
      return var2;
   }

   public static Map map(String var0, Object var1, String var2, Object var3) {
      LinkedHashMap var4 = new LinkedHashMap();
      var4.put(var0, var1);
      var4.put(var2, var3);
      return var4;
   }

   public static Map map(String var0, Object var1, String var2, Object var3, String var4, Object var5) {
      LinkedHashMap var6 = new LinkedHashMap();
      var6.put(var0, var1);
      var6.put(var2, var3);
      var6.put(var4, var5);
      return var6;
   }

   static {
      mapper.enable(SerializationFeature.INDENT_OUTPUT);
   }
}
