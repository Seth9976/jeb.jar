package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.units.code.asm.processor.memory.DirectEncodedMemoryArea;
import com.pnfsoftware.jeb.core.units.code.asm.processor.memory.EncodedMemoryAreaList;
import com.pnfsoftware.jeb.core.units.code.asm.processor.memory.IEncodedMemoryArea;
import com.pnfsoftware.jeb.core.units.code.asm.processor.memory.VirtualEncodedMemoryArea;
import java.util.function.Function;

@FunctionalInterface
public interface IX {
   IX pC = new bH("B");
   IX A = new bH("H");
   IX kS = new bH("S");
   IX wS = new bH("D");
   IX UT = new bH("Q");
   IX E = new bH("4B");
   IX sY = new bH("16B");
   IX ys = new bH("2H");
   IX ld = new bH("8H");
   IX gp = new bH("4S");
   IX oT = new bH("2D");
   IEncodedMemoryArea fI = DirectEncodedMemoryArea.get(22, 2);
   IEncodedMemoryArea WR = DirectEncodedMemoryArea.get(22, 1);
   IX NS = pC(-1, fI);
   IX vP = pC(-1, WR);
   IX xC = pC(-1, var0 -> WR.decodeInt(var0) + 2);
   IX ED = pC(-1, DirectEncodedMemoryArea.get(13, 2));
   IX Sc = pC(-1, DirectEncodedMemoryArea.get(17, 2));
   IX ah = pC(-1, DirectEncodedMemoryArea.get(21, 2));
   IX eP = pC(-1, DirectEncodedMemoryArea.get(23, 2));
   IX UO = pC(fI, true);

   CharSequence getDataType(byte[] var1) throws oJ;

   static nl pC(int var0, Function var1) {
      return new Uo(var0, var1);
   }

   static nl pC(int var0, IEncodedMemoryArea var1) {
      return new j(var0, var1);
   }

   static Hs pC(IEncodedMemoryArea var0) {
      return new ZF(var0);
   }

   static Hs pC(IEncodedMemoryArea var0, boolean var1) {
      return new ZF(new EncodedMemoryAreaList(var0, var1 ? VirtualEncodedMemoryArea._1 : VirtualEncodedMemoryArea._0));
   }
}
