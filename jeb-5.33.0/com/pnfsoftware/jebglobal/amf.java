package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.units.code.asm.decompiler.IERoutineContext;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.IEGeneric;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.IEImm;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.IEVar;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.opt.AbstractEPatternOptimizer;
import com.pnfsoftware.jeb.util.logging.StructuredLogger;
import java.util.Arrays;
import java.util.Collection;

public class amf extends AbstractEPatternOptimizer {
   private static final StructuredLogger pC = aco.pC(amf.class);

   @Override
   protected Collection getPatterns() {
      return Arrays.asList(
         alz.ys,
         alz.ld,
         alz.gp,
         alz.oT,
         alz.fI,
         alz.pC,
         alz.A,
         alz.wS,
         alz.kS,
         alz.UT,
         alz.E,
         alz.sY,
         ame.sY,
         ame.ys,
         ame.kS,
         ame.wS,
         ame.UT,
         ame.E,
         ame.pC,
         ame.A,
         amd.pC,
         ama.pC,
         ama.A,
         ama.kS,
         ama.wS,
         ama.UT,
         ama.E,
         ama.sY,
         ama.ys,
         ama.ld,
         ama.gp,
         ama.oT,
         ama.fI,
         ama.NS,
         ama.WR,
         ama.vP,
         amc.pC,
         amc.A,
         amc.kS,
         amc.wS,
         amc.UT
      );
   }

   public static IEGeneric pC(IERoutineContext var0, IEGeneric var1, IEGeneric var2) {
      if (var1 instanceof IEVar) {
         if (!(var2 instanceof IEVar)) {
            throw new RuntimeException();
         } else {
            return var0.copyPairOfVariables((IEVar)var1, (IEVar)var2);
         }
      } else if (var1 instanceof IEImm) {
         if (!(var2 instanceof IEImm)) {
            throw new RuntimeException();
         } else {
            return var0.createCompose(var1, var2);
         }
      } else {
         throw new RuntimeException();
      }
   }
}
