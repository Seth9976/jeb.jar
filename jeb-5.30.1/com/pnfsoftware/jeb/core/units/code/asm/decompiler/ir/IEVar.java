package com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir;

import com.pnfsoftware.jeb.core.units.code.IVariable;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.IERoutineContext;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ast.ICElement;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ast.ICMethod;
import com.pnfsoftware.jeb.util.serialization.annotations.Ser;

@Ser
public interface IEVar extends IVariable, IEGeneric {
   int FLAG_NO_COPY = 16;
   int FLAG_LIKELY_SAVED_VAR = 32;
   int FLAG_LIKELY_COPIED_PARAM = 64;
   int ID_BEGIN = 0;
   int ID_END = Integer.MAX_VALUE;
   int ID_PHYSICAL_REGISTER = 0;
   int ID_PHYSICAL_REGISTER_END = 65536;
   int ID_VIRTUAL_REGISTER = 65536;
   int ID_VIRTUAL_REGISTER_END = 131072;
   int ID_REGISTER = 0;
   int ID_REGISTER_END = 131072;
   int REGISTER_RANGE_SIZE = 131072;
   int ID_SYMBOL = 131072;
   int ID_SYMBOL_END = 196608;
   int ID_MEMVAR = 16777216;
   int ID_MEMVAR_END = Integer.MAX_VALUE;
   int ID_PSEUDO_VAR = -1;
   int IDL_PSEUDO_VAR = 1;
   int IDL_PSEUDO_VAR_END = 2;
   int IDL_SPECIAL = 2;
   int IDL_SPECIAL_END = 65536;
   int IDL_MIRROR = 65536;
   int IDL_MIRROR_END = 196608;
   int IDL_COPY_DUP2 = 196608;
   int IDL_COPY_DUP2_END = 1245184;
   int IDL_COPY_MERGER = 1245184;
   int IDL_COPY_MERGER_END = 1507328;
   int IDL_COPY_SLICE = 1507328;
   int IDL_COPY_SLICE_END = 1769472;
   int IDL_COPY_DUPFULL = 65536;
   int IDL_COPY_DUPFULL_END = 1245184;
   int IDL_COPY = 65536;
   int IDL_COPY_END = 1769472;
   int IDL_SYMBOL = 8388608;
   int IDL_SYMBOL_END = 8454144;
   int IDL_VIRTUAL = 16777216;
   int IDL_VIRTUAL_END = 33554432;
   int IDL_STACKVAR = 33554432;
   int IDL_STACKVAR_BASE = 1073741824;
   int IDL_STACKVAR_END = Integer.MAX_VALUE;
   int IDL_BEGIN = 1;
   int IDL_END = Integer.MAX_VALUE;

   boolean isTypeable();

   void copyProperties(IEVar var1);

   ICElement generateC(IERoutineContext var1, ICMethod var2, int var3);

   ICElement generateC(IERoutineContext var1, ICMethod var2, int var3, boolean var4);

   @Override
   int getId();

   @Override
   String getName();

   Long getAddress();

   default boolean isPhysicalRegister() {
      return this.getId() >= 0 && this.getId() < 65536;
   }

   default boolean isVirtualRegister() {
      return this.getId() >= 65536 && this.getId() < 131072;
   }

   default boolean isRegister() {
      return this.isPhysicalRegister() || this.isVirtualRegister();
   }

   default boolean isGlobalVariable() {
      return this.getId() >= 16777216 && this.getId() < Integer.MAX_VALUE;
   }

   default boolean isGlobalReference() {
      return this.getId() >= 131072 && this.getId() < 196608;
   }

   default boolean isRoutineScope() {
      return this.getId() < 0;
   }

   default boolean isPseudoVar() {
      return this.getId() == -1;
   }

   default boolean isCopy() {
      int var1 = -this.getId();
      return var1 >= 65536 && var1 < 1769472;
   }

   default boolean isStackVariable() {
      int var1 = -this.getId();
      return var1 >= 33554432 && var1 < Integer.MAX_VALUE;
   }

   default boolean isStackReference() {
      int var1 = -this.getId();
      return var1 >= 8388608 && var1 < 8454144;
   }

   default boolean isReference() {
      return this.isStackReference() || this.isGlobalReference();
   }
}
