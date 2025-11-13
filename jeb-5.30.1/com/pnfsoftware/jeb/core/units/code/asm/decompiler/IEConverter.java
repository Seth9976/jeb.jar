package com.pnfsoftware.jeb.core.units.code.asm.decompiler;

import com.pnfsoftware.jeb.core.units.code.IDFA;
import com.pnfsoftware.jeb.core.units.code.asm.INativeContext;
import com.pnfsoftware.jeb.core.units.code.asm.cfg.BasicBlock;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ast.ICMethod;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ast.ICStatement;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.exceptions.DecompilerException;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.ELocation;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.EState;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.IEBranchDetails;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.IEGeneric;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.IEImm;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.IEMem;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.IEPrototypeHandler;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.IEStatement;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.IEUntranslatedInstruction;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.IEVar;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.IWildcardPrototype;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.IWildcardType;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.SimulationPointInformation;
import com.pnfsoftware.jeb.core.units.code.asm.items.INativeMethodItem;
import com.pnfsoftware.jeb.util.serialization.annotations.Ser;
import java.util.List;

@Ser
public interface IEConverter {
   void initialize();

   int getAddressBitsize();

   int getRegisterBitsize();

   int getStackSlotSize();

   IWildcardType.Group getWildcardTypeManagerDefaultResolutionGroup();

   IEGeneric getRegister(String var1, ELocation var2);

   IEGeneric getRegister(String var1);

   IEGeneric getRegisterVariableFromNativeRegisterId(long var1, ELocation var3) throws DecompilerException;

   IEGeneric getRegisterVariableFromNativeRegisterId(long var1) throws DecompilerException;

   long getNativeRegisterIdFromRegisterVariable(IEVar var1, boolean var2) throws DecompilerException;

   long getNativeRegisterIdFromRegisterVariable(IEVar var1) throws DecompilerException;

   String getSlicedRegisterName(String var1, int var2, int var3);

   IEMem createStackMemoryAccess(IEGeneric var1, int var2);

   long sanitizeNativeAddress(long var1);

   Boolean isSegmentEMemReferencingPrimaryMemory(IEMem var1);

   INativeContext getNativeContext();

   INativeDecompilerContext getDecompiler();

   IEGlobalContext getGlobalContext();

   IEVar getProgramCounter();

   IEVar getStackPointer();

   IEVar getReturnAddressRegister();

   IEVar getGPRegister(int var1);

   IEVar getFPRegister(int var1);

   IEVar getTempRegister(int var1);

   IERoutineContext convert(INativeMethodItem var1);

   IEPrototypeHandler getPrototypeHandler(IERoutineContext var1);

   boolean canCreateCalls();

   IEGeneric convertReturnLocation(IERoutineContext var1, IWildcardPrototype var2);

   List convertReturnExpressions(IERoutineContext var1, IWildcardPrototype var2, INativeMethodItem var3, List var4, List var5);

   List convertParameterExpressions(IERoutineContext var1, IWildcardPrototype var2, INativeMethodItem var3, List var4);

   IEVar getInputVariableByIndex(IERoutineContext var1, int var2);

   IEVar getOutputVariableByIndex(IERoutineContext var1, int var2);

   Integer determineStackPointerDeltaAfterIRCall(IWildcardPrototype var1, List var2);

   Integer determineStackBytesUsedByCall(IWildcardPrototype var1, List var2);

   Integer determineStackPointerDeltaFromSimulation(SimulationPointInformation var1);

   IEBranchDetails getDefaultBranchToRoutineSideEffects(INativeMethodItem var1);

   IWildcardPrototype buildFailsafePrototype(IERoutineContext var1, IEStatement var2);

   int insertReturns(IERoutineContext var1);

   IEGeneric normalizeBranchingExpression(IDFA var1, BasicBlock var2, IEGeneric var3, IEGeneric var4);

   int defaultPCConversion(IERoutineContext var1);

   boolean resolveCustomCalls(IERoutineContext var1);

   ICStatement generateASTForUntranslatedIR(IEUntranslatedInstruction var1, IERoutineContext var2, ICMethod var3);

   IEImm evaluateUntranslatedIR(IEUntranslatedInstruction var1, IERoutineContext var2, EState var3);

   void initializeStateRegisters(EState var1, Long var2);

   void customInitStateRegisters(EState var1, Long var2);

   int getStateProcessorMode(EState var1);

   String formatStatistics();
}
