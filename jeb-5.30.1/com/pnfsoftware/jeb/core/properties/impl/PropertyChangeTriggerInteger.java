package com.pnfsoftware.jeb.core.properties.impl;

import com.pnfsoftware.jeb.core.properties.IPropertyManager;

public abstract class PropertyChangeTriggerInteger extends PropertyChangeTrigger {
   public PropertyChangeTriggerInteger(String var1, String var2, IPropertyManager var3) {
      super(var1, var2, var3);
   }

   @Override
   protected void check() {
      if (this.changedKey.endsWith("." + this.checkedKeyName)) {
         Integer var1 = this.pm.getIntegerUnsafe(this.checkedKeyName);
         if (var1 != null) {
            this.onChange(var1);
         }
      }
   }

   protected abstract void onChange(int var1);
}
