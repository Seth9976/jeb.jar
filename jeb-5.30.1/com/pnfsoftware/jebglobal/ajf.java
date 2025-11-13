package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ast.ICGoto;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ast.ICLabel;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ast.ICStatement;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ast.opt.AbstractCOptimizer;
import com.pnfsoftware.jeb.util.logging.StructuredLogger;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.TreeSet;

public class ajf extends AbstractCOptimizer {
   private static final StructuredLogger q = aeg.q(ajf.class);

   @Override
   protected int perform() {
      ahv var1 = new ahv(this.m);
      int var2 = 0;
      List var3 = var1.q();

      for (int var4 = 0; var4 < var3.size(); var4++) {
         ICStatement var5 = (ICStatement)var3.get(var4);
         if (var5 instanceof ICLabel) {
            int var6 = var1.q(var4, true, false, 1, null);
            if (var6 > var4 + 1) {
               ICStatement var7 = (ICStatement)var3.get(var6);
               if (!(var7 instanceof aid) || ((aid)var7).Uv() == aid.CU.q) {
                  var1.q(var4, var6);
                  var2++;
               }
            }
         }
      }

      if (var2 > 0) {
         this.m.fromFlatList(var3);
         var1 = new ahv(this.m);
      }

      int var26 = 0;
      var3 = var1.q();
      HashMap var27 = new HashMap();
      HashMap var28 = new HashMap();
      HashMap var29 = new HashMap();
      HashMap var8 = new HashMap();
      HashMap var9 = new HashMap();

      for (int var10 = 0; var10 < var3.size(); var10++) {
         ICStatement var11 = (ICStatement)var3.get(var10);
         if (var11 instanceof aib || var11 instanceof ahn || var11 instanceof ahp || var11 instanceof ahx) {
            int var12 = var1.xK(var10) + 1;
            if (!(var11 instanceof ahx)) {
               if (var12 - 3 >= 0) {
                  var28.put(var10, var12 - 3);
               }

               if (var10 - 1 >= 0 && var11 instanceof aib) {
                  var29.put(var10, var10 - 1);
               }
            }

            var8.put(var10, var12);
            var1.q(var12, true, false, 2, null);
            if (!var1.xK().isEmpty()) {
               ArrayList var13 = new ArrayList(var1.xK());
               var9.put(var10, var13);
            }
         } else if (var11 instanceof ICLabel) {
            var27.put((ICLabel)var11, var10);
         }
      }

      HashMap var30 = new HashMap();
      ArrayDeque var31 = new ArrayDeque();

      for (int var32 = 0; var32 < var3.size(); var32++) {
         ICStatement var34 = (ICStatement)var3.get(var32);
         if (var34 instanceof ahi) {
            var31.push(var32);
         } else if (var34 instanceof ahj) {
            var31.pop();
         }

         if (var34 instanceof ICGoto) {
            ICLabel var14 = ((ICGoto)var34).getLabel();
            Integer var15 = (Integer)var27.get(var14);
            if (var15 != null) {
               int var16 = var15;
               int var17 = 0;
               byte var18 = 0;
               ICLabel var19 = null;
               int var20 = -1;

               for (Iterator var21 = var31.iterator(); var21.hasNext(); var17++) {
                  var20 = (Integer)var21.next();
                  Integer var22 = (Integer)var28.get(var20);
                  if (var22 != null && var22 == var16) {
                     var18 = 1;
                     break;
                  }

                  var22 = (Integer)var29.get(var20);
                  if (var22 != null && var22 == var16) {
                     var18 = 1;
                     break;
                  }

                  Integer var23 = (Integer)var8.get(var20);
                  if (var23 != null && var23 == var16) {
                     var18 = 2;
                     break;
                  }

                  List var24 = (List)var9.get(var20);
                  if (var24 != null && var24.contains(var16)) {
                     var18 = 2;
                     break;
                  }
               }

               if (var18 > 0) {
                  if (var17 > 0) {
                     int var39 = var20 - 1;
                     if (var39 >= 0 && var3.get(var39) instanceof ICLabel) {
                        var19 = (ICLabel)var3.get(var39);
                     } else {
                        var19 = (ICLabel)var30.get(var20);
                        if (var19 == null) {
                           var19 = this.m.getLabelFactory().create();
                           var30.put(var20, var19);
                           Object[] var10000 = new Object[]{var19};
                        }
                     }
                  }

                  Object var40;
                  if (var18 == 1) {
                     var40 = this.ef.createContinue(var19);
                  } else {
                     var40 = this.ef.createBreak(var19);
                  }

                  var3.set(var32, var40);
                  var26++;
               }
            }
         }
      }

      if (!var30.isEmpty()) {
         TreeSet var33 = new TreeSet(var30.keySet());

         for (Integer var36 : var33.descendingSet()) {
            ICLabel var37 = (ICLabel)var30.get(var36);
            var3.add(var36, var37);
         }
      }

      if (var26 > 0) {
         this.m.fromFlatList(var3);
      }

      return var2 + var26;
   }
}
