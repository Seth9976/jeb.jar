package com.pnfsoftware.jeb.core.units.code.debug.impl;

import java.util.List;

public abstract class ValueArray extends AbstractValueComposite {
   private String typeName;

   public ValueArray(long var1, long var3, String var5) {
      super(var1, var3);
      this.typeName = var5;
   }

   @Override
   public String getTypeName() {
      return this.typeName;
   }

   public abstract int getLength();

   public abstract List getElements(int var1, int var2);
}
