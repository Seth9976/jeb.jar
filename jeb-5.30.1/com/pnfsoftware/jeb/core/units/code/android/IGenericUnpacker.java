package com.pnfsoftware.jeb.core.units.code.android;

import com.pnfsoftware.jeb.core.units.code.android.ir.DexDecEvaluationException;
import java.util.Collection;

public interface IGenericUnpacker {
   boolean shouldAttemptUnpack();

   IEmulatedAndroid getEmulatedAndroid();

   void setMaxExecutionTime(long var1);

   void setMonitorEvents(boolean var1);

   void setIntegrateRecoveredDexFiles(boolean var1);

   void setUseRecoveredSoFiles(boolean var1);

   Collection getRecoveredDexHashes();

   Collection getRecoveredSoHashes();

   void attemptUnpack() throws DexDecEvaluationException;

   String formatMonitorReport(int var1);

   void teardown();
}
