package com.pnfsoftware.jeb.core.units.code.android.adb;

import com.pnfsoftware.jeb.core.exceptions.JebException;

public class AdbException extends JebException {
   private static final long serialVersionUID = 1L;

   public AdbException() {
   }

   public AdbException(String var1) {
      super(var1);
   }

   public AdbException(Throwable var1) {
      super(var1);
   }

   public AdbException(String var1, Throwable var2) {
      super(var1, var2);
   }
}
