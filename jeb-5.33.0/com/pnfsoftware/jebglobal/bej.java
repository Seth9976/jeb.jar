package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.util.serialization.annotations.Ser;
import com.pnfsoftware.jeb.util.serialization.annotations.SerCustomInitPostGraph;
import com.pnfsoftware.jeb.util.serialization.annotations.SerId;
import com.pnfsoftware.jeb.util.serialization.annotations.SerTransient;
import com.pnfsoftware.jeb.util.serialization.annotations.SerVersion;

@Ser
@SerVersion(1)
public class bej {
   @SerTransient
   bel.Av pC;
   @SerId(1)
   byte[] A;
   @SerId(2)
   bem[] kS;
   @SerId(3)
   bey wS;
   @SerId(4)
   beh UT;
   @SerId(value = 5, deprecated = true)
   Boolean E;
   @SerId(value = 6, version = 1)
   Integer sY;

   void pC() {
      this.wS();
   }

   @SerCustomInitPostGraph
   private void wS() {
      int var1 = this.A[0] & 255;
      if (this.E == null && this.sY == null) {
         this.E = var1 >= 227 && var1 <= 254;
      }

      if (this.E != null && this.sY == null) {
         this.sY = Boolean.TRUE.equals(this.E) ? 1 : 0;
         if (var1 == 255) {
            this.sY = 2;
         }

         this.E = null;
      }

      if (this.sY == 0) {
         this.pC = bel.pC[var1];
      } else if (this.sY == 1) {
         this.pC = bel.A[var1 - 227];
      } else if (this.sY == 2) {
         var1 = this.A[1] & 255;
         this.pC = bel.wS[var1 - 0];
      } else if (this.sY == 3) {
         this.pC = bel.kS[var1 - 115];
      }
   }

   public boolean A() {
      return this.wS != null || this.UT != null;
   }

   public boolean kS() {
      for (bem var4 : this.kS) {
         int var5 = var4.getType();
         if (var5 == 2 || var5 == 5) {
            return true;
         }
      }

      return false;
   }
}
