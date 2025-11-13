package com.pnfsoftware.jeb.core.units.code.asm.processor;

import com.pnfsoftware.jeb.core.units.code.asm.processor.memory.IEncodedMemoryArea;

public abstract class AbstractOperandBuilder implements IOperandBuilder {
   public static final int OPTIONAL_DEFAULT_VALUE = 0;
   public static final int OPTIONAL_MASK = 0;
   public static final int NO_FLAG = 0;
   public static final int OPTIONAL = 268435456;
   private final IEncodedMemoryArea memoryArea;
   protected int flags;
   private final int defaultValue;
   private final int defaultValueMask;

   public AbstractOperandBuilder(IEncodedMemoryArea var1) {
      this(var1, 0, 0, 0);
   }

   public AbstractOperandBuilder(IEncodedMemoryArea var1, int var2) {
      this(var1, var2, 0, 0);
   }

   public AbstractOperandBuilder(IEncodedMemoryArea var1, int var2, int var3, int var4) {
      this.memoryArea = var1;
      this.flags = var2;
      this.defaultValue = var3;
      this.defaultValueMask = var4;
   }

   public boolean isOptional(long var1) {
      return (this.flags & 268435456) != 0
         && (this.defaultValueMask == 0 && var1 == this.defaultValue || this.defaultValueMask != 0 && (var1 & this.defaultValueMask) == this.defaultValue);
   }

   public int getFlags() {
      return this.flags;
   }

   protected IEncodedMemoryArea getMemoryArea() {
      return this.memoryArea;
   }

   protected int decodeMemoryArea(byte[] var1) {
      return this.memoryArea.decodeInt(var1);
   }
}
