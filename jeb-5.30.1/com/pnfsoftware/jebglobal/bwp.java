package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.units.code.android.ir.IDImm;
import com.pnfsoftware.jeb.util.format.Strings;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class bwp {
   private int q;
   private BigInteger RF;
   private BigInteger xK;
   private List Dw;

   public bwp(int var1) {
      this(var1, true);
   }

   public bwp(int var1, boolean var2) {
      if (var1 <= 0) {
         throw new IllegalArgumentException();
      } else {
         this.q = var1;
         this.RF = BigInteger.ONE.shiftLeft(var1 - 1).negate();
         this.xK = BigInteger.ONE.shiftLeft(var1 - 1);
         if (var2) {
            this.nf();
         }
      }
   }

   public bwp q() {
      return new bwp(this.q);
   }

   public bwp RF() {
      bwp var1 = new bwp(this.q, false);
      if (this.Dw == null) {
         var1.Dw = null;
      } else {
         var1.Dw = new ArrayList(this.Dw.size());

         for (bwp.eo var3 : this.Dw) {
            var1.Dw.add(var3.q());
         }
      }

      return var1;
   }

   @Override
   public int hashCode() {
      int var1 = 1;
      var1 = 31 * var1 + this.q;
      var1 = 31 * var1 + (this.xK == null ? 0 : this.xK.hashCode());
      var1 = 31 * var1 + (this.RF == null ? 0 : this.RF.hashCode());
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
         bwp var2 = (bwp)var1;
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

            if (this.RF == null) {
               if (var2.RF != null) {
                  return false;
               }
            } else if (!this.RF.equals(var2.RF)) {
               return false;
            }

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

   public int xK() {
      return this.q;
   }

   public List Dw() {
      return this.Dw == null ? Arrays.asList(new bwp.eo(this.RF, this.xK)) : Collections.unmodifiableList(this.Dw);
   }

   public int Uv() {
      return this.Dw == null ? 1 : this.Dw.size();
   }

   public BigInteger oW() {
      return this.RF;
   }

   public BigInteger gO() {
      return this.xK;
   }

   public boolean nf() {
      if (this.Dw == null) {
         this.Dw = new ArrayList();
         return true;
      } else if (this.Dw.isEmpty()) {
         return false;
      } else {
         this.Dw.clear();
         return true;
      }
   }

   public boolean gP() {
      return this.Dw != null && this.Dw.isEmpty();
   }

   public boolean za() {
      if (this.Dw == null) {
         return false;
      } else {
         this.Dw = null;
         return true;
      }
   }

   public boolean lm() {
      return this.Dw == null;
   }

   public boolean zz() {
      return this.Dw == null || !this.Dw.isEmpty();
   }

   private bwp.eo Uv(IDImm var1, IDImm var2) {
      BigInteger var3;
      if (var1 == null) {
         var3 = this.RF;
      } else {
         var3 = gO(var1);
      }

      BigInteger var4;
      if (var2 == null) {
         var4 = this.xK;
      } else {
         var4 = gO(var2);
      }

      if (var3.compareTo(var4) > 0) {
         throw new IllegalArgumentException();
      } else {
         return new bwp.eo(var3, var4);
      }
   }

   public boolean q(IDImm var1, IDImm var2) {
      return this.q(this.Uv(var1, var2));
   }

   public bwp RF(IDImm var1, IDImm var2) {
      this.q(var1, var2);
      return this;
   }

   public boolean q(IDImm var1) {
      BigInteger var2 = gO(var1);
      return this.q(new bwp.eo(var2, var2.add(BigInteger.ONE)));
   }

   public bwp RF(IDImm var1) {
      this.q(var1);
      return this;
   }

   private boolean q(bwp.eo var1) {
      if (var1.q.compareTo(var1.RF) >= 0) {
         return false;
      } else if (this.Dw == null) {
         return false;
      } else {
         if (this.Dw.isEmpty()) {
            this.Dw.add(var1);
         } else {
            int var2;
            for (var2 = 0; var2 < this.Dw.size(); var2++) {
               bwp.eo var3 = (bwp.eo)this.Dw.get(var2);
               if (var3.q.compareTo(var1.q) > 0) {
                  break;
               }
            }

            if (var2 > 0) {
               bwp.eo var7 = (bwp.eo)this.Dw.get(var2 - 1);
               if (var7.RF.compareTo(var1.RF) >= 0) {
                  return false;
               }
            }

            this.Dw.add(var2, var1);
            if (var2 > 0) {
               int var8 = var2 - 1;
               bwp.eo var4 = (bwp.eo)this.Dw.get(var8);
               if (var4.RF.compareTo(var1.q) >= 0) {
                  this.Dw.remove(var8);
                  var1.q = var4.q;
                  var2--;
               }
            }

            BigInteger var9 = null;
            int var10 = var2 + 1;

            while (var10 < this.Dw.size()) {
               bwp.eo var5 = (bwp.eo)this.Dw.get(var10);
               if (var1.RF.compareTo(var5.q) < 0) {
                  break;
               }

               var9 = var5.RF;
               this.Dw.remove(var10);
            }

            if (var9 == null || var9.compareTo(var1.RF) < 0) {
               var9 = var1.RF;
            }

            var1.RF = var9;
         }

         if (this.Dw.size() == 1) {
            var1 = (bwp.eo)this.Dw.get(0);
            if (var1.RF().equals(this.RF) && var1.xK().equals(this.xK)) {
               this.Dw = null;
            }
         }

         return true;
      }
   }

   public boolean q(bwp var1) {
      if (var1 != null && var1.q == this.q) {
         int var2 = 0;

         for (bwp.eo var4 : var1.Dw()) {
            if (this.q(var4)) {
               var2++;
            }
         }

         return var2 > 0;
      } else {
         throw new IllegalArgumentException();
      }
   }

   public bwp RF(bwp var1) {
      this.q(var1);
      return this;
   }

   public boolean xK(IDImm var1, IDImm var2) {
      return this.RF(this.Uv(var1, var2));
   }

   public bwp Dw(IDImm var1, IDImm var2) {
      this.xK(var1, var2);
      return this;
   }

   public boolean xK(IDImm var1) {
      BigInteger var2 = gO(var1);
      return this.RF(new bwp.eo(var2, var2.add(BigInteger.ONE)));
   }

   public bwp Dw(IDImm var1) {
      this.xK(var1);
      return this;
   }

   private boolean RF(bwp.eo var1) {
      if (var1.q.compareTo(var1.RF) >= 0) {
         if (this.Dw == null) {
            this.Dw = new ArrayList();
            return true;
         } else if (this.Dw.isEmpty()) {
            return false;
         } else {
            this.Dw.clear();
            return true;
         }
      } else if (this.Dw == null) {
         this.Dw = new ArrayList();
         this.Dw.add(var1);
         return true;
      } else if (this.Dw.isEmpty()) {
         return false;
      } else {
         int var2 = 0;
         int var3 = 0;

         while (var3 < this.Dw.size()) {
            bwp.eo var4 = (bwp.eo)this.Dw.get(var3);
            if (var4.RF.compareTo(var1.q) <= 0) {
               this.Dw.remove(var3);
               var2++;
            } else if (var4.q.compareTo(var1.q) <= 0) {
               if (var4.q.compareTo(var1.q) < 0) {
                  var4.q = var1.q;
                  var2++;
               }

               if (var4.RF.compareTo(var1.RF) > 0) {
                  var4.RF = var1.RF;
                  var2++;
               }

               var3++;
            } else if (var4.q.compareTo(var1.RF) < 0) {
               if (var4.RF.compareTo(var1.RF) > 0) {
                  var4.RF = var1.RF;
                  var2++;
               }

               var3++;
            } else {
               this.Dw.remove(var3);
               var2++;
            }
         }

         return var2 > 0;
      }
   }

   public boolean xK(bwp var1) {
      if (var1 != null && var1.q == this.q) {
         if (this.Dw == null) {
            if (var1.Dw == null) {
               return false;
            } else {
               this.Dw = new ArrayList(var1.Dw.size());

               for (bwp.eo var9 : var1.Dw) {
                  this.Dw.add(var9.q());
               }

               return true;
            }
         } else if (var1.Dw == null) {
            return false;
         } else {
            int var2 = 0;
            int var3 = 0;

            for (bwp.eo var5 : var1.Dw) {
               while (var3 < this.Dw.size()) {
                  bwp.eo var6 = (bwp.eo)this.Dw.get(var3);
                  if (var6.RF.compareTo(var5.q) <= 0) {
                     this.Dw.remove(var3);
                     var2++;
                  } else if (var6.q.compareTo(var5.q) <= 0) {
                     if (var6.q.compareTo(var5.q) < 0) {
                        var6.q = var5.q;
                        var2++;
                     }

                     if (var6.RF.compareTo(var5.RF) > 0) {
                        bwp.eo var7 = new bwp.eo(var5.RF, var6.RF);
                        this.Dw.add(var3 + 1, var7);
                        var3++;
                        var6.RF = var5.RF;
                        var2++;
                        break;
                     }

                     var3++;
                  } else {
                     if (var6.q.compareTo(var5.RF) >= 0) {
                        break;
                     }

                     if (var6.RF.compareTo(var5.RF) > 0) {
                        bwp.eo var10 = new bwp.eo(var5.RF, var6.RF);
                        this.Dw.add(var3 + 1, var10);
                        var3++;
                        var6.RF = var5.RF;
                        var2++;
                        break;
                     }

                     var3++;
                  }
               }
            }

            while (var3 < this.Dw.size()) {
               this.Dw.remove(var3);
               var2++;
            }

            return var2 > 0;
         }
      } else {
         throw new IllegalArgumentException();
      }
   }

   public bwp Dw(bwp var1) {
      this.xK(var1);
      return this;
   }

   public boolean Uv(IDImm var1) {
      BigInteger var2 = gO(var1);
      return this.q(var2);
   }

   public bwp oW(IDImm var1) {
      this.Uv(var1);
      return this;
   }

   private boolean q(BigInteger var1) {
      if (this.Dw == null) {
         this.Dw = new ArrayList();
         if (var1.equals(this.RF)) {
            this.Dw.add(new bwp.eo(this.RF.add(BigInteger.ONE), this.xK));
         } else if (var1.equals(this.xK.subtract(BigInteger.ONE))) {
            this.Dw.add(new bwp.eo(this.RF, this.xK.subtract(BigInteger.ONE)));
         } else {
            this.Dw.add(new bwp.eo(this.RF, var1));
            this.Dw.add(new bwp.eo(var1.add(BigInteger.ONE), this.xK));
         }

         return true;
      } else if (this.Dw.isEmpty()) {
         return false;
      } else {
         for (int var2 = 0; var2 < this.Dw.size(); var2++) {
            bwp.eo var3 = (bwp.eo)this.Dw.get(var2);
            if (var1.compareTo(var3.q) >= 0 && var1.compareTo(var3.RF) < 0) {
               if (var1.equals(var3.q)) {
                  var3.q = var3.q.add(BigInteger.ONE);
                  if (var3.q.equals(var3.RF)) {
                     this.Dw.remove(var2);
                  }

                  return true;
               }

               if (var1.equals(var3.RF.subtract(BigInteger.ONE))) {
                  var3.RF = var3.RF.subtract(BigInteger.ONE);
                  if (var3.q.equals(var3.RF)) {
                     this.Dw.remove(var2);
                  }

                  return true;
               }

               bwp.eo var4 = new bwp.eo(var1.add(BigInteger.ONE), var3.RF);
               var3.RF = var1;
               this.Dw.add(var2 + 1, var4);
               return true;
            }

            if (var3.RF.compareTo(var1) >= 0) {
               break;
            }
         }

         return false;
      }
   }

   public bwp JY() {
      if (this.Dw == null) {
         return new bwp(this.q);
      } else if (this.Dw.isEmpty()) {
         return new bwp(this.q, false);
      } else {
         bwp var1 = new bwp(this.q);
         BigInteger var2 = this.RF;
         bwp.eo var3 = null;

         for (int var4 = 0; var4 < this.Dw.size(); var4++) {
            var3 = (bwp.eo)this.Dw.get(var4);
            if (var3.q.compareTo(var2) > 0) {
               var1.Dw.add(new bwp.eo(var2, var3.q));
            }

            var2 = var3.RF;
         }

         if (var3.RF.compareTo(this.xK) < 0) {
            var1.Dw.add(new bwp.eo(var2, this.xK));
         }

         return var1;
      }
   }

   @Override
   public String toString() {
      if (this.Dw == null) {
         return "ALL";
      } else if (this.Dw.isEmpty()) {
         return "NONE";
      } else {
         StringBuilder var1 = new StringBuilder();
         int var2 = 0;

         for (bwp.eo var4 : this.Dw) {
            if (var2 >= 1) {
               var1.append("U");
            }

            var1.append(var4.q(true, this.RF, this.xK));
            var2++;
         }

         return var1.toString();
      }
   }

   private static BigInteger gO(IDImm var0) {
      return BigInteger.valueOf(var0.getRawValue());
   }

   public static final class eo {
      BigInteger q;
      BigInteger RF;

      public eo(BigInteger var1, BigInteger var2) {
         if (var1 != null && var2 != null) {
            this.q = var1;
            this.RF = var2;
         } else {
            throw new IllegalArgumentException();
         }
      }

      public bwp.eo q() {
         return new bwp.eo(this.q, this.RF);
      }

      public BigInteger RF() {
         return this.q;
      }

      public BigInteger xK() {
         return this.RF;
      }

      public BigInteger Dw() {
         return this.RF.subtract(this.q);
      }

      @Override
      public int hashCode() {
         int var1 = 1;
         var1 = 31 * var1 + (this.q == null ? 0 : this.q.hashCode());
         return 31 * var1 + (this.RF == null ? 0 : this.RF.hashCode());
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
            bwp.eo var2 = (bwp.eo)var1;
            if (this.q == null) {
               if (var2.q != null) {
                  return false;
               }
            } else if (!this.q.equals(var2.q)) {
               return false;
            }

            if (this.RF == null) {
               if (var2.RF != null) {
                  return false;
               }
            } else if (!this.RF.equals(var2.RF)) {
               return false;
            }

            return true;
         }
      }

      @Override
      public String toString() {
         return this.q(false, null, null);
      }

      public String q(boolean var1, BigInteger var2, BigInteger var3) {
         if (this.q.equals(var2) && this.RF.equals(var3)) {
            return "ALL";
         } else if (this.q.add(BigInteger.ONE).equals(this.RF)) {
            return Strings.ff("{%s}", this.q);
         } else if (this.q.equals(var2)) {
            return var1 ? Strings.ff("[MIN,%s]", this.RF.subtract(BigInteger.ONE)) : Strings.ff("[MIN,%s[", this.RF);
         } else if (this.RF.equals(var3)) {
            return var1 ? Strings.ff("[%s,MAX]", this.q) : Strings.ff("[%s,END[", this.q);
         } else {
            return var1 ? Strings.ff("[%s,%s]", this.q, this.RF.subtract(BigInteger.ONE)) : Strings.ff("[%s,%s[", this.q, this.RF);
         }
      }
   }
}
