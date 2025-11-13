package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.exceptions.JebException;
import com.pnfsoftware.jeb.core.units.code.asm.cfg.BasicBlock;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.IEAssign;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.IECompose;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.IEGeneric;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.IEMem;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.IEStatement;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.IEVar;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.opt.AbstractEOptimizer;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.opt.DataChainsUpdatePolicy;
import java.util.ArrayList;

@Deprecated
public class zT extends AbstractEOptimizer {
   private static final int[][] q = new int[][]{{0, 32}, {64, 96}};

   public zT() {
      super(DataChainsUpdatePolicy.UPDATE_IF_OPTIMIZED);
   }

   @Override
   protected int perform() {
      if (this.ectx.usesCopyVars()) {
         return 0;
      } else {
         int var1 = 0;

         for (BasicBlock var3 : this.cfg.getBlocks()) {
            for (int var4 = 0; var4 < var3.size() - 1; var4++) {
               IEStatement var5 = (IEStatement)var3.get(var4);
               IEStatement var6 = (IEStatement)var3.get(var4 + 1);
               IEStatement var7 = var4 + 2 < var3.size() ? (IEStatement)var3.get(var4 + 2) : null;
               if (this.q(var5) && this.q(var6)) {
                  IEMem var8 = (IEMem)((IEAssign)var5).getLeftOperand();
                  IEVar var9 = (IEVar)((IEAssign)var5).getRightOperand();
                  IEMem var10 = (IEMem)((IEAssign)var6).getLeftOperand();
                  IEVar var11 = (IEVar)((IEAssign)var6).getRightOperand();
                  IEStatement var12 = this.q(var8, var10, var9, var11, var5, var6, var7);
                  if (var12 != null) {
                     Long var13 = var5.getPrimaryLowerLevelAddress();
                     ArrayList var14 = new ArrayList();
                     var12.setSize(1);
                     var12.setPrimaryLowerLevelAddress(var13);
                     var14.add(var12);

                     try {
                        if (amw.q(this.ectx, var3, var4 + 2, var14)) {
                           Object[] var10000 = new Object[]{var12};
                           var1++;
                        }
                     } catch (JebException var15) {
                     }
                  }
               }
            }
         }

         return this.postPerform(var1);
      }
   }

   private boolean q(IEStatement var1) {
      return var1 instanceof IEAssign && ((IEAssign)var1).getLeftOperand() instanceof IEMem && this.q(((IEAssign)var1).getRightOperand());
   }

   private boolean q(IEGeneric var1) {
      if (!(var1 instanceof IEVar)) {
         return false;
      } else {
         switch (((IEVar)var1).getId()) {
            case 0:
            case 32:
            case 64:
            case 96:
               return true;
            default:
               return false;
         }
      }
   }

   private IEStatement q(IEMem var1, IEMem var2, IEVar var3, IEVar var4, IEStatement var5, IEStatement var6, IEStatement var7) {
      boolean var8 = this.ectx.getGlobalContext().isBigEndian();
      boolean var9 = false;
      boolean var10 = false;

      for (int[] var14 : q) {
         if (var3.getId() == var14[0] && var4.getId() == var14[1]) {
            var10 = true;
            var9 = var8;
            break;
         }

         if (var3.getId() == var14[1] && var4.getId() == var14[0]) {
            var10 = true;
            var9 = !var8;
            break;
         }
      }

      if (!var10) {
         return null;
      } else {
         if (var9) {
            IEVar var15 = var4;
            var4 = var3;
            var3 = var15;
            IEMem var17 = var2;
            var2 = var1;
            var1 = var17;
         }

         IEMem var16 = amw.q(this.ectx, var1, var2);
         if (var16 != null) {
            if (var7 != null && var7 instanceof IEAssign && ((IEAssign)var7).getLeftOperand().equals(var16)) {
               return null;
            } else {
               IECompose var18 = this.ectx.createCompose(var3, var4);
               IEAssign var19 = ((IEAssign)var5).duplicateWithNewOperands(var16.duplicate(), var18);
               var19.copyProperties(var5);
               var19.copyProperties(var6);
               return var19;
            }
         } else {
            return null;
         }
      }
   }
}
