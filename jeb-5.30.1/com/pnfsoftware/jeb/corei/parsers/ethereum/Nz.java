package com.pnfsoftware.jeb.corei.parsers.ethereum;

import com.pnfsoftware.jeb.core.units.code.asm.ChainedOperationResult;
import com.pnfsoftware.jeb.core.units.code.asm.cfg.CFG;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.AbstractNativeDecompilerExtension;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.IDecompiledMethod;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.INativeDecompilerContext;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.IEStatement;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.IEUntranslatedInstruction;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.IWildcardType;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.IWildcardTypeManager;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.opt.IEMasterOptimizer;

public class Nz extends AbstractNativeDecompilerExtension {
   @Override
   public ChainedOperationResult customizeIntermediateOptimizer(INativeDecompilerContext var1, IEMasterOptimizer var2) {
      tl var3 = (tl)var1.getConverter();
      if (var3.q().nf().xK()) {
         var2.registerOptimizer(new tw());
         var2.registerOptimizer(new EE());
      }

      var2.registerOptimizer(new nI());
      var2.registerOptimizer(new ej());
      var2.registerOptimizer(new Nt());
      var2.addDisregardedOutputFilter(new Uz(this, var2));
      return ChainedOperationResult.TRUE_CONTINUE;
   }

   @Override
   public ChainedOperationResult applyAdditionalTypes(IDecompiledMethod var1, CFG var2) {
      IWildcardTypeManager var3 = var1.getDecompiler().getIntermediateContext().getWildcardTypeManager();
      IWildcardType var4 = var3.create("address");

      for (IEStatement var6 : var2.instructions()) {
         if (var6 instanceof IEUntranslatedInstruction var7 && var7.getTag() instanceof Integer) {
            int var8 = (Integer)var7.getTag();
            switch (var8) {
               case 48:
               case 50:
               case 51:
               case 65:
                  var7.getReturnExpression().setType(var4, null);
                  break;
               case 49:
               case 63:
               case 255:
                  var7.getParameterExpression(0).setType(var4, null);
                  break;
               case 240:
                  var7.getReturnExpression().setType(var4, null);
                  break;
               case 241:
               case 242:
               case 244:
               case 250:
                  var7.getParameterExpression(1).setType(var4, null);
            }
         }
      }

      return ChainedOperationResult.TRUE_CONTINUE;
   }
}
