package com.pnfsoftware.jeb.core.properties.impl;

import com.pnfsoftware.jeb.core.properties.IPropertyDefinition;
import com.pnfsoftware.jeb.core.properties.IPropertyType;
import com.pnfsoftware.jeb.util.format.Strings;

public class PropertyDefinition implements IPropertyDefinition {
   private PropertyDefinitionManager manager;
   private String name;
   private String description;
   private IPropertyType type;
   private int flags;

   PropertyDefinition(PropertyDefinitionManager var1, String var2, String var3, IPropertyType var4, int var5) {
      this.manager = var1;
      this.name = var2;
      this.description = var3;
      this.type = var4;
      this.flags = var5;
   }

   public PropertyDefinitionManager getManager() {
      return this.manager;
   }

   @Override
   public String getName() {
      return this.name;
   }

   @Override
   public String getDescription() {
      return Strings.safe(this.description);
   }

   @Override
   public IPropertyType getType() {
      return this.type;
   }

   @Override
   public int getFlags() {
      return this.flags;
   }

   @Override
   public String toString() {
      return Strings.ff("%s(%s)", this.name, this.type);
   }
}
