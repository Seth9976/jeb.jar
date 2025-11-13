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
public class l implements IStackframeManager {
   private static final ILogger pC = GlobalLog.getLogger(l.class);
   @SerId(1)
   private iF A;
   @SerId(2)
   private auy kS;

   public l(iF var1, ayy var2) {
      this.A = var1;
      this.kS = new auy(var2);
   }

   public iF pC() {
      return this.A;
   }

   public aum pC(long var1, INativeType var3) {
      aum var6;
      try (ACLock var4 = this.A.getLock().a()) {
         aum var5 = (aum)this.A.getItemAt(var1);
         if (var5 != null && var5.UT().equals(var3)) {
            return var5;
         }

         var5 = this.kS.pC(var1, var3);
         if (var5 == null) {
            return null;
         }

         this.pC(var5);
         var6 = var5;
      }

      return var6;
   }

   @Override
   public boolean undefineItem(long var1) {
      boolean var5;
      try (ACLock var3 = this.A.getLock().a()) {
         INativeContinuousItem var4 = this.A.getItemAt(var1);
         if (var4 == null) {
            return false;
         }

         this.pC(var4, false);
         var5 = true;
      }

      return var5;
   }

   private void pC(aul var1) {
      try (ACLock var2 = this.A.getLock().a()) {
         this.undefineItems(var1.kS(), var1.wS());
         this.A.pC(var1);
      }
   }

   @Override
   public int undefineItems(long var1, long var3) {
      int var12;
      try (ACLock var5 = this.A.getLock().a()) {
         int var6 = 0;

         for (INativeContinuousItem var9 : this.A.getItemsInRange(var1, true, var3, true).values()) {
            this.pC(var9, true);
            var6++;
         }

         var12 = var6;
      }

      return var12;
   }

   public void pC(INativeContinuousItem var1, boolean var2) {
      try (ACLock var3 = this.A.getLock().a()) {
         this.A.pC(var1);
         if (!var2) {
            long var4 = var1.getMemoryAddress();
            this.A.wS().removeLabel(var4);
         }
      }
   }

   public boolean pC(IPrototypeItem var1, List var2) {
      boolean var15;
      try (ACLock var3 = this.A.getLock().a()) {
         ICallingConvention var4 = var1.getCallingConvention();
         int var5 = this.A.getSlotSize();
         IStorageEntryGenerator var6 = var4.getInputsGenerator();
         int var7 = 0;

         for (INativeType var9 : var1.getParameterTypes()) {
            StorageEntry var10 = var6.next(TypeUtil.getLayoutInfo(var9));
            if (var10 == null) {
               break;
            }

            if (var10.getType() == StorageEntry.Type.STACK) {
               int var11 = var10.getValueAsStackIndex() * var5;
               aum var12 = this.pC(var11, var9);
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
      return Strings.ff("Current Model:\n%s", this.A.toString());
   }
}
