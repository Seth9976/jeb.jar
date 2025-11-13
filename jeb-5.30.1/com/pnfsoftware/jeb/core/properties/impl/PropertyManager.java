package com.pnfsoftware.jeb.core.properties.impl;

import com.pnfsoftware.jeb.core.events.J;
import com.pnfsoftware.jeb.core.events.JebEvent;
import com.pnfsoftware.jeb.core.events.PropertyChangeNotification;
import com.pnfsoftware.jeb.core.properties.IConfiguration;
import com.pnfsoftware.jeb.core.properties.IPropertyDefinition;
import com.pnfsoftware.jeb.core.properties.IPropertyDefinitionManager;
import com.pnfsoftware.jeb.core.properties.IPropertyManager;
import com.pnfsoftware.jeb.util.events.EventSource;
import com.pnfsoftware.jeb.util.events.IEventListener;
import com.pnfsoftware.jeb.util.logging.GlobalLog;
import com.pnfsoftware.jeb.util.logging.ILogger;
import com.pnfsoftware.jeb.util.serialization.annotations.SerDisabled;
import java.util.concurrent.atomic.AtomicInteger;

@SerDisabled
public class PropertyManager extends EventSource implements IPropertyManager {
   private static final ILogger logger = GlobalLog.getLogger(PropertyManager.class);
   IPropertyDefinitionManager pdm;
   IConfiguration config;
   IPropertyManager master;
   IEventListener listener;
   private static AtomicInteger gid = new AtomicInteger();
   private int id = gid.getAndIncrement();
   private String ownerName = "PM";

   public PropertyManager(IPropertyDefinitionManager var1, IConfiguration var2, IPropertyManager var3) {
      this.pdm = var1;
      this.config = var2;
      this.master = var3;
      if (var3 != null) {
         this.listener = EventSource.relay(var3, this);
      }
   }

   public PropertyManager(IPropertyDefinitionManager var1, IConfiguration var2) {
      this(var1, var2, null);
   }

   public PropertyManager(IPropertyDefinitionManager var1, IPropertyManager var2) {
      this(var1, null, var2);
   }

   @Override
   public void dispose() {
      if (this.listener != null) {
         this.master.removeListener(this.listener);
      }
   }

   @Override
   public IPropertyDefinitionManager getPropertyDefinitionManager() {
      return this.pdm;
   }

   @Override
   public IConfiguration getConfiguration() {
      return this.config != null ? this.config : this.master.getConfiguration();
   }

   @Override
   public Object getValue(String var1) {
      return this.getValue(var1, 3, true, true);
   }

   @Override
   public Object getValue(String var1, int var2, boolean var3, boolean var4) {
      String var5 = this.genFqn(var1);
      if (var5 == null) {
         return null;
      } else {
         Object var6 = null;
         if (this.config != null) {
            var6 = this.config.getProperty(var5);
         }

         if (var6 == null && this.master != null && var2 >= 2) {
            var6 = this.master.getValue(var5, var2, false, var4);
         }

         IPropertyDefinition var7 = this.pdm == null ? null : PropertyUtil.getDefinition(this.pdm, var5);
         if (var7 != null) {
            if (var6 == null && var2 >= 3) {
               IPropertyDefinitionManager var8 = var7.getManager();
               if (var8 != null && (var8.getFlags() & 1) != 0) {
                  String var9 = PropertyUtil.levelUp(var5);
                  if (var9 != null) {
                     var6 = this.getValue(var9, var2, var3, var4);
                  }
               }
            }

            if (var6 == null && var3) {
               var6 = var7.getType().getDefault();
            }

            if (var6 != null && var4) {
               if (!var7.getType().validate(var6)) {
                  return null;
               }

               var6 = var7.getType().afterRead(var7, var6);
            }
         }

         return var6;
      }
   }

   @Override
   public boolean setValue(String var1, Object var2) {
      return this.setValue(var1, var2, true, null);
   }

   @Override
   public boolean setValue(String var1, Object var2, boolean var3, PropertyChangeObject var4) {
      String var5 = this.genFqn(var1);
      if (var5 == null) {
         return false;
      } else if (this.config != null) {
         IPropertyDefinition var6 = this.pdm == null ? null : PropertyUtil.getDefinition(this.pdm, var5);
         if (var2 == null) {
            if (var4 == null) {
               this.config.clearProperty(var5);
               this.notifyListeners(new JebEvent(J.PropertyChange, new PropertyChangeNotification(var5, null, var6)));
            } else {
               var4.add(this, var5, null, var6);
            }

            return true;
         } else {
            if (var6 != null) {
               if (var3 && !var6.getType().validate(var2)) {
                  Object[] var10000 = new Object[]{var6};
                  return false;
               }

               var2 = var6.getType().beforeWrite(var6, var2);
            }

            if (var4 == null) {
               this.config.setProperty(var5, var2);
               this.notifyListeners(new JebEvent(J.PropertyChange, new PropertyChangeNotification(var5, var2, var6)));
               if (this.config instanceof CommonsConfigurationWrapper) {
                  this.config.setProperty("", "");
               }
            } else {
               var4.add(this, var5, var2, var6);
            }

            return true;
         }
      } else {
         return this.master != null ? this.master.setValue(var5, var2, var3, var4) : false;
      }
   }

   private String genFqn(String var1) {
      if (var1.startsWith(".")) {
         return var1;
      } else {
         return this.pdm == null ? null : this.pdm.getNamespace() + "." + var1;
      }
   }

   @Override
   public boolean getBoolean(String var1) {
      return this.getBoolean(var1, false);
   }

   @Override
   public boolean getBoolean(String var1, boolean var2) {
      Object var3 = this.getValue(var1);
      if (var3 == null) {
         Object[] var10000 = new Object[]{var1};
         return var2;
      } else {
         return Boolean.parseBoolean(var3.toString());
      }
   }

   @Override
   public Boolean getBooleanUnsafe(String var1) {
      Object var2 = this.getValue(var1);
      if (var2 == null) {
         Object[] var10000 = new Object[]{var1};
         return null;
      } else {
         return Boolean.parseBoolean(var2.toString());
      }
   }

   @Override
   public boolean setBoolean(String var1, Boolean var2) {
      return this.setBoolean(var1, var2, null);
   }

   @Override
   public boolean setBoolean(String var1, Boolean var2, PropertyChangeObject var3) {
      return this.setValue(var1, var2, true, var3);
   }

   @Override
   public int getInteger(String var1) {
      return this.getInteger(var1, 0);
   }

   @Override
   public int getInteger(String var1, int var2) {
      Object var3 = this.getValue(var1);
      if (var3 == null) {
         Object[] var10000 = new Object[]{var1};
         return var2;
      } else {
         try {
            return Integer.parseInt(var3.toString());
         } catch (NumberFormatException var4) {
            return var2;
         }
      }
   }

   @Override
   public Integer getIntegerUnsafe(String var1) {
      Object var2 = this.getValue(var1);
      if (var2 == null) {
         Object[] var10000 = new Object[]{var1};
         return null;
      } else {
         return Integer.parseInt(var2.toString());
      }
   }

   @Override
   public boolean setInteger(String var1, Integer var2) {
      return this.setInteger(var1, var2, null);
   }

   @Override
   public boolean setInteger(String var1, Integer var2, PropertyChangeObject var3) {
      return this.setValue(var1, var2, true, var3);
   }

   @Override
   public String getString(String var1) {
      return this.getString(var1, "");
   }

   @Override
   public String getString(String var1, String var2) {
      Object var3 = this.getValue(var1);
      if (var3 == null) {
         Object[] var10000 = new Object[]{var1};
         return var2;
      } else {
         return var3.toString();
      }
   }

   @Override
   public String getStringUnsafe(String var1) {
      Object var2 = this.getValue(var1);
      if (var2 == null) {
         Object[] var10000 = new Object[]{var1};
         return null;
      } else {
         return var2.toString();
      }
   }

   @Override
   public boolean setString(String var1, String var2) {
      return this.setValue(var1, var2, true, null);
   }

   @Override
   public boolean setString(String var1, String var2, PropertyChangeObject var3) {
      return this.setValue(var1, var2, true, var3);
   }

   public void setOwnerName(String var1) {
      this.ownerName = var1;
   }

   public String getOwnerName() {
      return this.ownerName;
   }

   @Override
   public String toString() {
      String var1 = "PM_" + this.id;
      if (this.master != null) {
         for (IPropertyManager var2 = this.master; var2 instanceof PropertyManager; var2 = ((PropertyManager)var2).master) {
            var1 = var1 + ">" + ((PropertyManager)var2).id;
         }
      }

      if (this.pdm != null) {
         var1 = var1 + "|PDM:" + this.pdm.getRegion();
      }

      if (this.ownerName != null) {
         var1 = var1 + "|" + this.ownerName;
      }

      return var1;
   }
}
