package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.units.code.asm.processor.ProcessorException;
import com.pnfsoftware.jeb.core.units.code.asm.processor.memory.DirectEncodedMemoryArea;
import com.pnfsoftware.jeb.core.units.code.asm.processor.memory.EncodedMemoryAreaList;
import com.pnfsoftware.jeb.core.units.code.asm.processor.memory.IEncodedMemoryArea;
import com.pnfsoftware.jeb.core.units.code.asm.processor.memory.VirtualEncodedMemoryArea;

public class Xu {
   static final de.CU q = (var0, var1) -> !Lf.q(var1);
   public static final de RF = de.Ef;
   private static final de Rv = new sn(de.EE.q, de.EE.RF, de.EE.Uv);
   private static final de zx = new nA(de.EE.q, de.EE.RF, de.EE.Dw, de.EE.oW);
   private static final de ZT = new XY();
   private static final de Ri = new Jq();
   private static final de GY = new uR();
   private static final de Wx = new Xu.eo(de.EE.q, de.EE.RF, de.EE.Uv);
   private static final de AB = new Xu.eo(de.EE.q, de.EE.RF);
   private static final de CY = new ar(de.EE.q, de.EE.RF).RF(DirectEncodedMemoryArea.getThumb2(12, 0, 3)).RF(DirectEncodedMemoryArea.getThumb2(6, 0, 2));
   private static final de WI = new AM(de.EE.q, de.EE.Dw).RF(DirectEncodedMemoryArea.getThumb2(12, 0, 3)).RF(DirectEncodedMemoryArea.getThumb2(4, 0, 4));
   private static final de Tq = new rk(de.EE.RF, de.EE.RF).RF(DirectEncodedMemoryArea.getThumb2(10, 1, 1)).RF(DirectEncodedMemoryArea.getThumb2(12, 0, 3));
   public static final OQ xK = OQ.q(258);
   public static final OQ Dw = OQ.q(2562);
   private static final Ef[] Yp = new Ef[]{Pc.nf, Pc.lm, cn.HF};
   public static final Je[] Uv = new Je[]{
      q(rq.xK, Yp),
      q(rq.Uv, Yp),
      q(rq.RF, Yp),
      q(rq.oW, Yp),
      q(rq.q, Yp),
      q(rq.gP, Yp),
      q(rq.gO, Yp),
      q(rq.nf, Yp),
      new Qg("TST", Pc.lm, cn.HF),
      new Qg("TEQ", Pc.lm, cn.HF),
      new Qg("CMP", Pc.lm, cn.HF),
      new Qg("CMN", Pc.lm, cn.HF),
      q(rq.za, Yp),
      null,
      q(rq.Dw, Yp),
      xK(rq.qa, Pc.nf, cn.HF)
   };
   private static final go Gu = new go(VirtualEncodedMemoryArea.get(32, 5));
   public static final Je[] oW = new Je[]{
      xK(rq.io, Pc.nf, cn.HF),
      q(rq.JY, Pc.nf, Pc.Dw, Gu),
      q(rq.HF, Pc.nf, Pc.Dw, Gu),
      xK(rq.Hk, Pc.nf, Pc.Dw),
      q(rq.zz, Pc.nf, Pc.Dw, go.Ef),
      q(rq.JY, Pc.nf, Pc.Dw, go.Ef),
      q(rq.HF, Pc.nf, Pc.Dw, go.Ef),
      q(rq.LK, Pc.nf, Pc.Dw, go.Ef)
   };
   private static final Ef[] nY = new Ef[]{Pc.nf, Pc.lm, cn.LK};
   public static final Je[] gO = new Je[]{
      RF(rq.xK, nY),
      RF(rq.Uv, nY),
      RF(rq.RF, nY),
      RF(rq.oW, nY),
      RF(rq.q, nY),
      RF(rq.gP, nY),
      RF(rq.gO, nY),
      RF(rq.nf, nY),
      new Qg("TST", Pc.lm, cn.LK),
      new Qg("TEQ", Pc.lm, cn.LK),
      new Qg("CMP", Pc.lm, cn.LK),
      new Qg("CMN", Pc.lm, cn.LK),
      RF(rq.za, nY),
      null,
      RF(rq.Dw, nY),
      Dw(rq.qa, Pc.nf, cn.LK)
   };
   public static final Je[] nf = new Je[]{
      RF(rq.zz, Pc.nf, Pc.Dw, Pc.gO), RF(rq.JY, Pc.nf, Pc.Dw, Pc.gO), RF(rq.HF, Pc.nf, Pc.Dw, Pc.gO), RF(rq.LK, Pc.nf, Pc.Dw, Pc.gO)
   };
   public static final Je gP = new Qg("ADR", xK, Pc.nf, lO.oW);
   public static final Je za = new Qg("ADR", xK, Pc.nf, lO.nf);
   private static final Ef[] lF = new Ef[]{Pc.nf, Pc.lm, lO.Uv};
   public static final Je[] lm = new Je[]{
      q(rq.xK, lF),
      q(rq.Uv, lF),
      q(rq.RF, lF),
      q(rq.oW, lF),
      q(rq.q, lF),
      q(rq.gP, lF),
      q(rq.gO, lF),
      q(rq.nf, lF),
      new Qg("TST", Pc.lm, lO.Uv),
      new Qg("TEQ", Pc.lm, lO.Uv),
      new Qg("CMP", Pc.lm, lO.Uv),
      new Qg("CMN", Pc.lm, lO.Uv),
      q(rq.za, lF),
      xK(rq.io, Pc.nf, lO.Uv),
      q(rq.Dw, lF),
      xK(rq.qa, Pc.nf, lO.Uv)
   };
   public static final Je zz = xK(rq.io, Pc.nf, lO.gO);
   public static final Je JY = new Qg("MOVW", Pc.nf, go.AB).q(QI.RF);
   public static final Je[] HF = new Je[]{
      new Qg("ADD", iv.Dw, iv.Uv, iv.oW).q(q),
      new Qg("SUB", iv.Dw, iv.Uv, iv.oW).q(q),
      new Qg("ADD", iv.Dw, iv.Uv, go.za).q(q),
      new Qg("SUB", iv.Dw, iv.Uv, go.za).q(q)
   };
   public static final Je[] LK = new Je[]{new Qg("ADD", Dw, iv.lm, iv.za), new Qg("CMP", iv.lm, iv.za), new Qg("MOV", xK, iv.lm, iv.za), null};
   public static final Je io = new Qg("ADD", q(rq.q), iv.lm, iv.za, iv.lm);
   public static final Je qa = new Qg("ADD", iv.lm, iv.za);
   public static final Je[] Hk = new Je[]{
      new Qg("MOV", iv.Dw, cn.qa).q(q),
      new Qg("LSL", iv.Dw, iv.Uv, go.Gf).q(q),
      new Qg("LSR", iv.Dw, iv.Uv, Gu).q(q),
      new Qg("LSR", iv.Dw, iv.Uv, go.Gf).q(q),
      new Qg("ASR", iv.Dw, iv.Uv, Gu).q(q),
      new Qg("ASR", iv.Dw, iv.Uv, go.Gf).q(q),
      null,
      null
   };
   public static final Je[] Me = new Je[]{
      new Qg(0, "MOV", q, iv.gO, go.IN), new Qg(1, "CMP", iv.gO, go.IN), new Qg(2, "ADD", q, iv.gO, go.IN), new Qg(3, "SUB", q, iv.gO, go.IN)
   };
   public static final Je[] PV = new Je[]{
      new Qg(0, "AND", q, iv.Dw, iv.Uv),
      new Qg(1, "EOR", q, iv.Dw, iv.Uv),
      new Qg(2, "LSL", q, iv.Dw, iv.Uv),
      new Qg(3, "LSR", q, iv.Dw, iv.Uv),
      new Qg(4, "ASR", q, iv.Dw, iv.Uv),
      new Qg(5, "ADC", q, iv.Dw, iv.Uv),
      new Qg(6, "SBC", q, iv.Dw, iv.Uv),
      new Qg(7, "ROR", q, iv.Dw, iv.Uv),
      new Qg(8, "TST", iv.Dw, iv.Uv),
      new Qg(9, "RSB", q, iv.Dw, iv.Uv, go.Ri),
      new Qg(10, "CMP", iv.Dw, iv.Uv),
      new Qg(11, "CMN", iv.Dw, iv.Uv),
      new Qg(12, "ORR", q, iv.Dw, iv.Uv),
      new Qg(13, "MUL", q, iv.Dw, iv.Uv, iv.Dw),
      new Qg(14, "BIC", q, iv.Dw, iv.Uv),
      new Qg(15, "MVN", q, iv.Dw, iv.Uv)
   };
   public static final Je[] oQ = new Je[]{
      oW(rq.zz, Pc.gO, Pc.lm, Pc.Dw).q(Wx), oW(rq.JY, Pc.gO, Pc.lm, Pc.Dw).q(Wx), oW(rq.HF, Pc.gO, Pc.lm, Pc.Dw).q(Wx), oW(rq.LK, Pc.gO, Pc.lm, Pc.Dw).q(Wx)
   };
   private static final Ef[] nq = new Ef[]{Pc.gO, Pc.lm, cn.io};
   public static final Je[] xW = new Je[]{
      Uv(rq.xK, nq).q(zx),
      Uv(rq.Dw, nq).q(zx),
      Uv(rq.za, nq).q(zx),
      Uv(rq.lm, nq).q(RF),
      Uv(rq.Uv, nq).q(zx),
      null,
      null,
      null,
      Uv(rq.q, nq).q(zx),
      null,
      Uv(rq.gP, nq).q(zx),
      Uv(rq.gO, nq).q(Rv),
      null,
      Uv(rq.RF, nq).q(Rv),
      Uv(rq.oW, nq).q(RF),
      null
   };
   public static final Je[] KT = new Je[]{
      new Qg("TST", Pc.lm, cn.io).q(WI),
      null,
      gO(rq.io, Pc.gO, cn.io).q(AB),
      gO(rq.qa, Pc.gO, cn.io).q(CY),
      new Qg("TEQ", Pc.lm, cn.io),
      null,
      null,
      null,
      new Qg("CMN", Pc.lm, cn.io).q(WI),
      null,
      null,
      null,
      null,
      new Qg("CMP", Pc.lm, cn.io).q(WI),
      null,
      null
   };
   private static final go NX = new go(524288, new EncodedMemoryAreaList(DirectEncodedMemoryArea.get(12, 3), DirectEncodedMemoryArea.get(6, 2)));
   public static final Je[] Gf = new Je[]{
      gO(rq.io, Pc.gO, Pc.Dw).q(AB),
      oW(rq.zz, Pc.gO, Pc.Dw, NX).q(AB),
      oW(rq.JY, Pc.gO, Pc.Dw, Gu).q(AB),
      oW(rq.JY, Pc.gO, Pc.Dw, NX).q(AB),
      oW(rq.HF, Pc.gO, Pc.Dw, Gu).q(AB),
      oW(rq.HF, Pc.gO, Pc.Dw, NX).q(AB),
      gO(rq.Hk, Pc.gO, Pc.Dw).q(RF),
      oW(rq.LK, Pc.gO, Pc.Dw, NX).q(AB)
   };
   private static final Ef[] br = new Ef[]{Pc.gO, Pc.lm, lO.za};
   public static final Je[] Ef = new Je[]{
      Uv(rq.xK, br).q(RF),
      Uv(rq.Dw, br).q(RF),
      Uv(rq.za, br).q(RF),
      Uv(rq.lm, br).q(RF),
      Uv(rq.Uv, br).q(RF),
      null,
      null,
      null,
      Uv(rq.q, br).q(ZT),
      null,
      Uv(rq.gP, br).q(RF),
      Uv(rq.gO, br).q(RF),
      null,
      Uv(rq.RF, br).q(ZT),
      Uv(rq.oW, br).q(Ri),
      null
   };
   public static final Je[] cC = new Je[]{
      new Qg("TST", Pc.lm, lO.gP),
      null,
      gO(rq.io, Pc.gO, lO.gP).q(GY),
      gO(rq.qa, Pc.gO, lO.gP).q(RF),
      new Qg("TEQ", Pc.lm, lO.gP),
      null,
      null,
      null,
      new Qg("CMN", Pc.lm, lO.gP),
      null,
      null,
      null,
      null,
      new Qg("CMP", Pc.lm, lO.gP).q(Tq),
      null,
      null
   };
   public static final Je sH = new Qg("ADR", iv.gO, go.Xo);
   public static final Je CE = new Qg("ADD", iv.gO, Pc.Hk, go.Bu);
   public static final Je wF = new Qg("ADD", Pc.Hk, go.EB);
   public static final Je If = new Qg("SUB", Pc.Hk, go.EB);
   public static final IEncodedMemoryArea Dz = new EncodedMemoryAreaList(
      DirectEncodedMemoryArea.getThumb2(10, 1, 1), DirectEncodedMemoryArea.get(12, 3), DirectEncodedMemoryArea.get(0, 8)
   );
   public static final Ef[] mI = new Ef[]{Pc.gO, Pc.lm, new go(Dz)};
   public static final Je jq = new Qg("ADD", q(rq.q), mI).q(QI.RF);
   public static final Je ui = new Qg("ADDW", q(rq.q), mI).q(QI.RF);
   public static final Je TX = new Qg("ADR", Pc.gO, new go(35651584, Dz)).q(QI.RF).q(Tq);
   public static final Je Rr = new Qg("SUB", q(rq.RF), mI).q(QI.RF);
   public static final Je EB = new Qg("SUBW", q(rq.RF), mI).q(QI.RF);
   public static final Je Xo = new Qg("ADR", Pc.gO, new go(169869312, Dz)).q(QI.RF);
   public static final Je Bu = new Qg("SUB", q(rq.RF), Pc.PV, Pc.oQ, go.IN).q(RF);
   static final IEncodedMemoryArea IN = new EncodedMemoryAreaList(
      DirectEncodedMemoryArea.get(16, 4), DirectEncodedMemoryArea.getThumb2(10, 1, 1), DirectEncodedMemoryArea.get(12, 3), DirectEncodedMemoryArea.get(0, 8)
   );
   public static final Je rL = new Qg("MOV", Pc.gO, new go(IN)).q(QI.RF);
   public static final Je eJ = new Qg("MOVW", Pc.gO, new go(IN)).q(QI.RF);
   public static final Je YN = new Qg("MOVT", Pc.gO, new go(IN)).q(QI.RF);

   public static Je q(rq var0, Ef... var1) {
      return new Qg(var0.toString(), q(var0), var1).q(RF);
   }

   public static Je RF(rq var0, Ef... var1) {
      return new Qg(var0.toString(), q(var0), var1).q(RF).q(QI.RF);
   }

   public static OQ q(rq var0) {
      return OQ.q(2306 | xK(var0));
   }

   private static int xK(rq var0) {
      switch (var0) {
         case gP:
            return 36864;
         case q:
            return 4096;
         case xK:
            return 12288;
         case HF:
            return 57344;
         case Dw:
            return 16384;
         case Uv:
            return 20480;
         case zz:
            return 49152;
         case JY:
            return 53248;
         case io:
            return 4096;
         case qa:
            return 45056;
         case lm:
            return 45056;
         case za:
            return 40960;
         case LK:
            return 61440;
         case Hk:
            return 61440;
         case oW:
            return 24576;
         case nf:
            return 32768;
         case gO:
            return 28672;
         case RF:
            return 8192;
         default:
            return 0;
      }
   }

   public static Je xK(rq var0, Ef... var1) {
      return new Qg(var0.toString(), RF(var0), var1).q(RF);
   }

   public static Je Dw(rq var0, Ef... var1) {
      return new Qg(var0.toString(), RF(var0), var1).q(RF).q(QI.RF);
   }

   public static OQ RF(rq var0) {
      return OQ.q(2050 | xK(var0));
   }

   private static Qg Uv(rq var0, Ef... var1) {
      return new Qg(var0.toString(), q(var0), var1);
   }

   private static Qg oW(rq var0, Ef... var1) {
      return new Qg(var0.toString(), q(var0), var1).q(QI.RF);
   }

   private static Qg gO(rq var0, Ef... var1) {
      return new Qg(var0.toString(), RF(var0), var1).q(QI.RF);
   }

   public static final Je q(byte[] var0) throws ProcessorException {
      int var1 = RF(var0);
      boolean var2 = de.Ef.hasS(var0, null);
      if ((var0[1] & 15) == 15 && !var2) {
         if (var1 == 2 && ((var0[2] & 15) != 0 || var0[3] != 0)) {
            return za;
         }

         if (var1 == 4) {
            return gP;
         }
      }

      if (var1 >= 8 && var1 <= 11 && !var2) {
         vf.q(var0, "Data-Processing/TST/TEQ/CMP/CMN");
      }

      if (var1 >= 8 && var1 <= 11 && (var0[2] & 240) != 0) {
         vf.q(var0, "Data-Processing/TST/TEQ/CMP/CMN");
      }

      if ((var1 == 13 || var1 == 15) && (var0[1] & 15) != 0) {
         vf.q(var0, "Data-Processing/MOV/MVN");
      }

      return (var0[2] & 240) == 240 && var1 == 13 ? zz : lm[var1];
   }

   public static int RF(byte[] var0) {
      return (var0[0] & 1) << 3 | (var0[1] & 224) >>> 5;
   }

   private static class eo extends de.qV {
      public eo() {
      }

      public eo(de.EE... var1) {
         super(var1);
      }

      @Override
      public boolean hasS(byte[] var1, mZ var2) {
         return (var1[1] & 16) != 0;
      }
   }
}
