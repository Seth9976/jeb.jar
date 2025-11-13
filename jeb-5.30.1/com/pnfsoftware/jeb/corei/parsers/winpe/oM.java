package com.pnfsoftware.jeb.corei.parsers.winpe;

import com.pnfsoftware.jeb.core.units.code.asm.memory.IVirtualMemory;
import com.pnfsoftware.jeb.core.units.code.asm.memory.MemoryException;
import com.pnfsoftware.jeb.core.units.code.asm.memory.VirtualMemoryUtil;
import com.pnfsoftware.jeb.util.serialization.annotations.Ser;
import com.pnfsoftware.jeb.util.serialization.annotations.SerId;

@Ser
public class oM {
   public static final int q = 20;
   @SerId(1)
   public long RF;
   @SerId(2)
   public int xK;
   @SerId(3)
   public int Dw;
   @SerId(4)
   public long Uv;
   @SerId(5)
   public long oW;
   @SerId(6)
   public String gO;
   @SerId(7)
   public long nf;
   @SerId(8)
   public int gP;
   @SerId(9)
   public boolean za;
   @SerId(10)
   public long lm;

   public static oM q(long var0, IVirtualMemory var2, long var3) throws MemoryException {
      oM var5 = new oM();
      var5.nf = var0;
      var5.RF = var2.readLEInt(var0);
      var5.xK = var2.readLEInt(var0 + 4L);
      var5.Dw = var2.readLEInt(var0 + 8L);
      var5.Uv = var2.readLEInt(var0 + 12L) & 4294967295L;
      if (var5.Uv == 0L) {
         return null;
      } else {
         var5.oW = var2.readLEInt(var0 + 16L) & 4294967295L;
         if (var5.oW == 0L) {
            return null;
         } else {
            var5.gO = VirtualMemoryUtil.readNullTerminatedStringSafe(var2, var3 + var5.Uv, 256);
            return var5;
         }
      }
   }
}
