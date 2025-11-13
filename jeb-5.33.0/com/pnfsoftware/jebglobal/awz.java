package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.units.code.AddressableInstruction;
import com.pnfsoftware.jeb.core.units.code.ICodePointer;
import com.pnfsoftware.jeb.core.units.code.IFlowInformation;
import com.pnfsoftware.jeb.core.units.code.asm.cfg.BasicBlock;
import com.pnfsoftware.jeb.core.units.code.asm.cfg.CFG;
import com.pnfsoftware.jeb.core.units.code.asm.items.INativeContinuousItem;
import com.pnfsoftware.jeb.core.units.code.asm.mangling.IManglingEngine;
import com.pnfsoftware.jeb.core.units.code.asm.mangling.IUnmangledData;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class awz extends aws {
   private final String pC = "extern";
   private final String A = "_ptr_";
   private List kS = new ArrayList();
   private Set wS;
   private IManglingEngine UT;

   public void pC(Set var1) {
      this.wS = var1;
   }

   public void pC(IManglingEngine var1) {
      this.UT = var1;
   }

   @Override
   public List pC() {
      return this.kS;
   }

   @Override
   public void pC(auu var1) {
      CFG var2 = var1.E().getCFG();
      long var3 = var2.getFirstAddress();

      for (BasicBlock var6 : var2.getBlocks()) {
         for (AddressableInstruction var8 : var6.addressableInstructions()) {
            IFlowInformation var9 = var8.getRoutineCall();
            if (var9.isBrokenKnown() && var9.getTargets().size() == 1) {
               ICodePointer var10 = (ICodePointer)var9.getTargets().get(0);
               if (!var10.isUnknownAddress()) {
                  INativeContinuousItem var11 = var1.E().ld().getItemAt(var10.getAddress());
                  if (var11 != null) {
                     String var12 = var11.getName(true);
                     if (var12 != null && !((HM)var1.E().ld().getLabelManager()).pC(var12)) {
                        if (var12.startsWith("extern")) {
                           var12 = var12.substring(6);
                        } else if (var12.startsWith("_ptr_")) {
                           var12 = var12.substring(5);
                        }

                        String var13 = null;
                        if (this.UT != null) {
                           IUnmangledData var14 = this.UT.unmangle(var12);
                           if (var14 != null) {
                              var13 = var14.getSimple();
                           }
                        }

                        long var17 = var8.getOffset() - var3;
                        Object var16 = new awx(var17, var13 != null ? var13 : var12);
                        if (this.wS != null && (this.wS.contains(var12) || this.wS.contains(var13))) {
                           var16 = new axc((awy)var16);
                        }

                        this.kS.add(var16);
                     }
                  }
               }
            }
         }
      }
   }

   @Override
   public void A() {
      this.kS.clear();
   }
}
