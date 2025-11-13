package com.pnfsoftware.jeb.core;

import com.pnfsoftware.jeb.core.units.IUnit;

public interface IDynamicTargetPlugin extends IPlugin {
   boolean isTarget(IUnit var1);

   void setPrimaryTarget(IUnit var1);

   IUnit getPrimaryTarget();
}
