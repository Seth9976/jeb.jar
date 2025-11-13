package com.pnfsoftware.jeb.core.actions;

public class ActionMoveToPackageData extends ActionData {
   private String currentPackageFqname;
   private String dstPackageFqname;

   public String getCurrentPackageFqname() {
      return this.currentPackageFqname;
   }

   public void setCurrentPackageFqname(String var1) {
      this.currentPackageFqname = var1;
   }

   public String getDstPackageFqname() {
      return this.dstPackageFqname;
   }

   public void setDstPackageFqname(String var1) {
      this.dstPackageFqname = var1;
   }
}
