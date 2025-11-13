package com.pnfsoftware.jeb.corei.parsers.arm.converter;

import com.pnfsoftware.jeb.client.S;
import com.pnfsoftware.jeb.core.exceptions.JebRuntimeException;
import com.pnfsoftware.jeb.core.units.code.AddressableInstruction;
import com.pnfsoftware.jeb.core.units.code.IDFA;
import com.pnfsoftware.jeb.core.units.code.IInstructionOperand;
import com.pnfsoftware.jeb.core.units.code.asm.cfg.BasicBlock;
import com.pnfsoftware.jeb.core.units.code.asm.cfg.CFG;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.AbstractConverter;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ConverterInstructionEntry;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.IERoutineContext;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.exceptions.IllegalConversionException;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.exceptions.InstructionConversionException;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.exceptions.UnsupportedConversionException;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.EState;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.EUtil;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.IEAssign;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.IEBranchDetails;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.IECall;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.IECompose;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.IECond;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.IEGeneric;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.IEImm;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.IEMem;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.IEOperation;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.IESlice;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.IEStatement;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.IEUntranslatedInstruction;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.IEVar;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.IWildcardPrototype;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.IWildcardType;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.IWildcardTypeManager;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.OperationType;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.compiler.EExpressionMatcher;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.compiler.Node;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.compiler.O;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.compiler.Util;
import com.pnfsoftware.jeb.core.units.code.asm.items.INativeMethodItem;
import com.pnfsoftware.jeb.core.units.code.asm.processor.IProcessor;
import com.pnfsoftware.jeb.core.units.code.asm.processor.IRegisterBank;
import com.pnfsoftware.jeb.core.units.code.asm.processor.RegisterDescriptionEntry;
import com.pnfsoftware.jeb.core.units.code.asm.processor.arch.RegisterBankArm;
import com.pnfsoftware.jeb.core.units.code.asm.processor.arch.RegisterUtil;
import com.pnfsoftware.jeb.core.units.code.asm.simulator.IInsnEmulator;
import com.pnfsoftware.jeb.core.units.code.asm.type.INativeType;
import com.pnfsoftware.jeb.core.units.codeobject.ProcessorType;
import com.pnfsoftware.jeb.util.collect.Lists;
import com.pnfsoftware.jeb.util.collect.ReferenceCounter;
import com.pnfsoftware.jeb.util.format.Strings;
import com.pnfsoftware.jeb.util.logging.GlobalLog;
import com.pnfsoftware.jeb.util.logging.ILogger;
import com.pnfsoftware.jeb.util.serialization.annotations.Ser;
import com.pnfsoftware.jeb.util.serialization.annotations.SerCustomInitPostGraph;
import com.pnfsoftware.jeb.util.serialization.annotations.SerId;
import com.pnfsoftware.jeb.util.serialization.annotations.SerTransient;
import com.pnfsoftware.jebglobal.Bc;
import com.pnfsoftware.jebglobal.FH;
import com.pnfsoftware.jebglobal.Gq;
import com.pnfsoftware.jebglobal.KH;
import com.pnfsoftware.jebglobal.MX;
import com.pnfsoftware.jebglobal.OA;
import com.pnfsoftware.jebglobal.PU;
import com.pnfsoftware.jebglobal.Uw;
import com.pnfsoftware.jebglobal.Wl;
import com.pnfsoftware.jebglobal.Yg;
import com.pnfsoftware.jebglobal.Z;
import com.pnfsoftware.jebglobal.ZV;
import com.pnfsoftware.jebglobal.ala;
import com.pnfsoftware.jebglobal.cv;
import com.pnfsoftware.jebglobal.hy;
import com.pnfsoftware.jebglobal.lw;
import com.pnfsoftware.jebglobal.mN;
import com.pnfsoftware.jebglobal.pY;
import com.pnfsoftware.jebglobal.rw;
import com.pnfsoftware.jebglobal.u;
import com.pnfsoftware.jebglobal.yP;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Ser
public class HE extends AbstractConverter {
   private static final ILogger e = GlobalLog.getLogger(HE.class);
   private static final Object[] xM = new Object[]{
      "R0",
      0,
      32,
      "R1",
      32,
      32,
      "R2",
      64,
      32,
      "R3",
      96,
      32,
      "R4",
      128,
      32,
      "R5",
      160,
      32,
      "R6",
      192,
      32,
      "R7",
      224,
      32,
      "R8",
      256,
      32,
      "R9",
      288,
      32,
      "R10",
      320,
      32,
      "R11",
      352,
      32,
      "R12",
      384,
      32,
      "SP",
      416,
      32,
      "LR",
      448,
      32,
      "PC",
      480,
      32,
      "PSTATE.MODE",
      512,
      5,
      "Thumb",
      517,
      1,
      "PSTATE.GE",
      528,
      4,
      "Sat",
      539,
      1,
      "oVerflow",
      540,
      1,
      "Carry",
      541,
      1,
      "Zero",
      542,
      1,
      "Negative",
      543,
      1,
      "Q0",
      3072,
      128,
      "Q1",
      3200,
      128,
      "Q2",
      3328,
      128,
      "Q3",
      3456,
      128,
      "Q4",
      3584,
      128,
      "Q5",
      3712,
      128,
      "Q6",
      3840,
      128,
      "Q7",
      3968,
      128,
      "Q8",
      4096,
      128,
      "Q9",
      4224,
      128,
      "Q10",
      4352,
      128,
      "Q11",
      4480,
      128,
      "Q12",
      4608,
      128,
      "Q13",
      4736,
      128,
      "Q14",
      4864,
      128,
      "Q15",
      4992,
      128,
      "D0",
      3072,
      64,
      "D1",
      3136,
      64,
      "D2",
      3200,
      64,
      "D3",
      3264,
      64,
      "D4",
      3328,
      64,
      "D5",
      3392,
      64,
      "D6",
      3456,
      64,
      "D7",
      3520,
      64,
      "D8",
      3584,
      64,
      "D9",
      3648,
      64,
      "D10",
      3712,
      64,
      "D11",
      3776,
      64,
      "D12",
      3840,
      64,
      "D13",
      3904,
      64,
      "D14",
      3968,
      64,
      "D15",
      4032,
      64,
      "D16",
      4096,
      64,
      "D17",
      4160,
      64,
      "D18",
      4224,
      64,
      "D19",
      4288,
      64,
      "D20",
      4352,
      64,
      "D21",
      4416,
      64,
      "D22",
      4480,
      64,
      "D23",
      4544,
      64,
      "D24",
      4608,
      64,
      "D25",
      4672,
      64,
      "D26",
      4736,
      64,
      "D27",
      4800,
      64,
      "D28",
      4864,
      64,
      "D29",
      4928,
      64,
      "D30",
      4992,
      64,
      "D31",
      5056,
      64,
      "S0",
      3072,
      32,
      "S1",
      3104,
      32,
      "S2",
      3136,
      32,
      "S3",
      3168,
      32,
      "S4",
      3200,
      32,
      "S5",
      3232,
      32,
      "S6",
      3264,
      32,
      "S7",
      3296,
      32,
      "S8",
      3328,
      32,
      "S9",
      3360,
      32,
      "S10",
      3392,
      32,
      "S11",
      3424,
      32,
      "S12",
      3456,
      32,
      "S13",
      3488,
      32,
      "S14",
      3520,
      32,
      "S15",
      3552,
      32,
      "S16",
      3584,
      32,
      "S17",
      3616,
      32,
      "S18",
      3648,
      32,
      "S19",
      3680,
      32,
      "S20",
      3712,
      32,
      "S21",
      3744,
      32,
      "S22",
      3776,
      32,
      "S23",
      3808,
      32,
      "S24",
      3840,
      32,
      "S25",
      3872,
      32,
      "S26",
      3904,
      32,
      "S27",
      3936,
      32,
      "S28",
      3968,
      32,
      "S29",
      4000,
      32,
      "S30",
      4032,
      32,
      "S31",
      4064,
      32,
      "c0",
      9216,
      32,
      "c1",
      9248,
      32,
      "c2",
      9280,
      32,
      "c3",
      9312,
      32,
      "c4",
      9344,
      32,
      "c5",
      9376,
      32,
      "c6",
      9408,
      32,
      "c7",
      9440,
      32,
      "c8",
      9472,
      32,
      "c9",
      9504,
      32,
      "c10",
      9536,
      32,
      "c11",
      9568,
      32,
      "c12",
      9600,
      32,
      "c13",
      9632,
      32,
      "c14",
      9664,
      32,
      "c15",
      9696,
      32
   };
   private static final Object[] kU = new Object[]{
      "PSTATE.F",
      550,
      1,
      "PSTATE.I",
      551,
      1,
      "PSTATE.A",
      552,
      1,
      "PSTATE.D",
      553,
      1,
      "PSTATE.SP",
      576,
      1,
      "X0",
      768,
      64,
      "W0",
      768,
      32,
      "X1",
      832,
      64,
      "W1",
      832,
      32,
      "X2",
      896,
      64,
      "W2",
      896,
      32,
      "X3",
      960,
      64,
      "W3",
      960,
      32,
      "X4",
      1024,
      64,
      "W4",
      1024,
      32,
      "X5",
      1088,
      64,
      "W5",
      1088,
      32,
      "X6",
      1152,
      64,
      "W6",
      1152,
      32,
      "X7",
      1216,
      64,
      "W7",
      1216,
      32,
      "X8",
      1280,
      64,
      "W8",
      1280,
      32,
      "X9",
      1344,
      64,
      "W9",
      1344,
      32,
      "X10",
      1408,
      64,
      "W10",
      1408,
      32,
      "X11",
      1472,
      64,
      "W11",
      1472,
      32,
      "X12",
      1536,
      64,
      "W12",
      1536,
      32,
      "X13",
      1600,
      64,
      "W13",
      1600,
      32,
      "X14",
      1664,
      64,
      "W14",
      1664,
      32,
      "X15",
      1728,
      64,
      "W15",
      1728,
      32,
      "X16",
      1792,
      64,
      "W16",
      1792,
      32,
      "X17",
      1856,
      64,
      "W17",
      1856,
      32,
      "X18",
      1920,
      64,
      "W18",
      1920,
      32,
      "X19",
      1984,
      64,
      "W19",
      1984,
      32,
      "X20",
      2048,
      64,
      "W20",
      2048,
      32,
      "X21",
      2112,
      64,
      "W21",
      2112,
      32,
      "X22",
      2176,
      64,
      "W22",
      2176,
      32,
      "X23",
      2240,
      64,
      "W23",
      2240,
      32,
      "X24",
      2304,
      64,
      "W24",
      2304,
      32,
      "X25",
      2368,
      64,
      "W25",
      2368,
      32,
      "X26",
      2432,
      64,
      "W26",
      2432,
      32,
      "X27",
      2496,
      64,
      "W27",
      2496,
      32,
      "X28",
      2560,
      64,
      "W28",
      2560,
      32,
      "X29",
      2624,
      64,
      "W29",
      2624,
      32,
      "X30",
      2688,
      64,
      "W30",
      2688,
      32,
      "XSP",
      2752,
      64,
      "WSP",
      2752,
      32,
      "PC",
      2816,
      64,
      "XZR",
      2880,
      64,
      "WZR",
      2880,
      32,
      "V0",
      5120,
      128,
      "V1",
      5248,
      128,
      "V2",
      5376,
      128,
      "V3",
      5504,
      128,
      "V4",
      5632,
      128,
      "V5",
      5760,
      128,
      "V6",
      5888,
      128,
      "V7",
      6016,
      128,
      "V8",
      6144,
      128,
      "V9",
      6272,
      128,
      "V10",
      6400,
      128,
      "V11",
      6528,
      128,
      "V12",
      6656,
      128,
      "V13",
      6784,
      128,
      "V14",
      6912,
      128,
      "V15",
      7040,
      128,
      "V16",
      7168,
      128,
      "V17",
      7296,
      128,
      "V18",
      7424,
      128,
      "V19",
      7552,
      128,
      "V20",
      7680,
      128,
      "V21",
      7808,
      128,
      "V22",
      7936,
      128,
      "V23",
      8064,
      128,
      "V24",
      8192,
      128,
      "V25",
      8320,
      128,
      "V26",
      8448,
      128,
      "V27",
      8576,
      128,
      "V28",
      8704,
      128,
      "V29",
      8832,
      128,
      "V30",
      8960,
      128,
      "V31",
      9088,
      128
   };
   @SerTransient
   Sv pC;
   @SerTransient
   Ws A;
   @SerTransient
   cq kS;
   @SerTransient
   protected rQ wS;
   @SerTransient
   zp UT;
   @SerTransient
   yt E;
   @SerTransient
   sy sY;
   @SerTransient
   Kr ys;
   @SerTransient
   io ld;
   @SerTransient
   Pj gp;
   @SerTransient
   RC oT;
   @SerTransient
   private long[] Kq;
   @SerTransient
   private Map go;
   @SerId(5)
   int fI;
   @SerId(10)
   IEVar WR;
   @SerId(11)
   IEVar NS;
   @SerId(12)
   IEVar vP;
   @SerId(13)
   IEVar xC;
   @SerId(6)
   IEVar ED;
   @SerId(7)
   IEVar Sc;
   @SerId(8)
   IEVar ah;
   @SerId(9)
   IEVar eP;
   @SerId(14)
   private IEVar JF;
   @SerId(15)
   private IEVar Nq;
   @SerId(16)
   private IEVar pg;
   @SerId(18)
   IEVar UO;
   @SerId(19)
   IEVar Ab;
   @SerId(25)
   protected IEVar rl;
   @SerId(26)
   IEVar z;
   @SerId(27)
   IEVar Ek;
   @SerId(29)
   IEVar hK;
   @SerId(30)
   private IEVar gj;
   @SerId(31)
   private IEVar ZD;
   @SerId(32)
   private IEVar DL;
   @SerId(36)
   private IEVar UH;
   @SerId(37)
   private IEVar VD;
   @SerId(38)
   private IEVar Xs;
   @SerId(39)
   private IEVar KV;
   @SerId(40)
   private IEVar FK;
   @SerId(41)
   private IEVar Bi;
   @SerId(42)
   private IEVar wQ;
   @SerId(43)
   private IEVar PZ;
   @SerId(44)
   private IEVar Ip;
   @SerId(45)
   private IEVar Fm;
   @SerId(46)
   private IEVar FM;
   @SerId(35)
   IEVar Er;
   @SerId(33)
   IEImm FE;
   @SerId(34)
   IEImm Aj;
   @SerId(50)
   IEVar EX;
   @SerId(51)
   IEVar LM;
   @SerId(52)
   IEVar mv;
   @SerId(53)
   IEVar sO;
   @SerId(54)
   IEVar os;
   @SerId(60)
   IEVar Cu;
   @SerId(61)
   IEVar hZ;
   @SerId(62)
   IEVar UW;
   @SerId(63)
   IEVar PR;
   @SerId(64)
   IEVar cX;
   @SerId(65)
   IEVar DQ;
   @SerId(66)
   IEVar ZN;
   @SerId(67)
   IEVar OB;
   @SerId(68)
   IEVar pF;
   @SerId(70)
   private IEVar Wn;
   @SerId(71)
   private IEVar gy;
   @SerId(72)
   private IEVar pt;
   @SerId(73)
   private IEVar uE;
   @SerId(90)
   private int Um = 0;
   @SerId(91)
   private Map Ta = new HashMap();
   @SerId(92)
   private int So = 12672;
   @SerId(200)
   ReferenceCounter Bc;
   @SerId(201)
   int OI;
   @SerId(202)
   int Bf;
   @SerId(203)
   int Pe;
   @SerId(204)
   ReferenceCounter ck;
   @SerId(205)
   oP RW;

   public HE(IProcessor var1) {
      super(var1, var1.getMode() <= 32 ? 32 : 64);
      this.fI = this.regNormalBitsize;
      Object[][] var2 = var1.getMode() == 64 ? new Object[][]{kU, xM} : new Object[][]{xM};

      for (Object[] var6 : var2) {
         for (byte var7 = 0; var7 < var6.length; var7 += 3) {
            String var8 = (String)var6[var7];
            int var9 = (Integer)var6[var7 + 1];
            int var10 = (Integer)var6[var7 + 2];
            if (this.gCtx.canCreateVariable(var9, var10)) {
               this.gCtx.createRegister(var9, var8, var10);
            }
         }
      }

      this.WR = this.gCtx.getVar(0);
      this.NS = this.gCtx.getVar(32);
      this.vP = this.gCtx.getVar(64);
      this.xC = this.gCtx.getVar(96);
      this.ED = this.gCtx.getVar(128);
      this.Sc = this.gCtx.getVar(160);
      this.ah = this.gCtx.getVar(192);
      this.eP = this.gCtx.getVar(224);
      this.pg = this.gCtx.getVar(416);
      this.JF = this.gCtx.getVar(448);
      this.Nq = this.gCtx.getVar(480);
      if (var1.getMode() == 64) {
         this.Cu = this.gCtx.getVar(768);
         this.hZ = this.gCtx.getVar(832);
         this.UW = this.gCtx.getVar(896);
         this.PR = this.gCtx.getVar(960);
         this.cX = this.gCtx.getVar(1024);
         this.DQ = this.gCtx.getVar(1088);
         this.ZN = this.gCtx.getVar(1152);
         this.OB = this.gCtx.getVar(1216);
         this.pF = this.gCtx.getVar(1280);
         this.pt = this.gCtx.getVar(2624);
         this.Wn = this.gCtx.getVar(2688);
         this.gy = this.gCtx.getVar(2752);
         this.uE = this.gCtx.getVar(2816);
         this.EX = this.gCtx.getVar(553);
         this.LM = this.gCtx.getVar(552);
         this.mv = this.gCtx.getVar(551);
         this.sO = this.gCtx.getVar(550);
         this.os = this.gCtx.getVar(576);
      }

      this.RW = new oP(this.gCtx);
      this.rl = this.gCtx.getVar(517);
      this.z = this.gCtx.getVar(539);
      this.Ek = this.gCtx.getVar(528);
      this.hK = this.gCtx.getVar(512);
      this.ZD = this.gCtx.createVirtualRegister(65536, "tmp", 32);
      this.DL = this.gCtx.createVirtualRegister(65568, "tmp64", 64);
      this.UH = this.gCtx.createVirtualRegister(65664, "tmpPC", 32);
      this.gj = this.gCtx.createVirtualRegister(65792, "tmpPredicate", 1);
      this.Er = this.gCtx.createVirtualRegister(65793, "carryIn", 1);
      this.FE = this.gCtx.createImm(0L, 1);
      this.Aj = this.gCtx.createImm(1L, 1);
      this.wS();
      this.Bc = new ReferenceCounter();
      this.ck = new ReferenceCounter();
   }

   @SerCustomInitPostGraph
   private void wS() {
      this.pC = new Sv(this);
      this.A = new Ws(this);
      this.kS = new cq(this);
      this.wS = new rQ(this);
      this.UT = new zp(this);
      this.E = new yt(this);
      this.sY = new sy(this);
      this.ys = new Kr(this);
      this.ld = new io(this);
      this.gp = new Pj(this);
      this.oT = new RC(this);
      this.go = new HashMap();
      if (this.RW == null) {
         this.RW = new oP(this.gCtx);
      }

      if (this.VD == null) {
         this.VD = this.gCtx.createVirtualRegister(65816, "tmp8", 8);
      }

      if (this.Xs == null) {
         this.Xs = this.gCtx.createVirtualRegister(65824, "tmp16", 16);
      }

      if (this.KV == null) {
         this.KV = this.gCtx.createVirtualRegister(65840, "tmp128", 128);
      }

      if (this.FK == null) {
         this.FK = this.gCtx.createVirtualRegister(65696, "tmp64_2", 64);
      }

      if (this.wQ == null) {
         this.wQ = this.gCtx.createVirtualRegister(65760, "tmpData32", 32);
      }

      if (this.PZ == null) {
         this.PZ = this.gCtx.createVirtualRegister(65984, "tmpData64", 64);
      }

      if (this.FM == null) {
         this.FM = this.gCtx.createVirtualRegister(65794, "tmpData1", 1);
      }

      if (this.Ip == null) {
         this.Ip = this.gCtx.createVirtualRegister(65808, "tmpData8", 8);
      }

      if (this.Fm == null) {
         this.Fm = this.gCtx.createVirtualRegister(65968, "tmpData16", 16);
      }

      if (this.Bi == null) {
         this.Bi = this.gCtx.createVirtualRegister(66304, "tmpLr", 64);
      }

      if (this.Um != 0) {
         this.So = 12672 + this.Um * 64;
      }
   }

   @Override
   public IEVar getProgramCounter() {
      return this.proc.getMode() == 64 ? this.uE : this.Nq;
   }

   @Override
   public IEVar getReturnAddressRegister() {
      return this.proc.getMode() == 64 ? this.Wn : this.JF;
   }

   @Override
   public IEVar getStackPointer() {
      return this.proc.getMode() == 64 ? this.gy : this.pg;
   }

   @Override
   public long sanitizeNativeAddress(long var1) {
      var1 = super.sanitizeNativeAddress(var1);
      return (var1 & 1L) != 0L ? var1 & -2L : var1 & -4L;
   }

   public IEVar pC() {
      return this.getReturnAddressRegister();
   }

   public IEVar A() {
      return this.RW.kS;
   }

   public int kS() {
      return this.proc.getMode() == 64 ? 2688 : 448;
   }

   @Override
   public void setCurrentContext(IERoutineContext var1) {
      super.setCurrentContext(var1);
      this.pC.A = var1;
      this.A.A = var1;
      this.kS.A = var1;
      this.wS.A = var1;
      this.UT.A = var1;
      this.E.A = var1;
      this.sY.A = var1;
      this.ys.A = var1;
      this.ld.A = var1;
      this.gp.A = var1;
      this.oT.A = var1;
      this.RW.sY = var1;
   }

   @Override
   public void convertInstruction(ConverterInstructionEntry var1) {
      this.pC((com.pnfsoftware.jeb.corei.parsers.arm.rQ)var1.insn, var1.r, var1.address);
   }

   public void pC(com.pnfsoftware.jeb.corei.parsers.arm.rQ var1, List var2, long var3) {
      ArrayList var5 = new ArrayList();
      this.A(var1, var5, var3);
      if (!var5.isEmpty() && var1.UT().sY()) {
         IEGeneric var6 = this.RW.pC(var1.UT().A(), null);
         ArrayList var7 = new ArrayList();
         this.RW.pC(var2.size(), var7, var6, var5, var3 + var1.getSize(), true, this.getProgramCounter());
         var2.addAll(var7);
      } else {
         var2.addAll(var5);
      }
   }

   public void A(com.pnfsoftware.jeb.corei.parsers.arm.rQ var1, List var2, long var3) {
      String var5 = var1.wS().pC();
      if (var1.sY()) {
         var2.add(this.pC(var1, var3, false));
      } else if (var1.E()) {
         if (var1.getProcessorMode() == 64) {
            this.ld.pC(var1, var2, var3);
         } else {
            this.ys.pC(var1, var2, var3);
         }
      } else {
         switch (var5) {
            case "ADC":
               this.pC.UT(var1, var2, var3);
               break;
            case "ADD":
            case "ADDW":
               this.pC.E(var1, var2, var3);
               break;
            case "AND":
               this.pC.sY(var1, var2, var3);
               break;
            case "BIC":
               this.pC.ys(var1, var2, var3);
               break;
            case "EOR":
               this.pC.ld(var1, var2, var3);
               break;
            case "EON":
               this.pC.gp(var1, var2, var3);
               break;
            case "ORN":
               this.pC.oT(var1, var2, var3);
               break;
            case "ORR":
               this.pC.fI(var1, var2, var3);
               break;
            case "RSB":
               this.pC.WR(var1, var2, var3);
               break;
            case "RSC":
               this.pC.NS(var1, var2, var3);
               break;
            case "SBC":
               this.pC.ED(var1, var2, var3);
               break;
            case "SUB":
            case "SUBW":
               this.pC.vP(var1, var2, var3);
               break;
            case "NEG":
               this.pC.xC(var1, var2, var3);
               break;
            case "NGC":
               this.pC.Sc(var1, var2, var3);
               break;
            case "MADD":
            case "MLA":
               this.pC.pC(var1, var2, var3, OperationType.ADD);
               break;
            case "MSUB":
            case "MLS":
               this.pC.pC(var1, var2, var3, OperationType.SUB);
               break;
            case "MNEG":
               this.pC.pC(var1, var2, var3, OperationType.SUB, true);
               break;
            case "MUL":
               this.pC.rl(var1, var2, var3);
               break;
            case "BFC":
               this.pC.kS(var1, var2);
               break;
            case "BFI":
               this.pC.z(var1, var2, var3);
               break;
            case "BFXIL":
               this.pC.Ek(var1, var2, var3);
               break;
            case "CLS":
               this.pC.Er(var1, var2, var3);
               break;
            case "CLZ":
               this.pC.hK(var1, var2, var3);
               break;
            case "QADD":
               this.A.pC(var1, var2, var3);
               break;
            case "QADD16":
               this.A.ld(var1, var2, var3);
               break;
            case "QADD8":
               this.A.gp(var1, var2, var3);
               break;
            case "QSUB":
               this.A.A(var1, var2, var3);
               break;
            case "QSUB16":
               this.A.oT(var1, var2, var3);
               break;
            case "QSUB8":
               this.A.fI(var1, var2, var3);
               break;
            case "QASX":
               this.A.WR(var1, var2, var3);
               break;
            case "QSAX":
               this.A.NS(var1, var2, var3);
               break;
            case "QDADD":
               this.A.kS(var1, var2, var3);
               break;
            case "QDSUB":
               this.A.wS(var1, var2, var3);
               break;
            case "SSAT":
               this.A.UT(var1, var2, var3);
               break;
            case "SSAT16":
               this.A.sY(var1, var2, var3);
               break;
            case "UQADD16":
               this.A.vP(var1, var2, var3);
               break;
            case "UQADD8":
               this.A.xC(var1, var2, var3);
               break;
            case "UQASX":
               this.A.ah(var1, var2, var3);
               break;
            case "UQSAX":
               this.A.eP(var1, var2, var3);
               break;
            case "UQSUB16":
               this.A.ED(var1, var2, var3);
               break;
            case "UQSUB8":
               this.A.Sc(var1, var2, var3);
               break;
            case "USAT":
               this.A.E(var1, var2, var3);
               break;
            case "USAT16":
               this.A.ys(var1, var2, var3);
               break;
            case "SADD16":
               this.A.UO(var1, var2, var3);
               break;
            case "SADD8":
               this.A.Ab(var1, var2, var3);
               break;
            case "SASX":
               this.A.Ek(var1, var2, var3);
               break;
            case "SSAX":
               this.A.hK(var1, var2, var3);
               break;
            case "SHADD16":
               this.A.sO(var1, var2, var3);
               break;
            case "SHADD8":
               this.A.os(var1, var2, var3);
               break;
            case "SHASX":
               this.A.UW(var1, var2, var3);
               break;
            case "SHSAX":
               this.A.PR(var1, var2, var3);
               break;
            case "SSUB16":
               this.A.rl(var1, var2, var3);
               break;
            case "SSUB8":
               this.A.z(var1, var2, var3);
               break;
            case "SHSUB16":
               this.A.Cu(var1, var2, var3);
               break;
            case "SHSUB8":
               this.A.hZ(var1, var2, var3);
               break;
            case "UADD16":
               this.A.Er(var1, var2, var3);
               break;
            case "UADD8":
               this.A.FE(var1, var2, var3);
               break;
            case "UASX":
               this.A.LM(var1, var2, var3);
               break;
            case "USAX":
               this.A.mv(var1, var2, var3);
               break;
            case "UHADD16":
               this.A.cX(var1, var2, var3);
               break;
            case "UHADD8":
               this.A.DQ(var1, var2, var3);
               break;
            case "UHASX":
               this.A.pF(var1, var2, var3);
               break;
            case "UHSAX":
               this.A.Bc(var1, var2, var3);
               break;
            case "USUB16":
               this.A.Aj(var1, var2, var3);
               break;
            case "USUB8":
               this.A.EX(var1, var2, var3);
               break;
            case "UHSUB16":
               this.A.ZN(var1, var2, var3);
               break;
            case "UHSUB8":
               this.A.OB(var1, var2, var3);
               break;
            case "USAD8":
               this.A.OI(var1, var2, var3);
               break;
            case "USADA8":
               this.A.Bf(var1, var2, var3);
               break;
            case "SMLABB":
               this.A.go(var1, var2, var3);
               break;
            case "SMLABT":
               this.A.JF(var1, var2, var3);
               break;
            case "SMLATB":
               this.A.Nq(var1, var2, var3);
               break;
            case "SMLATT":
               this.A.pg(var1, var2, var3);
               break;
            case "SMLALBB":
               this.A.VD(var1, var2, var3);
               break;
            case "SMLALBT":
               this.A.Xs(var1, var2, var3);
               break;
            case "SMLALTB":
               this.A.KV(var1, var2, var3);
               break;
            case "SMLALTT":
               this.A.FK(var1, var2, var3);
               break;
            case "SMULBB":
               this.A.gj(var1, var2, var3);
               break;
            case "SMULBT":
               this.A.ZD(var1, var2, var3);
               break;
            case "SMULTB":
               this.A.DL(var1, var2, var3);
               break;
            case "SMULTT":
               this.A.UH(var1, var2, var3);
               break;
            case "SMMLA":
            case "SMMLAR":
               this.A.Bi(var1, var2, var3);
               break;
            case "SMMLS":
            case "SMMLSR":
               this.A.wQ(var1, var2, var3);
               break;
            case "SMMUL":
            case "SMMULR":
               this.A.PZ(var1, var2, var3);
               break;
            case "UMAAL":
               this.A.Ip(var1, var2, var3);
               break;
            case "SMLAL":
               this.A.FM(var1, var2, var3);
               break;
            case "UMLAL":
               this.A.Fm(var1, var2, var3);
               break;
            case "SMULL":
               if (this.proc.getMode() == 64) {
                  this.kS.pC(var1, var2, var3);
               } else {
                  this.A.gy(var1, var2, var3);
               }
               break;
            case "UMULL":
               if (this.proc.getMode() == 64) {
                  this.kS.E(var1, var2, var3);
               } else {
                  this.A.Wn(var1, var2, var3);
               }
               break;
            case "SMLAD":
            case "SMLADX":
               this.A.Um(var1, var2, var3);
               break;
            case "SMLSD":
            case "SMLSDX":
               this.A.Ta(var1, var2, var3);
               break;
            case "SMUAD":
            case "SMUADX":
               this.A.So(var1, var2, var3);
               break;
            case "SMUSD":
            case "SMUSDX":
               this.A.tH(var1, var2, var3);
               break;
            case "SMLALD":
            case "SMLALDX":
               this.A.Gm(var1, var2, var3);
               break;
            case "SMLSLD":
            case "SMLSLDX":
               this.A.Br(var1, var2, var3);
               break;
            case "SMULWB":
            case "SMULWT":
               this.A.pt(var1, var2, var3);
               break;
            case "SMLAWB":
            case "SMLAWT":
               this.A.uE(var1, var2, var3);
               break;
            case "SMADDL":
               this.kS.A(var1, var2, var3);
               break;
            case "SMNEGL":
               this.kS.kS(var1, var2, var3);
               break;
            case "SMSUBL":
               this.kS.wS(var1, var2, var3);
               break;
            case "SMULH":
               this.kS.UT(var1, var2, var3);
               break;
            case "UMADDL":
               this.kS.sY(var1, var2, var3);
               break;
            case "UMNEGL":
               this.kS.ys(var1, var2, var3);
               break;
            case "UMSUBL":
               this.kS.ld(var1, var2, var3);
               break;
            case "UMULH":
               this.kS.gp(var1, var2, var3);
               break;
            case "SXTAB":
               this.A.ck(var1, var2, var3);
               break;
            case "SXTAB16":
               this.A.RW(var1, var2, var3);
               break;
            case "SXTAH":
               this.A.e(var1, var2, var3);
               break;
            case "UXTAB":
               this.A.xM(var1, var2, var3);
               break;
            case "UXTAB16":
               this.A.kU(var1, var2, var3);
               break;
            case "UXTAH":
               this.A.Kq(var1, var2, var3);
               break;
            case "SXTB":
               this.pC.cX(var1, var2, var3);
               break;
            case "SXTB16":
               this.pC.DQ(var1, var2, var3);
               break;
            case "SXTH":
               this.pC.ZN(var1, var2, var3);
               break;
            case "SXTW":
               this.pC.OB(var1, var2, var3);
               break;
            case "UXTB":
               this.pC.pF(var1, var2, var3);
               break;
            case "UXTB16":
               this.pC.Bc(var1, var2, var3);
               break;
            case "UXTH":
               this.pC.OI(var1, var2, var3);
               break;
            case "PKHBT":
               this.pC.FE(var1, var2, var3);
               break;
            case "PKHTB":
               this.pC.Aj(var1, var2, var3);
               break;
            case "RBIT":
               this.pC.EX(var1, var2, var3);
               break;
            case "REV":
               this.pC.LM(var1, var2, var3);
               break;
            case "REV16":
               this.pC.mv(var1, var2, var3);
               break;
            case "REV32":
               this.pC.sO(var1, var2, var3);
               break;
            case "REVSH":
               this.pC.os(var1, var2, var3);
               break;
            case "SBFX":
               this.pC.Cu(var1, var2, var3);
               break;
            case "UBFX":
               this.pC.hZ(var1, var2, var3);
               break;
            case "SDIV":
               this.pC.UW(var1, var2, var3);
               break;
            case "UDIV":
               this.pC.PR(var1, var2, var3);
               break;
            case "SBFM":
               this.kS.fI(var1, var2, var3);
               break;
            case "SBFIZ":
               this.kS.oT(var1, var2, var3);
               break;
            case "UBFM":
               this.kS.NS(var1, var2, var3);
               break;
            case "UBFIZ":
               this.kS.WR(var1, var2, var3);
               break;
            case "CMP":
               this.pC.ah(var1, var2, var3);
               break;
            case "CMN":
               this.pC.eP(var1, var2, var3);
               break;
            case "TEQ":
               this.pC.UO(var1, var2, var3);
               break;
            case "TST":
               this.pC.Ab(var1, var2, var3);
               break;
            case "ADR":
            case "ADRP":
            case "MOV":
            case "MOVZ":
            case "MOVW":
               this.pC.pC(var1, var2, var3);
               break;
            case "MOVT":
               this.pC.A(var1, var2);
               break;
            case "MOVN":
            case "MVN":
               this.pC.A(var1, var2, var3);
               break;
            case "MOVK":
               this.pC.pC(var1, var2);
               break;
            case "ASR":
            case "LSL":
            case "LSR":
            case "ROR":
               this.pC.kS(var1, var2, var3);
               break;
            case "RRX":
               this.pC.wS(var1, var2, var3);
               break;
            case "CCMP":
               this.kS.gp(var1, var2);
               break;
            case "CCMN":
               this.kS.oT(var1, var2);
               break;
            case "CINC":
               this.kS.E(var1, var2);
               break;
            case "CINV":
               this.kS.UT(var1, var2);
               break;
            case "CSINC":
               this.kS.A(var1, var2);
               break;
            case "CSINV":
               this.kS.pC(var1, var2);
               break;
            case "CSEL":
               this.kS.kS(var1, var2);
               break;
            case "CSET":
               this.kS.ys(var1, var2);
               break;
            case "CNEG":
               this.kS.sY(var1, var2);
               break;
            case "CSNEG":
               this.kS.wS(var1, var2);
               break;
            case "CSETM":
               this.kS.ld(var1, var2);
               break;
            case "EXTR":
               this.kS.fI(var1, var2);
               break;
            case "SWP":
               if (this.proc.getMode() == 64) {
                  this.E.A(var1, var2, var1.pC(0).getOperandBitsize());
               } else {
                  this.UT.pC(var1, var2, var3);
               }
               break;
            case "SWPB":
               if (this.proc.getMode() == 64) {
                  this.E.A(var1, var2, 8);
               } else {
                  this.UT.A(var1, var2, var3);
               }
               break;
            case "B":
               this.wS.pC(var1, var2, var3);
               break;
            case "BX":
               this.wS.A(var1, var2, var3);
               break;
            case "BL":
               this.wS.kS(var1, var2, var3);
               break;
            case "BLX":
               this.wS.E(var1, var2, var3);
               break;
            case "BLR":
               this.wS.wS(var1, var2, var3);
               break;
            case "BR":
               this.wS.UT(var1, var2, var3);
               break;
            case "CBZ":
               this.wS.sY(var1, var2, var3);
               break;
            case "CBNZ":
               this.wS.ys(var1, var2, var3);
               break;
            case "RET":
               this.wS.ld(var1, var2, var3);
               break;
            case "TBZ":
               this.wS.gp(var1, var2, var3);
               break;
            case "TBNZ":
               this.wS.oT(var1, var2, var3);
               break;
            case "TBB":
               this.wS.fI(var1, var2, var3);
               break;
            case "TBH":
               this.wS.WR(var1, var2, var3);
               break;
            case "NOP":
            case "BTI":
            case "PACM":
            case "PLD":
            case "PLDW":
            case "PLI":
            case "PRFM":
            case "PRFUM":
               if (!this.doNotGenerateNops) {
                  var2.add(this.ctx.createNop());
               }
               break;
            case "IT":
               throw new UnsupportedConversionException("IT instruction can not be translated as a standalone instruction");
            case "MRS":
               if (this.proc.getMode() == 64) {
                  this.sY.kS(var1, var2, var3);
               } else {
                  this.sY.pC(var1, var2, var3);
               }
               break;
            case "MSR":
               if (this.proc.getMode() == 64) {
                  this.sY.wS(var1, var2, var3);
               } else {
                  this.sY.A(var1, var2, var3);
               }
               break;
            case "SVC":
               IEUntranslatedInstruction var22 = this.pC(var1, var3, true);
               if (this.proc.getMode() == 64) {
                  var22.addSideEffectUsedVariable(this.Cu, this.hZ, this.UW, this.PR, this.cX, this.DQ, this.pF);
                  var22.addSideEffectDefinedVariable(this.Cu);
               } else {
                  var22.addSideEffectUsedVariable(this.WR, this.NS, this.vP, this.xC, this.ED, this.Sc, this.ah, this.eP);
                  var22.addSideEffectDefinedVariable(this.WR);
               }

               var2.add(var22);
               break;
            case "MCR":
            case "MCR2":
               IEGeneric var21 = this.pC(var1, (IInstructionOperand)var1.A()[2], var3);
               var2.add(this.A(var1, var3, true, var21));
               break;
            case "MCRR":
            case "MCRR2":
               IEGeneric var20 = this.pC(var1, (IInstructionOperand)var1.A()[2], var3);
               IEGeneric var25 = this.pC(var1, (IInstructionOperand)var1.A()[3], var3);
               var2.add(this.A(var1, var3, true, var20, var25));
               break;
            case "MRC":
            case "MRC2":
               if (var1.A()[2].A(this.regNormalBitsize)) {
                  var2.add(this.pC(var1, var3, true, this.RW.pC, this.RW.A, this.RW.kS, this.RW.wS));
               } else {
                  var2.add(this.pC(var1, var3, true));
               }
               break;
            case "MRRC":
            case "MRRC2":
               var2.add(this.pC(var1, var3, true));
               break;
            case "AUTIA1716":
            case "AUTIASP":
            case "AUTIAZ":
            case "AUTIB1716":
            case "AUTIBSP":
            case "AUTIBZ":
               if (this.E()) {
                  this.oT.ys(var1, var2, var3, var5.charAt(4));
               } else {
                  var2.add(this.pC(var1, var3, false));
               }
               break;
            case "AUTIA":
            case "AUTIZA":
            case "AUTIB":
            case "AUTIZB":
               if (this.E()) {
                  this.oT.E(var1, var2, var3, var5.charAt(var5.length() - 1));
               } else {
                  var2.add(this.pC(var1, var3, false));
               }
               break;
            case "AUTDA":
            case "AUTDZA":
            case "AUTDB":
            case "AUTDZB":
               if (this.E()) {
                  this.oT.UT(var1, var2, var3, var5.charAt(var5.length() - 1));
               } else {
                  var2.add(this.pC(var1, var3, false));
               }
               break;
            case "PACIA1716":
            case "PACIASP":
            case "PACIAZ":
            case "PACIB1716":
            case "PACIBSP":
            case "PACIBZ":
               if (this.E()) {
                  this.oT.wS(var1, var2, var3, var5.charAt(4));
               } else {
                  var2.add(this.pC(var1, var3, false));
               }
               break;
            case "PACIA":
            case "PACIZA":
            case "PACIB":
            case "PACIZB":
               if (this.E()) {
                  this.oT.A(var1, var2, var3, var5.charAt(var5.length() - 1));
               } else {
                  var2.add(this.pC(var1, var3, false));
               }
               break;
            case "PACDA":
            case "PACDZA":
            case "PACDB":
            case "PACDZB":
               if (this.E()) {
                  this.oT.pC(var1, var2, var3, var5.charAt(var5.length() - 1));
               } else {
                  var2.add(this.pC(var1, var3, false));
               }
               break;
            case "PACGA":
               if (this.E()) {
                  this.oT.pC(var1, var2, var3);
               } else {
                  var2.add(this.pC(var1, var3, false));
               }
               break;
            case "XPACLRI":
            case "XPACD":
            case "XPACI":
               if (this.E()) {
                  this.oT.A(var1, var2, var3);
               } else {
                  var2.add(this.pC(var1, var3, false));
               }
               break;
            case "BRAA":
            case "BRAB":
            case "BRAAZ":
            case "BRABZ":
               if (this.E()) {
                  this.oT.ld(var1, var2, var3, var5.charAt(3));
               } else {
                  IEUntranslatedInstruction var19 = this.pC(var1, var3, true);
                  var19.addSideEffectDefinedVariable(this.uE);
                  var2.add(var19);
               }
               break;
            case "BLRAA":
            case "BLRAB":
            case "BLRAAZ":
            case "BLRABZ":
               if (this.E()) {
                  this.oT.gp(var1, var2, var3, var5.charAt(4));
               } else {
                  IEUntranslatedInstruction var18 = this.pC(var1, var3, true);
                  var18.addSideEffectDefinedVariable(this.uE, this.Wn);
                  var2.add(var18);
               }
               break;
            case "RETAA":
            case "RETAB":
               if (this.E()) {
                  this.oT.oT(var1, var2, var3, var5.charAt(4));
               } else {
                  IEUntranslatedInstruction var17 = this.pC(var1, var3, true);
                  var17.addSideEffectUsedVariable(this.Wn);
                  var17.addSideEffectDefinedVariable(this.uE);
                  var2.add(var17);
               }
               break;
            case "SWPLB":
            case "SWPAB":
            case "SWPALB":
               this.E.A(var1, var2, 8);
               break;
            case "SWPH":
            case "SWPLH":
            case "SWPAH":
            case "SWPALH":
               this.E.A(var1, var2, 16);
               break;
            case "SWPL":
            case "SWPA":
            case "SWPAL":
               this.E.A(var1, var2, var1.pC(0).getOperandBitsize());
               break;
            case "CASB":
            case "CASLB":
            case "CASAB":
            case "CASALB":
               this.E.pC(var1, var2, 8);
               break;
            case "CASH":
            case "CASLH":
            case "CASAH":
            case "CASALH":
               this.E.pC(var1, var2, 16);
               break;
            case "CAS":
            case "CASL":
            case "CASA":
            case "CASAL":
               this.E.pC(var1, var2, var1.pC(0).getOperandBitsize());
               break;
            case "CASP":
            case "CASPL":
            case "CASPA":
            case "CASPAL":
               this.E.pC(var1, var2);
               break;
            case "SEL":
               this.A.Pe(var1, var2, var3);
               break;
            case "ABS":
               var2.add(this.pC(var1, var3, false));
               break;
            case "CNT":
               this.kS.WR(var1, var2);
               break;
            case "CTZ":
            case "SMAX":
            case "SMIN":
            case "UMAX":
            case "UMIN":
            case "SWPP":
            case "SWPPA":
            case "SWPPAL":
            case "SWPPL":
               var2.add(this.pC(var1, var3, false));
               break;
            case "LD64B":
               IEUntranslatedInstruction var16 = this.pC(var1, var3, true);

               for (int var24 = 1; var24 < 8; var24++) {
                  IEGeneric var27 = this.pC(var1.A()[0].getOperandValue() + var24, true);
                  IEVar var29 = A(var27);
                  if (var29 != null) {
                     var16.addSideEffectDefinedVariable(var29);
                  }
               }

               var2.add(var16);
               break;
            case "ST64B":
               IEUntranslatedInstruction var15 = this.pC(var1, var3, true);

               for (int var23 = 1; var23 < 8; var23++) {
                  IEGeneric var26 = this.pC(var1.A()[0].getOperandValue() + var23, true);
                  IEVar var28 = A(var26);
                  if (var28 != null) {
                     var15.addSideEffectUsedVariable(var28);
                  }
               }

               var2.add(var15);
               break;
            case "ST64BV":
            case "ST64BV0":
               IEUntranslatedInstruction var14 = this.pC(var1, var3, true);
               IEGeneric var9 = this.pC(var1.A()[0].getOperandValue(), true);
               IEVar var10 = A(var9);
               if (var10 != null) {
                  var14.addSideEffectDefinedVariable(var10);
               }

               for (int var11 = 1; var11 < 8; var11++) {
                  IEGeneric var12 = this.pC(var1.A()[1].getOperandValue() + var11, true);
                  IEVar var13 = A(var12);
                  if (var13 != null) {
                     var14.addSideEffectUsedVariable(var13);
                  }
               }

               var2.add(var14);
               break;
            case "ADDG":
            case "SUBG":
            case "SUBP":
            case "SUBPS":
            case "CMPP":
            case "LDG":
            case "LDGM":
            case "ST2G":
            case "STG":
            case "STGM":
            case "STGP":
            case "STZ2G":
            case "STZG":
            case "STZGM":
            case "GMI":
            case "IRG":
            case "UDF":
            case "CRC32CB":
            case "CRC32CH":
            case "CRC32CW":
            case "CRC32CX":
            case "CRC32B":
            case "CRC32H":
            case "CRC32W":
            case "CRC32X":
            case "CDP":
            case "CDP2":
            case "LDC":
            case "LDCL":
            case "LDC2":
            case "LDC2L":
            case "STC":
            case "STCL":
            case "STC2":
            case "STC2L":
            case "DMB":
            case "DSB":
            case "SSBB":
            case "PSSBB":
            case "CLREX":
            case "CPS":
            case "CPSID":
            case "CPSIE":
            case "DCPS1":
            case "DCPS2":
            case "DCPS3":
            case "ERET":
            case "ERETAA":
            case "ERETAB":
            case "HINT":
            case "HLT":
            case "HVC":
            case "ISB":
            case "SETEND":
            case "SETPAN":
            case "SEV":
            case "SEVL":
            case "WFE":
            case "WFI":
            case "YIELD":
            case "ESB":
            case "CSDB":
            case "SB":
            case "TSB":
            case "PSB":
            case "CFP":
            case "CPP":
            case "DVP":
            case "WFET":
            case "WFIT":
            case "BRK":
            case "CHKFEAT":
            case "CLRBHB":
            case "BKPT":
            case "BXJ":
            case "DBG":
            case "RFE":
            case "RFEDA":
            case "RFEDB":
            case "RFEIA":
            case "RFEIB":
            case "SMC":
            case "SRSDA":
            case "SRSDB":
            case "SRSIA":
            case "SRSIB":
               var2.add(this.pC(var1, var3, true));
               break;
            case "AT":
            case "DC":
            case "IC":
            case "BRB":
            case "COSP":
            case "GCSB":
            case "GCSPUSHX":
            case "GCSPOPCX":
            case "GCSPOPM":
            case "GCSPOPX":
            case "GCSPUSHM":
            case "GCSSS1":
            case "GCSSS2":
            case "GCSSTR":
            case "GCSSTTR":
            case "TRCIT":
            case "SYS":
            case "SYSL":
            case "TLBI":
            case "SYSP":
            case "TLBIP":
               var2.add(this.pC(var1, var3, true));
               break;
            case "AXFLAG":
               this.kS.pC(var2);
               break;
            case "XAFLAG":
               this.kS.A(var2);
               break;
            case "CFINV":
               this.RW.pC(this, var2, null, new m(null, null, null, EUtil.notB(this.RW.kS), null));
               break;
            case "DRPS":
            case "DGH":
            case "SETF8":
            case "SETF16":
            case "RMIF":
            case "RPRFM":
            case "TCOMMIT":
            case "TSTART":
            case "TTEST":
            case "TCANCEL":
            case "MRRS":
            case "MSRR":
               var2.add(this.pC(var1, var3, true));
               break;
            default:
               mN var8 = MX.pC(var1);
               if (var8 != null) {
                  if ((var5.equals("LDRAA") || var5.equals("LDRAB")) && !this.E()) {
                     var2.add(this.pC(var1, var3, true));
                  } else if (var8.ys()) {
                     this.UT.pC(var1, var8, var2, var3);
                  } else {
                     this.UT.A(var1, var8, var2, var3);
                  }
               } else if (Strings.startsWith(var5, "CPY", "SETP", "SETG", "SETM", "SETE")) {
                  var2.add(this.pC(var1, var3, true));
               } else {
                  if (!Strings.startsWith(var5, "RCW", "LDIAPP", "STILP")) {
                     this.Bf++;
                     if (this.Bc != null) {
                        this.Bc.inc(var5);
                     }

                     throw new UnsupportedConversionException("Cannot convert instruction: " + var1);
                  }

                  var2.add(this.pC(var1, var3, true));
               }
         }
      }
   }

   @Override
   protected void convertBlock(BasicBlock var1, List var2) {
      long var3 = var1.getFirstAddress();
      long var5 = var3;
      ArrayList var7 = new ArrayList();
      int var8 = 0;
      oP.Av var9 = null;
      eW var10 = null;

      for (int var11 = var2.size(); var8 < var1.size() || var10 != null; var8++) {
         com.pnfsoftware.jeb.corei.parsers.arm.rQ var12;
         if (var8 >= var1.size()) {
            var12 = (com.pnfsoftware.jeb.corei.parsers.arm.rQ)this.ctx.getRoutine().getData().getCFG().getInstruction(var5);
            if (var12 == null) {
               this.Bf++;
               if (this.Bc != null) {
                  this.Bc.inc("ITBlock");
               }

               throw new UnsupportedConversionException(Strings.ff("Can not retrieve the end of IT Block at address %x", var5));
            }

            this.OI++;
         } else {
            this.OI++;
            var12 = (com.pnfsoftware.jeb.corei.parsers.arm.rQ)var1.get(var8);
         }

         var7.clear();
         if (var12.fI()) {
            if (var10 != null) {
               throw new IllegalConversionException("Illegal IT Block within an IT Block");
            }

            var10 = new eW(this, var12, var5);
         } else if (this.go.containsKey(var5)) {
            List var13 = (List)this.go.get(var5);
            if (var13 == null) {
               var7.add(this.ctx.createNop());
            } else {
               var7.addAll(var13);
            }
         } else {
            this.A(var12, var7, var5);
         }

         if (!var12.fI()) {
            IEGeneric var16 = var9 != null ? var9.pC(var1, var8, var2, var11, var10 == null) : null;
            var9 = this.RW.pC(this, var12, var7, var5, var8, var9);
            if (var10 == null && !var7.isEmpty() && var12.UT().sY() && !var12.UT().wS() && !var12.wS().A() && !var12.pC().isPCUpdated()) {
               var10 = eW.pC(this, var1, var8, var5, 10, var16);
            }

            if (var10 != null) {
               var10.pC(var5, var7, var12);
               if (var10.pC()) {
                  var10.pC(this.ctx, var2, var5 + var12.getSize());
                  var10 = null;
               }
            } else {
               this.pC(var7, var1, var8, var5);
               if (!var7.isEmpty() && var12.UT().sY()) {
                  IEGeneric var14 = this.RW.pC(var12.UT().A(), var16);
                  ArrayList var15 = new ArrayList();
                  this.RW.pC(var2.size(), var15, var14, var7, var5 + var12.getSize(), true, this.getProgramCounter());
                  var7 = var15;
               }

               EUtil.setLowerLevelAddress(var5, var7);
               var2.addAll(var7);
            }
         }

         var5 += var12.getSize();
      }
   }

   private void pC(List var1, BasicBlock var2, int var3, long var4) {
      com.pnfsoftware.jeb.corei.parsers.arm.rQ var6 = (com.pnfsoftware.jeb.corei.parsers.arm.rQ)var2.get(var3);
      com.pnfsoftware.jeb.corei.parsers.arm.rQ var7 = var3 + 1 < var2.size() ? (com.pnfsoftware.jeb.corei.parsers.arm.rQ)var2.get(var3 + 1) : null;
      Long var8 = this.pC(var1, var6, var7, var4);
      if (var8 != null) {
         var1.add(this.ctx.createBranchAssign(this.getProgramCounter(), this.ctx.createImm(var8, this.fI), false));
      }
   }

   Long pC(List var1, com.pnfsoftware.jeb.corei.parsers.arm.rQ var2, com.pnfsoftware.jeb.corei.parsers.arm.rQ var3, long var4) {
      if (this.pC(var2)) {
         long var6 = var4 + var2.getSize();
         if (var3 == null) {
            var3 = (com.pnfsoftware.jeb.corei.parsers.arm.rQ)this.ctx.getRoutine().getData().getCFG().getInstruction(var6);
            if (var3 == null) {
               return null;
            }
         }

         if (!var3.pC().isPCUpdated() || var3.pC().getBranchType() == IInsnEmulator.BranchType.CALL) {
            return null;
         }

         if (var2.UT().equals(var3.UT())) {
            ArrayList var8 = new ArrayList();
            this.A(var3, var8, var6);
            if (var8.size() > 0 && var8.get(var8.size() - 1) instanceof IEAssign) {
               IEAssign var9 = (IEAssign)var8.get(var8.size() - 1);
               if (!var9.isBreakingFlow()) {
                  throw new IllegalConversionException("An Arm Instruction has been wrongly translated to IR: " + var3);
               }

               var8.set(var8.size() - 1, this.ctx.createBranchAssign(var9.getLeftOperand(), var9.getRightOperand(), true));
               var1.addAll(var8);
               return var6 + var3.getSize();
            }
         }
      }

      return null;
   }

   IEUntranslatedInstruction pC(com.pnfsoftware.jeb.corei.parsers.arm.rQ var1, long var2, boolean var4, IEGeneric... var5) {
      IEUntranslatedInstruction var6 = this.pC(var1, var2, var4);

      for (IEGeneric var10 : var5) {
         if (var10 instanceof IEVar) {
            var6.addSideEffectDefinedVariable((IEVar)var10);
         }
      }

      return var6;
   }

   IEUntranslatedInstruction A(com.pnfsoftware.jeb.corei.parsers.arm.rQ var1, long var2, boolean var4, IEGeneric... var5) {
      IEUntranslatedInstruction var6 = this.pC(var1, var2, var4);

      for (IEGeneric var10 : var5) {
         if (var10 instanceof IEVar) {
            var6.addSideEffectUsedVariable((IEVar)var10);
         }
      }

      return var6;
   }

   private boolean pC(com.pnfsoftware.jeb.corei.parsers.arm.rQ var1) {
      if (!"MOV".equals(var1.wS().pC())) {
         return false;
      } else {
         Yg var2 = var1.A()[0];
         Yg var3 = var1.A()[1];
         return var2.kS(this.regNormalBitsize) && var3.A(this.regNormalBitsize);
      }
   }

   @Override
   public IEBranchDetails getDefaultBranchToRoutineSideEffects(INativeMethodItem var1) {
      ArrayList var2 = Lists.createArrayList();
      ArrayList var3 = Lists.createArrayList();
      ArrayList var4 = Lists.createArrayList();
      if (this.proc.getMode() == 64) {
         Lists.addNonNulls(var2, this.Cu, this.hZ, this.UW, this.PR, this.cX, this.DQ, this.ZN, this.OB, this.pF, this.gy);
         Lists.addNonNulls(var3, this.Cu);
         Lists.addNonNulls(var4, this.hZ, this.UW, this.PR, this.cX, this.DQ, this.ZN, this.OB, this.pF);
      } else {
         Lists.addNonNulls(var2, this.WR, this.NS, this.vP, this.xC, this.pg);
         Lists.addNonNulls(var3, this.WR);
         Lists.addNonNulls(var4, this.NS, this.vP, this.xC);
      }

      return this.gCtx.createBranchDetails(var3, var2, var4, 0);
   }

   public IEGeneric pC(com.pnfsoftware.jeb.corei.parsers.arm.rQ var1, long var2, int var4) {
      int var5 = cv.pC(var1.getProcessorMode());
      if (var2 == -1L) {
         throw new IllegalArgumentException("Address unknown for PC");
      } else {
         return this.pC(var2 + var5 + var4);
      }
   }

   public IEGeneric pC(com.pnfsoftware.jeb.corei.parsers.arm.rQ var1, long var2, IEGeneric var4) {
      int var5 = cv.pC(var1.getProcessorMode());
      if (var2 == -1L) {
         throw new IllegalArgumentException("Address unknown for PC");
      } else {
         return EUtil.add(this.pC(var2 + var5), var4);
      }
   }

   IEUntranslatedInstruction pC(com.pnfsoftware.jeb.corei.parsers.arm.rQ var1, long var2, boolean var4) {
      String var5 = var1.getMnemonic();
      if (!var5.startsWith("SVC")) {
         this.Pe++;
         if (this.ck != null) {
            this.ck.inc(var5);
         }
      }

      IEUntranslatedInstruction var6;
      if (var4) {
         var6 = this.ctx.createUntranslatedInstruction(var2, var5, this.A(var1, var2, false));
         Yg[] var7 = var1.WR();

         for (Yg var11 : var7) {
            int var12 = RegisterUtil.getRegisterGroup(var11.getOperandValue());
            if (var12 == 11 && RegisterUtil.getRegisterIndex(var11.getOperandValue()) == RegisterBankArm.APSR_nzcv) {
               var6.addSideEffectDefinedVariable(this.RW.pC);
               var6.addSideEffectDefinedVariable(this.RW.A);
               var6.addSideEffectDefinedVariable(this.RW.kS);
               var6.addSideEffectDefinedVariable(this.RW.wS);
            } else {
               IEVar var13 = A(this.getRegisterVariableFromNativeRegisterId(var11.getOperandValue()));
               if (var13 != null) {
                  var6.addSideEffectDefinedVariable(var13);
               }
            }
         }
      } else {
         var6 = this.ctx.createUntranslatedInstruction(var2, var5);
      }

      var6.setBreakingFlow(var1.getBreakingFlow(var2));
      var6.setRoutineCall(var1.getRoutineCall(var2));
      return var6;
   }

   IEGeneric[] A(com.pnfsoftware.jeb.corei.parsers.arm.rQ var1, long var2, boolean var4) {
      if (var1.A() == null) {
         return null;
      } else {
         List var5 = this.pC(var1, var1.A(), var2, var4);
         return (IEGeneric[])var5.toArray(new IEGeneric[var5.size()]);
      }
   }

   List pC(com.pnfsoftware.jeb.corei.parsers.arm.rQ var1, IInstructionOperand[] var2, long var3, boolean var5) {
      ArrayList var6 = new ArrayList();

      for (int var7 = 0; var7 < var2.length; var7++) {
         Yg var8 = (Yg)var2[var7];

         try {
            if (var8.getOperandType() == 7) {
               if (var8 instanceof rw && ((rw)var8).kS() == null) {
                  var6.addAll(this.pC(var1, ((FH)var8).pC(), var3, var5));
               } else if (var8 instanceof rw) {
                  var6.add(this.wS(var1, var8, var3, 0, false).A());
               } else {
                  var6.addAll(this.pC(var1, ((FH)var8).pC(), var3, var5));
               }
            } else {
               IEGeneric var9 = this.pC(var1, var8, 0, var3);
               if (var9 != null) {
                  var6.add(var9);
               }
            }
         } catch (InstructionConversionException var10) {
            if (var5) {
               throw var10;
            }

            e.catchingSilent(var10);
         } catch (RuntimeException var11) {
            if (var5) {
               throw var11;
            }

            e.catchingSilent(var11);
         }
      }

      return var6;
   }

   public pY pC(com.pnfsoftware.jeb.corei.parsers.arm.rQ var1, Yg var2) {
      switch (var2.getOperandType()) {
         case 0:
            return this.pC(var1, var2, -1L, 0, true);
         default:
            if (var2 instanceof rw) {
               return this.pC(var1, var2, -1L, 0, true);
            } else {
               throw new IllegalConversionException("Destination register must be a register in " + (var1 == null ? "" : var1.getMnemonic()));
            }
      }
   }

   public IEGeneric pC(com.pnfsoftware.jeb.corei.parsers.arm.rQ var1, IInstructionOperand var2, int var3, long var4) {
      pY var6 = this.pC(var1, (Yg)var2, var4, var3, false);
      return var6 == null ? null : var6.A();
   }

   public IEGeneric pC(com.pnfsoftware.jeb.corei.parsers.arm.rQ var1, IInstructionOperand var2, long var3) {
      pY var5 = this.pC(var1, (Yg)var2, var3, 0, false);
      return var5 == null ? null : var5.A();
   }

   public IEGeneric pC(com.pnfsoftware.jeb.corei.parsers.arm.rQ var1, IInstructionOperand var2) {
      return this.pC(var1, var2, 0);
   }

   public IEGeneric pC(com.pnfsoftware.jeb.corei.parsers.arm.rQ var1, IInstructionOperand var2, int var3) {
      if (var1.getProcessorMode() != 64) {
         throw new IllegalConversionException("Attempt to convert 32-bit operand in 64-bit operation " + var1.getMnemonic());
      } else {
         return this.pC(var1, (Yg)var2, -1L, var3, false).A();
      }
   }

   public IEMem pC(com.pnfsoftware.jeb.corei.parsers.arm.rQ var1, Yg var2, long var3, int var5) {
      IEGeneric var6 = this.pC(var1, (IInstructionOperand)((KH)var2).A(), var3);
      return this.ctx.createMem(var6, var5);
   }

   public int pC(com.pnfsoftware.jeb.corei.parsers.arm.rQ var1, Yg var2, long var3) {
      return (int)this.A(var1, var2, var3);
   }

   public long A(com.pnfsoftware.jeb.corei.parsers.arm.rQ var1, Yg var2, long var3) {
      switch (var2.getOperandType()) {
         case 1:
         case 2:
         case 3:
            return var2.pC(var3, var1.getProcessorMode(), null);
         default:
            throw new IllegalConversionException("NaN parameter");
      }
   }

   private pY pC(com.pnfsoftware.jeb.corei.parsers.arm.rQ var1, Yg var2, long var3, int var5, boolean var6) {
      pY var7 = this.A(var1, var2, var3, var5, var6);
      return var2.wS() ? new pY(this.pC(var7.pC()), var7.kS(), var7.wS()).pC(var7.UT()) : var7;
   }

   private pY A(com.pnfsoftware.jeb.corei.parsers.arm.rQ var1, Yg var2, long var3, int var5, boolean var6) {
      switch (var2.getOperandType()) {
         case 0:
            return this.kS(var1, var2, var3, var5, var6);
         case 1:
            return new pY(this.ctx.createImm(var2.getOperandValue(var3), var5 == 0 ? var2.getOperandBitsize() : var5));
         case 2:
         case 3:
            return new pY(this.pC(var2.getOperandValue(var3)));
         case 7:
            return this.wS(var1, var2, var3, var5, var6);
         case 4097:
            double var7 = ((Wl)var2).pC();
            if (var2.getOperandBitsize() == 32) {
               return new pY(this.ctx.createImm((float)var7));
            }

            return new pY(this.ctx.createImm(var7));
         default:
            return null;
      }
   }

   private pY kS(com.pnfsoftware.jeb.corei.parsers.arm.rQ var1, Yg var2, long var3, int var5, boolean var6) {
      if (!var6 && var2.A(this.regNormalBitsize)) {
         return new pY(this.pC(var1, var3, 0)).pC(var5);
      } else {
         IEGeneric var7 = this.pC(var2.getOperandValue(), var6);
         if (!var6 && var2 instanceof lw) {
            int var8 = u.pC((lw)var2);
            if (var8 <= 0) {
               throw new UnsupportedConversionException("Illegal size for SIMD Register " + var1);
            }

            var7 = var7.part(var8);
         }

         return new pY(var7).pC(var5);
      }
   }

   private pY wS(com.pnfsoftware.jeb.corei.parsers.arm.rQ var1, Yg var2, long var3, int var5, boolean var6) {
      if (var2.E().length == 1) {
         if (var2 instanceof KH) {
            throw new UnsupportedConversionException("Can not process operand list, type not managed: " + var2.getClass());
         } else {
            return this.A(var1, var2.E()[0], var3, var5, var6);
         }
      } else {
         if (!(var2 instanceof ZV) && !(var2 instanceof Bc)) {
            if (var2 instanceof rw) {
               int var8;
               IEGeneric var10;
               if (var2.E()[0] instanceof lw) {
                  lw var9 = (lw)var2.E()[0];
                  var10 = this.pC(var9.getOperandValue(), var6);
                  var8 = u.pC(var9);
               } else {
                  if (var2.E()[0] instanceof Uw) {
                     throw new UnsupportedConversionException("Register List must be converted by caller " + var1);
                  }

                  pY var11 = this.A(var1, var2.E()[0], var3, var5, var6);
                  if (!var11.E()) {
                     throw new UnsupportedConversionException("Double indexing not managed: " + var2.getClass());
                  }

                  var10 = var11.pC();
                  if (var1.getProcessorMode() == 64) {
                     throw new UnsupportedConversionException("Can not process operand list, type not managed: " + var2.getClass());
                  }

                  var8 = Gq.pC(var1.wS());
               }

               Integer var12 = ((rw)var2).kS();
               if (var12 == null) {
                  throw new UnsupportedConversionException("Can not process operand list, type not managed: " + var2.getClass());
               }

               if (var8 == -1) {
                  throw new UnsupportedConversionException("Can not process operand list, unknown size: " + var1);
               }

               return new pY(var10, var8 * var12, var8 * (var12 + 1));
            }

            if (var2 instanceof hy) {
               throw new UnsupportedConversionException("Can not process aligned register, must be managed by caller.");
            }
         } else {
            nA var7 = this.pC(var1, var2, var3, var5, this.A());
            if (var7 != null) {
               return new pY(var7.pC());
            }
         }

         throw new UnsupportedConversionException("Can not process operand list, type not managed: " + var2.getClass());
      }
   }

   nA pC(com.pnfsoftware.jeb.corei.parsers.arm.rQ var1, Yg var2, long var3, int var5, IEVar var6) {
      switch (var2.getOperandType()) {
         case 0:
         case 1:
         case 2:
         case 3:
         case 4:
         case 5:
         case 6:
         default:
            return new nA(this.pC(var1, var2, var5, var3), null);
         case 7:
            if (var2.E().length == 1) {
               return this.pC(var1, var2.E()[0], var3, var5, var6);
            } else if (var2 instanceof ZV) {
               Yg var12 = ((ZV)var2).A();
               IEGeneric var13 = this.pC(var1, var12, var5, var3);
               Z var14 = ((ZV)var2).kS();
               Yg var15 = var14.kS();
               IEGeneric var11 = null;
               if (var15 != null) {
                  var11 = this.pC(var1, (IInstructionOperand)var15, var3);
               }

               if (var14.A().pC()) {
                  return this.pC(var14.A(), var13, var11, var5, var6, var1.getProcessorMode());
               } else {
                  return var14.A().A() ? new nA(this.pC(var14.A(), var13, var15), null) : null;
               }
            } else if (var2 instanceof Bc) {
               Yg var7 = ((Bc)var2).A();
               IEGeneric var8 = this.pC(var1, var7, var5, var3);
               Yg var9 = ((Bc)var2).kS();
               IEGeneric var10 = this.pC(var1, (IInstructionOperand)var9, var3);
               return this.pC(Z.Av.wS, var8, var10, var5, this.A(), var1.getProcessorMode());
            } else {
               throw new UnsupportedConversionException("Can not process operand list, type not managed: " + var2.getClass());
            }
      }
   }

   nA pC(Z.Av var1, IEGeneric var2, IEGeneric var3, int var4, IEVar var5, int var6) {
      if (var3 != null) {
         if (var6 == 64) {
            var3 = var3.part(var2.getBitsize() == 64 ? 6 : 5);
         } else if (var1 == Z.Av.wS) {
            var3 = var3.part(8);
         }

         var3 = var3.zeroExtend(var2.getBitsize());
      }

      IEOperation var7;
      switch (var1) {
         case pC:
            var7 = this.ctx
               .createOperation(OperationType.SHL, this.kS(var2.getBitsize()), EUtil.sub(this.ctx.createImm(this.fI, var2.getBitsize()), var3.duplicate()));
            break;
         case A:
         case kS:
         case wS:
            var7 = this.ctx.createOperation(OperationType.SHL, this.kS(var2.getBitsize()), EUtil.sub(var3.duplicate(), this.kS(var2.getBitsize())));
            break;
         case UT:
            IECompose var8 = this.ctx.createCompose(var2.slice(1, var2.getBitsize()), var5);
            return new nA((IEGeneric)(var4 == 0 ? var8 : var8.zeroExtend(var4)), var2.bit(0), var5 == this.Er);
         default:
            return null;
      }

      Object var13 = this.ctx.createOperation(this.pC(var1), var2, var3);
      IEOperation var9 = this.ctx.createOperation(OperationType.AND, var2, var7);
      IECond var10 = this.ctx.createCond(var3, this.ctx.createCond(var9, this.Aj, this.FE), this.A());
      if (var6 != 64 && var1 != Z.Av.wS && (!(var3 instanceof IEImm) || ((IEImm)var3).getValueAsLong() > 32L)) {
         IEImm var11 = this.ctx.createImm(this.fI, var3.getBitsize());
         var10 = this.ctx.createCond(EUtil.gtU(var3, var11), this.FE, var10);
         IEOperation var12 = EUtil.geU(var3, var11);
         if (var1 != Z.Av.pC && var1 != Z.Av.A) {
            var13 = this.ctx
               .createCond(
                  var12,
                  this.ctx
                     .createCond(
                        EUtil.eq(var2.msb(), this.wS(1)), this.wS(((IEGeneric)var13).getBitsize()), this.ctx.createImm(-1L, ((IEGeneric)var13).getBitsize())
                     ),
                  (IEGeneric)var13
               );
         } else {
            var13 = this.ctx.createCond(var12, this.wS(((IEGeneric)var13).getBitsize()), (IEGeneric)var13);
         }
      }

      return new nA((IEGeneric)(var4 == 0 ? var13 : ((IEGeneric)var13).zeroExtend(var4)), var10);
   }

   OperationType pC(Z.Av var1) {
      switch (var1) {
         case pC:
            return OperationType.SHL;
         case A:
            return OperationType.SHR;
         case kS:
            return OperationType.SAR;
         case wS:
            return OperationType.ROR;
         default:
            return null;
      }
   }

   private IEGeneric pC(Z.Av var1, IEGeneric var2, Yg var3) {
      boolean var4 = false;
      int var5 = 0;
      switch (var1) {
         case oT:
            var5 = 8;
            break;
         case fI:
            var5 = 16;
            break;
         case WR:
            var5 = 32;
            break;
         case NS:
            var5 = 64;
            break;
         case sY:
            var4 = true;
            var5 = 8;
            break;
         case ys:
            var4 = true;
            var5 = 16;
            break;
         case ld:
            var4 = true;
            var5 = 32;
            break;
         case gp:
            var4 = true;
            var5 = 64;
      }

      var5 = Math.min(var5, var2.getBitsize());
      if (var3 == null) {
         return EUtil.extend(var2.slice(0, var5), var2.getBitsize(), !var4);
      } else {
         int var6 = (int)var3.getOperandValue();
         IEOperation var7 = this.ctx.createConversionOperation(var4 ? OperationType.CAST : OperationType.CAST_S, var2.slice(0, var5), var2.getBitsize());
         return var6 == 0 ? var7 : this.ctx.createOperation(OperationType.MUL, var7, this.ctx.createImm(1 << var6, var7.getBitsize()));
      }
   }

   private IRegisterBank UT() {
      return RegisterUtil.getBank(this.regNormalBitsize == 64 ? ProcessorType.ARM64 : ProcessorType.ARM);
   }

   public IEGeneric pC(long var1, boolean var3) {
      int var4 = RegisterUtil.getRegisterGroup(var1);
      int var5 = RegisterUtil.getRegisterIndex(var1);
      RegisterDescriptionEntry var6 = this.UT().getDescriptionEntryById(var1);
      switch (var4) {
         case 0:
            if (this.regNormalBitsize == 64) {
               int var12 = RegisterUtil.getRegisterBitsize(var1);
               var12 = var12 == 0 ? 64 : var12;
               if (!var3 && var5 == 33) {
                  return this.ctx.createImm(0L, var12);
               }

               if (var12 == 64) {
                  return this.gCtx.createRegister(A(var5), var6.getName(), this.regNormalBitsize);
               }

               IEVar var15 = this.gCtx.createRegister(A(var5), var6.getName(), this.fI);
               return (IEGeneric)(var3 ? var15 : var15.part(var12));
            }

            return this.gCtx.createRegister(pC(var5), var6.getName(), this.regNormalBitsize);
         case 1:
         case 3:
         case 5:
         case 10:
         default:
            throw new UnsupportedConversionException("Register type not converted yet: " + var4);
         case 2:
            return this.gCtx.createRegister(9728 + var5 * 32, var6.getName(), 32);
         case 4:
            return this.gCtx.createRegister(9216 + var5 * 32, var6.getName(), 32);
         case 6:
            int var10 = RegisterUtil.getRegisterBitsize(var1);
            var10 = var10 == 0 ? 128 : var10;
            if (this.fI == 64) {
               IEVar var14 = this.gCtx.createRegister(5120 + var5 * 128, var6.getName(), 128);
               return (IEGeneric)(var3 ? var14 : var14.part(var10));
            }

            IEVar var8 = this.gCtx.createRegister(3072 + var5 * 128, var6.getName(), 128);
            int var9 = RegisterUtil.getRegisterBitstart(var1);
            return var8.slice(var9, var9 + var10);
         case 7:
         case 12:
            return this.gCtx.createRegister(5120 + var5 * 128, var6.getName(), 128);
         case 8:
            return this.gCtx.createRegister(11776 + var5 * 32, var6.getName(), 32);
         case 9:
            IEVar var7 = (IEVar)this.Ta.get(var5);
            if (var7 == null) {
               if (this.So + 64 + (32768 - this.Ta.size()) >= 65536) {
                  var7 = this.gCtx.createRegister(this.So, var6.getName(), 1);
                  this.So++;
               } else {
                  var7 = this.gCtx.createRegister(this.So, var6.getName(), 64);
                  this.So += 64;
               }

               this.Ta.put(var5, var7);
            }

            return var7;
         case 11:
            if (var5 == RegisterBankArm.APSR_nzcv) {
               return this.ctx.createCompose(this.RW.wS, this.RW.kS, this.RW.A, this.RW.pC);
            }

            throw new UnsupportedConversionException("Register type [STATUS_REGISTER] not converted yet ");
         case 13:
            return this.gCtx.createRegister(12288 + var5 * 16, var6.getName(), 16);
      }
   }

   public OA pC(Yg[] var1, int var2, int var3) {
      if (var2 == 0) {
         return this.pC(var1[0]);
      } else if (var1.length == var3) {
         return this.pC(var1[var2]);
      } else if (var1.length + 1 == var3) {
         return this.pC(var1[var2 - 1]);
      } else {
         throw new IllegalArgumentException("Wrong index " + var2 + " for operands: " + Strings.joinv(",", var1));
      }
   }

   public OA pC(Yg var1) {
      switch (var1.getOperandType()) {
         case 0:
            IEGeneric var6 = this.pC(var1.getOperandValue(), false);
            if (var1 instanceof lw) {
               return new OA(var6, (lw)var1);
            }

            return new OA(var6);
         case 1:
            return new OA(this.ctx.createImm(var1.getOperandValue(), var1.getOperandBitsize()));
         case 7:
            if (var1 instanceof ZV var4) {
               if (var4.A().getOperandType() == 1 && var4.kS().kS().getOperandType() == 1) {
                  Long var3 = var4.pC(-1L, 64, null);
                  if (var3 != null) {
                     return new OA(this.ctx.createImm(var3, var1.getOperandBitsize()));
                  }
               }
            } else if (var1 instanceof rw) {
               pY var5 = this.wS(null, var1, -1L, 0, false);
               if (var5 != null) {
                  return new OA(var5);
               }
            }

            throw new IllegalArgumentException("Not a vector register");
         case 4097:
            double var2 = ((Wl)var1).pC();
            if (var1.getOperandBitsize() == 32) {
               return new OA(this.ctx.createImm((float)var2));
            }

            return new OA(this.ctx.createImm(var2));
         default:
            throw new IllegalArgumentException("Not a vector register");
      }
   }

   private static IEVar A(IEGeneric var0) {
      if (var0 instanceof IEVar) {
         return (IEVar)var0;
      } else {
         return var0 instanceof IESlice && ((IESlice)var0).getWholeExpression() instanceof IEVar ? (IEVar)((IESlice)var0).getWholeExpression() : null;
      }
   }

   IEImm pC(long var1) {
      return this.ctx.createImm(var1, this.fI);
   }

   IEOperation pC(IEGeneric var1) {
      return this.ctx.createOperation(OperationType.SUB, this.wS(var1.getBitsize()), var1);
   }

   @Override
   public IEGeneric normalizeBranchingExpression(IDFA var1, BasicBlock var2, IEGeneric var3, IEGeneric var4) {
      if (!(var3 instanceof yP)) {
         return super.normalizeBranchingExpression(var1, var2, var3, var4);
      } else {
         ala var16 = ((yP)var3).kS();
         Node var5 = Util.N(O.COMPOSE_2, Util.LC(0L, 2), Util.N(O.SLICE, Util.L(0), Util.LC(2L), Util.LC(32L)));
         Node var6 = Util.N(O.COMPOSE_2, Util.LC(0L, 1), Util.N(O.SLICE, Util.L(0), Util.LC(1L), Util.LC(32L)));
         Node var7 = Util.N(
            O.COND,
            Util.N(O.SLICE_FIRSTBIT, Util.L(0)),
            Util.N(O.COMPOSE_2, Util.LC(0L, 1), Util.N(O.SLICE, Util.L(0), Util.LC(1L), Util.LC(32L))),
            Util.N(O.COMPOSE_2, Util.LC(0L, 2), Util.N(O.SLICE, Util.L(0), Util.LC(2L), Util.LC(32L)))
         );
         Node[] var8 = new Node[]{var5, var6, var7};
         HashMap var9 = new HashMap();

         for (Node var13 : var8) {
            EExpressionMatcher var14 = new EExpressionMatcher(var13, var9);
            if (var14.isMatch(var16)) {
               IEGeneric var15 = (IEGeneric)var14.getMatchMap().get(0);
               if (var4 == null || this.pC(var2, var15, var4, var1) || super.isPCRightValueCompatibleReturnValue(var1, var2, var15, var4)) {
                  return var15;
               }
            }
         }

         return null;
      }
   }

   private boolean pC(BasicBlock var1, IEGeneric var2, IEGeneric var3, IDFA var4) {
      if (var2.equals(var3)) {
         return true;
      } else if (!(var2 instanceof IEVar)) {
         return false;
      } else {
         int var5 = ((IEVar)var2).getId();
         ArrayList var6 = new ArrayList();
         var6.add(var1);
         ArrayList var7 = new ArrayList();
         ArrayList var8 = new ArrayList();

         while (!var6.isEmpty()) {
            BasicBlock var9 = (BasicBlock)var6.remove(0);
            int var10 = this.pC(var9, var5, var3, var4);
            switch (var10) {
               case -1:
                  return false;
               case 0:
               default:
                  var7.add(var9);

                  for (BasicBlock var12 : var9.getInputs()) {
                     if (!var7.contains(var12) && !var6.contains(var12)) {
                        var6.add(var12);
                     }
                  }
                  break;
               case 1:
                  var8.add(var9);
                  var7.add(var9);
            }
         }

         return !var8.isEmpty();
      }
   }

   private int pC(BasicBlock var1, int var2, IEGeneric var3, IDFA var4) {
      for (int var5 = var1.size() - 1; var5 >= 0; var5--) {
         Collection var6 = var4.getDefUses(var1.getAddressOfInstruction(var5), var2);
         if (var6 != null && !var6.isEmpty()) {
            IEStatement var7 = (IEStatement)var1.get(var5);
            if (!(var7 instanceof IEAssign)) {
               return -1;
            }

            return ((IEAssign)var7).getRightOperand().equals(var3) ? 1 : -1;
         }
      }

      return 0;
   }

   void pC(com.pnfsoftware.jeb.corei.parsers.arm.rQ var1, List var2, IEGeneric var3, IEGeneric var4) {
      this.pC(var1, var2, var3, var4, false);
   }

   void pC(com.pnfsoftware.jeb.corei.parsers.arm.rQ var1, List var2, pY var3, IEGeneric var4) {
      this.pC(var1, var2, var3, var4, false);
   }

   void pC(com.pnfsoftware.jeb.corei.parsers.arm.rQ var1, List var2, pY var3, IEGeneric var4, boolean var5) {
      if (var3.E()) {
         this.pC(var1, var2, var3.pC(), var4, var5);
      } else {
         ArrayList var6 = new ArrayList();
         if (var3.kS() != 0) {
            var6.add(var3.pC().part(var3.kS()));
         }

         var6.add(var4);
         if (var3.wS() < var3.pC().getBitsize()) {
            var6.add(var3.pC().slice(var3.wS(), var3.pC().getBitsize()));
         }

         IEGeneric var7 = EUtil.compose(this.ctx, var6);
         this.pC(var1, var2, var3.pC(), var7, var5);
      }
   }

   void pC(com.pnfsoftware.jeb.corei.parsers.arm.rQ var1, List var2, IEGeneric var3, IEGeneric var4, boolean var5) {
      if (var1.getProcessorMode() != 64 && var3 instanceof IEVar && ((IEVar)var3).getId() == this.getProgramCounter().getId()) {
         if (!var5) {
            e.warn(S.L("PC shall not be updated: instruction '%s' is unpredictable"), var1);
         }

         this.pC(var1, var4, var2);
      } else if (var1.getProcessorMode() != 64 || !var3.isImm()) {
         var2.add(this.ctx.createAssign(var3, EUtil.safeExtend(var4, var3.getBitsize(), false)));
      }
   }

   void pC(com.pnfsoftware.jeb.corei.parsers.arm.rQ var1, IEGeneric var2, List var3) {
      if (var1.getProcessorMode() == 64) {
         throw new IllegalConversionException("Can not write directly to PC in AArch64 mode");
      } else {
         if (var1.getProcessorMode() == 32) {
            this.wS.pC(var3, var2);
         } else {
            var3.add(this.wS.pC(var1, var3, -1L, var2));
         }
      }
   }

   @Override
   public int getStateProcessorMode(EState var1) {
      if (var1 == null) {
         return this.proc.getMode();
      } else {
         return this.proc.getMode() == 64 ? 64 : (var1.getValueAsLong(517) == 0L ? 32 : 16);
      }
   }

   public static int pC(int var0) {
      return 0 + var0 * 32;
   }

   public static int A(int var0) {
      return 768 + var0 * 64;
   }

   IEImm kS(int var1) {
      return EUtil.one(var1);
   }

   IEImm wS(int var1) {
      return EUtil.zero(var1);
   }

   IEImm UT(int var1) {
      return EUtil.minusOne(var1);
   }

   IEVar E(int var1) {
      switch (var1) {
         case 1:
            return this.gj;
         case 8:
            return this.VD;
         case 16:
            return this.Xs;
         case 32:
            return this.ZD;
         case 64:
            return this.DL;
         case 128:
            return this.KV;
         default:
            throw new IllegalConversionException("no tmp variable available with size: " + var1);
      }
   }

   IEVar sY(int var1) {
      switch (var1) {
         case 32:
            return this.UH;
         case 64:
            return this.FK;
         default:
            throw new IllegalConversionException("no tmp variable available with size: " + var1);
      }
   }

   IEVar ys(int var1) {
      switch (var1) {
         case 1:
            return this.FM;
         case 8:
            return this.Ip;
         case 16:
            return this.Fm;
         case 32:
            return this.wQ;
         case 64:
            return this.PZ;
         case 128:
            return this.KV;
         default:
            throw new IllegalConversionException("no tmp variable available with size: " + var1);
      }
   }

   @Override
   public String formatStatistics() {
      StringBuilder var1 = new StringBuilder(super.formatStatistics());
      var1.append("\n\n");
      Strings.ff(var1, "Converted instructions: %d (incl. failed=%d, untranslated=%d)\n", this.OI, this.Bf, this.Pe);
      if (this.Bc != null && !this.Bc.isEmpty()) {
         var1.append("Failed instructions: ").append(this.Bc.formatTopReferences(-1));
      }

      if (this.ck != null && !this.ck.isEmpty()) {
         var1.append("Untranslated instructions: ").append(this.ck.formatTopReferences(-1));
      }

      return var1.toString();
   }

   @Override
   protected void preRoutineConversion(INativeMethodItem var1, IERoutineContext var2, List var3) {
      super.preRoutineConversion(var1, var2, var3);
      this.Kq = new long[]{var1.getData().getCFG().getFirstAddress(), var1.getData().getCFG().getEndAddress()};
      if (this.proc.getMode() == 64) {
         CFG var4 = var1.getData().getCFG();
         BasicBlock var5 = var4.getEntryBlock();
         List var6 = var4.getExitBlocks();
         com.pnfsoftware.jeb.corei.parsers.arm.rQ var7 = (com.pnfsoftware.jeb.corei.parsers.arm.rQ)var5.get(0);
         if (com.pnfsoftware.jeb.corei.parsers.arm.converter.Av.pC(var7)) {
            ArrayList var8 = new ArrayList();
            byte var9 = 0;

            for (BasicBlock var11 : var6) {
               if (var11.size() > 1 && PU.ys((com.pnfsoftware.jeb.corei.parsers.arm.rQ)var11.getLast())) {
                  if (!com.pnfsoftware.jeb.corei.parsers.arm.converter.Av.pC((com.pnfsoftware.jeb.corei.parsers.arm.rQ)var11.get(var11.size() - 2), var7)) {
                     break;
                  }

                  var8.add(var11.getLastAddress() - 4L);
               }
            }

            if (var8.size() + var9 == var6.size()) {
               this.go.put(var5.getFirstAddress(), Lists.createArrayList(var2.createNop()));

               for (Long var13 : var8) {
                  this.go.put(var13, Lists.createArrayList(var2.createNop()));
               }
            }
         }
      }
   }

   @Override
   protected void postRoutineConversion(INativeMethodItem var1, IERoutineContext var2) {
      super.postRoutineConversion(var1, var2);
      this.go = new HashMap();
   }

   @Override
   public int insertReturns(IERoutineContext var1) {
      int var2 = 0;
      CFG var3 = var1.getCfg();

      for (BasicBlock var5 : var3.getBlocks()) {
         AddressableInstruction var6 = var5.getLast2();
         boolean var8 = false;
         IECall var7;
         if (var6.getInstruction() instanceof IECall) {
            var7 = (IECall)var6.getInstruction();
         } else {
            if (!(var6.getInstruction() instanceof IEAssign) || !(((IEAssign)var6.getInstruction()).getRightOperand() instanceof IECall)) {
               continue;
            }

            var7 = (IECall)((IEAssign)var6.getInstruction()).getRightOperand();
            var8 = true;
         }

         long var9 = ((IEStatement)var6.getInstruction()).getPrimaryLowerLevelAddress();
         com.pnfsoftware.jeb.corei.parsers.arm.rQ var11 = (com.pnfsoftware.jeb.corei.parsers.arm.rQ)var1.getRoutine().getData().getCFG().getInstruction(var9);
         if (var11.wS().pC().equals("B")) {
            if (var5.outsize() != 1) {
               e.catchingSilent(new JebRuntimeException("Could not retrieve out block @0x" + Long.toHexString(var9)));
            } else {
               BasicBlock var12 = var5.getOutputBlock(0);
               if (var12.insize() != 1) {
                  e.catchingSilent(new JebRuntimeException("Multiple entry block @0x" + Long.toHexString(var9)));
               } else if (var12.size() != 1) {
                  e.catchingSilent(new JebRuntimeException("Multiple instructions @0x" + Long.toHexString(var9)));
               } else {
                  INativeType var13 = var1.getRoutine().getReturnType();
                  boolean var14 = var13 == null || var13.getSize() == 0;
                  if (!var14 && !var8 && var7.getPrototype().getReturnType().isVoid()) {
                     Object[] var10000 = new Object[]{var7};
                     IWildcardTypeManager var15 = var1.getWildcardTypeManager();
                     IWildcardType var16 = var15.createWithSlotcount(1);
                     IWildcardPrototype var17 = var7.getPrototype();
                     IWildcardPrototype var18 = var15.createPrototype(
                        var17.getCallingConvention(), var16, var17.getParameterTypes(), var17.getPrototypeAttributes()
                     );
                     IECall var19 = var1.createCall(var7.getCallSite(), null, var18, null, false);
                     IEVar var20 = this.proc.getMode() == 64 ? this.Cu : this.WR;
                     IEVar var21 = var1.copyVariable(var20);
                     var19.replaceVar(var20, var21, false);
                     var19.copyProperties(var7);
                     var5.set(var5.size() - 1, var19);
                     var2++;
                  }
               }
            }
         }
      }

      return var2 + super.insertReturns(var1);
   }

   @Override
   public boolean canCreateCalls() {
      return super.canCreateCalls();
   }

   @Override
   public void customInitStateRegisters(EState var1, Long var2) {
      super.customInitStateRegisters(var1, var2);
      if (this.fI == 64) {
         var1.setValue(this.RW.A, 1L);
      }
   }

   private boolean E() {
      return true;
   }

   @Ser
   public static enum Av {
      pC(16),
      A(17),
      kS(18),
      wS(19),
      UT(23),
      E(27);

      private final int sY;

      private Av(int var3) {
         this.sY = var3;
      }

      public int pC() {
         return this.sY;
      }
   }
}
