package com.pnfsoftware.jeb.core.properties.impl;

import com.pnfsoftware.jeb.core.properties.IPropertyDefinition;
import java.util.Comparator;

class PropertyUtil$2 implements Comparator {
   public int compare(IPropertyDefinition var1, IPropertyDefinition var2) {
      return var1.getName().compareToIgnoreCase(var2.getName());
   }
}
