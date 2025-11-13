package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.units.code.asm.processor.ProcessorException;
import com.pnfsoftware.jeb.core.units.code.asm.processor.memory.DirectEncodedMemoryArea;
import com.pnfsoftware.jeb.core.units.code.asm.processor.memory.EncodedMemoryAreaList;
import com.pnfsoftware.jeb.core.units.code.asm.processor.memory.FunctionEncodedMemoryArea;
import com.pnfsoftware.jeb.core.units.code.asm.processor.memory.IEncodedMemoryArea;
import com.pnfsoftware.jeb.core.units.code.asm.processor.memory.VirtualEncodedMemoryArea;

public class aP extends gZ {
   private static final IEncodedMemoryArea kk = DirectEncodedMemoryArea.get(0, 5);
   private static final IEncodedMemoryArea Rh = DirectEncodedMemoryArea.get(5, 5);
   private static final IEncodedMemoryArea vv = DirectEncodedMemoryArea.get(16, 5);
   private static final IEncodedMemoryArea fn = DirectEncodedMemoryArea.get(0, 3);
   private static final IEncodedMemoryArea AS = DirectEncodedMemoryArea.get(10, 3);
   private static final IEncodedMemoryArea wF = DirectEncodedMemoryArea.get(13, 3);
   private static final IEncodedMemoryArea hF = DirectEncodedMemoryArea.get(16, 3);
   private static final IEncodedMemoryArea FA = DirectEncodedMemoryArea.get(0, 4);
   private static final IEncodedMemoryArea IK = DirectEncodedMemoryArea.get(1, 3);
   private static final IEncodedMemoryArea DM = DirectEncodedMemoryArea.get(5, 3);
   private static final IEncodedMemoryArea IQ = DirectEncodedMemoryArea.get(5, 4);
   private static final IEncodedMemoryArea zR = DirectEncodedMemoryArea.get(6, 4);
   private static final IEncodedMemoryArea Ft = DirectEncodedMemoryArea.get(10, 4);
   private static final IEncodedMemoryArea kt = DirectEncodedMemoryArea.get(16, 4);
   private static final IEncodedMemoryArea Yw = DirectEncodedMemoryArea.get(16, 5);
   private static final IEncodedMemoryArea uD = new EncodedMemoryAreaList(VirtualEncodedMemoryArea._1, fn);
   private static final IEncodedMemoryArea ZY = new EncodedMemoryAreaList(VirtualEncodedMemoryArea._1, DM);
   public static final IEncodedMemoryArea pC = new EncodedMemoryAreaList(DirectEncodedMemoryArea.get(22, 2), DirectEncodedMemoryArea.get(19, 2));
   public static final IEncodedMemoryArea A = new EncodedMemoryAreaList(DirectEncodedMemoryArea.get(22, 2), DirectEncodedMemoryArea.get(8, 2));
   public static final IEncodedMemoryArea kS = new EncodedMemoryAreaList(DirectEncodedMemoryArea.get(22, 1), DirectEncodedMemoryArea.get(18, 3));
   public static final IX wS = new bH("/M");
   public static final IX UT = new bH("/Z");
   private final IX mK;
   private static final IX pW = IX.pC(-1, var0 -> ER.pC.decodeInt(var0) - 1);
   public static final IX E = new qU(4);
   public static final IEncodedMemoryArea sY = TN.UT(pC);
   public static final IEncodedMemoryArea ys = new FunctionEncodedMemoryArea(pC.getLength(), var0 -> {
      int var1 = Gq.pC((long)pC.decodeInt(var0));
      return var1 == -1 ? -1L : var1 + 1L;
   });
   public static final IEncodedMemoryArea ld = TN.UT(A);
   public static final IEncodedMemoryArea gp = TN.wS(kt);
   public static final IEncodedMemoryArea oT = TN.wS(Yw);
   public static final IEncodedMemoryArea fI = TN.wS(kS);
   public static final IX WR = IX.pC(4, ld);
   public static final IX NS = IX.pC(4, sY);
   public static final IX vP = IX.pC(4, ys);
   public static final IX xC = IX.pC(4, gp);
   public static final IX ED = IX.pC(4, oT);
   public static final IX Sc = IX.pC(4, fI);
   public static final aP ah = new aP(8388620, kk);
   public static final aP eP = pC(kk, IX.pC);
   public static final aP UO = pC(kk, IX.A);
   public static final aP Ab = pC(kk, IX.kS);
   public static final aP rl = pC(kk, IX.wS);
   public static final aP z = pC(kk, IX.UT);
   public static final aP Ek = pC(kk, IX.NS);
   public static final aP hK = pC(kk, IX.ED);
   public static final aP Er = pC(kk, IX.Sc);
   public static final aP FE = pC(kk, IX.ah);
   public static final aP Aj = pC(kk, IX.eP);
   public static final aP EX = pC(kk, IX.xC);
   public static final aP LM = pC(kk, pW);
   public static final aP mv = pC(kk, E);
   public static final aP sO = pC(kk, NS);
   public static final aP os = pC(kk, vP);
   public static final aP Cu = pC(kk, WR);
   public static final aP hZ = pC(kk, xC);
   public static final aP UW = pC(kk, ED);
   public static final aP PR = new aP(8388620, Rh);
   public static final aP cX = pC(Rh, IX.pC);
   public static final aP DQ = pC(Rh, IX.A);
   public static final aP ZN = pC(Rh, IX.kS);
   public static final aP OB = pC(Rh, IX.wS);
   public static final aP pF = pC(Rh, IX.UT);
   public static final aP Bc = pC(Rh, IX.NS);
   public static final aP OI = pC(Rh, IX.Sc);
   public static final aP Bf = pC(Rh, IX.vP);
   public static final aP Pe = pC(Rh, IX.xC);
   public static final aP ck = pC(Rh, pW);
   public static final aP RW = pC(Rh, NS);
   public static final aP e = pC(Rh, vP);
   public static final aP xM = pC(Rh, xC);
   public static final aP kU = pC(Rh, ED);
   public static final aP Kq = pC(TN.kS(Rh), IX.NS);
   public static final aP go = pC(TN.kS(Rh), IX.pC);
   public static final aP JF = pC(TN.kS(Rh), IX.A);
   public static final aP Nq = pC(EncodedMemoryAreaList.shift(zR, 1), IX.kS);
   public static final aP pg = pC(TN.pC(EncodedMemoryAreaList.shift(zR, 1)), IX.kS);
   public static final aP gj = new aP(8388620, vv);
   public static final aP ZD = pC(vv, IX.NS);
   public static final aP DL = pC(vv, IX.vP);
   public static final aP UH = pC(vv, IX.xC);
   public static final aP VD = pC(vv, pW);
   public static final aP Xs = pC(vv, IX.pC);
   public static final aP KV = pC(vv, IX.A);
   public static final aP FK = pC(vv, IX.kS);
   public static final aP Bi = pC(vv, IX.wS);
   public static final aP wQ = pC(vv, IX.UT);
   public static final aP PZ = pC(hF, IX.pC);
   public static final aP Ip = pC(hF, IX.A);
   public static final aP Fm = pC(hF, IX.kS);
   public static final aP FM = pC(kt, IX.A);
   public static final aP Wn = pC(kt, IX.kS);
   public static final aP gy = pC(kt, IX.wS);
   public static final aP pt = new aP(1048589, FA);
   public static final aP uE = new aP(1048589, FA, IX.NS);
   public static final aP Um = new aP(1048589, FA, IX.pC);
   public static final aP Ta = new aP(1048589, FA, IX.A);
   public static final aP So = new aP(1048589, FA, IX.kS);
   public static final aP tH = new aP(1048589, FA, IX.wS);
   public static final aP Gm = new aP(1048589, EncodedMemoryAreaList.shift(IK, 1), IX.NS);
   public static final aP Br = new aP(1048589, TN.pC(EncodedMemoryAreaList.shift(IK, 1)), IX.NS);
   public static final aP IE = new aP(1048589, TN.kS(FA), IX.NS);
   public static final aP AU = new aP(1048589, IQ);
   public static final aP jS = new aP(1048589, IQ, IX.NS);
   public static final aP KK = new aP(1048589, IQ, IX.pC);
   public static final aP oB = new aP(1048589, IQ, IX.A);
   public static final aP Rq = new aP(1048589, IQ, IX.kS);
   public static final aP LL = new aP(1048589, IQ, IX.wS);
   public static final aP rC = new aP(1048589, kt, IX.NS);
   public static final aP be = new aP(1048589, kt, IX.pC);
   public static final aP Xh = new aP(1048589, IQ, Sc);
   public static final aP sz = new aP(1048589, IQ, UT);
   public static final aP QQ = new aP(1048589, AS);
   public static final aP eE = new aP(1048589, AS, wS);
   public static final aP dM = new aP(1048589, AS, UT);
   public static final aP EM = new aP(
      1048589, AS, var0 -> DirectEncodedMemoryArea.get(16, 1).decodeInt(var0) == 0 ? UT.getDataType(var0) : wS.getDataType(var0)
   );
   public static final aP fD = new aP(1048589, Ft);
   public static final aP ii = new aP(1048589, Ft, wS);
   public static final aP Gu = new aP(1048589, Ft, UT);
   public static final aP hw = new aP(1048589, Ft, var0 -> DirectEncodedMemoryArea.get(4, 1).decodeInt(var0) == 0 ? UT.getDataType(var0) : wS.getDataType(var0));
   public static final aP qG = new aP(1048589, wF, wS);
   public static final aP yi = new aP(1048589, kt, wS);
   public static final aP zK = new aP(1048589, kt, UT);
   public static final aP LA = new aP(1048591, IQ, IX.NS);
   public static final aP ve = new aP(1048591, uD, IX.NS);
   public static final aP yv = new aP(1048591, ZY);
   public static final aP MZ = new aP(4194318, VirtualEncodedMemoryArea.get(16, 5));
   public static final aP fH = new aP(4194318, fn, IX.wS);
   public static final aP ET = new aP(4194318, fn, IX.kS, 3);

   private aP(int var1, IEncodedMemoryArea var2) {
      this(var1, var2, null, (1 << var2.getLength()) - 1);
   }

   public aP(int var1, IEncodedMemoryArea var2, IX var3) {
      this(var1, var2, var3, -1);
   }

   public aP(int var1, IEncodedMemoryArea var2, IX var3, int var4) {
      super(var1, Ll.Av.pC, var2, var4);
      this.mK = var3;
   }

   public static aP pC(IEncodedMemoryArea var0, IX var1) {
      return new aP(8388620, var0, var1, 31);
   }

   @Override
   public boolean wS(byte[] var1) {
      if (!super.wS(var1)) {
         return false;
      } else {
         Integer var2 = super.A(var1);
         if (var2 == null) {
            return true;
         } else {
            if (this.mK != null) {
               try {
                  CharSequence var3 = this.mK.getDataType(var1);
                  if (var3 == null) {
                     return false;
                  }
               } catch (oJ var4) {
                  return false;
               }
            }

            return true;
         }
      }
   }

   @Override
   public Yg buildOperand(byte[] var1, int var2) throws ProcessorException {
      Integer var3 = this.A(var1);
      if (var3 == null) {
         return null;
      } else if (this.mK == null) {
         return LC.pC(this.UT(var1), var3, var2, this.pC());
      } else {
         try {
            CharSequence var4 = this.mK.getDataType(var1);
            if (var4 != null) {
               lw var5 = new lw(this.UT(var1), var3, var2, this.pC(), var4.toString());
               if (var4.charAt(0) == '/') {
                  var5.pC("");
               }

               return var5;
            } else {
               throw new ProcessorException("Illegal datatype");
            }
         } catch (oJ var6) {
            throw new ProcessorException(var6);
         }
      }
   }

   @Override
   public boolean E(byte[] var1) {
      return false;
   }

   public static int sY(byte[] var0) throws oJ {
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
            throw new oJ("Reserved Bit mask");
         } else {
            return 0;
         }
      }
   }
}
