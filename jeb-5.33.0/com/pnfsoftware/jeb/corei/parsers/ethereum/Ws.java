package com.pnfsoftware.jeb.corei.parsers.ethereum;

import com.pnfsoftware.jeb.core.units.code.IDFA;
import com.pnfsoftware.jeb.core.units.code.asm.cfg.BasicBlock;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.EUtil;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.IEGeneric;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.IEStatement;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.IEUntranslatedInstruction;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.IEVar;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.opt.AbstractEOptimizer;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.opt.DataChainsUpdatePolicy;
import com.pnfsoftware.jeb.util.logging.GlobalLog;
import com.pnfsoftware.jeb.util.logging.ILogger;
import java.math.BigInteger;
import java.util.Collection;

public class Ws extends AbstractEOptimizer {
   private static final ILogger pC = GlobalLog.getLogger(Ws.class);
   private static final BigInteger A = BigInteger.ONE.shiftLeft(224);
   private static final BigInteger kS = BigInteger.valueOf(224L);

   public Ws() {
      super(DataChainsUpdatePolicy.UPDATE_IF_OPTIMIZED);
      this.setPriority(10.0);
   }

   @Override
   protected int perform() {
      IEVar var1 = ((oP)this.ectx.getGlobalContext().getConverter()).z;
      int var2 = 0;

      for (BasicBlock var5 : this.cfg) {
         for (int var6 = 0; var6 < var5.size(); var6++) {
            IEStatement var7 = (IEStatement)var5.get(var6);
            if (oP.pC(var7, 53)) {
               IEUntranslatedInstruction var8 = (IEUntranslatedInstruction)var7;
               IEGeneric var9 = var8.getParameterExpression(0);
               if (EUtil.isImmZero(var9)) {
                  IEGeneric var10 = var8.getReturnExpression();
                  if (var10 instanceof IEVar) {
                     IDFA var11 = this.cfg.doDataFlowAnalysis();
                     IEVar var12 = (IEVar)var10;
                     Collection var13 = var11.getDefUses(var5.getAddressOfInstruction(var6), var12.getId());
                     if (var13 != null && var13.size() == 1) {
                        long var14 = (Long)var13.iterator().next();
                        IEStatement var16 = (IEStatement)this.cfg.getInstruction(var14);
                        boolean var3 = var16.visitDepthPre(new bO(this, var12, var1));
                        if (var3) {
                           var5.set(var6, this.ectx.createNop(var7));
                           var2++;
                           break;
                        }
                     }
                  }
               }
            }
         }
      }

      return this.postPerform(var2);
   }
}
