package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.units.code.asm.processor.memory.DirectEncodedMemoryArea;
import com.pnfsoftware.jeb.util.collect.Maps;
import java.util.Map;

public class rh extends rB {
   private int oW = 0;
   private final boolean gO;
   private final Map nf;
   public static final Ef q = new rh(0, 16, true);
   public static final Ef RF = new rh(0, 8, false);
   public static final Ef xK = new rh(0, 16, false);
   public static final Ef Dw = new rh(0, 9, Maps.toMap(8, 14));
   public static final Ef Uv = new rh(0, 9, Maps.toMap(8, 15));

   public rh(int var1, int var2, boolean var3) {
      super(DirectEncodedMemoryArea.get(var1, var2));
      this.gO = var3;
      this.nf = null;
   }

   public rh(int var1, int var2, Map var3) {
      super(DirectEncodedMemoryArea.get(var1, var2));
      this.gO = false;
      this.nf = var3;
   }

   @Override
   protected int q(byte[] var1, int var2) {
      return (this.gO ? (var1[1] & 64) == 0 : !this.gO) ? 0 : 524288;
   }

   @Override
   protected CW q(int var1, int var2) {
      int var3 = var1;
      if (this.nf != null) {
         Integer var4 = (Integer)this.nf.get(var1);
         if (var4 != null) {
            var3 = var4;
         }
      }

      return GC.xK(this.oW, var3, var2);
   }
}
