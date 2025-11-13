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

public class bwg {
   private static final ILogger q = GlobalLog.getLogger(bwg.class);
   private IDMethodContext RF;
   private CFG xK;
   private boolean Dw;
   private boolean Uv;
   private Set oW;
   private IDFA gO;
   private Set nf = new HashSet();

   public bwg(IDMethodContext var1) {
      this.RF = var1;
   }

   public void q(boolean var1) {
      this.Dw = var1;
   }

   public void RF(boolean var1) {
      this.Uv = var1;
   }

   public void q(Collection var1) {
      this.oW = var1 == null ? null : (Set)var1.stream().map(var0 -> var0.getId()).collect(Collectors.toSet());
   }

   public int q() {
      if (this.xK != null) {
         throw new IllegalStateException("The SSA transformer was already used");
      } else {
         this.xK = this.RF.getCfg();
         this.gO = this.xK.doDataFlowAnalysis();
         int var1 = this.RF();
         if (var1 > 0) {
            this.xK.invalidateDataFlowAnalysis();
         }

         return var1;
      }
   }

   private int RF() {
      if (this.oW != null && this.oW.isEmpty()) {
         return 0;
      } else {
         int var1 = 0;
         IVariableInformationProvider var2 = this.xK.setVariableInformationProvider(null);

         try {
            ArrayList var3 = new ArrayList();
            ArrayList var4 = new ArrayList();

            for (IDInstruction var6 : this.xK.getInstructions()) {
               var6.getDefUse(var3, var4, null);
               if (!var3.isEmpty()) {
                  for (int var8 : var3) {
                     if ((this.oW == null || this.oW.contains(var8)) && !this.nf.contains(var8) && this.q(var6, var8)) {
                        var1++;
                     }
                  }
               }
            }
         } finally {
            this.xK.setVariableInformationProvider(var2);
         }

         if (var1 > 0) {
            this.xK.invalidateDataFlowAnalysis();
         }

         return var1;
      }
   }

   private boolean q(IDInstruction var1, int var2) {
      ArrayList var3 = new ArrayList();
      ArrayDeque var4 = new ArrayDeque();
      HashSet var5 = new HashSet();
      bwg.eo var6 = new bwg.eo(var1, true);
      var4.add(var6);
      var5.add(var6);

      while (!var4.isEmpty()) {
         var6 = (bwg.eo)var4.pop();
         IDInstruction var7 = var6.q;
         boolean var8 = var6.RF;
         var3.add(new bwg.eo(var7, var8));
         if (var8) {
            Collection var17;
            if (var7 == null) {
               var17 = this.gO.getInputMap(var2);
            } else {
               var17 = this.gO.getDefUses(var7.getOffset(), var2);
            }

            if (var17 != null) {
               for (long var21 : var17) {
                  bwg.eo var23 = new bwg.eo((IDInstruction)this.xK.getInstruction(var21), false);
                  if (!var5.contains(var23)) {
                     var4.add(var23);
                     var5.add(var23);
                  }
               }
            }
         } else {
            Collection var9 = this.gO.getUseDefs(var7.getOffset(), var2);
            if (var9 != null) {
               for (long var11 : var9) {
                  if (var11 < 0L && !this.Dw) {
                     return false;
                  }

                  bwg.eo var13 = new bwg.eo((IDInstruction)this.xK.getInstruction(var11), true);
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
      } else if (!this.Uv && var3.size() == 1) {
         return false;
      } else {
         IDVar var15 = this.RF.getVar(var2);
         IDVar var16;
         if (DUtil.isRegisterVarId(var2)) {
            var16 = this.RF.createCopyVar(var15);
         } else {
            var16 = this.RF.createVirtualVar(var15.getType());
         }

         this.nf.add(var16.getId());

         for (bwg.eo var20 : var3) {
            IDInstruction var22 = var20.q;
            if (!var20.RF) {
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

   private static class eo {
      IDInstruction q;
      boolean RF;

      public eo(IDInstruction var1, boolean var2) {
         this.q = var1;
         this.RF = var2;
      }

      @Override
      public int hashCode() {
         int var1 = 1;
         var1 = 31 * var1 + (this.q == null ? 0 : this.q.hashCode());
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
            bwg.eo var2 = (bwg.eo)var1;
            return this.q != var2.q ? false : this.RF == var2.RF;
         }
      }

      @Override
      public String toString() {
         return Strings.ff("0x%X[%s]", this.q == null ? -1L : this.q.getOffset(), this.RF ? "def" : "use");
      }
   }
}
