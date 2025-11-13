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

public class ud extends kx {
   private TreeMap ys = new TreeMap();

   public ud(a var1) {
      super(var1);
      this.pC = "deadcode";
   }

   @Override
   public void kS() {
      super.kS();
      this.ys = new TreeMap();
   }

   private ud.Av kS(long var1) {
      Entry var3 = this.ys.floorEntry(var1);
      if (var3 == null) {
         return null;
      } else {
         return ((ud.Av)var3.getValue()).pC.contains(var1) ? (ud.Av)var3.getValue() : null;
      }
   }

   private List wS(long var1) {
      this.A(var1);
      return Collections.emptyList();
   }

   @Override
   protected List A(long var1, long var3, boolean var5) {
      INativeContinuousItem var6 = this.A.ys().getPreviousItem(var1);
      if (var6 != null && var6.getMemoryAddressEnd() == var1 && var6 instanceof INativeInstructionItem var7) {
         int var8 = this.A.getProcessor().getMode();

         ArrayList var56;
         try {
            this.A.getProcessor().setMode(var7.getInstruction().getProcessorMode());
            long var9 = var1;
            ArrayList var11 = new ArrayList();
            ArrayList var12 = new ArrayList();

            IInstruction var13;
            for (var13 = null; var9 < var3; var9 += var13.getSize()) {
               var11.add(var9);
               var13 = this.A.getProcessor().parseAt(this.wS, var9);
               if (var9 + var13.getSize() > var3) {
                  return this.wS(var3);
               }

               if (var13.getMnemonic().toLowerCase().startsWith("nop")) {
                  return this.wS(var3);
               }

               IFlowInformation var14 = var13.getBreakingFlow(var9);
               if (var14.isBrokenKnown() && !var14.getTargets().isEmpty()) {
                  long var45 = ((ICodePointer)var14.getTargets().get(0)).getAddress();
                  INativeContinuousItem var17 = this.A.ys().getItemOver(var45);
                  if (var17 == null) {
                     var12.add(var45);
                  } else if (var17.getMemoryAddress() != ((ICodePointer)var14.getTargets().get(0)).getAddress() || !(var17 instanceof INativeInstructionItem)) {
                     return this.wS(var3);
                  }

                  if (var14.getTargets().size() == 1 && !var14.mustComputeFallThrough()) {
                     break;
                  }
               } else if (var14.isBroken()) {
                  return this.wS(var3);
               }
            }

            if (var9 == var3) {
               if (var13 == null || !var13.getRoutineCall(var9 - var13.getSize()).isBroken()) {
                  return this.wS(var3);
               }

               INativeContinuousItem var41 = this.A.ys().getItemAt(var9);
               if (var41 == null || !(var41 instanceof INativeInstructionItem)) {
                  return this.wS(var3);
               }
            }

            if (var12.isEmpty()) {
               ArrayList var43 = new ArrayList();

               for (Long var50 : var11) {
                  var43.add(this.A.pC(var50, 0));
               }

               return var43;
            }

            ud.Av var42 = new ud.Av(var11, var12, this.A.getProcessor().getMode());
            this.ys.put(var1, var42);
            ArrayList var46 = new ArrayList();
            var46.add(var42);
            boolean var16 = true;

            for (Long var54 : var12) {
               if (!this.pC(var54, var46)) {
                  var16 = false;
                  break;
               }
            }

            if (!var16) {
               return this.wS(var3);
            }

            ArrayList var52 = new ArrayList();

            for (ud.Av var19 : var46) {
               this.A.getProcessor().setMode(var19.kS);

               for (Long var21 : var19.pC) {
                  var52.add(this.A.pC(var21, 0));
               }

               this.ys.remove(var19.pC.get(0));
            }

            var56 = var52;
         } catch (ProcessorException var37) {
            return this.wS(var3);
         } finally {
            try {
               this.A.getProcessor().setMode(var8);
            } catch (ProcessorException var36) {
            }
         }

         return var56;
      } else {
         return this.wS(var3);
      }
   }

   private boolean pC(Long var1, List var2) {
      ud.Av var3 = this.kS(var1);
      if (var3 == null) {
         return false;
      } else {
         if (!var2.contains(var3)) {
            var2.add(var3);

            for (Long var5 : var3.A) {
               boolean var6 = this.pC(var5, var2);
               if (!var6) {
                  return false;
               }
            }
         }

         return true;
      }
   }

   private static class Av {
      List pC;
      List A;
      private int kS;

      public Av(List var1, List var2, int var3) {
         this.pC = var1;
         this.A = var2;
         this.kS = var3;
      }
   }
}
