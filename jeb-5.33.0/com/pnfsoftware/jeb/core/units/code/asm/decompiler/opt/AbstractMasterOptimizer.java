package com.pnfsoftware.jeb.core.units.code.asm.decompiler.opt;

import com.pnfsoftware.jeb.client.S;
import com.pnfsoftware.jeb.util.format.Strings;
import com.pnfsoftware.jeb.util.logging.StructuredLogger;
import com.pnfsoftware.jebglobal.acj;
import com.pnfsoftware.jebglobal.aco;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
import java.util.SortedMap;
import java.util.TreeMap;

public abstract class AbstractMasterOptimizer implements IMasterOptimizer {
   private static final StructuredLogger logger = aco.pC(AbstractMasterOptimizer.class);
   private static int runmode = 2;
   protected IOptimizerTarget t;
   private OptimizerMode currentMode = OptimizerMode.NORMAL;
   protected SortedMap optGrpMap = new TreeMap();
   protected List optList = new ArrayList();
   protected int grp1NMaxRunCount;
   protected List instrumenters = new ArrayList();
   protected boolean enableUnsafeOptimizers;
   protected boolean enableDeobfuscators;
   protected int decryptorSupport;
   private Set tagsAllowed = null;
   private Set tagsBlocked = new HashSet();
   private int totalOptCount;
   private int totalDeobOptCount;

   public AbstractMasterOptimizer(IOptimizerTarget var1, int var2) {
      this.setTarget(var1);
      this.grp1NMaxRunCount = var2;
   }

   @Override
   public IOptimizerTarget getTarget() {
      return this.t;
   }

   @Override
   public void setTarget(IOptimizerTarget var1) {
      this.t = var1;
   }

   @Override
   public OptimizerMode setMode(OptimizerMode var1) {
      OptimizerMode var2 = this.currentMode;
      this.currentMode = var1 == null ? OptimizerMode.NORMAL : var1;
      return var2;
   }

   @Override
   public OptimizerMode getMode() {
      return this.currentMode;
   }

   public void setEnableUnsafeOptimizers(boolean var1) {
      this.enableUnsafeOptimizers = var1;
   }

   public boolean isEnableUnsafeOptimizers() {
      return this.enableUnsafeOptimizers;
   }

   public void setEnableDeobfuscators(boolean var1) {
      this.enableDeobfuscators = var1;
   }

   public boolean isEnableDeobfuscators() {
      return this.enableDeobfuscators;
   }

   public void setDecryptorSupport(int var1) {
      this.decryptorSupport = var1;
   }

   public int getDecryptorSupport() {
      return this.decryptorSupport;
   }

   @Override
   public void setPolicyForOptimizerTag(String var1, boolean var2) {
      if (var2) {
         if (var1 == null || var1.isEmpty()) {
            this.tagsAllowed = new HashSet();
         } else if (var1.equals("*")) {
            this.tagsAllowed = null;
         } else {
            if (this.tagsAllowed == null) {
               return;
            }

            this.tagsAllowed.add(var1);
         }
      } else if (var1 == null || var1.isEmpty()) {
         this.tagsBlocked = new HashSet();
      } else if (var1.equals("*")) {
         this.tagsBlocked = null;
      } else {
         if (this.tagsBlocked == null) {
            return;
         }

         this.tagsBlocked.add(var1);
      }
   }

   @Override
   public OptimizerEntry registerOptimizer(IOptimizer var1) {
      return this.registerOptimizer(1, var1);
   }

   @Override
   public OptimizerEntry registerOptimizer(int var1, IOptimizer var2) {
      if (var1 < -2) {
         throw new IllegalArgumentException("Illegal group id: " + var1);
      } else if (var2.getMasterOptimizer() != null && var2.getMasterOptimizer() != this) {
         throw new IllegalStateException(Strings.ff("The optimizer %s is already used by a master optimizer! Please create a new optimizer object.", var2));
      } else {
         var2.setMasterOptimizer(this);
         if (var2.getType() == OptimizerType.ON_DEMAND) {
            var1 = -2;
         }

         Object var3 = null;

         for (int var4 = -2; var4 <= var1; var4++) {
            var3 = (List)this.optGrpMap.get(var4);
            if (var3 == null) {
               var3 = new ArrayList();
               this.optGrpMap.put(var4, var3);
            }
         }

         OptimizerEntry var5 = new OptimizerEntry(var2, var1);
         var3.add(var5);
         Collections.sort((List)var3, new AbstractMasterOptimizer$1(this));
         this.optList.add(var5);
         return var5;
      }
   }

   @Override
   public boolean unregisterOptimizer(OptimizerEntry var1) {
      if (!this.optList.remove(var1)) {
         return false;
      } else {
         int var2 = var1.getGroup();
         List var3 = (List)this.optGrpMap.get(var2);
         return var3 == null ? false : var3.remove(var1);
      }
   }

   public void unregisterAllOptimizers() {
      this.optList.clear();

      for (Integer var2 : this.optGrpMap.keySet()) {
         ((List)this.optGrpMap.get(var2)).clear();
      }
   }

   @Override
   public List getRegisteredOptimizers() {
      return new ArrayList(this.optList);
   }

   @Override
   public List getRegisteredOptimizers(int var1) {
      ArrayList var2 = new ArrayList();
      List var3 = (List)this.optGrpMap.get(var1);
      if (var3 != null) {
         var2.addAll(var3);
      }

      return var2;
   }

   @Override
   public OptimizerEntry getOptimizer(Class var1) {
      for (OptimizerEntry var3 : this.getRegisteredOptimizers()) {
         if (var1.isInstance(var3.getOptimizer())) {
            return var3;
         }
      }

      return null;
   }

   @Override
   public int getTotalOptimizationCount() {
      return this.totalOptCount;
   }

   @Override
   public int getOptimizationCount(boolean var1) {
      return var1 ? this.totalDeobOptCount : this.totalOptCount;
   }

   @Override
   public IOptimizer getOptimizerObject(Class var1) {
      OptimizerEntry var2 = this.getOptimizer(var1);
      return var2 == null ? null : var2.getOptimizer();
   }

   @Override
   public int perform() {
      if (runmode == 0) {
         return this.performV1();
      } else if (runmode == 1) {
         return this.performV2(false);
      } else if (runmode == 2) {
         return this.performV2(true);
      } else {
         throw new RuntimeException("Unknown runmode: " + runmode);
      }
   }

   protected int performV1() {
      int var1 = 0;
      Object[] var10000 = new Object[0];
      IOptimizerTarget var2 = this.getTarget();
      this.preAllOptimizationsCallback(var2);
      List var3 = (List)this.optGrpMap.get(0);
      if (var3 != null) {
         var1 += this.performMultiple(var3, 0);
      }

      int var4;
      if (this.grp1NMaxRunCount < 0) {
         var4 = Integer.MAX_VALUE;
      } else {
         int var5 = this.optGrpMap.subMap(1, Integer.MAX_VALUE).size();
         var4 = this.grp1NMaxRunCount * Math.max(1, var5);
         if (var4 < 0) {
            var4 = Integer.MAX_VALUE;
         }
      }

      int var11 = var4;
      int var6 = 1;
      int var7 = 0;

      while (var11-- > 0) {
         var3 = (List)this.optGrpMap.get(var6);
         if (var3 == null) {
            break;
         }

         int var8 = this.performMultiple(var3, var6);
         var1 += var8;
         if (var6 > var7) {
            var7 = var6;
         }

         if (var8 > 0) {
            var6 = 1;
         } else {
            var6++;
         }
      }

      if (var11 < 0) {
         logger.debug("opti runcount: exhausted");
         if (var7 < (Integer)this.optGrpMap.lastKey()) {
            logger.debug("some optimizer groups were not executed!");
         }

         this.reportMaxOptiRunCountReached(var2);
      }

      var3 = (List)this.optGrpMap.get(-1);
      if (var3 != null) {
         this.performMultiple(var3, -1);
      }

      this.postAllOptimizationsCallback(var2);
      return var1;
   }

   protected int performV2(boolean var1) {
      Object[] var10000 = new Object[0];
      IOptimizerTarget var2 = this.getTarget();
      this.preAllOptimizationsCallback(var2);
      this.optList.sort((var0, var1x) -> -Double.compare(var0.getOptimizer().getPriority(), var1x.getOptimizer().getPriority()));
      ArrayList var3 = new ArrayList(this.optList);
      int var4 = var1 ? 400 : 100;
      LinkedHashSet var5 = new LinkedHashSet();
      int var6 = 0;
      int var7 = 0;

      label36:
      while (true) {
         if (var6 > var4) {
            String var12 = "Optimization round exceeding threshold";
            acj.pC(new RuntimeException(var12));
            break;
         }

         var5.clear();
         int var8 = 0;
         Iterator var9 = var3.iterator();

         while (true) {
            if (var9.hasNext()) {
               OptimizerEntry var10 = (OptimizerEntry)var9.next();
               int var11 = this.performSingle(var10);
               if (var11 > 0) {
                  var8 += var11;
                  var5.add(var10.getOptimizer());
               }

               if (!var1 || var11 <= 0) {
                  continue;
               }
            }

            var7 += var8;
            var6++;
            if (var8 <= 0) {
               break label36;
            }
            break;
         }
      }

      this.postAllOptimizationsCallback(var2);
      return var7;
   }

   private void reportMaxOptiRunCountReached(IOptimizerTarget var1) {
   }

   @Override
   public int performMultiple(List var1) {
      return this.performMultiple(var1, null);
   }

   private int performMultiple(List var1, Integer var2) {
      Object[] var10000 = new Object[]{var2};
      int var3 = 0;

      for (OptimizerEntry var5 : var1) {
         var3 += this.performSingle(var5);
      }

      return var3;
   }

   @Override
   public int performSingle(OptimizerEntry var1) {
      if (!var1.isEnabled()) {
         return 0;
      } else {
         IOptimizer var2 = var1.getOptimizer();
         if (!this.getMode().meetsRequirement(var2.getRequiredModeThreshold())) {
            return 0;
         } else if (var2.getType() == OptimizerType.ON_DEMAND) {
            return 0;
         } else if (!this.enableUnsafeOptimizers && var2.getType() == OptimizerType.UNSAFE) {
            return 0;
         } else {
            boolean var3 = var2.getTags().contains("deobfuscator");
            if (!this.enableDeobfuscators && var3) {
               return 0;
            } else {
               IOptimizerTarget var4 = this.getTarget();
               this.preOptimizationCallback(var4, var1);
               long var5 = System.nanoTime();
               int var7 = 0;

               try {
                  var7 = var2.performOnTarget(var4);
                  if (var7 > 0) {
                     this.totalOptCount += var7;
                     if (var3) {
                        this.totalDeobOptCount += var7;
                     }
                  }
               } catch (Exception var12) {
                  if (!this.onOptimizerException(var4, var2, var12)) {
                     throw var12;
                  }
               } finally {
                  ;
               }

               long var8 = (System.nanoTime() - var5) / 1000000L;
               this.postOptimizationCallback(var4, var1, var7, var8);
               var1.stat.runcnt++;
               var1.stat.optcnt += var7;
               var1.stat.runtimeMs += var8;
               acj.pC();
               return var7;
            }
         }
      }
   }

   protected void preAllOptimizationsCallback(IOptimizerTarget var1) {
      for (IMasterOptimizerInstrumenter var3 : this.instrumenters) {
         var3.preAllOptimizationsCallback(var1);
      }
   }

   protected void preOptimizationCallback(IOptimizerTarget var1, OptimizerEntry var2) {
      for (IMasterOptimizerInstrumenter var4 : this.instrumenters) {
         var4.preOptimizationCallback(var1, var2);
      }
   }

   protected void postAllOptimizationsCallback(IOptimizerTarget var1) {
      for (IMasterOptimizerInstrumenter var3 : this.instrumenters) {
         var3.postAllOptimizationsCallback(var1);
      }
   }

   protected void postOptimizationCallback(IOptimizerTarget var1, OptimizerEntry var2, int var3, long var4) {
      for (IMasterOptimizerInstrumenter var7 : this.instrumenters) {
         var7.postOptimizationCallback(var1, var2, var3, var4);
      }
   }

   @Override
   public void registerInstrumenter(IMasterOptimizerInstrumenter var1) {
      this.instrumenters.add(var1);
   }

   @Override
   public boolean unregisterInstrumenter(IMasterOptimizerInstrumenter var1) {
      return this.instrumenters.remove(var1);
   }

   protected boolean onOptimizerException(IOptimizerTarget var1, IOptimizer var2, Exception var3) {
      logger.error(S.L("An optimizer failed on %s: %s: %s"), var1.getTargetName(), var2.getPluginInformation(), var3);
      return true;
   }

   public OptimizersPerformanceCounters retrievePerformanceCounters() {
      OptimizersPerformanceCounters var1 = new OptimizersPerformanceCounters();

      for (OptimizerEntry var3 : this.getRegisteredOptimizers()) {
         String var4 = var3.getOptimizer().getPluginInformation().getName();
         OptimizerEntry.Stat var5 = var3.getStatistics();
         OptimizersPerformanceCounters.E var6 = new OptimizersPerformanceCounters.E();
         var6.exectime = var5.getExecutionTimeMillis();
         var6.runcnt = var5.getRunCount();
         var6.optcnt = var5.getOptimizationCount();
         var1.map.put(var4, var6);
      }

      return var1;
   }

   protected abstract String getTargetAddress(IOptimizerTarget var1);
}
