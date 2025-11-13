package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.util.format.Strings;
import com.pnfsoftware.jeb.util.format.TextBuilder;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ta {
   public static final int q = 1;
   public static final int RF = 2;
   public static final int xK = 4;
   public static final int Dw = 8;
   public static final int Uv = 8;
   private int HF;
   private int LK;
   public int oW;
   public HK gO;
   public int nf;
   public List gP = new ArrayList();
   public int za;
   public int lm;
   public String zz;
   public String JY;

   public ta(uL var1) throws IOException {
      this.HF = var1.readUnsignedShort();
      this.LK = var1.readUnsignedShort();
      this.oW = var1.readInt();
      if (this.Uv()) {
         int var8 = this.oW;
         this.oW = this.HF & 65535;
         this.HF = 8;
         int var9 = this.LK >> 8 & 0xFF;
         this.LK &= 255;
         if (this.oW != -1) {
            this.gO = new HK(8, var9, var8);
         }
      } else {
         byte var2 = 8;
         int var3 = 0;
         if (this.RF()) {
            this.nf = var1.readInt();
            var3 = var1.readInt();
            var2 += 8;
         }

         zR.xK(var1, this.HF - var2);
         if (this.oW != -1) {
            if (!this.RF()) {
               this.gO = new HK(var1);
            } else {
               for (int var4 = 0; var4 < var3; var4++) {
                  int var5 = var1.readInt();
                  HK var6 = new HK(var1);
                  oI var7 = new oI(var5, var6);
                  this.gP.add(var7);
               }
            }
         }
      }
   }

   public boolean q() {
      return (this.LK & 1) == 0;
   }

   public boolean RF() {
      return (this.LK & 1) != 0;
   }

   public boolean xK() {
      return (this.LK & 2) != 0;
   }

   public boolean Dw() {
      return (this.LK & 4) != 0;
   }

   public boolean Uv() {
      return (this.LK & 8) != 0;
   }

   public boolean oW() {
      return this.oW == -1;
   }

   public void q(TextBuilder var1) {
      var1.appendLine("Value: flags=%d key=%Xh value=%s", this.LK, this.oW, this.gO);
   }

   @Override
   public String toString() {
      return Strings.ff("flags=%d key=%Xh value=%s (index=%Xh)", this.LK, this.oW, this.gO, this.za);
   }
}
