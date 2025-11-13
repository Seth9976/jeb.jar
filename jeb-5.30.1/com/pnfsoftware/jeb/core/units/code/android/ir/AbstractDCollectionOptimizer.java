package com.pnfsoftware.jeb.core.units.code.android.ir;

import com.pnfsoftware.jeb.core.AbstractPlugin;
import com.pnfsoftware.jeb.core.EditablePluginInformation;
import com.pnfsoftware.jeb.core.units.code.android.IDexDecompilerUnit;
import com.pnfsoftware.jeb.core.units.code.android.IDexUnit;
import com.pnfsoftware.jeb.core.units.code.java.IJavaOperatorFactory;
import com.pnfsoftware.jeb.core.units.code.java.IJavaTypeFactory;
import com.pnfsoftware.jeb.util.collect.Maps;
import com.pnfsoftware.jeb.util.concurrent.Watchdog;
import com.pnfsoftware.jeb.util.logging.GlobalLog;
import com.pnfsoftware.jeb.util.logging.ILogger;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public abstract class AbstractDCollectionOptimizer extends AbstractPlugin implements IDOptimizer {
   public static final ILogger logger = GlobalLog.getLogger(AbstractDCollectionOptimizer.class);
   public IJavaTypeFactory tf;
   public IJavaOperatorFactory of;
   public IDGlobalContext g;
   public IDexUnit dex;
   public IDexDecompilerUnit decomp;
   public List ctxlist;
   private EditablePluginInformation info = new EditablePluginInformation();
   private DOptimizerType type;
   private Set tags;
   private double priority = 0.0;
   private boolean enabled = true;
   Map pmcntmap = new HashMap();

   public AbstractDCollectionOptimizer() {
      this(null);
   }

   public AbstractDCollectionOptimizer(DOptimizerType var1) {
      this(var1, null);
   }

   public AbstractDCollectionOptimizer(DOptimizerType var1, String var2) {
      this.setType(var1);
      this.setName(var2);
   }

   protected void checkInterrupted() {
      Watchdog.verify(null);
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
   public final boolean isCollectionOptimizer() {
      return true;
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
   public final int performOnCollection(List var1, Map var2) {
      if (var1 != null) {
         this.assignLocalFields(var1);
      }

      this.pmcntmap = var2;
      if (var1 != null) {
         var1.forEach(var1x -> var1x.pushWorkingOptimizer(this));
      }

      int var3;
      try {
         var3 = this.performOnCollection();
      } finally {
         var1.forEach(var0 -> var0.popWorkingOptimizer());
      }

      return var3;
   }

   @Override
   public int perform(IDMethodContext var1) {
      throw new RuntimeException();
   }

   protected void recordMethodOptimization(IDMethodContext var1, int var2) {
      if (this.pmcntmap != null && var1 != null && var2 > 0) {
         this.pmcntmap.compute(var1, (var1x, var2x) -> var2x == null ? var2 : var2x + var2);
      }
   }

   public abstract int performOnCollection();

   public void assignLocalFields(List var1) {
      this.ctxlist = var1;
      IDMethodContext var2 = this.ctxlist != null && !this.ctxlist.isEmpty() ? (IDMethodContext)this.ctxlist.get(0) : null;
      if (var2 == null) {
         this.g = null;
         this.dex = null;
         this.decomp = null;
         this.tf = null;
         this.of = null;
      } else {
         this.g = var2.getGlobalContext();
         this.dex = this.g.getDex();
         this.decomp = this.g.getDecompiler();
         this.tf = this.g.getTypeFactory();
         this.of = this.g.getOperatorFactory();
      }
   }

   public void resetLocalFields() {
      this.assignLocalFields(null);
   }

   public Map generatePerClassContextsMap() {
      HashMap var1 = new HashMap();

      for (IDMethodContext var3 : this.ctxlist) {
         String var4 = var3.getMethodSignature();
         String var5 = var4.substring(0, var4.indexOf("->"));
         Maps.putMulti(var1, var5, var3);
      }

      return var1;
   }
}
