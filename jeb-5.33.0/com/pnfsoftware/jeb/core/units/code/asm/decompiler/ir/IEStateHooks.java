package com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir;

import com.pnfsoftware.jeb.core.units.IPriorityBasedHooks;

public interface IEStateHooks extends IPriorityBasedHooks {
   default Integer onReadMemory(EState var1, long var2, byte[] var4) {
      return null;
   }

   default Integer onReadMemoryPost(EState var1, long var2, byte[] var4, int var5) {
      return null;
   }

   default Boolean onWriteMemory(EState var1, long var2, byte[] var4) {
      return null;
   }
}
