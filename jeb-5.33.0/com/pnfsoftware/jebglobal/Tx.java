package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.units.code.asm.processor.ProcessorException;
import com.pnfsoftware.jeb.core.units.code.asm.processor.memory.DirectEncodedMemoryArea;
import com.pnfsoftware.jeb.core.units.code.asm.processor.memory.EncodedMemoryAreaList;
import com.pnfsoftware.jeb.core.units.code.asm.processor.memory.IEncodedMemoryArea;
import com.pnfsoftware.jeb.core.units.code.asm.processor.memory.VirtualEncodedMemoryArea;

class Tx implements Hu {
   private IX pC = IX.pC(-1, var0 -> {
      switch (Ir.UT(var0)) {
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
   private IEncodedMemoryArea A = new EncodedMemoryAreaList(DirectEncodedMemoryArea.get(30, 1), DirectEncodedMemoryArea.get(10, 3));
   private IEncodedMemoryArea kS = new EncodedMemoryAreaList(DirectEncodedMemoryArea.get(30, 1), DirectEncodedMemoryArea.get(11, 2));
   private IEncodedMemoryArea wS = new EncodedMemoryAreaList(DirectEncodedMemoryArea.get(30, 1), DirectEncodedMemoryArea.get(12, 1));
   private IEncodedMemoryArea UT = DirectEncodedMemoryArea.get(30, 1);

   @Override
   public Yg buildOperand(byte[] var1, int var2) throws ProcessorException {
      VirtualEncodedMemoryArea var3 = VirtualEncodedMemoryArea.get(Ir.wS(var1), 2);
      Yg var4 = kk.pC(0, var3, this.pC).buildOperand(var1, var2);
      return new rw(var4, Gq.A(var1, this.pC(var1)));
   }

   private IEncodedMemoryArea pC(byte[] var1) {
      switch (Ir.UT(var1)) {
         case 1:
            return this.A;
         case 2:
            return this.kS;
         case 3:
         case 5:
         case 6:
         case 7:
         default:
            return null;
         case 4:
            return this.wS;
         case 8:
            return this.UT;
      }
   }
}
