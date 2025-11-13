package com.pnfsoftware.jebglobal;

import java.security.Permission;

public class bxt extends Permission {
   private static final long serialVersionUID = -5783648141761093420L;
   private static final String q = "DisableSandboxSecurityPermission";

   public bxt() {
      super("DisableSandboxSecurityPermission");
   }

   @Override
   public boolean implies(Permission var1) {
      return this.equals(var1);
   }

   @Override
   public boolean equals(Object var1) {
      return var1 instanceof bxt;
   }

   @Override
   public int hashCode() {
      return "DisableSandboxSecurityPermission".hashCode();
   }

   @Override
   public String getActions() {
      return "";
   }
}
