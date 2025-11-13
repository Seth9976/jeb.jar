package com.pnfsoftware.jebglobal;

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
public class cno extends AbstractProcessor {
   private static final ILogger Dw = GlobalLog.getLogger(cno.class);
   static final int q = 0;
   static final int RF = 1;
   static final int xK = 2;
   @SerId(1)
   private int Uv;

   public cno(int var1) {
      super(4, var1, Endianness.LITTLE_ENDIAN, 2);

      this.q(switch (var1) {
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

   public void q(int var1) {
      this.Uv = var1;
   }

   public int q() {
      return this.Uv;
   }

   @Override
   public ProcessorType getType() {
      switch (this.mode) {
         case 32:
            return cnn.RF;
         case 64:
            return cnn.xK;
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
            return cnp.Dw;
         case 64:
            return cnp.Uv;
         default:
            throw new RuntimeException("Unsupported");
      }
   }

   @Override
   public boolean isRISC() {
      return true;
   }

   boolean RF() {
      return this.mode == 32;
   }

   boolean xK() {
      return this.mode == 64;
   }

   boolean Dw() {
      return this.mode == 128;
   }

   boolean Uv() {
      return this.mode == 32 || this.mode == 64;
   }

   boolean oW() {
      return this.mode == 64 || this.mode == 128;
   }

   protected cnl q(byte[] var1, int var2, int var3) throws ProcessorException {
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

         cnl var7;
         if (var5 == 3) {
            int var8 = var4 >> 2 & 31;
            switch (var8) {
               case 0:
               case 1:
                  var7 = this.RF(var6, var8);
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
                  var7 = this.xK(var6, var8);
                  break;
               case 4:
               case 6:
                  var7 = this.RF(var6, var8);
                  break;
               case 5:
               case 13:
                  var7 = this.q(var6, var8);
                  break;
               case 8:
               case 9:
                  var7 = this.Uv(var6, var8);
                  break;
               case 11:
                  var7 = this.gP(var6, var8);
                  break;
               case 12:
               case 14:
                  var7 = this.nf(var6, var8);
                  break;
               case 16:
               case 17:
               case 18:
               case 19:
                  var7 = this.lm(var6, var8);
                  break;
               case 20:
                  var7 = this.za(var6, var8);
                  break;
               case 24:
                  var7 = this.oW(var6, var8);
                  break;
               case 25:
                  var7 = this.RF(var6, var8);
                  break;
               case 27:
                  var7 = this.gO(var6, var8);
                  break;
               case 28:
                  var7 = this.Dw(var6, var8);
            }
         } else {
            var7 = this.xK(var6 & 65535);
         }

         Assert.a(var7 != null);
         return var7;
      }
   }

   int RF(int var1) {
      return var1 >> 12 & 7;
   }

   cnl q(int var1, int var2) {
      Assert.a(var2 == 13 || var2 == 5);
      int var3 = var1 >> 7 & 31;
      long var4 = var1 & -4096L;
      var4 >>= 12;
      cnq.eo var6 = var2 == 13 ? cnq.Sz : cnq.fq;
      return this.q(var1, var6, var3, var4, 0L, 0L);
   }

   cnl RF(int var1, int var2) {
      int var3 = var1 >> 12 & 7;
      int var4 = var1 >> 7 & 31;
      int var5 = var1 >> 15 & 31;
      long var6 = (long)var1 >> 20;
      cnq.eo var8 = null;
      switch (var2) {
         case 0:
            var8 = cnq.kf[var3];
            break;
         case 1:
            if (var3 == 2) {
               var8 = cnq.wS;
            } else if (var3 == 3) {
               var8 = cnq.GO;
            }
            break;
         case 4:
            if (var3 != 1 && var3 != 5) {
               var8 = cnq.os[var3];
            } else if (this.RF()) {
               if (var3 == 1 && var6 >> 5 == 0L) {
                  var8 = cnq.iu;
               } else if (var3 == 5 && var6 >> 5 == 0L) {
                  var8 = cnq.fn;
               } else {
                  if (var3 != 5 || var6 >> 5 != 32L) {
                     throw new RuntimeException();
                  }

                  var8 = cnq.ZU;
               }

               var6 &= 31L;
            } else {
               if (!this.oW()) {
                  break;
               }

               if (var3 == 1 && var6 >> 6 == 0L) {
                  var8 = cnq.iu;
               } else if (var3 == 5 && var6 >> 6 == 0L) {
                  var8 = cnq.fn;
               } else {
                  if (var3 != 5 || var6 >> 6 != 16L) {
                     throw new RuntimeException();
                  }

                  var8 = cnq.ZU;
               }

               var6 &= 63L;
            }
            break;
         case 6:
            if (var3 != 1 && var3 != 5) {
               var8 = cnq.kv[var3];
               break;
            }

            if (var3 == 1 && var6 >> 5 == 0L) {
               var8 = cnq.oS;
            } else if (var3 == 5 && var6 >> 5 == 0L) {
               var8 = cnq.FG;
            } else {
               if (var3 != 5 || var6 >> 5 != 32L) {
                  throw new RuntimeException();
               }

               var8 = cnq.Al;
            }

            var6 &= 31L;
            break;
         case 25:
            if (var3 == 0) {
               var8 = cnq.Bs;
            }
            break;
         default:
            throw new RuntimeException();
      }

      return this.q(var1, var8, var4, var5, var6, 0L);
   }

   cnl xK(int var1, int var2) {
      Assert.a(var2 == 3);
      int var3 = var1 >> 12 & 7;
      int var4 = var1 >> 7 & 31;
      int var5 = var1 >> 15 & 31;
      cnq.eo var6 = null;
      int var7 = 0;
      int var8 = 0;
      if (var3 == 0) {
         if (var5 == 0 && var4 == 0) {
            var7 = var1 >> 20 & 15;
            var8 = var1 >> 24 & 15;
            int var9 = var1 >> 28 & 15;
            if (var9 == 0) {
               var6 = cnq.ZA;
            } else if (var9 == 8) {
               var6 = cnq.Ov;
            }
         }
      } else if (var3 == 1) {
         var6 = cnq.wN;
      }

      return this.q(var1, var6, var8, var7, 0L, 0L);
   }

   cnl Dw(int var1, int var2) {
      Assert.a(var2 == 28);
      int var3 = var1 >> 12 & 7;
      int var4 = var1 >> 7 & 31;
      int var5 = var1 >> 15 & 31;
      long var6 = var1 >>> 20;
      cnq.eo var8 = null;
      if (var3 == 0 && var5 == 0 && var4 == 0) {
         if (var6 == 0L) {
            var8 = cnq.Lj;
         } else if (var6 == 1L) {
            var8 = cnq.nv;
         }
      } else if (var3 != 1 && var3 != 2 && var3 != 3) {
         if (var3 == 5 || var3 == 6 || var3 == 7) {
            if (var3 == 5) {
               var8 = cnq.hw;
            } else if (var3 == 6) {
               var8 = cnq.gm;
            } else if (var3 == 7) {
               var8 = cnq.uY;
            }
         }
      } else if (var3 == 1) {
         var8 = cnq.Uc;
      } else if (var3 == 2) {
         var8 = cnq.TB;
      } else if (var3 == 3) {
         var8 = cnq.dg;
      }

      return this.q(var1, var8, var4, var6, var5, 0L);
   }

   cnl Uv(int var1, int var2) {
      int var3 = var1 >> 12 & 7;
      int var4 = var1 >> 15 & 31;
      int var5 = var1 >> 20 & 31;
      long var6 = (long)var1 >> 25 << 5 | var1 >> 7 & 31L;
      cnq.eo var8 = null;
      if (var2 == 8) {
         var8 = cnq.qR[var3];
      } else if (var2 == 9) {
         if (var3 == 2) {
            var8 = cnq.Oz;
         } else if (var3 == 3) {
            var8 = cnq.QZ;
         }
      }

      return this.q(var1, var8, var5, var4, var6, 0L);
   }

   cnl oW(int var1, int var2) {
      Assert.a(var2 == 24);
      int var3 = var1 >> 12 & 7;
      int var4 = var1 >> 15 & 31;
      int var5 = var1 >> 20 & 31;
      long var6 = (long)var1 >> 31 << 12 | ((long)var1 >> 7 & 1L) << 11 | ((long)var1 >> 25 & 63L) << 5 | ((long)var1 >> 8 & 15L) << 1;
      cnq.eo var8 = cnq.eC[var3];
      return this.q(var1, var8, var4, var5, var6, 0L);
   }

   cnl gO(int var1, int var2) {
      Assert.a(var2 == 27);
      int var3 = var1 >> 7 & 31;
      long var4 = (long)var1 >> 31 << 20 | ((long)var1 >> 12 & 255L) << 12 | ((long)var1 >> 20 & 1L) << 11 | ((long)var1 >> 21 & 1023L) << 1;
      cnq.eo var6 = cnq.mJ;
      return this.q(var1, var6, var3, var4, 0L, 0L);
   }

   cnl nf(int var1, int var2) {
      Assert.a(var2 == 12 || var2 == 14);
      int var3 = var1 >> 12 & 7;
      int var4 = var1 >> 25 & 127;
      int var5 = var1 >> 7 & 31;
      int var6 = var1 >> 15 & 31;
      int var7 = var1 >> 20 & 31;
      Object var8 = null;

      return this.q(var1, switch (var3) {
         case 0 -> {
            if (var4 == 0) {
               yield var2 == 12 ? cnq.rV : cnq.Kn;
            } else if (var4 == 32) {
               yield var2 == 12 ? cnq.WX : cnq.vh;
            } else if (var4 == 1) {
               yield var2 == 12 ? cnq.sc : cnq.Ag;
            }
         }
         case 1 -> {
            if (var4 == 0) {
               yield var2 == 12 ? cnq.CB : cnq.Rd;
            } else if (var4 == 1) {
               yield var2 == 12 ? cnq.wQ : null;
            }
         }
         case 2 -> {
            if (var4 == 0) {
               yield var2 == 12 ? cnq.C : null;
            } else if (var4 == 1) {
               yield var2 == 12 ? cnq.Oj : null;
            }
         }
         case 3 -> {
            if (var4 == 0) {
               yield var2 == 12 ? cnq.GC : null;
            } else if (var4 == 1) {
               yield var2 == 12 ? cnq.VW : null;
            }
         }
         case 4 -> {
            if (var4 == 0) {
               yield var2 == 12 ? cnq.KF : null;
            } else if (var4 == 1) {
               yield var2 == 12 ? cnq.ap : cnq.rp;
            }
         }
         case 5 -> {
            if (var4 == 0) {
               yield var2 == 12 ? cnq.rk : cnq.Eq;
            } else if (var4 == 32) {
               yield var2 == 12 ? cnq.cy : cnq.hP;
            } else if (var4 == 1) {
               yield var2 == 12 ? cnq.RL : cnq.CW;
            }
         }
         case 6 -> {
            if (var4 == 0) {
               yield var2 == 12 ? cnq.jk : null;
            } else if (var4 == 1) {
               yield var2 == 12 ? cnq.hy : cnq.qm;
            }
         }
         case 7 -> {
            if (var4 == 0) {
               yield var2 == 12 ? cnq.Cl : null;
            } else if (var4 == 1) {
               yield var2 == 12 ? cnq.Xi : cnq.LR;
            }
         }
         default -> throw new RuntimeException();
      }, var5, var6, var7, 0L);
   }

   cnl gP(int var1, int var2) {
      Assert.a(var2 == 11);
      int var3 = var1 >> 12 & 7;
      int var4 = var1 >> 27 & 31;
      int var5 = var1 >> 7 & 31;
      int var6 = var1 >> 15 & 31;
      int var7 = var1 >> 20 & 31;
      cnq.eo[] var8;
      if (var3 == 2) {
         var8 = cnq.pe;
      } else {
         if (var3 != 3) {
            throw new RuntimeException();
         }

         var8 = cnq.xG;
      }

      cnq.eo var9 = var8[var4];
      if (var4 == 2 && var7 != 0) {
         var9 = null;
      }

      return this.q(var1, var9, var5, var6, var7, 0L);
   }

   cnl za(int var1, int var2) {
      Assert.a(var2 == 20);
      int var3 = var1 >> 25 & 127;
      int var4 = var1 >> 12 & 7;
      int var5 = var1 >> 7 & 31;
      int var6 = var1 >> 15 & 31;
      int var7 = var1 >> 20 & 31;
      boolean var8 = (var3 & 1) == 0;
      Assert.a((var3 & 2) == 0);
      int var9 = var3 >> 2;
      cnq.eo var10 = null;
      switch (var9) {
         case 0:
            var10 = var8 ? cnq.tX : cnq.sa;
            break;
         case 1:
            var10 = var8 ? cnq.Qt : cnq.WJ;
            break;
         case 2:
            var10 = var8 ? cnq.JW : cnq.pL;
            break;
         case 3:
            var10 = var8 ? cnq.Ub : cnq.aH;
            break;
         case 4:
            if (var4 == 0) {
               var10 = var8 ? cnq.yW : cnq.eb;
            } else if (var4 == 1) {
               var10 = var8 ? cnq.JF : cnq.zj;
            } else if (var4 == 2) {
               var10 = var8 ? cnq.uz : cnq.aV;
            }
            break;
         case 5:
            if (var4 == 0) {
               var10 = var8 ? cnq.Xz : cnq.Qo;
            } else if (var4 == 1) {
               var10 = var8 ? cnq.iK : cnq.lN;
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
               var10 = var8 ? null : cnq.qr;
            } else if (var7 == 1) {
               var10 = var8 ? cnq.gT : null;
            }
            break;
         case 11:
            if (var7 == 0) {
               var10 = var8 ? cnq.tb : cnq.yc;
            }
            break;
         case 20:
            if (var4 == 2) {
               var10 = var8 ? cnq.Qe : cnq.IJ;
            } else if (var4 == 1) {
               var10 = var8 ? cnq.dW : cnq.Of;
            } else if (var4 == 0) {
               var10 = var8 ? cnq.HK : cnq.AN;
            }
            break;
         case 24:
            if (var7 == 0) {
               var10 = var8 ? cnq.ZE : cnq.YR;
            } else if (var7 == 1) {
               var10 = var8 ? cnq.Jh : cnq.fN;
            } else if (var7 == 2) {
               var10 = var8 ? cnq.AG : cnq.fK;
            } else if (var7 == 3) {
               var10 = var8 ? cnq.er : cnq.ou;
            }
            break;
         case 26:
            if (var7 == 0) {
               var10 = var8 ? cnq.fe : cnq.GH;
            } else if (var7 == 1) {
               var10 = var8 ? cnq.Kl : cnq.BY;
            } else if (var7 == 2) {
               var10 = var8 ? cnq.SM : cnq.lA;
            } else if (var7 == 3) {
               var10 = var8 ? cnq.bj : cnq.yu;
            }
            break;
         case 28:
            if (var7 == 0 && var4 == 0) {
               var10 = var8 ? cnq.iO : cnq.DP;
            } else if (var7 == 0 && var4 == 1) {
               var10 = var8 ? cnq.uw : cnq.RW;
            }
            break;
         case 30:
            if (var7 == 0 && var4 == 0) {
               var10 = var8 ? cnq.So : cnq.lz;
            }
      }

      return this.q(var1, var10, var5, var6, var7, 0L);
   }

   cnl lm(int var1, int var2) {
      int var3 = var1 >> 25 & 3;
      int var4 = var1 >> 7 & 31;
      int var5 = var1 >> 15 & 31;
      int var6 = var1 >> 20 & 31;
      int var7 = var1 >> 27 & 31;
      cnq.eo var8 = null;
      if (var3 == 0 || var3 == 1) {
         if (var2 == 16) {
            var8 = var3 == 0 ? cnq.yn : cnq.Up;
         } else if (var2 == 17) {
            var8 = var3 == 0 ? cnq.es : cnq.HO;
         } else if (var2 == 18) {
            var8 = var3 == 0 ? cnq.o : cnq.cv;
         } else if (var2 == 19) {
            var8 = var3 == 0 ? cnq.gl : cnq.lk;
         }
      }

      return this.q(var1, var8, var4, var5, var6, var7);
   }

   cnl xK(int var1) {
      cnl var2 = null;
      int var3 = var1 & 3;
      int var4 = var1 >> 13 & 7;
      if (var3 == 0) {
         switch (var4) {
            case 0:
               var2 = this.Uv(var1, var3, var4);
               break;
            case 1:
            case 2:
            case 3:
               var2 = this.oW(var1, var3, var4);
            case 4:
               break;
            case 5:
            case 6:
            case 7:
               var2 = this.gO(var1, var3, var4);
               break;
            default:
               throw new RuntimeException();
         }
      } else if (var3 == 1) {
         switch (var4) {
            case 0:
               var2 = this.q(var1, var3, var4);
               break;
            case 1:
               if (this.RF()) {
                  var2 = this.xK(var1, var3, var4);
               } else if (this.oW()) {
                  var2 = this.q(var1, var3, var4);
               }
               break;
            case 2:
            case 3:
               var2 = this.q(var1, var3, var4);
               break;
            case 4:
               var2 = this.RF(var1, var3, var4);
               break;
            case 5:
               var2 = this.xK(var1, var3, var4);
               break;
            case 6:
            case 7:
               var2 = this.Dw(var1, var3, var4);
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
               var2 = this.q(var1, var3, var4);
               break;
            case 4:
               var2 = this.nf(var1, var3, var4);
               break;
            case 5:
            case 6:
            case 7:
               var2 = this.gP(var1, var3, var4);
               break;
            default:
               throw new RuntimeException();
         }
      }

      return var2;
   }

   cnl q(int var1, int var2, int var3) {
      int var4 = var1 >> 7 & 31;
      int var5 = var4;
      int var6 = 0;
      cnq.eo var7 = null;
      if (var2 == 1) {
         var6 = var1 >>> 12 << 31 >> 26 | var1 >> 2 & 31;
         if (var3 == 0) {
            if (var4 == 0) {
               if (var6 == 0) {
                  var7 = cnq.YT;
               }
            } else if (var6 != 0) {
               var7 = cnq.ND;
            }
         } else if (var3 == 1) {
            Assert.a(this.oW());
            if (var4 != 0) {
               var7 = cnq.hM;
            }
         } else if (var3 == 2) {
            if (var4 != 0) {
               var7 = cnq.AY;
            }
         } else if (var3 == 3) {
            if (var4 == 2) {
               var6 = var1 >>> 12 << 31 >> 22 | (var1 >> 2 & 1) << 5 | (var1 >> 3 & 3) << 7 | (var1 >> 5 & 1) << 6 | (var1 >> 6 & 1) << 4;
               var7 = cnq.XV;
               return this.q(var1, var7, var6);
            }

            if (var4 != 0 && var4 != 2) {
               var7 = cnq.Sz;
               var6 = var1 >>> 12 << 31 >> 14 | (var1 >> 2 & 31) << 12;
            }
         }
      } else if (var2 == 2) {
         if (var3 == 0) {
            if (var4 != 0) {
               if (this.Uv()) {
                  if ((var6 & 32) == 0) {
                     var7 = cnq.iu;
                     var6 = (var1 >> 12 & 1) << 5 | var1 >> 2 & 31;
                  }
               } else if (this.Dw()) {
               }
            }
         } else if (var3 == 1) {
            if (this.Uv()) {
               var7 = cnq.wh;
               var5 = 2;
               var6 = Uv(var1);
            } else if (this.Dw()) {
            }
         } else if (var3 == 2 || var3 == 3) {
            if (var3 == 2) {
               if (var4 != 0) {
                  var7 = cnq.AD;
                  var5 = 2;
                  var6 = Dw(var1);
               }
            } else if (this.RF()) {
               var7 = cnq.sE;
               var5 = 2;
               var6 = Dw(var1);
            } else if (this.oW()) {
               var7 = cnq.ZY;
               var5 = 2;
               var6 = Uv(var1);
            }
         }
      }

      return var7.xK == 3 ? this.q(var1, var7, var4, var5, var6) : this.q(var1, var7, var4, var6);
   }

   static final int Dw(int var0) {
      return (var0 >> 4 & 7) << 2 | (var0 >> 12 & 1) << 5 | (var0 >> 2 & 3) << 6;
   }

   static final int Uv(int var0) {
      return (var0 >> 5 & 3) << 3 | (var0 >> 12 & 1) << 5 | (var0 >> 2 & 7) << 6;
   }

   cnl RF(int var1, int var2, int var3) {
      Assert.a(var2 == 1 && var3 == 4);
      int var4 = 8 | var1 >> 7 & 7;
      int var5 = var1 >> 2 & 31 | (var1 >> 12 & 1) << 5;
      int var6 = var1 >> 10 & 3;
      int var7 = var1 >> 12 & 1;
      cnq.eo var8 = null;
      if (var6 == 0) {
         if (this.RF() && var7 != 0) {
            return null;
         }

         if (var5 != 0) {
            var8 = cnq.fn;
         }
      } else if (var6 == 1) {
         if (this.RF() && var7 != 0) {
            return null;
         }

         if (var5 != 0) {
            var8 = cnq.ZU;
         }
      } else if (var6 == 2) {
         var5 = var1 >>> 12 << 31 >> 26 | var1 >> 2 & 31;
         var8 = cnq.of;
      } else {
         Assert.a(var6 == 3);
         int var9 = 8 | var1 >> 2 & 7;
         int var10 = var1 >> 5 & 3;
         if (var7 == 0) {
            if (var10 == 0) {
               var8 = cnq.WX;
            } else if (var10 == 1) {
               var8 = cnq.KF;
            } else if (var10 == 2) {
               var8 = cnq.jk;
            } else {
               var8 = cnq.Cl;
            }
         } else if (var10 == 0) {
            var8 = cnq.vh;
         } else if (var10 == 1) {
            var8 = cnq.Kn;
         }

         var5 = var9;
      }

      return this.q(var1, var8, var4, var4, var5, 0L);
   }

   cnl xK(int var1, int var2, int var3) {
      long var4 = var1 >>> 12 << 31 >> 20
         | (var1 >> 2 & 1) << 5
         | (var1 >> 3 & 7) << 1
         | (var1 >> 6 & 1) << 7
         | (var1 >> 7 & 1) << 6
         | (var1 >> 8 & 1) << 10
         | (var1 >> 9 & 3) << 8
         | (var1 >> 11 & 1) << 4;
      cnq.eo var6 = null;
      if (var2 == 1 && var3 == 1) {
         var6 = cnq.mJ;
      } else if (var2 == 1 && var3 == 5) {
         var6 = cnq.Ua;
      }

      return var6 == cnq.Ua ? this.q(var1, var6, var4, 0L, 0L, 0L) : this.q(var1, var6, 1L, var4, 0L, 0L);
   }

   cnl Dw(int var1, int var2, int var3) {
      int var4 = 8 | var1 >> 7 & 7;
      long var5 = var1 >>> 12 << 31 >> 23 | (var1 >> 5 & 3) << 6 | (var1 >> 2 & 1) << 5 | (var1 >> 10 & 3) << 3 | (var1 >> 3 & 3) << 1;
      cnq.eo var7 = null;
      if (var2 == 1 && var3 == 6) {
         var7 = cnq.NY;
      } else if (var2 == 1 && var3 == 7) {
         var7 = cnq.xf;
      }

      return this.q(var1, var7, var4, var5, 0L, 0L);
   }

   cnl Uv(int var1, int var2, int var3) {
      int var4 = 8 | var1 >> 2 & 7;
      long var5 = (var1 >> 6 & 1) << 2 | (var1 >> 5 & 1) << 3 | (var1 >> 11 & 3) << 4 | (var1 >> 7 & 15) << 6;
      cnq.eo var7 = null;
      if (var2 == 0 && var3 == 0 && var5 != 0L) {
         var7 = cnq.ND;
      }

      return this.q(var1, var7, var4, 2L, var5, 0L);
   }

   cnl oW(int var1, int var2, int var3) {
      int var4 = 8 | var1 >> 2 & 7;
      int var5 = 8 | var1 >> 7 & 7;
      long var6 = (var1 >> 10 & 7) << 3 | (var1 >> 5 & 3) << 6;
      long var8 = (var1 >> 6 & 1) << 2 | (var1 >> 10 & 7) << 3 | (var1 >> 5 & 1) << 6;
      cnq.eo var10 = null;
      if (var2 == 0) {
         if (var3 == 1) {
            if (this.Uv()) {
               var10 = cnq.GO;
            } else if (this.Dw()) {
            }
         } else if (var3 == 2) {
            var6 = var8;
            var10 = cnq.fQ;
         } else if (var3 == 3) {
            if (this.RF()) {
               var6 = var8;
               var10 = cnq.wS;
            } else if (this.oW()) {
               var10 = cnq.pQ;
            }
         }
      }

      return this.q(var1, var10, var4, var5, var6, 0L);
   }

   cnl gO(int var1, int var2, int var3) {
      int var4 = 8 | var1 >> 2 & 7;
      int var5 = 8 | var1 >> 7 & 7;
      long var6 = (var1 >> 10 & 7) << 3 | (var1 >> 5 & 3) << 6;
      long var8 = (var1 >> 6 & 1) << 2 | (var1 >> 10 & 7) << 3 | (var1 >> 5 & 1) << 6;
      cnq.eo var10 = null;
      if (var2 == 0) {
         if (var3 == 5) {
            if (this.Uv()) {
               var10 = cnq.QZ;
            } else if (this.Dw()) {
            }
         } else if (var3 == 6) {
            var6 = var8;
            var10 = cnq.Yw;
         } else if (var3 == 7) {
            if (this.RF()) {
               var6 = var8;
               var10 = cnq.Oz;
            } else if (this.oW()) {
               var10 = cnq.IY;
            }
         }
      }

      return this.q(var1, var10, var4, var5, var6, 0L);
   }

   cnl nf(int var1, int var2, int var3) {
      int var4 = var1 >> 12 & 1;
      int var5 = var1 >> 7 & 31;
      int var6 = var1 >> 2 & 31;
      cnq.eo var7 = null;
      if (var2 == 2 && var3 == 4) {
         if (var4 == 0) {
            if (var6 == 0) {
               var7 = cnq.jT;
            } else {
               var7 = cnq.Ld;
            }
         } else if (var4 == 1) {
            if (var5 == 0 && var6 == 0) {
               var7 = cnq.nv;
            } else {
               if (var5 != 0 && var6 == 0) {
                  var7 = cnq.Bs;
                  return this.q(var1, var7, 1L, var5, 0L);
               }

               if (var5 != 0 && var6 != 0) {
                  var7 = cnq.rV;
                  return this.q(var1, var7, var5, var5, var6);
               }
            }
         }
      }

      return this.q(var1, var7, var5, var6);
   }

   cnl gP(int var1, int var2, int var3) {
      int var4 = var1 >> 2 & 31;
      cnq.eo var5 = null;
      int var6 = 0;
      if (var2 == 2) {
         if (var3 == 5) {
            if (this.Uv()) {
               var6 = oW(var1);
               var5 = cnq.iY;
            } else if (this.Dw()) {
               var6 = gO(var1);
               var5 = cnq.rw;
            }
         } else if (var3 == 6) {
            var6 = nf(var1);
            var5 = cnq.Cw;
         }

         if (var3 == 7) {
            if (this.RF()) {
               var6 = nf(var1);
               var5 = cnq.BR;
            } else if (this.oW()) {
               var6 = oW(var1);
               var5 = cnq.bH;
            }
         }
      }

      return this.q(var1, var5, var4, var6, 0L, 0L);
   }

   static final int oW(int var0) {
      return (var0 >> 10 & 7) << 3 | (var0 >> 7 & 7) << 6;
   }

   static final int gO(int var0) {
      return (var0 >> 11 & 3) << 4 | (var0 >> 7 & 15) << 6;
   }

   static final int nf(int var0) {
      return (var0 >> 9 & 15) << 2 | (var0 >> 7 & 3) << 6;
   }

   void q(cnq.eo var1) {
      Assert.a(var1 != null);
      Assert.a((this.Uv & 15) >= (var1.RF & 15));
   }

   cnl q(int var1, cnq.eo var2, cnm... var3) {
      int var4 = 0;

      for (cnm var8 : var3) {
         if (var8 == null) {
            break;
         }

         var4++;
      }

      return new cnl((var1 & 3) == 3 ? this.gP(var1) : this.za(var1), var2, (cnm[])Arrays.copyOf((Object[])var3, var4), this.getMode());
   }

   cnm q(long var1, boolean var3) {
      long var4;
      if (var3) {
         var4 = RegisterUtil.createPureRegisterId((int)var1, 2);
      } else {
         var4 = var1;
      }

      return new cnm(0, this.getGPRegisterBitsize(), var4);
   }

   cnm q(long var1) {
      return new cnm(0, this.getGPRegisterBitsize(), var1);
   }

   cnm RF(long var1) {
      return new cnm(1, this.getGPRegisterBitsize(), var1);
   }

   cnm xK(long var1) {
      return new cnm(9, this.getGPRegisterBitsize(), var1);
   }

   cnm Dw(long var1) {
      return new cnm(3, this.getGPRegisterBitsize(), var1);
   }

   cnl q(int var1, cnq.eo var2) {
      return this.q(var1, var2, 0L, 0L, 0L, 0L);
   }

   cnl q(int var1, cnq.eo var2, long var3) {
      return this.q(var1, var2, var3, 0L, 0L, 0L);
   }

   cnl q(int var1, cnq.eo var2, long var3, long var5) {
      return this.q(var1, var2, var3, var5, 0L, 0L);
   }

   cnl q(int var1, cnq.eo var2, long var3, long var5, long var7) {
      return this.q(var1, var2, var3, var5, var7, 0L);
   }

   cnl q(int var1, cnq.eo var2, long var3, long var5, long var7, long var9) {
      this.q(var2);
      cnm var11 = this.q(var2.Dw & 15, var3);
      cnm var12 = this.q(var2.Dw >> 4 & 15, var5);
      cnm var13 = this.q(var2.Dw >> 8 & 15, var7);
      cnm var14 = this.q(var2.Dw >> 12 & 15, var9);
      return this.q(var1, var2, var11, var12, var13, var14);
   }

   cnm q(int var1, long var2) {
      switch (var1) {
         case 1:
            return this.RF(var2);
         case 2:
            return this.xK(var2);
         case 3:
            return this.Dw(var2);
         case 4:
            return this.q(var2);
         case 5:
            return this.q(var2, true);
         default:
            return null;
      }
   }

   BytesBlock gP(int var1) {
      byte[] var2 = new byte[]{(byte)(var1 & 0xFF), (byte)(var1 >> 8 & 0xFF), (byte)(var1 >> 16 & 0xFF), (byte)(var1 >> 24 & 0xFF)};
      return new BytesBlock(var2, Endianness.LITTLE_ENDIAN, 2);
   }

   BytesBlock za(int var1) {
      byte[] var2 = new byte[]{(byte)(var1 & 0xFF), (byte)(var1 >> 8 & 0xFF)};
      return new BytesBlock(var2, Endianness.LITTLE_ENDIAN, 2);
   }

   @Override
   public String getRegisterName(long var1) {
      return this.RF(var1, false);
   }

   public String RF(long var1, boolean var3) {
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
