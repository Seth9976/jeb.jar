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
import java.util.Map.Entry;

@Ser
public class bgy {
   @SerId(1)
   private com.pnfsoftware.jeb.corei.parsers.dex.vi pC;
   @SerId(2)
   private HashMap A;

   public bgy(IDexUnit var1) {
      this.pC = (com.pnfsoftware.jeb.corei.parsers.dex.vi)var1;
      this.A = this.pC();
   }

   public bhe pC(int var1) {
      return (bhe)this.A.get(var1);
   }

   public boolean pC(int var1, int var2) {
      ArrayDeque var3 = new ArrayDeque();
      var3.add(var1);
      HashSet var4 = new HashSet();

      while (!var3.isEmpty()) {
         int var5 = (Integer)var3.remove();
         bhe var6 = (bhe)this.A.get(var5);
         if (var6 != null) {
            for (int var9 : var6.UT()) {
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

   private HashMap pC() {
      HashMap var1 = new HashMap();
      int var2 = this.pC.ld().E("Ljava/lang/Object;");
      HashSet var3 = new HashSet();
      ArrayList var4 = new ArrayList(this.pC.getClasses());
      ArrayList var5 = new ArrayList(this.pC.getMethods());
      ArrayList var6 = new ArrayList(this.pC.getFields());

      for (IDexClass var8 : var4) {
         bhe var9 = new bhe(var8.getClassTypeIndex());
         var9.A = var8.getSuperTypeIndex();
         if (var9.A < 0) {
            var9.A = var2;
         }

         if (var9.A >= 0) {
            var3.add(var9.A);
         }

         for (int var13 : var8.getInterfaceTypeIndexes()) {
            var9.kS.add(var13);
            var3.add(var13);
         }

         var1.put(var9.pC, var9);
      }

      for (int var17 : var3) {
         if (!var1.containsKey(var17)) {
            var1.put(var17, new bhe(var17));
         }
      }

      for (Entry var18 : var1.entrySet()) {
         int var21 = (Integer)var18.getKey();
         bhe var24 = (bhe)var18.getValue();
         bhe var27 = (bhe)var1.get(var24.A);
         if (var27 != null && var27 != var24) {
            var27.wS.add(var21);
         }

         for (int var33 : var24.kS) {
            var27 = (bhe)var1.get(var33);
            if (var27 != null && var27 != var24) {
               var27.wS.add(var21);
            }
         }
      }

      int var16 = 0;

      for (IDexMethod var22 : var5) {
         int var25 = var22.getClassType().getIndex();
         bhe var29 = (bhe)var1.get(var25);
         if (var29 != null) {
            var29.UT.add(var16);
         }

         var16++;
      }

      int var20 = 0;

      for (IDexField var26 : var6) {
         int var30 = var26.getClassType().getIndex();
         bhe var32 = (bhe)var1.get(var30);
         if (var32 != null) {
            var32.E.add(var20);
         }

         var20++;
      }

      return var1;
   }
}
