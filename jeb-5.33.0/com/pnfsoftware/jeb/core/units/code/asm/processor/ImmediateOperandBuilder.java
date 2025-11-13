package com.pnfsoftware.jeb.core.units.code.asm.processor;

import com.pnfsoftware.jeb.core.units.code.asm.processor.memory.IEncodedMemoryArea;

public class ImmediateOperandBuilder extends AbstractImmediateOperandBuilder {
   public ImmediateOperandBuilder(AbstractImmediateOperandBuilder.ImmediateType var1, IEncodedMemoryArea var2) {
      super(var1, 0, 0, var2);
   }

   public ImmediateOperandBuilder(AbstractImmediateOperandBuilder.ImmediateType var1, int var2, int var3, IEncodedMemoryArea var4) {
      super(var1, var2, var3, var4);
   }

   protected Operand buildImmediate(int var1, long var2) {
      byte var4 = 1;
      if ((this.flags & 2097152) != 0) {
         var4 = 3;
      } else if ((this.flags & 4194304) != 0) {
         var4 = 2;
      }

      return new Operand(var4, this.getSize(), var2);
   }
}
