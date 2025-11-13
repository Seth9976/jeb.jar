package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.units.code.android.JvmMethodSig;
import com.pnfsoftware.jeb.core.units.code.android.ir.DexDecEvaluationException;
import java.lang.invoke.StringConcatFactory;
import java.util.List;

public class bxw {
   String q;
   Integer RF;

   public bxw(String var1) {
      this(var1, null);
   }

   public bxw(String var1, Integer var2) {
      if (var1 == null) {
         throw new RuntimeException();
      } else {
         this.q = var1;
         this.RF = var2;
      }
   }

   public boolean q() {
      return this.q.contains(":");
   }

   public boolean RF() {
      return !this.xK() && this.q.contains("(");
   }

   public boolean xK() {
      return this.q.contains("<init>(");
   }

   public String Dw() {
      return this.q;
   }

   public String Uv() {
      int var1 = this.q.indexOf("->");
      int var2 = this.q.indexOf("(", var1);
      if (var2 < 0) {
         var2 = this.q.indexOf(":", var1);
      }

      return this.q.substring(var1 + 2, var2);
   }

   public boolean oW() {
      return this.RF != null;
   }

   public int gO() {
      return this.RF == null ? 0 : this.RF;
   }

   @Override
   public String toString() {
      return StringConcatFactory.makeConcatWithConstants<"makeConcatWithConstants","\u0001">(this.q);
   }

   public Object q(bye var1, String var2, List var3) throws DexDecEvaluationException {
      String var4 = JvmMethodSig.parse(var2).mname;
      switch (cvm.xK(var4)) {
         case -1993600206:
            return this.Uv();
         case -1661135310:
            String var5 = this.Dw();
            String var6 = var5.substring(var5.indexOf(58) + 1);
            bxy var7 = var1.q(var6, false);
            return var7.Dw();
         case -1071903403:
            return this.hashCode();
         case -520455021:
            return null;
         case 1195073928:
            return false;
         case 1590522277:
            return this.gO();
         case 1730227686:
            return true;
         default:
            throw new DexDecEvaluationException(
               cvm.q(new byte[]{22, 1, 3, 12, 2, 25, 8, 26, 0, 69, 76, 67, 108, 97, 126, 25, 90, 82, 95, 69, 8, 16}, 2, 132) + var2
            );
      }
   }
}
