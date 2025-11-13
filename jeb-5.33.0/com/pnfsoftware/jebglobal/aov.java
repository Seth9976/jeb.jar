package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.units.code.AddressableInstruction;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.IERoutineContext;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.IEAssign;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.IECompose;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.IEGeneric;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.IEVar;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.opt.AbstractEOptimizer;
import java.util.Collection;
import java.util.HashMap;

public class aov extends AbstractEOptimizer {
   boolean pC;

   public aov(boolean var1) {
      this.pC = var1;
   }

   public aov() {
      this(true);
   }

   @Override
   protected int perform() {
      int var1 = 0;
      IEVar[] var2 = new IEVar[2];

      for (AddressableInstruction var4 : this.cfg.addressableInstructions()) {
         if (var4.getInstruction() instanceof IEAssign var5) {
            boolean var7;
            IECompose var18;
            if (var5.getDstOperand() instanceof IECompose var9) {
               var18 = var9;
               var7 = true;
            } else {
               if (!this.pC || !(var5.getSrcOperand() instanceof IECompose var8)) {
                  continue;
               }

               var18 = var8;
               var7 = false;
            }

            if (pC(this.ectx, var18, var2)) {
               IEVar var19 = var2[0];
               IEVar var21 = var2[1];
               alr var11 = new alr(this.ectx, this.cfg);
               HashMap var12 = new HashMap();
               long var13 = var4.getOffset();
               var11.pC(var13, var19.getId(), var7);
               var12.put(0, var11.pC());
               var11.pC(var13, var21.getId(), var7);
               var12.put(1, var11.pC());
               if (!var12.isEmpty()) {
                  IEGeneric var15 = amf.pC(this.ectx, var19, var21);
                  if (var15 != null) {
                     int var16 = var19.getBitsize();
                     var1 += alr.pC(this.cfg, (Collection)var12.get(0), var19, var15.part(var16));
                     var1 += alr.pC(this.cfg, (Collection)var12.get(1), var21, var15.slice(var16));
                     this.cfg.invalidateDataFlowAnalysis();
                  }
               }
            }
         }
      }

      if (var1 > 0) {
         this.cfg.invalidateDataFlowAnalysis();
      }

      return var1;
   }

   public static boolean pC(IERoutineContext var0, IECompose var1, IEVar[] var2) {
      if (var1.getPartsCount() == 2
         && var1.getLowPart() instanceof IEVar var3
         && var1.getHighPart() instanceof IEVar var4
         && var3.getBitsize() == var4.getBitsize()) {
         Integer var8 = var0.getUnderlyingRegisterId(var3.getId());
         Integer var6 = var0.getUnderlyingRegisterId(var4.getId());
         if (var8 != null && var6 != null && var8 + var3.getBitsize() == var6) {
            if (var2 != null) {
               var2[0] = var3;
               var2[1] = var4;
            }

            return true;
         }
      }

      return false;
   }
}
