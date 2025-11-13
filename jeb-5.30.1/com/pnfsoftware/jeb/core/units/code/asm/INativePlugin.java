package com.pnfsoftware.jeb.core.units.code.asm;

import com.pnfsoftware.jeb.core.properties.IPropertyDefinitionManager;
import com.pnfsoftware.jeb.core.units.IUnitPlugin;

public interface INativePlugin extends IUnitPlugin {
   void setupCustomProperties(IPropertyDefinitionManager var1);
}
