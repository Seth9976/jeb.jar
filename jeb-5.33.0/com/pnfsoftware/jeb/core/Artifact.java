package com.pnfsoftware.jeb.core;

import com.pnfsoftware.jeb.core.events.J;
import com.pnfsoftware.jeb.core.events.JebEvent;
import com.pnfsoftware.jeb.core.events.JebEventSource;
import com.pnfsoftware.jeb.core.input.BytesInput;
import com.pnfsoftware.jeb.core.input.IInput;
import com.pnfsoftware.jeb.util.format.Strings;
import com.pnfsoftware.jeb.util.logging.GlobalLog;
import com.pnfsoftware.jeb.util.logging.ILogger;
import com.pnfsoftware.jeb.util.serialization.annotations.Ser;
import com.pnfsoftware.jeb.util.serialization.annotations.SerId;

@Ser
public class Artifact extends JebEventSource implements IArtifact {
   private static final ILogger logger = GlobalLog.getLogger(Artifact.class);
   public static final Artifact EMPTY = new Artifact("emptyArtifact", new BytesInput(new byte[0]));
   @SerId(1)
   private long creationTimestamp = System.currentTimeMillis();
   @SerId(2)
   private String name;
   @SerId(3)
   private IInput input;
   @SerId(4)
   private String notes;

   public Artifact(String var1, IInput var2) {
      this.name = var1;
      this.input = var2;
      this.notes = "";
   }

   @Override
   public IUnitCreator getParent() {
      return null;
   }

   @Override
   public IInput getInput() {
      return this.input;
   }

   public void setInput(IInput var1) {
      this.input = var1;
   }

   @Override
   public String getName() {
      return this.name;
   }

   @Override
   public void setName(String var1) {
      if (var1 != null && !var1.equals(this.name)) {
         this.name = var1;
         this.notifyListeners(new JebEvent(J.ArtifactPropertyChanged));
      }
   }

   @Override
   public String getNotes() {
      return this.notes;
   }

   @Override
   public void setNotes(String var1) {
      if (var1 != null && !var1.equals(this.notes)) {
         this.notes = var1;
         this.notifyListeners(new JebEvent(J.ArtifactPropertyChanged));
      }
   }

   @Override
   public long getCreationTimestamp() {
      return this.creationTimestamp;
   }

   @Override
   public String toString() {
      return Strings.ff("Artifact:{%s}", this.getName());
   }
}
