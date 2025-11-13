package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.client.S;
import com.pnfsoftware.jeb.core.units.code.android.controlflow.BasicBlock;
import com.pnfsoftware.jeb.core.units.code.android.ir.AbstractDOptimizer;
import com.pnfsoftware.jeb.core.units.code.android.ir.DOpcodeType;
import com.pnfsoftware.jeb.core.units.code.android.ir.IDArrayElt;
import com.pnfsoftware.jeb.core.units.code.android.ir.IDExpression;
import com.pnfsoftware.jeb.core.units.code.android.ir.IDImm;
import com.pnfsoftware.jeb.core.units.code.android.ir.IDInstruction;
import com.pnfsoftware.jeb.core.units.code.android.ir.IDInvokeInfo;
import com.pnfsoftware.jeb.core.units.code.android.ir.IDNewArrayInfo;
import com.pnfsoftware.jeb.core.units.code.android.ir.IDNewInfo;
import com.pnfsoftware.jeb.core.units.code.android.ir.IDVar;
import com.pnfsoftware.jeb.util.logging.GlobalLog;
import com.pnfsoftware.jeb.util.logging.ILogger;

public class cao extends AbstractDOptimizer {
   private static final ILogger pC = GlobalLog.getLogger(cao.class, Integer.MAX_VALUE);
   private boolean A;

   public cao() {
      this.getPluginInformation().setDescription(S.L("Dead code removal"));
   }

   @Override
   public int perform() {
      byte var1 = 0;
      return var1 + this.pC();
   }

   public int pC() {
      int var1 = 0;
      this.analyzeChains();

      int var4;
      for (BasicBlock var3 : this.cfg) {
         do {
            this.checkInterrupted();
            var4 = 0;
            int var5 = 0;

            while (var5 < var3.size()) {
               IDInstruction var6 = (IDInstruction)var3.get(var5);
               if (var6.isNop()) {
                  var5++;
               } else if (Boolean.TRUE.equals(var6.getData("KEEP_INSTRUCTION"))) {
                  var5++;
               } else if (!this.A
                  && var6.isInvoke()
                  && var6.getInvokeData() instanceof IDNewInfo var7
                  && "Ljava/lang/String;-><init>(Ljava/lang/String;)V".equals(var7.getMethodSignature())
                  && var7.getArgument(0) instanceof IDImm var18
                  && var18.isString()) {
                  var5++;
               } else {
                  long var17 = var6.getOffset();
                  if (!var6.hasSideEffects(this.ctx, true)) {
                     this.dfa.invalidateForRemoval(var17);
                     var6.transformToNop();
                     var5++;
                     var4++;
                  } else if (!var6.isAssign()) {
                     var5++;
                  } else {
                     IDExpression var19 = var6.getAssignDestination();
                     IDExpression var10 = var6.getAssignSource();
                     if (!this.A && var19 instanceof IDArrayElt var20) {
                        if (var20.getArray() instanceof IDNewArrayInfo var21
                           && var20.getIndex() instanceof IDImm var22
                           && var21.getCountOfInitialValues() == 0
                           && var21.getSize() instanceof IDImm var25) {
                           int var26 = (int)var25.asImm().getRawValue();
                           int var16 = (int)var22.asImm().getRawValue();
                           if (var26 > 0 && var26 <= 100000 && var16 >= 0 && var16 < var26 && !var10.hasSideEffects(this.ctx, true)) {
                              this.dfa.invalidateForRemoval(var17);
                              var6.transformToNop();
                              var5++;
                              var4++;
                              continue;
                           }
                        }

                        var5++;
                     } else if (!(var19 instanceof IDVar)) {
                        var5++;
                     } else if (var19 == var10) {
                        this.dfa.invalidateForRemoval(var17);
                        var6.transformToNop();
                        var5++;
                        var4++;
                     } else {
                        boolean var11 = false;
                        if (var6.hasUseSideEffects(true)) {
                           if (!(var10 instanceof IDInvokeInfo)) {
                              var5++;
                              continue;
                           }

                           var11 = true;
                        }

                        boolean var12 = true;

                        for (int var14 : this.dfa.getInstructionDefs(var17)) {
                           if (!this.dfa.checkNoUse(var17, var14)) {
                              var12 = false;
                              break;
                           }
                        }

                        if (!var12) {
                           var5++;
                        } else {
                           if (!var11) {
                              if (var6.canThrow()) {
                                 var5++;
                                 continue;
                              }

                              this.dfa.invalidateForRemoval(var17);
                              var6.transformToNop();
                           } else {
                              Object[] var10000 = new Object[0];
                              if (!var6.isAssignToVar() || !(var6.getAssignSource() instanceof IDInvokeInfo)) {
                                 var5++;
                                 continue;
                              }

                              this.dfa.invalidateForRemoval(var17);
                              var6.morph(DOpcodeType.IR_INVOKE, null, var6.getOperand2());
                           }

                           var5++;
                           var4++;
                        }
                     }
                  }
               }
            }

            var1 += var4;
         } while (var4 > 0);
      }

      if (var1 > 0) {
         this.invalidateChains();
      }

      return var1;
   }
}
