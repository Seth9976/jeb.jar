package com.pnfsoftware.jeb.core.properties;

import java.util.Collection;

public interface IPropertyDefinitionManager {
   String pdmGroupDefault = "";
   int FLAG_ALLOW_LOOK_UP = 1;
   int FLAG_MAY_ALPHASORT_CHILDREN = 16;
   int FLAG_SHOULD_NOT_ALPHASORT_CHILDREN = 32;

   String getNamespace();

   String getRegion();

   boolean isRoot();

   int getFlags();

   String getDescription();

   void attachToParent(IPropertyDefinitionManager var1);

   boolean registerChild(IPropertyDefinitionManager var1);

   IPropertyDefinitionManager getParent();

   boolean hasChildren();

   Collection getChildren();

   IPropertyDefinitionManager getChild(String var1);

   Collection getDefinitions();

   boolean hasDefinitions();

   IPropertyDefinition getDefinition(String var1);

   IPropertyDefinition addDefinition(String var1, IPropertyType var2, String var3);

   IPropertyDefinition addDefinition(String var1, IPropertyType var2, String var3, int var4);

   IPropertyDefinition addInternalDefinition(String var1, IPropertyType var2);

   IPropertyDefinition addInternalDefinition(String var1, IPropertyType var2, String var3);

   void removeDefinition(String var1);

   IPropertyDefinitionGroup addGroup(String var1);

   IPropertyDefinitionGroup getGroup(String var1);

   Collection getGroups();

   boolean removeGroup(String var1);
}
