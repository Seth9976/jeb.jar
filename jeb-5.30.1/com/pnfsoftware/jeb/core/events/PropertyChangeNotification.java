package com.pnfsoftware.jeb.core.events;

import com.pnfsoftware.jeb.core.properties.IPropertyDefinition;
import com.pnfsoftware.jeb.util.format.Strings;
import java.util.ArrayList;
import java.util.List;

public class PropertyChangeNotification {
   private List entries = new ArrayList();

   public PropertyChangeNotification() {
   }

   public PropertyChangeNotification(String var1, Object var2, IPropertyDefinition var3) {
      this.add(var1, var2, var3);
   }

   public void add(String var1, Object var2, IPropertyDefinition var3) {
      this.entries.add(new PropertyChangeNotification.Entry(var1, var2, var3));
   }

   public List entries() {
      return this.entries;
   }

   public boolean has(String... var1) {
      if (var1.length > 0) {
         for (PropertyChangeNotification.Entry var3 : this.entries) {
            for (String var7 : var1) {
               if (var3.getPropertyFullyQualifiedName().equals(var7)) {
                  return true;
               }
            }
         }
      }

      return false;
   }

   @Override
   public String toString() {
      return this.entries.toString();
   }

   public static class Entry {
      String propfqn;
      Object value;
      IPropertyDefinition pd;

      public Entry(String var1, Object var2, IPropertyDefinition var3) {
         if (var1 != null && var1.startsWith(".")) {
            this.propfqn = var1;
            this.value = var2;
            this.pd = var3;
         } else {
            throw new IllegalArgumentException("Invalid property FQN: " + var1);
         }
      }

      public String getPropertyFullyQualifiedName() {
         return this.propfqn;
      }

      public Object getUpdatedValue() {
         return this.value;
      }

      public IPropertyDefinition getPropertyDefinition() {
         return this.pd;
      }

      @Override
      public String toString() {
         return Strings.ff("%s->%s(def:%s)", this.propfqn, this.value, this.pd);
      }
   }
}
