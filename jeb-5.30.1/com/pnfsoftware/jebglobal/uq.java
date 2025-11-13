package com.pnfsoftware.jebglobal;

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
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Ser
public class uq extends AbstractConverter {
   private static final ILogger AN = GlobalLog.getLogger(uq.class);
   private static final long RW = -1L;
   private static final int YR = 0;
   public static final int q = 0;
   public static final int RF = 32;
   public static final int xK = 0;
   public static final int Dw = 32;
   public static final int Uv = 64;
   public static final int oW = 96;
   public static final int gO = 128;
   public static final int nf = 160;
   public static final int gP = 192;
   public static final int za = 224;
   public static final int lm = 256;
   public static final int zz = 288;
   public static final int JY = 320;
   public static final int HF = 352;
   public static final int LK = 384;
   public static final int io = 416;
   public static final int qa = 448;
   public static final int Hk = 480;
   public static final int Me = 512;
   public static final int PV = 512;
   public static final int oQ = 543;
   public static final int xW = 542;
   public static final int KT = 541;
   public static final int Gf = 540;
   public static final int Ef = 539;
   public static final int cC = 536;
   public static final int sH = 528;
   public static final int CE = 520;
   public static final int wF = 519;
   public static final int If = 518;
   public static final int Dz = 517;
   public static final int mI = 512;
   public static final int jq = 544;
   public static final int ui = 572;
   public static final int TX = 553;
   public static final int Rr = 552;
   public static final int EB = 551;
   public static final int Xo = 550;
   public static final int Bu = 576;
   public static final int IN = 768;
   public static final int rL = 64;
   public static final int eJ = 768;
   public static final int YN = 832;
   public static final int Rv = 896;
   public static final int zx = 960;
   public static final int ZT = 1024;
   public static final int Ri = 1088;
   public static final int GY = 1152;
   public static final int Wx = 1216;
   public static final int AB = 1280;
   public static final int CY = 1344;
   public static final int WI = 1408;
   public static final int Tq = 1472;
   public static final int Yp = 1536;
   public static final int Gu = 1600;
   public static final int nY = 1664;
   public static final int lF = 1728;
   public static final int nq = 1792;
   public static final int NX = 1856;
   public static final int br = 1920;
   public static final int tW = 1984;
   public static final int ZA = 2048;
   public static final int Ov = 2112;
   public static final int Lj = 2176;
   public static final int nv = 2240;
   public static final int LL = 2304;
   public static final int PQ = 2368;
   public static final int fQ = 2432;
   public static final int fi = 2496;
   public static final int bl = 2560;
   public static final int jb = 2624;
   public static final int pQ = 2688;
   public static final int kf = 2752;
   public static final int GM = 2816;
   public static final int TQ = 2880;
   public static final int Yw = 2944;
   public static final int IY = 3072;
   public static final int qR = 128;
   public static final int YA = 3072;
   public static final int fw = 3200;
   public static final int Wp = 3328;
   public static final int cY = 3456;
   public static final int PY = 3584;
   public static final int cR = 3712;
   public static final int eC = 3840;
   public static final int ND = 3968;
   public static final int Qu = 4096;
   public static final int jh = 4224;
   public static final int Jf = 4352;
   public static final int vC = 4480;
   public static final int of = 4608;
   public static final int os = 4736;
   public static final int iu = 4864;
   public static final int fn = 4992;
   public static final int ZU = 5120;
   public static final int Sz = 5120;
   public static final int fq = 128;
   public static final int mJ = 5120;
   public static final int Bs = 5248;
   public static final int rV = 5376;
   public static final int WX = 5504;
   public static final int CB = 5632;
   public static final int C = 5760;
   public static final int GC = 5888;
   public static final int KF = 6016;
   public static final int rk = 6144;
   public static final int cy = 6272;
   public static final int jk = 6400;
   public static final int Cl = 6528;
   public static final int hM = 6656;
   public static final int kv = 6784;
   public static final int oS = 6912;
   public static final int FG = 7040;
   public static final int Al = 7168;
   public static final int Kn = 7296;
   public static final int vh = 7424;
   public static final int Rd = 7552;
   public static final int Eq = 7680;
   public static final int hP = 7808;
   public static final int wN = 7936;
   public static final int Uc = 8064;
   public static final int TB = 8192;
   public static final int dg = 8320;
   public static final int hw = 8448;
   public static final int gm = 8576;
   public static final int uY = 8704;
   public static final int sc = 8832;
   public static final int wQ = 8960;
   public static final int Oj = 9088;
   public static final int VW = 9216;
   public static final int ap = 9216;
   public static final int RL = 32;
   public static final int hy = 9216;
   public static final int Xi = 9248;
   public static final int Ag = 9280;
   public static final int rp = 9312;
   public static final int CW = 9344;
   public static final int qm = 9376;
   public static final int LR = 9408;
   public static final int Uz = 9440;
   public static final int dF = 9472;
   public static final int kk = 9504;
   public static final int Rc = 9536;
   public static final int jz = 9568;
   public static final int MT = 9600;
   public static final int bY = 9632;
   public static final int LS = 9664;
   public static final int fG = 9696;
   public static final int cO = 9728;
   public static final int wr = 9728;
   public static final int pe = 32;
   public static final int Gg = 9728;
   public static final int CK = 9760;
   public static final int PW = 11712;
   public static final int zm = 11744;
   public static final int Wn = 11776;
   public static final int eG = 32;
   public static final int Id = 11776;
   public static final int Dk = 12256;
   public static final int dS = 12288;
   public static final int cb = 16;
   public static final int BU = 12672;
   public static final int xG = 64;
   private static final Object[] fN = new Object[]{
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
   private static final Object[] GH = new Object[]{
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
   private static final int BY = 10;
   @SerTransient
   Lb wS;
   @SerTransient
   IP Oz;
   @SerTransient
   A yn;
   @SerTransient
   protected aa es;
   @SerTransient
   mV o;
   @SerTransient
   Un gl;
   @SerTransient
   ya tX;
   @SerTransient
   B Qt;
   @SerTransient
   ZS JW;
   @SerTransient
   fT Ub;
   @SerTransient
   mn tb;
   @SerTransient
   long[] yW;
   @SerId(5)
   int JF;
   @SerId(10)
   IEVar uz;
   @SerId(11)
   IEVar Xz;
   @SerId(12)
   IEVar iK;
   @SerId(13)
   IEVar ZE;
   @SerId(6)
   IEVar Jh;
   @SerId(7)
   IEVar iO;
   @SerId(8)
   IEVar Qe;
   @SerId(9)
   IEVar dW;
   @SerId(14)
   private IEVar fK;
   @SerId(15)
   private IEVar ou;
   @SerId(16)
   private IEVar DP;
   @SerId(18)
   IEVar HK;
   @SerId(19)
   IEVar uw;
   @SerId(25)
   protected IEVar fe;
   @SerId(26)
   IEVar Kl;
   @SerId(27)
   IEVar So;
   @SerId(29)
   IEVar AG;
   @SerId(30)
   private IEVar lA;
   @SerId(31)
   private IEVar yu;
   @SerId(32)
   private IEVar lz;
   @SerId(36)
   private IEVar Nu;
   @SerId(37)
   private IEVar YT;
   @SerId(38)
   private IEVar AY;
   @SerId(39)
   private IEVar Ld;
   @SerId(40)
   private IEVar XV;
   @SerId(35)
   IEVar er;
   @SerId(33)
   IEImm SM;
   @SerId(34)
   IEImm bj;
   @SerId(50)
   IEVar GO;
   @SerId(51)
   IEVar QZ;
   @SerId(52)
   IEVar Up;
   @SerId(53)
   IEVar HO;
   @SerId(54)
   IEVar cv;
   @SerId(60)
   IEVar lk;
   @SerId(61)
   IEVar sa;
   @SerId(62)
   IEVar WJ;
   @SerId(63)
   IEVar pL;
   @SerId(64)
   IEVar aH;
   @SerId(65)
   IEVar yc;
   @SerId(66)
   IEVar eb;
   @SerId(67)
   IEVar zj;
   @SerId(68)
   IEVar aV;
   @SerId(70)
   private IEVar NY;
   @SerId(71)
   private IEVar xf;
   @SerId(72)
   private IEVar Ua;
   @SerId(73)
   private IEVar jT;
   @SerId(90)
   private int AD = 0;
   @SerId(91)
   private Map ZY = new HashMap();
   @SerId(92)
   private int Cw = 12672;
   @SerId(200)
   ReferenceCounter Qo;
   @SerId(201)
   int lN;
   @SerId(202)
   int gT;
   @SerId(203)
   int qr;
   @SerId(204)
   ReferenceCounter IJ;
   @SerId(205)
   ym Of;

   public uq(IProcessor var1) {
      super(var1, var1.getMode() <= 32 ? 32 : 64);
      this.JF = this.regNormalBitsize;
      Object[][] var2 = var1.getMode() == 64 ? new Object[][]{GH, fN} : new Object[][]{fN};

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

      this.uz = this.gCtx.getVar(0);
      this.Xz = this.gCtx.getVar(32);
      this.iK = this.gCtx.getVar(64);
      this.ZE = this.gCtx.getVar(96);
      this.Jh = this.gCtx.getVar(128);
      this.iO = this.gCtx.getVar(160);
      this.Qe = this.gCtx.getVar(192);
      this.dW = this.gCtx.getVar(224);
      this.DP = this.gCtx.getVar(416);
      this.fK = this.gCtx.getVar(448);
      this.ou = this.gCtx.getVar(480);
      if (var1.getMode() == 64) {
         this.lk = this.gCtx.getVar(768);
         this.sa = this.gCtx.getVar(832);
         this.WJ = this.gCtx.getVar(896);
         this.pL = this.gCtx.getVar(960);
         this.aH = this.gCtx.getVar(1024);
         this.yc = this.gCtx.getVar(1088);
         this.eb = this.gCtx.getVar(1152);
         this.zj = this.gCtx.getVar(1216);
         this.aV = this.gCtx.getVar(1280);
         this.Ua = this.gCtx.getVar(2624);
         this.NY = this.gCtx.getVar(2688);
         this.xf = this.gCtx.getVar(2752);
         this.jT = this.gCtx.getVar(2816);
         this.GO = this.gCtx.getVar(553);
         this.QZ = this.gCtx.getVar(552);
         this.Up = this.gCtx.getVar(551);
         this.HO = this.gCtx.getVar(550);
         this.cv = this.gCtx.getVar(576);
      }

      this.Of = new ym(this.gCtx);
      this.fe = this.gCtx.getVar(517);
      this.Kl = this.gCtx.getVar(539);
      this.So = this.gCtx.getVar(528);
      this.AG = this.gCtx.getVar(512);
      this.yu = this.gCtx.createVirtualRegister(65536, "tmp", 32);
      this.lz = this.gCtx.createVirtualRegister(65568, "tmp64", 64);
      this.Nu = this.gCtx.createVirtualRegister(65664, "tmpPC", 32);
      this.lA = this.gCtx.createVirtualRegister(65792, "tmpPredicate", 1);
      this.er = this.gCtx.createVirtualRegister(65793, "carryIn", 1);
      this.SM = this.gCtx.createImm(0L, 1);
      this.bj = this.gCtx.createImm(1L, 1);
      this.Dw();
      this.Qo = new ReferenceCounter();
      this.IJ = new ReferenceCounter();
   }

   @SerCustomInitPostGraph
   private void Dw() {
      this.wS = new Lb(this);
      this.Oz = new IP(this);
      this.yn = new A(this);
      this.es = new aa(this);
      this.o = new mV(this);
      this.gl = new Un(this);
      this.tX = new ya(this);
      this.Qt = new B(this);
      this.JW = new ZS(this);
      this.Ub = new fT(this);
      this.tb = new mn(this);
      if (this.Of == null) {
         this.Of = new ym(this.gCtx);
      }

      if (this.YT == null) {
         this.YT = this.gCtx.createVirtualRegister(65816, "tmp8", 8);
      }

      if (this.AY == null) {
         this.AY = this.gCtx.createVirtualRegister(65824, "tmp16", 16);
      }

      if (this.Ld == null) {
         this.Ld = this.gCtx.createVirtualRegister(65840, "tmp128", 128);
      }

      if (this.XV == null) {
         this.XV = this.gCtx.createVirtualRegister(65696, "tmp64_2", 64);
      }

      if (this.AD != 0) {
         this.Cw = 12672 + this.AD * 64;
      }
   }

   @Override
   public IEVar getProgramCounter() {
      return this.proc.getMode() == 64 ? this.jT : this.ou;
   }

   @Override
   public IEVar getReturnAddressRegister() {
      return this.proc.getMode() == 64 ? this.NY : this.fK;
   }

   @Override
   public IEVar getStackPointer() {
      return this.proc.getMode() == 64 ? this.xf : this.DP;
   }

   @Override
   public long sanitizeNativeAddress(long var1) {
      var1 = super.sanitizeNativeAddress(var1);
      return (var1 & 1L) != 0L ? var1 & -2L : var1 & -4L;
   }

   public IEVar q() {
      return this.getReturnAddressRegister();
   }

   public IEVar RF() {
      return this.Of.xK;
   }

   public int xK() {
      return this.proc.getMode() == 64 ? 2688 : 448;
   }

   public IEGeneric q(int var1) {
      return (IEGeneric)(this.proc.getMode() == 64 ? (var1 == 64 ? this.lk : this.lk.part(var1)) : (var1 == 32 ? this.uz : this.uz.part(var1)));
   }

   @Override
   public void setCurrentContext(IERoutineContext var1) {
      super.setCurrentContext(var1);
      this.wS.RF = var1;
      this.Oz.RF = var1;
      this.yn.RF = var1;
      this.es.RF = var1;
      this.o.RF = var1;
      this.gl.RF = var1;
      this.tX.RF = var1;
      this.Qt.RF = var1;
      this.JW.RF = var1;
      this.Ub.RF = var1;
      this.tb.RF = var1;
      this.Of.gO = var1;
   }

   @Override
   public void convertInstruction(ConverterInstructionEntry var1) {
      this.q((fA)var1.insn, var1.r, var1.address);
   }

   public void q(fA var1, List var2, long var3) {
      ArrayList var5 = new ArrayList();
      this.RF(var1, var5, var3);
      if (!var5.isEmpty() && var1.Uv().gO()) {
         IEGeneric var6 = this.Of.q(var1.Uv().RF(), null);
         ArrayList var7 = new ArrayList();
         this.Of.q(var2.size(), var7, var6, var5, var3 + var1.getSize(), true, this.getProgramCounter());
         var2.addAll(var7);
      } else {
         var2.addAll(var5);
      }
   }

   public void RF(fA var1, List var2, long var3) {
      String var5 = var1.Dw().q();
      if (var1.gO()) {
         var2.add(this.q(var1, var3, false));
      } else if (var1.oW()) {
         if (var1.getProcessorMode() == 64) {
            this.JW.q(var1, var2, var3);
         } else {
            this.Qt.q(var1, var2, var3);
         }
      } else {
         switch (var5) {
            case "ADC":
               this.wS.Uv(var1, var2, var3);
               break;
            case "ADD":
            case "ADDW":
               this.wS.oW(var1, var2, var3);
               break;
            case "AND":
               this.wS.gO(var1, var2, var3);
               break;
            case "BIC":
               this.wS.nf(var1, var2, var3);
               break;
            case "EOR":
               this.wS.gP(var1, var2, var3);
               break;
            case "EON":
               this.wS.za(var1, var2, var3);
               break;
            case "ORN":
               this.wS.lm(var1, var2, var3);
               break;
            case "ORR":
               this.wS.zz(var1, var2, var3);
               break;
            case "RSB":
               this.wS.JY(var1, var2, var3);
               break;
            case "RSC":
               this.wS.HF(var1, var2, var3);
               break;
            case "SBC":
               this.wS.qa(var1, var2, var3);
               break;
            case "SUB":
            case "SUBW":
               this.wS.LK(var1, var2, var3);
               break;
            case "NEG":
               this.wS.io(var1, var2, var3);
               break;
            case "NGC":
               this.wS.Hk(var1, var2, var3);
               break;
            case "MADD":
            case "MLA":
               this.wS.q(var1, var2, var3, OperationType.ADD);
               break;
            case "MSUB":
            case "MLS":
               this.wS.q(var1, var2, var3, OperationType.SUB);
               break;
            case "MNEG":
               this.wS.q(var1, var2, var3, OperationType.SUB, true);
               break;
            case "MUL":
               this.wS.KT(var1, var2, var3);
               break;
            case "BFC":
               this.wS.xK(var1, var2);
               break;
            case "BFI":
               this.wS.Gf(var1, var2, var3);
               break;
            case "BFXIL":
               this.wS.Ef(var1, var2, var3);
               break;
            case "CLS":
               this.wS.sH(var1, var2, var3);
               break;
            case "CLZ":
               this.wS.cC(var1, var2, var3);
               break;
            case "QADD":
               this.Oz.q(var1, var2, var3);
               break;
            case "QADD16":
               this.Oz.gP(var1, var2, var3);
               break;
            case "QADD8":
               this.Oz.za(var1, var2, var3);
               break;
            case "QSUB":
               this.Oz.RF(var1, var2, var3);
               break;
            case "QSUB16":
               this.Oz.lm(var1, var2, var3);
               break;
            case "QSUB8":
               this.Oz.zz(var1, var2, var3);
               break;
            case "QASX":
               this.Oz.JY(var1, var2, var3);
               break;
            case "QSAX":
               this.Oz.HF(var1, var2, var3);
               break;
            case "QDADD":
               this.Oz.xK(var1, var2, var3);
               break;
            case "QDSUB":
               this.Oz.Dw(var1, var2, var3);
               break;
            case "SSAT":
               this.Oz.Uv(var1, var2, var3);
               break;
            case "SSAT16":
               this.Oz.gO(var1, var2, var3);
               break;
            case "UQADD16":
               this.Oz.LK(var1, var2, var3);
               break;
            case "UQADD8":
               this.Oz.io(var1, var2, var3);
               break;
            case "UQASX":
               this.Oz.Me(var1, var2, var3);
               break;
            case "UQSAX":
               this.Oz.PV(var1, var2, var3);
               break;
            case "UQSUB16":
               this.Oz.qa(var1, var2, var3);
               break;
            case "UQSUB8":
               this.Oz.Hk(var1, var2, var3);
               break;
            case "USAT":
               this.Oz.oW(var1, var2, var3);
               break;
            case "USAT16":
               this.Oz.nf(var1, var2, var3);
               break;
            case "SADD16":
               this.Oz.oQ(var1, var2, var3);
               break;
            case "SADD8":
               this.Oz.xW(var1, var2, var3);
               break;
            case "SASX":
               this.Oz.Ef(var1, var2, var3);
               break;
            case "SSAX":
               this.Oz.cC(var1, var2, var3);
               break;
            case "SHADD16":
               this.Oz.jq(var1, var2, var3);
               break;
            case "SHADD8":
               this.Oz.ui(var1, var2, var3);
               break;
            case "SHASX":
               this.Oz.EB(var1, var2, var3);
               break;
            case "SHSAX":
               this.Oz.Xo(var1, var2, var3);
               break;
            case "SSUB16":
               this.Oz.KT(var1, var2, var3);
               break;
            case "SSUB8":
               this.Oz.Gf(var1, var2, var3);
               break;
            case "SHSUB16":
               this.Oz.TX(var1, var2, var3);
               break;
            case "SHSUB8":
               this.Oz.Rr(var1, var2, var3);
               break;
            case "UADD16":
               this.Oz.sH(var1, var2, var3);
               break;
            case "UADD8":
               this.Oz.CE(var1, var2, var3);
               break;
            case "UASX":
               this.Oz.Dz(var1, var2, var3);
               break;
            case "USAX":
               this.Oz.mI(var1, var2, var3);
               break;
            case "UHADD16":
               this.Oz.Bu(var1, var2, var3);
               break;
            case "UHADD8":
               this.Oz.IN(var1, var2, var3);
               break;
            case "UHASX":
               this.Oz.YN(var1, var2, var3);
               break;
            case "UHSAX":
               this.Oz.Rv(var1, var2, var3);
               break;
            case "USUB16":
               this.Oz.wF(var1, var2, var3);
               break;
            case "USUB8":
               this.Oz.If(var1, var2, var3);
               break;
            case "UHSUB16":
               this.Oz.rL(var1, var2, var3);
               break;
            case "UHSUB8":
               this.Oz.eJ(var1, var2, var3);
               break;
            case "USAD8":
               this.Oz.zx(var1, var2, var3);
               break;
            case "USADA8":
               this.Oz.ZT(var1, var2, var3);
               break;
            case "SMLABB":
               this.Oz.Tq(var1, var2, var3);
               break;
            case "SMLABT":
               this.Oz.Yp(var1, var2, var3);
               break;
            case "SMLATB":
               this.Oz.Gu(var1, var2, var3);
               break;
            case "SMLATT":
               this.Oz.nY(var1, var2, var3);
               break;
            case "SMLALBB":
               this.Oz.tW(var1, var2, var3);
               break;
            case "SMLALBT":
               this.Oz.ZA(var1, var2, var3);
               break;
            case "SMLALTB":
               this.Oz.Ov(var1, var2, var3);
               break;
            case "SMLALTT":
               this.Oz.Lj(var1, var2, var3);
               break;
            case "SMULBB":
               this.Oz.lF(var1, var2, var3);
               break;
            case "SMULBT":
               this.Oz.nq(var1, var2, var3);
               break;
            case "SMULTB":
               this.Oz.NX(var1, var2, var3);
               break;
            case "SMULTT":
               this.Oz.br(var1, var2, var3);
               break;
            case "SMMLA":
            case "SMMLAR":
               this.Oz.nv(var1, var2, var3);
               break;
            case "SMMLS":
            case "SMMLSR":
               this.Oz.LL(var1, var2, var3);
               break;
            case "SMMUL":
            case "SMMULR":
               this.Oz.PQ(var1, var2, var3);
               break;
            case "UMAAL":
               this.Oz.fQ(var1, var2, var3);
               break;
            case "SMLAL":
               this.Oz.bl(var1, var2, var3);
               break;
            case "UMLAL":
               this.Oz.fi(var1, var2, var3);
               break;
            case "SMULL":
               if (this.proc.getMode() == 64) {
                  this.yn.q(var1, var2, var3);
               } else {
                  this.Oz.pQ(var1, var2, var3);
               }
               break;
            case "UMULL":
               if (this.proc.getMode() == 64) {
                  this.yn.oW(var1, var2, var3);
               } else {
                  this.Oz.jb(var1, var2, var3);
               }
               break;
            case "SMLAD":
            case "SMLADX":
               this.Oz.TQ(var1, var2, var3);
               break;
            case "SMLSD":
            case "SMLSDX":
               this.Oz.Yw(var1, var2, var3);
               break;
            case "SMUAD":
            case "SMUADX":
               this.Oz.IY(var1, var2, var3);
               break;
            case "SMUSD":
            case "SMUSDX":
               this.Oz.qR(var1, var2, var3);
               break;
            case "SMLALD":
            case "SMLALDX":
               this.Oz.YA(var1, var2, var3);
               break;
            case "SMLSLD":
            case "SMLSLDX":
               this.Oz.fw(var1, var2, var3);
               break;
            case "SMULWB":
            case "SMULWT":
               this.Oz.kf(var1, var2, var3);
               break;
            case "SMLAWB":
            case "SMLAWT":
               this.Oz.GM(var1, var2, var3);
               break;
            case "SMADDL":
               this.yn.RF(var1, var2, var3);
               break;
            case "SMNEGL":
               this.yn.xK(var1, var2, var3);
               break;
            case "SMSUBL":
               this.yn.Dw(var1, var2, var3);
               break;
            case "SMULH":
               this.yn.Uv(var1, var2, var3);
               break;
            case "UMADDL":
               this.yn.gO(var1, var2, var3);
               break;
            case "UMNEGL":
               this.yn.nf(var1, var2, var3);
               break;
            case "UMSUBL":
               this.yn.gP(var1, var2, var3);
               break;
            case "UMULH":
               this.yn.za(var1, var2, var3);
               break;
            case "SXTAB":
               this.Oz.Ri(var1, var2, var3);
               break;
            case "SXTAB16":
               this.Oz.GY(var1, var2, var3);
               break;
            case "SXTAH":
               this.Oz.Wx(var1, var2, var3);
               break;
            case "UXTAB":
               this.Oz.AB(var1, var2, var3);
               break;
            case "UXTAB16":
               this.Oz.CY(var1, var2, var3);
               break;
            case "UXTAH":
               this.Oz.WI(var1, var2, var3);
               break;
            case "SXTB":
               this.wS.Bu(var1, var2, var3);
               break;
            case "SXTB16":
               this.wS.IN(var1, var2, var3);
               break;
            case "SXTH":
               this.wS.rL(var1, var2, var3);
               break;
            case "SXTW":
               this.wS.eJ(var1, var2, var3);
               break;
            case "UXTB":
               this.wS.YN(var1, var2, var3);
               break;
            case "UXTB16":
               this.wS.Rv(var1, var2, var3);
               break;
            case "UXTH":
               this.wS.zx(var1, var2, var3);
               break;
            case "PKHBT":
               this.wS.CE(var1, var2, var3);
               break;
            case "PKHTB":
               this.wS.wF(var1, var2, var3);
               break;
            case "RBIT":
               this.wS.If(var1, var2, var3);
               break;
            case "REV":
               this.wS.Dz(var1, var2, var3);
               break;
            case "REV16":
               this.wS.mI(var1, var2, var3);
               break;
            case "REV32":
               this.wS.jq(var1, var2, var3);
               break;
            case "REVSH":
               this.wS.ui(var1, var2, var3);
               break;
            case "SBFX":
               this.wS.TX(var1, var2, var3);
               break;
            case "UBFX":
               this.wS.Rr(var1, var2, var3);
               break;
            case "SDIV":
               this.wS.EB(var1, var2, var3);
               break;
            case "UDIV":
               this.wS.Xo(var1, var2, var3);
               break;
            case "SBFM":
               this.yn.zz(var1, var2, var3);
               break;
            case "SBFIZ":
               this.yn.lm(var1, var2, var3);
               break;
            case "UBFM":
               this.yn.HF(var1, var2, var3);
               break;
            case "UBFIZ":
               this.yn.JY(var1, var2, var3);
               break;
            case "CMP":
               this.wS.Me(var1, var2, var3);
               break;
            case "CMN":
               this.wS.PV(var1, var2, var3);
               break;
            case "TEQ":
               this.wS.oQ(var1, var2, var3);
               break;
            case "TST":
               this.wS.xW(var1, var2, var3);
               break;
            case "ADR":
            case "ADRP":
            case "MOV":
            case "MOVZ":
            case "MOVW":
               this.wS.q(var1, var2, var3);
               break;
            case "MOVT":
               this.wS.RF(var1, var2);
               break;
            case "MOVN":
            case "MVN":
               this.wS.RF(var1, var2, var3);
               break;
            case "MOVK":
               this.wS.q(var1, var2);
               break;
            case "ASR":
            case "LSL":
            case "LSR":
            case "ROR":
               this.wS.xK(var1, var2, var3);
               break;
            case "RRX":
               this.wS.Dw(var1, var2, var3);
               break;
            case "CCMP":
               this.yn.za(var1, var2);
               break;
            case "CCMN":
               this.yn.lm(var1, var2);
               break;
            case "CINC":
               this.yn.oW(var1, var2);
               break;
            case "CINV":
               this.yn.Uv(var1, var2);
               break;
            case "CSINC":
               this.yn.RF(var1, var2);
               break;
            case "CSINV":
               this.yn.q(var1, var2);
               break;
            case "CSEL":
               this.yn.xK(var1, var2);
               break;
            case "CSET":
               this.yn.nf(var1, var2);
               break;
            case "CNEG":
               this.yn.gO(var1, var2);
               break;
            case "CSNEG":
               this.yn.Dw(var1, var2);
               break;
            case "CSETM":
               this.yn.gP(var1, var2);
               break;
            case "EXTR":
               this.yn.zz(var1, var2);
               break;
            case "SWP":
               if (this.proc.getMode() == 64) {
                  this.gl.RF(var1, var2, var1.q(0).getOperandBitsize());
               } else {
                  this.o.q(var1, var2, var3);
               }
               break;
            case "SWPB":
               if (this.proc.getMode() == 64) {
                  this.gl.RF(var1, var2, 8);
               } else {
                  this.o.RF(var1, var2, var3);
               }
               break;
            case "B":
               this.es.q(var1, var2, var3);
               break;
            case "BX":
               this.es.RF(var1, var2, var3);
               break;
            case "BL":
               this.es.xK(var1, var2, var3);
               break;
            case "BLX":
               this.es.oW(var1, var2, var3);
               break;
            case "BLR":
               this.es.Dw(var1, var2, var3);
               break;
            case "BR":
               this.es.Uv(var1, var2, var3);
               break;
            case "CBZ":
               this.es.gO(var1, var2, var3);
               break;
            case "CBNZ":
               this.es.nf(var1, var2, var3);
               break;
            case "RET":
               this.es.gP(var1, var2, var3);
               break;
            case "TBZ":
               this.es.za(var1, var2, var3);
               break;
            case "TBNZ":
               this.es.lm(var1, var2, var3);
               break;
            case "TBB":
               this.es.zz(var1, var2, var3);
               break;
            case "TBH":
               this.es.JY(var1, var2, var3);
               break;
            case "NOP":
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
                  this.tX.xK(var1, var2, var3);
               } else {
                  this.tX.q(var1, var2, var3);
               }
               break;
            case "MSR":
               if (this.proc.getMode() == 64) {
                  this.tX.Dw(var1, var2, var3);
               } else {
                  this.tX.RF(var1, var2, var3);
               }
               break;
            case "SVC":
               IEUntranslatedInstruction var22 = this.q(var1, var3, true);
               if (this.proc.getMode() == 64) {
                  var22.addSideEffectUsedVariable(this.lk, this.sa, this.WJ, this.pL, this.aH, this.yc, this.aV);
                  var22.addSideEffectDefinedVariable(this.lk);
               } else {
                  var22.addSideEffectUsedVariable(this.uz, this.Xz, this.iK, this.ZE, this.Jh, this.iO, this.Qe, this.dW);
                  var22.addSideEffectDefinedVariable(this.uz);
               }

               var2.add(var22);
               break;
            case "MCR":
            case "MCR2":
               IEGeneric var21 = this.q(var1, (IInstructionOperand)var1.RF()[2], var3);
               var2.add(this.RF(var1, var3, true, var21));
               break;
            case "MCRR":
            case "MCRR2":
               IEGeneric var20 = this.q(var1, (IInstructionOperand)var1.RF()[2], var3);
               IEGeneric var25 = this.q(var1, (IInstructionOperand)var1.RF()[3], var3);
               var2.add(this.RF(var1, var3, true, var20, var25));
               break;
            case "MRC":
            case "MRC2":
               if (var1.RF()[2].RF(this.regNormalBitsize)) {
                  var2.add(this.q(var1, var3, true, this.Of.q, this.Of.RF, this.Of.xK, this.Of.Dw));
               } else {
                  var2.add(this.q(var1, var3, true));
               }
               break;
            case "MRRC":
            case "MRRC2":
               var2.add(this.q(var1, var3, true));
               break;
            case "AUTIA1716":
            case "AUTIASP":
            case "AUTIAZ":
            case "AUTIB1716":
            case "AUTIBSP":
            case "AUTIBZ":
               if (this.oW()) {
                  this.tb.oW(var1, var2, var3, var5.charAt(4));
               } else {
                  var2.add(this.q(var1, var3, false));
               }
               break;
            case "AUTIA":
            case "AUTIZA":
            case "AUTIB":
            case "AUTIZB":
               if (this.oW()) {
                  this.tb.Uv(var1, var2, var3, var5.charAt(var5.length() - 1));
               } else {
                  var2.add(this.q(var1, var3, false));
               }
               break;
            case "AUTDA":
            case "AUTDZA":
            case "AUTDB":
            case "AUTDZB":
               if (this.oW()) {
                  this.tb.Dw(var1, var2, var3, var5.charAt(var5.length() - 1));
               } else {
                  var2.add(this.q(var1, var3, false));
               }
               break;
            case "PACIA1716":
            case "PACIASP":
            case "PACIAZ":
            case "PACIB1716":
            case "PACIBSP":
            case "PACIBZ":
               if (this.oW()) {
                  this.tb.xK(var1, var2, var3, var5.charAt(4));
               } else {
                  var2.add(this.q(var1, var3, false));
               }
               break;
            case "PACIA":
            case "PACIZA":
            case "PACIB":
            case "PACIZB":
               if (this.oW()) {
                  this.tb.RF(var1, var2, var3, var5.charAt(var5.length() - 1));
               } else {
                  var2.add(this.q(var1, var3, false));
               }
               break;
            case "PACDA":
            case "PACDZA":
            case "PACDB":
            case "PACDZB":
               if (this.oW()) {
                  this.tb.q(var1, var2, var3, var5.charAt(var5.length() - 1));
               } else {
                  var2.add(this.q(var1, var3, false));
               }
               break;
            case "PACGA":
            case "XPACLRI":
            case "XPACD":
            case "XPACI":
               var2.add(this.q(var1, var3, false));
               break;
            case "BRAA":
            case "BRAB":
            case "BRAAZ":
            case "BRABZ":
               if (this.oW()) {
                  this.tb.gO(var1, var2, var3, var5.charAt(3));
               } else {
                  IEUntranslatedInstruction var19 = this.q(var1, var3, true);
                  var19.addSideEffectDefinedVariable(this.jT);
                  var2.add(var19);
               }
               break;
            case "BLRAA":
            case "BLRAB":
            case "BLRAAZ":
            case "BLRABZ":
               if (this.oW()) {
                  this.tb.nf(var1, var2, var3, var5.charAt(4));
               } else {
                  IEUntranslatedInstruction var18 = this.q(var1, var3, true);
                  var18.addSideEffectDefinedVariable(this.jT, this.NY);
                  var2.add(var18);
               }
               break;
            case "RETAA":
            case "RETAB":
               if (this.oW()) {
                  this.tb.gP(var1, var2, var3, var5.charAt(4));
               } else {
                  IEUntranslatedInstruction var17 = this.q(var1, var3, true);
                  var17.addSideEffectUsedVariable(this.NY);
                  var17.addSideEffectDefinedVariable(this.jT);
                  var2.add(var17);
               }
               break;
            case "SWPLB":
            case "SWPAB":
            case "SWPALB":
               this.gl.RF(var1, var2, 8);
               break;
            case "SWPH":
            case "SWPLH":
            case "SWPAH":
            case "SWPALH":
               this.gl.RF(var1, var2, 16);
               break;
            case "SWPL":
            case "SWPA":
            case "SWPAL":
               this.gl.RF(var1, var2, var1.q(0).getOperandBitsize());
               break;
            case "CASB":
            case "CASLB":
            case "CASAB":
            case "CASALB":
               this.gl.q(var1, var2, 8);
               break;
            case "CASH":
            case "CASLH":
            case "CASAH":
            case "CASALH":
               this.gl.q(var1, var2, 16);
               break;
            case "CAS":
            case "CASL":
            case "CASA":
            case "CASAL":
               this.gl.q(var1, var2, var1.q(0).getOperandBitsize());
               break;
            case "CASP":
            case "CASPL":
            case "CASPA":
            case "CASPAL":
               this.gl.q(var1, var2);
               break;
            case "CRC32":
            case "CRC32B":
            case "CRC32CB":
            case "CRC32CH":
            case "CRC32CW":
            case "CRC32H":
            case "CRC32W":
            case "CRC32CX":
            case "CRC32X":
            case "SEL":
            case "ABS":
            case "CNT":
            case "CTZ":
            case "SMAX":
            case "SMIN":
            case "UMAX":
            case "UMIN":
            case "SWPP":
            case "SWPPA":
            case "SWPPAL":
            case "SWPPL":
               var2.add(this.q(var1, var3, false));
               break;
            case "LD64B":
               IEUntranslatedInstruction var16 = this.q(var1, var3, true);

               for (int var24 = 1; var24 < 8; var24++) {
                  IEGeneric var27 = this.q(var1.RF()[0].getOperandValue() + var24, true);
                  IEVar var29 = RF(var27);
                  if (var29 != null) {
                     var16.addSideEffectDefinedVariable(var29);
                  }
               }

               var2.add(var16);
               break;
            case "ST64B":
               IEUntranslatedInstruction var15 = this.q(var1, var3, true);

               for (int var23 = 1; var23 < 8; var23++) {
                  IEGeneric var26 = this.q(var1.RF()[0].getOperandValue() + var23, true);
                  IEVar var28 = RF(var26);
                  if (var28 != null) {
                     var15.addSideEffectUsedVariable(var28);
                  }
               }

               var2.add(var15);
               break;
            case "ST64BV":
            case "ST64BV0":
               IEUntranslatedInstruction var14 = this.q(var1, var3, true);
               IEGeneric var9 = this.q(var1.RF()[0].getOperandValue(), true);
               IEVar var10 = RF(var9);
               if (var10 != null) {
                  var14.addSideEffectDefinedVariable(var10);
               }

               for (int var11 = 1; var11 < 8; var11++) {
                  IEGeneric var12 = this.q(var1.RF()[1].getOperandValue() + var11, true);
                  IEVar var13 = RF(var12);
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
            case "BTI":
            case "CFP":
            case "CPP":
            case "DVP":
            case "WFET":
            case "WFIT":
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
            case "AT":
            case "BRK":
            case "DC":
            case "DRPS":
            case "IC":
            case "BRB":
            case "CHKFEAT":
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
            case "CLRBHB":
            case "DGH":
            case "CFINV":
            case "AXFLAG":
            case "XAFLAG":
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
               var2.add(this.q(var1, var3, true));
               break;
            default:
               fV var8 = PG.q(var1);
               if (var8 != null) {
                  if ((var5.equals("LDRAA") || var5.equals("LDRAB")) && !this.oW()) {
                     var2.add(this.q(var1, var3, true));
                  } else if (var8.nf()) {
                     this.o.q(var1, var8, var2, var3);
                  } else {
                     this.o.RF(var1, var8, var2, var3);
                  }
               } else if (Strings.startsWith(var5, "CPY", "SETP", "SETG", "SETM", "SETE")) {
                  var2.add(this.q(var1, var3, true));
               } else {
                  if (!Strings.startsWith(var5, "RCW", "LDIAPP", "STILP")) {
                     this.gT++;
                     if (this.Qo != null) {
                        this.Qo.inc(var5);
                     }

                     throw new UnsupportedConversionException("Cannot convert instruction: " + var1);
                  }

                  var2.add(this.q(var1, var3, true));
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
      ym.eo var9 = null;
      QY var10 = null;

      for (int var11 = var2.size(); var8 < var1.size() || var10 != null; var8++) {
         fA var12;
         if (var8 >= var1.size()) {
            var12 = (fA)this.ctx.getRoutine().getData().getCFG().getInstruction(var5);
            if (var12 == null) {
               this.gT++;
               if (this.Qo != null) {
                  this.Qo.inc("ITBlock");
               }

               throw new UnsupportedConversionException(Strings.ff("Can not retrieve the end of IT Block at address %x", var5));
            }

            this.lN++;
         } else {
            this.lN++;
            var12 = (fA)var1.get(var8);
         }

         var7.clear();
         if (var12.zz()) {
            if (var10 != null) {
               throw new IllegalConversionException("Illegal IT Block within an IT Block");
            }

            var10 = new QY(this, var12, var5);
         } else {
            this.RF(var12, var7, var5);
         }

         if (!var12.zz()) {
            IEGeneric var13 = var9 != null ? var9.q(var1, var8, var2, var11, var10 == null) : null;
            var9 = this.Of.q(this, var12, var7, var5, var8, var9);
            if (var10 == null && !var7.isEmpty() && var12.Uv().gO() && !var12.Uv().Dw() && !var12.Dw().RF() && !var12.q().isPCUpdated()) {
               var10 = QY.q(this, var1, var8, var5, 10, var13);
            }

            if (var10 != null) {
               var10.q(var5, var7, var12);
               if (var10.q()) {
                  var10.q(this.ctx, var2, var5 + var12.getSize());
                  var10 = null;
               }
            } else {
               this.q(var7, var1, var8, var5);
               if (!var7.isEmpty() && var12.Uv().gO()) {
                  IEGeneric var14 = this.Of.q(var12.Uv().RF(), var13);
                  ArrayList var15 = new ArrayList();
                  this.Of.q(var2.size(), var15, var14, var7, var5 + var12.getSize(), true, this.getProgramCounter());
                  var7 = var15;
               }

               EUtil.setLowerLevelAddress(var5, var7);
               var2.addAll(var7);
            }
         }

         var5 += var12.getSize();
      }
   }

   private void q(List var1, BasicBlock var2, int var3, long var4) {
      fA var6 = (fA)var2.get(var3);
      fA var7 = var3 + 1 < var2.size() ? (fA)var2.get(var3 + 1) : null;
      Long var8 = this.q(var1, var6, var7, var4);
      if (var8 != null) {
         var1.add(this.ctx.createBranchAssign(this.getProgramCounter(), this.ctx.createImm(var8, this.JF), false));
      }
   }

   Long q(List var1, fA var2, fA var3, long var4) {
      if (this.q(var2)) {
         long var6 = var4 + var2.getSize();
         if (var3 == null) {
            var3 = (fA)this.ctx.getRoutine().getData().getCFG().getInstruction(var6);
            if (var3 == null) {
               return null;
            }
         }

         if (!var3.q().isPCUpdated() || var3.q().getBranchType() == IInsnEmulator.BranchType.CALL) {
            return null;
         }

         if (var2.Uv().equals(var3.Uv())) {
            ArrayList var8 = new ArrayList();
            this.RF(var3, var8, var6);
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

   IEUntranslatedInstruction q(fA var1, long var2, boolean var4, IEGeneric... var5) {
      IEUntranslatedInstruction var6 = this.q(var1, var2, var4);

      for (IEGeneric var10 : var5) {
         if (var10 instanceof IEVar) {
            var6.addSideEffectDefinedVariable((IEVar)var10);
         }
      }

      return var6;
   }

   IEUntranslatedInstruction RF(fA var1, long var2, boolean var4, IEGeneric... var5) {
      IEUntranslatedInstruction var6 = this.q(var1, var2, var4);

      for (IEGeneric var10 : var5) {
         if (var10 instanceof IEVar) {
            var6.addSideEffectUsedVariable((IEVar)var10);
         }
      }

      return var6;
   }

   private boolean q(fA var1) {
      if (!"MOV".equals(var1.Dw().q())) {
         return false;
      } else {
         CW var2 = var1.RF()[0];
         CW var3 = var1.RF()[1];
         return var2.xK(this.regNormalBitsize) && var3.RF(this.regNormalBitsize);
      }
   }

   @Override
   public IEBranchDetails getDefaultBranchToRoutineSideEffects(INativeMethodItem var1) {
      ArrayList var2 = Lists.createArrayList();
      ArrayList var3 = Lists.createArrayList();
      ArrayList var4 = Lists.createArrayList();
      if (this.proc.getMode() == 64) {
         Lists.addNonNulls(var2, this.lk, this.sa, this.WJ, this.pL, this.aH, this.yc, this.eb, this.zj, this.aV, this.xf);
         Lists.addNonNulls(var3, this.lk);
         Lists.addNonNulls(var4, this.sa, this.WJ, this.pL, this.aH, this.yc, this.eb, this.zj, this.aV);
      } else {
         Lists.addNonNulls(var2, this.uz, this.Xz, this.iK, this.ZE, this.DP);
         Lists.addNonNulls(var3, this.uz);
         Lists.addNonNulls(var4, this.Xz, this.iK, this.ZE);
      }

      return this.gCtx.createBranchDetails(var3, var2, var4, 0);
   }

   public IEGeneric q(fA var1, long var2, int var4) {
      int var5 = fp.q(var1.getProcessorMode());
      if (var2 == -1L) {
         throw new IllegalArgumentException("Address unknown for PC");
      } else {
         return this.q(var2 + var5 + var4);
      }
   }

   public IEGeneric q(fA var1, long var2, IEGeneric var4) {
      int var5 = fp.q(var1.getProcessorMode());
      if (var2 == -1L) {
         throw new IllegalArgumentException("Address unknown for PC");
      } else {
         return EUtil.add(this.q(var2 + var5), var4);
      }
   }

   IEUntranslatedInstruction q(fA var1, long var2, boolean var4) {
      String var5 = var1.getMnemonic();
      if (!var5.startsWith("SVC")) {
         this.qr++;
         if (this.IJ != null) {
            this.IJ.inc(var5);
         }
      }

      IEUntranslatedInstruction var6;
      if (var4) {
         var6 = this.ctx.createUntranslatedInstruction(var2, var5, this.RF(var1, var2, false));
         CW[] var7 = var1.JY();

         for (CW var11 : var7) {
            int var12 = RegisterUtil.getRegisterGroup(var11.getOperandValue());
            if (var12 == 11 && RegisterUtil.getRegisterIndex(var11.getOperandValue()) == RegisterBankArm.APSR_nzcv) {
               var6.addSideEffectDefinedVariable(this.Of.q);
               var6.addSideEffectDefinedVariable(this.Of.RF);
               var6.addSideEffectDefinedVariable(this.Of.xK);
               var6.addSideEffectDefinedVariable(this.Of.Dw);
            } else {
               IEVar var13 = RF(this.getRegisterVariableFromNativeRegisterId(var11.getOperandValue()));
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

   IEGeneric[] RF(fA var1, long var2, boolean var4) {
      if (var1.RF() == null) {
         return null;
      } else {
         List var5 = this.q(var1, var1.RF(), var2, var4);
         return (IEGeneric[])var5.toArray(new IEGeneric[var5.size()]);
      }
   }

   List q(fA var1, IInstructionOperand[] var2, long var3, boolean var5) {
      ArrayList var6 = new ArrayList();

      for (int var7 = 0; var7 < var2.length; var7++) {
         CW var8 = (CW)var2[var7];

         try {
            if (var8.getOperandType() == 7) {
               if (var8 instanceof BY && ((BY)var8).xK() == null) {
                  var6.addAll(this.q(var1, ((yS)var8).q(), var3, var5));
               } else if (var8 instanceof BY) {
                  var6.add(this.Dw(var1, var8, var3, 0, false).RF());
               } else {
                  var6.addAll(this.q(var1, ((yS)var8).q(), var3, var5));
               }
            } else {
               IEGeneric var9 = this.q(var1, var8, 0, var3);
               if (var9 != null) {
                  var6.add(var9);
               }
            }
         } catch (InstructionConversionException var10) {
            if (var5) {
               throw var10;
            }

            AN.catchingSilent(var10);
         } catch (RuntimeException var11) {
            if (var5) {
               throw var11;
            }

            AN.catchingSilent(var11);
         }
      }

      return var6;
   }

   public CZ q(fA var1, CW var2) {
      switch (var2.getOperandType()) {
         case 0:
            return this.q(var1, var2, -1L, 0, true);
         default:
            if (var2 instanceof BY) {
               return this.q(var1, var2, -1L, 0, true);
            } else {
               throw new IllegalConversionException("Destination register must be a register in " + (var1 == null ? "" : var1.getMnemonic()));
            }
      }
   }

   public IEGeneric q(fA var1, IInstructionOperand var2, int var3, long var4) {
      CZ var6 = this.q(var1, (CW)var2, var4, var3, false);
      return var6 == null ? null : var6.RF();
   }

   public IEGeneric q(fA var1, IInstructionOperand var2, long var3) {
      CZ var5 = this.q(var1, (CW)var2, var3, 0, false);
      return var5 == null ? null : var5.RF();
   }

   public IEGeneric q(fA var1, IInstructionOperand var2) {
      return this.q(var1, var2, 0);
   }

   public IEGeneric q(fA var1, IInstructionOperand var2, int var3) {
      if (var1.getProcessorMode() != 64) {
         throw new IllegalConversionException("Attempt to convert 32-bit operand in 64-bit operation " + var1.getMnemonic());
      } else {
         return this.q(var1, (CW)var2, -1L, var3, false).RF();
      }
   }

   public IEMem q(fA var1, CW var2, long var3, int var5) {
      IEGeneric var6 = this.q(var1, (IInstructionOperand)((wh)var2).RF(), var3);
      return this.ctx.createMem(var6, var5);
   }

   public int q(fA var1, CW var2, long var3) {
      return (int)this.RF(var1, var2, var3);
   }

   public long RF(fA var1, CW var2, long var3) {
      switch (var2.getOperandType()) {
         case 1:
         case 2:
         case 3:
            return var2.q(var3, var1.getProcessorMode(), null);
         default:
            throw new IllegalConversionException("NaN parameter");
      }
   }

   private CZ q(fA var1, CW var2, long var3, int var5, boolean var6) {
      CZ var7 = this.RF(var1, var2, var3, var5, var6);
      return var2.Dw() ? new CZ(this.q(var7.q()), var7.xK(), var7.Dw()).q(var7.Uv()) : var7;
   }

   private CZ RF(fA var1, CW var2, long var3, int var5, boolean var6) {
      switch (var2.getOperandType()) {
         case 0:
            return this.xK(var1, var2, var3, var5, var6);
         case 1:
            return new CZ(this.ctx.createImm(var2.getOperandValue(var3), var5 == 0 ? var2.getOperandBitsize() : var5));
         case 2:
         case 3:
            return new CZ(this.q(var2.getOperandValue(var3)));
         case 7:
            return this.Dw(var1, var2, var3, var5, var6);
         case 4097:
            double var7 = ((HR)var2).q();
            if (((HR)var2).getOperandBitsize() == 32) {
               return new CZ(this.ctx.createImm((float)var7));
            }

            return new CZ(this.ctx.createImm(var7));
         default:
            return null;
      }
   }

   private CZ xK(fA var1, CW var2, long var3, int var5, boolean var6) {
      if (!var6 && var2.RF(this.regNormalBitsize)) {
         return new CZ(this.q(var1, var3, 0)).q(var5);
      } else {
         IEGeneric var7 = this.q(var2.getOperandValue(), var6);
         if (!var6 && var2 instanceof RI) {
            int var8 = xB.q((RI)var2);
            if (var8 <= 0) {
               throw new UnsupportedConversionException("Illegal size for SIMD Register " + var1);
            }

            var7 = var7.part(var8);
         }

         return new CZ(var7).q(var5);
      }
   }

   private CZ Dw(fA var1, CW var2, long var3, int var5, boolean var6) {
      if (var2.oW().length == 1) {
         if (var2 instanceof wh) {
            throw new UnsupportedConversionException("Can not process operand list, type not managed: " + var2.getClass());
         } else {
            return this.RF(var1, var2.oW()[0], var3, var5, var6);
         }
      } else {
         if (!(var2 instanceof ZD) && !(var2 instanceof IM)) {
            if (var2 instanceof BY) {
               int var8;
               IEGeneric var10;
               if (var2.oW()[0] instanceof RI) {
                  RI var9 = (RI)var2.oW()[0];
                  var10 = this.q(var9.getOperandValue(), var6);
                  var8 = xB.q(var9);
               } else {
                  if (var2.oW()[0] instanceof eL) {
                     throw new UnsupportedConversionException("Register List must be converted by caller " + var1);
                  }

                  CZ var11 = this.RF(var1, var2.oW()[0], var3, var5, var6);
                  if (!var11.oW()) {
                     throw new UnsupportedConversionException("Double indexing not managed: " + var2.getClass());
                  }

                  var10 = var11.q();
                  if (var1.getProcessorMode() == 64) {
                     throw new UnsupportedConversionException("Can not process operand list, type not managed: " + var2.getClass());
                  }

                  var8 = k.q(var1.Dw());
               }

               Integer var12 = ((BY)var2).xK();
               if (var12 == null) {
                  throw new UnsupportedConversionException("Can not process operand list, type not managed: " + var2.getClass());
               }

               if (var8 == -1) {
                  throw new UnsupportedConversionException("Can not process operand list, unknown size: " + var1);
               }

               return new CZ(var10, var8 * var12, var8 * (var12 + 1));
            }

            if (var2 instanceof JK) {
               throw new UnsupportedConversionException("Can not process aligned register, must be managed by caller.");
            }
         } else {
            ii var7 = this.q(var1, var2, var3, var5, this.RF());
            if (var7 != null) {
               return new CZ(var7.q());
            }
         }

         throw new UnsupportedConversionException("Can not process operand list, type not managed: " + var2.getClass());
      }
   }

   ii q(fA var1, CW var2, long var3, int var5, IEVar var6) {
      switch (var2.getOperandType()) {
         case 0:
         case 1:
         case 2:
         case 3:
         case 4:
         case 5:
         case 6:
         default:
            return new ii(this.q(var1, var2, var5, var3), null);
         case 7:
            if (var2.oW().length == 1) {
               return this.q(var1, var2.oW()[0], var3, var5, var6);
            } else if (var2 instanceof ZD) {
               CW var12 = ((ZD)var2).RF();
               IEGeneric var13 = this.q(var1, var12, var5, var3);
               DH var14 = ((ZD)var2).xK();
               CW var15 = var14.xK();
               IEGeneric var11 = null;
               if (var15 != null) {
                  var11 = this.q(var1, (IInstructionOperand)var15, var3);
               }

               if (var14.RF().q()) {
                  return this.q(var14.RF(), var13, var11, var5, var6, var1.getProcessorMode());
               } else {
                  return var14.RF().RF() ? new ii(this.q(var14.RF(), var13, var15), null) : null;
               }
            } else if (var2 instanceof IM) {
               CW var7 = ((IM)var2).RF();
               IEGeneric var8 = this.q(var1, var7, var5, var3);
               CW var9 = ((IM)var2).xK();
               IEGeneric var10 = this.q(var1, (IInstructionOperand)var9, var3);
               return this.q(DH.eo.Dw, var8, var10, var5, this.RF(), var1.getProcessorMode());
            } else {
               throw new UnsupportedConversionException("Can not process operand list, type not managed: " + var2.getClass());
            }
      }
   }

   ii q(DH.eo var1, IEGeneric var2, IEGeneric var3, int var4, IEVar var5, int var6) {
      if (var3 != null) {
         if (var6 == 64) {
            var3 = var3.part(var2.getBitsize() == 64 ? 6 : 5);
         }

         var3 = var3.zeroExtend(var2.getBitsize());
      }

      IEOperation var7;
      switch (var1) {
         case q:
            var7 = this.ctx
               .createOperation(OperationType.SHL, this.Dw(var2.getBitsize()), EUtil.sub(this.ctx.createImm(this.JF, var2.getBitsize()), var3.duplicate()));
            break;
         case RF:
         case xK:
         case Dw:
            var7 = this.ctx.createOperation(OperationType.SHL, this.Dw(var2.getBitsize()), EUtil.sub(var3.duplicate(), this.Dw(var2.getBitsize())));
            break;
         case Uv:
            IECompose var8 = this.ctx.createCompose(var2.slice(1, var2.getBitsize()), var5);
            return new ii((IEGeneric)(var4 == 0 ? var8 : var8.zeroExtend(var4)), var2.bit(0), var5 == this.er);
         default:
            return null;
      }

      Object var12 = this.ctx.createOperation(this.q(var1), var2, var3);
      IEOperation var9 = this.ctx.createOperation(OperationType.AND, var2, var7);
      IECond var10 = this.ctx.createCond(var3, this.ctx.createCond(var9, this.bj, this.SM), this.RF());
      if (var6 != 64 && var1 != DH.eo.Dw && (!(var3 instanceof IEImm) || ((IEImm)var3).getValueAsLong() > 32L)) {
         IEImm var11 = this.ctx.createImm(this.JF, var3.getBitsize());
         var10 = this.ctx.createCond(EUtil.gtU(var3, var11), this.SM, var10);
         var12 = this.ctx.createCond(EUtil.geU(var3, var11), this.Uv(((IEGeneric)var12).getBitsize()), (IEGeneric)var12);
      }

      return new ii((IEGeneric)(var4 == 0 ? var12 : ((IEGeneric)var12).zeroExtend(var4)), var10);
   }

   OperationType q(DH.eo var1) {
      switch (var1) {
         case q:
            return OperationType.SHL;
         case RF:
            return OperationType.SHR;
         case xK:
            return OperationType.SAR;
         case Dw:
            return OperationType.ROR;
         default:
            return null;
      }
   }

   private IEGeneric q(DH.eo var1, IEGeneric var2, CW var3) {
      boolean var4 = false;
      int var5 = 0;
      switch (var1) {
         case lm:
            var5 = 8;
            break;
         case zz:
            var5 = 16;
            break;
         case JY:
            var5 = 32;
            break;
         case HF:
            var5 = 64;
            break;
         case gO:
            var4 = true;
            var5 = 8;
            break;
         case nf:
            var4 = true;
            var5 = 16;
            break;
         case gP:
            var4 = true;
            var5 = 32;
            break;
         case za:
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

   private IRegisterBank Uv() {
      return RegisterUtil.getBank(this.regNormalBitsize == 64 ? ProcessorType.ARM64 : ProcessorType.ARM);
   }

   public IEGeneric q(long var1, boolean var3) {
      int var4 = RegisterUtil.getRegisterGroup(var1);
      int var5 = RegisterUtil.getRegisterIndex(var1);
      RegisterDescriptionEntry var6 = this.Uv().getDescriptionEntryById(var1);
      switch (var4) {
         case 0:
            if (this.regNormalBitsize == 64) {
               int var12 = RegisterUtil.getRegisterBitsize(var1);
               var12 = var12 == 0 ? 64 : var12;
               if (!var3 && var5 == 33) {
                  return this.ctx.createImm(0L, var12);
               }

               if (var12 == 64) {
                  return this.gCtx.createRegister(xK(var5), var6.getName(), this.regNormalBitsize);
               }

               IEVar var15 = this.gCtx.createRegister(xK(var5), var6.getName(), this.JF);
               return (IEGeneric)(var3 ? var15 : var15.part(var12));
            }

            return this.gCtx.createRegister(RF(var5), var6.getName(), this.regNormalBitsize);
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
            if (this.JF == 64) {
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
            IEVar var7 = (IEVar)this.ZY.get(var5);
            if (var7 == null) {
               if (this.Cw + 64 + (32768 - this.ZY.size()) >= 65536) {
                  var7 = this.gCtx.createRegister(this.Cw, var6.getName(), 1);
                  this.Cw++;
               } else {
                  var7 = this.gCtx.createRegister(this.Cw, var6.getName(), 64);
                  this.Cw += 64;
               }

               this.ZY.put(var5, var7);
            }

            return var7;
         case 11:
            if (var5 == RegisterBankArm.APSR_nzcv) {
               return this.ctx.createCompose(this.Of.Dw, this.Of.xK, this.Of.RF, this.Of.q);
            }

            throw new UnsupportedConversionException("Register type [STATUS_REGISTER] not converted yet ");
         case 13:
            return this.gCtx.createRegister(12288 + var5 * 16, var6.getName(), 16);
      }
   }

   public RX q(CW[] var1, int var2, int var3) {
      if (var2 == 0) {
         return this.q(var1[0]);
      } else if (var1.length == var3) {
         return this.q(var1[var2]);
      } else if (var1.length + 1 == var3) {
         return this.q(var1[var2 - 1]);
      } else {
         throw new IllegalArgumentException("Wrong index for operands: " + Strings.joinv(",", var1));
      }
   }

   public RX q(CW var1) {
      switch (var1.getOperandType()) {
         case 0:
            IEGeneric var6 = this.q(var1.getOperandValue(), false);
            if (var1 instanceof RI) {
               return new RX(var6, (RI)var1);
            }

            return new RX(var6);
         case 1:
            return new RX(this.ctx.createImm(var1.getOperandValue(), var1.getOperandBitsize()));
         case 7:
            if (var1 instanceof ZD var4) {
               if (var4.RF().getOperandType() == 1 && var4.xK().xK().getOperandType() == 1) {
                  Long var3 = var4.q(-1L, 64, null);
                  if (var3 != null) {
                     return new RX(this.ctx.createImm(var3, var1.getOperandBitsize()));
                  }
               }
            } else if (var1 instanceof BY) {
               CZ var5 = this.Dw(null, var1, -1L, 0, false);
               if (var5 != null) {
                  return new RX(var5);
               }
            }

            throw new IllegalArgumentException("Not a vector register");
         case 4097:
            double var2 = ((HR)var1).q();
            if (((HR)var1).getOperandBitsize() == 32) {
               return new RX(this.ctx.createImm((float)var2));
            }

            return new RX(this.ctx.createImm(var2));
         default:
            throw new IllegalArgumentException("Not a vector register");
      }
   }

   private static IEVar RF(IEGeneric var0) {
      if (var0 instanceof IEVar) {
         return (IEVar)var0;
      } else {
         return var0 instanceof IESlice && ((IESlice)var0).getWholeExpression() instanceof IEVar ? (IEVar)((IESlice)var0).getWholeExpression() : null;
      }
   }

   IEImm q(long var1) {
      return this.ctx.createImm(var1, this.JF);
   }

   IEOperation q(IEGeneric var1) {
      return this.ctx.createOperation(OperationType.SUB, this.Uv(var1.getBitsize()), var1);
   }

   @Override
   public IEGeneric normalizeBranchingExpression(IDFA var1, BasicBlock var2, IEGeneric var3, IEGeneric var4) {
      if (!(var3 instanceof VQ)) {
         return super.normalizeBranchingExpression(var1, var2, var3, var4);
      } else {
         ane var16 = ((VQ)var3).xK();
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
               if (var4 == null || this.q(var2, var15, var4, var1) || super.isPCRightValueCompatibleReturnValue(var1, var2, var15, var4)) {
                  return var15;
               }
            }
         }

         return null;
      }
   }

   private boolean q(BasicBlock var1, IEGeneric var2, IEGeneric var3, IDFA var4) {
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
            int var10 = this.q(var9, var5, var3, var4);
            switch (var10) {
               case -1:
                  return false;
               case 0:
               default:
                  var7.add(var9);

                  for (BasicBlock var13 : new ArrayList(var9.getInputBlocks())) {
                     if (!var7.contains(var13) && !var6.contains(var13)) {
                        var6.add(var13);
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

   private int q(BasicBlock var1, int var2, IEGeneric var3, IDFA var4) {
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

   void q(fA var1, List var2, IEGeneric var3, IEGeneric var4) {
      this.q(var1, var2, var3, var4, false);
   }

   void q(fA var1, List var2, CZ var3, IEGeneric var4) {
      this.q(var1, var2, var3, var4, false);
   }

   void q(fA var1, List var2, CZ var3, IEGeneric var4, boolean var5) {
      if (var3.oW()) {
         this.q(var1, var2, var3.q(), var4, var5);
      } else {
         ArrayList var6 = new ArrayList();
         if (var3.xK() != 0) {
            var6.add(var3.q().part(var3.xK()));
         }

         var6.add(var4);
         if (var3.Dw() < var3.q().getBitsize()) {
            var6.add(var3.q().slice(var3.Dw(), var3.q().getBitsize()));
         }

         IEGeneric var7 = EUtil.compose(this.ctx, var6);
         this.q(var1, var2, var3.q(), var7, var5);
      }
   }

   void q(fA var1, List var2, IEGeneric var3, IEGeneric var4, boolean var5) {
      if (var1.getProcessorMode() != 64 && var3 instanceof IEVar && ((IEVar)var3).getId() == this.getProgramCounter().getId()) {
         if (!var5) {
            AN.warn(S.L("PC shall not be updated: instruction '%s' is unpredictable"), var1);
         }

         this.q(var1, var4, var2);
      } else if (var1.getProcessorMode() != 64 || !var3.isImm()) {
         var2.add(this.ctx.createAssign(var3, EUtil.safeExtend(var4, var3.getBitsize(), false)));
      }
   }

   void q(fA var1, IEGeneric var2, List var3) {
      if (var1.getProcessorMode() == 64) {
         throw new IllegalConversionException("Can not write directly to PC in AArch64 mode");
      } else {
         if (var1.getProcessorMode() == 32) {
            this.es.q(var3, var2);
         } else {
            var3.add(this.es.q(var1, var3, -1L, var2));
         }
      }
   }

   IEStatement q(uq.eo var1, IEGeneric var2) {
      return this.ctx.createJumpFar(this.q((long)var1.RF), var2);
   }

   @Override
   public int getStateProcessorMode(EState var1) {
      if (var1 == null) {
         return this.proc.getMode();
      } else {
         return this.proc.getMode() == 64 ? 64 : (var1.getValueAsLong(517) == 0L ? 32 : 16);
      }
   }

   public static int RF(int var0) {
      return 0 + var0 * 32;
   }

   public static int xK(int var0) {
      return 768 + var0 * 64;
   }

   IEImm Dw(int var1) {
      return EUtil.one(var1);
   }

   IEImm Uv(int var1) {
      return EUtil.zero(var1);
   }

   IEImm oW(int var1) {
      return EUtil.minusOne(var1);
   }

   IEVar gO(int var1) {
      switch (var1) {
         case 1:
            return this.lA;
         case 8:
            return this.YT;
         case 16:
            return this.AY;
         case 32:
            return this.yu;
         case 64:
            return this.lz;
         case 128:
            return this.Ld;
         default:
            throw new IllegalConversionException("no tmp variable available with size: " + var1);
      }
   }

   IEVar nf(int var1) {
      switch (var1) {
         case 32:
            return this.Nu;
         case 64:
            return this.XV;
         default:
            throw new IllegalConversionException("no tmp variable available with size: " + var1);
      }
   }

   @Override
   public String formatStatistics() {
      StringBuilder var1 = new StringBuilder(super.formatStatistics());
      var1.append("\n\n");
      Strings.ff(var1, "Converted instructions: %d (incl. failed=%d, untranslated=%d)\n", this.lN, this.gT, this.qr);
      if (this.Qo != null && !this.Qo.isEmpty()) {
         var1.append("Failed instructions: ").append(this.Qo.formatTopReferences(-1));
      }

      if (this.IJ != null && !this.IJ.isEmpty()) {
         var1.append("Untranslated instructions: ").append(this.IJ.formatTopReferences(-1));
      }

      return var1.toString();
   }

   @Override
   protected void preRoutineConversion(INativeMethodItem var1, IERoutineContext var2, List var3) {
      super.preRoutineConversion(var1, var2, var3);
      this.yW = new long[]{var1.getData().getCFG().getFirstAddress(), var1.getData().getCFG().getEndAddress()};
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
         fA var11 = (fA)var1.getRoutine().getData().getCFG().getInstruction(var9);
         if (var11.Dw().q().equals("B")) {
            List var12 = var5.getOutputBlocks();
            if (var12.size() != 1) {
               AN.catchingSilent(new JebRuntimeException("Could not retrieve out block @0x" + Long.toHexString(var9)));
            } else {
               BasicBlock var13 = (BasicBlock)var12.get(0);
               if (var13.getInputBlocks().size() != 1) {
                  AN.catchingSilent(new JebRuntimeException("Multiple entry block @0x" + Long.toHexString(var9)));
               } else if (var13.size() != 1) {
                  AN.catchingSilent(new JebRuntimeException("Multiple instructions @0x" + Long.toHexString(var9)));
               } else {
                  INativeType var14 = var1.getRoutine().getReturnType();
                  boolean var15 = var14 == null || var14.getSize() == 0;
                  if (!var15 && !var8 && var7.getPrototype().getReturnType().isVoid()) {
                     Object[] var10000 = new Object[]{var7};
                     IWildcardTypeManager var16 = var1.getWildcardTypeManager();
                     IWildcardType var17 = var16.createWithSlotcount(1);
                     IWildcardPrototype var18 = var7.getPrototype();
                     IWildcardPrototype var19 = var16.createPrototype(
                        var18.getCallingConvention(), var17, var18.getParameterTypes(), var18.getPrototypeAttributes()
                     );
                     IECall var20 = var1.createCall(var7.getCallSite(), null, var19, null, false);
                     IEVar var21 = this.proc.getMode() == 64 ? this.lk : this.uz;
                     IEVar var22 = var1.copyVariable(var21);
                     var20.replaceVar(var21, var22, false);
                     var20.copyProperties(var7);
                     var5.set(var5.size() - 1, var20);
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

   public final List q(BasicBlock var1, boolean var2) {
      if (!var2) {
         this.Of = new ym(this.gCtx, var2);
      }

      return this.convertBlockForTest(var1);
   }

   public final List q(fA var1, long var2) {
      ArrayList var11;
      try {
         aml var4 = new aml((alr)this.gCtx);
         this.setCurrentContext(var4);
         ArrayList var5 = new ArrayList();
         this.q(var1, var5, var2);

         for (IEStatement var7 : var5) {
            if (var7.getPrimaryLowerLevelAddress() == null) {
               var7.setLowerLevelAddress(var2);
            }
         }

         var4.setStatements(var5);
         this.gCtx.addRoutineContext(var4);
         var11 = var5;
      } finally {
         this.setCurrentContext(null);
      }

      return var11;
   }

   @Override
   public void customInitStateRegisters(EState var1, Long var2) {
      super.customInitStateRegisters(var1, var2);
      if (this.JF == 64) {
         var1.setValue(this.Of.RF, 1L);
      }
   }

   private boolean oW() {
      return true;
   }

   @Ser
   public static enum CU {
      q(16),
      RF(17),
      xK(18),
      Dw(19),
      Uv(23),
      oW(27);

      private final int gO;

      private CU(int var3) {
         this.gO = var3;
      }

      public int q() {
         return this.gO;
      }
   }

   static enum eo {
      q(4);

      final int RF;

      private eo(int var3) {
         this.RF = var3;
      }
   }
}
