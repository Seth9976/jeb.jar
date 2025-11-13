package com.pnfsoftware.jeb.corei.parsers.wasm;

import com.pnfsoftware.jeb.util.format.Strings;
import com.pnfsoftware.jeb.util.serialization.annotations.Ser;
import com.pnfsoftware.jeb.util.serialization.annotations.SerId;
import java.util.List;

@Ser
public class oM {
   @SerId(1)
   List q;
   @SerId(2)
   List RF;
   @SerId(3)
   int xK;
   @SerId(4)
   int Dw;

   public oM(List var1, int var2, int var3, List var4) {
      this.q = var1;
      this.xK = var2;
      this.Dw = var3;
      this.RF = var4;
   }

   public int q() {
      return this.Dw - this.xK;
   }

   public List RF() {
      return this.q;
   }

   public boolean xK() {
      return this.RF != null;
   }

   public List Dw() {
      return this.RF;
   }

   public String Uv() {
      StringBuilder var1 = new StringBuilder();
      int var2 = 0;
      var1.append("local_types: ");

      for (int var4 : this.q) {
         if (var2 >= 1) {
            var1.append(", ");
         }

         var1.append(Xa.xK(var4));
         var2++;
      }

      var1.append("\n");
      int var6 = this.xK;

      for (SG var5 : this.RF) {
         Strings.ff(var1, "    %04Xh/+%04Xh: %s\n", var6, var6 - this.xK, var5);
         var6 += var5.getSize();
      }

      return var1.toString();
   }

   @Override
   public String toString() {
      String var1 = Strings.ff("Bytecode:%Xh-%Xh", this.xK, this.Dw);
      return var1 + "(" + this.RF.size() + ")";
   }
}
