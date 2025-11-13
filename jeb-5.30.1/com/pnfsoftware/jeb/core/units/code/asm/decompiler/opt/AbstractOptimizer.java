package com.pnfsoftware.jeb.core.units.code.asm.decompiler.opt;

import com.pnfsoftware.jeb.core.AbstractPlugin;
import com.pnfsoftware.jeb.core.EditablePluginInformation;
import com.pnfsoftware.jeb.util.logging.GlobalLog;
import com.pnfsoftware.jeb.util.logging.ILogger;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

public abstract class AbstractOptimizer extends AbstractPlugin implements IOptimizer {
   public static final ILogger logger = GlobalLog.getLogger(AbstractOptimizer.class);
   private EditablePluginInformation info = new EditablePluginInformation();
   private IMasterOptimizer mo;
   private OptimizerMode requiredModeThreshold = OptimizerMode.NORMAL;
   private OptimizerType type;
   private Set tags;
   private double priority = 0.0;
   private int preferredExecStage;

   public AbstractOptimizer() {
      this(null);
   }

   public AbstractOptimizer(IOptimizerTarget var1) {
      this.setType(null);
      this.setName(null);
   }

   protected void setName(String var1) {
      if (var1 == null) {
         var1 = this.getClass().getSimpleName();
         int var2 = var1.indexOf(36);
         if (var2 >= 0) {
            var1 = var1.substring(0, var2);
         }
      }

      this.info.setName(var1);
   }

   public String getName() {
      return this.info.getName();
   }

   public EditablePluginInformation getPluginInformation() {
      return this.info;
   }

   @Override
   public void setMasterOptimizer(IMasterOptimizer var1) {
      this.mo = var1;
   }

   @Override
   public IMasterOptimizer getMasterOptimizer() {
      return this.mo;
   }

   protected void setType(OptimizerType var1) {
      if (var1 == null) {
         var1 = OptimizerType.NORMAL;
      }

      this.type = var1;
   }

   @Override
   public OptimizerMode getRequiredModeThreshold() {
      return this.requiredModeThreshold;
   }

   protected void setRequiredModeThreshold(OptimizerMode var1) {
      this.requiredModeThreshold = var1;
   }

   @Override
   public OptimizerType getType() {
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
   public Set getTags() {
      return this.tags == null ? Collections.emptySet() : Collections.unmodifiableSet(this.tags);
   }

   protected void setPreferredExecutionStage(int var1) {
      this.preferredExecStage = var1;
   }

   @Override
   public int getPreferredExecutionStage() {
      return this.preferredExecStage;
   }

   protected void setPriority(double var1) {
      this.priority = var1;
   }

   @Override
   public final double getPriority() {
      return this.priority;
   }
}
