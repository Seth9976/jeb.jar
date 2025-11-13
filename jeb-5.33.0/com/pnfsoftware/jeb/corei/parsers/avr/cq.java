package com.pnfsoftware.jeb.corei.parsers.avr;

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
public class cq extends AbstractConverter {
   private static final ILogger Aj = GlobalLog.getLogger(cq.class);
   @SerTransient
   K pC;
   @SerTransient
   bO A;
   @SerTransient
   Ws kS;
   @SerId(1)
   int wS;
   @SerId(2)
   final IEVar UT;
   @SerId(3)
   final IEVar E;
   @SerId(4)
   final IEVar sY;
   @SerId(5)
   final IEVar ys;
   @SerId(6)
   final IEVar ld;
   @SerId(10)
   final IEVar[] gp;
   @SerId(11)
   final IEVar[] oT;
   @SerId(20)
   final IEVar fI;
   @SerId(21)
   final IEVar WR;
   @SerId(22)
   final IEVar NS;
   @SerId(23)
   final IEVar vP;
   @SerId(24)
   final IEVar xC;
   @SerId(25)
   final IEVar ED;
   @SerId(26)
   final IEVar Sc;
   @SerId(27)
   final IEVar ah;
   @SerId(28)
   final IEVar[] eP;
   @SerId(40)
   final IEVar UO;
   @SerId(41)
   final IEVar Ab;
   @SerId(42)
   final IEImm rl;
   @SerId(43)
   final IEImm z;
   @SerId(44)
   final IEImm Ek;
   @SerId(45)
   final IEImm hK;
   @SerId(46)
   final IEImm Er;
   @SerId(50)
   boolean FE = true;

   public cq(RC var1) {
      super(var1, 8, 16);
      this.gp = new IEVar[96];

      for (int var2 = 0; var2 < 32; var2++) {
         this.gp[var2] = this.gCtx.createRegister(var2 * 8, yt.pC(0, this.regNormalBitsize, var2), this.regNormalBitsize);
      }

      this.oT = new IEVar[64];

      for (int var3 = 0; var3 < 64; var3++) {
         this.oT[var3] = this.gCtx.createRegister((32 + var3) * 8, yt.pC(2, this.regNormalBitsize, var3), this.regNormalBitsize);
         this.gp[32 + var3] = this.oT[var3];
      }

      this.UT = this.gCtx.createRegister(4096, "pc", 16);
      this.E = this.gCtx.createVirtualRegister(65536, "sp", 16);
      this.sY = this.gCtx.createVirtualRegister(65552, "X", 16);
      this.ys = this.gCtx.createVirtualRegister(65568, "Y", 16);
      this.ld = this.gCtx.createVirtualRegister(65584, "Z", 16);
      this.fI = this.gCtx.createVirtualRegister(65600, "cf", 1);
      this.WR = this.gCtx.createVirtualRegister(65601, "zf", 1);
      this.NS = this.gCtx.createVirtualRegister(65602, "nf", 1);
      this.vP = this.gCtx.createVirtualRegister(65603, "vf", 1);
      this.xC = this.gCtx.createVirtualRegister(65604, "sf", 1);
      this.ED = this.gCtx.createVirtualRegister(65605, "hf", 1);
      this.Sc = this.gCtx.createVirtualRegister(65606, "tf", 1);
      this.ah = this.gCtx.createVirtualRegister(65607, "if", 1);
      this.eP = new IEVar[]{this.fI, this.WR, this.NS, this.vP, this.xC, this.ED, this.Sc, this.ah};
      this.UO = this.gCtx.createVirtualRegister("tmp", 8);
      this.Ab = this.gCtx.createVirtualRegister("tmpw", 16);
      this.rl = this.gCtx.createImm(0L, 8);
      this.z = this.gCtx.createImm(1L, 8);
      this.Ek = this.gCtx.createImm(-1L, 8);
      this.hK = this.gCtx.createImm(0L, 1);
      this.Er = this.gCtx.createImm(1L, 1);
      this.sY();
   }

   @SerCustomInitPostGraph
   private void sY() {
      this.pC = new K(this);
      this.A = new bO(this);
      this.kS = new Ws(this);
   }

   @Override
   public IEVar getProgramCounter() {
      return this.UT;
   }

   @Override
   public IEVar getStackPointer() {
      return this.E;
   }

   public IEVar pC() {
      return this.sY;
   }

   public IEVar A() {
      return this.ys;
   }

   public IEVar kS() {
      return this.ld;
   }

   public IEVar pC(long var1, ELocation var3) {
      int var4 = (int)var1;
      return this.gp[var4];
   }

   @Override
   public long getNativeRegisterIdFromRegisterVariable(IEVar var1, boolean var2) {
      return super.getNativeRegisterIdFromRegisterVariable(var1, var2);
   }

   @Override
   public void setCurrentContext(IERoutineContext var1) {
      super.setCurrentContext(var1);
      this.pC.A = var1;
      this.A.A = var1;
      this.kS.A = var1;
   }

   @Override
   protected void convertBlock(BasicBlock var1, List var2) {
      long var3 = var1.getFirstAddress();
      long var5 = var3;
      ArrayList var7 = new ArrayList();
      ConverterInstructionEntry var8 = new ConverterInstructionEntry();
      var8.r = var7;
      KD var9 = null;

      try {
         for (int var10 = 0; var10 < var1.size(); var10++) {
            var9 = (KD)var1.get(var10);
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
                  this.pC.pC(var8, false);
                  break;
               case "adc":
                  this.pC.pC(var8, true);
                  break;
               case "sub":
                  this.pC.A(var8, false);
                  break;
               case "subi":
                  this.pC.A(var8, false);
                  break;
               case "sbc":
                  this.pC.A(var8, true);
                  break;
               case "sbci":
                  this.pC.A(var8, true);
                  break;
               case "mul":
                  this.pC.UT(var8);
                  break;
               case "tst":
                  this.pC.E(var8);
                  break;
               case "clr":
                  this.pC.sY(var8);
                  break;
               case "ser":
                  this.pC.ys(var8);
                  break;
               case "eor":
                  this.pC.pC(var8, OperationType.XOR);
                  break;
               case "and":
                  this.pC.pC(var8, OperationType.AND);
                  break;
               case "andi":
                  this.pC.pC(var8, OperationType.AND);
                  break;
               case "or":
                  this.pC.pC(var8, OperationType.OR);
                  break;
               case "ori":
                  this.pC.pC(var8, OperationType.OR);
                  break;
               case "com":
                  this.pC.ld(var8);
                  break;
               case "neg":
                  this.pC.gp(var8);
                  break;
               case "inc":
                  this.pC.pC(var8);
                  break;
               case "dec":
                  this.pC.A(var8);
                  break;
               case "adiw":
                  this.pC.kS(var8);
                  break;
               case "sbiw":
                  this.pC.wS(var8);
                  break;
               case "sbr":
               case "cbr":
                  throw new RuntimeException("Opcode is not generated by the AvrProcessor class");
               case "sbi":
                  this.pC.oT(var8);
                  break;
               case "cbi":
                  this.pC.fI(var8);
                  break;
               case "lsl":
                  this.pC.WR(var8);
                  break;
               case "lsr":
                  this.pC.vP(var8);
                  break;
               case "rol":
                  this.pC.NS(var8);
                  break;
               case "ror":
                  this.pC.xC(var8);
                  break;
               case "asr":
                  this.pC.ED(var8);
                  break;
               case "swap":
                  this.pC.Sc(var8);
                  break;
               case "bst":
                  this.pC.ah(var8);
                  break;
               case "bld":
                  this.pC.eP(var8);
                  break;
               case "sec":
                  this.pC.pC(var8, 0);
                  break;
               case "clc":
                  this.pC.pC(var8, 0);
                  break;
               case "sez":
                  this.pC.pC(var8, 1);
                  break;
               case "clz":
                  this.pC.pC(var8, 1);
                  break;
               case "sen":
                  this.pC.pC(var8, 2);
                  break;
               case "cln":
                  this.pC.pC(var8, 2);
                  break;
               case "sev":
                  this.pC.pC(var8, 3);
                  break;
               case "clv":
                  this.pC.pC(var8, 3);
                  break;
               case "ses":
                  this.pC.pC(var8, 4);
                  break;
               case "cls":
                  this.pC.pC(var8, 4);
                  break;
               case "seh":
                  this.pC.pC(var8, 5);
                  break;
               case "clh":
                  this.pC.pC(var8, 5);
                  break;
               case "set":
                  this.pC.pC(var8, 6);
                  break;
               case "clt":
                  this.pC.pC(var8, 6);
                  break;
               case "sei":
                  this.pC.pC(var8, 7);
                  break;
               case "cli":
                  this.pC.pC(var8, 7);
                  break;
               case "cp":
               case "cpi":
                  this.pC.kS(var8, false);
                  break;
               case "cpc":
                  this.pC.kS(var8, true);
                  break;
               case "mov":
               case "movw":
               case "ldi":
               case "in":
               case "out":
                  this.A.pC(var8);
                  break;
               case "ld":
               case "ldd":
                  this.A.A(var8);
                  break;
               case "st":
               case "std":
                  this.A.kS(var8);
                  break;
               case "push":
                  this.A.wS(var8);
                  break;
               case "pop":
                  this.A.UT(var8);
                  break;
               case "ret":
                  this.kS.pC(var8);
                  break;
               case "rcall":
               case "call":
                  this.kS.pC(var8, false);
                  break;
               case "icall":
                  this.kS.pC(var8, true);
                  break;
               case "rjmp":
               case "jmp":
                  this.kS.A(var8, false);
                  break;
               case "ijmp":
                  this.kS.A(var8, true);
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
                  this.kS.pC(var8, var12);
                  break;
               case "sbrc":
               case "sbic":
                  this.kS.kS(var8, true);
                  break;
               case "sbrs":
               case "sbis":
                  this.kS.kS(var8, true);
                  break;
               case "cpse":
                  this.kS.A(var8);
                  break;
               default:
                  throw new RuntimeException("Cannot convert instruction: " + var9);
            }

            EUtil.setLowerLevelAddress(var5, var7);
            var2.addAll(var7);
            var5 += var9.getSize();
         }
      } catch (Throwable var15) {
         Aj.error(S.L("Error: Instruction cannot be converted: %s %s"), Formatter.byteArrayToHex(var9.getCode()), var9.format(var5));
         throw var15;
      }
   }

   IEGeneric pC(KD var1, int var2, long var3) {
      return this.pC(var1, var2, var3, null);
   }

   IEGeneric pC(KD var1, int var2, long var3, bO.Av var5) {
      yt var6 = var1.pC()[var2];
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
            if (this.FE) {
               if (var12 == 0) {
                  if (var10 == 26) {
                     return this.sY.part(8);
                  }

                  if (var10 == 27) {
                     return this.sY.slice(8, 16);
                  }

                  if (var10 == 28) {
                     return this.ys.part(8);
                  }

                  if (var10 == 29) {
                     return this.ys.slice(8, 16);
                  }

                  if (var10 == 30) {
                     return this.ld.part(8);
                  }

                  if (var10 == 31) {
                     return this.ld.slice(8, 16);
                  }

                  if (var10 == 95) {
                     return this.ctx.createCompose(this.eP);
                  }
               } else if (var12 == 1) {
                  int var13 = var10 * 2;
                  if (var13 == 26) {
                     return this.sY;
                  }

                  if (var13 == 28) {
                     return this.ys;
                  }

                  if (var13 == 30) {
                     return this.ld;
                  }

                  if (var13 == 93) {
                     return this.E;
                  }
               }
            }

            if (var12 == 0) {
               Assert.a(var11 == 8);
               return this.gp[var10];
            }

            if (var12 == 1) {
               Assert.a(var11 == 16);
               return this.ctx.createCompose(this.gp[var10 * 2], this.gp[var10 * 2 + 1]);
            }
         } else {
            if (var7 == 4098) {
               int var15 = (int)var8 & 0xFF;
               if (this.FE && var15 == 63) {
                  return this.ctx.createCompose(this.eP);
               }

               return this.oT[var15];
            }

            if (var7 == 4097) {
               int var14 = (int)var8 & 0xFF;
               int var18 = (int)var8 >> 8 & 0xFF;
               switch (var14) {
                  case 1:
                  case 2:
                  case 3:
                     var5.pC = var14 == 1 ? 0 : (var14 == 2 ? 1 : -1);
                     var5.A = 26;
                     return this.ctx.createMem(this.wS(), 8);
                  case 4:
                  case 5:
                  case 6:
                     var5.pC = var14 == 4 ? 0 : (var14 == 5 ? 1 : -1);
                     var5.A = 28;
                     return this.ctx.createMem(this.UT(), 8);
                  case 7:
                  case 8:
                  case 9:
                     var5.pC = var14 == 7 ? 0 : (var14 == 8 ? 1 : -1);
                     var5.A = 30;
                     return this.ctx.createMem(this.E(), 8);
                  case 10:
                     return this.ctx.createMem(this.ctx.createOperation(OperationType.ADD, this.UT(), this.ctx.createImm(var18, 16)), 8);
                  case 11:
                     return this.ctx.createMem(this.ctx.createOperation(OperationType.ADD, this.E(), this.ctx.createImm(var18, 16)), 8);
               }
            }
         }

         throw new RuntimeException("TBI: operand conversion: " + var6);
      }
   }

   IEGeneric wS() {
      return (IEGeneric)(this.FE ? this.sY : this.ctx.createCompose(this.gp[26], this.gp[27]));
   }

   IEGeneric UT() {
      return (IEGeneric)(this.FE ? this.ys : this.ctx.createCompose(this.gp[28], this.gp[29]));
   }

   IEGeneric E() {
      return (IEGeneric)(this.FE ? this.ld : this.ctx.createCompose(this.gp[30], this.gp[31]));
   }

   void pC(List var1, IEGeneric var2) {
      var1.add(this.ctx.createAssign(this.WR, this.ctx.createCond(var2, this.hK, this.Er)));
   }

   void A(List var1, IEGeneric var2) {
      var1.add(this.ctx.createAssign(this.NS, var2.msb()));
   }

   void pC(List var1, IEGeneric var2, IEGeneric var3, IEGeneric var4, boolean var5, boolean var6, boolean var7) {
      if (!var5) {
         if (var6) {
            var1.add(this.ctx.createAssign(this.fI, this.ctx.createOperation(OperationType.LT_U, var4, var3)));
         }

         if (var7) {
            var1.add(this.ctx.createAssign(this.ED, this.ctx.createOperation(OperationType.LT_U, var4.part(4), var3.part(4))));
         }
      } else {
         if (var6) {
            var1.add(
               this.ctx
                  .createAssign(
                     this.fI,
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
                     this.ED,
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

   void A(List var1, IEGeneric var2, IEGeneric var3, IEGeneric var4, boolean var5, boolean var6, boolean var7) {
      if (!var5) {
         if (var6) {
            var1.add(this.ctx.createAssign(this.fI, this.ctx.createOperation(OperationType.LT_U, var2, var3)));
         }

         if (var7) {
            var1.add(this.ctx.createAssign(this.ED, this.ctx.createOperation(OperationType.LT_U, var2.part(4), var3.part(4))));
         }
      } else {
         if (var6) {
            var1.add(
               this.ctx
                  .createAssign(
                     this.fI,
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
                     this.ED,
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

   void pC(List var1, IEGeneric var2, IEGeneric var3, IEGeneric var4) {
      var1.add(this.ctx.createAssign(this.vP, EUtil.buildOverflowFlag(var2, var3, var4, true)));
   }

   void A(List var1, IEGeneric var2, IEGeneric var3, IEGeneric var4) {
      var1.add(this.ctx.createAssign(this.vP, EUtil.buildOverflowFlag(var2, var3, var4, false)));
   }

   void kS(List var1, IEGeneric var2) {
      var1.add(this.ctx.createAssign(this.xC, this.ctx.createOperation(OperationType.XOR, this.NS, this.vP)));
   }

   IEGeneric wS(List var1, IEGeneric var2) {
      IEVar var3 = this.UO;
      if (var2.getBitsize() == 16) {
         var3 = this.Ab;
      } else if (var2.getBitsize() != 8) {
         throw new RuntimeException("The source operand must be 8-bit or 16-bit wide");
      }

      var1.add(this.ctx.createAssign(var3, var2));
      return var3;
   }

   IEGeneric pC(List var1, IEGeneric var2, IEGeneric var3) {
      var1.add(this.ctx.createAssign(var2, var3));
      return var2;
   }

   void UT(List var1, IEGeneric var2) {
      var1.add(this.ctx.createBranchAssign(this.UT, var2, false));
   }

   void E(List var1, IEGeneric var2) {
      var1.add(this.ctx.createBranchAssign(this.UT, var2, true));
   }

   IEImm pC(long var1) {
      return this.ctx.createImm(var1, this.getAddressBitsize());
   }
}
