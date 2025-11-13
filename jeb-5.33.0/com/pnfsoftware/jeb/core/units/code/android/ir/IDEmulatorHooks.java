package com.pnfsoftware.jeb.core.units.code.android.ir;

import com.pnfsoftware.jeb.core.units.IPriorityBasedHooks;
import com.pnfsoftware.jeb.util.base.Wrapper;
import java.util.List;

public interface IDEmulatorHooks extends IPriorityBasedHooks {
   default Wrapper getField(long var1, String var3, IDImm var4) {
      return null;
   }

   default Wrapper examineFieldValue(long var1, Wrapper var3) {
      return null;
   }

   default Boolean setField(long var1, String var3, IDImm var4, IDImm[] var5) {
      return null;
   }

   default Wrapper invokeMethod(long var1, String var3, List var4) {
      return null;
   }

   default Wrapper examineMethodResult(long var1, IDImm var3) {
      return null;
   }
}
