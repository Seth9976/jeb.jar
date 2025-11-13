package com.pnfsoftware.jeb.corei.parsers.simatic;

import com.pnfsoftware.jeb.core.units.code.asm.processor.AbstractProcessor;
import com.pnfsoftware.jeb.core.units.code.asm.processor.BytesBlock;
import com.pnfsoftware.jeb.core.units.code.asm.processor.ProcessorException;
import com.pnfsoftware.jeb.core.units.codeobject.ProcessorType;
import com.pnfsoftware.jeb.util.base.Assert;
import com.pnfsoftware.jeb.util.format.Strings;
import com.pnfsoftware.jeb.util.io.EndianUtil;
import com.pnfsoftware.jeb.util.io.Endianness;
import com.pnfsoftware.jeb.util.serialization.annotations.Ser;
import java.nio.ByteOrder;
import java.util.Arrays;

@Ser
public class sy extends AbstractProcessor {
   private static final ByteOrder pC = ByteOrder.BIG_ENDIAN;

   public sy() {
      super(6, 32, Endianness.BIG_ENDIAN, 1);
   }

   @Override
   public ProcessorType getType() {
      return MC7Plugin.pC;
   }

   protected yt pC(byte[] var1, int var2, int var3) throws ProcessorException {
      if (var2 >= 0 && var2 < var3 && var2 < var1.length) {
         int var4 = var2 + 1;
         int var5 = var1[var2] & 255;
         if (var5 >= qt.pC.length) {
            throw new RuntimeException();
         } else {
            qt.Av var6 = qt.pC[var5];
            RC var7 = null;
            int var8 = 0;
            int var9 = 0;
            int var10 = 0;
            int var12 = var6.A & 0xFF;
            switch (var12) {
               case 0:
                  break;
               case 1:
                  int var32 = var1[var4++] & 255;
                  if (var32 == 0) {
                     break;
                  }

                  if (var32 >= 16 && var32 <= 127) {
                     var6 = var6.E[0];
                  } else {
                     if (var32 < 144 || var32 > 239) {
                        throw new ProcessorException();
                     }

                     var6 = var6.E[1];
                  }

                  Assert.a(var6 != null && (var6.A & 0xFF) == 2);
                  int var42 = var32 & 127;
                  if ((var42 & 8) != 0) {
                     throw new RuntimeException("Reserved it should be 0");
                  }

                  int var47 = var42 >> 4;
                  if (var47 < 1 || var47 > 6) {
                     throw new RuntimeException("Illegal memory type");
                  }

                  int var49 = var42 & 15;
                  var9 |= var47 | var49 << 20;
                  break;
               case 2:
               case 3:
               case 8:
               case 10:
               case 13:
               case 16:
               case 18:
               case 20:
               case 22:
               case 24:
               default:
                  throw new RuntimeException(Strings.ff("insnflags= 0x%X", var6.A));
               case 4:
                  int var31 = var1[var4++] & 255;
                  if ((var31 & 31) != 0) {
                     throw new RuntimeException("Unexpected");
                  }
                  break;
               case 5:
                  int var41 = var1[var4++] & 255;
                  if (var41 != 0) {
                     if (var41 < 1 || var41 > 12) {
                        throw new RuntimeException("Unexpected");
                     }

                     var9 |= var41 << 28;
                  }
                  break;
               case 6:
                  if ((var1[var4] & 128) != 0) {
                     var6 = var6.E[0];
                  }
                  break;
               case 7:
                  int var30 = var1[var4++] & 255;
                  if (var30 != 0) {
                     int var40 = var30 >> 6 & 2 | var30 >> 3 & 1;
                     var6 = var6.E[var40];
                     Assert.a(var6 != null && (var6.A & 0xFF) == 8);
                     int var46 = var30 & 7;
                     Assert.a(var46 >= 1 && var46 <= 6);
                     var9 |= var46;
                     int var15 = var30 >> 4 & 7;
                     var10 |= var15 | 12288;
                  }
                  break;
               case 9:
                  int var29 = var1[var4++] & 255;
                  if (var29 != 5) {
                     var6 = var6.E[0];
                  }
                  break;
               case 11:
                  int var28 = var1[var4++] & 255;
                  if (var28 != 0) {
                     if (var28 == 1) {
                        var6 = var6.E[0];
                     } else {
                        Assert.fail();
                     }
                  }
                  break;
               case 12:
                  int var27 = var1[var4++] & 255;
                  if ((var27 & 15) == 1) {
                     var8 = var27 >> 4;
                  } else if (var27 == 52) {
                     var6 = var6.E[1];
                  } else if (var27 == 68) {
                     var6 = var6.E[2];
                  } else if (var27 == 84) {
                     var6 = var6.E[3];
                  } else if (var27 == 54) {
                     var6 = var6.E[4];
                  } else if (var27 == 70) {
                     var6 = var6.E[5];
                  } else if (var27 == 86) {
                     var6 = var6.E[6];
                  } else {
                     var6 = var6.E[0];
                  }
                  break;
               case 14:
                  int var26 = var1[var4++] & 255;
                  int var39 = var26 & 15;
                  var6 = var6.E[var39];
                  Assert.a(var6 != null);
                  break;
               case 15:
                  int var25 = var1[var4++] & 255;
                  if (var25 != 0) {
                     var6 = var6.E[0];
                     Assert.a(var6 != null && (var6.A & 0xFF) == 16);
                     if ((var25 & 8) == 0) {
                        var10 |= 32;
                     } else {
                        var10 |= 33;
                     }

                     var10 |= 1073741824;
                     int var38 = var25 >> 4 & 7;
                     var9 |= var38;
                  }
                  break;
               case 17:
                  int var24 = var1[var4++] & 255;
                  if (var24 != 0) {
                     var6 = var6.E[var24 >> 2 & 1];
                     Assert.a(var6 != null && (var6.A & 0xFF) == 18);
                     int var37 = var24 & 3;
                     if (var37 == 0) {
                        throw new RuntimeException();
                     }

                     var9 |= var37 << 12;
                     int var45 = var24 >> 4;
                     if (var45 > 6) {
                        throw new RuntimeException();
                     }

                     if (var45 == 0) {
                        var45 = 16;
                     }

                     var9 |= var45;
                  }
                  break;
               case 19:
                  int var23 = var1[var4++] & 255;
                  if (var23 != 0) {
                     var6 = var6.E[0];
                     Assert.a(var6 != null && (var6.A & 0xFF) == 20);
                     if (var5 == 186) {
                        Assert.a((var23 & 8) == 0);
                        var9 |= 4096;
                     } else if (var5 == 187) {
                        boolean var35 = (var23 & 8) == 0;
                        var9 |= var35 ? 8192 : 12288;
                     } else {
                        Assert.fail();
                     }

                     int var36 = var23 & 7;
                     if (var36 > 6) {
                        throw new RuntimeException();
                     }

                     if (var36 == 0) {
                        var36 = 16;
                     }

                     var9 |= var36;
                     int var44 = var23 >> 4 & 7;
                     var10 |= var44 | 12288;
                  }
                  break;
               case 21:
                  int var22 = var1[var4++] & 255;
                  if (var22 != 0) {
                     var6 = var6.E[0];
                     Assert.a(var6 != null && (var6.A & 0xFF) == 22);
                     if ((var22 & 8) == 0) {
                        var10 |= 32;
                     } else {
                        var10 |= 33;
                     }

                     var10 |= 1073741824;
                     Assert.a((var22 & 128) == 0);
                     int var34 = var22 >> 4 & 7;
                     int var14 = (var22 & 3) << 12;
                     var9 |= var34 | var14;
                  } else {
                     Assert.fail();
                  }
                  break;
               case 23:
                  int var21 = var1[var4++] & 255;
                  if (var21 != 0) {
                     var6 = var6.E[0];
                     Assert.a(var6 != null && (var6.A & 0xFF) == 24);
                     var9 |= (var21 & 128) == 0 ? 8 : 9;
                     int var33 = var21 >> 4 & 7;
                     var10 |= var33 | 8192;
                  }
                  break;
               case 25:
               case 26:
               case 27:
                  int var11 = var1[var4++] & 255;
                  qt.Av[] var13 = null;
                  switch (var5) {
                     case 251:
                        var13 = qt.A;
                        break;
                     case 252:
                     case 253:
                     default:
                        Assert.fail();
                        break;
                     case 254:
                        var13 = qt.kS;
                        break;
                     case 255:
                        var13 = qt.wS;
                  }

                  if (var11 >= var13.length) {
                     throw new RuntimeException(Strings.ff("TBI: Opcode: %02X %02X", var5, var11));
                  }

                  var6 = var13[var11];
                  if ((var6.A & 0xFF) == 28) {
                     var8 = var11 & 15;
                  }
            }

            var9 |= var6.wS;
            var10 |= var6.UT;
            if (var9 != 0) {
               switch (var6.wS & 983040) {
                  case 0:
                  case 524288:
                     break;
                  case 65536:
                     var8 = var1[var4++] & 255;
                     break;
                  case 131072:
                     var8 = EndianUtil.bytesToShort(var1, var4, pC) & '\uffff';
                     var4 += 2;
                     break;
                  case 196608:
                  case 458752:
                     var8 = EndianUtil.bytesToInt(var1, var4, pC);
                     var4 += 4;
                     break;
                  case 262144:
                     var8 = var1[var4++] & 127;
                     break;
                  case 327680:
                     var8 = var1[var4++];
                     break;
                  case 393216:
                     var8 = EndianUtil.bytesToShort(var1, var4, pC);
                     var4 += 2;
                     break;
                  default:
                     throw new RuntimeException(Strings.ff("opndflags=0x%X", var6.wS));
               }

               var7 = new RC(var9, var8, var10);
            }

            BytesBlock var43 = new BytesBlock(Arrays.copyOfRange(var1, var2, var4));
            RC[] var48 = var7 == null ? new RC[0] : new RC[]{var7};
            return new yt(var43, var6, var48);
         }
      } else {
         return null;
      }
   }
}
