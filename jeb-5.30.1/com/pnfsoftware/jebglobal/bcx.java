package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.client.S;
import com.pnfsoftware.jeb.core.units.code.asm.cfg.BasicBlock;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.AbstractConverter;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ConverterInstructionEntry;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.IERoutineContext;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.ELocation;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.EUtil;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.IEGeneric;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.IEImm;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.IEVar;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.OperationType;
import com.pnfsoftware.jeb.core.units.code.asm.processor.arch.RegisterUtil;
import com.pnfsoftware.jeb.util.base.Assert;
import com.pnfsoftware.jeb.util.format.Formatter;
import com.pnfsoftware.jeb.util.logging.GlobalLog;
import com.pnfsoftware.jeb.util.logging.ILogger;
import com.pnfsoftware.jeb.util.serialization.annotations.Ser;
import com.pnfsoftware.jeb.util.serialization.annotations.SerCustomInitPostGraph;
import com.pnfsoftware.jeb.util.serialization.annotations.SerId;
import com.pnfsoftware.jeb.util.serialization.annotations.SerTransient;
import java.util.ArrayList;
import java.util.List;

@Ser
public class bcx extends AbstractConverter {
   private static final ILogger Rv = GlobalLog.getLogger(bcx.class);
   public static final int q = 4096;
   public static final int RF = 744;
   public static final int xK = 752;
   public static final int Dw = 760;
   private static final int zx = 26;
   private static final int ZT = 28;
   private static final int Ri = 30;
   public static final int Uv = 208;
   public static final int oW = 216;
   public static final int gO = 224;
   public static final int nf = 232;
   public static final int gP = 240;
   public static final int za = 248;
   public static final int lm = 65536;
   public static final int zz = 65552;
   public static final int JY = 65568;
   public static final int HF = 65584;
   public static final int LK = 65600;
   @SerTransient
   bcu io;
   @SerTransient
   bcw qa;
   @SerTransient
   bcv Hk;
   @SerId(1)
   int Me;
   @SerId(2)
   final IEVar PV;
   @SerId(3)
   final IEVar oQ;
   @SerId(4)
   final IEVar xW;
   @SerId(5)
   final IEVar KT;
   @SerId(6)
   final IEVar Gf;
   @SerId(10)
   final IEVar[] Ef;
   @SerId(11)
   final IEVar[] cC;
   @SerId(20)
   final IEVar sH;
   @SerId(21)
   final IEVar CE;
   @SerId(22)
   final IEVar wF;
   @SerId(23)
   final IEVar If;
   @SerId(24)
   final IEVar Dz;
   @SerId(25)
   final IEVar mI;
   @SerId(26)
   final IEVar jq;
   @SerId(27)
   final IEVar ui;
   @SerId(28)
   final IEVar[] TX;
   @SerId(40)
   final IEVar Rr;
   @SerId(41)
   final IEVar EB;
   @SerId(42)
   final IEImm Xo;
   @SerId(43)
   final IEImm Bu;
   @SerId(44)
   final IEImm IN;
   @SerId(45)
   final IEImm rL;
   @SerId(46)
   final IEImm eJ;
   @SerId(50)
   boolean YN = true;

   public bcx(bdf var1) {
      super(var1, 8, 16);
      this.Ef = new IEVar[96];

      for (int var2 = 0; var2 < 32; var2++) {
         this.Ef[var2] = this.gCtx.createRegister(var2 * 8, bdd.q(0, this.regNormalBitsize, var2), this.regNormalBitsize);
      }

      this.cC = new IEVar[64];

      for (int var3 = 0; var3 < 64; var3++) {
         this.cC[var3] = this.gCtx.createRegister((32 + var3) * 8, bdd.q(2, this.regNormalBitsize, var3), this.regNormalBitsize);
         this.Ef[32 + var3] = this.cC[var3];
      }

      this.PV = this.gCtx.createRegister(4096, "pc", 16);
      this.oQ = this.gCtx.createVirtualRegister(65536, "sp", 16);
      this.xW = this.gCtx.createVirtualRegister(65552, "X", 16);
      this.KT = this.gCtx.createVirtualRegister(65568, "Y", 16);
      this.Gf = this.gCtx.createVirtualRegister(65584, "Z", 16);
      this.sH = this.gCtx.createVirtualRegister(65600, "cf", 1);
      this.CE = this.gCtx.createVirtualRegister(65601, "zf", 1);
      this.wF = this.gCtx.createVirtualRegister(65602, "nf", 1);
      this.If = this.gCtx.createVirtualRegister(65603, "vf", 1);
      this.Dz = this.gCtx.createVirtualRegister(65604, "sf", 1);
      this.mI = this.gCtx.createVirtualRegister(65605, "hf", 1);
      this.jq = this.gCtx.createVirtualRegister(65606, "tf", 1);
      this.ui = this.gCtx.createVirtualRegister(65607, "if", 1);
      this.TX = new IEVar[]{this.sH, this.CE, this.wF, this.If, this.Dz, this.mI, this.jq, this.ui};
      this.Rr = this.gCtx.createVirtualRegister("tmp", 8);
      this.EB = this.gCtx.createVirtualRegister("tmpw", 16);
      this.Xo = this.gCtx.createImm(0L, 8);
      this.Bu = this.gCtx.createImm(1L, 8);
      this.IN = this.gCtx.createImm(-1L, 8);
      this.rL = this.gCtx.createImm(0L, 1);
      this.eJ = this.gCtx.createImm(1L, 1);
      this.gO();
   }

   @SerCustomInitPostGraph
   private void gO() {
      this.io = new bcu(this);
      this.qa = new bcw(this);
      this.Hk = new bcv(this);
   }

   @Override
   public IEVar getProgramCounter() {
      return this.PV;
   }

   @Override
   public IEVar getStackPointer() {
      return this.oQ;
   }

   public IEVar q() {
      return this.xW;
   }

   public IEVar RF() {
      return this.KT;
   }

   public IEVar xK() {
      return this.Gf;
   }

   public IEVar q(long var1, ELocation var3) {
      int var4 = (int)var1;
      return this.Ef[var4];
   }

   @Override
   public long getNativeRegisterIdFromRegisterVariable(IEVar var1, boolean var2) {
      return super.getNativeRegisterIdFromRegisterVariable(var1, var2);
   }

   @Override
   public void setCurrentContext(IERoutineContext var1) {
      super.setCurrentContext(var1);
      this.io.RF = var1;
      this.qa.RF = var1;
      this.Hk.RF = var1;
   }

   @Override
   protected void convertBlock(BasicBlock var1, List var2) {
      long var3 = var1.getFirstAddress();
      long var5 = var3;
      ArrayList var7 = new ArrayList();
      ConverterInstructionEntry var8 = new ConverterInstructionEntry();
      var8.r = var7;
      bdc var9 = null;

      try {
         for (int var10 = 0; var10 < var1.size(); var10++) {
            var9 = (bdc)var1.get(var10);
            var7.clear();
            int var11 = var2.size();
            var8.insn = var9;
            var8.address = var5;
            var8.irAddress = var11;
            String var12 = var9.getMnemonic();
            switch (var12) {
               case "nop":
                  if (!this.doNotGenerateNops) {
                     var8.r.add(this.ctx.createNop());
                  }
                  break;
               case "add":
                  this.io.q(var8, false);
                  break;
               case "adc":
                  this.io.q(var8, true);
                  break;
               case "sub":
                  this.io.RF(var8, false);
                  break;
               case "subi":
                  this.io.RF(var8, false);
                  break;
               case "sbc":
                  this.io.RF(var8, true);
                  break;
               case "sbci":
                  this.io.RF(var8, true);
                  break;
               case "mul":
                  this.io.Uv(var8);
                  break;
               case "tst":
                  this.io.oW(var8);
                  break;
               case "clr":
                  this.io.gO(var8);
                  break;
               case "ser":
                  this.io.nf(var8);
                  break;
               case "eor":
                  this.io.q(var8, OperationType.XOR);
                  break;
               case "and":
                  this.io.q(var8, OperationType.AND);
                  break;
               case "andi":
                  this.io.q(var8, OperationType.AND);
                  break;
               case "or":
                  this.io.q(var8, OperationType.OR);
                  break;
               case "ori":
                  this.io.q(var8, OperationType.OR);
                  break;
               case "com":
                  this.io.gP(var8);
                  break;
               case "neg":
                  this.io.za(var8);
                  break;
               case "inc":
                  this.io.q(var8);
                  break;
               case "dec":
                  this.io.RF(var8);
                  break;
               case "adiw":
                  this.io.xK(var8);
                  break;
               case "sbiw":
                  this.io.Dw(var8);
                  break;
               case "sbr":
               case "cbr":
                  throw new RuntimeException("Opcode is not generated by the AvrProcessor class");
               case "sbi":
                  this.io.lm(var8);
                  break;
               case "cbi":
                  this.io.zz(var8);
                  break;
               case "lsl":
                  this.io.JY(var8);
                  break;
               case "lsr":
                  this.io.LK(var8);
                  break;
               case "rol":
                  this.io.HF(var8);
                  break;
               case "ror":
                  this.io.io(var8);
                  break;
               case "asr":
                  this.io.qa(var8);
                  break;
               case "swap":
                  this.io.Hk(var8);
                  break;
               case "bst":
                  this.io.Me(var8);
                  break;
               case "bld":
                  this.io.PV(var8);
                  break;
               case "sec":
                  this.io.q(var8, 0);
                  break;
               case "clc":
                  this.io.q(var8, 0);
                  break;
               case "sez":
                  this.io.q(var8, 1);
                  break;
               case "clz":
                  this.io.q(var8, 1);
                  break;
               case "sen":
                  this.io.q(var8, 2);
                  break;
               case "cln":
                  this.io.q(var8, 2);
                  break;
               case "sev":
                  this.io.q(var8, 3);
                  break;
               case "clv":
                  this.io.q(var8, 3);
                  break;
               case "ses":
                  this.io.q(var8, 4);
                  break;
               case "cls":
                  this.io.q(var8, 4);
                  break;
               case "seh":
                  this.io.q(var8, 5);
                  break;
               case "clh":
                  this.io.q(var8, 5);
                  break;
               case "set":
                  this.io.q(var8, 6);
                  break;
               case "clt":
                  this.io.q(var8, 6);
                  break;
               case "sei":
                  this.io.q(var8, 7);
                  break;
               case "cli":
                  this.io.q(var8, 7);
                  break;
               case "cp":
               case "cpi":
                  this.io.xK(var8, false);
                  break;
               case "cpc":
                  this.io.xK(var8, true);
                  break;
               case "mov":
               case "movw":
               case "ldi":
               case "in":
               case "out":
                  this.qa.q(var8);
                  break;
               case "ld":
               case "ldd":
                  this.qa.RF(var8);
                  break;
               case "st":
               case "std":
                  this.qa.xK(var8);
                  break;
               case "push":
                  this.qa.Dw(var8);
                  break;
               case "pop":
                  this.qa.Uv(var8);
                  break;
               case "ret":
                  this.Hk.q(var8);
                  break;
               case "rcall":
               case "call":
                  this.Hk.q(var8, false);
                  break;
               case "icall":
                  this.Hk.q(var8, true);
                  break;
               case "rjmp":
               case "jmp":
                  this.Hk.RF(var8, false);
                  break;
               case "ijmp":
                  this.Hk.RF(var8, true);
                  break;
               case "breq":
               case "brne":
               case "brcs":
               case "brlo":
               case "brcc":
               case "brsh":
               case "brmi":
               case "brpl":
               case "brge":
               case "brlt":
               case "brhs":
               case "brhc":
               case "brts":
               case "brtc":
               case "brvs":
               case "brvc":
               case "brie":
               case "brid":
                  this.Hk.q(var8, var12);
                  break;
               case "sbrc":
               case "sbic":
                  this.Hk.xK(var8, true);
                  break;
               case "sbrs":
               case "sbis":
                  this.Hk.xK(var8, true);
                  break;
               case "cpse":
                  this.Hk.xK(var8);
                  break;
               default:
                  throw new RuntimeException("Cannot convert instruction: " + var9);
            }

            EUtil.setLowerLevelAddress(var5, var7);
            var2.addAll(var7);
            var5 += var9.getSize();
         }
      } catch (Throwable var15) {
         Rv.error(S.L("Error: Instruction cannot be converted: %s %s"), Formatter.byteArrayToHex(var9.getCode()), var9.format(var5));
         throw var15;
      }
   }

   IEGeneric q(bdc var1, int var2, long var3) {
      return this.q(var1, var2, var3, null);
   }

   IEGeneric q(bdc var1, int var2, long var3, bcw.eo var5) {
      bdd var6 = var1.q()[var2];
      int var7 = var6.getOperandType();
      long var8 = var6.getOperandValue();
      if (var7 == 1) {
         long var17 = var8 & 255L;
         return this.ctx.createImm(var17, 8);
      } else if (var7 == 2) {
         return this.ctx.createImm(var8, 16);
      } else if (var7 == 3) {
         long var16 = var3 + var8;
         return this.ctx.createImm(var16, 16);
      } else {
         if (var7 == 0) {
            int var10 = RegisterUtil.getRegisterIndex(var8);
            int var11 = RegisterUtil.getRegisterBitsize(var8);
            int var12 = RegisterUtil.getRegisterGroup(var8);
            if (this.YN) {
               if (var12 == 0) {
                  if (var10 == 26) {
                     return this.xW.part(8);
                  }

                  if (var10 == 27) {
                     return this.xW.slice(8, 16);
                  }

                  if (var10 == 28) {
                     return this.KT.part(8);
                  }

                  if (var10 == 29) {
                     return this.KT.slice(8, 16);
                  }

                  if (var10 == 30) {
                     return this.Gf.part(8);
                  }

                  if (var10 == 31) {
                     return this.Gf.slice(8, 16);
                  }

                  if (var10 == 95) {
                     return this.ctx.createCompose(this.TX);
                  }
               } else if (var12 == 1) {
                  int var13 = var10 * 2;
                  if (var13 == 26) {
                     return this.xW;
                  }

                  if (var13 == 28) {
                     return this.KT;
                  }

                  if (var13 == 30) {
                     return this.Gf;
                  }

                  if (var13 == 93) {
                     return this.oQ;
                  }
               }
            }

            if (var12 == 0) {
               Assert.a(var11 == 8);
               return this.Ef[var10];
            }

            if (var12 == 1) {
               Assert.a(var11 == 16);
               return this.ctx.createCompose(this.Ef[var10 * 2], this.Ef[var10 * 2 + 1]);
            }
         } else {
            if (var7 == 4098) {
               int var15 = (int)var8 & 0xFF;
               if (this.YN && var15 == 63) {
                  return this.ctx.createCompose(this.TX);
               }

               return this.cC[var15];
            }

            if (var7 == 4097) {
               int var14 = (int)var8 & 0xFF;
               int var18 = (int)var8 >> 8 & 0xFF;
               switch (var14) {
                  case 1:
                  case 2:
                  case 3:
                     var5.q = var14 == 1 ? 0 : (var14 == 2 ? 1 : -1);
                     var5.RF = 26;
                     return this.ctx.createMem(this.Dw(), 8);
                  case 4:
                  case 5:
                  case 6:
                     var5.q = var14 == 4 ? 0 : (var14 == 5 ? 1 : -1);
                     var5.RF = 28;
                     return this.ctx.createMem(this.Uv(), 8);
                  case 7:
                  case 8:
                  case 9:
                     var5.q = var14 == 7 ? 0 : (var14 == 8 ? 1 : -1);
                     var5.RF = 30;
                     return this.ctx.createMem(this.oW(), 8);
                  case 10:
                     return this.ctx.createMem(this.ctx.createOperation(OperationType.ADD, this.Uv(), this.ctx.createImm(var18, 16)), 8);
                  case 11:
                     return this.ctx.createMem(this.ctx.createOperation(OperationType.ADD, this.oW(), this.ctx.createImm(var18, 16)), 8);
               }
            }
         }

         throw new RuntimeException("TBI: operand conversion: " + var6);
      }
   }

   IEGeneric Dw() {
      return (IEGeneric)(this.YN ? this.xW : this.ctx.createCompose(this.Ef[26], this.Ef[27]));
   }

   IEGeneric Uv() {
      return (IEGeneric)(this.YN ? this.KT : this.ctx.createCompose(this.Ef[28], this.Ef[29]));
   }

   IEGeneric oW() {
      return (IEGeneric)(this.YN ? this.Gf : this.ctx.createCompose(this.Ef[30], this.Ef[31]));
   }

   void q(List var1, IEGeneric var2) {
      var1.add(this.ctx.createAssign(this.CE, this.ctx.createCond(var2, this.rL, this.eJ)));
   }

   void RF(List var1, IEGeneric var2) {
      var1.add(this.ctx.createAssign(this.wF, var2.msb()));
   }

   void q(List var1, IEGeneric var2, IEGeneric var3, IEGeneric var4, boolean var5, boolean var6, boolean var7) {
      if (!var5) {
         if (var6) {
            var1.add(this.ctx.createAssign(this.sH, this.ctx.createOperation(OperationType.LT_U, var4, var3)));
         }

         if (var7) {
            var1.add(this.ctx.createAssign(this.mI, this.ctx.createOperation(OperationType.LT_U, var4.part(4), var3.part(4))));
         }
      } else {
         if (var6) {
            var1.add(
               this.ctx
                  .createAssign(
                     this.sH,
                     this.ctx
                        .createOperation(
                           OperationType.LOG_OR,
                           this.ctx.createOperation(OperationType.LT_U, var4, var2),
                           this.ctx
                              .createOperation(
                                 OperationType.LOG_AND,
                                 this.ctx.createOperation(OperationType.LOG_EQ, var4, var2),
                                 this.ctx.createOperation(OperationType.LOG_NEQ, var3, this.ctx.createImm(0L, var3.getBitsize()))
                              )
                        )
                  )
            );
         }

         if (var7) {
            var1.add(
               this.ctx
                  .createAssign(
                     this.mI,
                     this.ctx
                        .createOperation(
                           OperationType.LOG_OR,
                           this.ctx.createOperation(OperationType.LT_U, var4.part(4), var2.part(4)),
                           this.ctx
                              .createOperation(
                                 OperationType.LOG_AND,
                                 this.ctx.createOperation(OperationType.LOG_EQ, var4.part(4), var2.part(4)),
                                 this.ctx.createOperation(OperationType.LOG_NEQ, var3.part(4), this.ctx.createImm(0L, 4))
                              )
                        )
                  )
            );
         }
      }
   }

   void RF(List var1, IEGeneric var2, IEGeneric var3, IEGeneric var4, boolean var5, boolean var6, boolean var7) {
      if (!var5) {
         if (var6) {
            var1.add(this.ctx.createAssign(this.sH, this.ctx.createOperation(OperationType.LT_U, var2, var3)));
         }

         if (var7) {
            var1.add(this.ctx.createAssign(this.mI, this.ctx.createOperation(OperationType.LT_U, var2.part(4), var3.part(4))));
         }
      } else {
         if (var6) {
            var1.add(
               this.ctx
                  .createAssign(
                     this.sH,
                     this.ctx
                        .createOperation(
                           OperationType.LOG_OR,
                           this.ctx.createOperation(OperationType.LT_U, var2, var3),
                           this.ctx
                              .createOperation(
                                 OperationType.LOG_AND,
                                 this.ctx.createOperation(OperationType.LOG_EQ, var2, var3),
                                 this.ctx.createOperation(OperationType.LOG_NEQ, var4, this.ctx.createImm(0L, var4.getBitsize()))
                              )
                        )
                  )
            );
         }

         if (var7) {
            var1.add(
               this.ctx
                  .createAssign(
                     this.mI,
                     this.ctx
                        .createOperation(
                           OperationType.LOG_OR,
                           this.ctx.createOperation(OperationType.LT_U, var2.part(4), var3.part(4)),
                           this.ctx
                              .createOperation(
                                 OperationType.LOG_AND,
                                 this.ctx.createOperation(OperationType.LOG_EQ, var2.part(4), var3.part(4)),
                                 this.ctx.createOperation(OperationType.LOG_NEQ, var4.part(4), this.ctx.createImm(0L, 4))
                              )
                        )
                  )
            );
         }
      }
   }

   void q(List var1, IEGeneric var2, IEGeneric var3, IEGeneric var4) {
      var1.add(this.ctx.createAssign(this.If, EUtil.buildOverflowFlag(var2, var3, var4, true)));
   }

   void RF(List var1, IEGeneric var2, IEGeneric var3, IEGeneric var4) {
      var1.add(this.ctx.createAssign(this.If, EUtil.buildOverflowFlag(var2, var3, var4, false)));
   }

   void xK(List var1, IEGeneric var2) {
      var1.add(this.ctx.createAssign(this.Dz, this.ctx.createOperation(OperationType.XOR, this.wF, this.If)));
   }

   IEGeneric Dw(List var1, IEGeneric var2) {
      IEVar var3 = this.Rr;
      if (var2.getBitsize() == 16) {
         var3 = this.EB;
      } else if (var2.getBitsize() != 8) {
         throw new RuntimeException("The source operand must be 8-bit or 16-bit wide");
      }

      var1.add(this.ctx.createAssign(var3, var2));
      return var3;
   }

   IEGeneric q(List var1, IEGeneric var2, IEGeneric var3) {
      var1.add(this.ctx.createAssign(var2, var3));
      return var2;
   }

   void Uv(List var1, IEGeneric var2) {
      var1.add(this.ctx.createBranchAssign(this.PV, var2, false));
   }

   void oW(List var1, IEGeneric var2) {
      var1.add(this.ctx.createBranchAssign(this.PV, var2, true));
   }

   IEImm q(long var1) {
      return this.ctx.createImm(var1, this.getAddressBitsize());
   }
}
