package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.units.code.asm.processor.AbstractImmediateOperandBuilder;
import com.pnfsoftware.jeb.core.units.code.asm.processor.memory.DirectEncodedMemoryArea;
import com.pnfsoftware.jeb.core.units.code.asm.processor.memory.IEncodedMemoryArea;

public class Ne {
   public static final int q = 2097152;
   public static final Ef RF = new go(DirectEncodedMemoryArea.get(10, 12));
   public static final Ef xK = new go(DirectEncodedMemoryArea.get(5, 16));

   public static Ef q(int var0, IEncodedMemoryArea var1) {
      return new go(AbstractImmediateOperandBuilder.ImmediateType.SignExtend64, var0, var1);
   }

   public static Ef RF(int var0, IEncodedMemoryArea var1) {
      return new go(AbstractImmediateOperandBuilder.ImmediateType.ZeroExtend64, var0, var1);
   }

   public static Ef q(IEncodedMemoryArea var0) {
      return new go(AbstractImmediateOperandBuilder.ImmediateType.ZeroExtend64, 65536, var0);
   }
}
