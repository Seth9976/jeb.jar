package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.util.format.Strings;
import com.pnfsoftware.jeb.util.serialization.annotations.Ser;
import com.pnfsoftware.jeb.util.serialization.annotations.SerId;

@Ser
public class avl extends auo {
   @SerId(1)
   private String pC;

   public avl(long var1, String var3) {
      super(0, null);
      this.UT(3);
      this.pC(var1);
      this.pC = var3;
   }

   public String kS() {
      return this.pC;
   }

   @Override
   public String toString() {
      return Strings.ff("PseudoMnemonic(\"%s\")", this.pC);
   }
}
