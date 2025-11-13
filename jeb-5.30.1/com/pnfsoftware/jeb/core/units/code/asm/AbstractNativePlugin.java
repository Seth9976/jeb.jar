package com.pnfsoftware.jeb.core.units.code.asm;

import com.pnfsoftware.jeb.core.properties.IPropertyDefinitionManager;
import com.pnfsoftware.jeb.core.units.AbstractUnitPlugin;

public abstract class AbstractNativePlugin extends AbstractUnitPlugin implements INativePlugin {
   public AbstractNativePlugin(String var1, double var2) {
      super(var1, var2);
   }

   @Override
   public void setupCustomProperties(IPropertyDefinitionManager var1) {
   }
}
