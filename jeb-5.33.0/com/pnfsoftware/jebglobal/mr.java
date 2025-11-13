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

public class mr {
   private ia pC;
   private Map A = new HashMap();

   public mr(ia var1) {
      this.pC = var1;
   }

   xa pC(long var1, int var3, boolean var4) {
      if (var1 == -1L) {
         return null;
      } else {
         xa var5 = (xa)this.A.get(var1);
         if (var5 == null || !var5.isEnabled() && var4) {
            if (!this.pC.A().pC(var1, var3)) {
               return null;
            } else {
               if (var5 == null) {
                  var5 = this.pC(var1, var3);
               }

               var5.pC = true;
               this.pC(new JebEvent(J.DbgBreakpointSet, var5.getAddress()));
               return var5;
            }
         } else {
            return var5;
         }
      }
   }

   synchronized xa pC(long var1, int var3) {
      xa var4 = (xa)this.A.get(var1);
      if (var4 == null) {
         var4 = new xa(this.pC, var1, var3);
         this.A.put(var1, var4);
      } else {
         var4.pC(var3);
      }

      return var4;
   }

   xa pC(long var1) {
      if (var1 == -1L) {
         return null;
      } else {
         xa var3 = (xa)this.A.get(var1);
         if (this.pC.A().pC(var1)) {
            var3 = this.pC(var1, 0);
            var3.pC = true;
         } else if (var3 != null) {
            var3.pC = false;
         }

         return var3;
      }
   }

   List pC() {
      return new ArrayList(this.A.values());
   }

   List A() {
      ArrayList var1 = new ArrayList();

      for (xK var3 : this.pC.A().PR()) {
         long var4 = var3.pC();
         xa var6 = this.pC(var4, 0);
         var6.pC = true;
         var1.add(var6);
      }

      ArrayList var7 = new ArrayList(this.A.values());

      for (xa var9 : var7) {
         if (!var1.contains(var9)) {
            var9.pC = false;
         }
      }

      return var7;
   }

   boolean pC(IDebuggerBreakpoint var1, boolean var2) {
      long var3 = Conversion.stringToLong(var1.getAddress(), -1L);
      if (var3 == -1L) {
         return false;
      } else {
         xa var5 = (xa)this.A.get(var3);
         if (var5 != null && var5.isEnabled()) {
            this.pC.A().A(var3);
         }

         if (!var2) {
            this.A.remove(var3);
         }

         if (var5 != null) {
            var5.pC = false;
            this.pC(new JebEvent(J.DbgBreakpointUnset, var5.getAddress()));
         }

         return true;
      }
   }

   boolean kS() {
      for (Entry var2 : this.A.entrySet()) {
         if (((xa)var2.getValue()).isEnabled()) {
            this.pC.A().A((Long)var2.getKey());
         }
      }

      this.A.clear();
      return true;
   }

   public void wS() {
      this.A.clear();
   }

   private void pC(JebEvent var1) {
      this.pC.notifyListeners(var1);
   }
}
