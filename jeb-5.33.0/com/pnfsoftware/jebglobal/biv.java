package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.units.code.java.IJavaBlock;
import com.pnfsoftware.jeb.core.units.code.java.IJavaCatchBlock;
import com.pnfsoftware.jeb.core.units.code.java.IJavaDefinition;
import com.pnfsoftware.jeb.core.units.code.java.IJavaIdentifier;
import com.pnfsoftware.jeb.core.units.code.java.IJavaType;
import com.pnfsoftware.jeb.util.serialization.annotations.Ser;
import com.pnfsoftware.jeb.util.serialization.annotations.SerId;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Ser
public class biv implements IJavaCatchBlock {
   @SerId(1)
   IJavaType pC;
   @SerId(2)
   IJavaIdentifier A;
   @SerId(3)
   IJavaDefinition kS;
   @SerId(4)
   IJavaBlock wS;
   @SerId(5)
   List UT;

   public biv(IJavaType var1, List var2, IJavaIdentifier var3, IJavaDefinition var4, IJavaBlock var5) {
      if (var1 == null) {
         throw new IllegalArgumentException("Null caught type");
      } else {
         this.pC = var1;
         this.UT = var2;
         this.A = var3;
         this.kS = var4;
         this.wS = var5;
      }
   }

   @Override
   public IJavaType getType() {
      return this.pC;
   }

   @Override
   public void setType(IJavaType var1) {
      if (var1 == null) {
         throw new IllegalArgumentException();
      } else {
         this.pC = var1;
      }
   }

   @Override
   public List getCaughtTypes() {
      ArrayList var1 = new ArrayList();
      var1.add(this.pC);
      if (this.UT != null) {
         var1.addAll(this.UT);
      }

      return var1;
   }

   @Override
   public List getAdditionalCaughtTypes() {
      return this.UT;
   }

   @Override
   public void addTypes(Collection var1) {
      for (IJavaType var3 : var1) {
         this.addType(var3);
      }
   }

   @Override
   public void addType(IJavaType var1) {
      if (var1 == null) {
         throw new IllegalArgumentException("Null caught type");
      } else if (!this.canCatch(var1.getName())) {
         if (this.UT == null) {
            this.UT = new ArrayList();
         }

         this.UT.add(var1);
      }
   }

   @Override
   public boolean canCatch(String var1) {
      for (IJavaType var3 : this.getCaughtTypes()) {
         if (var1.equals(var3.getName())) {
            return true;
         }
      }

      return false;
   }

   @Override
   public void setIdentifier(IJavaIdentifier var1) {
      this.A = var1;
   }

   @Override
   public IJavaIdentifier getIdentifier() {
      return this.A;
   }

   @Override
   public void setDefifinition(IJavaDefinition var1) {
      this.kS = var1;
   }

   @Override
   public IJavaDefinition getDefinition() {
      return this.kS;
   }

   @Override
   public void setBlock(IJavaBlock var1) {
      this.wS = var1;
   }

   @Override
   public IJavaBlock getBlock() {
      return this.wS;
   }
}
