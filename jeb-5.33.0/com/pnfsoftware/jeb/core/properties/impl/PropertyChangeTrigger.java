package com.pnfsoftware.jeb.core.properties.impl;

import com.pnfsoftware.jeb.core.properties.IPropertyManager;

abstract class PropertyChangeTrigger {
   protected String changedKey;
   protected String checkedKeyName;
   protected IPropertyManager pm;

   public PropertyChangeTrigger(String var1, String var2, IPropertyManager var3) {
      this.changedKey = var1;
      this.checkedKeyName = var2;
      this.pm = var3;
      this.check();
   }

   protected abstract void check();
}
