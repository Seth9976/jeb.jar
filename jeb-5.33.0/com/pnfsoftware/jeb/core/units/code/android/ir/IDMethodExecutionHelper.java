package com.pnfsoftware.jeb.core.units.code.android.ir;

import java.util.List;

public interface IDMethodExecutionHelper {
   String getMethodSignature();

   IDImm simulateExecution(IDState var1, List var2) throws DexDecEvaluationException;
}
