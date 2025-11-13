package com.pnfsoftware.jeb.core.units.code.android.dex;

import java.util.List;

public interface IDexDebugLine {
   int getLineNumber();

   List getVariables();

   List getVariablesEnd();

   List getVariablesRestart();

   boolean isPrologueEnd();

   boolean isEpilogueBegin();

   int getSourceIndex();
}
