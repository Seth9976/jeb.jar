package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.units.code.asm.items.INativeClassItem;
import com.pnfsoftware.jeb.core.units.code.asm.items.INativeMethodItem;
import com.pnfsoftware.jeb.core.units.code.asm.items.NativeItemEvent;
import com.pnfsoftware.jeb.core.units.code.asm.items.NativeItemEventType;
import com.pnfsoftware.jeb.core.units.code.asm.type.VirtualMethodCoordinates;
import com.pnfsoftware.jeb.util.base.Couple;
import com.pnfsoftware.jeb.util.concurrent.ACLock;
import com.pnfsoftware.jeb.util.format.Strings;
import com.pnfsoftware.jeb.util.serialization.annotations.Ser;
import com.pnfsoftware.jeb.util.serialization.annotations.SerId;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

@Ser
public class axl extends axj implements INativeClassItem, bbn {
   @SerId(1)
   private bby q;
   @SerId(2)
   private bbi RF;
   @SerId(4)
   private List xK = new ArrayList();
   @SerId(5)
   private List Dw = new ArrayList();
   @SerId(6)
   private List Uv = new ArrayList();
   @SerId(7)
   private int zz;

   public axl(bby var1, String var2) {
      super(-1073741824, var2);
      if (var2 == null) {
         throw new IllegalArgumentException();
      } else if (var1 == null) {
         throw new IllegalArgumentException();
      } else {
         this.q = var1;
         this.q(var1.oW());
         this.qa();
      }
   }

   @Override
   public bby xK() {
      return this.q;
   }

   public boolean Dw() {
      return this.zz == 1;
   }

   public void Uv() {
      if (this.zz != 0) {
         throw new IllegalStateException();
      } else {
         this.zz = 1;

         for (axp var2 : this.getVirtualMethods(true)) {
            var2.addListener(this);
         }

         this.q(true, null);
         this.io();
         this.Ef();
      }
   }

   @Override
   public String getName(boolean var1) {
      return this.RF != null ? this.RF.getName(var1) : super.getName(var1);
   }

   @Override
   public void setName(String var1) {
      try (ACLock var2 = this.za().a()) {
         if (this.RF != null) {
            this.RF.setName(var1);
         }

         super.setName(var1);
      }
   }

   @Override
   public String getSignature(boolean var1) {
      return this.RF != null ? this.RF.getSignature(var1) : super.getSignature(var1);
   }

   @Override
   public String getAddress(boolean var1) {
      axj var2 = this.q.xK(this);
      return var2 != null ? var2.getAddress(var1) + "::" + this.getName(var1) : this.getName(var1);
   }

   public bbp oW() {
      return this.q.Dw(this);
   }

   public void q(bbi var1) {
      try (ACLock var2 = this.za().a()) {
         if (this.RF != null && this.RF != var1) {
            throw new IllegalStateException("The type of this class item was already set");
         }

         if (var1 == null) {
            throw new IllegalArgumentException();
         }

         this.RF = var1;
         var1.q(this);
         var1.addListener(this);
      }
   }

   @Override
   public void onNativeItemEvent(NativeItemEvent var1) {
      if (this.Dw()) {
         if (var1.getItem() != null) {
            if (var1.getItem() == this.RF) {
               if (var1.getType() == NativeItemEventType.MODIFIED && var1.getDetails() instanceof bbu) {
                  bbu var2 = (bbu)var1.getDetails();
                  int var3 = this.RF.getIndexOfField(var2);
                  this.q(var3 >= 0, var2);
               }

               if (var1.getType() == NativeItemEventType.DISPOSED) {
                  this.q(false, null);
                  this.PV();
               }
            }

            if (var1.getItem() instanceof axp) {
               axp var6 = (axp)var1.getItem();
               if (var1.getType() == NativeItemEventType.DISPOSED) {
                  for (axx var4 : this.Uv) {
                     int var5 = var4.find(var6);
                     if (var5 >= 0) {
                        var4.RF(var5);
                        var6.removeListener(this);
                     }
                  }
               }
            }
         }
      }
   }

   private int q(boolean var1, bbu var2) {
      int var3 = 0;
      if (var1) {
         if (var2 == null) {
            for (bbu var5 : this.RF.getInstanceFields()) {
               this.q.q(this, this.RF, var5);
               var3++;
            }
         } else {
            this.q.q(this, this.RF, var2);
            var3 = 1;
         }
      } else {
         List var7 = this.q.q((axj)this).q(axm.class);
         if (var2 == null) {
            for (axm var6 : var7) {
               var6.PV();
               var3++;
            }
         } else {
            for (axm var10 : var7) {
               if (var10.oW() == var2) {
                  var10.PV();
                  var3 = 1;
                  break;
               }
            }
         }
      }

      return var3;
   }

   public bbi gO() {
      return this.RF;
   }

   @Override
   public List getVirtualTables() {
      return this.Uv;
   }

   public void q(int var1, axp var2) {
      if (this.zz != 0) {
         throw new IllegalStateException();
      } else if (var2 == null) {
         throw new RuntimeException();
      } else if (var1 >= 0 && var1 <= this.Uv.size()) {
         if (var1 == this.Uv.size()) {
            this.Uv.add(new axx(0L));
         }

         ((axx)this.Uv.get(var1)).q(var2);
      } else {
         throw new RuntimeException();
      }
   }

   public void q(List var1) {
      if (this.zz != 0) {
         throw new IllegalStateException();
      } else {
         var1.forEach(var1x -> this.Uv.add((axx)var1x));
      }
   }

   public axp q(int var1, int var2) {
      if (var1 >= 0 && var1 < this.Uv.size()) {
         axx var3 = (axx)this.Uv.get(var1);
         if (var2 >= 0 && var2 < var3.size()) {
            return var3.q(var2);
         }
      }

      return null;
   }

   @Override
   public Couple getCoordinatesOfVirtualMethod(INativeMethodItem var1) {
      int var2 = 0;

      for (axx var4 : this.Uv) {
         int var5 = var4.find(var1);
         if (var5 >= 0) {
            return new Couple(var2, var5);
         }

         var2++;
      }

      return null;
   }

   public boolean q(int var1, int var2, String var3) {
      boolean var6;
      try (ACLock var4 = this.za().a()) {
         axp var5 = this.q(var1, var2);
         if (var5 == null) {
            return false;
         }

         if (!Strings.equals(var5.getName(true), var3)) {
            var5.setName(var3);
         }

         var6 = true;
      }

      return var6;
   }

   @Override
   public boolean renameVirtualMethod(INativeMethodItem var1, String var2) {
      boolean var4;
      try (ACLock var3 = this.za().a()) {
         if (!this.getVirtualMethods(false).contains(var1)) {
            return false;
         }

         if (!Strings.equals(var1.getName(true), var2)) {
            var1.setName(var2);
         }

         var4 = true;
      }

      return var4;
   }

   @Override
   public boolean collectVirtualMethodOverrides(INativeMethodItem var1, Collection var2, Collection var3) {
      Couple var4 = this.getCoordinatesOfVirtualMethod(var1);
      if (var4 == null) {
         return false;
      } else {
         ArrayList var5 = var2 == null ? null : new ArrayList();
         ArrayList var6 = var3 == null ? null : new ArrayList();
         if (!this.RF.collectVirtualMethodOverrides((Integer)var4.getFirst(), (Integer)var4.getSecond(), var5, var6)) {
            return false;
         } else {
            if (var5 != null) {
               for (VirtualMethodCoordinates var8 : var5) {
                  axp var9 = ((bbi)var8.getClasstype()).oW().q(var8.getTableIndex(), var8.getMethodIndex());
                  var2.add(var9);
               }
            }

            if (var6 != null) {
               for (VirtualMethodCoordinates var11 : var6) {
                  axp var12 = ((bbi)var11.getClasstype()).oW().q(var11.getTableIndex(), var11.getMethodIndex());
                  var3.add(var12);
               }
            }

            return true;
         }
      }
   }

   @Override
   public List getSupertypes() {
      return this.RF == null ? Collections.emptyList() : this.RF.cC();
   }

   @Override
   public List getSubtypes() {
      return this.RF == null ? Collections.emptyList() : this.RF.CE();
   }

   @Override
   public List getImplementedInterfaces() {
      return new ArrayList();
   }

   @Override
   public List getMethods() {
      ArrayList var1 = new ArrayList();
      var1.addAll(this.getStaticMethods());
      var1.addAll(this.getNonVirtualMethods());
      var1.addAll(this.getVirtualMethods(true));
      return var1;
   }

   @Override
   public List getVirtualMethods(boolean var1) {
      ArrayList var2 = new ArrayList();
      if (var1) {
         for (axp var4 : this.q.q((axj)this).q(axp.class)) {
            if ((var4.getGenericFlags() & 268435456) != 0) {
               var2.add(var4);
            }
         }
      } else {
         for (axx var6 : this.Uv) {
            var2.addAll(var6.getAll());
         }
      }

      return var2;
   }

   @Override
   public List getNonVirtualMethods() {
      return this.xK;
   }

   public void q(axp var1) {
      try (ACLock var2 = this.za().a()) {
         if (var1 != null && !this.xK.contains(var1)) {
            this.xK.add(var1);
            return;
         }
      }
   }

   public void RF(axp var1) {
      try (ACLock var2 = this.za().a()) {
         this.xK.remove(var1);
      }
   }

   @Override
   public List getStaticMethods() {
      return this.Dw;
   }

   public void xK(axp var1) {
      try (ACLock var2 = this.za().a()) {
         if (var1 != null && !this.Dw.contains(var1)) {
            this.Dw.add(var1);
            return;
         }
      }
   }

   public void Dw(axp var1) {
      try (ACLock var2 = this.za().a()) {
         this.Dw.remove(var1);
      }
   }

   @Override
   public List getInstanceMethods() {
      ArrayList var1 = new ArrayList();
      var1.addAll(this.getNonVirtualMethods());
      var1.addAll(this.getVirtualMethods(true));
      return var1;
   }

   @Override
   public List getConstructors() {
      ArrayList var1 = new ArrayList();

      for (axp var3 : this.getInstanceMethods()) {
         if ((var3.getGenericFlags() & 65536) != 0) {
            var1.add(var3);
         }
      }

      return var1;
   }

   @Override
   public List getDestructors() {
      ArrayList var1 = new ArrayList();

      for (axp var3 : this.getInstanceMethods()) {
         if ((var3.getGenericFlags() & 536870912) != 0) {
            var1.add(var3);
         }
      }

      return var1;
   }

   @Override
   public List getFields() {
      return this.q.q((axj)this).q(axm.class);
   }

   @Override
   public List getInstanceFields() {
      ArrayList var1 = new ArrayList();

      for (axm var3 : this.q.q((axj)this).q(axm.class)) {
         if ((var3.getGenericFlags() & 8) == 0) {
            var1.add(var3);
         }
      }

      return var1;
   }

   @Override
   public List getStaticFields() {
      ArrayList var1 = new ArrayList();

      for (axm var3 : this.q.q((axj)this).q(axm.class)) {
         if ((var3.getGenericFlags() & 8) != 0) {
            var1.add(var3);
         }
      }

      return var1;
   }

   @Override
   protected void RF() {
      super.RF();
   }

   @Override
   protected final void c_() {
      super.c_();
      this.q.q(this);
   }

   @Override
   public String toString() {
      return Strings.ff("Class{%s}", this.getName(true));
   }
}
