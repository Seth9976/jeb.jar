package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.units.code.android.DexUtil;
import com.pnfsoftware.jeb.core.units.code.android.dex.IDexAnnotation;
import com.pnfsoftware.jeb.util.collect.ArrayUtil;
import com.pnfsoftware.jeb.util.serialization.annotations.Ser;
import com.pnfsoftware.jeb.util.serialization.annotations.SerId;
import java.util.List;

@Ser
public final class bdy implements IDexAnnotation {
   @SerId(1)
   private int pC;
   @SerId(2)
   private bdz[] A;

   private bdy(int var1, int var2) {
      this.pC = var1;
      this.A = new bdz[var2];
   }

   @Override
   public int getTypeIndex() {
      return this.pC;
   }

   @Override
   public List getElements() {
      return ArrayUtil.asView(this.A);
   }

   static bdy pC(bgb var0, byte[] var1, int var2, int[] var3) {
      int[] var4 = new int[1];
      int var6 = DexUtil.bytearrayULEB128ToInt(var1, var2, var4);
      var6 = var0.A(var6);
      int var5 = var2 + var4[0];
      int var7 = DexUtil.bytearrayULEB128ToInt(var1, var5, var4);
      var5 += var4[0];
      bdy var8 = new bdy(var6, var7);

      for (int var9 = 0; var9 < var7; var9++) {
         int var10 = DexUtil.bytearrayULEB128ToInt(var1, var5, var4);
         var10 = var0.pC(var10);
         var5 += var4[0];
         beg var11 = beg.pC(var0, var1, var5, var4);
         var5 += var4[0];
         bdz var12 = new bdz(var10, var11);
         var8.A[var9] = var12;
      }

      var3[0] = var5 - var2;
      return var8;
   }
}
