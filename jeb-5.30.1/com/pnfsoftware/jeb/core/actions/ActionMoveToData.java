package com.pnfsoftware.jeb.core.actions;

public class ActionMoveToData extends ActionData {
   public static final int FLAG_SKIP_CHECKS = 1;
   public static final int FLAG_NEVER_ANONYMOUS = 2;
   private String currentItemFqname;
   private String dstContainerFqname;
   private int flags;

   public String getCurrentItemFqname() {
      return this.currentItemFqname;
   }

   public void setCurrentItemFqname(String var1) {
      this.currentItemFqname = var1;
   }

   public String getDstContainerFqname() {
      return this.dstContainerFqname;
   }

   public void setDstContainerFqname(String var1) {
      this.dstContainerFqname = var1;
   }

   public int getFlags() {
      return this.flags;
   }

   public void setFlags(int var1) {
      this.flags = var1;
   }
}
