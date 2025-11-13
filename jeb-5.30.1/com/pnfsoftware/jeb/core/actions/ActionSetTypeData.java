package com.pnfsoftware.jeb.core.actions;

public class ActionSetTypeData extends ActionData {
   private String currentType;
   private String newType;

   public String getCurrentType() {
      return this.currentType;
   }

   public void setCurrentType(String var1) {
      this.currentType = var1;
   }

   public String getNewType() {
      return this.newType;
   }

   public void setNewType(String var1) {
      this.newType = var1;
   }
}
