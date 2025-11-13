package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.units.code.asm.processor.ProcessorException;
import com.pnfsoftware.jeb.core.units.code.asm.processor.memory.DirectEncodedMemoryArea;
import com.pnfsoftware.jeb.core.units.code.asm.processor.memory.EncodedMemoryAreaList;

public class Du {
   public tz pC(byte[] var1) throws ProcessorException {
      int var2 = (var1[0] & 30) >>> 1;
      int var3 = (var1[0] & 1) << 4 | (var1[1] & 240) >>> 4;
      switch (var2) {
         case 0:
         case 1:
         case 2:
         case 3:
            return UC.pC(var1, "Uncoherent State: Thumb-1 instruction parsed in Thumb-2");
         case 4:
            if ((var3 & 4) == 0) {
               return gI.A(var1);
            }

            int var4 = (var1[0] & 1) << 3 | (var1[1] & 224) >>> 5;
            switch (var4) {
               case 2:
                  int var12 = ZC.wS(var1, 0);
                  if (var12 != 0 && (var1[2] & 15) != 15) {
                     return null;
                  }

                  return gx.DQ[var12];
               case 3:
               case 7:
               case 10:
               case 11:
               case 14:
               case 15:
                  if ((var1[1] & 15) == 15) {
                     return gx.mv;
                  }

                  return gx.LM[ZC.wS(var1, 0)];
               case 4:
               case 5:
               case 8:
               case 9:
               case 12:
               case 13:
               default:
                  return null;
               case 6:
                  int var11 = ZC.wS(var1, 0);
                  int var15 = (var1[3] & 224) >>> 5;
                  if (var15 == 0) {
                     if ((var1[1] & 16) != 0 && (var1[2] & 255) == 240) {
                        return wJ.rl[(var1[3] & 16) >>> 4];
                     }

                     return UC.pC(var1, "Thumb-2/Load/Store Dual");
                  }

                  if (var15 < 4) {
                     int var17 = ZC.wS(var1, 2) | (var1[3] & 48) >>> 4;
                     if (var11 == 0 || (var1[3] & 15) == 15 && (var17 == 7 || (var1[2] & 15) == 15)) {
                        if (var11 == 0 && var17 != 3 && (var1[2] & 15) != 15) {
                           return null;
                        }

                        return gx.ZN[var17];
                     }

                     return null;
                  }

                  int var16 = (var1[3] & 64) >>> 3 | ZC.wS(var1, 2) | (var1[3] & 48) >>> 4;
                  if (var11 == 0 || (var1[3] & 15) == 15 && (var16 == 15 || (var1[2] & 15) == 15)) {
                     if (var11 != 0 || var16 == 11 || (var1[2] & 15) == 15 && (var16 >= 4 || (var1[3] & 15) == 15)) {
                        return gx.cX[var16];
                     }

                     return null;
                  }

                  return null;
            }
         case 5:
            int var5 = (var1[0] & 1) << 4 | (var1[1] & 240) >>> 4;
            if ((var1[2] & 128) != 0) {
               return UC.pC(var1, "Thumb-2/Data-Processing (shifted Register)");
            }

            if (var5 == 12) {
               int var14 = (var1[3] & 48) >>> 4;
               if (var14 == 0) {
                  return qf.gp;
               }

               if (var14 == 2) {
                  return qf.oT;
               }

               return UC.pC(var1, "Thumb-2/Data-Processing (shifted Register)");
            }

            return pC(var1, var5);
         case 6:
         case 7:
         case 14:
         case 15:
            int var13 = var1[0] & 3;
            if (var13 == 3) {
               return BN.pC(var1, 16);
            }

            return Ua.pC(var1, 16);
         case 8:
         case 9:
         case 10:
         case 11:
            if ((var1[2] & 128) != 0) {
               return this.ld(var1);
            }

            if (var2 != 8 && var2 != 10) {
               if ((var1[1] & 16) != 0) {
                  return UC.pC(var1, "Thumb-2/Data-Processing (Plain Binary Immediate)");
               }

               int var6 = var1[0] & 1;
               int var7 = (var1[1] & 96) >>> 5;
               if (var6 == 0) {
                  if (var7 < 2) {
                     return kS(var1);
                  }

                  if (var7 == 2) {
                     return wS(var1);
                  }

                  return UC.pC(var1, "Thumb-2/Data-Processing (Plain Binary Immediate)");
               }

               if ((var1[0] & 4) == 0 && (var1[3] & 32) == 0) {
                  int var8 = (var1[1] & 224) >>> 5;
                  boolean var9 = (var1[2] & 112) == 0 && (var1[3] & 192) == 0;
                  int var10 = var1[1] & 15;
                  if (var8 == 1 && var9) {
                     return qf.xC;
                  }

                  if (var8 == 5 && var9) {
                     return qf.ED;
                  }

                  if (var8 == 3 && var10 == 15) {
                     return gU.oT;
                  }

                  if ((var8 & 2) == 0) {
                     return qf.Sc[(var8 & 4) >>> 1 | var8 & 1];
                  }

                  return gU.fI[(var8 & 4) >>> 1 | var8 & 1];
               }

               return UC.pC(var1, "Thumb-2/Data-Processing (Saturate, Bitfield)");
            }

            return A(var1);
         case 12:
            if ((var3 & 17) == 16) {
               return Yw.pC(var1, 16);
            }

            return ys(var1);
         case 13:
            switch (var3 >>> 3) {
               case 0:
               case 1:
                  return UT(var1);
               case 2:
                  if ((var1[3] & 192) != 0) {
                     return UC.pC(var1, "Thumb-2/Multiply and absolute difference");
                  }

                  return E(var1);
               case 3:
                  return sY(var1);
            }
      }

      return null;
   }

   public static tz pC(byte[] var0, int var1) {
      if ((var1 == 4 || var1 == 5) && (var0[1] & 15) == 15) {
         int var2 = (var0[2] & 112) == 0 && (var0[3] & 192) == 0 ? 0 : 1;
         return bQ.z[(var0[3] & 48) >>> 3 | var2];
      } else {
         return pC(var0, var1, bQ.Ab, bQ.rl);
      }
   }

   public static tz A(byte[] var0) {
      int var1 = (var0[0] & 1) << 4 | (var0[1] & 240) >>> 4;
      return pC(var0, var1, bQ.Ek, bQ.hK);
   }

   private static tz pC(byte[] var0, int var1, tz[] var2, tz[] var3) {
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

   public static tz kS(byte[] var0) throws ProcessorException {
      int var1 = (var0[1] & 128) >>> 6 | (var0[1] & 32) >>> 5;
      int var2 = var0[1] & 15;
      int var3 = Gq.A(var0, NS.A);
      boolean var4 = var3 <= 255 || pC(var3);
      switch (var1) {
         case 0:
            if (var2 == 15) {
               return bQ.Cu;
            } else {
               if (var4) {
                  return bQ.os;
               }

               return bQ.sO;
            }
         case 1:
         case 2:
         default:
            return UC.pC(var0, "Thumb2/Data-Processing Simple Immediate");
         case 3:
            if (var2 != 15 || (var0[0] & 4) == 0 && (var0[2] & 112) == 0 && var0[3] == 0) {
               return var4 ? bQ.UW : bQ.hZ;
            } else {
               return bQ.PR;
            }
      }
   }

   private static boolean pC(int var0) {
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

   public static tz wS(byte[] var0) {
      int var1 = (var0[1] & 128) >>> 7;
      int var2 = Gq.A(var0, new EncodedMemoryAreaList(DirectEncodedMemoryArea.get(16, 4), NS.A));
      if (var1 == 0) {
         return pC(var2) ? bQ.OB : bQ.ZN;
      } else {
         return bQ.pF;
      }
   }

   public static tz UT(byte[] var0) throws ProcessorException {
      String var1 = "Thumb2/Data-Processing Register";
      if ((var0[2] & 240) != 240) {
         return UC.pC(var0, var1);
      } else {
         int var2 = (var0[3] & 240) >>> 4;
         int var3 = (var0[1] & 112) >>> 4;
         if ((var0[1] & 128) == 0) {
            if (var2 == 0) {
               int var6 = DirectEncodedMemoryArea.getThumb2(5, 1, 2).decodeInt(var0);
               return bQ.UO[var6];
            } else {
               return var2 >= 8 && (var0[3] & 64) == 0 ? UC.pC(qf.sY, var3 << 1 | ((var0[1] & 15) == 15 ? 1 : 0), var0, var1) : UC.pC(var0, var1);
            }
         } else if (var2 < 8) {
            int var5 = (var0[3] & 112) >>> 4;
            return UC.pC(qf.A, var3, var5, var0, var1);
         } else if (var2 < 12) {
            int var4 = (var0[3] & 48) >>> 4;
            return UC.pC(gU.kS, var3, var4, var0, var1);
         } else {
            return UC.pC(var0, var1);
         }
      }
   }

   private tz ld(byte[] var1) throws ProcessorException {
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
                           return wZ.fI;
                        }

                        if ((var1[3] & 239) == 32) {
                           return wZ.WR;
                        }
                     }
                  } else {
                     if (var4 == 2) {
                        if ((var1[1] & 15) == 15 && (var1[2] & 40) == 0) {
                           if (var6 == 0) {
                              return Eb.pC(var1, 16);
                           }

                           int var7 = (var1[2] & 6) >>> 1;
                           if (var7 == 0 && (var1[2] & 1) != 0) {
                              return wZ.xC[var1[2] & 7];
                           }

                           if (var7 == 2) {
                              return wZ.xC[var1[2] & 7];
                           }

                           if (var7 == 3) {
                              return wZ.xC[var1[2] & 7];
                           }
                        }

                        return UC.pC(var1, "Thumb-2/Hints-CPS");
                     }

                     if ((var1[1] & 15) == 15 && (var1[2] & 47) == 15) {
                        return Eb.A(var1, 16);
                     }
                  }

                  return null;
               }

               if (var3 == 15) {
                  if (var4 == 0) {
                     if ((var1[2] & 255) == 143 && var1[3] == 0) {
                        return wJ.Ab;
                     }
                  } else {
                     if (var4 == 1) {
                        if ((var1[1] & 15) == 14 && (var1[2] & 47) == 15) {
                           if ((var1[3] & 255) != 0) {
                              return bQ.cX;
                           }

                           return bb.Sc;
                        }

                        return UC.pC(var1, "Thumb-2/Exception Return");
                     }

                     if ((var1[2] & 32) == 0) {
                        if ((var1[3] & 255) == 0 && (var1[1] & 15) == 15) {
                           return wZ.gp;
                        }

                        if ((var1[3] & 239) == 32) {
                           return wZ.oT;
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
                        return bb.ED[var9];
                     }

                     return UC.pC(var1, "Thumb-2/DCPS");
                  }

                  return UC.pC(var1, "Thumb-2/Branches and misc");
               }

               if (var3 == 15) {
                  if (var4 <= 2) {
                     return UC.pC(var1, "Thumb-2/Branches and misc");
                  }

                  int var8 = (var1[1] & 16) >>> 3 | (var1[2] & 32) >>> 5;
                  if (var8 != 2 || (var1[2] & 15) == 0 && (var1[3] & 255) == 0) {
                     return bb.xC[var8];
                  }

                  return null;
               }
            }

            return wJ.Sc;
         case 1:
            return wJ.ah;
         case 2:
         case 3:
         default:
            return null;
         case 4:
            if ((var1[3] & 1) == 1) {
               return UC.pC(var1, "BLX T2 with H='1'");
            }

            return wJ.eP;
         case 5:
            return wJ.UO;
      }
   }

   public static tz E(byte[] var0) {
      return NN.ys[(var0[1] & 112) >>> 1 | (var0[3] & 48) >>> 3 | ((var0[2] & 240) == 240 ? 1 : 0)];
   }

   public static tz sY(byte[] var0) throws ProcessorException {
      int var1 = (var0[1] & 112) >>> 4;
      int var2 = (var0[3] & 240) >>> 4;
      switch (var1) {
         case 0:
            if (var2 == 0) {
               return NN.ld;
            }
            break;
         case 1:
            if (var2 == 15 && (var0[2] & 240) == 240) {
               return NN.ED;
            }
            break;
         case 2:
            if (var2 == 0) {
               return NN.gp;
            }
            break;
         case 3:
            if (var2 == 15 && (var0[2] & 240) == 240) {
               return NN.Sc;
            }
            break;
         case 4:
            if (var2 == 0) {
               return NN.oT;
            }

            if (var2 > 7) {
               return NN.fI[var2 - 8];
            }
            break;
         case 5:
            if (var2 == 12) {
               return NN.WR;
            }

            if (var2 == 13) {
               return NN.NS;
            }
            break;
         case 6:
            if (var2 == 0) {
               return NN.vP;
            }

            if (var2 == 6) {
               return NN.xC;
            }
      }

      return UC.pC(var0, "Thumb-2/Long Multiply and divide");
   }

   public static tz ys(byte[] var0) throws ProcessorException {
      String var1 = "Thumb2/Load/Store Single";
      int var2 = var0[1] & 15;
      int var3 = (var0[2] & 15) << 2 | (var0[3] & 192) >>> 6;
      int var4 = var0[0] & 1;
      int var5 = (var0[1] & 112) >>> 4;
      int var6 = (var0[2] & 240) >>> 4;
      if (var2 == 15) {
         if (var5 % 2 == 0) {
            return UC.pC(var0, var1);
         } else {
            int var9 = (var4 << 3 | var5) >>> 1;
            if (var6 == 15) {
               if (var9 == 0 || var9 == 1) {
                  return gx.FE;
               }

               if (var9 == 4) {
                  return gx.EX;
               }

               if (var9 == 5) {
                  return Eb.ys;
               }
            }

            return gx.Er[var9];
         }
      } else {
         int var7 = var4 << 3 | var5;
         boolean var8 = (var0[1] & 128) != 0;
         if (!var8) {
            if (var3 == 0) {
               if (var6 == 15) {
                  if (var7 == 1) {
                     return gx.Sc;
                  }

                  if (var7 == 3) {
                     return gx.ah;
                  }

                  if (var7 == 9) {
                     return gx.eP;
                  }

                  if (var7 == 11) {
                     return Eb.ys;
                  }
               }

               return UC.pC(gx.ED, var7, var0, var1);
            } else if ((var3 & 52) == 32) {
               return UC.pC(var0, var1);
            } else if ((var3 & 36) == 36) {
               return gx.UO[var7];
            } else if ((var3 & 60) == 48) {
               if (var6 == 15) {
                  if (var7 == 1) {
                     return gx.rl;
                  }

                  if (var7 == 3) {
                     return gx.z;
                  }

                  if (var7 == 9) {
                     return gx.Ek;
                  }

                  if (var7 == 11) {
                     return Eb.ys;
                  }
               }

               return UC.pC(gx.UO, var7, var0, var1);
            } else {
               return (var3 & 60) == 56 ? UC.pC(gx.Ab, var7, var0, var1) : UC.pC(var0, var1);
            }
         } else {
            if (var6 == 15) {
               if (var7 == 1) {
                  return gx.FE;
               }

               if (var7 == 3) {
                  return gx.Aj;
               }

               if (var7 == 9) {
                  return gx.EX;
               }

               if (var7 == 11) {
                  return Eb.ys;
               }
            }

            return UC.pC(gx.hK, var7, var0, var1);
         }
      }
   }
}
