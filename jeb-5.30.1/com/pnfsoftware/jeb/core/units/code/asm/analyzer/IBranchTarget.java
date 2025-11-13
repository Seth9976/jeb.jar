package com.pnfsoftware.jeb.core.units.code.asm.analyzer;

import com.pnfsoftware.jeb.core.units.code.ICodePointer;
import com.pnfsoftware.jeb.core.units.code.asm.items.INativeMethodItem;
import com.pnfsoftware.jeb.util.serialization.annotations.Ser;

@Ser
public interface IBranchTarget {
   boolean isInternal();

   ICodePointer getInternalAddress();

   INativeMethodItem getRoutine();
}
