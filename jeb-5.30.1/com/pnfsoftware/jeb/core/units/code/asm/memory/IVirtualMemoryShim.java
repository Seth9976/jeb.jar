package com.pnfsoftware.jeb.core.units.code.asm.memory;

public interface IVirtualMemoryShim extends IVirtualMemory {
   IVirtualMemory getUnderlyingMemory();

   int commitChanges(boolean var1, boolean var2, boolean var3);

   default int commitChanges() {
      return this.commitChanges(true, true, true);
   }

   MemoryChanges getChanges(boolean var1, boolean var2);

   default MemoryChanges getChanges() {
      return this.getChanges(true, true);
   }

   IVirtualMemoryShim duplicate();

   IVirtualMemory duplicate(boolean var1);
}
