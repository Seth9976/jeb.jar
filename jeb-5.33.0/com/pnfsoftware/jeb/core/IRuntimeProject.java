package com.pnfsoftware.jeb.core;

import com.pnfsoftware.jeb.core.properties.IPropertyDefinitionManager;
import com.pnfsoftware.jeb.core.properties.IPropertyManager;
import com.pnfsoftware.jeb.core.units.IUnit;
import com.pnfsoftware.jeb.core.units.IUnitProcessor;
import com.pnfsoftware.jeb.util.events.IEventSource;
import java.util.List;

public interface IRuntimeProject extends IUserDataSupport, IEventSource {
   String ArtifactProcessingDepth = "ArtifactProcessingDepth";
   String AlwaysProcessDuplicateInputs = "AlwaysProcessDuplicateInputs";
   String CompressPersistedProject = "CompressPersistedProject";
   String PersistenceStrategy = "PersistenceStrategy";
   String PersistArtifactFiles = "PersistArtifactFiles";

   IEnginesContext getEnginesContext();

   IPropertyDefinitionManager getPropertyDefinitionManager();

   IPropertyManager getPropertyManager();

   String getKey();

   String getPersistenceKey();

   int getPersistenceStrategy();

   void setPersistenceStrategy(int var1);

   boolean isReloaded();

   String getName();

   void setName(String var1);

   String getNotes();

   void setNotes(String var1);

   IUnitProcessor getProcessor();

   long getCreationTimestamp();

   long getRecordedTimestamp();

   long getUptime();

   void close();

   boolean isClosed();

   ILiveArtifact processArtifact(IArtifact var1);

   ILiveArtifact processArtifact(IArtifact var1, String var2, boolean var3, boolean var4);

   void finishArtifactProcessing(ILiveArtifact var1);

   boolean hasLiveArtifacts();

   List getLiveArtifacts();

   ILiveArtifact getLiveArtifact(int var1);

   int getArtifactCount();

   boolean destroyUnit(IUnit var1);

   BookmarkManager getBookmarkManager();

   List getInputRecords();

   List findUnits(Class var1);

   IUnit findUnit(Class var1);
}
