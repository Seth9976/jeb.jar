package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.util.format.Formatter;

public class RD extends Ae {
   private static final long serialVersionUID = 1L;

   public RD(String var1) {
      super("Operation not supported by the server: " + Formatter.escapeString(var1));
   }
}
