package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.units.code.android.IDexUnit;
import com.pnfsoftware.jeb.core.units.code.android.dex.IDexClass;
import com.pnfsoftware.jeb.core.units.code.android.dex.IDexField;
import com.pnfsoftware.jeb.core.units.code.android.dex.IDexMethod;
import com.pnfsoftware.jeb.util.serialization.annotations.Ser;
import com.pnfsoftware.jeb.util.serialization.annotations.SerId;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Map.Entry;

@Ser
public class bku {
   @SerId(1)
   private com.pnfsoftware.jeb.corei.parsers.dex.bK q;
   @SerId(2)
   private HashMap RF;

   public bku(IDexUnit var1) {
      this.q = (com.pnfsoftware.jeb.corei.parsers.dex.bK)var1;
      this.RF = this.q();
   }

   public bla q(int var1) {
      return (bla)this.RF.get(var1);
   }

   public boolean q(int var1, int var2) {
      ArrayDeque var3 = new ArrayDeque();
      var3.add(var1);
      HashSet var4 = new HashSet();

      while (!var3.isEmpty()) {
         int var5 = (Integer)var3.remove();
         bla var6 = (bla)this.RF.get(var5);
         if (var6 != null) {
            for (int var9 : var6.oW()) {
               if (var2 == var9) {
                  return true;
               }

               if (var4.add(var9)) {
                  var3.add(var9);
               }
            }
         }
      }

      return false;
   }

   private HashMap q() {
      HashMap var1 = new HashMap();
      int var2 = this.q.io().oW("Ljava/lang/Object;");
      HashSet var3 = new HashSet();
      ArrayList var4 = new ArrayList(this.q.getClasses());
      ArrayList var5 = new ArrayList(this.q.getMethods());
      ArrayList var6 = new ArrayList(this.q.getFields());

      for (IDexClass var8 : var4) {
         bla var9 = new bla(var8.getClassTypeIndex());
         var9.RF = var8.getSuperTypeIndex();
         if (var9.RF < 0) {
            var9.RF = var2;
         }

         if (var9.RF >= 0) {
            var3.add(var9.RF);
         }

         for (int var13 : var8.getInterfaceTypeIndexes()) {
            var9.xK.add(var13);
            var3.add(var13);
         }

         var1.put(var9.q, var9);
      }

      for (int var17 : var3) {
         if (!var1.containsKey(var17)) {
            var1.put(var17, new bla(var17));
         }
      }

      for (Entry var18 : var1.entrySet()) {
         int var21 = (Integer)var18.getKey();
         bla var24 = (bla)var18.getValue();
         bla var27 = (bla)var1.get(var24.RF);
         if (var27 != null && var27 != var24) {
            var27.Dw.add(var21);
         }

         for (int var33 : var24.xK) {
            var27 = (bla)var1.get(var33);
            if (var27 != null && var27 != var24) {
               var27.Dw.add(var21);
            }
         }
      }

      int var16 = 0;

      for (IDexMethod var22 : var5) {
         int var25 = var22.getClassType().getIndex();
         bla var29 = (bla)var1.get(var25);
         if (var29 != null) {
            var29.Uv.add(var16);
         }

         var16++;
      }

      int var20 = 0;

      for (IDexField var26 : var6) {
         int var30 = var26.getClassType().getIndex();
         bla var32 = (bla)var1.get(var30);
         if (var32 != null) {
            var32.oW.add(var20);
         }

         var20++;
      }

      return var1;
   }

   private Map RF() {
      HashMap var1 = new HashMap();
      int var2 = this.q.io().oW("Ljava/lang/Object;");
      HashSet var3 = new HashSet();
      ArrayList var4 = new ArrayList(this.q.getClasses());
      ArrayList var5 = new ArrayList(this.q.getMethods());
      ArrayList var6 = new ArrayList(this.q.getFields());

      for (IDexClass var8 : var4) {
         bla var9 = new bla(var8.getClassTypeIndex());
         var9.RF = var8.getSuperTypeIndex();
         if (var9.RF < 0) {
            var9.RF = var2;
         }

         if (var9.RF >= 0) {
            var3.add(var9.RF);
         }

         for (int var13 : var8.getInterfaceTypeIndexes()) {
            var9.xK.add(var13);
            var3.add(var13);
         }

         var1.put(var9.q, var9);
      }

      for (int var18 : var3) {
         if (!var1.containsKey(var18)) {
            var1.put(var18, new bla(var18));
         }
      }

      for (int var19 : var1.keySet()) {
         bla var22 = (bla)var1.get(var19);

         for (int var28 : var1.keySet()) {
            if (var28 != var19) {
               bla var31 = (bla)var1.get(var28);
               if (var31.RF == var19) {
                  var22.Dw.add(var28);
               }

               for (int var14 : var31.xK) {
                  if (var14 == var19) {
                     var22.Dw.add(var28);
                  }
               }
            }
         }
      }

      int var17 = 0;

      for (IDexMethod var23 : var5) {
         int var26 = var23.getClassType().getIndex();
         bla var29 = (bla)var1.get(var26);
         if (var29 != null) {
            var29.Uv.add(var17);
         }

         var17++;
      }

      int var21 = 0;

      for (IDexField var27 : var6) {
         int var30 = var27.getClassType().getIndex();
         bla var32 = (bla)var1.get(var30);
         if (var32 != null) {
            var32.oW.add(var21);
         }

         var21++;
      }

      return var1;
   }

   private void q(Map var1, Map var2) {
      if (!var1.keySet().equals(var2.keySet())) {
         throw new RuntimeException();
      } else {
         for (int var5 : var1.keySet()) {
            if (!((bla)var1.get(var5)).q((bla)var2.get(var5))) {
               throw new RuntimeException();
            }
         }
      }
   }
}
