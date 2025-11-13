package com.pnfsoftware.jeb.corei.parsers.ethereum;

import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.IEGeneric;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.IEImm;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.IEUntranslatedInstruction;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.opt.AbstractEExpressionOptimizer;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.opt.DataChainsUpdatePolicy;
import com.pnfsoftware.jeb.util.logging.GlobalLog;
import com.pnfsoftware.jeb.util.logging.ILogger;
import java.math.BigInteger;

public class rQ extends AbstractEExpressionOptimizer {
   private static final ILogger pC = GlobalLog.getLogger(rQ.class);

   public rQ() {
      super(DataChainsUpdatePolicy.UPDATE_IF_REQUIRED);
      this.skipStatementProcessing = false;
   }

   @Override
   protected AbstractEExpressionOptimizer.EOR optimizeExpression(IEGeneric var1) {
      if (var1 instanceof IEUntranslatedInstruction var2) {
         int var3 = (Integer)var2.getTag();
         if (var3 >= 161 && var3 <= 164) {
            IEGeneric var4 = var2.getParameterExpression(2);
            if (var4 instanceof IEImm && var2.getTag("eventName") == null) {
               BigInteger var5 = ((IEImm)var4).getUnsignedValue();
               io var6 = io.pC();
               String[] var7 = var6.pC(var5);
               if (var7 != null) {
                  String var8 = var7[0];
                  var2.setTag("eventName", var8);
               } else {
                  var2.setTag("eventName", "unknown_" + var5.toString(16));
               }
            }
         }
      }

      return null;
   }
}
