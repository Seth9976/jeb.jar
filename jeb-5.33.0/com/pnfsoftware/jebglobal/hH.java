package com.pnfsoftware.jebglobal;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class hH extends tH {
   public int E;
   public String sY;
   public int ys;
   public int ld;
   int gp;
   public Vb oT;
   public Vb fI;
   public List WR = new ArrayList();
   public List NS = new ArrayList();

   public hH(EX var1) throws IOException {
      super(var1, 512);
      this.E = var1.readInt();
      if (this.E == 0) {
         this.E = 2;
      }

      this.sY = bM.pC(var1, 128, true);
      int var2 = var1.readInt();
      this.ys = var1.readInt();
      int var3 = var1.readInt();
      this.ld = var1.readInt();
      this.gp = this.wS(var1);
      this.pC(var1);
      if (var2 != 0) {
         this.pC(var1, var2);
         this.oT = new Vb(var1, "Symbol Types Pool");
      }

      if (var3 != 0) {
         this.pC(var1, var3);
         this.fI = new Vb(var1, "Symbol Names Pool");
      }

      tH var4 = null;

      while (this.E(var1) > 0) {
         var4 = new tH(var1, 0);
         if (var4.kS == 513) {
            this.WR.add(new od(var4, var1));
            var4 = null;
         } else if (var4.kS == 514) {
            this.WR.add(new Up(var4, var1));
            var4 = null;
         } else if (var4.kS == 515) {
            this.NS.add(new Px(var4, var1));
            var4 = null;
         } else if (var4.kS == 516) {
            var4 = null;
         } else if (var4.kS == 517) {
            var4 = null;
         } else {
            if (var4.kS != 518) {
               break;
            }

            var4 = null;
         }
      }

      if (var4 != null) {
         var1.pC("Next header is unused { %s }", var4);
      }

      this.kS(var1);
   }

   public int pC() {
      return this.oT == null ? 0 : this.oT.pC();
   }

   public String pC(int var1) {
      return this.oT.pC(var1);
   }
}
