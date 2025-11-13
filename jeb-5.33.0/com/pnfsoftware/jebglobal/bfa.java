package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.units.code.android.IDexUnit;
import com.pnfsoftware.jeb.core.units.code.android.dex.IDexExceptionHandler;
import com.pnfsoftware.jeb.core.units.code.android.dex.IDexType;
import com.pnfsoftware.jeb.core.units.code.android.ir.IDExceptionHandler;
import com.pnfsoftware.jeb.core.units.code.android.ir.IDMethodContext;
import com.pnfsoftware.jeb.util.format.Strings;
import com.pnfsoftware.jeb.util.serialization.annotations.Ser;
import com.pnfsoftware.jeb.util.serialization.annotations.SerId;
import java.util.ArrayList;
import java.util.List;

@Ser
public class bfa implements IDexExceptionHandler, IDExceptionHandler {
   @SerId(1)
   private int pC;
   @SerId(2)
   private int A;

   public bfa(int var1, int var2) {
      this.pC = var1 < 0 ? -1 : var1;
      this.A = var2;
   }

   public bfa pC() {
      return new bfa(this.pC, this.A);
   }

   public static List pC(List var0) {
      ArrayList var1 = new ArrayList(var0.size());

      for (bfa var3 : var0) {
         var1.add(var3.pC());
      }

      return var1;
   }

   @Override
   public int hashCode() {
      int var1 = 1;
      var1 = 31 * var1 + this.A;
      return 31 * var1 + this.pC;
   }

   @Override
   public boolean equals(Object var1) {
      if (this == var1) {
         return true;
      } else if (var1 != null && this.getClass() == var1.getClass()) {
         bfa var2 = (bfa)var1;
         return this.A == var2.A && this.pC == var2.pC;
      } else {
         return false;
      }
   }

   @Override
   public boolean isTyped() {
      return this.pC >= 0;
   }

   public int A() {
      return this.pC;
   }

   @Override
   public int getTypeIndex() {
      return this.A();
   }

   @Override
   public void setTypeIndex(int var1) {
      this.pC = var1;
   }

   @Override
   public int getAddress() {
      return this.A;
   }

   @Override
   public void setAddress(int var1) {
      this.A = var1;
   }

   @Override
   public String toString() {
      String var1;
      if (this.A < 0) {
         var1 = Strings.ff("-0x%X", -this.A);
      } else {
         var1 = Strings.ff("0x%X", this.A);
      }

      var1 = var1 + "(";
      var1 = var1 + (this.pC < 0 ? "*" : Strings.ff("%d", this.pC));
      return var1 + ")";
   }

   @Override
   public boolean isCatchAll(IDMethodContext var1) {
      return this.isCatchAll(var1, false);
   }

   @Override
   public boolean isCatchAll(IDMethodContext var1, boolean var2) {
      if (this.pC == -1) {
         return true;
      } else {
         return var2 ? false : var1.getDex().getType(this.pC).getSignature(false).equals("Ljava/lang/Throwable;");
      }
   }

   @Override
   public IDexType resolveType(IDexUnit var1) {
      return this.pC == -1 ? var1.getType("Ljava/lang/Throwable;") : var1.getType(this.pC);
   }
}
