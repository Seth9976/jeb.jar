package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.units.code.java.AbstractJBlockOptimizer;
import com.pnfsoftware.jeb.core.units.code.java.IJavaArrayElt;
import com.pnfsoftware.jeb.core.units.code.java.IJavaAssignment;
import com.pnfsoftware.jeb.core.units.code.java.IJavaBlock;
import com.pnfsoftware.jeb.core.units.code.java.IJavaCall;
import com.pnfsoftware.jeb.core.units.code.java.IJavaCompound;
import com.pnfsoftware.jeb.core.units.code.java.IJavaConstant;
import com.pnfsoftware.jeb.core.units.code.java.IJavaDefinition;
import com.pnfsoftware.jeb.core.units.code.java.IJavaElement;
import com.pnfsoftware.jeb.core.units.code.java.IJavaExpression;
import com.pnfsoftware.jeb.core.units.code.java.IJavaIdentifier;
import com.pnfsoftware.jeb.core.units.code.java.IJavaLeftExpression;
import com.pnfsoftware.jeb.core.units.code.java.IJavaNewArray;
import com.pnfsoftware.jeb.core.units.code.java.IJavaStatement;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.TreeMap;

public class bmd extends AbstractJBlockOptimizer {
   @Override
   public int optimizeBlock(IJavaBlock var1, IJavaElement var2) {
      int var3 = 0;
      int var4 = 0;

      while (var4 < var1.size()) {
         IJavaStatement var5 = var1.get(var4);
         if (!(var5 instanceof IJavaCompound)) {
            int var6 = this.pC(var1, var4);
            if (var6 >= 0) {
               var3++;
               var4 = var6 + 1;
               continue;
            }
         }

         var4++;
      }

      return var3;
   }

   private int pC(IJavaBlock var1, int var2) {
      IJavaStatement var3 = var1.get(var2);
      ArrayList var4 = new ArrayList();
      this.pC(var1, var3, new HashSet(), var4);
      if (var4.isEmpty()) {
         return -1;
      } else {
         Object[] var10000 = new Object[]{var4.size()};
         ArrayList var5 = new ArrayList();

         for (IJavaCall var7 : var4) {
            List var8 = var7.getArguments();
            if (!var8.isEmpty()) {
               IJavaExpression var9 = (IJavaExpression)var8.get(var8.size() - 1);
               if (var9 instanceof IJavaNewArray) {
                  List var10 = ((IJavaNewArray)var9).getSizes();
                  if (var10 != null && var10.size() == 1 && var10.get(0) instanceof IJavaConstant) {
                     IJavaConstant var11 = (IJavaConstant)var10.get(0);
                     if (var11.isZero()) {
                        var7.removeArgument(var8.size() - 1);
                        var5.add(var7);
                     }
                  }
               }
            }
         }

         var4.removeAll(var5);
         if (var4.isEmpty()) {
            return var2;
         } else {
            var5 = new ArrayList();

            for (IJavaCall var23 : var4) {
               List var25 = var23.getArguments();
               if (!var25.isEmpty()) {
                  IJavaExpression var27 = (IJavaExpression)var25.get(var25.size() - 1);
                  if (var27 instanceof IJavaNewArray) {
                     List var29 = ((IJavaNewArray)var27).getInitialValues();
                     if (var29 != null) {
                        var23.removeArgument(var25.size() - 1);

                        for (IJavaExpression var12 : var29) {
                           var23.addArgument(var12);
                        }

                        if (var29.size() == 1 && var29.get(0) instanceof IJavaConstant && ((IJavaConstant)var29.get(0)).isNull()) {
                           var23.setArgument(
                              var23.getArgumentCount() - 1, this.jctx.createCastOperation(this.tf.getJavaLangObject(), (IJavaExpression)var29.get(0))
                           );
                        }

                        var5.add(var23);
                     }
                  }
               }
            }

            var4.removeAll(var5);
            if (var4.isEmpty()) {
               return var2;
            } else {
               IJavaExpression var22 = null;
               IJavaCall var24 = null;
               ArrayList var26 = null;
               TreeMap var28 = new TreeMap();
               int var30 = var2 - 1;

               while (true) {
                  label204: {
                     if (var30 >= 0) {
                        if (!(var1.get(var30) instanceof IJavaAssignment var34)) {
                           var10000 = new Object[0];
                           return -1;
                        }

                        if (!var34.isSimpleAssignment()) {
                           var10000 = new Object[0];
                           return -1;
                        }

                        IJavaLeftExpression var13 = var34.getLeft();
                        if (var13 instanceof IJavaArrayElt var37) {
                           IJavaExpression var38 = var37.getArray();
                           if (var22 == null) {
                              for (IJavaCall var41 : var4) {
                                 List var18 = var41.getArguments();
                                 if (!var18.isEmpty() && var18.get(var18.size() - 1) == var38) {
                                    var22 = var38;
                                    var24 = var41;
                                 }
                              }
                           }

                           if (var38 != var22) {
                              var10000 = new Object[0];
                              return -1;
                           }

                           IJavaExpression var40 = var37.getIndex();
                           if (!(var40 instanceof IJavaConstant) || ((IJavaConstant)var40).getType().isInt()) {
                              var10000 = new Object[0];
                              return -1;
                           }

                           int var42 = ((IJavaConstant)var40).getInt();
                           if (!var28.containsKey(var42)) {
                              var28.put(var42, var34.getRight());
                           }
                           break label204;
                        }

                        if (!(var13 instanceof IJavaDefinition) && !(var13 instanceof IJavaIdentifier)) {
                           break label204;
                        }

                        IJavaIdentifier var14;
                        if (var13 instanceof IJavaDefinition) {
                           var14 = ((IJavaDefinition)var13).getIdentifier();
                        } else {
                           var14 = (IJavaIdentifier)var13;
                        }

                        if (var14 != var22) {
                           var10000 = new Object[0];
                           return -1;
                        }

                        var26 = new ArrayList(var28.size());
                        int var15 = 0;

                        for (int var17 : var28.keySet()) {
                           if (var17 != var15) {
                              var10000 = new Object[0];
                              return -1;
                           }

                           var26.add((IJavaExpression)var28.get(var17));
                           var15++;
                        }
                     }

                     if (var26 != null && var22 != null && var24 != null) {
                        if (var26.size() == 1 && var26.get(0) instanceof IJavaConstant && ((IJavaConstant)var26.get(0)).isNull()) {
                           var10000 = new Object[0];
                           var26.set(0, this.jctx.createCastOperation(this.tf.getJavaLangObject(), (IJavaExpression)var26.get(0)));
                        }

                        List var33 = var24.getArguments();
                        var33.remove(var33.size() - 1);

                        for (int var35 = 0; var35 < var26.size(); var35++) {
                           var33.add((IJavaExpression)var26.get(var35));
                        }

                        int var36 = var26.size() + 1;

                        for (var2 -= var36; var36 > 0; var36--) {
                           var1.remove(var2);
                        }

                        var10000 = new Object[0];
                        return var2;
                     }

                     var10000 = new Object[]{var26, var22, var24};
                     return -1;
                  }

                  var30--;
               }
            }
         }
      }
   }

   private void pC(IJavaElement var1, IJavaElement var2, Set var3, List var4) {
      if (!var3.contains(var2)) {
         var3.add(var2);
         if (var2 instanceof IJavaCall && this.pC((IJavaCall)var2)) {
            var4.add((IJavaCall)var2);
         }

         for (IJavaElement var6 : var2.getSubElements()) {
            this.pC(var2, var6, var3, var4);
         }
      }
   }

   private boolean pC(IJavaCall var1) {
      String var2 = var1.getMethodClass();
      String var3 = var1.getMethodName();
      if (var2.equals("Ljava/lang/String;") || var2.equals("Ljava/util/Formatter;")) {
         return var3.equals("format");
      } else if (var2.equals("Ljava/lang/Class;")) {
         return var3.equals("getConstructor") || var3.equals("getDeclaredConstructor") || var3.equals("getMethod") || var3.equals("getDeclaredMethod");
      } else if (var2.equals("Ljava/lang/reflect/Method;")) {
         return var3.equals("invoke");
      } else {
         return var2.equals("Ljava/lang/reflect/Constructor;") ? var3.equals("newInstance") : false;
      }
   }
}
