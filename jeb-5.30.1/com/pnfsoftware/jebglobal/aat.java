package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.JebCoreService;
import com.pnfsoftware.jeb.core.units.code.asm.analyzer.LibraryID;
import com.pnfsoftware.jeb.core.units.code.asm.items.INativeMethodItem;
import com.pnfsoftware.jeb.util.serialization.annotations.Ser;
import com.pnfsoftware.jeb.util.serialization.annotations.SerId;
import java.util.HashMap;
import java.util.Map;

@Ser
public class aat {
   @SerId(1)
   private Map q = new HashMap();
   @SerId(2)
   private boolean RF;

   public aas q(LibraryID var1) {
      return (aas)this.q.get(var1);
   }

   public void q(LibraryID var1, INativeMethodItem var2) {
      aas var3 = (aas)this.q.get(var1);
      if (var3 == null) {
         Integer var4 = (Integer)aar.q.get(var1);
         if (var4 != null) {
            var3 = new aas(var1, var4.intValue());
         } else {
            var3 = new aas(var1, Long.MAX_VALUE);
         }

         this.q.put(var1, var3);
      }

      var3.q(var2);
   }

   public aas q() {
      aas var1 = null;
      if (!this.q.values().isEmpty()) {
         var1 = (aas)this.q.values().iterator().next();

         for (aas var3 : this.q.values()) {
            if (var3.RF() > var1.RF()) {
               var1 = var3;
            }
         }
      }

      return var1;
   }

   public void RF() {
      if (!this.RF) {
         this.RF = this.xK();
      }

      if (!this.RF) {
         this.RF = this.Dw();
      }
   }

   private boolean xK() {
      for (aas var2 : this.q.values()) {
         if (var2.xK()) {
            JebCoreService.notifyTelemetryToClient("libraryRangeConfirmed", "ID", var2.q().toString());
            return true;
         }
      }

      return false;
   }

   private boolean Dw() {
      for (aas var2 : this.q.values()) {
         if (var2.Dw()) {
            JebCoreService.notifyTelemetryToClient("libraryRangeAlmostConfirmed", "ID", var2.q().toString());
            return true;
         }
      }

      return false;
   }

   @Override
   public String toString() {
      return "LibraryRangeManager [libraryRanges=" + this.q + "]";
   }
}
