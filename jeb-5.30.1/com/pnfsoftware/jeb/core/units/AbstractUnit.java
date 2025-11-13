package com.pnfsoftware.jeb.core.units;

import com.pnfsoftware.jeb.client.S;
import com.pnfsoftware.jeb.core.IRuntimeProject;
import com.pnfsoftware.jeb.core.IUnitCreator;
import com.pnfsoftware.jeb.core.IUserDataSupport;
import com.pnfsoftware.jeb.core.UserDataSupport;
import com.pnfsoftware.jeb.core.events.J;
import com.pnfsoftware.jeb.core.events.JebEvent;
import com.pnfsoftware.jeb.core.events.JebEventSource;
import com.pnfsoftware.jeb.core.exceptions.JebRuntimeException;
import com.pnfsoftware.jeb.core.output.IUnitFormatter;
import com.pnfsoftware.jeb.core.output.UnitFormatterAdapter;
import com.pnfsoftware.jeb.core.properties.IPropertyDefinitionManager;
import com.pnfsoftware.jeb.core.properties.IPropertyManager;
import com.pnfsoftware.jeb.core.properties.impl.PropertyManager;
import com.pnfsoftware.jeb.util.base.Assert;
import com.pnfsoftware.jeb.util.concurrent.ACLock;
import com.pnfsoftware.jeb.util.events.IEventListener;
import com.pnfsoftware.jeb.util.format.Strings;
import com.pnfsoftware.jeb.util.logging.GlobalLog;
import com.pnfsoftware.jeb.util.logging.ILogger;
import com.pnfsoftware.jeb.util.serialization.annotations.Ser;
import com.pnfsoftware.jeb.util.serialization.annotations.SerCustomInitPostGraph;
import com.pnfsoftware.jeb.util.serialization.annotations.SerId;
import com.pnfsoftware.jeb.util.serialization.annotations.SerTransient;
import com.pnfsoftware.jebglobal.SG;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Random;

@Ser
public abstract class AbstractUnit extends JebEventSource implements IUnit {
   private static final ILogger logger = GlobalLog.getLogger(AbstractUnit.class);
   private static Random prng = new Random();
   @SerId(1)
   private String formatType;
   @SerId(2)
   private long creationTimestamp;
   @SerId(3)
   private String name;
   @SerId(4)
   private String notes;
   @SerId(5)
   private boolean processed;
   @SerId(6)
   private String status;
   @SerId(7)
   private List persistedChildren = new ArrayList();
   @SerId(9)
   private List notifications = new ArrayList();
   @SerId(10)
   private IUnitFormatter formatter = new UnitFormatterAdapter();
   @SerId(11)
   private IUnitNotificationManager notificationManager = new UnitNotificationManager();
   @SerId(12)
   private String realName;
   @SerId(13)
   private long uid;
   @SerId(14)
   private IUserDataSupport userData = new UserDataSupport();
   @SerTransient
   private List transientChildren = new ArrayList();
   @SerTransient
   private IPropertyDefinitionManager pdm;
   @SerTransient
   private IPropertyManager pm;
   @SerTransient
   private IEventListener pmListener;
   @SerTransient
   private IUnitCreator parent;
   @SerTransient
   private IUnitProcessor unitProcessor;
   @SerTransient
   private List contributions;
   @SerTransient
   private List interpreters;
   @SerTransient
   private boolean disposed;
   @SerTransient
   private boolean deserialized;
   @SerTransient
   private List placeholderPersistedChildren;

   private static long genUid() {
      long var0;
      do {
         var0 = prng.nextLong();
      } while (var0 <= 0L);

      return var0;
   }

   @SerCustomInitPostGraph
   private void init() {
      if (this.uid == 0L) {
         this.uid = genUid();
      }

      if (this.userData == null) {
         this.userData = new UserDataSupport();
      }

      this.transientChildren = new ArrayList();
      this.deserialized = true;
   }

   public AbstractUnit(String var1, String var2, IUnit var3) {
      this(var1, var2, var3.getUnitProcessor(), var3, var3.getPropertyDefinitionManager());
   }

   public AbstractUnit(String var1, String var2, IUnitProcessor var3, IUnitCreator var4, IPropertyDefinitionManager var5) {
      super(var4);
      this.uid = genUid();
      this.parent = var4;
      if (var1 != null && var1.length() != 0) {
         this.formatType = var1;
         if (var2 != null && var2.length() != 0) {
            this.name = var2;
            this.creationTimestamp = System.currentTimeMillis();
            this.notes = "";
            this.unitProcessor = var3;
            this.initializePropertyObjects(var4, var3, var5);
         } else {
            throw new IllegalArgumentException("A unit must have a non-empty name");
         }
      } else {
         throw new IllegalArgumentException("A unit must have a non-empty format type");
      }
   }

   @Override
   public void initializePropertyObjects(IUnitCreator var1, IUnitProcessor var2, IPropertyDefinitionManager var3) {
      IPropertyManager var4 = var1 instanceof IUnit ? ((IUnit)var1).getPropertyManager() : var2.getPropertyManager();
      if (var4 == null) {
         throw new JebRuntimeException(Strings.f("No parent property manager was found for %s", this));
      } else {
         this.pdm = var3;
         this.pm = new PropertyManager(var3, var4);
         this.getPropertyManager().addListener(this.pmListener = new AbstractUnit$1(this));
      }
   }

   @Override
   public void postDeserialization(IRuntimeProject var1) {
   }

   public void onPropertyChange(String var1) {
   }

   @Override
   public IPropertyDefinitionManager getPropertyDefinitionManager() {
      if (this.isDisposed()) {
         throw new IllegalStateException(Strings.f("%s is disposed", this));
      } else {
         return this.pdm;
      }
   }

   @Override
   public IPropertyManager getPropertyManager() {
      if (this.isDisposed()) {
         throw new IllegalStateException(Strings.f("%s is disposed", this));
      } else {
         return this.pm;
      }
   }

   @Override
   public long getUid() {
      return this.uid;
   }

   @Override
   public String getFormatType() {
      return this.formatType;
   }

   @Override
   public byte[] getIconData() {
      return null;
   }

   @Override
   public void setUnitProcessor(IUnitProcessor var1) {
      this.unitProcessor = var1;
   }

   @Override
   public IUnitProcessor getUnitProcessor() {
      return this.unitProcessor;
   }

   @Override
   public long getCreationTimestamp() {
      return this.creationTimestamp;
   }

   @Override
   public String getName() {
      return this.name;
   }

   @Override
   public void setName(String var1) {
      try (ACLock var2 = this.getLock().a()) {
         if (var1 != null && var1.length() != 0 && !var1.equals(this.name)) {
            this.name = var1;
            this.notifyListeners(new JebEvent(J.UnitPropertyChanged));
            return;
         }
      }
   }

   @Override
   public String getRealName() {
      return this.realName;
   }

   @Override
   public void setRealName(String var1) {
      if (!Strings.equals(var1, this.realName)) {
         this.realName = var1;
         this.notifyListeners(new JebEvent(J.UnitPropertyChanged));
      }
   }

   @Override
   public String getNotes() {
      return this.notes;
   }

   @Override
   public void setNotes(String var1) {
      if (var1 == null) {
         var1 = "";
      }

      if (!var1.equals(this.notes)) {
         this.notes = var1;
         this.notifyListeners(new JebEvent(J.UnitPropertyChanged));
      }
   }

   @Override
   public void setParent(IUnitCreator var1) {
      this.parent = var1;
   }

   @Override
   public IUnitCreator getParent() {
      return this.parent;
   }

   @Override
   public String getStatus() {
      return this.status;
   }

   public final void setStatus(String var1) {
      this.setStatus(var1, true);
   }

   public void setStatus(String var1, boolean var2) {
      this.status = var1;
      if (var2) {
         this.notifyListeners(new JebEvent(J.UnitStatusChanged));
      }
   }

   @Override
   public IUnitLock getLock() {
      return PassthroughUnitLock.getInstance();
   }

   @Override
   public boolean isProcessed() {
      return this.processed;
   }

   @Override
   public boolean isStale() {
      return false;
   }

   public final void setProcessed(boolean var1) {
      this.setProcessed(var1, true);
   }

   public void setProcessed(boolean var1, boolean var2) {
      this.processed = var1;
      if (var2 && var1) {
         this.notifyListeners(new JebEvent(J.UnitProcessed));
      }
   }

   @Override
   public boolean process() {
      if (this.isProcessed()) {
         return true;
      } else {
         boolean var1;
         try (ACLock var2 = this.getLock().a()) {
            var1 = this.processInternal();
            if (var1) {
               if (this.getStatus() != null) {
                  this.setStatus(null, false);
               }

               this.setProcessed(true);
            } else {
               if (this.getStatus() == null) {
                  this.setStatus(S.L("Processing failed"), false);
               }

               this.setProcessed(false, false);
            }
         }

         return var1;
      }
   }

   protected boolean processInternal() {
      throw new JebRuntimeException("Override processInternal() [RECOMMENDED] or process() [FOR FINER CONTROL]");
   }

   @Override
   public String getDescription() {
      StringBuilder var1 = new StringBuilder();
      Strings.ff(var1, "%s (%s)\n", this.getName(), this.getFormatType());
      return var1.toString();
   }

   @Override
   public boolean canBePersisted() {
      return true;
   }

   public boolean hasChildren() {
      return !this.getChildren().isEmpty();
   }

   @Override
   public List getChildren() {
      if (this.persistedChildren == null) {
         return new ArrayList();
      } else {
         ArrayList var1 = new ArrayList(this.persistedChildren);
         var1.addAll(this.transientChildren);
         return Collections.unmodifiableList(var1);
      }
   }

   @Override
   public void addChild(IUnit var1) {
      this.addChild(var1, true);
   }

   @Override
   public void addChild(IUnit var1, boolean var2) {
      this.addChild(var1, var2, true);
   }

   protected void addChild(IUnit var1, boolean var2, boolean var3) {
      if (var1 == null) {
         throw new IllegalArgumentException("The child unit is null");
      } else if (this.isDisposed()) {
         throw new IllegalStateException(Strings.f("%s is disposed", this));
      } else {
         List var4 = var2 && var1.canBePersisted() ? this.persistedChildren : this.transientChildren;
         if (!var4.contains(var1)) {
            if (var1.getParent() == this || var1.getParent() instanceof SG && ((SG)var1.getParent()).getWrap() == this) {
               var4.add(var1);
               if (var3) {
                  this.notifyListeners(new JebEvent(J.UnitCreated, var1));
               }
            } else {
               throw new IllegalArgumentException("Parent mismatch for the added unit");
            }
         }
      }
   }

   @Override
   public boolean isTransientChild(IUnit var1) {
      return this.transientChildren.contains(var1);
   }

   public synchronized void internalSwapChildren() {
      if (this.placeholderPersistedChildren == null) {
         this.placeholderPersistedChildren = new ArrayList(this.persistedChildren);
         this.persistedChildren.clear();
      } else {
         Assert.a(this.persistedChildren.isEmpty());
         this.persistedChildren.addAll(this.placeholderPersistedChildren);
         this.placeholderPersistedChildren = null;
      }
   }

   @Override
   public void dispose() {
      if (!this.disposed) {
         this.getFormatter().discardTransientPresentations();
         if (this.pmListener != null) {
            this.getPropertyManager().removeListener(this.pmListener);
            this.pmListener = null;
         }

         if (this.persistedChildren != null) {
            while (!this.persistedChildren.isEmpty()) {
               IUnit var1 = (IUnit)this.persistedChildren.get(0);
               this.removeChild(var1);
            }
         }

         if (this.transientChildren != null) {
            while (!this.transientChildren.isEmpty()) {
               IUnit var2 = (IUnit)this.transientChildren.get(0);
               this.removeChild(var2);
            }
         }

         this.persistedChildren = null;
         this.transientChildren = null;
         this.parent = null;
         this.pdm = null;
         this.pm = null;
         this.formatter = null;
         this.notifications = null;
         this.notificationManager = null;
         this.unitProcessor = null;
         this.contributions = null;
         this.interpreters = null;
         this.userData = null;
         this.disposed = true;
         this.notifyListeners(new JebEvent(J.UnitDisposed, this));
      }
   }

   @Override
   public final boolean isDisposed() {
      return this.disposed;
   }

   public boolean isDeserialized() {
      return this.deserialized;
   }

   @Override
   public final void removeChild(IUnit var1) {
      this.removeChild(var1, true);
   }

   protected void removeChild(IUnit var1, boolean var2) {
      if (this.isDisposed()) {
         throw new IllegalStateException(Strings.f("%s is disposed", this));
      } else {
         List var3;
         if (this.persistedChildren.contains(var1)) {
            var3 = this.persistedChildren;
         } else {
            if (!this.transientChildren.contains(var1)) {
               return;
            }

            var3 = this.transientChildren;
         }

         var3.remove(var1);
         var1.dispose();
         if (var2) {
            this.notifyListeners(new JebEvent(J.UnitDestroyed, var1));
         }
      }
   }

   public boolean setChild(IUnit var1, IUnit var2) {
      return this.setChild(var1, var2, true);
   }

   protected boolean setChild(IUnit var1, IUnit var2, boolean var3) {
      if (this.isDisposed()) {
         throw new IllegalStateException(Strings.f("%s is disposed", this));
      } else {
         List var4;
         if (this.persistedChildren.contains(var1)) {
            var4 = this.persistedChildren;
         } else {
            if (!this.transientChildren.contains(var1)) {
               return false;
            }

            var4 = this.transientChildren;
         }

         if (var2 == null) {
            throw new IllegalArgumentException("The child unit is null");
         } else if (var4 == this.persistedChildren && !var2.canBePersisted()) {
            return false;
         } else if (var2.getParent() == this || var2.getParent() instanceof SG && ((SG)var2.getParent()).getWrap() == this) {
            int var5 = var4.indexOf(var1);
            var4.set(var5, var2);
            var1.dispose();
            if (var3) {
               this.notifyListeners(new JebEvent(J.UnitDestroyed, var1));
               this.notifyListeners(new JebEvent(J.UnitCreated, var2));
            }

            return true;
         } else {
            throw new IllegalArgumentException("Parent mismatch for the added unit");
         }
      }
   }

   @Override
   public IUnitFormatter getFormatter() {
      return this.formatter;
   }

   @Override
   public IUnitNotificationManager getNotificationManager() {
      if (this.notificationManager == null) {
         this.notificationManager = new UnitNotificationManager();
         if (this.notifications != null) {
            this.notificationManager.addNotifications(this.notifications);
            this.notifications = null;
         }
      }

      return this.notificationManager;
   }

   public final void addNotification(IUnitNotification var1) {
      this.getNotificationManager().addNotification(var1);
   }

   @Override
   public List getContributions() {
      if (this.contributions == null) {
         this.contributions = new ArrayList();
      }

      return this.contributions;
   }

   @Override
   public List getInterpreters() {
      if (this.interpreters == null) {
         this.interpreters = new ArrayList();
      }

      return this.interpreters;
   }

   @Override
   public IQuickStateObject generateQuickState() {
      return null;
   }

   @Override
   public String toString() {
      return Strings.ff("Unit:name={%s},type={%s}", this.getName(), this.getFormatType());
   }

   public void logTrace(String var1, Object... var2) {
      UnitUtil.logTrace(this, null, logger, var1, var2);
   }

   public void logInfo(boolean var1, String var2, Object... var3) {
      UnitUtil.logInfo(this, null, var1, logger, var2, var3);
   }

   public void logWarn(boolean var1, String var2, Object... var3) {
      UnitUtil.logWarn(this, null, var1, logger, var2, var3);
   }

   public void logError(boolean var1, String var2, Object... var3) {
      UnitUtil.logError(this, null, var1, logger, var2, var3);
   }

   public void logException(Exception var1) {
      UnitUtil.logException(this, logger, var1);
   }

   @Override
   public void setData(Object var1, Object var2, boolean var3) {
      this.userData.setData(var1, var2, var3);
   }

   @Override
   public Object getData(Object var1) {
      return this.userData.getData(var1);
   }

   @Override
   public void clearAllData(Object var1) {
      this.userData.clearAllData(var1);
   }

   @Override
   public Map getAllData() {
      return this.userData.getAllData();
   }
}
