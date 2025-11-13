package com.pnfsoftware.jeb.core.actions;

import java.util.HashMap;
import java.util.Map;

public class ActionData implements IActionData {
   public static final int ERRCODE_UNKNOWN = 0;
   private boolean noInfoRequest;
   private String description;
   private Map map = new HashMap();
   private int errcode;
   private String errmsg;

   public ActionData() {
      this(false);
   }

   public ActionData(boolean var1) {
      this.noInfoRequest = var1;
   }

   public boolean isNoInfoRequest() {
      return this.noInfoRequest;
   }

   public void reset() {
      this.map.clear();
      this.errcode = 0;
      this.errmsg = null;
   }

   public boolean canReuseObject() {
      return false;
   }

   public void setDescription(String var1) {
      this.description = var1;
   }

   public String getDescription() {
      return this.description;
   }

   public void setExecutionError(int var1, String var2) {
      this.errcode = var1;
      this.errmsg = var2;
   }

   public void setExecutionErrorCode(int var1) {
      this.errcode = var1;
   }

   public int getExecutionErrorCode() {
      return this.errcode;
   }

   public void setExecutionErrorMessage(String var1) {
      this.errmsg = var1;
   }

   public String getExecutionErrorMessage() {
      return this.errmsg;
   }

   @Override
   public Object getValue(String var1) {
      return this.map.get(var1);
   }

   @Override
   public void setValue(String var1, Object var2) {
      this.map.put(var1, var2);
   }
}
