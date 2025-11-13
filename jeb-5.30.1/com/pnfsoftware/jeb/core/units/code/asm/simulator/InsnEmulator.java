package com.pnfsoftware.jeb.core.units.code.asm.simulator;

public abstract class InsnEmulator implements IInsnEmulator {
   public static final int NO_FLAG = 0;
   public static final int UPDATE_MASK = 255;
   public static final int UPDATE_NONE = 0;
   public static final int UPDATE_PC = 1;
   public static final int JUMP_MASK = 65280;
   public static final int JUMP_LAST_OPERAND = 256;
   public static final int LINK = 65536;
   private int flags;

   public InsnEmulator(int var1) {
      this.flags = var1;
   }

   @Override
   public boolean isPCUpdated() {
      switch (this.getFlags() & 0xFF) {
         case 0:
            return false;
         case 1:
            return true;
         default:
            return false;
      }
   }

   @Override
   public IInsnEmulator.BranchType getBranchType() {
      if ((this.getFlags() & 0xFF) == 0) {
         return null;
      } else {
         return (this.getFlags() & 65536) != 0 ? IInsnEmulator.BranchType.CALL : IInsnEmulator.BranchType.JMP;
      }
   }

   public int getFlags() {
      return this.flags;
   }
}
