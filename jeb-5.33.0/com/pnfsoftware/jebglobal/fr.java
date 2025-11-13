package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.units.code.asm.processor.memory.IEncodedMemoryArea;

public class fr extends UC {
   private String[] A;
   private IEncodedMemoryArea kS;

   public fr(String[] var1, IEncodedMemoryArea var2, Hu... var3) {
      super(null, var3);
      this.A = var1;
      this.kS = var2;
   }

   @Override
   protected String UT(byte[] var1) {
      int var2 = this.kS.decodeInt(var1);
      return var2 < this.A.length ? this.A[var2] : super.UT(var1);
   }
}
