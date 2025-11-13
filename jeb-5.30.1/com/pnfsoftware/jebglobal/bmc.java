package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.util.serialization.annotations.Ser;
import com.pnfsoftware.jeb.util.serialization.annotations.SerId;

@Ser
public class bmc {
   @SerId(1)
   private String q;
   @SerId(2)
   private Object RF;

   public bmc(String var1, Object var2) {
      this.q = var1;
      this.RF = var2;
   }

   public String q() {
      return this.q;
   }

   public Object RF() {
      return this.RF;
   }
}
