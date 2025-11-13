package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.units.code.Pointer;
import com.pnfsoftware.jeb.core.units.code.asm.analyzer.INativeCodeAnalyzer;
import com.pnfsoftware.jeb.core.units.code.asm.items.INativeContinuousItem;
import com.pnfsoftware.jeb.core.units.code.asm.items.INativeDataItem;
import com.pnfsoftware.jeb.core.units.code.asm.items.INativeInstructionItem;
import com.pnfsoftware.jeb.core.units.code.asm.items.INativeStringItem;

public class dA {
   private com.pnfsoftware.jeb.corei.parsers.arm.Av pC;

   public dA(com.pnfsoftware.jeb.corei.parsers.arm.Av var1) {
      this.pC = var1;
   }

   public void pC(long var1) {
      this.pC.pC(var1);
   }

   public void pC(Long var1, Long var2) {
      this.pC.pC(var1, var2);
   }

   public boolean A(long var1) {
      return this.pC.kS(var1);
   }

   public boolean kS(long var1) {
      return this.pC.wS(var1);
   }

   public int pC(long var1, long var3, int var5) {
      return this.pC.pC().pC(var1, var3, var5);
   }

   public Pointer pC(INativeCodeAnalyzer var1, long var2, int var4, boolean var5) {
      boolean var6 = this.pC.A(var2);
      long var7 = var2;
      if ((var2 & 1L) != 0L) {
         var7 = var2 - 1L;
      }

      if (!var6 && this.pC.wS(var7)) {
         return var5 ? new Pointer(var2, var4, 1) : null;
      } else {
         INativeContinuousItem var9 = var1.getModel().getItemOver(var2);
         if (var9 instanceof INativeInstructionItem) {
            return !var5 && var9.getMemoryAddress() == var7 && yj.pC(var1.getModel(), (INativeInstructionItem)var9) ? new Pointer(var2, -2, 1) : null;
         } else if (var9 instanceof INativeDataItem) {
            if (!yj.A(var1, var2)) {
               return null;
            } else {
               if (var9 instanceof INativeStringItem) {
                  var2 = var9.getMemoryAddress();
               }

               return new Pointer(var2, var4, 2);
            }
         } else {
            return var5 && this.pC.sY(var2) ? new Pointer(var2, var4, 1) : new Pointer(var2, var4, 0);
         }
      }
   }
}
