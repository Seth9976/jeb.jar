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

public class ajb extends AbstractCOptimizer {
   private static final int q = 5;

   @Override
   protected int perform() {
      ajb.eo var1 = new ajb.eo(this.m);
      return var1.q();
   }

   private static class eo {
      ICMethod q;
      List RF;
      private Map xK = new HashMap();
      private Map Dw = new HashMap();

      eo(ICMethod var1) {
         this.q = var1;
         this.RF = var1.toFlatList();
      }

      int q() {
         this.RF();
         this.xK();
         int var1 = this.Dw();
         if (var1 != 0) {
            this.q.fromFlatList(this.RF);
         }

         return var1;
      }

      private void RF() {
         for (ICStatement var2 : this.RF) {
            if (var2 instanceof ICGoto var3) {
               Object var4 = (List)this.xK.get(var3.getLabel());
               if (var4 == null) {
                  var4 = new ArrayList();
                  this.xK.put(var3.getLabel(), var4);
               }

               var4.add(var3);
            }
         }
      }

      private void xK() {
         int var1 = 0;

         for (ICStatement var3 : this.RF) {
            if (var3 instanceof ICLabel) {
               int var4 = var1 + 1;
               boolean var5 = false;
               ArrayList var6 = new ArrayList();

               for (int var7 = 0; var4 < this.RF.size() && var7 <= 5; var4++) {
                  ICStatement var8 = (ICStatement)this.RF.get(var4);
                  if (var8 instanceof ICCompound
                     || var8 instanceof aid && !(var8 instanceof ahm) && !(var8 instanceof ahu)
                     || CUtil.isGotoTo(var8, (ICLabel)var3)
                     || !this.RF(var8)) {
                     break;
                  }

                  if (!(var8 instanceof aid)) {
                     var6.add(var8);
                     var7++;
                  }

                  if (this.q(var8)) {
                     var5 = true;
                     break;
                  }
               }

               if (var5) {
                  Assert.a(!this.Dw.containsKey(var3));
                  this.Dw.put((ICLabel)var3, var6);
               }
            }

            var1++;
         }
      }

      private boolean q(ICStatement var1) {
         return var1 instanceof ICReturn || var1 instanceof ICGoto || var1 instanceof ICJumpFar || var1 instanceof ICContinue || var1 instanceof ICBreak;
      }

      private boolean RF(ICStatement var1) {
         return !(var1 instanceof ICBreak) && !(var1 instanceof ICContinue) && !(var1 instanceof ICLabel) && !(var1 instanceof ICDecl);
      }

      private int Dw() {
         int var1 = 0;

         for (Entry var3 : this.Dw.entrySet()) {
            List var4 = (List)this.xK.get(var3.getKey());
            if (var4 != null) {
               for (ICGoto var6 : var4) {
                  int var7 = this.RF.indexOf(var6);
                  if (var7 != -1) {
                     this.RF.remove(var6);

                     for (ICStatement var9 : (List)var3.getValue()) {
                        ICStatement var10 = var9.duplicate();
                        this.RF.add(var7, var10);
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
