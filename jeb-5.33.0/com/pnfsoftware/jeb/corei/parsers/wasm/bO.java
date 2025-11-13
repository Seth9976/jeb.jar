package com.pnfsoftware.jeb.corei.parsers.wasm;

import com.pnfsoftware.jeb.util.format.Strings;
import com.pnfsoftware.jeb.util.serialization.annotations.Ser;
import com.pnfsoftware.jeb.util.serialization.annotations.SerId;
import java.util.List;

@Ser
public class bO {
   @SerId(1)
   List pC;
   @SerId(2)
   List A;
   @SerId(3)
   int kS;
   @SerId(4)
   int wS;

   public bO(List var1, int var2, int var3, List var4) {
      this.pC = var1;
      this.kS = var2;
      this.wS = var3;
      this.A = var4;
   }

   public int pC() {
      return this.wS - this.kS;
   }

   public String A() {
      StringBuilder var1 = new StringBuilder();
      int var2 = 0;
      var1.append("local_types: ");

      for (int var4 : this.pC) {
         if (var2 >= 1) {
            var1.append(", ");
         }

         var1.append(Tb.kS(var4));
         var2++;
      }

      var1.append("\n");
      int var6 = this.kS;

      for (m var5 : this.A) {
         Strings.ff(var1, "    %04Xh/+%04Xh: %s\n", var6, var6 - this.kS, var5);
         var6 += var5.getSize();
      }

      return var1.toString();
   }

   @Override
   public String toString() {
      String var1 = Strings.ff("Bytecode:%Xh-%Xh", this.kS, this.wS);
      return var1 + "(" + this.A.size() + ")";
   }
}
