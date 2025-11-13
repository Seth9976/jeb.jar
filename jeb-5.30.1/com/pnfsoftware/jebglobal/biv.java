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
public class biv implements IDexExceptionHandler, IDExceptionHandler {
   @SerId(1)
   private int q;
   @SerId(2)
   private int RF;

   public biv(int var1, int var2) {
      this.q = var1 < 0 ? -1 : var1;
      this.RF = var2;
   }

   public biv q() {
      return new biv(this.q, this.RF);
   }

   public static List q(List var0) {
      ArrayList var1 = new ArrayList(var0.size());

      for (biv var3 : var0) {
         var1.add(var3.q());
      }

      return var1;
   }

   @Override
   public int hashCode() {
      int var1 = 1;
      var1 = 31 * var1 + this.RF;
      return 31 * var1 + this.q;
   }

   @Override
   public boolean equals(Object var1) {
      if (this == var1) {
         return true;
      } else if (var1 != null && this.getClass() == var1.getClass()) {
         biv var2 = (biv)var1;
         return this.RF == var2.RF && this.q == var2.q;
      } else {
         return false;
      }
   }

   @Override
   public boolean isTyped() {
      return this.q >= 0;
   }

   public int RF() {
      return this.q;
   }

   @Override
   public int getTypeIndex() {
      return this.RF();
   }

   @Override
   public void setTypeIndex(int var1) {
      this.q = var1;
   }

   @Override
   public int getAddress() {
      return this.RF;
   }

   @Override
   public void setAddress(int var1) {
      this.RF = var1;
   }

   @Override
   public String toString() {
      String var1;
      if (this.RF < 0) {
         var1 = Strings.ff("-0x%X", -this.RF);
      } else {
         var1 = Strings.ff("0x%X", this.RF);
      }

      var1 = var1 + "(";
      var1 = var1 + (this.q < 0 ? "*" : Strings.ff("%d", this.q));
      return var1 + ")";
   }

   @Override
   public boolean isCatchAll(IDMethodContext var1) {
      return this.isCatchAll(var1, false);
   }

   @Override
   public boolean isCatchAll(IDMethodContext var1, boolean var2) {
      if (this.q == -1) {
         return true;
      } else {
         return var2 ? false : var1.getDex().getType(this.q).getSignature(false).equals("Ljava/lang/Throwable;");
      }
   }

   @Override
   public IDexType resolveType(IDexUnit var1) {
      return this.q == -1 ? var1.getType("Ljava/lang/Throwable;") : var1.getType(this.q);
   }
}
