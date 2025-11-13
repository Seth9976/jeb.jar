package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.units.code.asm.processor.arch.RegisterBankArm;
import com.pnfsoftware.jeb.core.units.code.asm.processor.memory.DirectEncodedMemoryArea;
import com.pnfsoftware.jeb.core.units.code.asm.processor.memory.EncodedMemoryAreaList;
import com.pnfsoftware.jeb.core.units.code.asm.processor.memory.IEncodedMemoryArea;

public class US extends gZ {
   private static final Ll.Av ys = Ll.Av.E;
   private static final Ll.Av ld = Ll.Av.sY;
   public static final gZ pC = new US(11, DirectEncodedMemoryArea.get(22, 1).shift(4), ys);
   public static final gZ A = new US(11, new EncodedMemoryAreaList(DirectEncodedMemoryArea.get(22, 1), DirectEncodedMemoryArea.get(16, 4)), ld);
   public static final gZ kS = new US(
      2, new EncodedMemoryAreaList(DirectEncodedMemoryArea.get(22, 1), DirectEncodedMemoryArea.get(8, 1), DirectEncodedMemoryArea.get(16, 4)), Ll.Av.pC
   );
   public static final gZ wS = new US(11, DirectEncodedMemoryArea.get(20, 1).shift(4), ys);
   public static final gZ UT = new US(11, new EncodedMemoryAreaList(DirectEncodedMemoryArea.get(20, 1), DirectEncodedMemoryArea.get(8, 4)), ld);
   public static final gZ E = new US(
      2, new EncodedMemoryAreaList(DirectEncodedMemoryArea.getThumb2(4, 1, 1), DirectEncodedMemoryArea.get(4, 1), DirectEncodedMemoryArea.get(8, 4)), Ll.Av.pC
   );
   public static final gZ sY = new US(
      2, new EncodedMemoryAreaList(DirectEncodedMemoryArea.getThumb2(4, 1, 1), DirectEncodedMemoryArea.get(4, 1), DirectEncodedMemoryArea.get(16, 4)), Ll.Av.pC
   );

   public US(int var1, IEncodedMemoryArea var2, Ll.Av var3) {
      super(var1, var3, var2, -1);
   }

   @Override
   public Integer A(byte[] var1) {
      Integer var2 = super.A(var1);
      if (var2 == null) {
         return null;
      } else {
         return this.pC(ys) ? var2 == 0 ? RegisterBankArm.APSR : RegisterBankArm.SPSR : var2;
      }
   }

   @Override
   public boolean wS(byte[] var1) {
      Integer var2 = super.A(var1);
      return !this.pC(ld) || var2 == null || var2 != 0 && var2 != 16 ? super.wS(var1) : false;
   }

   @Override
   public boolean E(byte[] var1) {
      return false;
   }
}
