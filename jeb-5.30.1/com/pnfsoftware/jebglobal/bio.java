package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.units.code.android.IDexUnit;
import com.pnfsoftware.jeb.core.units.code.android.dex.IDexDebugVariable;
import com.pnfsoftware.jeb.util.format.Strings;
import com.pnfsoftware.jeb.util.serialization.annotations.Ser;
import com.pnfsoftware.jeb.util.serialization.annotations.SerId;

@Ser
public class bio implements IDexDebugVariable {
   @SerId(1)
   int q;
   @SerId(2)
   int RF;
   @SerId(3)
   int xK;
   @SerId(4)
   int Dw;
   @SerId(5)
   int Uv;

   public bio(int var1, int var2, int var3, int var4, int var5) {
      this.q = var1;
      this.RF = var2;
      this.xK = var3;
      this.Dw = var4;
      this.Uv = var5;
   }

   public bio q() {
      return new bio(this.q, this.RF, this.xK, this.Dw, this.Uv);
   }

   public bio q(int var1) {
      return new bio(var1, this.RF, this.xK, this.Dw, this.Uv);
   }

   @Override
   public int getAddress() {
      return this.q;
   }

   @Override
   public int getRegister() {
      return this.RF;
   }

   @Override
   public int getNameIndex() {
      return this.xK;
   }

   @Override
   public int getTypeIndex() {
      return this.Dw;
   }

   @Override
   public int getSignatureIndex() {
      return this.Uv;
   }

   @Override
   public String toString() {
      return this.format(null);
   }

   @Override
   public String format(IDexUnit var1) {
      StringBuilder var2 = new StringBuilder();
      if (this.q >= 0) {
         Strings.ff(var2, "+%Xh", this.q * 2);
      } else {
         Strings.ff(var2, "in");
      }

      Strings.ff(var2, ":v%d", this.RF);
      if (this.xK >= 0) {
         if (var1 == null) {
            Strings.ff(var2, ":name=%d", this.xK);
         } else {
            String var3 = var1.getString(this.xK).getValue();
            Strings.ff(var2, ":%s", var3);
         }
      }

      if (this.Dw >= 0) {
         if (var1 == null) {
            Strings.ff(var2, ":type=%d", this.Dw);
         } else {
            String var4 = var1.getType(this.Dw).getSignature();
            Strings.ff(var2, ":%s", var4);
         }
      }

      return var2.toString();
   }
}
