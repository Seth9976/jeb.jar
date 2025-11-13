package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.units.code.android.ir.IDInstruction;
import com.pnfsoftware.jeb.core.units.code.java.IJavaType;
import com.pnfsoftware.jeb.util.format.Strings;
import java.util.ArrayList;
import java.util.List;

public class bho {
   int pC;
   int A;
   IJavaType kS;
   List wS = new ArrayList();
   IDInstruction UT;

   public bho(int var1, IJavaType var2, IDInstruction var3) {
      if (var1 < 0) {
         throw new IllegalArgumentException("Illegal header node for handler: id=" + var1);
      } else if (var2 == null) {
         throw new IllegalArgumentException("No type for handler, type should be at least Throwable");
      } else {
         this.pC = var1;
         this.A = var1;
         this.kS = var2;
         this.UT = var3;
      }
   }

   @Override
   public String toString() {
      StringBuilder var1 = new StringBuilder(this.kS.toString());

      for (IJavaType var3 : this.wS) {
         var1.append("|").append(var3);
      }

      if (this.A < 0) {
         Strings.ff(var1, "=(%d-empty)", this.pC);
      } else {
         Strings.ff(var1, "=(%d..%d)", this.pC, this.A);
      }

      return var1.toString();
   }
}
