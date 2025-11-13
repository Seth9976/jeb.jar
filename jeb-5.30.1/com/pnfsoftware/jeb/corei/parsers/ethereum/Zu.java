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

public class Zu {
   private static final ILogger LK = GlobalLog.getLogger(Zu.class, Integer.MAX_VALUE);
   private static final int io = 500000;
   eM q;
   int RF;
   Set xK = new HashSet();
   List Dw = new ArrayList();
   List Uv;
   Map oW = new TreeMap();
   Map gO = new TreeMap();
   Map nf = new TreeMap();
   Map gP = new TreeMap();
   Map za = new TreeMap();
   int lm;
   List zz = new ArrayList();
   boolean JY;
   Set HF = new HashSet();

   public Zu(eM var1, int var2) {
      this.q = var1;
      this.RF = var2;
   }

   public boolean q() {
      try {
         Object[] var10000 = new Object[]{this.q.Uv(this.RF)};
         int var1 = this.RF == 0 ? 0 : 10;
         Zu.oM var2 = new Zu.oM(var1);
         this.q(this.RF, var2, 0);

         for (com.pnfsoftware.jeb.corei.parsers.ethereum.eo var4 : this.q.oW.values()) {
            if (var4.Dw != null && this.oW.containsKey(var4.Dw) && !this.gO.containsKey(var4.q) && !this.za.containsKey(var4.q)) {
               var10000 = new Object[]{var4};
               this.gO.put(var4.q, new Zu.eo(var4.q, var4.Dw, null));
            }
         }

         var10000 = new Object[]{this.oW.values()};
         var10000 = new Object[]{this.gO.values()};
         var10000 = new Object[]{this.nf.values()};
         var10000 = new Object[]{this.gP.values()};
         var10000 = new Object[]{this.za.values()};
         var10000 = new Object[]{this.zz.size()};
         this.RF();
         if (this.JY) {
            throw new RuntimeException("Exceeded recursion limits, cannot slice further");
         } else {
            return true;
         }
      } catch (Exception var5) {
         JebCoreService.notifySilentExceptionToClient(var5);
         return false;
      }
   }

   private void q(int var1, Zu.oM var2, int var3) {
      if (this.lm > 500000) {
         this.JY = true;
      }

      if (!this.JY) {
         com.pnfsoftware.jeb.corei.parsers.ethereum.eo var4 = this.q.Uv(var1);
         if (var4 != null) {
            if (this.xK.add(var4.q)) {
               this.Dw.add(var4.q);
               this.lm++;
               ArrayList var5 = new ArrayList(this.q.q(var4));
               Zu.ej var6 = null;

               for (int var7 = 0; var7 < var5.size(); var7++) {
                  vX var8 = (vX)var5.get(var7);
                  int var9 = var8.q();
                  if (var8.RF() != null) {
                     var2.RF(var8.RF());
                  } else if (var8.xK() != null) {
                     var2.xK(var8.xK());
                  } else if (var8.lm != null) {
                     var2.q(var8.lm);
                  } else if (var9 == 86) {
                     var6 = var2.RF();
                  } else if (var9 == 87) {
                     var6 = var2.RF();
                     var2.RF();
                  } else {
                     int var10 = var8.nf.Dw;
                     int var11 = var8.nf.Uv;
                     var2.q(var10, var11);
                  }
               }

               Integer var13 = null;
               Integer var14 = var4.Dw;
               Integer var15 = var4.Uv;
               if (var4.q()) {
                  if (var6 == null) {
                     throw new RuntimeException("Unexpected");
                  }

                  this.q(var3, "Unresolved block (maybe a return) branches to taint-trace: %s", var2.RF(var6));
                  Integer var16 = var2.q(var6);
                  int var12 = this.q(var2, var6);
                  if (var12 == 0) {
                     this.q(this.Dw);
                     if (var16 != null) {
                        var14 = var16;
                        var4.q(this.Dw, var16);
                     }
                  } else if (var12 == 2) {
                     this.xK.clear();
                  }
               }

               if (var14 != null && !this.Dw.contains(var14)) {
                  Zu.CU var17 = (Zu.CU)this.oW.get(var14);
                  if (var17 != null) {
                     Zu.ej var19 = var2.q(var17.RF);
                     var13 = var2.q(var19);
                     this.q("--- Passing over routine: %s -> returning to %s", var17, var13 == null ? "null" : "0x" + Integer.toHexString(var13));
                     var2.q(var17.RF + 1, var17.xK);
                     this.q(new Zu.eo(var4.q, var17.q, var13), false);
                     if (var13 != null) {
                        var4.RF(this.Dw, var13);
                     }
                  } else {
                     this.q(var14, new Zu.oM(var2), var3 + 1);
                     var17 = (Zu.CU)this.oW.get(var14);
                     if (var17 != null) {
                        Zu.ej var20 = var2.q(var17.RF);
                        var13 = var2.q(var20);
                        this.q("--- Passing over routine: %s -> returning to %s", var17, var13 == null ? "null" : "0x" + Integer.toHexString(var13));
                        var2.q(var17.RF + 1, var17.xK);
                     }
                  }

                  if (var13 != null && !this.Dw.contains(var13) && !var13.equals(var14)) {
                     this.q(var13, new Zu.oM(var2), var3 + 1);
                  }
               }

               if (var15 != null && !this.Dw.contains(var15) && (var14 == null || !var14.equals(var15)) && (var13 == null || !var13.equals(var15))) {
                  this.q(var15, new Zu.oM(var2), var3 + 1);
               }

               this.Dw.remove(this.Dw.size() - 1);
            }
         }
      }
   }

   private void q(List var1) {
      this.zz.add(new ArrayList(var1));
   }

   private void RF() {
      for (List var2 : this.zz) {
         Object[] var10000 = new Object[]{q((Collection)var2)};
      }
   }

   private static String q(Collection var0) {
      return Integers.formatIntegerCollection(var0, 16, "0x", null);
   }

   private int q(Zu.oM var1, Zu.ej var2) {
      if (var2.xK == null) {
         return 0;
      } else {
         int var3 = var2.xK;
         ArrayList var4 = new ArrayList();
         ArrayList var5 = new ArrayList();
         var4.add(var2);
         var5.add(var1);

         for (Zu.oM var6 = var1.q; var6 != null; var6 = var6.q) {
            Zu.ej var7 = (Zu.ej)var6.xK.get(var6.q() - 1 - var3);
            var4.add(0, var7);
            var5.add(0, var6);
            if (var7.xK == null) {
               break;
            }

            var3 = var7.xK;
         }

         if (((Zu.ej)var4.get(0)).RF == null) {
            return 0;
         } else {
            int var20 = ((Zu.ej)var4.get(0)).RF.intValue();
            com.pnfsoftware.jeb.corei.parsers.ethereum.eo var8 = this.q.Uv((Integer)this.Dw.get(this.Dw.size() - 1));
            int var9 = var4.size();
            int var10 = this.Dw.size() - var9;
            boolean var11 = false;
            boolean var12 = false;
            int var13 = var10;

            int var14;
            for (var14 = 0; var13 < this.Dw.size() - 1; var14++) {
               com.pnfsoftware.jeb.corei.parsers.ethereum.eo var15 = this.q.Uv((Integer)this.Dw.get(var13));
               if (var15.RF + 1 == var20) {
                  var11 = true;
                  break;
               }

               var13++;
            }

            if (var13 >= this.Dw.size() - 1) {
               var13 = var10;
               var14 = 0;
            }

            com.pnfsoftware.jeb.corei.parsers.ethereum.eo var21 = this.q.Uv((Integer)this.Dw.get(var13));
            if (var21.Uv == null) {
               if (var21.Dw == null) {
                  this.q("*** The candidate dispatch does not have a target offset! Unlikely to be a dispatch block...");
               } else {
                  vX var16 = this.q.Dw(var21.Dw);
                  if (var16 == null || var16.gO()) {
                     Zu.CU var17 = new Zu.CU(var21.Dw);
                     var17.RF = ((Zu.ej)var4.get(var14 + 1)).xK;
                     var17.xK = var1.q() - (((Zu.oM)var5.get(var14)).q() - var17.RF - 1);
                     Zu.eo var18 = new Zu.eo(var21.q);
                     var18.RF = var17.q;
                     var18.xK = var20;
                     this.q("*** Routine %s! %s", var11 ? "DETECTED" : "TENTATIVE ", var17);
                     this.q("*** Return-address-pushed block: %s", this.q.Uv((Integer)this.Dw.get(var10)));
                     this.q("*** Dispatch block: %s", var18);
                     if (var11) {
                        if (this.q(var17, false)) {
                           var12 = true;
                        }

                        this.q(var18, false);
                        var21.RF(this.Dw, var20);
                     } else {
                        this.q(var17, true);
                        this.q(var18, true);
                     }

                     Zu.nI var19 = this.q(var8.q);
                     var19.RF.add(var20);
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

   private Zu.nI q(int var1) {
      Zu.nI var2 = (Zu.nI)this.nf.get(var1);
      if (var2 == null) {
         var2 = new Zu.nI(var1);
         this.nf.put(var1, var2);
      }

      return var2;
   }

   private boolean q(Zu.CU var1, boolean var2) {
      Zu.CU var3 = (Zu.CU)this.oW.get(var1.q);
      boolean var4 = var3 == null;
      if (!var2) {
         if (var3 == null) {
            this.oW.put(var1.q, var1);
         } else if (!var3.q(var1)) {
            this.q("CF Discrepancy: 2 candidates differ");
         }

         this.gP.remove(var1.q);
      } else if (var3 == null) {
         var3 = (Zu.CU)this.gP.get(var1.q);
         if (var3 == null) {
            this.gP.put(var1.q, var1);
         } else if (!var3.q(var1)) {
            this.q("CF Discrepancy: 2 tentative-candidates differ");
         }
      }

      return var4;
   }

   private void q(Zu.eo var1, boolean var2) {
      if (!var2) {
         Zu.eo var3 = (Zu.eo)this.gO.get(var1.q);
         if (var3 == null) {
            this.gO.put(var1.q, var1);
         } else if (!var3.q(var1)) {
            this.q("CD Discrepancy: 2 candidates differ");
         }

         this.za.remove(var1.q);
      } else {
         Zu.eo var4 = (Zu.eo)this.gO.get(var1.q);
         if (var4 == null) {
            var4 = (Zu.eo)this.za.get(var1.q);
            if (var4 == null) {
               this.za.put(var1.q, var1);
            } else if (!var4.q(var1)) {
               this.q("CD Discrepancy: 2 tentative-candidates differ");
            }
         }
      }
   }

   private void q(int var1, String var2, Object... var3) {
      Object[] var10000 = new Object[]{Strings.spaces(2 * var1), Strings.ff(var2, var3)};
   }

   private void q(String var1, Object... var2) {
      this.q(this.Dw.size(), var1, var2);
   }

   static class CU {
      int q;
      int RF;
      int xK;

      CU(int var1) {
         this.q = var1;
      }

      CU(int var1, int var2, int var3) {
         this.q = var1;
         this.RF = var2;
         this.xK = var3;
      }

      boolean q(Zu.CU var1) {
         return var1.RF == this.RF && var1.xK == this.xK;
      }

      @Override
      public String toString() {
         return Strings.ff("0x%X:in=%d,out=%d", this.q, this.RF, this.xK);
      }
   }

   static class ej {
      static Zu.ej q = new Zu.ej();
      private BigInteger RF;
      private Integer xK;

      private ej() {
      }

      ej(BigInteger var1) {
         if (var1 == null) {
            throw new IllegalArgumentException();
         } else {
            this.RF = var1;
         }
      }

      ej(int var1) {
         this.xK = var1;
      }

      public Zu.ej q() {
         if (this == q) {
            return q;
         } else {
            Zu.ej var1 = new Zu.ej();
            var1.RF = this.RF;
            var1.xK = this.xK;
            return var1;
         }
      }

      boolean RF() {
         return this.RF == null && this.xK == null;
      }

      public BigInteger xK() {
         return this.RF;
      }

      public Integer Dw() {
         return this.xK;
      }

      @Override
      public String toString() {
         if (this.RF != null) {
            return "0x" + this.RF.toString(16);
         } else {
            return this.xK != null ? "#" + this.xK : "?";
         }
      }
   }

   static class eo {
      int q;
      int RF;
      Integer xK;

      eo(int var1) {
         this.q = var1;
      }

      eo(int var1, int var2, Integer var3) {
         this.q = var1;
         this.RF = var2;
         this.xK = var3;
      }

      boolean q(Zu.eo var1) {
         return var1.RF == this.RF && (var1.xK == null && this.xK == null || var1.xK != null && var1.xK.equals(this.xK));
      }

      @Override
      public String toString() {
         return Strings.ff("0x%X:target=0x%X,return=%s", this.q, this.RF, this.xK == null ? "???" : "0x" + Integer.toHexString(this.xK));
      }
   }

   static class nI {
      int q;
      Set RF = new HashSet();

      nI(int var1) {
         this.q = var1;
      }

      @Override
      public String toString() {
         return Strings.ff("0x%X:potential_returns=%s", this.q, Zu.q(this.RF));
      }
   }

   static class oM {
      Zu.oM q;
      int RF;
      List xK;

      oM(int var1) {
         if (var1 < 0) {
            throw new IllegalArgumentException();
         } else {
            this.RF = var1;
            this.xK = new ArrayList(this.RF);

            for (int var2 = this.RF - 1; var2 >= 0; var2--) {
               this.xK.add(new Zu.ej(var2));
            }
         }
      }

      oM(Zu.oM var1) {
         if (var1 == null) {
            throw new IllegalArgumentException();
         } else {
            this.q = var1;
            this.RF = var1.q();
            this.xK = new ArrayList(this.RF);

            for (int var2 = this.RF - 1; var2 >= 0; var2--) {
               this.xK.add(new Zu.ej(var2));
            }
         }
      }

      int q() {
         return this.xK.size();
      }

      Zu.ej q(BigInteger var1) {
         Zu.ej var2 = new Zu.ej(var1);
         this.xK.add(var2);
         return var2;
      }

      Zu.ej RF() {
         return (Zu.ej)this.xK.remove(this.xK.size() - 1);
      }

      Zu.ej q(int var1) {
         return (Zu.ej)this.xK.get(this.xK.size() - 1 - var1);
      }

      Zu.ej xK() {
         return this.q(0);
      }

      void RF(int var1) {
         int var2 = this.xK.size() - 1;
         if (var2 < 0) {
            throw new RuntimeException();
         } else {
            int var3 = this.xK.size() - 1 - var1;
            if (var3 < 0) {
               throw new RuntimeException();
            } else {
               Zu.ej var4 = (Zu.ej)this.xK.get(var2);
               Zu.ej var5 = (Zu.ej)this.xK.get(var3);
               this.xK.set(var2, var5);
               this.xK.set(var3, var4);
            }
         }
      }

      void xK(int var1) {
         int var2 = this.xK.size() - var1;
         if (var2 < 0) {
            this.xK.add(new Zu.ej(0));
         } else {
            Zu.ej var3 = (Zu.ej)this.xK.get(var2);
            this.xK.add(var3.q());
         }
      }

      void q(int var1, int var2) {
         if (var1 > 0) {
            while (var1-- != 0) {
               this.xK.remove(this.xK.size() - 1);
            }
         }

         if (var2 > 0) {
            while (var2-- != 0) {
               this.xK.add(Zu.ej.q);
            }
         }
      }

      int Dw() {
         return this.xK.size() - this.RF;
      }

      Integer q(Zu.ej var1) {
         if (var1.xK != null) {
            int var2 = var1.xK;

            for (Zu.oM var3 = this.q; var3 != null; var3 = var3.q) {
               var1 = (Zu.ej)var3.xK.get(var3.q() - 1 - var2);
               if (var1.xK == null) {
                  break;
               }

               var2 = var1.xK;
            }
         }

         return var1.RF == null ? null : var1.RF.intValue();
      }

      String RF(Zu.ej var1) {
         if (var1.xK == null) {
            return var1.toString();
         } else {
            StringBuilder var2 = new StringBuilder();
            int var3 = var1.xK;
            var2.append(var1);

            for (Zu.oM var4 = this.q; var4 != null; var4 = var4.q) {
               Zu.ej var5 = (Zu.ej)var4.xK.get(var4.q() - 1 - var3);
               var2.append(":").append(var5);
               if (var5.xK == null) {
                  break;
               }

               var3 = var5.xK;
            }

            return var2.toString();
         }
      }

      public String q(List var1) {
         StringBuilder var2 = new StringBuilder();
         Zu.oM var3 = this;

         for (int var4 = var1.size() - 1; var3 != null; var4--) {
            Strings.ff(var2, "- %04X: %s\n", var1.get(var4), var3);
            var3 = var3.q;
         }

         return var2.toString();
      }

      @Override
      public String toString() {
         if (this.xK.isEmpty()) {
            return "(empty)";
         } else {
            StringBuilder var1 = new StringBuilder();
            int var2 = 0;

            for (Zu.ej var4 : this.xK) {
               Strings.ff(var1, "%d:%-5s ", var2, var4);
               var2++;
            }

            return var1.toString();
         }
      }
   }
}
