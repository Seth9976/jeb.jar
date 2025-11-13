package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.units.code.asm.processor.ProcessorException;
import com.pnfsoftware.jeb.core.units.code.asm.processor.memory.DirectEncodedMemoryArea;
import com.pnfsoftware.jeb.core.units.code.asm.processor.memory.EncodedMemoryAreaList;

public class YE {
   public Je q(byte[] var1) throws ProcessorException {
      int var2 = (var1[0] & 30) >>> 1;
      int var3 = (var1[0] & 1) << 4 | (var1[1] & 240) >>> 4;
      switch (var2) {
         case 0:
         case 1:
         case 2:
         case 3:
            return Qg.q(var1, "Uncoherent State: Thumb-1 instruction parsed in Thumb-2");
         case 4:
            if ((var3 & 4) == 0) {
               return rU.RF(var1);
            }

            int var4 = (var1[0] & 1) << 3 | (var1[1] & 224) >>> 5;
            switch (var4) {
               case 2:
                  int var12 = HS.Dw(var1, 0);
                  if (var12 != 0 && (var1[2] & 15) != 15) {
                     return null;
                  }

                  return aD.IN[var12];
               case 3:
               case 7:
               case 10:
               case 11:
               case 14:
               case 15:
                  if ((var1[1] & 15) == 15) {
                     return aD.mI;
                  }

                  return aD.Dz[HS.Dw(var1, 0)];
               case 4:
               case 5:
               case 8:
               case 9:
               case 12:
               case 13:
               default:
                  return null;
               case 6:
                  int var11 = HS.Dw(var1, 0);
                  int var15 = (var1[3] & 224) >>> 5;
                  if (var15 == 0) {
                     if ((var1[1] & 16) != 0 && (var1[2] & 255) == 240) {
                        return oR.KT[(var1[3] & 16) >>> 4];
                     }

                     return Qg.q(var1, "Thumb-2/Load/Store Dual");
                  }

                  if (var15 < 4) {
                     int var17 = HS.Dw(var1, 2) | (var1[3] & 48) >>> 4;
                     if (var11 == 0 || (var1[3] & 15) == 15 && (var17 == 7 || (var1[2] & 15) == 15)) {
                        if (var11 == 0 && var17 != 3 && (var1[2] & 15) != 15) {
                           return null;
                        }

                        return aD.rL[var17];
                     }

                     return null;
                  }

                  int var16 = (var1[3] & 64) >>> 3 | HS.Dw(var1, 2) | (var1[3] & 48) >>> 4;
                  if (var11 == 0 || (var1[3] & 15) == 15 && (var16 == 15 || (var1[2] & 15) == 15)) {
                     if (var11 != 0 || var16 == 11 || (var1[2] & 15) == 15 && (var16 >= 4 || (var1[3] & 15) == 15)) {
                        return aD.Bu[var16];
                     }

                     return null;
                  }

                  return null;
            }
         case 5:
            int var5 = (var1[0] & 1) << 4 | (var1[1] & 240) >>> 4;
            if ((var1[2] & 128) != 0) {
               return Qg.q(var1, "Thumb-2/Data-Processing (shifted Register)");
            }

            if (var5 == 12) {
               int var14 = (var1[3] & 48) >>> 4;
               if (var14 == 0) {
                  return Lx.za;
               }

               if (var14 == 2) {
                  return Lx.lm;
               }

               return Qg.q(var1, "Thumb-2/Data-Processing (shifted Register)");
            }

            return q(var1, var5);
         case 6:
         case 7:
         case 14:
         case 15:
            int var13 = var1[0] & 3;
            if (var13 == 3) {
               return Ps.q(var1, 16);
            }

            return Ei.q(var1, 16);
         case 8:
         case 9:
         case 10:
         case 11:
            if ((var1[2] & 128) != 0) {
               return this.gP(var1);
            }

            if (var2 != 8 && var2 != 10) {
               if ((var1[1] & 16) != 0) {
                  return Qg.q(var1, "Thumb-2/Data-Processing (Plain Binary Immediate)");
               }

               int var6 = var1[0] & 1;
               int var7 = (var1[1] & 96) >>> 5;
               if (var6 == 0) {
                  if (var7 < 2) {
                     return xK(var1);
                  }

                  if (var7 == 2) {
                     return Dw(var1);
                  }

                  return Qg.q(var1, "Thumb-2/Data-Processing (Plain Binary Immediate)");
               }

               if ((var1[0] & 4) == 0 && (var1[3] & 32) == 0) {
                  int var8 = (var1[1] & 224) >>> 5;
                  boolean var9 = (var1[2] & 112) == 0 && (var1[3] & 192) == 0;
                  int var10 = var1[1] & 15;
                  if (var8 == 1 && var9) {
                     return Lx.io;
                  }

                  if (var8 == 5 && var9) {
                     return Lx.qa;
                  }

                  if (var8 == 3 && var10 == 15) {
                     return Lm.lm;
                  }

                  if ((var8 & 2) == 0) {
                     return Lx.Hk[(var8 & 4) >>> 1 | var8 & 1];
                  }

                  return Lm.zz[(var8 & 4) >>> 1 | var8 & 1];
               }

               return Qg.q(var1, "Thumb-2/Data-Processing (Saturate, Bitfield)");
            }

            return RF(var1);
         case 12:
            if ((var3 & 17) == 16) {
               return Ma.q(var1, 16);
            }

            return nf(var1);
         case 13:
            switch (var3 >>> 3) {
               case 0:
               case 1:
                  return Uv(var1);
               case 2:
                  if ((var1[3] & 192) != 0) {
                     return Qg.q(var1, "Thumb-2/Multiply and absolute difference");
                  }

                  return oW(var1);
               case 3:
                  return gO(var1);
            }
      }

      return null;
   }

   public static Je q(byte[] var0, int var1) {
      if ((var1 == 4 || var1 == 5) && (var0[1] & 15) == 15) {
         int var2 = (var0[2] & 112) == 0 && (var0[3] & 192) == 0 ? 0 : 1;
         return Xu.Gf[(var0[3] & 48) >>> 3 | var2];
      } else {
         return q(var0, var1, Xu.xW, Xu.KT);
      }
   }

   public static Je RF(byte[] var0) {
      int var1 = (var0[0] & 1) << 4 | (var0[1] & 240) >>> 4;
      return q(var0, var1, Xu.Ef, Xu.cC);
   }

   private static Je q(byte[] var0, int var1, Je[] var2, Je[] var3) {
      int var4 = var1 >> 1;
      int var5 = var0[2] & 15;
      int var6 = var0[1] & 15;
      if (var5 == 15) {
         switch (var1) {
            case 1:
            case 9:
            case 17:
            case 27:
               return var3[var4];
         }
      }

      if (var6 == 15) {
         switch (var1) {
            case 4:
            case 5:
            case 6:
            case 7:
               return var3[var4];
         }
      }

      return var2[var4];
   }

   public static Je xK(byte[] var0) throws ProcessorException {
      int var1 = (var0[1] & 128) >>> 6 | (var0[1] & 32) >>> 5;
      int var2 = var0[1] & 15;
      int var3 = k.RF(var0, lO.Dw);
      boolean var4 = var3 <= 255 || q(var3);
      switch (var1) {
         case 0:
            if (var2 == 15) {
               return Xu.TX;
            } else {
               if (var4) {
                  return Xu.ui;
               }

               return Xu.jq;
            }
         case 1:
         case 2:
         default:
            return Qg.q(var0, "Thumb2/Data-Processing Simple Immediate");
         case 3:
            if (var2 != 15 || (var0[0] & 4) == 0 && (var0[2] & 112) == 0 && var0[3] == 0) {
               return var4 ? Xu.EB : Xu.Rr;
            } else {
               return Xu.Xo;
            }
      }
   }

   private static boolean q(int var0) {
      if (var0 >>> 8 == 0) {
         return true;
      } else {
         int var1 = var0 & 0xFF;
         if (var0 == (var1 | var1 << 16)) {
            return true;
         } else if (var0 == (var1 | var1 << 8 | var1 << 16 | var1 << 24)) {
            return true;
         } else {
            var1 = var0 >>> 8 & 0xFF;
            if (var0 == (var1 << 8 | var1 << 24)) {
               return true;
            } else {
               int var2 = 0;

               while ((var0 & 1 << var2) == 0) {
                  var2++;
               }

               int var3 = Integer.rotateRight(var0, var2);
               if (var3 <= 127) {
                  return true;
               } else if (var2 >= 6) {
                  return false;
               } else {
                  var3 = Integer.rotateLeft(var0, 6);
                  var2 = 0;

                  while ((var3 & 1 << var2) == 0) {
                     var2++;
                  }

                  var3 = Integer.rotateRight(var3, var2);
                  return var3 <= 127;
               }
            }
         }
      }
   }

   public static Je Dw(byte[] var0) {
      int var1 = (var0[1] & 128) >>> 7;
      int var2 = k.RF(var0, new EncodedMemoryAreaList(DirectEncodedMemoryArea.get(16, 4), lO.Dw));
      if (var1 == 0) {
         return q(var2) ? Xu.eJ : Xu.rL;
      } else {
         return Xu.YN;
      }
   }

   public static Je Uv(byte[] var0) throws ProcessorException {
      String var1 = "Thumb2/Data-Processing Register";
      if ((var0[2] & 240) != 240) {
         return Qg.q(var0, var1);
      } else {
         int var2 = (var0[3] & 240) >>> 4;
         int var3 = (var0[1] & 112) >>> 4;
         if ((var0[1] & 128) == 0) {
            if (var2 == 0) {
               int var6 = DirectEncodedMemoryArea.getThumb2(5, 1, 2).decodeInt(var0);
               return Xu.oQ[var6];
            } else {
               return var2 >= 8 && (var0[3] & 64) == 0 ? Qg.q(Lx.gO, var3 << 1 | ((var0[1] & 15) == 15 ? 1 : 0), var0, var1) : Qg.q(var0, var1);
            }
         } else if (var2 < 8) {
            int var5 = (var0[3] & 112) >>> 4;
            return Qg.q(Lx.RF, var3, var5, var0, var1);
         } else if (var2 < 12) {
            int var4 = (var0[3] & 48) >>> 4;
            return Qg.q(Lm.xK, var3, var4, var0, var1);
         } else {
            return Qg.q(var0, var1);
         }
      }
   }

   private Je gP(byte[] var1) throws ProcessorException {
      int var2 = (var1[0] & 4) >> 2;
      int var3 = (var1[0] & 3) << 2 | (var1[1] & 192) >>> 6;
      int var4 = (var1[1] & 48) >>> 4;
      int var5 = (var1[2] & 112) >> 4 & 5;
      int var6 = var1[2] & 7;
      switch (var5) {
         case 0:
            if (var2 == 0) {
               if (var3 == 14) {
                  if (var4 < 2) {
                     if ((var1[2] & 32) == 0) {
                        if ((var1[3] & 255) == 0) {
                           return Fh.zz;
                        }

                        if ((var1[3] & 239) == 32) {
                           return Fh.JY;
                        }
                     }
                  } else {
                     if (var4 == 2) {
                        if ((var1[1] & 15) == 15 && (var1[2] & 40) == 0) {
                           if (var6 == 0) {
                              return iC.q(var1, 16);
                           }

                           int var7 = (var1[2] & 6) >>> 1;
                           if (var7 == 0 && (var1[2] & 1) != 0) {
                              return Fh.io[var1[2] & 7];
                           }

                           if (var7 == 2) {
                              return Fh.io[var1[2] & 7];
                           }

                           if (var7 == 3) {
                              return Fh.io[var1[2] & 7];
                           }
                        }

                        return Qg.q(var1, "Thumb-2/Hints-CPS");
                     }

                     if ((var1[1] & 15) == 15 && (var1[2] & 47) == 15) {
                        return iC.RF(var1, 16);
                     }
                  }

                  return null;
               }

               if (var3 == 15) {
                  if (var4 == 0) {
                     if ((var1[2] & 255) == 143 && var1[3] == 0) {
                        return oR.xW;
                     }
                  } else {
                     if (var4 == 1) {
                        if ((var1[1] & 15) == 14 && (var1[2] & 47) == 15) {
                           if ((var1[3] & 255) != 0) {
                              return Xu.Bu;
                           }

                           return oY.Hk;
                        }

                        return Qg.q(var1, "Thumb-2/Exception Return");
                     }

                     if ((var1[2] & 32) == 0) {
                        if ((var1[3] & 255) == 0 && (var1[1] & 15) == 15) {
                           return Fh.za;
                        }

                        if ((var1[3] & 239) == 32) {
                           return Fh.lm;
                        }
                     }
                  }

                  return null;
               }
            } else if (var2 == 1) {
               if (var3 == 14) {
                  if (var4 == 0 && var5 == 0) {
                     int var9 = var1[3] & 3;
                     if ((var1[1] & 15) == 15 && (var1[2] & 15) == 0 && (var1[3] & 252) == 0 && var9 != 0) {
                        return oY.qa[var9];
                     }

                     return Qg.q(var1, "Thumb-2/DCPS");
                  }

                  return Qg.q(var1, "Thumb-2/Branches and misc");
               }

               if (var3 == 15) {
                  if (var4 <= 2) {
                     return Qg.q(var1, "Thumb-2/Branches and misc");
                  }

                  int var8 = (var1[1] & 16) >>> 3 | (var1[2] & 32) >>> 5;
                  if (var8 != 2 || (var1[2] & 15) == 0 && (var1[3] & 255) == 0) {
                     return oY.io[var8];
                  }

                  return null;
               }
            }

            return oR.Hk;
         case 1:
            return oR.Me;
         case 2:
         case 3:
         default:
            return null;
         case 4:
            if ((var1[3] & 1) == 1) {
               return Qg.q(var1, "BLX T2 with H='1'");
            }

            return oR.PV;
         case 5:
            return oR.oQ;
      }
   }

   public static Je oW(byte[] var0) {
      return Do.nf[(var0[1] & 112) >>> 1 | (var0[3] & 48) >>> 3 | ((var0[2] & 240) == 240 ? 1 : 0)];
   }

   public static Je gO(byte[] var0) throws ProcessorException {
      int var1 = (var0[1] & 112) >>> 4;
      int var2 = (var0[3] & 240) >>> 4;
      switch (var1) {
         case 0:
            if (var2 == 0) {
               return Do.gP;
            }
            break;
         case 1:
            if (var2 == 15 && (var0[2] & 240) == 240) {
               return Do.qa;
            }
            break;
         case 2:
            if (var2 == 0) {
               return Do.za;
            }
            break;
         case 3:
            if (var2 == 15 && (var0[2] & 240) == 240) {
               return Do.Hk;
            }
            break;
         case 4:
            if (var2 == 0) {
               return Do.lm;
            }

            if (var2 > 7) {
               return Do.zz[var2 - 8];
            }
            break;
         case 5:
            if (var2 == 12) {
               return Do.JY;
            }

            if (var2 == 13) {
               return Do.HF;
            }
            break;
         case 6:
            if (var2 == 0) {
               return Do.LK;
            }

            if (var2 == 6) {
               return Do.io;
            }
      }

      return Qg.q(var0, "Thumb-2/Long Multiply and divide");
   }

   public static Je nf(byte[] var0) throws ProcessorException {
      String var1 = "Thumb2/Load/Store Single";
      int var2 = var0[1] & 15;
      int var3 = (var0[2] & 15) << 2 | (var0[3] & 192) >>> 6;
      int var4 = var0[0] & 1;
      int var5 = (var0[1] & 112) >>> 4;
      int var6 = (var0[2] & 240) >>> 4;
      if (var2 == 15) {
         if (var5 % 2 == 0) {
            return Qg.q(var0, var1);
         } else {
            int var9 = (var4 << 3 | var5) >>> 1;
            if (var6 == 15) {
               if (var9 == 0 || var9 == 1) {
                  return aD.CE;
               }

               if (var9 == 4) {
                  return aD.If;
               }

               if (var9 == 5) {
                  return iC.nf;
               }
            }

            return aD.sH[var9];
         }
      } else {
         int var7 = var4 << 3 | var5;
         boolean var8 = (var0[1] & 128) != 0;
         if (!var8) {
            if (var3 == 0) {
               if (var6 == 15) {
                  if (var7 == 1) {
                     return aD.Hk;
                  }

                  if (var7 == 3) {
                     return aD.Me;
                  }

                  if (var7 == 9) {
                     return aD.PV;
                  }

                  if (var7 == 11) {
                     return iC.nf;
                  }
               }

               return Qg.q(aD.qa, var7, var0, var1);
            } else if ((var3 & 52) == 32) {
               return Qg.q(var0, var1);
            } else if ((var3 & 36) == 36) {
               return aD.oQ[var7];
            } else if ((var3 & 60) == 48) {
               if (var6 == 15) {
                  if (var7 == 1) {
                     return aD.KT;
                  }

                  if (var7 == 3) {
                     return aD.Gf;
                  }

                  if (var7 == 9) {
                     return aD.Ef;
                  }

                  if (var7 == 11) {
                     return iC.nf;
                  }
               }

               return Qg.q(aD.oQ, var7, var0, var1);
            } else {
               return (var3 & 60) == 56 ? Qg.q(aD.xW, var7, var0, var1) : Qg.q(var0, var1);
            }
         } else {
            if (var6 == 15) {
               if (var7 == 1) {
                  return aD.CE;
               }

               if (var7 == 3) {
                  return aD.wF;
               }

               if (var7 == 9) {
                  return aD.If;
               }

               if (var7 == 11) {
                  return iC.nf;
               }
            }

            return Qg.q(aD.cC, var7, var0, var1);
         }
      }
   }
}
