package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.units.code.asm.analyzer.AutoLabelPolicy;
import com.pnfsoftware.jeb.core.units.code.asm.type.CallingConventionUtil;
import com.pnfsoftware.jeb.core.units.code.asm.type.ICallingConvention;
import com.pnfsoftware.jeb.util.format.Strings;
import com.pnfsoftware.jeb.util.serialization.annotations.Ser;
import com.pnfsoftware.jeb.util.serialization.annotations.SerId;

@Ser
public class ayf extends axj implements axy {
   @SerId(1)
   private abg q;
   @SerId(2)
   private int RF;
   @SerId(3)
   private int xK;

   public ayf(abg var1, long var2, int var4, int var5) {
      super(0, null);
      this.q(var2);
      this.Uv(3);
      this.q = var1;
      this.RF = var4;
      this.xK = var5;
   }

   public int xK() {
      return this.RF;
   }

   public int Dw() {
      return this.xK;
   }

   @Override
   public String getName(boolean var1) {
      String var2 = null;
      axp var3 = this.q.Dw(this.RF);
      if (var3 != null) {
         axo var4 = var3.oW();
         if (var4 != null) {
            bbs var5 = var3.Uv();
            ICallingConvention var6 = var3.sH();
            if (var5 != null && var6 != null) {
               int var7 = CallingConventionUtil.getParameterIndexByArgumentStackOffset(var6, var5, this.xK, var4.Dw().getSlotSize());
               if (var7 >= 0) {
                  var2 = var3.getParameterName(var7);
               }
            }

            if (var2 == null) {
               var2 = var4.Dw().oW().getLabel(this.xK, 0L, var1 ? AutoLabelPolicy.ON : AutoLabelPolicy.OFF);
            }
         }
      }

      return var2;
   }

   @Override
   public void setName(String var1) {
      axp var2 = this.q.Dw(this.RF);
      if (var2 != null) {
         axo var3 = var2.oW();
         if (var3 != null) {
            bbs var4 = var2.Uv();
            ICallingConvention var5 = var2.sH();
            if (var4 != null && var5 != null) {
               int var6 = CallingConventionUtil.getParameterIndexByArgumentStackOffset(var5, var4, this.xK, var3.Dw().getSlotSize());
               if (var6 >= 0) {
                  var2.setParameterName(var6, var1);
               }
            }

            var3.Dw().oW().setLabel(this.xK, var1, true, false, false);
         }
      }
   }

   @Override
   public String toString() {
      return Strings.ff("PseudoLocalAddress(routineIndex:%d,stackOffset:%dh)", this.RF, this.xK);
   }
}
