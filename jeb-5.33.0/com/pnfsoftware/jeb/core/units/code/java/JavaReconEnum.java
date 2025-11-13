package com.pnfsoftware.jeb.core.units.code.java;

import com.pnfsoftware.jeb.util.serialization.annotations.Ser;
import com.pnfsoftware.jeb.util.serialization.annotations.SerCustomInitPostGraph;
import com.pnfsoftware.jeb.util.serialization.annotations.SerId;
import java.util.ArrayList;
import java.util.List;

@Ser(oldId = 65699)
public class JavaReconEnum {
   @SerId(1)
   List ecsts = new ArrayList();
   @SerId(value = 2, deprecated = true)
   private IJavaMethod staticInit;
   @SerId(value = 3, deprecated = true)
   private List constructors;
   @SerId(value = 4, deprecated = true)
   private List implicitMethods;
   @SerId(value = 5, deprecated = true)
   private List implicitMethodSigs;
   @SerId(6)
   private IJavaStaticField valuesArray;

   @SerCustomInitPostGraph
   private void init() {
      if (this.implicitMethods != null) {
         this.implicitMethodSigs = new ArrayList(this.implicitMethods.size());
         this.implicitMethods.forEach(var1 -> this.implicitMethodSigs.add(var1.getSignature()));
         this.implicitMethods = null;
         if (this.staticInit != null) {
            this.implicitMethodSigs.add(this.staticInit.getSignature());
         }
      }

      this.staticInit = null;
      this.constructors = null;
   }

   public JavaReconEnum.ECst byField(IJavaStaticField var1) {
      if (var1 == null) {
         return null;
      } else {
         for (JavaReconEnum.ECst var3 : this.ecsts) {
            if (var1.equals(var3.sfield)) {
               return var3;
            }
         }

         return null;
      }
   }

   public JavaReconEnum.ECst byOrdinal(int var1) {
      for (JavaReconEnum.ECst var3 : this.ecsts) {
         if (var1 == var3.ordinal) {
            return var3;
         }
      }

      return null;
   }

   public void addEnumeratedConstant(JavaReconEnum.ECst var1) {
      this.ecsts.add(var1);
   }

   public List getEnumeratedConstants() {
      return this.ecsts;
   }

   public void setValuesArray(IJavaStaticField var1) {
      this.valuesArray = var1;
   }

   public IJavaStaticField getValuesArray() {
      return this.valuesArray;
   }

   @Ser(oldId = 65698)
   public static class ECst {
      @SerId(1)
      IJavaStaticField sfield;
      @SerId(2)
      String name;
      @SerId(3)
      int ordinal;
      @SerId(4)
      List adds;
      @SerId(5)
      IJavaNew newExpressionForSubEnum;

      public ECst(IJavaStaticField var1, String var2, int var3, List var4, IJavaNew var5) {
         this.sfield = var1;
         this.name = var2;
         this.ordinal = var3;
         this.adds = var4;
         this.newExpressionForSubEnum = var5;
      }

      public IJavaStaticField getCompilerGeneratedStaticField() {
         return this.sfield;
      }

      public String getName() {
         return this.name;
      }

      public int getOrdinal() {
         return this.ordinal;
      }

      public List getArgumentList() {
         try {
            Boolean.valueOf(this.adds.isEmpty());
         } catch (Exception var1) {
            this.adds = new ArrayList();
         }

         return this.adds;
      }

      public IJavaNew getNewExpressionForSubEnum() {
         return this.newExpressionForSubEnum;
      }
   }
}
