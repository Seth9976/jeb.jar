package com.pnfsoftware.jeb.core.units.code.asm.type;

import com.pnfsoftware.jeb.core.units.codeobject.CompilerType;
import com.pnfsoftware.jeb.core.units.codeobject.ProcessorType;
import com.pnfsoftware.jeb.core.units.codeobject.SubsystemType;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CallingConventionBuilder {
   int flags;
   String notes;
   String name;
   List altnames = new ArrayList();
   List proctypes = new ArrayList();
   List sstypes = new ArrayList();
   List comptypes = new ArrayList();
   List spoiledRegisters = new ArrayList();
   StorageEntry retAddrEntry;
   List inEntries = new ArrayList();
   List inRegPairs = new ArrayList();
   List inFpEntries = new ArrayList();
   List outEntries = new ArrayList();
   List outRegPairs = new ArrayList();
   StorageEntry outFpEntry;
   int iprdSlotcnt;
   StorageEntry iprdInputPtrEntry;
   StorageEntry iprdOutputPtrEntry;
   Map slotcountAlignmentMap = new HashMap();
   private boolean built;

   private CallingConventionBuilder() {
   }

   public CallingConventionBuilder duplicate() {
      CallingConventionBuilder var1 = new CallingConventionBuilder();
      var1.flags = this.flags;
      var1.notes = this.notes;
      var1.name = this.name;
      var1.altnames.addAll(this.altnames);
      var1.proctypes.addAll(this.proctypes);
      var1.sstypes.addAll(this.sstypes);
      var1.comptypes.addAll(this.comptypes);
      var1.spoiledRegisters.addAll(this.spoiledRegisters);
      var1.retAddrEntry = this.retAddrEntry;
      var1.inEntries.addAll(this.inEntries);
      var1.inRegPairs.addAll(this.inRegPairs);
      var1.inFpEntries.addAll(this.inFpEntries);
      var1.outEntries.addAll(this.outEntries);
      var1.outRegPairs.addAll(this.outRegPairs);
      var1.outFpEntry = this.outFpEntry;
      var1.slotcountAlignmentMap.putAll(this.slotcountAlignmentMap);
      return var1;
   }

   public CallingConventionBuilder(String var1) {
      this.setName(var1);
   }

   public CallingConventionBuilder(String var1, ProcessorType var2) {
      this.setName(var1);
      this.addProcessorType(var2);
   }

   public CallingConventionBuilder(CallingConventionName var1, ProcessorType var2) {
      this(var1.toString(), var2);
   }

   public void setNotes(String var1) {
      this.notes = var1;
   }

   public CallingConventionBuilder setFlags(int var1) {
      this.flags = var1;
      return this;
   }

   public CallingConventionBuilder addFlags(int var1) {
      this.flags |= var1;
      return this;
   }

   public CallingConventionBuilder setName(String var1) {
      return this.setName(var1, false);
   }

   public CallingConventionBuilder setName(String var1, boolean var2) {
      this.name = var1;
      if (var2) {
         this.altnames.clear();
      }

      return this;
   }

   public CallingConventionBuilder setAlternateNames(Collection var1) {
      if (var1 != null) {
         this.altnames.clear();
         this.altnames.addAll(var1);
      }

      return this;
   }

   public CallingConventionBuilder addAlternateName(String var1) {
      if (var1 != null && !this.altnames.contains(var1)) {
         this.altnames.add(var1);
      }

      return this;
   }

   public CallingConventionBuilder addAlternateNames(Collection var1) {
      if (var1 != null) {
         for (String var3 : var1) {
            this.addAlternateName(var3);
         }
      }

      return this;
   }

   public CallingConventionBuilder setProcessorTypes(Collection var1) {
      if (var1 != null) {
         this.proctypes.clear();
         this.proctypes.addAll(var1);
      }

      return this;
   }

   public CallingConventionBuilder addProcessorType(ProcessorType var1) {
      if (var1 != null && !this.proctypes.contains(var1)) {
         this.proctypes.add(var1);
      }

      return this;
   }

   public CallingConventionBuilder addSubsystemType(SubsystemType var1) {
      if (var1 != null && !this.sstypes.contains(var1)) {
         this.sstypes.add(var1);
      }

      return this;
   }

   public CallingConventionBuilder addCompilerType(CompilerType var1) {
      if (var1 != null && !this.comptypes.contains(var1)) {
         this.comptypes.add(var1);
      }

      return this;
   }

   public CallingConventionBuilder addSpoiledRegister(long var1) {
      if (!this.spoiledRegisters.contains(var1)) {
         this.spoiledRegisters.add(var1);
      }

      return this;
   }

   public CallingConventionBuilder addSpoiledRegisters(long... var1) {
      for (long var5 : var1) {
         this.addSpoiledRegister(var5);
      }

      return this;
   }

   public CallingConventionBuilder addSpoiledRegisters(Collection var1) {
      if (var1 != null) {
         for (StorageEntry var3 : var1) {
            if (var3.getType() == StorageEntry.Type.REGISTER) {
               this.addSpoiledRegister(var3.getValue());
            }
         }
      }

      return this;
   }

   public CallingConventionBuilder setReturnAddressSlot(StorageEntry var1) {
      this.retAddrEntry = var1;
      return this;
   }

   public CallingConventionBuilder addOutputSlot(StorageEntry var1) {
      this.outEntries.add(var1);
      return this;
   }

   public CallingConventionBuilder addOutputSlots(Collection var1) {
      if (var1 != null) {
         this.outEntries.addAll(var1);
      }

      return this;
   }

   public CallingConventionBuilder addInputSlot(StorageEntry var1) {
      this.inEntries.add(var1);
      return this;
   }

   public CallingConventionBuilder addInputSlots(Collection var1) {
      this.inEntries.addAll(var1);
      return this;
   }

   public CallingConventionBuilder addInputFpSlot(StorageEntry var1) {
      this.inFpEntries.add(var1);
      return this;
   }

   public CallingConventionBuilder addInputFpSlots(Collection var1) {
      if (var1 != null) {
         for (StorageEntry var3 : var1) {
            this.addInputFpSlot(var3);
         }
      }

      return this;
   }

   public CallingConventionBuilder addOutputRegisterPair(StorageEntry var1) {
      if (var1.getType() != StorageEntry.Type.REGISTER_PAIR) {
         throw new IllegalArgumentException("Expected a register pair slot, got " + var1);
      } else {
         this.outRegPairs.add(var1);
         return this;
      }
   }

   public CallingConventionBuilder addOutputRegisterPairs(Collection var1) {
      if (var1 != null) {
         for (StorageEntry var3 : var1) {
            this.addOutputRegisterPair(var3);
         }
      }

      return this;
   }

   public CallingConventionBuilder addInputRegisterPair(StorageEntry var1) {
      if (var1.getType() != StorageEntry.Type.REGISTER_PAIR) {
         throw new IllegalArgumentException("Expected a register pair slot, got " + var1);
      } else {
         this.inRegPairs.add(var1);
         return this;
      }
   }

   public CallingConventionBuilder addInputRegisterPairs(Collection var1) {
      if (var1 != null) {
         for (StorageEntry var3 : var1) {
            this.addInputRegisterPair(var3);
         }
      }

      return this;
   }

   public CallingConventionBuilder setOutputFpSlot(StorageEntry var1) {
      this.outFpEntry = var1;
      return this;
   }

   public CallingConventionBuilder setIPRD(int var1, StorageEntry var2, StorageEntry var3) {
      this.iprdSlotcnt = var1;
      this.iprdInputPtrEntry = var2;
      this.iprdOutputPtrEntry = var3;
      return this;
   }

   public CallingConventionBuilder addAlignementRequirement(int var1, int var2) {
      this.slotcountAlignmentMap.put(var1, var2);
      return this;
   }

   public ICallingConvention build() {
      if (this.built) {
         throw new IllegalStateException();
      } else {
         this.built = true;
         CallingConvention var1 = new CallingConvention();
         var1.flags = this.flags;
         var1.notes = this.notes;
         var1.name = this.name;
         var1.altnames = this.altnames;
         var1.proctypes = this.proctypes;
         var1.sstypes = this.sstypes;
         var1.comptypes = this.comptypes;
         var1.spoiledRegisters = this.spoiledRegisters;
         var1.retAddrEntry = this.retAddrEntry;
         var1.inEntries = this.inEntries;
         var1.outEntries = this.outEntries;
         var1.inRegPairs = this.inRegPairs;
         var1.outRegPairs = this.outRegPairs;
         var1.inFpEntries = this.inFpEntries;
         var1.outFpEntry = this.outFpEntry;
         var1.slotcountAlignmentMap = this.slotcountAlignmentMap;
         var1.iprdSlotcnt = this.iprdSlotcnt;
         var1.iprdInputPtrEntry = this.iprdInputPtrEntry;
         var1.iprdOutputPtrEntry = this.iprdOutputPtrEntry;
         if ((var1.flags & 256) != 0) {
            CallingConvention var2 = var1.clone();
            var2.name = var2.name + "_iprd";
            var2.altnames.clear();
            var2.flags &= -257;
            var2.iprdSlotcnt = 0;
            var2.iprdConvention = null;
            if (var2.iprdInputPtrEntry != null) {
               var2.inEntries.add(0, var2.iprdInputPtrEntry);
               var2.iprdInputPtrEntry = null;
            }

            if (var2.iprdOutputPtrEntry != null) {
               var2.outEntries.add(0, var2.iprdOutputPtrEntry);
               var2.iprdOutputPtrEntry = null;
            }

            var2.outFpEntry = null;
            var2.outRegPairs.clear();
            var2.outRegQuads.clear();
            var1.iprdConvention = var2;
         }

         return var1;
      }
   }
}
