package com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.opt;

import com.pnfsoftware.jeb.core.units.code.asm.decompiler.IERoutineContext;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.IEImm;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.opt.IMasterOptimizer;
import java.util.Collection;
import java.util.Set;

public interface IEMasterOptimizer extends IMasterOptimizer {
   void addDisregardedOutputFilter(IEOptFilterCanDiscard var1);

   void addDisregardedOutputBits(Collection var1);

   void addDisregardedOutputVariables(Collection var1);

   Set getDisregardedOutputRegisters();

   void addDefaultInput(int var1, IEImm var2);

   IEImm getDefaultInput(int var1);

   boolean canDiscardReachingOutDefinition(IERoutineContext var1, long var2, int var4);

   boolean canDiscardUnusedDefinition(IERoutineContext var1, long var2, int var4);
}
