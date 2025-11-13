package com.pnfsoftware.jeb.core.units.codeobject;

import com.pnfsoftware.jeb.core.units.code.asm.memory.IVirtualMemory;
import com.pnfsoftware.jeb.core.units.code.asm.memory.MemoryException;
import com.pnfsoftware.jeb.util.io.Endianness;

public interface IELFRelocationApplicator {
   int getType();

   default boolean canApply(IELFRelocationApplicator.RelocInstance var1) {
      return var1.se == null || var1.se.getAddress() != 0L;
   }

   void apply(IELFRelocationApplicator.RelocatedFile var1, IELFRelocationApplicator.RelocInstance var2) throws MemoryException;

   public static class RelocInstance {
      public IELFRelocationTable relocTable;
      public IELFRelocationEntry relocEntry;
      public IELFSymbolEntry se;

      public RelocInstance(IELFRelocationTable var1, IELFRelocationEntry var2, IELFSymbolEntry var3) {
         this.relocTable = var1;
         this.relocEntry = var2;
         this.se = var3;
      }
   }

   public static class RelocatedFile {
      public IELFUnit elfUnit;
      public IVirtualMemory mem;
      public long actualImageBase;
      public Endianness endianness;

      public RelocatedFile(IELFUnit var1, IVirtualMemory var2, long var3) {
         this.elfUnit = var1;
         this.mem = var2;
         this.actualImageBase = var3;
         this.endianness = var1.getHeader().getEndianness();
      }
   }
}
