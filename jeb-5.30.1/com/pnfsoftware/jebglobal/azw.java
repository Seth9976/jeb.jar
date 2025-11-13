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

public class azw extends azp {
   private final String q = "extern";
   private final String RF = "_ptr_";
   private List xK = new ArrayList();
   private Set Dw;
   private IManglingEngine Uv;

   public void q(Set var1) {
      this.Dw = var1;
   }

   public void q(IManglingEngine var1) {
      this.Uv = var1;
   }

   @Override
   public List q() {
      return this.xK;
   }

   @Override
   public void q(axp var1) {
      CFG var2 = var1.oW().getCFG();
      long var3 = var2.getFirstAddress();

      for (BasicBlock var6 : var2.getBlocks()) {
         for (AddressableInstruction var8 : var6.addressableInstructions()) {
            IFlowInformation var9 = var8.getRoutineCall();
            if (var9.isBrokenKnown() && var9.getTargets().size() == 1) {
               ICodePointer var10 = (ICodePointer)var9.getTargets().get(0);
               if (!var10.isUnknownAddress()) {
                  INativeContinuousItem var11 = var1.oW().gP().getItemAt(var10.getAddress());
                  if (var11 != null) {
                     String var12 = var11.getName(true);
                     if (var12 != null && !((Nx)var1.oW().gP().getLabelManager()).q(var12)) {
                        if (var12.startsWith("extern")) {
                           var12 = var12.substring(6);
                        } else if (var12.startsWith("_ptr_")) {
                           var12 = var12.substring(5);
                        }

                        String var13 = null;
                        if (this.Uv != null) {
                           IUnmangledData var14 = this.Uv.unmangle(var12);
                           if (var14 != null) {
                              var13 = var14.getSimple();
                           }
                        }

                        long var17 = var8.getOffset() - var3;
                        Object var16 = new azu(var17, var13 != null ? var13 : var12);
                        if (this.Dw != null && (this.Dw.contains(var12) || this.Dw.contains(var13))) {
                           var16 = new azz((azv)var16);
                        }

                        this.xK.add(var16);
                     }
                  }
               }
            }
         }
      }
   }

   @Override
   public void RF() {
      this.xK.clear();
   }
}
