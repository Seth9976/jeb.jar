package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.units.code.asm.cfg.CFG;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.IERoutineContext;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.EUtil;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.IEStatement;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.IEVar;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.VarSrc;
import com.pnfsoftware.jeb.util.collect.CollectionOrder;
import com.pnfsoftware.jeb.util.collect.SetMap;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.TreeMap;

public class anv extends ank {
   CFG xK;

   public anv(IERoutineContext var1) {
      super(var1);
      this.xK = var1.getCfg();
   }

   @Override
   public int Dw() {
      int var1 = 0;
      HashSet var2 = new HashSet();

      for (IEStatement var4 : this.xK.instructions()) {
         var2.addAll(EUtil.collectVars(var4));
      }

      SetMap var13 = new SetMap(CollectionOrder.NATURAL, CollectionOrder.NATURAL);

      for (IEVar var5 : var2) {
         int var6 = var5.getId();
         if (var6 < 0 && -var6 >= 65536 && -var6 < 1245184) {
            VarSrc var7 = this.RF.getSourceForVariable(var6);
            var13.put(var7.getAsDuplicate(), var5);
         }
      }

      Object[] var10000 = new Object[]{var13};

      for (int var16 : var13.keySet()) {
         Set var17 = var13.get(var16);
         List var18 = this.RF.getDuplicatesForRegister(var16);
         var10000 = new Object[]{var18};
         TreeMap var8 = new TreeMap();

         for (IEVar var10 : var18) {
            var8.put(var10, var17.contains(var10));
         }

         var10000 = new Object[]{var8};

         while (true) {
            IEVar var19 = null;
            IEVar var20 = null;

            for (IEVar var12 : var8.keySet()) {
               if (var19 == null && !(Boolean)var8.get(var12)) {
                  var19 = var12;
               } else if (var19 != null && (Boolean)var8.get(var12)) {
                  var20 = var12;
                  break;
               }
            }

            if (var20 == null) {
               break;
            }

            var20.copyProperties(var19);

            for (IEStatement var22 : this.xK.instructions()) {
               var1 += var22.replaceVar(var20, var19);
            }

            var8.put(var19, true);
            var8.put(var20, false);
         }
      }

      if (var1 > 0) {
         this.xK.invalidateDataFlowAnalysis();
      }

      return var1;
   }
}
