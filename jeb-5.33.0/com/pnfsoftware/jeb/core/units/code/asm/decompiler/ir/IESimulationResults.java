package com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir;

import com.pnfsoftware.jeb.core.units.code.asm.items.INativeMethodItem;

public interface IESimulationResults {
   boolean recordComment(long var1, String var3);

   boolean recordSupportRoutineCall(long var1, INativeMethodItem var3);
}
