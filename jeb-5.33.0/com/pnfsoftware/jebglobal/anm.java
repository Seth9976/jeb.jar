package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.IEImm;
import com.pnfsoftware.jeb.util.format.Strings;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public final class anm {
   private int pC;
   private BigInteger A;
   private BigInteger kS;
   private List wS;

   public anm(int var1) {
      this(var1, true);
   }

   public anm(int var1, boolean var2) {
      if (var1 <= 0) {
         throw new IllegalArgumentException();
      } else {
         this.pC = var1;
         this.A = BigInteger.ONE.shiftLeft(var1 - 1).negate();
         this.kS = BigInteger.ONE.shiftLeft(var1 - 1);
         if (var2) {
            this.sY();
         }
      }
   }

   public anm pC() {
      return new anm(this.pC);
   }

   public anm A() {
      anm var1 = new anm(this.pC, false);
      if (this.wS == null) {
         var1.wS = null;
      } else {
         var1.wS = new ArrayList(this.wS.size());

         for (anm.Av var3 : this.wS) {
            var1.wS.add(var3.pC());
         }
      }

      return var1;
   }

   @Override
   public int hashCode() {
      int var1 = 1;
      var1 = 31 * var1 + this.pC;
      var1 = 31 * var1 + (this.kS == null ? 0 : this.kS.hashCode());
      var1 = 31 * var1 + (this.A == null ? 0 : this.A.hashCode());
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
         anm var2 = (anm)var1;
         if (this.pC != var2.pC) {
            return false;
         } else {
            if (this.kS == null) {
               if (var2.kS != null) {
                  return false;
               }
            } else if (!this.kS.equals(var2.kS)) {
               return false;
            }

            if (this.A == null) {
               if (var2.A != null) {
                  return false;
               }
            } else if (!this.A.equals(var2.A)) {
               return false;
            }

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

   public List kS() {
      return this.wS == null ? Arrays.asList(new anm.Av(this.A, this.kS)) : Collections.unmodifiableList(this.wS);
   }

   public int wS() {
      return this.wS == null ? 1 : this.wS.size();
   }

   public BigInteger UT() {
      return this.A;
   }

   public BigInteger E() {
      return this.kS;
   }

   public boolean sY() {
      if (this.wS == null) {
         this.wS = new ArrayList();
         return true;
      } else if (this.wS.isEmpty()) {
         return false;
      } else {
         this.wS.clear();
         return true;
      }
   }

   public boolean ys() {
      return this.wS != null && this.wS.isEmpty();
   }

   public boolean ld() {
      if (this.wS == null) {
         return false;
      } else {
         this.wS = null;
         return true;
      }
   }

   public boolean gp() {
      return this.wS == null;
   }

   private anm.Av wS(IEImm var1, IEImm var2) {
      BigInteger var3;
      if (var1 == null) {
         var3 = this.A;
      } else {
         var3 = var1.getValue();
      }

      BigInteger var4;
      if (var2 == null) {
         var4 = this.kS;
      } else {
         var4 = var2.getValue();
      }

      if (var3.compareTo(var4) > 0) {
         throw new IllegalArgumentException();
      } else {
         return new anm.Av(var3, var4);
      }
   }

   public boolean pC(IEImm var1, IEImm var2) {
      return this.pC(this.wS(var1, var2));
   }

   public anm A(IEImm var1, IEImm var2) {
      this.pC(var1, var2);
      return this;
   }

   public boolean pC(IEImm var1) {
      BigInteger var2 = var1.getValue();
      return this.pC(new anm.Av(var2, var2.add(BigInteger.ONE)));
   }

   private boolean pC(anm.Av var1) {
      if (var1.pC.compareTo(var1.A) >= 0) {
         return false;
      } else if (this.wS == null) {
         return false;
      } else {
         if (this.wS.isEmpty()) {
            this.wS.add(var1);
         } else {
            int var2;
            for (var2 = 0; var2 < this.wS.size(); var2++) {
               anm.Av var3 = (anm.Av)this.wS.get(var2);
               if (var3.pC.compareTo(var1.pC) > 0) {
                  break;
               }
            }

            if (var2 > 0) {
               anm.Av var7 = (anm.Av)this.wS.get(var2 - 1);
               if (var7.A.compareTo(var1.A) >= 0) {
                  return false;
               }
            }

            this.wS.add(var2, var1);
            if (var2 > 0) {
               int var8 = var2 - 1;
               anm.Av var4 = (anm.Av)this.wS.get(var8);
               if (var4.A.compareTo(var1.pC) >= 0) {
                  this.wS.remove(var8);
                  var1.pC = var4.pC;
                  var2--;
               }
            }

            BigInteger var9 = null;
            int var10 = var2 + 1;

            while (var10 < this.wS.size()) {
               anm.Av var5 = (anm.Av)this.wS.get(var10);
               if (var1.A.compareTo(var5.pC) < 0) {
                  break;
               }

               var9 = var5.A;
               this.wS.remove(var10);
            }

            if (var9 == null || var9.compareTo(var1.A) < 0) {
               var9 = var1.A;
            }

            var1.A = var9;
         }

         if (this.wS.size() == 1) {
            var1 = (anm.Av)this.wS.get(0);
            if (var1.A().equals(this.A) && var1.kS().equals(this.kS)) {
               this.wS = null;
            }
         }

         return true;
      }
   }

   public boolean pC(anm var1) {
      if (var1 != null && var1.pC == this.pC) {
         int var2 = 0;

         for (anm.Av var4 : var1.kS()) {
            if (this.pC(var4)) {
               var2++;
            }
         }

         return var2 > 0;
      } else {
         throw new IllegalArgumentException();
      }
   }

   public anm A(anm var1) {
      this.pC(var1);
      return this;
   }

   public boolean kS(IEImm var1, IEImm var2) {
      return this.A(this.wS(var1, var2));
   }

   public boolean A(IEImm var1) {
      BigInteger var2 = var1.getValue();
      return this.A(new anm.Av(var2, var2.add(BigInteger.ONE)));
   }

   public anm kS(IEImm var1) {
      this.A(var1);
      return this;
   }

   private boolean A(anm.Av var1) {
      if (var1.pC.compareTo(var1.A) >= 0) {
         if (this.wS == null) {
            this.wS = new ArrayList();
            return true;
         } else if (this.wS.isEmpty()) {
            return false;
         } else {
            this.wS.clear();
            return true;
         }
      } else if (this.wS == null) {
         this.wS = new ArrayList();
         this.wS.add(var1);
         return true;
      } else if (this.wS.isEmpty()) {
         return false;
      } else {
         int var2 = 0;
         int var3 = 0;

         while (var3 < this.wS.size()) {
            anm.Av var4 = (anm.Av)this.wS.get(var3);
            if (var4.A.compareTo(var1.pC) <= 0) {
               this.wS.remove(var3);
               var2++;
            } else if (var4.pC.compareTo(var1.pC) <= 0) {
               if (var4.pC.compareTo(var1.pC) < 0) {
                  var4.pC = var1.pC;
                  var2++;
               }

               if (var4.A.compareTo(var1.A) > 0) {
                  var4.A = var1.A;
                  var2++;
               }

               var3++;
            } else if (var4.pC.compareTo(var1.A) < 0) {
               if (var4.A.compareTo(var1.A) > 0) {
                  var4.A = var1.A;
                  var2++;
               }

               var3++;
            } else {
               this.wS.remove(var3);
               var2++;
            }
         }

         return var2 > 0;
      }
   }

   public boolean kS(anm var1) {
      if (var1 != null && var1.pC == this.pC) {
         if (this.wS == null) {
            if (var1.wS == null) {
               return false;
            } else {
               this.wS = new ArrayList(var1.wS.size());

               for (anm.Av var9 : var1.wS) {
                  this.wS.add(var9.pC());
               }

               return true;
            }
         } else if (var1.wS == null) {
            return false;
         } else {
            int var2 = 0;
            int var3 = 0;

            for (anm.Av var5 : var1.wS) {
               while (var3 < this.wS.size()) {
                  anm.Av var6 = (anm.Av)this.wS.get(var3);
                  if (var6.A.compareTo(var5.pC) <= 0) {
                     this.wS.remove(var3);
                     var2++;
                  } else if (var6.pC.compareTo(var5.pC) <= 0) {
                     if (var6.pC.compareTo(var5.pC) < 0) {
                        var6.pC = var5.pC;
                        var2++;
                     }

                     if (var6.A.compareTo(var5.A) > 0) {
                        anm.Av var7 = new anm.Av(var5.A, var6.A);
                        this.wS.add(var3 + 1, var7);
                        var3++;
                        var6.A = var5.A;
                        var2++;
                        break;
                     }

                     var3++;
                  } else {
                     if (var6.pC.compareTo(var5.A) >= 0) {
                        break;
                     }

                     if (var6.A.compareTo(var5.A) > 0) {
                        anm.Av var10 = new anm.Av(var5.A, var6.A);
                        this.wS.add(var3 + 1, var10);
                        var3++;
                        var6.A = var5.A;
                        var2++;
                        break;
                     }

                     var3++;
                  }
               }
            }

            while (var3 < this.wS.size()) {
               this.wS.remove(var3);
               var2++;
            }

            return var2 > 0;
         }
      } else {
         throw new IllegalArgumentException();
      }
   }

   public anm wS(anm var1) {
      this.kS(var1);
      return this;
   }

   public boolean wS(IEImm var1) {
      BigInteger var2 = var1.getValue();
      return this.pC(var2);
   }

   private boolean pC(BigInteger var1) {
      if (this.wS == null) {
         this.wS = new ArrayList();
         if (var1.equals(this.A)) {
            this.wS.add(new anm.Av(this.A.add(BigInteger.ONE), this.kS));
         } else if (var1.equals(this.kS.subtract(BigInteger.ONE))) {
            this.wS.add(new anm.Av(this.A, this.kS.subtract(BigInteger.ONE)));
         } else {
            this.wS.add(new anm.Av(this.A, var1));
            this.wS.add(new anm.Av(var1.add(BigInteger.ONE), this.kS));
         }

         return true;
      } else if (this.wS.isEmpty()) {
         return false;
      } else {
         for (int var2 = 0; var2 < this.wS.size(); var2++) {
            anm.Av var3 = (anm.Av)this.wS.get(var2);
            if (var1.compareTo(var3.pC) >= 0 && var1.compareTo(var3.A) < 0) {
               if (var1.equals(var3.pC)) {
                  var3.pC = var3.pC.add(BigInteger.ONE);
                  if (var3.pC.equals(var3.A)) {
                     this.wS.remove(var2);
                  }

                  return true;
               }

               if (var1.equals(var3.A.subtract(BigInteger.ONE))) {
                  var3.A = var3.A.subtract(BigInteger.ONE);
                  if (var3.pC.equals(var3.A)) {
                     this.wS.remove(var2);
                  }

                  return true;
               }

               anm.Av var4 = new anm.Av(var1.add(BigInteger.ONE), var3.A);
               var3.A = var1;
               this.wS.add(var2 + 1, var4);
               return true;
            }

            if (var3.A.compareTo(var1) >= 0) {
               break;
            }
         }

         return false;
      }
   }

   public anm oT() {
      if (this.wS == null) {
         return new anm(this.pC);
      } else if (this.wS.isEmpty()) {
         return new anm(this.pC, false);
      } else {
         anm var1 = new anm(this.pC);
         BigInteger var2 = this.A;
         anm.Av var3 = null;

         for (int var4 = 0; var4 < this.wS.size(); var4++) {
            var3 = (anm.Av)this.wS.get(var4);
            if (var3.pC.compareTo(var2) > 0) {
               var1.wS.add(new anm.Av(var2, var3.pC));
            }

            var2 = var3.A;
         }

         if (var3.A.compareTo(this.kS) < 0) {
            var1.wS.add(new anm.Av(var2, this.kS));
         }

         return var1;
      }
   }

   @Override
   public String toString() {
      if (this.wS == null) {
         return "ALL";
      } else if (this.wS.isEmpty()) {
         return "NONE";
      } else {
         StringBuilder var1 = new StringBuilder();
         int var2 = 0;

         for (anm.Av var4 : this.wS) {
            if (var2 >= 1) {
               var1.append("U");
            }

            var1.append(var4.pC(true, this.A, this.kS));
            var2++;
         }

         return var1.toString();
      }
   }

   public static final class Av {
      BigInteger pC;
      BigInteger A;

      public Av(BigInteger var1, BigInteger var2) {
         if (var1 != null && var2 != null) {
            this.pC = var1;
            this.A = var2;
         } else {
            throw new IllegalArgumentException();
         }
      }

      public anm.Av pC() {
         return new anm.Av(this.pC, this.A);
      }

      public BigInteger A() {
         return this.pC;
      }

      public BigInteger kS() {
         return this.A;
      }

      public BigInteger wS() {
         return this.A.subtract(this.pC);
      }

      @Override
      public int hashCode() {
         int var1 = 1;
         var1 = 31 * var1 + (this.pC == null ? 0 : this.pC.hashCode());
         return 31 * var1 + (this.A == null ? 0 : this.A.hashCode());
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
            anm.Av var2 = (anm.Av)var1;
            if (this.pC == null) {
               if (var2.pC != null) {
                  return false;
               }
            } else if (!this.pC.equals(var2.pC)) {
               return false;
            }

            if (this.A == null) {
               if (var2.A != null) {
                  return false;
               }
            } else if (!this.A.equals(var2.A)) {
               return false;
            }

            return true;
         }
      }

      @Override
      public String toString() {
         return this.pC(false, null, null);
      }

      public String pC(boolean var1, BigInteger var2, BigInteger var3) {
         if (this.pC.equals(var2) && this.A.equals(var3)) {
            return "ALL";
         } else if (this.pC.add(BigInteger.ONE).equals(this.A)) {
            return Strings.ff("{%s}", this.pC);
         } else if (this.pC.equals(var2)) {
            return var1 ? Strings.ff("[MIN,%s]", this.A.subtract(BigInteger.ONE)) : Strings.ff("[MIN,%s[", this.A);
         } else if (this.A.equals(var3)) {
            return var1 ? Strings.ff("[%s,MAX]", this.pC) : Strings.ff("[%s,END[", this.pC);
         } else {
            return var1 ? Strings.ff("[%s,%s]", this.pC, this.A.subtract(BigInteger.ONE)) : Strings.ff("[%s,%s[", this.pC, this.A);
         }
      }
   }
}
