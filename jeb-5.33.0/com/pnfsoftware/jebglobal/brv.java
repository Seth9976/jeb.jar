package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.units.code.IDFA;
import com.pnfsoftware.jeb.core.units.code.android.controlflow.CFG;
import com.pnfsoftware.jeb.core.units.code.android.controlflow.IVariableInformationProvider;
import com.pnfsoftware.jeb.core.units.code.android.ir.DUtil;
import com.pnfsoftware.jeb.core.units.code.android.ir.IDInstruction;
import com.pnfsoftware.jeb.core.units.code.android.ir.IDMethodContext;
import com.pnfsoftware.jeb.core.units.code.android.ir.IDVar;
import com.pnfsoftware.jeb.util.format.Strings;
import com.pnfsoftware.jeb.util.logging.GlobalLog;
import com.pnfsoftware.jeb.util.logging.ILogger;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

public class brv {
   private static final ILogger pC = GlobalLog.getLogger(brv.class);
   private IDMethodContext A;
   private CFG kS;
   private boolean wS;
   private boolean UT;
   private Set E;
   private IDFA sY;
   private Set ys = new HashSet();

   public brv(IDMethodContext var1) {
      this.A = var1;
   }

   public void pC(boolean var1) {
      this.UT = var1;
   }

   public void pC(Collection var1) {
      this.E = var1 == null ? null : (Set)var1.stream().map(var0 -> var0.getId()).collect(Collectors.toSet());
   }

   public int pC() {
      if (this.kS != null) {
         throw new IllegalStateException("The SSA transformer was already used");
      } else {
         this.kS = this.A.getCfg();
         this.sY = this.kS.doDataFlowAnalysis();
         int var1 = this.A();
         if (var1 > 0) {
            this.kS.invalidateDataFlowAnalysis();
         }

         return var1;
      }
   }

   private int A() {
      if (this.E != null && this.E.isEmpty()) {
         return 0;
      } else {
         int var1 = 0;
         IVariableInformationProvider var2 = this.kS.setVariableInformationProvider(null);

         try {
            ArrayList var3 = new ArrayList();
            ArrayList var4 = new ArrayList();

            for (IDInstruction var6 : this.kS.getInstructions()) {
               var6.getDefUse(var3, var4, null);
               if (!var3.isEmpty()) {
                  for (int var8 : var3) {
                     if ((this.E == null || this.E.contains(var8)) && !this.ys.contains(var8) && this.pC(var6, var8)) {
                        var1++;
                     }
                  }
               }
            }
         } finally {
            this.kS.setVariableInformationProvider(var2);
         }

         if (var1 > 0) {
            this.kS.invalidateDataFlowAnalysis();
         }

         return var1;
      }
   }

   private boolean pC(IDInstruction var1, int var2) {
      ArrayList var3 = new ArrayList();
      ArrayDeque var4 = new ArrayDeque();
      HashSet var5 = new HashSet();
      brv.Av var6 = new brv.Av(var1, true);
      var4.add(var6);
      var5.add(var6);

      while (!var4.isEmpty()) {
         var6 = (brv.Av)var4.pop();
         IDInstruction var7 = var6.pC;
         boolean var8 = var6.A;
         var3.add(new brv.Av(var7, var8));
         if (var8) {
            Collection var17;
            if (var7 == null) {
               var17 = this.sY.getInputMap(var2);
            } else {
               var17 = this.sY.getDefUses(var7.getOffset(), var2);
            }

            if (var17 != null) {
               for (long var21 : var17) {
                  brv.Av var23 = new brv.Av((IDInstruction)this.kS.getInstruction(var21), false);
                  if (!var5.contains(var23)) {
                     var4.add(var23);
                     var5.add(var23);
                  }
               }
            }
         } else {
            Collection var9 = this.sY.getUseDefs(var7.getOffset(), var2);
            if (var9 != null) {
               for (long var11 : var9) {
                  if (var11 < 0L && !this.wS) {
                     return false;
                  }

                  brv.Av var13 = new brv.Av((IDInstruction)this.kS.getInstruction(var11), true);
                  if (!var5.contains(var13)) {
                     var4.add(var13);
                     var5.add(var13);
                  }
               }
            }
         }
      }

      if (var3.isEmpty()) {
         return false;
      } else if (!this.UT && var3.size() == 1) {
         return false;
      } else {
         IDVar var15 = this.A.getVar(var2);
         IDVar var16;
         if (DUtil.isRegisterVarId(var2)) {
            var16 = this.A.createCopyVar(var15);
         } else {
            var16 = this.A.createVirtualVar(var15.getType());
         }

         this.ys.add(var16.getId());

         for (brv.Av var20 : var3) {
            IDInstruction var22 = var20.pC;
            if (!var20.A) {
               if (var15 != null) {
                  var22.replaceUsedVariable(var15, var16);
               }
            } else if (var22 != null && var15 != null) {
               var22.replaceDefinedVariable(var15, var16);
            }
         }

         return true;
      }
   }

   private static class Av {
      IDInstruction pC;
      boolean A;

      public Av(IDInstruction var1, boolean var2) {
         this.pC = var1;
         this.A = var2;
      }

      @Override
      public int hashCode() {
         int var1 = 1;
         var1 = 31 * var1 + (this.pC == null ? 0 : this.pC.hashCode());
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
            brv.Av var2 = (brv.Av)var1;
            return this.pC != var2.pC ? false : this.A == var2.A;
         }
      }

      @Override
      public String toString() {
         return Strings.ff("0x%X[%s]", this.pC == null ? -1L : this.pC.getOffset(), this.A ? "def" : "use");
      }
   }
}
