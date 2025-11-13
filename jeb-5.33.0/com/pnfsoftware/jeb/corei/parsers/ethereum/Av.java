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
public class Av {
   @SerId(1)
   int pC;
   @SerId(2)
   int A;
   @SerId(3)
   int kS;
   @SerId(4)
   Integer wS;
   @SerId(5)
   Integer UT;
   @SerId(6)
   boolean E;
   @SerId(7)
   boolean sY;
   @SerId(8)
   boolean ys;
   @SerId(9)
   boolean ld;
   @SerId(10)
   boolean gp;
   @SerId(11)
   boolean oT;
   @SerId(12)
   boolean fI;
   @SerId(13)
   boolean WR;
   @SerId(14)
   boolean NS;
   @SerId(15)
   boolean vP;
   @SerId(16)
   Set xC = new TreeSet();
   @SerId(20)
   private List ED;
   @SerId(21)
   private boolean Sc;
   @SerId(22)
   private List ah;

   public Av(int var1) {
      this.pC = var1;
   }

   public boolean pC() {
      return this.pC(false);
   }

   public boolean pC(boolean var1) {
      return this.wS == null && !this.E && !this.ys && !this.fI && !this.ld && (!var1 || !this.gp);
   }

   @Override
   public String toString() {
      String var1 = Strings.ff("0x%X-0x%X", this.pC, this.A);
      if (!this.E) {
         var1 = var1 + Strings.ff("=>%s", this.wS == null ? (this.pC() ? "???" : "xxx") : "0x" + Integer.toHexString(this.wS));
      }

      if (this.UT != null) {
         var1 = var1 + "(FT)";
      }

      return var1;
   }

   public void pC(List var1, int var2) {
      if (this.ED == null) {
         this.ED = new ArrayList(1);
      }

      this.ED.add(new com.pnfsoftware.jeb.corei.parsers.ethereum.Av.Av(var1, var2));
   }

   public boolean A() {
      return this.ED != null && !this.ED.isEmpty();
   }

   public List kS() {
      ArrayList var1 = new ArrayList();
      if (this.ED != null) {
         for (com.pnfsoftware.jeb.corei.parsers.ethereum.Av.Av var3 : this.ED) {
            if (!var1.contains(var3.A)) {
               var1.add(var3.A);
            }
         }
      }

      return var1;
   }

   public void A(List var1, int var2) {
      if (var2 == this.A + 1) {
         this.Sc = true;
      } else {
         if (this.ah == null) {
            this.ah = new ArrayList(1);
         }

         this.ah.add(new com.pnfsoftware.jeb.corei.parsers.ethereum.Av.Av(var1, var2));
      }
   }

   public boolean wS() {
      return this.Sc;
   }

   public boolean UT() {
      return this.ah != null && !this.ah.isEmpty();
   }

   public List E() {
      ArrayList var1 = new ArrayList();
      if (this.Sc) {
         var1.add(this.A + 1);
      }

      if (this.ah != null) {
         for (com.pnfsoftware.jeb.corei.parsers.ethereum.Av.Av var3 : this.ah) {
            if (!var1.contains(var3.A)) {
               var1.add(var3.A);
            }
         }
      }

      return var1;
   }

   @Ser
   public static class Av {
      @SerId(1)
      List pC;
      @SerId(2)
      int A;

      Av(List var1, int var2) {
         if (var1 != null) {
            this.pC = new ArrayList(var1);
         }

         this.A = var2;
      }

      @Override
      public int hashCode() {
         int var1 = 1;
         var1 = 31 * var1 + this.A;
         return 31 * var1 + (this.pC == null ? 0 : this.pC.hashCode());
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
            com.pnfsoftware.jeb.corei.parsers.ethereum.Av.Av var2 = (com.pnfsoftware.jeb.corei.parsers.ethereum.Av.Av)var1;
            if (this.A != var2.A) {
               return false;
            } else {
               if (this.pC == null) {
                  if (var2.pC != null) {
                     return false;
                  }
               } else if (!this.pC.equals(var2.pC)) {
                  return false;
               }

               return true;
            }
         }
      }

      @Override
      public String toString() {
         return this.pC == null ? Strings.ff("0x%X", this.A) : Strings.ff("%s => 0x%X", Integers.formatIntegerCollection(this.pC, 16, "0x", null), this.A);
      }
   }
}
