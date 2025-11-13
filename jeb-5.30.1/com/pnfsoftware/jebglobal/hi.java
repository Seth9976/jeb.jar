package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.units.code.Pointer;
import com.pnfsoftware.jeb.core.units.code.asm.analyzer.INativeCodeAnalyzer;
import com.pnfsoftware.jeb.core.units.code.asm.items.INativeContinuousItem;
import com.pnfsoftware.jeb.core.units.code.asm.items.INativeDataItem;
import com.pnfsoftware.jeb.core.units.code.asm.items.INativeInstructionItem;
import com.pnfsoftware.jeb.core.units.code.asm.items.INativeStringItem;

public class hi {
   private FS q;

   public hi(FS var1) {
      this.q = var1;
   }

   public void q(long var1) {
      this.q.q(var1);
   }

   public Long RF(long var1) {
      return this.q.RF(var1);
   }

   public boolean xK(long var1) {
      return this.q.xK(var1);
   }

   public void q(Long var1, Long var2) {
      this.q.q(var1, var2);
   }

   public boolean Dw(long var1) {
      return this.q.Dw(var1);
   }

   public boolean Uv(long var1) {
      return this.q.Uv(var1);
   }

   public void oW(long var1) {
      this.q.oW(var1);
   }

   public int q(long var1, long var3, int var5) {
      return this.q.q().q(var1, var3, var5);
   }

   public Pointer q(INativeCodeAnalyzer var1, long var2, int var4, boolean var5) {
      boolean var6 = this.q.xK(var2);
      long var7 = var2;
      if ((var2 & 1L) != 0L) {
         var7 = var2 - 1L;
      }

      if (!var6 && this.q.Uv(var7)) {
         return var5 ? new Pointer(var2, var4, 1) : null;
      } else {
         INativeContinuousItem var9 = var1.getModel().getItemOver(var2);
         if (var9 instanceof INativeInstructionItem) {
            return !var5 && var9.getMemoryAddress() == var7 && aaj.q(var1.getModel(), (INativeInstructionItem)var9) ? new Pointer(var2, -2, 1) : null;
         } else if (var9 instanceof INativeDataItem) {
            if (!aaj.RF(var1, var2)) {
               return null;
            } else {
               if (var9 instanceof INativeStringItem) {
                  var2 = var9.getMemoryAddress();
               }

               return new Pointer(var2, var4, 2);
            }
         } else {
            return var5 && this.q.nf(var2) ? new Pointer(var2, var4, 1) : new Pointer(var2, var4, 0);
         }
      }
   }
}
