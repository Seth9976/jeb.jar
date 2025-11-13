package com.pnfsoftware.jeb.core.units.code.android.ir;

import com.pnfsoftware.jeb.core.units.IPriorityBasedHooks;
import com.pnfsoftware.jeb.util.base.Wrapper;
import java.util.List;

public interface IDSandboxHooks extends IPriorityBasedHooks {
   default Class loadClass(String var1) throws DexDecEvalSandboxExecutionException {
      return null;
   }

   default Boolean setField(long var1, String var3, String var4, Object var5, Object[] var6) throws DexDecEvalSandboxExecutionException {
      return null;
   }

   default Wrapper getField(long var1, String var3, String var4, Object var5) throws DexDecEvalSandboxExecutionException {
      return null;
   }

   default Wrapper examineFieldValue(long var1, Object var3) {
      return null;
   }

   default Wrapper invokeMethod(long var1, String var3, String var4, Object var5, List var6) throws DexDecEvalSandboxExecutionException {
      return null;
   }

   default Wrapper examineMethodResult(long var1, Object var3) {
      return null;
   }

   default Wrapper newInstance(long var1, String var3, String var4, List var5) throws DexDecEvalSandboxExecutionException {
      return null;
   }

   default void examineCreatedInstance(long var1, Object var3) {
   }
}
