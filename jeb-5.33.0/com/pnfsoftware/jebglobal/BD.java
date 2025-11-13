package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.util.format.Formatter;
import com.pnfsoftware.jeb.util.format.Strings;

public class BD extends Ae {
   private static final long serialVersionUID = 1L;
   private String pC;
   private int A;
   private String kS;

   public BD(String var1, int var2, String var3) {
      super(Strings.ff("Operation '%s' failed with error code: %Xh (%s)", Formatter.escapeString(var1), var2, var3));
      this.pC = var1;
      this.A = var2;
      this.kS = var3;
   }
}
