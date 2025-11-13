package com.pnfsoftware.jeb.corei.debuggers.android.vm;

import com.pnfsoftware.jeb.core.events.J;
import com.pnfsoftware.jeb.core.events.JebEvent;
import com.pnfsoftware.jeb.core.units.UnitAddress;
import com.pnfsoftware.jeb.core.units.code.debug.DebuggerEventType;
import com.pnfsoftware.jeb.core.units.code.debug.impl.DebuggerEventData;
import com.pnfsoftware.jebglobal.Ux;
import com.pnfsoftware.jebglobal.ch;
import com.pnfsoftware.jebglobal.oG;
import com.pnfsoftware.jebglobal.qt;
import com.pnfsoftware.jebglobal.zy;
import java.io.IOException;

class vX implements qt {
   vX(CI var1) {
      this.q = var1;
   }

   @Override
   public void q(long var1, oG var3, boolean var4) {
      Ux var5 = (Ux)this.q.za;
      var5.HF().q();
      String var6 = this.q.q(var3);
      if (var6 != null) {
         DebuggerEventData var7 = new DebuggerEventData(DebuggerEventType.BREAKPOINT, var1, var6);
         var7.setUnitAddress(new UnitAddress(this.q.xK(var6), var6));
         this.q.notifyListeners(new JebEvent(J.DbgTargetEvent, var7));
      }
   }

   @Override
   public void q(long var1, oG var3) {
      Ux var4 = (Ux)this.q.za;
      var4.HF().q();
      String var5 = this.q.q(var3);
      if (var5 != null) {
         DebuggerEventData var6 = new DebuggerEventData(DebuggerEventType.EXCEPTION, var1, var5);
         var6.setUnitAddress(new UnitAddress(this.q.xK(var5), var5));
         this.q.notifyListeners(new JebEvent(J.DbgTargetEvent, var6));
      }
   }

   @Override
   public void RF(long var1, oG var3) {
      Ux var4 = (Ux)this.q.za;
      var4.HF().q();
      String var5 = this.q.q(var3);
      if (var5 != null) {
         DebuggerEventData var6 = new DebuggerEventData(DebuggerEventType.BREAKPOINT, var1, var5);
         var6.setUnitAddress(new UnitAddress(this.q.xK(var5), var5));
         this.q.notifyListeners(new JebEvent(J.DbgTargetEvent, var6));
      }
   }

   @Override
   public void xK(long var1, oG var3) {
      String var4 = this.q.q(var3);
      if (var4 != null) {
         DebuggerEventData var5 = new DebuggerEventData(DebuggerEventType.BREAKPOINT, var1, var4);
         var5.setUnitAddress(new UnitAddress(this.q.xK(var4), var4));
         this.q.notifyListeners(new JebEvent(J.DbgTargetEvent, var5));
      }
   }

   @Override
   public void q(long var1, oG var3, ch var4) {
      String var5 = this.q.q(var3);
      if (var5 != null) {
         DebuggerEventData var6 = new DebuggerEventData(DebuggerEventType.BREAKPOINT_FUNCTION_EXIT, var1, var5);
         if (var4 != null) {
            try {
               var6.setReturnValue(this.q.q(var4));
            } catch (zy | IOException var8) {
               CI.q.catching(var8);
            }
         }

         var6.setUnitAddress(new UnitAddress(this.q.xK(var5), var5));
         this.q.notifyListeners(new JebEvent(J.DbgTargetEvent, var6));
      }
   }

   @Override
   public void q(long var1) {
      DebuggerEventData var3 = new DebuggerEventData(DebuggerEventType.THREAD_START, var1, null);
      this.q.notifyListeners(new JebEvent(J.DbgTargetEvent, var3));
   }

   @Override
   public void RF(long var1) {
      DebuggerEventData var3 = new DebuggerEventData(DebuggerEventType.THREAD_STOP, var1, null);
      this.q.notifyListeners(new JebEvent(J.DbgTargetEvent, var3));
   }

   @Override
   public void q(long var1, long var3, String var5) {
   }

   @Override
   public void xK(long var1) {
   }

   @Override
   public void q() {
      this.q.RF(true, true);
   }
}
