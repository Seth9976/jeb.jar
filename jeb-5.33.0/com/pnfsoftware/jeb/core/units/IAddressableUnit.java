package com.pnfsoftware.jeb.core.units;

import com.pnfsoftware.jeb.util.serialization.annotations.Ser;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.function.Predicate;

@Ser
public interface IAddressableUnit extends IUnit {
   Object getItemObject(long var1);

   boolean isValidAddress(String var1);

   default String getCanonicalAddress(String var1) {
      return var1;
   }

   String getAddressOfItem(long var1);

   List getRelatedItems(long var1);

   long getItemAtAddress(String var1);

   default Map getAddressLabels() {
      return Collections.emptyMap();
   }

   default String getAddressLabel(String var1) {
      return null;
   }

   default Collection getWellKnownAddresses(int var1, Predicate var2) {
      return Collections.emptyList();
   }
}
