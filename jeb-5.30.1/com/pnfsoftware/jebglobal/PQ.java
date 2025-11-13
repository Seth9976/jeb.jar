package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.units.code.asm.processor.ProcessorException;
import com.pnfsoftware.jeb.core.units.code.asm.processor.memory.DirectEncodedMemoryArea;
import com.pnfsoftware.jeb.util.collect.ArrayUtil;

public class PQ {
   public static Je q(byte[] var0) throws ProcessorException {
      int var1 = (var0[0] & 30) >>> 1;
      switch (var1) {
         case 0:
            if ((var0[0] & 128) == 0) {
               return RF(var0);
            }

            return R.q(var0);
         case 1:
         case 3:
         default:
            return null;
         case 2:
            return g.q(var0);
         case 4:
         case 6:
         case 12:
         case 14:
            return Dw(var0);
         case 5:
         case 13:
            return Sh.q(var0);
         case 7:
         case 15:
            return kU.q(var0);
         case 8:
         case 9:
            return NQ.q(var0);
         case 10:
         case 11:
            return xK(var0);
      }
   }

   private static Je RF(byte[] var0) {
      return var0[0] == 0 && var0[1] == 0 ? oY.Gf : null;
   }

   private static Je xK(byte[] var0) throws ProcessorException {
      int var1 = (var0[0] & 224) >>> 5;
      int var2 = DirectEncodedMemoryArea.get(22, 4).decodeInt(var0);
      int var3 = var0[3] & 31;
      switch (var1) {
         case 0:
            return dK.Dw;
         case 1:
         case 5:
            if (var2 < 8) {
               if (var2 < 4) {
                  return dK.gP;
               }

               return dK.nf;
            } else {
               if (var2 < 12) {
                  return dK.lm;
               }

               return dK.za;
            }
         case 2:
            if (var2 < 4 && (var0[3] & 16) == 0) {
               return dK.gO;
            }

            return Qg.q(var0, "Branch: conditional immediate");
         case 3:
         case 7:
         default:
            return Qg.q(var0, "Branch");
         case 4:
            return dK.Uv;
         case 6:
            if (var2 >= 8) {
               String var14 = "Unconditional branch (register)";
               int var17 = var0[1] & 31;
               int var19 = (var0[2] & 252) >>> 2;
               if (var17 == 31) {
                  int var21 = DirectEncodedMemoryArea.get(21, 4).decodeInt(var0);
                  return (Je)ArrayUtil.getSafe2(dK.oW, var21, var19, null);
               } else {
                  return Qg.q(var0, var14);
               }
            } else if (var2 < 4) {
               String var13 = "Exception generation";
               int var16 = (var0[1] & 224) >>> 5;
               int var18 = (var0[3] & 28) >>> 2;
               int var20 = var0[3] & 3;
               if (var18 == 0) {
                  if (var16 == 0) {
                     return oY.Me[var20];
                  }

                  if (var16 == 1 && var20 == 0) {
                     return oY.PV;
                  }

                  if (var16 == 2 && var20 == 0) {
                     return oY.oQ;
                  }

                  if (var16 == 3 && var20 == 0) {
                     return oY.xW;
                  }

                  if (var16 == 5) {
                     return oY.KT[var20];
                  }
               }

               return Qg.q(var0, var13);
            } else if (var2 == 4) {
               int var12 = DirectEncodedMemoryArea.decodeInt(12, 10, var0);
               int var15 = var0[1] & 32;
               int var6 = var0[1] & 7;
               int var7 = (var0[2] & 240) >>> 4;
               int var8 = var0[2] & 15;
               int var9 = (var0[3] & 224) >>> 5;
               if ((var12 & 256) == 256) {
                  return var15 == 0 ? Fh.Ri : Fh.ZT;
               } else if ((var12 & 384) == 128) {
                  return var15 == 0 ? Fh.xK(var0) : Fh.Dw(var0);
               } else {
                  if ((var12 & 896) == 512) {
                     String var10 = "system with result";
                     if (var6 == 3 && var7 == 3 && var9 == 3) {
                        return Qg.q(Fh.CY, var8, var0, var10);
                     }
                  } else {
                     if ((var12 & 911) == 4) {
                        String var25 = "PSTATE";
                        if (var3 != 31) {
                           return Qg.q(var0, var25);
                        }

                        if (var6 == 0 && var9 < 3) {
                           return Fh.AB[var9];
                        }

                        int var11 = var6 << 3 | var9;
                        if (ArrayUtil.getSafe(Fh.gO, var11, null) != null) {
                           return Fh.GY;
                        }

                        if (ArrayUtil.getSafe3(Fh.nf, var6, var9, (var8 & 6) >>> 1, null) != null) {
                           return Fh.Wx;
                        }

                        return Qg.q(var0, var25);
                     }

                     if (var12 == 49) {
                        String var24 = "System instructions with register argument";
                        if (var8 != 0) {
                           return Qg.q(var0, var24);
                        }

                        return Qg.q(iC.sH, var9, var0, var24);
                     }

                     if (var12 == 50) {
                        String var23 = "Hints";
                        if (var3 != 31) {
                           return Qg.q(var0, var23);
                        }

                        if (var8 == 0) {
                           if (var9 == 6) {
                              return iC.PV;
                           }

                           if (var9 == 7) {
                              return iC.oQ;
                           }
                        }

                        return (Je)ArrayUtil.getSafe2(iC.Me, var8, var9, iC.LK);
                     }

                     if (var12 == 51) {
                        String var22 = "Barriers";
                        if (var3 != 31) {
                           return Qg.q(var0, var22);
                        }

                        if (var9 == 4) {
                           if (var8 == 0) {
                              return iC.Gf;
                           }

                           if (var8 == 4) {
                              return iC.Ef;
                           }
                        }

                        if (var9 == 3 && var8 != 0) {
                           return Qg.q(var0, var22);
                        }

                        return Qg.q(iC.cC, var9, var0, var22);
                     }
                  }

                  return Qg.q(var0, "Branch: System");
               }
            } else {
               if (var2 == 5) {
                  int var4 = DirectEncodedMemoryArea.decodeInt(12, 10, var0);
                  if ((var4 & 256) == 256) {
                     return (var0[1] & 32) == 0 ? Fh.Tq : Fh.WI;
                  }

                  if ((var4 & 384) == 128) {
                     String var5 = "System pair instructions";
                     return Fh.q(var0, var3, var5);
                  }
               }

               return Qg.q(var0, "Branch: Unconditional (register)");
            }
      }
   }

   private static Je Dw(byte[] var0) throws ProcessorException {
      String var1 = "LoadStore";
      int var2 = (var0[0] & 192) >>> 6;
      int var3 = (var0[0] & 240) >>> 4;
      int var4 = (var0[0] & 4) >>> 2;
      int var5 = DirectEncodedMemoryArea.get(10, 15).decodeInt(var0);
      int var6 = DirectEncodedMemoryArea.get(23, 2).decodeInt(var0);
      int var7 = var0[1] & 63;
      int var8 = (var0[2] & 12) >>> 2;
      int var11 = var2 & 1;
      int var12 = (var0[1] & 192) >>> 6;
      switch (var3 & 3) {
         case 0:
            if (var4 != 0) {
               return zF.q(var0);
            } else if (var6 >= 2) {
               return Qg.q(var0, var1);
            } else {
               if ((var7 & 32) != 0 && (var6 != 0 || var2 < 2 && var6 == 0)) {
                  int var27 = DirectEncodedMemoryArea.get(10, 5).decodeInt(var0);
                  if (var27 != 31) {
                     return Qg.q(var0, var1);
                  }
               }

               int var28 = (var0[2] & 128) >>> 7;
               int var35 = var2 << 4 | (var0[1] & 224) >>> 4 | var28;
               return D.gO[var35];
            }
         case 1:
            if (var6 < 2) {
               return D.HF[var2 << 1 | var4];
            } else if ((var7 & 32) == 0) {
               if (var8 == 0 && var4 == 0) {
                  String var26 = "LDAPR/STLR (unscaled immediate)";
                  return Qg.q(D.gP, var2 << 2 | var12, var0, var26);
               } else if (var8 == 1) {
                  String var25 = "Memory Copy and Memory Set";
                  if (var2 != 0) {
                     return Qg.q(var0, var25);
                  } else {
                     int var34 = DirectEncodedMemoryArea.get(0, 5).decodeInt(var0);
                     int var39 = DirectEncodedMemoryArea.get(5, 5).decodeInt(var0);
                     int var41 = DirectEncodedMemoryArea.get(16, 5).decodeInt(var0);
                     int var43 = var4 << 2 | var12;
                     if (var34 != var41 && var34 != var39 && var39 != var41 && var34 != 31 && var39 != 21 && (var41 != 31 || var12 == 3)) {
                        return Qg.q(D.sH, var43, (var0[2] & 240) >>> 4, var0, var25);
                     }

                     return null;
                  }
               } else if (var8 == 2 && var4 != 0) {
                  String var24 = "LDAPR/STLR (SIMD&FP)";
                  return Qg.q(D.EB, var2, var12, var0, var24);
               } else if (var8 == 2 && var4 == 0) {
                  if (var6 == 2) {
                     String var23 = "LDIAPP/STILP";
                     return Qg.q(var12 == 0 ? D.TX : D.ui, var2, (var0[2] & 240) >>> 4, var0, var23);
                  } else {
                     if (var6 == 3 && (var5 & 2044) == 0) {
                        String var22 = "LDAPR/STLR (writeback)";
                        return Qg.q(D.Rr, var2, var12 & 1, var0, var22);
                     }

                     return null;
                  }
               } else {
                  if (var3 == 13 && var4 == 0 && var6 == 2 && (var5 & 8163) == 1987) {
                     String var21 = "GCS load/store";
                     return Qg.q(D.Xo, (var0[2] & 240) >>> 4, var0, var21);
                  }

                  return Qg.q(var0, var1);
               }
            } else if (var3 == 13 && var4 == 0) {
               if (var8 == 0) {
                  if (var12 != 1 && DirectEncodedMemoryArea.decodeInt(12, 9, var0) != 0) {
                     return Qg.q(var0, "Load/store memory tags");
                  }

                  return Qg.q(D.PV, var12, var0, "Load/store memory tags");
               }

               return Qg.q(D.Me, var12, var0, "Load/store memory tags");
            } else {
               if ((var3 & 8) == 0 && var4 == 0) {
                  if (var8 == 0) {
                     String var20 = "128-bit atomic memory";
                     if ((var0[1] & 31) != 31 && (var0[3] & 31) != 31) {
                        int var33 = (var0[2] & 128) >>> 7;
                        int var38 = (var0[2] & 112) >>> 4;
                        int var40 = var11 << 2 | var12;
                        return Qg.q(var33 == 0 ? D.KT : D.Gf, var38, var40, var0, var20);
                     }

                     return Qg.q(var0, var20);
                  }

                  if (var8 == 2 && (var0[2] & 240) == 0) {
                     return D.oQ[var11 << 2 | var12];
                  }

                  if (var8 == 3 && (var0[2] & 240) == 0) {
                     return D.xW[var11 << 2 | var12];
                  }
               }

               return Qg.q(var0, "LoadStore");
            }
         case 2:
            String var19 = "Load/Store Pairs";
            int var32 = (var0[1] & 64) >>> 6;
            int var37 = var2 << 2 | var4 << 1 | var32;
            return Qg.q(D.oW, var6, var37, var0, var19);
         default:
            if (var6 < 2) {
               int var18 = var2 << 3 | var4 << 2 | var12;
               if (var7 < 32) {
                  if (var8 == 0) {
                     return D.nf[var18];
                  } else if (var8 == 1) {
                     return D.lm[var18];
                  } else if (var8 != 2) {
                     return D.lm[var18];
                  } else if (var4 == 0) {
                     int var31 = var2 << 2 | var12;
                     return D.za[var31];
                  } else {
                     return Qg.q(var0, "LoadStore: Register Unprivileged");
                  }
               } else if (var8 == 0) {
                  String var30 = "Atomic memory operations";
                  if (var4 != 0) {
                     return Qg.q(var0, var30);
                  } else {
                     int var36 = (var0[2] & 128) >>> 7;
                     int var16 = (var0[2] & 112) >>> 4;
                     if (var36 == 0) {
                        int var42 = var12 << 3 | var16;
                        return var12 < 2 && (var0[3] & 31) == 31 ? D.qa[var2][var42] : D.io[var2][var42];
                     } else if (var2 == 3 && var12 == 0 && (var16 & 3) == 1 && (var0[1] & 31) != 31) {
                        return Qg.q(var0, var30);
                     } else {
                        int var17 = var2 << 2 | var12;
                        return Qg.q(D.Hk, var16, var17, var0, var30);
                     }
                  }
               } else {
                  if (var8 == 2) {
                     String var14 = "Load/store register (register offset)";
                     if ((var0[2] & 64) != 0) {
                        if (var18 == 26) {
                           int var15 = var0[3] & 31;
                           if ((var0[2] & 64) == 0) {
                              Qg.q(var0, var14);
                           } else if ((var15 & 24) == 24 && QJ.nY.q(var0) != 1L) {
                              return D.JY;
                           }
                        }

                        return D.zz[var18];
                     }
                  } else if (var2 == 3) {
                     int var29 = (var0[1] & 128) >>> 7;
                     return (Je)ArrayUtil.getSafe(D.LK, var4 << 1 | var29, null);
                  }

                  return Qg.q(var0, "LoadStore: register offset");
               }
            } else {
               String var13 = "Load/store register (unsigned immediate)";
               return (var2 & 1) != 0 && var4 != 0 && var12 >= 2 ? Qg.q(var0, var13) : Qg.q(D.xK, var2, var4 << 2 | var12, var0, var13);
            }
      }
   }
}
