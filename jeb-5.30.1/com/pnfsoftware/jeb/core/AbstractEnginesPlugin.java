package com.pnfsoftware.jeb.core;

import com.pnfsoftware.jeb.util.logging.GlobalLog;
import com.pnfsoftware.jeb.util.logging.ILogger;
import java.util.List;

public abstract class AbstractEnginesPlugin extends AbstractPlugin implements IEnginesPlugin {
   public static final ILogger logger = GlobalLog.getLogger(AbstractEnginesPlugin.class);

   @Override
   public List getExecutionOptionDefinitions() {
      return null;
   }

   @Override
   public void load(IEnginesContext var1) {
   }

   @Override
   public void execute(IEnginesContext var1) {
      this.execute(var1, null);
   }
}
