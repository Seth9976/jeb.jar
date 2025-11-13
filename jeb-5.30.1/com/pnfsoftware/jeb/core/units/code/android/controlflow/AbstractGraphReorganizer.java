package com.pnfsoftware.jeb.core.units.code.android.controlflow;

import com.pnfsoftware.jeb.core.units.code.ILocatedInstruction;
import com.pnfsoftware.jeb.util.logging.GlobalLog;
import com.pnfsoftware.jeb.util.logging.ILogger;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public abstract class AbstractGraphReorganizer {
   private static final ILogger logger = GlobalLog.getLogger(AbstractGraphReorganizer.class);
   private CFG cfg;
   private CFG reorganizedCfg;
   private Map conversionMap;

   public AbstractGraphReorganizer(CFG var1) {
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

            long var26 = 0L;
            long var8 = -1L;
            HashMap var10 = new HashMap();
            HashMap var11 = new HashMap();
            TreeMap var12 = new TreeMap();
            HashMap var13 = new HashMap();
            HashMap var14 = new HashMap();
            boolean var15 = var1[0] != 0;
            if (var15) {
               BasicBlockBuilder var16 = new BasicBlockBuilder();
               var16.insns.add(this.createTrampoline(var26, 0L));
               var12.put(var26, var16);
               var14.put(var16, 0);
               var11.put(var8, var26);
               var26++;
               var8--;
            }

            for (int var27 = 0; var27 < var1.length; var27++) {
               int var17 = var1[var27];
               boolean var18 = var27 + 1 < var1.length && var1[var27 + 1] == var17 + 1;
               BasicBlock var19 = this.cfg.get(var17);
               var10.put(var26, var19.getFirstAddress());
               var11.put(var19.getFirstAddress(), var26);
               BasicBlockBuilder var20 = new BasicBlockBuilder();
               var12.put(var26, var20);
               var13.put(var20, var17);

               for (ILocatedInstruction var22 : var19) {
                  var22 = this.updateInstructionOffset(var22, var26);
                  var26 += var22.getSize();
                  var20.insns.add(var22);
               }

               if (!var18 && this.needsTrampoline(var19)) {
                  if (this.isFallthrough(var19)) {
                     var20.insns.add(this.createTrampoline(var26, (Long)((List)var4.get(var17)).get(0)));
                     var26++;
                  } else {
                     BasicBlockBuilder var35 = new BasicBlockBuilder();
                     var35.insns.add(this.createTrampoline(var26, (Long)((List)var4.get(var17)).get(0)));
                     var12.put(var26, var35);
                     var14.put(var35, var17 + 1);
                     ((List)var4.get(var17)).set(0, var8);
                     var11.put(var8, var26);
                     var26++;
                     var8--;
                  }
               }
            }

            for (BasicBlockBuilder var30 : var12.values()) {
               ILocatedInstruction var31 = (ILocatedInstruction)var30.insns.get(var30.insns.size() - 1);
               Integer var32 = (Integer)var14.get(var30);
               if (var32 != null) {
                  long var34 = (Long)var3.get(var32);
                  long var41 = (Long)var11.get(var34);
                  this.updateTrampolineTarget(var31, var41);
                  var30.dst_offsets.add(var41);
               } else {
                  int var33 = (Integer)var13.get(var30);

                  for (long var39 : (List)var4.get(var33)) {
                     long var24 = (Long)var11.get(var39);
                     var30.dst_offsets.add(var24);
                  }

                  for (long var40 : (List)var5.get(var33)) {
                     long var42 = (Long)var11.get(var40);
                     var30.irrdst_offsets.add(var42);
                  }

                  if (var31.getBreakingFlow().isBroken()) {
                     this.updateTargets(var31, var11);
                  }
               }
            }

            CFG var29 = new CFG(var12.values());
            this.reorganizedCfg = var29;
            this.conversionMap = var11;
            return var29;
         }
      }
   }

   public void shift(int var1) {
      if (this.conversionMap != null) {
         throw new IllegalStateException();
      } else if (var1 <= 0) {
         throw new IllegalArgumentException();
      } else {
         HashMap var2 = new HashMap();

         for (BasicBlock var4 : this.cfg) {
            var2.put(var4.getAddress(), var4.getAddress() + var1);
         }

         for (BasicBlock var9 : this.cfg) {
            for (ILocatedInstruction var6 : var9) {
               this.updateInstructionOffset(var6, var6.getOffset() + var1);
               if (this.hasTargets(var6)) {
                  this.updateTargets(var6, var2);
               }
            }
         }

         BasicBlock var8 = new BasicBlock();
         var8.add(this.createNop(0L, var1));
         this.cfg.addBlock(0, var8);
         this.cfg.addEdge(var8, this.cfg.get(1));
         this.conversionMap = var2;
         this.reorganizedCfg = this.cfg;
      }
   }

   public CFG getReorganizedCfg() {
      return this.reorganizedCfg;
   }

   public Map getConversionMap() {
      return this.conversionMap;
   }

   private boolean isFallthrough(BasicBlock var1) {
      return !var1.getLast().getBreakingFlow().isBroken() && var1.outsize() >= 1;
   }

   private boolean needsTrampoline(BasicBlock var1) {
      return this.isFallthrough(var1) || this.canFallthrough(var1.getLast());
   }

   protected abstract boolean canFallthrough(ILocatedInstruction var1);

   protected abstract ILocatedInstruction updateInstructionOffset(ILocatedInstruction var1, long var2);

   protected abstract ILocatedInstruction createTrampoline(long var1, long var3);

   protected abstract ILocatedInstruction createNop(long var1, int var3);

   protected abstract void updateTrampolineTarget(ILocatedInstruction var1, long var2);

   protected abstract boolean hasTargets(ILocatedInstruction var1);

   protected abstract void updateTargets(ILocatedInstruction var1, Map var2);
}
