package com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir;

import com.pnfsoftware.jeb.core.units.code.asm.decompiler.IERoutineContext;
import java.util.Collection;

public interface IEStackManager {
   IERoutineContext getRoutineContext();

   int getNormalSlotBitsize();

   int getNormalSlotSize();

   Long getLowestVariableOffset();

   Long getHighestVariableOffset();

   Long getHighestVariableOffsetEnd();

   boolean canCreateVariable(long var1, int var3);

   boolean hasVariables();

   Collection getVariables();

   IEVar getVariable(long var1);

   IEVar getVariableAtSlot(int var1);

   IEVar removeVariable(IEVar var1);

   IEVar removeVariable(long var1);

   boolean canClearNativeStack(long var1, long var3);

   void clearNativeStack(long var1, long var3);

   IEVar createStackItem(long var1, int var3);

   IEVar createPureStackItem(long var1, int var3);

   IWildcardType getActualStackItemType(long var1);
}
