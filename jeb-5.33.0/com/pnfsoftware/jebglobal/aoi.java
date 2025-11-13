package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.units.code.asm.cfg.BasicBlock;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.IEGlobalContext;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.IEAssign;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.IEGeneric;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.IEImm;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.IEMem;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.IEStatement;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.IEVar;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.opt.AbstractEOptimizer;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.opt.DataChainsUpdatePolicy;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.opt.OptimizerMode;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.opt.OptimizerType;
import com.pnfsoftware.jeb.core.units.code.asm.items.INativeContinuousItem;
import com.pnfsoftware.jeb.core.units.codeobject.ProcessorType;
import com.pnfsoftware.jeb.util.logging.StructuredLogger;

public class aoi extends AbstractEOptimizer {
   private static final StructuredLogger pC = aco.pC(aoi.class);
   private int A;

   public aoi() {
      super(DataChainsUpdatePolicy.UPDATE_IF_OPTIMIZED, OptimizerType.UNSAFE);
      this.setRequiredModeThreshold(OptimizerMode.AGGRESSIVE);
   }

   @Override
   protected int perform() {
      this.A = 0;

      for (BasicBlock var2 : this.cfg) {
         for (IEStatement var4 : var2) {
            if (var4 instanceof IEAssign var5) {
               IEGeneric var6 = var5.getSrcOperand();
               IEGeneric var7 = var5.getDstOperand();
               if (this.pC(var6)) {
                  IEVar var8 = this.pC(var7.getBitsize());
                  if (var4.replaceSubExpression(var6, var8)) {
                     this.A++;
                  }
               } else if (!this.A(var6)) {
                  continue;
               }

               if (var7 instanceof IEVar && ((IEVar)var7).isStackVariable() && var7.addFlags(32)) {
                  this.A++;
               }
            }
         }
      }

      for (IEStatement var10 : this.cfg.instructions()) {
         var10.visitDepthPost(new aoj(this));
      }

      return this.postPerform(this.A);
   }

   boolean pC(IEGeneric var1) {
      if (var1 instanceof IEVar && ((IEVar)var1).isGlobalVariable()) {
         String var6 = ((IEVar)var1).getName();
         return this.pC(var6);
      } else {
         if (var1 instanceof IEMem) {
            IEGeneric var2 = ((IEMem)var1).getReference();
            if (var2 instanceof IEVar && ((IEVar)var2).isGlobalReference()) {
               String var7 = ((IEVar)var2).getName();
               return this.pC(var7);
            }

            if (var2 instanceof IEMem) {
               return this.pC(var2);
            }

            ProcessorType var3 = this.ectx.getNativeContext().getProcessor().getType();
            if (var3.isIntel()) {
               IEGeneric var4 = ((IEMem)var1).getSegment();
               if (var4 instanceof IEVar) {
                  String var5 = ((IEVar)var4).getName();
                  if (var5.equals("$gs") && var2 instanceof IEImm && ((IEImm)var2).canReadAsLong() && var2.asImm().getValueAsLong() == 20L) {
                     return true;
                  }

                  if (var5.equals("$fs") && var2 instanceof IEImm && ((IEImm)var2).canReadAsLong() && var2.asImm().getValueAsLong() == 40L) {
                     return true;
                  }
               }
            }
         }

         return false;
      }
   }

   boolean A(IEGeneric var1) {
      if (var1 instanceof IEImm var2 && var2.canReadAsAddress()) {
         long var3 = var2.getValueAsAddress();
         INativeContinuousItem var5 = this.ectx.getNativeContext().getNativeItemAt(var3);
         if (var5 != null) {
            return this.pC(var5.getName());
         }
      }

      if (var1 instanceof IEVar && ((IEVar)var1).isVirtualRegister()) {
         String var7 = ((IEVar)var1).getName();
         return this.pC(var7);
      } else {
         if (var1 instanceof IEMem) {
            IEGeneric var6 = ((IEMem)var1).getReference();
            if (var6 instanceof IEVar && ((IEVar)var6).isVirtualRegister()) {
               String var8 = ((IEVar)var6).getName();
               return this.pC(var8);
            }

            if (var6 instanceof IEMem) {
               return this.A(var6);
            }
         }

         return false;
      }
   }

   boolean kS(IEGeneric var1) {
      return this.pC(var1) || this.A(var1);
   }

   boolean pC(String var1) {
      if (var1 == null) {
         return false;
      } else {
         var1 = var1.toLowerCase();
         return !var1.contains("guard") ? false : var1.contains("stk") || var1.contains("stack");
      }
   }

   IEVar pC(int var1) {
      IEGlobalContext var2 = this.ectx.getGlobalContext();
      String var3 = A(var1);
      IEVar var4 = var2.getVariableByName(var3);
      if (var4 == null) {
         var4 = var2.createVirtualRegister(var3, var1);
      }

      return var4;
   }

   public static String A(int var0) {
      return "pseudoGuardStack" + var0;
   }
}
