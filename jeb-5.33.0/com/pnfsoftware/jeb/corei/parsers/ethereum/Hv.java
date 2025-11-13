package com.pnfsoftware.jeb.corei.parsers.ethereum;

import com.pnfsoftware.jeb.core.JebCoreService;
import com.pnfsoftware.jeb.util.format.Strings;
import com.pnfsoftware.jeb.util.logging.GlobalLog;
import com.pnfsoftware.jeb.util.logging.ILogger;
import com.pnfsoftware.jeb.util.primitives.Integers;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

public class Hv {
   private static final ILogger NS = GlobalLog.getLogger(Hv.class, Integer.MAX_VALUE);
   ma pC;
   int A;
   Set kS = new HashSet();
   List wS = new ArrayList();
   Map UT = new TreeMap();
   Map E = new TreeMap();
   Map sY = new TreeMap();
   Map ys = new TreeMap();
   Map ld = new TreeMap();
   int gp;
   List oT = new ArrayList();
   boolean fI;
   Set WR = new HashSet();

   public Hv(ma var1, int var2) {
      this.pC = var1;
      this.A = var2;
   }

   public boolean pC() {
      try {
         Object[] var10000 = new Object[]{this.pC.A(this.A)};
         int var1 = this.A == 0 ? 0 : 10;
         Hv.bO var2 = new Hv.bO(var1);
         this.pC(this.A, var2, 0);

         for (com.pnfsoftware.jeb.corei.parsers.ethereum.Av var4 : this.pC.E.values()) {
            if (var4.wS != null && this.UT.containsKey(var4.wS) && !this.E.containsKey(var4.pC) && !this.ld.containsKey(var4.pC)) {
               var10000 = new Object[]{var4};
               this.E.put(var4.pC, new Hv.Av(var4.pC, var4.wS, null));
            }
         }

         var10000 = new Object[]{this.UT.values()};
         var10000 = new Object[]{this.E.values()};
         var10000 = new Object[]{this.sY.values()};
         var10000 = new Object[]{this.ys.values()};
         var10000 = new Object[]{this.ld.values()};
         var10000 = new Object[]{this.oT.size()};
         this.A();
         if (this.fI) {
            throw new RuntimeException("Exceeded recursion limits, cannot slice further");
         } else {
            return true;
         }
      } catch (Exception var5) {
         JebCoreService.notifySilentExceptionToClient(var5);
         return false;
      }
   }

   private void pC(int var1, Hv.bO var2, int var3) {
      if (this.gp > 500000) {
         this.fI = true;
      }

      if (!this.fI) {
         com.pnfsoftware.jeb.corei.parsers.ethereum.Av var4 = this.pC.A(var1);
         if (var4 != null) {
            if (this.kS.add(var4.pC)) {
               this.wS.add(var4.pC);
               this.gp++;
               ArrayList var5 = new ArrayList(this.pC.pC(var4));
               Hv.Ws var6 = null;

               for (int var7 = 0; var7 < var5.size(); var7++) {
                  Mh var8 = (Mh)var5.get(var7);
                  int var9 = var8.pC();
                  if (var8.A() != null) {
                     var2.A(var8.A());
                  } else if (var8.kS() != null) {
                     var2.kS(var8.kS());
                  } else if (var8.wS != null) {
                     var2.pC(var8.wS);
                  } else if (var9 == 86) {
                     var6 = var2.A();
                  } else if (var9 == 87) {
                     var6 = var2.A();
                     var2.A();
                  } else {
                     int var10 = var8.pC.wS;
                     int var11 = var8.pC.UT;
                     var2.pC(var10, var11);
                  }
               }

               Integer var13 = null;
               Integer var14 = var4.wS;
               Integer var15 = var4.UT;
               if (var4.pC()) {
                  if (var6 == null) {
                     throw new RuntimeException("Unexpected");
                  }

                  this.pC(var3, "Unresolved block (maybe a return) branches to taint-trace: %s", var2.A(var6));
                  Integer var16 = var2.pC(var6);
                  int var12 = this.pC(var2, var6);
                  if (var12 == 0) {
                     this.pC(this.wS);
                     if (var16 != null) {
                        var14 = var16;
                        var4.pC(this.wS, var16);
                     }
                  } else if (var12 == 2) {
                     this.kS.clear();
                  }
               }

               if (var14 != null && !this.wS.contains(var14)) {
                  Hv.Sv var17 = (Hv.Sv)this.UT.get(var14);
                  if (var17 != null) {
                     Hv.Ws var19 = var2.pC(var17.A);
                     var13 = var2.pC(var19);
                     this.pC("--- Passing over routine: %s -> returning to %s", var17, var13 == null ? "null" : "0x" + Integer.toHexString(var13));
                     var2.pC(var17.A + 1, var17.kS);
                     this.pC(new Hv.Av(var4.pC, var17.pC, var13), false);
                     if (var13 != null) {
                        var4.A(this.wS, var13);
                     }
                  } else {
                     this.pC(var14, new Hv.bO(var2), var3 + 1);
                     var17 = (Hv.Sv)this.UT.get(var14);
                     if (var17 != null) {
                        Hv.Ws var20 = var2.pC(var17.A);
                        var13 = var2.pC(var20);
                        this.pC("--- Passing over routine: %s -> returning to %s", var17, var13 == null ? "null" : "0x" + Integer.toHexString(var13));
                        var2.pC(var17.A + 1, var17.kS);
                     }
                  }

                  if (var13 != null && !this.wS.contains(var13) && !var13.equals(var14)) {
                     this.pC(var13, new Hv.bO(var2), var3 + 1);
                  }
               }

               if (var15 != null && !this.wS.contains(var15) && (var14 == null || !var14.equals(var15)) && (var13 == null || !var13.equals(var15))) {
                  this.pC(var15, new Hv.bO(var2), var3 + 1);
               }

               this.wS.remove(this.wS.size() - 1);
            }
         }
      }
   }

   private void pC(List var1) {
      this.oT.add(new ArrayList(var1));
   }

   private void A() {
      for (List var2 : this.oT) {
         Object[] var10000 = new Object[]{pC((Collection)var2)};
      }
   }

   private static String pC(Collection var0) {
      return Integers.formatIntegerCollection(var0, 16, "0x", null);
   }

   private int pC(Hv.bO var1, Hv.Ws var2) {
      if (var2.kS == null) {
         return 0;
      } else {
         int var3 = var2.kS;
         ArrayList var4 = new ArrayList();
         ArrayList var5 = new ArrayList();
         var4.add(var2);
         var5.add(var1);

         for (Hv.bO var6 = var1.pC; var6 != null; var6 = var6.pC) {
            Hv.Ws var7 = (Hv.Ws)var6.kS.get(var6.pC() - 1 - var3);
            var4.add(0, var7);
            var5.add(0, var6);
            if (var7.kS == null) {
               break;
            }

            var3 = var7.kS;
         }

         if (((Hv.Ws)var4.get(0)).A == null) {
            return 0;
         } else {
            int var20 = ((Hv.Ws)var4.get(0)).A.intValue();
            com.pnfsoftware.jeb.corei.parsers.ethereum.Av var8 = this.pC.A((Integer)this.wS.get(this.wS.size() - 1));
            int var9 = var4.size();
            int var10 = this.wS.size() - var9;
            boolean var11 = false;
            boolean var12 = false;
            int var13 = var10;

            int var14;
            for (var14 = 0; var13 < this.wS.size() - 1; var14++) {
               com.pnfsoftware.jeb.corei.parsers.ethereum.Av var15 = this.pC.A((Integer)this.wS.get(var13));
               if (var15.A + 1 == var20) {
                  var11 = true;
                  break;
               }

               var13++;
            }

            if (var13 >= this.wS.size() - 1) {
               var13 = var10;
               var14 = 0;
            }

            com.pnfsoftware.jeb.corei.parsers.ethereum.Av var21 = this.pC.A((Integer)this.wS.get(var13));
            if (var21.UT == null) {
               if (var21.wS == null) {
                  this.pC("*** The candidate dispatch does not have a target offset! Unlikely to be a dispatch block...");
               } else {
                  Mh var16 = this.pC.pC(var21.wS);
                  if (var16 == null || var16.sY()) {
                     Hv.Sv var17 = new Hv.Sv(var21.wS);
                     var17.A = ((Hv.Ws)var4.get(var14 + 1)).kS;
                     var17.kS = var1.pC() - (((Hv.bO)var5.get(var14)).pC() - var17.A - 1);
                     Hv.Av var18 = new Hv.Av(var21.pC);
                     var18.A = var17.pC;
                     var18.kS = var20;
                     this.pC("*** Routine %s! %s", var11 ? "DETECTED" : "TENTATIVE ", var17);
                     this.pC("*** Return-address-pushed block: %s", this.pC.A((Integer)this.wS.get(var10)));
                     this.pC("*** Dispatch block: %s", var18);
                     if (var11) {
                        if (this.pC(var17, false)) {
                           var12 = true;
                        }

                        this.pC(var18, false);
                        var21.A(this.wS, var20);
                     } else {
                        this.pC(var17, true);
                        this.pC(var18, true);
                     }

                     Hv.K var19 = this.pC(var8.pC);
                     var19.A.add(var20);
                  }
               }
            }

            if (!var11) {
               return 0;
            } else {
               return var12 ? 2 : 1;
            }
         }
      }
   }

   private Hv.K pC(int var1) {
      Hv.K var2 = (Hv.K)this.sY.get(var1);
      if (var2 == null) {
         var2 = new Hv.K(var1);
         this.sY.put(var1, var2);
      }

      return var2;
   }

   private boolean pC(Hv.Sv var1, boolean var2) {
      Hv.Sv var3 = (Hv.Sv)this.UT.get(var1.pC);
      boolean var4 = var3 == null;
      if (!var2) {
         if (var3 == null) {
            this.UT.put(var1.pC, var1);
         } else if (!var3.pC(var1)) {
            this.pC("CF Discrepancy: 2 candidates differ");
         }

         this.ys.remove(var1.pC);
      } else if (var3 == null) {
         var3 = (Hv.Sv)this.ys.get(var1.pC);
         if (var3 == null) {
            this.ys.put(var1.pC, var1);
         } else if (!var3.pC(var1)) {
            this.pC("CF Discrepancy: 2 tentative-candidates differ");
         }
      }

      return var4;
   }

   private void pC(Hv.Av var1, boolean var2) {
      if (!var2) {
         Hv.Av var3 = (Hv.Av)this.E.get(var1.pC);
         if (var3 == null) {
            this.E.put(var1.pC, var1);
         } else if (!var3.pC(var1)) {
            this.pC("CD Discrepancy: 2 candidates differ");
         }

         this.ld.remove(var1.pC);
      } else {
         Hv.Av var4 = (Hv.Av)this.E.get(var1.pC);
         if (var4 == null) {
            var4 = (Hv.Av)this.ld.get(var1.pC);
            if (var4 == null) {
               this.ld.put(var1.pC, var1);
            } else if (!var4.pC(var1)) {
               this.pC("CD Discrepancy: 2 tentative-candidates differ");
            }
         }
      }
   }

   private void pC(int var1, String var2, Object... var3) {
      Object[] var10000 = new Object[]{Strings.spaces(2 * var1), Strings.ff(var2, var3)};
   }

   private void pC(String var1, Object... var2) {
      this.pC(this.wS.size(), var1, var2);
   }

   static class Av {
      int pC;
      int A;
      Integer kS;

      Av(int var1) {
         this.pC = var1;
      }

      Av(int var1, int var2, Integer var3) {
         this.pC = var1;
         this.A = var2;
         this.kS = var3;
      }

      boolean pC(Hv.Av var1) {
         return var1.A == this.A && (var1.kS == null && this.kS == null || var1.kS != null && var1.kS.equals(this.kS));
      }

      @Override
      public String toString() {
         return Strings.ff("0x%X:target=0x%X,return=%s", this.pC, this.A, this.kS == null ? "???" : "0x" + Integer.toHexString(this.kS));
      }
   }

   static class K {
      int pC;
      Set A = new HashSet();

      K(int var1) {
         this.pC = var1;
      }

      @Override
      public String toString() {
         return Strings.ff("0x%X:potential_returns=%s", this.pC, Hv.pC(this.A));
      }
   }

   static class Sv {
      int pC;
      int A;
      int kS;

      Sv(int var1) {
         this.pC = var1;
      }

      boolean pC(Hv.Sv var1) {
         return var1.A == this.A && var1.kS == this.kS;
      }

      @Override
      public String toString() {
         return Strings.ff("0x%X:in=%d,out=%d", this.pC, this.A, this.kS);
      }
   }

   static class Ws {
      static Hv.Ws pC = new Hv.Ws();
      private BigInteger A;
      private Integer kS;

      private Ws() {
      }

      Ws(BigInteger var1) {
         if (var1 == null) {
            throw new IllegalArgumentException();
         } else {
            this.A = var1;
         }
      }

      Ws(int var1) {
         this.kS = var1;
      }

      public Hv.Ws pC() {
         if (this == pC) {
            return pC;
         } else {
            Hv.Ws var1 = new Hv.Ws();
            var1.A = this.A;
            var1.kS = this.kS;
            return var1;
         }
      }

      @Override
      public String toString() {
         if (this.A != null) {
            return "0x" + this.A.toString(16);
         } else {
            return this.kS != null ? "#" + this.kS : "?";
         }
      }
   }

   static class bO {
      Hv.bO pC;
      int A;
      List kS;

      bO(int var1) {
         if (var1 < 0) {
            throw new IllegalArgumentException();
         } else {
            this.A = var1;
            this.kS = new ArrayList(this.A);

            for (int var2 = this.A - 1; var2 >= 0; var2--) {
               this.kS.add(new Hv.Ws(var2));
            }
         }
      }

      bO(Hv.bO var1) {
         if (var1 == null) {
            throw new IllegalArgumentException();
         } else {
            this.pC = var1;
            this.A = var1.pC();
            this.kS = new ArrayList(this.A);

            for (int var2 = this.A - 1; var2 >= 0; var2--) {
               this.kS.add(new Hv.Ws(var2));
            }
         }
      }

      int pC() {
         return this.kS.size();
      }

      Hv.Ws pC(BigInteger var1) {
         Hv.Ws var2 = new Hv.Ws(var1);
         this.kS.add(var2);
         return var2;
      }

      Hv.Ws A() {
         return (Hv.Ws)this.kS.remove(this.kS.size() - 1);
      }

      Hv.Ws pC(int var1) {
         return (Hv.Ws)this.kS.get(this.kS.size() - 1 - var1);
      }

      void A(int var1) {
         int var2 = this.kS.size() - 1;
         if (var2 < 0) {
            throw new RuntimeException();
         } else {
            int var3 = this.kS.size() - 1 - var1;
            if (var3 < 0) {
               throw new RuntimeException();
            } else {
               Hv.Ws var4 = (Hv.Ws)this.kS.get(var2);
               Hv.Ws var5 = (Hv.Ws)this.kS.get(var3);
               this.kS.set(var2, var5);
               this.kS.set(var3, var4);
            }
         }
      }

      void kS(int var1) {
         int var2 = this.kS.size() - var1;
         if (var2 < 0) {
            this.kS.add(new Hv.Ws(0));
         } else {
            Hv.Ws var3 = (Hv.Ws)this.kS.get(var2);
            this.kS.add(var3.pC());
         }
      }

      void pC(int var1, int var2) {
         if (var1 > 0) {
            while (var1-- != 0) {
               this.kS.remove(this.kS.size() - 1);
            }
         }

         if (var2 > 0) {
            while (var2-- != 0) {
               this.kS.add(Hv.Ws.pC);
            }
         }
      }

      Integer pC(Hv.Ws var1) {
         if (var1.kS != null) {
            int var2 = var1.kS;

            for (Hv.bO var3 = this.pC; var3 != null; var3 = var3.pC) {
               var1 = (Hv.Ws)var3.kS.get(var3.pC() - 1 - var2);
               if (var1.kS == null) {
                  break;
               }

               var2 = var1.kS;
            }
         }

         return var1.A == null ? null : var1.A.intValue();
      }

      String A(Hv.Ws var1) {
         if (var1.kS == null) {
            return var1.toString();
         } else {
            StringBuilder var2 = new StringBuilder();
            int var3 = var1.kS;
            var2.append(var1);

            for (Hv.bO var4 = this.pC; var4 != null; var4 = var4.pC) {
               Hv.Ws var5 = (Hv.Ws)var4.kS.get(var4.pC() - 1 - var3);
               var2.append(":").append(var5);
               if (var5.kS == null) {
                  break;
               }

               var3 = var5.kS;
            }

            return var2.toString();
         }
      }

      @Override
      public String toString() {
         if (this.kS.isEmpty()) {
            return "(empty)";
         } else {
            StringBuilder var1 = new StringBuilder();
            int var2 = 0;

            for (Hv.Ws var4 : this.kS) {
               Strings.ff(var1, "%d:%-5s ", var2, var4);
               var2++;
            }

            return var1.toString();
         }
      }
   }
}
