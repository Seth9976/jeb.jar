package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.client.S;
import com.pnfsoftware.jeb.core.events.J;
import com.pnfsoftware.jeb.core.events.JebEvent;
import com.pnfsoftware.jeb.core.units.code.debug.DebuggerEventType;
import com.pnfsoftware.jeb.core.units.code.debug.impl.DebuggerEventData;

class ap implements HJ {
   ap(um var1) {
      this.q = var1;
   }

   @Override
   public void q(af var1, int var2, int var3, int var4, long var5) {
      um.lm.info(S.L("[GDB] [SIGNAL] type=%s signal=%d tid=%d @ %Xh"), var1, var4, var3, var5);
      String var7 = this.q.q(var5);
      switch (var1) {
         case RF:
            this.q.Uv.q(false);
            DebuggerEventData var9 = new DebuggerEventData(DebuggerEventType.BREAKPOINT, var3, var7);
            var9.setUnitAddress(this.q.Uv.q(var5));
            this.q.notifyListeners(new JebEvent(J.DbgTargetEvent, var9));
            break;
         case q:
            this.q.Uv.q(false);
            DebuggerEventData var8 = new DebuggerEventData(DebuggerEventType.SIGNAL, var3, var7);
            var8.setUnitAddress(this.q.Uv.q(var5));
            var8.setSignalNumber(var4);
            this.q.notifyListeners(new JebEvent(J.DbgTargetEvent, var8));
            break;
         case xK:
            this.q.RF(true, true);
      }
   }

   @Override
   public void q(byte[] var1) {
      DebuggerEventData var2 = new DebuggerEventData(DebuggerEventType.OUTPUT, 0L, null);
      var2.setOutput(var1);
      this.q.notifyListeners(new JebEvent(J.DbgTargetEvent, var2));
   }
}
