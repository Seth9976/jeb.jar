package com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.opt;

import com.pnfsoftware.jeb.core.units.code.asm.cfg.BasicBlock;
import com.pnfsoftware.jeb.core.units.code.asm.cfg.CFG;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.IERoutineContext;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.EUtil;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.IEGeneric;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.opt.AbstractOptimizer;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.opt.OptimizerType;
import com.pnfsoftware.jebglobal.amw;

public abstract class AbstractEOptimizer extends AbstractOptimizer implements IEOptimizer {
   private DataChainsUpdatePolicy dataChainsUpdatePolicy;
   protected IERoutineContext ectx;
   protected CFG cfg;

   public AbstractEOptimizer() {
      this(null);
   }

   public AbstractEOptimizer(DataChainsUpdatePolicy var1) {
      this(var1, null);
   }

   public AbstractEOptimizer(DataChainsUpdatePolicy var1, OptimizerType var2) {
      this.setType(var2);
      this.setDataChainsUpdatePolicy(var1);
   }

   public IEMasterOptimizer getMasterOptimizer() {
      return (IEMasterOptimizer)super.getMasterOptimizer();
   }

   protected IEMasterOptimizer getMasterOptimizerSafe() {
      Object var1 = this.getMasterOptimizer();
      if (var1 == null) {
         var1 = EMasterOptimizer.EMPTY;
      }

      return (IEMasterOptimizer)var1;
   }

   @Override
   public DataChainsUpdatePolicy getDataChainsUpdatePolicy() {
      return this.dataChainsUpdatePolicy;
   }

   protected void setDataChainsUpdatePolicy(DataChainsUpdatePolicy var1) {
      if (var1 == null) {
         var1 = DataChainsUpdatePolicy.UPDATE_PERFORMED_INTERNALLY;
      }

      this.dataChainsUpdatePolicy = var1;
   }

   @Override
   public IEGeneric performOnExpression(IEGeneric var1, IERoutineContext var2) {
      return null;
   }

   public final int performOnTarget(IERoutineContext var1) {
      this.ectx = var1;
      this.cfg = var1.getCfg();

      int var2;
      try {
         var2 = this.perform();
      } finally {
         this.ectx = null;
         this.cfg = null;
      }

      return var2;
   }

   protected abstract int perform();

   protected final int postPerform(int var1) {
      return this.postPerform(var1, false);
   }

   protected final int postPerform(int var1, boolean var2) {
      if (var1 > 0
         && (
            this.dataChainsUpdatePolicy == DataChainsUpdatePolicy.UPDATE_IF_OPTIMIZED
               || this.dataChainsUpdatePolicy == DataChainsUpdatePolicy.UPDATE_IF_REQUIRED && var2
         )) {
         this.ectx.invalidateDataFlowAnalysis();
      }

      return var1;
   }

   protected boolean deleteUnreachableTrampoline(BasicBlock var1) {
      return amw.q(this.cfg, var1);
   }

   protected int cleanCfg() {
      return amw.q(this.cfg);
   }

   protected void verifyCfg() {
      EUtil.verify(this.ectx);
   }

   protected void dumpCfg(String var1) {
      EUtil.dump(this.ectx.getCfg(), var1);
   }
}
