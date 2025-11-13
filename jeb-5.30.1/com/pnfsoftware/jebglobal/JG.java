package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.util.format.Formatter;

public class JG extends WI {
   private static final long serialVersionUID = 1L;
   private String q;

   public JG(String var1) {
      super("Operation not supported by the server: " + Formatter.escapeString(var1));
   }

   public String q() {
      return this.q;
   }
}
