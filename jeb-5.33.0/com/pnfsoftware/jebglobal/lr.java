package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.client.S;
import com.pnfsoftware.jeb.core.events.J;
import com.pnfsoftware.jeb.core.events.JebEvent;
import com.pnfsoftware.jeb.core.units.code.debug.DebuggerEventType;
import com.pnfsoftware.jeb.core.units.code.debug.impl.DebuggerEventData;

class lr implements ZB {
   lr(dy var1) {
      this.pC = var1;
   }

   @Override
   public void pC(rH var1, int var2, int var3, int var4, long var5) {
      dy.fI.info(S.L("[GDB] [SIGNAL] type=%s signal=%s(%d) tid=%d @ 0x%X"), var1, yv.pC(var4), var4, var3, var5);
      String var7 = this.pC.pC(var5);
      switch (pr.pC[var1.ordinal()]) {
         case 1:
            this.pC.UT.pC(false);
            DebuggerEventData var9 = new DebuggerEventData(DebuggerEventType.BREAKPOINT, var3, var7);
            var9.setUnitAddress(this.pC.UT.pC(var5));
            this.pC.notifyListeners(new JebEvent(J.DbgTargetEvent, var9));
            break;
         case 2:
            this.pC.UT.pC(false);
            DebuggerEventData var8 = new DebuggerEventData(DebuggerEventType.SIGNAL, var3, var7);
            var8.setUnitAddress(this.pC.UT.pC(var5));
            var8.setSignalNumber(var4);
            this.pC.notifyListeners(new JebEvent(J.DbgTargetEvent, var8));
            break;
         case 3:
            this.pC.A(true, true);
            if (this.pC.WR != null) {
               this.pC.WR.A(false, true);
            }
      }
   }

   @Override
   public void pC(byte[] var1) {
      DebuggerEventData var2 = new DebuggerEventData(DebuggerEventType.OUTPUT, 0L, null);
      var2.setOutput(var1);
      this.pC.notifyListeners(new JebEvent(J.DbgTargetEvent, var2));
   }
}
