package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.client.Licensing;
import com.pnfsoftware.jeb.core.events.J;
import com.pnfsoftware.jeb.core.events.JebEvent;
import com.pnfsoftware.jeb.core.units.IUnitIdentifier;
import com.pnfsoftware.jeb.util.format.Strings;
import com.pnfsoftware.jeb.util.logging.GlobalLog;
import com.pnfsoftware.jeb.util.logging.ILogger;
import com.pnfsoftware.jeb.util.net.INet;

public class s implements Runnable {
   private static final ILogger gO = GlobalLog.getLogger(s.class);
   private static final int nf = 60000;
   private static final int gP = 1800000;
   zJ q;
   Xa RF;
   INet xK;
   PY Dw;
   String Uv;
   long oW;

   public s(zJ var1) {
      this.q = var1;
      this.RF = var1.q();
      this.xK = var1.xK();
      this.Dw = new PY(this.xK);
   }

   @Override
   public void run() {
   }

   private boolean q(boolean var1) {
      dv var2 = dv.q;
      int var3 = 0;
      long var4 = System.currentTimeMillis() + this.oW;
      vn var6 = this.RF();
      if (var6 == null) {
         var2 = dv.RF;
      } else {
         var3 = var6.RF();
         var4 = var6.q();
      }

      if (var3 == 1) {
         var2 = dv.oW;
      } else {
         long var7 = var4 / 1000L;
         long var9 = Licensing.getExpirationTimestamp();
         if (var7 > 0L && var9 > 0L && var7 > var9) {
            var2 = dv.xK;
         }
      }

      int var11 = this.q.getPropertyManager().getInteger(".Lvt");
      if (var11 > var2.q()) {
         dv var8 = dv.q(var11);
         if (var8 != null) {
            var2 = var8;
         }
      }

      if (var2 != dv.q) {
         this.RF.q(var2);
         if (var2 == dv.xK) {
            this.q(5);
         }

         if (var1) {
            String var12 = Strings.safe(var2.RF(), this.Uv);
            this.RF.notifyListeners(new JebEvent(J.CoreError, var12));
         }

         return false;
      } else {
         this.RF.q(dv.q);
         return true;
      }
   }

   private dv q() {
      return dv.Uv;
   }

   private vn RF() {
      return this.Dw.Dw();
   }

   private void q(int var1) {
      long var2 = Licensing.license_id & -16L | var1;

      for (IUnitIdentifier var5 : this.q.getUnitIdentifiers()) {
         var5.setData("DGRM", var2);
      }
   }
}
