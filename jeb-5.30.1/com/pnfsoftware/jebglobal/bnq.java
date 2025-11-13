package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.units.code.java.IJavaLabel;
import com.pnfsoftware.jeb.core.units.code.java.IJavaLabelFactory;
import com.pnfsoftware.jeb.util.base.Assert;
import com.pnfsoftware.jeb.util.serialization.annotations.Ser;
import com.pnfsoftware.jeb.util.serialization.annotations.SerId;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

@Ser
public class bnq implements IJavaLabelFactory {
   @SerId(1)
   private Map RF = new TreeMap();
   @SerId(2)
   private Map xK = new TreeMap();
   @SerId(3)
   int q;

   public void q() {
      this.RF.clear();
   }

   @Override
   public IJavaLabel create(int var1, String var2) {
      Object var3 = (IJavaLabel)this.RF.get(var1);
      if (var3 == null) {
         var3 = new bnp(var1, var2);
         this.RF.put(var1, var3);
      } else {
         Assert.a(((IJavaLabel)var3).getName().equals(var2), "Label at offset already exists using a different name!");
      }

      return (IJavaLabel)var3;
   }

   @Override
   public IJavaLabel create(int var1) {
      return this.create(var1, "label_" + var1);
   }

   @Override
   public List getLabels() {
      return new ArrayList(this.RF.values());
   }

   @Override
   public void recordTrampoline(int var1, int var2) {
      if (this.xK != null) {
         this.xK.put(var1, var2);
      }
   }

   @Override
   public boolean checkEquivalence(IJavaLabel var1, IJavaLabel var2) {
      if (var1 == null || var2 == null) {
         return false;
      } else if (var1 == var2) {
         return true;
      } else if (this.xK == null) {
         return false;
      } else {
         int var3 = this.q(var1.getOffset());
         int var4 = this.q(var2.getOffset());
         return var3 == var4;
      }
   }

   private int q(int var1) {
      while (true) {
         Integer var2 = (Integer)this.xK.get(var1);
         if (var2 == null) {
            return var1;
         }

         var1 = var2;
      }
   }

   @Override
   public IJavaLabel create() {
      this.q++;
      int var1 = -this.q;
      return this.create(var1, "alab" + this.q);
   }
}
