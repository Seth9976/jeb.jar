package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.units.code.IInstruction;
import com.pnfsoftware.jeb.util.serialization.annotations.Ser;
import java.util.ArrayList;

@Ser
public abstract class FH extends Yg {
   public FH(Yg... var1) {
      this(0L, 768, var1);
   }

   public FH(long var1, int var3, Yg... var4) {
      super(var4.length == 0 ? 0 : var4[0].getOperandBitsize(), var1, pC(var4), var3 & 16777215);
   }

   public FH(int var1, long var2, int var4, Yg... var5) {
      super(var1, var2, pC(var5), var4 & 16777215);
   }

   public Yg[] pC() {
      ArrayList var1 = new ArrayList();

      for (Yg var5 : this.E()) {
         if (var5.isRegister()) {
            var1.add(var5);
         } else if (var5.getOperandType() == 7) {
            for (Yg var9 : ((FH)var5).pC()) {
               var1.add(var9);
            }
         }
      }

      return (Yg[])var1.toArray(new Yg[var1.size()]);
   }

   private static Yg[] pC(Yg[] var0) {
      if (var0.length > 1 && var0[var0.length - 1] == null) {
         Yg[] var1 = new Yg[var0.length - 1];
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
