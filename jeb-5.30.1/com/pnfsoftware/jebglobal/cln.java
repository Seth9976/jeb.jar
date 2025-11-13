package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.exceptions.JebRuntimeException;
import com.pnfsoftware.jeb.core.units.INativeCodeUnit;
import com.pnfsoftware.jeb.core.units.code.AddressableInstruction;
import com.pnfsoftware.jeb.core.units.code.FlowInformation;
import com.pnfsoftware.jeb.core.units.code.IFlowInformation;
import com.pnfsoftware.jeb.core.units.code.IInstruction;
import com.pnfsoftware.jeb.core.units.code.IInstructionOperand;
import com.pnfsoftware.jeb.core.units.code.asm.analyzer.BranchTarget;
import com.pnfsoftware.jeb.core.units.code.asm.cfg.BasicBlock;
import com.pnfsoftware.jeb.core.units.code.asm.cfg.CFG;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.AbstractConverter;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.IERoutineContext;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.exceptions.IllegalConversionException;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.exceptions.InstructionConversionException;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.exceptions.UnsupportedConversionException;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.EState;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.EUtil;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.IEAssign;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.IEBranchDetails;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.IECond;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.IEGeneric;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.IEImm;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.IEJumpFar;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.IEMem;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.IEStatement;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.IEUntranslatedInstruction;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.IEVar;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.OperationType;
import com.pnfsoftware.jeb.core.units.code.asm.items.INativeContinuousItem;
import com.pnfsoftware.jeb.core.units.code.asm.items.INativeInstructionItem;
import com.pnfsoftware.jeb.core.units.code.asm.items.INativeMethodItem;
import com.pnfsoftware.jeb.core.units.code.asm.processor.IRegisterBank;
import com.pnfsoftware.jeb.core.units.code.asm.processor.RegisterDescriptionEntry;
import com.pnfsoftware.jeb.core.units.code.asm.processor.arch.RegisterUtil;
import com.pnfsoftware.jeb.core.units.code.asm.type.IPrototypeItem;
import com.pnfsoftware.jeb.core.units.code.asm.type.ITypeManager;
import com.pnfsoftware.jeb.core.units.codeobject.IELFUnit;
import com.pnfsoftware.jeb.core.units.codeobject.ProcessorType;
import com.pnfsoftware.jeb.util.collect.CollectionUtil;
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
import java.util.List;

@Ser
public class cln extends AbstractConverter {
   private static final ILogger pL = GlobalLog.getLogger(cln.class);
   public static final int q = 0;
   public static final int RF = 64;
   public static final int xK = 0;
   public static final int Dw = 64;
   public static final int Uv = 128;
   public static final int oW = 192;
   public static final int gO = 256;
   public static final int nf = 320;
   public static final int gP = 384;
   public static final int za = 448;
   public static final int lm = 512;
   public static final int zz = 576;
   public static final int JY = 640;
   public static final int HF = 704;
   public static final int LK = 768;
   public static final int io = 832;
   public static final int qa = 896;
   public static final int Hk = 960;
   public static final int Me = 1024;
   public static final int PV = 1088;
   public static final int oQ = 1152;
   public static final int xW = 1216;
   public static final int KT = 1280;
   public static final int Gf = 1344;
   public static final int Ef = 1408;
   public static final int cC = 1472;
   public static final int sH = 1536;
   public static final int CE = 1600;
   public static final int wF = 1664;
   public static final int If = 1728;
   public static final int Dz = 1792;
   public static final int mI = 1856;
   public static final int jq = 1920;
   public static final int ui = 1984;
   public static final int TX = 2048;
   public static final int Rr = 2112;
   public static final int EB = 2176;
   public static final int Xo = 2176;
   public static final int Bu = 2240;
   public static final int IN = 2241;
   public static final int rL = 2288;
   public static final int eJ = 1;
   public static final int YN = 2288;
   public static final int Rv = 2289;
   public static final int zx = 2290;
   public static final int ZT = 2291;
   public static final int Ri = 2292;
   public static final int GY = 2293;
   public static final int Wx = 2294;
   public static final int AB = 2295;
   public static final int CY = 2320;
   public static final int WI = 2304;
   public static final int Tq = 64;
   public static final int Yp = 2304;
   public static final int Gu = 2368;
   public static final int nY = 2432;
   public static final int lF = 2496;
   public static final int nq = 2560;
   public static final int NX = 2624;
   public static final int br = 2688;
   public static final int tW = 2752;
   public static final int ZA = 2816;
   public static final int Ov = 2880;
   public static final int Lj = 2944;
   public static final int nv = 3008;
   public static final int LL = 3072;
   public static final int PQ = 3136;
   public static final int fQ = 3200;
   public static final int fi = 3264;
   public static final int bl = 3328;
   public static final int jb = 3392;
   public static final int pQ = 3456;
   public static final int kf = 3520;
   public static final int GM = 3584;
   public static final int TQ = 3648;
   public static final int Yw = 3712;
   public static final int IY = 3776;
   public static final int qR = 3840;
   public static final int YA = 3904;
   public static final int fw = 3968;
   public static final int Wp = 4032;
   public static final int cY = 4096;
   public static final int PY = 4160;
   public static final int cR = 4224;
   public static final int eC = 4288;
   public static final int ND = 4352;
   public static final int Qu = 4352;
   public static final int jh = 64;
   public static final int Jf = 4352;
   public static final int vC = 4416;
   public static final int of = 4480;
   public static final int os = 4544;
   public static final int iu = 4608;
   public static final int fn = 4672;
   public static final int ZU = 4736;
   public static final int Sz = 4800;
   public static final int fq = 4864;
   public static final int mJ = 4928;
   public static final int Bs = 4992;
   public static final int rV = 5056;
   public static final int WX = 5120;
   public static final int CB = 5184;
   public static final int C = 5248;
   public static final int GC = 5312;
   public static final int KF = 5376;
   public static final int rk = 5440;
   public static final int cy = 5504;
   public static final int jk = 5568;
   public static final int Cl = 5632;
   public static final int hM = 5696;
   public static final int kv = 5760;
   public static final int oS = 5824;
   public static final int FG = 5888;
   public static final int Al = 5952;
   public static final int Kn = 6016;
   public static final int vh = 6080;
   public static final int Rd = 6144;
   public static final int Eq = 6208;
   public static final int hP = 6272;
   public static final int wN = 6336;
   public static final int Uc = 6400;
   public static final int TB = 6400;
   public static final int dg = 64;
   public static final int hw = 6400;
   public static final int gm = 6464;
   public static final int uY = 6528;
   public static final int sc = 6592;
   public static final int wQ = 6656;
   public static final int Oj = 6720;
   public static final int VW = 6784;
   public static final int ap = 6848;
   public static final int RL = 6912;
   public static final int hy = 6976;
   public static final int Xi = 7040;
   public static final int Ag = 7104;
   public static final int rp = 7168;
   public static final int CW = 7232;
   public static final int qm = 7296;
   public static final int LR = 7360;
   public static final int Uz = 7424;
   public static final int dF = 7488;
   public static final int kk = 7552;
   public static final int Rc = 7616;
   public static final int jz = 7680;
   public static final int MT = 7744;
   public static final int bY = 7808;
   public static final int LS = 7872;
   public static final int fG = 7936;
   public static final int cO = 8000;
   public static final int wr = 8064;
   public static final int pe = 8128;
   public static final int Gg = 8192;
   public static final int CK = 8256;
   public static final int PW = 8320;
   public static final int zm = 8384;
   public static final int Wn = 8448;
   public static final int eG = 8448;
   public static final int Id = 64;
   public static final int Dk = 8448;
   public static final int dS = 8512;
   public static final int cb = 8576;
   public static final int BU = 8640;
   public static final int xG = 8704;
   public static final int wS = 8768;
   public static final int Oz = 8832;
   private static final Object[] aH = new Object[]{
      "$zero",
      0,
      64,
      "$zero",
      0,
      32,
      "$at",
      64,
      64,
      "$at",
      64,
      32,
      "$v0",
      128,
      64,
      "$v0",
      128,
      32,
      "$v1",
      192,
      64,
      "$v1",
      192,
      32,
      "$a0",
      256,
      64,
      "$a0",
      256,
      32,
      "$a1",
      320,
      64,
      "$a1",
      320,
      32,
      "$a2",
      384,
      64,
      "$a2",
      384,
      32,
      "$a3",
      448,
      64,
      "$a3",
      448,
      32,
      "$a4",
      512,
      64,
      "$t0",
      512,
      32,
      "$a5",
      576,
      64,
      "$t1",
      576,
      32,
      "$a6",
      640,
      64,
      "$t2",
      640,
      32,
      "$a7",
      704,
      64,
      "$t3",
      704,
      32,
      "$t4",
      768,
      64,
      "$t4",
      768,
      32,
      "$t5",
      832,
      64,
      "$t5",
      832,
      32,
      "$t6",
      896,
      64,
      "$t6",
      896,
      32,
      "$t7",
      960,
      64,
      "$t7",
      960,
      32,
      "$s0",
      1024,
      64,
      "$s0",
      1024,
      32,
      "$s1",
      1088,
      64,
      "$s1",
      1088,
      32,
      "$s2",
      1152,
      64,
      "$s2",
      1152,
      32,
      "$s3",
      1216,
      64,
      "$s3",
      1216,
      32,
      "$s4",
      1280,
      64,
      "$s4",
      1280,
      32,
      "$s5",
      1344,
      64,
      "$s5",
      1344,
      32,
      "$s6",
      1408,
      64,
      "$s6",
      1408,
      32,
      "$s7",
      1472,
      64,
      "$s7",
      1472,
      32,
      "$t8",
      1536,
      64,
      "$t8",
      1536,
      32,
      "$t9",
      1600,
      64,
      "$t9",
      1600,
      32,
      "$k0",
      1664,
      64,
      "$k0",
      1664,
      32,
      "$k1",
      1728,
      64,
      "$k1",
      1728,
      32,
      "$gp",
      1792,
      64,
      "$gp",
      1792,
      32,
      "$sp",
      1856,
      64,
      "$sp",
      1856,
      32,
      "$fp",
      1920,
      64,
      "$fp",
      1920,
      32,
      "$ra",
      1984,
      64,
      "$ra",
      1984,
      32,
      "lo",
      2048,
      64,
      "lo",
      2048,
      32,
      "hi",
      2112,
      64,
      "hi",
      2112,
      32,
      "PC",
      2176,
      64,
      "PC",
      2176,
      32,
      "LLbit",
      2240,
      1,
      "ISAMode",
      2241,
      1,
      "cc0",
      2288,
      1,
      "cc1",
      2289,
      1,
      "cc2",
      2290,
      1,
      "cc3",
      2291,
      1,
      "cc4",
      2292,
      1,
      "cc5",
      2293,
      1,
      "cc6",
      2294,
      1,
      "cc7",
      2295,
      1,
      "$f0",
      2304,
      64,
      "$f0",
      2304,
      32,
      "$f1",
      2368,
      64,
      "$f1",
      2368,
      32,
      "$f2",
      2432,
      64,
      "$f2",
      2432,
      32,
      "$f3",
      2496,
      64,
      "$f3",
      2496,
      32,
      "$f4",
      2560,
      64,
      "$f4",
      2560,
      32,
      "$f5",
      2624,
      64,
      "$f5",
      2624,
      32,
      "$f6",
      2688,
      64,
      "$f6",
      2688,
      32,
      "$f7",
      2752,
      64,
      "$f7",
      2752,
      32,
      "$f8",
      2816,
      64,
      "$f8",
      2816,
      32,
      "$f9",
      2880,
      64,
      "$f9",
      2880,
      32,
      "$f10",
      2944,
      64,
      "$f10",
      2944,
      32,
      "$f11",
      3008,
      64,
      "$f11",
      3008,
      32,
      "$f12",
      3072,
      64,
      "$f12",
      3072,
      32,
      "$f13",
      3136,
      64,
      "$f13",
      3136,
      32,
      "$f14",
      3200,
      64,
      "$f14",
      3200,
      32,
      "$f15",
      3264,
      64,
      "$f15",
      3264,
      32,
      "$f16",
      3328,
      64,
      "$f16",
      3328,
      32,
      "$f17",
      3392,
      64,
      "$f17",
      3392,
      32,
      "$f18",
      3456,
      64,
      "$f18",
      3456,
      32,
      "$f19",
      3520,
      64,
      "$f19",
      3520,
      32,
      "$f20",
      3584,
      64,
      "$f20",
      3584,
      32,
      "$f21",
      3648,
      64,
      "$f21",
      3648,
      32,
      "$f22",
      3712,
      64,
      "$f22",
      3712,
      32,
      "$f23",
      3776,
      64,
      "$f23",
      3776,
      32,
      "$f24",
      3840,
      64,
      "$f24",
      3840,
      32,
      "$f25",
      3904,
      64,
      "$f25",
      3904,
      32,
      "$f26",
      3968,
      64,
      "$f26",
      3968,
      32,
      "$f27",
      4032,
      64,
      "$f27",
      4032,
      32,
      "$f28",
      4096,
      64,
      "$f28",
      4096,
      32,
      "$f29",
      4160,
      64,
      "$f29",
      4160,
      32,
      "$f30",
      4224,
      64,
      "$f30",
      4224,
      32,
      "$f31",
      4288,
      64,
      "$f31",
      4288,
      32,
      "$cop0",
      4352,
      64,
      "$cop0",
      4352,
      32,
      "$cop1",
      4416,
      64,
      "$cop1",
      4416,
      32,
      "$cop2",
      4480,
      64,
      "$cop2",
      4480,
      32,
      "$cop3",
      4544,
      64,
      "$cop3",
      4544,
      32,
      "$cop4",
      4608,
      64,
      "$cop4",
      4608,
      32,
      "$cop5",
      4672,
      64,
      "$cop5",
      4672,
      32,
      "$cop6",
      4736,
      64,
      "$cop6",
      4736,
      32,
      "$cop7",
      4800,
      64,
      "$cop7",
      4800,
      32,
      "$cop8",
      4864,
      64,
      "$cop8",
      4864,
      32,
      "$cop9",
      4928,
      64,
      "$cop9",
      4928,
      32,
      "$cop10",
      4992,
      64,
      "$cop10",
      4992,
      32,
      "$cop11",
      5056,
      64,
      "$cop11",
      5056,
      32,
      "$cop12",
      5120,
      64,
      "$cop12",
      5120,
      32,
      "$cop13",
      5184,
      64,
      "$cop13",
      5184,
      32,
      "$cop13",
      5248,
      64,
      "$cop14",
      5248,
      32,
      "$cop15",
      5312,
      64,
      "$cop15",
      5312,
      32,
      "$cop16",
      5376,
      64,
      "$cop16",
      5376,
      32,
      "$cop17",
      5440,
      64,
      "$cop17",
      5440,
      32,
      "$cop18",
      5504,
      64,
      "$cop18",
      5504,
      32,
      "$cop19",
      5568,
      64,
      "$cop19",
      5568,
      32,
      "$cop20",
      5632,
      64,
      "$cop20",
      5632,
      32,
      "$cop21",
      5696,
      64,
      "$cop21",
      5696,
      32,
      "$cop22",
      5760,
      64,
      "$cop22",
      5760,
      32,
      "$cop23",
      5824,
      64,
      "$cop23",
      5824,
      32,
      "$cop24",
      5888,
      64,
      "$cop24",
      5888,
      32,
      "$cop25",
      5952,
      64,
      "$cop25",
      5952,
      32,
      "$cop26",
      6016,
      64,
      "$cop26",
      6016,
      32,
      "$cop27",
      6080,
      64,
      "$cop27",
      6080,
      32,
      "$cop28",
      6144,
      64,
      "$cop28",
      6144,
      32,
      "$cop29",
      6208,
      64,
      "$cop29",
      6208,
      32,
      "$cop30",
      6272,
      64,
      "$cop30",
      6272,
      32,
      "$cop31",
      6336,
      64,
      "$cop31",
      6336,
      32,
      "CPUNum",
      6400,
      64,
      "CPUNum",
      6400,
      32,
      "SYNCI_Step",
      6464,
      64,
      "SYNCI_Step",
      6464,
      32,
      "CC",
      6528,
      64,
      "CC",
      6528,
      32,
      "CCRes",
      6592,
      64,
      "CCRes",
      6592,
      32,
      "PerfCtr",
      6656,
      64,
      "PerfCtr",
      6656,
      32,
      "XNP",
      6720,
      64,
      "XNP",
      6720,
      32,
      "$hw6",
      6784,
      64,
      "$hw6",
      6784,
      32,
      "$hw7",
      6848,
      64,
      "$hw7",
      6848,
      32,
      "$hw8",
      6912,
      64,
      "$hw8",
      6912,
      32,
      "$hw9",
      6976,
      64,
      "$hw9",
      6976,
      32,
      "$hw10",
      7040,
      64,
      "$hw10",
      7040,
      32,
      "$hw11",
      7104,
      64,
      "$hw11",
      7104,
      32,
      "$hw12",
      7168,
      64,
      "$hw12",
      7168,
      32,
      "$hw13",
      7232,
      64,
      "$hw13",
      7232,
      32,
      "$hw13",
      7296,
      64,
      "$hw14",
      7296,
      32,
      "$hw15",
      7360,
      64,
      "$hw15",
      7360,
      32,
      "$hw16",
      7424,
      64,
      "$hw16",
      7424,
      32,
      "$hw17",
      7488,
      64,
      "$hw17",
      7488,
      32,
      "$hw18",
      7552,
      64,
      "$hw18",
      7552,
      32,
      "$hw19",
      7616,
      64,
      "$hw19",
      7616,
      32,
      "$hw20",
      7680,
      64,
      "$hw20",
      7680,
      32,
      "$hw21",
      7744,
      64,
      "$hw21",
      7744,
      32,
      "$hw22",
      7808,
      64,
      "$hw22",
      7808,
      32,
      "$hw23",
      7872,
      64,
      "$hw23",
      7872,
      32,
      "$hw24",
      7936,
      64,
      "$hw24",
      7936,
      32,
      "$hw25",
      8000,
      64,
      "$hw25",
      8000,
      32,
      "$hw26",
      8064,
      64,
      "$hw26",
      8064,
      32,
      "$hw27",
      8128,
      64,
      "$hw27",
      8128,
      32,
      "$hw28",
      8192,
      64,
      "$hw28",
      8192,
      32,
      "ULR",
      8256,
      64,
      "ULR",
      8256,
      32,
      "$hw30",
      8320,
      64,
      "$hw30",
      8320,
      32,
      "$hw31",
      8384,
      64,
      "$hw31",
      8384,
      32,
      "$lo1",
      8448,
      64,
      "$lo1",
      8448,
      32,
      "$lo2",
      8512,
      64,
      "$lo2",
      8512,
      32,
      "$lo3",
      8576,
      64,
      "$lo3",
      8576,
      32,
      "$hi1",
      8640,
      64,
      "$hi1",
      8640,
      32,
      "$hi2",
      8704,
      64,
      "$hi2",
      8704,
      32,
      "$hi3",
      8768,
      64,
      "$hi3",
      8768,
      32
   };
   @SerTransient
   cli yn;
   @SerTransient
   clj es;
   @SerTransient
   cll o;
   @SerTransient
   clm gl;
   @SerTransient
   clr tX;
   @SerId(6)
   int Qt;
   @SerId(7)
   IEVar JW;
   @SerId(8)
   IEVar Ub;
   @SerId(9)
   IEVar tb;
   @SerId(10)
   IEVar yW;
   @SerId(11)
   IEVar JF;
   @SerId(12)
   IEVar uz;
   @SerId(13)
   IEVar Xz;
   @SerId(14)
   IEVar iK;
   @SerId(15)
   IEVar ZE;
   @SerId(16)
   IEVar Jh;
   @SerId(17)
   IEVar iO;
   @SerId(18)
   IEVar Qe;
   @SerId(19)
   IEVar dW;
   @SerId(20)
   IEVar HK;
   @SerId(21)
   IEImm uw;
   @SerId(22)
   IEImm fe;
   @SerId(23)
   IEImm Kl;
   @SerId(26)
   IEVar So;
   @SerId(27)
   IEVar AG;
   @SerId(28)
   IEVar[] er = new IEVar[8];
   @SerId(29)
   IEVar SM;
   @SerId(30)
   IEVar bj;
   @SerId(31)
   IEVar GO;
   @SerId(32)
   int QZ;
   @SerId(33)
   clg Up;
   @SerId(200)
   ReferenceCounter HO;
   @SerId(201)
   int cv;
   @SerId(202)
   int lk;
   @SerId(203)
   int sa;
   @SerId(204)
   ReferenceCounter WJ;

   public cln(clc var1) {
      super(var1, var1.getMode());
      this.Up = var1.q();
      if (var1.getMode() != 64 && var1.q() != clg.za) {
         this.QZ = 32;
      } else {
         this.QZ = 64;
      }

      for (byte var2 = 0; var2 < aH.length; var2 += 3) {
         String var3 = (String)aH[var2];
         int var4 = (Integer)aH[var2 + 1];
         int var5 = (Integer)aH[var2 + 2];
         if ((var3.startsWith("$f") && !var3.equals("$fp") ? this.QZ == var5 : var5 <= this.regNormalBitsize) && this.gCtx.canCreateVariable(var4, var5)) {
            this.gCtx.createRegister(var4, var3, var5);
         }
      }

      this.Qt = this.regNormalBitsize;
      this.JW = this.gCtx.getVar(128);
      this.Ub = this.gCtx.getVar(192);
      this.tb = this.gCtx.getVar(256);
      this.yW = this.gCtx.getVar(320);
      this.JF = this.gCtx.getVar(384);
      this.uz = this.gCtx.getVar(448);
      this.ZE = this.gCtx.getVar(1856);
      this.iK = this.gCtx.getVar(1920);
      this.Jh = this.gCtx.getVar(1984);
      this.iO = this.gCtx.getVar(2048);
      this.Qe = this.gCtx.getVar(2112);
      this.Xz = this.gCtx.getVar(2176);

      for (int var6 = 0; var6 < 8; var6++) {
         this.er[var6] = this.gCtx.getVar(2288 + var6 * 1);
      }

      this.dW = this.gCtx.getVar(2240);
      this.GO = this.gCtx.getVar(2241);
      this.HK = this.gCtx.createVirtualRegister(65536, "tmp", 32);
      this.So = this.gCtx.createVirtualRegister(65568, "tmpJump", this.Qt);
      this.SM = this.gCtx.createVirtualRegister(65664, "tmp", 64);
      this.bj = this.gCtx.createVirtualRegister(65728, "tmp2", 64);
      this.AG = this.gCtx.createVirtualRegister(65792, "tmpJumpPredicate", 1);
      this.uw = this.gCtx.createImm(0L, 1);
      this.fe = this.gCtx.createImm(1L, 1);
      this.Kl = this.gCtx.createImm(0L, 32);
      this.q();
      this.HO = new ReferenceCounter();
      this.WJ = new ReferenceCounter();
   }

   @SerCustomInitPostGraph
   private void q() {
      this.yn = new cli(this);
      this.es = new clj(this);
      this.o = new cll(this);
      this.gl = new clm(this);
      this.tX = new clr(this);
      if (this.QZ == 0) {
         this.QZ = this.Qt;
      }

      if (this.Up == null) {
         this.Up = ((clc)this.proc).q();
      }
   }

   @Override
   public IEVar getProgramCounter() {
      return this.Xz;
   }

   @Override
   public IEVar getReturnAddressRegister() {
      return this.Jh;
   }

   @Override
   public IEVar getStackPointer() {
      return this.ZE;
   }

   @Override
   public void setCurrentContext(IERoutineContext var1) {
      super.setCurrentContext(var1);
      this.yn.RF = var1;
      this.es.RF = var1;
      this.o.RF = var1;
      this.gl.RF = var1;
      this.tX.RF = var1;
   }

   @Override
   protected BasicBlock preBlockConversion(CFG var1, BasicBlock var2, List var3) {
      if (var2.size() > 1 && ((cki)var2.get(var2.size() - 2)).RF().q() && ((cki)var2.get(var2.size() - 1)).Dw()) {
         throw new IllegalConversionException(
            Strings.ff("CTI instruction %s is not managed in delay slot for routine at %xh", var2.get(var2.size() - 1), var1.getEntryAddress())
         );
      } else {
         boolean var4 = true;
         if (((cki)var2.get(var2.size() - 1)).RF().q()) {
            var2 = var2.shallowCopy(true);
            var4 = false;
            cki var5 = (cki)var1.getInstruction(var2.getEndAddress());
            if (var5 == null) {
               throw new IllegalConversionException(
                  Strings.ff("Can not process routine at %xh because the last delay slot instruction can not be retrieved.", var1.getEntryAddress())
               );
            }

            var2.add(var5);
         }

         for (int var25 = 0; var25 < var2.size(); var25++) {
            cmd var6 = (cmd)var2.get(var25);
            String var7 = var6.getMnemonic();
            switch (var7) {
               case "SWL":
               case "SWR":
               case "LWL":
               case "LWR":
               case "LDL":
               case "LDR":
               case "SDL":
               case "SDR":
                  int var11 = var25 + 1;
                  cmd var12 = null;

                  cmd var13;
                  for (var13 = null; var11 < var2.size(); var11++) {
                     var13 = (cmd)var2.get(var11);
                     var12 = this.o.q(var6, var13, this.proc.getEndianness().isBig());
                     if (var12 != null) {
                        break;
                     }
                  }

                  if (var12 != null) {
                     if (var25 + 1 != var11) {
                        boolean var14 = false;
                        ArrayList var15 = new ArrayList();
                        ArrayList var16 = new ArrayList();
                        var6.getDefUse(var15, var16, null);

                        for (int var17 = var25 + 1; var17 < var11; var17++) {
                           cmd var18 = (cmd)var2.get(var17);
                           if (var18.RF().isPCUpdated()) {
                              if (var17 != var11 - 1 || !var18.RF().q() || var18.RF().xK()) {
                                 var14 = true;
                                 break;
                              }
                           } else {
                              ArrayList var19 = new ArrayList();
                              ArrayList var20 = new ArrayList();

                              try {
                                 var18.getDefUse(var19, var20, null);
                              } catch (Exception var24) {
                                 try {
                                    clv[] var21 = (clv[])var18.getOperands();

                                    for (int var22 = 0; var22 < var21.length; var22++) {
                                       var21[var22].q(var20);
                                    }

                                    if (!CollectionUtil.intersect(var20, var16).isEmpty() || !CollectionUtil.intersect(var20, var15).isEmpty()) {
                                       var14 = true;
                                       break;
                                    }
                                 } catch (Exception var23) {
                                    var14 = true;
                                    break;
                                 }
                              }

                              if (!CollectionUtil.intersect(var19, var16).isEmpty() || !CollectionUtil.intersect(var19, var15).isEmpty()) {
                                 var14 = true;
                                 break;
                              }

                              if (!CollectionUtil.intersect(var20, var15).isEmpty()) {
                                 var14 = true;
                                 break;
                              }

                              if (!CollectionUtil.intersect(var20, var16).isEmpty() && this.q(var6, var13)) {
                                 var14 = true;
                                 break;
                              }
                           }
                        }

                        if (var14) {
                           pL.catchingSilent(new JebRuntimeException(Strings.f("Can not merge Mips xWx at %xh", var2.getFirstAddress())));
                           continue;
                        }
                     }

                     if (var4) {
                        var2 = var2.shallowCopy(true);
                        var4 = false;
                     }

                     var2.set(var25, var12);
                     var2.set(var11, new cmg(var13, "NOP"));
                  }
            }
         }

         return var2;
      }
   }

   private boolean q(cmd var1, cmd var2) {
      if (!var2.gO() || ((clv[])var1.getOperands()).length != 2 || ((clv[])var2.getOperands()).length != 2) {
         return false;
      } else if (!this.q(var1) && !this.q(var2)) {
         clv var3 = ((clv[])var1.getOperands())[1];
         clv var4 = ((clv[])var2.getOperands())[1];
         if (var3.getOperandType() == 7 && var4.getOperandType() == 7) {
            if (var3.getOperands().length >= 2 && var4.getOperands().length >= 2 && var3.getOperands()[1].equals(var4.getOperands()[1])) {
               clv var5 = (clv)var3.getOperands()[0];
               clv var6 = (clv)var4.getOperands()[0];
               if (var3.getOperandType() == 1 && var4.getOperandType() == 1) {
                  String var7 = var2.getMnemonic();
                  int var8 = !var7.startsWith("SW") && !var7.startsWith("SH") && !var7.startsWith("SEH") && !var7.startsWith("SB") && !var7.startsWith("SEB")
                     ? 8
                     : 4;
                  return Math.abs(var5.getOperandValue() - var6.getOperandValue()) < var8;
               } else {
                  return false;
               }
            } else {
               return false;
            }
         } else {
            return false;
         }
      } else {
         return false;
      }
   }

   private boolean q(cmd var1) {
      return ((clv[])var1.getOperands())[1].getOperandType() == 7 && ((clv[])var1.getOperands())[1].getOperands().length == 2
         ? ((clv)((clv[])var1.getOperands())[1].getOperands()[0]).getOperandValue() == 0L
            && ((clv[])var1.getOperands())[0].getOperandValue() == ((clv)((clv[])var1.getOperands())[1].getOperands()[1]).getOperandValue()
         : true;
   }

   @Override
   protected void convertBlock(BasicBlock var1, List var2) {
      long var3 = var1.getFirstAddress();
      long var5 = var3;
      ArrayList var7 = new ArrayList();
      ArrayList var8 = new ArrayList();
      int var9 = 0;
      Long var10 = null;

      for (boolean var11 = false; var9 < var1.size(); var9++) {
         this.cv++;
         cmd var12 = (cmd)var1.get(var9);
         String var13 = var12.getMnemonic();
         var7.clear();
         if (cmd.q(var13)) {
            this.tX.q(var12, var7, var8, var5);
            if (var13.equals("BC1TL") || var13.equals("BC1FL")) {
               var11 = true;
            }
         } else {
            if (var13.startsWith("B") && var13.endsWith("C") && var13.length() > 3 && !var13.equals("BALC")) {
               var13 = "B**C";
            }

            switch (var13) {
               case "ADD":
               case "ADDI":
               case "ADDIU":
               case "ADDU":
                  this.yn.q(var12, var7, OperationType.ADD, true);
                  break;
               case "AND":
               case "ANDI":
                  this.yn.q(var12, var7, OperationType.AND, false, this.Qt);
                  break;
               case "CLO":
                  this.yn.q(var12, var7);
                  break;
               case "CLZ":
                  this.yn.RF(var12, var7);
                  break;
               case "DIV":
                  this.yn.q(var12, var7, true);
                  break;
               case "DIVU":
                  this.yn.q(var12, var7, false);
                  break;
               case "EXT":
                  this.yn.q(var12, var7, 32);
                  break;
               case "INS":
                  this.yn.xK(var12, var7);
                  break;
               case "LUI":
                  this.yn.Dw(var12, var7);
                  break;
               case "MOD":
                  this.yn.RF(var12, var7, true);
                  break;
               case "MODU":
                  this.yn.RF(var12, var7, false);
                  break;
               case "MOVN":
                  this.yn.RF(var12, var7, OperationType.LOG_NEQ);
                  break;
               case "MOVZ":
                  this.yn.RF(var12, var7, OperationType.LOG_EQ);
                  break;
               case "MUL":
                  this.yn.Dw(var12, var7, true);
                  break;
               case "MULU":
                  this.yn.Dw(var12, var7, false);
                  break;
               case "MULT":
                  this.yn.xK(var12, var7, true);
                  break;
               case "MULTU":
                  this.yn.xK(var12, var7, false);
                  break;
               case "MUH":
                  this.yn.Uv(var12, var7, true);
                  break;
               case "MUHU":
                  this.yn.Uv(var12, var7, false);
                  break;
               case "MADD":
                  this.yn.oW(var12, var7, true);
                  break;
               case "MADDU":
                  this.yn.oW(var12, var7, false);
                  break;
               case "MSUB":
                  this.yn.gO(var12, var7, true);
                  break;
               case "MSUBU":
                  this.yn.gO(var12, var7, false);
                  break;
               case "NOR":
                  this.yn.Uv(var12, var7);
                  break;
               case "OR":
               case "ORI":
                  this.yn.q(var12, var7, OperationType.OR, false, this.Qt);
                  break;
               case "SEB":
                  this.yn.RF(var12, var7, 8);
                  break;
               case "SEH":
                  this.yn.RF(var12, var7, 16);
                  break;
               case "SUB":
               case "SUBU":
                  this.yn.q(var12, var7, OperationType.SUB, false);
                  break;
               case "XOR":
               case "XORI":
                  this.yn.q(var12, var7, OperationType.XOR, false, this.Qt);
                  break;
               case "WSBH":
                  this.yn.gO(var12, var7);
                  break;
               case "ROTR":
               case "ROTRV":
                  this.yn.q(var12, var7, OperationType.ROR, 32);
                  break;
               case "SLL":
               case "SLLV":
                  this.yn.q(var12, var7, OperationType.SHL, 32);
                  break;
               case "SRL":
               case "SRLV":
                  this.yn.q(var12, var7, OperationType.SHR, 32);
                  break;
               case "SRA":
               case "SRAV":
                  this.yn.q(var12, var7, OperationType.SAR, 32);
                  break;
               case "LI":
               case "MOVE":
                  this.yn.q(var12, var7, var5);
                  break;
               case "NEG":
               case "NEGU":
                  this.yn.oW(var12, var7);
                  break;
               case "NOT":
                  this.yn.q(var12, var7, OperationType.NOT);
                  break;
               case "B":
               case "BAL":
               case "J":
               case "JR":
               case "JAL":
                  if (this.q(var12, var8, var7, var5, var13)) {
                     this.es.q(var12, var8, var5);
                  }
                  break;
               case "JALR":
                  if (this.q(var12, var8, var7, var5, var13)) {
                     this.es.RF(var12, var8, var5);
                  }
                  break;
               case "JIALC":
               case "JALRC":
               case "JIC":
               case "JRC":
                  if (this.q(var12, var8, var7, var5, var13)) {
                     this.es.xK(var12, var7, var5);
                  }
                  break;
               case "NAL":
                  this.es.Dw(var12, var7, var5);
                  break;
               case "JALX":
                  if (this.q(var12, var8, var7, var5, var13)) {
                     this.es.oW(var12, var8, var5);
                  }
                  break;
               case "BEQ":
               case "BEQZ":
               case "BGEZ":
               case "BGEZAL":
               case "BGTZ":
               case "BLEZ":
               case "BLTZ":
               case "BLTZAL":
               case "BNE":
               case "BNEZ":
                  if (this.q(var12, var8, var7, var5, var13)) {
                     this.es.q(var12, var8, var5, 8);
                  }
                  break;
               case "B**C":
                  if (this.q(var12, var8, var7, var5, var13)) {
                     this.es.q(var12, var7, var5, 4);
                  }
                  break;
               case "BEQL":
               case "BGEZALL":
               case "BGEZL":
               case "BGTZL":
               case "BLEZL":
               case "BLTZL":
               case "BLTZALL":
               case "BNEL":
                  if (this.q(var12, var8, var7, var5, var13)) {
                     this.es.q(var12, var8, var5, 8);
                  }

                  var11 = true;
                  break;
               case "LB":
                  this.o.RF(var12, var7, 8);
                  break;
               case "LBU":
                  this.o.xK(var12, var7, 8);
                  break;
               case "LH":
                  this.o.RF(var12, var7, 16);
                  break;
               case "LHU":
                  this.o.xK(var12, var7, 16);
                  break;
               case "LW":
                  this.o.RF(var12, var7, 32);
                  break;
               case "LD":
                  this.o.xK(var12, var7, 64);
                  break;
               case "LWL":
                  this.o.q(var12, var7, 4, this.proc.getEndianness().isBig(), var2.size());
                  break;
               case "LWR":
                  this.o.RF(var12, var7, 4, this.proc.getEndianness().isBig(), var2.size());
                  break;
               case "LWPC":
                  this.o.q(var12, var7, var5, true);
                  break;
               case "SB":
                  this.o.q(var12, var7, 8);
                  break;
               case "SH":
                  this.o.q(var12, var7, 16);
                  break;
               case "SW":
                  this.o.q(var12, var7, 32);
                  break;
               case "SD":
                  this.o.q(var12, var7, 64);
                  break;
               case "SWL":
                  this.o.xK(var12, var7, 4, this.proc.getEndianness().isBig(), var2.size());
                  break;
               case "SWR":
                  this.o.Dw(var12, var7, 4, this.proc.getEndianness().isBig(), var2.size());
                  break;
               case "SC":
                  this.o.q(var12, var7);
                  break;
               case "LL":
                  this.o.RF(var12, var7);
                  break;
               case "MFHI":
                  this.o.q(var12, var7, false);
                  break;
               case "MFLO":
                  this.o.q(var12, var7, true);
                  break;
               case "MTHI":
                  this.o.RF(var12, var7, false);
                  break;
               case "MTLO":
                  this.o.RF(var12, var7, true);
                  break;
               case "SELEQZ":
                  this.gl.q(var12, var7);
                  break;
               case "SELNEZ":
                  this.gl.RF(var12, var7);
                  break;
               case "SLT":
                  this.gl.q(var12, var7, true);
                  break;
               case "SLTI":
                  this.gl.RF(var12, var7, true);
                  break;
               case "SLTIU":
                  this.gl.RF(var12, var7, false);
                  break;
               case "SLTU":
                  this.gl.q(var12, var7, false);
                  break;
               case "PREF":
               case "NOP":
               case "SSNOP":
               case "SYNC":
                  if (!this.doNotGenerateNops) {
                     var7.add(this.ctx.createNop());
                  }
                  break;
               case "TEQ":
                  if ((var9 <= 0 || !this.RF(var12, (cmd)var1.get(var9 - 1))) && (var9 + 1 >= var1.size() || !this.RF(var12, (cmd)var1.get(var9 + 1)))) {
                     var7.add(this.q(var12, var5));
                  }
                  break;
               case "TEQI":
               case "TGE":
               case "TGEI":
               case "TGEIU":
               case "TGEU":
               case "TLT":
               case "TLTI":
               case "TLTIU":
               case "TLTU":
               case "TNE":
               case "TNEI":
                  var7.add(this.q(var12, var5));
                  break;
               case "AUI":
                  this.yn.gP(var12, var7);
                  break;
               case "AUIPC":
                  this.yn.xK(var12, var7, var5);
                  break;
               case "LAPC":
               case "ADDIUPC":
                  this.yn.q(var12, var7, var5);
                  break;
               case "ALUIPC":
                  this.yn.Dw(var12, var7, var5);
                  break;
               case "BC":
               case "BALC":
                  this.es.q(var12, var7, var5);
                  break;
               case "LSA":
                  this.yn.nf(var12, var7);
                  break;
               case "ALIGN":
                  this.yn.Uv(var12, var7, var5);
                  break;
               case "BITSWAP":
                  this.yn.za(var12, var7);
                  break;
               case "BREAK":
               case "RDHWR":
               case "UDI":
                  var7.add(this.q(var12, var5));
                  break;
               case "SYSCALL":
                  IEUntranslatedInstruction var22 = this.q(var12, var5, this.JW);
                  var22.addSideEffectUsedVariable(this.tb, this.yW, this.JF, this.uz);
                  var22.addSideEffectDefinedVariable(this.JW, this.uz);
                  var7.add(var22);
                  break;
               case "DADD":
               case "DADDI":
               case "DADDIU":
               case "DADDU":
                  this.yn.q(var12, var7, OperationType.ADD, true, var5);
                  break;
               case "DSLL":
                  this.yn.q(var12, var7, var5, OperationType.SHL, 0);
                  break;
               case "DSLL32":
                  this.yn.q(var12, var7, var5, OperationType.SHL, 32);
                  break;
               case "DSRL":
                  this.yn.q(var12, var7, var5, OperationType.SHR, 0);
                  break;
               case "DSRL32":
                  this.yn.q(var12, var7, var5, OperationType.SHR, 32);
                  break;
               case "DEXT":
                  this.yn.RF(var12, var7, var5);
                  break;
               case "LDL":
                  this.o.q(var12, var7, 8, this.proc.getEndianness().isBig(), var2.size());
                  break;
               case "LDR":
                  this.o.RF(var12, var7, 8, this.proc.getEndianness().isBig(), var2.size());
                  break;
               case "SDL":
                  this.o.xK(var12, var7, 8, this.proc.getEndianness().isBig(), var2.size());
                  break;
               case "SDR":
                  this.o.Dw(var12, var7, 8, this.proc.getEndianness().isBig(), var2.size());
                  break;
               case "LWU":
                  this.o.xK(var12, var7, 32);
                  break;
               case "LWUPC":
                  this.o.q(var12, var7, var5, false);
                  break;
               case "MOVF":
               case "MOVT":
               case "DALIGN":
               case "DAUI":
               case "DAHI":
               case "DATI":
               case "DBITSWAP":
               case "DCLZ":
               case "DCLO":
               case "DSUB":
               case "DSUBU":
               case "DNEG":
               case "DNEGU":
               case "DSLLV":
               case "DSRLV":
               case "DSRAV":
               case "DLSA":
               case "DSRA":
               case "DSRA32":
               case "DROTR":
               case "DROTR32":
               case "DROTRV":
               case "DMULT":
               case "DMULTU":
               case "DDIV":
               case "DDIVU":
               case "DMOD":
               case "DMODU":
               case "DMUH":
               case "DMUL":
               case "DMUHU":
               case "DMULU":
               case "LDPC":
               case "LLD":
               case "LLDX":
               case "SCD":
               case "SCDX":
               case "DEXTM":
               case "DEXTU":
               case "DINS":
               case "DINSM":
               case "DINSU":
               case "DSBH":
               case "DSHD":
               case "LBE":
               case "LBUE":
               case "LHE":
               case "LHUE":
               case "LLE":
               case "LLX":
               case "LLXE":
               case "LWE":
               case "LWLE":
               case "LWRE":
               case "PREFE":
               case "SBE":
               case "SCE":
               case "SCXE":
               case "SHE":
               case "SWE":
               case "SWLE":
               case "SWRE":
               case "PREFX":
               case "SCX":
               case "CACHE":
               case "CACHEE":
               case "PAUSE":
               case "SDBBP":
               case "SIGRIE":
               case "SYNCI":
               case "TLBINV":
               case "TLBINVF":
               case "TLBP":
               case "TLBR":
               case "TLBWI":
               case "TLBWR":
               case "WAIT":
               case "ADDSC":
               case "ADDWC":
               case "APPEND":
               case "BALIGN":
               case "BITREV":
               case "BPOSGE32":
               case "BPOSGE32C":
               case "BPOSGE64":
               case "DAPPEND":
               case "DBALIGN":
               case "DEXTP":
               case "DEXTPDP":
               case "DEXTPDPV":
               case "DEXTPV":
               case "DINSV":
               case "DMADD":
               case "DMADDU":
               case "DMSUB":
               case "DMSUBU":
               case "DMTHLIP":
               case "DSHILO":
               case "DSHILOV":
               case "EXTP":
               case "EXTPDP":
               case "EXTPDPV":
               case "EXTPV":
               case "INSV":
               case "LDX":
               case "MODSUB":
               case "MTHLIP":
               case "PREPEND":
               case "PREPENDD":
               case "PREPENDW":
               case "SHILO":
               case "SHILOV":
                  var7.add(this.q(var12, var5));
                  break;
               case "RDDSP":
               case "RDPGPR":
                  IEVar var21 = this.RF(((clv[])var12.getOperands())[0].getOperandValue());
                  IEUntranslatedInstruction var28 = this.q(var12, var5, var21);
                  var28.addSideEffectDefinedVariable(var21);
                  var7.add(var28);
                  break;
               case "WRDSP":
               case "WRPGPR":
                  IEVar var20 = this.RF(((clv[])var12.getOperands())[var13.equals("WRPGPR") ? 1 : 0].getOperandValue());
                  IEUntranslatedInstruction var27 = this.q(var12, var5, var20);
                  var27.addSideEffectDefinedVariable(var20);
                  var7.add(var27);
                  break;
               case "DERET":
                  var7.add(this.ctx.createReturn());
                  break;
               case "ERET":
                  var7.add(this.ctx.createAssign(this.dW, this.uw));
                  var7.add(this.ctx.createReturn());
                  break;
               case "ERETNC":
                  var7.add(this.ctx.createReturn());
                  break;
               case "EHB":
                  var7.add(this.q(var12, var5));
                  break;
               case "DI":
               case "EI":
                  IEUntranslatedInstruction var16;
                  if (var12.getOperands() != null && ((clv[])var12.getOperands()).length != 0) {
                     if (((clv[])var12.getOperands())[0].getOperandType() != 0 || ((clv[])var12.getOperands()).length > 1) {
                        throw new IllegalConversionException("Invalid DI/EI instruction: " + var12);
                     }

                     IEVar[] var17 = new IEVar[]{this.RF(((clv[])var12.getOperands())[0].getOperandValue())};
                     var16 = this.q(var12, var5, var17);
                     var16.addSideEffectDefinedVariable(var17);
                  } else {
                     var16 = this.q(var12, var5);
                  }

                  var7.add(var16);
                  break;
               case "LBUX":
                  this.o.xK(var12, var7, 8);
                  break;
               case "LHX":
                  this.o.xK(var12, var7, 16);
                  break;
               case "LWX":
                  this.o.xK(var12, var7, 32);
                  break;
               default:
                  this.lk++;
                  if (this.HO != null) {
                     this.HO.inc(var13);
                  }

                  throw new UnsupportedConversionException("Cannot convert instruction: " + var12);
            }
         }

         this.q(var8, var7, var12, var5);
         if (!var8.isEmpty() && var10 == null) {
            var10 = var5;
         }

         if (!var8.isEmpty() && var10 != null && var10 != var5) {
            IEStatement var14 = (IEStatement)var8.get(var8.size() - 1);
            IEAssign var19 = null;
            if (var14 instanceof IEAssign) {
               var19 = (IEAssign)var14;
            }

            if (var11) {
               EUtil.setLowerLevelAddress(var10, var8);
            } else {
               EUtil.setLowerLevelAddress(var10, var7);
               EUtil.setLowerLevelAddress(var10, var8);
            }

            for (int var23 = 0; var23 < var8.size() - 1; var23++) {
               var2.add((IEStatement)var8.get(var23));
            }

            if (var19 != null && var11) {
               if (!(var19.getSrcOperand() instanceof IECond)) {
                  throw new IllegalConversionException("Likely branch must be conditional");
               }

               IECond var24 = (IECond)var19.getSrcOperand();
               IEAssign var29 = this.ctx
                  .createBranchAssign(this.Xz, this.ctx.createCond(var24.getCondition(), this.q(var10, 1), var24.getExpressionFalse()), false);
               var2.add(var29);
               var29.copyLowerLevelAddresses(var19);
               IEAssign var18 = this.ctx.createBranchAssign(this.Xz, var24.getExpressionTrue(), var19.isRoutineCall());
               var18.copyLowerLevelAddresses(var19);
               var8.set(var8.size() - 1, var18);
               EUtil.setLowerLevelAddress(var10 + 1L, var7);
            }

            if (!var7.isEmpty()) {
               for (IEStatement var30 : var7) {
                  if (this.q(var30)) {
                     var2.add(this.q(var12, var13, var5));
                  } else {
                     var2.add(var30);
                  }
               }
            }

            if (var19 != null && !var11 && var19.getSrcOperand() instanceof IECond) {
               IECond var26 = (IECond)var19.getSrcOperand();
               IEAssign var31 = this.ctx
                  .createBranchAssign(this.Xz, this.ctx.createCond(var26.getCondition(), var26.getExpressionTrue(), this.q(var10, 1)), false);
               var31.copyLowerLevelAddresses(var19);
               var2.add(var31);
               var8.set(
                  var8.size() - 1, this.ctx.createBranchAssign(this.Xz, var26.getExpressionFalse(), var19.isRoutineCall()).withLowerLevelAddress(var10 + 1L)
               );
            }

            var11 = false;
            var2.add((IEStatement)var8.get(var8.size() - 1));
            var8.clear();
         }

         if (var8.isEmpty()) {
            if (var10 == null) {
               EUtil.setLowerLevelAddress(var5, var7);
               var2.addAll(var7);
            }

            var10 = null;
         }

         var5 += var12.getSize();
      }
   }

   private boolean RF(cmd var1, cmd var2) {
      return var2.getMnemonic().startsWith("DIV") && ((clv[])var1.getOperands())[0] == ((clv[])var2.getOperands())[1] && this.q(((clv[])var1.getOperands())[1]);
   }

   private boolean q(clv var1) {
      return var1.getOperandType() == 0
         && RegisterUtil.getRegisterGroup(var1.getOperandValue()) == 0
         && RegisterUtil.getRegisterIndex(var1.getOperandValue()) == 0;
   }

   boolean q(cmd var1, List var2, List var3, long var4, String var6) {
      if (!var2.isEmpty()) {
         var3.add(this.q(var1, var6, var4));
         return false;
      } else {
         return true;
      }
   }

   private IEStatement q(cmd var1, String var2, long var3) {
      return this.ctx.createUntranslatedInstruction(var3, "SIGILL: " + var2, this.q(var1, var3, false));
   }

   IEUntranslatedInstruction q(cmd var1, long var2) {
      String var4 = var1.getMnemonic();
      this.sa++;
      if (this.WJ != null) {
         this.WJ.inc(var4);
      }

      amv var5 = (amv)this.ctx.createUntranslatedInstruction(var2, var4);
      var5.setBreakingFlow(var1.getBreakingFlow(var2));
      var5.setRoutineCall(var1.getRoutineCall(var2));
      return var5;
   }

   private void q(List var1, List var2, cmd var3, long var4) {
      if (var2.size() == 1 && var2.get(0) instanceof IEUntranslatedInstruction && var3.RF().q()) {
         IEUntranslatedInstruction var6 = (IEUntranslatedInstruction)var2.remove(0);
         if (this.q(var3, var1, var2, var4, var3.getMnemonic())) {
            var1.add(var6);
            if (var6.getBreakingFlow(var4) != FlowInformation.NONE) {
               var6.setBreakingFlow(this.q(var6.getBreakingFlow(var4)));
            }

            if (var6.getRoutineCall(var4) != FlowInformation.NONE) {
               var6.setRoutineCall(this.q(var6.getRoutineCall(var4)));
            }
         }
      }
   }

   private IFlowInformation q(IFlowInformation var1) {
      FlowInformation var2 = new FlowInformation(var1.mustComputeFallThrough(), 0);
      var2.addTargets(var1.getTargets());
      return var2;
   }

   IEUntranslatedInstruction q(cmd var1, long var2, IEGeneric... var4) {
      String var5 = var1.getMnemonic();
      if (!var5.equals("SYSCALL")) {
         this.sa++;
         if (this.WJ != null) {
            this.WJ.inc(var5);
         }
      }

      amv var6 = (amv)this.ctx.createUntranslatedInstruction(var2, var5, var4 == null ? this.q(var1, var2, false) : var4);
      var6.setBreakingFlow(var1.getBreakingFlow(var2));
      var6.setRoutineCall(var1.getRoutineCall(var2));
      return var6;
   }

   IEGeneric[] q(cmd var1, long var2, boolean var4) {
      if (var1.getOperands() == null) {
         return null;
      } else {
         ArrayList var5 = new ArrayList();

         for (int var6 = 0; var6 < ((clv[])var1.getOperands()).length; var6++) {
            clv var7 = ((clv[])var1.getOperands())[var6];
            if (var7.getOperandType() == 0 && RegisterUtil.getRegisterGroup(var7.getOperandValue()) == 6) {
               int var10 = RegisterUtil.getRegisterIndex(var7.getOperandValue());
               var5.add(this.Dw(var10));
               var5.add(this.Uv(var10));
            } else {
               try {
                  IEGeneric var8 = this.q(var1, (IInstructionOperand)var7, var2);
                  if (var8 != null) {
                     var5.add(var8);
                  }
               } catch (InstructionConversionException var9) {
                  if (var4) {
                     throw var9;
                  }

                  pL.catchingSilent(var9);
               }
            }
         }

         return (IEGeneric[])var5.toArray(new IEGeneric[var5.size()]);
      }
   }

   private boolean q(IEStatement var1) {
      if (!(var1 instanceof IEAssign)) {
         return false;
      } else {
         IEGeneric var2 = ((IEAssign)var1).getDstOperand();
         return var2 == this.Xz;
      }
   }

   @Override
   public IEBranchDetails getDefaultBranchToRoutineSideEffects(INativeMethodItem var1) {
      ArrayList var2 = Lists.createArrayList();
      Lists.addNonNulls(var2, this.tb, this.yW, this.JF, this.uz, this.ZE);
      ArrayList var3 = Lists.createArrayList();
      Lists.addNonNulls(var3, this.JW);
      ArrayList var4 = Lists.createArrayList();
      Lists.addNonNulls(var4, this.Ub);
      return this.gCtx.createBranchDetails(var3, var2, var4, 0);
   }

   public IEGeneric q(long var1) {
      return this.q(var1, 0);
   }

   public IEGeneric q(long var1, int var3) {
      return this.ctx.createImm(var1 + var3, this.Qt);
   }

   public int q(cmd var1, clv var2, long var3) {
      return (int)this.RF(var1, var2, var3);
   }

   public long RF(cmd var1, clv var2, long var3) {
      switch (var2.getOperandType()) {
         case 1:
         case 2:
         case 3:
            return var2.q(var3, var1.getProcessorMode(), null);
         default:
            throw new IllegalConversionException("NaN parameter");
      }
   }

   public IEGeneric q(cmd var1, IInstructionOperand var2) {
      return this.q(var1, (clv)var2, 0L, 0);
   }

   public IEGeneric q(cmd var1, IInstructionOperand var2, long var3) {
      return this.q(var1, (clv)var2, var3, 0);
   }

   public IEGeneric q(cmd var1, clv var2, long var3, int var5) {
      switch (var2.getOperandType()) {
         case 0:
            return this.RF(var2.getOperandValue());
         case 1:
            return this.ctx.createImm(var2.getOperandValue(), var5 == 0 ? var2.getOperandBitsize() : var5);
         case 2:
         case 3:
            return this.ctx.createImm(var2.getOperandValue(var3), this.Qt);
         case 4:
         case 5:
         case 6:
         default:
            return null;
         case 7:
            return var2.getOperands().length == 1 ? this.q(var1, (clv)var2.getOperands()[0], var3, var5) : this.q(var1, var2);
      }
   }

   public IEGeneric q(cmd var1, clv var2) {
      return this.xK(var1, var2, 0L);
   }

   public IEGeneric xK(cmd var1, clv var2, long var3) {
      if (var2.getOperandType() == 7) {
         IInstructionOperand[] var5 = var2.getOperands();
         IEGeneric var6 = this.q(var1, var5[1], var3);
         IEGeneric var7 = this.q(var1, (clv)var5[0], var3, this.Qt);
         return this.ctx.createOperation(OperationType.ADD, var7, var6);
      } else {
         throw new IllegalConversionException("Expected an operand list");
      }
   }

   public IEMem q(cmd var1, clv var2, int var3) {
      IEGeneric var4 = this.xK(var1, var2, 0L);
      return this.gCtx.createMem(var4, var3);
   }

   private IRegisterBank RF() {
      return RegisterUtil.getBank(this.regNormalBitsize == 64 ? ProcessorType.MIPS64 : ProcessorType.MIPS);
   }

   IEVar RF(long var1) {
      int var3 = RegisterUtil.getRegisterGroup(var1);
      int var4 = RegisterUtil.getRegisterIndex(var1);
      RegisterDescriptionEntry var5 = this.RF().getDescriptionEntryById(var1);
      String var6 = var5.getName();
      switch (var3) {
         case 0:
            return this.gCtx.createRegister(0 + var4 * 64, var6, this.regNormalBitsize);
         case 1:
         case 6:
         case 7:
         case 8:
         case 9:
         default:
            throw new UnsupportedConversionException("Register type not converted yet: " + var1);
         case 2:
            return this.gCtx.createRegister(2288 + var4 * 1, var6, 1);
         case 3:
            return this.gCtx.createRegister(2304 + var4 * 64, var6, this.QZ);
         case 4:
            return this.gCtx.createRegister(4352 + var4 * 64, var6, this.regNormalBitsize);
         case 5:
            return this.gCtx.createRegister(6400 + var4 * 64, var6, this.regNormalBitsize);
         case 10:
            if (var4 == 0) {
               return this.Xz;
            } else {
               throw new IllegalConversionException("Can not access lo and hi filter");
            }
      }
   }

   @Override
   public boolean resolveCustomCalls(IERoutineContext var1) {
      if (!(var1.getNativeContext() instanceof INativeCodeUnit var3)) {
         return false;
      } else if (!(var3.getCodeObjectContainer() instanceof IELFUnit)) {
         return false;
      } else {
         ITypeManager var4 = var3.getTypeManager();
         int var5 = 0;

         for (BasicBlock var7 : var1.getCfg()) {
            for (AddressableInstruction var9 : var7.addressableInstructions()) {
               IEStatement var10 = (IEStatement)var9.getInstruction();
               if (var10 instanceof IEUntranslatedInstruction var11) {
                  INativeContinuousItem var12 = var3.getNativeItemAt(var11.getNativeAddress());
                  if (var12 instanceof INativeInstructionItem) {
                     IInstruction var13 = ((INativeInstructionItem)var12).getInstruction();
                     if (var13 instanceof cki && Strings.equalsIgnoreCase(var13.getMnemonic(), "syscall")) {
                        IEGeneric var14 = (IEGeneric)var11.getOperands()[0];
                        if (var14 instanceof IEImm && ((IEImm)var14).canReadAsLong()) {
                           int var15 = (int)((IEImm)var14).getValueAsLong();
                           Object[] var10000 = new Object[]{var15};
                           Long var16 = var10.getPrimaryLowerLevelAddress();
                           if (var16 != null) {
                              cle var17 = cle.q();
                              String var18 = var17.q(var15);
                              if (var18 != null) {
                                 var10000 = new Object[]{var18};
                                 IPrototypeItem var19 = var17.q(var15, var4, true);
                                 if (var19 != null) {
                                    String var20 = "sys_" + var18;
                                    INativeMethodItem var21 = var3.getMethod(var20);
                                    if (var21 == null) {
                                       var21 = var3.createMethodReference(var20, var19, null);
                                    }

                                    var3.getCodeAnalyzer().recordDynamicBranchTarget(var16, true, new BranchTarget(var21), false);
                                    var5++;
                                 }
                              }
                           }
                        }
                     }
                  }
               }
            }
         }

         return var5 > 0;
      }
   }

   @Override
   protected void postBlockConversion(CFG var1, BasicBlock var2, List var3, int var4) {
      super.postBlockConversion(var1, var2, var3, var4);
      if (var2.outsize() == 0 && !var2.isEmpty()) {
         AddressableInstruction var5 = var2.getLast2();
         cki var6 = (cki)var5.getInstruction();
         long var7 = var5.getOffset();
         boolean var9 = !var6.getBreakingFlow(var7).isBroken() || var6.getRoutineCall(var7).isBroken();
         if (var9) {
            IEJumpFar var10 = this.ctx.createJumpFar(this.ctx.createImm(var7 + var6.getSize(), this.getAddressBitsize()));
            var3.add(var10);
            if (!var6.RF().isPCUpdated() && var2.size() >= 2) {
               AddressableInstruction var11 = var2.get2(var2.size() - 2);
               if (((cki)var11.getInstruction()).RF().isPCUpdated()) {
                  var10.setLowerLevelAddress(var11.getOffset());
               } else {
                  var10.setLowerLevelAddress(var7);
               }
            } else {
               var10.setLowerLevelAddress(var7);
            }
         }
      }
   }

   @Override
   public void customInitStateRegisters(EState var1, Long var2) {
      var1.removeValue(1792);
      if (var2 != null) {
         var1.setValue(1600, var2);
      }
   }

   public static int q(int var0) {
      return 0 + var0 * 64;
   }

   IEImm RF(int var1) {
      return EUtil.one(var1);
   }

   IEImm xK(int var1) {
      return EUtil.zero(var1);
   }

   public IEVar Dw(int var1) {
      if (var1 == 0) {
         return this.iO;
      } else {
         IEVar var2 = this.gCtx.getVar(8448 + (var1 - 1) * 64);
         return var2 != null ? var2 : null;
      }
   }

   public IEVar Uv(int var1) {
      if (var1 == 0) {
         return this.Qe;
      } else {
         IEVar var2 = this.gCtx.getVar(8640 + (var1 - 1) * 64);
         return var2 != null ? var2 : null;
      }
   }

   IEVar oW(int var1) {
      switch (var1) {
         case 32:
            return this.HK;
         case 64:
            return this.SM;
         default:
            throw new IllegalConversionException("no tmp variable available with size: " + var1);
      }
   }

   @Override
   public String formatStatistics() {
      StringBuilder var1 = new StringBuilder(super.formatStatistics());
      var1.append("\n\n");
      Strings.ff(var1, "Converted instructions: %d (incl. failed=%d, untranslated=%d)\n", this.cv, this.lk, this.sa);
      if (this.HO != null && !this.HO.isEmpty()) {
         var1.append("Failed instructions: ").append(this.HO.formatTopReferences(-1));
      }

      if (this.WJ != null && !this.WJ.isEmpty()) {
         var1.append("Untranslated instructions: ").append(this.WJ.formatTopReferences(-1));
      }

      return var1.toString();
   }
}
