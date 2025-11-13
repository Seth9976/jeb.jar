package com.pnfsoftware.jeb.core.units.code.debug.impl;

import com.pnfsoftware.jeb.core.exceptions.JebException;
import com.pnfsoftware.jeb.core.units.code.debug.ITypedValue;
import java.util.List;

public abstract class ValueObject extends AbstractValueComposite {
   private String typeName;

   public ValueObject(long var1, long var3, String var5) {
      super(var1, var3);
      this.typeName = var5;
   }

   @Override
   public String getTypeName() {
      return this.typeName;
   }

   public abstract ITypedValue invoke(String var1, long var2, List var4) throws JebException;
}
