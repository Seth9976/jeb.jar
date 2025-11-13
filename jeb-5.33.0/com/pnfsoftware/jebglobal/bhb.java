package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.units.code.android.IDexUnit;
import com.pnfsoftware.jeb.core.units.code.android.dex.IDexClass;
import com.pnfsoftware.jeb.core.units.code.android.dex.IDexField;
import com.pnfsoftware.jeb.core.units.code.android.dex.IDexType;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

public class bhb {
   private com.pnfsoftware.jeb.corei.parsers.dex.vi pC;

   public bhb(IDexUnit var1) {
      this.pC = (com.pnfsoftware.jeb.corei.parsers.dex.vi)var1;
   }

   public Integer pC(IDexField var1) {
      List var2 = this.pC(var1, true, false);
      if (var2.isEmpty()) {
         return null;
      } else {
         int var3 = (Integer)var2.get(var2.size() - 1);
         return !this.pC.E(var3).isInternal() ? null : var3;
      }
   }

   public List A(IDexField var1) {
      return this.pC(var1, true);
   }

   public List pC(IDexField var1, boolean var2) {
      List var3 = this.pC(var1, true, true);
      if (!var2) {
         var3.remove(Integer.valueOf(var1.getIndex()));
      }

      return var3;
   }

   private List pC(IDexField var1, boolean var2, boolean var3) {
      ArrayList var4 = new ArrayList();
      bft var5 = (bft)var1;
      String var6 = var5.getName(false);
      String var7 = var5.getFieldTypeSignature(false);
      Object var8 = var5.getClassType();
      var4.add(var5.getIndex());
      if (var2) {
         while (true) {
            IDexClass var9 = ((IDexType)var8).getImplementingClass();
            if (var9 == null || var5 != null && ((bfs)var9).pC(var5)) {
               break;
            }

            int var10 = var9.getSuperTypeIndex();
            if (var10 == -1) {
               break;
            }

            bfy var11 = this.pC.wS(var10);
            String var12 = var11.getSignature(false) + "->" + var6 + ":" + var7;
            var5 = this.pC.kS(var12);
            if (var5 != null) {
               var4.add(var5.getIndex());
            }

            var8 = var11;
         }
      }

      if (var3) {
         bgy var21 = this.pC.eP();
         HashSet var22 = new HashSet();
         ArrayDeque var23 = new ArrayDeque();
         var23.add(var1.getClassTypeIndex());

         while (!var23.isEmpty()) {
            int var24 = (Integer)var23.pop();
            var22.add(var24);
            bhe var13 = var21.pC(var24);
            if (var13 != null) {
               for (int var15 : var13.wS) {
                  boolean var16 = false;
                  bfy var17 = this.pC.wS(var15);
                  String var18 = var17.getSignature(false) + "->" + var6 + ":" + var7;
                  var5 = this.pC.kS(var18);
                  if (var5 != null) {
                     bfs var19 = var17.A();
                     if (var19 != null) {
                        if (var19.pC(var5)) {
                           var16 = true;
                        } else {
                           var4.add(var5.getIndex());
                        }
                     }
                  }

                  if (!var16 && !var22.contains(var15)) {
                     var23.add(var15);
                  }
               }
            }
         }
      }

      return var4;
   }
}
