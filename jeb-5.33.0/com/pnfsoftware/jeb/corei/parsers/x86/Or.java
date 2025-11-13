package com.pnfsoftware.jeb.corei.parsers.x86;

import com.pnfsoftware.jeb.core.units.code.asm.processor.AbstractProcessor;
import com.pnfsoftware.jeb.core.units.code.asm.processor.ProcessorException;
import com.pnfsoftware.jeb.core.units.code.asm.processor.UnsupportedInstructionException;
import com.pnfsoftware.jeb.core.units.codeobject.ProcessorType;
import com.pnfsoftware.jeb.util.base.Assert;
import com.pnfsoftware.jeb.util.collect.CFBytesTrie;
import com.pnfsoftware.jeb.util.format.Strings;
import com.pnfsoftware.jeb.util.io.Endianness;
import com.pnfsoftware.jeb.util.logging.GlobalLog;
import com.pnfsoftware.jeb.util.logging.ILogger;
import com.pnfsoftware.jeb.util.serialization.annotations.Ser;
import com.pnfsoftware.jeb.util.serialization.annotations.SerId;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Ser
public class Or extends AbstractProcessor {
   private static final ILogger pC = GlobalLog.getLogger(Or.class);
   @SerId(1)
   private jJ A;
   @SerId(2)
   private long kS;
   @SerId(3)
   private long wS;
   @SerId(4)
   private Map UT;
   @SerId(5)
   private CFBytesTrie E;

   public Or(int var1, boolean var2, jJ var3) {
      super(16, var1, Endianness.LITTLE_ENDIAN, 1);
      switch (this.mode) {
         case 16:
         case 32:
         case 64:
            this.supportedModes.add(16);
            this.supportedModes.add(32);
            this.supportedModes.add(64);
            if (var2) {
               this.E = new CFBytesTrie();
               this.E.setKeyExtractor(new hR());
               this.UT = new HashMap();
            }

            if (var3 == null) {
               var3 = jJ.pC;
            }

            this.A = var3;
            return;
         default:
            throw new IllegalArgumentException("Invalid processor mode, must be 16- 32- or 64-bit");
      }
   }

   public Or(int var1) {
      this(var1, true, jJ.kS);
   }

   @Override
   public ProcessorType getType() {
      switch (this.getMode()) {
         case 64:
            return ProcessorType.X86_64;
         default:
            return ProcessorType.X86;
      }
   }

   @Override
   public boolean clearInstructionCache() {
      if (this.E != null) {
         this.E.clear();
      }

      if (this.UT != null) {
         this.UT.clear();
      }

      return true;
   }

   @Override
   public String getRegisterName(long var1) {
      return MG.UT(var1);
   }

   protected vh pC(byte[] var1, int var2, int var3) throws ProcessorException {
      if (this.E != null) {
         vh var4 = (vh)this.E.get(var1, var2, var3, false);
         if (var4 != null) {
            this.kS++;
            return var4;
         }
      }

      int var52 = var2;
      int[] var6 = new int[4];
      int var7 = 0;

      while (true) {
         int var5 = var1[var52] & 255;
         var52++;
         int var8 = A(var5);
         if (var8 < 0) {
            if (this.mode != 64 || (var5 & 240) != 64) {
               rm var57 = Pj.HO[var5];
               int var9 = -1;
               int var10 = -1;
               int var11 = 0;
               int var12 = 0;
               int var13 = 0;
               byte var14 = 0;
               int var15 = -1;
               int var16 = 0;
               int var17 = 0;
               int var18 = 0;
               boolean var19 = false;
               boolean var20 = false;
               if (var5 == 196 || var5 == 197) {
                  int var59 = var1[var52] & 255;
                  if (this.mode == 64 || this.mode == 32 && (var59 & 192) == 192) {
                     var52++;
                     if (var5 == 197) {
                        var10 = var59 >> 3 & 15 ^ 15;
                        var11 = var59 >> 2 & 1;
                        var12 = var59 & 3;
                        var13 = 1;
                        if (this.mode == 64) {
                           var7 = 64 | var59 >> 5 & 4 ^ 4;
                        }
                     } else {
                        int var64 = var1[var52] & 255;
                        var52++;
                        var10 = var64 >> 3 & 15 ^ 15;
                        var11 = var64 >> 2 & 1;
                        var12 = var64 & 3;
                        var13 = var59 & 31;
                        if (this.mode == 64) {
                           var7 = 64 | var64 >> 4 & 8 | var59 >> 5 & 7 ^ 7;
                        }
                     }
                  }
               } else if (var5 == 98) {
                  int var58 = var1[var52] & 255;
                  if (this.mode == 64 || this.mode == 32 && (var58 & 192) == 192) {
                     int var10001 = ++var52;
                     var52++;
                     int var63 = var1[var10001] & 255;
                     int var23 = var1[var52++] & 255;
                     if ((var58 & 12) != 0 || (var63 & 4) != 4) {
                        throw new ProcessorException("Illegal EVEX prefix");
                     }

                     var16 = var58 >> 4 & 1 ^ 1;
                     var10 = var63 >> 3 & 15 ^ 15;
                     var13 = var58 & 3;
                     var15 = var23 & 7;
                     if ((var23 & 8) == 0) {
                        var10 |= 16;
                     }

                     var11 = var23 >> 5 & 1;
                     var18 = var23 >> 6 & 1;
                     var17 = var23 >> 7 & 1;
                     if (this.mode == 64) {
                        var7 = 64 | var63 >> 4 & 8 | var58 >> 5 & 7 ^ 7;
                     }
                  }
               } else if (var5 == 143) {
                  int var21 = var1[var52] & 255;
                  if ((var21 & 56) != 0 && (var21 & 192) == 192) {
                     int var22 = var1[++var52] & 255;
                     var52++;
                     var10 = var22 >> 3 & 15 ^ 15;
                     var11 = var22 >> 2 & 1;
                     var12 = var22 & 3;
                     var13 = var21 & 31;
                     if (this.mode == 64) {
                        var7 = 64 | var22 >> 4 & 8 | var21 >> 5 & 7 ^ 7;
                     }

                     var19 = true;
                  }
               }

               if (var10 != -1) {
                  if (!var19) {
                     switch (var13) {
                        case 1:
                           var5 = 15;
                           break;
                        case 2:
                           var5 = 15;
                           var14 = 56;
                           break;
                        case 3:
                           var5 = 15;
                           var14 = 58;
                           break;
                        default:
                           throw new RuntimeException("VEX: unsupported m index: " + var13);
                     }
                  }

                  switch (var12) {
                     case 0:
                     default:
                        break;
                     case 1:
                        var6[2] = 102;
                        break;
                     case 2:
                        var6[0] = 243;
                        break;
                     case 3:
                        var6[0] = 242;
                  }
               }

               if (var5 == 15) {
                  if (var14 == 0) {
                     var5 = var1[var52] & 255;
                     var52++;
                     var57 = Pj.jj[var5];
                  } else {
                     var5 = var14;
                  }

                  if (var5 == 56) {
                     var5 = var1[var52] & 255;
                     var52++;
                     var57 = this.pC(Pj.jH[var5], var6);
                     var20 = true;
                  } else if (var5 == 58) {
                     var5 = var1[var52] & 255;
                     var52++;
                     var57 = this.pC(Pj.uJ[var5], var6);
                     var20 = true;
                  }
               } else if ((var5 & 248) == 216) {
                  int[] var60 = new int[]{var1[var52] & 255};
                  var52++;
                  var57 = this.pC(var5, var60);
                  var9 = var60[0];
               } else if (var19) {
                  var5 = var1[var52] & 255;
                  var52++;
                  if (var13 == 8) {
                     var57 = gb.A[var5];
                  } else if (var13 == 9) {
                     var57 = gb.kS[var5];
                  } else if (var13 == 10) {
                     var57 = gb.wS[var5];
                  }
               }

               if (var9 < 0) {
                  if (this.mode == 64 && (var57.kS & 1L) != 0L) {
                     if ((var57.kS & 32L) == 0L) {
                        throw new ProcessorException("Illegal opcode in 64-bit mode");
                     }

                     var57 = var57.E;
                  }

                  int var65 = -1;
                  if ((var57.kS & 2048L) != 0L) {
                     var65 = Pj.pC(var57.kS);
                     rm[] var61;
                     if (var19) {
                        var61 = gb.ld[var65];
                     } else {
                        var61 = Pj.XZ[var65];
                     }

                     var9 = var1[var52] & 255;
                     var52++;
                     var57 = var61[var9 >> 3 & 7];
                  }

                  if ((var57.kS & 524288L) != 0L) {
                     if (var65 < 0) {
                        throw new RuntimeException("Reserved for extensions");
                     }

                     if ((var9 >> 6 & 3) == 3) {
                        int var67 = var9 >> 3 & 7;
                        var57 = this.pC(BP.A[var65][var67], var6, var9 & 7);
                     }
                  } else if ((var57.kS & 1048576L) != 0L) {
                     if (var65 < 0) {
                        throw new RuntimeException("Reserved for extensions");
                     }

                     if ((var9 >> 6 & 3) != 3) {
                        int var68 = var9 >> 3 & 7;
                        var57 = this.pC(BP.kS[var65][var68], var6, var9 & 7);
                     }
                  } else if ((var57.kS & 262144L) != 0L) {
                     if (var65 < 0) {
                        var57 = this.pC(BP.E[var5], var6);
                     } else {
                        int var69 = var9 >> 3 & 7;
                        var57 = this.pC(BP.wS[var65][var69], var6, var9 & 7);
                     }
                  } else if ((var57.kS & 33554432L) != 0L) {
                     if (var65 < 0) {
                        throw new RuntimeException("Reserved for extensions");
                     }

                     int var70 = (var9 >> 6 & 3) != 3 ? 0 : 1;
                     int var24 = var9 >> 3 & 7;
                     var57 = this.pC(BP.UT[var65][var70][var24], var6, var9 & 7);
                  }

                  if (var9 < 0) {
                     for (int var26 : var57.UT) {
                        if (pC(var26)) {
                           var9 = var1[var52] & 255;
                           var52++;
                           break;
                        }
                     }
                  }
               }

               int var62 = -1;
               int var66 = -1;
               int var72 = -1;
               int var74 = -1;
               int var75 = 0;
               if (var9 >= 0) {
                  var62 = var9 >> 6 & 3;
                  var66 = var9 & 7;
                  var72 = var9 >> 3 & 7;
                  if (var62 == 3 && var57.sY != null) {
                     var57 = var57.sY;
                  }
               }

               boolean var76 = var20 ? false : var6[2] == 102;
               int var27 = this.pC(var76, var7, var57);
               boolean var28 = var6[3] == 103;
               int var29 = this.pC(var28);
               if (var27 == 64 && var57.E != null) {
                  var57 = var57.E;
               }

               if ((var57.kS & 8388608L) != 0L && var10 == -1) {
                  throw new ProcessorException();
               }

               if (var57.UT == null) {
                  throw new RuntimeException("Missing operands");
               }

               Av[] var30 = new Av[var57.UT.length];

               for (int var31 = 0; var31 < var30.length; var31++) {
                  int var32 = var57.UT[var31];
                  if ((var32 & 32768) == 0 && var32 != 1) {
                     int var33 = var32 >> 8 & 0xFF;
                     if (var33 == 18 || var33 == 21) {
                        var62 = 3;
                     }
                  }
               }

               if (var62 != 3) {
                  if (var29 == 16) {
                     if (var62 == 1) {
                        var75 = var1[var52];
                        var52++;
                     } else if (var62 == 2 || var62 == 0 && var66 == 6) {
                        var75 = pC(var1, var52);
                        var52 += 2;
                     }
                  } else {
                     int var77 = var9 & 199;
                     if (var77 == 4 || var77 == 68 || var77 == 132) {
                        var74 = var1[var52] & 255;
                        var52++;
                     }

                     if (var62 == 1) {
                        var75 = var1[var52];
                        var52++;
                     } else if (var62 == 2 || var62 == 0 && (var66 == 5 || var66 == 4 && (var74 & 7) == 5)) {
                        var75 = A(var1, var52);
                        var52 += 4;
                     }
                  }
               }

               int var78 = 0;

               for (int var34 = 0; var34 < var30.length; var34++) {
                  int var35 = var57.UT[var34];
                  Av var36 = null;
                  short var37 = -1;
                  long var39 = -1L;
                  int var38;
                  if ((var35 & 32768) != 0) {
                     int var41 = var35 & 65535;
                     int var42 = var35 >> 16 & 65535;
                     switch (var41) {
                        case 65520:
                           var37 = 0;
                           var38 = 8;
                           var39 = MG.pC(var42, 0, 8);
                           break;
                        case 65521:
                           var37 = 0;
                           var38 = 16;
                           var39 = MG.pC(var42, 0, 16);
                           break;
                        case 65522:
                           var37 = 0;
                           var38 = 32;
                           var39 = MG.pC(var42, 0, 32);
                           break;
                        case 65523:
                        case 65528:
                        case 65529:
                        case 65530:
                        case 65531:
                        case 65532:
                        case 65533:
                        default:
                           throw new RuntimeException("TBI: regclass: " + var41);
                        case 65524:
                           var37 = 0;
                           var38 = var27 == 16 ? 16 : 32;
                           var39 = MG.pC(var42, 0, var38);
                           break;
                        case 65525:
                           var37 = 0;
                           var38 = var27;
                           var39 = MG.pC(var42, 0, var27);
                           break;
                        case 65526:
                           var37 = 0;
                           var38 = var27;
                           var39 = MG.pC(var42 + ((var7 & 1) != 0 ? 8 : 0), 0, var27);
                           break;
                        case 65527:
                           var37 = 0;
                           var38 = 8;
                           if (var7 == 0) {
                              int var43 = (var42 >> 2 & 1) == 0 ? 0 : 8;
                              var39 = MG.pC(var42 & 3, 0, 8, var43);
                           } else {
                              var39 = MG.pC(var42 + ((var7 & 1) != 0 ? 8 : 0), 0, 8);
                           }
                           break;
                        case 65534:
                           var37 = 0;
                           var38 = 80;
                           var39 = MG.pC(var42, 3, 80);
                           break;
                        case 65535:
                           var37 = 0;
                           var38 = 16;
                           var39 = MG.pC(var42, 2, 16);
                     }
                  } else if (var35 == 1) {
                     var37 = 1;
                     var38 = 8;
                     var39 = 1L;
                  } else {
                     int var84 = var35 >> 8 & 0xFF;
                     if (var84 == 8 && var10 == -1) {
                        continue;
                     }

                     int var85 = var35 & 0xFF;

                     var38 = switch (var85) {
                        case 0 -> {
                           if (var78 == 0) {
                              yield 0;
                           } else {
                              yield var30[var78 - 1].getOperandBitsize();
                           }
                        }
                        case 1 -> var27 == 16 ? 32 : 64;
                        case 2 -> 8;
                        default -> throw new RuntimeException("TBI: unsupported operand type: " + var85);
                        case 4, 15 -> 32;
                        case 5 -> 128;
                        case 6 -> var27 == 32 ? 48 : (var27 == 64 ? 80 : 16);
                        case 7, 9, 18 -> {
                           yield 128;
                           if (var11 == 1) {
                              if (var18 != 0) {
                                 throw new ProcessorException("Unsupported EVEX combination: evex.l' is combined with vex.l");
                              }

                              yield 256;
                           } else if (var18 == 1) {
                              yield 512;
                           }
                        }
                        case 8, 10 -> 64;
                        case 11 -> 256;
                        case 12 -> var27 == 64 ? 80 : 48;
                        case 13 -> 64;
                        case 14 -> 32;
                        case 16 -> var27;
                        case 17 -> 16;
                        case 19 -> this.mode == 64 && var27 == 64 ? 64 : 32;
                        case 20 -> var27 == 16 ? 16 : 32;
                        case 21 -> {
                           if (this.mode != 32 && this.mode != 64) {
                              throw new ProcessorException("Illegal in 16-bit mode");
                           }

                           yield this.mode == 32 ? 64 : 128;
                        }
                        case 22 -> 512;
                        case 32 -> 80;
                        case 33 -> this.mode == 64 && !var76 ? 224 : 112;
                        case 34 -> this.mode == 64 && !var76 ? 864 : 752;
                     };

                     switch (var84) {
                        case 1:
                           long var89;
                           switch (var27) {
                              case 16:
                                 var89 = pC(var1, var52) & 65535L;
                                 var52 += 2;
                                 break;
                              case 32:
                                 var89 = A(var1, var52) & 4294967295L;
                                 var52 += 4;
                                 break;
                              default:
                                 throw new RuntimeException("Opndtype 'A' for supported in 64-bit");
                           }

                           int var93 = pC(var1, var52) & '\uffff';
                           var52 += 2;
                           var37 = 4097;
                           var38 = 0;
                           var39 = (long)var93 << 32 | var89;
                           break;
                        case 2:
                        case 3:
                        case 4:
                        case 7:
                        case 8:
                        case 12:
                        case 16:
                        case 19:
                        case 22:
                           var37 = 0;
                           byte var92 = 0;
                           byte var88;

                           var39 = MG.pC(switch (var84) {
                              case 2 -> {
                                 var88 = 0;
                                 yield var10;
                              }
                              case 3 -> {
                                 var88 = 8;
                                 yield var72 + ((var7 & 4) != 0 ? 8 : 0);
                              }
                              case 4 -> {
                                 var88 = 9;
                                 yield var72 + ((var7 & 4) != 0 ? 8 : 0);
                              }
                              default -> throw new RuntimeException();
                              case 7 -> {
                                 var88 = 0;
                                 yield var72;
                                 if (var7 == 0 && var38 == 8 && var72 >= 4) {
                                    var92 = 8;
                                    yield var72 - 4;
                                 } else if ((var7 & 4) != 0) {
                                    yield var72 + 8;
                                 }
                              }
                              case 8 -> {
                                 var88 = 5;
                                 yield var10;
                              }
                              case 12 -> {
                                 var88 = 5;
                                 yield var1[var52++] >> 4 & (this.mode == 64 ? 15 : 7);
                              }
                              case 16 -> {
                                 var88 = 4;
                                 yield var72;
                              }
                              case 19 -> {
                                 if (var72 >= 6) {
                                    throw new ProcessorException("Reserved segment register");
                                 }

                                 var88 = 2;
                                 yield var72;
                              }
                              case 22 -> {
                                 var88 = 5;
                                 yield var72 + ((var7 & 4) != 0 ? 8 : 0) + (var16 == 1 ? 16 : 0);
                              }
                           }, var88, var38, var92);
                           break;
                        case 5:
                        case 13:
                        case 14:
                        case 17:
                        case 18:
                        case 21:
                        case 23:
                           if (var62 == 3) {
                              if (var84 == 13) {
                                 throw new ProcessorException("Expected a register");
                              }

                              var37 = 0;
                              byte var45 = 0;
                              int var44;
                              byte var86;
                              if (var84 == 5 || var84 == 18) {
                                 var86 = 0;
                                 var44 = var66;
                                 if (var7 == 0 && var38 == 8 && var66 >= 4) {
                                    var44 = var66 - 4;
                                    var45 = 8;
                                 } else if ((var7 & 1) != 0) {
                                    var44 = var66 + 8;
                                 }
                              } else if (var84 != 17 && var84 != 14) {
                                 if (var84 != 23 && var84 != 21) {
                                    throw new RuntimeException();
                                 }

                                 var86 = 5;
                                 var44 = var66 + ((var7 & 1) != 0 ? 8 : 0);
                              } else {
                                 var86 = 4;
                                 var44 = var66;
                              }

                              var39 = MG.pC(var44, var86, var38, var45);
                           } else {
                              if (var84 == 18 || var84 == 21 || var84 == 14) {
                                 throw new ProcessorException("Expected non-register");
                              }

                              long var87 = -1L;
                              int var91 = 0;
                              long var46 = -1L;
                              byte var48 = 0;
                              if (var29 == 16) {
                                 switch (var66) {
                                    case 0:
                                       var87 = MG.pC(3, var48, 16);
                                       var46 = MG.pC(6, var48, 16);
                                       var91 = 1;
                                       break;
                                    case 1:
                                       var87 = MG.pC(3, var48, 16);
                                       var46 = MG.pC(7, var48, 16);
                                       var91 = 1;
                                       break;
                                    case 2:
                                       var87 = MG.pC(5, var48, 16);
                                       var46 = MG.pC(6, var48, 16);
                                       var91 = 1;
                                       break;
                                    case 3:
                                       var87 = MG.pC(5, var48, 16);
                                       var46 = MG.pC(7, var48, 16);
                                       var91 = 1;
                                       break;
                                    case 4:
                                       var87 = MG.pC(6, var48, 16);
                                       break;
                                    case 5:
                                       var87 = MG.pC(7, var48, 16);
                                       break;
                                    case 6:
                                       if (var62 != 0) {
                                          var87 = MG.pC(5, var48, 16);
                                       }
                                       break;
                                    case 7:
                                       var87 = MG.pC(3, var48, 16);
                                 }
                              } else if (var62 == 0 && var66 == 5) {
                                 if (this.mode == 64) {
                                    var87 = MG.pC(0, 10, var29);
                                 }
                              } else if (var66 != 4) {
                                 var87 = MG.pC(var66 + ((var7 & 1) != 0 ? 8 : 0), var48, var29);
                              } else {
                                 int var49 = var74 >> 6 & 3;
                                 int var50 = var74 >> 3 & 7;
                                 if ((var7 & 2) != 0) {
                                    var50 += 8;
                                 }

                                 int var51 = var74 & 7;
                                 if (var51 != 5 || var62 != 0) {
                                    if ((var7 & 1) != 0) {
                                       var51 += 8;
                                    }

                                    var87 = MG.pC(var51, var48, var29);
                                 }

                                 if (var50 != 4) {
                                    var91 = 1 << var49;
                                    var46 = MG.pC(var50, var48, var29);
                                 }
                              }

                              var36 = this.pC(var38, var87, var91, var46, var75);
                           }
                           break;
                        case 6:
                           var37 = 0;
                           var39 = MG.pC(0, 11, var38);
                           break;
                        case 9:
                        case 10:
                           long var79;
                           switch (var38) {
                              case 8:
                                 var79 = var1[var52++];
                                 break;
                              case 16:
                                 var79 = pC(var1, var52);
                                 var52 += 2;
                                 break;
                              case 32:
                                 var79 = A(var1, var52);
                                 var52 += 4;
                                 break;
                              case 64:
                                 var79 = kS(var1, var52);
                                 var52 += 8;
                                 break;
                              default:
                                 throw new RuntimeException("TBI");
                           }

                           if (var84 == 9) {
                              var37 = 1;
                              var39 = var79;
                           } else {
                              var37 = 3;
                              var39 = var79 + (var52 - var2);
                           }
                           break;
                        case 11:
                        case 20:
                        case 26:
                        default:
                           throw new RuntimeException("TBI: addressing method: " + var84);
                        case 15:
                           switch (var29) {
                              case 16:
                                 var39 = pC(var1, var52) & 65535L;
                                 var52 += 2;
                                 break;
                              case 32:
                                 var39 = A(var1, var52) & 4294967295L;
                                 var52 += 4;
                                 break;
                              case 64:
                                 var39 = kS(var1, var52);
                                 var52 += 8;
                                 break;
                              default:
                                 throw new RuntimeException();
                           }

                           var37 = 5;
                           if (var38 == 0) {
                              throw new RuntimeException("No operand size for O operand?");
                           }
                           break;
                        case 24:
                           var37 = 4;
                           var39 = MG.pC(6, 0, var29);
                           break;
                        case 25:
                           var37 = 4;
                           var39 = MG.pC(7, 0, var29);
                           break;
                        case 27:
                           if (var72 < 0 || var72 > 3) {
                              throw new RuntimeException("Illegal MPX bnd register selected: " + var72);
                           }

                           var37 = 0;
                           var39 = MG.pC(var72, 13, 1);
                     }
                  }

                  if (var36 == null) {
                     var36 = this.pC(var37, var38, var39);
                  }

                  var30[var78] = var36;
                  var78++;
               }

               if (var52 > var3) {
                  throw new ProcessorException("Not enough bytes");
               }

               if (var52 - var2 > 15) {
                  throw new ProcessorException("Instruction is too long");
               }

               if (var57.wS == null) {
                  throw new UnsupportedInstructionException("Opcode mnemonic is null");
               }

               byte[] var80 = Arrays.copyOfRange(var1, var2, var52);
               vh var81 = new vh(this.mode, var80, var57);
               int var82 = 0;
               if (var10 != -1) {
                  if (var19) {
                     var82 |= 64;
                  } else {
                     var82 |= 1;
                     if (var15 != -1) {
                        var82 |= 2;
                        if (var17 != 0) {
                           var82 |= 4;
                        }

                        if (var15 != 0) {
                           var82 |= (var15 & 7) << 3;
                        }
                     }
                  }
               }

               Assert.a((var27 & -256) == 0);
               Assert.a((var29 & -256) == 0);
               var81.kS = var82 | var27 << 16 | var29 << 24;
               var81.wS = var6;
               var81.UT = var7;
               var81.E = var9;
               var81.sY = var74;
               var81.pC(var30, var78);
               this.kS++;
               this.wS++;
               boolean var83 = this.pC(var81);
               if (!var83 && this.E != null) {
                  this.E.put(var80, var81);
               }

               return var81;
            }

            var7 = var5;
         } else {
            var6[var8] = var5;
            var7 = 0;
         }
      }
   }

   private boolean pC(vh var1) {
      return false;
   }

   private int pC(boolean var1, int var2, rm var3) throws ProcessorException {
      switch (this.mode) {
         case 16:
            return var1 ? 32 : 16;
         case 32:
            return var1 ? 16 : 32;
         case 64:
            if ((var2 & 8) != 0) {
               return 64;
            } else if (var1 && (var3.kS & 8L) != 0L) {
               throw new ProcessorException("66h not allowed (VEX encoding)");
            } else if ((var3.kS & 16L) != 0L) {
               return 64;
            } else {
               if ((var3.kS & 4L) != 0L && this.A != jJ.A) {
                  return 64;
               }

               int var4 = (var3.kS & 6L) != 0L ? 64 : 32;
               return var1 ? 16 : var4;
            }
         default:
            throw new RuntimeException();
      }
   }

   private int pC(boolean var1) {
      switch (this.mode) {
         case 16:
            return var1 ? 32 : 16;
         case 32:
            return var1 ? 16 : 32;
         case 64:
            return var1 ? 32 : 64;
         default:
            throw new RuntimeException();
      }
   }

   static boolean pC(int var0) {
      int var1 = var0 >> 8 & 0xFF;
      switch (var1) {
         case 3:
         case 4:
         case 5:
         case 7:
         case 13:
         case 14:
         case 16:
         case 17:
         case 18:
         case 19:
         case 20:
         case 21:
         case 22:
         case 23:
            return true;
         case 6:
         case 8:
         case 9:
         case 10:
         case 11:
         case 12:
         case 15:
         default:
            return false;
      }
   }

   static int A(int var0) {
      switch (var0) {
         case 38:
         case 46:
         case 54:
         case 62:
         case 100:
         case 101:
            return 1;
         case 102:
            return 2;
         case 103:
            return 3;
         case 240:
         case 242:
         case 243:
            return 0;
         default:
            return -1;
      }
   }

   static short pC(byte[] var0, int var1) {
      return (short)(var0[var1] & 255 | (var0[var1 + 1] & 255) << 8);
   }

   static int A(byte[] var0, int var1) {
      return var0[var1] & 0xFF | (var0[var1 + 1] & 0xFF) << 8 | (var0[var1 + 2] & 0xFF) << 16 | (var0[var1 + 3] & 0xFF) << 24;
   }

   static long kS(byte[] var0, int var1) {
      return var0[var1] & 255L
         | (var0[var1 + 1] & 255L) << 8
         | (var0[var1 + 2] & 255L) << 16
         | (var0[var1 + 3] & 255L) << 24
         | (var0[var1 + 4] & 255L) << 32
         | (var0[var1 + 5] & 255L) << 40
         | (var0[var1 + 6] & 255L) << 48
         | (var0[var1 + 7] & 255L) << 56;
   }

   private rm pC(int var1, int[] var2) {
      int var4 = var2[0];
      int var5 = var4 >> 6 & 3;
      rm var3;
      if (var5 != 3) {
         int var6 = var4 >> 3 & 7;
         switch (var1) {
            case 216:
               var3 = Bv.cX[var6];
               break;
            case 217:
               var3 = Bv.ZN[var6];
               break;
            case 218:
               var3 = Bv.pF[var6];
               break;
            case 219:
               var3 = Bv.OI[var6];
               break;
            case 220:
               var3 = Bv.Pe[var6];
               break;
            case 221:
               var3 = Bv.RW[var6];
               break;
            case 222:
               var3 = Bv.xM[var6];
               break;
            case 223:
               var3 = Bv.Kq[var6];
               break;
            default:
               throw new RuntimeException();
         }
      } else {
         int var7 = var4 & 63;

         var3 = switch (var1) {
            case 216 -> Bv.DQ[var7];
            case 217 -> Bv.OB[var7];
            case 218 -> Bv.Bc[var7];
            case 219 -> Bv.Bf[var7];
            case 220 -> Bv.ck[var7];
            case 221 -> Bv.e[var7];
            case 222 -> Bv.kU[var7];
            case 223 -> Bv.go[var7];
            default -> throw new RuntimeException();
         };
         var2[0] = -1;
      }

      return var3;
   }

   rm pC(Object[] var1, int[] var2) {
      return this.pC(var1, var2, -1);
   }

   rm pC(Object[] var1, int[] var2, int var3) {
      byte var4;
      if (var2[2] == 102) {
         var4 = 1;
         if (var2[0] == 243) {
            var4 = 2;
         } else if (var2[0] == 242) {
            var4 = 4;
         }
      } else if (var2[0] == 243) {
         var4 = 2;
      } else if (var2[0] == 242) {
         var4 = 3;
      } else {
         var4 = 0;
      }

      if (var4 >= var1.length) {
         var4 = 0;
      }

      Object var5 = var1[var4];
      if (var5 == null) {
         var5 = var1[0];
      }

      if (var5 instanceof rm) {
         return (rm)var5;
      } else if (!(var5 instanceof List)) {
         return null;
      } else {
         Assert.a(var3 >= 0 && var3 <= 7);
         return (rm)((List)var5).get(var3);
      }
   }

   private Av pC(int var1, int var2, long var3) {
      lB var5 = new lB(var1, var2, var3);
      return this.pC(var5);
   }

   private Av pC(int var1, long var2, int var4, long var5, long var7) {
      QM var9 = new QM(var1, var2, var4, var5, var7);
      return this.pC(var9);
   }

   private Av pC(Av var1) {
      if (this.UT == null) {
         return var1;
      } else {
         Av var2 = (Av)this.UT.get(var1);
         if (var2 == null) {
            this.UT.put(var1, var1);
         } else {
            var1 = var2;
         }

         return var1;
      }
   }

   @Override
   public String toString() {
      StringBuilder var1 = new StringBuilder();
      Strings.ff(var1, "cnt=%d,unique=%d", this.kS, this.wS);
      if (this.E != null) {
         Strings.ff(var1, ",cache={%s}", this.E.formatInternalState());
      }

      return var1.toString();
   }

   static {
      Pj.pC = true;
   }
}
