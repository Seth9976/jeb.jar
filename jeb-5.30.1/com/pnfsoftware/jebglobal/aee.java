package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.units.code.asm.cfg.BasicBlock;
import com.pnfsoftware.jeb.core.units.code.asm.cfg.CFG;
import com.pnfsoftware.jeb.core.units.code.asm.cfg.CFGUtil;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.IEConverter;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.IERoutineContext;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.exceptions.IllegalIntermediateExpressionException;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.ELocation;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.EUtil;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.IEAssign;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.IEGeneric;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.IEGroup;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.IEImm;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.IERange;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.IESlice;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.IEStatement;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.IEVar;
import com.pnfsoftware.jeb.core.units.code.asm.items.INativeMethodItem;
import com.pnfsoftware.jeb.util.logging.StructuredLogger;
import java.util.IdentityHashMap;
import java.util.List;

public class aee {
   private static final StructuredLogger q = aeg.q(aee.class);

   public static IERoutineContext q(IEConverter var0, long var1) {
      for (IERoutineContext var4 : var0.getGlobalContext().getRoutineContexts()) {
         Long var5 = var4.getRoutine().getMemoryAddress();
         if (var5 != null && var5 == var1) {
            return var4;
         }
      }

      return null;
   }

   public static IERoutineContext q(IEConverter var0, INativeMethodItem var1) {
      for (IERoutineContext var3 : var0.getGlobalContext().getRoutineContexts()) {
         if (var3.getRoutine() == var1) {
            return var3;
         }
      }

      return null;
   }

   public static ELocation RF(IEConverter var0, long var1) {
      for (IERoutineContext var4 : var0.getGlobalContext().getRoutineContexts()) {
         Long var5 = var4.convertNativeAddress(var1);
         if (var5 != null) {
            return new ELocation(var4, var5.intValue());
         }
      }

      return null;
   }

   public static void q(List var0) {
      boolean var1 = q(var0, 1);
      if (!var1) {
         throw new IllegalIntermediateExpressionException("The list of IR statements contains reused elements that could not be duplicated");
      }
   }

   public static boolean RF(List var0) {
      return q(var0, 0);
   }

   public static boolean q(CFG var0) {
      IdentityHashMap var1 = new IdentityHashMap();

      for (IEStatement var3 : var0.instructions()) {
         q(var3, var1);
      }

      for (IEGeneric var6 : var1.keySet()) {
         int var4 = (Integer)var1.get(var6);
         if (var4 > 1
            && (!(var6 instanceof IEImm) || ((IEImm)var6).isMutable())
            && !(var6 instanceof IEVar)
            && !(var6 instanceof IERange)
            && !(var6 instanceof IEGroup)) {
            q.debug("Re-used IR item: %s", var6);
            return false;
         }
      }

      return true;
   }

   private static boolean q(List var0, int var1) {
      IdentityHashMap var2 = new IdentityHashMap();

      for (IEStatement var4 : var0) {
         q(var4, var2);
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

         return q(var0, var1);
      }
   }

   private static void q(IEGeneric var0, IdentityHashMap var1) {
      Integer var2 = (Integer)var1.get(var0);
      if (var2 != null) {
         var1.put(var0, var2 + 1);
      } else {
         var1.put(var0, 1);
      }

      for (IEGeneric var4 : EUtil.getSubExpressions(var0)) {
         q(var4, var1);
      }
   }

   public static void xK(List var0) {
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

   public static CFG RF(CFG var0) {
      CFG var1 = CFGUtil.duplicateShallow(var0);

      for (BasicBlock var3 : var1.getBlocks()) {
         int var4 = 0;

         for (IEStatement var6 : var3) {
            IEStatement var7 = (IEStatement)var6.duplicate();
            var3.set(var4, var7);
            var4++;
         }
      }

      return var1;
   }
}
