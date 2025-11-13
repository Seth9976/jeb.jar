package com.pnfsoftware.jeb.core.units.code.asm.analyzer;

import java.util.Collection;

public interface INativeCodeAdvancedAnalyzer {
   void perform();

   void analyzeRoutines(Collection var1);

   void analyzeInternalRoutines(Collection var1);
}
