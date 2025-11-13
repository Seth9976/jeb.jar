package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.units.codeobject.ProcessorType;
import com.pnfsoftware.jeb.util.logging.GlobalLog;
import com.pnfsoftware.jeb.util.logging.ILogger;

public class Da {
   private static final ILogger q = GlobalLog.getLogger(Da.class);

   public static pF q(ProcessorType var0) {
      try {
         YF var1 = new YF(new BI(var0));
         return var1.Dw();
      } catch (Exception var2) {
         q.catching(var2);
         return null;
      }
   }
}
