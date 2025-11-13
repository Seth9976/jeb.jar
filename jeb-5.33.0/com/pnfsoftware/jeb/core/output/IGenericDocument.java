package com.pnfsoftware.jeb.core.output;

import com.pnfsoftware.jeb.core.properties.IPropertyManager;
import com.pnfsoftware.jeb.core.units.IUnit;
import com.pnfsoftware.jeb.util.serialization.annotations.Ser;

@Ser
public interface IGenericDocument {
   void dispose();

   IPropertyManager getPropertyManager();

   default IUnit getUnit() {
      return null;
   }
}
