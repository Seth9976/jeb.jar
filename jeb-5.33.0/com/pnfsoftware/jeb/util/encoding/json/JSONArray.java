package com.pnfsoftware.jeb.util.encoding.json;

import java.io.IOException;
import java.io.StringWriter;
import java.io.Writer;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

public class JSONArray extends ArrayList implements JSONAware, JSONStreamAware {
   private static final long serialVersionUID = 3957988303675231981L;

   public JSONArray() {
   }

   public JSONArray(Collection var1) {
      super(var1);
   }

   public static void writeJSONString(Collection var0, Writer var1) throws IOException {
      if (var0 == null) {
         var1.write("null");
      } else {
         boolean var2 = true;
         Iterator var3 = var0.iterator();
         var1.write(91);

         while (var3.hasNext()) {
            if (var2) {
               var2 = false;
            } else {
               var1.write(44);
            }

            Object var4 = var3.next();
            if (var4 == null) {
               var1.write("null");
            } else {
               JSONValue.writeJSONString(var4, var1);
            }
         }

         var1.write(93);
      }
   }

   @Override
   public void writeJSONString(Writer var1) throws IOException {
      writeJSONString(this, var1);
   }

   public static String toJSONString(Collection var0) {
      StringWriter var1 = new StringWriter();

      try {
         writeJSONString(var0, var1);
         return var1.toString();
      } catch (IOException var3) {
         throw new RuntimeException(var3);
      }
   }

   public static void writeJSONString(byte[] var0, Writer var1) throws IOException {
      if (var0 == null) {
         var1.write("null");
      } else if (var0.length == 0) {
         var1.write("[]");
      } else {
         var1.write("[");
         var1.write(String.valueOf(var0[0]));

         for (int var2 = 1; var2 < var0.length; var2++) {
            var1.write(",");
            var1.write(String.valueOf(var0[var2]));
         }

         var1.write("]");
      }
   }

   public static String toJSONString(byte[] var0) {
      StringWriter var1 = new StringWriter();

      try {
         writeJSONString(var0, var1);
         return var1.toString();
      } catch (IOException var3) {
         throw new RuntimeException(var3);
      }
   }

   public static void writeJSONString(short[] var0, Writer var1) throws IOException {
      if (var0 == null) {
         var1.write("null");
      } else if (var0.length == 0) {
         var1.write("[]");
      } else {
         var1.write("[");
         var1.write(String.valueOf(var0[0]));

         for (int var2 = 1; var2 < var0.length; var2++) {
            var1.write(",");
            var1.write(String.valueOf(var0[var2]));
         }

         var1.write("]");
      }
   }

   public static String toJSONString(short[] var0) {
      StringWriter var1 = new StringWriter();

      try {
         writeJSONString(var0, var1);
         return var1.toString();
      } catch (IOException var3) {
         throw new RuntimeException(var3);
      }
   }

   public static void writeJSONString(int[] var0, Writer var1) throws IOException {
      if (var0 == null) {
         var1.write("null");
      } else if (var0.length == 0) {
         var1.write("[]");
      } else {
         var1.write("[");
         var1.write(String.valueOf(var0[0]));

         for (int var2 = 1; var2 < var0.length; var2++) {
            var1.write(",");
            var1.write(String.valueOf(var0[var2]));
         }

         var1.write("]");
      }
   }

   public static String toJSONString(int[] var0) {
      StringWriter var1 = new StringWriter();

      try {
         writeJSONString(var0, var1);
         return var1.toString();
      } catch (IOException var3) {
         throw new RuntimeException(var3);
      }
   }

   public static void writeJSONString(long[] var0, Writer var1) throws IOException {
      if (var0 == null) {
         var1.write("null");
      } else if (var0.length == 0) {
         var1.write("[]");
      } else {
         var1.write("[");
         var1.write(String.valueOf(var0[0]));

         for (int var2 = 1; var2 < var0.length; var2++) {
            var1.write(",");
            var1.write(String.valueOf(var0[var2]));
         }

         var1.write("]");
      }
   }

   public static String toJSONString(long[] var0) {
      StringWriter var1 = new StringWriter();

      try {
         writeJSONString(var0, var1);
         return var1.toString();
      } catch (IOException var3) {
         throw new RuntimeException(var3);
      }
   }

   public static void writeJSONString(float[] var0, Writer var1) throws IOException {
      if (var0 == null) {
         var1.write("null");
      } else if (var0.length == 0) {
         var1.write("[]");
      } else {
         var1.write("[");
         var1.write(String.valueOf(var0[0]));

         for (int var2 = 1; var2 < var0.length; var2++) {
            var1.write(",");
            var1.write(String.valueOf(var0[var2]));
         }

         var1.write("]");
      }
   }

   public static String toJSONString(float[] var0) {
      StringWriter var1 = new StringWriter();

      try {
         writeJSONString(var0, var1);
         return var1.toString();
      } catch (IOException var3) {
         throw new RuntimeException(var3);
      }
   }

   public static void writeJSONString(double[] var0, Writer var1) throws IOException {
      if (var0 == null) {
         var1.write("null");
      } else if (var0.length == 0) {
         var1.write("[]");
      } else {
         var1.write("[");
         var1.write(String.valueOf(var0[0]));

         for (int var2 = 1; var2 < var0.length; var2++) {
            var1.write(",");
            var1.write(String.valueOf(var0[var2]));
         }

         var1.write("]");
      }
   }

   public static String toJSONString(double[] var0) {
      StringWriter var1 = new StringWriter();

      try {
         writeJSONString(var0, var1);
         return var1.toString();
      } catch (IOException var3) {
         throw new RuntimeException(var3);
      }
   }

   public static void writeJSONString(boolean[] var0, Writer var1) throws IOException {
      if (var0 == null) {
         var1.write("null");
      } else if (var0.length == 0) {
         var1.write("[]");
      } else {
         var1.write("[");
         var1.write(String.valueOf(var0[0]));

         for (int var2 = 1; var2 < var0.length; var2++) {
            var1.write(",");
            var1.write(String.valueOf(var0[var2]));
         }

         var1.write("]");
      }
   }

   public static String toJSONString(boolean[] var0) {
      StringWriter var1 = new StringWriter();

      try {
         writeJSONString(var0, var1);
         return var1.toString();
      } catch (IOException var3) {
         throw new RuntimeException(var3);
      }
   }

   public static void writeJSONString(char[] var0, Writer var1) throws IOException {
      if (var0 == null) {
         var1.write("null");
      } else if (var0.length == 0) {
         var1.write("[]");
      } else {
         var1.write("[\"");
         var1.write(String.valueOf(var0[0]));

         for (int var2 = 1; var2 < var0.length; var2++) {
            var1.write("\",\"");
            var1.write(String.valueOf(var0[var2]));
         }

         var1.write("\"]");
      }
   }

   public static String toJSONString(char[] var0) {
      StringWriter var1 = new StringWriter();

      try {
         writeJSONString(var0, var1);
         return var1.toString();
      } catch (IOException var3) {
         throw new RuntimeException(var3);
      }
   }

   public static void writeJSONString(Object[] var0, Writer var1) throws IOException {
      if (var0 == null) {
         var1.write("null");
      } else if (var0.length == 0) {
         var1.write("[]");
      } else {
         var1.write("[");
         JSONValue.writeJSONString(var0[0], var1);

         for (int var2 = 1; var2 < var0.length; var2++) {
            var1.write(",");
            JSONValue.writeJSONString(var0[var2], var1);
         }

         var1.write("]");
      }
   }

   public static String toJSONString(Object[] var0) {
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
}
