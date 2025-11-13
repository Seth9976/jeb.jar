package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.units.code.asm.processor.ProcessorException;
import com.pnfsoftware.jeb.core.units.code.asm.processor.memory.DirectEncodedMemoryArea;
import com.pnfsoftware.jeb.core.units.code.asm.processor.memory.EncodedMemoryAreaList;
import com.pnfsoftware.jeb.core.units.code.asm.processor.memory.IEncodedMemoryArea;

public class Fo extends Df {
   static final IEncodedMemoryArea q = new EncodedMemoryAreaList(DirectEncodedMemoryArea.get(11, 1), DirectEncodedMemoryArea.get(20, 2));
   static final IEncodedMemoryArea RF = EncodedMemoryAreaList.fromBits(11, 21);
   static final IEncodedMemoryArea xK = DirectEncodedMemoryArea.get(11, 1);
   private dD za;
   private static final IEncodedMemoryArea lm = DirectEncodedMemoryArea.get(22, 2);
   static final Ef Dw = new Fo(new dD(lm, null, null, 1, null));
   static final Ef Uv = new Fo(new dD(lm, null, 1, 2, null));
   static final Ef oW = new Fo(new dD(lm, null, null, null, 1));
   static final Ef gO = new nw(new dD(lm, null, 2, 3, null));
   static final Ef nf = new H(new dD(lm, 1, null, 2, 3));

   private Fo(dD var1) {
      super(16, null, null);
      this.za = var1;
   }

   int q(byte[] var1) {
      Integer var2 = (Integer)this.za.RF(var1);
      return var2 == null ? -1 : var2;
   }

   @Override
   public CW buildOperand(byte[] var1, int var2) throws ProcessorException {
      int var3 = this.q(var1);
      if (var3 < 0) {
         return null;
      } else if (var3 == 0) {
         return null;
      } else {
         rR var4 = this.q(var3);
         return new VP(var4, this.RF(var3)).buildOperand(var1, var2);
      }
   }

   protected rR q(int var1) {
      switch (var1) {
         case 1:
            return new XD(8388615, DirectEncodedMemoryArea.get(this.gP, 4), this.xK(var1));
         default:
            return XD.q(this.gP, this.xK(var1));
      }
   }

   protected IEncodedMemoryArea RF(int var1) {
      switch (var1) {
         case 1:
            return q;
         case 2:
            return RF;
         case 3:
            return xK;
         default:
            return null;
      }
   }

   protected Dm xK(int var1) {
      switch (var1) {
         case 1:
            return Dm.RF;
         case 2:
            return Dm.xK;
         case 3:
            return Dm.Dw;
         default:
            return null;
      }
   }
}
