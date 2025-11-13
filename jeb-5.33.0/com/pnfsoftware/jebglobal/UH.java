package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.client.Licensing;
import com.pnfsoftware.jeb.core.events.J;
import com.pnfsoftware.jeb.core.events.JebEvent;
import com.pnfsoftware.jeb.core.units.IUnitIdentifier;
import com.pnfsoftware.jeb.util.format.Strings;
import com.pnfsoftware.jeb.util.logging.GlobalLog;
import com.pnfsoftware.jeb.util.logging.ILogger;
import com.pnfsoftware.jeb.util.net.INet;

public class UH implements Runnable {
   private static final ILogger sY = GlobalLog.getLogger(UH.class);
   Nj pC;
   GA A;
   INet kS;
   hi wS;
   String UT;
   long E;

   public UH(Nj var1) {
      this.pC = var1;
      this.A = var1.pC();
      this.kS = var1.kS();
      this.wS = new hi(this.kS);
   }

   @Override
   public void run() {
   }

   private boolean pC(boolean var1) {
      Jq var2 = Jq.pC;
      int var3 = 0;
      long var4 = System.currentTimeMillis() + this.E;
      be var6 = this.pC();
      if (var6 == null) {
         var2 = Jq.A;
      } else {
         var3 = var6.A();
         var4 = var6.pC();
      }

      if (var3 == 1) {
         var2 = Jq.E;
      } else {
         long var7 = var4 / 1000L;
         long var9 = Licensing.getExpirationTimestamp();
         if (var7 > 0L && var9 > 0L && var7 > var9) {
            var2 = Jq.kS;
         }
      }

      int var11 = this.pC.getPropertyManager().getInteger(".Lvt");
      if (var11 > var2.pC()) {
         Jq var8 = Jq.pC(var11);
         if (var8 != null) {
            var2 = var8;
         }
      }

      if (var2 != Jq.pC) {
         this.A.pC(var2);
         if (var2 == Jq.kS) {
            this.pC(5);
         }

         if (var1) {
            String var12 = Strings.safe(var2.A(), this.UT);
            this.A.notifyListeners(new JebEvent(J.CoreError, var12));
         }

         return false;
      } else {
         this.A.pC(Jq.pC);
         return true;
      }
   }

   private be pC() {
      return this.wS.A();
   }

   private void pC(int var1) {
      long var2 = Licensing.license_id & -16L | var1;

      for (IUnitIdentifier var5 : this.pC.getUnitIdentifiers()) {
         var5.setData("DGRM", var2);
      }
   }
}
