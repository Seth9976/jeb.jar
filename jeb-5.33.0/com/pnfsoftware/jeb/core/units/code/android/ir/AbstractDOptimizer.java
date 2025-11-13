package com.pnfsoftware.jeb.core.units.code.android.ir;

import com.pnfsoftware.jeb.core.AbstractPlugin;
import com.pnfsoftware.jeb.core.EditablePluginInformation;
import com.pnfsoftware.jeb.core.units.code.IDFA;
import com.pnfsoftware.jeb.core.units.code.android.IDexDecompilerUnit;
import com.pnfsoftware.jeb.core.units.code.android.IDexUnit;
import com.pnfsoftware.jeb.core.units.code.android.controlflow.CFG;
import com.pnfsoftware.jeb.core.units.code.java.IJavaOperatorFactory;
import com.pnfsoftware.jeb.core.units.code.java.IJavaTypeFactory;
import com.pnfsoftware.jeb.util.concurrent.Watchdog;
import com.pnfsoftware.jeb.util.logging.GlobalLog;
import com.pnfsoftware.jeb.util.logging.ILogger;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public abstract class AbstractDOptimizer extends AbstractPlugin implements IDOptimizer {
   public static final ILogger logger = GlobalLog.getLogger(AbstractDOptimizer.class);
   public IJavaTypeFactory tf;
   public IJavaOperatorFactory of;
   public IDGlobalContext g;
   public IDexUnit dex;
   public IDexDecompilerUnit decomp;
   public List ctxlist;
   public IDMethodContext ctx;
   public CFG cfg;
   public IDFA dfa;
   private EditablePluginInformation info = new EditablePluginInformation();
   private DOptimizerType type;
   private Set tags;
   private double priority = 0.0;
   private boolean enabled = true;

   public AbstractDOptimizer() {
      this(null);
   }

   public AbstractDOptimizer(DOptimizerType var1) {
      this(var1, null);
   }

   public AbstractDOptimizer(DOptimizerType var1, String var2) {
      this.setType(var1);
      this.setName(var2);
   }

   protected void checkInterrupted() {
      Watchdog.verify(this.ctx == null ? null : this.ctx.getWatchdog());
   }

   public final EditablePluginInformation getPluginInformation() {
      return this.info;
   }

   protected void setName(String var1) {
      if (var1 == null) {
         Class var2 = this.getClass();
         var1 = var2.getSimpleName();
         if (var2.getName().contains(".jebglobal.")) {
            var1 = "Unknown_" + var1;
         }

         int var3 = var1.indexOf(36);
         if (var3 >= 0) {
            var1 = var1.substring(0, var3);
         }
      }

      this.info.setName(var1);
   }

   @Override
   public final String getName() {
      return this.info.getName();
   }

   @Override
   public boolean isCollectionOptimizer() {
      return false;
   }

   protected void setType(DOptimizerType var1) {
      if (var1 == null) {
         var1 = DOptimizerType.NORMAL;
      }

      this.type = var1;
   }

   @Override
   public final DOptimizerType getType() {
      return this.type;
   }

   protected void addTag(String var1) {
      if (var1 != null && !var1.isEmpty()) {
         if (this.tags == null) {
            this.tags = new HashSet();
         }

         this.tags.add(var1);
      }
   }

   protected void removeTag(String var1) {
      if (var1 != null && !var1.isEmpty()) {
         if (this.tags != null) {
            if (this.tags.remove(var1) && this.tags.isEmpty()) {
               this.tags = null;
            }
         }
      }
   }

   @Override
   public final Set getTags() {
      return this.tags == null ? Collections.emptySet() : Collections.unmodifiableSet(this.tags);
   }

   protected void setPriority(double var1) {
      this.priority = var1;
   }

   @Override
   public final double getPriority() {
      return this.priority;
   }

   public void setEnabled(boolean var1) {
      this.enabled = var1;
   }

   @Override
   public final boolean isEnabled() {
      return this.enabled;
   }

   @Override
   public final int perform(IDMethodContext var1) {
      if (var1 != null) {
         this.assignLocalFields(var1);
      }

      if (var1 != null) {
         var1.pushWorkingOptimizer(this);
      }

      int var2;
      try {
         var2 = this.perform();
      } finally {
         if (var1 != null) {
            var1.popWorkingOptimizer();
         }
      }

      return var2;
   }

   public abstract int perform();

   @Override
   public int performOnCollection(List var1, Map var2) {
      throw new RuntimeException();
   }

   public void assignLocalFields(IDMethodContext var1) {
      this.ctx = var1;
      if (this.ctx == null) {
         this.cfg = null;
         this.g = null;
         this.dex = null;
         this.decomp = null;
         this.tf = null;
         this.of = null;
      } else {
         this.cfg = this.ctx.getCfg();
         this.g = this.ctx.getGlobalContext();
         this.dex = this.g.getDex();
         this.decomp = this.g.getDecompiler();
         this.tf = this.g.getTypeFactory();
         this.of = this.g.getOperatorFactory();
      }
   }

   public void resetLocalFields() {
      this.assignLocalFields(null);
   }

   protected IDFA analyzeChains(boolean var1) {
      this.dfa = this.cfg.doDataFlowAnalysis(var1);
      return this.dfa;
   }

   protected IDFA analyzeChains() {
      return this.analyzeChains(false);
   }

   protected void invalidateChains() {
      this.cfg.invalidateDataFlowAnalysis();
      this.dfa = null;
   }

   protected int cleanGraph() {
      return this.cleanGraph(true, true);
   }

   protected int cleanGraph(boolean var1, boolean var2) {
      int var3 = 0;
      if (var1) {
         var3 += DUtil.removeUnreachableBlocks(this.ctx);
      }

      if (var2) {
         var3 += DUtil.simplifyJCondsAndSwitches(this.ctx);
      }

      return var3;
   }
}
