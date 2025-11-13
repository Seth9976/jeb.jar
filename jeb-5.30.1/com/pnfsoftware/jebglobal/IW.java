package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.util.format.Formatter;
import com.pnfsoftware.jeb.util.format.Strings;

public class IW extends WI {
   private static final long serialVersionUID = 1L;
   private String q;
   private int RF;
   private String xK;

   public IW(String var1, int var2, String var3) {
      super(Strings.ff("Operation '%s' failed with error code: %Xh (%s)", Formatter.escapeString(var1), var2, var3));
      this.q = var1;
      this.RF = var2;
      this.xK = var3;
   }

   public String q() {
      return this.q;
   }

   public int RF() {
      return this.RF;
   }

   public String xK() {
      return this.xK;
   }
}
