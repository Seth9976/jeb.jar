package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.units.code.android.JvmMethodSig;
import com.pnfsoftware.jeb.core.units.code.java.AbstractJBlockOptimizer;
import com.pnfsoftware.jeb.core.units.code.java.IJavaBlock;
import com.pnfsoftware.jeb.core.units.code.java.IJavaCall;
import com.pnfsoftware.jeb.core.units.code.java.IJavaElement;
import com.pnfsoftware.jeb.core.units.code.java.IJavaExpression;
import com.pnfsoftware.jeb.core.units.code.java.IJavaNew;
import com.pnfsoftware.jeb.core.units.code.java.IJavaStatement;
import com.pnfsoftware.jeb.core.units.code.java.JavaOperatorType;
import com.pnfsoftware.jeb.util.collect.IdentityHashSet;
import com.pnfsoftware.jeb.util.format.Strings;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Set;

public class brd extends AbstractJBlockOptimizer {
   @Override
   public int optimizeBlock(IJavaBlock var1, IJavaElement var2) {
      int var3 = 0;
      IdentityHashSet var4 = new IdentityHashSet();

      for (int var5 = 0; var5 < var1.size(); var5++) {
         IJavaStatement var6 = var1.get(var5);
         var3 += this.q(var1, var6, var4);
      }

      return var3;
   }

   private int q(IJavaElement var1, IJavaElement var2, Set var3) {
      if (var3.contains(var2)) {
         return 0;
      } else {
         var3.add(var2);
         int var4 = 0;
         if (var2 instanceof IJavaCall && this.q(var1, (IJavaCall)var2)) {
            var4++;
         }

         for (IJavaElement var6 : var2.getSubElements()) {
            var4 += this.q(var2, var6, var3);
         }

         return var4;
      }
   }

   private boolean q(String var1, String var2) {
      JvmMethodSig var3 = JvmMethodSig.parse(var1);
      return var3.mname.equals(var2) && (var3.csig.equals("Ljava/lang/StringBuilder;") || var3.csig.equals("Ljava/lang/StringBuffer;"));
   }

   private boolean q(IJavaElement var1, IJavaCall var2) {
      if (!this.q(var2.getMethodSignature(), "toString")) {
         return false;
      } else {
         ArrayList var3 = new ArrayList();
         ArrayDeque var4 = new ArrayDeque();
         var5 = var2;

         while (true) {
            IJavaExpression var6 = (IJavaExpression)var5.getArguments().get(0);
            if (!(var6 instanceof IJavaCall var5)) {
               if (!(var6 instanceof IJavaNew var7)) {
                  return false;
               } else if (!Strings.isContainedIn(var7.getType().toString(), "java.lang.StringBuilder", "java.lang.StringBuffer")) {
                  return false;
               } else {
                  if (!var7.getArguments().isEmpty()) {
                     if (var7.getArguments().size() != 1) {
                        return false;
                     }

                     if (this.dex == null) {
                        return false;
                     }

                     String var8 = this.dex.getMethod(var7.getConstructor().getSignature()).getPrototype().getShorty();
                     if (!var8.equals("VL")) {
                        return false;
                     }

                     IJavaExpression var9 = (IJavaExpression)var7.getArguments().get(0);
                     var4.push(var9);
                  }

                  if (var3.isEmpty()) {
                     return false;
                  } else {
                     var6 = (IJavaExpression)var4.pop();

                     while (!var4.isEmpty()) {
                        var6 = this.jctx.createOperation(var6, this.of.getStandardOperator(JavaOperatorType.CONCAT), (IJavaExpression)var4.pop());
                     }

                     try {
                        return var1.replaceSubElement(var2, var6);
                     } catch (ClassCastException var10) {
                        return false;
                     }
                  }
               }
            }

            if (!this.q(var5.getMethodSignature(), "append")) {
               return false;
            }

            var3.add((IJavaExpression)var5.getArguments().get(1));
            var4.push((IJavaExpression)var5.getArguments().get(1));
         }
      }
   }
}
