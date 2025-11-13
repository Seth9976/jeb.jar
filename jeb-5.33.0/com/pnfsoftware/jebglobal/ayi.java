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
public class ayi extends ayv implements IClassType {
   @SerId(1)
   private int kS;
   @SerId(2)
   private auq wS;
   @SerId(3)
   private List UT = new CopyOnWriteArrayList();
   @SerId(4)
   private List ld = new CopyOnWriteArrayList();
   @SerId(5)
   private List gp = new CopyOnWriteArrayList();
   @SerId(6)
   private int oT;
   @SerTransient
   public boolean pC;

   ayi(ayy var1, String var2, String var3, int var4, int var5) {
      super(var1, var2, var3, Math.max(1, var4), var5);
      this.NS();
   }

   @Override
   public void onNativeItemEvent(NativeItemEvent var1) {
      super.onNativeItemEvent(var1);
   }

   @Override
   protected void A() {
      super.A();

      for (ayz var2 : this.UT) {
         var2.A();
         var2.kS().xC();
      }

      for (ayi var3 : new ArrayList(this.gp)) {
         var3.xC();
      }

      for (ayi var7 : this.ld) {
         var7.A(this);
      }
   }

   public void pC(auq var1) {
      if (this.wS != null) {
         throw new IllegalStateException();
      } else {
         this.wS = var1;
      }
   }

   private void pC(ayi var1) {
      this.gp().verifyLocked();
      this.gp.add(var1);
      if (!this.Ab()) {
         this.pC(true);
      }
   }

   private void A(ayi var1) {
      this.gp().verifyLocked();
      this.gp.remove(var1);
      if (this.gp.isEmpty() && this.Ab()) {
         this.pC(false);
      }
   }

   public void pC(IVirtualTableDefinition var1) {
      try (ACLock var2 = this.gp().a()) {
         if (this.kS != 0 || this.getFieldsCount() > 0 || !this.UT.isEmpty()) {
            throw new IllegalStateException();
         }

         if (var1 == null) {
            throw new IllegalArgumentException();
         }

         ayz var3 = (ayz)var1;
         ayt var4 = this.kS().pC(this.kS().UT("void"), 2);
         this.pC("vtable", var4, 0);
         this.UT.add(var3);
         this.oT = 1;
         var3.wS = this;
      }
   }

   public void pC(Collection var1, Collection var2) {
      Collection var3 = var1;
      Collection var4 = var2;

      try (ACLock var5 = this.gp().a()) {
         if (var4 == null) {
            this.ld.addAll(var3);
         } else {
            label79: {
               if (this.kS == 0 && this.getFieldsCount() <= 0 && this.UT.isEmpty()) {
                  if (var3 != null && var4 != null) {
                     int var6 = 0;

                     for (ayi var8 : var3) {
                        this.ld.add(var8);
                        this.pC("super" + (var6 == 0 ? "" : var6), var8, -1);
                        var6++;
                     }

                     this.UT.addAll(var4);
                     this.oT = var6;
                     Iterator var12 = var4.iterator();

                     while (true) {
                        if (!var12.hasNext()) {
                           break label79;
                        }

                        ayz var14 = (ayz)var12.next();
                        var14.wS = this;
                     }
                  }

                  throw new IllegalArgumentException();
               }

               throw new IllegalStateException();
            }
         }

         for (ayi var13 : var3) {
            var13.pC(this);
         }
      }
   }

   public auq E() {
      return this.wS;
   }

   public boolean sY() {
      return this.kS == 1;
   }

   public void ys() {
      if (this.kS != 0) {
         throw new IllegalStateException();
      } else {
         this.kS = 1;

         for (ayz var2 : this.UT) {
            var2.pC();
         }

         this.WR();
      }
   }

   @Override
   public List getVirtualTables() {
      return this.UT;
   }

   public boolean pC(int var1, int var2) {
      if (var1 >= 0 && var1 < this.UT.size()) {
         ayz var3 = (ayz)this.UT.get(var1);
         if (var2 >= 0 && var2 < var3.size()) {
            return true;
         }
      }

      return false;
   }

   public List ld() {
      return this.ld;
   }

   public List UO() {
      return this.gp;
   }

   boolean pC(ayz var1, int var2, String var3) {
      this.gp().verifyLocked();
      int var4 = this.UT.indexOf(var1);
      return var4 < 0 ? false : this.renameVirtualMethod(var4, var2, var3);
   }

   @Override
   public boolean renameVirtualMethod(int var1, int var2, String var3) {
      boolean var6;
      try (ACLock var4 = this.gp().a()) {
         List var5 = pC(this, var1);
         if (var5 == null) {
            return false;
         }

         pC(this, var1, var2, var3);
         pC(this, var5, var2, var3);
         var6 = true;
      }

      return var6;
   }

   private static void pC(ayi var0, int var1, int var2, String var3) {
      var0.pC = true;

      try {
         if (var1 < 0 || var1 >= var0.UT.size()) {
            return;
         }

         ayz var4 = (ayz)var0.UT.get(var1);
         if (var2 >= var4.size()) {
            return;
         }

         var4.kS.pC(var2, var3);
         if (var0.wS != null) {
            var0.wS.pC(var1, var2, var3);
         }

         List var5 = kS(var0);
         if (var1 >= var5.size()) {
            return;
         }

         List var6 = (List)var5.get(var1);
         if (var6.size() != 1) {
            Assert.a(var6.get(0) == var0);
            ayi var7 = (ayi)var6.get(1);
            var6.remove(0);
            int var8 = pC(var7, var6);
            pC(var7, var8, var2, var3);
            return;
         }
      } finally {
         var0.pC = false;
      }
   }

   private static void pC(ayi var0, List var1, int var2, String var3) {
      for (ayi var5 : var0.gp) {
         var5.pC = true;

         try {
            ArrayList var6 = new ArrayList(var1);
            var6.add(0, var5);
            int var7 = pC(var5, var6);
            if (var7 >= 0) {
               ((ayz)var5.UT.get(var7)).kS.pC(var2, var3);
               if (var5.pC(var7, var2)) {
                  if (var5.wS != null) {
                     var5.wS.pC(var7, var2, var3);
                  }

                  pC(var5, var6, var2, var3);
               }
            }
         } finally {
            var5.pC = false;
         }
      }
   }

   @Override
   public boolean collectVirtualMethodOverrides(int var1, int var2, Collection var3, Collection var4) {
      if (var3 != null) {
         pC(this, var1, var2, var3);
      }

      if (var4 != null) {
         List var5 = pC(this, var1);
         pC(this, var5, var2, var4);
      }

      return true;
   }

   private static void pC(ayi var0, int var1, int var2, Collection var3) {
      if (var1 >= 0 && var1 < var0.UT.size()) {
         ayz var4 = (ayz)var0.UT.get(var1);
         if (var2 < var4.size()) {
            var3.add(new VirtualMethodCoordinates(var0, var1, var2));
            List var5 = kS(var0);
            if (var1 < var5.size()) {
               List var6 = (List)var5.get(var1);
               if (var6.size() == 1) {
                  return;
               }

               Assert.a(var6.get(0) == var0);
               ayi var7 = (ayi)var6.get(1);
               var6.remove(0);
               var1 = pC(var7, var6);
               pC(var7, var1, var2, var3);
            }
         }
      }
   }

   private static void pC(ayi var0, List var1, int var2, Collection var3) {
      for (ayi var5 : var0.gp) {
         ArrayList var6 = new ArrayList(var1);
         var6.add(0, var5);
         int var7 = pC(var5, var6);
         if (var7 >= 0 && var5.pC(var7, var2)) {
            var3.add(new VirtualMethodCoordinates(var5, var7, var2));
            pC(var5, var6, var2, var3);
         }
      }
   }

   private static List kS(ayi var0) {
      return new ClassVtablePaths(var0, new ayj()).determineFull();
   }

   private static List pC(ayi var0, int var1) {
      List var2 = kS(var0);
      return var1 >= 0 && var1 < var2.size() ? (List)var2.get(var1) : null;
   }

   private static int pC(ayi var0, List var1) {
      int var2 = 0;

      for (List var4 : kS(var0)) {
         if (pC(var4, var1)) {
            return var2;
         }

         var2++;
      }

      return -1;
   }

   private static boolean pC(List var0, List var1) {
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
   public boolean pC(ayu var1, String var2) {
      int var8;
      try (ACLock var3 = this.gp().a()) {
         if (this.sY()) {
            var8 = this.getFields().indexOf(var1);
            if (var8 < this.oT) {
               return false;
            }
         }

         var8 = super.pC(var1, var2);
      }

      return (boolean)var8;
   }

   @Override
   public ayu pC(String var1, INativeType var2, int var3, int var4, int var5, int var6, boolean var7) {
      ayu var13;
      try (ACLock var8 = this.gp().a()) {
         if (this.sY()) {
            int var9 = this.pC(this.oT).getOffset();
            if (var3 >= 0 && var3 < var9) {
               return null;
            }
         }

         var13 = super.pC(var1, var2, var3, var4, var5, var6, var7);
      }

      return var13;
   }

   @Override
   public boolean pC(IStructureTypeField var1) {
      int var7;
      try (ACLock var2 = this.gp().a()) {
         if (this.sY()) {
            var7 = this.getIndexOfField(var1);
            if (var7 < this.oT) {
               return false;
            }
         }

         var7 = super.pC(var1);
      }

      return (boolean)var7;
   }

   @Override
   public List getInstanceFields() {
      List var1 = this.getFields();
      return var1.subList(this.oT, var1.size());
   }

   @Override
   public auq wS() {
      return this.wS;
   }

   @Override
   public String toString() {
      return Strings.ff("ClassType{%d}(%s,%d)", this.ld.size(), this.getSignature(false), this.getSize());
   }
}
