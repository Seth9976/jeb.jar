package com.pnfsoftware.jeb.core.units.codeobject;

import com.pnfsoftware.jeb.core.units.code.asm.memory.IVirtualMemory;
import com.pnfsoftware.jeb.core.units.code.asm.memory.MemoryException;
import com.pnfsoftware.jeb.corei.parsers.elf.vb;
import com.pnfsoftware.jeb.util.base.Assert;
import com.pnfsoftware.jeb.util.io.Endianness;

public class ELFStandardRelocOperations {
   public final IELFUnit elfUnit;
   public final IVirtualMemory mem;
   public final long actualImageBase;
   public final long theoriticalImageBase;
   public final long deltaImageBase;
   public final IELFSymbolEntry se;
   public final IELFRelocationEntry relocEntry;
   public final IELFRelocationTable relocTable;
   public final long targetAddress;
   public final Endianness endianness;

   public ELFStandardRelocOperations(IELFRelocationApplicator.RelocatedFile var1, IELFRelocationApplicator.RelocInstance var2) {
      this.elfUnit = var1.elfUnit;
      this.mem = var1.mem;
      this.actualImageBase = var1.actualImageBase;
      this.theoriticalImageBase = this.elfUnit.getLoaderInformation().getImageBase();
      this.deltaImageBase = this.actualImageBase - this.theoriticalImageBase;
      this.relocEntry = var2.relocEntry;
      this.relocTable = var2.relocTable;
      this.se = var2.se;
      this.targetAddress = this.getTargetAddress();
      this.endianness = var1.endianness;
   }

   private long getTargetAddress() {
      long var1 = this.relocEntry.getOffset();
      if (this.elfUnit.getHeader().getType() == 1) {
         Assert.a(this.elfUnit.getSectionCount() != 0, "REL without sections");
         return this.actualImageBase + var1 + this.elfUnit.getSection(this.relocTable.getTargetSectionIndex()).getOffsetInMemory();
      } else {
         return this.deltaImageBase + var1;
      }
   }

   public long A() throws MemoryException {
      return this.relocEntry.isAddendSet() ? this.relocEntry.getAddend() : this.mem.readInt(this.targetAddress) & 4294967295L;
   }

   public long S() {
      if (this.se.getType() == 3) {
         return this.actualImageBase + this.elfUnit.getSection(this.se.getShIndex()).getOffsetInMemory();
      } else if (this.elfUnit.getHeader().getType() == 1) {
         return this.actualImageBase + this.se.getAddress();
      } else {
         return ((vb)this.elfUnit).q() ? this.actualImageBase + this.se.getAddress() : this.deltaImageBase + this.se.getValue();
      }
   }

   public long P() {
      return this.targetAddress;
   }

   public long B() {
      return this.actualImageBase;
   }
}
