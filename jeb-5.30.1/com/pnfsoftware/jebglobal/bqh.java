package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.units.code.java.AbstractJOptimizer;
import com.pnfsoftware.jeb.core.units.code.java.IJavaGoto;
import com.pnfsoftware.jeb.core.units.code.java.IJavaLabel;
import com.pnfsoftware.jeb.core.units.code.java.IJavaStatement;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.TreeSet;

public class bqh extends AbstractJOptimizer {
   @Override
   public int perform() {
      if (this.m == null) {
         return 0;
      } else {
         bsb var1 = new bsb(this.m);
         int var2 = 0;
         List var3 = var1.RF();

         for (int var4 = 0; var4 < var3.size(); var4++) {
            IJavaStatement var5 = (IJavaStatement)var3.get(var4);
            if (var5 instanceof IJavaLabel) {
               int var6 = var1.q(var4, true, false, 1, null);
               if (var6 > var4 + 1) {
                  IJavaStatement var7 = (IJavaStatement)var3.get(var6);
                  if (!(var7 instanceof bsq) || ((bsq)var7).za() == bsq.CU.q) {
                     var1.q(var4, var6);
                     var2++;
                  }
               }
            }
         }

         if (var2 > 0) {
            this.m.fromFlatList(var3);
            var1 = new bsb(this.m);
         }

         int var28 = 0;
         var3 = var1.RF();
         HashMap var29 = new HashMap();
         HashMap var30 = new HashMap();
         HashMap var31 = new HashMap();
         HashMap var8 = new HashMap();
         HashMap var9 = new HashMap();
         HashMap var10 = new HashMap();

         for (int var11 = 0; var11 < var3.size(); var11++) {
            IJavaStatement var12 = (IJavaStatement)var3.get(var11);
            if (var12 instanceof brm) {
               int var13 = var1.Uv(var11) + 1;
               if (!(var12 instanceof bsd)) {
                  if (var13 - 3 >= 0) {
                     var30.put(var11, var13 - 3);
                  }

                  if (var12 instanceof bso) {
                     if (var11 - 1 >= 0) {
                        var31.put(var11, var11 - 1);
                     }

                     if (((bso)var12).q().isLitteralTrue()) {
                        var8.put(var11, var11 + 2);
                     }
                  }
               }

               var9.put(var11, var13);
               var1.q(var13, true, false, 2, null);
               if (!var1.Dw().isEmpty()) {
                  ArrayList var14 = new ArrayList(var1.Dw());
                  var10.put(var11, var14);
               }
            } else if (var12 instanceof IJavaLabel) {
               var29.put((IJavaLabel)var12, var11);
            }
         }

         HashMap var32 = new HashMap();
         ArrayDeque var33 = new ArrayDeque();

         for (int var34 = 0; var34 < var3.size(); var34++) {
            IJavaStatement var36 = (IJavaStatement)var3.get(var34);
            if (var36 instanceof brm) {
               var33.push(var34);
            } else if (var36 instanceof brn) {
               var33.pop();
            }

            if (var36 instanceof IJavaGoto) {
               IJavaLabel var15 = ((IJavaGoto)var36).getLabel();
               Integer var16 = (Integer)var29.get(var15);
               if (var16 != null) {
                  int var17 = var16;
                  int var18 = 0;
                  int var19 = 0;
                  byte var20 = 0;
                  IJavaLabel var21 = null;
                  int var22 = -1;
                  Iterator var23 = var33.iterator();

                  while (var23.hasNext()) {
                     var22 = (Integer)var23.next();
                     Integer var24 = (Integer)var30.get(var22);
                     if (var24 != null && var24 == var17) {
                        var20 = 1;
                        break;
                     }

                     var24 = (Integer)var31.get(var22);
                     if (var24 != null && var24 == var17) {
                        var20 = 1;
                        break;
                     }

                     var24 = (Integer)var8.get(var22);
                     if (var24 != null && var24 == var17) {
                        var20 = 1;
                        break;
                     }

                     Integer var25 = (Integer)var9.get(var22);
                     if (var25 != null && var25 == var17) {
                        var20 = 2;
                        break;
                     }

                     List var26 = (List)var10.get(var22);
                     if (var26 != null && var26.contains(var17)) {
                        var20 = 2;
                        break;
                     }

                     var18++;
                     if (!(var3.get(var22) instanceof bsd)) {
                        var19++;
                     }
                  }

                  if (var20 > 0) {
                     if (var20 != 1 && var18 > 0 || var20 == 1 && var19 > 0) {
                        int var42 = var22 - 1;
                        if (var42 >= 0 && var3.get(var42) instanceof IJavaLabel) {
                           var21 = (IJavaLabel)var3.get(var42);
                        } else {
                           var21 = (IJavaLabel)var32.get(var22);
                           if (var21 == null) {
                              var21 = this.m.getLabelFactory().create();
                              var32.put(var22, var21);
                              Object[] var10000 = new Object[]{var21};
                           }
                        }
                     }

                     Object var43;
                     if (var20 == 1) {
                        var43 = this.jctx.createContinue(var21);
                     } else {
                        var43 = this.jctx.createBreak(var21);
                     }

                     var3.set(var34, var43);
                     var28++;
                  }
               }
            }
         }

         if (!var32.isEmpty()) {
            TreeSet var35 = new TreeSet(var32.keySet());

            for (Integer var38 : var35.descendingSet()) {
               IJavaLabel var39 = (IJavaLabel)var32.get(var38);
               var3.add(var38, var39);
            }
         }

         if (var28 > 0) {
            this.m.fromFlatList(var3);
         }

         return var2 + var28;
      }
   }
}
