package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.units.code.asm.processor.ProcessorException;
import com.pnfsoftware.jeb.core.units.code.asm.processor.memory.DirectEncodedMemoryArea;
import com.pnfsoftware.jeb.util.io.EndianUtil;

public class Xc {
   private static final short[] pC = new short[]{18112};

   public tz pC(byte[] var1) throws ProcessorException {
      short var2 = EndianUtil.bigEndianBytesToShort(var1);
      if (var2 == pC[0]) {
         return Eb.ys;
      } else {
         int var3 = (var1[0] & 224) >>> 5;
         int var4 = (var1[0] & 28) >>> 2;
         switch (var3) {
            case 0:
               if ((var1[0] & 24) >>> 3 == 3) {
                  return bQ.NS[(var1[0] & 6) >>> 1];
               }

               int var9 = var4 >> 1;
               long var11 = DirectEncodedMemoryArea.get(6, 5).decode(var1);
               return bQ.Sc[var9 << 1 | (var11 == 0L ? 0 : 1)];
            case 1:
               return bQ.ah[(var1[0] & 24) >>> 3];
            case 2:
               if (var4 == 0) {
                  return bQ.eP[(var1[0] & 3) << 2 | (var1[1] & 192) >>> 6];
               }

               if (var4 == 1) {
                  int var8 = var1[0] & 3;
                  if (var8 != 3) {
                     if (var8 != 0) {
                        return bQ.vP[var8];
                     }

                     int var10 = (var1[1] & 120) >>> 3;
                     if (var10 == 13) {
                        return bQ.xC;
                     }

                     int var12 = (var1[1] & 128) >>> 4 | var1[1] & 7;
                     if (var12 == 13) {
                        return bQ.ED;
                     }

                     return bQ.vP[var8];
                  }

                  if ((var1[1] & 7) == 0) {
                     return wJ.WR[(var1[1] & 128) >>> 7];
                  }
               } else {
                  if ((var4 & 6) == 2) {
                     return gx.sO;
                  }

                  if ((var4 & 4) == 4) {
                     return gx.os[(var1[0] & 14) >>> 1];
                  }
               }

               UC.pC(var1, "Misc 16-bits instructions");
               break;
            case 3:
               return gx.Cu[(var1[0] & 24) >>> 3];
            case 4:
               return gx.hZ[(var1[0] & 24) >>> 3];
            case 5:
               if ((var1[0] & 16) == 0) {
                  if ((var1[0] & 8) == 0) {
                     return bQ.Er;
                  }

                  return bQ.FE;
               }

               int var5 = var1[0] & 15;
               int var6 = (var1[1] & 192) >>> 6;
               switch (var5) {
                  case 0:
                     if ((var1[1] & 128) == 0) {
                        return bQ.Aj;
                     }

                     return bQ.EX;
                  case 1:
                  case 3:
                  case 9:
                  case 11:
                     if ((var1[0] & 8) == 0) {
                        return wJ.vP;
                     }

                     return wJ.NS;
                  case 2:
                     return qf.E[var6];
                  case 4:
                  case 5:
                  case 12:
                  case 13:
                     if (var1[1] == 0 && (var1[0] & 1) == 0) {
                        return UC.pC(var1, "Push/Pop");
                     }

                     if ((var1[0] & 8) == 0) {
                        return gI.wS;
                     }

                     return gI.UT;
                  case 6:
                     if (var6 == 0) {
                        if ((var1[1] & 247) == 16) {
                           return wZ.Ab;
                        }
                     } else if (var6 == 1) {
                        if ((var1[1] & 32) == 0) {
                           if ((var1[1] & 7) == 0) {
                              return wZ.Sc;
                           }
                        } else if ((var1[1] & 8) == 0) {
                           return wZ.vP[(var1[1] & 16) >>> 4];
                        }
                     }

                     UC.pC(var1, "Misc 16-bits instructions");
                     return null;
                  case 7:
                  case 8:
                     UC.pC(var1, "Misc 16-bits instructions");
                     return null;
                  case 10:
                     return gU.A[var6];
                  case 14:
                     return bb.WR;
                  case 15:
                     int var7 = var1[1] & 15;
                     if (var7 == 0) {
                        if ((var1[1] & 96) != 96 && (var1[1] & 128) != 128) {
                           return Eb.xC[(var1[1] & 240) >>> 4];
                        }

                        return Eb.gp;
                     }

                     return wJ.pC(var1);
                  default:
                     return null;
               }
            case 6:
               if ((var1[0] & 16) == 0) {
                  if (var1[1] == 0) {
                     return UC.pC(var1, "Ldm/Stm");
                  }

                  if ((var1[0] & 8) == 0) {
                     return gI.E;
                  }

                  return gI.sY;
               }

               if ((var1[0] & 14) == 14) {
                  if ((var1[0] & 1) == 0) {
                     return bb.NS;
                  }

                  return bb.vP;
               }

               return wJ.xC;
            case 7:
               if ((var1[0] & 24) == 0) {
                  return wJ.ED;
               }
         }

         return null;
      }
   }
}
