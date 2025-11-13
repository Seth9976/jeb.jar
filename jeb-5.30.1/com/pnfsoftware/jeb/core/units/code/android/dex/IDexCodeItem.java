package com.pnfsoftware.jeb.core.units.code.android.dex;

import com.pnfsoftware.jeb.core.units.code.android.controlflow.CFG;
import java.util.List;
import java.util.Map;

public interface IDexCodeItem {
   int getRegisterCount();

   int getInputArgumentCount();

   int getOutputArgumentCount();

   List getExceptionItems();

   IDexDebugInfo getDebugInfo();

   int getDexEntryIndex();

   default int getDexFileIndex() {
      return this.getDexEntryIndex();
   }

   int getInstructionsOffset();

   int getInstructionsSize();

   boolean hasParsingErrors();

   List getParsingErrors();

   List getInstructions();

   IDalvikInstruction getInstruction(int var1);

   IDalvikInstruction getInstructionAt(int var1);

   CFG getControlFlowGraph();

   Map getGaps();
}
