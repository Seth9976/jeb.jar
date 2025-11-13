package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.units.code.IDFA;
import com.pnfsoftware.jeb.core.units.code.asm.cfg.CFG;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.IERoutineContext;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.IEGeneric;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.IEStatement;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.IEVar;
import com.pnfsoftware.jeb.util.format.Strings;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;

public class alr {
   protected IERoutineContext pC;
   protected CFG A;
   private List kS = new ArrayList();
   private boolean wS;
   private IDFA UT;

   public alr(IERoutineContext var1, CFG var2) {
      this.pC = var1;
      this.A = var2;
   }

   public Collection pC() {
      List var1 = this.kS;
      this.kS = new ArrayList();
      this.wS = false;
      return var1;
   }

   private void kS() {
      if (!this.wS) {
         this.UT = this.A.doDataFlowAnalysis();
         this.wS = true;
      }
   }

   public boolean pC(long var1, int var3, boolean var4) {
      if (!this.A(var1, var3, var4)) {
         return false;
      } else {
         this.kS();
         ArrayDeque var5 = new ArrayDeque();
         alr.Av var6 = new alr.Av(var1, var4);
         var5.add(var6);
         HashSet var7 = new HashSet();
         var7.add(var6);
         Collection var8 = null;

         while (!var5.isEmpty()) {
            var6 = (alr.Av)var5.pop();
            var1 = var6.pC;
            IEStatement var9 = (IEStatement)this.A.getInstruction(var1);
            var4 = var6.A;
            if (!this.pC(var1, var9, var4)) {
               return false;
            }

            this.kS.add(var6);
            if (var4) {
               Collection var18;
               if (var1 == -1L) {
                  var18 = this.UT.getInputMap(var3);
               } else {
                  var18 = this.UT.getDefUses(var1, var3);
               }

               if (var18 != null) {
                  for (long var21 : var18) {
                     alr.Av var23 = new alr.Av(var21, false);
                     if (!var7.contains(var23)) {
                        var5.add(var23);
                        var7.add(var23);
                     }
                  }
               }

               if (var8 == null) {
                  var8 = this.UT.getOutputMap(var3);
               }

               if (var8 != null && var8.contains(var1)) {
                  for (long var22 : var8) {
                     alr.Av var24 = new alr.Av(var22, true);
                     if (!var7.contains(var24)) {
                        var5.add(var24);
                        var7.add(var24);
                     }
                  }
               }
            } else {
               Collection var10 = this.UT.getUseDefs(var1, var3);
               if (var10 != null) {
                  for (long var12 : var10) {
                     alr.Av var14 = new alr.Av(var12, true);
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

   public final Collection A() {
      return this.kS;
   }

   protected boolean A(long var1, int var3, boolean var4) {
      IEVar var5 = this.pC.getVariableById(var3);
      if (var5 == null) {
         return false;
      } else if (var1 == -1L) {
         return var4;
      } else {
         IEStatement var6 = (IEStatement)this.A.getInstruction(var1);
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

   protected boolean pC(long var1, IEStatement var3, boolean var4) {
      return true;
   }

   @Override
   public String toString() {
      return Strings.ff("DFW_records:%s", this.A());
   }

   public int pC(IEVar var1, IEGeneric var2) {
      return pC(this.A, this.pC(), var1, var2);
   }

   public static int pC(CFG var0, Collection var1, IEVar var2, IEGeneric var3) {
      int var4 = 0;

      for (alr.Av var6 : var1) {
         IEStatement var7 = (IEStatement)var0.getInstruction(var6.pC());
         if (var7 != null) {
            if (!var6.A()) {
               var4 += var7.replaceVar(var2, var3, true);
            } else {
               var4 += var7.replaceDefinedVar(var2, var3);
            }
         }
      }

      return var4;
   }

   public static class Av {
      long pC;
      boolean A;

      public Av(long var1, boolean var3) {
         this.pC = var1;
         this.A = var3;
      }

      public long pC() {
         return this.pC;
      }

      public boolean A() {
         return this.A;
      }

      @Override
      public int hashCode() {
         int var1 = 1;
         var1 = 31 * var1 + (int)(this.pC ^ this.pC >>> 32);
         return 31 * var1 + (this.A ? 1231 : 1237);
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
            alr.Av var2 = (alr.Av)var1;
            return this.pC != var2.pC ? false : this.A == var2.A;
         }
      }

      @Override
      public String toString() {
         String var1;
         if (this.pC == -1L) {
            var1 = "init";
         } else {
            var1 = Strings.ff("0x%X", this.pC);
         }

         return Strings.ff("%s@%s", this.A ? "def" : "use", var1);
      }
   }
}
