package com.pnfsoftware.jeb.core.units;

import com.pnfsoftware.jeb.core.IArtifact;
import com.pnfsoftware.jeb.core.IRuntimeProject;
import com.pnfsoftware.jeb.core.IUnitCreator;
import com.pnfsoftware.jeb.core.IUserDataSupport;
import com.pnfsoftware.jeb.core.RuntimeProjectUtil;
import com.pnfsoftware.jeb.core.events.J;
import com.pnfsoftware.jeb.core.events.JebEvent;
import com.pnfsoftware.jeb.core.input.IInput;
import com.pnfsoftware.jeb.core.output.IUnitFormatter;
import com.pnfsoftware.jeb.core.properties.IPropertyDefinitionManager;
import com.pnfsoftware.jeb.core.properties.IPropertyManager;
import com.pnfsoftware.jeb.util.events.IEventSource;
import com.pnfsoftware.jeb.util.serialization.annotations.Ser;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

@Ser
public interface IUnit extends IUnitCreator, IUserDataSupport, IEventSource {
   @Override
   default IInput getInput() {
      return null;
   }

   default Collection getExtraInputs() {
      return Collections.emptyList();
   }

   default IRuntimeProject getParentProject() {
      return RuntimeProjectUtil.findProject(this);
   }

   default IArtifact getParentArtifact() {
      Object var1 = this;

      while (var1 instanceof IUnit) {
         var1 = ((IUnit)var1).getParent();
      }

      return !(var1 instanceof IArtifact) ? null : (IArtifact)var1;
   }

   IPropertyDefinitionManager getPropertyDefinitionManager();

   IPropertyManager getPropertyManager();

   long getUid();

   String getFormatType();

   byte[] getIconData();

   IUnitProcessor getUnitProcessor();

   long getCreationTimestamp();

   @Override
   String getName();

   void setName(String var1);

   String getRealName();

   void setRealName(String var1);

   String getNotes();

   void setNotes(String var1);

   @Override
   IUnitCreator getParent();

   boolean process();

   String getStatus();

   boolean isProcessed();

   boolean isStale();

   boolean canBePersisted();

   List getChildren();

   void addChild(IUnit var1);

   void addChild(IUnit var1, boolean var2);

   boolean isTransientChild(IUnit var1);

   void removeChild(IUnit var1);

   void dispose();

   boolean isDisposed();

   String getDescription();

   IUnitFormatter getFormatter();

   IUnitNotificationManager getNotificationManager();

   void setUnitProcessor(IUnitProcessor var1);

   void initializePropertyObjects(IUnitCreator var1, IUnitProcessor var2, IPropertyDefinitionManager var3);

   void postDeserialization(IRuntimeProject var1);

   void setParent(IUnitCreator var1);

   List getInterpreters();

   List getContributions();

   IQuickStateObject generateQuickState();

   IUnitLock getLock();

   default void notifyGenericChange() {
      this.notifyListeners(new JebEvent(J.UnitChange));
   }

   default boolean execprvfunc(int var1, Object var2) {
      return false;
   }
}
