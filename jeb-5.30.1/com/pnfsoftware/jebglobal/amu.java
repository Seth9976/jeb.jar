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
public class amu implements IWildcardTypeManager, INativeItemListener {
   @SerId(1)
   bby q;
   @SerId(2)
   int RF;
   @SerId(3)
   int xK;
   @SerId(4)
   private IdentityHashMap Dw = new IdentityHashMap();
   @SerId(5)
   private Map Uv = new HashMap();
   @SerId(6)
   private List oW = new ArrayList();
   @SerId(7)
   private List gO = new CopyOnWriteArrayList();
   @SerId(8)
   private IWildcardType.Group nf;

   public amu(ITypeManager var1) {
      this(var1, IWildcardType.Group.INTEGER);
   }

   public amu(ITypeManager var1, IWildcardType.Group var2) {
      if (var1 != null && var2 != null) {
         this.q = (bby)var1;
         this.RF = this.q.getSlotSize() * 8;
         this.xK = this.q.getPointerSize() * 8;
         this.nf = var2;
      } else {
         throw new NullPointerException();
      }
   }

   @Override
   public int getSlotBitsize() {
      return this.RF;
   }

   @Override
   public int getPointerBitsize() {
      return this.xK;
   }

   public IWildcardType.Group q() {
      return this.nf;
   }

   @Override
   public void onNativeItemEvent(NativeItemEvent var1) {
      if (var1.getItem() instanceof ali var3) {
         if (var3.isDisposed()) {
            if (var3 instanceof ams) {
               ams var7 = (ams)this.Dw.remove(((ams)var3).getNativeType());
               Assert.a(var7 == var3);
            } else if (var3 instanceof ami) {
               boolean var4 = false;

               for (ami var6 : this.oW) {
                  if (var6 == var3) {
                     var4 = this.oW.remove((ami)var3);
                     break;
                  }
               }

               Assert.a(var4);
            }
         }

         this.q(var1);
      }
   }

   @Override
   public void addListener(INativeItemListener var1) {
      if (var1 != null) {
         this.gO.add(var1);
      }
   }

   @Override
   public void removeListener(INativeItemListener var1) {
      if (var1 != null) {
         this.gO.remove(var1);
      }
   }

   public void q(NativeItemEvent var1) {
      for (INativeItemListener var3 : this.gO) {
         if (var3 != var1.getItem()) {
            var3.onNativeItemEvent(var1);
         }
      }
   }

   public bby RF() {
      return this.q;
   }

   public ams q(String var1) {
      Object var2 = this.q.Uv(var1);
      if (var2 == null) {
         var2 = TypeUtil.buildQuickType(this.q, var1);
         if (var2 == null) {
            throw new IllegalArgumentException("Cannot create etype for illegal native type: " + var1);
         }
      }

      return this.q((INativeType)var2);
   }

   public ams q(INativeType var1) {
      ams var4;
      try (ACLock var2 = this.q.oW().a()) {
         if (var1 == null) {
            throw new NullPointerException("Native type is null");
         }

         ams var3 = (ams)this.Dw.get(var1);
         if (var3 == null) {
            var3 = new ams(this, var1);
            this.Dw.put(var1, var3);
            var3.addListener(this);
         }

         var4 = var3;
      }

      return var4;
   }

   public ams q(int var1) {
      ams var4;
      try (ACLock var2 = this.q.oW().a()) {
         if (var1 <= 0) {
            throw new IllegalArgumentException();
         }

         ams var3 = (ams)this.Uv.get(var1);
         if (var3 == null) {
            var3 = new ams(this, var1);
            this.Uv.put(var1, var3);
         }

         var4 = var3;
      }

      return var4;
   }

   public ams RF(int var1) {
      return this.q(var1 * this.RF);
   }

   public ams q(int var1, int var2) {
      return this.q(var1).q(var2);
   }

   public ams xK(int var1) {
      ams var6;
      try (ACLock var2 = this.q.oW().a()) {
         if (var1 <= 0) {
            throw new IllegalArgumentException("Cannot create wildcard type with effective bitsize " + var1);
         }

         int var3 = (var1 + 7) / 8;
         bbd var5 = this.q.q(var3, false);
         int var4;
         if (var5 != null) {
            var4 = var5.getSize() * 8;
         } else {
            var4 = var3 * 8;
         }

         var6 = this.q(var4, var1);
      }

      return var6;
   }

   public ams Dw(int var1) {
      ams var4;
      try (ACLock var2 = this.q.oW().a()) {
         ams var3 = this.RF(1);
         var3 = var3.q(IWildcardType.Group.POINTER);
         var3 = var3.xK(var1);
         var4 = var3;
      }

      return var4;
   }

   public ami q(ICallingConvention var1, IWildcardType var2, List var3, Collection var4) {
      ArrayList var5 = null;
      if (var2 != null && !var2.isVoid()) {
         var5 = new ArrayList(1);
         var5.add(var2);
      }

      return this.q(var1, var5, var3, var4);
   }

   public ami q(ICallingConvention var1, List var2, List var3, Collection var4) {
      ami var7;
      try (ACLock var5 = this.q.oW().a()) {
         ami var6 = new ami(this, var1, var2, var3, var4);
         this.oW.add(var6);
         var6.addListener(this);
         var7 = var6;
      }

      return var7;
   }

   public ami q(IPrototypeItem var1) {
      ami var16;
      try (ACLock var2 = this.q.oW().a()) {
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
            var12.add(this.q(var7));
         }

         ArrayList var14 = new ArrayList();

         for (INativeType var8 : var1.getParameterTypes()) {
            INativeType var9 = TypeUtil.getNonAlias(var8);
            if (var9 instanceof IArrayType) {
               IReferenceType var17 = ((IArrayType)var9).getElementType().getReference();
               var14.add(this.q(var17));
            } else {
               var14.add(this.q(var8));
            }
         }

         if (var4 != null) {
            var12.set(0, this.q(var4));
            var14.add(0, this.q(var4));
         }

         var16 = this.q(var3, var12, var14, var1.getPrototypeAttributes());
      }

      return var16;
   }

   @Override
   public IWildcardType parse(String var1, int var2) {
      return ams.q(this, var1, var2);
   }
}
