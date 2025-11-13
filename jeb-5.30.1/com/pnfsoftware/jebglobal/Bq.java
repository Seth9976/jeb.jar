package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.units.code.asm.processor.ProcessorException;
import com.pnfsoftware.jeb.core.units.code.asm.processor.memory.DirectEncodedMemoryArea;
import com.pnfsoftware.jeb.core.units.code.asm.processor.memory.EncodedMemoryAreaList;
import com.pnfsoftware.jeb.core.units.code.asm.processor.memory.FunctionEncodedMemoryArea;
import com.pnfsoftware.jeb.core.units.code.asm.processor.memory.IEncodedMemoryArea;
import com.pnfsoftware.jeb.core.units.code.asm.processor.memory.VirtualEncodedMemoryArea;

public class Bq extends rR {
   private static final int FG = 5;
   private static final int Al = 4;
   private static final int Kn = 3;
   private static final int vh = 8388620;
   private static final int Rd = 1048589;
   private static final int Eq = 1048591;
   private static final int hP = 4194318;
   private static final IEncodedMemoryArea wN = DirectEncodedMemoryArea.get(0, 5);
   private static final IEncodedMemoryArea Uc = DirectEncodedMemoryArea.get(5, 5);
   private static final IEncodedMemoryArea TB = DirectEncodedMemoryArea.get(16, 5);
   private static final IEncodedMemoryArea dg = DirectEncodedMemoryArea.get(0, 3);
   private static final IEncodedMemoryArea hw = DirectEncodedMemoryArea.get(10, 3);
   private static final IEncodedMemoryArea gm = DirectEncodedMemoryArea.get(13, 3);
   private static final IEncodedMemoryArea uY = DirectEncodedMemoryArea.get(16, 3);
   private static final IEncodedMemoryArea sc = DirectEncodedMemoryArea.get(0, 4);
   private static final IEncodedMemoryArea wQ = DirectEncodedMemoryArea.get(1, 3);
   private static final IEncodedMemoryArea Oj = DirectEncodedMemoryArea.get(5, 3);
   private static final IEncodedMemoryArea VW = DirectEncodedMemoryArea.get(5, 4);
   private static final IEncodedMemoryArea ap = DirectEncodedMemoryArea.get(6, 4);
   private static final IEncodedMemoryArea RL = DirectEncodedMemoryArea.get(10, 4);
   private static final IEncodedMemoryArea hy = DirectEncodedMemoryArea.get(16, 4);
   private static final IEncodedMemoryArea Xi = DirectEncodedMemoryArea.get(16, 5);
   private static final IEncodedMemoryArea Ag = new EncodedMemoryAreaList(VirtualEncodedMemoryArea._1, dg);
   private static final IEncodedMemoryArea rp = new EncodedMemoryAreaList(VirtualEncodedMemoryArea._1, Oj);
   public static final IEncodedMemoryArea Dw = new EncodedMemoryAreaList(DirectEncodedMemoryArea.get(22, 2), DirectEncodedMemoryArea.get(19, 2));
   public static final IEncodedMemoryArea Uv = new EncodedMemoryAreaList(DirectEncodedMemoryArea.get(22, 2), DirectEncodedMemoryArea.get(8, 2));
   public static final IEncodedMemoryArea oW = new EncodedMemoryAreaList(DirectEncodedMemoryArea.get(22, 1), DirectEncodedMemoryArea.get(18, 3));
   public static final Dm gO = new qh("/M");
   public static final Dm nf = new qh("/Z");
   private final Dm CW;
   private static final Dm qm = Dm.q(-1, var0 -> XD.Uv.decodeInt(var0) - 1);
   public static final Dm gP = new At(4);
   public static final IEncodedMemoryArea za = dX.Uv(Dw);
   public static final IEncodedMemoryArea lm = new FunctionEncodedMemoryArea(Dw.getLength(), var0 -> {
      int var1 = k.q((long)Dw.decodeInt(var0));
      return var1 == -1 ? -1L : var1 + 1L;
   });
   public static final IEncodedMemoryArea zz = dX.Uv(Uv);
   public static final IEncodedMemoryArea JY = dX.Dw(hy);
   public static final IEncodedMemoryArea HF = dX.Dw(Xi);
   public static final IEncodedMemoryArea LK = dX.Dw(oW);
   public static final Dm io = Dm.q(4, zz);
   public static final Dm qa = Dm.q(4, za);
   public static final Dm Hk = Dm.q(4, lm);
   public static final Dm Me = Dm.q(4, JY);
   public static final Dm PV = Dm.q(4, HF);
   public static final Dm oQ = Dm.q(4, LK);
   public static final Bq xW = new Bq(8388620, wN);
   public static final Bq KT = q(wN, Dm.q);
   public static final Bq Gf = q(wN, Dm.RF);
   public static final Bq Ef = q(wN, Dm.xK);
   public static final Bq cC = q(wN, Dm.Dw);
   public static final Bq sH = q(wN, Dm.Uv);
   public static final Bq CE = q(wN, Dm.HF);
   public static final Bq wF = q(wN, Dm.qa);
   public static final Bq If = q(wN, Dm.Hk);
   public static final Bq Dz = q(wN, Dm.Me);
   public static final Bq mI = q(wN, Dm.PV);
   public static final Bq jq = q(wN, Dm.io);
   public static final Bq ui = q(wN, qm);
   public static final Bq TX = q(wN, gP);
   public static final Bq Rr = q(wN, qa);
   public static final Bq EB = q(wN, Hk);
   public static final Bq Xo = q(wN, io);
   public static final Bq Bu = q(wN, Me);
   public static final Bq IN = q(wN, PV);
   public static final Bq rL = new Bq(8388620, Uc);
   public static final Bq eJ = q(Uc, Dm.q);
   public static final Bq YN = q(Uc, Dm.RF);
   public static final Bq Rv = q(Uc, Dm.xK);
   public static final Bq zx = q(Uc, Dm.Dw);
   public static final Bq ZT = q(Uc, Dm.Uv);
   public static final Bq Ri = q(Uc, Dm.HF);
   public static final Bq GY = q(Uc, Dm.Hk);
   public static final Bq Wx = q(Uc, Dm.LK);
   public static final Bq AB = q(Uc, Dm.io);
   public static final Bq CY = q(Uc, qm);
   public static final Bq WI = q(Uc, qa);
   public static final Bq Tq = q(Uc, Hk);
   public static final Bq Yp = q(Uc, Me);
   public static final Bq Gu = q(Uc, PV);
   public static final Bq nY = q(dX.xK(Uc), Dm.HF);
   public static final Bq lF = q(dX.xK(Uc), Dm.q);
   public static final Bq nq = q(dX.xK(Uc), Dm.RF);
   public static final Bq NX = q(EncodedMemoryAreaList.shift(ap, 1), Dm.xK);
   public static final Bq br = q(dX.q(EncodedMemoryAreaList.shift(ap, 1)), Dm.xK);
   public static final Bq tW = new Bq(8388620, TB);
   public static final Bq ZA = q(TB, Dm.HF);
   public static final Bq Ov = q(TB, Dm.LK);
   public static final Bq Lj = q(TB, Dm.io);
   public static final Bq nv = q(TB, qm);
   public static final Bq LL = q(TB, Dm.q);
   public static final Bq PQ = q(TB, Dm.RF);
   public static final Bq fQ = q(TB, Dm.xK);
   public static final Bq fi = q(TB, Dm.Dw);
   public static final Bq bl = q(TB, Dm.Uv);
   public static final Bq jb = q(uY, Dm.q);
   public static final Bq pQ = q(uY, Dm.RF);
   public static final Bq kf = q(uY, Dm.xK);
   public static final Bq GM = q(hy, Dm.RF);
   public static final Bq TQ = q(hy, Dm.xK);
   public static final Bq Yw = q(hy, Dm.Dw);
   public static final Bq IY = new Bq(1048589, sc);
   public static final Bq qR = new Bq(1048589, sc, Dm.HF);
   public static final Bq YA = new Bq(1048589, sc, Dm.q);
   public static final Bq fw = new Bq(1048589, sc, Dm.RF);
   public static final Bq Wp = new Bq(1048589, sc, Dm.xK);
   public static final Bq cY = new Bq(1048589, sc, Dm.Dw);
   public static final Bq PY = new Bq(1048589, EncodedMemoryAreaList.shift(wQ, 1), Dm.HF);
   public static final Bq cR = new Bq(1048589, dX.q(EncodedMemoryAreaList.shift(wQ, 1)), Dm.HF);
   public static final Bq eC = new Bq(1048589, dX.xK(sc), Dm.HF);
   public static final Bq ND = new Bq(1048589, VW);
   public static final Bq Qu = new Bq(1048589, VW, Dm.HF);
   public static final Bq jh = new Bq(1048589, VW, Dm.q);
   public static final Bq Jf = new Bq(1048589, VW, Dm.RF);
   public static final Bq vC = new Bq(1048589, VW, Dm.xK);
   public static final Bq of = new Bq(1048589, VW, Dm.Dw);
   public static final Bq os = new Bq(1048589, hy, Dm.HF);
   public static final Bq iu = new Bq(1048589, hy, Dm.q);
   public static final Bq fn = new Bq(1048589, VW, oQ);
   public static final Bq ZU = new Bq(1048589, VW, nf);
   public static final Bq Sz = new Bq(1048589, hw);
   public static final Bq fq = new Bq(1048589, hw, gO);
   public static final Bq mJ = new Bq(1048589, hw, nf);
   public static final Bq Bs = new Bq(
      1048589, hw, var0 -> DirectEncodedMemoryArea.get(16, 1).decodeInt(var0) == 0 ? nf.getDataType(var0) : gO.getDataType(var0)
   );
   public static final Bq rV = new Bq(1048589, RL);
   public static final Bq WX = new Bq(1048589, RL, gO);
   public static final Bq CB = new Bq(1048589, RL, nf);
   public static final Bq C = new Bq(1048589, RL, var0 -> DirectEncodedMemoryArea.get(4, 1).decodeInt(var0) == 0 ? nf.getDataType(var0) : gO.getDataType(var0));
   public static final Bq GC = new Bq(1048589, gm, gO);
   public static final Bq KF = new Bq(1048589, hy, gO);
   public static final Bq rk = new Bq(1048589, hy, nf);
   public static final Bq cy = new Bq(1048591, VW, Dm.HF);
   public static final Bq jk = new Bq(1048591, Ag, Dm.HF);
   public static final Bq Cl = new Bq(1048591, rp);
   public static final Bq hM = new Bq(4194318, VirtualEncodedMemoryArea.get(16, 5));
   public static final Bq kv = new Bq(4194318, dg, Dm.Dw);
   public static final Bq oS = new Bq(4194318, dg, Dm.xK, 3);

   private Bq(int var1, IEncodedMemoryArea var2) {
      this(var1, var2, null, (1 << var2.getLength()) - 1);
   }

   public Bq(int var1, IEncodedMemoryArea var2, Dm var3) {
      this(var1, var2, var3, -1);
   }

   public Bq(int var1, IEncodedMemoryArea var2, Dm var3, int var4) {
      super(var1, jD.eo.q, var2, var4);
      this.CW = var3;
   }

   public static Bq q(IEncodedMemoryArea var0, Dm var1) {
      return new Bq(8388620, var0, var1, 31);
   }

   @Override
   public boolean Dw(byte[] var1) {
      if (!super.Dw(var1)) {
         return false;
      } else {
         Integer var2 = super.RF(var1);
         if (var2 == null) {
            return true;
         } else {
            if (this.CW != null) {
               try {
                  CharSequence var3 = this.CW.getDataType(var1);
                  if (var3 == null) {
                     return false;
                  }
               } catch (eK var4) {
                  return false;
               }
            }

            return true;
         }
      }
   }

   @Override
   public CW buildOperand(byte[] var1, int var2) throws ProcessorException {
      Integer var3 = this.RF(var1);
      if (var3 == null) {
         return null;
      } else if (this.CW == null) {
         return com.pnfsoftware.jebglobal.GC.q(this.Uv(var1), var3, var2, this.q());
      } else {
         try {
            CharSequence var4 = this.CW.getDataType(var1);
            if (var4 != null) {
               RI var5 = new RI(this.Uv(var1), var3, var2, this.q(), var4.toString());
               if (var4.charAt(0) == '/') {
                  var5.q("");
               }

               return var5;
            } else {
               throw new ProcessorException("Illegal datatype");
            }
         } catch (eK var6) {
            throw new ProcessorException(var6);
         }
      }
   }

   @Override
   public boolean oW(byte[] var1) {
      return false;
   }

   public static int gO(byte[] var0) throws eK {
      int var1 = DirectEncodedMemoryArea.get(5, 13).decodeInt(var0);
      if ((var1 & 4096) != 0) {
         return 3;
      } else {
         int var2 = var1 & 63;
         if ((var2 & 32) == 0) {
            return 2;
         } else if ((var2 & 16) == 0) {
            return 1;
         } else if ((var2 & 62) == 62) {
            throw new eK("Reserved Bit mask");
         } else {
            return 0;
         }
      }
   }
}
