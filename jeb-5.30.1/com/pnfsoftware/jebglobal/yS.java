package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.units.code.IInstruction;
import com.pnfsoftware.jeb.util.serialization.annotations.Ser;
import java.util.ArrayList;

@Ser
public abstract class yS extends CW {
   public static final int q = 524288;

   public yS(CW... var1) {
      this(0L, 768, var1);
   }

   public yS(long var1, int var3, CW... var4) {
      super(var4.length == 0 ? 0 : var4[0].getOperandBitsize(), var1, q(var4), var3 & 16777215);
   }

   public yS(int var1, long var2, int var4, CW... var5) {
      super(var1, var2, q(var5), var4 & 16777215);
   }

   public CW[] q() {
      ArrayList var1 = new ArrayList();

      for (CW var5 : this.oW()) {
         if (var5.isRegister()) {
            var1.add(var5);
         } else if (var5.getOperandType() == 7) {
            for (CW var9 : ((yS)var5).q()) {
               var1.add(var9);
            }
         }
      }

      return (CW[])var1.toArray(new CW[var1.size()]);
   }

   private static CW[] q(CW[] var0) {
      if (var0.length > 1 && var0[var0.length - 1] == null) {
         CW[] var1 = new CW[var0.length - 1];
         System.arraycopy(var0, 0, var1, 0, var1.length);
         return var1;
      } else {
         return var0;
      }
   }

   @Override
   public String getSuffix(IInstruction var1) {
      String var2 = super.getSuffix(var1);
      return (this.getFlags() & 524288) != 0 ? var2 + "^" : var2;
   }
}
