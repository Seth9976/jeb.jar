package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.units.code.asm.processor.memory.IEncodedMemoryArea;

public class ru implements Hu {
   private IEncodedMemoryArea pC;
   private ZW A;
   private IEncodedMemoryArea kS;

   public ru(IEncodedMemoryArea var1, ZW var2, IEncodedMemoryArea var3) {
      this.pC = var1;
      this.A = var2;
      this.kS = var3;
   }

   @Override
   public Yg buildOperand(byte[] var1, int var2) {
      int var3 = this.kS.decodeInt(var1);
      if (var3 == 15) {
         return new KH(this.pC(var1, var2, 0), null, false, true, var2);
      } else {
         return var3 == 13 ? new KH(this.pC(var1, var2, 0), null, true, true, var2) : new KH(this.pC(var1, var2, 1), LC.pC(var3, var2), false, false, var2);
      }
   }

   private Yg pC(byte[] var1, int var2, int var3) {
      int var4 = this.pC.decodeInt(var1);
      Integer var5 = (Integer)this.A.A(var1);
      if (var5 != null) {
         Yg var6 = LC.pC(var4, var2);
         return new hy(var4, var3, var6, var5);
      } else {
         return LC.pC(var4, var2, var3);
      }
   }

   @Override
   public String kS(byte[] var1) {
      return this.pC.decodeInt(var1) == 15 ? "Invalid Rn value" : Hu.super.kS(var1);
   }
}
