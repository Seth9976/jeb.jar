package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.units.code.asm.processor.memory.DirectEncodedMemoryArea;
import com.pnfsoftware.jeb.core.units.code.asm.processor.memory.IEncodedMemoryArea;

class vS implements IX {
   private Integer mv;
   private ZW sO;
   static final IX Ab = new vS(new ZW(ER.pC, 0, null, null, null));
   static final IX rl = new vS(new ZW(ER.pC, 0, 1, null, null));
   static final IX z = new vS(new ZW(ER.pC, 0, 1, 2, null));
   static final IX Ek = new vS(new ZW(ER.pC, 0, 1, 2, 3));
   static final IX hK = new vS(new ZW(ER.pC, null, 1, 2, null));
   static final IX Er = new vS(new ZW(ER.pC, null, 1, 2, 3));
   static final IEncodedMemoryArea FE = DirectEncodedMemoryArea.get(22, 1);
   static final IX Aj = new vS(new ZW(FE, 1, 2));
   static final IX EX = new vS(new ZW(FE, 2, 3));
   static final IX LM = new CZ();

   public vS() {
   }

   public vS(ZW var1) {
      this.sO = var1;
   }

   public vS(Integer var1, ZW var2) {
      this.mv = var1;
      this.sO = var2;
   }

   int pC(byte[] var1) {
      Integer var2 = (Integer)this.sO.A(var1);
      return var2 == null ? -1 : var2;
   }

   @Override
   public CharSequence getDataType(byte[] var1) throws oJ {
      int var2 = this.pC(var1);
      if (this.mv != null) {
         var1 = new byte[]{(byte)(this.mv << 6), 0, 0, 0};
      }

      switch (var2) {
         case 0:
            return XW.UT.getDataType(var1);
         case 1:
            return XW.wS.getDataType(var1);
         case 2:
            return XW.A.getDataType(var1);
         case 3:
            return XW.E.getDataType(var1);
         case 4:
            return "1Q";
         default:
            return "";
      }
   }
}
