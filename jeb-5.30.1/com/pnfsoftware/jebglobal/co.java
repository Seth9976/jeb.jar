package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.units.code.asm.processor.ProcessorException;
import com.pnfsoftware.jeb.core.units.code.asm.processor.memory.DirectEncodedMemoryArea;
import com.pnfsoftware.jeb.util.io.EndianUtil;

public class co {
   private static final short[] q = new short[]{18112};

   public Je q(byte[] var1) throws ProcessorException {
      short var2 = EndianUtil.bigEndianBytesToShort(var1);
      if (var2 == q[0]) {
         return iC.nf;
      } else {
         int var3 = (var1[0] & 224) >>> 5;
         int var4 = (var1[0] & 28) >>> 2;
         switch (var3) {
            case 0:
               if ((var1[0] & 24) >>> 3 == 3) {
                  return Xu.HF[(var1[0] & 6) >>> 1];
               }

               int var9 = var4 >> 1;
               long var11 = DirectEncodedMemoryArea.get(6, 5).decode(var1);
               return Xu.Hk[var9 << 1 | (var11 == 0L ? 0 : 1)];
            case 1:
               return Xu.Me[(var1[0] & 24) >>> 3];
            case 2:
               if (var4 == 0) {
                  return Xu.PV[(var1[0] & 3) << 2 | (var1[1] & 192) >>> 6];
               }

               if (var4 == 1) {
                  int var8 = var1[0] & 3;
                  if (var8 != 3) {
                     if (var8 != 0) {
                        return Xu.LK[var8];
                     }

                     int var10 = (var1[1] & 120) >>> 3;
                     if (var10 == 13) {
                        return Xu.io;
                     }

                     int var12 = (var1[1] & 128) >>> 4 | var1[1] & 7;
                     if (var12 == 13) {
                        return Xu.qa;
                     }

                     return Xu.LK[var8];
                  }

                  if ((var1[1] & 7) == 0) {
                     return oR.JY[(var1[1] & 128) >>> 7];
                  }
               } else {
                  if ((var4 & 6) == 2) {
                     return aD.jq;
                  }

                  if ((var4 & 4) == 4) {
                     return aD.ui[(var1[0] & 14) >>> 1];
                  }
               }

               Qg.q(var1, "Misc 16-bits instructions");
               break;
            case 3:
               return aD.TX[(var1[0] & 24) >>> 3];
            case 4:
               return aD.Rr[(var1[0] & 24) >>> 3];
            case 5:
               if ((var1[0] & 16) == 0) {
                  if ((var1[0] & 8) == 0) {
                     return Xu.sH;
                  }

                  return Xu.CE;
               }

               int var5 = var1[0] & 15;
               int var6 = (var1[1] & 192) >>> 6;
               switch (var5) {
                  case 0:
                     if ((var1[1] & 128) == 0) {
                        return Xu.wF;
                     }

                     return Xu.If;
                  case 1:
                  case 3:
                  case 9:
                  case 11:
                     if ((var1[0] & 8) == 0) {
                        return oR.LK;
                     }

                     return oR.HF;
                  case 2:
                     return Lx.oW[var6];
                  case 4:
                  case 5:
                  case 12:
                  case 13:
                     if (var1[1] == 0 && (var1[0] & 1) == 0) {
                        return Qg.q(var1, "Push/Pop");
                     }

                     if ((var1[0] & 8) == 0) {
                        return rU.Dw;
                     }

                     return rU.Uv;
                  case 6:
                     if (var6 == 0) {
                        if ((var1[1] & 247) == 16) {
                           return Fh.xW;
                        }
                     } else if (var6 == 1) {
                        if ((var1[1] & 32) == 0) {
                           if ((var1[1] & 7) == 0) {
                              return Fh.Hk;
                           }
                        } else if ((var1[1] & 8) == 0) {
                           return Fh.LK[(var1[1] & 16) >>> 4];
                        }
                     }

                     Qg.q(var1, "Misc 16-bits instructions");
                     return null;
                  case 7:
                  case 8:
                     Qg.q(var1, "Misc 16-bits instructions");
                     return null;
                  case 10:
                     return Lm.RF[var6];
                  case 14:
                     return oY.JY;
                  case 15:
                     int var7 = var1[1] & 15;
                     if (var7 == 0) {
                        if ((var1[1] & 96) != 96 && (var1[1] & 128) != 128) {
                           return iC.io[(var1[1] & 240) >>> 4];
                        }

                        return iC.za;
                     }

                     return oR.q(var1);
                  default:
                     return null;
               }
            case 6:
               if ((var1[0] & 16) == 0) {
                  if (var1[1] == 0) {
                     return Qg.q(var1, "Ldm/Stm");
                  }

                  if ((var1[0] & 8) == 0) {
                     return rU.oW;
                  }

                  return rU.gO;
               }

               if ((var1[0] & 14) == 14) {
                  if ((var1[0] & 1) == 0) {
                     return oY.HF;
                  }

                  return oY.LK;
               }

               return oR.io;
            case 7:
               if ((var1[0] & 24) == 0) {
                  return oR.qa;
               }
         }

         return null;
      }
   }
}
