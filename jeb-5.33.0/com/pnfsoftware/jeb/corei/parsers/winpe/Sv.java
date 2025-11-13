package com.pnfsoftware.jeb.corei.parsers.winpe;

import com.pnfsoftware.jeb.core.units.code.asm.memory.IVirtualMemory;
import com.pnfsoftware.jeb.core.units.code.asm.memory.MemoryException;
import com.pnfsoftware.jeb.core.units.code.asm.memory.VirtualMemoryUtil;
import com.pnfsoftware.jeb.util.serialization.annotations.Ser;
import com.pnfsoftware.jeb.util.serialization.annotations.SerId;

@Ser
public class Sv {
   @SerId(1)
   public int pC;
   @SerId(2)
   public int A;
   @SerId(3)
   public int kS;
   @SerId(4)
   public int wS;
   @SerId(5)
   public long UT;
   @SerId(6)
   public int E;
   @SerId(7)
   public int sY;
   @SerId(8)
   public int ys;
   @SerId(9)
   public long ld;
   @SerId(10)
   public long gp;
   @SerId(11)
   public long oT;
   @SerId(12)
   public String fI;

   public static Sv pC(long var0, IVirtualMemory var2, long var3) throws MemoryException {
      Sv var5 = new Sv();
      var5.pC = var2.readLEInt(var0);
      var5.A = var2.readLEInt(var0 + 4L);
      var5.kS = var2.readLEShort(var0 + 8L) & '\uffff';
      var5.wS = var2.readLEShort(var0 + 10L) & '\uffff';
      var5.UT = var2.readLEInt(var0 + 12L) & 4294967295L;
      var5.E = var2.readLEInt(var0 + 16L) & 65535;
      var5.sY = var2.readLEInt(var0 + 20L);
      var5.ys = var2.readLEInt(var0 + 24L);
      var5.ld = var2.readLEInt(var0 + 28L) & 4294967295L;
      var5.gp = var2.readLEInt(var0 + 32L) & 4294967295L;
      var5.oT = var2.readLEInt(var0 + 36L) & 4294967295L;
      if (var5.UT != 0L) {
         var5.fI = VirtualMemoryUtil.readNullTerminatedStringSafe(var2, var3 + var5.UT, 256);
      }

      return var5;
   }
}
