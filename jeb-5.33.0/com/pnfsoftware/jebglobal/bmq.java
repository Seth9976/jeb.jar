package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.units.code.java.AbstractJOptimizer;
import com.pnfsoftware.jeb.core.units.code.java.IJavaBreak;
import com.pnfsoftware.jeb.core.units.code.java.IJavaContinue;
import com.pnfsoftware.jeb.core.units.code.java.IJavaGoto;
import com.pnfsoftware.jeb.core.units.code.java.IJavaLabel;
import com.pnfsoftware.jeb.core.units.code.java.IJavaStatement;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;

public class bmq extends AbstractJOptimizer {
   @Override
   public int perform() {
      if (this.m == null) {
         return 0;
      } else {
         int var1 = 0;
         HashMap var2 = new HashMap();
         HashSet var3 = new HashSet();
         List var4 = this.m.getStatements();

         for (int var5 = 0; var5 < var4.size(); var5++) {
            IJavaStatement var6 = (IJavaStatement)var4.get(var5);
            if (var6 instanceof IJavaLabel) {
               var3.add((IJavaLabel)var6);
            } else if (var6 instanceof IJavaGoto) {
               var2.put(var6, ((IJavaGoto)var6).getLabel());
            } else if (var6 instanceof IJavaContinue && ((IJavaContinue)var6).getLabel() != null) {
               var2.put(var6, ((IJavaContinue)var6).getLabel());
            } else if (var6 instanceof IJavaBreak && ((IJavaBreak)var6).getLabel() != null) {
               var2.put(var6, ((IJavaBreak)var6).getLabel());
            }
         }

         for (IJavaLabel var10 : var3) {
            if (!var2.values().contains(var10)) {
               this.m.deleteStatement(var10);
               var1++;
            }
         }

         for (IJavaStatement var11 : var2.keySet()) {
            IJavaLabel var7 = (IJavaLabel)var2.get(var11);
            if (!var3.contains(var7)) {
               this.m.deleteStatement(var11);
               var1++;
            }
         }

         return var1;
      }
   }
}
