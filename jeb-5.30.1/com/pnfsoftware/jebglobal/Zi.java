package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.units.code.asm.processor.RegisterLayoutBridge;
import com.pnfsoftware.jeb.core.units.code.asm.processor.arch.AbstractRegisterData;
import com.pnfsoftware.jeb.util.collect.BytePipe;
import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.Map;

public class Zi {
   public static void q(AbstractRegisterData var0, kW var1, RegisterLayoutBridge var2) throws IOException {
      for (int var4 : var1.xK()) {
         byte[] var5 = var1.q(var4);
         if (var5 != null) {
            Integer var6 = var2.convertSource(var4);
            if (var6 != null) {
               var5 = var2.convertSourceValue(var4, var6, var5);
               if (!var0.setValue(var6, var5)) {
                  throw new IOException("Cannot load register data for: " + var0.getName(var6));
               }
            }
         }
      }

      var0.clearAllDirty();
   }

   public static byte[] RF(AbstractRegisterData var0, kW var1, RegisterLayoutBridge var2) {
      BytePipe var3 = new BytePipe();

      for (int var5 : var1.xK()) {
         Integer var6 = var2.convertSource(var5);
         if (var6 == null) {
            break;
         }

         byte[] var7 = var0.getValue(var6);
         if (var7 == null) {
            break;
         }

         var7 = var2.convertDestinationValue(var6, var5, var7);
         var3.append(var7);
      }

      return var3.getAll();
   }

   public static Map q(AbstractRegisterData var0, kW var1, RegisterLayoutBridge var2, boolean var3) {
      LinkedHashMap var4 = new LinkedHashMap();

      for (int var6 : var1.xK()) {
         Integer var7 = var2.convertSource(var6);
         if (var7 != null && (!var3 || var0.isDirty(var7))) {
            byte[] var8 = var0.getValue(var7);
            if (var8 != null) {
               var8 = var2.convertDestinationValue(var7, var6, var8);
               var4.put(var6, var8);
            }
         }
      }

      return var4;
   }
}
