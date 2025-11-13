package com.pnfsoftware.jeb.core.units;

import com.pnfsoftware.jeb.core.AbstractPlugin;
import com.pnfsoftware.jeb.core.properties.IPropertyDefinitionManager;
import com.pnfsoftware.jeb.core.properties.IPropertyManager;
import com.pnfsoftware.jeb.core.properties.impl.PropertyDefinitionManager;
import com.pnfsoftware.jeb.util.serialization.ITypeIdProvider;

public abstract class AbstractUnitPlugin extends AbstractPlugin implements IUnitPlugin {
   protected String type;
   protected double priority;
   protected IPropertyDefinitionManager pdm;
   protected IPropertyManager pm;

   public AbstractUnitPlugin(String var1, double var2) {
      if (var1 == null) {
         throw new IllegalArgumentException("A native code plugin must have a valid type");
      } else {
         this.type = var1;
         this.priority = var2;
      }
   }

   @Override
   public void initialize(IPropertyDefinitionManager var1) {
      this.pdm = new PropertyDefinitionManager(this.type, var1);
   }

   @Override
   public IPropertyDefinitionManager getPropertyDefinitionManager() {
      return this.pdm;
   }

   @Override
   public String getFormatType() {
      return this.type;
   }

   @Override
   public double getPriority() {
      return this.priority;
   }

   @Override
   public ITypeIdProvider getTypeIdProvider() {
      return null;
   }
}
