package com.pnfsoftware.jeb.util.encoding.json;

import java.io.IOException;
import java.io.StringWriter;
import java.io.Writer;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

public class JSONObject extends HashMap implements JSONAware, JSONStreamAware, Map {
   private static final long serialVersionUID = -503443796854799292L;

   public JSONObject() {
   }

   public JSONObject(Map var1) {
      super(var1);
   }

   public static void writeJSONString(Map var0, Writer var1) throws IOException {
      if (var0 == null) {
         var1.write("null");
      } else {
         boolean var2 = true;
         Iterator var3 = var0.entrySet().iterator();
         var1.write(123);

         while (var3.hasNext()) {
            if (var2) {
               var2 = false;
            } else {
               var1.write(44);
            }

            Entry var4 = (Entry)var3.next();
            var1.write(34);
            var1.write(escape(String.valueOf(var4.getKey())));
            var1.write(34);
            var1.write(58);
            JSONValue.writeJSONString(var4.getValue(), var1);
         }

         var1.write(125);
      }
   }

   @Override
   public void writeJSONString(Writer var1) throws IOException {
      writeJSONString(this, var1);
   }

   public static String toJSONString(Map var0) {
      StringWriter var1 = new StringWriter();

      try {
         writeJSONString(var0, var1);
         return var1.toString();
      } catch (IOException var3) {
         throw new RuntimeException(var3);
      }
   }

   @Override
   public String toJSONString() {
      return toJSONString(this);
   }

   @Override
   public String toString() {
      return this.toJSONString();
   }

   public static String toString(String var0, Object var1) {
      StringBuilder var2 = new StringBuilder();
      var2.append('"');
      if (var0 == null) {
         var2.append("null");
      } else {
         JSONValue.escape(var0, var2);
      }

      var2.append('"').append(':');
      var2.append(JSONValue.toJSONString(var1));
      return var2.toString();
   }

   public static String escape(String var0) {
      return JSONValue.escape(var0);
   }
}
