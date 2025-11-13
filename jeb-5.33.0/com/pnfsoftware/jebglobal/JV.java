package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.util.format.Strings;
import java.io.IOException;
import java.lang.invoke.StringConcatFactory;

public class JV {
   public String pC;
   public String A;

   public static JV pC(AN var0) throws IOException {
      JV var1 = new JV();
      var1.pC = var0.NS();
      var1.A = var0.NS();
      return var1;
   }

   @Override
   public String toString() {
      return Strings.isBlank(this.A)
         ? StringConcatFactory.makeConcatWithConstants<"makeConcatWithConstants","\u0001">(this.pC)
         : Strings.ff("sig=%s,genSig=%s", this.pC, this.A);
   }
}
