package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.util.serialization.annotations.Ser;
import com.pnfsoftware.jeb.util.serialization.annotations.SerCustomInitPostGraph;
import com.pnfsoftware.jeb.util.serialization.annotations.SerId;
import com.pnfsoftware.jeb.util.serialization.annotations.SerTransient;
import com.pnfsoftware.jeb.util.serialization.annotations.SerVersion;

@Ser
@SerVersion(1)
public class bid {
   @SerTransient
   bif.eo q;
   @SerId(1)
   byte[] RF;
   @SerId(2)
   big[] xK;
   @SerId(3)
   bis Dw;
   @SerId(4)
   bib Uv;
   @SerId(value = 5, deprecated = true)
   Boolean oW;
   @SerId(value = 6, version = 1)
   Integer gO;

   void q() {
      this.Dw();
   }

   @SerCustomInitPostGraph
   private void Dw() {
      int var1 = this.RF[0] & 255;
      if (this.oW == null && this.gO == null) {
         this.oW = var1 >= 227 && var1 <= 254;
      }

      if (this.oW != null && this.gO == null) {
         this.gO = Boolean.TRUE.equals(this.oW) ? 1 : 0;
         if (var1 == 255) {
            this.gO = 2;
         }

         this.oW = null;
      }

      if (this.gO == 0) {
         this.q = bif.KT[var1];
      } else if (this.gO == 1) {
         this.q = bif.Ef[var1 - 227];
      } else if (this.gO == 2) {
         var1 = this.RF[1] & 255;
         this.q = bif.wF[var1 - 0];
      } else if (this.gO == 3) {
         this.q = bif.sH[var1 - 115];
      }
   }

   public boolean RF() {
      return this.Dw != null || this.Uv != null;
   }

   public boolean xK() {
      for (big var4 : this.xK) {
         int var5 = var4.getType();
         if (var5 == 2 || var5 == 5) {
            return true;
         }
      }

      return false;
   }
}
