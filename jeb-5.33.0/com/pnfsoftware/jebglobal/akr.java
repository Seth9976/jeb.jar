package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.IWildcardType;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.IWildcardTypeManager;
import com.pnfsoftware.jeb.core.units.code.asm.items.INativeItemListener;
import com.pnfsoftware.jeb.core.units.code.asm.items.NativeItemEvent;
import com.pnfsoftware.jeb.core.units.code.asm.type.IArrayType;
import com.pnfsoftware.jeb.core.units.code.asm.type.ICallingConvention;
import com.pnfsoftware.jeb.core.units.code.asm.type.INativeType;
import com.pnfsoftware.jeb.core.units.code.asm.type.IPrototypeItem;
import com.pnfsoftware.jeb.core.units.code.asm.type.IReferenceType;
import com.pnfsoftware.jeb.core.units.code.asm.type.ITypeManager;
import com.pnfsoftware.jeb.core.units.code.asm.type.TypeUtil;
import com.pnfsoftware.jeb.util.base.Assert;
import com.pnfsoftware.jeb.util.concurrent.ACLock;
import com.pnfsoftware.jeb.util.serialization.annotations.Ser;
import com.pnfsoftware.jeb.util.serialization.annotations.SerId;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.IdentityHashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CopyOnWriteArrayList;

@Ser
public class akr implements IWildcardTypeManager, INativeItemListener {
   @SerId(1)
   ayy pC;
   @SerId(2)
   int A;
   @SerId(3)
   int kS;
   @SerId(4)
   private IdentityHashMap wS = new IdentityHashMap();
   @SerId(5)
   private Map UT = new HashMap();
   @SerId(6)
   private List E = new ArrayList();
   @SerId(7)
   private List sY = new CopyOnWriteArrayList();
   @SerId(8)
   private IWildcardType.Group ys;

   public akr(ITypeManager var1, IWildcardType.Group var2) {
      if (var1 != null && var2 != null) {
         this.pC = (ayy)var1;
         this.A = this.pC.getSlotSize() * 8;
         this.kS = this.pC.getPointerSize() * 8;
         this.ys = var2;
      } else {
         throw new NullPointerException();
      }
   }

   @Override
   public int getSlotBitsize() {
      return this.A;
   }

   @Override
   public int getPointerBitsize() {
      return this.kS;
   }

   public IWildcardType.Group pC() {
      return this.ys;
   }

   @Override
   public void onNativeItemEvent(NativeItemEvent var1) {
      if (var1.getItem() instanceof ajf var3) {
         if (var3.isDisposed()) {
            if (var3 instanceof akp) {
               akp var7 = (akp)this.wS.remove(((akp)var3).getNativeType());
               Assert.a(var7 == var3);
            } else if (var3 instanceof akf) {
               boolean var4 = false;

               for (akf var6 : this.E) {
                  if (var6 == var3) {
                     var4 = this.E.remove((akf)var3);
                     break;
                  }
               }

               Assert.a(var4);
            }
         }

         this.pC(var1);
      }
   }

   @Override
   public void addListener(INativeItemListener var1) {
      if (var1 != null) {
         this.sY.add(var1);
      }
   }

   @Override
   public void removeListener(INativeItemListener var1) {
      if (var1 != null) {
         this.sY.remove(var1);
      }
   }

   public void pC(NativeItemEvent var1) {
      for (INativeItemListener var3 : this.sY) {
         if (var3 != var1.getItem()) {
            var3.onNativeItemEvent(var1);
         }
      }
   }

   public ayy A() {
      return this.pC;
   }

   public akp pC(String var1) {
      Object var2 = this.pC.UT(var1);
      if (var2 == null) {
         var2 = TypeUtil.buildQuickType(this.pC, var1);
         if (var2 == null) {
            throw new IllegalArgumentException("Cannot create etype for illegal native type: " + var1);
         }
      }

      return this.pC((INativeType)var2);
   }

   public akp pC(INativeType var1) {
      akp var4;
      try (ACLock var2 = this.pC.wS().a()) {
         if (var1 == null) {
            throw new NullPointerException("Native type is null");
         }

         akp var3 = (akp)this.wS.get(var1);
         if (var3 == null) {
            var3 = new akp(this, var1);
            this.wS.put(var1, var3);
            var3.addListener(this);
         }

         var4 = var3;
      }

      return var4;
   }

   public akp pC(int var1) {
      akp var4;
      try (ACLock var2 = this.pC.wS().a()) {
         if (var1 <= 0) {
            throw new IllegalArgumentException();
         }

         akp var3 = (akp)this.UT.get(var1);
         if (var3 == null) {
            var3 = new akp(this, var1);
            this.UT.put(var1, var3);
         }

         var4 = var3;
      }

      return var4;
   }

   public akp A(int var1) {
      return this.pC(var1 * this.A);
   }

   public akp pC(int var1, int var2) {
      return this.pC(var1).pC(var2);
   }

   public akp kS(int var1) {
      akp var6;
      try (ACLock var2 = this.pC.wS().a()) {
         if (var1 <= 0) {
            throw new IllegalArgumentException("Cannot create wildcard type with effective bitsize " + var1);
         }

         int var3 = (var1 + 7) / 8;
         aye var5 = this.pC.pC(var3, false);
         int var4;
         if (var5 != null) {
            var4 = var5.getSize() * 8;
         } else {
            var4 = var3 * 8;
         }

         var6 = this.pC(var4, var1);
      }

      return var6;
   }

   public akp wS(int var1) {
      akp var5;
      try (ACLock var2 = this.pC.wS().a()) {
         int var3 = (this.kS + (this.A - 1)) / this.A;
         akp var4 = this.A(var3);
         var4 = var4.pC(IWildcardType.Group.POINTER);
         var4 = var4.kS(var1);
         var5 = var4;
      }

      return var5;
   }

   public akf pC(ICallingConvention var1, IWildcardType var2, List var3, Collection var4) {
      ArrayList var5 = null;
      if (var2 != null && !var2.isVoid()) {
         var5 = new ArrayList(1);
         var5.add(var2);
      }

      return this.pC(var1, var5, var3, var4);
   }

   public akf pC(ICallingConvention var1, List var2, List var3, Collection var4) {
      akf var7;
      try (ACLock var5 = this.pC.wS().a()) {
         akf var6 = new akf(this, var1, var2, var3, var4);
         this.E.add(var6);
         var6.addListener(this);
         var7 = var6;
      }

      return var7;
   }

   public akf pC(IPrototypeItem var1) {
      akf var16;
      try (ACLock var2 = this.pC.wS().a()) {
         ICallingConvention var3 = var1.getCallingConvention();
         IReferenceType var4 = null;
         if ((var3.getFlags() & 256) != 0) {
            List var5 = var1.getReturnTypes();
            if (var5.size() == 1) {
               INativeType var6 = (INativeType)var5.get(0);
               if (TypeUtil.isCompositeType(var6)) {
                  var4 = var6.getReference();
               }
            }
         }

         ArrayList var12 = new ArrayList();

         for (INativeType var7 : var1.getReturnTypes()) {
            var12.add(this.pC(var7));
         }

         ArrayList var14 = new ArrayList();

         for (INativeType var8 : var1.getParameterTypes()) {
            INativeType var9 = TypeUtil.getNonAlias(var8);
            if (var9 instanceof IArrayType) {
               IReferenceType var17 = ((IArrayType)var9).getElementType().getReference();
               var14.add(this.pC(var17));
            } else {
               var14.add(this.pC(var8));
            }
         }

         if (var4 != null) {
            var12.set(0, this.pC(var4));
            var14.add(0, this.pC(var4));
         }

         var16 = this.pC(var3, var12, var14, var1.getPrototypeAttributes());
      }

      return var16;
   }

   @Override
   public IWildcardType parse(String var1, int var2) {
      return akp.pC(this, var1, var2);
   }
}
