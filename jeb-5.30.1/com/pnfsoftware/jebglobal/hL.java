package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.util.format.Strings;
import java.io.IOException;

public class hL {
   public byte q;
   public long RF;
   public String xK;
   public String Dw;
   public int Uv;

   public static hL q(Hv var0) throws IOException {
      hL var1 = new hL();
      var1.q = var0.RF();
      var1.RF = var0.HF();
      var1.xK = var0.xW();
      var1.Dw = var0.xW();
      var1.Uv = var0.Uv();
      return var1;
   }

   @Override
   public int hashCode() {
      int var1 = 1;
      var1 = 31 * var1 + (this.Dw == null ? 0 : this.Dw.hashCode());
      var1 = 31 * var1 + this.q;
      var1 = 31 * var1 + (this.xK == null ? 0 : this.xK.hashCode());
      var1 = 31 * var1 + this.Uv;
      return 31 * var1 + (int)(this.RF ^ this.RF >>> 32);
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
         hL var2 = (hL)var1;
         if (this.Dw == null) {
            if (var2.Dw != null) {
               return false;
            }
         } else if (!this.Dw.equals(var2.Dw)) {
            return false;
         }

         if (this.q != var2.q) {
            return false;
         } else {
            if (this.xK == null) {
               if (var2.xK != null) {
                  return false;
               }
            } else if (!this.xK.equals(var2.xK)) {
               return false;
            }

            return this.Uv != var2.Uv ? false : this.RF == var2.RF;
         }
      }
   }

   @Override
   public String toString() {
      return Strings.ff("id=%d,tag=%d,status=0x%X,sig=%s%s", this.RF, this.q, this.Uv, this.xK, Strings.isBlank(this.Dw) ? "" : ",genSig=" + this.Dw);
   }
}
