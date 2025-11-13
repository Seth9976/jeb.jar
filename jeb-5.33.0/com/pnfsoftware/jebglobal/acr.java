package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.units.code.asm.items.INativeItem;
import com.pnfsoftware.jeb.core.units.code.asm.items.INativeMethodItem;
import com.pnfsoftware.jeb.util.format.Strings;
import com.pnfsoftware.jeb.util.serialization.annotations.Ser;
import com.pnfsoftware.jeb.util.serialization.annotations.SerId;
import java.util.ArrayList;
import java.util.List;

@Ser
public class acr {
   @SerId(1)
   private INativeItem pC;
   @SerId(2)
   private Integer A;
   @SerId(3)
   private List kS = new ArrayList();

   public acr(INativeItem var1) {
      if (var1 == null) {
         throw new NullPointerException();
      } else {
         this.pC = var1;
      }
   }

   public void pC(int var1) {
      this.A = var1;
   }

   public Integer pC() {
      return this.A;
   }

   public boolean pC(INativeMethodItem var1) {
      for (acr.Av var3 : this.kS) {
         if (var3.pC == var1) {
            if (var1 != null) {
               var3.A++;
            }

            return true;
         }
      }

      this.kS.add(new acr.Av(var1));
      return true;
   }

   public boolean pC(INativeMethodItem var1, boolean var2) {
      for (int var3 = 0; var3 < this.kS.size(); var3++) {
         acr.Av var4 = (acr.Av)this.kS.get(var3);
         if (var4.pC == var1) {
            if (var1 == null) {
               var4.A = 0;
            } else {
               var4.A--;
            }

            if (var4.A == 0 || var2) {
               this.kS.remove(var3);
            }

            return true;
         }
      }

      return false;
   }

   public boolean A() {
      return !this.kS.isEmpty();
   }

   public List kS() {
      return this.kS;
   }

   @Override
   public String toString() {
      return Strings.ff("item=%s;symbolId=%s;users=%s", this.pC.getName(), this.A == null ? "X" : "0x" + Integer.toHexString(this.A), this.kS);
   }

   @Ser
   public static class Av {
      @SerId(1)
      INativeMethodItem pC;
      @SerId(2)
      int A;

      public Av(INativeMethodItem var1) {
         this.pC = var1;
         if (var1 == null) {
            this.A = -1;
         } else {
            this.A = 1;
         }
      }

      public INativeMethodItem pC() {
         return this.pC;
      }

      @Override
      public String toString() {
         return this.pC == null ? "GLOBAL" : Strings.ff("%s[%dx]", this.pC.getName(), this.A);
      }
   }
}
