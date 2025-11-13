package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.units.code.asm.processor.memory.DirectEncodedMemoryArea;
import com.pnfsoftware.jeb.core.units.code.asm.processor.memory.IEncodedMemoryArea;
import com.pnfsoftware.jeb.core.units.code.asm.processor.memory.VirtualEncodedMemoryArea;

public class Yw {
   private static final ji pC = new Uf.Av(null, DirectEncodedMemoryArea.get(6, 2), 2);
   private static final ji A = new Uf.Av(null, DirectEncodedMemoryArea.get(6, 2), 3);
   private static final Hu kS = pC(DirectEncodedMemoryArea.get(4, 2));
   private static final Hu wS = pC(VirtualEncodedMemoryArea._0);
   private static final tz[] UT = new tz[]{
      new UC(0, "VST4", pC, pC(BS.z, BS.DQ, BS.ZN, BS.OB), kS),
      new UC(1, "VST4", pC, pC(BS.z, BS.ZN, BS.pF, BS.Bc), kS),
      new UC(2, "VST1", A, pC(BS.z, BS.DQ, BS.ZN, BS.OB), kS),
      new UC(3, "VST2", pC, pC(BS.z, BS.DQ, BS.ZN, BS.OB), kS),
      new UC(4, "VST3", pC, pC(BS.z, BS.DQ, BS.ZN), kS),
      new UC(5, "VST3", pC, pC(BS.z, BS.ZN, BS.pF), kS),
      new UC(6, "VST1", A, pC(BS.z, BS.DQ, BS.ZN), kS),
      new UC(7, "VST1", A, pC(BS.z), kS),
      new UC(8, "VST2", pC, pC(BS.z, BS.DQ), kS),
      new UC(9, "VST2", pC, pC(BS.z, BS.ZN), kS),
      new UC(10, "VST1", A, pC(BS.z, BS.DQ), kS),
      null,
      null,
      null,
      null,
      null
   };
   private static final tz[] E = new tz[]{
      new UC(0, "VST1", Uf.vP, pC(1), wS),
      new UC(1, "VST2", Uf.vP, pC(2), pC(DirectEncodedMemoryArea.get(4, 1), null, 16)),
      new UC(2, "VST3", Uf.vP, pC(3), wS),
      new UC(3, "VST4", Uf.vP, pC(4), pC(DirectEncodedMemoryArea.get(4, 1), null, 32)),
      new UC(4, "VST1", Uf.xC, pC(VirtualEncodedMemoryArea._0, 1), pC(DirectEncodedMemoryArea.get(4, 2), null, 16)),
      new UC(5, "VST2", Uf.xC, pC(DirectEncodedMemoryArea.get(5, 1), 2), pC(DirectEncodedMemoryArea.get(4, 1), null, 32)),
      new UC(6, "VST3", Uf.xC, pC(DirectEncodedMemoryArea.get(5, 1), 3), wS),
      new UC(7, "VST4", Uf.xC, pC(DirectEncodedMemoryArea.get(5, 1), 4), pC(DirectEncodedMemoryArea.get(4, 1), null, 64)),
      new UC(8, "VST1", Uf.ED, A(VirtualEncodedMemoryArea._0, 1), pC(DirectEncodedMemoryArea.get(4, 3), null, null, null, 32)),
      new UC(9, "VST2", Uf.ED, A(DirectEncodedMemoryArea.get(6, 1), 2), pC(DirectEncodedMemoryArea.get(4, 2), null, 64)),
      new UC(10, "VST3", Uf.ED, A(DirectEncodedMemoryArea.get(6, 1), 3), wS),
      new UC(11, "VST4", Uf.ED, A(DirectEncodedMemoryArea.get(6, 1), 4), pC(DirectEncodedMemoryArea.get(4, 2), null, 64, 128)),
      null,
      null,
      null,
      null
   };
   private static final tz[] sY = new tz[]{
      new UC(0, "VLD4", pC, pC(BS.z, BS.DQ, BS.ZN, BS.OB), kS),
      new UC(1, "VLD4", pC, pC(BS.z, BS.ZN, BS.pF, BS.Bc), kS),
      new UC(2, "VLD1", A, pC(BS.z, BS.DQ, BS.ZN, BS.OB), kS),
      new UC(3, "VLD2", pC, pC(BS.z, BS.DQ, BS.ZN, BS.OB), kS),
      new UC(4, "VLD3", pC, pC(BS.z, BS.DQ, BS.ZN), kS),
      new UC(5, "VLD3", pC, pC(BS.z, BS.ZN, BS.pF), kS),
      new UC(6, "VLD1", A, pC(BS.z, BS.DQ, BS.ZN), kS),
      new UC(7, "VLD1", A, pC(BS.z), kS),
      new UC(8, "VLD2", pC, pC(BS.z, BS.DQ), kS),
      new UC(9, "VLD2", pC, pC(BS.z, BS.ZN), kS),
      new UC(10, "VLD1", A, pC(BS.z, BS.DQ), kS),
      null,
      null,
      null,
      null,
      null
   };
   private static final tz[] ys = new tz[]{
      new UC(0, "VLD1", Uf.vP, pC(1), wS),
      new UC(1, "VLD2", Uf.vP, pC(2), pC(DirectEncodedMemoryArea.get(4, 1), null, 16)),
      new UC(2, "VLD3", Uf.vP, pC(3), wS),
      new UC(3, "VLD4", Uf.vP, pC(4), pC(DirectEncodedMemoryArea.get(4, 1), null, 32)),
      new UC(4, "VLD1", Uf.xC, pC(VirtualEncodedMemoryArea._0, 1), pC(DirectEncodedMemoryArea.get(4, 2), null, 16)),
      new UC(5, "VLD2", Uf.xC, pC(DirectEncodedMemoryArea.get(5, 1), 2), pC(DirectEncodedMemoryArea.get(4, 1), null, 32)),
      new UC(6, "VLD3", Uf.xC, pC(DirectEncodedMemoryArea.get(5, 1), 3), wS),
      new UC(7, "VLD4", Uf.xC, pC(DirectEncodedMemoryArea.get(5, 1), 4), pC(DirectEncodedMemoryArea.get(4, 1), null, 64)),
      new UC(8, "VLD1", Uf.ED, A(VirtualEncodedMemoryArea._0, 1), pC(DirectEncodedMemoryArea.get(4, 3), null, null, null, 32)),
      new UC(9, "VLD2", Uf.ED, A(DirectEncodedMemoryArea.get(6, 1), 2), pC(DirectEncodedMemoryArea.get(4, 2), null, 64)),
      new UC(10, "VLD3", Uf.ED, A(DirectEncodedMemoryArea.get(6, 1), 3), wS),
      new UC(11, "VLD4", Uf.ED, A(DirectEncodedMemoryArea.get(6, 1), 4), pC(DirectEncodedMemoryArea.get(4, 2), null, 64, 128)),
      null,
      null,
      null,
      null
   };
   private static final IEncodedMemoryArea ld = DirectEncodedMemoryArea.get(4, 1);
   private static final IEncodedMemoryArea gp = DirectEncodedMemoryArea.get(5, 1);
   private static final tz[] oT = new tz[]{
      new UC(0, "VLD1", Uf.vP, A(gp), wS),
      new UC(4, "VLD1", Uf.xC, A(gp), pC(ld, null, 16)),
      new UC(8, "VLD1", Uf.ED, A(gp), pC(ld, null, 32)),
      null,
      new UC(1, "VLD2", Uf.vP, kS(gp, 2), pC(ld, null, 16)),
      new UC(5, "VLD2", Uf.xC, kS(gp, 2), pC(ld, null, 32)),
      new UC(9, "VLD2", Uf.ED, kS(gp, 2), pC(ld, null, 64)),
      null,
      new UC(2, "VLD3", Uf.vP, kS(gp, 3), wS),
      new UC(6, "VLD3", Uf.xC, kS(gp, 3), wS),
      new UC(10, "VLD3", Uf.ED, kS(gp, 3), wS),
      null,
      new UC(3, "VLD4", Uf.vP, kS(gp, 4), pC(ld, null, 32)),
      new UC(7, "VLD4", Uf.xC, kS(gp, 4), pC(ld, null, 64)),
      new UC(11, "VLD4", Uf.ED, kS(gp, 4), pC(ld, null, 64)),
      new UC(11, "VLD4", Uf.ED, kS(gp, 4), pC(ld, null, 128))
   };

   public static Fw pC(Hu... var0) {
      return new Fw(var0);
   }

   protected static Hu pC(IEncodedMemoryArea var0, Integer... var1) {
      return new ru(DirectEncodedMemoryArea.get(16, 4), new ZW(var0, var1), DirectEncodedMemoryArea.get(0, 4));
   }

   protected static Hu pC(IEncodedMemoryArea var0) {
      return pC(var0, null, 64, 128, 256);
   }

   public static Wp pC(int var0) {
      return new Wp(DirectEncodedMemoryArea.get(5, 3), VirtualEncodedMemoryArea._0, var0);
   }

   public static Wp pC(IEncodedMemoryArea var0, int var1) {
      return new Wp(DirectEncodedMemoryArea.get(6, 2), var0, var1);
   }

   public static Wp A(IEncodedMemoryArea var0, int var1) {
      return new Wp(DirectEncodedMemoryArea.get(7, 1), var0, var1);
   }

   public static Wp kS(IEncodedMemoryArea var0, int var1) {
      return new Wp(null, var0, var1);
   }

   public static Hu A(IEncodedMemoryArea var0) {
      return new Wp(null, VirtualEncodedMemoryArea._0, var0);
   }

   public static tz pC(byte[] var0, int var1) {
      int var2 = (var0[1] & 32) >>> 5;
      int var3 = (var0[1] & 128) >>> 7;
      int var4 = var0[2] & 15;
      if (var2 == 0) {
         return var3 == 0 ? UT[var4] : E[var4];
      } else if (var3 == 0) {
         return sY[var4];
      } else {
         return var4 < 12 ? ys[var4] : oT[(var4 & 3) << 2 | (var0[3] & 192) >>> 6];
      }
   }
}
