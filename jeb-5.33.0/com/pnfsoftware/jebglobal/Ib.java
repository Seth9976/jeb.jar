package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.units.code.CodePointer;
import com.pnfsoftware.jeb.core.units.code.Pointer;
import com.pnfsoftware.jeb.util.collect.SynchronizedLinkedMap;
import com.pnfsoftware.jeb.util.format.Strings;
import com.pnfsoftware.jeb.util.logging.GlobalLog;
import com.pnfsoftware.jeb.util.logging.ILogger;
import com.pnfsoftware.jeb.util.serialization.annotations.Ser;
import com.pnfsoftware.jeb.util.serialization.annotations.SerCustomInitPostGraph;
import com.pnfsoftware.jeb.util.serialization.annotations.SerId;
import javax.annotation.concurrent.Immutable;

@Ser
public class Ib {
   private static final ILogger pC = GlobalLog.getLogger(Ib.class);
   @SerId(1)
   private SynchronizedLinkedMap A = new SynchronizedLinkedMap();
   @SerId(2)
   private SynchronizedLinkedMap kS;

   @SerCustomInitPostGraph
   private void UT() {
      if (this.kS == null) {
         this.kS = new SynchronizedLinkedMap();
      }
   }

   public Ib() {
      this.UT();
   }

   public boolean pC(Pointer var1, int var2, int var3) {
      long var4 = var1.getAddress();
      int var6 = var1.getType() == 1 ? var1.getType() : 0;
      int var7 = var1 instanceof CodePointer ? ((CodePointer)var1).getMode() : -1;
      Ib.Sv var8 = new Ib.Sv(var4, var6, var7);
      Ib.Av var9 = this.pC(var8);
      if (var9 != null) {
         if ((var3 & 65536) != (var9.kS & 65536)) {
            if ((var3 & 65536) == 0) {
               var9.pC = var1;
               var9.kS &= -65537;
               var9.wS = -1;
               var9.A = var2;
               this.kS.remove(var8);
               this.A.put(var8, var9);
            }

            return true;
         } else if (var9.pC.equals(var1)) {
            if (var2 > var9.A) {
               var9.A = var2;
            }

            var9.kS |= var3;
            return true;
         } else if (var9.pC.getType() != 0 && var1.getType() == 0 && var1.getSize() == 0) {
            return false;
         } else {
            if (var9.pC.getType() == 0 && var1.getType() == 2) {
               Pointer var10 = var9.pC;
               var9.pC = var1;
               var1 = var10;
            }

            if (var9.pC.getType() == 2) {
               int var12 = var9.pC.getSize();
               int var11 = var1.getSize();
               if (var12 != var11) {
                  if (var12 == 0) {
                     return true;
                  }

                  if (var11 == 0) {
                     var9.pC.setSize(var11);
                     return true;
                  }

                  if (var12 >= 16 && var12 % 16 == 0 && var11 < var12) {
                     var9.pC.setSize(var11);
                     return true;
                  }

                  if (var11 >= 16 && var11 % 16 == 0 && var12 < var11) {
                     return true;
                  }

                  if (var12 < var11) {
                     var9.pC(var12);
                     var9.pC.setSize(var11);
                     return true;
                  }

                  if (var11 < var12) {
                     var9.pC(var11);
                     return true;
                  }
               }
            }

            new Object[]{var4, var9, null}[2] = new Ib.Av(var1, var2, var3);
            return false;
         }
      } else {
         if ((var3 & 65536) == 0) {
            this.A.put(var8, new Ib.Av(var1, var2, var3));
         } else {
            this.kS.put(var8, new Ib.Av(var1, var2, var3));
         }

         return true;
      }
   }

   public boolean pC() {
      return this.A.isEmpty() && this.kS.isEmpty();
   }

   public Ib.Av pC(Ib.Sv var1) {
      Ib.Av var2 = (Ib.Av)this.A.get(var1);
      return var2 != null ? var2 : (Ib.Av)this.kS.get(var1);
   }

   public Ib.Av A() {
      if (this.A.isEmpty()) {
         return this.E();
      } else {
         Ib.Sv var1 = (Ib.Sv)this.A.firstKey();
         return (Ib.Av)this.A.remove(var1);
      }
   }

   private Ib.Av E() {
      if (this.kS.isEmpty()) {
         return null;
      } else {
         Ib.Sv var1 = (Ib.Sv)this.kS.firstKey();
         return (Ib.Av)this.kS.remove(var1);
      }
   }

   public Ib.Av kS() {
      if (this.A.isEmpty()) {
         return this.sY();
      } else {
         Ib.Sv var1 = (Ib.Sv)this.A.firstKey();
         return (Ib.Av)this.A.get(var1);
      }
   }

   private Ib.Av sY() {
      if (this.kS.isEmpty()) {
         return null;
      } else {
         Ib.Sv var1 = (Ib.Sv)this.kS.firstKey();
         return (Ib.Av)this.kS.get(var1);
      }
   }

   public void wS() {
      this.A.clear();
      this.A = new SynchronizedLinkedMap();
      this.kS.clear();
      this.kS = new SynchronizedLinkedMap();
   }

   @Override
   public String toString() {
      if (this.kS.isEmpty()) {
         return this.A.toString();
      } else {
         StringBuilder var1 = new StringBuilder();
         var1.append("Safe elements: ").append(this.A.toString()).append("\n");
         var1.append("Unsafe elements: ").append(this.kS.toString());
         return var1.toString();
      }
   }

   @Ser
   public static class Av {
      @SerId(1)
      private Pointer pC;
      @SerId(2)
      private int A;
      @SerId(3)
      private int kS;
      @SerId(4)
      private int wS = -1;

      public Av(Pointer var1, int var2, int var3) {
         this.pC = var1;
         this.A = var2;
         this.kS = var3;
      }

      public Pointer pC() {
         return this.pC;
      }

      public int A() {
         return this.A;
      }

      public int kS() {
         return this.kS;
      }

      public void pC(int var1) {
         if (this.wS == -1) {
            this.wS = var1;
         } else if (var1 < this.wS) {
            this.wS = var1;
         }
      }

      @Override
      public String toString() {
         return Strings.ff("%s[perm=%d][flags=%d]", this.pC, this.A, this.kS);
      }
   }

   @Ser
   @Immutable
   public static class Sv {
      @SerId(1)
      long pC;
      @SerId(2)
      int A;
      @SerId(3)
      int kS = -1;

      public Sv(long var1, int var3, int var4) {
         this.pC = var1;
         this.A = var3;
         this.kS = var4;
      }

      @Override
      public int hashCode() {
         int var1 = 1;
         var1 = 31 * var1 + (int)(this.pC ^ this.pC >>> 32);
         var1 = 31 * var1 + this.kS;
         return 31 * var1 + this.A;
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
            Ib.Sv var2 = (Ib.Sv)var1;
            if (this.pC != var2.pC) {
               return false;
            } else {
               return this.kS != var2.kS ? false : this.A == var2.A;
            }
         }
      }

      @Override
      public String toString() {
         return Strings.ff("0x%X[type=%d][mode=%d]", this.pC, this.A, this.kS);
      }
   }
}
