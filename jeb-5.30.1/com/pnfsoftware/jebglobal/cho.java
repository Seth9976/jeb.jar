package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.units.code.android.controlflow.BasicBlock;
import com.pnfsoftware.jeb.core.units.code.android.ir.AbstractDOptimizer;
import com.pnfsoftware.jeb.core.units.code.android.ir.DCopyOptions;
import com.pnfsoftware.jeb.core.units.code.android.ir.DOptimizerType;
import com.pnfsoftware.jeb.core.units.code.android.ir.DUtil;
import com.pnfsoftware.jeb.core.units.code.android.ir.DexDecEvaluationException;
import com.pnfsoftware.jeb.core.units.code.android.ir.IDEmuFrame;
import com.pnfsoftware.jeb.core.units.code.android.ir.IDExpression;
import com.pnfsoftware.jeb.core.units.code.android.ir.IDField;
import com.pnfsoftware.jeb.core.units.code.android.ir.IDImm;
import com.pnfsoftware.jeb.core.units.code.android.ir.IDInstruction;
import com.pnfsoftware.jeb.core.units.code.android.ir.IDState;
import com.pnfsoftware.jeb.core.units.code.android.ir.IDVar;
import java.util.Iterator;
import java.util.Set;

public class cho extends AbstractDOptimizer {
   boolean q;
   boolean RF;
   boolean xK;

   public cho(boolean var1, boolean var2, boolean var3) {
      this.addTag("deobfuscator");
      if (var1) {
         this.setType(DOptimizerType.UNSAFE);
      }

      this.q = var1;
      this.RF = var2;
      this.xK = var3;
   }

   public cho() {
      this(true, true, true);
   }

   @Override
   public int perform() {
      int var1 = 0;
      Iterator var2 = this.cfg.iterator();

      while (true) {
         BasicBlock var3;
         BasicBlock var8;
         Integer var11;
         while (true) {
            IDInstruction var4;
            boolean var5;
            IDExpression var6;
            while (true) {
               if (!var2.hasNext()) {
                  if (var1 > 0) {
                     this.cleanGraph();
                     this.cfg.invalidateDataFlowAnalysis();
                  }

                  return var1;
               }

               var3 = (BasicBlock)var2.next();
               if (var3.size() >= 2 && ((IDInstruction)var3.getLast()).isJump() && (var4 = (IDInstruction)var3.get(var3.size() - 2)).isAssign()) {
                  var5 = false;
                  var6 = var4.getAssignDestination();
                  if (var6 instanceof IDVar) {
                     break;
                  }

                  if (this.q && var6 instanceof IDField) {
                     var5 = true;
                     break;
                  }
               }
            }

            IDExpression var7 = var4.getAssignSource();
            if (this.RF && var7 instanceof IDImm || this.xK && bto.oW(var7)) {
               var8 = var3.getOutputBlock(0);
               if (var8.size() == 1 && ((IDInstruction)var8.get(0)).isJcondOrSwitch()) {
                  IDInstruction var9 = (IDInstruction)var8.get(0);
                  if (!var9.hasUseSideEffects(false)
                     && (var6 instanceof IDVar var10 ? q(var9, var10) : var6 instanceof IDField && var9.find(var6, 0, 1, null) != null)) {
                     IDVar var20 = null;
                     if (var5) {
                        var20 = ((bud)this.ctx).retrieveTemporaryVariable(var7.getType(), 0);
                     }

                     IDState var12 = this.ctx.getGlobalContext().createState();

                     try {
                        var12.setSubRoutineInvocationPolicy(255);
                        var12.setFieldAccessPolicy(255);
                        var12.enableEmulator(true);
                        var12.enableSandbox(true);
                        var12.setMaxIterationCount(2);
                        var12.pushContext("context");
                        IDEmuFrame var13 = var12.pushFrame(this.ctx.getMethodSignature());
                        if (var5) {
                           DCopyOptions var14 = new DCopyOptions();
                           var14.replmap_eq.put(var6, var20);
                           var4 = var4.copy(var14);
                        }

                        var13.setPC((int)var4.getOffset());
                        var4.evaluate(var12);
                        if (var5) {
                           DCopyOptions var22 = new DCopyOptions();
                           var22.replmap_eq.put(var6, var20);
                           var9 = var9.copy(var22);
                           if (!q(var9, var20)) {
                              continue;
                           }
                        }

                        var13.setPC((int)var9.getOffset());
                        var9.evaluate(var12);
                        var11 = var13.getNextPC();
                        break;
                     } catch (DexDecEvaluationException var18) {
                     } finally {
                        var12.dispose();
                     }
                  }
               }
            }
         }

         if (var11 != null) {
            IDInstruction var21 = (IDInstruction)var3.getLast();
            this.cfg.reconnectEdge(var3, var8, this.cfg.getBlockAt((long)var11.intValue()));
            var21.setBranchTarget(var11);
            var1++;
         }
      }
   }

   static boolean q(IDInstruction var0, IDVar var1) {
      Set var2 = DUtil.collectUniqueVarIds(var0);
      return var2.size() == 1 && (Integer)var2.iterator().next() == var1.getId();
   }
}
