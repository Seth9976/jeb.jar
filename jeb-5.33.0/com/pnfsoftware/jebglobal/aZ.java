package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.units.code.asm.processor.memory.EncodedMemoryAreaList;

class aZ {
   private static final ZW kS = new ZW(Uf.pC, 1L, 16L, 0L, 1L);
   public static final Hu pC = new sG(Uf.pC).pC(1, BS.Er, new EncodedMemoryAreaList(BS.ld, BS.fI)).pC(2, BS.FE, BS.ld);
   public static final Hu A = pC;
   private static final tz[] wS = new tz[]{
      new UC(0, "VMLA", Uf.gp, BS.z, BS.Ek, A),
      new UC(1, "VMLA", Uf.gp, BS.ah, BS.eP, A),
      new UC(2, "VMLA", Uf.NS, BS.z, BS.Ek, A).pC(kS),
      new UC(3, "VMLA", Uf.NS, BS.ah, BS.eP, A).pC(kS),
      new UC(4, "VMLAL", Uf.wS, BS.ah, BS.Ek, A),
      new UC(5, "VMLAL", Uf.sY, BS.ah, BS.Ek, A),
      new UC(6, "VQDMLAL", Uf.wS, BS.ah, BS.Ek, pC),
      null,
      new UC(8, "VMLS", Uf.gp, BS.z, BS.Ek, A),
      new UC(9, "VMLS", Uf.gp, BS.ah, BS.eP, A),
      new UC(10, "VMLS", Uf.NS, BS.z, BS.Ek, A).pC(kS),
      new UC(11, "VMLS", Uf.NS, BS.ah, BS.eP, A).pC(kS),
      new UC(12, "VMLSL", Uf.wS, BS.ah, BS.Ek, A),
      new UC(13, "VMLSL", Uf.sY, BS.ah, BS.Ek, A),
      new UC(14, "VQDMLSL", Uf.wS, BS.ah, BS.Ek, pC),
      null,
      new UC(16, "VMUL", Uf.gp, BS.z, BS.Ek, pC),
      new UC(17, "VMUL", Uf.gp, BS.ah, BS.eP, pC),
      new UC(18, "VMUL", Uf.NS, BS.z, BS.Ek, pC).pC(kS),
      new UC(19, "VMUL", Uf.NS, BS.ah, BS.eP, pC).pC(kS),
      new UC(20, "VMULL", Uf.wS, BS.ah, BS.Ek, pC),
      new UC(21, "VMULL", Uf.sY, BS.ah, BS.Ek, pC),
      new UC(22, "VQDMULL", Uf.wS, BS.ah, BS.Ek, A),
      null,
      new UC(8, "VQDMULH", Uf.wS, BS.z, BS.Ek, A),
      new UC(9, "VQDMULH", Uf.wS, BS.ah, BS.eP, A),
      new UC(10, "VQRDMULH", Uf.wS, BS.z, BS.Ek, A),
      new UC(11, "VQRDMULH", Uf.wS, BS.ah, BS.eP, A),
      new UC(12, "VQRDMLAH", Uf.wS, BS.z, BS.Ek, A),
      new UC(13, "VQRDMLAH", Uf.wS, BS.ah, BS.eP, A),
      new UC(12, "VQRDMLSH", Uf.wS, BS.z, BS.Ek, A),
      new UC(13, "VQRDMLSH", Uf.wS, BS.ah, BS.eP, A)
   };

   public static tz pC(byte[] var0, int var1) {
      int var2 = var0[2] & 15;
      int var3 = Uf.pC(var0, var1);
      int var4 = Uf.pC(var0);
      return var4 != 0 && var4 != 3 ? wS[var2 << 1 | var3] : null;
   }
}
