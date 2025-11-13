package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.units.code.asm.processor.memory.IEncodedMemoryArea;
import com.pnfsoftware.jeb.core.units.code.asm.processor.memory.VirtualEncodedMemoryArea;

public class ZW {
   public static final Long[] pC = new Long[]{1L, 1L, 1L, 1L, 1L, 1L, 1L, 1L, 1L, 1L, 1L, 1L, 1L, 1L, 1L, 0L};
   public static final Long[] A = new Long[]{
      1L, 1L, 1L, 1L, 1L, 1L, 1L, 1L, 1L, 1L, 1L, 1L, 1L, 1L, 1L, 1L, 1L, 1L, 1L, 1L, 1L, 1L, 1L, 1L, 1L, 1L, 1L, 1L, 1L, 1L, 1L, 0L
   };
   public static final Long[] kS = new Long[]{
      0L, 1L, 1L, 1L, 1L, 1L, 1L, 1L, 1L, 1L, 1L, 1L, 1L, 1L, 1L, 1L, 1L, 1L, 1L, 1L, 1L, 1L, 1L, 1L, 1L, 1L, 1L, 1L, 1L, 1L, 1L, 1L
   };
   private IEncodedMemoryArea wS;
   private Object[] UT;

   @SafeVarargs
   public ZW(IEncodedMemoryArea var1, Object... var2) {
      this.wS = var1;
      this.UT = var2;
   }

   @SafeVarargs
   public ZW(Object... var1) {
      this.wS = VirtualEncodedMemoryArea._0;
      this.UT = var1;
   }

   public Object A(byte[] var1) {
      int var2 = this.pC(var1);
      return var2 >= this.UT.length ? null : this.UT[var2];
   }

   public int pC(byte[] var1) {
      return this.wS.decodeInt(var1);
   }
}
