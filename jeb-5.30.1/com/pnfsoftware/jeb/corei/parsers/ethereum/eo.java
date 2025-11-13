package com.pnfsoftware.jeb.corei.parsers.ethereum;

import com.pnfsoftware.jeb.util.format.Strings;
import com.pnfsoftware.jeb.util.primitives.Integers;
import com.pnfsoftware.jeb.util.serialization.annotations.Ser;
import com.pnfsoftware.jeb.util.serialization.annotations.SerId;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

@Ser
public class eo {
   @SerId(1)
   int q;
   @SerId(2)
   int RF;
   @SerId(3)
   int xK;
   @SerId(4)
   Integer Dw;
   @SerId(5)
   Integer Uv;
   @SerId(6)
   boolean oW;
   @SerId(7)
   boolean gO;
   @SerId(8)
   boolean nf;
   @SerId(9)
   boolean gP;
   @SerId(10)
   boolean za;
   @SerId(11)
   boolean lm;
   @SerId(12)
   boolean zz;
   @SerId(13)
   boolean JY;
   @SerId(14)
   boolean HF;
   @SerId(15)
   boolean LK;
   @SerId(16)
   Set io = new TreeSet();
   @SerId(20)
   private List qa;
   @SerId(21)
   private boolean Hk;
   @SerId(22)
   private List Me;

   public eo(int var1) {
      this.q = var1;
   }

   public boolean q() {
      return this.q(false);
   }

   public boolean q(boolean var1) {
      return this.Dw == null && !this.oW && !this.nf && !this.zz && !this.gP && (!var1 || !this.za);
   }

   public boolean RF() {
      return this.lm || this.za;
   }

   public boolean q(int var1) {
      return (this.lm || this.za) && this.Dw != null && this.Dw == this.q;
   }

   public boolean RF(int var1) {
      return (this.lm || this.za) && this.RF + 1 == var1;
   }

   @Override
   public String toString() {
      String var1 = Strings.ff("0x%X-0x%X", this.q, this.RF);
      if (!this.oW) {
         var1 = var1 + Strings.ff("=>%s", this.Dw == null ? (this.q() ? "???" : "xxx") : "0x" + Integer.toHexString(this.Dw));
      }

      if (this.Uv != null) {
         var1 = var1 + "(FT)";
      }

      return var1;
   }

   public void q(List var1, int var2) {
      if (this.qa == null) {
         this.qa = new ArrayList(1);
      }

      this.qa.add(new com.pnfsoftware.jeb.corei.parsers.ethereum.eo.eo(var1, var2));
   }

   public boolean xK() {
      return this.qa != null && !this.qa.isEmpty();
   }

   public List Dw() {
      ArrayList var1 = new ArrayList();
      if (this.qa != null) {
         for (com.pnfsoftware.jeb.corei.parsers.ethereum.eo.eo var3 : this.qa) {
            if (!var1.contains(var3.RF)) {
               var1.add(var3.RF);
            }
         }
      }

      return var1;
   }

   public void RF(List var1, int var2) {
      if (var2 == this.RF + 1) {
         this.Hk = true;
      } else {
         if (this.Me == null) {
            this.Me = new ArrayList(1);
         }

         this.Me.add(new com.pnfsoftware.jeb.corei.parsers.ethereum.eo.eo(var1, var2));
      }
   }

   public boolean Uv() {
      return this.Hk;
   }

   public boolean oW() {
      return this.Me != null && !this.Me.isEmpty();
   }

   public List gO() {
      ArrayList var1 = new ArrayList();
      if (this.Hk) {
         var1.add(this.RF + 1);
      }

      if (this.Me != null) {
         for (com.pnfsoftware.jeb.corei.parsers.ethereum.eo.eo var3 : this.Me) {
            if (!var1.contains(var3.RF)) {
               var1.add(var3.RF);
            }
         }
      }

      return var1;
   }

   @Ser
   public static class eo {
      @SerId(1)
      List q;
      @SerId(2)
      int RF;

      eo(List var1, int var2) {
         if (var1 != null) {
            this.q = new ArrayList(var1);
         }

         this.RF = var2;
      }

      @Override
      public int hashCode() {
         int var1 = 1;
         var1 = 31 * var1 + this.RF;
         return 31 * var1 + (this.q == null ? 0 : this.q.hashCode());
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
            com.pnfsoftware.jeb.corei.parsers.ethereum.eo.eo var2 = (com.pnfsoftware.jeb.corei.parsers.ethereum.eo.eo)var1;
            if (this.RF != var2.RF) {
               return false;
            } else {
               if (this.q == null) {
                  if (var2.q != null) {
                     return false;
                  }
               } else if (!this.q.equals(var2.q)) {
                  return false;
               }

               return true;
            }
         }
      }

      @Override
      public String toString() {
         return this.q == null ? Strings.ff("0x%X", this.RF) : Strings.ff("%s => 0x%X", Integers.formatIntegerCollection(this.q, 16, "0x", null), this.RF);
      }
   }
}
