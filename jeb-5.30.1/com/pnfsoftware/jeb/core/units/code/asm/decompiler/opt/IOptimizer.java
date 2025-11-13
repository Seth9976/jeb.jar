package com.pnfsoftware.jeb.core.units.code.asm.decompiler.opt;

import com.pnfsoftware.jeb.core.IPlugin;
import java.util.Set;

public interface IOptimizer extends IPlugin {
   String DEOBFUSCATOR = "deobfuscator";
   String DEAD_CODE_REMOVER = "dcr";
   double PRIORITY_LOW = -10.0;
   double PRIORITY_STANDARD = 0.0;
   double PRIORITY_HIGH = 10.0;

   OptimizerMode getRequiredModeThreshold();

   OptimizerType getType();

   Set getTags();

   int getPreferredExecutionStage();

   double getPriority();

   IMasterOptimizer getMasterOptimizer();

   void setMasterOptimizer(IMasterOptimizer var1);

   int performOnTarget(IOptimizerTarget var1);
}
