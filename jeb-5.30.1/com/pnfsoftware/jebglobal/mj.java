package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.units.code.asm.processor.memory.EncodedMemoryAreaList;

class mj {
   private static final dD xK = new dD(Zo.q, 1L, 16L, 0L, 1L);
   public static final Ef q = new WD(Zo.q).q(1, Y.If, new EncodedMemoryAreaList(Y.zz, Y.LK)).q(2, Y.Dz, Y.zz);
   public static final Ef RF = q;
   private static final Je[] Dw = new Je[]{
      new Qg(0, "VMLA", Zo.za, Y.sH, Y.CE, RF),
      new Qg(1, "VMLA", Zo.za, Y.xW, Y.KT, RF),
      new Qg(2, "VMLA", Zo.HF, Y.sH, Y.CE, RF).q(xK),
      new Qg(3, "VMLA", Zo.HF, Y.xW, Y.KT, RF).q(xK),
      new Qg(4, "VMLAL", Zo.Dw, Y.xW, Y.CE, RF),
      new Qg(5, "VMLAL", Zo.gO, Y.xW, Y.CE, RF),
      new Qg(6, "VQDMLAL", Zo.Dw, Y.xW, Y.CE, q),
      null,
      new Qg(8, "VMLS", Zo.za, Y.sH, Y.CE, RF),
      new Qg(9, "VMLS", Zo.za, Y.xW, Y.KT, RF),
      new Qg(10, "VMLS", Zo.HF, Y.sH, Y.CE, RF).q(xK),
      new Qg(11, "VMLS", Zo.HF, Y.xW, Y.KT, RF).q(xK),
      new Qg(12, "VMLSL", Zo.Dw, Y.xW, Y.CE, RF),
      new Qg(13, "VMLSL", Zo.gO, Y.xW, Y.CE, RF),
      new Qg(14, "VQDMLSL", Zo.Dw, Y.xW, Y.CE, q),
      null,
      new Qg(16, "VMUL", Zo.za, Y.sH, Y.CE, q),
      new Qg(17, "VMUL", Zo.za, Y.xW, Y.KT, q),
      new Qg(18, "VMUL", Zo.HF, Y.sH, Y.CE, q).q(xK),
      new Qg(19, "VMUL", Zo.HF, Y.xW, Y.KT, q).q(xK),
      new Qg(20, "VMULL", Zo.Dw, Y.xW, Y.CE, q),
      new Qg(21, "VMULL", Zo.gO, Y.xW, Y.CE, q),
      new Qg(22, "VQDMULL", Zo.Dw, Y.xW, Y.CE, RF),
      null,
      new Qg(8, "VQDMULH", Zo.Dw, Y.sH, Y.CE, RF),
      new Qg(9, "VQDMULH", Zo.Dw, Y.xW, Y.KT, RF),
      new Qg(10, "VQRDMULH", Zo.Dw, Y.sH, Y.CE, RF),
      new Qg(11, "VQRDMULH", Zo.Dw, Y.xW, Y.KT, RF),
      new Qg(12, "VQRDMLAH", Zo.Dw, Y.sH, Y.CE, RF),
      new Qg(13, "VQRDMLAH", Zo.Dw, Y.xW, Y.KT, RF),
      new Qg(12, "VQRDMLSH", Zo.Dw, Y.sH, Y.CE, RF),
      new Qg(13, "VQRDMLSH", Zo.Dw, Y.xW, Y.KT, RF)
   };

   public static Je q(byte[] var0, int var1) {
      int var2 = var0[2] & 15;
      int var3 = Zo.q(var0, var1);
      int var4 = Zo.q(var0);
      return var4 != 0 && var4 != 3 ? Dw[var2 << 1 | var3] : null;
   }
}
