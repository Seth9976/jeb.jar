package com.pnfsoftware.jeb.core.properties.impl;

import com.pnfsoftware.jeb.core.events.J;
import com.pnfsoftware.jeb.core.events.JebEvent;
import com.pnfsoftware.jeb.core.events.PropertyChangeNotification;
import com.pnfsoftware.jeb.core.properties.IConfiguration;
import com.pnfsoftware.jeb.core.properties.IPropertyDefinition;
import com.pnfsoftware.jeb.core.properties.IPropertyManager;
import com.pnfsoftware.jeb.util.collect.Maps;
import java.util.HashMap;
import java.util.IdentityHashMap;
import java.util.List;
import java.util.Map;

public class PropertyChangeObject {
   private Map entries = new HashMap();
   private Map map = new IdentityHashMap();

   public int size() {
      return this.entries.size();
   }

   public boolean isEmpty() {
      return this.entries.isEmpty();
   }

   public boolean has(String var1) {
      return this.entries.containsKey(var1);
   }

   public Object get(String var1) {
      return this.entries.get(var1);
   }

   public PropertyChangeObject.Entry add(IPropertyManager var1, String var2, Object var3, IPropertyDefinition var4) {
      if (var1 != null && var2 != null && var4 != null) {
         PropertyChangeObject.Entry var5 = new PropertyChangeObject.Entry();
         var5.fqname = var2;
         var5.value = var3;
         var5.pd = var4;
         if (this.entries.put(var2, var3) != null) {
            List var6 = (List)this.map.get(var1);
            int var7 = 0;

            for (PropertyChangeObject.Entry var9 : var6) {
               if (var9.fqname.equals(var2)) {
                  break;
               }

               var7++;
            }

            if (var7 < var6.size()) {
               var6.remove(var7);
            }
         }

         Maps.putMulti(this.map, var1, var5);
         return var5;
      } else {
         throw new IllegalArgumentException();
      }
   }

   public void commit() {
      this.commit(true);
   }

   public void commit(boolean var1) {
      for (IPropertyManager var3 : this.map.keySet()) {
         IConfiguration var4 = var3.getConfiguration();
         PropertyChangeNotification var5 = new PropertyChangeNotification();

         for (PropertyChangeObject.Entry var7 : (List)this.map.get(var3)) {
            if (var7.value == null) {
               var4.clearProperty(var7.fqname);
            } else {
               var4.setProperty(var7.fqname, var7.value.toString());
            }

            var5.add(var7.fqname, var7.value, var7.pd);
         }

         if (var4 instanceof CommonsConfigurationWrapper) {
            var4.setProperty("", "");
         }

         var3.notifyListeners(new JebEvent(J.PropertyChange, var5));
      }

      this.map.clear();
      this.entries.clear();
   }

   @Override
   public String toString() {
      StringBuilder var1 = new StringBuilder();

      for (IPropertyManager var3 : this.map.keySet()) {
         for (PropertyChangeObject.Entry var5 : (List)this.map.get(var3)) {
            var1.append(var5.fqname).append(",");
         }
      }

      return var1.toString();
   }

   public static class Entry {
      String fqname;
      Object value;
      IPropertyDefinition pd;
   }
}
