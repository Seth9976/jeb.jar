package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.events.J;
import com.pnfsoftware.jeb.core.events.JebEvent;
import com.pnfsoftware.jeb.core.units.code.debug.IDebuggerBreakpoint;
import com.pnfsoftware.jeb.util.encoding.Conversion;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

public class yG {
   private um q;
   private Map RF = new HashMap();

   public yG(um var1) {
      this.q = var1;
   }

   xI q(long var1, int var3, boolean var4) {
      if (var1 == -1L) {
         return null;
      } else {
         xI var5 = (xI)this.RF.get(var1);
         if (var5 == null || !var5.isEnabled() && var4) {
            if (!this.q.RF().q(var1, var3)) {
               return null;
            } else {
               if (var5 == null) {
                  var5 = this.q(var1, var3);
               }

               var5.q = true;
               this.q(new JebEvent(J.DbgBreakpointSet, var5.getAddress()));
               return var5;
            }
         } else {
            return var5;
         }
      }
   }

   synchronized xI q(long var1, int var3) {
      xI var4 = (xI)this.RF.get(var1);
      if (var4 == null) {
         var4 = new xI(this.q, var1, var3);
         this.RF.put(var1, var4);
      } else {
         var4.q(var3);
      }

      return var4;
   }

   xI q(long var1) {
      if (var1 == -1L) {
         return null;
      } else {
         xI var3 = (xI)this.RF.get(var1);
         if (this.q.RF().q(var1)) {
            var3 = this.q(var1, 0);
            var3.q = true;
         } else if (var3 != null) {
            var3.q = false;
         }

         return var3;
      }
   }

   List q() {
      return new ArrayList(this.RF.values());
   }

   List RF() {
      ArrayList var1 = new ArrayList();

      for (fQ var3 : this.q.RF().IN()) {
         long var4 = var3.q();
         xI var6 = this.q(var4, 0);
         var6.q = true;
         var1.add(var6);
      }

      ArrayList var7 = new ArrayList(this.RF.values());

      for (xI var9 : var7) {
         if (!var1.contains(var9)) {
            var9.q = false;
         }
      }

      return var7;
   }

   boolean q(IDebuggerBreakpoint var1, boolean var2) {
      long var3 = Conversion.stringToLong(var1.getAddress(), -1L);
      if (var3 == -1L) {
         return false;
      } else {
         xI var5 = (xI)this.RF.get(var3);
         if (var5 != null && var5.isEnabled()) {
            this.q.RF().xK(var3);
         }

         if (!var2) {
            this.RF.remove(var3);
         }

         if (var5 != null) {
            var5.q = false;
            this.q(new JebEvent(J.DbgBreakpointUnset, var5.getAddress()));
         }

         return true;
      }
   }

   boolean xK() {
      for (Entry var2 : this.RF.entrySet()) {
         if (((xI)var2.getValue()).isEnabled()) {
            this.q.RF().xK((Long)var2.getKey());
         }
      }

      this.RF.clear();
      return true;
   }

   public void Dw() {
      this.RF.clear();
   }

   private void q(JebEvent var1) {
      this.q.notifyListeners(var1);
   }
}
