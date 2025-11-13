package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.units.code.IDFA;
import com.pnfsoftware.jeb.core.units.code.asm.cfg.CFG;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.IERoutineContext;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.IEGeneric;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.IEStatement;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.IEVar;
import com.pnfsoftware.jeb.util.format.Strings;
import com.pnfsoftware.jeb.util.primitives.Longs;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.List;

public class anw {
   protected IERoutineContext q;
   protected CFG RF;
   private List xK = new ArrayList();
   private boolean Dw;
   private IDFA Uv;

   public anw(IERoutineContext var1, CFG var2) {
      this.q = var1;
      this.RF = var2;
   }

   public Collection q() {
      List var1 = this.xK;
      this.xK = new ArrayList();
      this.Dw = false;
      return var1;
   }

   private void xK() {
      if (!this.Dw) {
         this.Uv = this.RF.doDataFlowAnalysis();
         this.Dw = true;
      }
   }

   public boolean q(long var1, int var3) {
      return this.q(var1, var3, true);
   }

   public boolean RF(long var1, int var3) {
      return this.q(var1, var3, false);
   }

   public boolean q(long var1, int var3, boolean var4) {
      if (!this.RF(var1, var3, var4)) {
         return false;
      } else {
         this.xK();
         ArrayDeque var5 = new ArrayDeque();
         anw.eo var6 = new anw.eo(var1, var4);
         var5.add(var6);
         HashSet var7 = new HashSet();
         var7.add(var6);
         Collection var8 = null;

         while (!var5.isEmpty()) {
            var6 = (anw.eo)var5.pop();
            var1 = var6.q;
            IEStatement var9 = (IEStatement)this.RF.getInstruction(var1);
            var4 = var6.RF;
            if (!this.q(var1, var9, var4)) {
               return false;
            }

            this.xK.add(var6);
            if (var4) {
               Collection var18;
               if (var1 == -1L) {
                  var18 = this.Uv.getInputMap(var3);
               } else {
                  var18 = this.Uv.getDefUses(var1, var3);
               }

               if (var18 != null) {
                  for (long var21 : var18) {
                     anw.eo var23 = new anw.eo(var21, false);
                     if (!var7.contains(var23)) {
                        var5.add(var23);
                        var7.add(var23);
                     }
                  }
               }

               if (var8 == null) {
                  var8 = this.Uv.getOutputMap(var3);
               }

               if (var8 != null && var8.contains(var1)) {
                  for (long var22 : var8) {
                     anw.eo var24 = new anw.eo(var22, true);
                     if (!var7.contains(var24)) {
                        var5.add(var24);
                        var7.add(var24);
                     }
                  }
               }
            } else {
               Collection var10 = this.Uv.getUseDefs(var1, var3);
               if (var10 != null) {
                  for (long var12 : var10) {
                     anw.eo var14 = new anw.eo(var12, true);
                     if (!var7.contains(var14)) {
                        var5.add(var14);
                        var7.add(var14);
                     }
                  }
               }
            }
         }

         return true;
      }
   }

   public final Collection RF() {
      return this.xK;
   }

   protected boolean RF(long var1, int var3, boolean var4) {
      IEVar var5 = this.q.getVariableById(var3);
      if (var5 == null) {
         return false;
      } else if (var1 == -1L) {
         return var4;
      } else {
         IEStatement var6 = (IEStatement)this.RF.getInstruction(var1);
         if (var6 == null) {
            return false;
         } else {
            ArrayList var7 = new ArrayList();
            ArrayList var8 = new ArrayList();
            var6.getDefUse(var7, var8, null);
            return var4 ? var7.contains(var3) : var8.contains(var3);
         }
      }
   }

   protected boolean q(long var1, IEStatement var3, boolean var4) {
      return true;
   }

   @Override
   public String toString() {
      return Strings.ff("DFW_records:%s", this.RF());
   }

   public int q(IEVar var1, IEGeneric var2) {
      return q(this.RF, this.q(), var1, var2);
   }

   public static int q(CFG var0, Collection var1, IEVar var2, IEGeneric var3) {
      int var4 = 0;

      for (anw.eo var6 : var1) {
         IEStatement var7 = (IEStatement)var0.getInstruction(var6.q());
         if (var7 != null) {
            if (!var6.RF()) {
               var4 += var7.replaceVar(var2, var3, true);
            } else {
               var4 += var7.replaceDefinedVar(var2, var3);
            }
         }
      }

      return var4;
   }

   public static class eo {
      long q;
      boolean RF;

      public static anw.eo q(long var0) {
         return new anw.eo(var0, true);
      }

      public static anw.eo RF(long var0) {
         return new anw.eo(var0, false);
      }

      public eo(long var1, boolean var3) {
         this.q = var1;
         this.RF = var3;
      }

      public long q() {
         return this.q;
      }

      public boolean RF() {
         return this.RF;
      }

      @Override
      public int hashCode() {
         int var1 = 1;
         var1 = 31 * var1 + (int)(this.q ^ this.q >>> 32);
         return 31 * var1 + (this.RF ? 1231 : 1237);
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
            anw.eo var2 = (anw.eo)var1;
            return this.q != var2.q ? false : this.RF == var2.RF;
         }
      }

      @Override
      public String toString() {
         String var1;
         if (this.q == -1L) {
            var1 = "init";
         } else {
            var1 = Strings.ff("0x%X", this.q);
         }

         return Strings.ff("%s@%s", this.RF ? "def" : "use", var1);
      }

      public static int q(Collection var0, long var1) {
         int var3 = 0;
         Iterator var4 = var0.iterator();

         while (var4.hasNext()) {
            anw.eo var5 = (anw.eo)var4.next();
            if (var5.q() == var1) {
               var4.remove();
               var3++;
            }
         }

         return var3;
      }

      public static String q(Collection var0) {
         LinkedHashSet var1 = new LinkedHashSet();
         LinkedHashSet var2 = new LinkedHashSet();

         for (anw.eo var4 : var0) {
            if (var4.RF()) {
               var1.add(var4.q());
            } else {
               var2.add(var4.q());
            }
         }

         StringBuilder var5 = new StringBuilder();
         var5.append("uses@{").append(Longs.formatLongCollection(var2, 16, "0x", "", ", ")).append("}");
         var5.append(" defs@{").append(Longs.formatLongCollection(var1, 16, "0x", "", ", ")).append("}");
         return var5.toString();
      }
   }
}
