package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.units.code.asm.items.INativeItem;
import com.pnfsoftware.jeb.core.units.code.asm.items.NativeItemEvent;
import com.pnfsoftware.jeb.core.units.code.asm.items.NativeItemEventType;
import com.pnfsoftware.jeb.core.units.code.asm.type.ICallingConvention;
import com.pnfsoftware.jeb.core.units.code.asm.type.INativeType;
import com.pnfsoftware.jeb.core.units.code.asm.type.IPrototypeItem;
import com.pnfsoftware.jeb.core.units.code.asm.type.PrototypeAttribute;
import com.pnfsoftware.jeb.core.units.code.asm.type.TypeLayoutInfo;
import com.pnfsoftware.jeb.core.units.code.asm.type.TypeUtil;
import com.pnfsoftware.jeb.util.base.Assert;
import com.pnfsoftware.jeb.util.collect.ConcurrentHashSet;
import com.pnfsoftware.jeb.util.concurrent.ACLock;
import com.pnfsoftware.jeb.util.format.Strings;
import com.pnfsoftware.jeb.util.serialization.annotations.Ser;
import com.pnfsoftware.jeb.util.serialization.annotations.SerId;
import com.pnfsoftware.jeb.util.serialization.annotations.SerTransient;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.concurrent.CopyOnWriteArrayList;

@Ser
public class bbs extends bbd implements IPrototypeItem {
   @SerId(1)
   ICallingConvention q;
   @SerId(2)
   List RF = new CopyOnWriteArrayList();
   @SerId(3)
   List xK = new CopyOnWriteArrayList();
   @SerId(4)
   volatile Set Dw;
   @SerTransient
   List Uv = new CopyOnWriteArrayList();
   @SerTransient
   String zz;

   bbs(bby var1, ICallingConvention var2, List var3, List var4, Collection var5) {
      super(var1, null);
      if (var3 == null) {
         var3 = new ArrayList();
      }

      if (var4 == null) {
         var4 = new ArrayList();
      }

      if (var2 == null) {
         var2 = var1.Dw().getDefaultConvention();
      }

      for (bbd var7 : var3) {
         this.RF(var7, false);
      }

      for (bbd var9 : var4) {
         this.xK(var9, false);
      }

      this.q = var2;
      if (var5 != null) {
         this.Dw = new HashSet(var5);
         if (this.Dw.contains(PrototypeAttribute.VarArg)) {
            this.RF(128, false);
         }
      }
   }

   @Override
   public int getSize() {
      return this.xK().getPointerSize();
   }

   @Override
   public String getName(boolean var1) {
      StringBuilder var2 = new StringBuilder();
      if (!this.gO()) {
         var2.append(this.nf().getName(var1));
      } else {
         var2.append("(");
         int var3 = 0;

         for (bbd var5 : this.RF) {
            if (var3 >= 1) {
               var2.append(", ");
            }

            var2.append(var5.getName(var1));
            var3++;
         }

         var2.append("):");
      }

      var2.append("(");
      if (this.xK == null) {
         var2.append("?");
      } else {
         int var7 = 0;

         for (bbd var9 : this.xK) {
            if (var7 >= 1) {
               var2.append(",");
            }

            String var6 = var9 == null ? "?" : var9.getName(var1);
            var2.append(var6);
            var7++;
         }
      }

      if (this.isVariableArgument()) {
         if (this.xK != null && !this.xK.isEmpty()) {
            var2.append(",");
         }

         var2.append("...");
      }

      var2.append(")");
      return var2.toString();
   }

   @Override
   public void setName(String var1) {
      throw new RuntimeException("Cannot set name of a prototype");
   }

   @Override
   public String q(boolean var1, String var2) {
      return this.q(var1, var2, null);
   }

   public String q(boolean var1, String var2, List var3) {
      if (var2 == null) {
         var2 = this.getRoutineName();
         if (var2 == null) {
            var2 = "f";
         }
      }

      if (var3 == null) {
         var3 = this.Uv;
      }

      StringBuilder var4 = new StringBuilder();
      boolean var5 = this.gO();
      if (!var5) {
         var4.append(this.nf().getSignature(var1));
      }

      var4.append(' ');
      var4.append(this.q.getName());

      for (PrototypeAttribute var7 : this.getPrototypeAttributes()) {
         if (var7 != PrototypeAttribute.VarArg) {
            var4.append(' ').append(var7.format());
         }
      }

      if (var2 != null) {
         var4.append(' ');
         var4.append(var2);
      }

      var4.append("(");
      int var10 = 0;

      for (bbd var8 : this.xK) {
         if (var10 >= 1) {
            var4.append(", ");
         }

         String var9 = null;
         if (var3 != null && var10 < var3.size()) {
            var9 = (String)var3.get(var10);
         }

         var4.append(var8.q(var1, var9));
         var10++;
      }

      if (this.isVariableArgument()) {
         if (!this.xK.isEmpty()) {
            var4.append(", ");
         }

         var4.append("...");
      }

      var4.append(")");
      if (this.gO()) {
         var4.append(": (");
         var10 = 0;

         for (bbd var14 : this.RF) {
            if (var10 >= 1) {
               var4.append(", ");
            }

            var4.append(var14.getSignature(var1));
            var10++;
         }

         var4.append(")");
      }

      return var4.toString();
   }

   @Override
   public boolean isVariableArgument() {
      return this.Dw != null && this.Dw.contains(PrototypeAttribute.VarArg);
   }

   @Override
   public boolean isNoReturn() {
      return this.Dw != null && this.Dw.contains(PrototypeAttribute.NoReturn);
   }

   @Override
   public Collection getPrototypeAttributes() {
      return this.Dw == null ? Collections.emptySet() : this.Dw;
   }

   public boolean oW() {
      return this.Dw != null && !this.Dw.isEmpty();
   }

   public void q(PrototypeAttribute var1, boolean var2) {
      try (ACLock var3 = this.za().a()) {
         if (var1 == null) {
            throw new IllegalArgumentException();
         }

         if (var1 == PrototypeAttribute.VarArg) {
            this.RF(128, false);
         }

         if (this.Dw == null) {
            this.Dw = new ConcurrentHashSet();
         }

         boolean var4 = this.Dw.add(var1);
         if (var2) {
            this.Dw(var4);
         }
      }
   }

   public void RF(PrototypeAttribute var1, boolean var2) {
      try (ACLock var3 = this.za().a()) {
         if (var1 == null) {
            throw new IllegalArgumentException();
         }

         if (this.Dw == null) {
            return;
         }

         if (var1 == PrototypeAttribute.VarArg) {
            this.xK(128, false);
         }

         boolean var4 = this.Dw.remove(var1);
         if (var2) {
            this.Dw(var4);
         }
      }
   }

   private bbd q(bbd var1, bbd var2, boolean var3) {
      if (var2 != null && var1 == null) {
         throw new IllegalArgumentException();
      } else if (var2 == var1) {
         return var2;
      } else {
         if (var2 != null) {
            var2.removeListener(this);
         }

         var1.addListener(this);
         if (var3) {
            this.q(NativeItemEventType.MODIFIED);
         }

         return var1;
      }
   }

   @Override
   public int getCountOfReturns() {
      return this.RF.size();
   }

   public boolean gO() {
      return this.RF.size() >= 2;
   }

   public bbd nf() {
      return this.RF.isEmpty() ? this.xK().nf() : (bbd)this.RF.get(0);
   }

   public void q(bbd var1, boolean var2) {
      this.Me();
      Assert.a(this.RF.isEmpty());
      if (var1 != null && TypeUtil.isVoid(var1)) {
         var1 = null;
      }

      this.q(var1, null, var2);
      if (var1 != null) {
         this.RF(var1, var2);
      }
   }

   @Override
   public List getReturnTypes() {
      return this.RF;
   }

   public void RF(bbd var1, boolean var2) {
      this.Me();
      if (!this.RF.isEmpty() || var1 == null || !TypeUtil.isVoid(var1)) {
         try (ACLock var3 = this.za().a()) {
            if (var1 == null || TypeUtil.isVoid(var1)) {
               throw new NullPointerException("Illegal return type: " + var1);
            }

            this.RF.add(this.q(var1, null, var2));
         }
      }
   }

   @Override
   public int getCountOfParameters() {
      return this.xK.size();
   }

   @Override
   public List getParameterTypes() {
      return this.xK;
   }

   @Override
   public List getParameterNames() {
      return this.Uv;
   }

   public void xK(bbd var1, boolean var2) {
      this.q(var1, null, var2);
   }

   public void q(bbd var1, String var2, boolean var3) {
      this.Me();

      try (ACLock var4 = this.za().a()) {
         if (var1 == null || TypeUtil.isVoid(var1)) {
            throw new NullPointerException("Illegal parameter type: " + var1);
         }

         this.xK.add(this.q(var1, null, var3));
         this.Uv.add(var2);
      }
   }

   @Override
   public String getRoutineName() {
      return this.zz;
   }

   public void q(String var1) {
      this.zz = var1;
   }

   @Override
   public ICallingConvention getCallingConvention() {
      return this.q;
   }

   public void q(ICallingConvention var1) {
      this.Me();
      if (var1 == null) {
         throw new IllegalArgumentException();
      } else {
         this.q = var1;
      }
   }

   @Override
   public void onNativeItemEvent(NativeItemEvent var1) {
      INativeItem var2 = var1.getItem();
      if (this.RF.contains(var2) || this.xK.contains(var2)) {
         if (var1.getType() == NativeItemEventType.DISPOSED) {
            this.PV();
         } else {
            this.q(NativeItemEventType.UPDATED);
         }
      }
   }

   @Override
   protected void RF() {
      super.RF();

      for (bbd var2 : this.RF) {
         var2.removeListener(this);
      }

      for (bbd var4 : this.xK) {
         var4.removeListener(this);
      }
   }

   @Override
   public String toString() {
      return Strings.ff("Prototype(%s)", this.getSignature(false));
   }

   public bbs gP() {
      if (this.q != null && (this.q.getFlags() & 256) != 0 && this.q.getIPRDConvention() != null && this.getCountOfReturns() == 1) {
         bbd var1 = this.nf();
         INativeType var2 = TypeUtil.getNonAlias(var1);
         if (!TypeUtil.isCompositeType(var2)) {
            return this;
         } else {
            TypeLayoutInfo var3 = TypeUtil.getLayoutInfo(var2);
            if (var3.getSlotcount() < this.q.getIPRDMinimumSlotCount()) {
               return this;
            } else {
               ArrayList var4 = new ArrayList(this.xK);
               bbt var5 = this.nf().Uv();
               var4.add(0, var5);
               ArrayList var6 = new ArrayList(this.Uv);
               var6.add(0, "ptrReturnData");
               bbs var7 = this.xK().q(this.q.getIPRDConvention(), var5, var4, this.Dw);
               var7.Uv.addAll(var6);
               var7.zz = this.zz;
               return var7;
            }
         }
      } else {
         return this;
      }
   }
}
