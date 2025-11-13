package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.units.code.asm.decompiler.IEConverter;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.EUtil;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.IEGeneric;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.IEMem;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.IEVar;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.VarSrc;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.opt.AbstractEExpressionOptimizer;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.opt.DataChainsUpdatePolicy;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.opt.OptimizerMode;
import com.pnfsoftware.jeb.util.base.Assert;
import com.pnfsoftware.jeb.util.format.Strings;
import java.util.HashMap;
import java.util.Map;

public class ctt extends AbstractEExpressionOptimizer {
   private Map q = new HashMap();

   public ctt(IEConverter var1) {
      super(DataChainsUpdatePolicy.UPDATE_IF_OPTIMIZED);
      this.setRequiredModeThreshold(OptimizerMode.UNFRIENDLY);
      Assert.a(var1 instanceof crx);
      String[] var2 = new String[]{"fs", "gs"};

      for (String var6 : var2) {
         IEVar var7 = var1.getGlobalContext().getVariableByName(var6);
         if (var7 != null) {
            String var8 = Strings.ff("__%s_BASE", var6.toUpperCase());
            IEVar var9 = var1.getGlobalContext().getVariableByName(var8);
            if (var9 == null) {
               var9 = var1.getGlobalContext().createVirtualRegister(var8, var1.getAddressBitsize());
            }

            this.q.put(var7.getId(), var9);
         }
      }
   }

   @Override
   protected AbstractEExpressionOptimizer.EOR optimizeExpression(IEGeneric var1) {
      if (var1 instanceof IEMem && ((IEMem)var1).getSegment() instanceof IEVar var3) {
         VarSrc var4 = this.ectx.getSourceForVariable(var3.getId());
         if (var4 != null && var4.isDuplicate()) {
            int var5 = var4.getAsDuplicate();
            IEVar var6 = (IEVar)this.q.get(var5);
            if (var6 != null) {
               IEGeneric var7 = ((IEMem)var1).getReference().zeroExtend(var6.getBitsize());
               IEMem var8 = this.ectx.createMem(EUtil.add(var6, var7), var1.getBitsize());
               return AbstractEExpressionOptimizer.EOR.create(var8, true);
            }
         }
      }

      return null;
   }
}
