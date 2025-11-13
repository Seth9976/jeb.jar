package com.pnfsoftware.jeb.core.exceptions;

import com.pnfsoftware.jeb.core.units.IUnit;

public class JebRuntimeException extends RuntimeException {
   private static final long serialVersionUID = 1L;
   private IUnit unit;

   protected JebRuntimeException() {
   }

   public JebRuntimeException(Throwable var1) {
      super(var1);
   }

   public JebRuntimeException(String var1) {
      super(var1);
   }

   public JebRuntimeException(String var1, Throwable var2) {
      super(var1, var2);
   }

   public JebRuntimeException withUnit(IUnit var1) {
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
