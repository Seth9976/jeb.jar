package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.util.format.Strings;
import java.io.IOException;
import java.lang.invoke.StringConcatFactory;

public class Vi {
   public String q;
   public String RF;

   public static Vi q(Hv var0) throws IOException {
      Vi var1 = new Vi();
      var1.q = var0.xW();
      var1.RF = var0.xW();
      return var1;
   }

   @Override
   public String toString() {
      return Strings.isBlank(this.RF)
         ? StringConcatFactory.makeConcatWithConstants<"makeConcatWithConstants","\u0001">(this.q)
         : Strings.ff("sig=%s,genSig=%s", this.q, this.RF);
   }
}
