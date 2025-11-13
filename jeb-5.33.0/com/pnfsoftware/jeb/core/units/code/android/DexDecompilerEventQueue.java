package com.pnfsoftware.jeb.core.units.code.android;

import com.pnfsoftware.jeb.util.collect.AsyncEventQueue;
import com.pnfsoftware.jeb.util.serialization.annotations.Ser;

@Ser
public class DexDecompilerEventQueue extends AsyncEventQueue {
   public DexDecompilerEventQueue(int var1) {
      super(var1);
   }
}
