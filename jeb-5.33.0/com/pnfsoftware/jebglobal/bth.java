package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.units.code.android.JvmMethodSig;
import com.pnfsoftware.jeb.core.units.code.android.ir.DexDecEvaluationException;
import java.lang.invoke.StringConcatFactory;
import java.util.List;

public class bth {
   String pC;
   Integer A;

   public bth(String var1) {
      this(var1, null);
   }

   public bth(String var1, Integer var2) {
      if (var1 == null) {
         throw new RuntimeException();
      } else {
         this.pC = var1;
         this.A = var2;
      }
   }

   public boolean pC() {
      return this.pC.contains(":");
   }

   public boolean A() {
      return !this.kS() && this.pC.contains("(");
   }

   public boolean kS() {
      return this.pC.contains("<init>(");
   }

   public String wS() {
      return this.pC;
   }

   public String UT() {
      int var1 = this.pC.indexOf("->");
      int var2 = this.pC.indexOf("(", var1);
      if (var2 < 0) {
         var2 = this.pC.indexOf(":", var1);
      }

      return this.pC.substring(var1 + 2, var2);
   }

   public boolean E() {
      return this.A != null;
   }

   public int sY() {
      return this.A == null ? 0 : this.A;
   }

   @Override
   public String toString() {
      return StringConcatFactory.makeConcatWithConstants<"makeConcatWithConstants","\u0001">(this.pC);
   }

   public Object pC(btp var1, String var2, List var3) throws DexDecEvaluationException {
      String var4 = JvmMethodSig.parse(var2).mname;
      switch (ckx.kS(var4)) {
         case -1993600206:
            return this.UT();
         case -1661135310:
            String var5 = this.wS();
            String var6 = var5.substring(var5.indexOf(58) + 1);
            btj var7 = var1.pC(var6, false);
            return var7.wS();
         case -1071903403:
            return this.hashCode();
         case -520455021:
            return null;
         case 1195073928:
            return false;
         case 1590522277:
            return this.sY();
         case 1730227686:
            return true;
         default:
            throw new DexDecEvaluationException(
               ckx.pC(new byte[]{89, 59, 29, 6, 5, 0, 31, 29, 6, 17, 1, 68, 101, 4, 14, 111, 67, 2, 18, 22, 95, 26}, 1, 12) + var2
            );
      }
   }
}
