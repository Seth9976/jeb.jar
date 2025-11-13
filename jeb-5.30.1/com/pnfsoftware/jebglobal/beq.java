package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.util.format.Strings;

public class beq extends ber {
   public String q;
   public String RF;

   beq(bew var1) {
      super(var1);
      this.q = var1.Uv("name");
      this.RF = var1.Uv("url");
   }

   @Override
   void q(bev var1) {
   }

   @Override
   public String toString() {
      return Strings.ff("%s (url: %s)", this.q, this.RF);
   }
}
