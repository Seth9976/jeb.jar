package com.pnfsoftware.jeb.core.units.code.android.ir;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Set;

public interface IDTryData {
   IDTryData copy();

   boolean isEmpty();

   boolean hasProtectedBlocks();

   List getProtectedBlocks();

   boolean isProtectedBlock(int var1);

   List getHandlers();

   List getHandlers(int var1);

   Set getHandledExceptionTypesAt(int var1);

   List getBlockHandlers(int var1);

   void setBlockHandlers(int var1, Collection var2);

   boolean compareHandlers(int var1, int var2);

   boolean protectBlock(int var1, int var2, int var3, int var4);

   boolean replaceHandler(int var1, IDExceptionHandler var2, IDExceptionHandler var3);

   boolean moveProtectedBlock(int var1, int var2);

   boolean copyProtectedBlock(int var1, int var2);

   boolean addProtectionFromBlock(int var1, int var2);

   boolean unprotectBlock(int var1);

   boolean unprotectBlock(int var1, int var2);

   boolean unprotectBlock(int var1, int var2, int var3);

   int updateTargets(Map var1);

   int updateTargets(Map var1, boolean var2);

   boolean updateHandlerAddress(int var1, int var2);
}
