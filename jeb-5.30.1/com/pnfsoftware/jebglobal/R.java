package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.units.code.asm.processor.ProcessorException;
import com.pnfsoftware.jeb.core.units.code.asm.processor.memory.DirectEncodedMemoryArea;
import com.pnfsoftware.jeb.core.units.code.asm.processor.memory.EncodedMemoryAreaList;
import com.pnfsoftware.jeb.core.units.code.asm.processor.memory.IEncodedMemoryArea;
import com.pnfsoftware.jeb.core.units.code.asm.processor.memory.VirtualEncodedMemoryArea;

public class R {
   private static final IEncodedMemoryArea xK = DirectEncodedMemoryArea.get(22, 2);
   private static final IEncodedMemoryArea Dw = new EncodedMemoryAreaList(DirectEncodedMemoryArea.get(16, 1), xK);
   private static final Dm Uv = Dm.q(7, Dw);
   private static final Bq oW = Bq.q(DirectEncodedMemoryArea.get(0, 5), Uv);
   private static final Bq gO = Bq.q(DirectEncodedMemoryArea.get(5, 5), Uv);
   private static final Ef nf = new MG(0, Dw);
   private static final Ef gP = new MG(5, Dw);
   private static final Ef[] za = new Ef[]{Bq.oS, Bq.fq, Bq.GC, Bq.eJ, Bq.LL};
   private static final Ef[] lm = new Ef[]{Bq.oS, Bq.fq, Bq.GC, Bq.YN, Bq.PQ};
   private static final Ef[] zz = new Ef[]{Bq.oS, Bq.fq, Bq.GC, Bq.Rv};
   private static final Ef[] JY = new Ef[]{Bq.oS, Bq.fq, Bq.GC, Bq.Rv, Bq.fQ};
   private static final Ef[] HF = new Ef[]{Bq.kv, Bq.fq, Bq.GC, Bq.YN, Bq.PQ};
   private static final Ef[] LK = new Ef[]{Bq.kv, Bq.fq, Bq.GC, Bq.zx};
   private static final Ef[] io = new Ef[]{Bq.kv, Bq.fq, Bq.GC, Bq.zx, Bq.fi};
   private static final Ef qa = Bf.q(YH.qa, YH.Hk, VirtualEncodedMemoryArea.get(0, 2), xK, jD.eo.JY);
   private static final Ef Hk = Bf.q(YH.qa, YH.Hk, VirtualEncodedMemoryArea.get(0, 2), VirtualEncodedMemoryArea.get(4, 32), jD.eo.JY);
   private static final Ef Me = FI.q(YH.qa, go.zz);
   private static final Ef PV = new MG(0, xK);
   private static final Ef oQ = new MG(0, VirtualEncodedMemoryArea.get(7, 32));
   private static final Ef xW = new xT(PV);
   private static final Ef KT = new xT(oQ);
   private static final Je[] Gf = new Je[]{
      new Qg("SMOPA", za).q(QJ.ZA),
      new Qg("SMOPS", za).q(QJ.ZA),
      new Qg("SUMOPA", za).q(QJ.ZA),
      new Qg("SUMOPS", za).q(QJ.ZA),
      new Qg("USMOPA", za).q(QJ.ZA),
      new Qg("USMOPS", za).q(QJ.ZA),
      new Qg("UMOPA", za).q(QJ.ZA),
      new Qg("UMOPS", za).q(QJ.ZA)
   };
   private static final Je[] Ef = new Je[]{
      new Qg("SMOPA", HF).q(QJ.jh),
      new Qg("SMOPS", HF).q(QJ.jh),
      new Qg("SUMOPA", HF).q(QJ.jh),
      new Qg("SUMOPS", HF).q(QJ.jh),
      new Qg("USMOPA", HF).q(QJ.jh),
      new Qg("USMOPS", HF).q(QJ.jh),
      new Qg("UMOPA", HF).q(QJ.jh),
      new Qg("UMOPS", HF).q(QJ.jh)
   };
   private static final Je[] cC = new Je[]{new Qg("FMOPA", io).q(QJ.Jf), new Qg("FMOPS", io).q(QJ.Jf)};
   private static final Je[] sH = new Je[]{new Qg("FMOPA", JY).q(QJ.ZA), new Qg("FMOPS", JY).q(QJ.ZA)};
   private static final Je[] CE = new Je[]{new Qg("FMOPA", lm).q(QJ.ZA), new Qg("FMOPS", lm).q(QJ.ZA)};
   private static final Je[] wF = new Je[]{new Qg("BFMOPA", lm).q(QJ.ZA), new Qg("BFMOPS", lm).q(QJ.ZA)};
   private static final Je If = new Qg("MOV", oW, Bq.fq, gP).q(QJ.ZA);
   private static final Je Dz = new Qg("MOV", nf, Bq.fq, gO).q(QJ.ZA);
   private static final Je[] mI = new Je[]{
      new Qg("ADDHA", zz).q(QJ.ZA), new Qg("ADDVA", zz).q(QJ.ZA), new Qg("ADDHA", LK).q(QJ.jh), new Qg("ADDVA", LK).q(QJ.jh)
   };
   private static final Je jq = new Qg("ZERO", new Vw(DirectEncodedMemoryArea.get(0, 8))).q(QJ.ZA);
   private static final Je[] ui = new Je[]{
      new Qg("LD1B", xW, Bq.mJ, qa).q(QJ.ZA),
      new Qg("ST1B", xW, Bq.Sz, qa).q(QJ.ZA),
      new Qg("LD1H", xW, Bq.mJ, qa).q(QJ.ZA),
      new Qg("ST1H", xW, Bq.Sz, qa).q(QJ.ZA),
      new Qg("LD1W", xW, Bq.mJ, qa).q(QJ.ZA),
      new Qg("ST1W", xW, Bq.Sz, qa).q(QJ.ZA),
      new Qg("LD1D", xW, Bq.mJ, qa).q(QJ.ZA),
      new Qg("ST1D", xW, Bq.Sz, qa).q(QJ.ZA)
   };
   private static final Je TX = new Qg("ST1Q", KT, Bq.Sz, Hk).q(QJ.ZA);
   private static final Je Rr = new Qg("LD1Q", KT, Bq.mJ, Hk).q(QJ.ZA);
   private static final Ef EB = new zg(Bq.hM, YH.GY, go.q(DirectEncodedMemoryArea.get(0, 4)));
   private static final Je[] Xo = new Je[]{new Qg("LDR", EB, Me).q(QJ.ZA), new Qg("STR", EB, Me).q(QJ.ZA)};
   static final Je[] q = new Je[]{new Qg("ADDSVL", YH.io, YH.Hk, go.wF).q(QJ.ZA), new Qg("ADDSPL", YH.io, YH.Hk, go.wF).q(QJ.ZA)};
   static final Je RF = new Qg("RDSVL", YH.Uv, go.wF).q(QJ.ZA);

   public static Je q(byte[] var0) throws ProcessorException {
      int var1 = (var0[0] & 96) >>> 5;
      int var2 = (var0[0] & 1) << 5 | (var0[1] & 248) >>> 3;
      int var3 = (var0[1] & 2) >>> 1;
      int var4 = (var0[3] & 28) >>> 2;
      int var5 = (var0[3] & 16) >>> 4;
      int var6 = (var0[1] & 192) >>> 6;
      int var7 = var0[1] & 1;
      switch (var1) {
         case 0:
         case 1:
            switch (var2 & 24) {
               case 16:
                  if (var1 == 0 && (var4 & 3) == 0) {
                     switch (var2 & 36) {
                        case 0:
                           return sH[var5];
                        case 4:
                        default:
                           break;
                        case 32:
                           return wF[var5];
                        case 36:
                           return CE[var5];
                     }
                  }

                  if (var1 == 1 && (var4 & 1) == 0 && (var4 & 2) == 0) {
                     int var14 = (var0[0] & 1) << 2 | (var0[1] & 32) >>> 4 | var5;
                     return Gf[var14];
                  }

                  return null;
               case 24:
                  if ((var4 & 2) == 0) {
                     if (var1 == 1) {
                        int var13 = (var0[0] & 1) << 2 | (var0[1] & 32) >>> 4 | var5;
                        return Ef[var13];
                     }

                     if (var1 == 0 && (var2 & 60) == 24) {
                        return cC[var5];
                     }
                  }

                  return null;
               default:
                  return null;
            }
         case 2:
            switch (var2 & 39) {
               case 0:
                  int var12 = (var0[1] & 4) >>> 2;
                  if (var3 != 0) {
                     int var15 = (var0[2] & 2) >>> 1;
                     if (var12 == 0 && var15 == 0) {
                        if (var7 != 0 && var6 != 3) {
                           return null;
                        }

                        return If;
                     }
                  } else if (var4 < 4 && var12 == 0 && (var7 == 0 || var6 == 3)) {
                     return Dz;
                  }

                  return null;
               case 1:
                  if (var6 == 0 && (var0[1] & 7) == 0 && var0[2] == 0) {
                     return jq;
                  }

                  return null;
               case 2:
                  if ((var4 & 2) == 0) {
                     int var9 = var0[1] & 128;
                     int var10 = var0[1] & 6;
                     if (var9 != 0 && var10 == 0 && var5 == 0) {
                        int var11 = (var0[1] & 64) >>> 6;
                        return mI[var11 << 1 | var7];
                     }
                  }

                  return null;
               default:
                  return null;
            }
         case 3:
            int var8 = (var0[0] & 1) << 3 | (var0[1] & 224) >>> 5;
            if (var5 != 0) {
               return null;
            } else {
               switch (var8) {
                  case 0:
                  case 2:
                  case 4:
                  case 6:
                     return ui[var8];
                  case 1:
                  case 3:
                  case 5:
                  case 7:
                     return ui[var8];
                  case 8:
                  case 9:
                     if ((var0[1] & 31) == 0 && (var0[2] & 156) == 0) {
                        return Xo[var8 & 1];
                     }
                  case 10:
                  case 11:
                  case 12:
                  case 13:
                  default:
                     break;
                  case 14:
                     return Rr;
                  case 15:
                     return TX;
               }
            }
         default:
            return null;
      }
   }
}
