package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.units.code.asm.items.INativeContinuousItem;
import com.pnfsoftware.jeb.core.units.code.asm.items.INativeDataItem;
import com.pnfsoftware.jeb.core.units.code.asm.memory.IVirtualMemory;
import com.pnfsoftware.jeb.core.units.code.asm.memory.MemoryException;

public class cud {
   long q;
   long RF;
   int xK;
   private conn Dw;

   public static cud q(cum var0, long var1, long var3, boolean var5) throws MemoryException {
      IVirtualMemory var6 = var0.q.getMemory();
      boolean var9 = var0.q.getProcessor().getMode() == 64;
      cud var10 = new cud();
      long var7 = var1 + 4L;
      var10.q = var6.readInt(var7) & 4294967295L;
      if (var10.q != 0L && var9) {
         var10.q += var3;
      }

      var7 += 4L;
      var7 += 4L;
      var10.RF = var6.readInt(var7) & 4294967295L;
      if (var10.RF != 0L && var9) {
         var10.RF += var3;
      }

      var10.xK = var9 ? 20 : 16;
      if (var10.q != 0L) {
         var10.Dw = conn.q(var6, var10.q, var9);
         if (var10.Dw == null) {
            return null;
         }
      }

      if (var5) {
         if (var0.gP != null) {
            var0.q.defineData(var1, var0.gP);
         }

         if (var10.Dw != null) {
            INativeContinuousItem var11 = var0.q.getModel().getItemAt(var10.Dw.q());
            if ((var11 == null || !(var11 instanceof axr)) && var0.qa != null) {
               var0.q.defineData(var10.Dw.q(), var0.RF.getType("TypeDescriptor"));
               var11 = var0.q.getModel().getItemAt(var10.Dw.q());
               if (var11 != null) {
                  var11.setName("??_R0" + var10.Dw.xK().substring(1) + "@8");
                  ((aae)var0.q).q((INativeDataItem)var11);
               }

               var0.q.recordAnalysisComment(var10.Dw.RF(), "name");
            }
         }
      }

      return var10;
   }
}
