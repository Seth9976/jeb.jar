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
public class auq extends auo implements INativeClassItem, ayn {
   @SerId(1)
   private ayy pC;
   @SerId(2)
   private ayi A;
   @SerId(4)
   private List kS = new ArrayList();
   @SerId(5)
   private List wS = new ArrayList();
   @SerId(6)
   private List UT = new ArrayList();
   @SerId(7)
   private int ld;

   public auq(ayy var1, String var2) {
      super(-1073741824, var2);
      if (var2 == null) {
         throw new IllegalArgumentException();
      } else if (var1 == null) {
         throw new IllegalArgumentException();
      } else {
         this.pC = var1;
         this.pC(var1.wS());
         this.NS();
      }
   }

   @Override
   public ayy kS() {
      return this.pC;
   }

   public boolean wS() {
      return this.ld == 1;
   }

   public void UT() {
      if (this.ld != 0) {
         throw new IllegalStateException();
      } else {
         this.ld = 1;

         for (auu var2 : this.getVirtualMethods(true)) {
            var2.addListener(this);
         }

         this.pC(true, null);
         this.WR();
         this.eP();
      }
   }

   @Override
   public String getName(boolean var1) {
      return this.A != null ? this.A.getName(var1) : super.getName(var1);
   }

   @Override
   public void setName(String var1) {
      try (ACLock var2 = this.gp().a()) {
         if (this.A != null) {
            this.A.setName(var1);
         }

         super.setName(var1);
      }
   }

   @Override
   public String getSignature(boolean var1) {
      return this.A != null ? this.A.getSignature(var1) : super.getSignature(var1);
   }

   @Override
   public String getAddress(boolean var1) {
      auo var2 = this.pC.A(this);
      return var2 != null ? var2.getAddress(var1) + "::" + this.getName(var1) : this.getName(var1);
   }

   public ayp E() {
      return this.pC.kS(this);
   }

   public void pC(ayi var1) {
      try (ACLock var2 = this.gp().a()) {
         if (this.A != null && this.A != var1) {
            throw new IllegalStateException("The type of this class item was already set");
         }

         if (var1 == null) {
            throw new IllegalArgumentException();
         }

         this.A = var1;
         var1.pC(this);
         var1.addListener(this);
      }
   }

   @Override
   public void onNativeItemEvent(NativeItemEvent var1) {
      if (this.wS()) {
         if (var1.getItem() != null) {
            if (var1.getItem() == this.A) {
               if (var1.getType() == NativeItemEventType.MODIFIED && var1.getDetails() instanceof ayu) {
                  ayu var2 = (ayu)var1.getDetails();
                  int var3 = this.A.getIndexOfField(var2);
                  this.pC(var3 >= 0, var2);
               }

               if (var1.getType() == NativeItemEventType.DISPOSED) {
                  this.pC(false, null);
                  this.xC();
               }
            }

            if (var1.getItem() instanceof auu) {
               auu var6 = (auu)var1.getItem();
               if (var1.getType() == NativeItemEventType.DISPOSED) {
                  for (avc var4 : this.UT) {
                     int var5 = var4.find(var6);
                     if (var5 >= 0) {
                        var4.A(var5);
                        var6.removeListener(this);
                     }
                  }
               }
            }
         }
      }
   }

   private int pC(boolean var1, ayu var2) {
      int var3 = 0;
      if (var1) {
         if (var2 == null) {
            for (ayu var5 : this.A.getInstanceFields()) {
               this.pC.pC(this, this.A, var5);
               var3++;
            }
         } else {
            this.pC.pC(this, this.A, var2);
            var3 = 1;
         }
      } else {
         List var7 = this.pC.pC((auo)this).pC(aur.class);
         if (var2 == null) {
            for (aur var6 : var7) {
               var6.xC();
               var3++;
            }
         } else {
            for (aur var10 : var7) {
               if (var10.UT() == var2) {
                  var10.xC();
                  var3 = 1;
                  break;
               }
            }
         }
      }

      return var3;
   }

   public ayi sY() {
      return this.A;
   }

   @Override
   public List getVirtualTables() {
      return this.UT;
   }

   public void pC(List var1) {
      if (this.ld != 0) {
         throw new IllegalStateException();
      } else {
         var1.forEach(var1x -> this.UT.add((avc)var1x));
      }
   }

   public auu pC(int var1, int var2) {
      if (var1 >= 0 && var1 < this.UT.size()) {
         avc var3 = (avc)this.UT.get(var1);
         if (var2 >= 0 && var2 < var3.size()) {
            return var3.pC(var2);
         }
      }

      return null;
   }

   @Override
   public Couple getCoordinatesOfVirtualMethod(INativeMethodItem var1) {
      int var2 = 0;

      for (avc var4 : this.UT) {
         int var5 = var4.find(var1);
         if (var5 >= 0) {
            return new Couple(var2, var5);
         }

         var2++;
      }

      return null;
   }

   public boolean pC(int var1, int var2, String var3) {
      boolean var6;
      try (ACLock var4 = this.gp().a()) {
         auu var5 = this.pC(var1, var2);
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
      try (ACLock var3 = this.gp().a()) {
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
         if (!this.A.collectVirtualMethodOverrides((Integer)var4.getFirst(), (Integer)var4.getSecond(), var5, var6)) {
            return false;
         } else {
            if (var5 != null) {
               for (VirtualMethodCoordinates var8 : var5) {
                  auu var9 = ((ayi)var8.getClasstype()).E().pC(var8.getTableIndex(), var8.getMethodIndex());
                  var2.add(var9);
               }
            }

            if (var6 != null) {
               for (VirtualMethodCoordinates var11 : var6) {
                  auu var12 = ((ayi)var11.getClasstype()).E().pC(var11.getTableIndex(), var11.getMethodIndex());
                  var3.add(var12);
               }
            }

            return true;
         }
      }
   }

   @Override
   public List getSupertypes() {
      return this.A == null ? Collections.emptyList() : this.A.ld();
   }

   @Override
   public List getSubtypes() {
      return this.A == null ? Collections.emptyList() : this.A.UO();
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
         for (auu var4 : this.pC.pC((auo)this).pC(auu.class)) {
            if ((var4.getGenericFlags() & 268435456) != 0) {
               var2.add(var4);
            }
         }
      } else {
         for (avc var6 : this.UT) {
            var2.addAll(var6.getAll());
         }
      }

      return var2;
   }

   @Override
   public List getNonVirtualMethods() {
      return this.kS;
   }

   public void pC(auu var1) {
      try (ACLock var2 = this.gp().a()) {
         if (var1 != null && !this.kS.contains(var1)) {
            this.kS.add(var1);
            return;
         }
      }
   }

   @Override
   public List getStaticMethods() {
      return this.wS;
   }

   public void A(auu var1) {
      try (ACLock var2 = this.gp().a()) {
         if (var1 != null && !this.wS.contains(var1)) {
            this.wS.add(var1);
            return;
         }
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

      for (auu var3 : this.getInstanceMethods()) {
         if ((var3.getGenericFlags() & 65536) != 0) {
            var1.add(var3);
         }
      }

      return var1;
   }

   @Override
   public List getDestructors() {
      ArrayList var1 = new ArrayList();

      for (auu var3 : this.getInstanceMethods()) {
         if ((var3.getGenericFlags() & 536870912) != 0) {
            var1.add(var3);
         }
      }

      return var1;
   }

   @Override
   public List getFields() {
      return this.pC.pC((auo)this).pC(aur.class);
   }

   @Override
   public List getInstanceFields() {
      ArrayList var1 = new ArrayList();

      for (aur var3 : this.pC.pC((auo)this).pC(aur.class)) {
         if ((var3.getGenericFlags() & 8) == 0) {
            var1.add(var3);
         }
      }

      return var1;
   }

   @Override
   public List getStaticFields() {
      ArrayList var1 = new ArrayList();

      for (aur var3 : this.pC.pC((auo)this).pC(aur.class)) {
         if ((var3.getGenericFlags() & 8) != 0) {
            var1.add(var3);
         }
      }

      return var1;
   }

   @Override
   protected void A() {
      super.A();
   }

   @Override
   protected final void c_() {
      super.c_();
      this.pC.pC(this);
   }

   @Override
   public String toString() {
      return Strings.ff("Class{%s}", this.getName(true));
   }
}
