package com.pnfsoftware.jeb.corei.parsers.simatic;

import com.pnfsoftware.jeb.core.JebCoreService;
import com.pnfsoftware.jeb.core.units.INativeCodeUnit;
import com.pnfsoftware.jeb.core.units.code.ICodePointer;
import com.pnfsoftware.jeb.core.units.code.IFlowInformation;
import com.pnfsoftware.jeb.core.units.code.asm.cfg.CFG;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.AbstractConverter;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ConverterInstructionEntry;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.IERoutineContext;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.EUtil;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.FunctionOptype;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.IEAssign;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.IEGeneric;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.IEImm;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.IEMem;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.IEOperation;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.IEUntranslatedInstruction;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.IEVar;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.OperationType;
import com.pnfsoftware.jeb.core.units.code.asm.items.INativeMethodItem;
import com.pnfsoftware.jeb.core.units.code.asm.type.INativeType;
import com.pnfsoftware.jeb.core.units.code.asm.type.IPrototypeItem;
import com.pnfsoftware.jeb.core.units.code.asm.type.IReferenceType;
import com.pnfsoftware.jeb.core.units.code.simatic.S7;
import com.pnfsoftware.jeb.util.base.Assert;
import com.pnfsoftware.jeb.util.collect.ReferenceCounter;
import com.pnfsoftware.jeb.util.logging.GlobalLog;
import com.pnfsoftware.jeb.util.logging.ILogger;
import com.pnfsoftware.jeb.util.serialization.annotations.Ser;
import com.pnfsoftware.jeb.util.serialization.annotations.SerId;
import com.pnfsoftware.jebglobal.C;
import java.util.ArrayList;
import java.util.List;

@Ser
public class Sv extends AbstractConverter {
   private static final ILogger VD = GlobalLog.getLogger(Sv.class);
   @SerId(1)
   ReferenceCounter pC;
   @SerId(2)
   int A;
   @SerId(3)
   int kS;
   @SerId(4)
   int wS;
   @SerId(11)
   gb UT;
   @SerId(12)
   C E;
   @SerId(20)
   IEVar sY;
   @SerId(21)
   IEVar ys;
   @SerId(30)
   IEVar ld;
   @SerId(31)
   IEVar gp;
   @SerId(32)
   IEVar oT;
   @SerId(33)
   IEVar fI;
   @SerId(34)
   IEVar WR;
   @SerId(35)
   IEVar NS;
   @SerId(36)
   IEVar vP;
   @SerId(37)
   IEVar xC;
   @SerId(38)
   IEVar ED;
   @SerId(40)
   IEVar Sc;
   @SerId(41)
   IEVar ah;
   @SerId(42)
   IEVar eP;
   @SerId(43)
   IEVar UO;
   @SerId(50)
   IEVar Ab;
   @SerId(51)
   IEVar rl;
   @SerId(54)
   IEVar z;
   @SerId(55)
   IEVar Ek;
   @SerId(58)
   IEVar hK;
   @SerId(59)
   IEVar Er;
   @SerId(60)
   IEVar FE;
   @SerId(61)
   IEVar Aj;
   @SerId(62)
   IEVar EX;
   @SerId(63)
   IEVar LM;
   @SerId(64)
   IEVar mv;
   @SerId(68)
   IEVar sO;
   @SerId(69)
   IEVar os;
   @SerId(70)
   IEVar Cu;
   @SerId(71)
   IEVar hZ;
   @SerId(72)
   IEVar UW;
   @SerId(73)
   IEVar PR;
   @SerId(74)
   IEVar cX;
   @SerId(75)
   IEVar DQ;
   @SerId(76)
   IEVar[] ZN;
   @SerId(77)
   IEVar OB;
   @SerId(80)
   IEVar pF;
   @SerId(81)
   IEVar Bc;
   @SerId(82)
   IEVar OI;
   @SerId(83)
   IEVar Bf;
   @SerId(84)
   IEVar Pe;
   @SerId(85)
   IEVar ck;
   @SerId(86)
   IEVar RW;
   @SerId(87)
   IEVar e;
   @SerId(88)
   IEVar xM;
   @SerId(89)
   IEVar kU;
   @SerId(90)
   IEVar Kq;
   @SerId(100)
   IEImm go;
   @SerId(101)
   IEImm JF;
   @SerId(102)
   IEImm Nq;
   @SerId(103)
   IEImm pg;
   @SerId(104)
   IEImm gj;
   @SerId(105)
   IEImm ZD;
   @SerId(106)
   IEImm DL;
   @SerId(107)
   IEImm UH;

   public Sv(gb var1, INativeCodeUnit var2) {
      super(var2.getProcessor(), 32);
      this.UT = var1;
      this.kS();
   }

   private void kS() {
      this.sY = this.gCtx.createRegister(0, "PC", this.regNormalBitsize);
      this.ys = this.gCtx.createRegister(576, "SP", this.regNormalBitsize);
      this.ld = this.gCtx.createRegister(128, "FC", 1);
      this.gp = this.gCtx.createRegister(129, "RLO", 1);
      this.oT = this.gCtx.createRegister(130, "STA", 1);
      this.fI = this.gCtx.createRegister(131, "OR", 1);
      this.WR = this.gCtx.createRegister(132, "OS", 1);
      this.NS = this.gCtx.createRegister(133, "OV", 1);
      this.vP = this.gCtx.createRegister(134, "CC0", 1);
      this.xC = this.gCtx.createRegister(135, "CC1", 1);
      this.ED = this.gCtx.createRegister(136, "BR", 1);
      this.Sc = this.gCtx.createRegister(768, "ACCU1", 32);
      this.ah = this.gCtx.createRegister(832, "ACCU2", 32);
      this.Ab = this.gCtx.createRegister(1024, "AR1", 32);
      this.rl = this.gCtx.createRegister(1088, "AR2", 32);
      this.z = this.gCtx.createRegister(1280, "DBR1", 32);
      this.Ek = this.gCtx.createRegister(1344, "DBR2", 32);
      this.sO = this.gCtx.createRegister(256, "rI", 32);
      this.os = this.gCtx.createRegister(320, "rQ", 32);
      this.Cu = this.gCtx.createRegister(384, "rM", 32);
      this.hZ = this.gCtx.createRegister(448, "rDB", 32);
      this.UW = this.gCtx.createRegister(512, "rDI", 32);
      this.PR = this.ys;
      this.cX = this.gCtx.createRegister(640, "rC", 32);
      this.DQ = this.gCtx.createRegister(704, "rT", 32);
      this.sO.setFlags(16);
      this.os.setFlags(16);
      this.Cu.setFlags(16);
      this.cX.setFlags(16);
      this.DQ.setFlags(16);
      this.ZN = new IEVar[16];

      for (int var1 = 0; var1 < 16; var1++) {
         this.ZN[var1] = this.gCtx.createRegister("PAR" + var1, 32);
      }

      this.OB = this.gCtx.createRegister("LR", 32);
      this.hK = this.gCtx.createRegister("rtabOB", 32);
      this.Er = this.gCtx.createRegister("rtabFB", 32);
      this.FE = this.gCtx.createRegister("rtabFC", 32);
      this.Aj = this.gCtx.createRegister("rtabDB", 32);
      this.EX = this.gCtx.createRegister("rtabSFB", 32);
      this.LM = this.gCtx.createRegister("rtabSFC", 32);
      this.mv = this.gCtx.createRegister("rtabSDB", 32);
      this.pF = this.gCtx.createVirtualRegister("tmp1", 1);
      this.Bc = this.gCtx.createVirtualRegister("tmp8", 8);
      this.OI = this.gCtx.createVirtualRegister("tmp16", 16);
      this.Bf = this.gCtx.createVirtualRegister("tmp32", 32);
      this.Pe = this.gCtx.createVirtualRegister("tmp64", 64);
      this.ck = this.gCtx.createVirtualRegister("tmp16_2", 16);
      this.RW = this.gCtx.createVirtualRegister("tmp32_2", 32);
      this.e = this.gCtx.createVirtualRegister("tmp64_2", 64);
      this.xM = this.gCtx.createVirtualRegister("PTR", 32);
      this.kU = this.gCtx.createVirtualRegister("ADDR", 32);
      this.Kq = this.gCtx.createVirtualRegister("SH", 32);
      this.go = EUtil.zero(1);
      this.JF = EUtil.one(1);
      this.Nq = EUtil.zero(8);
      this.pg = EUtil.one(8);
      this.gj = EUtil.zero(16);
      this.ZD = EUtil.one(16);
      this.DL = EUtil.zero(32);
      this.UH = EUtil.one(32);
   }

   @Override
   public IEVar getProgramCounter() {
      return this.sY;
   }

   @Override
   public IEVar getStackPointer() {
      return this.ys;
   }

   public IEVar pC(int var1) {
      switch (var1) {
         case 1:
            return this.pF;
         case 16:
            return this.OI;
         case 32:
            return this.Bf;
         case 64:
            return this.Pe;
         default:
            throw new RuntimeException();
      }
   }

   public IEVar A(int var1) {
      switch (var1) {
         case 1:
            return this.Sc;
         case 2:
            return this.ah;
         case 3:
            return this.eP;
         case 4:
            return this.UO;
         default:
            throw new RuntimeException();
      }
   }

   IEGeneric kS(int var1) {
      return this.A(var1).slice(0, 16);
   }

   IEGeneric wS(int var1) {
      return this.A(var1).slice(0, 8);
   }

   public IEVar UT(int var1) {
      switch (var1) {
         case 1:
            return this.Ab;
         case 2:
            return this.rl;
         default:
            throw new RuntimeException();
      }
   }

   public boolean pC() {
      return false;
   }

   public boolean A() {
      return true;
   }

   @Override
   protected void preRoutineConversion(INativeMethodItem var1, IERoutineContext var2, List var3) {
      super.preRoutineConversion(var1, var2, var3);
      CFG var4 = var2.getRoutine().getData().getCFG();
      long var5 = var4.getEntryAddress();
      IEAssign var7 = var2.createAssign(this.PR, EUtil.sub(this.PR, EUtil.imm(65536L, 32)));
      var7.setLowerLevelAddress(var5);
      var3.add(var7);
   }

   @Override
   public void convertInstruction(ConverterInstructionEntry var1) {
      yt var2 = (yt)var1.insn;
      String var3 = var2.getMnemonic();
      if (var2.getCountOfOperands() >= 1) {
         int var4 = ((RC)var2.getOperand(0)).A();
         if (var4 == 9 || var4 == 8) {
            Object var7 = null;
            if (!var3.equals("L") && !var3.equals("LC")) {
               String var8 = (var4 == 9 ? "__COUNTER_" : "__TIMER_") + var3;
               this.pC(var1, var8, this.Ab(var1), (IEGeneric)var7);
            } else {
               this.A(var1, var4 == 8, var3.equals("LC"));
            }

            return;
         }
      }

      switch (var3) {
         case "A":
            this.pC(var1, OperationType.AND, false);
            break;
         case "AN":
            this.pC(var1, OperationType.AND, true);
            break;
         case "O":
            this.pC(var1, OperationType.OR, false);
            break;
         case "ON":
            this.pC(var1, OperationType.OR, true);
            break;
         case "X":
            this.pC(var1, OperationType.XOR, false);
            break;
         case "XN":
            this.pC(var1, OperationType.XOR, true);
            break;
         case "FN":
            this.pC(var1, false);
            break;
         case "FP":
            this.pC(var1, true);
            break;
         case "=":
            this.pC(var1);
            break;
         case "R":
            this.kS(var1);
            break;
         case "S":
            this.A(var1);
            break;
         case "NOT":
            this.pC(var1, this.gp, EUtil.notB(this.gp));
            break;
         case "SET":
            this.A(var1, true);
            break;
         case "CLR":
            this.A(var1, false);
            break;
         case "SAVE":
            this.pC(var1, this.ED, this.gp);
            break;
         case ">I":
            this.pC(var1, 16, OperationType.GT_S);
            break;
         case "<I":
            this.pC(var1, 16, OperationType.LT_S);
            break;
         case "<>I":
            this.pC(var1, 16, OperationType.LOG_NEQ);
            break;
         case "==I":
            this.pC(var1, 16, OperationType.LOG_EQ);
            break;
         case ">=I":
            this.pC(var1, 16, OperationType.GE_S);
            break;
         case "<=I":
            this.pC(var1, 16, OperationType.LE_S);
            break;
         case ">D":
            this.pC(var1, 32, OperationType.GT_S);
            break;
         case "<D":
            this.pC(var1, 32, OperationType.LT_S);
            break;
         case "<>D":
            this.pC(var1, 32, OperationType.LOG_NEQ);
            break;
         case "==D":
            this.pC(var1, 32, OperationType.LOG_EQ);
            break;
         case ">=D":
            this.pC(var1, 32, OperationType.GE_S);
            break;
         case "<=D":
            this.pC(var1, 32, OperationType.LE_S);
            break;
         case ">R":
            this.pC(var1, 32, OperationType.FGT);
            break;
         case "<R":
            this.pC(var1, 32, OperationType.FLT);
            break;
         case "<>R":
            this.pC(var1, 32, OperationType.FNE);
            break;
         case "==R":
            this.pC(var1, 32, OperationType.FEQ);
            break;
         case ">=R":
            this.pC(var1, 32, OperationType.FGE);
            break;
         case "<=R":
            this.pC(var1, 32, OperationType.FLE);
            break;
         case "BTI":
            this.wS(var1, 16);
            break;
         case "BTD":
            this.wS(var1, 32);
            break;
         case "ITB":
            this.UT(var1, 16);
            break;
         case "DTB":
            this.UT(var1, 32);
            break;
         case "ITD":
            this.pC(var1, this.Sc, this.Sc.part(16).signExtend(32));
            break;
         case "DTR":
            this.ys(var1);
            break;
         case "INVI":
            this.pC(var1, this.kS(1), EUtil.notB(this.kS(1)));
            break;
         case "INVD":
            this.pC(var1, this.Sc, EUtil.notB(this.Sc));
            break;
         case "NEGI":
            this.sY(var1, 16);
            break;
         case "NEGD":
            this.sY(var1, 32);
            break;
         case "NEGR":
            this.pC(var1, this.Sc, this.ctx.createCompose(this.Sc.part(31), EUtil.notB(this.Sc.msb())));
            break;
         case "CAW":
            this.ys(var1, 16);
            break;
         case "CAD":
            this.ys(var1, 32);
            break;
         case "RND":
            this.E(var1, 0);
            break;
         case "TRUNC":
            this.E(var1, 1);
            break;
         case "RND+":
            this.E(var1, 2);
            break;
         case "RND-":
            this.E(var1, 3);
            break;
         case "OPN":
            this.fI(var1);
            break;
         case "CDB":
            this.WR(var1);
            break;
         case "JU":
            this.ld(var1);
            break;
         case "JL":
            this.gp(var1);
            break;
         case "JC":
            this.pC(var1, false, false);
            break;
         case "JCN":
            this.pC(var1, true, false);
            break;
         case "JCB":
            this.pC(var1, false, true);
            break;
         case "JNB":
            this.pC(var1, true, true);
            break;
         case "JBI":
            this.kS(var1, false);
            break;
         case "JNBI":
            this.kS(var1, true);
            break;
         case "JO":
            this.wS(var1, false);
            break;
         case "JOS":
            this.UT(var1, false);
            break;
         case "JZ":
            this.ld(var1, 0);
            break;
         case "JN":
            this.ld(var1, 1);
            break;
         case "JP":
            this.ld(var1, 2);
            break;
         case "JM":
            this.ld(var1, 3);
            break;
         case "JPZ":
            this.ld(var1, 4);
            break;
         case "JMZ":
            this.ld(var1, 5);
            break;
         case "JUO":
            this.ld(var1, 6);
            break;
         case "LOOP":
            this.oT(var1);
            break;
         case "+":
            this.sY(var1);
            break;
         case "+I":
            this.pC(var1, 16, true);
            break;
         case "-I":
            this.pC(var1, 16, false);
            break;
         case "*I":
            this.kS(var1, 16);
            break;
         case "/I":
            this.A(var1, 16, false);
            break;
         case "+D":
            this.pC(var1, 32, true);
            break;
         case "-D":
            this.pC(var1, 32, false);
            break;
         case "*D":
            this.kS(var1, 32);
            break;
         case "/D":
            this.A(var1, 32, false);
            break;
         case "MOD":
            this.A(var1, 32, true);
            break;
         case "+R":
            this.pC(var1, OperationType.FADD);
            break;
         case "-R":
            this.pC(var1, OperationType.FSUB);
            break;
         case "*R":
            this.pC(var1, OperationType.FMUL);
            break;
         case "/R":
            this.pC(var1, OperationType.FDIV);
            break;
         case "ABS":
            this.pC(var1, this.Sc, this.ctx.createCompose(this.Sc.part(31), this.go));
            break;
         case "SQR":
         case "SQRT":
         case "EXP":
         case "LN":
         case "SIN":
         case "COS":
         case "TAN":
         case "ASIN":
         case "ACOS":
         case "ATAN":
            this.pC(var1, var3);
            break;
         case "L":
            this.wS(var1);
            break;
         case "LAR1":
            this.pC(var1, 1);
            break;
         case "LAR2":
            this.pC(var1, 2);
            break;
         case "T":
            this.UT(var1);
            break;
         case "TAR1":
            this.A(var1, 1);
            break;
         case "TAR2":
            this.A(var1, 2);
            break;
         case "CAR":
            this.E(var1);
            break;
         case "BE":
         case "BEU":
            this.E(var1, false);
            break;
         case "BEC":
            this.E(var1, true);
            break;
         case "UC":
            this.sY(var1, false);
            break;
         case "CC":
            this.sY(var1, true);
            break;
         case "SLW":
            this.A(var1, OperationType.SHL, 16);
            break;
         case "SLD":
            this.A(var1, OperationType.SHL, 32);
            break;
         case "SRW":
            this.A(var1, OperationType.SHR, 16);
            break;
         case "SRD":
            this.A(var1, OperationType.SHR, 32);
            break;
         case "SSI":
            this.A(var1, OperationType.SAR, 16);
            break;
         case "SSD":
            this.A(var1, OperationType.SAR, 32);
            break;
         case "RLD":
            this.A(var1, OperationType.ROL);
            break;
         case "RRD":
            this.A(var1, OperationType.ROR);
            break;
         case "RLDA":
            this.kS(var1, OperationType.ROL);
            break;
         case "RRDA":
            this.kS(var1, OperationType.ROR);
            break;
         case "AW":
            this.pC(var1, OperationType.AND, 16);
            break;
         case "OW":
            this.pC(var1, OperationType.OR, 16);
            break;
         case "XOW":
            this.pC(var1, OperationType.XOR, 16);
            break;
         case "AD":
            this.pC(var1, OperationType.AND, 32);
            break;
         case "OD":
            this.pC(var1, OperationType.OR, 32);
            break;
         case "XOD":
            this.pC(var1, OperationType.XOR, 32);
            break;
         case "TAK":
            this.NS(var1);
            break;
         case "PUSH":
            this.vP(var1);
            break;
         case "POP":
            this.vP(var1);
            break;
         case "ENT":
            this.xC(var1);
            break;
         case "LEAVE":
            this.ED(var1);
            break;
         case "INC":
            this.Sc(var1);
            break;
         case "DEC":
            this.ah(var1);
            break;
         case "+AR1":
            this.gp(var1, 1);
            break;
         case "+AR2":
            this.gp(var1, 2);
            break;
         case "NOP":
            this.eP(var1);
            break;
         case "BLD":
            this.eP(var1);
            break;
         default:
            RuntimeException var6 = new RuntimeException("Failed conversion for MC7 opcode: " + var3);
            this.pC(var1, var3, this.Ab(var1), null);
            JebCoreService.notifySilentExceptionToClient(var6, this.UT);
      }
   }

   private void pC(ConverterInstructionEntry var1, IEGeneric var2, IEGeneric var3) {
      var1.r.add(this.ctx.createAssign(var2, var3));
   }

   private void pC(ConverterInstructionEntry var1, IEGeneric var2) {
      var1.r.add(this.ctx.createBranchAssign(this.sY, var2, false));
   }

   private void pC(ConverterInstructionEntry var1, int var2, IEGeneric var3) {
      var1.r.add(this.ctx.createJump(var2, var3));
   }

   private void oT(ConverterInstructionEntry var1, int var2) {
      var1.r.add(this.ctx.createJump(var2));
   }

   private void eP(ConverterInstructionEntry var1) {
      var1.r.add(this.ctx.createNop());
   }

   private void UO(ConverterInstructionEntry var1) {
      var1.r.add(this.ctx.createReturn());
   }

   private void pC(ConverterInstructionEntry var1, String var2, IEGeneric var3, IEGeneric var4) {
      IEUntranslatedInstruction var5 = this.ctx.createUntranslatedInstruction(var1.address, var2);
      if (var3 != null) {
         var5.setParameterExpressions(var3);
      }

      if (var4 != null) {
         var5.setReturnExpression(var4);
      }

      var1.r.add(var5);
   }

   private IEVar E(int var1) {
      switch (var1) {
         case 1:
            return this.sO;
         case 2:
            return this.os;
         case 3:
            return this.Cu;
         case 4:
            return this.hZ;
         case 5:
            return this.UW;
         case 6:
            return this.PR;
         default:
            throw new RuntimeException("Cannot convert area code: " + var1);
      }
   }

   private IEGeneric Ab(ConverterInstructionEntry var1) {
      return this.pC(var1, null);
   }

   private IEGeneric pC(ConverterInstructionEntry var1, IEGeneric[] var2) {
      long var3 = var1.address;
      yt var5 = (yt)var1.insn;
      if (var5.getCountOfOperands() == 0) {
         return null;
      } else {
         RC var6 = (RC)var5.getOperand(0);
         int var7 = var6.pC();
         if (var7 == 768) {
            long var19 = var6.pC(var3);
            return EUtil.imm(var19, 32);
         } else if (var7 == 256) {
            int[] var18 = var6.sY();
            IEVar var22 = this.E(var6.A());
            IEMem var25 = this.ctx.createMem(EUtil.add(var22, EUtil.imm(var18[0], 32)), 8);
            if (var2 != null) {
               var2[0] = EUtil.imm(var18[1], 8);
               return var25;
            } else {
               return var25.bit(var18[1]);
            }
         } else if (var7 == 1792) {
            boolean var21 = false;
            int var24 = var6.A();

            byte var17 = switch (var24) {
               case 4, 8, 9, 10, 11, 14 -> 16;
               case 65 -> {
                  var21 = true;
                  yield 1;
               }
               case 66 -> {
                  var21 = true;
                  yield 8;
               }
               case 67 -> {
                  var21 = true;
                  yield 16;
               }
               case 68 -> {
                  var21 = true;
                  yield 32;
               }
               case 74 -> 0;
               default -> throw new RuntimeException("TBD param conversion for area=" + var24);
            };
            Assert.a((var6.kS() & 1) == 0);
            int var27 = var6.kS() / 2 - 1;
            IEVar var29 = this.ZN[var27];
            if (var21) {
               if (var17 == 1 && var2 != null) {
                  var2[0] = this.go;
                  return this.ctx.createMem(var29, 8);
               } else {
                  return this.ctx.createMem(var29, var17);
               }
            } else {
               return (IEGeneric)(var17 == 0 ? var29 : var29.part(var17));
            }
         } else if (var6.wS()) {
            FunctionOptype var20 = this.gCtx.createFunctionType("ExtractOff", 0, 1, 32);
            FunctionOptype var23 = this.gCtx.createFunctionType("ExtractBit", 0, 1, 32);
            FunctionOptype var26 = this.gCtx.createFunctionType("ToNP", 0, 1, 32);
            int var28 = var6.UT();
            Object var16;
            if (var6.ys() == 1) {
               if (var28 != 32 && var28 != 33) {
                  IEVar var33 = this.E(var28);
                  IEOperation var35 = EUtil.add(var33, EUtil.imm(var6.kS(), 32));
                  this.pC(var1, this.xM, this.ctx.createMem(var35, 32));
                  this.pC(var1, this.kU, EUtil.add(this.E(var6.A()), this.ctx.createOperation(var20, this.xM)));
               } else {
                  IEVar var32 = var28 == 32 ? this.Ab : this.rl;
                  this.pC(var1, this.xM, EUtil.add(var32, EUtil.imm(var6.kS(), 32)));
                  if (var6.A() == 0) {
                     this.pC(var1, this.kU, this.ctx.createOperation(var26, this.xM));
                  } else {
                     this.pC(var1, this.kU, EUtil.add(this.E(var6.A()), this.ctx.createOperation(var20, this.xM)));
                  }
               }

               this.pC(var1, this.Kq, this.ctx.createOperation(var23, this.xM));
               if (var2 != null) {
                  var16 = this.ctx.createMem(this.kU, 8);
                  var2[0] = this.Kq;
               } else {
                  var16 = EUtil.shr(this.ctx.createMem(this.kU, 8), this.Kq).lsb();
               }
            } else if (var28 != 32 && var28 != 33) {
               IEVar var31 = this.E(var28);
               IEOperation var34 = EUtil.add(var31, EUtil.imm(var6.kS(), 32));
               int var15 = var6.A();
               if (var15 == 8 || var15 == 9 || var15 == 36 || var15 == 37 || var15 == 10 || var15 == 11 || var15 == 12 || var15 == 13) {
                  return this.ctx.createMem(var34, 16);
               }

               this.pC(var1, this.xM, this.ctx.createMem(var34, 32));
               var16 = this.ctx.createMem(EUtil.add(this.E(var6.A()), this.ctx.createOperation(var20, this.xM)), var6.ys());
            } else {
               IEVar var30 = var28 == 32 ? this.Ab : this.rl;
               this.pC(var1, this.xM, EUtil.add(var30, EUtil.imm(var6.kS(), 32)));
               if (var6.A() == 0) {
                  var16 = this.ctx.createMem(this.ctx.createOperation(var26, this.xM), var6.ys());
               } else {
                  var16 = this.ctx.createMem(EUtil.add(this.E(var6.A()), this.ctx.createOperation(var20, this.xM)), var6.ys());
               }
            }

            return (IEGeneric)var16;
         } else {
            IEImm var8 = null;
            int var9 = var6.ld();
            if (var9 > 0) {
               var8 = EUtil.imm(var6.kS(), var9);
            }

            int var10 = var6.A();
            int var11 = 0;
            byte var12 = 1;
            if (var10 != 0) {
               IEVar var13 = null;
               switch (var10) {
                  case 1:
                     var13 = this.sO;
                     break;
                  case 2:
                     var13 = this.os;
                     break;
                  case 3:
                     var13 = this.Cu;
                     break;
                  case 4:
                     var13 = this.hZ;
                     break;
                  case 5:
                     var13 = this.UW;
                     break;
                  case 6:
                     var13 = this.PR;
               }

               if (var13 == null) {
                  switch (var10) {
                     case 8:
                     case 9:
                     case 10:
                     case 11:
                     case 12:
                     case 13:
                     case 15:
                     case 34:
                     case 35:
                     case 36:
                     case 37:
                        return var8;
                     case 14:
                     case 16:
                     case 17:
                     case 18:
                     case 19:
                     case 20:
                     case 21:
                     case 22:
                     case 23:
                     case 24:
                     case 29:
                     case 30:
                     case 31:
                     case 38:
                     case 39:
                     case 40:
                     case 41:
                     case 42:
                     case 43:
                     case 44:
                     case 45:
                     case 46:
                     case 47:
                     case 48:
                     default:
                        throw new RuntimeException("TBI: opnd area " + var10);
                     case 25:
                        return this.WR;
                     case 26:
                        return this.NS;
                     case 27:
                        return EUtil.andB(this.xC, this.vP);
                     case 28:
                        return this.ED;
                     case 32:
                        return this.Ab;
                     case 33:
                        return this.rl;
                     case 49:
                        return EUtil.andB(this.xC, EUtil.notB(this.vP));
                     case 50:
                        return EUtil.andB(EUtil.notB(this.xC), this.vP);
                     case 51:
                        return EUtil.xorB(this.xC, this.vP);
                     case 52:
                        return EUtil.andB(EUtil.notB(this.xC), EUtil.notB(this.vP));
                     case 53:
                        return EUtil.notB(this.vP);
                     case 54:
                        return EUtil.notB(this.xC);
                  }
               } else {
                  if (var11 == 0) {
                     var11 = var6.ys();
                     if (var11 == 0) {
                        var11 = 16;
                     }

                     if (var10 == 11 || var10 == 10) {
                        var11 = 32;
                     }
                  }

                  Object var14 = var8.zeroExtend(var13.getBitsize());
                  if (var12 != 1) {
                     var14 = EUtil.mul((IEGeneric)var14, EUtil.imm(var12, ((IEGeneric)var14).getBitsize()));
                  }

                  return this.ctx.createMem(EUtil.add(var13, (IEGeneric)var14), var11);
               }
            } else if (var10 == 0) {
               return var8;
            } else {
               throw new RuntimeException("TBI: opnd= " + var6);
            }
         }
      }
   }

   void pC(ConverterInstructionEntry var1, OperationType var2, boolean var3) {
      IEGeneric var4 = this.Ab(var1);
      if (var4 == null) {
         Assert.a(var2 == OperationType.OR);
         throw new RuntimeException("TBI: And before Or");
      } else {
         if (var3) {
            var4 = EUtil.notB(var4);
         }

         this.pC(var1, var1.reljmp(4), EUtil.eq(this.ld, this.JF));
         this.pC(var1, this.ld, this.JF);
         this.pC(var1, this.gp, var4);
         this.oT(var1, var1.reljmp(2));
         this.pC(var1, this.gp, EUtil.op(var2, this.gp, var4));
      }
   }

   void pC(ConverterInstructionEntry var1, boolean var2) {
      IEGeneric var3 = this.Ab(var1);
      this.pC(var1, var1.reljmp(4), EUtil.eq(this.ld, this.JF));
      this.pC(var1, this.ld, this.JF);
      this.pC(var1, this.gp, var3);
      this.oT(var1, var1.reljmp(2));
      this.pC(var1, this.gp, var2 ? EUtil.andB(EUtil.notB(var3), this.gp) : EUtil.andB(var3, EUtil.notB(this.gp)));
   }

   void pC(ConverterInstructionEntry var1) {
      IEGeneric[] var2 = new IEGeneric[1];
      IEGeneric var3 = this.pC(var1, var2);
      this.pC(var1, this.fI, this.go);
      this.pC(var1, this.ld, this.go);
      IEOperation var4 = EUtil.orB(EUtil.andB(var3, EUtil.notB(EUtil.shl(this.pg, var2[0]))), EUtil.shl(this.gp.zeroExtend(8), var2[0]));
      this.pC(var1, var3, var4);
   }

   void A(ConverterInstructionEntry var1) {
      if (((RC)((yt)var1.insn).getOperand(0)).A() == 9) {
         throw new RuntimeException();
      } else {
         IEGeneric[] var2 = new IEGeneric[1];
         IEGeneric var3 = this.pC(var1, var2);
         this.pC(var1, this.fI, this.go);
         this.pC(var1, this.ld, this.go);
         this.pC(var1, var1.reljmp(2), EUtil.eq(this.gp, this.go));
         IEOperation var4 = EUtil.orB(var3, EUtil.shl(this.pg, var2[0]));
         this.pC(var1, var3, var4);
      }
   }

   void kS(ConverterInstructionEntry var1) {
      if (((RC)((yt)var1.insn).getOperand(0)).A() == 9) {
         throw new RuntimeException();
      } else {
         IEGeneric[] var2 = new IEGeneric[1];
         IEGeneric var3 = this.pC(var1, var2);
         this.pC(var1, this.fI, this.go);
         this.pC(var1, this.ld, this.go);
         this.pC(var1, var1.reljmp(2), EUtil.eq(this.gp, this.go));
         IEOperation var4 = EUtil.andB(var3, EUtil.notB(EUtil.shl(this.pg, var2[0])));
         this.pC(var1, var3, var4);
      }
   }

   void A(ConverterInstructionEntry var1, boolean var2) {
      this.pC(var1, this.fI, this.go);
      this.pC(var1, this.ld, this.go);
      this.pC(var1, this.gp, var2 ? this.JF : this.go);
   }

   void wS(ConverterInstructionEntry var1) {
      this.pC(var1, this.ah, this.Sc);
      if (((RC)((yt)var1.insn).getOperand(0)).E()) {
         this.pC(var1, this.Sc, EUtil.zero(this.Sc.getBitsize()));
         this.pC(var1, this.Sc.bit(1), this.gp);
         this.pC(var1, this.Sc.bit(4), this.WR);
         this.pC(var1, this.Sc.bit(5), this.NS);
         this.pC(var1, this.Sc.bit(6), this.vP);
         this.pC(var1, this.Sc.bit(7), this.xC);
         this.pC(var1, this.Sc.bit(8), this.ED);
         if (!this.A()) {
            this.pC(var1, this.Sc.bit(0), this.ld);
            this.pC(var1, this.Sc.bit(2), this.oT);
            this.pC(var1, this.Sc.bit(3), this.fI);
         }
      } else {
         int var2 = ((RC)((yt)var1.insn).getOperand(0)).A();
         if (var2 != 34 && var2 != 35) {
            if (var2 != 36 && var2 != 37) {
               IEGeneric var6 = this.Ab(var1);
               this.pC(var1, this.Sc, var6.zeroExtend(this.Sc.getBitsize()));
            } else {
               IEVar var5 = var2 == 36 ? this.z : this.Ek;
               this.pC(var1, this.Sc, var5.zeroExtend(32));
            }
         } else {
            IEVar var3 = var2 == 34 ? this.z : this.Ek;
            IEGeneric var4 = this.A(4, var3);
            this.pC(var1, this.Sc, var4.zeroExtend(32));
         }
      }
   }

   void UT(ConverterInstructionEntry var1) {
      if (((RC)((yt)var1.insn).getOperand(0)).E()) {
         this.pC(var1, this.gp, this.Sc.bit(1));
         this.pC(var1, this.WR, this.Sc.bit(4));
         this.pC(var1, this.NS, this.Sc.bit(5));
         this.pC(var1, this.vP, this.Sc.bit(6));
         this.pC(var1, this.xC, this.Sc.bit(7));
         this.pC(var1, this.ED, this.Sc.bit(8));
         if (!this.A()) {
            this.pC(var1, this.ld, this.Sc.bit(0));
            this.pC(var1, this.oT, this.Sc.bit(2));
            this.pC(var1, this.fI, this.Sc.bit(3));
         }
      } else {
         IEGeneric var2 = this.Ab(var1);
         this.pC(var1, var2, this.Sc.part(var2.getBitsize()));
      }
   }

   void pC(ConverterInstructionEntry var1, int var2) {
      IEGeneric var3 = this.Ab(var1);
      if (var3 == null) {
         this.pC(var1, this.UT(var2), this.Sc);
      } else {
         this.pC(var1, this.UT(var2), var3);
      }
   }

   void A(ConverterInstructionEntry var1, int var2) {
      IEGeneric var3 = this.Ab(var1);
      if (var3 == null) {
         this.pC(var1, this.ah, this.Sc);
         this.pC(var1, this.Sc, this.UT(var2));
      } else {
         this.pC(var1, var3, this.UT(var2));
      }
   }

   void E(ConverterInstructionEntry var1) {
      this.pC(var1, this.Bf, this.Ab);
      this.pC(var1, this.Ab, this.rl);
      this.pC(var1, this.rl, this.Bf);
   }

   void sY(ConverterInstructionEntry var1) {
      IEGeneric var2 = this.Ab(var1);
      if (var2.getBitsize() == 16) {
         this.pC(var1, this.Sc.part(16), EUtil.add(this.Sc.part(16), var2));
      } else if (var2.getBitsize() == 32) {
         this.pC(var1, this.Sc, EUtil.add(this.Sc, var2));
      } else {
         Assert.fail();
      }
   }

   void pC(ConverterInstructionEntry var1, int var2, boolean var3) {
      IEGeneric var4 = this.ah.part(var2);
      IEGeneric var5 = this.Sc.part(var2);
      IEVar var7 = this.pC(var2);
      this.pC(var1, var7, var3 ? EUtil.add(var4, var5) : EUtil.sub(var4, var5));
      IEGeneric var8 = EUtil.buildOverflowFlag(var4, var5, var7, var3);
      this.pC(var1, this.NS, var8);
      this.pC(var1, var1.reljmp(2), EUtil.eq(this.NS, this.go));
      this.pC(var1, this.WR, this.JF);
      this.pC(var1, this.vP, var7.msb());
      this.pC(var1, this.xC, EUtil.andL(EUtil.notB(var7.msb()), EUtil.ne(var7, EUtil.zero(var2))));
      this.pC(var1, var5, var7);
   }

   void kS(ConverterInstructionEntry var1, int var2) {
      int var3 = 2 * var2;
      IEGeneric var4 = this.ah.part(var2).signExtend(var3);
      IEGeneric var5 = this.Sc.part(var2).signExtend(var3);
      IEVar var6 = this.pC(var3);
      int var7 = var1.irAddress + var1.r.size();
      this.pC(var1, var6, EUtil.mul(var4, var5));
      this.pC(var1, var7 + 6, EUtil.ne(var6, EUtil.zero(var3)));
      this.pC(var1, this.NS, this.go);
      this.pC(var1, this.xC, this.go);
      this.pC(var1, this.vP, this.go);
      this.oT(var1, var7 + 24);
      this.pC(var1, var7 + 11, var6.slice(var2 - 1));
      this.pC(var1, this.NS, this.go);
      this.pC(var1, this.xC, this.JF);
      this.pC(var1, this.vP, this.go);
      this.oT(var1, var7 + 24);
      this.pC(var1, var7 + 16, EUtil.eq(var6.slice(var2 - 1), EUtil.minusOne(var2 + 1)));
      this.pC(var1, this.NS, this.go);
      this.pC(var1, this.xC, this.go);
      this.pC(var1, this.vP, this.JF);
      this.oT(var1, var7 + 24);
      this.pC(var1, var7 + 21, var6.bit(var3 - 1));
      this.pC(var1, this.NS, this.JF);
      this.pC(var1, this.xC, this.JF);
      this.pC(var1, this.vP, this.go);
      this.oT(var1, var7 + 24);
      this.pC(var1, this.NS, this.JF);
      this.pC(var1, this.xC, this.go);
      this.pC(var1, this.vP, this.JF);
      this.pC(var1, this.Sc, var6.part(32));
      if (this.pC()) {
         this.pC(var1, this.ah, this.eP);
         this.pC(var1, this.eP, this.UO);
      }
   }

   void A(ConverterInstructionEntry var1, int var2, boolean var3) {
      Assert.a(var2 == 16 || var2 == 32);
      IEGeneric var4 = this.ah.part(var2);
      IEGeneric var5 = this.Sc.part(var2);
      IEVar var6 = this.pC(var2);
      IEImm var7 = EUtil.one(var2)._shl(var2 - 1);
      IEImm var8 = EUtil.minusOne(var2);
      int var9 = var1.irAddress + var1.r.size();
      this.pC(var1, var9 + 6, var5);
      this.pC(var1, this.WR, this.JF);
      this.pC(var1, this.NS, this.JF);
      this.pC(var1, this.xC, this.JF);
      this.pC(var1, this.vP, this.JF);
      this.oT(var1, var9 + 28);
      if (var3) {
         Assert.a(var2 == 32);
         this.pC(var1, var6, EUtil.remS(var4, var5));
         this.eP(var1);
      } else {
         this.pC(var1, var6, EUtil.divS(var4, var5));
         if (var2 == 16) {
            this.pC(var1, this.Sc.slice(16), EUtil.remS(var4, var5));
         } else {
            this.eP(var1);
         }
      }

      this.pC(var1, var9 + 14, EUtil.orL(EUtil.ne(var4, var7), EUtil.ne(var5, var8)));
      this.pC(var1, this.WR, this.JF);
      this.pC(var1, this.NS, this.JF);
      this.pC(var1, this.xC, this.JF);
      this.pC(var1, this.vP, this.go);
      this.oT(var1, var9 + 27);
      this.pC(var1, var9 + 19, var6);
      this.pC(var1, this.NS, this.go);
      this.pC(var1, this.xC, this.go);
      this.pC(var1, this.vP, this.go);
      this.oT(var1, var9 + 27);
      this.pC(var1, var9 + 24, var6.msb());
      this.pC(var1, this.NS, this.go);
      this.pC(var1, this.xC, this.JF);
      this.pC(var1, this.vP, this.go);
      this.oT(var1, var9 + 27);
      this.pC(var1, this.NS, this.go);
      this.pC(var1, this.xC, this.go);
      this.pC(var1, this.vP, this.JF);
      this.pC(var1, this.Sc.part(var2), var6);
      if (this.pC()) {
         this.pC(var1, this.ah, this.eP);
         this.pC(var1, this.eP, this.UO);
      }
   }

   void pC(ConverterInstructionEntry var1, OperationType var2) {
      IEVar var3 = this.ah;
      IEVar var4 = this.Sc;
      this.pC(var1, this.Sc, EUtil.op(var2, var3, var4));
      if (this.pC()) {
         this.pC(var1, this.ah, this.eP);
         this.pC(var1, this.eP, this.UO);
      }
   }

   void pC(ConverterInstructionEntry var1, String var2) {
      FunctionOptype var3 = this.gCtx.createFunctionType(var2, 0, 1, 0);
      IEOperation var4 = this.ctx.createOperation(var3, this.Sc);
      this.pC(var1, this.Sc, var4);
   }

   void pC(ConverterInstructionEntry var1, int var2, OperationType var3) {
      Assert.a(var2 == 16 || var2 == 32);
      IEGeneric var4 = this.ah.part(var2);
      IEGeneric var5 = this.Sc.part(var2);
      this.pC(var1, this.gp, EUtil.op(var3, var4, var5));
      this.pC(var1, this.vP, EUtil.op(OperationType.LT_S, var4, var5));
      this.pC(var1, this.xC, EUtil.op(OperationType.GT_S, var4, var5));
      this.pC(var1, this.fI, this.go);
      this.pC(var1, this.NS, this.go);
   }

   void wS(ConverterInstructionEntry var1, int var2) {
      IEGeneric var3 = this.Sc.part(var2);
      IEVar var4 = this.pC(var2);
      IEImm var5 = EUtil.zero(var2);
      IEImm var6 = EUtil.imm(10L, var2);
      this.pC(var1, var4, var5);
      int var7 = var2 / 4 - 1;
      int var8 = var2 - 8;

      for (int var9 = 0; var9 < var7; var9++) {
         this.pC(var1, var4, EUtil.add(EUtil.mul(var4, var6), var3.slice(var8, var8 + 4).zeroExtend(var2)));
         var8 -= 4;
      }

      this.pC(var1, var3, this.ctx.createCond(var3.msb(), EUtil.sub(var5, var4), var4));
   }

   void UT(ConverterInstructionEntry var1, int var2) {
      IEGeneric var3 = this.Sc.part(var2);
      FunctionOptype var4 = this.gCtx.createFunctionType("IntToBCD", 0, 1, 0);
      int var5 = var1.irAddress + var1.r.size();
      this.pC(var1, var5 + 4, EUtil.andL(EUtil.geS(var3, EUtil.imm(-999L, var2)), EUtil.leS(var3, EUtil.imm(999L, var2))));
      this.pC(var1, this.NS, this.JF);
      this.pC(var1, this.WR, this.JF);
      this.oT(var1, var5 + 5);
      this.pC(var1, var3, this.gCtx.createOperation(var4, var3));
   }

   void ys(ConverterInstructionEntry var1) {
      this.pC(var1, this.Sc, EUtil.createConversionOperation(OperationType.INT2FP, this.Sc, 32));
   }

   void E(ConverterInstructionEntry var1, int var2) {
      this.pC(var1, this.Sc, EUtil.createConversionOperation(OperationType.FP2INT, this.Sc, 32));
   }

   void sY(ConverterInstructionEntry var1, int var2) {
      IEGeneric var3 = this.Sc.part(var2);
      int var4 = var1.irAddress + var1.r.size();
      this.pC(var1, var3, EUtil.add(EUtil.notB(var3), EUtil.one(var2)));
      this.pC(var1, var4 + 6, EUtil.ne(var3, EUtil.zero(var2)));
      this.pC(var1, this.NS, this.go);
      this.pC(var1, this.xC, this.go);
      this.pC(var1, this.vP, this.go);
      this.oT(var1, var4 + 20);
      this.pC(var1, var4 + 11, var3.msb());
      this.pC(var1, this.NS, this.go);
      this.pC(var1, this.xC, this.JF);
      this.pC(var1, this.vP, this.go);
      this.oT(var1, var4 + 20);
      this.pC(var1, var4 + 16, EUtil.eq(var3.part(var2 - 1), EUtil.zero(var2 - 1)));
      this.pC(var1, this.NS, this.go);
      this.pC(var1, this.xC, this.go);
      this.pC(var1, this.vP, this.JF);
      this.oT(var1, var4 + 20);
      this.pC(var1, this.WR, this.JF);
      this.pC(var1, this.NS, this.JF);
      this.pC(var1, this.xC, this.go);
      this.pC(var1, this.vP, this.JF);
   }

   void ys(ConverterInstructionEntry var1, int var2) {
      if (var2 == 16) {
         IEGeneric var3 = this.Sc.part(16);
         this.pC(var1, var3, this.ctx.createCompose(var3.slice(8, 16), var3.slice(0, 8)));
      } else {
         if (var2 != 32) {
            throw new RuntimeException();
         }

         IEGeneric var4 = this.Sc.part(32);
         this.pC(var1, var4, this.ctx.createCompose(var4.slice(24, 32), var4.slice(16, 24), var4.slice(8, 16), var4.slice(0, 8)));
      }
   }

   void ld(ConverterInstructionEntry var1) {
      IEGeneric var2 = this.Ab(var1);
      this.pC(var1, this.sY, var2);
   }

   void gp(ConverterInstructionEntry var1) {
      IFlowInformation var2 = ((yt)var1.insn).getBreakingFlow(var1.address);
      List var3 = var2.getTargets();
      this.pC(var1, this.Bc, this.wS(1));
      int var4 = var1.irAddress + var1.r.size();
      int var5 = 0;

      for (ICodePointer var7 : var3) {
         if (var5 + 1 != var3.size()) {
            this.pC(var1, var4 + 2, EUtil.ne(this.Bc, EUtil.imm(var5, 8)));
         }

         this.pC(var1, EUtil.imm(var7.getAddress(), 32));
         var5++;
         var4 += 2;
      }
   }

   void pC(ConverterInstructionEntry var1, boolean var2, boolean var3) {
      IEGeneric var4 = this.Ab(var1);
      IEImm var5 = EUtil.imm(var1.address + ((yt)var1.insn).getSize(), 32);
      IEVar var6 = var3 ? this.ED : this.pF;
      this.pC(var1, this.fI, this.go);
      this.pC(var1, this.ld, this.go);
      this.pC(var1, var6, this.gp);
      this.pC(var1, this.gp, this.JF);
      var1.r.add(this.ctx.createBranchAssign(this.sY, this.ctx.createCond((IEGeneric)(var2 ? EUtil.notB(var6) : var6), var4, var5), false));
   }

   void kS(ConverterInstructionEntry var1, boolean var2) {
      IEGeneric var3 = this.Ab(var1);
      IEImm var4 = EUtil.imm(var1.address + ((yt)var1.insn).getSize(), 32);
      this.pC(var1, this.fI, this.go);
      this.pC(var1, this.ld, this.go);
      var1.r.add(this.ctx.createBranchAssign(this.sY, this.ctx.createCond((IEGeneric)(var2 ? EUtil.notB(this.ED) : this.ED), var3, var4), false));
   }

   void wS(ConverterInstructionEntry var1, boolean var2) {
      IEGeneric var3 = this.Ab(var1);
      IEImm var4 = EUtil.imm(var1.address + ((yt)var1.insn).getSize(), 32);
      var1.r.add(this.ctx.createBranchAssign(this.sY, this.ctx.createCond((IEGeneric)(var2 ? EUtil.notB(this.NS) : this.NS), var3, var4), false));
   }

   void UT(ConverterInstructionEntry var1, boolean var2) {
      IEGeneric var3 = this.Ab(var1);
      IEImm var4 = EUtil.imm(var1.address + ((yt)var1.insn).getSize(), 32);
      this.pC(var1, this.pF, this.WR);
      this.pC(var1, this.WR, this.go);
      var1.r.add(this.ctx.createBranchAssign(this.sY, this.ctx.createCond((IEGeneric)(var2 ? EUtil.notB(this.pF) : this.pF), var3, var4), false));
   }

   void ld(ConverterInstructionEntry var1, int var2) {
      IEGeneric var3 = this.Ab(var1);
      IEImm var4 = EUtil.imm(var1.address + ((yt)var1.insn).getSize(), 32);
      Object var5 = null;
      if (var2 == 0) {
         var5 = EUtil.notB(EUtil.orB(this.vP, this.xC));
      } else if (var2 == 1) {
         var5 = EUtil.xorB(this.vP, this.xC);
      } else if (var2 == 2) {
         var5 = EUtil.andB(this.xC, EUtil.notB(this.vP));
      } else if (var2 == 3) {
         var5 = EUtil.andB(this.vP, EUtil.notB(this.xC));
      } else if (var2 == 4) {
         var5 = EUtil.notB(this.vP);
      } else if (var2 == 5) {
         var5 = EUtil.notB(this.xC);
      } else if (var2 == 6) {
         var5 = EUtil.andB(this.vP, this.xC);
      } else {
         Assert.fail();
      }

      var1.r.add(this.ctx.createBranchAssign(this.sY, this.ctx.createCond((IEGeneric)var5, var3, var4), false));
   }

   void oT(ConverterInstructionEntry var1) {
      IEGeneric var2 = this.Ab(var1);
      IEImm var3 = EUtil.imm(var1.address + ((yt)var1.insn).getSize(), 32);
      this.pC(var1, this.Sc.part(16), EUtil.sub(this.Sc.part(16), EUtil.one(16)));
      var1.r.add(this.ctx.createBranchAssign(this.sY, this.ctx.createCond(this.Sc.part(16), var2, var3), false));
   }

   void fI(ConverterInstructionEntry var1) {
      int var4 = ((RC)((yt)var1.insn).getOperand(0)).A();
      IEVar var2;
      IEVar var3;
      if (var4 != 4 && var4 != 36) {
         if (var4 != 5 && var4 != 37) {
            throw new RuntimeException();
         }

         var2 = this.Ek;
         var3 = this.UW;
      } else {
         var2 = this.z;
         var3 = this.hZ;
      }

      IEGeneric var5 = this.Ab(var1);
      this.pC(var1, var2, var5.zeroExtend(var2.getBitsize()));
      Object var6 = null;
      if (this.UT != null && var5 instanceof IEImm) {
         long var7 = this.UT.getAddressOfData(S7.BlockType.DB, (int)var5.asImm().getValueAsLong());
         if (var7 != -1L) {
            var6 = EUtil.imm(var7, 32);
         }
      }

      if (var6 == null) {
         var6 = this.pC(4, var2);
      }

      this.pC(var1, var3, (IEGeneric)var6);
   }

   void WR(ConverterInstructionEntry var1) {
      this.pC(var1, this.Bf, this.z);
      this.pC(var1, this.z, this.Ek);
      this.pC(var1, this.Ek, this.Bf);
      this.pC(var1, this.Bf, this.hZ);
      this.pC(var1, this.hZ, this.UW);
      this.pC(var1, this.UW, this.Bf);
   }

   void pC(ConverterInstructionEntry var1, OperationType var2, int var3) {
      IEGeneric var4 = this.Sc.part(var3);
      IEGeneric var5 = this.Ab(var1);
      if (var5 == null) {
         var5 = this.ah.part(var3);
      }

      this.pC(var1, var4, EUtil.op(var2, var4, var5));
      this.pC(var1, this.xC, EUtil.ne(var4, EUtil.zero(var3)));
      this.pC(var1, this.vP, this.go);
      this.pC(var1, this.NS, this.go);
   }

   void NS(ConverterInstructionEntry var1) {
      this.pC(var1, this.Bf, this.Sc);
      this.pC(var1, this.Sc, this.ah);
      this.pC(var1, this.ah, this.Bf);
   }

   void vP(ConverterInstructionEntry var1) {
      if (this.pC()) {
         this.pC(var1, this.UO, this.eP);
         this.pC(var1, this.eP, this.ah);
      }

      this.pC(var1, this.ah, this.Sc);
   }

   void xC(ConverterInstructionEntry var1) {
      if (this.pC()) {
         this.pC(var1, this.UO, this.eP);
         this.pC(var1, this.eP, this.ah);
      }
   }

   void ED(ConverterInstructionEntry var1) {
      if (this.pC()) {
         this.pC(var1, this.ah, this.eP);
         this.pC(var1, this.eP, this.UO);
      }
   }

   void Sc(ConverterInstructionEntry var1) {
      IEGeneric var2 = this.wS(1);
      IEGeneric var3 = this.Ab(var1);
      this.pC(var1, var2, EUtil.add(var2, var3));
   }

   void ah(ConverterInstructionEntry var1) {
      IEGeneric var2 = this.wS(1);
      IEGeneric var3 = this.Ab(var1);
      this.pC(var1, var2, EUtil.sub(var2, var3));
   }

   void gp(ConverterInstructionEntry var1, int var2) {
      IEGeneric var3 = this.UT(var2).part(24);
      IEGeneric var4 = this.Ab(var1);
      if (var4 == null) {
         var4 = this.kS(1).signExtend(24);
      } else {
         Assert.a(var4.getBitsize() == 16);
         var4 = var4.signExtend(24);
      }

      this.pC(var1, var3, EUtil.add(var3, var4));
   }

   void A(ConverterInstructionEntry var1, OperationType var2, int var3) {
      Assert.a(var3 == 16 || var3 == 32);
      IEGeneric var4 = this.Sc.part(var3);
      IEGeneric var5 = this.Ab(var1);
      if (var5 == null) {
         var5 = this.wS(2);
      }

      Assert.a(var5.getBitsize() == 8);
      IEImm var6 = EUtil.imm(var3, 8);
      int var7 = var1.irAddress;
      if (var2 == OperationType.SAR) {
         this.pC(var1, var7 + 9, EUtil.eq(var5, this.Nq));
         this.pC(var1, this.NS, this.go);
         this.pC(var1, this.vP, this.go);
         this.pC(var1, var7 + 7, EUtil.ltU(var5, var6));
         this.pC(var1, this.xC, var4.msb());
         this.pC(var1, var4, this.gCtx.createCond(this.xC, EUtil.minusOne(var3), EUtil.zero(var3)));
         this.oT(var1, var7 + 9);
         this.pC(var1, this.xC, EUtil.op(OperationType.SHR, var4, EUtil.sub(var5, this.pg)).lsb());
         this.pC(var1, var4, EUtil.op(OperationType.SAR, var4, var5));
      } else if (var2 == OperationType.SHR) {
         this.pC(var1, var7 + 9, EUtil.eq(var5, this.Nq));
         this.pC(var1, this.NS, this.go);
         this.pC(var1, this.vP, this.go);
         this.pC(var1, var7 + 7, EUtil.ltU(var5, var6));
         this.pC(var1, this.xC, this.go);
         this.pC(var1, var4, EUtil.zero(var3));
         this.oT(var1, var7 + 9);
         this.pC(var1, this.xC, EUtil.op(OperationType.SHR, var4, EUtil.sub(var5, this.pg)).lsb());
         this.pC(var1, var4, EUtil.op(OperationType.SHR, var4, var5));
      } else if (var2 == OperationType.SHL) {
         this.pC(var1, var7 + 9, EUtil.eq(var5, this.Nq));
         this.pC(var1, this.NS, this.go);
         this.pC(var1, this.vP, this.go);
         this.pC(var1, var7 + 7, EUtil.ltU(var5, var6));
         this.pC(var1, this.xC, this.go);
         this.pC(var1, var4, EUtil.zero(var3));
         this.oT(var1, var7 + 9);
         this.pC(var1, this.xC, EUtil.op(OperationType.SHR, var4, EUtil.sub(var6, var5)).lsb());
         this.pC(var1, var4, EUtil.op(OperationType.SHL, var4, var5));
      } else {
         Assert.fail();
      }
   }

   void A(ConverterInstructionEntry var1, OperationType var2) {
      IEGeneric var3 = this.Sc.part(32);
      IEGeneric var4 = this.Ab(var1);
      if (var4 == null) {
         var4 = this.wS(2);
      }

      Assert.a(var4.getBitsize() == 8);
      IEImm var5 = EUtil.imm(32L, 8);
      int var6 = var1.irAddress;
      IEImm var7 = EUtil.imm(32L, 8)._sub(this.pg);
      if (var2 == OperationType.ROL) {
         this.pC(var1, var6 + 5, EUtil.eq(var4, this.Nq));
         this.pC(var1, this.NS, this.go);
         this.pC(var1, this.vP, this.go);
         this.pC(var1, this.xC, EUtil.op(OperationType.SHR, var3, EUtil.sub(var5, EUtil.andB(var4, var7))).lsb());
         this.pC(var1, var3, EUtil.op(OperationType.ROL, var3, var4));
      } else if (var2 == OperationType.ROR) {
         this.pC(var1, var6 + 5, EUtil.eq(var4, this.Nq));
         this.pC(var1, this.NS, this.go);
         this.pC(var1, this.vP, this.go);
         this.pC(var1, this.xC, EUtil.op(OperationType.SHR, var3, EUtil.andB(EUtil.sub(var4, this.pg), var7)).lsb());
         this.pC(var1, var3, EUtil.op(OperationType.ROR, var3, var4));
      } else {
         Assert.fail();
      }
   }

   void kS(ConverterInstructionEntry var1, OperationType var2) {
      IEGeneric var3 = this.Sc.part(32);
      IEImm var4 = this.pg;
      Assert.a(var4.getBitsize() == 8);
      if (var2 == OperationType.ROL) {
         this.pC(var1, this.NS, this.go);
         this.pC(var1, this.vP, this.go);
         this.pC(var1, this.pF, var3.msb());
         this.pC(var1, var3, EUtil.orB(EUtil.op(OperationType.SHL, var3, this.pg), this.xC.zeroExtend(32)));
         this.pC(var1, this.xC, this.pF);
      } else if (var2 == OperationType.ROR) {
         this.pC(var1, this.NS, this.go);
         this.pC(var1, this.vP, this.go);
         this.pC(var1, this.pF, var3.lsb());
         this.pC(var1, var3, EUtil.orB(EUtil.op(OperationType.SHR, var3, this.pg), this.xC.zeroExtend(32).leftShift(31)));
         this.pC(var1, this.xC, this.pF);
      } else {
         Assert.fail();
      }
   }

   void A(ConverterInstructionEntry var1, boolean var2, boolean var3) {
      IEGeneric var4 = this.Ab(var1);
      String var6 = "Read" + (var2 ? "Timer" : "Counter");
      FunctionOptype var7 = this.gCtx.createFunctionType(var6, 0, 1, 16);
      IEOperation var5 = this.ctx.createOperation(var7, var4);
      if (var3) {
         FunctionOptype var8 = this.gCtx.createFunctionType("IntToBCD", 0, 1, 0);
         var5 = this.ctx.createOperation(var8, var5);
      }

      this.pC(var1, this.ah, this.Sc);
      this.pC(var1, this.kS(1), var5);
   }

   void E(ConverterInstructionEntry var1, boolean var2) {
      this.pC(var1, this.WR, this.go);
      this.pC(var1, this.fI, this.go);
      this.pC(var1, this.ld, this.go);
      if (var2) {
         this.pC(var1, this.pF, this.gp);
         this.pC(var1, this.gp, this.JF);
         this.pC(var1, var1.reljmp(2), EUtil.notB(this.pF));
      }

      this.UO(var1);
   }

   void sY(ConverterInstructionEntry var1, boolean var2) {
      RC var3 = (RC)((yt)var1.insn).getOperand(0);
      ArrayList var4 = null;
      int var5 = var3.A();
      if (var5 != 10 && var5 != 12) {
         if (var5 != 11 && var5 != 13) {
            throw new RuntimeException();
         }
      } else {
         var4 = new ArrayList();
         if (this.nctx != null) {
            List var6 = null;
            if (var3.pC() == 0) {
               int var7 = var3.kS();
               if (this.UT != null) {
                  S7.BlockType var8 = var5 == 10 ? S7.BlockType.FC : S7.BlockType.SFC;
                  long var9 = this.UT.getAddressOfCode(var8, var7);
                  if (var9 != -1L) {
                     INativeMethodItem var11 = this.nctx.getRoutine(var9);
                     if (var11 != null) {
                        IPrototypeItem var12 = var11.getPrototype();
                        if (var12 != null) {
                           var6 = var12.getParameterTypes();
                        }
                     }
                  }
               }
            }

            List var14 = ((yt)var1.insn).pC(var1.address, this.nctx.getMemory());
            Assert.a(var14 != null);
            int var15 = 0;

            for (int var10 : var14) {
               byte var22 = -1;
               if (var6 != null && var15 < var6.size()) {
                  INativeType var23 = (INativeType)var6.get(var15);
                  if (var23.getName().startsWith("MC7P")) {
                     var22 = 0;
                  } else if (var23 instanceof IReferenceType) {
                     var22 = 1;
                  } else {
                     var22 = 2;
                  }
               }

               Object var24;
               if (var22 != 0
                  && var22 != 1
                  && (
                     (var10 >>> 19 & 31) != 0
                        || !S7.AreaType.fromId(var10 >>> 24 & 0xFF)
                           .isAnyOf(S7.AreaType.V, S7.AreaType.M, S7.AreaType.I, S7.AreaType.Q, S7.AreaType.DB, S7.AreaType.DI)
                  )) {
                  if (var22 != 2 && (var10 & 65535) != 0) {
                     var24 = EUtil.imm(var10, 32);
                  } else {
                     var24 = EUtil.imm(var10 >>> 16, 32);
                  }
               } else {
                  var24 = this.pC(var10, var22 == 0);
               }

               var4.add(var24);
               var15++;
            }
         }
      }

      this.pC(var1, this.WR, this.go);
      this.pC(var1, this.fI, this.go);
      this.pC(var1, this.ld, this.go);
      if (var2) {
         this.pC(var1, this.pF, this.gp);
         this.pC(var1, this.gp, this.JF);
         this.pC(var1, var1.reljmp(2), EUtil.notB(this.pF));
      }

      long var13 = var1.address + ((yt)var1.insn).getSize();
      this.pC(var1, this.OB, EUtil.imm(var13, 32));
      if (var4 != null) {
         int var16 = 0;

         for (IEGeneric var21 : var4) {
            this.pC(var1, this.ZN[var16], var21);
            var16++;
         }
      }

      IEGeneric var17 = this.Ab(var1);
      var17 = this.pC(((RC)((yt)var1.insn).getOperand(0)).A(), var17);
      var1.r.add(this.ctx.createBranchAssign(this.sY, var17, true));
   }

   private IEGeneric pC(int var1, IEGeneric var2) {
      FunctionOptype var4 = this.gCtx.createFunctionType(switch (var1) {
         case 4, 5 -> "GetDBAddress";
         default -> throw new RuntimeException();
         case 10 -> "GetFCAddress";
         case 11 -> "GetFBAddress";
         case 12 -> "GetSFCAddress";
         case 13 -> "GetSFBAddress";
         case 15 -> "GetOBAddress";
      }, 0, 1, 32);
      return this.gCtx.createOperation(var4, var2);
   }

   private IEGeneric A(int var1, IEGeneric var2) {
      switch (var1) {
         case 4:
         case 5:
            String var3 = "GetDBLength";
            FunctionOptype var4 = this.gCtx.createFunctionType(var3, 0, 1, 16);
            return this.gCtx.createOperation(var4, var2);
         default:
            throw new RuntimeException();
      }
   }

   private IEGeneric pC(int var1, boolean var2) {
      Assert.a((var1 >>> 19 & 31) == 0);
      S7.AreaType var3 = S7.AreaType.fromId(var1 >>> 24 & 0xFF);

      IEVar var4 = switch (var3) {
         case V -> this.PR;
         case M -> this.Cu;
         case I -> this.sO;
         case Q -> this.os;
         case DB -> this.hZ;
         case DI -> this.UW;
         default -> throw new RuntimeException();
      };
      int var5 = var1 >>> 3 & 65535;
      int var6 = var1 & 7;
      IEOperation var7 = EUtil.add(var4, EUtil.imm(var5, 32));
      if (var6 != 0) {
         FunctionOptype var8 = this.gCtx.createFunctionType("BitAddr", 0, 2, 32);
         var7 = this.gCtx.createOperation(var8, var7, EUtil.imm(var6, 32));
      }

      if (var2) {
         FunctionOptype var9 = this.gCtx.createFunctionType("ToMC7P", 16, 1, 32);
         var7 = this.gCtx.createOperation(var9, var7);
      }

      return var7;
   }
}
