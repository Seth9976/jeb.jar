package com.pnfsoftware.jeb.core.properties;

import java.util.List;

public interface IPropertyDefinitionGroup {
   default boolean isDefaultGroup() {
      return this.getName().isEmpty();
   }

   String getName();

   List getDefinitions();

   default IPropertyDefinition addDefinition(String var1, IPropertyType var2) {
      return this.addDefinition(var1, var2, null, 0);
   }

   default IPropertyDefinition addDefinition(String var1, IPropertyType var2, String var3) {
      return this.addDefinition(var1, var2, var3, 0);
   }

   IPropertyDefinition addDefinition(String var1, IPropertyType var2, String var3, int var4);

   void removeDefinition(String var1);
}
