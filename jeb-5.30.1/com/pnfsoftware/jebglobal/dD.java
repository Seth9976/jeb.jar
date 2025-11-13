package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.units.code.asm.processor.memory.IEncodedMemoryArea;
import com.pnfsoftware.jeb.core.units.code.asm.processor.memory.VirtualEncodedMemoryArea;

public class dD {
   public static final Long[] q = new Long[]{1L, 1L, 1L, 1L, 1L, 1L, 1L, 1L, 1L, 1L, 1L, 1L, 1L, 1L, 1L, 0L};
   public static final Long[] RF = new Long[]{
      1L, 1L, 1L, 1L, 1L, 1L, 1L, 1L, 1L, 1L, 1L, 1L, 1L, 1L, 1L, 1L, 1L, 1L, 1L, 1L, 1L, 1L, 1L, 1L, 1L, 1L, 1L, 1L, 1L, 1L, 1L, 0L
   };
   public static final Long[] xK = new Long[]{
      0L, 1L, 1L, 1L, 1L, 1L, 1L, 1L, 1L, 1L, 1L, 1L, 1L, 1L, 1L, 1L, 1L, 1L, 1L, 1L, 1L, 1L, 1L, 1L, 1L, 1L, 1L, 1L, 1L, 1L, 1L, 1L
   };
   private IEncodedMemoryArea Dw;
   private Object[] Uv;

   @SafeVarargs
   public dD(IEncodedMemoryArea var1, Object... var2) {
      this.Dw = var1;
      this.Uv = var2;
   }

   @SafeVarargs
   public dD(Object... var1) {
      this.Dw = VirtualEncodedMemoryArea._0;
      this.Uv = var1;
   }

   public Object RF(byte[] var1) {
      int var2 = this.q(var1);
      return var2 >= this.Uv.length ? null : this.Uv[var2];
   }

   public int q(byte[] var1) {
      return this.Dw.decodeInt(var1);
   }
}
