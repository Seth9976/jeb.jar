package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.units.IUnitLock;
import com.pnfsoftware.jeb.core.units.code.asm.analyzer.ICommentManager;
import com.pnfsoftware.jeb.core.units.code.asm.analyzer.IMethodStackMemoryModel;
import com.pnfsoftware.jeb.core.units.code.asm.items.INativeContinuousItem;
import com.pnfsoftware.jeb.core.units.code.asm.items.INativeDataItem;
import com.pnfsoftware.jeb.util.collect.ISegmentFactory;
import com.pnfsoftware.jeb.util.format.Strings;
import com.pnfsoftware.jeb.util.serialization.annotations.Ser;
import com.pnfsoftware.jeb.util.serialization.annotations.SerId;

@Ser
public class abr extends Bm implements IMethodStackMemoryModel {
   @SerId(1)
   private int RF;
   @SerId(2)
   private axo xK;
   @SerId(3)
   private abp Dw;
   @SerId(4)
   private ICommentManager Uv;

   public abr(int var1, int var2, axo var3, IUnitLock var4) {
      super(var1, true, var3, var4);
      this.RF = var2;
      this.xK = var3;
      this.Dw = new abp(this);
      this.Uv = new abo(this);
   }

   @Override
   public int getSlotSize() {
      return this.RF;
   }

   @Override
   public ISegmentFactory getGapFactory() {
      return new abs(this);
   }

   public abp oW() {
      return this.Dw;
   }

   @Override
   public ICommentManager getCommentManager() {
      return this.Uv;
   }

   @Override
   public String toString() {
      StringBuilder var1 = new StringBuilder();
      Long var2 = null;

      for (Long var4 : this.RF(true)) {
         INativeContinuousItem var5 = this.getItemAt(var4);
         INativeDataItem var6 = (INativeDataItem)var5;
         if (var2 != null && !((Long)var6.getEnd()).equals(var2)) {
            var1.append("...\n");
         }

         if (var4 >= 0L) {
            Strings.ff(var1, "+%04X", var4);
         } else {
            Strings.ff(var1, "-%04X", -var4);
         }

         var1.append("(").append(Long.toHexString(var6.getMemorySize()).toUpperCase()).append(") : ");
         var1.append(var6.getName(true)).append(" {").append(var6.getType()).append("}\n");
         var2 = (Long)var6.getBegin();
      }

      return var1.toString();
   }

   @Override
   public void q(long var1) {
      this.q.verifyLocked();
      axg var3 = (axg)this.getItemAt(var1);
      if (var3 != null) {
         this.q((INativeContinuousItem)var3);
      }
   }
}
