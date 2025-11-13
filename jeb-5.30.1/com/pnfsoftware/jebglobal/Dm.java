package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.units.code.asm.processor.memory.DirectEncodedMemoryArea;
import com.pnfsoftware.jeb.core.units.code.asm.processor.memory.EncodedMemoryAreaList;
import com.pnfsoftware.jeb.core.units.code.asm.processor.memory.IEncodedMemoryArea;
import com.pnfsoftware.jeb.core.units.code.asm.processor.memory.VirtualEncodedMemoryArea;
import java.util.function.Function;

@FunctionalInterface
public interface Dm {
   Dm q = new qh("B");
   Dm RF = new qh("H");
   Dm xK = new qh("S");
   Dm Dw = new qh("D");
   Dm Uv = new qh("Q");
   Dm oW = new qh("4B");
   Dm gO = new qh("16B");
   Dm nf = new qh("2H");
   Dm gP = new qh("8H");
   Dm za = new qh("4S");
   Dm lm = new qh("2D");
   IEncodedMemoryArea zz = DirectEncodedMemoryArea.get(22, 2);
   IEncodedMemoryArea JY = DirectEncodedMemoryArea.get(22, 1);
   Dm HF = q(-1, zz);
   Dm LK = q(-1, JY);
   Dm io = q(-1, var0 -> JY.decodeInt(var0) + 2);
   Dm qa = q(-1, DirectEncodedMemoryArea.get(13, 2));
   Dm Hk = q(-1, DirectEncodedMemoryArea.get(17, 2));
   Dm Me = q(-1, DirectEncodedMemoryArea.get(21, 2));
   Dm PV = q(-1, DirectEncodedMemoryArea.get(23, 2));
   Dm oQ = q(zz, true);

   CharSequence getDataType(byte[] var1) throws eK;

   static PZ q(int var0, Function var1) {
      return new Wf(var0, var1);
   }

   static PZ q(int var0, IEncodedMemoryArea var1) {
      return new Bs(var0, var1);
   }

   static kP q(Function var0) {
      return new ET(var0);
   }

   static kP q(IEncodedMemoryArea var0) {
      return new xO(var0);
   }

   static kP q(IEncodedMemoryArea var0, boolean var1) {
      return new xO(new EncodedMemoryAreaList(var0, var1 ? VirtualEncodedMemoryArea._1 : VirtualEncodedMemoryArea._0));
   }
}
