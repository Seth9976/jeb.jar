package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.units.code.asm.processor.ProcessorException;
import com.pnfsoftware.jeb.core.units.code.asm.processor.memory.DirectEncodedMemoryArea;
import com.pnfsoftware.jeb.core.units.code.asm.processor.memory.IEncodedMemoryArea;
import com.pnfsoftware.jeb.core.units.code.asm.processor.memory.VirtualEncodedMemoryArea;

public class kk extends h {
   private IX pC;
   private int A;

   private kk(int var1, IEncodedMemoryArea var2, IEncodedMemoryArea var3, IX var4) {
      super(var2, var3, Ll.Av.UT);
      this.pC = var4;
      this.A = var1;
   }

   @Override
   protected Yg pC(int var1, byte[] var2, int var3) throws ProcessorException {
      int var4 = DirectEncodedMemoryArea.get(this.A, 5).decodeInt(var2);
      int var5 = (var4 + var1) % 32;
      return new ER(8388615, VirtualEncodedMemoryArea.get(var5, 5), this.pC).buildOperand(var2, var3);
   }

   public static Hu pC(int var0, IEncodedMemoryArea var1, IX var2) {
      return new kk(var0, VirtualEncodedMemoryArea._0, var1, var2);
   }
}
