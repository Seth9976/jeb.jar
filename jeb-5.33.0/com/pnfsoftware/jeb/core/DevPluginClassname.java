package com.pnfsoftware.jeb.core;

public class DevPluginClassname {
   private String classname;
   private boolean enabled;

   public DevPluginClassname(String var1, boolean var2) {
      this.classname = var1;
      this.enabled = var2;
   }

   public String getClassname() {
      return this.classname;
   }

   public boolean isEnabled() {
      return this.enabled;
   }
}
