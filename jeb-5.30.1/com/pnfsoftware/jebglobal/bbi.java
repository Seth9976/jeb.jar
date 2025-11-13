package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.units.code.asm.items.NativeItemEvent;
import com.pnfsoftware.jeb.core.units.code.asm.type.ClassVtablePaths;
import com.pnfsoftware.jeb.core.units.code.asm.type.IClassType;
import com.pnfsoftware.jeb.core.units.code.asm.type.INativeType;
import com.pnfsoftware.jeb.core.units.code.asm.type.IStructureTypeField;
import com.pnfsoftware.jeb.core.units.code.asm.type.IVirtualTableDefinition;
import com.pnfsoftware.jeb.core.units.code.asm.type.VirtualMethodCoordinates;
import com.pnfsoftware.jeb.util.base.Assert;
import com.pnfsoftware.jeb.util.concurrent.ACLock;
import com.pnfsoftware.jeb.util.format.Strings;
import com.pnfsoftware.jeb.util.serialization.annotations.Ser;
import com.pnfsoftware.jeb.util.serialization.annotations.SerId;
import com.pnfsoftware.jeb.util.serialization.annotations.SerTransient;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

@Ser
public class bbi extends bbv implements IClassType {
   @SerId(1)
   private int xK;
   @SerId(2)
   private axl Dw;
   @SerId(3)
   private List Uv = new CopyOnWriteArrayList();
   @SerId(4)
   private List zz = new CopyOnWriteArrayList();
   @SerId(5)
   private List JY = new CopyOnWriteArrayList();
   @SerId(6)
   private int HF;
   @SerTransient
   public boolean q;

   bbi(bby var1, String var2, String var3, int var4, int var5) {
      super(var1, var2, var3, Math.max(1, var4), var5);
      this.qa();
   }

   @Override
   public void onNativeItemEvent(NativeItemEvent var1) {
      super.onNativeItemEvent(var1);
   }

   @Override
   protected void RF() {
      super.RF();

      for (bbz var2 : this.Uv) {
         var2.xK();
         var2.Dw().PV();
      }

      for (bbi var3 : new ArrayList(this.JY)) {
         var3.PV();
      }

      for (bbi var7 : this.zz) {
         var7.RF(this);
      }
   }

   public void q(axl var1) {
      if (this.Dw != null) {
         throw new IllegalStateException();
      } else {
         this.Dw = var1;
      }
   }

   private void q(bbi var1) {
      this.za().verifyLocked();
      this.JY.add(var1);
      if (!this.If()) {
         this.q(true);
      }
   }

   private void RF(bbi var1) {
      this.za().verifyLocked();
      this.JY.remove(var1);
      if (this.JY.isEmpty() && this.If()) {
         this.q(false);
      }
   }

   public void q(IVirtualTableDefinition var1) {
      try (ACLock var2 = this.za().a()) {
         if (this.xK != 0 || this.getFieldsCount() > 0 || !this.Uv.isEmpty()) {
            throw new IllegalStateException();
         }

         if (var1 == null) {
            throw new IllegalArgumentException();
         }

         bbz var3 = (bbz)var1;
         bbt var4 = this.xK().q(this.xK().Uv("void"), 2);
         this.q("vtable", var4, 0);
         this.Uv.add(var3);
         this.HF = 1;
         var3.Dw = this;
      }
   }

   public void q(Collection var1, Collection var2) {
      Collection var3 = var1;
      Collection var4 = var2;

      try (ACLock var5 = this.za().a()) {
         if (var4 == null) {
            this.zz.addAll(var3);
         } else {
            label79: {
               if (this.xK == 0 && this.getFieldsCount() <= 0 && this.Uv.isEmpty()) {
                  if (var3 != null && var4 != null) {
                     int var6 = 0;

                     for (bbi var8 : var3) {
                        this.zz.add(var8);
                        this.q("super" + (var6 == 0 ? "" : var6), var8, -1);
                        var6++;
                     }

                     this.Uv.addAll(var4);
                     this.HF = var6;
                     Iterator var12 = var4.iterator();

                     while (true) {
                        if (!var12.hasNext()) {
                           break label79;
                        }

                        bbz var14 = (bbz)var12.next();
                        var14.Dw = this;
                     }
                  }

                  throw new IllegalArgumentException();
               }

               throw new IllegalStateException();
            }
         }

         for (bbi var13 : var3) {
            var13.q(this);
         }
      }
   }

   public axl oW() {
      return this.Dw;
   }

   public boolean gO() {
      return this.xK == 1;
   }

   public void nf() {
      if (this.xK != 0) {
         throw new IllegalStateException();
      } else {
         this.xK = 1;

         for (bbz var2 : this.Uv) {
            var2.RF();
         }

         this.io();
      }
   }

   public int gP() {
      return this.Uv.size();
   }

   @Override
   public List getVirtualTables() {
      return this.Uv;
   }

   public bbz q(int var1) {
      return (bbz)this.Uv.get(var1);
   }

   public boolean q(int var1, int var2) {
      if (var1 >= 0 && var1 < this.Uv.size()) {
         bbz var3 = (bbz)this.Uv.get(var1);
         if (var2 >= 0 && var2 < var3.size()) {
            return true;
         }
      }

      return false;
   }

   public List cC() {
      return this.zz;
   }

   public boolean sH() {
      return !this.zz.isEmpty();
   }

   public List CE() {
      return this.JY;
   }

   public boolean wF() {
      return !this.JY.isEmpty();
   }

   boolean q(bbz var1, int var2, String var3) {
      this.za().verifyLocked();
      int var4 = this.Uv.indexOf(var1);
      return var4 < 0 ? false : this.renameVirtualMethod(var4, var2, var3);
   }

   @Override
   public boolean renameVirtualMethod(int var1, int var2, String var3) {
      boolean var6;
      try (ACLock var4 = this.za().a()) {
         List var5 = q(this, var1);
         if (var5 == null) {
            return false;
         }

         q(this, var1, var2, var3);
         q(this, var5, var2, var3);
         var6 = true;
      }

      return var6;
   }

   private static void q(bbi var0, int var1, int var2, String var3) {
      var0.q = true;

      try {
         if (var1 < 0 || var1 >= var0.Uv.size()) {
            return;
         }

         bbz var4 = (bbz)var0.Uv.get(var1);
         if (var2 >= var4.size()) {
            return;
         }

         var4.xK.q(var2, var3);
         if (var0.Dw != null) {
            var0.Dw.q(var1, var2, var3);
         }

         List var5 = xK(var0);
         if (var1 >= var5.size()) {
            return;
         }

         List var6 = (List)var5.get(var1);
         if (var6.size() != 1) {
            Assert.a(var6.get(0) == var0);
            bbi var7 = (bbi)var6.get(1);
            var6.remove(0);
            int var8 = q(var7, var6);
            q(var7, var8, var2, var3);
            return;
         }
      } finally {
         var0.q = false;
      }
   }

   private static void q(bbi var0, List var1, int var2, String var3) {
      for (bbi var5 : var0.JY) {
         var5.q = true;

         try {
            ArrayList var6 = new ArrayList(var1);
            var6.add(0, var5);
            int var7 = q(var5, var6);
            if (var7 >= 0) {
               ((bbz)var5.Uv.get(var7)).xK.q(var2, var3);
               if (var5.q(var7, var2)) {
                  if (var5.Dw != null) {
                     var5.Dw.q(var7, var2, var3);
                  }

                  q(var5, var6, var2, var3);
               }
            }
         } finally {
            var5.q = false;
         }
      }
   }

   @Override
   public boolean collectVirtualMethodOverrides(int var1, int var2, Collection var3, Collection var4) {
      if (var3 != null) {
         q(this, var1, var2, var3);
      }

      if (var4 != null) {
         List var5 = q(this, var1);
         q(this, var5, var2, var4);
      }

      return true;
   }

   private static void q(bbi var0, int var1, int var2, Collection var3) {
      if (var1 >= 0 && var1 < var0.Uv.size()) {
         bbz var4 = (bbz)var0.Uv.get(var1);
         if (var2 < var4.size()) {
            var3.add(new VirtualMethodCoordinates(var0, var1, var2));
            List var5 = xK(var0);
            if (var1 < var5.size()) {
               List var6 = (List)var5.get(var1);
               if (var6.size() == 1) {
                  return;
               }

               Assert.a(var6.get(0) == var0);
               bbi var7 = (bbi)var6.get(1);
               var6.remove(0);
               var1 = q(var7, var6);
               q(var7, var1, var2, var3);
            }
         }
      }
   }

   private static void q(bbi var0, List var1, int var2, Collection var3) {
      for (bbi var5 : var0.JY) {
         ArrayList var6 = new ArrayList(var1);
         var6.add(0, var5);
         int var7 = q(var5, var6);
         if (var7 >= 0 && var5.q(var7, var2)) {
            var3.add(new VirtualMethodCoordinates(var5, var7, var2));
            q(var5, var6, var2, var3);
         }
      }
   }

   private static List xK(bbi var0) {
      return new ClassVtablePaths(var0, new bbj()).determineFull();
   }

   private static List q(bbi var0, int var1) {
      List var2 = xK(var0);
      return var1 >= 0 && var1 < var2.size() ? (List)var2.get(var1) : null;
   }

   private static int q(bbi var0, List var1) {
      int var2 = 0;

      for (List var4 : xK(var0)) {
         if (q(var4, var1)) {
            return var2;
         }

         var2++;
      }

      return -1;
   }

   private static boolean q(List var0, List var1) {
      if (var0.size() != var1.size()) {
         return false;
      } else {
         for (int var2 = 0; var2 < var0.size(); var2++) {
            if (var0.get(var2) != var1.get(var2)) {
               return false;
            }
         }

         return true;
      }
   }

   @Override
   public boolean q(bbu var1, String var2) {
      int var8;
      try (ACLock var3 = this.za().a()) {
         if (this.gO()) {
            var8 = this.getFields().indexOf(var1);
            if (var8 < this.HF) {
               return false;
            }
         }

         var8 = super.q(var1, var2);
      }

      return (boolean)var8;
   }

   @Override
   public bbu q(String var1, INativeType var2, int var3, int var4, int var5, int var6, boolean var7) {
      bbu var13;
      try (ACLock var8 = this.za().a()) {
         if (this.gO()) {
            int var9 = this.xK(this.HF).getOffset();
            if (var3 >= 0 && var3 < var9) {
               return null;
            }
         }

         var13 = super.q(var1, var2, var3, var4, var5, var6, var7);
      }

      return var13;
   }

   @Override
   public boolean q(IStructureTypeField var1) {
      int var7;
      try (ACLock var2 = this.za().a()) {
         if (this.gO()) {
            var7 = this.getIndexOfField(var1);
            if (var7 < this.HF) {
               return false;
            }
         }

         var7 = super.q(var1);
      }

      return (boolean)var7;
   }

   @Override
   public List getInstanceFields() {
      List var1 = this.getFields();
      return var1.subList(this.HF, var1.size());
   }

   @Override
   public axl Dw() {
      return this.Dw;
   }

   @Override
   public String toString() {
      return Strings.ff("ClassType{%d}(%s,%d)", this.zz.size(), this.getSignature(false), this.getSize());
   }
}
