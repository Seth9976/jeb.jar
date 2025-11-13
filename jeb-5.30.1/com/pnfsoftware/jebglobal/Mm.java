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

class Mm {
   private Map q = new HashMap();
   private List RF = new ArrayList();

   public boolean q() {
      return this.q.isEmpty();
   }

   public boolean q(CW var1) {
      long var2 = Uv(var1);
      return this.q.containsKey(var2);
   }

   public Set RF(CW var1) {
      if (var1 == null) {
         return null;
      } else if (var1.getOperandType() != 0) {
         return null;
      } else {
         long var2 = Uv(var1);
         return this.q(var2);
      }
   }

   public Set q(long var1) {
      Mm.ej var3 = (Mm.ej)this.q.get(var1);
      if (var3 == null) {
         return null;
      } else {
         var3.Dw = true;
         return var3.xK();
      }
   }

   public Set xK(CW var1) {
      long var2 = Uv(var1);
      Mm.ej var4 = (Mm.ej)this.q.get(var2);
      return var4 == null ? null : var4.xK();
   }

   public List q(CW[] var1) {
      ArrayList var2 = new ArrayList();

      for (int var3 = 0; var3 < var1.length; var3++) {
         if (var1[var3].getOperandType() == 7) {
            var2.addAll(this.q(var1[var3].oW()));
         } else if (var1[var3].getOperandType() == 0) {
            List var4 = this.Dw(var1[var3]);
            if (var4 != null) {
               var2.addAll(var4);
            }
         }
      }

      return var2;
   }

   public List Dw(CW var1) {
      if (var1.getOperandType() == 7) {
         return this.q(var1.oW());
      } else {
         long var2 = Uv(var1);
         return this.RF(var2);
      }
   }

   public List RF(long var1) {
      Mm.ej var3 = (Mm.ej)this.q.remove(var1);
      return var3 != null && var3.q() ? Arrays.asList(new Couple(var3.xK, var3.xK())) : null;
   }

   public List q(INativeCodeAnalyzer var1) {
      ArrayList var2 = new ArrayList();

      for (Entry var4 : this.q.entrySet()) {
         Mm.ej var5 = (Mm.ej)var4.getValue();
         if (var5.q()) {
            var2.add(new Couple(var5.xK, var5.xK()));
         }
      }

      this.q.clear();

      for (Mm.ej var7 : this.RF) {
         if (var7.q()) {
            var2.add(new Couple(var7.xK, var7.xK()));
         }
      }

      this.RF.clear();
      return var2;
   }

   public void q(Op var1, CW var2, long var3, long var5, Mm.ej var7) {
      this.q(var1, var2, var3, var5, var7.Uv == Mm.eo.RF ? Mm.eo.q : var7.Uv);
   }

   public void q(Op var1, CW var2, long var3, long var5, Mm.eo var7) {
      long var8 = Uv(var2);
      if (var8 != -1L) {
         Mm.ej var10 = (Mm.ej)this.q.get(var8);
         if (CharSequences.isBlank(var1.Dw())) {
            if (var10 != null) {
               this.RF.add(var10);
            }

            this.q.put(var8, new Mm.ej(var3, var5, var7));
         } else if (var10 == null) {
            this.q.put(var8, new Mm.ej(var3, var5, var7));
         } else {
            var10.q(var1.Dw(), var3);
         }
      }
   }

   public void q(Op var1, CW var2, List var3, long var4, Mm.eo var6) {
      long var7 = Uv(var2);
      if (var7 != -1L) {
         if (CharSequences.isBlank(var1.Dw())) {
            this.q.put(var7, new Mm.ej(var3, var4, var6));
         } else {
            Mm.ej var9 = (Mm.ej)this.q.get(var7);
            if (var9 == null) {
               this.q.put(var7, new Mm.ej(var3, var4, var6));
            } else {
               var9.q(var1.Dw(), var3);
            }
         }
      }
   }

   public Set q(CW var1, long var2, int var4) {
      switch (var1.getOperandType()) {
         case 0:
            if (!this.oW(var1)) {
               return null;
            } else {
               if (var1.RF(var4)) {
                  return Sets.newHashSet(var1.q(var2, var4, null));
               }

               return this.xK(var1);
            }
         case 1:
            return Sets.newHashSet(var1.q(var2, var4, null));
         default:
            return null;
      }
   }

   public boolean q(Op var1, CW var2, long var3, CW var5, CW var6, int var7) {
      long var8 = Uv(var2);
      if (var8 == -1L) {
         return false;
      } else {
         Mm.CU var10 = new Mm.CU(var5, var3, var7, var1.Dw());
         if (!var10.RF()) {
            return false;
         } else if (var1.q().equals("MOV")) {
            var10.q();
            this.q(var1, var2, var10.Dw, var5.isRegister() ? var10.xK.xK : var3, var10.q);
            return true;
         } else {
            Mm.CU var11 = new Mm.CU(var6, var3, var7, var1.Dw());
            if (var11.Dw == null) {
               Set var12 = this.q(var6, var3, var7);
               if (var12 == null) {
                  return false;
               }

               var11.Dw = new ArrayList(var12);
            }

            if (!var11.RF()) {
               return false;
            } else {
               boolean var19 = false;
               ArrayList var13 = new ArrayList();
               if (var11.Dw.size() > var10.Dw.size()) {
                  Mm.CU var14 = var11;
                  var11 = var10;
                  var10 = var14;
               }

               for (int var20 = 0; var20 < var10.Dw.size(); var20++) {
                  Long var15 = (Long)var10.Dw.get(var20);
                  Long var16 = var20 < var11.Dw.size() ? (Long)var11.Dw.get(var20) : (Long)var11.Dw.get(0);
                  String var17 = var1.q();
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
                  var10.q();
                  var11.q();
               }

               if (!CharSequences.isBlank(var1.Dw())) {
                  Mm.ej var21 = (Mm.ej)this.q.get(var8);
                  if (var21 != null) {
                     var21.Dw = false;
                  }
               }

               if (!var13.isEmpty()) {
                  Mm.eo var22 = this.q(var10.q, var11.q, var19);
                  this.q(var1, var2, var13, var3, var22);
                  return true;
               } else {
                  return false;
               }
            }
         }
      }
   }

   private Mm.eo q(Mm.eo var1, Mm.eo var2, boolean var3) {
      if (var1 == Mm.eo.q || var2 == Mm.eo.q) {
         return Mm.eo.q;
      } else if (var1 != Mm.eo.RF && var2 != Mm.eo.RF) {
         return Mm.eo.Dw;
      } else {
         return var3 ? Mm.eo.q : Mm.eo.RF;
      }
   }

   public void RF() {
      for (Mm.ej var2 : this.q.values()) {
         var2.RF();
      }
   }

   private boolean oW(CW var1) {
      return Uv(var1) != -1L;
   }

   public static long Uv(CW var0) {
      if (var0.getOperandType() != 0) {
         return -1L;
      } else {
         return var0 instanceof RI ? -1L : RegisterUtil.getPureId(var0.getOperandValue());
      }
   }

   private Mm.ej gO(CW var1) {
      long var2 = Uv(var1);
      return var2 == -1L ? null : (Mm.ej)this.q.get(var2);
   }

   public Long xK(long var1) {
      Mm.ej var3 = (Mm.ej)this.q.get(var1);
      return var3 == null ? null : (var3.Uv == Mm.eo.xK ? var3.xK : null);
   }

   public boolean q(CW var1, int var2) {
      if (var2 != 64 && var1.RF(var2)) {
         return true;
      } else {
         Mm.ej var3 = this.gO(var1);
         return var3 != null && (var3.Uv == Mm.eo.q || var3.Uv == Mm.eo.RF);
      }
   }

   public Mm xK() {
      Mm var1 = new Mm();

      for (Entry var3 : this.q.entrySet()) {
         var1.q.put((Long)var3.getKey(), ((Mm.ej)var3.getValue()).Dw());
      }

      for (Mm.ej var5 : this.RF) {
         var1.RF.add(var5.Dw());
      }

      return var1;
   }

   private class CU {
      private final Mm.ej xK;
      Mm.eo q = Mm.eo.Dw;
      private List Dw;

      public CU(CW var2, long var3, int var5, String var6) {
         this.xK = Mm.this.gO(var2);
         this.q = Mm.eo.Dw;
         if (this.xK != null) {
            this.Dw = this.xK.q(var6);
            this.q = this.xK.Uv;
         }
      }

      public void q() {
         if (this.xK != null) {
            this.xK.Dw = true;
         }
      }

      public boolean RF() {
         return this.Dw != null && !this.Dw.isEmpty();
      }
   }

   private static class ej {
      private long q;
      private Mm.nI RF = null;
      private long xK;
      private boolean Dw = false;
      private Mm.eo Uv = Mm.eo.Dw;

      public ej(long var1, long var3, Mm.eo var5) {
         this.q = var1;
         this.xK = var3;
         this.Uv = var5;
      }

      public ej(List var1, long var2, Mm.eo var4) {
         this.xK = var2;
         this.Uv = var4;
         this.RF = new Mm.nI();
         this.RF.q.addAll(var1);
      }

      @Override
      public String toString() {
         return "RegisterValue [@"
            + Long.toHexString(this.xK)
            + "h:"
            + Long.toHexString(this.q)
            + "h="
            + (this.RF == null ? "null" : this.RF.toString())
            + ", "
            + (this.Dw ? "" : "not ")
            + "analyzed, "
            + this.Uv
            + "]";
      }

      public boolean q() {
         return !this.Dw && this.Uv == Mm.eo.q;
      }

      public void q(String var1, long var2) {
         if (this.RF == null) {
            this.RF = new Mm.nI();
            this.RF.q.add(this.q);
         }

         Object var4 = (Set)this.RF.RF.get(var1);
         if (var4 == null) {
            var4 = new HashSet();
            this.RF.RF.put(var1, var4);
         }

         var4.add(var2);
      }

      public void q(String var1, List var2) {
         if (this.RF == null) {
            this.RF = new Mm.nI();
            this.RF.q.add(this.q);
         }

         this.RF.RF.put(var1, new HashSet(var2));
      }

      public void RF() {
         if (this.RF != null) {
            for (Set var2 : this.RF.RF.values()) {
               this.RF.q.addAll(var2);
            }

            this.RF.RF.clear();
         }
      }

      public List q(String var1) {
         if (this.RF == null) {
            return Arrays.asList(this.q);
         } else {
            ArrayList var2 = new ArrayList();
            Set var3 = (Set)this.RF.RF.get(var1);
            if (var3 != null && !var3.isEmpty()) {
               var2.addAll(var3);
            } else {
               var2.addAll(this.RF.q);
            }

            Collections.sort(var2);
            return var2;
         }
      }

      public Set xK() {
         if (this.RF == null) {
            return Sets.newHashSet(this.q);
         } else {
            TreeSet var1 = new TreeSet();
            var1.addAll(this.RF.q);

            for (Set var3 : this.RF.RF.values()) {
               var1.addAll(var3);
            }

            return var1;
         }
      }

      public Mm.ej Dw() {
         Mm.ej var1 = new Mm.ej(this.q, this.xK, this.Uv);
         var1.Dw = this.Dw;
         if (this.RF != null) {
            var1.RF = new Mm.nI();
            var1.RF.q.addAll(this.RF.q);
            var1.RF.RF.putAll(this.RF.RF);
         }

         return null;
      }
   }

   static enum eo {
      q,
      RF,
      xK,
      Dw;
   }

   private static class nI {
      private Set q = new HashSet();
      private Map RF = new HashMap();

      @Override
      public String toString() {
         StringBuilder var1 = new StringBuilder();
         if (this.q.size() > 1) {
            var1.append("{");
         }

         var1.append(Strings.join(",", this.q, Long::toHexString));
         if (!this.RF.isEmpty()) {
            var1.append("{?");
            this.RF.forEach((var1x, var2) -> var1.append(var1x).append(":{").append(Strings.join(",", var2, Long::toHexString)).append("}"));
            var1.append("}");
         }

         if (this.q.size() > 1) {
            var1.append("}");
         }

         return var1.toString();
      }
   }
}
