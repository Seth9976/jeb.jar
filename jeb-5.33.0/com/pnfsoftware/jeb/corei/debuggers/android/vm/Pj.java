package com.pnfsoftware.jeb.corei.debuggers.android.vm;

import com.pnfsoftware.jeb.core.units.code.debug.DebuggerOperationType;

public class Pj {
   static final int[] pC = new int[DebuggerOperationType.values().length];

   static {
      try {
         pC[DebuggerOperationType.ATTACH.ordinal()] = 1;
      } catch (NoSuchFieldError var1) {
      }
   }
}
