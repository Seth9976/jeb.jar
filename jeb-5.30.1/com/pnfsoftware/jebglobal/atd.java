package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.units.code.AddressableInstruction;
import com.pnfsoftware.jeb.core.units.code.IDFA;
import com.pnfsoftware.jeb.core.units.code.asm.cfg.BasicBlock;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.EUtil;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.IEAssign;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.IEGeneric;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.IEImm;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.IEJump;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.IEOperation;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.IEStatement;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.IEVar;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.opt.AbstractEOptimizer;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.opt.DataChainsUpdatePolicy;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.concurrent.atomic.AtomicInteger;

public class atd extends AbstractEOptimizer {
   static final int q = 6;
   static final int RF = 100;
   private Set xK = new HashSet();

   public atd() {
      super(DataChainsUpdatePolicy.UPDATE_PERFORMED_INTERNALLY);
      this.addTag("deobfuscator");
   }

   @Override
   protected int perform() {
      if (!this.q()) {
         return 0;
      } else {
         int var1 = 0;

         label89:
         for (BasicBlock var3 : this.cfg) {
            AddressableInstruction var4 = var3.getLast2();
            if (EUtil.isConditionalJump((IEStatement)var4.getInstruction())) {
               IEJump var5 = (IEJump)var3.getLast();
               IEGeneric var6 = var5.getCondition();
               long var7 = var4.getOffset();

               for (int var9 = 0; var9 <= 6; var9++) {
                  if (EUtil.containsUndeterminedInvocations(var6) || EUtil.hasSideEffect(var6)) {
                     continue label89;
                  }

                  List var10 = var6.getExplicitlyUsed().getVarIds();
                  if (var10.size() != 1) {
                     continue label89;
                  }

                  int var11 = (Integer)var10.get(0);
                  IEVar var12 = this.ectx.getVariableById(var11);
                  IDFA var13 = this.cfg.doDataFlowAnalysis();
                  Long var14 = var13.checkSingleDef(var7, var11);
                  if (var14 == null || var14 == -1L) {
                     break;
                  }

                  IEStatement var15 = (IEStatement)this.cfg.getInstruction(var14);
                  if (!(var15 instanceof IEAssign) || !((IEAssign)var15).getDstOperand().equals(var12)) {
                     continue label89;
                  }

                  IEGeneric var16 = ((IEAssign)var15).getSrcOperand();
                  var7 = var14;
                  if (var9 == 0) {
                     var6 = var6.duplicate();
                  }

                  if (var6.replaceVar(var12, var16) == 0) {
                     continue label89;
                  }
               }

               if (this.q(var6) && !this.xK.contains(var6.toString())) {
                  Boolean var18 = null;

                  try {
                     var18 = this.RF(var6);
                  } catch (Exception var17) {
                     logger.catchingSilent(var17);
                  }

                  if (var18 == null) {
                     this.xK.add(var6.toString());
                  } else {
                     IEImm var19 = var18 ? EUtil.one(1) : EUtil.zero(1);
                     var5.setCondition(var19);
                     var1++;
                  }
               }
            }
         }

         if (var1 > 0) {
            this.cleanCfg();
            this.cfg.invalidateDataFlowAnalysis();
         }

         return this.postPerform(var1);
      }
   }

   private boolean q() {
      return avt.RF();
   }

   private Boolean RF(IEGeneric var1) throws Exception {
      try (avt var2 = new avt()) {
         if (!var2.q(var1)) {
            return null;
         }

         var2.q(100);
         Boolean var3 = var2.q();
         if (Boolean.FALSE.equals(var3)) {
            return false;
         }
      }

      try (avt var9 = new avt()) {
         if (!var9.q(EUtil.notL(var1))) {
            return null;
         }

         var9.q(100);
         Boolean var10 = var9.q();
         if (Boolean.FALSE.equals(var10)) {
            return true;
         }
      }

      return null;
   }

   boolean q(IEGeneric var1) {
      if (EUtil.isLikeImmediate(var1)) {
         return false;
      } else if (var1 instanceof IEOperation) {
         for (IEGeneric var3 : var1.asOperation().getOperands()) {
            if (var3.findByType(IEOperation.class) != null) {
               return true;
            }
         }

         return false;
      } else {
         AtomicInteger var2 = new AtomicInteger();
         var1.visitDepthPost(new ate(this, var2));
         return var2.get() >= 2;
      }
   }
}
