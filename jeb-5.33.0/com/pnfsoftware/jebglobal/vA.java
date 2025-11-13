package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.units.codeobject.ProcessorType;
import com.pnfsoftware.jeb.util.logging.GlobalLog;
import com.pnfsoftware.jeb.util.logging.ILogger;

public class vA {
   private static final ILogger pC = GlobalLog.getLogger(vA.class);

   public static Ti pC(ProcessorType var0) {
      try {
         hq var1 = new hq(new To(var0));
         return var1.kS();
      } catch (Exception var2) {
         pC.catching(var2);
         return null;
      }
   }
}
