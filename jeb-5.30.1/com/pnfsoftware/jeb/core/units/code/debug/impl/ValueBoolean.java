package com.pnfsoftware.jeb.core.units.code.debug.impl;

import com.pnfsoftware.jeb.util.format.Strings;

public class ValueBoolean extends AbstractValuePrimitive {
   private boolean v;

   public ValueBoolean(boolean var1) {
      this.v = var1;
   }

   @Override
   public String getTypeName() {
      return "boolean";
   }

   public Boolean getValue() {
      return this.v;
   }

   @Override
   public String toString() {
      return Strings.ff("%b", this.getValue());
   }
}
