package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.units.code.asm.processor.ProcessorException;
import com.pnfsoftware.jeb.core.units.code.asm.processor.memory.DirectEncodedMemoryArea;
import com.pnfsoftware.jeb.core.units.code.asm.processor.memory.EncodedMemoryAreaList;
import com.pnfsoftware.jeb.core.units.code.asm.processor.memory.IEncodedMemoryArea;
import com.pnfsoftware.jeb.core.units.code.asm.processor.memory.VirtualEncodedMemoryArea;

public class KL {
   private static final IEncodedMemoryArea kS = DirectEncodedMemoryArea.get(22, 2);
   private static final IEncodedMemoryArea wS = new EncodedMemoryAreaList(DirectEncodedMemoryArea.get(16, 1), kS);
   private static final IX UT = IX.pC(7, wS);
   private static final aP E = aP.pC(DirectEncodedMemoryArea.get(0, 5), UT);
   private static final aP sY = aP.pC(DirectEncodedMemoryArea.get(5, 5), UT);
   private static final Hu ys = new MB(0, wS);
   private static final Hu ld = new MB(5, wS);
   private static final Hu[] gp = new Hu[]{aP.ET, aP.eE, aP.qG, aP.cX, aP.Xs};
   private static final Hu[] oT = new Hu[]{aP.ET, aP.eE, aP.qG, aP.DQ, aP.KV};
   private static final Hu[] fI = new Hu[]{aP.ET, aP.eE, aP.qG, aP.ZN};
   private static final Hu[] WR = new Hu[]{aP.ET, aP.eE, aP.qG, aP.ZN, aP.FK};
   private static final Hu[] NS = new Hu[]{aP.fH, aP.eE, aP.qG, aP.DQ, aP.KV};
   private static final Hu[] vP = new Hu[]{aP.fH, aP.eE, aP.qG, aP.OB};
   private static final Hu[] xC = new Hu[]{aP.fH, aP.eE, aP.qG, aP.OB, aP.Bi};
   private static final Hu ED = LF.pC(sQ.NS, sQ.vP, VirtualEncodedMemoryArea.get(0, 2), kS, Ll.Av.WR);
   private static final Hu Sc = LF.pC(sQ.NS, sQ.vP, VirtualEncodedMemoryArea.get(0, 2), VirtualEncodedMemoryArea.get(4, 32), Ll.Av.WR);
   private static final Hu ah = ky.pC(sQ.NS, IV.UT);
   private static final Hu eP = new MB(0, kS);
   private static final Hu UO = new MB(0, VirtualEncodedMemoryArea.get(7, 32));
   private static final Hu Ab = new Fw(eP);
   private static final Hu rl = new Fw(UO);
   private static final tz[] z = new tz[]{
      new UC("SMOPA", gp).pC(Le.ys),
      new UC("SMOPS", gp).pC(Le.ys),
      new UC("SUMOPA", gp).pC(Le.ys),
      new UC("SUMOPS", gp).pC(Le.ys),
      new UC("USMOPA", gp).pC(Le.ys),
      new UC("USMOPS", gp).pC(Le.ys),
      new UC("UMOPA", gp).pC(Le.ys),
      new UC("UMOPS", gp).pC(Le.ys)
   };
   private static final tz[] Ek = new tz[]{
      new UC("SMOPA", NS).pC(Le.os),
      new UC("SMOPS", NS).pC(Le.os),
      new UC("SUMOPA", NS).pC(Le.os),
      new UC("SUMOPS", NS).pC(Le.os),
      new UC("USMOPA", NS).pC(Le.os),
      new UC("USMOPS", NS).pC(Le.os),
      new UC("UMOPA", NS).pC(Le.os),
      new UC("UMOPS", NS).pC(Le.os)
   };
   private static final tz[] hK = new tz[]{new UC("FMOPA", xC).pC(Le.Cu), new UC("FMOPS", xC).pC(Le.Cu)};
   private static final tz[] Er = new tz[]{new UC("FMOPA", WR).pC(Le.ys), new UC("FMOPS", WR).pC(Le.ys)};
   private static final tz[] FE = new tz[]{new UC("FMOPA", oT).pC(Le.ys), new UC("FMOPS", oT).pC(Le.ys)};
   private static final tz[] Aj = new tz[]{new UC("BFMOPA", oT).pC(Le.ys), new UC("BFMOPS", oT).pC(Le.ys)};
   private static final tz EX = new UC("MOV", E, aP.eE, ld).pC(Le.ys);
   private static final tz LM = new UC("MOV", ys, aP.eE, sY).pC(Le.ys);
   private static final tz[] mv = new tz[]{
      new UC("ADDHA", fI).pC(Le.ys), new UC("ADDVA", fI).pC(Le.ys), new UC("ADDHA", vP).pC(Le.os), new UC("ADDVA", vP).pC(Le.os)
   };
   private static final tz sO = new UC("ZERO", new Lt(DirectEncodedMemoryArea.get(0, 8))).pC(Le.ys);
   private static final tz[] os = new tz[]{
      new UC("LD1B", Ab, aP.dM, ED).pC(Le.ys),
      new UC("ST1B", Ab, aP.QQ, ED).pC(Le.ys),
      new UC("LD1H", Ab, aP.dM, ED).pC(Le.ys),
      new UC("ST1H", Ab, aP.QQ, ED).pC(Le.ys),
      new UC("LD1W", Ab, aP.dM, ED).pC(Le.ys),
      new UC("ST1W", Ab, aP.QQ, ED).pC(Le.ys),
      new UC("LD1D", Ab, aP.dM, ED).pC(Le.ys),
      new UC("ST1D", Ab, aP.QQ, ED).pC(Le.ys)
   };
   private static final tz Cu = new UC("ST1Q", rl, aP.QQ, Sc).pC(Le.ys);
   private static final tz hZ = new UC("LD1Q", rl, aP.dM, Sc).pC(Le.ys);
   private static final Hu UW = new TF(aP.MZ, sQ.OI, IV.pC(DirectEncodedMemoryArea.get(0, 4)));
   private static final tz[] PR = new tz[]{new UC("LDR", UW, ah).pC(Le.ys), new UC("STR", UW, ah).pC(Le.ys)};
   static final tz[] pC = new tz[]{new UC("ADDSVL", sQ.WR, sQ.vP, IV.Ab).pC(Le.ys), new UC("ADDSPL", sQ.WR, sQ.vP, IV.Ab).pC(Le.ys)};
   static final tz A = new UC("RDSVL", sQ.A, IV.Ab).pC(Le.ys);

   public static tz pC(byte[] var0) throws ProcessorException {
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
                           return Er[var5];
                        case 4:
                        default:
                           break;
                        case 32:
                           return Aj[var5];
                        case 36:
                           return FE[var5];
                     }
                  }

                  if (var1 == 1 && (var4 & 1) == 0 && (var4 & 2) == 0) {
                     int var14 = (var0[0] & 1) << 2 | (var0[1] & 32) >>> 4 | var5;
                     return z[var14];
                  }

                  return null;
               case 24:
                  if ((var4 & 2) == 0) {
                     if (var1 == 1) {
                        int var13 = (var0[0] & 1) << 2 | (var0[1] & 32) >>> 4 | var5;
                        return Ek[var13];
                     }

                     if (var1 == 0 && (var2 & 60) == 24) {
                        return hK[var5];
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

                        return EX;
                     }
                  } else if (var4 < 4 && var12 == 0 && (var7 == 0 || var6 == 3)) {
                     return LM;
                  }

                  return null;
               case 1:
                  if (var6 == 0 && (var0[1] & 7) == 0 && var0[2] == 0) {
                     return sO;
                  }

                  return null;
               case 2:
                  if ((var4 & 2) == 0) {
                     int var9 = var0[1] & 128;
                     int var10 = var0[1] & 6;
                     if (var9 != 0 && var10 == 0 && var5 == 0) {
                        int var11 = (var0[1] & 64) >>> 6;
                        return mv[var11 << 1 | var7];
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
                     return os[var8];
                  case 1:
                  case 3:
                  case 5:
                  case 7:
                     return os[var8];
                  case 8:
                  case 9:
                     if ((var0[1] & 31) == 0 && (var0[2] & 156) == 0) {
                        return PR[var8 & 1];
                     }
                  case 10:
                  case 11:
                  case 12:
                  case 13:
                  default:
                     break;
                  case 14:
                     return hZ;
                  case 15:
                     return Cu;
               }
            }
         default:
            return null;
      }
   }
}
