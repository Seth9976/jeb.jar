package com.pnfsoftware.jeb.core;

import com.pnfsoftware.jeb.core.input.IInput;
import com.pnfsoftware.jeb.util.events.IEventSource;
import com.pnfsoftware.jeb.util.serialization.annotations.Ser;

@Ser
public interface IArtifact extends IUnitCreator, IEventSource {
   default IRuntimeProject getParentProject() {
      return RuntimeProjectUtil.findProject(this);
   }

   long getCreationTimestamp();

   @Override
   String getName();

   void setName(String var1);

   String getNotes();

   void setNotes(String var1);

   @Override
   IUnitCreator getParent();

   @Override
   IInput getInput();
}
