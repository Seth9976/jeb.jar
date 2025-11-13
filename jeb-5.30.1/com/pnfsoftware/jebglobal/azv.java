package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.units.code.ICodePointer;
import com.pnfsoftware.jeb.core.units.code.IFlowInformation;
import com.pnfsoftware.jeb.core.units.code.IInstruction;
import com.pnfsoftware.jeb.core.units.code.asm.analyzer.IMemoryModelListener;
import com.pnfsoftware.jeb.core.units.code.asm.items.INativeContinuousItem;
import com.pnfsoftware.jeb.core.units.code.asm.items.INativeItem;
import com.pnfsoftware.jeb.core.units.code.asm.sig.INativeAttribute;
import com.pnfsoftware.jeb.util.base.Couple;
import com.pnfsoftware.jeb.util.logging.GlobalLog;
import com.pnfsoftware.jeb.util.logging.ILogger;
import com.pnfsoftware.jeb.util.serialization.annotations.Ser;
import com.pnfsoftware.jeb.util.serialization.annotations.SerId;

@Ser
public abstract class azv implements INativeAttribute {
   private static final ILogger xK = GlobalLog.getLogger(azv.class);
   @SerId(1)
   protected final long q;
   @SerId(2)
   protected final String RF;

   public azv(long var1, String var3) {
      this.q = var1;
      this.RF = var3;
   }

   public Couple q() {
      return new Couple(this.q, this.RF);
   }

   @Override
   public boolean importTo(INativeItem var1) {
      boolean var2 = false;
      if (var1 instanceof axp var3) {
         long var4 = var3.oW().getCFG().getFirstAddress() + this.q;
         INativeContinuousItem var6 = var3.oW().gP().getItemAt(var4);
         if (var6 != null && var6 instanceof axn var7) {
            IInstruction var8 = var7.getInstruction();
            IFlowInformation var9 = var8.getRoutineCall(var4);
            if (var9.isBrokenKnown() && var9.getTargets().size() == 1) {
               ICodePointer var10 = (ICodePointer)var9.getTargets().get(0);
               if (!var10.isUnknownAddress()) {
                  axp var11 = ((aaf)var3.oW().gP()).oW(var10.getAddress());
                  if (var11 != null && (var11.wF() == null || !var11.wF().q().getFlags().hasMeaningfulTargetName())) {
                     String var12 = var11.getName(true);
                     if (var12 != null && (((Nx)var3.oW().gP().getLabelManager()).q(var12) || v.xK(var12))) {
                        IMemoryModelListener var13 = ((aaf)var3.oW().gP()).q();
                        if (var13 instanceof abg && var3.oW() != null) {
                           abg var14 = (abg)var13;
                           if (((aae)var14.getCodeAnalyzer()).q(var11, this.RF, false)) {
                              var11.q(true);
                           }
                        }

                        if (var11.getName().equals(var12)) {
                           var11.setName(this.RF);
                        }

                        var11.RF(true);
                        var2 = true;
                     }

                     if (this.q(var11)) {
                        var2 = true;
                     }
                  }
               }
            }
         }
      }

      return var2;
   }

   protected abstract boolean q(axp var1);

   @Override
   public boolean isPrintable() {
      return false;
   }

   @Override
   public String getType() {
      return "CalledRoutineNameAttr";
   }

   @Override
   public String toString() {
      return "CalledRoutineNameAttr [p=" + this.q + ", name=" + this.RF + "]";
   }

   @Override
   public int hashCode() {
      int var1 = 1;
      var1 = 31 * var1 + (this.RF == null ? 0 : this.RF.hashCode());
      return 31 * var1 + (int)(this.q ^ this.q >>> 32);
   }

   @Override
   public boolean equals(Object var1) {
      if (this == var1) {
         return true;
      } else if (var1 == null) {
         return false;
      } else if (this.getClass() != var1.getClass()) {
         return false;
      } else {
         azv var2 = (azv)var1;
         if (this.RF == null) {
            if (var2.RF != null) {
               return false;
            }
         } else if (!this.RF.equals(var2.RF)) {
            return false;
         }

         return this.q == var2.q;
      }
   }
}
