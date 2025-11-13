package com.pnfsoftware.jeb.core.properties.impl;

import com.pnfsoftware.jeb.client.S;
import com.pnfsoftware.jeb.core.properties.IPropertyDefinition;
import com.pnfsoftware.jeb.core.properties.IPropertyDefinitionGroup;
import com.pnfsoftware.jeb.core.properties.IPropertyDefinitionManager;
import com.pnfsoftware.jeb.core.properties.IPropertyType;
import com.pnfsoftware.jeb.util.format.Strings;
import com.pnfsoftware.jeb.util.logging.GlobalLog;
import com.pnfsoftware.jeb.util.logging.ILogger;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class PropertyDefinitionManager implements IPropertyDefinitionManager {
   private static final ILogger logger = GlobalLog.getLogger(PropertyDefinitionManager.class);
   private int flags;
   private String description;
   private String region;
   private IPropertyDefinitionManager parent;
   private Map children = new LinkedHashMap();
   private Map definitions = new LinkedHashMap();
   private Map groups = new LinkedHashMap();

   public PropertyDefinitionManager(String var1, IPropertyDefinitionManager var2, String var3, int var4) {
      if (var1 != null && !isValidRegionName(var1)) {
         throw new IllegalArgumentException(var1 + " is an invalid region name: it must be a valid Java identifier name");
      } else {
         if (var1 == null) {
            var1 = "";
         }

         this.region = var1;
         this.description = var3;
         this.flags = var4;
         if (var2 != null) {
            this.attachToParent(var2);
         }

         this.groups.put("", new PropertyDefinitionManager.Group());
      }
   }

   public PropertyDefinitionManager(String var1, IPropertyDefinitionManager var2) {
      this(var1, var2, null, 0);
   }

   public PropertyDefinitionManager(String var1) {
      this(var1, null);
   }

   public PropertyDefinitionManager() {
      this(null, null);
   }

   @Override
   public void attachToParent(IPropertyDefinitionManager var1) {
      if (var1 == null || var1 == this) {
         throw new IllegalArgumentException("Invalid parent");
      } else if (this.parent != null) {
         throw new IllegalStateException("PDM already has a parent");
      } else if (this.region == null) {
         throw new IllegalStateException("A root PDM cannot have a parent");
      } else if (!var1.registerChild(this)) {
         throw new RuntimeException("Could not be registered as a child of the provided parent");
      } else {
         this.parent = var1;
      }
   }

   @Override
   public boolean registerChild(IPropertyDefinitionManager var1) {
      if (this.children.containsKey(var1.getRegion())) {
         return false;
      } else {
         this.children.put(var1.getRegion(), var1);
         return true;
      }
   }

   @Override
   public String getRegion() {
      return this.region;
   }

   @Override
   public boolean isRoot() {
      return this.region.length() == 0;
   }

   @Override
   public int getFlags() {
      return this.flags;
   }

   @Override
   public String getDescription() {
      return this.description;
   }

   @Override
   public IPropertyDefinitionManager getParent() {
      return this.parent;
   }

   @Override
   public String getNamespace() {
      if (this.parent == null) {
         return this.region.isEmpty() ? this.region : "." + this.region;
      } else {
         return this.parent.getNamespace() + "." + this.region;
      }
   }

   @Override
   public boolean hasChildren() {
      return !this.children.isEmpty();
   }

   @Override
   public Collection getChildren() {
      return Collections.unmodifiableCollection(this.children.values());
   }

   @Override
   public IPropertyDefinitionManager getChild(String var1) {
      return (IPropertyDefinitionManager)this.children.get(var1);
   }

   @Override
   public boolean hasDefinitions() {
      return !this.definitions.isEmpty();
   }

   @Override
   public IPropertyDefinition getDefinition(String var1) {
      return (IPropertyDefinition)this.definitions.get(var1);
   }

   @Override
   public Collection getDefinitions() {
      return Collections.unmodifiableCollection(this.definitions.values());
   }

   @Override
   public IPropertyDefinition addDefinition(String var1, IPropertyType var2, String var3) {
      return this.addDefinition(var1, var2, var3, 0, true, null);
   }

   @Override
   public IPropertyDefinition addDefinition(String var1, IPropertyType var2, String var3, int var4) {
      return this.addDefinition(var1, var2, var3, var4, true, null);
   }

   @Override
   public IPropertyDefinition addInternalDefinition(String var1, IPropertyType var2) {
      return this.addDefinition(var1, var2, null, 1, true, null);
   }

   @Override
   public IPropertyDefinition addInternalDefinition(String var1, IPropertyType var2, String var3) {
      return this.addDefinition(var1, var2, var3, 1, true, null);
   }

   IPropertyDefinition addDefinition(String var1, IPropertyType var2, String var3, int var4, boolean var5, String var6) {
      var1 = this.simplifyName(var1);
      IPropertyDefinition var7 = (IPropertyDefinition)this.definitions.get(var1);
      if (var7 != null && !var5) {
         throw new RuntimeException("A property definition with that name already exists.");
      } else if (!isValidPropertyName(var1)) {
         throw new IllegalArgumentException("Invalid property name");
      } else {
         if (var3 == null && var7 != null) {
            var3 = var7.getDescription();
         }

         PropertyDefinition var8 = new PropertyDefinition(this, var1, var3, var2, var4);
         this.definitions.put(var1, var8);
         if (var6 == null) {
            var6 = "";
         }

         ((PropertyDefinitionManager.Group)this.groups.get(var6)).defs.add(var8);
         return var8;
      }
   }

   private String simplifyName(String var1) {
      int var2 = var1.lastIndexOf(46);
      if (var2 < 0) {
         return var1;
      } else {
         String var3 = var1.substring(var2 + 1);
         String var4 = this.getNamespace() + "." + var3;
         if (!var1.equals(var4)) {
            String var5 = Strings.ff(S.L("Inconsistent fully-qualified name for property definition: %s (expected: %s)"), var1, var4);
            logger.warn(var5);
         }

         return var3;
      }
   }

   @Override
   public void removeDefinition(String var1) {
      var1 = this.simplifyName(var1);
      this.definitions.remove(var1);

      for (IPropertyDefinitionGroup var3 : this.groups.values()) {
         var3.removeDefinition(var1);
      }
   }

   private static boolean isValidRegionName(String var0) {
      if (var0.length() <= 0) {
         return false;
      } else if (!Character.isJavaIdentifierStart(var0.charAt(0))) {
         return false;
      } else {
         for (int var1 = 1; var1 < var0.length(); var1++) {
            if (!Character.isJavaIdentifierPart(var0.charAt(var1))) {
               return false;
            }
         }

         return true;
      }
   }

   private static boolean isValidPropertyName(String var0) {
      if (var0.length() <= 0) {
         return false;
      } else if (!Character.isUpperCase(var0.charAt(0))) {
         return false;
      } else if (!Character.isJavaIdentifierStart(var0.charAt(0))) {
         return false;
      } else {
         for (int var1 = 1; var1 < var0.length(); var1++) {
            if (!Character.isJavaIdentifierPart(var0.charAt(var1))) {
               return false;
            }
         }

         return true;
      }
   }

   @Override
   public String toString() {
      return Strings.ff("PDM:%s:%d", Strings.safe2(this.region, "<root>"), this.children.size());
   }

   @Override
   public IPropertyDefinitionGroup addGroup(String var1) {
      if (this.groups.containsKey(var1)) {
         throw new IllegalArgumentException("Group already exists: " + var1);
      } else {
         PropertyDefinitionManager.Group var2 = new PropertyDefinitionManager.Group(var1);
         this.groups.put(var1, var2);
         return var2;
      }
   }

   @Override
   public Collection getGroups() {
      return Collections.unmodifiableCollection(this.groups.values());
   }

   @Override
   public IPropertyDefinitionGroup getGroup(String var1) {
      return (IPropertyDefinitionGroup)this.groups.get(var1);
   }

   @Override
   public boolean removeGroup(String var1) {
      if (Strings.isBlank(var1)) {
         return false;
      } else {
         IPropertyDefinitionGroup var2 = (IPropertyDefinitionGroup)this.groups.remove(var1);
         if (var2 == null) {
            return false;
         } else {
            PropertyDefinitionManager.Group var3 = (PropertyDefinitionManager.Group)this.groups.get("");

            for (IPropertyDefinition var5 : var2.getDefinitions()) {
               var3.defs.add(var5);
            }

            return true;
         }
      }
   }

   class Group implements IPropertyDefinitionGroup {
      String grpname;
      List defs = new ArrayList();

      Group() {
         this.grpname = "";
      }

      Group(String var2) {
         if (Strings.isBlank(var2)) {
            throw new IllegalArgumentException("Illegal group name: " + var2);
         } else {
            this.grpname = var2;
         }
      }

      @Override
      public String getName() {
         return this.grpname;
      }

      @Override
      public List getDefinitions() {
         return Collections.unmodifiableList(this.defs);
      }

      @Override
      public IPropertyDefinition addDefinition(String var1, IPropertyType var2, String var3, int var4) {
         return PropertyDefinitionManager.this.addDefinition(var1, var2, var3, var4, true, this.grpname);
      }

      @Override
      public void removeDefinition(String var1) {
         Iterator var2 = this.defs.iterator();

         while (var2.hasNext()) {
            if (Strings.equals(var1, ((IPropertyDefinition)var2.next()).getName())) {
               var2.remove();
            }
         }
      }
   }
}
