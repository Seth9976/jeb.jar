package com.pnfsoftware.jeb.core.properties.impl;

import com.pnfsoftware.jeb.core.properties.IPropertyManager;

public abstract class PropertyChangeTriggerBoolean extends PropertyChangeTrigger {
   public PropertyChangeTriggerBoolean(String var1, String var2, IPropertyManager var3) {
      super(var1, var2, var3);
   }

   @Override
   protected void check() {
      if (this.changedKey.endsWith("." + this.checkedKeyName)) {
         Boolean var1 = this.pm.getBooleanUnsafe(this.checkedKeyName);
         if (var1 != null) {
            this.onChange(var1);
         }
      }
   }

   protected abstract void onChange(boolean var1);
}
