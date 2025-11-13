package com.pnfsoftware.jeb.core.units.code.asm.decompiler;

import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.IEVar;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.IWildcardPrototype;
import com.pnfsoftware.jeb.core.units.code.asm.items.INativeItem;
import com.pnfsoftware.jeb.core.units.code.asm.items.INativeMethodItem;
import com.pnfsoftware.jeb.util.serialization.annotations.Ser;

@Ser
public interface INativeObjectTracker {
   int getCountOfObjects();

   void acquire(INativeItem var1, INativeMethodItem var2);

   void release(INativeItem var1, INativeMethodItem var2);

   void releaseAllFromUser(INativeMethodItem var1);

   INativeItem getNativeItemFromVar(IEVar var1);

   IEVar getSymbolForNativeItem(INativeItem var1, INativeMethodItem var2, IERoutineContext var3);

   boolean setCandidatePrototype(INativeMethodItem var1, IWildcardPrototype var2, int var3);

   IWildcardPrototype getCandidatePrototype(INativeMethodItem var1);
}
