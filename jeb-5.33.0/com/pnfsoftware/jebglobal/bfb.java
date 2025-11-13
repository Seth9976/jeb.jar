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
public class bfb implements IDexExceptionItem, IDExceptionItem {
   @SerId(1)
   int pC;
   @SerId(2)
   int A;
   @SerId(3)
   List kS;
   @SerId(4)
   private Integer wS;

   private bfb(int var1, int var2, List var3, Integer var4) {
      if (var1 >= 0 && var2 >= 0 && var3 != null) {
         this.pC = var1;
         this.A = var2;
         this.kS = var3;
         this.wS = var4;
      } else {
         throw new DexParsingException(Strings.ff("Illegal exception item: address=%Xh size=%d handlers=%s", var1, var2, var3));
      }
   }

   public bfb(int var1, int var2, List var3) {
      this(var1, var2, var3, null);
   }

   @Override
   public int hashCode() {
      int var1 = 1;
      var1 = 31 * var1 + (this.kS == null ? 0 : this.kS.hashCode());
      var1 = 31 * var1 + this.pC;
      var1 = 31 * var1 + this.A;
      return 31 * var1 + (this.wS == null ? 0 : this.wS.hashCode());
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
         bfb var2 = (bfb)var1;
         if (this.kS == null) {
            if (var2.kS != null) {
               return false;
            }
         } else if (!this.kS.equals(var2.kS)) {
            return false;
         }

         if (this.pC != var2.pC) {
            return false;
         } else if (this.A != var2.A) {
            return false;
         } else {
            if (this.wS == null) {
               if (var2.wS != null) {
                  return false;
               }
            } else if (!this.wS.equals(var2.wS)) {
               return false;
            }

            return true;
         }
      }
   }

   public bfb pC() {
      return new bfb(this.pC, this.A, bfa.pC(this.kS), this.wS);
   }

   @Override
   public int getTryAddress() {
      return this.pC;
   }

   @Override
   public int getTrySize() {
      return this.wS == null ? this.A : this.wS;
   }

   public void pC(int var1) {
      if (var1 < 0) {
         throw new IllegalArgumentException();
      } else {
         this.A = var1;
         this.wS = null;
      }
   }

   public int A() {
      return this.pC + this.getTrySize();
   }

   public void A(int var1) {
      if (var1 < this.pC) {
         throw new IllegalArgumentException();
      } else {
         this.wS = var1 - this.pC;
      }
   }

   public boolean kS() {
      return this.wS != null;
   }

   @Override
   public List getHandlers() {
      return new ArrayList(this.kS);
   }

   public int wS() {
      return this.kS.size();
   }

   public bfa kS(int var1) {
      return (bfa)this.kS.get(var1 >= 0 ? var1 : this.kS.size() + var1);
   }

   public List UT() {
      ArrayList var1 = new ArrayList(this.kS.size());
      this.kS.forEach(var1x -> var1.add(var1x.getAddress()));
      return var1;
   }

   @Override
   public String toString() {
      StringBuilder var1 = new StringBuilder();
      Strings.ff(var1, "%stry=[0x%X-0x%X[ handlers=[", this.kS() ? "adjusted_" : "", this.getTryAddress(), this.A());
      int var2 = 0;

      for (bfa var4 : this.kS) {
         if (var2++ >= 1) {
            var1.append(", ");
         }

         var1.append(var4.toString());
      }

      var1.append("]");
      return var1.toString();
   }
}
