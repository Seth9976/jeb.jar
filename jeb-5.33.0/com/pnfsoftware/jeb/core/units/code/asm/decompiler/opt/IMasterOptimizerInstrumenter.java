package com.pnfsoftware.jeb.core.units.code.asm.decompiler.opt;

public interface IMasterOptimizerInstrumenter {
   void preAllOptimizationsCallback(IOptimizerTarget var1);

   void preOptimizationCallback(IOptimizerTarget var1, OptimizerEntry var2);

   void postAllOptimizationsCallback(IOptimizerTarget var1);

   void postOptimizationCallback(IOptimizerTarget var1, OptimizerEntry var2, int var3, long var4);
}
