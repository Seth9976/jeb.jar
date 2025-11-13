package com.pnfsoftware.jeb.core.units.code.asm.cfg;

import com.pnfsoftware.jeb.core.units.code.IInstruction;
import com.pnfsoftware.jeb.util.logging.GlobalLog;
import com.pnfsoftware.jeb.util.logging.ILogger;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public abstract class AbstractCFGReorganizer {
   private static final ILogger logger = GlobalLog.getLogger(AbstractCFGReorganizer.class);
   private CFG cfg;
   private CFG reorganizedCfg;
   private Map conversionMap;

   public AbstractCFGReorganizer(CFG var1) {
      this.cfg = var1;
   }

   private static int verifyOrder(int var0, int[] var1) {
      HashSet var2 = new HashSet();

      for (int var6 : var1) {
         if (var6 < 0 || var6 >= var0 || !var2.add(var6)) {
            return 0;
         }
      }

      if (var2.size() != var0) {
         return 0;
      } else {
         int var8 = 0;

         for (int var7 : var1) {
            if (var7 != var8) {
               return 1;
            }

            var8++;
         }

         return -1;
      }
   }

   public CFG reorder(int[] var1) {
      if (this.conversionMap != null) {
         throw new IllegalStateException();
      } else {
         int var2 = verifyOrder(this.cfg.size(), var1);
         if (var2 <= 0) {
            if (var2 == 0) {
               throw new IllegalArgumentException();
            } else {
               return null;
            }
         } else {
            ArrayList var3 = new ArrayList();
            ArrayList var4 = new ArrayList();
            ArrayList var5 = new ArrayList();

            for (BasicBlock var7 : this.cfg) {
               var3.add(var7.getFirstAddress());
               var4.add(var7.getOutputOffsets());
               var5.add(var7.getIrregularOutputOffsets());
            }

            long var24 = 0L;
            long var8 = -1L;
            HashMap var10 = new HashMap();
            HashMap var11 = new HashMap();
            TreeMap var12 = new TreeMap();
            HashMap var13 = new HashMap();
            HashMap var14 = new HashMap();
            boolean var15 = var1[0] != 0;
            if (var15) {
               AbstractCFGReorganizer.BBB var16 = new AbstractCFGReorganizer.BBB();
               var16.insns.add(this.createTrampoline(0L));
               var12.put(var24, var16);
               var14.put(var16, 0);
               var11.put(var8, var24);
               var24++;
               var8--;
            }

            for (int var25 = 0; var25 < var1.length; var25++) {
               int var17 = var1[var25];
               boolean var18 = var25 + 1 < var1.length && var1[var25 + 1] == var17 + 1;
               BasicBlock var19 = this.cfg.get(var17);
               var10.put(var24, var19.getFirstAddress());
               var11.put(var19.getFirstAddress(), var24);
               AbstractCFGReorganizer.BBB var20 = new AbstractCFGReorganizer.BBB();
               var12.put(var24, var20);
               var13.put(var20, var17);

               for (IInstruction var22 : var19) {
                  var24 += var22.getSize();
                  var20.insns.add(var22);
               }

               if (!var18 && this.needsTrampoline(var19)) {
                  if (this.isFallthrough(var19)) {
                     var20.insns.add(this.createTrampoline((Long)((List)var4.get(var17)).get(0)));
                     var24++;
                  } else {
                     AbstractCFGReorganizer.BBB var33 = new AbstractCFGReorganizer.BBB();
                     var33.insns.add(this.createTrampoline((Long)((List)var4.get(var17)).get(0)));
                     var12.put(var24, var33);
                     var14.put(var33, var17 + 1);
                     ((List)var4.get(var17)).set(0, var8);
                     var11.put(var8, var24);
                     var24++;
                     var8--;
                  }
               }
            }

            for (AbstractCFGReorganizer.BBB var28 : var12.values()) {
               IInstruction var30 = (IInstruction)var28.insns.get(var28.insns.size() - 1);
               Integer var31 = (Integer)var14.get(var28);
               if (var31 != null) {
                  long var32 = (Long)var3.get(var31);
                  long var34 = (Long)var11.get(var32);
                  this.updateTrampolineTarget(var30, var34);
               } else {
                  this.updateTargets(var30, var11);
               }
            }

            ArrayList var27 = new ArrayList();
            var12.values().forEach(var1x -> var27.addAll(var1x.insns));
            CFG var29 = this.buildCFG(var27);
            this.reorganizedCfg = var29;
            this.conversionMap = var11;
            return var29;
         }
      }
   }

   public CFG getReorganizedCfg() {
      return this.reorganizedCfg;
   }

   public Map getConversionMap() {
      return this.conversionMap;
   }

   private boolean isFallthrough(BasicBlock var1) {
      return !var1.getLast().getBreakingFlow(var1.getLastAddress()).isBroken() && var1.outsize() >= 1;
   }

   private boolean needsTrampoline(BasicBlock var1) {
      return this.isFallthrough(var1) || this.canFallthrough(var1.getLast());
   }

   protected abstract boolean canFallthrough(IInstruction var1);

   protected abstract IInstruction createTrampoline(long var1);

   protected abstract IInstruction createNop(int var1);

   protected abstract void updateTrampolineTarget(IInstruction var1, long var2);

   protected abstract boolean hasTargets(IInstruction var1);

   protected abstract void updateTargets(IInstruction var1, Map var2);

   protected abstract CFG buildCFG(List var1);

   private static class BBB {
      List insns = new ArrayList();
   }
}
