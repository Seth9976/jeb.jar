package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.units.code.asm.processor.memory.DirectEncodedMemoryArea;
import com.pnfsoftware.jeb.core.units.code.asm.processor.memory.EncodedMemoryAreaList;
import com.pnfsoftware.jeb.core.units.code.asm.processor.memory.IEncodedMemoryArea;

public class lO extends jD {
   public static final int q = 33554432;
   public static final int RF = 2097152;
   private final lO.eo lm;
   private final CW.CU zz;
   private final int JY;
   public static final IEncodedMemoryArea xK = new EncodedMemoryAreaList(DirectEncodedMemoryArea.get(26, 1), DirectEncodedMemoryArea.get(12, 3));
   public static final IEncodedMemoryArea Dw = new EncodedMemoryAreaList(
      DirectEncodedMemoryArea.get(26, 1), DirectEncodedMemoryArea.get(12, 3), DirectEncodedMemoryArea.get(0, 8)
   );
   public static final Ef Uv = new lO(lO.eo.q, jD.eo.Dw, 0);
   public static final Ef oW = new lO(lO.eo.q, jD.eo.Dw, 35651584);
   public static final Ef gO = new lO(lO.eo.q, CW.CU.RF, jD.eo.Dw, 0);
   public static final Ef nf = new lO(lO.eo.q, CW.CU.xK, jD.eo.Dw, 35651584);
   public static final Ef gP = new lO(lO.eo.RF, jD.eo.Dw, 0);
   public static final Ef za = new lO(lO.eo.RF, CW.CU.RF, jD.eo.Dw, 0);

   private lO(lO.eo var1, CW.CU var2, jD.eo var3, int var4) {
      super(var3);
      this.lm = var1;
      this.zz = var2;
      this.JY = var4;
   }

   private lO(lO.eo var1, jD.eo var2, int var3) {
      this(var1, CW.CU.q, var2, var3);
   }

   // $VF: Unable to simplify switch on enum
   // Please report this to the Vineflower issue tracker, at https://github.com/Vineflower/vineflower/issues with a copy of the class file (if you have the rights to distribute it!)
   protected long q(byte[] var1) {
      long var2 = switch (this.lm) {
         case RF -> {
            int var6 = var1[3] & 255;
            int var7 = k.RF(var1, xK);
            yield k.xK(var7, var6);
         }
         case q -> {
            int var4 = var1[2] & 15;
            int var5 = var1[3] & 255;
            yield k.RF(var4, var5);
         }
         default -> 0L;
      };
      if (this.zz == CW.CU.RF) {
         var2 = (int)var2 & 4294967295L;
      }

      return var2;
   }

   @Override
   public CW buildOperand(byte[] var1, int var2) {
      long var3 = this.q(var1);
      if ((this.JY & 2097152) != 0) {
         if (this.zz != null && this.zz == CW.CU.xK) {
            var3 = -var3;
         }

         return new fp(var3, var2, (this.JY & 33554432) != 0, false);
      } else {
         if (this.lm == lO.eo.q) {
            int var5 = var1[2] & 15;
            int var6 = var1[3] & 255;
            if (var5 != 0 && var3 == var6 >> 2 * var5) {
               return new IM((int)var3, 768, this.q(), this.zz, this.RF(), CW.q(this.RF(), this.zz, var6, 65536), CW.q(this.RF(), (long)(2 * var5)));
            }
         }

         return CW.q(this.RF(), this.zz, var3, this.q());
      }
   }

   protected int RF() {
      int var10000 = uy.q[this.lm.ordinal()];
      return 32;
   }

   public static enum eo {
      q,
      RF;
   }
}
