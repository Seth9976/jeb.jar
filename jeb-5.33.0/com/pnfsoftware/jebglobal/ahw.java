package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ast.CVisitResults;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ast.ICBlock;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ast.ICCompound;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ast.ICDecl;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ast.ICMethod;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ast.ICStatement;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ast.opt.AbstractCOptimizer;
import com.pnfsoftware.jeb.util.base.Couple;
import com.pnfsoftware.jeb.util.collect.IdentityHashSet;
import java.util.IdentityHashMap;
import java.util.TreeSet;

public class ahw extends AbstractCOptimizer {
   @Override
   protected int perform() {
      ahw.Av var1 = new ahw.Av(this.m);
      return var1.pC();
   }

   private static class Av {
      ICMethod pC;
      IdentityHashMap A = new IdentityHashMap();
      IdentityHashSet kS = new IdentityHashSet();

      Av(ICMethod var1) {
         this.pC = var1;
      }

      int pC() {
         this.pC(this.pC.getBody());
         return this.A();
      }

      private void pC(ICBlock var1) {
         for (int var2 = 0; var2 < var1.size(); var2++) {
            ICStatement var3 = var1.get(var2);
            if (var3 instanceof ICDecl) {
               this.A.put(((ICDecl)var3).getIdentifier(), new Couple(var1, var2));
            } else {
               var3.visitDepthPost(new ahx(this), var1, new CVisitResults(256));
            }

            if (var3 instanceof ICCompound) {
               for (ICBlock var5 : ((ICCompound)var3).getBlocks()) {
                  this.pC(var5);
               }
            }
         }
      }

      private int A() {
         this.A.keySet().removeAll(this.kS);
         IdentityHashMap var1 = new IdentityHashMap();

         for (Couple var3 : this.A.values()) {
            ICBlock var4 = (ICBlock)var3.getFirst();
            TreeSet var5 = (TreeSet)var1.get(var4);
            if (var5 == null) {
               var5 = new TreeSet();
               var1.put(var4, var5);
            }

            var5.add((Integer)var3.getSecond());
         }

         int var8 = 0;

         for (ICBlock var10 : var1.keySet()) {
            TreeSet var11 = (TreeSet)var1.get(var10);

            for (int var7 : var11.descendingSet()) {
               var10.remove(var7);
               var8++;
            }
         }

         return var8;
      }
   }
}
