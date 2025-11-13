package com.pnfsoftware.jeb.corei.parsers.winpe;

import com.pnfsoftware.jeb.core.units.code.asm.memory.IVirtualMemory;
import com.pnfsoftware.jeb.core.units.code.asm.memory.MemoryException;
import com.pnfsoftware.jeb.core.units.code.asm.memory.VirtualMemoryUtil;
import com.pnfsoftware.jeb.util.serialization.annotations.Ser;
import com.pnfsoftware.jeb.util.serialization.annotations.SerId;

@Ser
public class bO {
   @SerId(1)
   public long pC;
   @SerId(2)
   public int A;
   @SerId(3)
   public int kS;
   @SerId(4)
   public long wS;
   @SerId(5)
   public long UT;
   @SerId(6)
   public String E;
   @SerId(7)
   public long sY;
   @SerId(8)
   public int ys;
   @SerId(9)
   public boolean ld;
   @SerId(10)
   public long gp;

   public static bO pC(long var0, IVirtualMemory var2, long var3) throws MemoryException {
      bO var5 = new bO();
      var5.sY = var0;
      var5.pC = var2.readLEInt(var0);
      var5.A = var2.readLEInt(var0 + 4L);
      var5.kS = var2.readLEInt(var0 + 8L);
      var5.wS = var2.readLEInt(var0 + 12L) & 4294967295L;
      if (var5.wS == 0L) {
         return null;
      } else {
         var5.UT = var2.readLEInt(var0 + 16L) & 4294967295L;
         if (var5.UT == 0L) {
            return null;
         } else {
            var5.E = VirtualMemoryUtil.readNullTerminatedStringSafe(var2, var3 + var5.wS, 256);
            return var5;
         }
      }
   }
}
