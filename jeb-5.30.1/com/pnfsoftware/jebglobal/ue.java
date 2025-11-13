package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.units.code.asm.processor.arch.RegisterBankArm;
import com.pnfsoftware.jeb.core.units.code.asm.processor.memory.DirectEncodedMemoryArea;
import com.pnfsoftware.jeb.core.units.code.asm.processor.memory.EncodedMemoryAreaList;
import com.pnfsoftware.jeb.core.units.code.asm.processor.memory.IEncodedMemoryArea;

public class ue extends rR {
   private static final jD.eo lm = jD.eo.oW;
   private static final jD.eo zz = jD.eo.gO;
   public static final rR Dw = new ue(11, DirectEncodedMemoryArea.get(22, 1).shift(4), lm);
   public static final rR Uv = new ue(11, new EncodedMemoryAreaList(DirectEncodedMemoryArea.get(22, 1), DirectEncodedMemoryArea.get(16, 4)), zz);
   public static final rR oW = new ue(
      2, new EncodedMemoryAreaList(DirectEncodedMemoryArea.get(22, 1), DirectEncodedMemoryArea.get(8, 1), DirectEncodedMemoryArea.get(16, 4)), jD.eo.q
   );
   public static final rR gO = new ue(11, DirectEncodedMemoryArea.get(20, 1).shift(4), lm);
   public static final rR nf = new ue(11, new EncodedMemoryAreaList(DirectEncodedMemoryArea.get(20, 1), DirectEncodedMemoryArea.get(8, 4)), zz);
   public static final rR gP = new ue(
      2, new EncodedMemoryAreaList(DirectEncodedMemoryArea.getThumb2(4, 1, 1), DirectEncodedMemoryArea.get(4, 1), DirectEncodedMemoryArea.get(8, 4)), jD.eo.q
   );
   public static final rR za = new ue(
      2, new EncodedMemoryAreaList(DirectEncodedMemoryArea.getThumb2(4, 1, 1), DirectEncodedMemoryArea.get(4, 1), DirectEncodedMemoryArea.get(16, 4)), jD.eo.q
   );

   public ue(int var1, IEncodedMemoryArea var2, jD.eo var3) {
      super(var1, var3, var2, -1);
   }

   @Override
   public Integer RF(byte[] var1) {
      Integer var2 = super.RF(var1);
      if (var2 == null) {
         return null;
      } else {
         return this.q(lm) ? var2 == 0 ? RegisterBankArm.APSR : RegisterBankArm.SPSR : var2;
      }
   }

   @Override
   public boolean Dw(byte[] var1) {
      Integer var2 = super.RF(var1);
      return !this.q(zz) || var2 == null || var2 != 0 && var2 != 16 ? super.Dw(var1) : false;
   }

   @Override
   public boolean oW(byte[] var1) {
      return false;
   }
}
