package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.units.code.asm.analyzer.AutoLabelPolicy;
import com.pnfsoftware.jeb.core.units.code.asm.type.CallingConventionUtil;
import com.pnfsoftware.jeb.core.units.code.asm.type.ICallingConvention;
import com.pnfsoftware.jeb.util.format.Strings;
import com.pnfsoftware.jeb.util.serialization.annotations.Ser;
import com.pnfsoftware.jeb.util.serialization.annotations.SerId;

@Ser
public class avj extends auo {
   @SerId(1)
   private C pC;
   @SerId(2)
   private int A;
   @SerId(3)
   private int kS;

   public avj(C var1, long var2, int var4, int var5) {
      super(0, null);
      this.pC(var2);
      this.UT(3);
      this.pC = var1;
      this.A = var4;
      this.kS = var5;
   }

   public int kS() {
      return this.A;
   }

   public int wS() {
      return this.kS;
   }

   @Override
   public String getName(boolean var1) {
      String var2 = null;
      auu var3 = this.pC.wS(this.A);
      if (var3 != null) {
         aut var4 = var3.E();
         if (var4 != null) {
            ays var5 = var3.UT();
            ICallingConvention var6 = var3.Ab();
            if (var5 != null && var6 != null) {
               int var7 = CallingConventionUtil.getParameterIndexByArgumentStackOffset(var6, var5, this.kS, var4.wS().getSlotSize());
               if (var7 >= 0) {
                  var2 = var3.getParameterName(var7);
               }
            }

            if (var2 == null) {
               var2 = var4.wS().wS().getLabel(this.kS, 0L, var1 ? AutoLabelPolicy.ON : AutoLabelPolicy.OFF);
            }
         }
      }

      return var2;
   }

   @Override
   public void setName(String var1) {
      auu var2 = this.pC.wS(this.A);
      if (var2 != null) {
         aut var3 = var2.E();
         if (var3 != null) {
            ays var4 = var2.UT();
            ICallingConvention var5 = var2.Ab();
            if (var4 != null && var5 != null) {
               int var6 = CallingConventionUtil.getParameterIndexByArgumentStackOffset(var5, var4, this.kS, var3.wS().getSlotSize());
               if (var6 >= 0) {
                  var2.setParameterName(var6, var1);
               }
            }

            var3.wS().wS().setLabel(this.kS, var1, true, false, false);
         }
      }
   }

   @Override
   public String toString() {
      return Strings.ff("PseudoLocalAddress(routineIndex:%d,stackOffset:%dh)", this.A, this.kS);
   }
}
