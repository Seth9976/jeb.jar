package com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.opt;

import com.pnfsoftware.jeb.core.units.code.asm.cfg.CFG;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.IERoutineContext;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.IEGeneric;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.IEStatement;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.compiler.EPatternCompiler;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.compiler.EPatternMatcher;
import com.pnfsoftware.jeb.util.logging.StructuredLogger;
import com.pnfsoftware.jebglobal.aeg;
import java.util.ArrayList;
import java.util.Collection;
import java.util.IdentityHashMap;
import java.util.Map;

public abstract class AbstractEPatternOptimizer extends AbstractEOptimizer {
   private static final StructuredLogger logger = aeg.q(AbstractEPatternOptimizer.class);

   public AbstractEPatternOptimizer() {
      this(true);
   }

   public AbstractEPatternOptimizer(boolean var1) {
      super(var1 ? DataChainsUpdatePolicy.UPDATE_IF_OPTIMIZED : DataChainsUpdatePolicy.UPDATE_IF_REQUIRED);
   }

   protected abstract Collection getPatterns();

   private static Map generateDescDepthsMap(CFG var0) {
      IdentityHashMap var1 = new IdentityHashMap();

      for (IEStatement var3 : var0.instructions()) {
         calcDescDepthsRecurse(var3, var1);
      }

      return var1;
   }

   private static Map generateDescDepthsMap(IEGeneric var0) {
      IdentityHashMap var1 = new IdentityHashMap();
      calcDescDepthsRecurse(var0, var1);
      return var1;
   }

   private static int calcDescDepthsRecurse(IEGeneric var0, Map var1) {
      ArrayList var3 = new ArrayList();
      var0.collectSubExpressions(var3);
      int var2;
      if (var3.isEmpty()) {
         var2 = 0;
      } else {
         int var4 = 0;

         for (IEGeneric var6 : var3) {
            int var7 = calcDescDepthsRecurse(var6, var1);
            if (var7 > var4) {
               var4 = var7;
            }
         }

         var2 = 1 + var4;
      }

      var1.put(var0, var2);
      return var2;
   }

   @Override
   protected int perform() {
      int var1 = 0;
      Map var2 = null;
      boolean var3 = false;

      for (EPatternCompiler.EPattern var5 : this.getPatterns()) {
         EPatternMatcher var6 = new EPatternMatcher(var5, this.cfg, this.ectx);
         if (var2 == null) {
            var2 = generateDescDepthsMap(this.cfg);
         }

         var6.setIRDepthsMap(var2);
         EPatternMatcher.Result var7 = null;

         while (true) {
            var7 = var6.search(var7);
            if (var7 == null) {
               break;
            }

            if (var6.replace(var7, false)) {
               if (!var3 && var6.getPattern().getOutput() != null && (var6.getPattern().getOutput().getFlags() & 256) != 0) {
                  var3 = true;
               }

               var2 = null;
               var1++;
            }
         }
      }

      return this.postPerform(var1, var3);
   }

   @Override
   public IEGeneric performOnExpression(IEGeneric var1, IERoutineContext var2) {
      this.ectx = var2;
      int var3 = 0;
      Map var4 = null;

      for (EPatternCompiler.EPattern var6 : this.getPatterns()) {
         EPatternMatcher var7 = new EPatternMatcher(var6, var1, var2);
         EPatternMatcher.Result var8 = null;

         while (true) {
            if (var4 == null) {
               var4 = generateDescDepthsMap(var1);
            }

            var7.setIRDepthsMap(var4);
            var8 = var7.search(var8);
            if (var8 == null) {
               break;
            }

            if (var7.replace(var8, false)) {
               var4 = null;
               var3++;
               if (var8.getOptimizedExpression() != var1) {
                  var1 = var8.getOptimizedExpression();
                  return var3 <= 0 ? null : var1;
               }
            }
         }
      }

      return var3 <= 0 ? null : var1;
   }
}
