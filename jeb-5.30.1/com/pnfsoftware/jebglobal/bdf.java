package com.pnfsoftware.jebglobal;

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
public class bdf extends AbstractProcessor {
   private static final ILogger q = GlobalLog.getLogger(bdf.class);

   public bdf() {
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

   protected bdc q(byte[] var1, int var2, int var3) throws ProcessorException {
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
         bdc var11 = null;
         switch (var6) {
            case 0:
               if (var5 == 0) {
                  var11 = this.q("nop");
               } else if (var7 == 0 && var8 == 1) {
                  var11 = this.q("movw", 2);
                  this.q(var11, var5, bdf.eo.nf);
                  this.q(var11, var5, bdf.eo.gO);
               } else if (var7 == 1) {
                  if (var8 == 0) {
                     var11 = this.q("muls", 2);
                     this.q(var11, var5, bdf.eo.Uv);
                     this.q(var11, var5, bdf.eo.RF);
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

                     var11 = this.q(var40, 2);
                     this.q(var11, var5, bdf.eo.oW);
                     this.q(var11, var5, bdf.eo.xK);
                  }
               } else if ((var7 & 6) == 2) {
                  var11 = this.q("cpc", 2);
                  this.q(var11, var5, bdf.eo.Dw);
                  this.q(var11, var5, bdf.eo.q);
               } else if ((var7 & 6) == 4) {
                  var11 = this.q("sbc", 2);
                  this.q(var11, var5, bdf.eo.Dw);
                  this.q(var11, var5, bdf.eo.q);
               } else if ((var7 & 6) == 6) {
                  var11 = this.q("add", 2);
                  this.q(var11, var5, bdf.eo.Dw);
                  this.q(var11, var5, bdf.eo.q);
               }
               break;
            case 1:
               if ((var7 & 6) == 0) {
                  var11 = this.q("cpse", 2);
                  this.q(var11, var5, bdf.eo.Dw);
                  this.q(var11, var5, bdf.eo.q);
               } else if ((var7 & 6) == 2) {
                  var11 = this.q("cp", 2);
                  this.q(var11, var5, bdf.eo.Dw);
                  this.q(var11, var5, bdf.eo.q);
               } else if ((var7 & 6) == 4) {
                  var11 = this.q("sub", 2);
                  this.q(var11, var5, bdf.eo.Dw);
                  this.q(var11, var5, bdf.eo.q);
               } else if ((var7 & 6) == 6) {
                  var11 = this.q("adc", 2);
                  this.q(var11, var5, bdf.eo.Dw);
                  this.q(var11, var5, bdf.eo.q);
               }
               break;
            case 2:
               if ((var7 & 6) == 0) {
                  var11 = this.q("and", 2);
                  this.q(var11, var5, bdf.eo.Dw);
                  this.q(var11, var5, bdf.eo.q);
               } else if ((var7 & 6) == 2) {
                  var11 = this.q("eor", 2);
                  this.q(var11, var5, bdf.eo.Dw);
                  this.q(var11, var5, bdf.eo.q);
               } else if ((var7 & 6) == 4) {
                  var11 = this.q("or", 2);
                  this.q(var11, var5, bdf.eo.Dw);
                  this.q(var11, var5, bdf.eo.q);
               } else if ((var7 & 6) == 6) {
                  var11 = this.q("mov", 2);
                  this.q(var11, var5, bdf.eo.Dw);
                  this.q(var11, var5, bdf.eo.q);
               }
               break;
            case 3:
               var11 = this.q("cpi", 2);
               this.q(var11, var5, bdf.eo.Uv);
               this.q(var11, var5, bdf.eo.lm);
               break;
            case 4:
               var11 = this.q("sbci", 2);
               this.q(var11, var5, bdf.eo.Uv);
               this.q(var11, var5, bdf.eo.lm);
               break;
            case 5:
               var11 = this.q("subi", 2);
               this.q(var11, var5, bdf.eo.Uv);
               this.q(var11, var5, bdf.eo.lm);
               break;
            case 6:
               var11 = this.q("ori", 2);
               this.q(var11, var5, bdf.eo.Uv);
               this.q(var11, var5, bdf.eo.lm);
               break;
            case 7:
               var11 = this.q("andi", 2);
               this.q(var11, var5, bdf.eo.Uv);
               this.q(var11, var5, bdf.eo.lm);
               break;
            case 8:
            case 10:
               bdd var30 = this.q(var5, bdf.eo.Dw);
               boolean var39 = (var10 & 8) != 0;
               int var43 = var5 >> 8 & 32 | var5 >> 7 & 24 | var5 & 7;
               bdd var15 = this.Uv(var39 ? 10 : 11, var43);
               if ((var7 & 1) == 0) {
                  var11 = this.q("ldd", 2);
                  var11.q(var30, var15);
               } else {
                  var11 = this.q("std", 2);
                  var11.q(var15, var30);
               }
               break;
            case 9:
               if (var7 != 0 && var7 != 1) {
                  if (var7 == 2) {
                     if ((var10 & 8) == 0) {
                        var11 = this.q(switch (var10) {
                           case 0 -> "com";
                           case 1 -> "neg";
                           case 2 -> "swap";
                           case 3 -> "inc";
                           default -> throw new ProcessorException();
                           case 5 -> "asr";
                           case 6 -> "lsr";
                           case 7 -> "ror";
                        }, 1);
                        this.q(var11, var5, bdf.eo.Dw);
                     } else if (var8 == 0 && var10 == 8) {
                        String var28 = this.RF(var9 & 7);
                        boolean var38 = (var9 & 8) == 0;
                        var11 = this.q((var38 ? "se" : "cl") + var28, 0);
                     } else if (var8 == 1 && var10 == 8) {
                        var11 = this.q(switch (var9) {
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
                           var11 = this.q((var10 & 2) == 0 ? "jmp" : "call", 1);
                           var11.q(this.q(2, 22, 2 * var37));
                        } else if (var10 == 10) {
                           var11 = this.q("dec", 1);
                           this.q(var11, var5, bdf.eo.Dw);
                        } else if (var7 == 2 && var8 == 0 && var10 == 11) {
                           var11 = this.q("des", 1);
                           this.q(var11, var5, bdf.eo.gP);
                        }
                     } else if (var9 == 0) {
                        var11 = this.q(var8 == 0 ? "ijmp" : "icall");
                     } else if (var9 == 1) {
                        var11 = this.q(var8 == 0 ? "eijmp" : "eicall");
                     }
                  } else if (var7 == 3) {
                     var11 = this.q(var8 == 0 ? "adiw" : "sbiw", 2);
                     this.q(var11, var5, bdf.eo.zz);
                     this.q(var11, var5, bdf.eo.za);
                  } else if (var7 != 4 && var7 != 5) {
                     if (var7 == 6 || var7 == 7) {
                        var11 = this.q("mul", 2);
                        this.q(var11, var5, bdf.eo.Dw);
                        this.q(var11, var5, bdf.eo.q);
                     }
                  } else {
                     String var29;
                     if (var8 == 0) {
                        var29 = var7 == 4 ? "cbi" : "sbi";
                     } else {
                        var29 = var7 == 4 ? "sbic" : "sbis";
                     }

                     var11 = this.q(var29, 2);
                     this.q(var11, var5, bdf.eo.JY);
                     this.q(var11, var5, bdf.eo.LK);
                  }
               } else if (var10 == 15) {
                  var11 = this.q(var7 == 0 ? "pop" : "push", 1);
                  this.q(var11, var5, bdf.eo.Dw);
               } else if (var10 == 12 || var10 == 13 || var10 == 14) {
                  bdd var24 = this.q(var5, bdf.eo.Dw);
                  bdd var36 = this.q(var10 == 12 ? 1 : (var10 == 13 ? 2 : 3));
                  if (var7 == 0) {
                     var11 = this.q("ld", 2);
                     var11.q(var24, var36);
                  } else {
                     var11 = this.q("st", 2);
                     var11.q(var36, var24);
                  }
               } else if (var10 == 1 || var10 == 2) {
                  bdd var23 = this.q(var5, bdf.eo.Dw);
                  bdd var35 = this.q(var10 == 1 ? 8 : 9);
                  if (var7 == 0) {
                     var11 = this.q("ld", 2);
                     var11.q(var23, var35);
                  } else {
                     var11 = this.q("st", 2);
                     var11.q(var35, var23);
                  }
               } else if (var10 == 9 || var10 == 10) {
                  bdd var22 = this.q(var5, bdf.eo.Dw);
                  bdd var34 = this.q(var10 == 9 ? 5 : 6);
                  if (var7 == 0) {
                     var11 = this.q("ld", 2);
                     var11.q(var22, var34);
                  } else {
                     var11 = this.q("st", 2);
                     var11.q(var34, var22);
                  }
               } else if (var7 == 1 && var10 >= 4 && var10 <= 7) {
                  var11 = this.q(switch (var10) {
                     case 4 -> "xch";
                     case 5 -> "las";
                     case 6 -> "lac";
                     case 7 -> "lat";
                     default -> throw new RuntimeException();
                  }, 2);
                  var11.q(this.q(7));
                  var11.q(this.q(var5, bdf.eo.Dw));
               } else if (var10 == 0) {
                  if (var2 + 2 > var3) {
                     throw new ProcessorException("Not enough bytes to fetch the second word of the LDS/STS instruction");
                  }

                  int var20 = EndianUtil.bytesToShort(var1, var2, this.getEndianness().toByteOrder()) & '\uffff';
                  var2 += 2;
                  bdd var33 = this.q(var5, bdf.eo.Dw);
                  bdd var42 = this.q(5, 8, var20);
                  if (var7 == 0) {
                     var11 = this.q("lds", 2);
                     var11.q(var33, var42);
                  } else {
                     var11 = this.q("sts", 2);
                     var11.q(var42, var33);
                  }
               } else if (var7 == 0 && (var10 & 12) == 4) {
                  var11 = this.q((var10 & 2) == 0 ? "lpm" : "elpm", 2);
                  var11.q(this.q(var5, bdf.eo.Dw));
                  var11.q(this.q((var10 & 1) == 0 ? 7 : 8));
               }
               break;
            case 11:
               bdd var19 = this.q(var5, bdf.eo.Dw);
               bdd var13 = this.q(var5, bdf.eo.HF);
               if ((var7 & 4) == 0) {
                  var11 = this.q("in", 2);
                  var11.q(var19, var13);
               } else {
                  var11 = this.q("out", 2);
                  var11.q(var13, var19);
               }
               break;
            case 12:
            case 13:
               long var18 = MathUtil.signExtend(var5 & 4095, 12);
               var11 = this.q((var6 & 1) == 0 ? "rjmp" : "rcall", 1);
               var11.q(this.q(3, 24, 2L + 2L * var18));
               break;
            case 14:
               var11 = this.q("ldi", 2);
               this.q(var11, var5, bdf.eo.Uv);
               this.q(var11, var5, bdf.eo.lm);
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

                  var11 = this.q(var14, 1);
                  var11.q(this.q(3, 16, 2L + 2L * var12));
               } else if ((var7 & 6) == 4 && (var10 & 8) == 0) {
                  var11 = this.q(var7 == 4 ? "bld" : "bst", 2);
                  this.q(var11, var5, bdf.eo.Dw);
                  this.q(var11, var5, bdf.eo.LK);
               } else if ((var7 & 6) == 6 && (var10 & 8) == 0) {
                  var11 = this.q((var7 & 1) == 0 ? "sbrc" : "sbrs", 2);
                  this.q(var11, var5, bdf.eo.Dw);
                  this.q(var11, var5, bdf.eo.LK);
               }
               break;
            default:
               throw new ProcessorException("Unhandled instruction");
         }

         if (var11 == null) {
            throw new ProcessorException(Strings.ff("Cannot parse opcode: %02Xh", var5));
         } else {
            var11.RF = Arrays.copyOfRange(var1, var2, var2);
            String var32 = var11.getMnemonic();
            switch (var32) {
               case "cpse":
               case "sbrc":
               case "sbrs":
               case "sbic":
               case "sbis":
                  if (var3 - var2 < 2) {
                     var11.Dw = 2;
                  } else {
                     try {
                        bdc var44 = (bdc)this.parseAt(var1, var2, var3);
                        var11.Dw = var44.getSize();
                     } catch (ProcessorException var16) {
                        var11.Dw = 4;
                     }
                  }
               default:
                  return var11;
            }
         }
      }
   }

   bdd q(bdc var1, int var2, bdf.eo var3) throws ProcessorException {
      bdd var4 = this.q(var2, var3);
      var1.q(var4);
      return var4;
   }

   bdd q(int var1, bdf.eo var2) throws ProcessorException {
      bdd var3;
      switch (var2) {
         case q:
            var3 = this.q(0, 8, bdd.RF(0, 8, this.q(var1, 5)));
            break;
         case RF:
            var3 = this.q(0, 8, bdd.RF(0, 8, 16 + this.q(var1, 4)));
            break;
         case xK:
            var3 = this.q(0, 8, bdd.RF(0, 8, 16 + this.q(var1, 3)));
            break;
         case Dw:
            var3 = this.q(0, 8, bdd.RF(0, 8, this.RF(var1, 5)));
            break;
         case Uv:
            var3 = this.q(0, 8, bdd.RF(0, 8, 16 + this.RF(var1, 4)));
            break;
         case oW:
            var3 = this.q(0, 8, bdd.RF(0, 8, 16 + this.RF(var1, 3)));
            break;
         case gO:
            var3 = this.q(0, 16, bdd.RF(1, 16, this.xK(var1, 4)));
            break;
         case nf:
            var3 = this.q(0, 16, bdd.RF(1, 16, this.Dw(var1, 4)));
            break;
         case gP:
            long var10 = var1 >> 4 & 15;
            var3 = this.q(1, 8, var10);
            break;
         case za:
            long var9 = var1 >> 2 & 48 | var1 & 15;
            var3 = this.q(1, 8, var9);
            break;
         case lm:
            long var8 = var1 >> 4 & 240 | var1 & 15;
            var3 = this.q(1, 8, var8);
            break;
         case zz:
            int var7 = var1 >> 4 & 3;
            var3 = this.q(0, 16, bdd.RF(1, 16, 12 + var7));
            break;
         case LK:
            var3 = this.q(1, 8, var1 & 7);
            break;
         case JY:
            long var6 = var1 >> 3 & 31;
            return this.q(4098, 5, var6);
         case HF:
            long var4 = var1 & 15 | var1 >> 5 & 48;
            return this.q(4098, 6, var4);
         default:
            throw new RuntimeException("Unsupported operand type: " + var2);
      }

      return var3;
   }

   int q(int var1, int var2) throws ProcessorException {
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

   int RF(int var1, int var2) throws ProcessorException {
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

   int xK(int var1, int var2) throws ProcessorException {
      if (var2 == 4) {
         return var1 & 15;
      } else {
         throw new ProcessorException();
      }
   }

   int Dw(int var1, int var2) throws ProcessorException {
      if (var2 == 4) {
         return var1 >> 4 & 15;
      } else {
         throw new ProcessorException();
      }
   }

   bdc q(String var1) {
      return this.q(var1, 0);
   }

   bdc q(String var1, int var2) {
      bdc var3 = new bdc(var1);
      var3.xK = new bdd[var2];
      return var3;
   }

   bdd q(int var1, int var2, long var3) {
      return new bdd(var1, var2, var3);
   }

   bdd Uv(int var1, int var2) {
      return bdd.q(var1, var2);
   }

   bdd q(int var1) {
      return this.Uv(var1, 0);
   }

   String RF(int var1) {
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

   static enum eo {
      q,
      RF,
      xK,
      Dw,
      Uv,
      oW,
      gO,
      nf,
      gP,
      za,
      lm,
      zz,
      JY,
      HF,
      LK;
   }
}
