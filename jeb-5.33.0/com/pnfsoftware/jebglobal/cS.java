package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.util.format.Strings;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class cS {
   private int ld;
   private int gp;
   public int pC;
   public fp A;
   public int kS;
   public List wS = new ArrayList();
   public int UT;
   public int E;
   public String sY;
   public String ys;

   public cS(EX var1) throws IOException {
      this.ld = var1.readUnsignedShort();
      this.gp = var1.readUnsignedShort();
      this.pC = var1.readInt();
      if (this.wS()) {
         int var8 = this.pC;
         this.pC = this.ld & 65535;
         this.ld = 8;
         int var9 = this.gp >> 8 & 0xFF;
         this.gp &= 255;
         if (this.pC != -1) {
            this.A = new fp(8, var9, var8);
         }
      } else {
         byte var2 = 8;
         int var3 = 0;
         if (this.A()) {
            this.kS = var1.readInt();
            var3 = var1.readInt();
            var2 += 8;
         }

         bM.A(var1, this.ld - var2);
         if (this.pC != -1) {
            if (!this.A()) {
               this.A = new fp(var1);
            } else {
               for (int var4 = 0; var4 < var3; var4++) {
                  int var5 = var1.readInt();
                  fp var6 = new fp(var1);
                  Bz var7 = new Bz(var5, var6);
                  this.wS.add(var7);
               }
            }
         }
      }
   }

   public boolean pC() {
      return (this.gp & 1) == 0;
   }

   public boolean A() {
      return (this.gp & 1) != 0;
   }

   public boolean kS() {
      return (this.gp & 2) != 0;
   }

   public boolean wS() {
      return (this.gp & 8) != 0;
   }

   public boolean UT() {
      return this.pC == -1;
   }

   @Override
   public String toString() {
      return Strings.ff("flags=%d key=%Xh value=%s (index=%Xh)", this.gp, this.pC, this.A, this.UT);
   }
}
