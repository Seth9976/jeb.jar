package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.units.code.asm.processor.memory.DirectEncodedMemoryArea;
import com.pnfsoftware.jeb.core.units.code.asm.processor.memory.IEncodedMemoryArea;

class kT implements Dm {
   private Integer mI;
   private dD jq;
   static final Dm xW = new kT(new dD(XD.Uv, 0, null, null, null));
   static final Dm KT = new kT(new dD(XD.Uv, 0, 1, null, null));
   static final Dm Gf = new kT(new dD(XD.Uv, 0, 1, 2, null));
   static final Dm Ef = new kT(new dD(XD.Uv, 0, 1, 2, 3));
   static final Dm cC = new kT(new dD(XD.Uv, null, 1, 2, null));
   static final Dm sH = new kT(new dD(XD.Uv, null, 1, 2, 3));
   static final IEncodedMemoryArea CE = DirectEncodedMemoryArea.get(22, 1);
   static final Dm wF = new kT(new dD(CE, 1, 2));
   static final Dm If = new kT(new dD(CE, 2, 3));
   static final Dm Dz = new ZK();

   public kT() {
   }

   public kT(dD var1) {
      this.jq = var1;
   }

   public kT(Integer var1, dD var2) {
      this.mI = var1;
      this.jq = var2;
   }

   int q(byte[] var1) {
      Integer var2 = (Integer)this.jq.RF(var1);
      return var2 == null ? -1 : var2;
   }

   @Override
   public CharSequence getDataType(byte[] var1) throws eK {
      int var2 = this.q(var1);
      if (this.mI != null) {
         var1 = new byte[]{(byte)(this.mI << 6), 0, 0, 0};
      }

      switch (var2) {
         case 0:
            return kE.Uv.getDataType(var1);
         case 1:
            return kE.Dw.getDataType(var1);
         case 2:
            return kE.RF.getDataType(var1);
         case 3:
            return kE.oW.getDataType(var1);
         case 4:
            return "1Q";
         default:
            return "";
      }
   }
}
