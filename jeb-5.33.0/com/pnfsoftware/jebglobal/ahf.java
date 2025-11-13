package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ast.CUtil;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ast.ICBreak;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ast.ICCompound;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ast.ICContinue;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ast.ICDecl;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ast.ICGoto;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ast.ICJumpFar;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ast.ICLabel;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ast.ICMethod;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ast.ICReturn;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ast.ICStatement;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ast.opt.AbstractCOptimizer;
import com.pnfsoftware.jeb.util.base.Assert;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

public class ahf extends AbstractCOptimizer {
   @Override
   protected int perform() {
      ahf.Av var1 = new ahf.Av(this.m);
      return var1.pC();
   }

   private static class Av {
      ICMethod pC;
      List A;
      private Map kS = new HashMap();
      private Map wS = new HashMap();

      Av(ICMethod var1) {
         this.pC = var1;
         this.A = var1.toFlatList();
      }

      int pC() {
         this.A();
         this.kS();
         int var1 = this.wS();
         if (var1 != 0) {
            this.pC.fromFlatList(this.A);
         }

         return var1;
      }

      private void A() {
         for (ICStatement var2 : this.A) {
            if (var2 instanceof ICGoto var3) {
               Object var4 = (List)this.kS.get(var3.getLabel());
               if (var4 == null) {
                  var4 = new ArrayList();
                  this.kS.put(var3.getLabel(), var4);
               }

               var4.add(var3);
            }
         }
      }

      private void kS() {
         int var1 = 0;

         for (ICStatement var3 : this.A) {
            if (var3 instanceof ICLabel) {
               int var4 = var1 + 1;
               boolean var5 = false;
               ArrayList var6 = new ArrayList();

               for (int var7 = 0; var4 < this.A.size() && var7 <= 5; var4++) {
                  ICStatement var8 = (ICStatement)this.A.get(var4);
                  if (var8 instanceof ICCompound
                     || var8 instanceof agk && !(var8 instanceof aft) && !(var8 instanceof agb)
                     || CUtil.isGotoTo(var8, (ICLabel)var3)
                     || !this.A(var8)) {
                     break;
                  }

                  if (!(var8 instanceof agk)) {
                     var6.add(var8);
                     var7++;
                  }

                  if (this.pC(var8)) {
                     var5 = true;
                     break;
                  }
               }

               if (var5) {
                  Assert.a(!this.wS.containsKey(var3));
                  this.wS.put((ICLabel)var3, var6);
               }
            }

            var1++;
         }
      }

      private boolean pC(ICStatement var1) {
         return var1 instanceof ICReturn || var1 instanceof ICGoto || var1 instanceof ICJumpFar || var1 instanceof ICContinue || var1 instanceof ICBreak;
      }

      private boolean A(ICStatement var1) {
         return !(var1 instanceof ICBreak) && !(var1 instanceof ICContinue) && !(var1 instanceof ICLabel) && !(var1 instanceof ICDecl);
      }

      private int wS() {
         int var1 = 0;

         for (Entry var3 : this.wS.entrySet()) {
            List var4 = (List)this.kS.get(var3.getKey());
            if (var4 != null) {
               for (ICGoto var6 : var4) {
                  int var7 = this.A.indexOf(var6);
                  if (var7 != -1) {
                     this.A.remove(var6);

                     for (ICStatement var9 : (List)var3.getValue()) {
                        ICStatement var10 = var9.duplicate();
                        this.A.add(var7, var10);
                        var7++;
                     }

                     var1++;
                  }
               }
            }
         }

         return var1;
      }
   }
}
