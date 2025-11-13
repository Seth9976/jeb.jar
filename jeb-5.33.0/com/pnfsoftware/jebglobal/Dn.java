package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.util.format.Strings;
import com.pnfsoftware.jeb.util.logging.GlobalLog;
import com.pnfsoftware.jeb.util.logging.ILogger;

public class Dn {
   private static final ILogger pC = GlobalLog.getLogger(Dn.class);
   private int A;
   private String kS;
   private String wS;
   private Tl UT;

   public Dn(int var1, String var2, String var3, Tl var4) {
      this.A = var1;
      this.kS = var2;
      this.wS = var3;
      this.UT = var4;
   }

   public Dn(int var1, String var2) {
      this(var1, var2, null, null);
   }

   public int pC() {
      return this.A;
   }

   public String A() {
      return this.kS;
   }

   public String kS() {
      return this.wS;
   }

   public Tl wS() {
      return this.UT;
   }

   @Override
   public String toString() {
      return Strings.ff("tid=%d(%Xh),name=%s", this.pC(), this.pC(), this.A());
   }
}
