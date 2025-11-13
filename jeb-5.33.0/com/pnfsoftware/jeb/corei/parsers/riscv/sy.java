package com.pnfsoftware.jeb.corei.parsers.riscv;

import com.pnfsoftware.jeb.core.units.code.asm.processor.AbstractProcessor;
import com.pnfsoftware.jeb.core.units.code.asm.processor.BytesBlock;
import com.pnfsoftware.jeb.core.units.code.asm.processor.IRegisterBank;
import com.pnfsoftware.jeb.core.units.code.asm.processor.ProcessorException;
import com.pnfsoftware.jeb.core.units.code.asm.processor.RegisterDescriptionEntry;
import com.pnfsoftware.jeb.core.units.code.asm.processor.arch.RegisterUtil;
import com.pnfsoftware.jeb.core.units.codeobject.ProcessorType;
import com.pnfsoftware.jeb.util.base.Assert;
import com.pnfsoftware.jeb.util.io.EndianUtil;
import com.pnfsoftware.jeb.util.io.Endianness;
import com.pnfsoftware.jeb.util.logging.GlobalLog;
import com.pnfsoftware.jeb.util.logging.ILogger;
import com.pnfsoftware.jeb.util.serialization.annotations.Ser;
import com.pnfsoftware.jeb.util.serialization.annotations.SerId;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

@Ser
public class sy extends AbstractProcessor {
   private static final ILogger pC = GlobalLog.getLogger(sy.class);
   @SerId(1)
   private int A;

   public sy(int var1) {
      super(4, var1, Endianness.LITTLE_ENDIAN, 2);

      this.pC(switch (var1) {
         case 32 -> 0;
         case 64 -> 1;
         case 128 -> 2;
         default -> throw new RuntimeException();
      });
   }

   @Override
   public Collection getSupportedModes() {
      return Arrays.asList(32, 64);
   }

   public void pC(int var1) {
      this.A = var1;
   }

   @Override
   public ProcessorType getType() {
      switch (this.mode) {
         case 32:
            return RiscvPlugin.pC;
         case 64:
            return RiscvPlugin.A;
         default:
            throw new RuntimeException();
      }
   }

   @Override
   public int getPCRegisterBitsize() {
      return this.getMode();
   }

   @Override
   public int getGPRegisterBitsize() {
      return this.getMode();
   }

   @Override
   public IRegisterBank getRegisterBank() {
      switch (this.getMode()) {
         case 32:
            return HE.kS;
         case 64:
            return HE.wS;
         default:
            throw new RuntimeException("Unsupported");
      }
   }

   @Override
   public boolean isRISC() {
      return true;
   }

   boolean pC() {
      return this.mode == 32;
   }

   boolean A() {
      return this.mode == 128;
   }

   boolean kS() {
      return this.mode == 32 || this.mode == 64;
   }

   boolean wS() {
      return this.mode == 64 || this.mode == 128;
   }

   protected yt pC(byte[] var1, int var2, int var3) throws ProcessorException {
      if (var2 + 2 > var3) {
         throw new ProcessorException("Not enough bytes");
      } else {
         int var4 = var1[var2] & 127;
         int var5 = var4 & 3;
         int var6;
         if (var2 + 4 > var3) {
            if (var5 == 3) {
               throw new ProcessorException("Not enough bytes");
            }

            var6 = EndianUtil.littleEndianBytesToShort(var1, var2);
         } else {
            var6 = EndianUtil.littleEndianBytesToInt(var1, var2);
         }

         yt var7;
         if (var5 == 3) {
            int var8 = var4 >> 2 & 31;
            switch (var8) {
               case 0:
               case 1:
                  var7 = this.A(var6, var8);
                  break;
               case 2:
               case 7:
               case 10:
               case 15:
               case 21:
               case 22:
               case 23:
               case 26:
               default:
                  throw new RuntimeException();
               case 3:
                  var7 = this.kS(var6, var8);
                  break;
               case 4:
               case 6:
                  var7 = this.A(var6, var8);
                  break;
               case 5:
               case 13:
                  var7 = this.pC(var6, var8);
                  break;
               case 8:
               case 9:
                  var7 = this.UT(var6, var8);
                  break;
               case 11:
                  var7 = this.ld(var6, var8);
                  break;
               case 12:
               case 14:
                  var7 = this.ys(var6, var8);
                  break;
               case 16:
               case 17:
               case 18:
               case 19:
                  var7 = this.oT(var6, var8);
                  break;
               case 20:
                  var7 = this.gp(var6, var8);
                  break;
               case 24:
                  var7 = this.E(var6, var8);
                  break;
               case 25:
                  var7 = this.A(var6, var8);
                  break;
               case 27:
                  var7 = this.sY(var6, var8);
                  break;
               case 28:
                  var7 = this.wS(var6, var8);
            }
         } else {
            var7 = this.A(var6 & 65535);
         }

         Assert.a(var7 != null);
         return var7;
      }
   }

   yt pC(int var1, int var2) {
      Assert.a(var2 == 13 || var2 == 5);
      int var3 = var1 >> 7 & 31;
      long var4 = var1 & -4096L;
      var4 >>= 12;
      qt.Av var6 = var2 == 13 ? qt.hZ : qt.UW;
      return this.pC(var1, var6, var3, var4, 0L, 0L);
   }

   yt A(int var1, int var2) {
      int var3 = var1 >> 12 & 7;
      int var4 = var1 >> 7 & 31;
      int var5 = var1 >> 15 & 31;
      long var6 = (long)var1 >> 20;
      qt.Av var8 = null;
      switch (var2) {
         case 0:
            var8 = qt.WR[var3];
            break;
         case 1:
            if (var3 == 2) {
               var8 = qt.LA;
            } else if (var3 == 3) {
               var8 = qt.CK;
            }
            break;
         case 4:
            if (var3 != 1 && var3 != 5) {
               var8 = qt.mv[var3];
            } else if (this.pC()) {
               if (var3 == 1 && var6 >> 5 == 0L) {
                  var8 = qt.sO;
               } else if (var3 == 5 && var6 >> 5 == 0L) {
                  var8 = qt.os;
               } else {
                  if (var3 != 5 || var6 >> 5 != 32L) {
                     throw new RuntimeException();
                  }

                  var8 = qt.Cu;
               }

               var6 &= 31L;
            } else {
               if (!this.wS()) {
                  break;
               }

               if (var3 == 1 && var6 >> 6 == 0L) {
                  var8 = qt.sO;
               } else if (var3 == 5 && var6 >> 6 == 0L) {
                  var8 = qt.os;
               } else {
                  if (var3 != 5 || var6 >> 6 != 16L) {
                     throw new RuntimeException();
                  }

                  var8 = qt.Cu;
               }

               var6 &= 63L;
            }
            break;
         case 6:
            if (var3 != 1 && var3 != 5) {
               var8 = qt.xM[var3];
               break;
            }

            if (var3 == 1 && var6 >> 5 == 0L) {
               var8 = qt.kU;
            } else if (var3 == 5 && var6 >> 5 == 0L) {
               var8 = qt.Kq;
            } else {
               if (var3 != 5 || var6 >> 5 != 32L) {
                  throw new RuntimeException();
               }

               var8 = qt.go;
            }

            var6 &= 31L;
            break;
         case 25:
            if (var3 == 0) {
               var8 = qt.cX;
            }
            break;
         default:
            throw new RuntimeException();
      }

      return this.pC(var1, var8, var4, var5, var6, 0L);
   }

   yt kS(int var1, int var2) {
      Assert.a(var2 == 3);
      int var3 = var1 >> 12 & 7;
      int var4 = var1 >> 7 & 31;
      int var5 = var1 >> 15 & 31;
      qt.Av var6 = null;
      int var7 = 0;
      int var8 = 0;
      if (var3 == 0) {
         if (var5 == 0 && var4 == 0) {
            var7 = var1 >> 20 & 15;
            var8 = var1 >> 24 & 15;
            int var9 = var1 >> 28 & 15;
            if (var9 == 0) {
               var6 = qt.A;
            } else if (var9 == 8) {
               var6 = qt.kS;
            }
         }
      } else if (var3 == 1) {
         var6 = qt.DL;
      }

      return this.pC(var1, var6, var8, var7, 0L, 0L);
   }

   yt wS(int var1, int var2) {
      Assert.a(var2 == 28);
      int var3 = var1 >> 12 & 7;
      int var4 = var1 >> 7 & 31;
      int var5 = var1 >> 15 & 31;
      long var6 = var1 >>> 20;
      qt.Av var8 = null;
      if (var3 == 0 && var5 == 0 && var4 == 0) {
         if (var6 == 0L) {
            var8 = qt.wS;
         } else if (var6 == 1L) {
            var8 = qt.UT;
         }
      } else if (var3 != 1 && var3 != 2 && var3 != 3) {
         if (var3 == 5 || var3 == 6 || var3 == 7) {
            if (var3 == 5) {
               var8 = qt.KV;
            } else if (var3 == 6) {
               var8 = qt.FK;
            } else if (var3 == 7) {
               var8 = qt.Bi;
            }
         }
      } else if (var3 == 1) {
         var8 = qt.UH;
      } else if (var3 == 2) {
         var8 = qt.VD;
      } else if (var3 == 3) {
         var8 = qt.Xs;
      }

      return this.pC(var1, var8, var4, var6, var5, 0L);
   }

   yt UT(int var1, int var2) {
      int var3 = var1 >> 12 & 7;
      int var4 = var1 >> 15 & 31;
      int var5 = var1 >> 20 & 31;
      long var6 = (long)var1 >> 25 << 5 | var1 >> 7 & 31L;
      qt.Av var8 = null;
      if (var2 == 8) {
         var8 = qt.Sc[var3];
      } else if (var2 == 9) {
         if (var3 == 2) {
            var8 = qt.ve;
         } else if (var3 == 3) {
            var8 = qt.Eq;
         }
      }

      return this.pC(var1, var8, var5, var4, var6, 0L);
   }

   yt E(int var1, int var2) {
      Assert.a(var2 == 24);
      int var3 = var1 >> 12 & 7;
      int var4 = var1 >> 15 & 31;
      int var5 = var1 >> 20 & 31;
      long var6 = (long)var1 >> 31 << 12 | ((long)var1 >> 7 & 1L) << 11 | ((long)var1 >> 25 & 63L) << 5 | ((long)var1 >> 8 & 15L) << 1;
      qt.Av var8 = qt.Ek[var3];
      return this.pC(var1, var8, var4, var5, var6, 0L);
   }

   yt sY(int var1, int var2) {
      Assert.a(var2 == 27);
      int var3 = var1 >> 7 & 31;
      long var4 = (long)var1 >> 31 << 20 | ((long)var1 >> 12 & 255L) << 12 | ((long)var1 >> 20 & 1L) << 11 | ((long)var1 >> 21 & 1023L) << 1;
      qt.Av var6 = qt.PR;
      return this.pC(var1, var6, var3, var4, 0L, 0L);
   }

   yt ys(int var1, int var2) {
      Assert.a(var2 == 12 || var2 == 14);
      int var3 = var1 >> 12 & 7;
      int var4 = var1 >> 25 & 127;
      int var5 = var1 >> 7 & 31;
      int var6 = var1 >> 15 & 31;
      int var7 = var1 >> 20 & 31;
      Object var8 = null;

      return this.pC(var1, switch (var3) {
         case 0 -> {
            if (var4 == 0) {
               yield var2 == 12 ? qt.DQ : qt.JF;
            } else if (var4 == 32) {
               yield var2 == 12 ? qt.ZN : qt.Nq;
            } else if (var4 == 1) {
               yield var2 == 12 ? qt.wQ : qt.uE;
            }
         }
         case 1 -> {
            if (var4 == 0) {
               yield var2 == 12 ? qt.OB : qt.pg;
            } else if (var4 == 1) {
               yield var2 == 12 ? qt.PZ : null;
            }
         }
         case 2 -> {
            if (var4 == 0) {
               yield var2 == 12 ? qt.pF : null;
            } else if (var4 == 1) {
               yield var2 == 12 ? qt.Ip : null;
            }
         }
         case 3 -> {
            if (var4 == 0) {
               yield var2 == 12 ? qt.Bc : null;
            } else if (var4 == 1) {
               yield var2 == 12 ? qt.Fm : null;
            }
         }
         case 4 -> {
            if (var4 == 0) {
               yield var2 == 12 ? qt.OI : null;
            } else if (var4 == 1) {
               yield var2 == 12 ? qt.FM : qt.Um;
            }
         }
         case 5 -> {
            if (var4 == 0) {
               yield var2 == 12 ? qt.Bf : qt.gj;
            } else if (var4 == 32) {
               yield var2 == 12 ? qt.Pe : qt.ZD;
            } else if (var4 == 1) {
               yield var2 == 12 ? qt.Wn : qt.Ta;
            }
         }
         case 6 -> {
            if (var4 == 0) {
               yield var2 == 12 ? qt.ck : null;
            } else if (var4 == 1) {
               yield var2 == 12 ? qt.gy : qt.So;
            }
         }
         case 7 -> {
            if (var4 == 0) {
               yield var2 == 12 ? qt.RW : null;
            } else if (var4 == 1) {
               yield var2 == 12 ? qt.pt : qt.tH;
            }
         }
         default -> throw new RuntimeException();
      }, var5, var6, var7, 0L);
   }

   yt ld(int var1, int var2) {
      Assert.a(var2 == 11);
      int var3 = var1 >> 12 & 7;
      int var4 = var1 >> 27 & 31;
      int var5 = var1 >> 7 & 31;
      int var6 = var1 >> 15 & 31;
      int var7 = var1 >> 20 & 31;
      qt.Av[] var8;
      if (var3 == 2) {
         var8 = qt.Xh;
      } else {
         if (var3 != 3) {
            throw new RuntimeException();
         }

         var8 = qt.zK;
      }

      qt.Av var9 = var8[var4];
      if (var4 == 2 && var7 != 0) {
         var9 = null;
      }

      return this.pC(var1, var9, var5, var6, var7, 0L);
   }

   yt gp(int var1, int var2) {
      Assert.a(var2 == 20);
      int var3 = var1 >> 25 & 127;
      int var4 = var1 >> 12 & 7;
      int var5 = var1 >> 7 & 31;
      int var6 = var1 >> 15 & 31;
      int var7 = var1 >> 20 & 31;
      boolean var8 = (var3 & 1) == 0;
      Assert.a((var3 & 2) == 0);
      int var9 = var3 >> 2;
      qt.Av var10 = null;
      switch (var9) {
         case 0:
            var10 = var8 ? qt.kk : qt.ho;
            break;
         case 1:
            var10 = var8 ? qt.Rh : qt.VE;
            break;
         case 2:
            var10 = var8 ? qt.vv : qt.lt;
            break;
         case 3:
            var10 = var8 ? qt.fn : qt.uw;
            break;
         case 4:
            if (var4 == 0) {
               var10 = var8 ? qt.wF : qt.hM;
            } else if (var4 == 1) {
               var10 = var8 ? qt.hF : qt.MJ;
            } else if (var4 == 2) {
               var10 = var8 ? qt.FA : qt.OA;
            }
            break;
         case 5:
            if (var4 == 0) {
               var10 = var8 ? qt.IK : qt.kT;
            } else if (var4 == 1) {
               var10 = var8 ? qt.DM : qt.x;
            }
         case 6:
         case 7:
         case 9:
         case 10:
         case 12:
         case 13:
         case 14:
         case 15:
         case 16:
         case 17:
         case 18:
         case 19:
         case 21:
         case 22:
         case 23:
         case 25:
         case 27:
         case 29:
         default:
            break;
         case 8:
            if (var7 == 0) {
               var10 = var8 ? null : qt.JV;
            } else if (var7 == 1) {
               var10 = var8 ? qt.un : null;
            }
            break;
         case 11:
            if (var7 == 0) {
               var10 = var8 ? qt.AS : qt.QP;
            }
            break;
         case 20:
            if (var4 == 2) {
               var10 = var8 ? qt.kt : qt.Iq;
            } else if (var4 == 1) {
               var10 = var8 ? qt.Yw : qt.mV;
            } else if (var4 == 0) {
               var10 = var8 ? qt.uD : qt.Gh;
            }
            break;
         case 24:
            if (var7 == 0) {
               var10 = var8 ? qt.IQ : qt.yC;
            } else if (var7 == 1) {
               var10 = var8 ? qt.zR : qt.uu;
            } else if (var7 == 2) {
               var10 = var8 ? qt.kQ : qt.Is;
            } else if (var7 == 3) {
               var10 = var8 ? qt.te : qt.BP;
            }
            break;
         case 26:
            if (var7 == 0) {
               var10 = var8 ? qt.mK : qt.Tq;
            } else if (var7 == 1) {
               var10 = var8 ? qt.pW : qt.HO;
            } else if (var7 == 2) {
               var10 = var8 ? qt.B : qt.TP;
            } else if (var7 == 3) {
               var10 = var8 ? qt.RR : qt.gd;
            }
            break;
         case 28:
            if (var7 == 0 && var4 == 0) {
               var10 = var8 ? qt.Ft : qt.Wm;
            } else if (var7 == 0 && var4 == 1) {
               var10 = var8 ? qt.ZY : qt.HG;
            }
            break;
         case 30:
            if (var7 == 0 && var4 == 0) {
               var10 = var8 ? qt.Gg : qt.eI;
            }
      }

      return this.pC(var1, var10, var5, var6, var7, 0L);
   }

   yt oT(int var1, int var2) {
      int var3 = var1 >> 25 & 3;
      int var4 = var1 >> 7 & 31;
      int var5 = var1 >> 15 & 31;
      int var6 = var1 >> 20 & 31;
      int var7 = var1 >> 27 & 31;
      qt.Av var8 = null;
      if (var3 == 0 || var3 == 1) {
         if (var2 == 16) {
            var8 = var3 == 0 ? qt.yv : qt.y;
         } else if (var2 == 17) {
            var8 = var3 == 0 ? qt.MZ : qt.JP;
         } else if (var2 == 18) {
            var8 = var3 == 0 ? qt.fH : qt.jY;
         } else if (var2 == 19) {
            var8 = var3 == 0 ? qt.ET : qt.ee;
         }
      }

      return this.pC(var1, var8, var4, var5, var6, var7);
   }

   yt A(int var1) {
      yt var2 = null;
      int var3 = var1 & 3;
      int var4 = var1 >> 13 & 7;
      if (var3 == 0) {
         switch (var4) {
            case 0:
               var2 = this.UT(var1, var3, var4);
               break;
            case 1:
            case 2:
            case 3:
               var2 = this.E(var1, var3, var4);
            case 4:
               break;
            case 5:
            case 6:
            case 7:
               var2 = this.sY(var1, var3, var4);
               break;
            default:
               throw new RuntimeException();
         }
      } else if (var3 == 1) {
         switch (var4) {
            case 0:
               var2 = this.pC(var1, var3, var4);
               break;
            case 1:
               if (this.pC()) {
                  var2 = this.kS(var1, var3, var4);
               } else if (this.wS()) {
                  var2 = this.pC(var1, var3, var4);
               }
               break;
            case 2:
            case 3:
               var2 = this.pC(var1, var3, var4);
               break;
            case 4:
               var2 = this.A(var1, var3, var4);
               break;
            case 5:
               var2 = this.kS(var1, var3, var4);
               break;
            case 6:
            case 7:
               var2 = this.wS(var1, var3, var4);
               break;
            default:
               throw new RuntimeException();
         }
      } else {
         Assert.a(var3 == 2);
         switch (var4) {
            case 0:
            case 1:
            case 2:
            case 3:
               var2 = this.pC(var1, var3, var4);
               break;
            case 4:
               var2 = this.ys(var1, var3, var4);
               break;
            case 5:
            case 6:
            case 7:
               var2 = this.ld(var1, var3, var4);
               break;
            default:
               throw new RuntimeException();
         }
      }

      return var2;
   }

   yt pC(int var1, int var2, int var3) {
      int var4 = var1 >> 7 & 31;
      int var5 = var4;
      int var6 = 0;
      qt.Av var7 = null;
      if (var2 == 1) {
         var6 = var1 >>> 12 << 31 >> 26 | var1 >> 2 & 31;
         if (var3 == 0) {
            if (var4 == 0) {
               if (var6 == 0) {
                  var7 = qt.AQ;
               }
            } else if (var6 != 0) {
               var7 = qt.hK;
            }
         } else if (var3 == 1) {
            Assert.a(this.wS());
            if (var4 != 0) {
               var7 = qt.e;
            }
         } else if (var3 == 2) {
            if (var4 != 0) {
               var7 = qt.BX;
            }
         } else if (var3 == 3) {
            if (var4 == 2) {
               var6 = var1 >>> 12 << 31 >> 22 | (var1 >> 2 & 1) << 5 | (var1 >> 3 & 3) << 7 | (var1 >> 5 & 1) << 6 | (var1 >> 6 & 1) << 4;
               var7 = qt.NN;
               return this.pC(var1, var7, var6);
            }

            if (var4 != 0 && var4 != 2) {
               var7 = qt.hZ;
               var6 = var1 >>> 12 << 31 >> 14 | (var1 >> 2 & 31) << 12;
            }
         }
      } else if (var2 == 2) {
         if (var3 == 0) {
            if (var4 != 0) {
               if (this.kS()) {
                  if ((var6 & 32) == 0) {
                     var7 = qt.sO;
                     var6 = (var1 >> 12 & 1) << 5 | var1 >> 2 & 31;
                  }
               } else if (this.A()) {
               }
            }
         } else if (var3 == 1) {
            if (this.kS()) {
               var7 = qt.Mi;
               var5 = 2;
               var6 = wS(var1);
            } else if (this.A()) {
            }
         } else if (var3 == 2 || var3 == 3) {
            if (var3 == 2) {
               if (var4 != 0) {
                  var7 = qt.Ig;
                  var5 = 2;
                  var6 = kS(var1);
               }
            } else if (this.pC()) {
               var7 = qt.Op;
               var5 = 2;
               var6 = kS(var1);
            } else if (this.wS()) {
               var7 = qt.TV;
               var5 = 2;
               var6 = wS(var1);
            }
         }
      }

      return var7.kS == 3 ? this.pC(var1, var7, var4, var5, var6) : this.pC(var1, var7, var4, var6);
   }

   static final int kS(int var0) {
      return (var0 >> 4 & 7) << 2 | (var0 >> 12 & 1) << 5 | (var0 >> 2 & 3) << 6;
   }

   static final int wS(int var0) {
      return (var0 >> 5 & 3) << 3 | (var0 >> 12 & 1) << 5 | (var0 >> 2 & 7) << 6;
   }

   yt A(int var1, int var2, int var3) {
      Assert.a(var2 == 1 && var3 == 4);
      int var4 = 8 | var1 >> 7 & 7;
      int var5 = var1 >> 2 & 31 | (var1 >> 12 & 1) << 5;
      int var6 = var1 >> 10 & 3;
      int var7 = var1 >> 12 & 1;
      qt.Av var8 = null;
      if (var6 == 0) {
         if (this.pC() && var7 != 0) {
            return null;
         }

         if (var5 != 0) {
            var8 = qt.os;
         }
      } else if (var6 == 1) {
         if (this.pC() && var7 != 0) {
            return null;
         }

         if (var5 != 0) {
            var8 = qt.Cu;
         }
      } else if (var6 == 2) {
         var5 = var1 >>> 12 << 31 >> 26 | var1 >> 2 & 31;
         var8 = qt.LM;
      } else {
         Assert.a(var6 == 3);
         int var9 = 8 | var1 >> 2 & 7;
         int var10 = var1 >> 5 & 3;
         if (var7 == 0) {
            if (var10 == 0) {
               var8 = qt.ZN;
            } else if (var10 == 1) {
               var8 = qt.OI;
            } else if (var10 == 2) {
               var8 = qt.ck;
            } else {
               var8 = qt.RW;
            }
         } else if (var10 == 0) {
            var8 = qt.Nq;
         } else if (var10 == 1) {
            var8 = qt.JF;
         }

         var5 = var9;
      }

      return this.pC(var1, var8, var4, var4, var5, 0L);
   }

   yt kS(int var1, int var2, int var3) {
      long var4 = var1 >>> 12 << 31 >> 20
         | (var1 >> 2 & 1) << 5
         | (var1 >> 3 & 7) << 1
         | (var1 >> 6 & 1) << 7
         | (var1 >> 7 & 1) << 6
         | (var1 >> 8 & 1) << 10
         | (var1 >> 9 & 3) << 8
         | (var1 >> 11 & 1) << 4;
      qt.Av var6 = null;
      if (var2 == 1 && var3 == 1) {
         var6 = qt.PR;
      } else if (var2 == 1 && var3 == 5) {
         var6 = qt.aK;
      }

      return var6 == qt.aK ? this.pC(var1, var6, var4, 0L, 0L, 0L) : this.pC(var1, var6, 1L, var4, 0L, 0L);
   }

   yt wS(int var1, int var2, int var3) {
      int var4 = 8 | var1 >> 7 & 7;
      long var5 = var1 >>> 12 << 31 >> 23 | (var1 >> 5 & 3) << 6 | (var1 >> 2 & 1) << 5 | (var1 >> 10 & 3) << 3 | (var1 >> 3 & 3) << 1;
      qt.Av var7 = null;
      if (var2 == 1 && var3 == 6) {
         var7 = qt.np;
      } else if (var2 == 1 && var3 == 7) {
         var7 = qt.ik;
      }

      return this.pC(var1, var7, var4, var5, 0L, 0L);
   }

   yt UT(int var1, int var2, int var3) {
      int var4 = 8 | var1 >> 2 & 7;
      long var5 = (var1 >> 6 & 1) << 2 | (var1 >> 5 & 1) << 3 | (var1 >> 11 & 3) << 4 | (var1 >> 7 & 15) << 6;
      qt.Av var7 = null;
      if (var2 == 0 && var3 == 0 && var5 != 0L) {
         var7 = qt.hK;
      }

      return this.pC(var1, var7, var4, 2L, var5, 0L);
   }

   yt E(int var1, int var2, int var3) {
      int var4 = 8 | var1 >> 2 & 7;
      int var5 = 8 | var1 >> 7 & 7;
      long var6 = (var1 >> 10 & 7) << 3 | (var1 >> 5 & 3) << 6;
      long var8 = (var1 >> 6 & 1) << 2 | (var1 >> 10 & 7) << 3 | (var1 >> 5 & 1) << 6;
      qt.Av var10 = null;
      if (var2 == 0) {
         if (var3 == 1) {
            if (this.kS()) {
               var10 = qt.CK;
            } else if (this.A()) {
            }
         } else if (var3 == 2) {
            var6 = var8;
            var10 = qt.ys;
         } else if (var3 == 3) {
            if (this.pC()) {
               var6 = var8;
               var10 = qt.LA;
            } else if (this.wS()) {
               var10 = qt.fI;
            }
         }
      }

      return this.pC(var1, var10, var4, var5, var6, 0L);
   }

   yt sY(int var1, int var2, int var3) {
      int var4 = 8 | var1 >> 2 & 7;
      int var5 = 8 | var1 >> 7 & 7;
      long var6 = (var1 >> 10 & 7) << 3 | (var1 >> 5 & 3) << 6;
      long var8 = (var1 >> 6 & 1) << 2 | (var1 >> 10 & 7) << 3 | (var1 >> 5 & 1) << 6;
      qt.Av var10 = null;
      if (var2 == 0) {
         if (var3 == 5) {
            if (this.kS()) {
               var10 = qt.Eq;
            } else if (this.A()) {
            }
         } else if (var3 == 6) {
            var6 = var8;
            var10 = qt.xC;
         } else if (var3 == 7) {
            if (this.pC()) {
               var6 = var8;
               var10 = qt.ve;
            } else if (this.wS()) {
               var10 = qt.ED;
            }
         }
      }

      return this.pC(var1, var10, var4, var5, var6, 0L);
   }

   yt ys(int var1, int var2, int var3) {
      int var4 = var1 >> 12 & 1;
      int var5 = var1 >> 7 & 31;
      int var6 = var1 >> 2 & 31;
      qt.Av var7 = null;
      if (var2 == 2 && var3 == 4) {
         if (var4 == 0) {
            if (var6 == 0) {
               var7 = qt.gR;
            } else {
               var7 = qt.xg;
            }
         } else if (var4 == 1) {
            if (var5 == 0 && var6 == 0) {
               var7 = qt.UT;
            } else {
               if (var5 != 0 && var6 == 0) {
                  var7 = qt.cX;
                  return this.pC(var1, var7, 1L, var5, 0L);
               }

               if (var5 != 0 && var6 != 0) {
                  var7 = qt.DQ;
                  return this.pC(var1, var7, var5, var5, var6);
               }
            }
         }
      }

      return this.pC(var1, var7, var5, var6);
   }

   yt ld(int var1, int var2, int var3) {
      int var4 = var1 >> 2 & 31;
      qt.Av var5 = null;
      int var6 = 0;
      if (var2 == 2) {
         if (var3 == 5) {
            if (this.kS()) {
               var6 = UT(var1);
               var5 = qt.TD;
            } else if (this.A()) {
               var6 = E(var1);
               var5 = qt.Tr;
            }
         } else if (var3 == 6) {
            var6 = sY(var1);
            var5 = qt.pY;
         }

         if (var3 == 7) {
            if (this.pC()) {
               var6 = sY(var1);
               var5 = qt.yB;
            } else if (this.wS()) {
               var6 = UT(var1);
               var5 = qt.l;
            }
         }
      }

      return this.pC(var1, var5, var4, var6, 0L, 0L);
   }

   static final int UT(int var0) {
      return (var0 >> 10 & 7) << 3 | (var0 >> 7 & 7) << 6;
   }

   static final int E(int var0) {
      return (var0 >> 11 & 3) << 4 | (var0 >> 7 & 15) << 6;
   }

   static final int sY(int var0) {
      return (var0 >> 9 & 15) << 2 | (var0 >> 7 & 3) << 6;
   }

   void pC(qt.Av var1) {
      Assert.a(var1 != null);
      Assert.a((this.A & 15) >= (var1.A & 15));
   }

   yt pC(int var1, qt.Av var2, RC... var3) {
      int var4 = 0;

      for (RC var8 : var3) {
         if (var8 == null) {
            break;
         }

         var4++;
      }

      return new yt((var1 & 3) == 3 ? this.ys(var1) : this.ld(var1), var2, (RC[])Arrays.copyOf((Object[])var3, var4), this.getMode());
   }

   RC pC(long var1, boolean var3) {
      long var4;
      if (var3) {
         var4 = RegisterUtil.createPureRegisterId((int)var1, 2);
      } else {
         var4 = var1;
      }

      return new RC(0, this.getGPRegisterBitsize(), var4);
   }

   RC pC(long var1) {
      return new RC(0, this.getGPRegisterBitsize(), var1);
   }

   RC A(long var1) {
      return new RC(1, this.getGPRegisterBitsize(), var1);
   }

   RC kS(long var1) {
      return new RC(9, this.getGPRegisterBitsize(), var1);
   }

   RC wS(long var1) {
      return new RC(3, this.getGPRegisterBitsize(), var1);
   }

   yt pC(int var1, qt.Av var2, long var3) {
      return this.pC(var1, var2, var3, 0L, 0L, 0L);
   }

   yt pC(int var1, qt.Av var2, long var3, long var5) {
      return this.pC(var1, var2, var3, var5, 0L, 0L);
   }

   yt pC(int var1, qt.Av var2, long var3, long var5, long var7) {
      return this.pC(var1, var2, var3, var5, var7, 0L);
   }

   yt pC(int var1, qt.Av var2, long var3, long var5, long var7, long var9) {
      this.pC(var2);
      RC var11 = this.pC(var2.wS & 15, var3);
      RC var12 = this.pC(var2.wS >> 4 & 15, var5);
      RC var13 = this.pC(var2.wS >> 8 & 15, var7);
      RC var14 = this.pC(var2.wS >> 12 & 15, var9);
      return this.pC(var1, var2, var11, var12, var13, var14);
   }

   RC pC(int var1, long var2) {
      switch (var1) {
         case 1:
            return this.A(var2);
         case 2:
            return this.kS(var2);
         case 3:
            return this.wS(var2);
         case 4:
            return this.pC(var2);
         case 5:
            return this.pC(var2, true);
         default:
            return null;
      }
   }

   BytesBlock ys(int var1) {
      byte[] var2 = new byte[]{(byte)(var1 & 0xFF), (byte)(var1 >> 8 & 0xFF), (byte)(var1 >> 16 & 0xFF), (byte)(var1 >> 24 & 0xFF)};
      return new BytesBlock(var2, Endianness.LITTLE_ENDIAN, 2);
   }

   BytesBlock ld(int var1) {
      byte[] var2 = new byte[]{(byte)(var1 & 0xFF), (byte)(var1 >> 8 & 0xFF)};
      return new BytesBlock(var2, Endianness.LITTLE_ENDIAN, 2);
   }

   @Override
   public String getRegisterName(long var1) {
      return this.A(var1, false);
   }

   public String A(long var1, boolean var3) {
      IRegisterBank var4 = this.getRegisterBank();
      RegisterDescriptionEntry var5 = var4.getDescriptionEntryById(var1);
      if (var5 != null) {
         if (var3) {
            List var6 = var5.getNames();
            if (var6.size() >= 2) {
               return (String)var6.get(1);
            }
         }

         return var5.getName();
      } else {
         return null;
      }
   }
}
