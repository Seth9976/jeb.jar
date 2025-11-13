package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.IEAssign;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.IEGeneric;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.IEUntranslatedInstruction;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.IEVar;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.opt.AbstractEExpressionOptimizer;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.opt.DataChainsUpdatePolicy;
import com.pnfsoftware.jeb.core.units.code.asm.processor.arch.RegisterUtil;
import com.pnfsoftware.jeb.util.logging.GlobalLog;
import com.pnfsoftware.jeb.util.logging.ILogger;
import java.util.HashSet;

public class clq extends AbstractEExpressionOptimizer {
   private static final ILogger q = GlobalLog.getLogger(clq.class);
   private static long RF = RegisterUtil.createRegisterId(0, 0, 32);
   private static long xK = RegisterUtil.createRegisterId(0, 0, 64);
   private IEGeneric Dw = null;

   public clq() {
      super(DataChainsUpdatePolicy.UPDATE_IF_OPTIMIZED);
      this.skipStatementProcessing = false;
   }

   @Override
   protected AbstractEExpressionOptimizer.EOR optimizeExpression(IEGeneric var1) {
      if (var1 instanceof IEUntranslatedInstruction) {
         return null;
      } else if (var1 instanceof IEAssign && this.q(((IEAssign)var1).getDstOperand())) {
         Object[] var10000 = new Object[]{var1};
         return AbstractEExpressionOptimizer.EOR.create(this.ectx.createNop((IEAssign)var1), true);
      } else {
         return this.q(var1) ? AbstractEExpressionOptimizer.EOR.create(this.ectx.createImm(0L, var1.getBitsize()), true) : null;
      }
   }

   private boolean q(IEGeneric var1) {
      if (var1 instanceof IEVar) {
         int var2 = ((IEVar)var1).getId();
         if (var2 == 0) {
            return true;
         } else {
            HashSet var3 = new HashSet();
            var3.add(var2);
            IEGeneric var4 = this.ectx.retrieveVariableForRegister(this.q(), var3, false);
            return var4 == var1;
         }
      } else {
         return false;
      }
   }

   private IEGeneric q() {
      if (this.Dw == null) {
         this.Dw = this.ectx.getConverter().getRegisterVariableFromNativeRegisterId(this.ectx.getConverter().getRegisterBitsize() == 64 ? xK : RF);
      }

      return this.Dw;
   }
}
