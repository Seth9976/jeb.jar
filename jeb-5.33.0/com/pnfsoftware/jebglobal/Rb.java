package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.units.code.asm.processor.ProcessorException;
import com.pnfsoftware.jeb.core.units.code.asm.processor.memory.DirectEncodedMemoryArea;
import com.pnfsoftware.jeb.util.io.EndianUtil;
import com.pnfsoftware.jeb.util.logging.GlobalLog;
import com.pnfsoftware.jeb.util.logging.ILogger;

public class Rb {
   private static final ILogger pC = GlobalLog.getLogger(Rb.class);
   private static final int[] A = new int[]{0, -509607936};

   public static tz pC(byte[] var0) throws ProcessorException {
      int var1 = (var0[0] & 14) >>> 1;
      if ((var0[0] & 248) == 240) {
         return wS(var0);
      } else {
         switch (var1) {
            case 0:
               int var2 = EndianUtil.bigEndianBytesToInt(var0);
               if (var2 != A[0] && var2 != A[1]) {
                  int var3 = (var0[3] & 240) >>> 4;
                  if (var3 == 9) {
                     if ((var0[0] & 1) == 0) {
                        int var8 = (var0[1] & 224) >>> 5;
                        return NN.pC[var8];
                     }

                     if ((var0[1] & 128) == 0) {
                        if ((var0[1] & 176) == 0) {
                           return gx.OB[(var0[1] & 64) >>> 6];
                        }

                        pC(var0, "Data-Processing/Synchronization primitive");
                     } else {
                        String var4 = "Load/Store Exclusive and Load-Acquire/Store-Release";
                        int var16 = (var0[1] & 96) >>> 5;
                        int var19 = (var0[1] & 16) >>> 2 | var0[2] & 3;
                        if ((var0[2] & 12) == 12) {
                           return UC.pC(gx.PR, var16, var19, var0, var4);
                        }
                     }
                  } else if ((var3 & 9) == 9) {
                     int var9 = var0[1] & 16 | (var0[3] & 96) >>> 3 | ZC.pC(var0, 1) | ZC.kS(var0, 0);
                     int var17 = var9 >>> 2;
                     if (var9 >= 8 && var9 <= 15) {
                        int var20 = (var0[2] & 240) >>> 4;
                        if (var20 >= 14) {
                           throw new com.pnfsoftware.jeb.corei.parsers.arm.DH("Unpredictable Rt value");
                        }
                     }

                     if ((var0[1] & 64) != 0) {
                        int var21 = var0[1] & 15;
                        if (var21 == 15 && (var9 & 28) == 8 && var9 != 10) {
                           return null;
                        }

                        if ((var9 & 3) == 1) {
                           return gx.NS[var17];
                        }

                        return gx.WR[var17];
                     }

                     if ((var0[2] & 15) == 0) {
                        if ((var9 & 3) == 1) {
                           return gx.xC[var17];
                        }

                        return gx.vP[var17];
                     }

                     pC(var0, "Data-Processing/Synchronization primitive");
                  } else if ((var0[0] & 1) == 1 && (var0[1] & 144) == 0) {
                     int var10 = (var0[3] & 240) >>> 4;
                     int var18 = (var0[1] & 96) >>> 5;
                     switch (var10) {
                        case 0:
                           return wZ.A(var0);
                        case 1:
                        case 2:
                        case 3:
                           if (var18 == 1 && (var0[1] & 15) == 15 && (var0[2] & 255) == 255) {
                              return wJ.fI[(var0[3] & 48) >>> 4];
                           }

                           if (var18 == 3 && var10 == 1 && (var0[1] & 15) == 15 && (var0[2] & 15) == 15) {
                              return gU.NS;
                           }

                           return null;
                        case 4:
                           if ((var0[2] & 13) == 0) {
                              return fA.pC[(var0[1] & 96) >>> 4 | (var0[2] & 2) >>> 1];
                           }

                           pC(var0, "Data-Processing/Move Special register");
                           break;
                        case 5:
                           if ((var0[2] & 15) == 0) {
                              return qf.kS[(var0[1] & 96) >>> 5];
                           }

                           pC(var0, "Data-Processing/Saturate");
                           break;
                        case 6:
                           if (var18 == 3) {
                              return bb.ys;
                           }
                           break;
                        case 7:
                           int var22 = (var0[1] & 96) >>> 5;
                           if (var22 != 3 || (var0[1] & 15) == 0 && (var0[2] & 255) == 0) {
                              return bb.sY[var22];
                           }

                           return null;
                        default:
                           if ((var0[3] & 144) != 128) {
                              throw new ProcessorException("Unexpected part in the parsing algorithm");
                           }

                           int var7 = (var0[1] & 96) >>> 3 | (var0[3] & 96) >>> 5;
                           if ((var7 == 5 || var7 == 7 || var7 >= 12) && (var0[2] & 240) != 0) {
                              pC(var0, "Data-Processing/SMUL");
                           }

                           return NN.A[var7];
                     }

                     pC(var0, "Data-Processing/Miscellaneous");
                  }

                  int var11 = bQ.A(var0);
                  if (var11 >= 8 && var11 <= 11 && (!ji.Ek.hasS(var0, null) || (var0[2] & 240) != 0)) {
                     pC(var0, "Data-Processing/TST/TEQ/CMP/CMN");
                  }

                  if ((var11 == 13 || var11 == 15) && (var0[1] & 15) != 0) {
                     pC(var0, "Data-Processing/MOV/MVN");
                  }

                  if ((var0[3] & 16) == 0) {
                     if (var11 == 13) {
                        var11 = (DirectEncodedMemoryArea.get(7, 5).decode(var0) == 0L ? 0 : 1) << 2 | (var0[3] & 96) >>> 5;
                        return bQ.E[var11];
                     } else {
                        return bQ.UT[var11];
                     }
                  } else if (var11 == 13) {
                     var11 = (var0[3] & 96) >>> 5;
                     return bQ.ys[var11];
                  } else {
                     return bQ.sY[var11];
                  }
               } else {
                  return Eb.ys;
               }
            case 1:
               if ((var0[0] & 1) == 0) {
                  return bQ.pC(var0);
               } else if ((var0[1] & 176) == 0) {
                  if ((var0[1] & 64) == 0) {
                     return bQ.WR;
                  }

                  return gU.wS;
               } else {
                  if ((var0[1] & 176) == 32) {
                     if ((var0[1] & 79) == 0) {
                        if ((var0[2] & 255) == 240) {
                           return Eb.pC(var0, 32);
                        }

                        return null;
                     }

                     return wZ.A(var0);
                  }

                  return bQ.pC(var0);
               }
            case 2:
               if (DirectEncodedMemoryArea.get(0, 12).decode(var0) == 4L) {
                  if ((var0[0] & 1) == 0 && (var0[1] & 255) == 157) {
                     return gx.kS;
                  }

                  if ((var0[0] & 1) == 1 && (var0[1] & 255) == 45) {
                     return gx.wS;
                  }
               }

               int var15 = ZC.wS(var0, 1) | (var0[1] & 64) >>> 6;
               if (ZC.pC(var0, 0) == 0 && ZC.kS(var0, 0) != 0) {
                  return gx.A[var15];
               }

               return gx.pC[var15];
            case 3:
               if ((var0[3] & 16) == 0) {
                  int var14 = ZC.wS(var0, 1) | (var0[1] & 64) >>> 6;
                  if (ZC.pC(var0, 0) == 0 && ZC.kS(var0, 0) != 0) {
                     return gx.ld[var14];
                  }

                  return gx.ys[var14];
               } else {
                  tz var5 = A(var0);
                  if (var5 != null) {
                     return var5;
                  }

                  pC(var0, "Media Instructions");
                  return null;
               }
            case 4:
            case 5:
               return kS(var0);
            case 6:
            case 7:
               int var6 = var0[0] & 3;
               if (var6 == 3) {
                  if ((var0[0] & 240) == 240) {
                     pC(var0, "Supervisor call");
                  }

                  return bb.ld;
               }

               return Ua.pC(var0, 32);
            default:
               throw new ProcessorException("Unexpected part in the parsing algorithm");
         }
      }
   }

   private static tz kS(byte[] var0) throws ProcessorException {
      int var1 = (var0[0] & 2) >>> 1;
      if (var1 == 0) {
         if ((var0[0] & 240) != 240) {
            return gI.pC(var0);
         } else {
            int var2 = ZC.wS(var0, 0);
            return (var2 != 0 || (var0[1] & 15) != 13 || (var0[2] & 255) != 5 || (var0[3] & 224) != 0)
                  && (var2 == 0 || (var0[2] & 255) != 10 || (var0[3] & 255) != 0)
               ? null
               : bb.pC(var0);
         }
      } else {
         return wJ.oT[ZC.UT(var0, 1) | var0[0] & 1];
      }
   }

   public static void pC(byte[] var0, String var1) throws ProcessorException {
      UC.pC(var0, var1);
   }

   private static tz wS(byte[] var0) throws ProcessorException {
      int var1 = var0[0] & 7;
      int var2 = (var0[1] & 16) >>> 4;
      switch (var1) {
         case 0:
         case 1:
            if ((var0[0] & 255) == 241) {
               int var8 = (var0[1] & 240) >>> 4;
               if (var8 == 0 && (var0[3] & 32) == 0) {
                  if ((var0[2] & 252) == 0) {
                     if ((var0[1] & 1) == 0 && (var0[2] & 2) == 0) {
                        return wZ.NS[(var0[1] & 14) >>> 1];
                     }

                     if (var0[1] == 1 && (var0[2] & 1) == 0 && var0[3] == 0) {
                        return wZ.ED;
                     }
                  }
               } else if (var8 == 1 && (var0[3] & 240) == 0) {
                  if ((var0[1] & 15) == 0 && (var0[2] & 253) == 0 && var0[3] == 0) {
                     return wZ.UO;
                  }
               } else if ((var0[1] & 192) == 0 && (var0[3] & 240) == 112) {
                  pC(var0, "UNPREDICTABLE: Unconditional/Miscellaneous");
               }
            }

            pC(var0, "Unconditional/Miscellaneous");
            break;
         case 2:
         case 3:
            return BN.pC(var0, 32);
         case 4:
         case 5:
         case 6:
         case 7:
            if (var2 == 0) {
               if (var1 == 4) {
                  return Yw.pC(var0, 32);
               }

               return null;
            }

            if ((var0[2] & 240) == 240) {
               int var3 = (var0[0] & 3) << 3 | (var0[1] & 224) >>> 5;
               int var4 = (var0[3] & 16) >>> 4;
               int var5 = var0[0] & 1;
               int var6 = (var0[1] & 64) >>> 6;
               int var7 = var5 << 1 | var6;
               if (var3 == 11) {
                  if ((var0[1] & 15) == 15 && (var0[2] & 15) == 0) {
                     return Eb.A(var0, 32);
                  }
               } else if ((var3 & 17) == 0) {
                  if (var7 == 3 && (var0[1] & 15) == 15) {
                     return gx.UT;
                  }

                  switch (var7) {
                     case 0:
                     default:
                        pC(var0, "Memory Hints/Preload Immediate");
                        break;
                     case 1:
                        return gx.sY;
                     case 2:
                        return gx.E;
                     case 3:
                        return gx.UT;
                  }
               } else if ((var3 & 17) == 16 && var4 == 0) {
                  switch (var7) {
                     case 0:
                     default:
                        pC(var0, "Memory Hints/Preload Register");
                        break;
                     case 1:
                        return gx.fI;
                     case 2:
                        return gx.oT;
                     case 3:
                        return gx.gp;
                  }
               }
            }

            pC(var0, "Unconditional");
      }

      return null;
   }

   public static tz A(byte[] var0) throws ProcessorException {
      String var1 = "Media Instructions";
      int var2 = (var0[0] & 1) << 4 | (var0[1] & 240) >>> 4;
      int var3 = (var0[3] & 224) >>> 5;
      int var4 = (var2 & 24) >>> 3;
      int var5 = var2 & 7;
      switch (var4) {
         case 0:
            if ((var0[2] & 15) == 15) {
               int var10 = (var0[1] & 112) >>> 4;
               int var7 = (var0[3] & 96) >>> 4 | (var0[3] & 128) >>> 7;
               return UC.pC(qf.pC, var10, var7, var0, var1);
            }

            return null;
         case 1:
            if (var5 == 0 && var3 == 5) {
               if ((var0[2] & 15) == 15) {
                  return gU.WR;
               }
            } else {
               if (var5 == 0 && (var3 & 1) == 0) {
                  int var9 = var0[3] & 64;
                  if (var9 == 0) {
                     return qf.ys;
                  }

                  return qf.ld;
               }

               if (var5 == 2 && var3 == 1) {
                  if ((var0[2] & 15) == 15) {
                     return qf.fI;
                  }
               } else if (var5 == 6 && var3 == 1) {
                  if ((var0[2] & 15) == 15) {
                     return qf.WR;
                  }
               } else if ((var5 & 3) == 3 && (var3 & 3) == 1) {
                  if ((var0[1] & 15) == 15 && (var0[2] & 15) == 15) {
                     return gU.pC[(var0[1] & 64) >>> 5 | (var0[3] & 128) >>> 7];
                  }
               } else {
                  if ((var5 & 2) == 2 && (var3 & 1) == 0) {
                     int var8 = var5 & 4;
                     if (var8 == 0) {
                        return qf.NS;
                     }

                     return qf.vP;
                  }

                  if (var3 == 3) {
                     if ((var0[2] & 3) == 0) {
                        return UC.pC(qf.UT, (var0[1] & 48) >>> 4, (var0[1] & 64) >>> 5 | ZC.E(var0, 0), var0, var1);
                     }

                     return null;
                  }
               }
            }

            return null;
         case 2:
            int var6 = (var0[1] & 112) >>> 4;
            switch (var6) {
               case 0:
                  return UC.pC(NN.kS, NN.pC(var0), var0, "SMLAD/SMLSD");
               case 1:
                  if (var3 == 0 && (var0[2] & 240) == 240) {
                     return NN.E;
                  }

                  return null;
               case 2:
                  return null;
               case 3:
                  if (var3 == 0 && (var0[2] & 240) == 240) {
                     return NN.sY;
                  }

                  return null;
               case 4:
                  return UC.pC(NN.wS, NN.pC(var0), var0, "SMLALD/SMLSLD");
               case 5:
                  return NN.UT[NN.pC(var0)];
               case 6:
               case 7:
               default:
                  return null;
            }
         case 3:
            if ((var5 == 4 || var5 == 5) && (var3 == 0 || var3 == 4)) {
               if ((var0[3] & 15) == 15) {
                  return gU.E;
               }

               return gU.UT;
            }

            if (var5 == 7 && var3 == 7) {
               if ((var0[0] & 240) == 224) {
                  return bb.fI;
               }
            } else {
               if ((var5 & 2) == 2 && (var3 & 3) == 2) {
                  if ((var0[1] & 64) == 0) {
                     return gU.sY;
                  }

                  return gU.ys;
               }

               if (var5 == 0 && var3 == 0) {
                  if ((var0[2] & 240) == 240) {
                     return gU.ld;
                  }

                  return gU.gp;
               }
            }
      }

      return null;
   }
}
