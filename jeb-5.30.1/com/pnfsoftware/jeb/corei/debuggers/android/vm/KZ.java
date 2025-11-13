package com.pnfsoftware.jeb.corei.debuggers.android.vm;

import com.pnfsoftware.jeb.core.units.code.debug.DebuggerOperationType;

public class KZ {
   static final int[] q = new int[DebuggerOperationType.values().length];

   static {
      try {
         q[DebuggerOperationType.ATTACH.ordinal()] = 1;
      } catch (NoSuchFieldError var1) {
      }
   }
}
