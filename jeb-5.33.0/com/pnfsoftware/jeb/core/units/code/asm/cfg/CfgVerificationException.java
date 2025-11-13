package com.pnfsoftware.jeb.core.units.code.asm.cfg;

import com.pnfsoftware.jeb.core.exceptions.JebRuntimeException;
import com.pnfsoftware.jeb.util.format.Strings;

public class CfgVerificationException extends JebRuntimeException {
   private static final long serialVersionUID = 1L;

   public CfgVerificationException(String var1, Object... var2) {
      super(Strings.ff(var1, var2));
   }
}
