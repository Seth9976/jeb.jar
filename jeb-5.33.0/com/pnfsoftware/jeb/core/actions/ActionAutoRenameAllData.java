package com.pnfsoftware.jeb.core.actions;

public class ActionAutoRenameAllData extends ActionData {
   private Integer defaultTargets;
   private int targets;
   private Integer defaultPolicy;
   private int policy;
   private String defaultFilter;
   private String filter;
   private int renamedCount;

   public void setDefaultTargets(int var1) {
      this.defaultTargets = var1;
   }

   public Integer getDefaultTargets() {
      return this.defaultTargets;
   }

   public void setTargets(int var1) {
      this.targets = var1;
   }

   public int getTargets() {
      return this.targets;
   }

   public void setDefaultPolicy(int var1) {
      this.defaultPolicy = var1;
   }

   public Integer getDefaultPolicy() {
      return this.defaultPolicy;
   }

   public void setPolicy(int var1) {
      this.policy = var1;
   }

   public int getPolicy() {
      return this.policy;
   }

   public void setDefaultFilter(String var1) {
      this.defaultFilter = var1;
   }

   public String getDefaultFilter() {
      return this.defaultFilter;
   }

   public void setFilter(String var1) {
      this.filter = var1;
   }

   public String getFilter() {
      return this.filter;
   }

   public void setRenamedCount(int var1) {
      this.renamedCount = var1;
   }

   public int getRenamedCount() {
      return this.renamedCount;
   }
}
