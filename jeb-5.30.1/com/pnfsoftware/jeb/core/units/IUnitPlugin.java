package com.pnfsoftware.jeb.core.units;

import com.pnfsoftware.jeb.core.IPlugin;
import com.pnfsoftware.jeb.core.properties.IPropertyDefinitionManager;
import com.pnfsoftware.jeb.util.serialization.ITypeIdProvider;

public interface IUnitPlugin extends IPlugin {
   void initialize(IPropertyDefinitionManager var1);

   IPropertyDefinitionManager getPropertyDefinitionManager();

   String getFormatType();

   double getPriority();

   ITypeIdProvider getTypeIdProvider();
}
