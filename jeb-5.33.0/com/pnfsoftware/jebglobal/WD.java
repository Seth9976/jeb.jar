package com.pnfsoftware.jebglobal;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

public class WD extends tH {
   boolean E;
   Vb sY;
   List ys = new ArrayList();

   public WD(EX var1) throws IOException {
      super(var1, 0);
      if (this.kS == 0) {
         this.E = true;
      } else if (this.kS != 2) {
         throw new IOException("Unexpected chunk type: " + this.kS);
      }

      int var2 = var1.readInt();
      this.pC(var1);

      while (true) {
         tH var3 = new tH(var1, 0);
         if (var3.kS == 1) {
            this.sY = new Vb(var3, var1, "Resources String Pool", false);
            HashSet var4 = new HashSet();

            for (int var5 = 0; var5 < var2; var5++) {
               hH var6 = new hH(var1);
               if (var4.add(var6.E)) {
                  this.ys.add(var6);
               }

               if (this.E(var1) < 8) {
                  break;
               }
            }

            this.kS(var1);
            return;
         }

         var3.kS(var1);
      }
   }

   public Vb pC() {
      return this.sY;
   }

   public List A() {
      return this.ys;
   }
}
