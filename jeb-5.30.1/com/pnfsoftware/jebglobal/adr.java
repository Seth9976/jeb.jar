package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.units.code.asm.analyzer.CallGraphVertex;
import com.pnfsoftware.jeb.util.serialization.annotations.Ser;

@Ser
public class adr extends ads {
   public static final String q = "attrIsSafe";

   public adr(CallGraphVertex var1, CallGraphVertex var2) {
      super(var1, var2);
   }

   public void q(Boolean var1) {
      this.q("attrIsSafe", var1);
   }

   public boolean q() {
      Boolean var1 = (Boolean)this.RF("attrIsSafe");
      return var1 != null && var1;
   }
}
