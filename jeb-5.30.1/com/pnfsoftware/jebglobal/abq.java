package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.units.code.asm.analyzer.IStackframeManager;
import com.pnfsoftware.jeb.core.units.code.asm.items.INativeContinuousItem;
import com.pnfsoftware.jeb.core.units.code.asm.type.ICallingConvention;
import com.pnfsoftware.jeb.core.units.code.asm.type.INativeType;
import com.pnfsoftware.jeb.core.units.code.asm.type.IPrototypeItem;
import com.pnfsoftware.jeb.core.units.code.asm.type.IStorageEntryGenerator;
import com.pnfsoftware.jeb.core.units.code.asm.type.StorageEntry;
import com.pnfsoftware.jeb.core.units.code.asm.type.TypeUtil;
import com.pnfsoftware.jeb.util.concurrent.ACLock;
import com.pnfsoftware.jeb.util.format.Strings;
import com.pnfsoftware.jeb.util.logging.GlobalLog;
import com.pnfsoftware.jeb.util.logging.ILogger;
import com.pnfsoftware.jeb.util.serialization.annotations.Ser;
import com.pnfsoftware.jeb.util.serialization.annotations.SerId;
import java.util.List;

@Ser
public class abq implements IStackframeManager {
   private static final ILogger q = GlobalLog.getLogger(abq.class);
   @SerId(1)
   private abr RF;
   @SerId(2)
   private axt xK;

   public abq(abr var1, bby var2) {
      this.RF = var1;
      this.xK = new axt(var2);
   }

   public abr q() {
      return this.RF;
   }

   public axh q(long var1, INativeType var3) {
      axh var6;
      try (ACLock var4 = this.RF.getLock().a()) {
         axh var5 = (axh)this.RF.getItemAt(var1);
         if (var5 != null && var5.Uv().equals(var3)) {
            return var5;
         }

         var5 = this.xK.q(var1, var3);
         if (var5 == null) {
            return null;
         }

         this.q(var5);
         var6 = var5;
      }

      return var6;
   }

   @Override
   public boolean undefineItem(long var1) {
      boolean var5;
      try (ACLock var3 = this.RF.getLock().a()) {
         INativeContinuousItem var4 = this.RF.getItemAt(var1);
         if (var4 == null) {
            return false;
         }

         this.q(var4, false);
         var5 = true;
      }

      return var5;
   }

   private void q(axg var1) {
      try (ACLock var2 = this.RF.getLock().a()) {
         this.undefineItems(var1.xK(), var1.Dw());
         this.RF.q(var1);
      }
   }

   @Override
   public int undefineItems(long var1, long var3) {
      int var12;
      try (ACLock var5 = this.RF.getLock().a()) {
         int var6 = 0;

         for (INativeContinuousItem var9 : this.RF.getItemsInRange(var1, true, var3, true).values()) {
            this.q(var9, true);
            var6++;
         }

         var12 = var6;
      }

      return var12;
   }

   public void q(long var1) {
      try (ACLock var3 = this.RF.getLock().a()) {
         INativeContinuousItem var4 = this.RF.getItemOver(var1);
         if (var4 != null) {
            this.q(var4, true);
         }
      }
   }

   public void q(INativeContinuousItem var1, boolean var2) {
      try (ACLock var3 = this.RF.getLock().a()) {
         this.RF.q(var1);
         if (!var2) {
            long var4 = var1.getMemoryAddress();
            this.RF.oW().removeLabel(var4);
         }
      }
   }

   public boolean q(IPrototypeItem var1, List var2) {
      boolean var15;
      try (ACLock var3 = this.RF.getLock().a()) {
         ICallingConvention var4 = var1.getCallingConvention();
         int var5 = this.RF.getSlotSize();
         IStorageEntryGenerator var6 = var4.getInputsGenerator();
         int var7 = 0;

         for (INativeType var9 : var1.getParameterTypes()) {
            StorageEntry var10 = var6.next(TypeUtil.getLayoutInfo(var9));
            if (var10 == null) {
               break;
            }

            if (var10.getType() == StorageEntry.Type.STACK) {
               int var11 = var10.getValueAsStackIndex() * var5;
               axh var12 = this.q(var11, var9);
               if (var2 != null && var7 < var2.size()) {
                  var12.setName((String)var2.get(var7));
               }
            }

            var7++;
         }

         var15 = true;
      }

      return var15;
   }

   @Override
   public String toString() {
      return Strings.ff("Current Model:\n%s", this.RF.toString());
   }
}
