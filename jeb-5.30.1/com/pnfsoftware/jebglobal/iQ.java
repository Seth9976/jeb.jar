package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.units.code.asm.processor.memory.IEncodedMemoryArea;

public class iQ extends Qg {
   private String[] Uv;
   private IEncodedMemoryArea oW;

   public iQ(String[] var1, IEncodedMemoryArea var2, de var3, Ef... var4) {
      super(0, null, var3, var4);
      this.Uv = var1;
      this.oW = var2;
   }

   public iQ(String[] var1, IEncodedMemoryArea var2, Ef... var3) {
      super(null, var3);
      this.Uv = var1;
      this.oW = var2;
   }

   @Override
   protected String Uv(byte[] var1) {
      int var2 = this.oW.decodeInt(var1);
      return var2 < this.Uv.length ? this.Uv[var2] : super.Uv(var1);
   }
}
