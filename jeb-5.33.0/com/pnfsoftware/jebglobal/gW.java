package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.util.format.Strings;
import java.io.IOException;

public class gW {
   public byte pC;
   public long A;
   public String kS;
   public String wS;
   public int UT;

   public static gW pC(AN var0) throws IOException {
      gW var1 = new gW();
      var1.pC = var0.pC();
      var1.A = var0.ys();
      var1.kS = var0.NS();
      var1.wS = var0.NS();
      var1.UT = var0.wS();
      return var1;
   }

   @Override
   public int hashCode() {
      int var1 = 1;
      var1 = 31 * var1 + (this.wS == null ? 0 : this.wS.hashCode());
      var1 = 31 * var1 + this.pC;
      var1 = 31 * var1 + (this.kS == null ? 0 : this.kS.hashCode());
      var1 = 31 * var1 + this.UT;
      return 31 * var1 + (int)(this.A ^ this.A >>> 32);
   }

   @Override
   public boolean equals(Object var1) {
      if (this == var1) {
         return true;
      } else if (var1 == null) {
         return false;
      } else if (this.getClass() != var1.getClass()) {
         return false;
      } else {
         gW var2 = (gW)var1;
         if (this.wS == null) {
            if (var2.wS != null) {
               return false;
            }
         } else if (!this.wS.equals(var2.wS)) {
            return false;
         }

         if (this.pC != var2.pC) {
            return false;
         } else {
            if (this.kS == null) {
               if (var2.kS != null) {
                  return false;
               }
            } else if (!this.kS.equals(var2.kS)) {
               return false;
            }

            return this.UT != var2.UT ? false : this.A == var2.A;
         }
      }
   }

   @Override
   public String toString() {
      return Strings.ff("id=%d,tag=%d,status=0x%X,sig=%s%s", this.A, this.pC, this.UT, this.kS, Strings.isBlank(this.wS) ? "" : ",genSig=" + this.wS);
   }
}
