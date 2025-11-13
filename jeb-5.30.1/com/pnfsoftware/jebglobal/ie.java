package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.units.code.asm.processor.ProcessorException;
import com.pnfsoftware.jeb.core.units.code.asm.processor.memory.DirectEncodedMemoryArea;
import com.pnfsoftware.jeb.core.units.code.asm.processor.memory.EncodedMemoryAreaList;
import com.pnfsoftware.jeb.core.units.code.asm.processor.memory.IEncodedMemoryArea;
import com.pnfsoftware.jeb.core.units.code.asm.processor.memory.VirtualEncodedMemoryArea;

class ie implements Ef {
   private Dm q = Dm.q(-1, var0 -> {
      switch (zF.Uv(var0)) {
         case 1:
            return 0;
         case 2:
            return 1;
         case 3:
         case 5:
         case 6:
         case 7:
         default:
            return -1;
         case 4:
            return 2;
         case 8:
            return 3;
      }
   });
   private IEncodedMemoryArea RF = new EncodedMemoryAreaList(DirectEncodedMemoryArea.get(30, 1), DirectEncodedMemoryArea.get(10, 3));
   private IEncodedMemoryArea xK = new EncodedMemoryAreaList(DirectEncodedMemoryArea.get(30, 1), DirectEncodedMemoryArea.get(11, 2));
   private IEncodedMemoryArea Dw = new EncodedMemoryAreaList(DirectEncodedMemoryArea.get(30, 1), DirectEncodedMemoryArea.get(12, 1));
   private IEncodedMemoryArea Uv = DirectEncodedMemoryArea.get(30, 1);

   @Override
   public CW buildOperand(byte[] var1, int var2) throws ProcessorException {
      VirtualEncodedMemoryArea var3 = VirtualEncodedMemoryArea.get(zF.Dw(var1), 2);
      CW var4 = hc.q(0, var3, this.q).buildOperand(var1, var2);
      return new BY(var4, k.RF(var1, this.q(var1)));
   }

   private IEncodedMemoryArea q(byte[] var1) {
      switch (zF.Uv(var1)) {
         case 1:
            return this.RF;
         case 2:
            return this.xK;
         case 3:
         case 5:
         case 6:
         case 7:
         default:
            return null;
         case 4:
            return this.Dw;
         case 8:
            return this.Uv;
      }
   }
}
