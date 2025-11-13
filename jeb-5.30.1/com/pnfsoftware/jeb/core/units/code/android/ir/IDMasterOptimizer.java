package com.pnfsoftware.jeb.core.units.code.android.ir;

import java.util.List;

public interface IDMasterOptimizer {
   IDMethodContext getTarget();

   boolean add(IDOptimizer var1);

   boolean remove(IDOptimizer var1);

   List getListOfOptimizers();

   void setOptimizerEnabled(IDOptimizer var1, boolean var2);

   IDOptimizer findOptimizer(Class var1);

   void setSafeMode(boolean var1);

   boolean isSafeMode();

   void setPolicyForOptimizerTag(String var1, boolean var2);

   int perform();

   void registerInstrumenter(IDMasterOptimizerInstrumenter var1);

   void unregisterInstrumenter(IDMasterOptimizerInstrumenter var1);

   List getInstrumenters();
}
