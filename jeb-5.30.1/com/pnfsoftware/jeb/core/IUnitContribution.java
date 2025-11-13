package com.pnfsoftware.jeb.core;

import com.pnfsoftware.jeb.core.units.IAddressableUnit;
import com.pnfsoftware.jeb.util.base.TypedContent;
import java.util.List;

public interface IUnitContribution extends IDynamicTargetPlugin {
   TypedContent getItemInformation(IAddressableUnit var1, long var2, String var4, List var5);

   TypedContent getLocationInformation(IAddressableUnit var1, String var2);
}
