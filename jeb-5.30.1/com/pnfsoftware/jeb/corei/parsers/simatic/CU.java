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
import com.pnfsoftware.jebglobal.abg;
import java.util.ArrayList;
import java.util.List;

@Ser
public class CU extends AbstractConverter {
   private static final ILogger Jf = GlobalLog.getLogger(CU.class);
   public static final int q = 0;
   public static final int RF = 128;
   public static final int xK = 129;
   public static final int Dw = 130;
   public static final int Uv = 131;
   public static final int oW = 132;
   public static final int gO = 133;
   public static final int nf = 134;
   public static final int gP = 135;
   public static final int za = 136;
   public static final int lm = 256;
   public static final int zz = 320;
   public static final int JY = 384;
   public static final int HF = 448;
   public static final int LK = 512;
   public static final int io = 576;
   public static final int qa = 640;
   public static final int Hk = 704;
   public static final int Me = 576;
   public static final int PV = 768;
   public static final int oQ = 832;
   public static final int xW = 896;
   public static final int KT = 960;
   public static final int Gf = 1024;
   public static final int Ef = 1088;
   public static final int cC = 1280;
   public static final int sH = 1344;
   @SerId(1)
   ReferenceCounter CE;
   @SerId(2)
   int wF;
   @SerId(3)
   int If;
   @SerId(4)
   int Dz;
   @SerId(11)
   LR mI;
   @SerId(12)
   abg jq;
   @SerId(20)
   IEVar ui;
   @SerId(21)
   IEVar TX;
   @SerId(30)
   IEVar Rr;
   @SerId(31)
   IEVar EB;
   @SerId(32)
   IEVar Xo;
   @SerId(33)
   IEVar Bu;
   @SerId(34)
   IEVar IN;
   @SerId(35)
   IEVar rL;
   @SerId(36)
   IEVar eJ;
   @SerId(37)
   IEVar YN;
   @SerId(38)
   IEVar Rv;
   @SerId(40)
   IEVar zx;
   @SerId(41)
   IEVar ZT;
   @SerId(42)
   IEVar Ri;
   @SerId(43)
   IEVar GY;
   @SerId(50)
   IEVar Wx;
   @SerId(51)
   IEVar AB;
   @SerId(54)
   IEVar CY;
   @SerId(55)
   IEVar WI;
   @SerId(58)
   IEVar Tq;
   @SerId(59)
   IEVar Yp;
   @SerId(60)
   IEVar Gu;
   @SerId(61)
   IEVar nY;
   @SerId(62)
   IEVar lF;
   @SerId(63)
   IEVar nq;
   @SerId(64)
   IEVar NX;
   @SerId(68)
   IEVar br;
   @SerId(69)
   IEVar tW;
   @SerId(70)
   IEVar ZA;
   @SerId(71)
   IEVar Ov;
   @SerId(72)
   IEVar Lj;
   @SerId(73)
   IEVar nv;
   @SerId(74)
   IEVar LL;
   @SerId(75)
   IEVar PQ;
   @SerId(76)
   IEVar[] fQ;
   @SerId(77)
   IEVar fi;
   @SerId(80)
   IEVar bl;
   @SerId(81)
   IEVar jb;
   @SerId(82)
   IEVar pQ;
   @SerId(83)
   IEVar kf;
   @SerId(84)
   IEVar GM;
   @SerId(85)
   IEVar TQ;
   @SerId(86)
   IEVar Yw;
   @SerId(87)
   IEVar IY;
   @SerId(88)
   IEVar qR;
   @SerId(89)
   IEVar YA;
   @SerId(90)
   IEVar fw;
   @SerId(100)
   IEImm Wp;
   @SerId(101)
   IEImm cY;
   @SerId(102)
   IEImm PY;
   @SerId(103)
   IEImm cR;
   @SerId(104)
   IEImm eC;
   @SerId(105)
   IEImm ND;
   @SerId(106)
   IEImm Qu;
   @SerId(107)
   IEImm jh;

   public CU(LR var1, INativeCodeUnit var2) {
      super(var2.getProcessor(), 32);
      this.mI = var1;
      this.xK();
   }

   public CU(Vj var1) {
      super(var1, 32);
      this.xK();
   }

   private void xK() {
      this.ui = this.gCtx.createRegister(0, "PC", this.regNormalBitsize);
      this.TX = this.gCtx.createRegister(576, "SP", this.regNormalBitsize);
      this.Rr = this.gCtx.createRegister(128, "FC", 1);
      this.EB = this.gCtx.createRegister(129, "RLO", 1);
      this.Xo = this.gCtx.createRegister(130, "STA", 1);
      this.Bu = this.gCtx.createRegister(131, "OR", 1);
      this.IN = this.gCtx.createRegister(132, "OS", 1);
      this.rL = this.gCtx.createRegister(133, "OV", 1);
      this.eJ = this.gCtx.createRegister(134, "CC0", 1);
      this.YN = this.gCtx.createRegister(135, "CC1", 1);
      this.Rv = this.gCtx.createRegister(136, "BR", 1);
      this.zx = this.gCtx.createRegister(768, "ACCU1", 32);
      this.ZT = this.gCtx.createRegister(832, "ACCU2", 32);
      this.Wx = this.gCtx.createRegister(1024, "AR1", 32);
      this.AB = this.gCtx.createRegister(1088, "AR2", 32);
      this.CY = this.gCtx.createRegister(1280, "DBR1", 32);
      this.WI = this.gCtx.createRegister(1344, "DBR2", 32);
      this.br = this.gCtx.createRegister(256, "rI", 32);
      this.tW = this.gCtx.createRegister(320, "rQ", 32);
      this.ZA = this.gCtx.createRegister(384, "rM", 32);
      this.Ov = this.gCtx.createRegister(448, "rDB", 32);
      this.Lj = this.gCtx.createRegister(512, "rDI", 32);
      this.nv = this.TX;
      this.LL = this.gCtx.createRegister(640, "rC", 32);
      this.PQ = this.gCtx.createRegister(704, "rT", 32);
      this.br.setFlags(16);
      this.tW.setFlags(16);
      this.ZA.setFlags(16);
      this.LL.setFlags(16);
      this.PQ.setFlags(16);
      this.fQ = new IEVar[16];

      for (int var1 = 0; var1 < 16; var1++) {
         this.fQ[var1] = this.gCtx.createRegister("PAR" + var1, 32);
      }

      this.fi = this.gCtx.createRegister("LR", 32);
      this.Tq = this.gCtx.createRegister("rtabOB", 32);
      this.Yp = this.gCtx.createRegister("rtabFB", 32);
      this.Gu = this.gCtx.createRegister("rtabFC", 32);
      this.nY = this.gCtx.createRegister("rtabDB", 32);
      this.lF = this.gCtx.createRegister("rtabSFB", 32);
      this.nq = this.gCtx.createRegister("rtabSFC", 32);
      this.NX = this.gCtx.createRegister("rtabSDB", 32);
      this.bl = this.gCtx.createVirtualRegister("tmp1", 1);
      this.jb = this.gCtx.createVirtualRegister("tmp8", 8);
      this.pQ = this.gCtx.createVirtualRegister("tmp16", 16);
      this.kf = this.gCtx.createVirtualRegister("tmp32", 32);
      this.GM = this.gCtx.createVirtualRegister("tmp64", 64);
      this.TQ = this.gCtx.createVirtualRegister("tmp16_2", 16);
      this.Yw = this.gCtx.createVirtualRegister("tmp32_2", 32);
      this.IY = this.gCtx.createVirtualRegister("tmp64_2", 64);
      this.qR = this.gCtx.createVirtualRegister("PTR", 32);
      this.YA = this.gCtx.createVirtualRegister("ADDR", 32);
      this.fw = this.gCtx.createVirtualRegister("SH", 32);
      this.Wp = EUtil.zero(1);
      this.cY = EUtil.one(1);
      this.PY = EUtil.zero(8);
      this.cR = EUtil.one(8);
      this.eC = EUtil.zero(16);
      this.ND = EUtil.one(16);
      this.Qu = EUtil.zero(32);
      this.jh = EUtil.one(32);
   }

   @Override
   public IEVar getProgramCounter() {
      return this.ui;
   }

   @Override
   public IEVar getStackPointer() {
      return this.TX;
   }

   public IEVar q(int var1) {
      switch (var1) {
         case 1:
            return this.bl;
         case 16:
            return this.pQ;
         case 32:
            return this.kf;
         case 64:
            return this.GM;
         default:
            throw new RuntimeException();
      }
   }

   public IEVar RF(int var1) {
      switch (var1) {
         case 1:
            return this.zx;
         case 2:
            return this.ZT;
         case 3:
            return this.Ri;
         case 4:
            return this.GY;
         default:
            throw new RuntimeException();
      }
   }

   IEGeneric xK(int var1) {
      return this.RF(var1).part(32);
   }

   IEGeneric Dw(int var1) {
      return this.RF(var1).slice(0, 16);
   }

   IEGeneric Uv(int var1) {
      return this.RF(var1).slice(16, 32);
   }

   IEGeneric oW(int var1) {
      return this.RF(var1).slice(0, 8);
   }

   IEGeneric gO(int var1) {
      return this.RF(var1).slice(8, 16);
   }

   IEGeneric nf(int var1) {
      return this.RF(var1).slice(16, 24);
   }

   IEGeneric gP(int var1) {
      return this.RF(var1).slice(24, 32);
   }

   public IEVar za(int var1) {
      switch (var1) {
         case 1:
            return this.Wx;
         case 2:
            return this.AB;
         default:
            throw new RuntimeException();
      }
   }

   public boolean q() {
      return false;
   }

   public boolean RF() {
      return true;
   }

   @Override
   protected void preRoutineConversion(INativeMethodItem var1, IERoutineContext var2, List var3) {
      super.preRoutineConversion(var1, var2, var3);
      CFG var4 = var2.getRoutine().getData().getCFG();
      long var5 = var4.getEntryAddress();
      IEAssign var7 = var2.createAssign(this.nv, EUtil.sub(this.nv, EUtil.imm(65536L, 32)));
      var7.setLowerLevelAddress(var5);
      var3.add(var7);
   }

   @Override
   public void convertInstruction(ConverterInstructionEntry var1) {
      PY var2 = (PY)var1.insn;
      String var3 = var2.getMnemonic();
      if (var2.getCountOfOperands() >= 1) {
         int var4 = ((vb)var2.getOperand(0)).RF();
         if (var4 == 9 || var4 == 8) {
            Object var7 = null;
            if (!var3.equals("L") && !var3.equals("LC")) {
               String var8 = (var4 == 9 ? "__COUNTER_" : "__TIMER_") + var3;
               this.q(var1, var8, this.KT(var1), (IEGeneric)var7);
            } else {
               this.RF(var1, var4 == 8, var3.equals("LC"));
            }

            return;
         }
      }

      switch (var3) {
         case "A":
            this.q(var1, OperationType.AND, false);
            break;
         case "AN":
            this.q(var1, OperationType.AND, true);
            break;
         case "O":
            this.q(var1, OperationType.OR, false);
            break;
         case "ON":
            this.q(var1, OperationType.OR, true);
            break;
         case "X":
            this.q(var1, OperationType.XOR, false);
            break;
         case "XN":
            this.q(var1, OperationType.XOR, true);
            break;
         case "FN":
            this.q(var1, false);
            break;
         case "FP":
            this.q(var1, true);
            break;
         case "=":
            this.q(var1);
            break;
         case "R":
            this.xK(var1);
            break;
         case "S":
            this.RF(var1);
            break;
         case "NOT":
            this.q(var1, this.EB, EUtil.notB(this.EB));
            break;
         case "SET":
            this.RF(var1, true);
            break;
         case "CLR":
            this.RF(var1, false);
            break;
         case "SAVE":
            this.q(var1, this.Rv, this.EB);
            break;
         case ">I":
            this.q(var1, 16, OperationType.GT_S);
            break;
         case "<I":
            this.q(var1, 16, OperationType.LT_S);
            break;
         case "<>I":
            this.q(var1, 16, OperationType.LOG_NEQ);
            break;
         case "==I":
            this.q(var1, 16, OperationType.LOG_EQ);
            break;
         case ">=I":
            this.q(var1, 16, OperationType.GE_S);
            break;
         case "<=I":
            this.q(var1, 16, OperationType.LE_S);
            break;
         case ">D":
            this.q(var1, 32, OperationType.GT_S);
            break;
         case "<D":
            this.q(var1, 32, OperationType.LT_S);
            break;
         case "<>D":
            this.q(var1, 32, OperationType.LOG_NEQ);
            break;
         case "==D":
            this.q(var1, 32, OperationType.LOG_EQ);
            break;
         case ">=D":
            this.q(var1, 32, OperationType.GE_S);
            break;
         case "<=D":
            this.q(var1, 32, OperationType.LE_S);
            break;
         case ">R":
            this.q(var1, 32, OperationType.FGT);
            break;
         case "<R":
            this.q(var1, 32, OperationType.FLT);
            break;
         case "<>R":
            this.q(var1, 32, OperationType.FNE);
            break;
         case "==R":
            this.q(var1, 32, OperationType.FEQ);
            break;
         case ">=R":
            this.q(var1, 32, OperationType.FGE);
            break;
         case "<=R":
            this.q(var1, 32, OperationType.FLE);
            break;
         case "BTI":
            this.Dw(var1, 16);
            break;
         case "BTD":
            this.Dw(var1, 32);
            break;
         case "ITB":
            this.Uv(var1, 16);
            break;
         case "DTB":
            this.Uv(var1, 32);
            break;
         case "ITD":
            this.q(var1, this.zx, this.zx.part(16).signExtend(32));
            break;
         case "DTR":
            this.nf(var1);
            break;
         case "INVI":
            this.q(var1, this.Dw(1), EUtil.notB(this.Dw(1)));
            break;
         case "INVD":
            this.q(var1, this.zx, EUtil.notB(this.zx));
            break;
         case "NEGI":
            this.gO(var1, 16);
            break;
         case "NEGD":
            this.gO(var1, 32);
            break;
         case "NEGR":
            this.q(var1, this.zx, this.ctx.createCompose(this.zx.part(31), EUtil.notB(this.zx.msb())));
            break;
         case "CAW":
            this.nf(var1, 16);
            break;
         case "CAD":
            this.nf(var1, 32);
            break;
         case "RND":
            this.oW(var1, 0);
            break;
         case "TRUNC":
            this.oW(var1, 1);
            break;
         case "RND+":
            this.oW(var1, 2);
            break;
         case "RND-":
            this.oW(var1, 3);
            break;
         case "OPN":
            this.zz(var1);
            break;
         case "CDB":
            this.JY(var1);
            break;
         case "JU":
            this.gP(var1);
            break;
         case "JL":
            this.za(var1);
            break;
         case "JC":
            this.q(var1, false, false);
            break;
         case "JCN":
            this.q(var1, true, false);
            break;
         case "JCB":
            this.q(var1, false, true);
            break;
         case "JNB":
            this.q(var1, true, true);
            break;
         case "JBI":
            this.xK(var1, false);
            break;
         case "JNBI":
            this.xK(var1, true);
            break;
         case "JO":
            this.Dw(var1, false);
            break;
         case "JOS":
            this.Uv(var1, false);
            break;
         case "JZ":
            this.gP(var1, 0);
            break;
         case "JN":
            this.gP(var1, 1);
            break;
         case "JP":
            this.gP(var1, 2);
            break;
         case "JM":
            this.gP(var1, 3);
            break;
         case "JPZ":
            this.gP(var1, 4);
            break;
         case "JMZ":
            this.gP(var1, 5);
            break;
         case "JUO":
            this.gP(var1, 6);
            break;
         case "LOOP":
            this.lm(var1);
            break;
         case "+":
            this.gO(var1);
            break;
         case "+I":
            this.q(var1, 16, true);
            break;
         case "-I":
            this.q(var1, 16, false);
            break;
         case "*I":
            this.xK(var1, 16);
            break;
         case "/I":
            this.RF(var1, 16, false);
            break;
         case "+D":
            this.q(var1, 32, true);
            break;
         case "-D":
            this.q(var1, 32, false);
            break;
         case "*D":
            this.xK(var1, 32);
            break;
         case "/D":
            this.RF(var1, 32, false);
            break;
         case "MOD":
            this.RF(var1, 32, true);
            break;
         case "+R":
            this.q(var1, OperationType.FADD);
            break;
         case "-R":
            this.q(var1, OperationType.FSUB);
            break;
         case "*R":
            this.q(var1, OperationType.FMUL);
            break;
         case "/R":
            this.q(var1, OperationType.FDIV);
            break;
         case "ABS":
            this.q(var1, this.zx, this.ctx.createCompose(this.zx.part(31), this.Wp));
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
            this.q(var1, var3);
            break;
         case "L":
            this.Dw(var1);
            break;
         case "LAR1":
            this.q(var1, 1);
            break;
         case "LAR2":
            this.q(var1, 2);
            break;
         case "T":
            this.Uv(var1);
            break;
         case "TAR1":
            this.RF(var1, 1);
            break;
         case "TAR2":
            this.RF(var1, 2);
            break;
         case "CAR":
            this.oW(var1);
            break;
         case "BE":
         case "BEU":
            this.oW(var1, false);
            break;
         case "BEC":
            this.oW(var1, true);
            break;
         case "UC":
            this.gO(var1, false);
            break;
         case "CC":
            this.gO(var1, true);
            break;
         case "SLW":
            this.RF(var1, OperationType.SHL, 16);
            break;
         case "SLD":
            this.RF(var1, OperationType.SHL, 32);
            break;
         case "SRW":
            this.RF(var1, OperationType.SHR, 16);
            break;
         case "SRD":
            this.RF(var1, OperationType.SHR, 32);
            break;
         case "SSI":
            this.RF(var1, OperationType.SAR, 16);
            break;
         case "SSD":
            this.RF(var1, OperationType.SAR, 32);
            break;
         case "RLD":
            this.RF(var1, OperationType.ROL);
            break;
         case "RRD":
            this.RF(var1, OperationType.ROR);
            break;
         case "RLDA":
            this.xK(var1, OperationType.ROL);
            break;
         case "RRDA":
            this.xK(var1, OperationType.ROR);
            break;
         case "AW":
            this.q(var1, OperationType.AND, 16);
            break;
         case "OW":
            this.q(var1, OperationType.OR, 16);
            break;
         case "XOW":
            this.q(var1, OperationType.XOR, 16);
            break;
         case "AD":
            this.q(var1, OperationType.AND, 32);
            break;
         case "OD":
            this.q(var1, OperationType.OR, 32);
            break;
         case "XOD":
            this.q(var1, OperationType.XOR, 32);
            break;
         case "TAK":
            this.HF(var1);
            break;
         case "PUSH":
            this.LK(var1);
            break;
         case "POP":
            this.LK(var1);
            break;
         case "ENT":
            this.qa(var1);
            break;
         case "LEAVE":
            this.Hk(var1);
            break;
         case "INC":
            this.Me(var1);
            break;
         case "DEC":
            this.PV(var1);
            break;
         case "+AR1":
            this.za(var1, 1);
            break;
         case "+AR2":
            this.za(var1, 2);
            break;
         case "NOP":
            this.oQ(var1);
            break;
         case "BLD":
            this.oQ(var1);
            break;
         default:
            RuntimeException var6 = new RuntimeException("Failed conversion for MC7 opcode: " + var3);
            this.q(var1, var3, this.KT(var1), null);
            JebCoreService.notifySilentExceptionToClient(var6, this.mI);
      }
   }

   private void q(ConverterInstructionEntry var1, IEGeneric var2, IEGeneric var3) {
      var1.r.add(this.ctx.createAssign(var2, var3));
   }

   private void q(ConverterInstructionEntry var1, IEGeneric var2) {
      var1.r.add(this.ctx.createBranchAssign(this.ui, var2, false));
   }

   private void q(ConverterInstructionEntry var1, int var2, IEGeneric var3) {
      var1.r.add(this.ctx.createJump(var2, var3));
   }

   private void lm(ConverterInstructionEntry var1, int var2) {
      var1.r.add(this.ctx.createJump(var2));
   }

   private void oQ(ConverterInstructionEntry var1) {
      var1.r.add(this.ctx.createNop());
   }

   private void xW(ConverterInstructionEntry var1) {
      var1.r.add(this.ctx.createReturn());
   }

   private void q(ConverterInstructionEntry var1, String var2, IEGeneric var3, IEGeneric var4) {
      IEUntranslatedInstruction var5 = this.ctx.createUntranslatedInstruction(var1.address, var2);
      if (var3 != null) {
         var5.setParameterExpressions(var3);
      }

      if (var4 != null) {
         var5.setReturnExpression(var4);
      }

      var1.r.add(var5);
   }

   private IEVar lm(int var1) {
      switch (var1) {
         case 1:
            return this.br;
         case 2:
            return this.tW;
         case 3:
            return this.ZA;
         case 4:
            return this.Ov;
         case 5:
            return this.Lj;
         case 6:
            return this.nv;
         default:
            throw new RuntimeException("Cannot convert area code: " + var1);
      }
   }

   private IEGeneric KT(ConverterInstructionEntry var1) {
      return this.q(var1, null);
   }

   private IEGeneric q(ConverterInstructionEntry var1, IEGeneric[] var2) {
      long var3 = var1.address;
      PY var5 = (PY)var1.insn;
      if (var5.getCountOfOperands() == 0) {
         return null;
      } else {
         vb var6 = (vb)var5.getOperand(0);
         int var7 = var6.q();
         if (var7 == 768) {
            long var19 = var6.q(var3);
            return EUtil.imm(var19, 32);
         } else if (var7 == 256) {
            int[] var18 = var6.lm();
            IEVar var22 = this.lm(var6.RF());
            IEMem var25 = this.ctx.createMem(EUtil.add(var22, EUtil.imm(var18[0], 32)), 8);
            if (var2 != null) {
               var2[0] = EUtil.imm(var18[1], 8);
               return var25;
            } else {
               return var25.bit(var18[1]);
            }
         } else if (var7 == 1792) {
            boolean var21 = false;
            int var24 = var6.RF();

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
            Assert.a((var6.xK() & 1) == 0);
            int var27 = var6.xK() / 2 - 1;
            IEVar var29 = this.fQ[var27];
            if (var21) {
               if (var17 == 1 && var2 != null) {
                  var2[0] = this.Wp;
                  return this.ctx.createMem(var29, 8);
               } else {
                  return this.ctx.createMem(var29, var17);
               }
            } else {
               return (IEGeneric)(var17 == 0 ? var29 : var29.part(var17));
            }
         } else if (var6.Dw()) {
            FunctionOptype var20 = this.gCtx.createFunctionType("ExtractOff", 0, 1, 1, 32);
            FunctionOptype var23 = this.gCtx.createFunctionType("ExtractBit", 0, 1, 1, 32);
            FunctionOptype var26 = this.gCtx.createFunctionType("ToNP", 0, 1, 1, 32);
            int var28 = var6.Uv();
            Object var16;
            if (var6.zz() == 1) {
               if (var28 != 32 && var28 != 33) {
                  IEVar var33 = this.lm(var28);
                  IEOperation var35 = EUtil.add(var33, EUtil.imm(var6.xK(), 32));
                  this.q(var1, this.qR, this.ctx.createMem(var35, 32));
                  this.q(var1, this.YA, EUtil.add(this.lm(var6.RF()), this.ctx.createOperation(var20, this.qR)));
               } else {
                  IEVar var32 = var28 == 32 ? this.Wx : this.AB;
                  this.q(var1, this.qR, EUtil.add(var32, EUtil.imm(var6.xK(), 32)));
                  if (var6.RF() == 0) {
                     this.q(var1, this.YA, this.ctx.createOperation(var26, this.qR));
                  } else {
                     this.q(var1, this.YA, EUtil.add(this.lm(var6.RF()), this.ctx.createOperation(var20, this.qR)));
                  }
               }

               this.q(var1, this.fw, this.ctx.createOperation(var23, this.qR));
               if (var2 != null) {
                  var16 = this.ctx.createMem(this.YA, 8);
                  var2[0] = this.fw;
               } else {
                  var16 = EUtil.shr(this.ctx.createMem(this.YA, 8), this.fw).lsb();
               }
            } else if (var28 != 32 && var28 != 33) {
               IEVar var31 = this.lm(var28);
               IEOperation var34 = EUtil.add(var31, EUtil.imm(var6.xK(), 32));
               int var15 = var6.RF();
               if (var15 == 8 || var15 == 9 || var15 == 36 || var15 == 37 || var15 == 10 || var15 == 11 || var15 == 12 || var15 == 13) {
                  return this.ctx.createMem(var34, 16);
               }

               this.q(var1, this.qR, this.ctx.createMem(var34, 32));
               var16 = this.ctx.createMem(EUtil.add(this.lm(var6.RF()), this.ctx.createOperation(var20, this.qR)), var6.zz());
            } else {
               IEVar var30 = var28 == 32 ? this.Wx : this.AB;
               this.q(var1, this.qR, EUtil.add(var30, EUtil.imm(var6.xK(), 32)));
               if (var6.RF() == 0) {
                  var16 = this.ctx.createMem(this.ctx.createOperation(var26, this.qR), var6.zz());
               } else {
                  var16 = this.ctx.createMem(EUtil.add(this.lm(var6.RF()), this.ctx.createOperation(var20, this.qR)), var6.zz());
               }
            }

            return (IEGeneric)var16;
         } else {
            IEImm var8 = null;
            int var9 = var6.JY();
            if (var9 > 0) {
               var8 = EUtil.imm(var6.xK(), var9);
            }

            int var10 = var6.RF();
            int var11 = 0;
            byte var12 = 1;
            if (var10 != 0) {
               IEVar var13 = null;
               switch (var10) {
                  case 1:
                     var13 = this.br;
                     break;
                  case 2:
                     var13 = this.tW;
                     break;
                  case 3:
                     var13 = this.ZA;
                     break;
                  case 4:
                     var13 = this.Ov;
                     break;
                  case 5:
                     var13 = this.Lj;
                     break;
                  case 6:
                     var13 = this.nv;
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
                        return this.IN;
                     case 26:
                        return this.rL;
                     case 27:
                        return EUtil.andB(this.YN, this.eJ);
                     case 28:
                        return this.Rv;
                     case 32:
                        return this.Wx;
                     case 33:
                        return this.AB;
                     case 49:
                        return EUtil.andB(this.YN, EUtil.notB(this.eJ));
                     case 50:
                        return EUtil.andB(EUtil.notB(this.YN), this.eJ);
                     case 51:
                        return EUtil.xorB(this.YN, this.eJ);
                     case 52:
                        return EUtil.andB(EUtil.notB(this.YN), EUtil.notB(this.eJ));
                     case 53:
                        return EUtil.notB(this.eJ);
                     case 54:
                        return EUtil.notB(this.YN);
                  }
               } else {
                  if (var11 == 0) {
                     var11 = var6.zz();
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

   void q(ConverterInstructionEntry var1, OperationType var2, boolean var3) {
      IEGeneric var4 = this.KT(var1);
      if (var4 == null) {
         Assert.a(var2 == OperationType.OR);
         throw new RuntimeException("TBI: And before Or");
      } else {
         if (var3) {
            var4 = EUtil.notB(var4);
         }

         this.q(var1, var1.reljmp(4), EUtil.eq(this.Rr, this.cY));
         this.q(var1, this.Rr, this.cY);
         this.q(var1, this.EB, var4);
         this.lm(var1, var1.reljmp(2));
         this.q(var1, this.EB, EUtil.op(var2, this.EB, var4));
      }
   }

   void q(ConverterInstructionEntry var1, boolean var2) {
      IEGeneric var3 = this.KT(var1);
      this.q(var1, var1.reljmp(4), EUtil.eq(this.Rr, this.cY));
      this.q(var1, this.Rr, this.cY);
      this.q(var1, this.EB, var3);
      this.lm(var1, var1.reljmp(2));
      this.q(var1, this.EB, var2 ? EUtil.andB(EUtil.notB(var3), this.EB) : EUtil.andB(var3, EUtil.notB(this.EB)));
   }

   void q(ConverterInstructionEntry var1) {
      IEGeneric[] var2 = new IEGeneric[1];
      IEGeneric var3 = this.q(var1, var2);
      this.q(var1, this.Bu, this.Wp);
      this.q(var1, this.Rr, this.Wp);
      IEOperation var4 = EUtil.orB(EUtil.andB(var3, EUtil.notB(EUtil.shl(this.cR, var2[0]))), EUtil.shl(this.EB.zeroExtend(8), var2[0]));
      this.q(var1, var3, var4);
   }

   void RF(ConverterInstructionEntry var1) {
      if (((vb)((PY)var1.insn).getOperand(0)).RF() == 9) {
         throw new RuntimeException();
      } else {
         IEGeneric[] var2 = new IEGeneric[1];
         IEGeneric var3 = this.q(var1, var2);
         this.q(var1, this.Bu, this.Wp);
         this.q(var1, this.Rr, this.Wp);
         this.q(var1, var1.reljmp(2), EUtil.eq(this.EB, this.Wp));
         IEOperation var4 = EUtil.orB(var3, EUtil.shl(this.cR, var2[0]));
         this.q(var1, var3, var4);
      }
   }

   void xK(ConverterInstructionEntry var1) {
      if (((vb)((PY)var1.insn).getOperand(0)).RF() == 9) {
         throw new RuntimeException();
      } else {
         IEGeneric[] var2 = new IEGeneric[1];
         IEGeneric var3 = this.q(var1, var2);
         this.q(var1, this.Bu, this.Wp);
         this.q(var1, this.Rr, this.Wp);
         this.q(var1, var1.reljmp(2), EUtil.eq(this.EB, this.Wp));
         IEOperation var4 = EUtil.andB(var3, EUtil.notB(EUtil.shl(this.cR, var2[0])));
         this.q(var1, var3, var4);
      }
   }

   void RF(ConverterInstructionEntry var1, boolean var2) {
      this.q(var1, this.Bu, this.Wp);
      this.q(var1, this.Rr, this.Wp);
      this.q(var1, this.EB, var2 ? this.cY : this.Wp);
   }

   void Dw(ConverterInstructionEntry var1) {
      this.q(var1, this.ZT, this.zx);
      if (((vb)((PY)var1.insn).getOperand(0)).nf()) {
         this.q(var1, this.zx, EUtil.zero(this.zx.getBitsize()));
         this.q(var1, this.zx.bit(1), this.EB);
         this.q(var1, this.zx.bit(4), this.IN);
         this.q(var1, this.zx.bit(5), this.rL);
         this.q(var1, this.zx.bit(6), this.eJ);
         this.q(var1, this.zx.bit(7), this.YN);
         this.q(var1, this.zx.bit(8), this.Rv);
         if (!this.RF()) {
            this.q(var1, this.zx.bit(0), this.Rr);
            this.q(var1, this.zx.bit(2), this.Xo);
            this.q(var1, this.zx.bit(3), this.Bu);
         }
      } else {
         int var2 = ((vb)((PY)var1.insn).getOperand(0)).RF();
         if (var2 != 34 && var2 != 35) {
            if (var2 != 36 && var2 != 37) {
               IEGeneric var6 = this.KT(var1);
               this.q(var1, this.zx, var6.zeroExtend(this.zx.getBitsize()));
            } else {
               IEVar var5 = var2 == 36 ? this.CY : this.WI;
               this.q(var1, this.zx, var5.zeroExtend(32));
            }
         } else {
            IEVar var3 = var2 == 34 ? this.CY : this.WI;
            IEGeneric var4 = this.RF(4, var3);
            this.q(var1, this.zx, var4.zeroExtend(32));
         }
      }
   }

   void Uv(ConverterInstructionEntry var1) {
      if (((vb)((PY)var1.insn).getOperand(0)).nf()) {
         this.q(var1, this.EB, this.zx.bit(1));
         this.q(var1, this.IN, this.zx.bit(4));
         this.q(var1, this.rL, this.zx.bit(5));
         this.q(var1, this.eJ, this.zx.bit(6));
         this.q(var1, this.YN, this.zx.bit(7));
         this.q(var1, this.Rv, this.zx.bit(8));
         if (!this.RF()) {
            this.q(var1, this.Rr, this.zx.bit(0));
            this.q(var1, this.Xo, this.zx.bit(2));
            this.q(var1, this.Bu, this.zx.bit(3));
         }
      } else {
         IEGeneric var2 = this.KT(var1);
         this.q(var1, var2, this.zx.part(var2.getBitsize()));
      }
   }

   void q(ConverterInstructionEntry var1, int var2) {
      IEGeneric var3 = this.KT(var1);
      if (var3 == null) {
         this.q(var1, this.za(var2), this.zx);
      } else {
         this.q(var1, this.za(var2), var3);
      }
   }

   void RF(ConverterInstructionEntry var1, int var2) {
      IEGeneric var3 = this.KT(var1);
      if (var3 == null) {
         this.q(var1, this.ZT, this.zx);
         this.q(var1, this.zx, this.za(var2));
      } else {
         this.q(var1, var3, this.za(var2));
      }
   }

   void oW(ConverterInstructionEntry var1) {
      this.q(var1, this.kf, this.Wx);
      this.q(var1, this.Wx, this.AB);
      this.q(var1, this.AB, this.kf);
   }

   void gO(ConverterInstructionEntry var1) {
      IEGeneric var2 = this.KT(var1);
      if (var2.getBitsize() == 16) {
         this.q(var1, this.zx.part(16), EUtil.add(this.zx.part(16), var2));
      } else if (var2.getBitsize() == 32) {
         this.q(var1, this.zx, EUtil.add(this.zx, var2));
      } else {
         Assert.fail();
      }
   }

   void q(ConverterInstructionEntry var1, int var2, boolean var3) {
      IEGeneric var4 = this.ZT.part(var2);
      IEGeneric var5 = this.zx.part(var2);
      IEVar var7 = this.q(var2);
      this.q(var1, var7, var3 ? EUtil.add(var4, var5) : EUtil.sub(var4, var5));
      IEGeneric var8 = EUtil.buildOverflowFlag(var4, var5, var7, var3);
      this.q(var1, this.rL, var8);
      this.q(var1, var1.reljmp(2), EUtil.eq(this.rL, this.Wp));
      this.q(var1, this.IN, this.cY);
      this.q(var1, this.eJ, var7.msb());
      this.q(var1, this.YN, EUtil.andL(EUtil.notB(var7.msb()), EUtil.ne(var7, EUtil.zero(var2))));
      this.q(var1, var5, var7);
   }

   void xK(ConverterInstructionEntry var1, int var2) {
      int var3 = 2 * var2;
      IEGeneric var4 = this.ZT.part(var2).signExtend(var3);
      IEGeneric var5 = this.zx.part(var2).signExtend(var3);
      IEVar var6 = this.q(var3);
      int var7 = var1.irAddress + var1.r.size();
      this.q(var1, var6, EUtil.mul(var4, var5));
      this.q(var1, var7 + 6, EUtil.ne(var6, EUtil.zero(var3)));
      this.q(var1, this.rL, this.Wp);
      this.q(var1, this.YN, this.Wp);
      this.q(var1, this.eJ, this.Wp);
      this.lm(var1, var7 + 24);
      this.q(var1, var7 + 11, var6.slice(var2 - 1));
      this.q(var1, this.rL, this.Wp);
      this.q(var1, this.YN, this.cY);
      this.q(var1, this.eJ, this.Wp);
      this.lm(var1, var7 + 24);
      this.q(var1, var7 + 16, EUtil.eq(var6.slice(var2 - 1), EUtil.minusOne(var2 + 1)));
      this.q(var1, this.rL, this.Wp);
      this.q(var1, this.YN, this.Wp);
      this.q(var1, this.eJ, this.cY);
      this.lm(var1, var7 + 24);
      this.q(var1, var7 + 21, var6.bit(var3 - 1));
      this.q(var1, this.rL, this.cY);
      this.q(var1, this.YN, this.cY);
      this.q(var1, this.eJ, this.Wp);
      this.lm(var1, var7 + 24);
      this.q(var1, this.rL, this.cY);
      this.q(var1, this.YN, this.Wp);
      this.q(var1, this.eJ, this.cY);
      this.q(var1, this.zx, var6.part(32));
      if (this.q()) {
         this.q(var1, this.ZT, this.Ri);
         this.q(var1, this.Ri, this.GY);
      }
   }

   void RF(ConverterInstructionEntry var1, int var2, boolean var3) {
      Assert.a(var2 == 16 || var2 == 32);
      IEGeneric var4 = this.ZT.part(var2);
      IEGeneric var5 = this.zx.part(var2);
      IEVar var6 = this.q(var2);
      IEImm var7 = EUtil.one(var2)._shl(var2 - 1);
      IEImm var8 = EUtil.minusOne(var2);
      int var9 = var1.irAddress + var1.r.size();
      this.q(var1, var9 + 6, var5);
      this.q(var1, this.IN, this.cY);
      this.q(var1, this.rL, this.cY);
      this.q(var1, this.YN, this.cY);
      this.q(var1, this.eJ, this.cY);
      this.lm(var1, var9 + 28);
      if (var3) {
         Assert.a(var2 == 32);
         this.q(var1, var6, EUtil.remS(var4, var5));
         this.oQ(var1);
      } else {
         this.q(var1, var6, EUtil.divS(var4, var5));
         if (var2 == 16) {
            this.q(var1, this.zx.slice(16), EUtil.remS(var4, var5));
         } else {
            this.oQ(var1);
         }
      }

      this.q(var1, var9 + 14, EUtil.orL(EUtil.ne(var4, var7), EUtil.ne(var5, var8)));
      this.q(var1, this.IN, this.cY);
      this.q(var1, this.rL, this.cY);
      this.q(var1, this.YN, this.cY);
      this.q(var1, this.eJ, this.Wp);
      this.lm(var1, var9 + 27);
      this.q(var1, var9 + 19, var6);
      this.q(var1, this.rL, this.Wp);
      this.q(var1, this.YN, this.Wp);
      this.q(var1, this.eJ, this.Wp);
      this.lm(var1, var9 + 27);
      this.q(var1, var9 + 24, var6.msb());
      this.q(var1, this.rL, this.Wp);
      this.q(var1, this.YN, this.cY);
      this.q(var1, this.eJ, this.Wp);
      this.lm(var1, var9 + 27);
      this.q(var1, this.rL, this.Wp);
      this.q(var1, this.YN, this.Wp);
      this.q(var1, this.eJ, this.cY);
      this.q(var1, this.zx.part(var2), var6);
      if (this.q()) {
         this.q(var1, this.ZT, this.Ri);
         this.q(var1, this.Ri, this.GY);
      }
   }

   void q(ConverterInstructionEntry var1, OperationType var2) {
      IEVar var3 = this.ZT;
      IEVar var4 = this.zx;
      this.q(var1, this.zx, EUtil.op(var2, var3, var4));
      if (this.q()) {
         this.q(var1, this.ZT, this.Ri);
         this.q(var1, this.Ri, this.GY);
      }
   }

   void q(ConverterInstructionEntry var1, String var2) {
      FunctionOptype var3 = this.gCtx.createFunctionType(var2, 0, 1, 1, 0);
      IEOperation var4 = this.ctx.createOperation(var3, this.zx);
      this.q(var1, this.zx, var4);
   }

   void q(ConverterInstructionEntry var1, int var2, OperationType var3) {
      Assert.a(var2 == 16 || var2 == 32);
      IEGeneric var4 = this.ZT.part(var2);
      IEGeneric var5 = this.zx.part(var2);
      this.q(var1, this.EB, EUtil.op(var3, var4, var5));
      this.q(var1, this.eJ, EUtil.op(OperationType.LT_S, var4, var5));
      this.q(var1, this.YN, EUtil.op(OperationType.GT_S, var4, var5));
      this.q(var1, this.Bu, this.Wp);
      this.q(var1, this.rL, this.Wp);
   }

   void Dw(ConverterInstructionEntry var1, int var2) {
      IEGeneric var3 = this.zx.part(var2);
      IEVar var4 = this.q(var2);
      IEImm var5 = EUtil.zero(var2);
      IEImm var6 = EUtil.imm(10L, var2);
      this.q(var1, var4, var5);
      int var7 = var2 / 4 - 1;
      int var8 = var2 - 8;

      for (int var9 = 0; var9 < var7; var9++) {
         this.q(var1, var4, EUtil.add(EUtil.mul(var4, var6), var3.slice(var8, var8 + 4).zeroExtend(var2)));
         var8 -= 4;
      }

      this.q(var1, var3, this.ctx.createCond(var3.msb(), EUtil.sub(var5, var4), var4));
   }

   void Uv(ConverterInstructionEntry var1, int var2) {
      IEGeneric var3 = this.zx.part(var2);
      FunctionOptype var4 = this.gCtx.createFunctionType("IntToBCD", 0, 1, 1, 0);
      int var5 = var1.irAddress + var1.r.size();
      this.q(var1, var5 + 4, EUtil.andL(EUtil.geS(var3, EUtil.imm(-999L, var2)), EUtil.leS(var3, EUtil.imm(999L, var2))));
      this.q(var1, this.rL, this.cY);
      this.q(var1, this.IN, this.cY);
      this.lm(var1, var5 + 5);
      this.q(var1, var3, this.gCtx.createOperation(var4, var3));
   }

   void nf(ConverterInstructionEntry var1) {
      this.q(var1, this.zx, EUtil.createConversionOperation(OperationType.INT2FP, this.zx, 32));
   }

   void oW(ConverterInstructionEntry var1, int var2) {
      this.q(var1, this.zx, EUtil.createConversionOperation(OperationType.FP2INT, this.zx, 32));
   }

   void gO(ConverterInstructionEntry var1, int var2) {
      IEGeneric var3 = this.zx.part(var2);
      int var4 = var1.irAddress + var1.r.size();
      this.q(var1, var3, EUtil.add(EUtil.notB(var3), EUtil.one(var2)));
      this.q(var1, var4 + 6, EUtil.ne(var3, EUtil.zero(var2)));
      this.q(var1, this.rL, this.Wp);
      this.q(var1, this.YN, this.Wp);
      this.q(var1, this.eJ, this.Wp);
      this.lm(var1, var4 + 20);
      this.q(var1, var4 + 11, var3.msb());
      this.q(var1, this.rL, this.Wp);
      this.q(var1, this.YN, this.cY);
      this.q(var1, this.eJ, this.Wp);
      this.lm(var1, var4 + 20);
      this.q(var1, var4 + 16, EUtil.eq(var3.part(var2 - 1), EUtil.zero(var2 - 1)));
      this.q(var1, this.rL, this.Wp);
      this.q(var1, this.YN, this.Wp);
      this.q(var1, this.eJ, this.cY);
      this.lm(var1, var4 + 20);
      this.q(var1, this.IN, this.cY);
      this.q(var1, this.rL, this.cY);
      this.q(var1, this.YN, this.Wp);
      this.q(var1, this.eJ, this.cY);
   }

   void nf(ConverterInstructionEntry var1, int var2) {
      if (var2 == 16) {
         IEGeneric var3 = this.zx.part(16);
         this.q(var1, var3, this.ctx.createCompose(var3.slice(8, 16), var3.slice(0, 8)));
      } else {
         if (var2 != 32) {
            throw new RuntimeException();
         }

         IEGeneric var4 = this.zx.part(32);
         this.q(var1, var4, this.ctx.createCompose(var4.slice(24, 32), var4.slice(16, 24), var4.slice(8, 16), var4.slice(0, 8)));
      }
   }

   void gP(ConverterInstructionEntry var1) {
      IEGeneric var2 = this.KT(var1);
      this.q(var1, this.ui, var2);
   }

   void za(ConverterInstructionEntry var1) {
      IFlowInformation var2 = ((PY)var1.insn).getBreakingFlow(var1.address);
      List var3 = var2.getTargets();
      this.q(var1, this.jb, this.oW(1));
      int var4 = var1.irAddress + var1.r.size();
      int var5 = 0;

      for (ICodePointer var7 : var3) {
         if (var5 + 1 != var3.size()) {
            this.q(var1, var4 + 2, EUtil.ne(this.jb, EUtil.imm(var5, 8)));
         }

         this.q(var1, EUtil.imm(var7.getAddress(), 32));
         var5++;
         var4 += 2;
      }
   }

   void q(ConverterInstructionEntry var1, boolean var2, boolean var3) {
      IEGeneric var4 = this.KT(var1);
      IEImm var5 = EUtil.imm(var1.address + ((PY)var1.insn).getSize(), 32);
      IEVar var6 = var3 ? this.Rv : this.bl;
      this.q(var1, this.Bu, this.Wp);
      this.q(var1, this.Rr, this.Wp);
      this.q(var1, var6, this.EB);
      this.q(var1, this.EB, this.cY);
      var1.r.add(this.ctx.createBranchAssign(this.ui, this.ctx.createCond((IEGeneric)(var2 ? EUtil.notB(var6) : var6), var4, var5), false));
   }

   void xK(ConverterInstructionEntry var1, boolean var2) {
      IEGeneric var3 = this.KT(var1);
      IEImm var4 = EUtil.imm(var1.address + ((PY)var1.insn).getSize(), 32);
      this.q(var1, this.Bu, this.Wp);
      this.q(var1, this.Rr, this.Wp);
      var1.r.add(this.ctx.createBranchAssign(this.ui, this.ctx.createCond((IEGeneric)(var2 ? EUtil.notB(this.Rv) : this.Rv), var3, var4), false));
   }

   void Dw(ConverterInstructionEntry var1, boolean var2) {
      IEGeneric var3 = this.KT(var1);
      IEImm var4 = EUtil.imm(var1.address + ((PY)var1.insn).getSize(), 32);
      var1.r.add(this.ctx.createBranchAssign(this.ui, this.ctx.createCond((IEGeneric)(var2 ? EUtil.notB(this.rL) : this.rL), var3, var4), false));
   }

   void Uv(ConverterInstructionEntry var1, boolean var2) {
      IEGeneric var3 = this.KT(var1);
      IEImm var4 = EUtil.imm(var1.address + ((PY)var1.insn).getSize(), 32);
      this.q(var1, this.bl, this.IN);
      this.q(var1, this.IN, this.Wp);
      var1.r.add(this.ctx.createBranchAssign(this.ui, this.ctx.createCond((IEGeneric)(var2 ? EUtil.notB(this.bl) : this.bl), var3, var4), false));
   }

   void gP(ConverterInstructionEntry var1, int var2) {
      IEGeneric var3 = this.KT(var1);
      IEImm var4 = EUtil.imm(var1.address + ((PY)var1.insn).getSize(), 32);
      Object var5 = null;
      if (var2 == 0) {
         var5 = EUtil.notB(EUtil.orB(this.eJ, this.YN));
      } else if (var2 == 1) {
         var5 = EUtil.xorB(this.eJ, this.YN);
      } else if (var2 == 2) {
         var5 = EUtil.andB(this.YN, EUtil.notB(this.eJ));
      } else if (var2 == 3) {
         var5 = EUtil.andB(this.eJ, EUtil.notB(this.YN));
      } else if (var2 == 4) {
         var5 = EUtil.notB(this.eJ);
      } else if (var2 == 5) {
         var5 = EUtil.notB(this.YN);
      } else if (var2 == 6) {
         var5 = EUtil.andB(this.eJ, this.YN);
      } else {
         Assert.fail();
      }

      var1.r.add(this.ctx.createBranchAssign(this.ui, this.ctx.createCond((IEGeneric)var5, var3, var4), false));
   }

   void lm(ConverterInstructionEntry var1) {
      IEGeneric var2 = this.KT(var1);
      IEImm var3 = EUtil.imm(var1.address + ((PY)var1.insn).getSize(), 32);
      this.q(var1, this.zx.part(16), EUtil.sub(this.zx.part(16), EUtil.one(16)));
      var1.r.add(this.ctx.createBranchAssign(this.ui, this.ctx.createCond(this.zx.part(16), var2, var3), false));
   }

   void zz(ConverterInstructionEntry var1) {
      int var4 = ((vb)((PY)var1.insn).getOperand(0)).RF();
      IEVar var2;
      IEVar var3;
      if (var4 != 4 && var4 != 36) {
         if (var4 != 5 && var4 != 37) {
            throw new RuntimeException();
         }

         var2 = this.WI;
         var3 = this.Lj;
      } else {
         var2 = this.CY;
         var3 = this.Ov;
      }

      IEGeneric var5 = this.KT(var1);
      this.q(var1, var2, var5.zeroExtend(var2.getBitsize()));
      Object var6 = null;
      if (this.mI != null && var5 instanceof IEImm) {
         long var7 = this.mI.getAddressOfData(S7.BlockType.DB, (int)var5.asImm().getValueAsLong());
         if (var7 != -1L) {
            var6 = EUtil.imm(var7, 32);
         }
      }

      if (var6 == null) {
         var6 = this.q(4, var2);
      }

      this.q(var1, var3, (IEGeneric)var6);
   }

   void JY(ConverterInstructionEntry var1) {
      this.q(var1, this.kf, this.CY);
      this.q(var1, this.CY, this.WI);
      this.q(var1, this.WI, this.kf);
      this.q(var1, this.kf, this.Ov);
      this.q(var1, this.Ov, this.Lj);
      this.q(var1, this.Lj, this.kf);
   }

   void q(ConverterInstructionEntry var1, OperationType var2, int var3) {
      IEGeneric var4 = this.zx.part(var3);
      IEGeneric var5 = this.KT(var1);
      if (var5 == null) {
         var5 = this.ZT.part(var3);
      }

      this.q(var1, var4, EUtil.op(var2, var4, var5));
      this.q(var1, this.YN, EUtil.ne(var4, EUtil.zero(var3)));
      this.q(var1, this.eJ, this.Wp);
      this.q(var1, this.rL, this.Wp);
   }

   void HF(ConverterInstructionEntry var1) {
      this.q(var1, this.kf, this.zx);
      this.q(var1, this.zx, this.ZT);
      this.q(var1, this.ZT, this.kf);
   }

   void LK(ConverterInstructionEntry var1) {
      if (this.q()) {
         this.q(var1, this.GY, this.Ri);
         this.q(var1, this.Ri, this.ZT);
      }

      this.q(var1, this.ZT, this.zx);
   }

   void io(ConverterInstructionEntry var1) {
      this.q(var1, this.zx, this.ZT);
      if (this.q()) {
         this.q(var1, this.ZT, this.Ri);
         this.q(var1, this.Ri, this.GY);
      }
   }

   void qa(ConverterInstructionEntry var1) {
      if (this.q()) {
         this.q(var1, this.GY, this.Ri);
         this.q(var1, this.Ri, this.ZT);
      }
   }

   void Hk(ConverterInstructionEntry var1) {
      if (this.q()) {
         this.q(var1, this.ZT, this.Ri);
         this.q(var1, this.Ri, this.GY);
      }
   }

   void Me(ConverterInstructionEntry var1) {
      IEGeneric var2 = this.oW(1);
      IEGeneric var3 = this.KT(var1);
      this.q(var1, var2, EUtil.add(var2, var3));
   }

   void PV(ConverterInstructionEntry var1) {
      IEGeneric var2 = this.oW(1);
      IEGeneric var3 = this.KT(var1);
      this.q(var1, var2, EUtil.sub(var2, var3));
   }

   void za(ConverterInstructionEntry var1, int var2) {
      IEGeneric var3 = this.za(var2).part(24);
      IEGeneric var4 = this.KT(var1);
      if (var4 == null) {
         var4 = this.Dw(1).signExtend(24);
      } else {
         Assert.a(var4.getBitsize() == 16);
         var4 = var4.signExtend(24);
      }

      this.q(var1, var3, EUtil.add(var3, var4));
   }

   void RF(ConverterInstructionEntry var1, OperationType var2, int var3) {
      Assert.a(var3 == 16 || var3 == 32);
      IEGeneric var4 = this.zx.part(var3);
      IEGeneric var5 = this.KT(var1);
      if (var5 == null) {
         var5 = this.oW(2);
      }

      Assert.a(var5.getBitsize() == 8);
      IEImm var6 = EUtil.imm(var3, 8);
      int var7 = var1.irAddress;
      if (var2 == OperationType.SAR) {
         this.q(var1, var7 + 9, EUtil.eq(var5, this.PY));
         this.q(var1, this.rL, this.Wp);
         this.q(var1, this.eJ, this.Wp);
         this.q(var1, var7 + 7, EUtil.ltU(var5, var6));
         this.q(var1, this.YN, var4.msb());
         this.q(var1, var4, this.gCtx.createCond(this.YN, EUtil.minusOne(var3), EUtil.zero(var3)));
         this.lm(var1, var7 + 9);
         this.q(var1, this.YN, EUtil.op(OperationType.SHR, var4, EUtil.sub(var5, this.cR)).lsb());
         this.q(var1, var4, EUtil.op(OperationType.SAR, var4, var5));
      } else if (var2 == OperationType.SHR) {
         this.q(var1, var7 + 9, EUtil.eq(var5, this.PY));
         this.q(var1, this.rL, this.Wp);
         this.q(var1, this.eJ, this.Wp);
         this.q(var1, var7 + 7, EUtil.ltU(var5, var6));
         this.q(var1, this.YN, this.Wp);
         this.q(var1, var4, EUtil.zero(var3));
         this.lm(var1, var7 + 9);
         this.q(var1, this.YN, EUtil.op(OperationType.SHR, var4, EUtil.sub(var5, this.cR)).lsb());
         this.q(var1, var4, EUtil.op(OperationType.SHR, var4, var5));
      } else if (var2 == OperationType.SHL) {
         this.q(var1, var7 + 9, EUtil.eq(var5, this.PY));
         this.q(var1, this.rL, this.Wp);
         this.q(var1, this.eJ, this.Wp);
         this.q(var1, var7 + 7, EUtil.ltU(var5, var6));
         this.q(var1, this.YN, this.Wp);
         this.q(var1, var4, EUtil.zero(var3));
         this.lm(var1, var7 + 9);
         this.q(var1, this.YN, EUtil.op(OperationType.SHR, var4, EUtil.sub(var6, var5)).lsb());
         this.q(var1, var4, EUtil.op(OperationType.SHL, var4, var5));
      } else {
         Assert.fail();
      }
   }

   void RF(ConverterInstructionEntry var1, OperationType var2) {
      IEGeneric var3 = this.zx.part(32);
      IEGeneric var4 = this.KT(var1);
      if (var4 == null) {
         var4 = this.oW(2);
      }

      Assert.a(var4.getBitsize() == 8);
      IEImm var5 = EUtil.imm(32L, 8);
      int var6 = var1.irAddress;
      IEImm var7 = EUtil.imm(32L, 8)._sub(this.cR);
      if (var2 == OperationType.ROL) {
         this.q(var1, var6 + 5, EUtil.eq(var4, this.PY));
         this.q(var1, this.rL, this.Wp);
         this.q(var1, this.eJ, this.Wp);
         this.q(var1, this.YN, EUtil.op(OperationType.SHR, var3, EUtil.sub(var5, EUtil.andB(var4, var7))).lsb());
         this.q(var1, var3, EUtil.op(OperationType.ROL, var3, var4));
      } else if (var2 == OperationType.ROR) {
         this.q(var1, var6 + 5, EUtil.eq(var4, this.PY));
         this.q(var1, this.rL, this.Wp);
         this.q(var1, this.eJ, this.Wp);
         this.q(var1, this.YN, EUtil.op(OperationType.SHR, var3, EUtil.andB(EUtil.sub(var4, this.cR), var7)).lsb());
         this.q(var1, var3, EUtil.op(OperationType.ROR, var3, var4));
      } else {
         Assert.fail();
      }
   }

   void xK(ConverterInstructionEntry var1, OperationType var2) {
      IEGeneric var3 = this.zx.part(32);
      IEImm var4 = this.cR;
      Assert.a(var4.getBitsize() == 8);
      if (var2 == OperationType.ROL) {
         this.q(var1, this.rL, this.Wp);
         this.q(var1, this.eJ, this.Wp);
         this.q(var1, this.bl, var3.msb());
         this.q(var1, var3, EUtil.orB(EUtil.op(OperationType.SHL, var3, this.cR), this.YN.zeroExtend(32)));
         this.q(var1, this.YN, this.bl);
      } else if (var2 == OperationType.ROR) {
         this.q(var1, this.rL, this.Wp);
         this.q(var1, this.eJ, this.Wp);
         this.q(var1, this.bl, var3.lsb());
         this.q(var1, var3, EUtil.orB(EUtil.op(OperationType.SHR, var3, this.cR), this.YN.zeroExtend(32).leftShift(31)));
         this.q(var1, this.YN, this.bl);
      } else {
         Assert.fail();
      }
   }

   void RF(ConverterInstructionEntry var1, boolean var2, boolean var3) {
      IEGeneric var4 = this.KT(var1);
      String var6 = "Read" + (var2 ? "Timer" : "Counter");
      FunctionOptype var7 = this.gCtx.createFunctionType(var6, 0, 1, 1, 16);
      IEOperation var5 = this.ctx.createOperation(var7, var4);
      if (var3) {
         FunctionOptype var8 = this.gCtx.createFunctionType("IntToBCD", 0, 1, 1, 0);
         var5 = this.ctx.createOperation(var8, var5);
      }

      this.q(var1, this.ZT, this.zx);
      this.q(var1, this.Dw(1), var5);
   }

   void oW(ConverterInstructionEntry var1, boolean var2) {
      this.q(var1, this.IN, this.Wp);
      this.q(var1, this.Bu, this.Wp);
      this.q(var1, this.Rr, this.Wp);
      if (var2) {
         this.q(var1, this.bl, this.EB);
         this.q(var1, this.EB, this.cY);
         this.q(var1, var1.reljmp(2), EUtil.notB(this.bl));
      }

      this.xW(var1);
   }

   void gO(ConverterInstructionEntry var1, boolean var2) {
      vb var3 = (vb)((PY)var1.insn).getOperand(0);
      ArrayList var4 = null;
      int var5 = var3.RF();
      if (var5 != 10 && var5 != 12) {
         if (var5 != 11 && var5 != 13) {
            throw new RuntimeException();
         }
      } else {
         var4 = new ArrayList();
         if (this.nctx != null) {
            List var6 = null;
            if (var3.q() == 0) {
               int var7 = var3.xK();
               if (this.mI != null) {
                  S7.BlockType var8 = var5 == 10 ? S7.BlockType.FC : S7.BlockType.SFC;
                  long var9 = this.mI.getAddressOfCode(var8, var7);
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

            List var14 = ((PY)var1.insn).q(var1.address, this.nctx.getMemory());
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
                  var24 = this.q(var10, var22 == 0);
               }

               var4.add(var24);
               var15++;
            }
         }
      }

      this.q(var1, this.IN, this.Wp);
      this.q(var1, this.Bu, this.Wp);
      this.q(var1, this.Rr, this.Wp);
      if (var2) {
         this.q(var1, this.bl, this.EB);
         this.q(var1, this.EB, this.cY);
         this.q(var1, var1.reljmp(2), EUtil.notB(this.bl));
      }

      long var13 = var1.address + ((PY)var1.insn).getSize();
      this.q(var1, this.fi, EUtil.imm(var13, 32));
      if (var4 != null) {
         int var16 = 0;

         for (IEGeneric var21 : var4) {
            this.q(var1, this.fQ[var16], var21);
            var16++;
         }
      }

      IEGeneric var17 = this.KT(var1);
      var17 = this.q(((vb)((PY)var1.insn).getOperand(0)).RF(), var17);
      var1.r.add(this.ctx.createBranchAssign(this.ui, var17, true));
   }

   private IEGeneric q(int var1, IEGeneric var2) {
      FunctionOptype var4 = this.gCtx.createFunctionType(switch (var1) {
         case 4, 5 -> "GetDBAddress";
         default -> throw new RuntimeException();
         case 10 -> "GetFCAddress";
         case 11 -> "GetFBAddress";
         case 12 -> "GetSFCAddress";
         case 13 -> "GetSFBAddress";
         case 15 -> "GetOBAddress";
      }, 0, 1, 1, 32);
      return this.gCtx.createOperation(var4, var2);
   }

   private IEGeneric RF(int var1, IEGeneric var2) {
      switch (var1) {
         case 4:
         case 5:
            String var3 = "GetDBLength";
            FunctionOptype var4 = this.gCtx.createFunctionType(var3, 0, 1, 1, 16);
            return this.gCtx.createOperation(var4, var2);
         default:
            throw new RuntimeException();
      }
   }

   private IEGeneric q(int var1, boolean var2) {
      Assert.a((var1 >>> 19 & 31) == 0);
      S7.AreaType var3 = S7.AreaType.fromId(var1 >>> 24 & 0xFF);

      IEVar var4 = switch (var3) {
         case V -> this.nv;
         case M -> this.ZA;
         case I -> this.br;
         case Q -> this.tW;
         case DB -> this.Ov;
         case DI -> this.Lj;
         default -> throw new RuntimeException();
      };
      int var5 = var1 >>> 3 & 65535;
      int var6 = var1 & 7;
      IEOperation var7 = EUtil.add(var4, EUtil.imm(var5, 32));
      if (var6 != 0) {
         FunctionOptype var8 = this.gCtx.createFunctionType("BitAddr", 0, 2, 2, 32);
         var7 = this.gCtx.createOperation(var8, var7, EUtil.imm(var6, 32));
      }

      if (var2) {
         FunctionOptype var9 = this.gCtx.createFunctionType("ToMC7P", 16, 1, 1, 32);
         var7 = this.gCtx.createOperation(var9, var7);
      }

      return var7;
   }
}
