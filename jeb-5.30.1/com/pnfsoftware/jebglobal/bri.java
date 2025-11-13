package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.units.code.android.JvmMethodSig;
import com.pnfsoftware.jeb.core.units.code.java.AbstractJOptimizer;
import com.pnfsoftware.jeb.core.units.code.java.IJavaAssignment;
import com.pnfsoftware.jeb.core.units.code.java.IJavaCall;
import com.pnfsoftware.jeb.core.units.code.java.IJavaConstant;
import com.pnfsoftware.jeb.core.units.code.java.IJavaDefinition;
import com.pnfsoftware.jeb.core.units.code.java.IJavaExpression;
import com.pnfsoftware.jeb.core.units.code.java.IJavaMethod;
import com.pnfsoftware.jeb.core.units.code.java.IJavaNew;
import com.pnfsoftware.jeb.core.units.code.java.IJavaNewArray;
import com.pnfsoftware.jeb.core.units.code.java.IJavaStatement;
import com.pnfsoftware.jeb.core.units.code.java.IJavaStaticField;
import com.pnfsoftware.jeb.core.units.code.java.IJavaType;
import com.pnfsoftware.jeb.core.units.code.java.JOptimizerType;
import com.pnfsoftware.jeb.core.units.code.java.JavaReconEnum;
import com.pnfsoftware.jeb.util.format.Strings;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.TreeMap;

public class bri extends AbstractJOptimizer {
   public bri() {
      super(JOptimizerType.UNSAFE);
   }

   @Override
   public int perform() {
      if (this.c != null && this.c.isEnumeration()) {
         if (this.c.getReconEnum() != null) {
            return 0;
         } else {
            IJavaMethod var1 = null;
            ArrayList var2 = new ArrayList();

            for (IJavaMethod var4 : this.c.getMethods()) {
               if (var4.getName().equals("<clinit>")) {
                  var1 = var4;
               } else if (var4.getName().equals("<init>")) {
                  var2.add(var4);
               }
            }

            if (var1 == null) {
               return 0;
            } else {
               JavaReconEnum var14 = new JavaReconEnum();
               int[] var15 = new int[2];
               if (!this.q(var1, var15, var14)) {
                  return 0;
               } else {
                  HashSet var5 = new HashSet();

                  for (JavaReconEnum.ECst var7 : var14.getEnumeratedConstants()) {
                     IJavaNew var8 = var7.getNewExpressionForSubEnum();
                     if (var8 != null) {
                        var5.add(var8.getType().getSignature());
                     }
                  }

                  for (String var19 : this.c.getMethodsSignatures()) {
                     String var22 = JvmMethodSig.nameAndParams(var19);
                     if (Strings.isContainedIn(var22, "valueOf(Ljava/lang/String;)", "values()")) {
                        com.pnfsoftware.jeb.corei.parsers.dexdec.tw.q(this.decomp, this.dex.getMethod(var19), "Built-in enum method");
                     }
                  }

                  for (IJavaMethod var20 : this.c.getMethods()) {
                     String var23 = JvmMethodSig.nameAndParams(var20.getSignature());
                     if (Strings.isContainedIn(var23, "valueOf(Ljava/lang/String;)", "values()")) {
                        var20.markOptionalRendering();
                     }
                  }

                  List var18 = var1.toFlatList();
                  int var21 = var15[0];
                  int var24 = var15[1] - var21;

                  while (var24-- > 0) {
                     var18.remove(var21);
                  }

                  var1.fromFlatList(var18);
                  if (var1.isEmpty()) {
                     var1.markOptionalRendering();
                  }

                  for (IJavaMethod var10 : var2) {
                     String var11 = var10.getSignature();
                     if (!var10.isEmpty()
                        && var10.getBody().get(0) instanceof IJavaCall var13
                        && var13.getMethod().getClassType() != var10.getClassType()
                        && var13.getMethodName().equals("<init>")) {
                        var10.getBody().remove(0);
                     }

                     var10.setIndexOfFirstVisibleParameter(3);
                     JvmMethodSig var25 = JvmMethodSig.parse(var11);
                     if (var5.contains(var25.partypes.get(var25.partypes.size() - 1))) {
                        com.pnfsoftware.jeb.corei.parsers.dexdec.tw.q(this.decomp, this.dex.getMethod(var11), "Constructor for custom enum");
                        var10.markOptionalRendering();
                     }
                  }

                  this.c.setReconEnum(var14);
                  return 1;
               }
            }
         }
      } else {
         return 0;
      }
   }

   boolean q(IJavaMethod var1, int[] var2, JavaReconEnum var3) {
      List var4 = var1.toFlatList();
      int var5 = -1;
      int var6 = 0;
      TreeMap var7 = new TreeMap();
      int var8 = 0;
      Iterator var9 = var4.iterator();

      while (true) {
         while (true) {
            if (var9.hasNext()) {
               IJavaStatement var10 = (IJavaStatement)var9.next();
               if (!(var10 instanceof IJavaAssignment var11)) {
                  if (var5 >= 0) {
                     return false;
                  }

                  var6++;
                  continue;
               }

               if (var11.getRight() instanceof IJavaNew var12) {
                  IJavaStaticField var24 = null;
                  Object var25;
                  if (var11.getLeft() instanceof IJavaStaticField) {
                     var24 = (IJavaStaticField)var11.getLeft();
                     var25 = var24;
                  } else {
                     if (!(var11.getLeft() instanceof IJavaDefinition var27)) {
                        if (var5 >= 0) {
                           return false;
                        }

                        var6++;
                        continue;
                     }

                     var8++;
                     var25 = var27.getIdentifier();
                  }

                  IJavaType var30 = var12.getType();
                  IJavaNew var31 = null;
                  if (var30 != this.c.getType()) {
                     var31 = var12;
                  }

                  List var19 = var12.getArguments();
                  if (var19.size() < 2) {
                     return false;
                  }

                  if (!(var19.get(0) instanceof IJavaConstant) || !((IJavaConstant)var19.get(0)).isString()) {
                     return false;
                  }

                  String var20 = ((IJavaConstant)var19.get(0)).getString();
                  if (!(var19.get(1) instanceof IJavaConstant) || ((IJavaConstant)var19.get(1)).getType() != this.tf.getInt()) {
                     return false;
                  }

                  int var21 = ((IJavaConstant)var19.get(1)).getInt();
                  var7.put(var21, var25);
                  var3.addEnumeratedConstant(new JavaReconEnum.ECst(var24, var20, var21, new ArrayList(var19.subList(2, var19.size())), var31));
                  if ("SPECIAL_TRIGGER_FORCE_FAIL_1234567890".equals(var20)) {
                     return false;
                  }

                  if (var5 < 0) {
                     var5 = var6;
                  }
                  break;
               }

               if (!(var11.getRight() instanceof IJavaNewArray var13)) {
                  if (var5 >= 0) {
                     return false;
                  }
                  break;
               }

               if (var13.hasInitialValues()) {
                  List var23 = var13.getInitialValues();
                  if (var23.size() != var7.size()) {
                     return false;
                  }

                  int var15 = 0;

                  for (IJavaExpression var17 : var13.getInitialValues()) {
                     IJavaExpression var18 = (IJavaExpression)var7.get(var15);
                     if (var18 == null || !var18.equals(var17)) {
                        return false;
                     }

                     var15++;
                  }

                  if (var11.getLeft() instanceof IJavaStaticField var26) {
                     var3.setValuesArray(var26);
                  }
               }
            }

            if (var8 > var7.size() / 2) {
               return false;
            }

            if (var5 < 0) {
               return false;
            }

            var2[0] = var5;
            var2[1] = var6 + 1;
            return true;
         }

         var6++;
      }
   }
}
