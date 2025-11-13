package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.client.S;
import com.pnfsoftware.jeb.core.units.code.android.DexUtil;
import com.pnfsoftware.jeb.util.collect.ArrayUtil;
import com.pnfsoftware.jeb.util.logging.GlobalLog;
import com.pnfsoftware.jeb.util.logging.ILogger;
import com.pnfsoftware.jeb.util.serialization.annotations.Ser;
import com.pnfsoftware.jeb.util.serialization.annotations.SerId;
import com.pnfsoftware.jeb.util.serialization.annotations.SerStaticOk;
import java.util.Arrays;
import java.util.List;

@Ser
public final class beb {
   private static final ILogger A = GlobalLog.getLogger(beb.class);
   @SerStaticOk
   public static beb pC = new beb(0);
   @SerId(1)
   private bea[] kS;

   private beb(int var1) {
      if (var1 < 0) {
         throw new IllegalArgumentException("Illegal annotation count in the set");
      } else {
         this.kS = new bea[var1];
      }
   }

   public List pC() {
      return ArrayUtil.asView(this.kS);
   }

   static beb pC(com.pnfsoftware.jeb.corei.parsers.dex.vi var0, bgb var1, byte[] var2, int var3) {
      if (var3 == 0) {
         return pC;
      } else {
         int var4 = DexUtil.bytearrayULEInt32ToInt(var2, var3);
         beb var5 = new beb(var4);

         for (int var6 = 0; var6 < var4; var6++) {
            int var7 = DexUtil.bytearrayULEInt32ToInt(var2, var3 + 4 + 4 * var6);
            int var8 = var2[var7] & 255;
            if (var8 > 2) {
               var0.logWarn(true, "%s: %d", S.L("Invalid visibility for annotation, forcing to BUILD"), var8);
               var8 = 0;
            }

            int[] var9 = new int[1];
            bdy var10 = bdy.pC(var1, var2, var7 + 1, var9);
            var5.kS[var6] = new bea(var8, var10);
         }

         return var5;
      }
   }

   @Override
   public String toString() {
      return Arrays.toString((Object[])this.kS);
   }
}
