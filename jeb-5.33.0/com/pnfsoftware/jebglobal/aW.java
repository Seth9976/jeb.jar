package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.JebCoreService;
import com.pnfsoftware.jeb.core.units.code.asm.analyzer.LibraryID;
import com.pnfsoftware.jeb.core.units.code.asm.items.INativeMethodItem;
import com.pnfsoftware.jeb.util.serialization.annotations.Ser;
import com.pnfsoftware.jeb.util.serialization.annotations.SerId;
import java.util.HashMap;
import java.util.Map;

@Ser
public class aW {
   @SerId(1)
   private Map pC = new HashMap();
   @SerId(2)
   private boolean A;

   public jF pC(LibraryID var1) {
      return (jF)this.pC.get(var1);
   }

   public void pC(LibraryID var1, INativeMethodItem var2) {
      jF var3 = (jF)this.pC.get(var1);
      if (var3 == null) {
         Integer var4 = (Integer)kz.pC.get(var1);
         if (var4 != null) {
            var3 = new jF(var1, var4.intValue());
         } else {
            var3 = new jF(var1, Long.MAX_VALUE);
         }

         this.pC.put(var1, var3);
      }

      var3.pC(var2);
   }

   public void pC() {
      if (!this.A) {
         this.A = this.A();
      }

      if (!this.A) {
         this.A = this.kS();
      }
   }

   private boolean A() {
      for (jF var2 : this.pC.values()) {
         if (var2.A()) {
            JebCoreService.notifyTelemetryToClient("libraryRangeConfirmed", "ID", var2.pC().toString());
            return true;
         }
      }

      return false;
   }

   private boolean kS() {
      for (jF var2 : this.pC.values()) {
         if (var2.kS()) {
            JebCoreService.notifyTelemetryToClient("libraryRangeAlmostConfirmed", "ID", var2.pC().toString());
            return true;
         }
      }

      return false;
   }

   @Override
   public String toString() {
      return "LibraryRangeManager [libraryRanges=" + this.pC + "]";
   }
}
