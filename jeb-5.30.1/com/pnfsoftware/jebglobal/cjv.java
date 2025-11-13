package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.units.code.android.DexUtil;
import com.pnfsoftware.jeb.core.units.code.asm.memory.IVirtualMemory;
import com.pnfsoftware.jeb.core.units.code.asm.memory.MemoryException;
import com.pnfsoftware.jeb.util.collect.BytePipe;

public class cjv {
   public static String q(String var0) {
      switch (var0) {
         case "V":
            return "void";
         case "Z":
            return "jboolean";
         case "B":
            return "jbyte";
         case "C":
            return "jchar";
         case "S":
            return "jshort";
         case "I":
            return "jint";
         case "J":
            return "jlong";
         case "F":
            return "jfloat";
         case "D":
            return "jdouble";
         default:
            if (!var0.startsWith("[") && !var0.startsWith("L")) {
               throw new RuntimeException();
            } else {
               return "jobject";
            }
      }
   }

   public static String RF(String var0) {
      return var0.startsWith("[") ? var0 : "L" + var0 + ";";
   }

   public static String q(IVirtualMemory var0, long var1) {
      int var3 = 0;
      long var4 = var1;
      BytePipe var6 = new BytePipe(64);

      try {
         while (var3 < 65536) {
            byte var7 = var0.readByte(var4);
            var6.append(var7);
            if (var7 == 0) {
               break;
            }

            var3++;
            var4++;
         }
      } catch (MemoryException var8) {
         return null;
      }

      byte[] var9 = var6.getAll();
      return DexUtil.bytearrayMUTF8ToString(var9, 0, null);
   }
}
