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
public class bms implements IJavaCatchBlock {
   @SerId(1)
   IJavaType q;
   @SerId(2)
   IJavaIdentifier RF;
   @SerId(3)
   IJavaDefinition xK;
   @SerId(4)
   IJavaBlock Dw;
   @SerId(5)
   List Uv;

   public bms(IJavaType var1, List var2, IJavaIdentifier var3, IJavaDefinition var4, IJavaBlock var5) {
      if (var1 == null) {
         throw new IllegalArgumentException("Null caught type");
      } else {
         this.q = var1;
         this.Uv = var2;
         this.RF = var3;
         this.xK = var4;
         this.Dw = var5;
      }
   }

   @Override
   public IJavaType getType() {
      return this.q;
   }

   @Override
   public void setType(IJavaType var1) {
      if (var1 == null) {
         throw new IllegalArgumentException();
      } else {
         this.q = var1;
      }
   }

   @Override
   public List getCaughtTypes() {
      ArrayList var1 = new ArrayList();
      var1.add(this.q);
      if (this.Uv != null) {
         var1.addAll(this.Uv);
      }

      return var1;
   }

   @Override
   public List getAdditionalCaughtTypes() {
      return this.Uv;
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
         if (this.Uv == null) {
            this.Uv = new ArrayList();
         }

         this.Uv.add(var1);
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
      this.RF = var1;
   }

   @Override
   public IJavaIdentifier getIdentifier() {
      return this.RF;
   }

   @Override
   public void setDefifinition(IJavaDefinition var1) {
      this.xK = var1;
   }

   @Override
   public IJavaDefinition getDefinition() {
      return this.xK;
   }

   @Override
   public void setBlock(IJavaBlock var1) {
      this.Dw = var1;
   }

   @Override
   public IJavaBlock getBlock() {
      return this.Dw;
   }
}
