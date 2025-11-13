package com.pnfsoftware.jeb.core.units.code.android.ir;

import java.util.Map;

public interface IDEmuFrame {
   IDEmuFrame copy();

   String getMethodSignature();

   int getIterationCount();

   void setPC(int var1);

   int getPC();

   Integer getNextPC();

   int updatePC();

   void setVariable(int var1, IDImm var2) throws DexDecEvaluationException;

   IDImm getVariable(int var1, boolean var2) throws DexDecEvaluationException;

   IDImm getVariable(int var1) throws DexDecEvaluationException;

   boolean hasVariable(int var1) throws DexDecEvaluationException;

   boolean deleteVariable(int var1) throws DexDecEvaluationException;

   void deleteVariables();

   Map getVarMap();

   void setVarMap(Map var1);

   void setExecutionComplete(boolean var1);

   boolean isExecutionComplete();

   void setRaisedException(IDImm var1);

   IDImm getRaisedException();
}
