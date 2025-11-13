package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.units.code.android.JvmMethodSig;
import com.pnfsoftware.jeb.core.units.code.android.controlflow.CFG;
import com.pnfsoftware.jeb.core.units.code.android.ir.AbstractDOptimizer;
import com.pnfsoftware.jeb.core.units.code.android.ir.DInvokeType;
import com.pnfsoftware.jeb.core.units.code.android.ir.DOptimizerType;
import com.pnfsoftware.jeb.core.units.code.android.ir.IDArrayElt;
import com.pnfsoftware.jeb.core.units.code.android.ir.IDCallInfo;
import com.pnfsoftware.jeb.core.units.code.android.ir.IDExpression;
import com.pnfsoftware.jeb.core.units.code.android.ir.IDField;
import com.pnfsoftware.jeb.core.units.code.android.ir.IDInstruction;
import com.pnfsoftware.jeb.core.units.code.android.ir.IDMethodContext;
import com.pnfsoftware.jeb.core.units.code.android.ir.IDVar;
import com.pnfsoftware.jeb.util.base.Assert;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.LongAdder;

public class cfe extends AbstractDOptimizer {
   private static final LongAdder q = new LongAdder();
   private static final int RF = 3;

   public cfe() {
      super(DOptimizerType.UNSAFE);
      this.addTag("inliner");
   }

   @Override
   public int perform() {
      if (bto.q(this.g)) {
         return 0;
      } else {
         int var1 = 0;
         ConcurrentHashMap var2 = (ConcurrentHashMap)this.g.getData("keyFailedNeutDecr", var0 -> new ConcurrentHashMap());

         label68:
         for (IDInstruction var4 : this.cfg.instructions()) {
            if (var4.isInvoke() && var4.getInvokeData() instanceof IDCallInfo var5) {
               String var12 = var5.getMethodSignature();
               if (((LongAdder)var2.getOrDefault(var12, q)).longValue() < 3L
                  && (var5.getInvokeType() != DInvokeType.DIRECT || !var5.getMethodName().equals("<init>"))) {
                  JvmMethodSig var7 = JvmMethodSig.parse(var12);
                  if (var5.hasThis()) {
                     var7.partypes.add(0, var7.csig);
                  }

                  int var8 = 0;

                  for (IDExpression var10 : var5.getArguments()) {
                     if (!bto.gO(var10)) {
                        String var11 = (String)var7.partypes.get(var8);
                        if (var11.length() != 1 && !var11.startsWith("L")) {
                           Assert.a(var11.startsWith("["));
                           continue label68;
                        }
                     }

                     var8++;
                  }

                  if (!this.q(var5)) {
                     ((LongAdder)var2.computeIfAbsent(var12, var0 -> new LongAdder())).increment();
                  } else {
                     var2.remove(var12);
                     var4.transformToNop();
                     this.cfg.invalidateDataFlowAnalysis();
                     var1++;
                  }
               }
            }
         }

         return var1;
      }
   }

   boolean q(IDCallInfo var1) {
      bvv var2 = new bvv(this.ctx);
      if (!bto.RF(this.g, var1.getMethodSignature())) {
         var2.q(10);
      }

      var2.xK(1);
      IDMethodContext var3 = var2.q(var1);
      if (var3 == null) {
         return false;
      } else {
         CFG var4 = var3.getCfg();
         bup var5 = null;

         for (IDInstruction var7 : var4.instructions()) {
            if (var7.hasUseSideEffects(false)) {
               return false;
            }

            if (var7.isThrow()) {
               return false;
            }

            if (var7.isAssign()) {
               IDExpression var8 = var7.getAssignDestination();
               if (var8 instanceof IDField) {
                  return false;
               }

               if (var8 instanceof IDArrayElt var9) {
                  if (!(var9.getArray() instanceof IDVar var10)) {
                     return false;
                  }

                  if (var5 == null) {
                     var5 = new bup(var3);
                     if (!var5.Uv()) {
                        return false;
                     }
                  }

                  int var12 = var10.getId();
                  if (var5.Dw().contains(var12) && var5.xK().contains(var12)) {
                     return false;
                  }
               }
            }
         }

         return true;
      }
   }
}
