package com.pnfsoftware.jeb.core.units.code.asm.decompiler.opt;

import java.util.List;

public interface IMasterOptimizer {
   int DEFAULT_GROUP = 1;

   IOptimizerTarget getTarget();

   void setTarget(IOptimizerTarget var1);

   OptimizerMode setMode(OptimizerMode var1);

   OptimizerMode getMode();

   void setPolicyForOptimizerTag(String var1, boolean var2);

   int perform();

   int performMultiple(List var1);

   int performSingle(OptimizerEntry var1);

   int getTotalOptimizationCount();

   int getOptimizationCount(boolean var1);

   OptimizerEntry registerOptimizer(IOptimizer var1);

   OptimizerEntry registerOptimizer(int var1, IOptimizer var2);

   boolean unregisterOptimizer(OptimizerEntry var1);

   List getRegisteredOptimizers();

   List getRegisteredOptimizers(int var1);

   OptimizerEntry getOptimizer(Class var1);

   IOptimizer getOptimizerObject(Class var1);

   void registerInstrumenter(IMasterOptimizerInstrumenter var1);

   boolean unregisterInstrumenter(IMasterOptimizerInstrumenter var1);
}
