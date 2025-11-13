package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.units.code.asm.decompiler.IERoutineContext;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.IEGeneric;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.IEImm;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.IEVar;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.opt.AbstractEPatternOptimizer;
import com.pnfsoftware.jeb.util.logging.StructuredLogger;
import java.util.Arrays;
import java.util.Collection;

public class aon extends AbstractEPatternOptimizer {
   private static final StructuredLogger q = aeg.q(aon.class);

   @Override
   protected Collection getPatterns() {
      return Arrays.asList(
         aoh.gO,
         aoh.nf,
         aoh.gP,
         aoh.za,
         aoh.lm,
         aoh.q,
         aoh.RF,
         aoh.Dw,
         aoh.xK,
         aoh.Uv,
         aoh.oW,
         aom.gO,
         aom.nf,
         aom.xK,
         aom.Dw,
         aom.Uv,
         aom.oW,
         aom.q,
         aom.RF,
         aol.q,
         aoi.q,
         aoi.RF,
         aoi.xK,
         aoi.Dw,
         aoi.Uv,
         aoi.oW,
         aoi.gO,
         aoi.nf,
         aoi.gP,
         aoi.za,
         aoi.lm,
         aoi.zz,
         aoi.HF,
         aoi.JY,
         aoi.LK,
         aok.q,
         aok.RF,
         aok.xK,
         aok.Dw,
         aok.Uv
      );
   }

   static IEGeneric q(IERoutineContext var0, IEGeneric var1, IEGeneric var2) {
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
