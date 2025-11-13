package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.units.code.asm.processor.ProcessorException;
import com.pnfsoftware.jeb.core.units.code.asm.processor.memory.DirectEncodedMemoryArea;
import com.pnfsoftware.jeb.util.io.EndianUtil;
import com.pnfsoftware.jeb.util.logging.GlobalLog;
import com.pnfsoftware.jeb.util.logging.ILogger;

public class vf {
   private static final ILogger q = GlobalLog.getLogger(vf.class);
   private static final int[] RF = new int[]{0, -509607936};

   public static Je q(byte[] var0) throws ProcessorException {
      int var1 = (var0[0] & 14) >>> 1;
      if ((var0[0] & 248) == 240) {
         return Dw(var0);
      } else {
         switch (var1) {
            case 0:
               int var2 = EndianUtil.bigEndianBytesToInt(var0);
               if (var2 != RF[0] && var2 != RF[1]) {
                  int var3 = (var0[3] & 240) >>> 4;
                  if (var3 == 9) {
                     if ((var0[0] & 1) == 0) {
                        int var8 = (var0[1] & 224) >>> 5;
                        return Do.q[var8];
                     }

                     if ((var0[1] & 128) == 0) {
                        if ((var0[1] & 176) == 0) {
                           return aD.eJ[(var0[1] & 64) >>> 6];
                        }

                        q(var0, "Data-Processing/Synchronization primitive");
                     } else {
                        String var4 = "Load/Store Exclusive and Load-Acquire/Store-Release";
                        int var16 = (var0[1] & 96) >>> 5;
                        int var19 = (var0[1] & 16) >>> 2 | var0[2] & 3;
                        if ((var0[2] & 12) == 12) {
                           return Qg.q(aD.Xo, var16, var19, var0, var4);
                        }
                     }
                  } else if ((var3 & 9) == 9) {
                     int var9 = var0[1] & 16 | (var0[3] & 96) >>> 3 | HS.q(var0, 1) | HS.xK(var0, 0);
                     int var17 = var9 >>> 2;
                     if (var9 >= 8 && var9 <= 15) {
                        int var20 = (var0[2] & 240) >>> 4;
                        if (var20 >= 14) {
                           throw new zL("Unpredictable Rt value");
                        }
                     }

                     if ((var0[1] & 64) != 0) {
                        int var21 = var0[1] & 15;
                        if (var21 == 15 && (var9 & 28) == 8 && var9 != 10) {
                           return null;
                        }

                        if ((var9 & 3) == 1) {
                           return aD.HF[var17];
                        }

                        return aD.JY[var17];
                     }

                     if ((var0[2] & 15) == 0) {
                        if ((var9 & 3) == 1) {
                           return aD.io[var17];
                        }

                        return aD.LK[var17];
                     }

                     q(var0, "Data-Processing/Synchronization primitive");
                  } else if ((var0[0] & 1) == 1 && (var0[1] & 144) == 0) {
                     int var10 = (var0[3] & 240) >>> 4;
                     int var18 = (var0[1] & 96) >>> 5;
                     switch (var10) {
                        case 0:
                           return Fh.RF(var0);
                        case 1:
                        case 2:
                        case 3:
                           if (var18 == 1 && (var0[1] & 15) == 15 && (var0[2] & 255) == 255) {
                              return oR.zz[(var0[3] & 48) >>> 4];
                           }

                           if (var18 == 3 && var10 == 1 && (var0[1] & 15) == 15 && (var0[2] & 15) == 15) {
                              return Lm.HF;
                           }

                           return null;
                        case 4:
                           if ((var0[2] & 13) == 0) {
                              return Cf.q[(var0[1] & 96) >>> 4 | (var0[2] & 2) >>> 1];
                           }

                           q(var0, "Data-Processing/Move Special register");
                           break;
                        case 5:
                           if ((var0[2] & 15) == 0) {
                              return Lx.xK[(var0[1] & 96) >>> 5];
                           }

                           q(var0, "Data-Processing/Saturate");
                           break;
                        case 6:
                           if (var18 == 3) {
                              return oY.nf;
                           }
                           break;
                        case 7:
                           int var22 = (var0[1] & 96) >>> 5;
                           if (var22 != 3 || (var0[1] & 15) == 0 && (var0[2] & 255) == 0) {
                              return oY.gO[var22];
                           }

                           return null;
                        default:
                           if ((var0[3] & 144) != 128) {
                              throw new ProcessorException("Unexpected part in the parsing algorithm");
                           }

                           int var7 = (var0[1] & 96) >>> 3 | (var0[3] & 96) >>> 5;
                           if ((var7 == 5 || var7 == 7 || var7 >= 12) && (var0[2] & 240) != 0) {
                              q(var0, "Data-Processing/SMUL");
                           }

                           return Do.RF[var7];
                     }

                     q(var0, "Data-Processing/Miscellaneous");
                  }

                  int var11 = Xu.RF(var0);
                  if (var11 >= 8 && var11 <= 11 && (!de.Ef.hasS(var0, null) || (var0[2] & 240) != 0)) {
                     q(var0, "Data-Processing/TST/TEQ/CMP/CMN");
                  }

                  if ((var11 == 13 || var11 == 15) && (var0[1] & 15) != 0) {
                     q(var0, "Data-Processing/MOV/MVN");
                  }

                  if ((var0[3] & 16) == 0) {
                     if (var11 == 13) {
                        var11 = (DirectEncodedMemoryArea.get(7, 5).decode(var0) == 0L ? 0 : 1) << 2 | (var0[3] & 96) >>> 5;
                        return Xu.oW[var11];
                     } else {
                        return Xu.Uv[var11];
                     }
                  } else if (var11 == 13) {
                     var11 = (var0[3] & 96) >>> 5;
                     return Xu.nf[var11];
                  } else {
                     return Xu.gO[var11];
                  }
               } else {
                  return iC.nf;
               }
            case 1:
               if ((var0[0] & 1) == 0) {
                  return Xu.q(var0);
               } else if ((var0[1] & 176) == 0) {
                  if ((var0[1] & 64) == 0) {
                     return Xu.JY;
                  }

                  return Lm.Dw;
               } else {
                  if ((var0[1] & 176) == 32) {
                     if ((var0[1] & 79) == 0) {
                        if ((var0[2] & 255) == 240) {
                           return iC.q(var0, 32);
                        }

                        return null;
                     }

                     return Fh.RF(var0);
                  }

                  return Xu.q(var0);
               }
            case 2:
               if (DirectEncodedMemoryArea.get(0, 12).decode(var0) == 4L) {
                  if ((var0[0] & 1) == 0 && (var0[1] & 255) == 157) {
                     return aD.xK;
                  }

                  if ((var0[0] & 1) == 1 && (var0[1] & 255) == 45) {
                     return aD.Dw;
                  }
               }

               int var15 = HS.Dw(var0, 1) | (var0[1] & 64) >>> 6;
               if (HS.q(var0, 0) == 0 && HS.xK(var0, 0) != 0) {
                  return aD.RF[var15];
               }

               return aD.q[var15];
            case 3:
               if ((var0[3] & 16) == 0) {
                  int var14 = HS.Dw(var0, 1) | (var0[1] & 64) >>> 6;
                  if (HS.q(var0, 0) == 0 && HS.xK(var0, 0) != 0) {
                     return aD.gP[var14];
                  }

                  return aD.nf[var14];
               } else {
                  Je var5 = RF(var0);
                  if (var5 != null) {
                     return var5;
                  }

                  q(var0, "Media Instructions");
                  return null;
               }
            case 4:
            case 5:
               return xK(var0);
            case 6:
            case 7:
               int var6 = var0[0] & 3;
               if (var6 == 3) {
                  if ((var0[0] & 240) == 240) {
                     q(var0, "Supervisor call");
                  }

                  return oY.gP;
               }

               return Ei.q(var0, 32);
            default:
               throw new ProcessorException("Unexpected part in the parsing algorithm");
         }
      }
   }

   private static Je xK(byte[] var0) throws ProcessorException {
      int var1 = (var0[0] & 2) >>> 1;
      if (var1 == 0) {
         if ((var0[0] & 240) != 240) {
            return rU.q(var0);
         } else {
            int var2 = HS.Dw(var0, 0);
            return (var2 != 0 || (var0[1] & 15) != 13 || (var0[2] & 255) != 5 || (var0[3] & 224) != 0)
                  && (var2 == 0 || (var0[2] & 255) != 10 || (var0[3] & 255) != 0)
               ? null
               : oY.q(var0);
         }
      } else {
         return oR.lm[HS.Uv(var0, 1) | var0[0] & 1];
      }
   }

   public static void q(byte[] var0, String var1) throws ProcessorException {
      Qg.q(var0, var1);
   }

   private static Je Dw(byte[] var0) throws ProcessorException {
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
                        return Fh.HF[(var0[1] & 14) >>> 1];
                     }

                     if (var0[1] == 1 && (var0[2] & 1) == 0 && var0[3] == 0) {
                        return Fh.qa;
                     }
                  }
               } else if (var8 == 1 && (var0[3] & 240) == 0) {
                  if ((var0[1] & 15) == 0 && (var0[2] & 253) == 0 && var0[3] == 0) {
                     return Fh.oQ;
                  }
               } else if ((var0[1] & 192) == 0 && (var0[3] & 240) == 112) {
                  q(var0, "UNPREDICTABLE: Unconditional/Miscellaneous");
               }
            }

            q(var0, "Unconditional/Miscellaneous");
            break;
         case 2:
         case 3:
            return Ps.q(var0, 32);
         case 4:
         case 5:
         case 6:
         case 7:
            if (var2 == 0) {
               if (var1 == 4) {
                  return Ma.q(var0, 32);
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
                     return iC.RF(var0, 32);
                  }
               } else if ((var3 & 17) == 0) {
                  if (var7 == 3 && (var0[1] & 15) == 15) {
                     return aD.Uv;
                  }

                  switch (var7) {
                     case 0:
                     default:
                        q(var0, "Memory Hints/Preload Immediate");
                        break;
                     case 1:
                        return aD.gO;
                     case 2:
                        return aD.oW;
                     case 3:
                        return aD.Uv;
                  }
               } else if ((var3 & 17) == 16 && var4 == 0) {
                  switch (var7) {
                     case 0:
                     default:
                        q(var0, "Memory Hints/Preload Register");
                        break;
                     case 1:
                        return aD.zz;
                     case 2:
                        return aD.lm;
                     case 3:
                        return aD.za;
                  }
               }
            }

            q(var0, "Unconditional");
      }

      return null;
   }

   public static Je RF(byte[] var0) throws ProcessorException {
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
               return Qg.q(Lx.q, var10, var7, var0, var1);
            }

            return null;
         case 1:
            if (var5 == 0 && var3 == 5) {
               if ((var0[2] & 15) == 15) {
                  return Lm.JY;
               }
            } else {
               if (var5 == 0 && (var3 & 1) == 0) {
                  int var9 = var0[3] & 64;
                  if (var9 == 0) {
                     return Lx.nf;
                  }

                  return Lx.gP;
               }

               if (var5 == 2 && var3 == 1) {
                  if ((var0[2] & 15) == 15) {
                     return Lx.zz;
                  }
               } else if (var5 == 6 && var3 == 1) {
                  if ((var0[2] & 15) == 15) {
                     return Lx.JY;
                  }
               } else if ((var5 & 3) == 3 && (var3 & 3) == 1) {
                  if ((var0[1] & 15) == 15 && (var0[2] & 15) == 15) {
                     return Lm.q[(var0[1] & 64) >>> 5 | (var0[3] & 128) >>> 7];
                  }
               } else {
                  if ((var5 & 2) == 2 && (var3 & 1) == 0) {
                     int var8 = var5 & 4;
                     if (var8 == 0) {
                        return Lx.HF;
                     }

                     return Lx.LK;
                  }

                  if (var3 == 3) {
                     if ((var0[2] & 3) == 0) {
                        return Qg.q(Lx.Uv, (var0[1] & 48) >>> 4, (var0[1] & 64) >>> 5 | HS.oW(var0, 0), var0, var1);
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
                  return Qg.q(Do.xK, Do.q(var0), var0, "SMLAD/SMLSD");
               case 1:
                  if (var3 == 0 && (var0[2] & 240) == 240) {
                     return Do.oW;
                  }

                  return null;
               case 2:
                  return null;
               case 3:
                  if (var3 == 0 && (var0[2] & 240) == 240) {
                     return Do.gO;
                  }

                  return null;
               case 4:
                  return Qg.q(Do.Dw, Do.q(var0), var0, "SMLALD/SMLSLD");
               case 5:
                  return Do.Uv[Do.q(var0)];
               case 6:
               case 7:
               default:
                  return null;
            }
         case 3:
            if ((var5 == 4 || var5 == 5) && (var3 == 0 || var3 == 4)) {
               if ((var0[3] & 15) == 15) {
                  return Lm.oW;
               }

               return Lm.Uv;
            }

            if (var5 == 7 && var3 == 7) {
               if ((var0[0] & 240) == 224) {
                  return oY.zz;
               }
            } else {
               if ((var5 & 2) == 2 && (var3 & 3) == 2) {
                  if ((var0[1] & 64) == 0) {
                     return Lm.gO;
                  }

                  return Lm.nf;
               }

               if (var5 == 0 && var3 == 0) {
                  if ((var0[2] & 240) == 240) {
                     return Lm.gP;
                  }

                  return Lm.za;
               }
            }
      }

      return null;
   }
}
