package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.units.code.asm.processor.memory.DirectEncodedMemoryArea;
import com.pnfsoftware.jeb.core.units.code.asm.processor.memory.IEncodedMemoryArea;
import com.pnfsoftware.jeb.core.units.code.asm.processor.memory.VirtualEncodedMemoryArea;

public class Ma {
   private static final de q = new Zo.eo(null, DirectEncodedMemoryArea.get(6, 2), 2);
   private static final de RF = new Zo.eo(null, DirectEncodedMemoryArea.get(6, 2), 3);
   private static final Ef xK = q(DirectEncodedMemoryArea.get(4, 2));
   private static final Ef Dw = q(VirtualEncodedMemoryArea._0);
   private static final Je[] Uv = new Je[]{
      new Qg(0, "VST4", q, q(Y.sH, Y.YN, Y.Rv, Y.zx), xK),
      new Qg(1, "VST4", q, q(Y.sH, Y.Rv, Y.ZT, Y.Ri), xK),
      new Qg(2, "VST1", RF, q(Y.sH, Y.YN, Y.Rv, Y.zx), xK),
      new Qg(3, "VST2", q, q(Y.sH, Y.YN, Y.Rv, Y.zx), xK),
      new Qg(4, "VST3", q, q(Y.sH, Y.YN, Y.Rv), xK),
      new Qg(5, "VST3", q, q(Y.sH, Y.Rv, Y.ZT), xK),
      new Qg(6, "VST1", RF, q(Y.sH, Y.YN, Y.Rv), xK),
      new Qg(7, "VST1", RF, q(Y.sH), xK),
      new Qg(8, "VST2", q, q(Y.sH, Y.YN), xK),
      new Qg(9, "VST2", q, q(Y.sH, Y.Rv), xK),
      new Qg(10, "VST1", RF, q(Y.sH, Y.YN), xK),
      null,
      null,
      null,
      null,
      null
   };
   private static final Je[] oW = new Je[]{
      new Qg(0, "VST1", Zo.LK, q(1), Dw),
      new Qg(1, "VST2", Zo.LK, q(2), q(DirectEncodedMemoryArea.get(4, 1), null, 16)),
      new Qg(2, "VST3", Zo.LK, q(3), Dw),
      new Qg(3, "VST4", Zo.LK, q(4), q(DirectEncodedMemoryArea.get(4, 1), null, 32)),
      new Qg(4, "VST1", Zo.io, q(VirtualEncodedMemoryArea._0, 1), q(DirectEncodedMemoryArea.get(4, 2), null, 16)),
      new Qg(5, "VST2", Zo.io, q(DirectEncodedMemoryArea.get(5, 1), 2), q(DirectEncodedMemoryArea.get(4, 1), null, 32)),
      new Qg(6, "VST3", Zo.io, q(DirectEncodedMemoryArea.get(5, 1), 3), Dw),
      new Qg(7, "VST4", Zo.io, q(DirectEncodedMemoryArea.get(5, 1), 4), q(DirectEncodedMemoryArea.get(4, 1), null, 64)),
      new Qg(8, "VST1", Zo.qa, RF(VirtualEncodedMemoryArea._0, 1), q(DirectEncodedMemoryArea.get(4, 3), null, null, null, 32)),
      new Qg(9, "VST2", Zo.qa, RF(DirectEncodedMemoryArea.get(6, 1), 2), q(DirectEncodedMemoryArea.get(4, 2), null, 64)),
      new Qg(10, "VST3", Zo.qa, RF(DirectEncodedMemoryArea.get(6, 1), 3), Dw),
      new Qg(11, "VST4", Zo.qa, RF(DirectEncodedMemoryArea.get(6, 1), 4), q(DirectEncodedMemoryArea.get(4, 2), null, 64, 128)),
      null,
      null,
      null,
      null
   };
   private static final Je[] gO = new Je[]{
      new Qg(0, "VLD4", q, q(Y.sH, Y.YN, Y.Rv, Y.zx), xK),
      new Qg(1, "VLD4", q, q(Y.sH, Y.Rv, Y.ZT, Y.Ri), xK),
      new Qg(2, "VLD1", RF, q(Y.sH, Y.YN, Y.Rv, Y.zx), xK),
      new Qg(3, "VLD2", q, q(Y.sH, Y.YN, Y.Rv, Y.zx), xK),
      new Qg(4, "VLD3", q, q(Y.sH, Y.YN, Y.Rv), xK),
      new Qg(5, "VLD3", q, q(Y.sH, Y.Rv, Y.ZT), xK),
      new Qg(6, "VLD1", RF, q(Y.sH, Y.YN, Y.Rv), xK),
      new Qg(7, "VLD1", RF, q(Y.sH), xK),
      new Qg(8, "VLD2", q, q(Y.sH, Y.YN), xK),
      new Qg(9, "VLD2", q, q(Y.sH, Y.Rv), xK),
      new Qg(10, "VLD1", RF, q(Y.sH, Y.YN), xK),
      null,
      null,
      null,
      null,
      null
   };
   private static final Je[] nf = new Je[]{
      new Qg(0, "VLD1", Zo.LK, q(1), Dw),
      new Qg(1, "VLD2", Zo.LK, q(2), q(DirectEncodedMemoryArea.get(4, 1), null, 16)),
      new Qg(2, "VLD3", Zo.LK, q(3), Dw),
      new Qg(3, "VLD4", Zo.LK, q(4), q(DirectEncodedMemoryArea.get(4, 1), null, 32)),
      new Qg(4, "VLD1", Zo.io, q(VirtualEncodedMemoryArea._0, 1), q(DirectEncodedMemoryArea.get(4, 2), null, 16)),
      new Qg(5, "VLD2", Zo.io, q(DirectEncodedMemoryArea.get(5, 1), 2), q(DirectEncodedMemoryArea.get(4, 1), null, 32)),
      new Qg(6, "VLD3", Zo.io, q(DirectEncodedMemoryArea.get(5, 1), 3), Dw),
      new Qg(7, "VLD4", Zo.io, q(DirectEncodedMemoryArea.get(5, 1), 4), q(DirectEncodedMemoryArea.get(4, 1), null, 64)),
      new Qg(8, "VLD1", Zo.qa, RF(VirtualEncodedMemoryArea._0, 1), q(DirectEncodedMemoryArea.get(4, 3), null, null, null, 32)),
      new Qg(9, "VLD2", Zo.qa, RF(DirectEncodedMemoryArea.get(6, 1), 2), q(DirectEncodedMemoryArea.get(4, 2), null, 64)),
      new Qg(10, "VLD3", Zo.qa, RF(DirectEncodedMemoryArea.get(6, 1), 3), Dw),
      new Qg(11, "VLD4", Zo.qa, RF(DirectEncodedMemoryArea.get(6, 1), 4), q(DirectEncodedMemoryArea.get(4, 2), null, 64, 128)),
      null,
      null,
      null,
      null
   };
   private static final IEncodedMemoryArea gP = DirectEncodedMemoryArea.get(4, 1);
   private static final IEncodedMemoryArea za = DirectEncodedMemoryArea.get(5, 1);
   private static final Je[] lm = new Je[]{
      new Qg(0, "VLD1", Zo.LK, RF(za), Dw),
      new Qg(4, "VLD1", Zo.io, RF(za), q(gP, null, 16)),
      new Qg(8, "VLD1", Zo.qa, RF(za), q(gP, null, 32)),
      null,
      new Qg(1, "VLD2", Zo.LK, xK(za, 2), q(gP, null, 16)),
      new Qg(5, "VLD2", Zo.io, xK(za, 2), q(gP, null, 32)),
      new Qg(9, "VLD2", Zo.qa, xK(za, 2), q(gP, null, 64)),
      null,
      new Qg(2, "VLD3", Zo.LK, xK(za, 3), Dw),
      new Qg(6, "VLD3", Zo.io, xK(za, 3), Dw),
      new Qg(10, "VLD3", Zo.qa, xK(za, 3), Dw),
      null,
      new Qg(3, "VLD4", Zo.LK, xK(za, 4), q(gP, null, 32)),
      new Qg(7, "VLD4", Zo.io, xK(za, 4), q(gP, null, 64)),
      new Qg(11, "VLD4", Zo.qa, xK(za, 4), q(gP, null, 64)),
      new Qg(11, "VLD4", Zo.qa, xK(za, 4), q(gP, null, 128))
   };

   public static xT q(Ef... var0) {
      return new xT(var0);
   }

   protected static Ef q(IEncodedMemoryArea var0, Integer... var1) {
      return new PD(DirectEncodedMemoryArea.get(16, 4), new dD(var0, var1), DirectEncodedMemoryArea.get(0, 4));
   }

   protected static Ef q(IEncodedMemoryArea var0) {
      return q(var0, null, 64, 128, 256);
   }

   public static le q(int var0) {
      return new le(DirectEncodedMemoryArea.get(5, 3), VirtualEncodedMemoryArea._0, var0);
   }

   public static le q(IEncodedMemoryArea var0, int var1) {
      return new le(DirectEncodedMemoryArea.get(6, 2), var0, var1);
   }

   public static le RF(IEncodedMemoryArea var0, int var1) {
      return new le(DirectEncodedMemoryArea.get(7, 1), var0, var1);
   }

   public static le xK(IEncodedMemoryArea var0, int var1) {
      return new le(null, var0, var1);
   }

   public static Ef RF(IEncodedMemoryArea var0) {
      return new le(null, VirtualEncodedMemoryArea._0, var0);
   }

   public static Je q(byte[] var0, int var1) {
      int var2 = (var0[1] & 32) >>> 5;
      int var3 = (var0[1] & 128) >>> 7;
      int var4 = var0[2] & 15;
      if (var2 == 0) {
         return var3 == 0 ? Uv[var4] : oW[var4];
      } else if (var3 == 0) {
         return gO[var4];
      } else {
         return var4 < 12 ? nf[var4] : lm[(var4 & 3) << 2 | (var0[3] & 192) >>> 6];
      }
   }
}
