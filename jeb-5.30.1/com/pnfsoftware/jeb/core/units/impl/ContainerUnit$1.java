package com.pnfsoftware.jeb.core.units.impl;

import com.pnfsoftware.jeb.core.units.IUnit;
import java.util.Comparator;

class ContainerUnit$1 implements Comparator {
   public int compare(IUnit var1, IUnit var2) {
      if (var1 instanceof ContainerUnit && var2 instanceof ContainerUnit) {
         return var1.getName().compareTo(var2.getName());
      } else if (var1 instanceof ContainerUnit) {
         return -1;
      } else {
         return var2 instanceof ContainerUnit ? 1 : var1.getName().compareTo(var2.getName());
      }
   }
}
