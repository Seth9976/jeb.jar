package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.client.S;
import com.pnfsoftware.jeb.core.units.code.android.DexUtil;
import com.pnfsoftware.jeb.core.units.code.android.dex.IDexAnnotationItem;
import com.pnfsoftware.jeb.util.collect.ArrayUtil;
import com.pnfsoftware.jeb.util.logging.GlobalLog;
import com.pnfsoftware.jeb.util.logging.ILogger;
import com.pnfsoftware.jeb.util.serialization.annotations.Ser;
import com.pnfsoftware.jeb.util.serialization.annotations.SerId;
import com.pnfsoftware.jeb.util.serialization.annotations.SerStaticOk;
import java.util.Arrays;
import java.util.List;

@Ser
public final class bhv {
   private static final ILogger RF = GlobalLog.getLogger(bhv.class);
   @SerStaticOk
   public static bhv q = new bhv(0);
   @SerId(1)
   private bhu[] xK;

   private bhv(int var1) {
      if (var1 < 0) {
         throw new IllegalArgumentException("Illegal annotation count in the set");
      } else {
         this.xK = new bhu[var1];
      }
   }

   public int q() {
      return this.xK.length;
   }

   public IDexAnnotationItem q(int var1) {
      return this.xK[var1];
   }

   public List RF() {
      return ArrayUtil.asView(this.xK);
   }

   static bhv q(com.pnfsoftware.jeb.corei.parsers.dex.bK var0, bjw var1, byte[] var2, int var3) {
      if (var3 == 0) {
         return q;
      } else {
         int var4 = DexUtil.bytearrayULEInt32ToInt(var2, var3);
         bhv var5 = new bhv(var4);

         for (int var6 = 0; var6 < var4; var6++) {
            int var7 = DexUtil.bytearrayULEInt32ToInt(var2, var3 + 4 + 4 * var6);
            int var8 = var2[var7] & 255;
            if (var8 > 2) {
               var0.logWarn(true, "%s: %d", S.L("Invalid visibility for annotation, forcing to BUILD"), var8);
               var8 = 0;
            }

            int[] var9 = new int[1];
            bhs var10 = bhs.q(var1, var2, var7 + 1, var9);
            var5.xK[var6] = new bhu(var8, var10);
         }

         return var5;
      }
   }

   @Override
   public String toString() {
      return Arrays.toString((Object[])this.xK);
   }
}
