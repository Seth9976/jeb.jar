package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.units.code.asm.processor.AbstractImmediateOperandBuilder;
import com.pnfsoftware.jeb.core.units.code.asm.processor.ProcessorException;
import com.pnfsoftware.jeb.core.units.code.asm.processor.memory.DirectEncodedMemoryArea;
import com.pnfsoftware.jeb.core.units.code.asm.processor.memory.EncodedMemoryAreaList;
import com.pnfsoftware.jeb.core.units.code.asm.processor.memory.IEncodedMemoryArea;
import com.pnfsoftware.jeb.core.units.code.asm.processor.memory.VirtualEncodedMemoryArea;

public class NQ {
   private static final Ef q = new cn(Ne.RF, cn.gP, VirtualEncodedMemoryArea.get(12, 32));
   private static final Ef RF = new cn(Ne.xK, cn.gP, DirectEncodedMemoryArea.get(21, 2).shift(4));
   private static final IEncodedMemoryArea xK = new EncodedMemoryAreaList(DirectEncodedMemoryArea.get(5, 19), DirectEncodedMemoryArea.get(29, 2));
   private static final Je Dw = new Qg("ADR", YH.Uv, q(2097152, xK));
   private static final Je Uv = new Qg("ADRP", YH.Uv, q(18874368, EncodedMemoryAreaList.shift(xK, 12)));
   private static final Je[] oW = new Je[]{
      new Qg("ADD", YH.YN, YH.zx, Ne.RF), new Qg("ADD", YH.YN, YH.zx, q), new Qg("ADD", YH.Dz, YH.zx, Ne.RF).q(de.cC), new Qg("ADD", YH.Dz, YH.zx, q).q(de.cC)
   };
   private static final Je gO = new Qg("MOV", YH.YN, YH.zx);
   private static final Je[] nf = new Je[]{new Qg("CMN", YH.zx, Ne.RF), new Qg("CMN", YH.zx, q)};
   private static final Je[] gP = new Je[]{
      new Qg("SUB", YH.YN, YH.zx, Ne.RF), new Qg("SUB", YH.YN, YH.zx, q), new Qg("SUB", YH.Dz, YH.zx, Ne.RF).q(de.cC), new Qg("SUB", YH.Dz, YH.zx, q).q(de.cC)
   };
   private static final Je[] za = new Je[]{new Qg("CMP", YH.zx, Ne.RF), new Qg("CMP", YH.zx, q)};
   private static final Je[] lm = new Je[]{new Qg("MOVN", YH.Dz, RF), null, new Qg("MOVZ", YH.Dz, RF), new Qg("MOVK", YH.Dz, RF)};
   private static final Ef zz = (var0, var1) -> {
      int var2 = var0[0] & 128;
      int var3 = DirectEncodedMemoryArea.get(16, 6).decodeInt(var0);
      return CW.q((long)((var3 - (var2 == 0 ? 32 : 64)) * -1), 589824);
   };
   private static final Ef JY = (var0, var1) -> {
      int var2 = DirectEncodedMemoryArea.get(10, 6).decodeInt(var0);
      int var3 = DirectEncodedMemoryArea.get(16, 6).decodeInt(var0);
      return CW.q((long)(var2 - var3 + 1), 589824);
   };
   private static final Je[] HF = new Je[]{
      new Qg("SBFM", YH.Dz, YH.mI, go.mI, go.If), new Qg("BFM", YH.Dz, YH.mI, go.mI, go.If), new Qg("UBFM", YH.Dz, YH.mI, go.mI, go.If), null
   };
   private static final go LK = new go(1048576, DirectEncodedMemoryArea.get(10, 6));
   private static final Je io = new Qg("BFI", YH.Dz, YH.mI, zz, LK);
   private static final Je qa = new Qg("BFC", YH.Dz, zz, LK);
   private static final Je Hk = new Qg("BFXIL", YH.Dz, YH.mI, go.mI, JY);
   private static final Je Me = new Qg("SBFIZ", YH.Dz, YH.mI, zz, LK);
   private static final Je PV = new Qg("UBFIZ", YH.Dz, YH.mI, zz, LK);
   private static final Je oQ = new Qg("LSL", YH.Dz, YH.mI, zz);
   private static final Je xW = new Qg("LSR", YH.Dz, YH.mI, go.TX);
   private static final Je KT = new Qg("ASR", YH.Dz, YH.mI, go.TX);
   private static final Je Gf = new Qg("SXTB", YH.Dz, YH.KT);
   private static final Je Ef = new Qg("SXTH", YH.Dz, YH.KT);
   private static final Je cC = new Qg("SXTW", YH.Uv, YH.KT);
   private static final Je sH = new Qg("SBFX", YH.Dz, YH.mI, go.mI, JY);
   private static final Je CE = new Qg("UXTB", YH.PV, YH.KT);
   private static final Je wF = new Qg("UXTH", YH.PV, YH.KT);
   private static final Je If = new Qg("UBFX", YH.Dz, YH.mI, go.mI, JY);
   private static final Je Dz = new Qg("ROR", YH.Dz, YH.mI, go.If);
   private static final Je mI = new Qg("EXTR", YH.Dz, YH.mI, YH.ui, go.If);
   private static final Ef jq = (var0, var1) -> {
      int var2 = var0[0] & 128;
      return CW.q(
         64,
         xB.q(
            DirectEncodedMemoryArea.get(22, 1).decodeInt(var0),
            DirectEncodedMemoryArea.get(10, 6).decodeInt(var0),
            DirectEncodedMemoryArea.get(16, 6).decodeInt(var0),
            var2 == 0 ? 32 : 64
         )
      );
   };
   private static final Ef[] ui = new Ef[]{YH.YN, YH.mI, jq};
   private static final Je[] TX = new Je[]{new Qg("AND", ui), new Qg("ORR", ui), new Qg("EOR", ui), new Qg("AND", YH.Dz, YH.mI, jq).q(de.cC)};
   private static final Je Rr = new Qg("MOV", YH.YN, jq);
   private static final Je EB = new Qg("TST", YH.mI, jq);
   private static final Je[] Xo = new Je[]{new Qg("ADDG", YH.io, YH.qa, go.ui, go.io).q(QJ.jb), new Qg("SUBG", YH.io, YH.qa, go.ui, go.io).q(QJ.jb)};
   private static final Je[] Bu = new Je[]{
      new Qg("SMAX", YH.Dz, YH.mI, go.zx).q(QJ.fn),
      new Qg("UMAX", YH.Dz, YH.mI, go.Rv).q(QJ.fn),
      new Qg("SMIN", YH.Dz, YH.mI, go.zx).q(QJ.fn),
      new Qg("UMIN", YH.Dz, YH.mI, go.Rv).q(QJ.fn)
   };

   private static go q(int var0, IEncodedMemoryArea var1) {
      return new go(AbstractImmediateOperandBuilder.ImmediateType.SignExtend64, var0, var1);
   }

   public static Je q(byte[] var0) throws ProcessorException {
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
            return var1 == 0 ? Dw : Uv;
         case 4:
         case 5:
            int var11 = (var0[1] & 192) >>> 6;
            int var12 = DirectEncodedMemoryArea.get(10, 12).decodeInt(var0);
            if ((var0[0] & 64) != 0) {
               if (var3 != 0 && var5 == 31) {
                  return za[var11];
               }

               return gP[var3 << 1 | var11];
            } else {
               if (var11 != 0 || var3 != 0 || var12 != 0 || var5 != 31 && var6 != 31) {
                  if (var3 != 0 && var5 == 31) {
                     return nf[var11];
                  }

                  return oW[var3 << 1 | var11];
               }

               return gO;
            }
         case 6:
            String var16 = "Add/subtract (immediate, with tags)";
            if (var1 != 0 && var3 == 0) {
               int var17 = (var0[0] & 64) >>> 6;
               return Qg.q(Xo, var17, var0, var16);
            }

            return Qg.q(var0, var16);
         case 7:
            String var15 = "Min/max (immediate)";
            if (var4 != 0) {
               return Qg.q(var0, var15);
            }

            int var14 = (var0[1] & 60) >>> 2;
            return Qg.q(Bu, var14, var0, var15);
         case 8:
         case 9:
            if (var2 != 0 && var1 == 0) {
               return Qg.q(var0, "Data-Processing Immediate: Logical");
            } else if (var4 == 1 && var6 == 31 && !q(var1, var2, var8, var9)) {
               return Rr;
            } else {
               if (var4 == 3 && var5 == 31) {
                  return EB;
               }

               return TX[var4];
            }
         case 10:
         case 11:
            if (var4 != 1 && (var1 != 0 || var2 == 0)) {
               return lm[var4];
            }

            return Qg.q(var0, "Data-Processing Immediate: Move wide");
         case 12:
         case 13:
            if (var4 != 3 && (var1 != 0 || var2 == 0) && (var1 == 0 || var2 != 0)) {
               if (var4 == 0) {
                  if ((var1 != 0 || var8 != 31) && (var1 == 0 || var8 != 63)) {
                     if (var8 < var9) {
                        return Me;
                     }

                     if (var9 == 0) {
                        if (var8 == 7) {
                           return Gf;
                        }

                        if (var8 == 15) {
                           return Ef;
                        }

                        if (var8 == 31) {
                           return cC;
                        }
                     }

                     return sH;
                  }

                  return KT;
               }

               if (var4 == 1) {
                  if (var8 < var9) {
                     return var6 == 31 ? qa : io;
                  }

                  return Hk;
               }

               if (var4 != 2) {
                  return HF[var4];
               }

               if ((var1 != 0 || var8 != 31) && (var1 == 0 || var8 != 63)) {
                  if ((var1 != 0 || var8 + 1 != var9) && (var1 == 0 || var8 + 1 != var9)) {
                     if (var8 < var9) {
                        return PV;
                     }

                     if (var3 == 0 && var9 == 0) {
                        if (var8 == 7) {
                           return CE;
                        }

                        if (var8 == 15) {
                           return wF;
                        }
                     }

                     return If;
                  }

                  return oQ;
               }

               return xW;
            }

            return Qg.q(var0, "Data-Processing Immediate: Bitfield");
         case 14:
         case 15:
            int var13 = var0[1] & 32;
            if (var4 == 0 && var13 == 0 && (var1 == 0 && var2 == 0 && (var0[2] & 128) == 0 || var1 != 0 && var2 != 0)) {
               if (var6 == var7) {
                  return Dz;
               }

               return mI;
            }

            return Qg.q(var0, "Data-Processing Immediate: Extract");
         default:
            return null;
      }
   }

   private static boolean q(int var0, int var1, int var2, int var3) {
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
