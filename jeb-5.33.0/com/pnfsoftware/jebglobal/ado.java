package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ast.ICStatement;
import com.pnfsoftware.jeb.util.serialization.annotations.Ser;
import com.pnfsoftware.jeb.util.serialization.annotations.SerId;
import java.util.List;

@Ser
public abstract class ado extends adl implements ICStatement {
   @SerId(1)
   long pC = -1L;

   protected void pC(ado var1) {
      super.pC(var1);
      var1.pC = this.pC;
   }

   @Override
   public long getIntermediateOffset() {
      return this.pC;
   }

   @Override
   public void setIntermediateOffset(long var1) {
      this.pC = var1;
   }

   public static boolean pC(List var0, long var1, ICStatement var3) {
      for (int var4 = 0; var4 < var0.size(); var4++) {
         ICStatement var5 = (ICStatement)var0.get(var4);
         if (var5.getIntermediateOffset() == var1) {
            if (var0.get(var4) != var3) {
               var0.add(var4, var3);
            }

            return true;
         }

         if (var5 instanceof adm && ((adm)var5).insertAt(var1, var3)) {
            return true;
         }
      }

      return false;
   }
}
