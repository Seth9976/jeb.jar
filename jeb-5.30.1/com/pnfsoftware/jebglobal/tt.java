package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.util.format.TextBuilder;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class tt extends jA {
   public int oW;
   public String gO;
   public int nf;
   public int gP;
   int za;
   public AX lm;
   public AX zz;
   public List JY = new ArrayList();
   public List HF = new ArrayList();

   public tt(uL var1) throws IOException {
      super(var1, 512);
      this.oW = var1.readInt();
      if (this.oW == 0) {
         this.oW = 2;
      }

      this.gO = zR.q(var1, 128, true);
      int var2 = var1.readInt();
      this.nf = var1.readInt();
      int var3 = var1.readInt();
      this.gP = var1.readInt();
      this.za = this.gO(var1);
      this.xK(var1);
      if (var2 != 0) {
         this.q(var1, var2);
         this.lm = new AX(var1, "Symbol Types Pool");
      }

      if (var3 != 0) {
         this.q(var1, var3);
         this.zz = new AX(var1, "Symbol Names Pool");
      }

      jA var4 = null;

      while (this.gP(var1) > 0) {
         var4 = new jA(var1, 0);
         if (var4.xK == 513) {
            this.JY.add(new JF(var4, var1));
            var4 = null;
         } else if (var4.xK == 514) {
            this.JY.add(new ZC(var4, var1));
            var4 = null;
         } else if (var4.xK == 515) {
            this.HF.add(new L(var4, var1));
            var4 = null;
         } else if (var4.xK == 516) {
            var4 = null;
         } else if (var4.xK == 517) {
            var4 = null;
         } else {
            if (var4.xK != 518) {
               break;
            }

            var4 = null;
         }
      }

      if (var4 != null) {
         var1.q("Next header is unused { %s }", var4);
      }

      this.Uv(var1);
   }

   public int q() {
      return this.lm == null ? 0 : this.lm.q();
   }

   public String q(int var1) {
      return this.lm.q(var1);
   }

   public int RF() {
      return this.zz == null ? 0 : this.zz.q();
   }

   public String RF(int var1) {
      return this.zz.q(var1);
   }

   @Override
   public void q(TextBuilder var1) {
      var1.appendLine("id=%Xh name='%s'", this.oW, this.gO);
      var1.appendLine("lastPublicType=%d/%d lastPublicKey=%d/%d", this.nf, this.q(), this.gP, this.RF());
      if (this.lm != null) {
         var1.appendLine("Resource type symbol table:");
         this.lm.q(var1);
      }

      if (this.zz != null) {
         var1.appendLine("Resource key symbol table:");
         this.zz.q(var1);
      }

      var1.appendLine("Entry values:");

      for (jA var3 : this.JY) {
         var1.append((CharSequence)"- ");
         var3.q(var1);
      }
   }
}
