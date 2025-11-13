package com.pnfsoftware.jeb.core.exceptions;

import com.pnfsoftware.jeb.core.units.IUnit;

public class JebException extends Exception {
   private static final long serialVersionUID = 1L;
   private IUnit unit;

   public JebException() {
   }

   public JebException(String var1) {
      super(var1);
   }

   public JebException(Throwable var1) {
      super(var1);
   }

   public JebException(String var1, Throwable var2) {
      super(var1, var2);
   }

   public JebException withUnit(IUnit var1) {
      this.unit = var1;
      return this;
   }

   public void setUnit(IUnit var1) {
      this.unit = var1;
   }

   public IUnit getUnit() {
      return this.unit;
   }
}
