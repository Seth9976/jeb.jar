package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.IEGeneric;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.compiler.INodeHandler;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.compiler.O;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.compiler.SubstitutionDefinition;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.compiler.Util;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.opt.AbstractEExpressionOptimizer;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.opt.DataChainsUpdatePolicy;
import com.pnfsoftware.jeb.util.logging.StructuredLogger;

public class apc extends AbstractEExpressionOptimizer {
   private static final StructuredLogger A = aco.pC(apc.class);
   private static INodeHandler kS = new apd();
   public static final SubstitutionDefinition[] pC = new SubstitutionDefinition[]{
      Util.SD(
         Util.N(O.SLICE_HALF1, Util.N(O.DIV_U, Util.N(O.COMPOSE_2EQ, Util.L(0), Util.LC(0L)), Util.N(O.COMPOSE_2EQ, Util.L(1), Util.LC(0L)))),
         Util.N(O.DIV_U, Util.L(0), Util.L(1))
      ),
      Util.SD(
         Util.N(O.SLICE_HALF1, Util.N(O.REM_U, Util.N(O.COMPOSE_2EQ, Util.L(0), Util.LC(0L)), Util.N(O.COMPOSE_2EQ, Util.L(1), Util.LC(0L)))),
         Util.N(O.REM_U, Util.L(0), Util.L(1))
      ),
      Util.SD(
         Util.N(
            O.SLICE_HALF1,
            Util.N(
               O.DIV_S,
               Util.N(O.COMPOSE_2EQ, Util.L(0), Util.N(O.COND, Util.N(O.SLICE_LASTBIT, Util.L(0)), Util.LC(-1L), Util.LC(0L))),
               Util.N(O.COMPOSE_2EQ, Util.L(1), Util.N(O.COND, Util.N(O.SLICE_LASTBIT, Util.L(1)), Util.LC(-1L), Util.LC(0L)))
            )
         ),
         Util.N(O.DIV_S, Util.L(0), Util.L(1))
      ),
      Util.SD(
         Util.N(
            O.SLICE_HALF1,
            Util.N(
               O.REM_S,
               Util.N(O.COMPOSE_2EQ, Util.L(0), Util.N(O.COND, Util.N(O.SLICE_LASTBIT, Util.L(0)), Util.LC(-1L), Util.LC(0L))),
               Util.N(O.COMPOSE_2EQ, Util.L(1), Util.N(O.COND, Util.N(O.SLICE_LASTBIT, Util.L(1)), Util.LC(-1L), Util.LC(0L)))
            )
         ),
         Util.N(O.REM_S, Util.L(0), Util.L(1))
      ),
      Util.SD(Util.N(O.SLICE_HALF1, Util.N(O.DIV_U, Util.N(O.COMPOSE_2EQ, Util.L(0), Util.LC(0L)), Util.L(1, 0, 1, kS))), Util.N(O.DIV_U, Util.L(0), Util.L(1))),
      Util.SD(Util.N(O.SLICE_HALF1, Util.N(O.REM_U, Util.N(O.COMPOSE_2EQ, Util.L(0), Util.LC(0L)), Util.L(1, 0, 1, kS))), Util.N(O.REM_U, Util.L(0), Util.L(1))),
      Util.SD(Util.N(O.SLICE_HALF1, Util.N(O.DIV_S, Util.N(O.COMPOSE_2EQ, Util.L(0), Util.LC(0L)), Util.L(1, 0, 1, kS))), Util.N(O.DIV_S, Util.L(0), Util.L(1))),
      Util.SD(Util.N(O.SLICE_HALF1, Util.N(O.REM_S, Util.N(O.COMPOSE_2EQ, Util.L(0), Util.LC(0L)), Util.L(1, 0, 1, kS))), Util.N(O.REM_S, Util.L(0), Util.L(1)))
   };

   public apc() {
      super(DataChainsUpdatePolicy.UPDATE_IF_REQUIRED);
   }

   @Override
   protected AbstractEExpressionOptimizer.EOR optimizeExpression(IEGeneric var1) {
      IEGeneric var2 = this.doSubstitution(var1, pC);
      return var2 != null ? AbstractEExpressionOptimizer.EOR.create(var2) : null;
   }
}
