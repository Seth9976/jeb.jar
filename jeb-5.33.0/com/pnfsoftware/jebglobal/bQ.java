package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.units.code.asm.processor.ProcessorException;
import com.pnfsoftware.jeb.core.units.code.asm.processor.memory.DirectEncodedMemoryArea;
import com.pnfsoftware.jeb.core.units.code.asm.processor.memory.EncodedMemoryAreaList;
import com.pnfsoftware.jeb.core.units.code.asm.processor.memory.IEncodedMemoryArea;
import com.pnfsoftware.jeb.core.units.code.asm.processor.memory.VirtualEncodedMemoryArea;

public class bQ {
   static final ji.Av pC = (var0, var1) -> !uV.pC(var1);
   public static final ji A = ji.Ek;
   private static final ji Bc = new DG(ji.DH.pC, ji.DH.A, ji.DH.UT);
   private static final ji OI = new dL(ji.DH.pC, ji.DH.A, ji.DH.wS, ji.DH.E);
   private static final ji Bf = new UT();
   private static final ji Pe = new Wi();
   private static final ji ck = new iY();
   private static final ji RW = new bQ.Av(ji.DH.pC, ji.DH.A, ji.DH.UT);
   private static final ji e = new bQ.Av(ji.DH.pC, ji.DH.A);
   private static final ji xM = new HD(ji.DH.pC, ji.DH.A).A(DirectEncodedMemoryArea.getThumb2(12, 0, 3)).A(DirectEncodedMemoryArea.getThumb2(6, 0, 2));
   private static final ji kU = new Wg(ji.DH.pC, ji.DH.wS).A(DirectEncodedMemoryArea.getThumb2(12, 0, 3)).A(DirectEncodedMemoryArea.getThumb2(4, 0, 4));
   private static final ji Kq = new hB(ji.DH.A, ji.DH.A).A(DirectEncodedMemoryArea.getThumb2(10, 1, 1)).A(DirectEncodedMemoryArea.getThumb2(12, 0, 3));
   public static final hg kS = hg.pC(258);
   public static final hg wS = hg.pC(2562);
   private static final Hu[] go = new Hu[]{wT.UT, wT.ys, Dj.A};
   public static final tz[] UT = new tz[]{
      pC(VZ.kS, go),
      pC(VZ.UT, go),
      pC(VZ.A, go),
      pC(VZ.E, go),
      pC(VZ.pC, go),
      pC(VZ.ld, go),
      pC(VZ.sY, go),
      pC(VZ.ys, go),
      new UC("TST", wT.ys, Dj.A),
      new UC("TEQ", wT.ys, Dj.A),
      new UC("CMP", wT.ys, Dj.A),
      new UC("CMN", wT.ys, Dj.A),
      pC(VZ.gp, go),
      null,
      pC(VZ.wS, go),
      kS(VZ.ED, wT.UT, Dj.A)
   };
   private static final IV JF = new IV(VirtualEncodedMemoryArea.get(32, 5));
   public static final tz[] E = new tz[]{
      kS(VZ.xC, wT.UT, Dj.A),
      pC(VZ.WR, wT.UT, wT.pC, JF),
      pC(VZ.NS, wT.UT, wT.pC, JF),
      kS(VZ.Sc, wT.UT, wT.pC),
      pC(VZ.fI, wT.UT, wT.pC, IV.Sc),
      pC(VZ.WR, wT.UT, wT.pC, IV.Sc),
      pC(VZ.NS, wT.UT, wT.pC, IV.Sc),
      pC(VZ.vP, wT.UT, wT.pC, IV.Sc)
   };
   private static final Hu[] Nq = new Hu[]{wT.UT, wT.ys, Dj.kS};
   public static final tz[] sY = new tz[]{
      A(VZ.kS, Nq),
      A(VZ.UT, Nq),
      A(VZ.A, Nq),
      A(VZ.E, Nq),
      A(VZ.pC, Nq),
      A(VZ.ld, Nq),
      A(VZ.sY, Nq),
      A(VZ.ys, Nq),
      new UC("TST", wT.ys, Dj.kS),
      new UC("TEQ", wT.ys, Dj.kS),
      new UC("CMP", wT.ys, Dj.kS),
      new UC("CMN", wT.ys, Dj.kS),
      A(VZ.gp, Nq),
      null,
      A(VZ.wS, Nq),
      wS(VZ.ED, wT.UT, Dj.kS)
   };
   public static final tz[] ys = new tz[]{
      A(VZ.fI, wT.UT, wT.pC, wT.wS), A(VZ.WR, wT.UT, wT.pC, wT.wS), A(VZ.NS, wT.UT, wT.pC, wT.wS), A(VZ.vP, wT.UT, wT.pC, wT.wS)
   };
   public static final tz ld = new UC("ADR", kS, wT.UT, com.pnfsoftware.jebglobal.NS.wS);
   public static final tz gp = new UC("ADR", kS, wT.UT, com.pnfsoftware.jebglobal.NS.E);
   private static final Hu[] pg = new Hu[]{wT.UT, wT.ys, com.pnfsoftware.jebglobal.NS.kS};
   public static final tz[] oT = new tz[]{
      pC(VZ.kS, pg),
      pC(VZ.UT, pg),
      pC(VZ.A, pg),
      pC(VZ.E, pg),
      pC(VZ.pC, pg),
      pC(VZ.ld, pg),
      pC(VZ.sY, pg),
      pC(VZ.ys, pg),
      new UC("TST", wT.ys, com.pnfsoftware.jebglobal.NS.kS),
      new UC("TEQ", wT.ys, com.pnfsoftware.jebglobal.NS.kS),
      new UC("CMP", wT.ys, com.pnfsoftware.jebglobal.NS.kS),
      new UC("CMN", wT.ys, com.pnfsoftware.jebglobal.NS.kS),
      pC(VZ.gp, pg),
      kS(VZ.xC, wT.UT, com.pnfsoftware.jebglobal.NS.kS),
      pC(VZ.wS, pg),
      kS(VZ.ED, wT.UT, com.pnfsoftware.jebglobal.NS.kS)
   };
   public static final tz fI = kS(VZ.xC, wT.UT, com.pnfsoftware.jebglobal.NS.UT);
   public static final tz WR = new UC("MOVW", wT.UT, IV.pF).pC(cT.A);
   public static final tz[] NS = new tz[]{
      new UC("ADD", LY.pC, LY.A, LY.kS).pC(pC),
      new UC("SUB", LY.pC, LY.A, LY.kS).pC(pC),
      new UC("ADD", LY.pC, LY.A, IV.kS).pC(pC),
      new UC("SUB", LY.pC, LY.A, IV.kS).pC(pC)
   };
   public static final tz[] vP = new tz[]{new UC("ADD", wS, LY.ys, LY.sY), new UC("CMP", LY.ys, LY.sY), new UC("MOV", kS, LY.ys, LY.sY), null};
   public static final tz xC = new UC("ADD", pC(VZ.pC), LY.ys, LY.sY, LY.ys);
   public static final tz ED = new UC("ADD", LY.ys, LY.sY);
   public static final tz[] Sc = new tz[]{
      new UC("MOV", LY.pC, Dj.UT).pC(pC),
      new UC("LSL", LY.pC, LY.A, IV.ED).pC(pC),
      new UC("LSR", LY.pC, LY.A, JF).pC(pC),
      new UC("LSR", LY.pC, LY.A, IV.ED).pC(pC),
      new UC("ASR", LY.pC, LY.A, JF).pC(pC),
      new UC("ASR", LY.pC, LY.A, IV.ED).pC(pC),
      null,
      null
   };
   public static final tz[] ah = new tz[]{
      new UC(0, "MOV", pC, LY.wS, IV.sO), new UC(1, "CMP", LY.wS, IV.sO), new UC(2, "ADD", pC, LY.wS, IV.sO), new UC(3, "SUB", pC, LY.wS, IV.sO)
   };
   public static final tz[] eP = new tz[]{
      new UC(0, "AND", pC, LY.pC, LY.A),
      new UC(1, "EOR", pC, LY.pC, LY.A),
      new UC(2, "LSL", pC, LY.pC, LY.A),
      new UC(3, "LSR", pC, LY.pC, LY.A),
      new UC(4, "ASR", pC, LY.pC, LY.A),
      new UC(5, "ADC", pC, LY.pC, LY.A),
      new UC(6, "SBC", pC, LY.pC, LY.A),
      new UC(7, "ROR", pC, LY.pC, LY.A),
      new UC(8, "TST", LY.pC, LY.A),
      new UC(9, "RSB", pC, LY.pC, LY.A, IV.DQ),
      new UC(10, "CMP", LY.pC, LY.A),
      new UC(11, "CMN", LY.pC, LY.A),
      new UC(12, "ORR", pC, LY.pC, LY.A),
      new UC(13, "MUL", pC, LY.pC, LY.A, LY.pC),
      new UC(14, "BIC", pC, LY.pC, LY.A),
      new UC(15, "MVN", pC, LY.pC, LY.A)
   };
   public static final tz[] UO = new tz[]{
      E(VZ.fI, wT.wS, wT.ys, wT.pC).pC(RW), E(VZ.WR, wT.wS, wT.ys, wT.pC).pC(RW), E(VZ.NS, wT.wS, wT.ys, wT.pC).pC(RW), E(VZ.vP, wT.wS, wT.ys, wT.pC).pC(RW)
   };
   private static final Hu[] gj = new Hu[]{wT.wS, wT.ys, Dj.wS};
   public static final tz[] Ab = new tz[]{
      UT(VZ.kS, gj).pC(OI),
      UT(VZ.wS, gj).pC(OI),
      UT(VZ.gp, gj).pC(OI),
      UT(VZ.oT, gj).pC(A),
      UT(VZ.UT, gj).pC(OI),
      null,
      null,
      null,
      UT(VZ.pC, gj).pC(OI),
      null,
      UT(VZ.ld, gj).pC(OI),
      UT(VZ.sY, gj).pC(Bc),
      null,
      UT(VZ.A, gj).pC(Bc),
      UT(VZ.E, gj).pC(A),
      null
   };
   public static final tz[] rl = new tz[]{
      new UC("TST", wT.ys, Dj.wS).pC(kU),
      null,
      sY(VZ.xC, wT.wS, Dj.wS).pC(e),
      sY(VZ.ED, wT.wS, Dj.wS).pC(xM),
      new UC("TEQ", wT.ys, Dj.wS),
      null,
      null,
      null,
      new UC("CMN", wT.ys, Dj.wS).pC(kU),
      null,
      null,
      null,
      null,
      new UC("CMP", wT.ys, Dj.wS).pC(kU),
      null,
      null
   };
   private static final IV ZD = new IV(524288, new EncodedMemoryAreaList(DirectEncodedMemoryArea.get(12, 3), DirectEncodedMemoryArea.get(6, 2)));
   public static final tz[] z = new tz[]{
      sY(VZ.xC, wT.wS, wT.pC).pC(e),
      E(VZ.fI, wT.wS, wT.pC, ZD).pC(e),
      E(VZ.WR, wT.wS, wT.pC, JF).pC(e),
      E(VZ.WR, wT.wS, wT.pC, ZD).pC(e),
      E(VZ.NS, wT.wS, wT.pC, JF).pC(e),
      E(VZ.NS, wT.wS, wT.pC, ZD).pC(e),
      sY(VZ.Sc, wT.wS, wT.pC).pC(A),
      E(VZ.vP, wT.wS, wT.pC, ZD).pC(e)
   };
   private static final Hu[] DL = new Hu[]{wT.wS, wT.ys, com.pnfsoftware.jebglobal.NS.ys};
   public static final tz[] Ek = new tz[]{
      UT(VZ.kS, DL).pC(A),
      UT(VZ.wS, DL).pC(A),
      UT(VZ.gp, DL).pC(A),
      UT(VZ.oT, DL).pC(A),
      UT(VZ.UT, DL).pC(A),
      null,
      null,
      null,
      UT(VZ.pC, DL).pC(Bf),
      null,
      UT(VZ.ld, DL).pC(A),
      UT(VZ.sY, DL).pC(A),
      null,
      UT(VZ.A, DL).pC(Bf),
      UT(VZ.E, DL).pC(Pe),
      null
   };
   public static final tz[] hK = new tz[]{
      new UC("TST", wT.ys, com.pnfsoftware.jebglobal.NS.sY),
      null,
      sY(VZ.xC, wT.wS, com.pnfsoftware.jebglobal.NS.sY).pC(ck),
      sY(VZ.ED, wT.wS, com.pnfsoftware.jebglobal.NS.sY).pC(A),
      new UC("TEQ", wT.ys, com.pnfsoftware.jebglobal.NS.sY),
      null,
      null,
      null,
      new UC("CMN", wT.ys, com.pnfsoftware.jebglobal.NS.sY),
      null,
      null,
      null,
      null,
      new UC("CMP", wT.ys, com.pnfsoftware.jebglobal.NS.sY).pC(Kq),
      null,
      null
   };
   public static final tz Er = new UC("ADR", LY.wS, IV.LM);
   public static final tz FE = new UC("ADD", LY.wS, wT.vP, IV.mv);
   public static final tz Aj = new UC("ADD", wT.vP, IV.EX);
   public static final tz EX = new UC("SUB", wT.vP, IV.EX);
   public static final IEncodedMemoryArea LM = new EncodedMemoryAreaList(
      DirectEncodedMemoryArea.getThumb2(10, 1, 1), DirectEncodedMemoryArea.get(12, 3), DirectEncodedMemoryArea.get(0, 8)
   );
   public static final Hu[] mv = new Hu[]{wT.wS, wT.ys, new IV(LM)};
   public static final tz sO = new UC("ADD", pC(VZ.pC), mv).pC(cT.A);
   public static final tz os = new UC("ADDW", pC(VZ.pC), mv).pC(cT.A);
   public static final tz Cu = new UC("ADR", wT.wS, new IV(35651584, LM)).pC(cT.A).pC(Kq);
   public static final tz hZ = new UC("SUB", pC(VZ.A), mv).pC(cT.A);
   public static final tz UW = new UC("SUBW", pC(VZ.A), mv).pC(cT.A);
   public static final tz PR = new UC("ADR", wT.wS, new IV(169869312, LM)).pC(cT.A);
   public static final tz cX = new UC("SUB", pC(VZ.A), wT.ED, wT.Sc, IV.sO).pC(A);
   static final IEncodedMemoryArea DQ = new EncodedMemoryAreaList(
      DirectEncodedMemoryArea.get(16, 4), DirectEncodedMemoryArea.getThumb2(10, 1, 1), DirectEncodedMemoryArea.get(12, 3), DirectEncodedMemoryArea.get(0, 8)
   );
   public static final tz ZN = new UC("MOV", wT.wS, new IV(DQ)).pC(cT.A);
   public static final tz OB = new UC("MOVW", wT.wS, new IV(DQ)).pC(cT.A);
   public static final tz pF = new UC("MOVT", wT.wS, new IV(DQ)).pC(cT.A);

   public static tz pC(VZ var0, Hu... var1) {
      return new UC(var0.toString(), pC(var0), var1).pC(A);
   }

   public static tz A(VZ var0, Hu... var1) {
      return new UC(var0.toString(), pC(var0), var1).pC(A).pC(cT.A);
   }

   public static hg pC(VZ var0) {
      return hg.pC(2306 | kS(var0));
   }

   private static int kS(VZ var0) {
      switch (var0) {
         case ld:
            return 36864;
         case pC:
            return 4096;
         case kS:
            return 12288;
         case NS:
            return 57344;
         case wS:
            return 16384;
         case UT:
            return 20480;
         case fI:
            return 49152;
         case WR:
            return 53248;
         case xC:
            return 4096;
         case ED:
            return 45056;
         case oT:
            return 45056;
         case gp:
            return 40960;
         case vP:
            return 61440;
         case Sc:
            return 61440;
         case E:
            return 24576;
         case ys:
            return 32768;
         case sY:
            return 28672;
         case A:
            return 8192;
         default:
            return 0;
      }
   }

   public static tz kS(VZ var0, Hu... var1) {
      return new UC(var0.toString(), A(var0), var1).pC(A);
   }

   public static tz wS(VZ var0, Hu... var1) {
      return new UC(var0.toString(), A(var0), var1).pC(A).pC(cT.A);
   }

   public static hg A(VZ var0) {
      return hg.pC(2050 | kS(var0));
   }

   private static UC UT(VZ var0, Hu... var1) {
      return new UC(var0.toString(), pC(var0), var1);
   }

   private static UC E(VZ var0, Hu... var1) {
      return new UC(var0.toString(), pC(var0), var1).pC(cT.A);
   }

   private static UC sY(VZ var0, Hu... var1) {
      return new UC(var0.toString(), A(var0), var1).pC(cT.A);
   }

   public static final tz pC(byte[] var0) throws ProcessorException {
      int var1 = A(var0);
      boolean var2 = ji.Ek.hasS(var0, null);
      if ((var0[1] & 15) == 15 && !var2) {
         if (var1 == 2 && ((var0[2] & 15) != 0 || var0[3] != 0)) {
            return gp;
         }

         if (var1 == 4) {
            return ld;
         }
      }

      if (var1 >= 8 && var1 <= 11 && !var2) {
         Rb.pC(var0, "Data-Processing/TST/TEQ/CMP/CMN");
      }

      if (var1 >= 8 && var1 <= 11 && (var0[2] & 240) != 0) {
         Rb.pC(var0, "Data-Processing/TST/TEQ/CMP/CMN");
      }

      if ((var1 == 13 || var1 == 15) && (var0[1] & 15) != 0) {
         Rb.pC(var0, "Data-Processing/MOV/MVN");
      }

      return (var0[2] & 240) == 240 && var1 == 13 ? fI : oT[var1];
   }

   public static int A(byte[] var0) {
      return (var0[0] & 1) << 3 | (var0[1] & 224) >>> 5;
   }

   private static class Av extends ji.rQ {
      public Av() {
      }

      public Av(ji.DH... var1) {
         super(var1);
      }

      @Override
      public boolean hasS(byte[] var1, zj var2) {
         return (var1[1] & 16) != 0;
      }
   }
}
