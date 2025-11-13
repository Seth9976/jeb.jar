package com.pnfsoftware.jeb.core.units.code.asm.items;

import com.pnfsoftware.jeb.core.units.code.asm.analyzer.IMethodStackMemoryModel;
import com.pnfsoftware.jeb.core.units.code.asm.analyzer.IStackframeManager;
import com.pnfsoftware.jeb.core.units.code.asm.cfg.CFG;
import com.pnfsoftware.jeb.util.serialization.annotations.Ser;
import java.util.List;
import java.util.Set;

@Ser
public interface INativeMethodDataItem extends INativeMemoryItem {
   INativeMethodItem getRoutine();

   List getMethodReferences();

   CFG getCFG();

   IMethodStackMemoryModel getStackframeModel();

   IStackframeManager getStackframeManager();

   INativeMethodItem getTrampolineTarget();

   Integer getSPDeltaOnReturn();

   void setSPDeltaOnReturn(Integer var1);

   boolean isPotentialSwitches();

   Set getBadAddresses();
}
