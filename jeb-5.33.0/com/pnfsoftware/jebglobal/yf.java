package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.units.code.asm.processor.AbstractImmediateOperandBuilder;
import com.pnfsoftware.jeb.core.units.code.asm.processor.ProcessorException;
import com.pnfsoftware.jeb.core.units.code.asm.processor.memory.DirectEncodedMemoryArea;
import com.pnfsoftware.jeb.core.units.code.asm.processor.memory.EncodedMemoryAreaList;
import com.pnfsoftware.jeb.core.units.code.asm.processor.memory.IEncodedMemoryArea;
import com.pnfsoftware.jeb.core.units.code.asm.processor.memory.VirtualEncodedMemoryArea;

public class yf {
   private static final Hu pC = new Dj(aX.pC, Dj.pC, VirtualEncodedMemoryArea.get(12, 32));
   private static final Hu A = new Dj(aX.A, Dj.pC, DirectEncodedMemoryArea.get(21, 2).shift(4));
   private static final IEncodedMemoryArea kS = new EncodedMemoryAreaList(DirectEncodedMemoryArea.get(5, 19), DirectEncodedMemoryArea.get(29, 2));
   private static final tz wS = new UC("ADR", sQ.A, pC(2097152, kS));
   private static final tz UT = new UC("ADRP", sQ.A, pC(18874368, EncodedMemoryAreaList.shift(kS, 12)));
   private static final tz[] E = new tz[]{
      new UC("ADD", sQ.DQ, sQ.OB, aX.pC),
      new UC("ADD", sQ.DQ, sQ.OB, pC),
      new UC("ADD", sQ.FE, sQ.OB, aX.pC).pC(ji.hK),
      new UC("ADD", sQ.FE, sQ.OB, pC).pC(ji.hK)
   };
   private static final tz sY = new UC("MOV", sQ.DQ, sQ.OB);
   private static final tz[] ys = new tz[]{new UC("CMN", sQ.OB, aX.pC), new UC("CMN", sQ.OB, pC)};
   private static final tz[] ld = new tz[]{
      new UC("SUB", sQ.DQ, sQ.OB, aX.pC),
      new UC("SUB", sQ.DQ, sQ.OB, pC),
      new UC("SUB", sQ.FE, sQ.OB, aX.pC).pC(ji.hK),
      new UC("SUB", sQ.FE, sQ.OB, pC).pC(ji.hK)
   };
   private static final tz[] gp = new tz[]{new UC("CMP", sQ.OB, aX.pC), new UC("CMP", sQ.OB, pC)};
   private static final tz[] oT = new tz[]{new UC("MOVN", sQ.FE, A), null, new UC("MOVZ", sQ.FE, A), new UC("MOVK", sQ.FE, A)};
   private static final Hu fI = (var0, var1) -> {
      int var2 = var0[0] & 128;
      int var3 = DirectEncodedMemoryArea.get(16, 6).decodeInt(var0);
      return Yg.pC((long)((var3 - (var2 == 0 ? 32 : 64)) * -1), 589824);
   };
   private static final Hu WR = (var0, var1) -> {
      int var2 = DirectEncodedMemoryArea.get(10, 6).decodeInt(var0);
      int var3 = DirectEncodedMemoryArea.get(16, 6).decodeInt(var0);
      return Yg.pC((long)(var2 - var3 + 1), 589824);
   };
   private static final tz[] NS = new tz[]{
      new UC("SBFM", sQ.FE, sQ.Aj, IV.Ek, IV.rl), new UC("BFM", sQ.FE, sQ.Aj, IV.Ek, IV.rl), new UC("UBFM", sQ.FE, sQ.Aj, IV.Ek, IV.rl), null
   };
   private static final IV vP = new IV(1048576, DirectEncodedMemoryArea.get(10, 6));
   private static final tz xC = new UC("BFI", sQ.FE, sQ.Aj, fI, vP);
   private static final tz ED = new UC("BFC", sQ.FE, fI, vP);
   private static final tz Sc = new UC("BFXIL", sQ.FE, sQ.Aj, IV.Ek, WR);
   private static final tz ah = new UC("SBFIZ", sQ.FE, sQ.Aj, fI, vP);
   private static final tz eP = new UC("UBFIZ", sQ.FE, sQ.Aj, fI, vP);
   private static final tz UO = new UC("LSL", sQ.FE, sQ.Aj, fI);
   private static final tz Ab = new UC("LSR", sQ.FE, sQ.Aj, IV.FE);
   private static final tz rl = new UC("ASR", sQ.FE, sQ.Aj, IV.FE);
   private static final tz z = new UC("SXTB", sQ.FE, sQ.eP);
   private static final tz Ek = new UC("SXTH", sQ.FE, sQ.eP);
   private static final tz hK = new UC("SXTW", sQ.A, sQ.eP);
   private static final tz Er = new UC("SBFX", sQ.FE, sQ.Aj, IV.Ek, WR);
   private static final tz FE = new UC("UXTB", sQ.ED, sQ.eP);
   private static final tz Aj = new UC("UXTH", sQ.ED, sQ.eP);
   private static final tz EX = new UC("UBFX", sQ.FE, sQ.Aj, IV.Ek, WR);
   private static final tz LM = new UC("ROR", sQ.FE, sQ.Aj, IV.rl);
   private static final tz mv = new UC("EXTR", sQ.FE, sQ.Aj, sQ.LM, IV.rl);
   private static final Hu sO = (var0, var1) -> {
      int var2 = var0[0] & 128;
      return Yg.pC(
         64,
         u.pC(
            DirectEncodedMemoryArea.get(22, 1).decodeInt(var0),
            DirectEncodedMemoryArea.get(10, 6).decodeInt(var0),
            DirectEncodedMemoryArea.get(16, 6).decodeInt(var0),
            var2 == 0 ? 32 : 64
         )
      );
   };
   private static final Hu[] os = new Hu[]{sQ.DQ, sQ.Aj, sO};
   private static final tz[] Cu = new tz[]{new UC("AND", os), new UC("ORR", os), new UC("EOR", os), new UC("AND", sQ.FE, sQ.Aj, sO).pC(ji.hK)};
   private static final tz hZ = new UC("MOV", sQ.DQ, sO);
   private static final tz UW = new UC("TST", sQ.Aj, sO);
   private static final tz[] PR = new tz[]{new UC("ADDG", sQ.WR, sQ.NS, IV.Er, IV.ld).pC(Le.ED), new UC("SUBG", sQ.WR, sQ.NS, IV.Er, IV.ld).pC(Le.ED)};
   private static final tz[] cX = new tz[]{
      new UC("SMAX", sQ.FE, sQ.Aj, IV.PR).pC(Le.DQ),
      new UC("UMAX", sQ.FE, sQ.Aj, IV.UW).pC(Le.DQ),
      new UC("SMIN", sQ.FE, sQ.Aj, IV.PR).pC(Le.DQ),
      new UC("UMIN", sQ.FE, sQ.Aj, IV.UW).pC(Le.DQ)
   };

   private static IV pC(int var0, IEncodedMemoryArea var1) {
      return new IV(AbstractImmediateOperandBuilder.ImmediateType.SignExtend64, var0, var1);
   }

   public static tz pC(byte[] var0) throws ProcessorException {
      int var1 = (var0[0] & 128) >>> 7;
      int var2 = (var0[1] & 64) >>> 6;
      int var3 = (var0[0] & 32) >>> 5;
      int var4 = (var0[0] & 96) >>> 5;
      int var5 = DirectEncodedMemoryArea.get(0, 5).decodeInt(var0);
      int var6 = DirectEncodedMemoryArea.get(5, 5).decodeInt(var0);
      int var7 = DirectEncodedMemoryArea.get(16, 5).decodeInt(var0);
      int var8 = DirectEncodedMemoryArea.get(10, 6).decodeInt(var0);
      int var9 = DirectEncodedMemoryArea.get(16, 6).decodeInt(var0);
      int var10 = (var0[0] & 3) << 2 | (var0[1] & 192) >>> 6;
      switch (var10) {
         case 0:
         case 1:
         case 2:
         case 3:
            return var1 == 0 ? wS : UT;
         case 4:
         case 5:
            int var11 = (var0[1] & 192) >>> 6;
            int var12 = DirectEncodedMemoryArea.get(10, 12).decodeInt(var0);
            if ((var0[0] & 64) != 0) {
               if (var3 != 0 && var5 == 31) {
                  return gp[var11];
               }

               return ld[var3 << 1 | var11];
            } else {
               if (var11 != 0 || var3 != 0 || var12 != 0 || var5 != 31 && var6 != 31) {
                  if (var3 != 0 && var5 == 31) {
                     return ys[var11];
                  }

                  return E[var3 << 1 | var11];
               }

               return sY;
            }
         case 6:
            String var16 = "Add/subtract (immediate, with tags)";
            if (var1 != 0 && var3 == 0) {
               int var17 = (var0[0] & 64) >>> 6;
               return UC.pC(PR, var17, var0, var16);
            }

            return UC.pC(var0, var16);
         case 7:
            String var15 = "Min/max (immediate)";
            if (var4 != 0) {
               return UC.pC(var0, var15);
            }

            int var14 = (var0[1] & 60) >>> 2;
            return UC.pC(cX, var14, var0, var15);
         case 8:
         case 9:
            if (var2 != 0 && var1 == 0) {
               return UC.pC(var0, "Data-Processing Immediate: Logical");
            } else if (var4 == 1 && var6 == 31 && !pC(var1, var2, var8, var9)) {
               return hZ;
            } else {
               if (var4 == 3 && var5 == 31) {
                  return UW;
               }

               return Cu[var4];
            }
         case 10:
         case 11:
            if (var4 != 1 && (var1 != 0 || var2 == 0)) {
               return oT[var4];
            }

            return UC.pC(var0, "Data-Processing Immediate: Move wide");
         case 12:
         case 13:
            if (var4 != 3 && (var1 != 0 || var2 == 0) && (var1 == 0 || var2 != 0)) {
               if (var4 == 0) {
                  if ((var1 != 0 || var8 != 31) && (var1 == 0 || var8 != 63)) {
                     if (var8 < var9) {
                        return ah;
                     }

                     if (var9 == 0) {
                        if (var8 == 7) {
                           return z;
                        }

                        if (var8 == 15) {
                           return Ek;
                        }

                        if (var8 == 31) {
                           return hK;
                        }
                     }

                     return Er;
                  }

                  return rl;
               }

               if (var4 == 1) {
                  if (var8 < var9) {
                     return var6 == 31 ? ED : xC;
                  }

                  return Sc;
               }

               if (var4 != 2) {
                  return NS[var4];
               }

               if ((var1 != 0 || var8 != 31) && (var1 == 0 || var8 != 63)) {
                  if ((var1 != 0 || var8 + 1 != var9) && (var1 == 0 || var8 + 1 != var9)) {
                     if (var8 < var9) {
                        return eP;
                     }

                     if (var3 == 0 && var9 == 0) {
                        if (var8 == 7) {
                           return FE;
                        }

                        if (var8 == 15) {
                           return Aj;
                        }
                     }

                     return EX;
                  }

                  return UO;
               }

               return Ab;
            }

            return UC.pC(var0, "Data-Processing Immediate: Bitfield");
         case 14:
         case 15:
            int var13 = var0[1] & 32;
            if (var4 == 0 && var13 == 0 && (var1 == 0 && var2 == 0 && (var0[2] & 128) == 0 || var1 != 0 && var2 != 0)) {
               if (var6 == var7) {
                  return LM;
               }

               return mv;
            }

            return UC.pC(var0, "Data-Processing Immediate: Extract");
         default:
            return null;
      }
   }

   private static boolean pC(int var0, int var1, int var2, int var3) {
      int var4 = var0 == 0 ? 32 : 64;
      if (var0 != 0 && var1 != 0) {
         return false;
      } else if (var0 == 0 && var1 == 0 && (var2 & 32) == 0) {
         return false;
      } else if (var2 < 16) {
         return -var3 % 16 <= 15 - var2;
      } else {
         return var2 >= var4 - 15 ? var3 % 16 <= var2 - (var4 - 15) : false;
      }
   }
}
