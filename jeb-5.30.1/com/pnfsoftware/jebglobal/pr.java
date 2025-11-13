package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.util.format.Strings;
import com.pnfsoftware.jeb.util.logging.GlobalLog;
import com.pnfsoftware.jeb.util.logging.ILogger;

public class pr {
   private static final ILogger q = GlobalLog.getLogger(pr.class);
   private int RF;
   private String xK;
   private String Dw;
   private kW Uv;

   public pr(int var1, String var2, String var3, kW var4) {
      this.RF = var1;
      this.xK = var2;
      this.Dw = var3;
      this.Uv = var4;
   }

   public pr(int var1, String var2) {
      this(var1, var2, null, null);
   }

   public int q() {
      return this.RF;
   }

   public String RF() {
      return this.xK;
   }

   public String xK() {
      return this.Dw;
   }

   public kW Dw() {
      return this.Uv;
   }

   @Override
   public String toString() {
      return Strings.ff("tid=%d(%Xh),name=%s", this.q(), this.q(), this.RF());
   }
}
