package com.pnfsoftware.jeb.core.actions;

import java.util.List;

public class ActionXrefsData extends ActionData {
   private int hintMaxResults;
   private String target;
   private List addresses;
   private List details;
   private List userAddresses;

   public void setHintMaxResults(int var1) {
      this.hintMaxResults = var1;
   }

   public int getHintMaxResults() {
      return this.hintMaxResults;
   }

   public String getTarget() {
      return this.target;
   }

   public void setTarget(String var1) {
      this.target = var1;
   }

   public List getAddresses() {
      return this.addresses;
   }

   public void setAddresses(List var1) {
      this.addresses = var1;
   }

   public List getDetails() {
      return this.details;
   }

   public void setDetails(List var1) {
      this.details = var1;
   }

   public List getUserAddresses() {
      return this.userAddresses;
   }

   public void setUserAddresses(List var1) {
      this.userAddresses = var1;
   }
}
