package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.units.code.android.IDexUnit;
import com.pnfsoftware.jeb.core.units.code.android.dex.IDexDebugVariable;
import com.pnfsoftware.jeb.util.format.Strings;
import com.pnfsoftware.jeb.util.serialization.annotations.Ser;
import com.pnfsoftware.jeb.util.serialization.annotations.SerId;

@Ser
public class beu implements IDexDebugVariable {
   @SerId(1)
   int pC;
   @SerId(2)
   int A;
   @SerId(3)
   int kS;
   @SerId(4)
   int wS;
   @SerId(5)
   int UT;

   public beu(int var1, int var2, int var3, int var4, int var5) {
      this.pC = var1;
      this.A = var2;
      this.kS = var3;
      this.wS = var4;
      this.UT = var5;
   }

   @Override
   public int getAddress() {
      return this.pC;
   }

   @Override
   public int getRegister() {
      return this.A;
   }

   @Override
   public int getNameIndex() {
      return this.kS;
   }

   @Override
   public int getTypeIndex() {
      return this.wS;
   }

   @Override
   public int getSignatureIndex() {
      return this.UT;
   }

   @Override
   public String toString() {
      return this.format(null);
   }

   @Override
   public String format(IDexUnit var1) {
      StringBuilder var2 = new StringBuilder();
      if (this.pC >= 0) {
         Strings.ff(var2, "+%Xh", this.pC * 2);
      } else {
         Strings.ff(var2, "in");
      }

      Strings.ff(var2, ":v%d", this.A);
      if (this.kS >= 0) {
         if (var1 == null) {
            Strings.ff(var2, ":name=%d", this.kS);
         } else {
            String var3 = var1.getString(this.kS).getValue();
            Strings.ff(var2, ":%s", var3);
         }
      }

      if (this.wS >= 0) {
         if (var1 == null) {
            Strings.ff(var2, ":type=%d", this.wS);
         } else {
            String var4 = var1.getType(this.wS).getSignature();
            Strings.ff(var2, ":%s", var4);
         }
      }

      return var2.toString();
   }
}
