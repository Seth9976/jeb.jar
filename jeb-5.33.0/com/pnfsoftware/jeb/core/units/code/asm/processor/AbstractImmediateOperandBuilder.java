package com.pnfsoftware.jeb.core.units.code.asm.processor;

import com.pnfsoftware.jeb.core.units.code.IInstructionOperand;
import com.pnfsoftware.jeb.core.units.code.asm.processor.memory.EncodedMemoryAreaUtil;
import com.pnfsoftware.jeb.core.units.code.asm.processor.memory.IEncodedMemoryArea;

public abstract class AbstractImmediateOperandBuilder extends AbstractOperandBuilder {
   public static final int POST_ADD1 = 1048576;
   public static final int REL_ADDRESS = 2097152;
   public static final int ABS_ADDRESS = 4194304;
   public static final int PC_SHIFT_4 = 8388608;
   private final AbstractImmediateOperandBuilder.ImmediateType type;
   private int postAdd = 0;

   protected AbstractImmediateOperandBuilder(AbstractImmediateOperandBuilder.ImmediateType var1, int var2, int var3, IEncodedMemoryArea var4) {
      super(var4, var2, var3, 0);
      this.type = var1;
   }

   protected AbstractImmediateOperandBuilder(AbstractImmediateOperandBuilder.ImmediateType var1, int var2, int var3, int var4, IEncodedMemoryArea var5) {
      super(var5, var2, var3, var4);
      this.type = var1;
   }

   public void postAdd(int var1) {
      this.postAdd = var1;
   }

   protected long getValue(byte[] var1, int var2) throws ProcessorException {
      switch (this.type) {
         case SignExtend32:
            return EncodedMemoryAreaUtil.signExtendInt(var1, this.getMemoryArea());
         case SignExtend64:
            return EncodedMemoryAreaUtil.signExtendLong(var1, this.getMemoryArea());
         case ZeroExtendLength:
         case ZeroExtend32:
         case ZeroExtend64:
         default:
            return EncodedMemoryAreaUtil.zeroExtend(var1, this.getMemoryArea());
      }
   }

   @Override
   public IInstructionOperand buildOperand(byte[] var1, int var2) throws ProcessorException {
      long var3 = this.getValue(var1, var2);
      if ((this.flags & 1048576) != 0) {
         var3++;
      }

      var3 += this.postAdd;
      return this.isOptional(var3) ? null : this.buildImmediate(var2, var3);
   }

   protected int getSize() {
      switch (this.type) {
         case SignExtend32:
         case ZeroExtendLength:
         case ZeroExtend32:
            return this.getMemoryArea().getLength();
         case SignExtend64:
         case ZeroExtend64:
            return 64;
         default:
            return 32;
      }
   }

   public int getPostAdd() {
      return this.postAdd;
   }

   public boolean isSigned() {
      return this.type == AbstractImmediateOperandBuilder.ImmediateType.SignExtend32 || this.type == AbstractImmediateOperandBuilder.ImmediateType.SignExtend64;
   }

   protected abstract IInstructionOperand buildImmediate(int var1, long var2);

   public static enum ImmediateType {
      SignExtend32,
      SignExtend64,
      ZeroExtendLength,
      ZeroExtend32,
      ZeroExtend64;
   }
}
