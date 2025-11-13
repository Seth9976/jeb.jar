package com.pnfsoftware.jeb.core.units.code.android.ir;

import com.pnfsoftware.jeb.core.IPlugin;
import java.util.List;
import java.util.Map;
import java.util.Set;

public interface IDOptimizer extends IPlugin {
   String DEOBFUSCATOR = "deobfuscator";
   String INLINER = "inliner";
   String REORDERER = "reorderer";
   String SLOW = "slow";

   String getName();

   DOptimizerType getType();

   Set getTags();

   double getPriority();

   boolean isEnabled();

   boolean isCollectionOptimizer();

   int perform(IDMethodContext var1);

   int performOnCollection(List var1, Map var2);
}
