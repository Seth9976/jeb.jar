package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.util.format.Strings;
import java.lang.invoke.StringConcatFactory;
import java.util.ArrayList;
import java.util.List;

public class bav extends bay {
   public String pC;
   public bba A;
   public List kS = new ArrayList();
   public List wS = new ArrayList();
   public bax UT;

   bav(bbc var1) {
      super(var1);
      this.pC = var1.UT("name");
   }

   @Override
   void pC(bbb var1) {
      this.A = (bba)var1.pC(this.sY, "superType", bba.class);
      var1.pC(this.sY, "functions", baw.class, this.wS);
      this.UT = (bax)var1.pC(this.sY, "library", bax.class);
   }

   @Override
   public String toString() {
      StringBuilder var1 = new StringBuilder(StringConcatFactory.makeConcatWithConstants<"makeConcatWithConstants","\u0001">(this.pC));
      if (this.A != null && !this.A.toString().equalsIgnoreCase("Null")) {
         Strings.ff(var1, " extends %s { ...", this.A);
      }

      if (this.UT != null) {
         Strings.ff(var1, " [LIB URL: %s]", this.UT.A);
      }

      for (baw var3 : this.wS) {
         Strings.ff(var1, "\n  %s", var3);
      }

      return var1.toString();
   }
}
