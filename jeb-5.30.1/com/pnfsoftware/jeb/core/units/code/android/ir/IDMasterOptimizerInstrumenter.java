package com.pnfsoftware.jeb.core.units.code.android.ir;

public interface IDMasterOptimizerInstrumenter {
   int beforePass(IDOptimizer var1, IDMethodContext var2);

   int afterPass(IDOptimizer var1, IDMethodContext var2, int var3);

   int afterFailedPass(IDOptimizer var1, IDMethodContext var2, Exception var3);
}
