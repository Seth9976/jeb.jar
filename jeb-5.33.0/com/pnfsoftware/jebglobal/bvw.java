package com.pnfsoftware.jebglobal;

import java.io.UnsupportedEncodingException;

public class bvw {
   public static final int pC = 0;
   public static final int A = 1;
   public static final int kS = 2;
   public static final int wS = 4;
   public static final int UT = 8;
   public static final int E = 16;

   public static byte[] pC(String var0, int var1) {
      return pC(var0.getBytes(), var1);
   }

   public static byte[] pC(byte[] var0, int var1) {
      return pC(var0, 0, var0.length, var1);
   }

   public static byte[] pC(byte[] var0, int var1, int var2, int var3) {
      bvw.Sv var4 = new bvw.Sv(var3, new byte[var2 * 3 / 4]);
      if (!var4.pC(var0, var1, var2, true)) {
         throw new IllegalArgumentException("bad base-64");
      } else if (var4.A == var4.pC.length) {
         return var4.pC;
      } else {
         byte[] var5 = new byte[var4.A];
         System.arraycopy(var4.pC, 0, var5, 0, var4.A);
         return var5;
      }
   }

   public static String A(byte[] var0, int var1) {
      try {
         return new String(kS(var0, var1), "US-ASCII");
      } catch (UnsupportedEncodingException var3) {
         throw new AssertionError(var3);
      }
   }

   public static String A(byte[] var0, int var1, int var2, int var3) {
      try {
         return new String(kS(var0, var1, var2, var3), "US-ASCII");
      } catch (UnsupportedEncodingException var5) {
         throw new AssertionError(var5);
      }
   }

   public static byte[] kS(byte[] var0, int var1) {
      return kS(var0, 0, var0.length, var1);
   }

   public static byte[] kS(byte[] var0, int var1, int var2, int var3) {
      bvw.K var4 = new bvw.K(var3, null);
      int var5 = var2 / 3 * 4;
      if (var4.UT) {
         if (var2 % 3 > 0) {
            var5 += 4;
         }
      } else {
         switch (var2 % 3) {
            case 0:
            default:
               break;
            case 1:
               var5 += 2;
               break;
            case 2:
               var5 += 3;
         }
      }

      if (var4.E && var2 > 0) {
         var5 += ((var2 - 1) / 57 + 1) * (var4.sY ? 2 : 1);
      }

      var4.pC = new byte[var5];
      var4.pC(var0, var1, var2, true);

      assert var4.A == var5;

      return var4.pC;
   }

   private bvw() {
   }

   abstract static class Av {
      public byte[] pC;
      public int A;

      public abstract boolean pC(byte[] var1, int var2, int var3, boolean var4);

      public abstract int pC(int var1);
   }

   static class K extends bvw.Av {
      public static final int kS = 19;
      private static final byte[] ld = new byte[]{
         65,
         66,
         67,
         68,
         69,
         70,
         71,
         72,
         73,
         74,
         75,
         76,
         77,
         78,
         79,
         80,
         81,
         82,
         83,
         84,
         85,
         86,
         87,
         88,
         89,
         90,
         97,
         98,
         99,
         100,
         101,
         102,
         103,
         104,
         105,
         106,
         107,
         108,
         109,
         110,
         111,
         112,
         113,
         114,
         115,
         116,
         117,
         118,
         119,
         120,
         121,
         122,
         48,
         49,
         50,
         51,
         52,
         53,
         54,
         55,
         56,
         57,
         43,
         47
      };
      private static final byte[] gp = new byte[]{
         65,
         66,
         67,
         68,
         69,
         70,
         71,
         72,
         73,
         74,
         75,
         76,
         77,
         78,
         79,
         80,
         81,
         82,
         83,
         84,
         85,
         86,
         87,
         88,
         89,
         90,
         97,
         98,
         99,
         100,
         101,
         102,
         103,
         104,
         105,
         106,
         107,
         108,
         109,
         110,
         111,
         112,
         113,
         114,
         115,
         116,
         117,
         118,
         119,
         120,
         121,
         122,
         48,
         49,
         50,
         51,
         52,
         53,
         54,
         55,
         56,
         57,
         45,
         95
      };
      private final byte[] oT;
      int wS;
      private int fI;
      public final boolean UT;
      public final boolean E;
      public final boolean sY;
      private final byte[] WR;

      public K(int var1, byte[] var2) {
         this.pC = var2;
         this.UT = (var1 & 1) == 0;
         this.E = (var1 & 2) == 0;
         this.sY = (var1 & 4) != 0;
         this.WR = (var1 & 8) == 0 ? ld : gp;
         this.oT = new byte[2];
         this.wS = 0;
         this.fI = this.E ? 19 : -1;
      }

      @Override
      public int pC(int var1) {
         return var1 * 8 / 5 + 10;
      }

      @Override
      public boolean pC(byte[] var1, int var2, int var3, boolean var4) {
         byte[] var5 = this.WR;
         byte[] var6 = this.pC;
         int var7 = 0;
         int var8 = this.fI;
         int var20 = var2;
         var3 += var2;
         int var10 = -1;
         switch (this.wS) {
            case 0:
            default:
               break;
            case 1:
               if (var2 + 2 <= var3) {
                  int var25 = (this.oT[0] & 255) << 16;
                  var20 = var2 + 1;
                  var10 = var25 | (var1[var2] & 255) << 8 | var1[var20++] & 255;
                  this.wS = 0;
               }
               break;
            case 2:
               if (var2 + 1 <= var3) {
                  int var10000 = (this.oT[0] & 255) << 16 | (this.oT[1] & 255) << 8;
                  var20 = var2 + 1;
                  var10 = var10000 | var1[var2] & 255;
                  this.wS = 0;
               }
         }

         if (var10 != -1) {
            var6[var7++] = var5[var10 >> 18 & 63];
            var6[var7++] = var5[var10 >> 12 & 63];
            var6[var7++] = var5[var10 >> 6 & 63];
            var6[var7++] = var5[var10 & 63];
            if (--var8 == 0) {
               if (this.sY) {
                  var6[var7++] = 13;
               }

               var6[var7++] = 10;
               var8 = 19;
            }
         }

         while (var20 + 3 <= var3) {
            var10 = (var1[var20] & 255) << 16 | (var1[var20 + 1] & 255) << 8 | var1[var20 + 2] & 255;
            var6[var7] = var5[var10 >> 18 & 63];
            var6[var7 + 1] = var5[var10 >> 12 & 63];
            var6[var7 + 2] = var5[var10 >> 6 & 63];
            var6[var7 + 3] = var5[var10 & 63];
            var20 += 3;
            var7 += 4;
            if (--var8 == 0) {
               if (this.sY) {
                  var6[var7++] = 13;
               }

               var6[var7++] = 10;
               var8 = 19;
            }
         }

         if (var4) {
            if (var20 - this.wS == var3 - 1) {
               int var11 = 0;
               var10 = ((this.wS > 0 ? this.oT[var11++] : var1[var20++]) & 255) << 4;
               this.wS -= var11;
               var6[var7++] = var5[var10 >> 6 & 63];
               var6[var7++] = var5[var10 & 63];
               if (this.UT) {
                  var6[var7++] = 61;
                  var6[var7++] = 61;
               }

               if (this.E) {
                  if (this.sY) {
                     var6[var7++] = 13;
                  }

                  var6[var7++] = 10;
               }
            } else if (var20 - this.wS == var3 - 2) {
               int var24 = 0;
               var10 = ((this.wS > 1 ? this.oT[var24++] : var1[var20++]) & 255) << 10 | ((this.wS > 0 ? this.oT[var24++] : var1[var20++]) & 255) << 2;
               this.wS -= var24;
               var6[var7++] = var5[var10 >> 12 & 63];
               var6[var7++] = var5[var10 >> 6 & 63];
               var6[var7++] = var5[var10 & 63];
               if (this.UT) {
                  var6[var7++] = 61;
               }

               if (this.E) {
                  if (this.sY) {
                     var6[var7++] = 13;
                  }

                  var6[var7++] = 10;
               }
            } else if (this.E && var7 > 0 && var8 != 19) {
               if (this.sY) {
                  var6[var7++] = 13;
               }

               var6[var7++] = 10;
            }

            assert this.wS == 0;

            assert var20 == var3;
         } else if (var20 == var3 - 1) {
            this.oT[this.wS++] = var1[var20];
         } else if (var20 == var3 - 2) {
            this.oT[this.wS++] = var1[var20];
            this.oT[this.wS++] = var1[var20 + 1];
         }

         this.A = var7;
         this.fI = var8;
         return true;
      }
   }

   static class Sv extends bvw.Av {
      private static final int[] kS = new int[]{
         -1,
         -1,
         -1,
         -1,
         -1,
         -1,
         -1,
         -1,
         -1,
         -1,
         -1,
         -1,
         -1,
         -1,
         -1,
         -1,
         -1,
         -1,
         -1,
         -1,
         -1,
         -1,
         -1,
         -1,
         -1,
         -1,
         -1,
         -1,
         -1,
         -1,
         -1,
         -1,
         -1,
         -1,
         -1,
         -1,
         -1,
         -1,
         -1,
         -1,
         -1,
         -1,
         -1,
         62,
         -1,
         -1,
         -1,
         63,
         52,
         53,
         54,
         55,
         56,
         57,
         58,
         59,
         60,
         61,
         -1,
         -1,
         -1,
         -2,
         -1,
         -1,
         -1,
         0,
         1,
         2,
         3,
         4,
         5,
         6,
         7,
         8,
         9,
         10,
         11,
         12,
         13,
         14,
         15,
         16,
         17,
         18,
         19,
         20,
         21,
         22,
         23,
         24,
         25,
         -1,
         -1,
         -1,
         -1,
         -1,
         -1,
         26,
         27,
         28,
         29,
         30,
         31,
         32,
         33,
         34,
         35,
         36,
         37,
         38,
         39,
         40,
         41,
         42,
         43,
         44,
         45,
         46,
         47,
         48,
         49,
         50,
         51,
         -1,
         -1,
         -1,
         -1,
         -1,
         -1,
         -1,
         -1,
         -1,
         -1,
         -1,
         -1,
         -1,
         -1,
         -1,
         -1,
         -1,
         -1,
         -1,
         -1,
         -1,
         -1,
         -1,
         -1,
         -1,
         -1,
         -1,
         -1,
         -1,
         -1,
         -1,
         -1,
         -1,
         -1,
         -1,
         -1,
         -1,
         -1,
         -1,
         -1,
         -1,
         -1,
         -1,
         -1,
         -1,
         -1,
         -1,
         -1,
         -1,
         -1,
         -1,
         -1,
         -1,
         -1,
         -1,
         -1,
         -1,
         -1,
         -1,
         -1,
         -1,
         -1,
         -1,
         -1,
         -1,
         -1,
         -1,
         -1,
         -1,
         -1,
         -1,
         -1,
         -1,
         -1,
         -1,
         -1,
         -1,
         -1,
         -1,
         -1,
         -1,
         -1,
         -1,
         -1,
         -1,
         -1,
         -1,
         -1,
         -1,
         -1,
         -1,
         -1,
         -1,
         -1,
         -1,
         -1,
         -1,
         -1,
         -1,
         -1,
         -1,
         -1,
         -1,
         -1,
         -1,
         -1,
         -1,
         -1,
         -1,
         -1,
         -1,
         -1,
         -1,
         -1,
         -1,
         -1,
         -1,
         -1,
         -1,
         -1,
         -1,
         -1,
         -1,
         -1,
         -1,
         -1,
         -1,
         -1,
         -1,
         -1,
         -1,
         -1,
         -1
      };
      private static final int[] wS = new int[]{
         -1,
         -1,
         -1,
         -1,
         -1,
         -1,
         -1,
         -1,
         -1,
         -1,
         -1,
         -1,
         -1,
         -1,
         -1,
         -1,
         -1,
         -1,
         -1,
         -1,
         -1,
         -1,
         -1,
         -1,
         -1,
         -1,
         -1,
         -1,
         -1,
         -1,
         -1,
         -1,
         -1,
         -1,
         -1,
         -1,
         -1,
         -1,
         -1,
         -1,
         -1,
         -1,
         -1,
         -1,
         -1,
         62,
         -1,
         -1,
         52,
         53,
         54,
         55,
         56,
         57,
         58,
         59,
         60,
         61,
         -1,
         -1,
         -1,
         -2,
         -1,
         -1,
         -1,
         0,
         1,
         2,
         3,
         4,
         5,
         6,
         7,
         8,
         9,
         10,
         11,
         12,
         13,
         14,
         15,
         16,
         17,
         18,
         19,
         20,
         21,
         22,
         23,
         24,
         25,
         -1,
         -1,
         -1,
         -1,
         63,
         -1,
         26,
         27,
         28,
         29,
         30,
         31,
         32,
         33,
         34,
         35,
         36,
         37,
         38,
         39,
         40,
         41,
         42,
         43,
         44,
         45,
         46,
         47,
         48,
         49,
         50,
         51,
         -1,
         -1,
         -1,
         -1,
         -1,
         -1,
         -1,
         -1,
         -1,
         -1,
         -1,
         -1,
         -1,
         -1,
         -1,
         -1,
         -1,
         -1,
         -1,
         -1,
         -1,
         -1,
         -1,
         -1,
         -1,
         -1,
         -1,
         -1,
         -1,
         -1,
         -1,
         -1,
         -1,
         -1,
         -1,
         -1,
         -1,
         -1,
         -1,
         -1,
         -1,
         -1,
         -1,
         -1,
         -1,
         -1,
         -1,
         -1,
         -1,
         -1,
         -1,
         -1,
         -1,
         -1,
         -1,
         -1,
         -1,
         -1,
         -1,
         -1,
         -1,
         -1,
         -1,
         -1,
         -1,
         -1,
         -1,
         -1,
         -1,
         -1,
         -1,
         -1,
         -1,
         -1,
         -1,
         -1,
         -1,
         -1,
         -1,
         -1,
         -1,
         -1,
         -1,
         -1,
         -1,
         -1,
         -1,
         -1,
         -1,
         -1,
         -1,
         -1,
         -1,
         -1,
         -1,
         -1,
         -1,
         -1,
         -1,
         -1,
         -1,
         -1,
         -1,
         -1,
         -1,
         -1,
         -1,
         -1,
         -1,
         -1,
         -1,
         -1,
         -1,
         -1,
         -1,
         -1,
         -1,
         -1,
         -1,
         -1,
         -1,
         -1,
         -1,
         -1,
         -1,
         -1,
         -1,
         -1,
         -1,
         -1,
         -1,
         -1,
         -1
      };
      private static final int UT = -1;
      private static final int E = -2;
      private int sY;
      private int ys;
      private final int[] ld;

      public Sv(int var1, byte[] var2) {
         this.pC = var2;
         this.ld = (var1 & 8) == 0 ? kS : wS;
         this.sY = 0;
         this.ys = 0;
      }

      @Override
      public int pC(int var1) {
         return var1 * 3 / 4 + 10;
      }

      @Override
      public boolean pC(byte[] var1, int var2, int var3, boolean var4) {
         if (this.sY == 6) {
            return false;
         } else {
            int var5 = var2;
            var3 += var2;
            int var6 = this.sY;
            int var7 = this.ys;
            int var8 = 0;
            byte[] var9 = this.pC;
            int[] var10 = this.ld;

            while (var5 < var3) {
               if (var6 == 0) {
                  while (
                     var5 + 4 <= var3
                        && (
                              var7 = var10[var1[var5] & 255] << 18
                                 | var10[var1[var5 + 1] & 255] << 12
                                 | var10[var1[var5 + 2] & 255] << 6
                                 | var10[var1[var5 + 3] & 255]
                           )
                           >= 0
                  ) {
                     var9[var8 + 2] = (byte)var7;
                     var9[var8 + 1] = (byte)(var7 >> 8);
                     var9[var8] = (byte)(var7 >> 16);
                     var8 += 3;
                     var5 += 4;
                  }

                  if (var5 >= var3) {
                     break;
                  }
               }

               int var11 = var10[var1[var5++] & 255];
               switch (var6) {
                  case 0:
                     if (var11 >= 0) {
                        var7 = var11;
                        var6++;
                     } else if (var11 != -1) {
                        this.sY = 6;
                        return false;
                     }
                     break;
                  case 1:
                     if (var11 >= 0) {
                        var7 = var7 << 6 | var11;
                        var6++;
                     } else if (var11 != -1) {
                        this.sY = 6;
                        return false;
                     }
                     break;
                  case 2:
                     if (var11 >= 0) {
                        var7 = var7 << 6 | var11;
                        var6++;
                     } else if (var11 == -2) {
                        var9[var8++] = (byte)(var7 >> 4);
                        var6 = 4;
                     } else if (var11 != -1) {
                        this.sY = 6;
                        return false;
                     }
                     break;
                  case 3:
                     if (var11 >= 0) {
                        var7 = var7 << 6 | var11;
                        var9[var8 + 2] = (byte)var7;
                        var9[var8 + 1] = (byte)(var7 >> 8);
                        var9[var8] = (byte)(var7 >> 16);
                        var8 += 3;
                        var6 = 0;
                     } else if (var11 == -2) {
                        var9[var8 + 1] = (byte)(var7 >> 2);
                        var9[var8] = (byte)(var7 >> 10);
                        var8 += 2;
                        var6 = 5;
                     } else if (var11 != -1) {
                        this.sY = 6;
                        return false;
                     }
                     break;
                  case 4:
                     if (var11 == -2) {
                        var6++;
                     } else if (var11 != -1) {
                        this.sY = 6;
                        return false;
                     }
                     break;
                  case 5:
                     if (var11 != -1) {
                        this.sY = 6;
                        return false;
                     }
               }
            }

            if (!var4) {
               this.sY = var6;
               this.ys = var7;
               this.A = var8;
               return true;
            } else {
               switch (var6) {
                  case 0:
                  case 5:
                  default:
                     break;
                  case 1:
                     this.sY = 6;
                     return false;
                  case 2:
                     var9[var8++] = (byte)(var7 >> 4);
                     break;
                  case 3:
                     var9[var8++] = (byte)(var7 >> 10);
                     var9[var8++] = (byte)(var7 >> 2);
                     break;
                  case 4:
                     this.sY = 6;
                     return false;
               }

               this.sY = var6;
               this.A = var8;
               return true;
            }
         }
      }
   }
}
