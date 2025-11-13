package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.units.code.asm.processor.ProcessorException;
import com.pnfsoftware.jeb.core.units.code.asm.processor.memory.DirectEncodedMemoryArea;
import com.pnfsoftware.jeb.core.units.code.asm.processor.memory.EncodedMemoryAreaList;
import com.pnfsoftware.jeb.core.units.code.asm.processor.memory.IEncodedMemoryArea;

public class GS extends PP {
   static final IEncodedMemoryArea pC = new EncodedMemoryAreaList(DirectEncodedMemoryArea.get(11, 1), DirectEncodedMemoryArea.get(20, 2));
   static final IEncodedMemoryArea A = EncodedMemoryAreaList.fromBits(11, 21);
   static final IEncodedMemoryArea kS = DirectEncodedMemoryArea.get(11, 1);
   private ZW gp;
   private static final IEncodedMemoryArea oT = DirectEncodedMemoryArea.get(22, 2);
   static final Hu wS = new GS(new ZW(oT, null, null, 1, null));
   static final Hu UT = new GS(new ZW(oT, null, 1, 2, null));
   static final Hu E = new GS(new ZW(oT, null, null, null, 1));
   static final Hu sY = new hz(new ZW(oT, null, 2, 3, null));
   static final Hu ys = new qO(new ZW(oT, 1, null, 2, 3));

   private GS(ZW var1) {
      super(16, null, null);
      this.gp = var1;
   }

   int pC(byte[] var1) {
      Integer var2 = (Integer)this.gp.A(var1);
      return var2 == null ? -1 : var2;
   }

   @Override
   public Yg buildOperand(byte[] var1, int var2) throws ProcessorException {
      int var3 = this.pC(var1);
      if (var3 < 0) {
         return null;
      } else if (var3 == 0) {
         return null;
      } else {
         gZ var4 = this.pC(var3);
         return new Yu(var4, this.A(var3)).buildOperand(var1, var2);
      }
   }

   protected gZ pC(int var1) {
      switch (var1) {
         case 1:
            return new ER(8388615, DirectEncodedMemoryArea.get(this.ld, 4), this.kS(var1));
         default:
            return ER.pC(this.ld, this.kS(var1));
      }
   }

   protected IEncodedMemoryArea A(int var1) {
      switch (var1) {
         case 1:
            return pC;
         case 2:
            return A;
         case 3:
            return kS;
         default:
            return null;
      }
   }

   protected IX kS(int var1) {
      switch (var1) {
         case 1:
            return IX.A;
         case 2:
            return IX.kS;
         case 3:
            return IX.wS;
         default:
            return null;
      }
   }
}
