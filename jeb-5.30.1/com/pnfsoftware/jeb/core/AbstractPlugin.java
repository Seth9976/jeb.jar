package com.pnfsoftware.jeb.core;

import java.util.HashMap;
import java.util.Map;

public abstract class AbstractPlugin implements IPlugin {
   private Map datamap;

   protected AbstractPlugin() {
   }

   @Override
   public void setData(Object var1, Object var2) {
      if (this.datamap == null) {
         this.datamap = new HashMap();
      }

      this.datamap.put(var1, var2);
   }

   @Override
   public Object getData(Object var1) {
      return this.datamap == null ? null : this.datamap.get(var1);
   }

   @Override
   public void dispose() {
   }
}
