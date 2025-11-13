package com.pnfsoftware.jeb.core.units.code.android.ir;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Set;

public interface IDSwitchData extends IDElement {
   boolean isEmptySwitch();

   boolean isRegularSwitch();

   boolean isStringSwitch();

   boolean convertToStringKeys(Map var1);

   IDTarget addCase(Object var1, IDTarget var2, boolean var3);

   int getCaseCount();

   Set getCases();

   Set getCasesAsInt();

   IDTarget getTargetForCase(Object var1);

   int getTargetCount();

   Collection getTargets(boolean var1);

   List getKeysForTargets(IDTarget var1);

   void clear();

   IDTarget deleteCase(Object var1);

   int deleteCasesToTarget(IDTarget var1);

   int deleteCasesTo(int var1);

   int updateCases(int var1, int var2);

   int updateCases(Map var1, boolean var2);

   IDSwitchData duplicate();
}
