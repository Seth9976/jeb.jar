package com.pnfsoftware.jeb.corei.debuggers.android.vm;

import com.pnfsoftware.jeb.core.events.J;
import com.pnfsoftware.jeb.core.events.JebEvent;
import com.pnfsoftware.jeb.core.units.UnitAddress;
import com.pnfsoftware.jeb.core.units.code.debug.DebuggerEventType;
import com.pnfsoftware.jeb.core.units.code.debug.impl.DebuggerEventData;
import com.pnfsoftware.jebglobal.Jx;
import com.pnfsoftware.jebglobal.bA;
import com.pnfsoftware.jebglobal.rG;
import com.pnfsoftware.jebglobal.wX;
import com.pnfsoftware.jebglobal.xl;
import java.io.IOException;

class ma implements xl {
   ma(Tb var1) {
      this.pC = var1;
   }

   @Override
   public void pC(long var1, Jx var3, boolean var4) {
      bA var5 = (bA)this.pC.gp;
      var5.fI().pC();
      String var6 = this.pC.pC(var3);
      if (var6 != null) {
         DebuggerEventData var7 = new DebuggerEventData(DebuggerEventType.BREAKPOINT, var1, var6);
         var7.setUnitAddress(new UnitAddress(this.pC.A(var6), var6));
         this.pC.notifyListeners(new JebEvent(J.DbgTargetEvent, var7));
      }
   }

   @Override
   public void pC(long var1, Jx var3) {
      bA var4 = (bA)this.pC.gp;
      var4.fI().pC();
      String var5 = this.pC.pC(var3);
      if (var5 != null) {
         DebuggerEventData var6 = new DebuggerEventData(DebuggerEventType.EXCEPTION, var1, var5);
         var6.setUnitAddress(new UnitAddress(this.pC.A(var5), var5));
         this.pC.notifyListeners(new JebEvent(J.DbgTargetEvent, var6));
      }
   }

   @Override
   public void A(long var1, Jx var3) {
      bA var4 = (bA)this.pC.gp;
      var4.fI().pC();
      String var5 = this.pC.pC(var3);
      if (var5 != null) {
         DebuggerEventData var6 = new DebuggerEventData(DebuggerEventType.BREAKPOINT, var1, var5);
         var6.setUnitAddress(new UnitAddress(this.pC.A(var5), var5));
         this.pC.notifyListeners(new JebEvent(J.DbgTargetEvent, var6));
      }
   }

   @Override
   public void kS(long var1, Jx var3) {
      String var4 = this.pC.pC(var3);
      if (var4 != null) {
         DebuggerEventData var5 = new DebuggerEventData(DebuggerEventType.BREAKPOINT, var1, var4);
         var5.setUnitAddress(new UnitAddress(this.pC.A(var4), var4));
         this.pC.notifyListeners(new JebEvent(J.DbgTargetEvent, var5));
      }
   }

   @Override
   public void pC(long var1, Jx var3, rG var4) {
      String var5 = this.pC.pC(var3);
      if (var5 != null) {
         DebuggerEventData var6 = new DebuggerEventData(DebuggerEventType.BREAKPOINT_FUNCTION_EXIT, var1, var5);
         if (var4 != null) {
            try {
               var6.setReturnValue(this.pC.pC(var4));
            } catch (wX | IOException var8) {
               Tb.pC.catching(var8);
            }
         }

         var6.setUnitAddress(new UnitAddress(this.pC.A(var5), var5));
         this.pC.notifyListeners(new JebEvent(J.DbgTargetEvent, var6));
      }
   }

   @Override
   public void pC(long var1) {
      DebuggerEventData var3 = new DebuggerEventData(DebuggerEventType.THREAD_START, var1, null);
      this.pC.notifyListeners(new JebEvent(J.DbgTargetEvent, var3));
   }

   @Override
   public void A(long var1) {
      DebuggerEventData var3 = new DebuggerEventData(DebuggerEventType.THREAD_STOP, var1, null);
      this.pC.notifyListeners(new JebEvent(J.DbgTargetEvent, var3));
   }

   @Override
   public void pC() {
      this.pC.A(true, true);
   }
}
