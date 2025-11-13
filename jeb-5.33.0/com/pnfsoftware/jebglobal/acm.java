package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.units.code.asm.cfg.CFG;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.exceptions.IllegalIntermediateExpressionException;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.EUtil;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.IEAssign;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.IEGeneric;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.IEGroup;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.IEImm;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.IERange;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.IESlice;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.IEStatement;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.IEVar;
import com.pnfsoftware.jeb.util.logging.StructuredLogger;
import java.util.IdentityHashMap;
import java.util.List;

public class acm {
   private static final StructuredLogger pC = aco.pC(acm.class);

   public static void pC(List var0) {
      boolean var1 = pC(var0, 1);
      if (!var1) {
         throw new IllegalIntermediateExpressionException("The list of IR statements contains reused elements that could not be duplicated");
      }
   }

   public static boolean pC(CFG var0) {
      IdentityHashMap var1 = new IdentityHashMap();

      for (IEStatement var3 : var0.instructions()) {
         pC(var3, var1);
      }

      for (IEGeneric var6 : var1.keySet()) {
         int var4 = (Integer)var1.get(var6);
         if (var4 > 1
            && (!(var6 instanceof IEImm) || ((IEImm)var6).isMutable())
            && !(var6 instanceof IEVar)
            && !(var6 instanceof IERange)
            && !(var6 instanceof IEGroup)) {
            pC.debug("Re-used IR item: %s", var6);
            return false;
         }
      }

      return true;
   }

   private static boolean pC(List var0, int var1) {
      IdentityHashMap var2 = new IdentityHashMap();

      for (IEStatement var4 : var0) {
         pC(var4, var2);
      }

      boolean var8 = false;

      for (IEGeneric var5 : var2.keySet()) {
         int var6 = (Integer)var2.get(var5);
         if (var6 > 1
            && (!(var5 instanceof IEImm) || ((IEImm)var5).isMutable())
            && !(var5 instanceof IEVar)
            && !(var5 instanceof IERange)
            && !(var5 instanceof IEGroup)) {
            var8 = true;
         }
      }

      if (!var8) {
         return true;
      } else if (var1-- == 0) {
         return false;
      } else {
         for (int var10 = 0; var10 < var0.size(); var10++) {
            IEStatement var11 = (IEStatement)var0.get(var10);
            IEStatement var12 = (IEStatement)var11.duplicate();
            var0.set(var10, var12);
         }

         return pC(var0, var1);
      }
   }

   private static void pC(IEGeneric var0, IdentityHashMap var1) {
      Integer var2 = (Integer)var1.get(var0);
      if (var2 != null) {
         var1.put(var0, var2 + 1);
      } else {
         var1.put(var0, 1);
      }

      for (IEGeneric var4 : EUtil.getSubExpressions(var0)) {
         pC(var4, var1);
      }
   }

   public static void A(List var0) {
      for (int var1 = 0; var1 < var0.size(); var1++) {
         IEStatement var2 = (IEStatement)var0.get(var1);
         if (var2 instanceof IEAssign var3 && var3.getDstOperand() instanceof IESlice var5 && var5.getWholeExpression() instanceof IEVar) {
            IEVar var6 = (IEVar)var5.getWholeExpression();
            int var7 = var6.getBitsize();
            int var8 = var5.getBitStart();
            int var9 = var5.getBitEnd();
            IEGeneric var10 = var3.getSrcOperand();
            IEGeneric var11;
            if (var8 == 0) {
               if (var9 == var7) {
                  var11 = var10;
               } else {
                  var11 = EUtil.compose(var2.getContext(), var10, var6.slice(var9, var7));
               }
            } else if (var9 == var7) {
               var11 = EUtil.compose(var2.getContext(), var6.slice(0, var8), var10);
            } else {
               var11 = EUtil.compose(var2.getContext(), var6.slice(0, var8), var10, var6.slice(var9, var7));
            }

            var0.set(var1, var3.duplicateWithNewOperands(var6, var11));
         }
      }
   }
}
