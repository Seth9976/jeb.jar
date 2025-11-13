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
public class axt implements INativeDataAnalyzer, INativeItemListener {
   @SerId(1)
   private bby q;
   @SerId(2)
   private AtomicInteger RF = new AtomicInteger();
   @SerId(3)
   private Map xK = new ConcurrentHashMap();

   public axt(ITypeManager var1) {
      if (var1 == null) {
         throw new NullPointerException();
      } else {
         this.q = (bby)var1;
      }
   }

   public bby q() {
      return this.q;
   }

   public axh q(long var1, INativeType var3) {
      return this.q(var1, var3, -1);
   }

   public axh q(long var1, INativeType var3, int var4) {
      this.q.verify(var3);

      axh var6;
      try (ACLock var5 = this.q.oW().a()) {
         var6 = this.q(var1, 0, var3, var4);
      }

      return var6;
   }

   private axh q(long var1, int var3, INativeType var4, int var5) {
      return this.q(var1, var3, var4, var5, !var4.hasFlag(4096));
   }

   private axh q(long var1, int var3, INativeType var4, int var5, boolean var6) {
      this.q.oW().verifyLocked();
      bbd var7 = (bbd)var4;
      bbd var8 = (bbd)TypeUtil.getNonAlias(var7);
      int var9 = var8.getSize();
      var6 = var6 && !var8.hasFlag(128) && !var7.hasFlag(128);
      Object var10;
      if (var8 instanceof bbq || var8 instanceof bbt || var8 instanceof bbm) {
         var10 = new axv(var1 + var3, var9, var7, var6);
         if (!var6) {
            ((axh)var10).RF(128, false);
         }
      } else if (var8 instanceof bbv var11) {
         boolean var12 = var11.isUnion();
         axr var13 = new axr(var1 + var3, var9, var7);

         for (bbu var15 : var11.getFieldsWithGaps()) {
            int var16 = var15.getOffset();
            if (var5 >= 0 && var16 >= var5) {
               var13.q(var16);
               break;
            }

            axh var17 = this.q(var1, var3, var15, var6);
            var13.q(var17);
            if (var12) {
               break;
            }
         }

         var13.CE();
         var10 = var13;
      } else {
         if (!(var8 instanceof bbf var19)) {
            throw new RuntimeException("Type cannot be used to create a data item: " + var8);
         }

         axq var20 = new axq(var1 + var3, var9, var7);
         int var21 = 0;
         bbd var22 = var19.oW();
         int var23 = var22.getSize();

         for (int var24 = 0; var24 < var19.getElementCount(); var24++) {
            axh var25 = this.q(var1, var3 + var21, var22, -1, var6);
            var20.q(var25);
            var21 += var23;
         }

         var20.sH();
         var10 = var20;
      }

      return this.q((axh)var10, var6);
   }

   private axh q(long var1, int var3, bbu var4, boolean var5) {
      int var6 = var4.getOffset();
      Object var7;
      if (var4.isSynthetic()) {
         var7 = new axs(var1 + var3 + var6, var4.getSize());
      } else {
         var7 = this.q(var1, var3 + var6, var4.q(), -1, var5);
         if ((var4.getFlags() & 1) != 0) {
            DataHints var8 = ((axh)var7).getHints(true);
            var8.setAddressCalculationHint(2);
            if (var7 instanceof axq) {
               for (INativeDataItem var10 : ((axq)var7).cC()) {
                  DataHints var11 = var10.getHints(true);
                  var11.setAddressCalculationHint(2);
               }
            }
         }

         if (var4.isBitfield()) {
            axv var12 = (axv)var7;
            var12.q(var4.getBitstart(), var4.getBitsize());
         }
      }

      return (axh)var7;
   }

   public axu q(long var1, INativeMethodItem var3, String var4) {
      axu var5 = new axu(this.q, var1, this.q.getPointerSize(), var3, var4);
      return (axu)this.q(var5, true);
   }

   public axw q(long var1, long var3, INativeType var5, StringEncoding var6, String var7) {
      axw var8 = new axw(var1, var3, (bbd)var5, var6, var7);
      return (axw)this.q(var8, true);
   }

   private axh q(axh var1, boolean var2) {
      int var3 = this.RF.getAndIncrement();
      var1.Dw(var3);
      var1.q(-8574853690513424384L | var3 & 4294967295L);
      this.xK.put(var3, var1);
      if (var2) {
         var1.addListener(this);
      }

      return var1;
   }

   public INativeDataItem q(long var1) {
      if ((var1 & -72057594037927936L) != -8574853690513424384L) {
         return null;
      } else {
         int var3 = (int)var1;
         return (INativeDataItem)this.xK.get(var3);
      }
   }

   public INativeDataItem q(int var1) {
      return (INativeDataItem)this.xK.get(var1);
   }

   @Override
   public void onNativeItemEvent(NativeItemEvent var1) {
      if (var1.getItem() instanceof INativeDataItem var2 && var2.isDisposed()) {
         var2.removeListener(this);
         INativeDataItem var10000 = (INativeDataItem)this.xK.remove(var2.getIndex());
      }
   }
}
