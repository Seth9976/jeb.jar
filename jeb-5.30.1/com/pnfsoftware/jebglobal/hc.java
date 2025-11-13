package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.units.code.asm.processor.ProcessorException;
import com.pnfsoftware.jeb.core.units.code.asm.processor.memory.DirectEncodedMemoryArea;
import com.pnfsoftware.jeb.core.units.code.asm.processor.memory.IEncodedMemoryArea;
import com.pnfsoftware.jeb.core.units.code.asm.processor.memory.VirtualEncodedMemoryArea;

public class hc extends LN {
   private Dm q;
   private int RF;

   private hc(int var1, IEncodedMemoryArea var2, IEncodedMemoryArea var3, Dm var4) {
      super(var2, var3, jD.eo.Uv);
      this.q = var4;
      this.RF = var1;
   }

   @Override
   protected CW q(int var1, byte[] var2, int var3) throws ProcessorException {
      int var4 = DirectEncodedMemoryArea.get(this.RF, 5).decodeInt(var2);
      int var5 = (var4 + var1) % 32;
      return new XD(8388615, VirtualEncodedMemoryArea.get(var5, 5), this.q).buildOperand(var2, var3);
   }

   public static Ef q(int var0, IEncodedMemoryArea var1, Dm var2) {
      return new hc(var0, VirtualEncodedMemoryArea._0, var1, var2);
   }
}
