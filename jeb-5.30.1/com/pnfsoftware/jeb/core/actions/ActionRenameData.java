package com.pnfsoftware.jeb.core.actions;

public class ActionRenameData extends ActionData {
   public static final int ERRCODE_NAMING_CONFLICT = 1;
   private String originalName;
   private String currentName;
   private String newName;
   private boolean bypassNameConflictChecksIfPossible;

   @Override
   public void reset() {
      super.reset();
      this.newName = null;
      this.bypassNameConflictChecksIfPossible = false;
   }

   @Override
   public boolean canReuseObject() {
      return true;
   }

   public String getOriginalName() {
      return this.originalName;
   }

   public void setOriginalName(String var1) {
      this.originalName = var1;
   }

   public String getCurrentName() {
      return this.currentName;
   }

   public void setCurrentName(String var1) {
      this.currentName = var1;
   }

   public String getNewName() {
      return this.newName;
   }

   public void setNewName(String var1) {
      this.newName = var1;
   }

   public void setBypassNameChecks(boolean var1) {
      this.bypassNameConflictChecksIfPossible = var1;
   }

   public boolean isBypassNameChecks() {
      return this.bypassNameConflictChecksIfPossible;
   }
}
