package com.pnfsoftware.jeb.core.units.code.asm.type;

import com.pnfsoftware.jeb.core.units.code.asm.processor.IRegisterBank;
import com.pnfsoftware.jeb.core.units.code.asm.processor.arch.RegisterUtil;
import com.pnfsoftware.jeb.core.units.codeobject.CompilerType;
import com.pnfsoftware.jeb.core.units.codeobject.ProcessorType;
import com.pnfsoftware.jeb.core.units.codeobject.SubsystemType;
import com.pnfsoftware.jeb.util.collect.CollectionUtil;
import com.pnfsoftware.jeb.util.encoding.Hash;
import com.pnfsoftware.jeb.util.format.Strings;
import com.pnfsoftware.jeb.util.io.EndianUtil;
import com.pnfsoftware.jeb.util.serialization.annotations.Ser;
import com.pnfsoftware.jeb.util.serialization.annotations.SerId;
import com.pnfsoftware.jeb.util.serialization.annotations.SerTransient;
import com.pnfsoftware.jebglobal.aeb;
import java.nio.ByteOrder;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

@Ser
public class CallingConvention implements ICallingConvention {
   @SerId(1)
   String name;
   @SerId(2)
   List altnames = new ArrayList();
   @SerId(3)
   int flags;
   @SerId(4)
   List proctypes = new ArrayList();
   @SerId(5)
   List sstypes = new ArrayList();
   @SerId(6)
   List comptypes = new ArrayList();
   @SerId(7)
   List spoiledRegisters;
   @SerId(8)
   List savedRegisters;
   @SerId(9)
   StorageEntry retAddrEntry;
   @SerId(10)
   List outEntries = new ArrayList();
   @SerId(11)
   List inEntries = new ArrayList();
   @SerId(12)
   List outRegPairs = new ArrayList();
   @SerId(13)
   List inRegPairs = new ArrayList();
   @SerId(14)
   StorageEntry outFpEntry;
   @SerId(15)
   List inFpEntries = new ArrayList();
   @SerId(16)
   String notes;
   @SerId(17)
   Map slotcountAlignmentMap = new HashMap();
   @SerId(18)
   List outRegQuads = new ArrayList();
   @SerId(19)
   List inRegQuads = new ArrayList();
   @SerId(21)
   int iprdSlotcnt;
   @SerId(22)
   StorageEntry iprdInputPtrEntry;
   @SerId(23)
   StorageEntry iprdOutputPtrEntry;
   @SerId(24)
   CallingConvention iprdConvention;
   @SerTransient
   private volatile Set allSpoiledRegisters;
   @SerTransient
   private volatile Set pureSpoiledRegisters;
   @SerTransient
   private Long key;

   public CallingConvention clone() {
      CallingConvention var1 = new CallingConvention();
      var1.name = this.name;
      var1.altnames.addAll(this.altnames);
      var1.flags = this.flags;
      var1.proctypes.addAll(this.proctypes);
      var1.sstypes.addAll(this.sstypes);
      var1.comptypes.addAll(this.comptypes);
      if (this.spoiledRegisters != null) {
         var1.spoiledRegisters = new ArrayList(this.spoiledRegisters);
      }

      if (this.savedRegisters != null) {
         var1.savedRegisters = new ArrayList(this.savedRegisters);
      }

      var1.retAddrEntry = this.retAddrEntry;
      var1.outEntries.addAll(this.outEntries);
      var1.inEntries.addAll(this.inEntries);
      var1.outRegPairs.addAll(this.outRegPairs);
      var1.inRegPairs.addAll(this.inRegPairs);
      var1.outFpEntry = this.outFpEntry;
      var1.inFpEntries.addAll(this.inFpEntries);
      var1.notes = this.notes;
      var1.slotcountAlignmentMap.putAll(this.slotcountAlignmentMap);
      var1.outRegQuads.addAll(this.outRegQuads);
      var1.inRegQuads.addAll(this.inRegQuads);
      var1.iprdSlotcnt = this.iprdSlotcnt;
      var1.iprdInputPtrEntry = this.iprdInputPtrEntry;
      var1.iprdOutputPtrEntry = this.iprdOutputPtrEntry;
      var1.iprdConvention = this.iprdConvention;
      return var1;
   }

   @Override
   public long getIdentifierKey() {
      if (this.key == null) {
         String var1 = this.getName()
            + Strings.joinList(this.getAlternateNames())
            + this.getFlags()
            + Strings.joinList(this.getProcessorTypes())
            + Strings.joinList(this.getSubsystemTypes())
            + Strings.joinList(this.getCompilerTypes());
         byte[] var2 = Hash.calculateMD5(Strings.encodeUTF8(var1));
         this.key = EndianUtil.bytesToLong(var2, 0, ByteOrder.LITTLE_ENDIAN);
      }

      return this.key;
   }

   @Override
   public String getNotes() {
      return this.notes;
   }

   @Override
   public int getFlags() {
      return this.flags;
   }

   @Override
   public boolean hasFlag(int var1) {
      return (this.flags & var1) == var1;
   }

   @Override
   public boolean isUnknown() {
      return CallingConventionName.UNKNOWN.toString().equals(this.name);
   }

   @Override
   public String getName() {
      return this.name;
   }

   @Override
   public List getAlternateNames() {
      return Collections.unmodifiableList(this.altnames);
   }

   @Override
   public List getNames() {
      ArrayList var1 = new ArrayList(1 + this.altnames.size());
      if (this.name != null) {
         var1.add(this.name);
      }

      var1.addAll(this.altnames);
      return Collections.unmodifiableList(var1);
   }

   public ProcessorType getPrimaryProcessorType() {
      return this.proctypes.isEmpty() ? null : (ProcessorType)this.proctypes.get(0);
   }

   @Override
   public List getProcessorTypes() {
      return Collections.unmodifiableList(this.proctypes);
   }

   @Override
   public List getSubsystemTypes() {
      return Collections.unmodifiableList(this.sstypes);
   }

   @Override
   public List getCompilerTypes() {
      return Collections.unmodifiableList(this.comptypes);
   }

   @Override
   public boolean isCompatibleWith(ProcessorType var1, SubsystemType var2, CompilerType var3) {
      if ((var1.isARM() || var1.isIntel()) && var2 == SubsystemType.UNKNOWN) {
         var2 = SubsystemType.LINUX;
      }

      boolean var4 = false;

      for (ProcessorType var6 : this.proctypes) {
         if (var6 == ProcessorType.UNKNOWN || var6 == var1) {
            var4 = true;
            break;
         }
      }

      if (!var4) {
         return false;
      } else {
         if (!this.sstypes.isEmpty()) {
            var4 = false;

            for (SubsystemType var11 : this.sstypes) {
               if (var11 == SubsystemType.UNKNOWN || var11.isCompatibleWith(var2)) {
                  var4 = true;
                  break;
               }
            }

            if (!var4) {
               return false;
            }
         }

         if (!this.comptypes.isEmpty()) {
            var4 = false;

            for (CompilerType var12 : this.comptypes) {
               if (var12 == CompilerType.UNKNOWN || var12.isCompatibleWith(var3)) {
                  var4 = true;
                  break;
               }
            }

            if (!var4) {
               return false;
            }
         }

         return true;
      }
   }

   @Override
   public Collection getSpoiledRegisters() {
      if (this.allSpoiledRegisters == null) {
         synchronized (this) {
            if (this.allSpoiledRegisters == null) {
               HashSet var2 = new HashSet(this.spoiledRegisters);
               if (this.retAddrEntry != null && this.retAddrEntry.isRegisterBased()) {
                  var2.addAll(this.retAddrEntry.getRegisters());
               }

               for (StorageEntry var4 : this.outEntries) {
                  if (var4.isRegisterBased()) {
                     var2.addAll(var4.getRegisters());
                  }
               }

               for (StorageEntry var9 : this.outRegPairs) {
                  if (var9.isRegisterBased()) {
                     var2.addAll(var9.getRegisters());
                  }
               }

               for (StorageEntry var10 : this.outRegQuads) {
                  if (var10.isRegisterBased()) {
                     var2.addAll(var10.getRegisters());
                  }
               }

               if (this.outFpEntry != null && this.outFpEntry.isRegisterBased()) {
                  var2.addAll(this.outFpEntry.getRegisters());
               }

               this.allSpoiledRegisters = var2;
            }
         }
      }

      return Collections.unmodifiableCollection(this.allSpoiledRegisters);
   }

   @Override
   public Collection getPureSpoiledRegisters() {
      if (this.pureSpoiledRegisters == null) {
         synchronized (this) {
            if (this.pureSpoiledRegisters == null) {
               HashSet var2 = new HashSet(this.spoiledRegisters);
               if (this.retAddrEntry != null && this.retAddrEntry.isRegisterBased()) {
                  var2.removeAll(this.retAddrEntry.getRegisters());
               }

               for (StorageEntry var4 : this.outEntries) {
                  if (var4.isRegisterBased()) {
                     var2.removeAll(var4.getRegisters());
                  }
               }

               for (StorageEntry var9 : this.outRegPairs) {
                  if (var9.isRegisterBased()) {
                     var2.removeAll(var9.getRegisters());
                  }
               }

               for (StorageEntry var10 : this.outRegQuads) {
                  if (var10.isRegisterBased()) {
                     var2.removeAll(var10.getRegisters());
                  }
               }

               if (this.outFpEntry != null && this.outFpEntry.isRegisterBased()) {
                  var2.removeAll(this.outFpEntry.getRegisters());
               }

               this.pureSpoiledRegisters = var2;
            }
         }
      }

      return Collections.unmodifiableCollection(this.pureSpoiledRegisters);
   }

   @Override
   public StorageEntry getReturnAddressSlot() {
      return this.getReturnAddressSlot(null);
   }

   @Override
   public StorageEntry getReturnAddressSlot(Integer var1) {
      if (this.hasFlag(128)) {
         if (var1 == null) {
            var1 = 0;
         }

         return StorageEntry.createStackSlot(var1.intValue());
      } else {
         return this.retAddrEntry;
      }
   }

   StorageEntry getOutputSlotByIndex(int var1) {
      return this.getSlotByIndex(this.outEntries, var1);
   }

   StorageEntry getOutputDualSlot() {
      return this.outRegPairs.isEmpty() ? null : (StorageEntry)this.outRegPairs.get(0);
   }

   private StorageEntry getStackParamBegin() {
      if (this.inEntries.isEmpty()) {
         return null;
      } else {
         StorageEntry var1 = (StorageEntry)this.inEntries.get(this.inEntries.size() - 1);
         return !var1.isStackBased() ? null : var1;
      }
   }

   private StorageEntry getStackParamBegin(int var1) {
      StorageEntry var2 = this.getStackParamBegin();
      return var2 == null ? null : var2.withCount(var1);
   }

   @Override
   public StorageEntry getOutput(TypeLayoutInfo var1, int var2) {
      return this.getOutputsGenerator(var2).next(var1);
   }

   private StorageEntry getSlotByIndex(List var1, int var2) {
      if (var2 < 0) {
         throw new IllegalArgumentException("Invalid slot index: " + var2);
      } else if (var2 < var1.size()) {
         return (StorageEntry)var1.get(var2);
      } else {
         int var3 = var1.size() - 1;
         if (var3 < 0) {
            return null;
         } else {
            StorageEntry var4 = (StorageEntry)var1.get(var3);
            return var4.getType() == StorageEntry.Type.STACK ? StorageEntry.createStackSlot(var4.getValue() + var2 - var3) : null;
         }
      }
   }

   @Override
   public int getInputSlotCountHint() {
      return this.inEntries.size();
   }

   @Override
   public int getOutputSlotCountHint() {
      return this.outEntries.size();
   }

   @Override
   public Map getSlotcountAlignmentMap() {
      return this.slotcountAlignmentMap;
   }

   @Override
   public int determineSlotcountAlignment(int var1) {
      if (var1 > 1 && this.slotcountAlignmentMap != null && !this.slotcountAlignmentMap.isEmpty()) {
         Integer var2 = (Integer)this.slotcountAlignmentMap.get(var1);
         if (var2 == null) {
            var2 = (Integer)this.slotcountAlignmentMap.get(0);
            if (var2 == null) {
               var2 = 1;
            }
         }

         return var2;
      } else {
         return 1;
      }
   }

   @Override
   public int getIPRDMinimumSlotCount() {
      return this.iprdSlotcnt;
   }

   @Override
   public StorageEntry getIPRDInputPtrEntry() {
      return this.iprdInputPtrEntry;
   }

   @Override
   public StorageEntry getIPRDOutputPtrEntry() {
      return this.iprdOutputPtrEntry;
   }

   @Override
   public ICallingConvention getIPRDConvention() {
      return this.iprdConvention;
   }

   @Override
   public String toString() {
      return this.format(0);
   }

   @Override
   public String format(int var1) {
      if (var1 == 1) {
         return this.formatUser();
      } else if (var1 == 2) {
         return this.formatParseable();
      } else {
         StringBuilder var2 = new StringBuilder(this.getName());
         if (!this.getAlternateNames().isEmpty()) {
            Strings.ff(var2, "(%s)", Strings.join(",", this.getAlternateNames()));
         }

         if (!this.getProcessorTypes().isEmpty()) {
            Strings.ff(var2, ",procs:%s", Strings.join(",", this.getProcessorTypes()));
         }

         if (!this.getSubsystemTypes().isEmpty()) {
            Strings.ff(var2, ",subs:%s", Strings.join(",", this.getSubsystemTypes()));
         }

         if (!this.getCompilerTypes().isEmpty()) {
            Strings.ff(var2, ",comps:%s", Strings.join(",", this.getCompilerTypes()));
         }

         return var2.toString();
      }
   }

   public String formatParseable() {
      StringBuilder var1 = new StringBuilder();
      String var2 = this.getNotes();
      if (var2 != null) {
         Strings.ff(var1, "notes: %s\n", var2);
      }

      if (this.getAlternateNames().isEmpty()) {
         Strings.ff(var1, "name: %s\n", Strings.safe(this.getName()));
      } else {
         Strings.ff(var1, "names: %s\n", Strings.joinList(this.getNames()));
      }

      Strings.ff(var1, "processors: %s\n", Strings.joinList(this.getProcessorTypes()));
      Strings.ff(var1, "subsystems: %s\n", Strings.joinList(this.getSubsystemTypes()));
      Strings.ff(var1, "compilers: %s\n", Strings.joinList(this.getCompilerTypes()));
      if (this.getFlags() != 0) {
         Strings.ff(var1, "flags: [");
         this.formatFlags(var1, ",");
         Strings.ff(var1, "]\n");
      }

      IRegisterBank var3 = RegisterUtil.getBank((ProcessorType)this.proctypes.get(0));
      if (var3 == null) {
         Strings.ff(var1, "\n! cannot format registers information\n");
      } else {
         Strings.ff(
            var1,
            "\nspoiled_registers: %s\n",
            Strings.joinList((Iterable)this.spoiledRegisters.stream().map(var1x -> var3.getDescriptionEntryById(var1x).getName()).collect(Collectors.toList()))
         );
         if (this.getReturnAddressSlot() != null) {
            Strings.ff(var1, "return_address_location: %s\n", this.getReturnAddressSlot().formatParseable(var3));
         }

         Strings.ff(
            var1, "\ninputs: %s\n", Strings.joinList((Iterable)this.inEntries.stream().map(var1x -> var1x.formatParseable(var3)).collect(Collectors.toList()))
         );
         Strings.ff(
            var1, "outputs: %s\n", Strings.joinList((Iterable)this.outEntries.stream().map(var1x -> var1x.formatParseable(var3)).collect(Collectors.toList()))
         );
         Strings.ff(
            var1,
            "\ninput_pairs: %s\n",
            Strings.joinList((Iterable)this.inRegPairs.stream().map(var1x -> var1x.formatParseable(var3)).collect(Collectors.toList()))
         );
         Strings.ff(
            var1,
            "output_pairs: %s\n",
            Strings.joinList((Iterable)this.outRegPairs.stream().map(var1x -> var1x.formatParseable(var3)).collect(Collectors.toList()))
         );
         Strings.ff(
            var1,
            "\ninput_floats: %s\n",
            Strings.joinList((Iterable)this.inFpEntries.stream().map(var1x -> var1x.formatParseable(var3)).collect(Collectors.toList()))
         );
         Strings.ff(var1, "output_float: %s\n", this.outFpEntry == null ? "" : this.outFpEntry.formatParseable(var3));
         Strings.ff(var1, "\nalignments: %s\n", this.slotcountAlignmentMap);
      }

      return var1.toString();
   }

   public String formatUser() {
      StringBuilder var1 = new StringBuilder();
      String var2 = this.getNotes();
      if (var2 != null) {
         Strings.ff(var1, "Notes      : %s\n", var2);
      }

      Strings.ff(var1, "Names      : %s\n", Strings.join(",", this.getNames()));
      if (!this.getProcessorTypes().isEmpty()) {
         Strings.ff(var1, "Processors : %s\n", Strings.join(",", this.getProcessorTypes()));
      }

      if (!this.getSubsystemTypes().isEmpty()) {
         Strings.ff(var1, "Subsystem  : %s\n", Strings.join(",", this.getSubsystemTypes()));
      }

      if (!this.getCompilerTypes().isEmpty()) {
         Strings.ff(var1, "Compiler   : %s\n", Strings.join(",", this.getCompilerTypes()));
      }

      if (this.getFlags() != 0) {
         Strings.ff(var1, "Flags      : ");
         this.formatFlags(var1, " ");
         Strings.ff(var1, "\n");
      }

      Strings.ff(var1, "\n");
      IRegisterBank var3 = RegisterUtil.getBank((ProcessorType)this.proctypes.get(0));
      if (var3 == null) {
         Strings.ff(var1, "\n! cannot format registers information\n");
      } else {
         if (!this.spoiledRegisters.isEmpty()) {
            Strings.ff(var1, "Spoiled registers:\n  ");

            for (long var5 : this.spoiledRegisters) {
               Strings.ff(var1, "%s ", var3.getDescriptionEntryById(var5).getName());
            }

            Strings.ff(var1, "\n");
         }

         Strings.ff(var1, "\n");
         if (this.getReturnAddressSlot() != null) {
            Strings.ff(var1, "Return address location:\n");
            Strings.ff(var1, "  %s\n", this.getReturnAddressSlot().formatLong(var3));
            Strings.ff(var1, "\n");
         }

         Strings.ff(var1, "Inputs:\n");
         int var7 = 0;

         for (StorageEntry var6 : this.inEntries) {
            Strings.ff(var1, "  param #%d: %s\n", var7, var6.formatLong(var3));
            var7++;
         }

         Strings.ff(var1, "  ...\n");
         var7 = 0;

         for (StorageEntry var17 : this.inRegPairs) {
            Strings.ff(var1, "  (long param #%d: %s)\n", var7, var17.formatLong(var3));
            var7++;
         }

         Strings.ff(var1, "\n");
         Strings.ff(var1, "Outputs:\n");
         var7 = 0;

         for (StorageEntry var18 : this.outEntries) {
            Strings.ff(var1, "  retval #%d: %s\n", var7, var18.formatLong(var3));
            var7++;
         }

         var7 = 0;

         for (StorageEntry var19 : this.outRegPairs) {
            Strings.ff(var1, "  (long retval #%d: %s)\n", var7, var19.formatLong(var3));
            var7++;
         }

         Strings.ff(var1, "\n");
         Strings.ff(var1, "FP Inputs:\n");
         var7 = 0;

         for (StorageEntry var20 : this.inFpEntries) {
            Strings.ff(var1, "  float #%d: %s\n", var7, var20.formatLong(var3));
            var7++;
         }

         Strings.ff(var1, "FP Output:\n");
         if (this.outFpEntry != null) {
            Strings.ff(var1, "  float: %s\n", this.outFpEntry.formatLong(var3));
         }

         Strings.ff(var1, "\n");
         if (!this.slotcountAlignmentMap.isEmpty()) {
            Strings.ff("Alignments: %s\n", this.slotcountAlignmentMap);
         }
      }

      return var1.toString();
   }

   private void formatFlags(StringBuilder var1, String var2) {
      if (this.hasFlag(1)) {
         Strings.ff(var1, "STACK_CLEANED_BY_CALLEE" + var2);
      }

      if (this.hasFlag(32)) {
         Strings.ff(var1, "OUTPUT_AFTER_INPUT" + var2);
      }

      if (this.hasFlag(64)) {
         Strings.ff(var1, "FLOAT_INPUT_ON_STACK" + var2);
      }

      if (this.hasFlag(128)) {
         Strings.ff(var1, "LINK_AFTER_INPUT" + var2);
      }

      if (this.hasFlag(256)) {
         Strings.ff(var1, "IPRD" + var2);
      }

      if (this.hasFlag(512)) {
         Strings.ff(var1, "OUTPUT_PUSHED" + var2);
      }

      if (this.hasFlag(1024)) {
         Strings.ff(var1, "PARALLEL_INPUT_REGISTER_STACKS" + var2);
      }

      if (this.hasFlag(2048)) {
         Strings.ff(var1, "FIRST_ARG_IS_THIS_POINTER" + var2);
      }

      if (this.hasFlag(4096)) {
         Strings.ff(var1, "COMPOSITE_INPUT_ON_STACK" + var2);
      }

      if (this.hasFlag(8192)) {
         Strings.ff(var1, "FORBID_PARAMS_2SLOTSUP" + var2);
      }

      if (this.hasFlag(16384)) {
         Strings.ff(var1, "FORBID_PARAMS_3SLOTSUP" + var2);
      }

      if (this.hasFlag(32768)) {
         Strings.ff(var1, "SKIP_PASSED_INPUT_REGISTERS" + var2);
      }
   }

   public CallingConvention.ArgLocationGenerator getInputsGenerator() {
      return new CallingConvention.ArgLocationGenerator();
   }

   public CallingConvention.RetLocationGenerator getOutputsGenerator(int var1) {
      return new CallingConvention.RetLocationGenerator(var1);
   }

   public class ArgLocationGenerator implements IStorageEntryGenerator {
      private final boolean parallel;
      private final StorageEntry stkSlotStart;
      private final List regSlots;
      private List entries = new ArrayList();
      private StorageEntry lastEntry;
      private StorageEntry lastStackEntry;
      private int gpRegIndex;
      private int fpRegIndex;

      private ArgLocationGenerator() {
         this.parallel = CallingConvention.this.hasFlag(1024);
         int var2 = CallingConvention.this.inEntries.size();
         if (var2 >= 1 && ((StorageEntry)CallingConvention.this.inEntries.get(var2 - 1)).isStackBased()) {
            ArrayList var3 = new ArrayList(CallingConvention.this.inEntries);
            this.stkSlotStart = (StorageEntry)var3.remove(var2 - 1);
            this.regSlots = Collections.unmodifiableList(var3);
         } else {
            this.stkSlotStart = null;
            this.regSlots = Collections.unmodifiableList(CallingConvention.this.inEntries);
         }
      }

      @Override
      public void reset() {
         this.entries.clear();
         this.lastEntry = null;
         this.lastStackEntry = null;
         this.gpRegIndex = 0;
         this.fpRegIndex = 0;
      }

      private void incrGpRegIndex() {
         this.gpRegIndex++;
         if (this.parallel) {
            this.fpRegIndex++;
         }
      }

      private void incrFpRegIndex() {
         this.fpRegIndex++;
         if (this.parallel) {
            this.gpRegIndex++;
         }
      }

      @Override
      public List getCurrentEntries() {
         return Collections.unmodifiableList(this.entries);
      }

      @Override
      public StorageEntry next(TypeLayoutInfo var1) {
         StorageEntry var2 = this.nextInternal(var1);
         if (var2 == null) {
            return null;
         } else {
            this.entries.add(var2);
            this.lastEntry = var2;
            if (var2.isStackBased()) {
               this.lastStackEntry = var2;
            }

            return var2;
         }
      }

      private StorageEntry nextInternal(TypeLayoutInfo var1) {
         StorageEntry var2 = null;
         int var3 = var1.getSlotcount();
         if (var3 <= 0) {
            return null;
         } else if (var3 >= 2 && CallingConvention.this.hasFlag(8192)) {
            return null;
         } else if (var3 >= 3 && CallingConvention.this.hasFlag(16384)) {
            return null;
         } else {
            if (var1.isFloat()) {
               if (CallingConvention.this.hasFlag(64)) {
                  if (this.lastEntry != null && this.lastStackEntry != null) {
                     var2 = this.lastStackEntry.nextStackEntry(var3);
                  } else if (this.stkSlotStart != null) {
                     return this.stkSlotStart.withCount(var3);
                  }

                  return var2;
               }

               if (CallingConvention.this.inFpEntries != null && !CallingConvention.this.inFpEntries.isEmpty()) {
                  if (this.lastEntry == null || this.fpRegIndex < CallingConvention.this.inFpEntries.size()) {
                     var2 = (StorageEntry)CallingConvention.this.inFpEntries.get(this.fpRegIndex);
                     this.incrFpRegIndex();
                  } else if (this.lastStackEntry == null) {
                     var2 = CallingConvention.this.getStackParamBegin(var3);
                  } else {
                     var2 = this.lastStackEntry.nextStackEntry(var3);
                  }

                  return var2;
               }
            }

            if (var1.isComposite() && CallingConvention.this.hasFlag(4096)) {
               if (this.lastEntry != null && this.lastStackEntry != null) {
                  var2 = this.lastStackEntry.nextStackEntry(var3);
               } else if (this.stkSlotStart != null) {
                  return this.stkSlotStart.withCount(var3);
               }

               return var2;
            } else if (var3 == 1) {
               if (this.lastEntry == null) {
                  if (this.regSlots.isEmpty()) {
                     if (this.stkSlotStart != null) {
                        var2 = this.stkSlotStart.withCount(var3);
                     }
                  } else {
                     var2 = (StorageEntry)this.regSlots.get(this.gpRegIndex);
                     this.incrGpRegIndex();
                  }

                  return var2;
               } else {
                  if (this.gpRegIndex < this.regSlots.size() && this.canUseGPReg()) {
                     var2 = (StorageEntry)this.regSlots.get(this.gpRegIndex);
                     this.incrGpRegIndex();
                  } else if (this.lastStackEntry == null) {
                     if (this.stkSlotStart != null) {
                        var2 = this.stkSlotStart.withCount(1);
                     }
                  } else {
                     var2 = this.lastStackEntry.nextStackEntry(1);
                  }

                  return var2;
               }
            } else {
               int var4 = CallingConvention.this.determineSlotcountAlignment(var3);
               if (var3 == 2) {
                  if (this.lastEntry == null) {
                     if (CallingConvention.this.inRegPairs.isEmpty()) {
                        if (this.stkSlotStart != null) {
                           var2 = this.stkSlotStart.withCount(2);
                        }
                     } else {
                        var2 = (StorageEntry)CallingConvention.this.inRegPairs.get(0);
                        this.incrGpRegIndex();
                        this.incrGpRegIndex();
                     }

                     return var2;
                  } else {
                     if (this.gpRegIndex < this.regSlots.size() && this.canUseGPReg()) {
                        HashSet var5 = new HashSet();
                        this.entries.forEach(var1x -> var1x.collectRegisters(var5));

                        for (StorageEntry var7 : CallingConvention.this.inRegPairs) {
                           Collection var8 = var7.getRegisters();
                           if (!CollectionUtil.hasIntersection(var8, var5)) {
                              for (int var9 = this.regSlots.size() - 1; var9 >= 0; var9--) {
                                 if (CollectionUtil.hasIntersection(((StorageEntry)this.regSlots.get(var9)).getRegisters(), var8)) {
                                    while (this.gpRegIndex <= var9) {
                                       this.incrGpRegIndex();
                                    }
                                    break;
                                 }
                              }

                              return var7;
                           }
                        }
                     }

                     if (this.lastStackEntry == null) {
                        if (this.stkSlotStart != null) {
                           var2 = this.stkSlotStart.withCount(2);
                        }
                     } else {
                        var2 = this.lastStackEntry.nextStackEntry(2, var4);
                     }

                     return var2;
                  }
               } else {
                  if (this.lastStackEntry == null) {
                     if (this.stkSlotStart != null) {
                        var2 = this.stkSlotStart.withCount(var3);
                     }
                  } else {
                     var2 = this.lastStackEntry.nextStackEntry(var3, var4);
                  }

                  return var2;
               }
            }
         }
      }

      private boolean canUseGPReg() {
         return this.lastStackEntry == null || !CallingConvention.this.hasFlag(32768);
      }
   }

   public class RetLocationGenerator implements IStorageEntryGenerator {
      private final int inputStackSlotCount;
      private List entries = new ArrayList();
      private StorageEntry lastEntry;
      private StorageEntry lastStackEntry;

      private RetLocationGenerator(int var2) {
         this.inputStackSlotCount = var2;
      }

      @Override
      public void reset() {
         this.entries.clear();
         this.lastEntry = null;
         this.lastStackEntry = null;
      }

      @Override
      public List getCurrentEntries() {
         return Collections.unmodifiableList(this.entries);
      }

      @Override
      public StorageEntry next(TypeLayoutInfo var1) {
         StorageEntry var2 = this.nextInternal(var1);
         if (var2 == null) {
            return null;
         } else {
            this.entries.add(var2);
            this.lastEntry = var2;
            if (var2.isStackBased()) {
               this.lastStackEntry = var2;
            }

            return var2;
         }
      }

      private StorageEntry nextInternal(TypeLayoutInfo var1) {
         int var2 = this.entries.size();
         if (CallingConvention.this.hasFlag(32)) {
            return CallingConvention.this.getOutputSlotByIndex(this.inputStackSlotCount + var2);
         } else if (CallingConvention.this.hasFlag(512)) {
            int var3 = this.inputStackSlotCount;
            if (CallingConvention.this.getReturnAddressSlot(this.inputStackSlotCount).isStackBased()) {
               var3++;
            }

            var3 -= 1 + var2;
            return StorageEntry.createStackSlot(var3);
         } else if (var1.isFloat() && var2 == 0 && CallingConvention.this.outFpEntry != null) {
            return CallingConvention.this.outFpEntry;
         } else if (var1.getSlotcount() == 1) {
            return CallingConvention.this.getOutputSlotByIndex(var2);
         } else if (var1.getSlotcount() == 2) {
            if (var2 != 0) {
               aeb.q(new RuntimeException("Unexpected index for multi-slot output: " + var2));
               return null;
            } else {
               return CallingConvention.this.getOutputDualSlot();
            }
         } else {
            return null;
         }
      }
   }
}
