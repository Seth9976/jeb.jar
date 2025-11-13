package com.pnfsoftware.jeb.client.api;

import com.pnfsoftware.jeb.core.units.IUnit;
import java.util.List;

public interface IUnitView {
   String getLabel();

   void setLabel(String var1);

   void setFocus();

   IUnit getUnit();

   List getFragments();

   void setActiveFragment(IUnitFragment var1);

   IUnitFragment getActiveFragment();

   String getFragmentLabel(IUnitFragment var1);

   void setFragmentLabel(IUnitFragment var1, String var2);
}
