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
public abstract class awy implements INativeAttribute {
   private static final ILogger kS = GlobalLog.getLogger(awy.class);
   @SerId(1)
   protected final long pC;
   @SerId(2)
   protected final String A;

   public awy(long var1, String var3) {
      this.pC = var1;
      this.A = var3;
   }

   public Couple pC() {
      return new Couple(this.pC, this.A);
   }

   @Override
   public boolean importTo(INativeItem var1) {
      boolean var2 = false;
      if (var1 instanceof auu var3) {
         long var4 = var3.E().getCFG().getFirstAddress() + this.pC;
         INativeContinuousItem var6 = var3.E().ld().getItemAt(var4);
         if (var6 != null && var6 instanceof aus var7) {
            IInstruction var8 = var7.getInstruction();
            IFlowInformation var9 = var8.getRoutineCall(var4);
            if (var9.isBrokenKnown() && var9.getTargets().size() == 1) {
               ICodePointer var10 = (ICodePointer)var9.getTargets().get(0);
               if (!var10.isUnknownAddress()) {
                  auu var11 = ((Tw)var3.E().ld()).E(var10.getAddress());
                  if (var11 != null && (var11.z() == null || !var11.z().pC().getFlags().hasMeaningfulTargetName())) {
                     String var12 = var11.getName(true);
                     if (var12 != null && (((HM)var3.E().ld().getLabelManager()).pC(var12) || ph.kS(var12))) {
                        IMemoryModelListener var13 = ((Tw)var3.E().ld()).pC();
                        if (var13 instanceof C && var3.E() != null) {
                           C var14 = (C)var13;
                           if (((a)var14.getCodeAnalyzer()).pC(var11, this.A, false)) {
                              var11.pC(true);
                           }
                        }

                        if (var11.getName().equals(var12)) {
                           var11.setName(this.A);
                        }

                        var11.A(true);
                        var2 = true;
                     }

                     if (this.pC(var11)) {
                        var2 = true;
                     }
                  }
               }
            }
         }
      }

      return var2;
   }

   protected abstract boolean pC(auu var1);

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
      return "CalledRoutineNameAttr [p=" + this.pC + ", name=" + this.A + "]";
   }

   @Override
   public int hashCode() {
      int var1 = 1;
      var1 = 31 * var1 + (this.A == null ? 0 : this.A.hashCode());
      return 31 * var1 + (int)(this.pC ^ this.pC >>> 32);
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
         awy var2 = (awy)var1;
         if (this.A == null) {
            if (var2.A != null) {
               return false;
            }
         } else if (!this.A.equals(var2.A)) {
            return false;
         }

         return this.pC == var2.pC;
      }
   }
}
