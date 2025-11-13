package com.pnfsoftware.jeb.corei.parsers.avr;

import com.pnfsoftware.jeb.core.units.code.asm.processor.AbstractProcessor;
import com.pnfsoftware.jeb.core.units.code.asm.processor.ProcessorException;
import com.pnfsoftware.jeb.core.units.codeobject.ProcessorType;
import com.pnfsoftware.jeb.util.format.Strings;
import com.pnfsoftware.jeb.util.io.EndianUtil;
import com.pnfsoftware.jeb.util.io.Endianness;
import com.pnfsoftware.jeb.util.logging.GlobalLog;
import com.pnfsoftware.jeb.util.logging.ILogger;
import com.pnfsoftware.jeb.util.math.MathUtil;
import com.pnfsoftware.jeb.util.serialization.annotations.Ser;
import java.util.Arrays;

@Ser
public class RC extends AbstractProcessor {
   private static final ILogger pC = GlobalLog.getLogger(RC.class);

   public RC() {
      super(4, 8, Endianness.LITTLE_ENDIAN, 2);
   }

   @Override
   public ProcessorType getType() {
      return ProcessorType.AVR;
   }

   @Override
   public int getPCRegisterBitsize() {
      return 16;
   }

   @Override
   public int getGPRegisterBitsize() {
      return 8;
   }

   @Override
   public boolean isRISC() {
      return true;
   }

   protected KD pC(byte[] var1, int var2, int var3) throws ProcessorException {
      if (var2 + 2 > var3) {
         throw new ProcessorException("Not enough bytes to fetch an instruction");
      } else {
         int var5 = EndianUtil.bytesToShort(var1, var2, this.getEndianness().toByteOrder()) & '\uffff';
         var2 += 2;
         int var6 = var5 >>> 12;
         int var7 = var5 >> 9 & 7;
         int var8 = var5 >> 8 & 1;
         int var9 = var5 >> 4 & 15;
         int var10 = var5 & 15;
         KD var11 = null;
         switch (var6) {
            case 0:
               if (var5 == 0) {
                  var11 = this.pC("nop");
               } else if (var7 == 0 && var8 == 1) {
                  var11 = this.pC("movw", 2);
                  this.pC(var11, var5, RC.Av.ys);
                  this.pC(var11, var5, RC.Av.sY);
               } else if (var7 == 1) {
                  if (var8 == 0) {
                     var11 = this.pC("muls", 2);
                     this.pC(var11, var5, RC.Av.UT);
                     this.pC(var11, var5, RC.Av.A);
                  } else {
                     int var31 = var5 & 136;
                     String var40;
                     if (var31 == 0) {
                        var40 = "mulsu";
                     } else if (var31 == 8) {
                        var40 = "fmul";
                     } else if (var31 == 128) {
                        var40 = "fmuls";
                     } else {
                        var40 = "fmulsu";
                     }

                     var11 = this.pC(var40, 2);
                     this.pC(var11, var5, RC.Av.E);
                     this.pC(var11, var5, RC.Av.kS);
                  }
               } else if ((var7 & 6) == 2) {
                  var11 = this.pC("cpc", 2);
                  this.pC(var11, var5, RC.Av.wS);
                  this.pC(var11, var5, RC.Av.pC);
               } else if ((var7 & 6) == 4) {
                  var11 = this.pC("sbc", 2);
                  this.pC(var11, var5, RC.Av.wS);
                  this.pC(var11, var5, RC.Av.pC);
               } else if ((var7 & 6) == 6) {
                  var11 = this.pC("add", 2);
                  this.pC(var11, var5, RC.Av.wS);
                  this.pC(var11, var5, RC.Av.pC);
               }
               break;
            case 1:
               if ((var7 & 6) == 0) {
                  var11 = this.pC("cpse", 2);
                  this.pC(var11, var5, RC.Av.wS);
                  this.pC(var11, var5, RC.Av.pC);
               } else if ((var7 & 6) == 2) {
                  var11 = this.pC("cp", 2);
                  this.pC(var11, var5, RC.Av.wS);
                  this.pC(var11, var5, RC.Av.pC);
               } else if ((var7 & 6) == 4) {
                  var11 = this.pC("sub", 2);
                  this.pC(var11, var5, RC.Av.wS);
                  this.pC(var11, var5, RC.Av.pC);
               } else if ((var7 & 6) == 6) {
                  var11 = this.pC("adc", 2);
                  this.pC(var11, var5, RC.Av.wS);
                  this.pC(var11, var5, RC.Av.pC);
               }
               break;
            case 2:
               if ((var7 & 6) == 0) {
                  var11 = this.pC("and", 2);
                  this.pC(var11, var5, RC.Av.wS);
                  this.pC(var11, var5, RC.Av.pC);
               } else if ((var7 & 6) == 2) {
                  var11 = this.pC("eor", 2);
                  this.pC(var11, var5, RC.Av.wS);
                  this.pC(var11, var5, RC.Av.pC);
               } else if ((var7 & 6) == 4) {
                  var11 = this.pC("or", 2);
                  this.pC(var11, var5, RC.Av.wS);
                  this.pC(var11, var5, RC.Av.pC);
               } else if ((var7 & 6) == 6) {
                  var11 = this.pC("mov", 2);
                  this.pC(var11, var5, RC.Av.wS);
                  this.pC(var11, var5, RC.Av.pC);
               }
               break;
            case 3:
               var11 = this.pC("cpi", 2);
               this.pC(var11, var5, RC.Av.UT);
               this.pC(var11, var5, RC.Av.oT);
               break;
            case 4:
               var11 = this.pC("sbci", 2);
               this.pC(var11, var5, RC.Av.UT);
               this.pC(var11, var5, RC.Av.oT);
               break;
            case 5:
               var11 = this.pC("subi", 2);
               this.pC(var11, var5, RC.Av.UT);
               this.pC(var11, var5, RC.Av.oT);
               break;
            case 6:
               var11 = this.pC("ori", 2);
               this.pC(var11, var5, RC.Av.UT);
               this.pC(var11, var5, RC.Av.oT);
               break;
            case 7:
               var11 = this.pC("andi", 2);
               this.pC(var11, var5, RC.Av.UT);
               this.pC(var11, var5, RC.Av.oT);
               break;
            case 8:
            case 10:
               yt var30 = this.pC(var5, RC.Av.wS);
               boolean var39 = (var10 & 8) != 0;
               int var43 = var5 >> 8 & 32 | var5 >> 7 & 24 | var5 & 7;
               yt var15 = this.UT(var39 ? 10 : 11, var43);
               if ((var7 & 1) == 0) {
                  var11 = this.pC("ldd", 2);
                  var11.pC(var30, var15);
               } else {
                  var11 = this.pC("std", 2);
                  var11.pC(var15, var30);
               }
               break;
            case 9:
               if (var7 != 0 && var7 != 1) {
                  if (var7 == 2) {
                     if ((var10 & 8) == 0) {
                        var11 = this.pC(switch (var10) {
                           case 0 -> "com";
                           case 1 -> "neg";
                           case 2 -> "swap";
                           case 3 -> "inc";
                           default -> throw new ProcessorException();
                           case 5 -> "asr";
                           case 6 -> "lsr";
                           case 7 -> "ror";
                        }, 1);
                        this.pC(var11, var5, RC.Av.wS);
                     } else if (var8 == 0 && var10 == 8) {
                        String var28 = this.A(var9 & 7);
                        boolean var38 = (var9 & 8) == 0;
                        var11 = this.pC((var38 ? "se" : "cl") + var28, 0);
                     } else if (var8 == 1 && var10 == 8) {
                        var11 = this.pC(switch (var9) {
                           case 0 -> "ret";
                           case 1 -> "reti";
                           default -> throw new RuntimeException();
                           case 8 -> "sleep";
                           case 9 -> "break";
                           case 10 -> "wdr";
                           case 12 -> "lpm";
                           case 13 -> "elpm";
                           case 14 -> "spm";
                           case 15 -> "spm z+";
                        }, 0);
                     } else if (var10 != 9 || (var9 & 14) != 0) {
                        if ((var10 & 12) == 12) {
                           if (var2 + 2 > var3) {
                              throw new ProcessorException("Not enough bytes to fetch the second word of the JMP/CALL instruction");
                           }

                           int var26 = EndianUtil.bytesToShort(var1, var2, this.getEndianness().toByteOrder()) & '\uffff';
                           var2 += 2;
                           int var37 = (var5 >> 4 & 31) << 17 | (var5 & 1) << 16 | var26;
                           var11 = this.pC((var10 & 2) == 0 ? "jmp" : "call", 1);
                           var11.pC(this.pC(2, 22, 2 * var37));
                        } else if (var10 == 10) {
                           var11 = this.pC("dec", 1);
                           this.pC(var11, var5, RC.Av.wS);
                        } else if (var7 == 2 && var8 == 0 && var10 == 11) {
                           var11 = this.pC("des", 1);
                           this.pC(var11, var5, RC.Av.ld);
                        }
                     } else if (var9 == 0) {
                        var11 = this.pC(var8 == 0 ? "ijmp" : "icall");
                     } else if (var9 == 1) {
                        var11 = this.pC(var8 == 0 ? "eijmp" : "eicall");
                     }
                  } else if (var7 == 3) {
                     var11 = this.pC(var8 == 0 ? "adiw" : "sbiw", 2);
                     this.pC(var11, var5, RC.Av.fI);
                     this.pC(var11, var5, RC.Av.gp);
                  } else if (var7 != 4 && var7 != 5) {
                     if (var7 == 6 || var7 == 7) {
                        var11 = this.pC("mul", 2);
                        this.pC(var11, var5, RC.Av.wS);
                        this.pC(var11, var5, RC.Av.pC);
                     }
                  } else {
                     String var29;
                     if (var8 == 0) {
                        var29 = var7 == 4 ? "cbi" : "sbi";
                     } else {
                        var29 = var7 == 4 ? "sbic" : "sbis";
                     }

                     var11 = this.pC(var29, 2);
                     this.pC(var11, var5, RC.Av.WR);
                     this.pC(var11, var5, RC.Av.vP);
                  }
               } else if (var10 == 15) {
                  var11 = this.pC(var7 == 0 ? "pop" : "push", 1);
                  this.pC(var11, var5, RC.Av.wS);
               } else if (var10 == 12 || var10 == 13 || var10 == 14) {
                  yt var24 = this.pC(var5, RC.Av.wS);
                  yt var36 = this.pC(var10 == 12 ? 1 : (var10 == 13 ? 2 : 3));
                  if (var7 == 0) {
                     var11 = this.pC("ld", 2);
                     var11.pC(var24, var36);
                  } else {
                     var11 = this.pC("st", 2);
                     var11.pC(var36, var24);
                  }
               } else if (var10 == 1 || var10 == 2) {
                  yt var23 = this.pC(var5, RC.Av.wS);
                  yt var35 = this.pC(var10 == 1 ? 8 : 9);
                  if (var7 == 0) {
                     var11 = this.pC("ld", 2);
                     var11.pC(var23, var35);
                  } else {
                     var11 = this.pC("st", 2);
                     var11.pC(var35, var23);
                  }
               } else if (var10 == 9 || var10 == 10) {
                  yt var22 = this.pC(var5, RC.Av.wS);
                  yt var34 = this.pC(var10 == 9 ? 5 : 6);
                  if (var7 == 0) {
                     var11 = this.pC("ld", 2);
                     var11.pC(var22, var34);
                  } else {
                     var11 = this.pC("st", 2);
                     var11.pC(var34, var22);
                  }
               } else if (var7 == 1 && var10 >= 4 && var10 <= 7) {
                  var11 = this.pC(switch (var10) {
                     case 4 -> "xch";
                     case 5 -> "las";
                     case 6 -> "lac";
                     case 7 -> "lat";
                     default -> throw new RuntimeException();
                  }, 2);
                  var11.pC(this.pC(7));
                  var11.pC(this.pC(var5, RC.Av.wS));
               } else if (var10 == 0) {
                  if (var2 + 2 > var3) {
                     throw new ProcessorException("Not enough bytes to fetch the second word of the LDS/STS instruction");
                  }

                  int var20 = EndianUtil.bytesToShort(var1, var2, this.getEndianness().toByteOrder()) & '\uffff';
                  var2 += 2;
                  yt var33 = this.pC(var5, RC.Av.wS);
                  yt var42 = this.pC(5, 8, var20);
                  if (var7 == 0) {
                     var11 = this.pC("lds", 2);
                     var11.pC(var33, var42);
                  } else {
                     var11 = this.pC("sts", 2);
                     var11.pC(var42, var33);
                  }
               } else if (var7 == 0 && (var10 & 12) == 4) {
                  var11 = this.pC((var10 & 2) == 0 ? "lpm" : "elpm", 2);
                  var11.pC(this.pC(var5, RC.Av.wS));
                  var11.pC(this.pC((var10 & 1) == 0 ? 7 : 8));
               }
               break;
            case 11:
               yt var19 = this.pC(var5, RC.Av.wS);
               yt var13 = this.pC(var5, RC.Av.NS);
               if ((var7 & 4) == 0) {
                  var11 = this.pC("in", 2);
                  var11.pC(var19, var13);
               } else {
                  var11 = this.pC("out", 2);
                  var11.pC(var13, var19);
               }
               break;
            case 12:
            case 13:
               long var18 = MathUtil.signExtend(var5 & 4095, 12);
               var11 = this.pC((var6 & 1) == 0 ? "rjmp" : "rcall", 1);
               var11.pC(this.pC(3, 24, 2L + 2L * var18));
               break;
            case 14:
               var11 = this.pC("ldi", 2);
               this.pC(var11, var5, RC.Av.UT);
               this.pC(var11, var5, RC.Av.oT);
               break;
            case 15:
               if ((var7 & 4) == 0) {
                  long var12 = MathUtil.signExtend(var5 >> 3 & 127, 7);
                  String var14;
                  if ((var7 & 2) == 0) {
                     switch (var10 & 7) {
                        case 0:
                           var14 = "brcs";
                           break;
                        case 1:
                           var14 = "breq";
                           break;
                        case 2:
                           var14 = "brmi";
                           break;
                        case 3:
                           var14 = "brvs";
                           break;
                        case 4:
                           var14 = "brlt";
                           break;
                        case 5:
                           var14 = "brhs";
                           break;
                        case 6:
                           var14 = "brts";
                           break;
                        case 7:
                           var14 = "brie";
                           break;
                        default:
                           throw new RuntimeException();
                     }
                  } else {
                     switch (var10 & 7) {
                        case 0:
                           var14 = "brcc";
                           break;
                        case 1:
                           var14 = "brne";
                           break;
                        case 2:
                           var14 = "brpl";
                           break;
                        case 3:
                           var14 = "brvc";
                           break;
                        case 4:
                           var14 = "brge";
                           break;
                        case 5:
                           var14 = "brhc";
                           break;
                        case 6:
                           var14 = "brtc";
                           break;
                        case 7:
                           var14 = "brid";
                           break;
                        default:
                           throw new RuntimeException();
                     }
                  }

                  var11 = this.pC(var14, 1);
                  var11.pC(this.pC(3, 16, 2L + 2L * var12));
               } else if ((var7 & 6) == 4 && (var10 & 8) == 0) {
                  var11 = this.pC(var7 == 4 ? "bld" : "bst", 2);
                  this.pC(var11, var5, RC.Av.wS);
                  this.pC(var11, var5, RC.Av.vP);
               } else if ((var7 & 6) == 6 && (var10 & 8) == 0) {
                  var11 = this.pC((var7 & 1) == 0 ? "sbrc" : "sbrs", 2);
                  this.pC(var11, var5, RC.Av.wS);
                  this.pC(var11, var5, RC.Av.vP);
               }
               break;
            default:
               throw new ProcessorException("Unhandled instruction");
         }

         if (var11 == null) {
            throw new ProcessorException(Strings.ff("Cannot parse opcode: %02Xh", var5));
         } else {
            var11.A = Arrays.copyOfRange(var1, var2, var2);
            String var32 = var11.getMnemonic();
            switch (var32) {
               case "cpse":
               case "sbrc":
               case "sbrs":
               case "sbic":
               case "sbis":
                  if (var3 - var2 < 2) {
                     var11.wS = 2;
                  } else {
                     try {
                        KD var44 = (KD)this.parseAt(var1, var2, var3);
                        var11.wS = var44.getSize();
                     } catch (ProcessorException var16) {
                        var11.wS = 4;
                     }
                  }
               default:
                  return var11;
            }
         }
      }
   }

   yt pC(KD var1, int var2, RC.Av var3) throws ProcessorException {
      yt var4 = this.pC(var2, var3);
      var1.pC(var4);
      return var4;
   }

   yt pC(int var1, RC.Av var2) throws ProcessorException {
      yt var3;
      switch (var2) {
         case pC:
            var3 = this.pC(0, 8, yt.A(0, 8, this.pC(var1, 5)));
            break;
         case A:
            var3 = this.pC(0, 8, yt.A(0, 8, 16 + this.pC(var1, 4)));
            break;
         case kS:
            var3 = this.pC(0, 8, yt.A(0, 8, 16 + this.pC(var1, 3)));
            break;
         case wS:
            var3 = this.pC(0, 8, yt.A(0, 8, this.A(var1, 5)));
            break;
         case UT:
            var3 = this.pC(0, 8, yt.A(0, 8, 16 + this.A(var1, 4)));
            break;
         case E:
            var3 = this.pC(0, 8, yt.A(0, 8, 16 + this.A(var1, 3)));
            break;
         case sY:
            var3 = this.pC(0, 16, yt.A(1, 16, this.kS(var1, 4)));
            break;
         case ys:
            var3 = this.pC(0, 16, yt.A(1, 16, this.wS(var1, 4)));
            break;
         case ld:
            long var10 = var1 >> 4 & 15;
            var3 = this.pC(1, 8, var10);
            break;
         case gp:
            long var9 = var1 >> 2 & 48 | var1 & 15;
            var3 = this.pC(1, 8, var9);
            break;
         case oT:
            long var8 = var1 >> 4 & 240 | var1 & 15;
            var3 = this.pC(1, 8, var8);
            break;
         case fI:
            int var7 = var1 >> 4 & 3;
            var3 = this.pC(0, 16, yt.A(1, 16, 12 + var7));
            break;
         case vP:
            var3 = this.pC(1, 8, var1 & 7);
            break;
         case WR:
            long var6 = var1 >> 3 & 31;
            return this.pC(4098, 5, var6);
         case NS:
            long var4 = var1 & 15 | var1 >> 5 & 48;
            return this.pC(4098, 6, var4);
         default:
            throw new RuntimeException("Unsupported operand type: " + var2);
      }

      return var3;
   }

   int pC(int var1, int var2) throws ProcessorException {
      if (var2 == 3) {
         return var1 & 7;
      } else if (var2 == 4) {
         return var1 & 15;
      } else if (var2 == 5) {
         return var1 & 15 | var1 >> 5 & 16;
      } else {
         throw new ProcessorException();
      }
   }

   int A(int var1, int var2) throws ProcessorException {
      if (var2 == 3) {
         return var1 >> 4 & 7;
      } else if (var2 == 4) {
         return var1 >> 4 & 15;
      } else if (var2 == 5) {
         return var1 >> 4 & 31;
      } else {
         throw new ProcessorException();
      }
   }

   int kS(int var1, int var2) throws ProcessorException {
      if (var2 == 4) {
         return var1 & 15;
      } else {
         throw new ProcessorException();
      }
   }

   int wS(int var1, int var2) throws ProcessorException {
      if (var2 == 4) {
         return var1 >> 4 & 15;
      } else {
         throw new ProcessorException();
      }
   }

   KD pC(String var1) {
      return this.pC(var1, 0);
   }

   KD pC(String var1, int var2) {
      KD var3 = new KD(var1);
      var3.kS = new yt[var2];
      return var3;
   }

   yt pC(int var1, int var2, long var3) {
      return new yt(var1, var2, var3);
   }

   yt UT(int var1, int var2) {
      return yt.pC(var1, var2);
   }

   yt pC(int var1) {
      return this.UT(var1, 0);
   }

   String A(int var1) {
      switch (var1) {
         case 0:
            return "c";
         case 1:
            return "z";
         case 2:
            return "n";
         case 3:
            return "v";
         case 4:
            return "s";
         case 5:
            return "h";
         case 6:
            return "t";
         case 7:
            return "i";
         default:
            throw new RuntimeException();
      }
   }

   static enum Av {
      pC,
      A,
      kS,
      wS,
      UT,
      E,
      sY,
      ys,
      ld,
      gp,
      oT,
      fI,
      WR,
      NS,
      vP;
   }
}
