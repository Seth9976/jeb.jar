package com.pnfsoftware.jeb.core.units.code.asm.decompiler;

import com.pnfsoftware.jeb.core.units.code.IDFA;
import com.pnfsoftware.jeb.core.units.code.asm.INativeContext;
import com.pnfsoftware.jeb.core.units.code.asm.cfg.CFG;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.EState;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.EVarCopyFinder;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.FunctionOptype;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.IEAssign;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.IECall;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.IECompose;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.IECond;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.IEGeneric;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.IEGroup;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.IEGroupElt;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.IEImm;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.IEJump;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.IEJumpFar;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.IEMem;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.IENop;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.IEOperation;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.IEReturn;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.IEStackManager;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.IEStatement;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.IESwitch;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.IEUntranslatedInstruction;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.IEVar;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.IWildcardPrototype;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.IWildcardType;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.IWildcardTypeManager;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.OperationType;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.VarSrc;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.opt.IOptimizerTarget;
import com.pnfsoftware.jeb.core.units.code.asm.items.INativeDataItem;
import com.pnfsoftware.jeb.core.units.code.asm.items.INativeFieldItem;
import com.pnfsoftware.jeb.core.units.code.asm.items.INativeItem;
import com.pnfsoftware.jeb.core.units.code.asm.items.INativeMethodItem;
import com.pnfsoftware.jeb.core.units.code.asm.type.StorageEntry;
import com.pnfsoftware.jeb.util.base.Couple;
import com.pnfsoftware.jeb.util.serialization.annotations.Ser;
import java.math.BigInteger;
import java.util.Collection;
import java.util.List;
import java.util.Set;

@Ser
public interface IERoutineContext extends IOptimizerTarget {
   boolean isAllowUnsafeAnalysis();

   INativeMethodItem getRoutine();

   void setRoutine(INativeMethodItem var1);

   IWildcardPrototype getPrototype();

   void setPrototype(IWildcardPrototype var1);

   IEGlobalContext getGlobalContext();

   IEConverter getConverter();

   INativeDecompilerContext getDecompiler();

   INativeContext getNativeContext();

   IWildcardTypeManager getWildcardTypeManager();

   IEStackManager getStackManager();

   IEVar getProgramCounter();

   int getProgramCounterId();

   IEVar getStackPointer();

   int getStackPointerId();

   void setStatements(List var1);

   void setStatements(List var1, boolean var2, boolean var3, boolean var4);

   CFG buildCfg(List var1);

   CFG buildCfg(List var1, boolean var2, boolean var3);

   void invalidateDataFlowAnalysis();

   Long convertNativeAddress(long var1);

   Long convertIntermediateOffset(int var1);

   List getIntermediateOffsetsMappingToNativeAddress(long var1);

   List getIntermediateOffsetsMappingToNativeAddresses(Collection var1);

   List getStatements();

   CFG getCfg();

   void setCfg(CFG var1);

   EState buildEmptyState();

   IEAssign createAssign(IEGeneric var1, IEGeneric var2);

   IEAssign createBranchAssign(IEGeneric var1, IEGeneric var2, boolean var3);

   IEAssign createAssignIf(IEAssign var1, IEGeneric var2);

   IESwitch createSwitch(IEGeneric var1, int var2);

   IEJump createJump(int var1);

   IEJump createJump(int var1, IEGeneric var2);

   IEJumpFar createJumpFar(IEGeneric var1);

   IEJumpFar createJumpFar(IEGeneric var1, IEGeneric var2);

   IENop createNop();

   IENop createNop(IEStatement var1);

   IEReturn createReturn();

   IEReturn createReturn(IEGeneric var1);

   IEReturn createReturn(List var1);

   IECall createCall(IEGeneric var1, IEGeneric var2, List var3, List var4, int var5, List var6, IWildcardPrototype var7);

   IECall createCall(IEGeneric var1, List var2, IWildcardPrototype var3, List var4, boolean var5);

   IEUntranslatedInstruction createUntranslatedInstruction(long var1, String var3, IEGeneric... var4);

   IEUntranslatedInstruction createUntranslatedInstruction(long var1, String var3, List var4);

   IEMem createMem(IEGeneric var1, int var2);

   IEMem createMem(IEGeneric var1, IEGeneric var2, int var3);

   IEOperation createOperation(OperationType var1, IEGeneric var2);

   IEOperation createOperation(OperationType var1, IEGeneric var2, IEGeneric var3);

   IEOperation createOperation(FunctionOptype var1, IEGeneric... var2);

   IEOperation createResizeOperation(IEGeneric var1, int var2, boolean var3);

   IEOperation createConversionOperation(OperationType var1, IEGeneric var2, int var3);

   IEImm createImm(long var1, int var3);

   IEImm createImm(byte[] var1, int var2);

   IEImm createImm(BigInteger var1, int var2);

   IEImm createImm(String var1, int var2);

   IEImm createImm(float var1);

   IEImm createImm(double var1);

   IECompose createCompose(IEGeneric... var1);

   IECompose createCompose(Collection var1);

   IECond createCond(IEGeneric var1, IEGeneric var2, IEGeneric var3);

   IEGroupElt createGroupElt(IEGroup var1, IEGeneric var2);

   IEVar createVar(String var1, int var2);

   IEVar createVirtualVar(String var1, int var2);

   IEVar getStackReference(long var1);

   IEVar removeStackReference(long var1);

   IEVar createStackReference(long var1, IWildcardType var3);

   IEVar createStackReference(long var1);

   IEVar getVariableByName(String var1);

   IEVar getVariableById(int var1);

   Collection getRoutineVariablesInRange(int var1, int var2);

   Collection getStackVariables();

   IEVar getStackVariable(int var1);

   Collection getStackVariables(int var1, int var2);

   Collection getMemoryVariables();

   IEVar createSymbolForRoutine(INativeMethodItem var1);

   IEVar createSymbolForField(INativeFieldItem var1);

   IEVar createSymbolForGlobalVariable(INativeDataItem var1);

   boolean usesCopyVars();

   List getDuplicatesForRegister(int var1);

   Integer getUnderlyingRegisterId(int var1);

   IEVar getRegisterMirror(IEVar var1);

   IEVar copyVariable(IEVar var1);

   Couple copyTruncatedVariable(IEVar var1, int var2);

   IEVar copyPairOfVariables(IEVar var1, IEVar var2);

   EVarCopyFinder copyFinder(StorageEntry var1, Collection var2, Long var3);

   VarSrc getSourceForVariable(int var1);

   Set getCopiesOfVariable(int var1);

   Set getCopiesUsingVariable(int var1);

   IEGeneric retrieveVariableForRegister(IEGeneric var1, Collection var2, boolean var3);

   IEGeneric getInputVariableForRegister(IDFA var1, long var2);

   IEGeneric getInputVariableForRegister(IDFA var1, IEVar var2);

   IEGeneric getOutputVariableForRegister(IDFA var1, long var2, int var4);

   IEGeneric getOutputVariableForRegister(IDFA var1, long var2, IEVar var4);

   List getRoutineInputVariables();

   void setTypeForSame(IEGeneric var1, IWildcardType var2);

   void acquireNativeItem(INativeItem var1);

   void log(String var1, Object... var2);

   void logUnsafeOpt(String var1, Object... var2);

   void setData(Object var1, Object var2);

   Object getData(Object var1);

   boolean addNote(String var1);

   boolean removeNote(String var1);

   List getNotes();

   IECall createBuiltinMethodCall(String var1, IEGeneric var2, IEGeneric... var3);
}
