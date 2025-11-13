package com.pnfsoftware.jeb.client.api;

import com.pnfsoftware.jeb.core.output.AddressConversionPrecision;
import com.pnfsoftware.jeb.core.output.IItem;
import com.pnfsoftware.jeb.core.units.IUnit;

public interface IUnitFragment {
   IUnit getUnit();

   boolean isActiveItem(IItem var1);

   IItem getActiveItem();

   String getActiveItemAsText();

   String getActiveAddress();

   String getActiveAddress(AddressConversionPrecision var1);

   boolean setActiveAddress(String var1);
}
