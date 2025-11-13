package com.pnfsoftware.jeb.core.units.code.java;

import com.pnfsoftware.jeb.core.IPlugin;

public interface IJOptimizer extends IPlugin {
   String getName();

   JOptimizerType getType();

   double getPriority();

   boolean isEnabled();

   int perform(IJavaDecompilableElement var1);
}
