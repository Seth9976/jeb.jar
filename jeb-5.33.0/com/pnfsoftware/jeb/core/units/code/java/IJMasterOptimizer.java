package com.pnfsoftware.jeb.core.units.code.java;

public interface IJMasterOptimizer {
   IJavaDecompilableElement getTarget();

   boolean add(IJOptimizer var1);

   boolean remove(IJOptimizer var1);

   void setSafeMode(boolean var1);

   boolean isSafeMode();

   int perform();
}
