package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.units.code.android.ir.IDTypeInfoProvider;
import com.pnfsoftware.jeb.core.units.code.java.AbstractJOptimizer;
import com.pnfsoftware.jeb.core.units.code.java.IJavaAssignment;
import com.pnfsoftware.jeb.core.units.code.java.IJavaCall;
import com.pnfsoftware.jeb.core.units.code.java.IJavaClass;
import com.pnfsoftware.jeb.core.units.code.java.IJavaConstant;
import com.pnfsoftware.jeb.core.units.code.java.IJavaDefinition;
import com.pnfsoftware.jeb.core.units.code.java.IJavaElement;
import com.pnfsoftware.jeb.core.units.code.java.IJavaExpression;
import com.pnfsoftware.jeb.core.units.code.java.IJavaIdentifier;
import com.pnfsoftware.jeb.core.units.code.java.IJavaLeftExpression;
import com.pnfsoftware.jeb.core.units.code.java.IJavaMethod;
import com.pnfsoftware.jeb.core.units.code.java.IJavaReturn;
import com.pnfsoftware.jeb.core.units.code.java.IJavaStatement;
import com.pnfsoftware.jeb.core.units.code.java.IJavaType;
import com.pnfsoftware.jeb.core.units.code.java.JOptimizerType;
import com.pnfsoftware.jeb.core.units.code.java.JUtil;
import com.pnfsoftware.jeb.util.base.Assert;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class bni extends AbstractJOptimizer {
   int pC;
   private int A;

   public bni() {
      super(JOptimizerType.UNSAFE);
   }

   @Override
   public int perform() {
      if (this.c == null) {
         return 0;
      } else {
         this.pC = 0;

         for (IJavaMethod var2 : this.c.getMethods()) {
            var2.visitDepthPost(new bnj(this));
         }

         return this.pC;
      }
   }

   String pC(IJavaType var1) {
      if (this.A++ > 10000) {
         return null;
      } else if (this.decomp == null) {
         return null;
      } else {
         IDTypeInfoProvider var2 = this.decomp.getIntermediateContext().getTypeInfoProvider();
         return var2.isFunctionalType(var1.getSignature());
      }
   }

   IJavaMethod pC(IJavaClass var1, String var2) {
      for (IJavaMethod var4 : var1.getMethods()) {
         if (var4.getSignature().endsWith("->" + var2)) {
            return var4;
         }
      }

      return null;
   }

   IJavaCall pC(IJavaStatement var1) {
      if (var1 instanceof IJavaCall) {
         return (IJavaCall)var1;
      } else {
         return var1 instanceof IJavaReturn && ((IJavaReturn)var1).getExpression() instanceof IJavaCall ? (IJavaCall)((IJavaReturn)var1).getExpression() : null;
      }
   }

   boolean pC(IJavaMethod var1, List var2, Map var3) {
      for (IJavaStatement var5 : var1.getBody()) {
         if (var5 instanceof IJavaAssignment && ((IJavaAssignment)var5).isSimpleAssignment()) {
            IJavaLeftExpression var6 = ((IJavaAssignment)var5).getLeft();
            IJavaExpression var7 = ((IJavaAssignment)var5).getRight();
            Assert.a(1 + var2.size() == var1.getParameterCount());
            int var8 = 0;
            IJavaExpression var9 = var7.duplicate();

            for (IJavaDefinition var11 : var1.getParameters().subList(1, var1.getParameterCount())) {
               IJavaIdentifier var12 = var11.getIdentifier();
               if (var9 == var12) {
                  var9 = (IJavaExpression)var2.get(var8);
               } else {
                  var9.replaceSubElement(var12, (IJavaElement)var2.get(var8));
               }

               var8++;
            }

            var3.put(var6, var9);
         } else if (!(var5 instanceof IJavaReturn)) {
            return false;
         }
      }

      return true;
   }

   boolean pC(IJavaMethod var1, IJavaCall var2, Map var3, List var4, Map var5) {
      if (var2 == null) {
         for (int var14 = var1.getParameterCount() - var1.getVisibleParameterCount(); var14 < var1.getParameterCount(); var14++) {
            var4.add(var14);
         }

         return true;
      } else {
         for (IJavaIdentifier var8 : (List)var1.getVisibleParameters().stream().map(var0 -> var0.getIdentifier()).collect(Collectors.toList())) {
            int var9 = 0;

            for (IJavaExpression var11 : var2.getArguments()) {
               if (this.pC(var8, var11)) {
                  break;
               }

               var9++;
            }

            var4.add(var9 >= var2.getArgumentCount() ? -1 : var9);
         }

         List var15 = var2.getArguments();
         int var16 = 0;

         for (IJavaExpression var18 : var15) {
            if (!var4.contains(var16)) {
               if (!(var18 instanceof IJavaConstant)) {
                  var18 = var18.duplicate();

                  for (IJavaExpression var12 : var3.keySet()) {
                     IJavaExpression var13 = (IJavaExpression)var3.get(var12);
                     if (var18.equals(var12)) {
                        var18 = var13;
                     } else {
                        var18.replaceSubElement(var12, var13);
                     }
                  }
               }

               IJavaIdentifier var20 = ((IJavaDefinition)var2.getMethod().getParameters().get(var16)).getIdentifier();
               if (!var20.equals(var18)) {
                  var5.put(var20, var18);
               }
            }

            var16++;
         }

         return true;
      }
   }

   boolean pC(IJavaIdentifier var1, IJavaExpression var2) {
      return var1 == JUtil.getVarLike(var2);
   }
}
