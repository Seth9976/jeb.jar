package com.pnfsoftware.jeb.core.units.code.asm.decompiler;

import com.pnfsoftware.jeb.core.units.code.asm.INativeContext;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.EState;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.FunctionOptype;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.IEBranchDetails;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.IECompose;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.IECond;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.IEGeneric;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.IEGroup;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.IEGroupElt;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.IEImm;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.IEMem;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.IEOperation;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.IERange;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.IESlice;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.IEVar;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.IWildcardPrototype;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.IWildcardTypeManager;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.OperationType;
import com.pnfsoftware.jeb.core.units.code.asm.items.INativeDataItem;
import com.pnfsoftware.jeb.core.units.code.asm.items.INativeMethodItem;
import com.pnfsoftware.jeb.core.units.code.asm.memory.IVirtualMemory;
import com.pnfsoftware.jeb.core.units.code.asm.type.IPrototypeItem;
import com.pnfsoftware.jeb.util.serialization.annotations.Ser;
import java.math.BigInteger;
import java.util.Collection;
import java.util.List;
import java.util.Set;

@Ser
public interface IEGlobalContext {
   IVirtualMemory getNativeMemory();

   IWildcardTypeManager getWildcardTypeManager();

   IWildcardPrototype getCandidatePrototype(INativeMethodItem var1);

   boolean setCandidatePrototype(INativeMethodItem var1, IWildcardPrototype var2, int var3);

   IERoutineContext createRoutineContext();

   IERoutineContext createRoutineContext(INativeMethodItem var1);

   List getRoutineContexts();

   IERoutineContext getRoutineContext(int var1);

   void addRoutineContext(IERoutineContext var1);

   boolean removeRoutineContext(IERoutineContext var1, boolean var2);

   int getRegisterBitsize();

   int getAddressBitsize();

   boolean isBigEndian();

   AbstractConverter getConverter();

   INativeContext getNativeContext();

   INativeObjectTracker getObjectTracker();

   IEBranchDetails createBranchDetails(List var1, List var2, List var3, int var4);

   IEBranchDetails createBranchDetails(List var1, List var2, List var3, int var4, IPrototypeItem var5, List var6);

   IECompose createCompose(IEGeneric... var1);

   IECompose createCompose(Collection var1);

   IECond createCond(IEGeneric var1, IEGeneric var2, IEGeneric var3);

   IEGroupElt createGroupElt(IEGroup var1, IEGeneric var2);

   IEImm createImm(long var1, int var3);

   IEImm createImm(byte[] var1, int var2);

   IEImm createImm(BigInteger var1, int var2);

   IEImm createImm(String var1, int var2);

   IEImm createImm(float var1);

   IEImm createImm(double var1);

   IEMem createMem(IEGeneric var1, int var2);

   IEMem createMem(IEGeneric var1, IEGeneric var2, int var3);

   IEOperation createOperation(OperationType var1, IEGeneric var2);

   IEOperation createOperation(OperationType var1, IEGeneric var2, IEGeneric var3);

   IEOperation createOperation(FunctionOptype var1, IEGeneric... var2);

   FunctionOptype createFunctionType(String var1, int var2, int var3, int var4);

   FunctionOptype getFunctionType(String var1);

   IEOperation createResizeOperation(IEGeneric var1, int var2, boolean var3);

   IEOperation createConversionOperation(OperationType var1, IEGeneric var2, int var3);

   IERange createRange(int var1, int var2);

   IESlice createSlice(IEGeneric var1, int var2, int var3);

   IESlice createSlice(IEGeneric var1, IERange var2);

   IEVar createRegister(int var1, String var2, int var3);

   IEVar createRegister(String var1, int var2);

   IEVar createVirtualRegister(int var1, String var2, int var3);

   IEVar createVirtualRegister(String var1, int var2);

   IEVar createGlobalReference(String var1, Long var2);

   IEVar getGlobalVariable(long var1);

   IEVar createGlobalVariable(long var1, int var3);

   Collection getGlobalVariables();

   IEVar createSymbolForRoutine(INativeMethodItem var1, IERoutineContext var2);

   INativeMethodItem retrieveRoutineFromSymbol(IEVar var1);

   IEVar createSymbolForData(INativeDataItem var1, IERoutineContext var2);

   INativeDataItem retrieveDataFromSymbol(IEVar var1);

   IEVar getVariableByName(String var1);

   IEVar getVarSafe(int var1);

   IEVar getVar(int var1);

   boolean canCreateVariable(int var1, int var2);

   Collection getVariables(int var1, int var2);

   Collection getAllVariables();

   Collection getAllRegisters();

   Collection getAllRegisters(Set var1);

   EState buildState(boolean var1, boolean var2, boolean var3);

   default EState buildState() {
      return this.buildState(true, true, true);
   }

   default EState buildEmptyState() {
      return this.buildState(false, false, false);
   }

   void setData(Object var1, Object var2);

   Object getData(Object var1);
}
