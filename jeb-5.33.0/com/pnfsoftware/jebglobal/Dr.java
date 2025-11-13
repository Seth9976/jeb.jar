package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.client.S;
import com.pnfsoftware.jeb.core.events.J;
import com.pnfsoftware.jeb.core.events.JebEvent;
import com.pnfsoftware.jeb.core.units.code.debug.DebuggerEventType;
import com.pnfsoftware.jeb.core.units.code.debug.impl.DebuggerEventData;

class Dr implements ZB {
   Dr(ia var1) {
      this.pC = var1;
   }

   // $VF: Unable to simplify switch on enum
   // Please report this to the Vineflower issue tracker, at https://github.com/Vineflower/vineflower/issues with a copy of the class file (if you have the rights to distribute it!)
   @Override
   public void pC(rH var1, int var2, int var3, int var4, long var5) {
      ia.oT.info(S.L("[GDB] [SIGNAL] type=%s signal=%d tid=%d @ %Xh"), var1, var4, var3, var5);
      String var7 = this.pC.pC(var5);
      switch (var1) {
         case A:
            this.pC.UT.pC(false);
            DebuggerEventData var9 = new DebuggerEventData(DebuggerEventType.BREAKPOINT, var3, var7);
            var9.setUnitAddress(this.pC.UT.pC(var5));
            this.pC.notifyListeners(new JebEvent(J.DbgTargetEvent, var9));
            break;
         case pC:
            this.pC.UT.pC(false);
            DebuggerEventData var8 = new DebuggerEventData(DebuggerEventType.SIGNAL, var3, var7);
            var8.setUnitAddress(this.pC.UT.pC(var5));
            var8.setSignalNumber(var4);
            this.pC.notifyListeners(new JebEvent(J.DbgTargetEvent, var8));
            break;
         case kS:
            this.pC.A(true, true);
      }
   }

   @Override
   public void pC(byte[] var1) {
      DebuggerEventData var2 = new DebuggerEventData(DebuggerEventType.OUTPUT, 0L, null);
      var2.setOutput(var1);
      this.pC.notifyListeners(new JebEvent(J.DbgTargetEvent, var2));
   }
}
