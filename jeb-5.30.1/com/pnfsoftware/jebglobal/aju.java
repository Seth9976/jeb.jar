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

public class aju extends AbstractCOptimizer {
   @Override
   protected int perform() {
      aju.eo var1 = new aju.eo(this.m);
      return var1.q();
   }

   private static class eo {
      ICMethod q;
      IdentityHashMap RF = new IdentityHashMap();
      IdentityHashSet xK = new IdentityHashSet();

      eo(ICMethod var1) {
         this.q = var1;
      }

      int q() {
         this.q(this.q.getBody());
         return this.RF();
      }

      private void q(ICBlock var1) {
         for (int var2 = 0; var2 < var1.size(); var2++) {
            ICStatement var3 = var1.get(var2);
            if (var3 instanceof ICDecl) {
               this.RF.put(((ICDecl)var3).getIdentifier(), new Couple(var1, var2));
            } else {
               var3.visitDepthPost(new ajv(this), var1, new CVisitResults(256));
            }

            if (var3 instanceof ICCompound) {
               for (ICBlock var5 : ((ICCompound)var3).getBlocks()) {
                  this.q(var5);
               }
            }
         }
      }

      private int RF() {
         this.RF.keySet().removeAll(this.xK);
         IdentityHashMap var1 = new IdentityHashMap();

         for (Couple var3 : this.RF.values()) {
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
