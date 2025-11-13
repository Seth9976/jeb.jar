package com.pnfsoftware.jeb.util.base;

import com.pnfsoftware.jeb.client.S;
import com.pnfsoftware.jeb.util.logging.GlobalLog;
import com.pnfsoftware.jeb.util.logging.ILogger;

public class ExecutionTargetWithProgressCallback {
   private static final ILogger logger = GlobalLog.getLogger(ExecutionTargetWithProgressCallback.class);
   protected IProgressCallback callback = new ExecutionTargetWithProgressCallback$1(this);
   private IProgressCallback proxy;

   public void setCallback(IProgressCallback var1) {
      if (this.proxy != null) {
         logger.warning(S.L("Overriding callback method (%s -> %s)"), this.proxy, var1);
      }

      this.proxy = var1;
   }
}
