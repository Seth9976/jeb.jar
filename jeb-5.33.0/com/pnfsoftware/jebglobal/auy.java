package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.units.code.asm.analyzer.INativeDataAnalyzer;
import com.pnfsoftware.jeb.core.units.code.asm.items.DataHints;
import com.pnfsoftware.jeb.core.units.code.asm.items.INativeDataItem;
import com.pnfsoftware.jeb.core.units.code.asm.items.INativeItemListener;
import com.pnfsoftware.jeb.core.units.code.asm.items.INativeMethodItem;
import com.pnfsoftware.jeb.core.units.code.asm.items.NativeItemEvent;
import com.pnfsoftware.jeb.core.units.code.asm.type.INativeType;
import com.pnfsoftware.jeb.core.units.code.asm.type.ITypeManager;
import com.pnfsoftware.jeb.core.units.code.asm.type.StringEncoding;
import com.pnfsoftware.jeb.core.units.code.asm.type.TypeUtil;
import com.pnfsoftware.jeb.util.concurrent.ACLock;
import com.pnfsoftware.jeb.util.serialization.annotations.Ser;
import com.pnfsoftware.jeb.util.serialization.annotations.SerId;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

@Ser
public class auy implements INativeDataAnalyzer, INativeItemListener {
   @SerId(1)
   private ayy pC;
   @SerId(2)
   private AtomicInteger A = new AtomicInteger();
   @SerId(3)
   private Map kS = new ConcurrentHashMap();

   public auy(ITypeManager var1) {
      if (var1 == null) {
         throw new NullPointerException();
      } else {
         this.pC = (ayy)var1;
      }
   }

   public ayy pC() {
      return this.pC;
   }

   public aum pC(long var1, INativeType var3) {
      return this.pC(var1, var3, -1);
   }

   public aum pC(long var1, INativeType var3, int var4) {
      this.pC.verify(var3);

      aum var6;
      try (ACLock var5 = this.pC.wS().a()) {
         var6 = this.pC(var1, 0, var3, var4);
      }

      return var6;
   }

   private aum pC(long var1, int var3, INativeType var4, int var5) {
      return this.pC(var1, var3, var4, var5, !var4.hasFlag(4096));
   }

   private aum pC(long var1, int var3, INativeType var4, int var5, boolean var6) {
      this.pC.wS().verifyLocked();
      aye var7 = (aye)var4;
      aye var8 = (aye)TypeUtil.getNonAlias(var7);
      int var9 = var8.getSize();
      var6 = var6 && !var8.hasFlag(128) && !var7.hasFlag(128);
      Object var10;
      if (var8 instanceof ayq || var8 instanceof ayt || var8 instanceof aym) {
         var10 = new ava(var1 + var3, var9, var7, var6);
         if (!var6) {
            ((aum)var10).A(128, false);
         }
      } else if (var8 instanceof ayv var11) {
         boolean var12 = var11.isUnion();
         auw var13 = new auw(var1 + var3, var9, var7);

         for (ayu var15 : var11.getFieldsWithGaps()) {
            int var16 = var15.getOffset();
            if (var5 >= 0 && var16 >= var5) {
               var13.pC(var16);
               break;
            }

            aum var17 = this.pC(var1, var3, var15, var6);
            var13.pC(var17);
            if (var12) {
               break;
            }
         }

         var13.Ab();
         var10 = var13;
      } else {
         if (!(var8 instanceof ayg var19)) {
            throw new RuntimeException("Type cannot be used to create a data item: " + var8);
         }

         auv var20 = new auv(var1 + var3, var9, var7);
         int var21 = 0;
         aye var22 = var19.E();
         int var23 = var22.getSize();

         for (int var24 = 0; var24 < var19.getElementCount(); var24++) {
            aum var25 = this.pC(var1, var3 + var21, var22, -1, var6);
            var20.pC(var25);
            var21 += var23;
         }

         var20.Ab();
         var10 = var20;
      }

      return this.pC((aum)var10, var6);
   }

   private aum pC(long var1, int var3, ayu var4, boolean var5) {
      int var6 = var4.getOffset();
      Object var7;
      if (var4.isSynthetic()) {
         var7 = new auxx(var1 + var3 + var6, var4.getSize());
      } else {
         var7 = this.pC(var1, var3 + var6, var4.pC(), -1, var5);
         if ((var4.getFlags() & 1) != 0) {
            DataHints var8 = ((aum)var7).getHints(true);
            var8.setAddressCalculationHint(2);
            if (var7 instanceof auv) {
               for (INativeDataItem var10 : ((auv)var7).UO()) {
                  DataHints var11 = var10.getHints(true);
                  var11.setAddressCalculationHint(2);
               }
            }
         }

         if (var4.isBitfield()) {
            ava var12 = (ava)var7;
            var12.pC(var4.getBitstart(), var4.getBitsize());
         }
      }

      return (aum)var7;
   }

   public auz pC(long var1, INativeMethodItem var3, String var4) {
      auz var5 = new auz(this.pC, var1, this.pC.getPointerSize(), var3, var4);
      return (auz)this.pC(var5, true);
   }

   public avb pC(long var1, long var3, INativeType var5, StringEncoding var6, String var7) {
      avb var8 = new avb(var1, var3, (aye)var5, var6, var7);
      return (avb)this.pC(var8, true);
   }

   private aum pC(aum var1, boolean var2) {
      int var3 = this.A.getAndIncrement();
      var1.wS(var3);
      var1.pC(-8574853690513424384L | var3 & 4294967295L);
      this.kS.put(var3, var1);
      if (var2) {
         var1.addListener(this);
      }

      return var1;
   }

   public INativeDataItem pC(long var1) {
      if ((var1 & -72057594037927936L) != -8574853690513424384L) {
         return null;
      } else {
         int var3 = (int)var1;
         return (INativeDataItem)this.kS.get(var3);
      }
   }

   public INativeDataItem pC(int var1) {
      return (INativeDataItem)this.kS.get(var1);
   }

   @Override
   public void onNativeItemEvent(NativeItemEvent var1) {
      if (var1.getItem() instanceof INativeDataItem var2 && var2.isDisposed()) {
         var2.removeListener(this);
         INativeDataItem var10000 = (INativeDataItem)this.kS.remove(var2.getIndex());
      }
   }
}
