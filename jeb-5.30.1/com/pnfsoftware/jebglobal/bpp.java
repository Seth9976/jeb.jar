package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.units.code.java.AbstractJBlockOptimizer;
import com.pnfsoftware.jeb.core.units.code.java.IJavaBlock;
import com.pnfsoftware.jeb.core.units.code.java.IJavaCall;
import com.pnfsoftware.jeb.core.units.code.java.IJavaCompound;
import com.pnfsoftware.jeb.core.units.code.java.IJavaElement;
import com.pnfsoftware.jeb.core.units.code.java.IJavaExpression;
import com.pnfsoftware.jeb.core.units.code.java.IJavaNewArray;
import com.pnfsoftware.jeb.core.units.code.java.IJavaStatement;
import com.pnfsoftware.jeb.core.units.code.java.IJavaStaticField;
import com.pnfsoftware.jeb.core.units.code.java.IJavaType;
import com.pnfsoftware.jeb.util.base.JavaUtil;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

public class bpp extends AbstractJBlockOptimizer {
   @Override
   public int optimizeBlock(IJavaBlock var1, IJavaElement var2) {
      int var3 = 0;

      for (int var4 = 0; var4 < var1.size(); var4++) {
         IJavaStatement var5 = var1.get(var4);
         if (!(var5 instanceof IJavaCompound)) {
            var3 += this.q(var1, var4);
         }
      }

      return var3;
   }

   private int q(IJavaBlock var1, int var2) {
      IJavaStatement var3 = var1.get(var2);
      ArrayList var4 = new ArrayList();
      this.q(var1, var3, new HashSet(), var4);
      int var5 = 0;

      for (bpp.eo var7 : var4) {
         IJavaCall var8 = (IJavaCall)var7.RF;
         List var9 = var8.getArguments();
         if (var9.size() == 2 && var9.get(0) instanceof IJavaStaticField && var9.get(1) instanceof IJavaNewArray) {
            IJavaType var10 = null;
            int var11 = -1;
            List var12 = null;
            IJavaExpression var13 = (IJavaExpression)var9.get(0);
            IJavaType var14 = ((IJavaStaticField)var13).getClassType();
            String var15 = ((IJavaStaticField)var13).getFieldName();
            if (var15.equals("TYPE")) {
               String var16 = JavaUtil.wrapperToPrimitive(var14.getName());
               var10 = this.tf.parseType(JavaUtil.primitiveToLetter(var16));
               var11 = 0;
            } else if (var15.equals("class")) {
               if (var14.isArray()) {
                  var10 = var14.getArrayElementType();
                  var11 = var14.getDimensions();
               } else {
                  var10 = var14;
                  var11 = 0;
               }
            }

            IJavaExpression var20 = (IJavaExpression)var9.get(1);
            if (((IJavaNewArray)var20).getType().getSignature().equals("[I")) {
               var12 = ((IJavaNewArray)var20).getInitialValues();
            }

            if (var10 != null && var11 >= 0 && var12 != null) {
               int var17 = var12.size() + var11;
               IJavaType var18 = this.tf.createArrayType(var10, var17);
               IJavaNewArray var19 = this.jctx.createNewArray(var18, true, var12);
               if (var7.q.replaceSubElement(var8, var19)) {
                  var5++;
               }
            }
         }
      }

      return var5;
   }

   private void q(IJavaElement var1, IJavaElement var2, HashSet var3, List var4) {
      if (!var3.contains(var2)) {
         var3.add(var2);
         if (var2 instanceof IJavaCall && this.q((IJavaCall)var2)) {
            var4.add(new bpp.eo(var1, var2));
         } else {
            for (IJavaElement var6 : var2.getSubElements()) {
               this.q(var2, var6, var3, var4);
            }
         }
      }
   }

   private boolean q(IJavaCall var1) {
      String var2 = var1.getMethodClass();
      String var3 = var1.getMethodName();
      return var2.equals("Ljava/lang/reflect/Array;") && var3.equals("newInstance");
   }

   static class eo {
      public IJavaElement q;
      public IJavaElement RF;

      public eo(IJavaElement var1, IJavaElement var2) {
         this.q = var1;
         this.RF = var2;
      }
   }
}
