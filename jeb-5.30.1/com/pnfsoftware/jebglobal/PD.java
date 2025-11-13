package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.units.code.asm.processor.memory.IEncodedMemoryArea;

public class PD implements Ef {
   private IEncodedMemoryArea q;
   private dD RF;
   private IEncodedMemoryArea xK;

   public PD(IEncodedMemoryArea var1, dD var2, IEncodedMemoryArea var3) {
      this.q = var1;
      this.RF = var2;
      this.xK = var3;
   }

   @Override
   public CW buildOperand(byte[] var1, int var2) {
      int var3 = this.xK.decodeInt(var1);
      if (var3 == 15) {
         return new wh(this.q(var1, var2, 0), null, false, true, var2);
      } else {
         return var3 == 13 ? new wh(this.q(var1, var2, 0), null, true, true, var2) : new wh(this.q(var1, var2, 1), GC.q(var3, var2), false, false, var2);
      }
   }

   private CW q(byte[] var1, int var2, int var3) {
      int var4 = this.q.decodeInt(var1);
      Integer var5 = (Integer)this.RF.RF(var1);
      if (var5 != null) {
         CW var6 = GC.q(var4, var2);
         return new JK(var4, var3, var6, var5);
      } else {
         return GC.q(var4, var2, var3);
      }
   }

   @Override
   public String xK(byte[] var1) {
      return this.q.decodeInt(var1) == 15 ? "Invalid Rn value" : Ef.super.xK(var1);
   }
}
