package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.util.format.Strings;
import java.lang.invoke.StringConcatFactory;
import java.util.ArrayList;
import java.util.List;

public class beo extends ber {
   public String q;
   public beu RF;
   public List xK = new ArrayList();
   public List Dw = new ArrayList();
   public beq Uv;

   beo(bew var1) {
      super(var1);
      this.q = var1.Uv("name");
   }

   @Override
   void q(bev var1) {
      this.RF = (beu)var1.q(this.gO, "superType", beu.class);
      var1.q(this.gO, "functions", bep.class, this.Dw);
      this.Uv = (beq)var1.q(this.gO, "library", beq.class);
   }

   @Override
   public String toString() {
      StringBuilder var1 = new StringBuilder(StringConcatFactory.makeConcatWithConstants<"makeConcatWithConstants","\u0001">(this.q));
      if (this.RF != null && !this.RF.toString().equalsIgnoreCase("Null")) {
         Strings.ff(var1, " extends %s { ...", this.RF);
      }

      if (this.Uv != null) {
         Strings.ff(var1, " [LIB URL: %s]", this.Uv.RF);
      }

      for (bep var3 : this.Dw) {
         Strings.ff(var1, "\n  %s", var3);
      }

      return var1.toString();
   }
}
