package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.client.S;
import com.pnfsoftware.jeb.core.units.code.java.AbstractJOptimizer;
import com.pnfsoftware.jeb.core.units.code.java.IJavaAssignment;
import com.pnfsoftware.jeb.core.units.code.java.IJavaBlock;
import com.pnfsoftware.jeb.core.units.code.java.IJavaCall;
import com.pnfsoftware.jeb.core.units.code.java.IJavaConstant;
import com.pnfsoftware.jeb.core.units.code.java.IJavaDefinition;
import com.pnfsoftware.jeb.core.units.code.java.IJavaExpression;
import com.pnfsoftware.jeb.core.units.code.java.IJavaIdentifier;
import com.pnfsoftware.jeb.core.units.code.java.IJavaInstanceField;
import com.pnfsoftware.jeb.core.units.code.java.IJavaMethod;
import com.pnfsoftware.jeb.core.units.code.java.IJavaStatement;
import com.pnfsoftware.jeb.core.units.code.java.JOptimizerType;
import com.pnfsoftware.jeb.core.units.code.java.JavaReconAnon;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class bnf extends AbstractJOptimizer {
   public bnf() {
      super(JOptimizerType.UNSAFE);
   }

   @Override
   public int perform() {
      if (this.c == null || !this.c.isAnonymous()) {
         return 0;
      } else if (this.c.getReconAnon() != null) {
         return 0;
      } else {
         IJavaMethod var1 = null;

         for (IJavaMethod var3 : this.c.getMethods()) {
            if (var3.getName().equals("<clinit>")) {
               return -1;
            }

            if (var3.getName().equals("<init>")) {
               if (var1 != null) {
                  return -1;
               }

               var1 = var3;
            }
         }

         if (var1 == null) {
            return -1;
         } else {
            int var16 = var1.getParameterCount();
            if (var16 == 0) {
               return -1;
            } else {
               IJavaCall var17 = null;
               ArrayList var4 = new ArrayList();
               HashMap var5 = new HashMap();
               ArrayList var6 = new ArrayList();

               for (IJavaDefinition var8 : var1.getParameters()) {
                  var6.add(var8.getIdentifier());
               }

               int var18 = var1.getIndexOfFirstVisibleParameter();
               IJavaBlock var19 = var1.getBody();
               int var9 = -1;

               for (int var10 = 0; var10 < var19.size(); var10++) {
                  IJavaStatement var11 = var19.get(var10);
                  if (pC(var11, var1)) {
                     var9 = var10;
                     var17 = (IJavaCall)var11;
                     break;
                  }

                  if (var11 instanceof IJavaAssignment
                     && ((IJavaAssignment)var11).getLeft() instanceof IJavaInstanceField
                     && ((IJavaAssignment)var11).getRight() instanceof IJavaIdentifier) {
                     IJavaInstanceField var12 = (IJavaInstanceField)((IJavaAssignment)var11).getLeft();
                     IJavaIdentifier var13 = (IJavaIdentifier)((IJavaAssignment)var11).getRight();
                     int var14 = var6.indexOf(var13);
                     if (var14 < 0) {
                        return -1;
                     }

                     if (var14 >= var18) {
                        var5.put(var14 - var18, var12);
                     }
                  }
               }

               if (var17 != null && var9 >= 0) {
                  int var20 = var17.getMethod().getIndexOfFirstVisibleParameter();
                  List var21 = var17.getArguments();

                  for (IJavaExpression var24 : var21.subList(var20, var21.size())) {
                     int var26 = var6.indexOf(var24);
                     if (var26 < 0 && (!this.c.isEnumeration() || !(var24 instanceof IJavaConstant))) {
                        return -1;
                     }

                     if (var26 >= var18) {
                        var4.add(var26 - var18);
                     }
                  }

                  int var23 = var9 + 1;

                  while (var23-- > 0) {
                     var19.remove(0);
                  }

                  JavaReconAnon var25 = new JavaReconAnon(var4, var5);
                  this.c.setReconAnon(var25);

                  for (IJavaInstanceField var15 : var5.values()) {
                     var15.getField().markOptionalRendering();
                     com.pnfsoftware.jeb.corei.parsers.dexdec.rQ.pC(
                        this.decomp, this.dex.getField(var15.getFieldSignature()), S.L("Synthetic field for anonymous class")
                     );
                  }

                  return 1;
               } else {
                  return -1;
               }
            }
         }
      }
   }

   public static boolean pC(IJavaStatement var0, IJavaMethod var1) {
      return var0 instanceof IJavaCall var2 && var2.getMethod().getClassType() != var1.getClassType() && var2.getMethodName().equals("<init>");
   }
}
