package com.pnfsoftware.jeb.core.units.code.android.ir;

public class AbstractDInstrumenter implements IDMasterOptimizerInstrumenter {
   @Override
   public int beforePass(IDOptimizer var1, IDMethodContext var2) {
      return 0;
   }

   @Override
   public int afterPass(IDOptimizer var1, IDMethodContext var2, int var3) {
      return 0;
   }

   @Override
   public int afterFailedPass(IDOptimizer var1, IDMethodContext var2, Exception var3) {
      return 0;
   }
}
