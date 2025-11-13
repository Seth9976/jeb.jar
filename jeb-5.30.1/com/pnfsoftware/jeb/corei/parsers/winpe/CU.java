package com.pnfsoftware.jeb.corei.parsers.winpe;

import com.pnfsoftware.jeb.core.units.code.asm.memory.IVirtualMemory;
import com.pnfsoftware.jeb.core.units.code.asm.memory.MemoryException;
import com.pnfsoftware.jeb.core.units.code.asm.memory.VirtualMemoryUtil;
import com.pnfsoftware.jeb.util.serialization.annotations.Ser;
import com.pnfsoftware.jeb.util.serialization.annotations.SerId;

@Ser
public class CU {
   @SerId(1)
   public int q;
   @SerId(2)
   public int RF;
   @SerId(3)
   public int xK;
   @SerId(4)
   public int Dw;
   @SerId(5)
   public long Uv;
   @SerId(6)
   public int oW;
   @SerId(7)
   public int gO;
   @SerId(8)
   public int nf;
   @SerId(9)
   public long gP;
   @SerId(10)
   public long za;
   @SerId(11)
   public long lm;
   @SerId(12)
   public String zz;

   public static CU q(long var0, IVirtualMemory var2, long var3) throws MemoryException {
      CU var5 = new CU();
      var5.q = var2.readLEInt(var0);
      var5.RF = var2.readLEInt(var0 + 4L);
      var5.xK = var2.readLEShort(var0 + 8L) & '\uffff';
      var5.Dw = var2.readLEShort(var0 + 10L) & '\uffff';
      var5.Uv = var2.readLEInt(var0 + 12L) & 4294967295L;
      var5.oW = var2.readLEInt(var0 + 16L) & 65535;
      var5.gO = var2.readLEInt(var0 + 20L);
      var5.nf = var2.readLEInt(var0 + 24L);
      var5.gP = var2.readLEInt(var0 + 28L) & 4294967295L;
      var5.za = var2.readLEInt(var0 + 32L) & 4294967295L;
      var5.lm = var2.readLEInt(var0 + 36L) & 4294967295L;
      if (var5.Uv != 0L) {
         var5.zz = VirtualMemoryUtil.readNullTerminatedStringSafe(var2, var3 + var5.Uv, 256);
      }

      return var5;
   }
}
