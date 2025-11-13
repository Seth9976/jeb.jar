package com.pnfsoftware.jeb.core.events;

public abstract class AbstractQuestionNotification extends ClientNotification {
   private Object response;
   private Object defaultResponse;
   private boolean askDoNotShowAnymore;
   private boolean doNotShowAnymoreResponse;

   public AbstractQuestionNotification(String var1, Object var2, boolean var3) {
      super(var1);
      this.defaultResponse = var2;
      this.askDoNotShowAnymore = var3;
   }

   public Object getDefaultResponse() {
      return this.defaultResponse;
   }

   public void setResponse(Object var1) {
      this.response = var1;
   }

   public Object getResponse() {
      return this.response != null ? this.response : this.defaultResponse;
   }

   public boolean isAskDoNotShowAnymore() {
      return this.askDoNotShowAnymore;
   }

   public boolean isDoNotShowAnymoreResponse() {
      return this.doNotShowAnymoreResponse;
   }

   public void setDoNotShowAnymoreResponse(boolean var1) {
      this.doNotShowAnymoreResponse = var1;
   }
}
