package com.pnfsoftware.jeb.core.actions;

import java.util.Map;

public class ActionCollapseData extends ActionData {
   private Map records;

   public ActionCollapseData(boolean var1) {
      super(var1);
   }

   public void setRecords(Map var1) {
      this.records = var1;
   }

   public Map getRecords() {
      return this.records;
   }
}
