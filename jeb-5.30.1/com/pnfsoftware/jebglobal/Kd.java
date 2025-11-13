package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.util.format.TextBuilder;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

public class Kd extends jA implements zb {
   boolean oW;
   AX gO;
   List nf = new ArrayList();

   public Kd(uL var1) throws IOException {
      super(var1, 0);
      if (this.xK == 0) {
         this.oW = true;
      } else if (this.xK != 2) {
         throw new IOException("Unexpected chunk type: " + this.xK);
      }

      int var2 = var1.readInt();
      this.xK(var1);

      while (true) {
         jA var3 = new jA(var1, 0);
         if (var3.xK == 1) {
            this.gO = new AX(var3, var1, "Resources String Pool", false);
            HashSet var4 = new HashSet();

            for (int var5 = 0; var5 < var2; var5++) {
               tt var6 = new tt(var1);
               if (var4.add(var6.oW)) {
                  this.nf.add(var6);
               }

               if (this.gP(var1) < 8) {
                  break;
               }
            }

            this.Uv(var1);
            return;
         }

         var3.Uv(var1);
      }
   }

   public AX q() {
      return this.gO;
   }

   public List RF() {
      return this.nf;
   }

   @Override
   public void q(TextBuilder var1) {
      var1.appendLine("Resource Table:");
      var1.indent();
      var1.appendLine("Strings: (%d,%d)", this.gO.q(), this.gO.RF());
      this.gO.q(var1);
      var1.unindent();
      var1.indent();
      var1.appendLine("Packages: (%d)", this.nf.size());

      for (tt var3 : this.nf) {
         var1.indent();
         var3.q(var1);
         var1.unindent();
      }

      var1.unindent();
   }
}
