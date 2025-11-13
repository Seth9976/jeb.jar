package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.units.code.android.ir.IDInstruction;
import com.pnfsoftware.jeb.core.units.code.java.IJavaType;
import com.pnfsoftware.jeb.util.format.Strings;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class bll {
   int q;
   int RF;
   IJavaType xK;
   List Dw = new ArrayList();
   IDInstruction Uv;

   public bll(int var1, IJavaType var2, IDInstruction var3) {
      if (var1 < 0) {
         throw new IllegalArgumentException("Illegal header node for handler: id=" + var1);
      } else if (var2 == null) {
         throw new IllegalArgumentException("No type for handler, type should be at least Throwable");
      } else {
         this.q = var1;
         this.RF = var1;
         this.xK = var2;
         this.Uv = var3;
      }
   }

   public int q() {
      return this.q;
   }

   public int RF() {
      return this.RF;
   }

   public IJavaType xK() {
      return this.xK;
   }

   public List Dw() {
      return Collections.unmodifiableList(this.Dw);
   }

   public IDInstruction Uv() {
      return this.Uv;
   }

   @Override
   public String toString() {
      StringBuilder var1 = new StringBuilder(this.xK.toString());

      for (IJavaType var3 : this.Dw) {
         var1.append("|").append(var3);
      }

      if (this.RF < 0) {
         Strings.ff(var1, "=(%d-empty)", this.q);
      } else {
         Strings.ff(var1, "=(%d..%d)", this.q, this.RF);
      }

      return var1.toString();
   }
}
