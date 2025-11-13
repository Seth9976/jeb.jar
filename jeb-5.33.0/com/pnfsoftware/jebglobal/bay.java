package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.util.logging.GlobalLog;
import com.pnfsoftware.jeb.util.logging.ILogger;

public abstract class bay {
   protected static final ILogger E = GlobalLog.getLogger(bay.class);
   protected bbc sY;

   protected bay(bbc var1) {
      this.sY = var1;
   }

   abstract void pC(bbb var1);
}
