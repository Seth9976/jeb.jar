package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.units.code.android.DexParsingException;
import com.pnfsoftware.jeb.core.units.code.android.dex.IDexExceptionItem;
import com.pnfsoftware.jeb.core.units.code.android.ir.IDExceptionItem;
import com.pnfsoftware.jeb.util.format.Strings;
import com.pnfsoftware.jeb.util.serialization.annotations.Ser;
import com.pnfsoftware.jeb.util.serialization.annotations.SerId;
import java.util.ArrayList;
import java.util.List;

@Ser
public class biw implements IDexExceptionItem, IDExceptionItem {
   @SerId(1)
   int q;
   @SerId(2)
   int RF;
   @SerId(3)
   List xK;
   @SerId(4)
   private Integer Dw;

   private biw(int var1, int var2, List var3, Integer var4) {
      if (var1 >= 0 && var2 >= 0 && var3 != null) {
         this.q = var1;
         this.RF = var2;
         this.xK = var3;
         this.Dw = var4;
      } else {
         throw new DexParsingException(Strings.ff("Illegal exception item: address=%Xh size=%d handlers=%s", var1, var2, var3));
      }
   }

   public biw(int var1, int var2, List var3) {
      this(var1, var2, var3, null);
   }

   @Override
   public int hashCode() {
      int var1 = 1;
      var1 = 31 * var1 + (this.xK == null ? 0 : this.xK.hashCode());
      var1 = 31 * var1 + this.q;
      var1 = 31 * var1 + this.RF;
      return 31 * var1 + (this.Dw == null ? 0 : this.Dw.hashCode());
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
         biw var2 = (biw)var1;
         if (this.xK == null) {
            if (var2.xK != null) {
               return false;
            }
         } else if (!this.xK.equals(var2.xK)) {
            return false;
         }

         if (this.q != var2.q) {
            return false;
         } else if (this.RF != var2.RF) {
            return false;
         } else {
            if (this.Dw == null) {
               if (var2.Dw != null) {
                  return false;
               }
            } else if (!this.Dw.equals(var2.Dw)) {
               return false;
            }

            return true;
         }
      }
   }

   public biw q() {
      return new biw(this.q, this.RF, biv.q(this.xK), this.Dw);
   }

   @Override
   public int getTryAddress() {
      return this.q;
   }

   public void q(int var1, boolean var2) {
      int var3 = var1 - this.q;
      this.q += var3;
      if (var2) {
         this.RF -= var3;
         if (this.Dw != null) {
            this.Dw = this.Dw - var3;
         }
      }
   }

   @Override
   public int getTrySize() {
      return this.Dw == null ? this.RF : this.Dw;
   }

   public void q(int var1) {
      if (var1 < 0) {
         throw new IllegalArgumentException();
      } else {
         this.RF = var1;
         this.Dw = null;
      }
   }

   public int RF() {
      return this.q + this.getTrySize();
   }

   public void RF(int var1) {
      if (var1 < this.q) {
         throw new IllegalArgumentException();
      } else {
         this.Dw = var1 - this.q;
      }
   }

   public boolean xK() {
      return this.Dw != null;
   }

   public void Dw() {
      this.Dw = null;
   }

   public int Uv() {
      return this.q + this.RF;
   }

   public int oW() {
      return this.RF;
   }

   @Override
   public List getHandlers() {
      return new ArrayList(this.xK);
   }

   public int gO() {
      return this.xK.size();
   }

   public biv xK(int var1) {
      return (biv)this.xK.get(var1 >= 0 ? var1 : this.xK.size() + var1);
   }

   public List nf() {
      ArrayList var1 = new ArrayList(this.xK.size());
      this.xK.forEach(var1x -> var1.add(var1x.getAddress()));
      return var1;
   }

   @Override
   public String toString() {
      StringBuilder var1 = new StringBuilder();
      Strings.ff(var1, "%stry=[0x%X-0x%X[ handlers=[", this.xK() ? "adjusted_" : "", this.getTryAddress(), this.RF());
      int var2 = 0;

      for (biv var4 : this.xK) {
         if (var2++ >= 1) {
            var1.append(", ");
         }

         var1.append(var4.toString());
      }

      var1.append("]");
      return var1.toString();
   }
}
