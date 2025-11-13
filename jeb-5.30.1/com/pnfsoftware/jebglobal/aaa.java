package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.units.code.ICodePointer;
import com.pnfsoftware.jeb.core.units.code.IFlowInformation;
import com.pnfsoftware.jeb.core.units.code.IInstruction;
import com.pnfsoftware.jeb.core.units.code.asm.items.INativeContinuousItem;
import com.pnfsoftware.jeb.core.units.code.asm.items.INativeInstructionItem;
import com.pnfsoftware.jeb.core.units.code.asm.processor.ProcessorException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.TreeMap;
import java.util.Map.Entry;

public class aaa extends PA {
   private TreeMap nf = new TreeMap();

   public aaa(aae var1) {
      super(var1);
      this.q = "deadcode";
   }

   @Override
   public void xK() {
      super.xK();
      this.nf = new TreeMap();
   }

   private aaa.eo Dw(long var1) {
      Entry var3 = this.nf.floorEntry(var1);
      if (var3 == null) {
         return null;
      } else {
         return ((aaa.eo)var3.getValue()).q.contains(var1) ? (aaa.eo)var3.getValue() : null;
      }
   }

   private List Uv(long var1) {
      this.RF(var1);
      return Collections.emptyList();
   }

   @Override
   protected List RF(long var1, long var3, boolean var5) {
      INativeContinuousItem var6 = this.RF.za().getPreviousItem(var1);
      if (var6 != null && var6.getMemoryAddressEnd() == var1 && var6 instanceof INativeInstructionItem var7) {
         int var8 = this.RF.getProcessor().getMode();

         ArrayList var56;
         try {
            this.RF.getProcessor().setMode(var7.getInstruction().getProcessorMode());
            long var9 = var1;
            ArrayList var11 = new ArrayList();
            ArrayList var12 = new ArrayList();

            IInstruction var13;
            for (var13 = null; var9 < var3; var9 += var13.getSize()) {
               var11.add(var9);
               var13 = this.RF.getProcessor().parseAt(this.Dw, var9);
               if (var9 + var13.getSize() > var3) {
                  return this.Uv(var3);
               }

               if (var13.getMnemonic().toLowerCase().startsWith("nop")) {
                  return this.Uv(var3);
               }

               IFlowInformation var14 = var13.getBreakingFlow(var9);
               if (var14.isBrokenKnown() && !var14.getTargets().isEmpty()) {
                  long var45 = ((ICodePointer)var14.getTargets().get(0)).getAddress();
                  INativeContinuousItem var17 = this.RF.za().getItemOver(var45);
                  if (var17 == null) {
                     var12.add(var45);
                  } else if (var17.getMemoryAddress() != ((ICodePointer)var14.getTargets().get(0)).getAddress() || !(var17 instanceof INativeInstructionItem)) {
                     return this.Uv(var3);
                  }

                  if (var14.getTargets().size() == 1 && !var14.mustComputeFallThrough()) {
                     break;
                  }
               } else if (var14.isBroken()) {
                  return this.Uv(var3);
               }
            }

            if (var9 == var3) {
               if (var13 == null || !var13.getRoutineCall(var9 - var13.getSize()).isBroken()) {
                  return this.Uv(var3);
               }

               INativeContinuousItem var41 = this.RF.za().getItemAt(var9);
               if (var41 == null || !(var41 instanceof INativeInstructionItem)) {
                  return this.Uv(var3);
               }
            }

            if (var12.isEmpty()) {
               ArrayList var43 = new ArrayList();

               for (Long var50 : var11) {
                  var43.add(this.RF.q(var50, 0));
               }

               return var43;
            }

            aaa.eo var42 = new aaa.eo(var11, var12, this.RF.getProcessor().getMode());
            this.nf.put(var1, var42);
            ArrayList var46 = new ArrayList();
            var46.add(var42);
            boolean var16 = true;

            for (Long var54 : var12) {
               if (!this.q(var54, var46)) {
                  var16 = false;
                  break;
               }
            }

            if (!var16) {
               return this.Uv(var3);
            }

            ArrayList var52 = new ArrayList();

            for (aaa.eo var19 : var46) {
               this.RF.getProcessor().setMode(var19.xK);

               for (Long var21 : var19.q) {
                  var52.add(this.RF.q(var21, 0));
               }

               this.nf.remove(var19.q.get(0));
            }

            var56 = var52;
         } catch (ProcessorException var37) {
            return this.Uv(var3);
         } finally {
            try {
               this.RF.getProcessor().setMode(var8);
            } catch (ProcessorException var36) {
            }
         }

         return var56;
      } else {
         return this.Uv(var3);
      }
   }

   private boolean q(Long var1, List var2) {
      aaa.eo var3 = this.Dw(var1);
      if (var3 == null) {
         return false;
      } else {
         if (!var2.contains(var3)) {
            var2.add(var3);

            for (Long var5 : var3.RF) {
               boolean var6 = this.q(var5, var2);
               if (!var6) {
                  return false;
               }
            }
         }

         return true;
      }
   }

   private static class eo {
      List q;
      List RF;
      private int xK;

      public eo(List var1, List var2, int var3) {
         this.q = var1;
         this.RF = var2;
         this.xK = var3;
      }
   }
}
