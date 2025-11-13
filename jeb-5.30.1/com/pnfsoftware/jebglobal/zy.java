package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.exceptions.JebException;
import com.pnfsoftware.jeb.util.format.Strings;

public class zy extends JebException {
   private static final long serialVersionUID = 1L;

   public zy(String var1, Object... var2) {
      super(Strings.ff(var1, var2));
   }

   public zy(Fx var1, String var2, Object... var3) {
      super(Strings.ff(var2, var3), var1);
   }
}
