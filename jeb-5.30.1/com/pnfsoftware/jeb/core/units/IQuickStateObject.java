package com.pnfsoftware.jeb.core.units;

import com.pnfsoftware.jeb.util.serialization.annotations.Ser;

@Ser
public interface IQuickStateObject {
   boolean isTargetUnit(IUnit var1);

   boolean reload(IUnit var1);
}
