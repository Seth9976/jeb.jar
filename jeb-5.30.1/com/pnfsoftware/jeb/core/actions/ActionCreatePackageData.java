package com.pnfsoftware.jeb.core.actions;

public class ActionCreatePackageData extends ActionData {
   private String currentPackageFqname;
   private String fqname;

   public String getCurrentPackageFqname() {
      return this.currentPackageFqname;
   }

   public void setCurrentPackageFqname(String var1) {
      this.currentPackageFqname = var1;
   }

   public String getFqname() {
      return this.fqname;
   }

   public void setFqname(String var1) {
      this.fqname = var1;
   }
}
