package com.pnfsoftware.jeb.core.units.code.asm.type;

import com.pnfsoftware.jeb.core.units.codeobject.CompilerType;
import com.pnfsoftware.jeb.core.units.codeobject.ProcessorType;
import com.pnfsoftware.jeb.core.units.codeobject.SubsystemType;
import com.pnfsoftware.jeb.util.serialization.annotations.Ser;
import java.util.Collection;
import java.util.List;
import java.util.Map;

@Ser
public interface ICallingConvention {
   int FLAG_STACK_CLEANED_BY_CALLEE = 1;
   int FLAG_OUTPUT_AFTER_INPUT = 32;
   int FLAG_FLOAT_INPUT_ON_STACK = 64;
   int FLAG_LINK_AFTER_INPUT = 128;
   int FLAG_IPRD = 256;
   int FLAG_OUTPUT_PUSHED = 512;
   int FLAG_PARALLEL_INPUT_REGISTER_STACKS = 1024;
   int FLAG_FIRST_ARG_IS_THIS_POINTER = 2048;
   int FLAG_COMPOSITE_INPUT_ON_STACK = 4096;
   int FLAG_FORBID_PARAMS_2SLOTSUP = 8192;
   int FLAG_FORBID_PARAMS_3SLOTSUP = 16384;
   int FLAG_SKIP_PASSED_INPUT_REGISTERS = 32768;

   long getIdentifierKey();

   String getNotes();

   int getFlags();

   boolean hasFlag(int var1);

   default boolean isStackCleanedByCaller() {
      return (this.getFlags() & 1) == 0;
   }

   default boolean isStackCleanedByCallee() {
      return (this.getFlags() & 1) != 0;
   }

   String getName();

   List getAlternateNames();

   List getNames();

   List getProcessorTypes();

   List getSubsystemTypes();

   List getCompilerTypes();

   boolean isCompatibleWith(ProcessorType var1, SubsystemType var2, CompilerType var3);

   Collection getSpoiledRegisters();

   Collection getPureSpoiledRegisters();

   StorageEntry getReturnAddressSlot();

   StorageEntry getReturnAddressSlot(Integer var1);

   StorageEntry getOutput(TypeLayoutInfo var1, int var2);

   int getInputSlotCountHint();

   int getOutputSlotCountHint();

   Map getSlotcountAlignmentMap();

   int determineSlotcountAlignment(int var1);

   int getIPRDMinimumSlotCount();

   StorageEntry getIPRDInputPtrEntry();

   StorageEntry getIPRDOutputPtrEntry();

   ICallingConvention getIPRDConvention();

   IStorageEntryGenerator getInputsGenerator();

   IStorageEntryGenerator getOutputsGenerator(int var1);

   boolean isUnknown();

   String format(int var1);
}
