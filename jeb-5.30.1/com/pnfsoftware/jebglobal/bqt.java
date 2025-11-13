package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.units.code.java.AbstractJOptimizer;
import com.pnfsoftware.jeb.core.units.code.java.IJavaMonitor;
import com.pnfsoftware.jeb.core.units.code.java.IJavaStatement;
import com.pnfsoftware.jeb.core.units.code.java.JOptimizerType;
import java.util.LinkedList;
import java.util.List;

public class bqt extends AbstractJOptimizer {
   public bqt() {
      super(JOptimizerType.UNSAFE);
   }

   @Override
   public int perform() {
      if (this.m == null) {
         return 0;
      } else {
         List var1 = this.m.toFlatList();
         int var2 = 0;
         LinkedList var3 = new LinkedList();

         for (IJavaStatement var5 : var1) {
            if (var5 instanceof IJavaMonitor) {
               if (((IJavaMonitor)var5).isEnter()) {
                  return 0;
               }

               var3.add(0, var2);
            }

            var2++;
         }

         int var7 = var3.size();
         if (var7 > 0) {
            for (int var6 : var3) {
               var1.remove(var6);
            }

            this.m.fromFlatList(var1);
         }

         return var7;
      }
   }
}
