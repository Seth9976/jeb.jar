package com.pnfsoftware.jeb.core.units.code.java;

import com.pnfsoftware.jeb.core.AbstractPlugin;
import com.pnfsoftware.jeb.core.EditablePluginInformation;
import com.pnfsoftware.jeb.core.units.code.android.IDexDecompilerUnit;
import com.pnfsoftware.jeb.core.units.code.android.IDexUnit;
import com.pnfsoftware.jeb.util.logging.GlobalLog;
import com.pnfsoftware.jeb.util.logging.ILogger;

public abstract class AbstractJOptimizer extends AbstractPlugin implements IJOptimizer {
   public static final ILogger logger = GlobalLog.getLogger(AbstractJOptimizer.class);
   public IJavaClass c;
   public IJavaMethod m;
   public IJavaGlobalContext jctx;
   public IJavaTypeFactory tf;
   public IJavaOperatorFactory of;
   public IJavaConstantFactory cf;
   public IDexUnit dex;
   public IDexDecompilerUnit decomp;
   public DeferredRequestsCollector drcollector;
   private EditablePluginInformation info = new EditablePluginInformation();
   private JOptimizerType type;
   private double priority = 0.0;
   private boolean enabled = true;

   public AbstractJOptimizer() {
      this(null);
   }

   public AbstractJOptimizer(JOptimizerType var1) {
      this(var1, null);
   }

   public AbstractJOptimizer(JOptimizerType var1, String var2) {
      this.setType(var1);
      this.setName(var2);
   }

   public EditablePluginInformation getPluginInformation() {
      return this.info;
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

   @Override
   public String getName() {
      return this.info.getName();
   }

   protected void setType(JOptimizerType var1) {
      if (var1 == null) {
         var1 = JOptimizerType.NORMAL;
      }

      this.type = var1;
   }

   @Override
   public JOptimizerType getType() {
      return this.type;
   }

   protected void setPriority(double var1) {
      this.priority = var1;
   }

   @Override
   public double getPriority() {
      return this.priority;
   }

   public void setEnabled(boolean var1) {
      this.enabled = var1;
   }

   @Override
   public boolean isEnabled() {
      return this.enabled;
   }

   @Override
   public final int perform(IJavaDecompilableElement var1) {
      if (var1 instanceof IJavaMethod) {
         this.m = (IJavaMethod)var1;
      } else {
         if (!(var1 instanceof IJavaClass)) {
            throw new IllegalArgumentException("The provided AST element is neither a class nor a method");
         }

         this.c = (IJavaClass)var1;
      }

      int var2;
      try {
         this.jctx = var1.getGlobalContext();
         if (this.jctx == null) {
            throw new IllegalArgumentException("The provided AST element does not have an attached Java global context");
         }

         this.tf = this.jctx.getTypeFactory();
         this.of = this.jctx.getOperatorFactory();
         this.cf = this.jctx.getConstantFactory();
         this.decomp = this.jctx.getDecompiler();
         this.dex = this.decomp == null ? null : this.decomp.getCodeUnit();
         var2 = this.perform();
      } finally {
         this.m = null;
         this.c = null;
         this.jctx = null;
         this.tf = null;
         this.of = null;
         this.cf = null;
         this.decomp = null;
         this.dex = null;
      }

      return var2;
   }

   public abstract int perform();
}
