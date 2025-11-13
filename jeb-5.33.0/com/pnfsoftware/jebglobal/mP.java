package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.units.code.asm.analyzer.INativeCodeAnalyzer;
import com.pnfsoftware.jeb.core.units.code.asm.processor.arch.RegisterUtil;
import com.pnfsoftware.jeb.util.base.Couple;
import com.pnfsoftware.jeb.util.collect.Sets;
import com.pnfsoftware.jeb.util.format.CharSequences;
import com.pnfsoftware.jeb.util.format.Strings;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;
import java.util.Map.Entry;

class mP {
   private Map pC = new HashMap();
   private List A = new ArrayList();

   public boolean pC() {
      return this.pC.isEmpty();
   }

   public boolean pC(Yg var1) {
      long var2 = UT(var1);
      return this.pC.containsKey(var2);
   }

   public Set A(Yg var1) {
      if (var1 == null) {
         return null;
      } else if (var1.getOperandType() != 0) {
         return null;
      } else {
         long var2 = UT(var1);
         return this.pC(var2);
      }
   }

   public Set pC(long var1) {
      mP.Ws var3 = (mP.Ws)this.pC.get(var1);
      if (var3 == null) {
         return null;
      } else {
         var3.wS = true;
         return var3.kS();
      }
   }

   public Set kS(Yg var1) {
      long var2 = UT(var1);
      mP.Ws var4 = (mP.Ws)this.pC.get(var2);
      return var4 == null ? null : var4.kS();
   }

   public List pC(Yg[] var1) {
      ArrayList var2 = new ArrayList();

      for (int var3 = 0; var3 < var1.length; var3++) {
         if (var1[var3].getOperandType() == 7) {
            var2.addAll(this.pC(var1[var3].E()));
         } else if (var1[var3].getOperandType() == 0) {
            List var4 = this.wS(var1[var3]);
            if (var4 != null) {
               var2.addAll(var4);
            }
         }
      }

      return var2;
   }

   public List wS(Yg var1) {
      if (var1.getOperandType() == 7) {
         return this.pC(var1.E());
      } else {
         long var2 = UT(var1);
         return this.A(var2);
      }
   }

   public List A(long var1) {
      mP.Ws var3 = (mP.Ws)this.pC.remove(var1);
      return var3 != null && var3.pC() ? Arrays.asList(new Couple(var3.kS, var3.kS())) : null;
   }

   public List pC(INativeCodeAnalyzer var1) {
      ArrayList var2 = new ArrayList();

      for (Entry var4 : this.pC.entrySet()) {
         mP.Ws var5 = (mP.Ws)var4.getValue();
         if (var5.pC()) {
            var2.add(new Couple(var5.kS, var5.kS()));
         }
      }

      this.pC.clear();

      for (mP.Ws var7 : this.A) {
         if (var7.pC()) {
            var2.add(new Couple(var7.kS, var7.kS()));
         }
      }

      this.A.clear();
      return var2;
   }

   public void pC(NC var1, Yg var2, long var3, long var5, mP.Av var7) {
      long var8 = UT(var2);
      if (var8 != -1L) {
         mP.Ws var10 = (mP.Ws)this.pC.get(var8);
         if (CharSequences.isBlank(var1.wS())) {
            if (var10 != null) {
               this.A.add(var10);
            }

            this.pC.put(var8, new mP.Ws(var3, var5, var7));
         } else if (var10 == null) {
            this.pC.put(var8, new mP.Ws(var3, var5, var7));
         } else {
            var10.pC(var1.wS(), var3);
         }
      }
   }

   public void pC(NC var1, Yg var2, List var3, long var4, mP.Av var6) {
      long var7 = UT(var2);
      if (var7 != -1L) {
         if (CharSequences.isBlank(var1.wS())) {
            this.pC.put(var7, new mP.Ws(var3, var4, var6));
         } else {
            mP.Ws var9 = (mP.Ws)this.pC.get(var7);
            if (var9 == null) {
               this.pC.put(var7, new mP.Ws(var3, var4, var6));
            } else {
               var9.pC(var1.wS(), var3);
            }
         }
      }
   }

   public Set pC(Yg var1, long var2, int var4) {
      switch (var1.getOperandType()) {
         case 0:
            if (!this.E(var1)) {
               return null;
            } else {
               if (var1.A(var4)) {
                  return Sets.newHashSet(var1.pC(var2, var4, null));
               }

               return this.kS(var1);
            }
         case 1:
            return Sets.newHashSet(var1.pC(var2, var4, null));
         default:
            return null;
      }
   }

   public boolean pC(NC var1, Yg var2, long var3, Yg var5, Yg var6, int var7) {
      long var8 = UT(var2);
      if (var8 == -1L) {
         return false;
      } else {
         mP.Sv var10 = new mP.Sv(var5, var3, var7, var1.wS());
         if (!var10.A()) {
            return false;
         } else if (var1.pC().equals("MOV")) {
            var10.pC();
            this.pC(var1, var2, var10.wS, var5.isRegister() ? var10.kS.kS : var3, var10.pC);
            return true;
         } else {
            mP.Sv var11 = new mP.Sv(var6, var3, var7, var1.wS());
            if (var11.wS == null) {
               Set var12 = this.pC(var6, var3, var7);
               if (var12 == null) {
                  return false;
               }

               var11.wS = new ArrayList(var12);
            }

            if (!var11.A()) {
               return false;
            } else {
               boolean var19 = false;
               ArrayList var13 = new ArrayList();
               if (var11.wS.size() > var10.wS.size()) {
                  mP.Sv var14 = var11;
                  var11 = var10;
                  var10 = var14;
               }

               for (int var20 = 0; var20 < var10.wS.size(); var20++) {
                  Long var15 = (Long)var10.wS.get(var20);
                  Long var16 = var20 < var11.wS.size() ? (Long)var11.wS.get(var20) : (Long)var11.wS.get(0);
                  String var17 = var1.pC();
                  switch (var17) {
                     case "ADD":
                        var13.add(var15 + var16);
                        var19 = true;
                        break;
                     case "SUB":
                        var13.add(var15 - var16);
                        var19 = true;
                        break;
                     case "ORR":
                        var13.add(var15 | var16);
                  }
               }

               if (!var13.isEmpty()) {
                  var10.pC();
                  var11.pC();
               }

               if (!CharSequences.isBlank(var1.wS())) {
                  mP.Ws var21 = (mP.Ws)this.pC.get(var8);
                  if (var21 != null) {
                     var21.wS = false;
                  }
               }

               if (!var13.isEmpty()) {
                  mP.Av var22 = this.pC(var10.pC, var11.pC, var19);
                  this.pC(var1, var2, var13, var3, var22);
                  return true;
               } else {
                  return false;
               }
            }
         }
      }
   }

   private mP.Av pC(mP.Av var1, mP.Av var2, boolean var3) {
      if (var1 == mP.Av.pC || var2 == mP.Av.pC) {
         return mP.Av.pC;
      } else if (var1 != mP.Av.A && var2 != mP.Av.A) {
         return mP.Av.wS;
      } else {
         return var3 ? mP.Av.pC : mP.Av.A;
      }
   }

   public void A() {
      for (mP.Ws var2 : this.pC.values()) {
         var2.A();
      }
   }

   private boolean E(Yg var1) {
      return UT(var1) != -1L;
   }

   public static long UT(Yg var0) {
      if (var0.getOperandType() != 0) {
         return -1L;
      } else {
         return var0 instanceof lw ? -1L : RegisterUtil.getPureId(var0.getOperandValue());
      }
   }

   private mP.Ws sY(Yg var1) {
      long var2 = UT(var1);
      return var2 == -1L ? null : (mP.Ws)this.pC.get(var2);
   }

   public Long kS(long var1) {
      mP.Ws var3 = (mP.Ws)this.pC.get(var1);
      return var3 == null ? null : (var3.UT == mP.Av.kS ? var3.kS : null);
   }

   public boolean pC(Yg var1, int var2) {
      if (var2 != 64 && var1.A(var2)) {
         return true;
      } else {
         mP.Ws var3 = this.sY(var1);
         return var3 != null && (var3.UT == mP.Av.pC || var3.UT == mP.Av.A);
      }
   }

   static enum Av {
      pC,
      A,
      kS,
      wS;
   }

   private static class K {
      private Set pC = new HashSet();
      private Map A = new HashMap();

      @Override
      public String toString() {
         StringBuilder var1 = new StringBuilder();
         if (this.pC.size() > 1) {
            var1.append("{");
         }

         var1.append(Strings.join(",", this.pC, Long::toHexString));
         if (!this.A.isEmpty()) {
            var1.append("{?");
            this.A.forEach((var1x, var2) -> var1.append(var1x).append(":{").append(Strings.join(",", var2, Long::toHexString)).append("}"));
            var1.append("}");
         }

         if (this.pC.size() > 1) {
            var1.append("}");
         }

         return var1.toString();
      }
   }

   private class Sv {
      private final mP.Ws kS;
      mP.Av pC = mP.Av.wS;
      private List wS;

      public Sv(Yg var2, long var3, int var5, String var6) {
         this.kS = mP.this.sY(var2);
         this.pC = mP.Av.wS;
         if (this.kS != null) {
            this.wS = this.kS.pC(var6);
            this.pC = this.kS.UT;
         }
      }

      public void pC() {
         if (this.kS != null) {
            this.kS.wS = true;
         }
      }

      public boolean A() {
         return this.wS != null && !this.wS.isEmpty();
      }
   }

   private static class Ws {
      private long pC;
      private mP.K A = null;
      private long kS;
      private boolean wS = false;
      private mP.Av UT = mP.Av.wS;

      public Ws(long var1, long var3, mP.Av var5) {
         this.pC = var1;
         this.kS = var3;
         this.UT = var5;
      }

      public Ws(List var1, long var2, mP.Av var4) {
         this.kS = var2;
         this.UT = var4;
         this.A = new mP.K();
         this.A.pC.addAll(var1);
      }

      @Override
      public String toString() {
         return "RegisterValue [@"
            + Long.toHexString(this.kS)
            + "h:"
            + Long.toHexString(this.pC)
            + "h="
            + (this.A == null ? "null" : this.A.toString())
            + ", "
            + (this.wS ? "" : "not ")
            + "analyzed, "
            + this.UT
            + "]";
      }

      public boolean pC() {
         return !this.wS && this.UT == mP.Av.pC;
      }

      public void pC(String var1, long var2) {
         if (this.A == null) {
            this.A = new mP.K();
            this.A.pC.add(this.pC);
         }

         Object var4 = (Set)this.A.A.get(var1);
         if (var4 == null) {
            var4 = new HashSet();
            this.A.A.put(var1, var4);
         }

         var4.add(var2);
      }

      public void pC(String var1, List var2) {
         if (this.A == null) {
            this.A = new mP.K();
            this.A.pC.add(this.pC);
         }

         this.A.A.put(var1, new HashSet(var2));
      }

      public void A() {
         if (this.A != null) {
            for (Set var2 : this.A.A.values()) {
               this.A.pC.addAll(var2);
            }

            this.A.A.clear();
         }
      }

      public List pC(String var1) {
         if (this.A == null) {
            return Arrays.asList(this.pC);
         } else {
            ArrayList var2 = new ArrayList();
            Set var3 = (Set)this.A.A.get(var1);
            if (var3 != null && !var3.isEmpty()) {
               var2.addAll(var3);
            } else {
               var2.addAll(this.A.pC);
            }

            Collections.sort(var2);
            return var2;
         }
      }

      public Set kS() {
         if (this.A == null) {
            return Sets.newHashSet(this.pC);
         } else {
            TreeSet var1 = new TreeSet();
            var1.addAll(this.A.pC);

            for (Set var3 : this.A.A.values()) {
               var1.addAll(var3);
            }

            return var1;
         }
      }
   }
}
