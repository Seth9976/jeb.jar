package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.units.code.asm.processor.AbstractImmediateOperandBuilder;
import com.pnfsoftware.jeb.core.units.code.asm.processor.memory.DirectEncodedMemoryArea;
import com.pnfsoftware.jeb.core.units.code.asm.processor.memory.IEncodedMemoryArea;

public class aX {
   public static final Hu pC = new IV(DirectEncodedMemoryArea.get(10, 12));
   public static final Hu A = new IV(DirectEncodedMemoryArea.get(5, 16));

   public static Hu pC(int var0, IEncodedMemoryArea var1) {
      return new IV(AbstractImmediateOperandBuilder.ImmediateType.SignExtend64, var0, var1);
   }

   public static Hu pC(IEncodedMemoryArea var0) {
      return new IV(AbstractImmediateOperandBuilder.ImmediateType.ZeroExtend64, 65536, var0);
   }
}
