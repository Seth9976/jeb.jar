package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.units.code.asm.analyzer.CallGraphVertex;
import com.pnfsoftware.jeb.core.units.code.asm.analyzer.ICallGraph;
import com.pnfsoftware.jeb.util.serialization.annotations.Ser;
import com.pnfsoftware.jeb.util.serialization.annotations.SerId;
import java.util.Map;
import java.util.Set;
import java.util.Map.Entry;

@Ser
public class Gd {
   @SerId(1)
   private Tw pC;
   @SerId(2)
   private Map A;
   @SerId(3)
   private Map kS;

   public ICallGraph pC() {
      bj var1 = new bj(this.pC);

      for (Entry var3 : this.A.entrySet()) {
         long var4 = (Long)var3.getKey();

         for (CallGraphVertex var7 : (Set)var3.getValue()) {
            if (var7.isInternal()) {
               var1.recordInternalCall(var4, var7.getInternalAddress(), true);
            } else if (var7.isResolved()) {
               var1.recordExternalCall(var4, var7.getRoutine(), true);
            } else {
               var1.recordUnresolvedCall(var4, var7.getDereferencedAddress(), true);
            }
         }
      }

      return var1;
   }
}
