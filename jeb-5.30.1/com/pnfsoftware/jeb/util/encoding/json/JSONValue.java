package com.pnfsoftware.jeb.util.encoding.json;

import java.io.IOException;
import java.io.Reader;
import java.io.StringReader;
import java.io.StringWriter;
import java.io.Writer;
import java.util.Collection;
import java.util.Map;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class JSONValue {
   @Deprecated
   public static Object parse(Reader var0) {
      try {
         JSONParser var1 = new JSONParser();
         return var1.parse(var0);
      } catch (Exception var2) {
         return null;
      }
   }

   @Deprecated
   public static Object parse(String var0) {
      StringReader var1 = new StringReader(var0);
      return parse(var1);
   }

   public static Object parseWithException(Reader var0) throws IOException, ParseException {
      JSONParser var1 = new JSONParser();
      return var1.parse(var0);
   }

   public static Object parseWithException(String var0) throws ParseException {
      JSONParser var1 = new JSONParser();
      return var1.parse(var0);
   }

   public static void writeJSONString(Object var0, Writer var1) throws IOException {
      if (var0 == null) {
         var1.write("null");
      } else if (var0 instanceof String) {
         var1.write(34);
         var1.write(escape((String)var0));
         var1.write(34);
      } else if (var0 instanceof Double) {
         if (!((Double)var0).isInfinite() && !((Double)var0).isNaN()) {
            var1.write(var0.toString());
         } else {
            var1.write("null");
         }
      } else if (!(var0 instanceof Float)) {
         if (var0 instanceof Number) {
            var1.write(var0.toString());
         } else if (var0 instanceof Boolean) {
            var1.write(var0.toString());
         } else if (var0 instanceof JSONStreamAware) {
            ((JSONStreamAware)var0).writeJSONString(var1);
         } else if (var0 instanceof JSONAware) {
            var1.write(((JSONAware)var0).toJSONString());
         } else if (var0 instanceof Map) {
            JSONObject.writeJSONString((Map)var0, var1);
         } else if (var0 instanceof Collection) {
            JSONArray.writeJSONString((Collection)var0, var1);
         } else if (var0 instanceof byte[]) {
            JSONArray.writeJSONString((byte[])var0, var1);
         } else if (var0 instanceof short[]) {
            JSONArray.writeJSONString((short[])var0, var1);
         } else if (var0 instanceof int[]) {
            JSONArray.writeJSONString((int[])var0, var1);
         } else if (var0 instanceof long[]) {
            JSONArray.writeJSONString((long[])var0, var1);
         } else if (var0 instanceof float[]) {
            JSONArray.writeJSONString((float[])var0, var1);
         } else if (var0 instanceof double[]) {
            JSONArray.writeJSONString((double[])var0, var1);
         } else if (var0 instanceof boolean[]) {
            JSONArray.writeJSONString((boolean[])var0, var1);
         } else if (var0 instanceof char[]) {
            JSONArray.writeJSONString((char[])var0, var1);
         } else if (var0 instanceof Object[]) {
            JSONArray.writeJSONString((Object[])var0, var1);
         } else {
            var1.write(var0.toString());
         }
      } else {
         if (!((Float)var0).isInfinite() && !((Float)var0).isNaN()) {
            var1.write(var0.toString());
         } else {
            var1.write("null");
         }
      }
   }

   public static String toJSONString(Object var0) {
      StringWriter var1 = new StringWriter();

      try {
         writeJSONString(var0, var1);
         return var1.toString();
      } catch (IOException var3) {
         throw new RuntimeException(var3);
      }
   }

   public static String escape(String var0) {
      if (var0 == null) {
         return null;
      } else {
         StringBuilder var1 = new StringBuilder();
         escape(var0, var1);
         return var1.toString();
      }
   }

   static void escape(String var0, StringBuilder var1) {
      int var2 = var0.length();

      for (int var3 = 0; var3 < var2; var3++) {
         char var4 = var0.charAt(var3);
         switch (var4) {
            case '\b':
               var1.append("\\b");
               continue;
            case '\t':
               var1.append("\\t");
               continue;
            case '\n':
               var1.append("\\n");
               continue;
            case '\f':
               var1.append("\\f");
               continue;
            case '\r':
               var1.append("\\r");
               continue;
            case '"':
               var1.append("\\\"");
               continue;
            case '/':
               var1.append("\\/");
               continue;
            case '\\':
               var1.append("\\\\");
               continue;
         }

         if (var4 >= 0 && var4 <= 31 || var4 >= 127 && var4 <= 159 || var4 >= 8192 && var4 <= 8447) {
            String var5 = Integer.toHexString(var4);
            var1.append("\\u");

            for (int var6 = 0; var6 < 4 - var5.length(); var6++) {
               var1.append('0');
            }

            var1.append(var5.toUpperCase());
         } else {
            var1.append(var4);
         }
      }
   }
}
