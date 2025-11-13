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
import com.pnfsoftware.jeb.util.collect.Maps;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.concurrent.atomic.AtomicInteger;

public class aqm extends AbstractEOptimizer {
   private Set pC = new HashSet();

   public aqm() {
      super(DataChainsUpdatePolicy.UPDATE_PERFORMED_INTERNALLY);
      this.addTag("deobfuscator");
   }

   @Override
   protected int perform() {
      if (!this.pC()) {
         return 0;
      } else {
         int var1 = 0;

         label92:
         for (BasicBlock var3 : this.cfg) {
            AddressableInstruction var4 = var3.getLast2();
            if (EUtil.isConditionalJump((IEStatement)var4.getInstruction())) {
               IEJump var5 = (IEJump)var3.getLast();
               IEGeneric var6 = var5.getCondition();
               long var7 = var4.getOffset();

               for (int var9 = 0; var9 <= 6; var9++) {
                  if (EUtil.containsUndeterminedInvocations(var6) || EUtil.hasSideEffect(var6)) {
                     continue label92;
                  }

                  List var10 = var6.getExplicitlyUsed().getVarIds();
                  if (var10.size() != 1) {
                     continue label92;
                  }

                  int var11 = (Integer)var10.get(0);
                  IEVar var12 = this.ectx.getVariableById(var11);
                  IDFA var13 = this.cfg.doDataFlowAnalysis();
                  Long var14 = var13.checkSingleDef(var7, var11);
                  if (var14 == null || var14 == -1L) {
                     break;
                  }

                  IEStatement var15 = (IEStatement)this.cfg.getInstruction(var14);
                  if (!(var15 instanceof IEAssign var16) || !var16.getDstOperand().equals(var12)) {
                     continue label92;
                  }

                  IEGeneric var17 = var16.getSrcOperand();
                  var7 = var14;
                  if (var9 == 0) {
                     var6 = var6.duplicate();
                  }

                  if (var6.replaceVar(var12, var17) == 0) {
                     continue label92;
                  }
               }

               if (this.pC(var6) && !this.pC.contains(var6.toString()) && ata.A(var6)) {
                  Boolean var19 = null;

                  try {
                     var19 = this.A(var6);
                  } catch (Exception var18) {
                     logger.catchingSilent(var18);
                  }

                  if (var19 == null) {
                     this.pC.add(var6.toString());
                  } else {
                     IEImm var20 = var19 ? EUtil.one(1) : EUtil.zero(1);
                     var5.setCondition(var20);
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

   private boolean pC() {
      return ata.pC();
   }

   private Boolean A(IEGeneric var1) throws Exception {
      try {
         Boolean var4;
         try (ata var2 = new ata()) {
            var2.pC(var1);
            var2.pC(100);
            Boolean var8 = var2.A();
            if (var8 == null) {
               return null;
            }

            if (Boolean.FALSE.equals(var8)) {
               return false;
            }

            var8 = var2.kS();
            if (!Boolean.FALSE.equals(var8)) {
               return null;
            }

            var4 = true;
         }

         return var4;
      } catch (Exception var7) {
         HashMap var3 = Maps.toMap("ir-expression", var1.toString());
         acj.pC(var7, var3);
         return null;
      }
   }

   boolean pC(IEGeneric var1) {
      if (EUtil.isLikeImmediate(var1)) {
         return false;
      } else if (var1 instanceof IEOperation var5) {
         for (IEGeneric var4 : var5.getOperands()) {
            if (var4.findByType(IEOperation.class) != null) {
               return true;
            }
         }

         return false;
      } else {
         AtomicInteger var2 = new AtomicInteger();
         var1.visitDepthPost(new aqn(this, var2));
         return var2.get() >= 2;
      }
   }
}
