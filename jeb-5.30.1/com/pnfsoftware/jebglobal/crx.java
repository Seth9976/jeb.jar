package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.client.Licensing;
import com.pnfsoftware.jeb.client.S;
import com.pnfsoftware.jeb.core.JebCoreService;
import com.pnfsoftware.jeb.core.units.code.asm.cfg.BasicBlock;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.AbstractConverter;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ConverterInstructionEntry;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.IERoutineContext;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.exceptions.UnsupportedConversionException;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.ELocation;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.EState;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.EUtil;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.IEAssign;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.IEBranchDetails;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.IECond;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.IEGeneric;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.IEImm;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.IEMem;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.IEOperation;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.IESlice;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.IEUntranslatedInstruction;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.IEVar;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.OperationType;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.SimulationPointInformation;
import com.pnfsoftware.jeb.core.units.code.asm.items.INativeMethodItem;
import com.pnfsoftware.jeb.core.units.code.asm.memory.MemoryException;
import com.pnfsoftware.jeb.util.base.Assert;
import com.pnfsoftware.jeb.util.collect.Lists;
import com.pnfsoftware.jeb.util.collect.Maps;
import com.pnfsoftware.jeb.util.collect.ReferenceCounter;
import com.pnfsoftware.jeb.util.encoding.Conversion;
import com.pnfsoftware.jeb.util.format.Formatter;
import com.pnfsoftware.jeb.util.format.Strings;
import com.pnfsoftware.jeb.util.logging.GlobalLog;
import com.pnfsoftware.jeb.util.logging.ILogger;
import com.pnfsoftware.jeb.util.serialization.annotations.Ser;
import com.pnfsoftware.jeb.util.serialization.annotations.SerCustomInitPostGraph;
import com.pnfsoftware.jeb.util.serialization.annotations.SerId;
import com.pnfsoftware.jeb.util.serialization.annotations.SerTransient;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

@Ser
public class crx extends AbstractConverter {
   private static final ILogger QZ = GlobalLog.getLogger(crx.class);
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
   public static final int PV = 1024;
   public static final int oQ = 1024;
   public static final int xW = 1026;
   public static final int KT = 1028;
   public static final int Gf = 1030;
   public static final int Ef = 1031;
   public static final int cC = 1032;
   public static final int sH = 1033;
   public static final int CE = 1034;
   public static final int wF = 1035;
   public static final int If = 1036;
   public static final int Dz = 1038;
   public static final int mI = 1040;
   public static final int jq = 1041;
   public static final int ui = 1042;
   public static final int TX = 1043;
   public static final int Rr = 1044;
   public static final int EB = 1045;
   public static final int Xo = 1088;
   public static final int Bu = 1280;
   public static final int IN = 32;
   public static final int rL = 1280;
   public static final int eJ = 1312;
   public static final int YN = 1344;
   public static final int Rv = 1376;
   public static final int zx = 1408;
   public static final int ZT = 1440;
   public static final int Ri = 1472;
   public static final int GY = 1536;
   public static final int Wx = 1536;
   private static final int Up = 128;
   public static final int AB = 1536;
   public static final int CY = 1664;
   public static final int WI = 1792;
   public static final int Tq = 1920;
   public static final int Yp = 2048;
   public static final int Gu = 2176;
   public static final int nY = 2304;
   public static final int lF = 2432;
   public static final int nq = 2560;
   public static final int NX = 2576;
   public static final int br = 2592;
   public static final int tW = 2608;
   public static final int ZA = 2656;
   public static final int Ov = 2704;
   public static final int Lj = 1536;
   public static final int nv = 1664;
   public static final int LL = 1792;
   public static final int PQ = 1920;
   public static final int fQ = 2048;
   public static final int fi = 2176;
   public static final int bl = 2304;
   public static final int jb = 2432;
   public static final int pQ = 4096;
   public static final int kf = 512;
   public static final int GM = 20480;
   private static final Object[] HO = new Object[]{
      "rax",
      0,
      64,
      "eax",
      0,
      32,
      "ax",
      0,
      16,
      "al",
      0,
      8,
      "ah",
      8,
      8,
      "rcx",
      64,
      64,
      "ecx",
      64,
      32,
      "cx",
      64,
      16,
      "cl",
      64,
      8,
      "ch",
      72,
      8,
      "rdx",
      128,
      64,
      "edx",
      128,
      32,
      "dx",
      128,
      16,
      "dl",
      128,
      8,
      "dh",
      136,
      8,
      "rbx",
      192,
      64,
      "ebx",
      192,
      32,
      "bx",
      192,
      16,
      "bl",
      192,
      8,
      "bh",
      200,
      8,
      "rsp",
      256,
      64,
      "esp",
      256,
      32,
      "sp",
      256,
      16,
      "spl",
      256,
      8,
      "rbp",
      320,
      64,
      "ebp",
      320,
      32,
      "bp",
      320,
      16,
      "bpl",
      320,
      8,
      "rsi",
      384,
      64,
      "esi",
      384,
      32,
      "si",
      384,
      16,
      "sil",
      384,
      8,
      "rdi",
      448,
      64,
      "edi",
      448,
      32,
      "di",
      448,
      16,
      "dil",
      448,
      8,
      "r8",
      512,
      64,
      "r8d",
      512,
      32,
      "r8w",
      512,
      16,
      "r8b",
      512,
      8,
      "r9",
      576,
      64,
      "r9d",
      576,
      32,
      "r9w",
      576,
      16,
      "r9b",
      576,
      8,
      "r10",
      640,
      64,
      "r10d",
      640,
      32,
      "r10w",
      640,
      16,
      "r10b",
      640,
      8,
      "r11",
      704,
      64,
      "r11d",
      704,
      32,
      "r11w",
      704,
      16,
      "r11b",
      704,
      8,
      "r12",
      768,
      64,
      "r12d",
      768,
      32,
      "r12w",
      768,
      16,
      "r12b",
      768,
      8,
      "r13",
      832,
      64,
      "r13d",
      832,
      32,
      "r13w",
      832,
      16,
      "r13b",
      832,
      8,
      "r14",
      896,
      64,
      "r14d",
      896,
      32,
      "r14w",
      896,
      16,
      "r14b",
      896,
      8,
      "r15",
      960,
      64,
      "r15d",
      960,
      32,
      "r15w",
      960,
      16,
      "r15b",
      960,
      8,
      "cf",
      1024,
      1,
      "_f1",
      1025,
      1,
      "pf",
      1026,
      1,
      "_f3",
      1027,
      1,
      "af",
      1028,
      1,
      "_f5",
      1029,
      1,
      "zf",
      1030,
      1,
      "sf",
      1031,
      1,
      "tf",
      1032,
      1,
      "if",
      1033,
      1,
      "df",
      1034,
      1,
      "of",
      1035,
      1,
      "iopl",
      1036,
      2,
      "nt",
      1038,
      1,
      "_f15",
      1039,
      1,
      "rf",
      1040,
      1,
      "vm",
      1041,
      1,
      "ac",
      1042,
      1,
      "vif",
      1043,
      1,
      "vip",
      1044,
      1,
      "id",
      1045,
      1,
      "rflags",
      1024,
      64,
      "eflags",
      1024,
      32,
      "flags",
      1024,
      16,
      "rip",
      1088,
      64,
      "eip",
      1088,
      32,
      "ip",
      1088,
      16,
      "es",
      1280,
      16,
      "cs",
      1312,
      16,
      "ss",
      1344,
      16,
      "ds",
      1376,
      16,
      "fs",
      1408,
      16,
      "gs",
      1440,
      16
   };
   public static boolean TQ = true;
   private static final double cv = 0.0;
   private static final double lk = 1.0;
   private static final double sa = Math.PI;
   private static final double WJ = 1.0 / Math.log10(2.0);
   private static final double pL = 1.0 / Math.log(2.0);
   private static final double aH = Math.log10(2.0);
   private static final double yc = Math.log(2.0);
   @SerTransient
   crp Yw;
   @SerTransient
   crr IY;
   @SerTransient
   crs qR;
   @SerTransient
   crq YA;
   @SerTransient
   crt fw;
   @SerTransient
   cru Wp;
   @SerTransient
   crv cY;
   @SerTransient
   cro PY;
   @SerTransient
   crw cR;
   @SerId(7)
   final int eC;
   @SerId(8)
   final IEVar ND;
   @SerId(9)
   final IEVar Qu;
   @SerId(10)
   final IEVar jh;
   @SerId(11)
   final IEVar Jf;
   @SerId(12)
   final IEVar vC;
   @SerId(13)
   final IEVar of;
   @SerId(15)
   final IEVar os;
   @SerId(16)
   final IEVar iu;
   @SerId(17)
   final IEVar fn;
   @SerId(18)
   final IEVar ZU;
   @SerId(19)
   final IEVar Sz;
   @SerId(20)
   final IEVar fq;
   @SerId(21)
   final IEVar mJ;
   @SerId(22)
   final IEVar Bs;
   @SerId(23)
   final IEVar rV;
   @SerId(24)
   final IEVar WX;
   @SerId(25)
   final IEVar CB;
   @SerId(26)
   final IEVar C;
   @SerId(27)
   final IEVar GC;
   @SerId(28)
   final IEVar KF;
   @SerId(29)
   final IEVar rk;
   @SerId(30)
   final IEVar cy;
   @SerId(31)
   final IEVar jk;
   @SerId(32)
   final IEVar Cl;
   @SerId(33)
   final IEVar hM;
   @SerId(34)
   final IEVar kv;
   @SerId(35)
   final IEVar oS;
   @SerId(36)
   final IEVar FG;
   @SerId(37)
   final IEVar Al;
   @SerId(38)
   final IEVar Kn;
   @SerId(39)
   final IEVar vh;
   @SerId(40)
   final IEVar Rd;
   @SerId(41)
   private final IEVar eb;
   @SerId(42)
   final IEImm Eq;
   @SerId(43)
   final IEImm hP;
   @SerId(44)
   final IEImm wN;
   @SerId(45)
   final IEImm Uc;
   @SerId(46)
   final IEImm TB;
   @SerId(47)
   final IEImm dg;
   @SerId(48)
   final IEImm hw;
   @SerId(49)
   final IEImm gm;
   @SerId(50)
   final IEImm uY;
   @SerId(51)
   final IEImm sc;
   @SerId(52)
   final IEImm wQ;
   @SerId(53)
   final IEImm Oj;
   @SerId(54)
   final IEImm VW;
   @SerId(55)
   final IEImm ap;
   @SerId(56)
   final IEImm RL;
   @SerId(57)
   final IEImm hy;
   @SerId(58)
   final IEImm Xi;
   @SerId(59)
   final IEGeneric Ag;
   @SerId(60)
   final IEGeneric rp;
   @SerId(61)
   final IEGeneric CW;
   @SerId(62)
   final IEGeneric qm;
   @SerId(63)
   final IEGeneric LR;
   @SerId(64)
   final IEGeneric Uz;
   @SerId(65)
   final IEGeneric dF;
   @SerId(66)
   final IEGeneric kk;
   @SerId(67)
   final IEGeneric Rc;
   @SerId(68)
   final IEGeneric jz;
   @SerId(69)
   final IEGeneric MT;
   @SerId(70)
   final IEGeneric bY;
   @SerId(76)
   final IEVar LS;
   @SerId(77)
   final IEVar[] fG;
   @SerId(78)
   final als cO;
   @SerId(79)
   final IEVar[] wr;
   @SerId(80)
   final als pe;
   @SerId(81)
   final IEVar Gg;
   @SerId(82)
   final IEVar CK;
   @SerId(83)
   final IEVar PW;
   @SerId(84)
   final IEVar zm;
   @SerId(85)
   final IEVar Wn;
   @SerId(86)
   final IEVar eG;
   @SerId(87)
   final IEVar Id;
   @SerId(88)
   final IEVar Dk;
   @SerId(89)
   final IEVar dS;
   @SerId(90)
   final IEVar cb;
   @SerId(91)
   final IEVar BU;
   @SerId(92)
   final IEVar xG;
   @SerId(93)
   final IEVar wS;
   @SerId(94)
   final IEVar Oz;
   @SerId(95)
   final IEVar yn;
   @SerId(96)
   final IEVar es;
   @SerId(97)
   final IEVar o;
   @SerId(98)
   final IEVar gl;
   @SerId(99)
   final IEVar tX;
   @SerId(100)
   final IEVar Qt;
   @SerId(101)
   final IEVar JW;
   @SerId(102)
   final IEVar Ub;
   @SerId(103)
   final IEVar tb;
   @SerId(104)
   final IEVar yW;
   @SerId(105)
   final IEVar JF;
   @SerId(106)
   final IEVar uz;
   @SerId(110)
   final IEGeneric[] Xz;
   @SerId(128)
   final IEVar iK;
   @SerId(120)
   private final IEVar zj;
   @SerId(200)
   ReferenceCounter ZE;
   @SerId(201)
   int Jh;
   @SerId(202)
   int iO;
   @SerId(203)
   int Qe;
   @SerId(204)
   final IEVar dW;
   @SerId(205)
   final IEVar HK;
   @SerId(206)
   final IEVar uw;
   @SerId(207)
   final IEVar fe;
   @SerId(208)
   final IEVar Kl;
   @SerId(209)
   final IEVar So;
   @SerId(210)
   final IEVar[] AG;
   @SerId(211)
   final IEVar[] er;
   @SerId(212)
   final IEVar[] SM;
   @SerId(213)
   final IEVar[] bj;
   @SerTransient
   boolean GO;
   private static final int aV = 1;
   private static final int Qo = 2;
   private static final int lN = 4;
   private static final int gT = 8;
   private static final int qr = 16;
   private static final int IJ = 32;
   private static final int Of = 63;
   private static final int AN = 256;
   @SerTransient
   private int RW;
   @SerTransient
   private int YR;
   @SerTransient
   private int fN;
   @SerTransient
   private Set GH;
   @SerTransient
   private Integer BY;

   public crx(cti var1) {
      super(var1, var1.getMode());

      for (byte var2 = 0; var2 < HO.length; var2 += 3) {
         String var3 = (String)HO[var2];
         int var4 = (Integer)HO[var2 + 1];
         int var5 = (Integer)HO[var2 + 2];
         if (this.regNormalBitsize >= var5 && (this.regNormalBitsize >= 64 || var4 < 512 || var4 > 960) && this.gCtx.canCreateVariable(var4, var5)) {
            this.gCtx.createRegister(var4, var3, var5);
         }
      }

      this.eC = 16;
      this.os = this.gCtx.getVar(0);
      this.iu = this.gCtx.getVar(64);
      this.fn = this.gCtx.getVar(128);
      this.ZU = this.gCtx.getVar(192);
      this.Sz = this.gCtx.getVar(256);
      this.fq = this.gCtx.getVar(320);
      this.mJ = this.gCtx.getVar(384);
      this.Bs = this.gCtx.getVar(448);
      this.ND = this.gCtx.getVar(1280);
      this.Qu = this.gCtx.getVar(1312);
      this.jh = this.gCtx.getVar(1344);
      this.Jf = this.gCtx.getVar(1376);
      this.vC = this.gCtx.getVar(1408);
      this.of = this.gCtx.getVar(1440);
      this.rV = this.gCtx.getVar(1088);
      this.Ag = this.os.part(8);
      this.rp = this.os.slice(8, 16);
      this.CW = this.os.part(16);
      this.qm = this.iu.part(8);
      this.LR = this.iu.slice(8, 16);
      this.Uz = this.iu.part(16);
      this.dF = this.fn.part(8);
      this.kk = this.fn.slice(8, 16);
      this.Rc = this.fn.part(16);
      this.jz = this.ZU.part(8);
      this.MT = this.ZU.slice(8, 16);
      this.bY = this.ZU.part(16);
      this.WX = this.gCtx.getVar(1024);
      this.CB = this.gCtx.getVar(1026);
      this.C = this.gCtx.getVar(1028);
      this.GC = this.gCtx.getVar(1030);
      this.KF = this.gCtx.getVar(1031);
      this.rk = this.gCtx.getVar(1032);
      this.cy = this.gCtx.getVar(1033);
      this.jk = this.gCtx.getVar(1034);
      this.Cl = this.gCtx.getVar(1035);
      this.hM = this.gCtx.getVar(1036);
      this.kv = this.gCtx.getVar(1038);
      this.oS = this.gCtx.getVar(1040);
      this.FG = this.gCtx.getVar(1041);
      this.Al = this.gCtx.getVar(1042);
      this.Kn = this.gCtx.getVar(1043);
      this.vh = this.gCtx.getVar(1044);
      this.Rd = this.gCtx.getVar(1045);
      this.eb = this.gCtx.createVirtualRegister(65536, "tmp", this.regNormalBitsize);
      this.zj = this.gCtx.createVirtualRegister("tmp2x", 2 * this.regNormalBitsize);
      this.dW = this.gCtx.createVirtualRegister("tmp1", 1);
      this.HK = this.gCtx.createVirtualRegister("tmp8", 8);
      this.uw = this.gCtx.createVirtualRegister("tmp16", 16);
      this.fe = this.gCtx.createVirtualRegister("tmp32", 32);
      this.Kl = this.gCtx.createVirtualRegister("tmp64", 64);
      this.So = this.gCtx.createVirtualRegister("tmp128", 128);
      this.AG = new IEVar[16];
      this.AG[0] = this.HK;

      for (int var6 = 1; var6 < this.AG.length; var6++) {
         this.AG[var6] = this.gCtx.createVirtualRegister("tmp8_" + var6, 8);
      }

      this.er = new IEVar[16];
      this.er[0] = this.uw;

      for (int var7 = 1; var7 < this.er.length; var7++) {
         this.er[var7] = this.gCtx.createVirtualRegister("tmp16_" + var7, 16);
      }

      this.SM = new IEVar[8];
      this.SM[0] = this.fe;

      for (int var8 = 1; var8 < this.SM.length; var8++) {
         this.SM[var8] = this.gCtx.createVirtualRegister("tmp32_" + var8, 32);
      }

      this.bj = new IEVar[8];
      this.bj[0] = this.Kl;

      for (int var9 = 1; var9 < this.bj.length; var9++) {
         this.bj[var9] = this.gCtx.createVirtualRegister("tmp64_" + var9, 64);
      }

      this.Eq = this.gCtx.createImm(0L, 1);
      this.hP = this.gCtx.createImm(1L, 1);
      this.wN = this.gCtx.createImm(0L, this.regNormalBitsize);
      this.Uc = this.gCtx.createImm(1L, this.regNormalBitsize);
      this.TB = this.gCtx.createImm(-1L, this.regNormalBitsize);
      this.dg = this.gCtx.createImm(0L, 8);
      this.hw = this.gCtx.createImm(1L, 8);
      this.gm = this.gCtx.createImm(-1L, 8);
      this.uY = this.gCtx.createImm(0L, 16);
      this.sc = this.gCtx.createImm(1L, 16);
      this.wQ = this.gCtx.createImm(-1L, 16);
      this.Oj = this.gCtx.createImm(0L, 32);
      this.VW = this.gCtx.createImm(1L, 32);
      this.ap = this.gCtx.createImm(-1L, 32);
      this.RL = this.gCtx.createImm(0L, 64);
      this.hy = this.gCtx.createImm(1L, 64);
      this.Xi = this.gCtx.createImm(-1L, 64);
      this.LS = this.gCtx.createVirtualRegister(73728, "fpuTmp", 80);
      this.fG = new IEVar[8];

      for (int var10 = 0; var10 < 8; var10++) {
         this.fG[var10] = this.gCtx.createRegister(1536 + var10 * 80, "st" + var10, 80);
      }

      this.cO = new als("st", 80, this.fG);
      this.wr = new IEVar[8];

      for (int var11 = 0; var11 < 8; var11++) {
         this.wr[var11] = this.gCtx.createRegister(2592 + var11 * 2, "tw" + var11, 2);
      }

      this.pe = new als("tw", 2, this.wr);
      this.CK = this.gCtx.createRegister(2576, "swIE", 1);
      this.PW = this.gCtx.createRegister(2577, "swDE", 1);
      this.zm = this.gCtx.createRegister(2578, "swZE", 1);
      this.Wn = this.gCtx.createRegister(2579, "swOE", 1);
      this.eG = this.gCtx.createRegister(2580, "swUE", 1);
      this.Id = this.gCtx.createRegister(2581, "swPE", 1);
      this.Dk = this.gCtx.createRegister(2582, "swSF", 1);
      this.dS = this.gCtx.createRegister(2583, "swES", 1);
      this.cb = this.gCtx.createRegister(2584, "swC0", 1);
      this.BU = this.gCtx.createRegister(2585, "swC1", 1);
      this.xG = this.gCtx.createRegister(2586, "swC2", 1);
      this.Gg = this.gCtx.createRegister(2587, "swTop", 3);
      this.wS = this.gCtx.createRegister(2590, "swC3", 1);
      this.Oz = this.gCtx.createRegister(2591, "swB", 1);
      this.yn = this.gCtx.createRegister(2560, "cwIM", 1);
      this.es = this.gCtx.createRegister(2561, "cwDM", 1);
      this.o = this.gCtx.createRegister(2562, "cwZM", 1);
      this.gl = this.gCtx.createRegister(2563, "cwOM", 1);
      this.tX = this.gCtx.createRegister(2564, "cwUM", 1);
      this.Qt = this.gCtx.createRegister(2565, "cwPM", 1);
      this.JW = this.gCtx.createRegister(2568, "cwPC", 2);
      this.Ub = this.gCtx.createRegister(2570, "cwRC", 2);
      this.tb = this.gCtx.createRegister(2572, "cwX", 1);
      this.yW = this.gCtx.createRegister(2608, "fpuLastInsn", 48);
      this.JF = this.gCtx.createRegister(2656, "fpuLastData", 48);
      this.uz = this.gCtx.createRegister(2704, "fpuOpcode", 11);
      this.iK = this.gCtx.createRegister(20480, "mxcsr", 32);
      if (var1.getMode() != 32 && var1.getMode() != 64) {
         this.Xz = new IEGeneric[0];
      } else {
         byte var12 = 32;
         this.Xz = new IEGeneric[var12];

         for (int var13 = 0; var13 < var12; var13++) {
            this.Xz[var13] = this.gCtx.createRegister(4096 + var13 * 512, Strings.f("xmm%d", var13), 128);
         }
      }

      this.q();
      this.ZE = new ReferenceCounter();
   }

   public IEVar q(int var1) {
      return (IEVar)this.RF(var1, null);
   }

   public IEVar[] RF(int var1) {
      switch (var1) {
         case 8:
            return this.AG;
         case 16:
            return this.er;
         case 32:
            return this.SM;
         case 64:
            return this.bj;
         default:
            throw new RuntimeException();
      }
   }

   public IEVar q(int var1, List var2) {
      if (var1 < this.regNormalBitsize) {
         var2.add(this.ctx.createAssign(this.eb, EUtil.zero(this.regNormalBitsize)));
      }

      return this.eb;
   }

   public IEGeneric RF(int var1, List var2) {
      if (var1 == this.regNormalBitsize) {
         return this.eb;
      } else if (var1 == 2 * this.regNormalBitsize) {
         return this.zj;
      } else {
         switch (var1) {
            case 1:
               return this.dW;
            case 8:
               return this.HK;
            case 16:
               return this.uw;
            case 32:
               return this.fe;
            case 64:
               return this.Kl;
            case 128:
               return this.So;
            default:
               if (var2 == null) {
                  throw new RuntimeException();
               } else {
                  IEVar var3;
                  if (var1 < this.regNormalBitsize) {
                     var3 = this.eb;
                  } else {
                     if (var1 >= 2 * this.regNormalBitsize) {
                        throw new RuntimeException();
                     }

                     var3 = this.zj;
                  }

                  var2.add(this.ctx.createAssign(var3, EUtil.zero(var3.getBitsize())));
                  return var3.part(var1);
               }
         }
      }
   }

   IEVar xK(int var1) {
      switch (var1) {
         case 1:
            return this.dW;
         case 8:
            return this.HK;
         case 16:
            return this.uw;
         case 32:
            return this.fe;
         case 64:
            return this.Kl;
         default:
            throw new RuntimeException();
      }
   }

   IEVar Dw(int var1) {
      switch (var1) {
         case 8:
            return this.AG[1];
         case 16:
            return this.er[1];
         case 32:
            return this.SM[1];
         case 64:
            return this.bj[1];
         default:
            throw new RuntimeException();
      }
   }

   IEVar Uv(int var1) {
      switch (var1) {
         case 8:
            return this.AG[2];
         case 16:
            return this.er[2];
         case 32:
            return this.SM[2];
         case 64:
            return this.bj[2];
         default:
            throw new RuntimeException();
      }
   }

   @Override
   public IEGeneric getRegister(String var1, ELocation var2) {
      if (var1.equals("st(0)")) {
         int var3 = -1;
         if (var2 != null) {
            try {
               Integer var4 = (Integer)((Map)var2.getRoutineContext().getData("x87_TOP_values_post"))
                  .get(var2.getRoutineContext().convertIntermediateOffset((int)var2.getStatementOffset()));
               if (var4 != null) {
                  var3 = var4;
               }
            } catch (Exception var5) {
            }
         }

         if (var3 < 0) {
            return this.gCtx.createGroupElt(this.cO, this.Gg);
         }

         var1 = "st" + var3;
      }

      return super.getRegister(var1, var2);
   }

   @Override
   public IEGeneric getRegisterVariableFromNativeRegisterId(long var1, ELocation var3) {
      if (var1 >= 0L && var1 <= (this.proc.getMode() == 64 ? 15 : 7)) {
         int var4 = 0 + (int)var1 * 64;
         IEVar var5 = this.gCtx.getVar(var4);
         if (var5 != null) {
            return var5;
         }
      }

      return super.getRegisterVariableFromNativeRegisterId(var1, var3);
   }

   @Override
   public long getNativeRegisterIdFromRegisterVariable(IEVar var1, boolean var2) {
      int var3 = var1.getId();
      return var2 && var3 >= 0 && var3 < 1024 ? (var3 - 0) / 64 : super.getNativeRegisterIdFromRegisterVariable(var1, var2);
   }

   @Override
   public String getSlicedRegisterName(String var1, int var2, int var3) {
      if (var2 == 0 && Strings.startsWith(var1, "xmm", "ymm", "zmm")) {
         String var4 = null;
         if (var3 == 32) {
            var4 = "fps";
         } else if (var3 == 64) {
            var4 = "fpd";
         }

         if (var4 != null) {
            int var5 = Conversion.stringToInt(var1.substring(3), -1);
            if (var5 >= 0) {
               return var4 + var5;
            }
         }
      }

      return super.getSlicedRegisterName(var1, var2, var3);
   }

   @SerCustomInitPostGraph
   private void q() {
      this.GO = true;
      this.Yw = new crp(this);
      this.IY = new crr(this);
      this.qR = new crs(this);
      this.YA = new crq(this);
      this.fw = new crt(this);
      this.Wp = new cru(this);
      this.cY = new crv(this);
      this.PY = new cro(this);
      this.cR = new crw(this);
      this.RW = this.getProgramCounter().getId();
      this.YR = this.getStackPointer().getId();
      this.fN = this.gCtx.getAddressBitsize() / 8;
      this.GH = new HashSet();
   }

   public IEGeneric q(IEGeneric var1) {
      switch (var1.getBitsize()) {
         case 1:
            return this.Eq;
         case 8:
            return this.dg;
         case 16:
            return this.uY;
         case 32:
            return this.Oj;
         default:
            return this.RL.part(var1.getBitsize());
      }
   }

   public IEGeneric RF(IEGeneric var1) {
      switch (var1.getBitsize()) {
         case 1:
            return this.hP;
         case 8:
            return this.hw;
         case 16:
            return this.sc;
         case 32:
            return this.VW;
         default:
            return this.hy.part(var1.getBitsize());
      }
   }

   public IEGeneric xK(IEGeneric var1) {
      switch (var1.getBitsize()) {
         case 1:
            return this.hP;
         case 8:
            return this.gm;
         case 16:
            return this.wQ;
         case 32:
            return this.ap;
         default:
            return this.Xi.part(var1.getBitsize());
      }
   }

   @Override
   public IEMem createStackMemoryAccess(IEGeneric var1, int var2) {
      return this.gCtx.createMem(this.jh, var1, var2);
   }

   public IEMem q(int var1, IEGeneric var2, int var3) {
      if (var1 >= 0 && var1 <= 5) {
         IEVar var4 = this.gCtx.getVar(1280 + var1 * 32);
         if (var4 == null) {
            throw new RuntimeException();
         } else {
            return this.gCtx.createMem(var4, var2, var3);
         }
      } else {
         throw new RuntimeException();
      }
   }

   public void q(ConverterInstructionEntry var1) {
      this.IY.q(var1, false, false);
   }

   @Override
   public IEVar getProgramCounter() {
      return this.rV;
   }

   @Override
   public IEVar getStackPointer() {
      return this.Sz;
   }

   @Override
   public void setCurrentContext(IERoutineContext var1) {
      super.setCurrentContext(var1);
      this.Yw.RF = var1;
      this.IY.RF = var1;
      this.qR.RF = var1;
      this.YA.RF = var1;
      this.fw.RF = var1;
      this.Wp.RF = var1;
      this.cY.RF = var1;
      this.PY.RF = var1;
      this.cR.RF = var1;
   }

   @Override
   protected void convertBlock(BasicBlock var1, List var2) {
      long var3 = var1.getFirstAddress();
      long var5 = var3;
      ArrayList var7 = new ArrayList();
      ConverterInstructionEntry var8 = new ConverterInstructionEntry();
      var8.r = var7;
      ctc var9 = null;

      try {
         int var10 = 0;

         for (int var11 = var1.size(); var10 < var11; var10++) {
            var9 = (ctc)var1.get(var10);
            int var12 = var2.size();
            var8.insn = var9;
            var8.address = var5;
            var8.irAddress = var12;
            var7.clear();
            var8.r = var7;
            var8.ph1Insn = var10 < var11 - 1 ? (ctc)var1.get(var10 + 1) : null;
            this.convertInstruction(var8);
            EUtil.setLowerLevelAddress(var5, var7);
            var2.addAll(var7);
            var5 += var9.getSize();
         }
      } catch (Throwable var16) {
         QZ.error(S.L("Error: Instruction cannot be converted: %s %s"), Formatter.byteArrayToHex(var9.getCode()), var9.format(var5));
         QZ.catchingSilent(var16);
         throw var16;
      } finally {
         ;
      }
   }

   @Override
   public void convertInstruction(ConverterInstructionEntry var1) {
      this.Jh++;

      try {
         if (!this.attemptConversionByExtension(var1) && !this.q(var1, null, true)) {
            this.RF(var1);
            this.Qe++;
            UnsupportedConversionException var2 = new UnsupportedConversionException("Cannot convert instruction: " + var1.insn);
            if (Licensing.isDebugBuild()) {
               throw var2;
            }

            aeb.q(var2);
         }
      } catch (RuntimeException var4) {
         if (Licensing.isDebugBuild()) {
            throw var4;
         }

         String var3 = Strings.ff("%s @ 0x%X: %s", ((ctc)var1.insn).getMnemonic(), var1.address, Formatter.byteArrayToHex(((ctc)var1.insn).getCode()));
         JebCoreService.notifySilentExceptionToClient(var4, Maps.toMap("instruction", var3));
         this.iO++;
         if (this.ZE != null) {
            this.ZE.inc(((ctc)var1.insn).getMnemonic());
         }

         this.RF(var1);
      }
   }

   private int q(String var1) {
      switch (var1) {
         case "add":
         case "sub":
         case "neg":
         case "cmp":
         case "test":
         case "mul":
         case "imul":
         case "div":
         case "idiv":
         case "or":
         case "xor":
         case "and":
            return 63;
         case "adc":
         case "sbb":
            return 319;
         case "inc":
         case "dec":
            return 62;
         default:
            return -1;
      }
   }

   private boolean q(ConverterInstructionEntry var1, String var2, boolean var3) {
      ctc var4 = (ctc)var1.insn;
      if (var2 == null) {
         var2 = var4.getMnemonic();
      }

      boolean var5 = false;
      if (TQ && var1.ph1Insn != null) {
         int var6 = this.q(var2);
         if (var6 >= 0) {
            String var7 = ((ctc)var1.ph1Insn).getMnemonic();
            int var8 = this.q(var7);
            if (var8 >= 0 && (var8 >> 8 & var6) == 0 && (~(var8 & 0xFF) & var6 & 0xFF) == 0) {
               var5 = true;
            }
         }
      }

      switch (var2) {
         case "nop":
         case "wait":
         case "endbr32":
         case "endbr64":
            if (!this.doNotGenerateNops) {
               this.fw.q(var1);
            }
            break;
         case "add":
            this.Yw.q(var1, var5);
            break;
         case "sub":
            this.Yw.xK(var1, var5);
            break;
         case "adc":
            this.Yw.RF(var1, var5);
            break;
         case "sbb":
            this.Yw.Dw(var1, var5);
            break;
         case "inc":
            this.Yw.oW(var1, var5);
            break;
         case "dec":
            this.Yw.gO(var1, var5);
            break;
         case "neg":
            this.Yw.nf(var1, var5);
            break;
         case "cmp":
            this.Yw.Uv(var1, var5);
            break;
         case "mul":
            this.Yw.gP(var1, var5);
            break;
         case "imul":
            this.Yw.za(var1, var5);
            break;
         case "div":
            this.Yw.lm(var1, var5);
            break;
         case "idiv":
            this.Yw.zz(var1, var5);
            break;
         case "xadd":
            this.Yw.q(var1);
            break;
         case "cmpxchg":
            this.Yw.RF(var1);
            break;
         case "aaa":
            this.Yw.xK(var1);
            break;
         case "aas":
            this.Yw.Dw(var1);
            break;
         case "aam":
            this.Yw.Uv(var1);
            break;
         case "aad":
            this.Yw.oW(var1);
            break;
         case "daa":
            this.Yw.gO(var1);
            break;
         case "das":
            this.Yw.nf(var1);
            break;
         case "sal":
         case "shl":
            this.Wp.q(var1);
            break;
         case "shr":
            this.Wp.RF(var1);
            break;
         case "sar":
            this.Wp.xK(var1);
            break;
         case "rol":
            this.Wp.Dw(var1);
            break;
         case "ror":
            this.Wp.oW(var1);
            break;
         case "rcl":
            this.Wp.Uv(var1);
            break;
         case "rcr":
            this.Wp.gO(var1);
            break;
         case "shld":
            this.Wp.nf(var1);
            break;
         case "shrd":
            this.Wp.gP(var1);
            break;
         case "or":
            this.YA.RF(var1, var5);
            break;
         case "xor":
            this.YA.xK(var1, var5);
            break;
         case "and":
            this.YA.q(var1, var5);
            break;
         case "not":
            this.YA.q(var1);
            break;
         case "test":
            this.YA.Dw(var1, var5);
            break;
         case "mov":
            this.qR.q(var1);
            break;
         case "lea":
            this.qR.RF(var1);
            break;
         case "movsx":
         case "movsxd":
            this.qR.xK(var1);
            break;
         case "movzx":
            this.qR.Dw(var1);
            break;
         case "xchg":
            this.qR.Uv(var1);
            break;
         case "cwd":
            this.qR.oW(var1);
            break;
         case "cdq":
            this.qR.gO(var1);
            break;
         case "cqo":
            this.qR.nf(var1);
            break;
         case "cbw":
            this.qR.gP(var1);
            break;
         case "cwde":
            this.qR.za(var1);
            break;
         case "cdqe":
            this.qR.lm(var1);
            break;
         case "clc":
            this.qR.zz(var1);
            break;
         case "stc":
            this.qR.JY(var1);
            break;
         case "cli":
            this.qR.io(var1);
            break;
         case "sti":
            this.qR.qa(var1);
            break;
         case "cld":
            this.qR.HF(var1);
            break;
         case "std":
            this.qR.LK(var1);
            break;
         case "cmc":
            this.qR.Hk(var1);
            break;
         case "movsb":
         case "movsw":
         case "movsq":
         case "movs":
            this.qR.Me(var1);
            break;
         case "stosb":
         case "stosw":
         case "stosd":
         case "stosq":
         case "stos":
            this.qR.oQ(var1);
            break;
         case "lodsb":
         case "lodsw":
         case "lodsd":
         case "lodsq":
         case "lods":
            this.qR.PV(var1);
            break;
         case "cmpsb":
         case "cmpsw":
         case "cmpsd":
         case "cmpsq":
         case "cmps":
            this.qR.xW(var1);
            break;
         case "scasb":
         case "scasw":
         case "scasd":
         case "scasq":
         case "scas":
            this.qR.KT(var1);
            break;
         case "bsf":
            this.qR.Gf(var1);
            break;
         case "bsr":
            this.qR.Ef(var1);
            break;
         case "bswap":
            this.qR.cC(var1);
            break;
         case "seto":
         case "setno":
         case "setb":
         case "setnb":
         case "setz":
         case "setnz":
         case "setbe":
         case "setnbe":
         case "sets":
         case "setns":
         case "setp":
         case "setnp":
         case "setl":
         case "setnl":
         case "setle":
         case "setnle":
            this.qR.sH(var1);
            break;
         case "cmovo":
         case "cmovno":
         case "cmovb":
         case "cmovnb":
         case "cmovz":
         case "cmovnz":
         case "cmovbe":
         case "cmovnbe":
         case "cmovs":
         case "cmovns":
         case "cmovp":
         case "cmovnp":
         case "cmovl":
         case "cmovnl":
         case "cmovle":
         case "cmovnle":
            this.qR.CE(var1);
            break;
         case "jcxz":
         case "jecxz":
         case "jrcxz":
         case "jo":
         case "jno":
         case "jb":
         case "jnb":
         case "jz":
         case "jnz":
         case "jbe":
         case "jnbe":
         case "js":
         case "jns":
         case "jp":
         case "jnp":
         case "jl":
         case "jnl":
         case "jle":
         case "jnle":
            this.IY.q(var1);
            break;
         case "loop":
         case "loope":
         case "loopne":
            this.IY.RF(var1);
            break;
         case "jmp":
            this.IY.xK(var1);
            break;
         case "call":
            this.IY.Dw(var1);
            break;
         case "ret":
            this.IY.Uv(var1);
            break;
         case "push":
            this.cY.q(var1);
            break;
         case "pop":
            this.cY.RF(var1);
            break;
         case "pusha":
         case "pushad":
            this.cY.xK(var1);
            break;
         case "popa":
         case "popad":
            this.cY.Dw(var1);
            break;
         case "pushf":
         case "pushfd":
         case "pushfq":
            this.cY.Uv(var1);
            break;
         case "popf":
         case "popfd":
         case "popfq":
            this.cY.oW(var1);
            break;
         case "enter":
            this.cY.gO(var1);
            break;
         case "leave":
            this.cY.nf(var1);
            break;
         case "lahf":
            this.fw.RF(var1);
            break;
         case "sahf":
            this.fw.xK(var1);
            break;
         case "bt":
            this.fw.Dw(var1);
            break;
         case "btc":
            this.fw.Uv(var1);
            break;
         case "btr":
            this.fw.oW(var1);
            break;
         case "bts":
            this.fw.gO(var1);
            break;
         case "xlat":
         case "xlatb":
            this.fw.nf(var1);
            break;
         case "salc":
            this.fw.gP(var1);
            break;
         case "fnop":
         case "fwait":
            this.PY.q(var1);
            break;
         case "fld":
            this.PY.Dw(var1);
            break;
         case "fst":
            this.PY.Uv(var1);
            break;
         case "fstp":
            this.PY.oW(var1);
            break;
         case "fild":
            this.PY.gO(var1);
            break;
         case "fist":
            this.PY.nf(var1);
            break;
         case "fistp":
            this.PY.gP(var1);
            break;
         case "fxch":
            this.PY.za(var1);
            break;
         case "fldz":
            this.PY.q(var1, 0.0);
            break;
         case "fld1":
            this.PY.q(var1, 1.0);
            break;
         case "fldpi":
            this.PY.q(var1, Math.PI);
            break;
         case "fldl2t":
            this.PY.q(var1, WJ);
            break;
         case "fldl2e":
            this.PY.q(var1, pL);
            break;
         case "fldlg2":
            this.PY.q(var1, aH);
            break;
         case "fldln2":
            this.PY.q(var1, yc);
            break;
         case "fadd":
            this.PY.q(var1, OperationType.FADD, false, false, false);
            break;
         case "faddp":
            this.PY.q(var1, OperationType.FADD, true, false, false);
            break;
         case "fiadd":
            this.PY.q(var1, OperationType.FADD, false, true, false);
            break;
         case "fsub":
            this.PY.q(var1, OperationType.FSUB, false, false, false);
            break;
         case "fsubp":
            this.PY.q(var1, OperationType.FSUB, true, false, false);
            break;
         case "fisub":
            this.PY.q(var1, OperationType.FSUB, false, true, false);
            break;
         case "fmul":
            this.PY.q(var1, OperationType.FMUL, false, false, false);
            break;
         case "fmulp":
            this.PY.q(var1, OperationType.FMUL, true, false, false);
            break;
         case "fimul":
            this.PY.q(var1, OperationType.FMUL, false, true, false);
            break;
         case "fdiv":
            this.PY.q(var1, OperationType.FDIV, false, false, false);
            break;
         case "fdivp":
            this.PY.q(var1, OperationType.FDIV, true, false, false);
            break;
         case "fidiv":
            this.PY.q(var1, OperationType.FDIV, false, true, false);
            break;
         case "fsubr":
            this.PY.q(var1, OperationType.FSUB, false, false, true);
            break;
         case "fsubrp":
            this.PY.q(var1, OperationType.FSUB, true, false, true);
            break;
         case "fisubr":
            this.PY.q(var1, OperationType.FSUB, false, true, true);
            break;
         case "fdivr":
            this.PY.q(var1, OperationType.FDIV, false, false, true);
            break;
         case "fdivrp":
            this.PY.q(var1, OperationType.FDIV, true, false, true);
            break;
         case "fidivr":
            this.PY.q(var1, OperationType.FDIV, false, true, true);
            break;
         case "fdecstp":
            this.PY.RF(var1);
            break;
         case "fincstp":
            this.PY.xK(var1);
            break;
         case "fptan":
            this.PY.lm(var1);
            break;
         case "fxtract":
            this.PY.zz(var1);
            break;
         case "fsincos":
            this.PY.zz(var1);
            break;
         case "fxsave":
         case "fxsave64":
            this.q(var1, var2, 2);
            break;
         case "fxrstor":
         case "fxrstor64":
            this.q(var1, var2, 0);
            break;
         case "emms":
            this.PY.LK(var1);
            break;
         case "movd":
            this.PY.RF(var1, 32);
            break;
         case "movq":
            this.PY.RF(var1, 64);
            break;
         case "packsswb":
            this.PY.q(var1, true, 16);
            break;
         case "packssdw":
            this.PY.q(var1, true, 32);
            break;
         case "packuswb":
            this.PY.q(var1, false, 16);
            break;
         case "packusdw":
            this.PY.q(var1, false, 32);
            break;
         case "paddb":
            this.PY.q(var1, 8, OperationType.ADD);
            break;
         case "paddw":
            this.PY.q(var1, 16, OperationType.ADD);
            break;
         case "paddd":
            this.PY.q(var1, 32, OperationType.ADD);
            break;
         case "paddq":
            this.PY.q(var1, 64, OperationType.ADD);
            break;
         case "paddsb":
            this.PY.q(var1, 8, OperationType.ADD_SSAT);
            break;
         case "paddsw":
            this.PY.q(var1, 16, OperationType.ADD_SSAT);
            break;
         case "paddusb":
            this.PY.q(var1, 8, OperationType.ADD_USAT);
            break;
         case "paddusw":
            this.PY.q(var1, 16, OperationType.ADD_USAT);
            break;
         case "psubb":
            this.PY.q(var1, 8, OperationType.SUB);
            break;
         case "psubw":
            this.PY.q(var1, 16, OperationType.SUB);
            break;
         case "psubd":
            this.PY.q(var1, 32, OperationType.SUB);
            break;
         case "psubq":
            this.PY.q(var1, 64, OperationType.SUB);
            break;
         case "psubsb":
            this.PY.q(var1, 8, OperationType.SUB_SSAT);
            break;
         case "psubsw":
            this.PY.q(var1, 16, OperationType.SUB_SSAT);
            break;
         case "psubusb":
            this.PY.q(var1, 8, OperationType.SUB_USAT);
            break;
         case "psubusw":
            this.PY.q(var1, 16, OperationType.SUB_USAT);
            break;
         case "pand":
            this.PY.q(var1, 0);
            break;
         case "pandn":
            this.PY.q(var1, 1);
            break;
         case "por":
            this.PY.q(var1, 2);
            break;
         case "pxor":
            this.PY.q(var1, 3);
            break;
         case "pcmpeqb":
            this.PY.q(var1, 8, OperationType.LOG_EQ);
            break;
         case "pcmpeqw":
            this.PY.q(var1, 16, OperationType.LOG_EQ);
            break;
         case "pcmpeqd":
            this.PY.q(var1, 32, OperationType.LOG_EQ);
            break;
         case "pcmpgtb":
            this.PY.q(var1, 8, OperationType.GT_S);
            break;
         case "pcmpgtw":
            this.PY.q(var1, 16, OperationType.GT_S);
            break;
         case "pcmpgtd":
            this.PY.q(var1, 32, OperationType.GT_S);
            break;
         case "pmaddwd":
            this.q(var1, var2, 1);
            break;
         case "pmulhw":
            this.PY.q(var1, 16, OperationType.MUL2_S, true);
            break;
         case "pmullw":
            this.PY.q(var1, 16, OperationType.MUL2_S, false);
            break;
         case "pmulhuw":
            this.PY.q(var1, 16, OperationType.MUL2_U, true);
            break;
         case "psllw":
            this.PY.q(var1, 16, OperationType.SHL);
            break;
         case "pslld":
            this.PY.q(var1, 32, OperationType.SHL);
            break;
         case "psllq":
            this.PY.q(var1, 64, OperationType.SHL);
            break;
         case "psraw":
            this.PY.q(var1, 16, OperationType.SAR);
            break;
         case "psrad":
            this.PY.q(var1, 32, OperationType.SAR);
            break;
         case "psraq":
            this.PY.q(var1, 64, OperationType.SAR);
            break;
         case "psrlw":
            this.PY.q(var1, 16, OperationType.SHR);
            break;
         case "psrld":
            this.PY.q(var1, 32, OperationType.SHR);
            break;
         case "psrlq":
            this.PY.q(var1, 64, OperationType.SHR);
            break;
         case "punpckhbw":
            this.PY.RF(var1, true, 8);
            break;
         case "punpckhwd":
            this.PY.RF(var1, true, 16);
            break;
         case "punpckhdq":
            this.PY.RF(var1, true, 32);
            break;
         case "punpcklbw":
            this.PY.RF(var1, false, 8);
            break;
         case "punpcklwd":
            this.PY.RF(var1, false, 16);
            break;
         case "punpckldq":
            this.PY.RF(var1, false, 32);
            break;
         case "maskmovq":
            this.PY.io(var1);
            break;
         case "movntq":
            this.xK(var1);
            break;
         case "pshufw":
            this.q(var1, var2, 2);
            break;
         case "pinsrw":
            this.q(var1, var2, 2);
            break;
         case "pextrw":
            this.q(var1, var2, 2);
            break;
         case "pmovmskb":
            this.q(var1, var2, 2);
            break;
         case "pminub":
            this.q(var1, var2, 1);
            break;
         case "pminuw":
            this.q(var1, var2, 1);
            break;
         case "pminsb":
            this.q(var1, var2, 1);
            break;
         case "pminsw":
            this.q(var1, var2, 1);
            break;
         case "pmaxub":
            this.q(var1, var2, 1);
            break;
         case "pmaxuw":
            this.q(var1, var2, 1);
            break;
         case "pmaxsb":
            this.q(var1, var2, 1);
            break;
         case "pmaxsw":
            this.q(var1, var2, 1);
            break;
         case "pmaxsd":
            this.q(var1, var2, 1);
            break;
         case "pmaxsq":
            this.q(var1, var2, 1);
            break;
         case "pavgb":
            this.q(var1, var2, 1);
            break;
         case "pavgw":
            this.q(var1, var2, 1);
            break;
         case "psadbw":
            this.q(var1, var2, 1);
            break;
         case "pmuludq":
            this.PY.q(var1, 32, OperationType.MUL2_U);
            break;
         case "psignb":
         case "psignw":
         case "psignd":
            this.q(var1, var2, 1);
            break;
         case "pshufb":
            this.q(var1, var2, 1);
            break;
         case "pmulhrsw":
            this.q(var1, var2, 1);
            break;
         case "pmaddubsw":
            this.q(var1, var2, 1);
            break;
         case "phsubw":
         case "phsubd":
         case "phsubsw":
         case "phaddw":
         case "phaddd":
         case "phaddsw":
            this.q(var1, var2, 1);
            break;
         case "palignr":
            this.q(var1, var2, 1);
            break;
         case "pabsb":
         case "pabsw":
         case "pabsd":
         case "pabsq":
            this.q(var1, var2, 2);
            break;
         case "movss":
            this.cR.q(var1);
            break;
         case "movups":
            this.cR.q(var1, 128, 128);
            break;
         case "movlps":
            this.cR.q(var1, 64, 128);
            break;
         case "movhlps":
            this.cR.q(var1, 64);
            break;
         case "movhps":
         case "movlhps":
            this.cR.RF(var1, 64);
            break;
         case "movaps":
            this.cR.q(var1, 128, 128);
            break;
         case "movntps":
            this.cR.q(var1, 128, 128);
            break;
         case "movmskps":
            this.q(var1, var2, 2);
            break;
         case "unpcklps":
         case "unpckhps":
            this.q(var1, var2, 1);
            break;
         case "cvtpi2ps":
            this.cR.Dw(var1);
            break;
         case "cvtps2pi":
            this.cR.q(var1, false);
            break;
         case "cvtsi2ss":
            this.cR.Uv(var1);
            break;
         case "cvtss2si":
            this.cR.RF(var1, false);
            break;
         case "cvttps2pi":
            this.cR.q(var1, true);
            break;
         case "cvttss2si":
            this.cR.RF(var1, true);
            break;
         case "addss":
            this.cR.q(var1, 0, 1, 32);
            break;
         case "addps":
            this.cR.q(var1, 0, 4, 32);
            break;
         case "subss":
            this.cR.q(var1, 1, 1, 32);
            break;
         case "subps":
            this.cR.q(var1, 1, 4, 32);
            break;
         case "mulss":
            this.cR.q(var1, 2, 1, 32);
            break;
         case "mulps":
            this.cR.q(var1, 2, 4, 32);
            break;
         case "divss":
            this.cR.q(var1, 3, 1, 32);
            break;
         case "divps":
            this.cR.q(var1, 3, 4, 32);
            break;
         case "andps":
            this.cR.q(var1, 4, 4, 32);
            break;
         case "orps":
            this.cR.q(var1, 5, 4, 32);
            break;
         case "xorps":
            this.cR.q(var1, 6, 4, 32);
            break;
         case "andnps":
            this.cR.q(var1, 7, 4, 32);
            break;
         case "minss":
            this.cR.q(var1, 8, 1, 32);
            break;
         case "minps":
            this.cR.q(var1, 8, 4, 32);
            break;
         case "maxss":
            this.cR.q(var1, 9, 1, 32);
            break;
         case "maxps":
            this.cR.q(var1, 9, 4, 32);
            break;
         case "sqrtps":
         case "sqrtss":
         case "rsqrtps":
         case "rsqrtss":
         case "rcpps":
         case "rcpss":
            this.q(var1, var2, 2);
            break;
         case "cmpeqss":
            this.cR.q(var1, 10, 1, 32);
            break;
         case "cmpeqps":
            this.cR.q(var1, 10, 4, 32);
            break;
         case "cmpltss":
            this.cR.q(var1, 11, 1, 32);
            break;
         case "cmpltps":
            this.cR.q(var1, 11, 4, 32);
            break;
         case "cmpless":
            this.cR.q(var1, 12, 1, 32);
            break;
         case "cmpleps":
            this.cR.q(var1, 12, 4, 32);
            break;
         case "cmpunordss":
            this.cR.q(var1, 13, 1, 32);
            break;
         case "cmpunordps":
            this.cR.q(var1, 13, 4, 32);
            break;
         case "cmpneqss":
            this.cR.q(var1, 14, 1, 32);
            break;
         case "cmpneqps":
            this.cR.q(var1, 14, 4, 32);
            break;
         case "cmpnltss":
            this.cR.q(var1, 15, 1, 32);
            break;
         case "cmpnltps":
            this.cR.q(var1, 15, 4, 32);
            break;
         case "cmpnless":
            this.cR.q(var1, 16, 1, 32);
            break;
         case "cmpnleps":
            this.cR.q(var1, 16, 4, 32);
            break;
         case "cmpordss":
            this.cR.q(var1, 17, 1, 32);
            break;
         case "cmpordps":
            this.cR.q(var1, 17, 4, 32);
            break;
         case "comiss":
            this.cR.q(var1, 32, true);
            break;
         case "ucomiss":
            this.cR.q(var1, 32, false);
            break;
         case "ldmxcsr":
            this.cR.za(var1);
            break;
         case "stmxcsr":
            this.cR.lm(var1);
            break;
         case "shufps":
            this.q(var1, var2, 1);
            break;
         case "movnti":
         case "movntdq":
            this.xK(var1);
            break;
         case "movsd":
            this.cR.RF(var1);
            break;
         case "movdqu":
         case "movdqa":
         case "movupd":
         case "movapd":
            this.cR.q(var1, 128, 128);
            break;
         case "movntpd":
            this.xK(var1);
            break;
         case "movhpd":
            this.cR.xK(var1, 64);
            break;
         case "movlpd":
            this.cR.q(var1, 64, 128);
            break;
         case "movmskpd":
            this.cR.xK(var1);
            break;
         case "unpcklpd":
         case "unpckhpd":
            this.q(var1, var2, 1);
            break;
         case "addsd":
            this.cR.q(var1, 0, 1, 64);
            break;
         case "addpd":
            this.cR.q(var1, 0, 2, 64);
            break;
         case "subsd":
            this.cR.q(var1, 1, 1, 64);
            break;
         case "subpd":
            this.cR.q(var1, 1, 2, 64);
            break;
         case "mulsd":
            this.cR.q(var1, 2, 1, 64);
            break;
         case "mulpd":
            this.cR.q(var1, 2, 2, 64);
            break;
         case "divsd":
            this.cR.q(var1, 3, 1, 64);
            break;
         case "divpd":
            this.cR.q(var1, 3, 2, 64);
            break;
         case "andpd":
            this.cR.q(var1, 4, 2, 64);
            break;
         case "orpd":
            this.cR.q(var1, 5, 2, 64);
            break;
         case "xorpd":
            this.cR.q(var1, 6, 2, 64);
            break;
         case "andnpd":
            this.cR.q(var1, 7, 2, 64);
            break;
         case "minsd":
            this.cR.q(var1, 8, 1, 64);
            break;
         case "minpd":
            this.cR.q(var1, 8, 2, 64);
            break;
         case "maxsd":
            this.cR.q(var1, 9, 1, 64);
            break;
         case "maxpd":
            this.cR.q(var1, 9, 2, 64);
            break;
         case "sqrtpd":
         case "sqrtsd":
            this.q(var1, var2, 2);
            break;
         case "cmpeqsd":
            this.cR.q(var1, 10, 1, 64);
            break;
         case "cmpeqpd":
            this.cR.q(var1, 10, 2, 64);
            break;
         case "cmpltsd":
            this.cR.q(var1, 11, 1, 64);
            break;
         case "cmpltpd":
            this.cR.q(var1, 11, 2, 64);
            break;
         case "cmplesd":
            this.cR.q(var1, 12, 1, 64);
            break;
         case "cmplepd":
            this.cR.q(var1, 12, 2, 64);
            break;
         case "cmpunordsd":
            this.cR.q(var1, 13, 1, 64);
            break;
         case "cmpunordpd":
            this.cR.q(var1, 13, 2, 64);
            break;
         case "cmpneqsd":
            this.cR.q(var1, 14, 1, 64);
            break;
         case "cmpneqpd":
            this.cR.q(var1, 14, 2, 64);
            break;
         case "cmpnltsd":
            this.cR.q(var1, 15, 1, 64);
            break;
         case "cmpnltpd":
            this.cR.q(var1, 15, 2, 64);
            break;
         case "cmpnlesd":
            this.cR.q(var1, 16, 1, 64);
            break;
         case "cmpnlepd":
            this.cR.q(var1, 16, 2, 64);
            break;
         case "cmpordsd":
            this.cR.q(var1, 17, 1, 64);
            break;
         case "cmpordpd":
            this.cR.q(var1, 17, 2, 64);
            break;
         case "comisd":
            this.cR.q(var1, 64, true);
            break;
         case "ucomisd":
            this.cR.q(var1, 64, false);
            break;
         case "shufpd":
            this.q(var1, var2, 1);
            break;
         case "cvtdq2pd":
            this.cR.q(var1, OperationType.INT2FP, 2, 32, 64, 128);
            break;
         case "cvtdq2ps":
            this.cR.q(var1, OperationType.INT2FP, 4, 32, 32, 128);
            break;
         case "cvtpd2dq":
            this.cR.q(var1, OperationType.FP2INT, 2, 64, 32, 128);
            break;
         case "cvtpd2pi":
            this.cR.q(var1, OperationType.FP2INT, 2, 64, 32, 64);
            break;
         case "cvtpd2ps":
            this.cR.q(var1, OperationType.FP2FP, 2, 64, 32, 128);
            break;
         case "cvtpi2pd":
            this.cR.q(var1, OperationType.INT2FP, 2, 32, 64, 128);
            break;
         case "cvtps2dq":
            this.cR.q(var1, OperationType.FP2INT, 4, 32, 32, 128);
            break;
         case "cvtps2pd":
            this.cR.q(var1, OperationType.FP2FP, 2, 32, 64, 128);
            break;
         case "cvtsd2si":
            this.cR.xK(var1, false);
            break;
         case "cvtsd2ss":
            this.cR.gO(var1);
            break;
         case "cvtsi2sd":
            this.cR.oW(var1);
            break;
         case "cvtss2sd":
            this.cR.nf(var1);
            break;
         case "cvttsd2si":
            this.cR.xK(var1, true);
            break;
         case "cvttpd2dq":
            this.cR.q(var1, OperationType.FP2INT, 2, 64, 32, 128, true);
            break;
         case "cvttpd2pi":
            this.cR.q(var1, OperationType.FP2INT, 2, 64, 32, 64, true);
            break;
         case "cvttps2dq":
            this.cR.q(var1, OperationType.FP2INT, 4, 32, 32, 128, true);
            break;
         case "pshuflw":
         case "pshufhw":
         case "pshufd":
            this.q(var1, var2, 2);
            break;
         case "movq2dq":
            this.cR.q(var1, 64, 128);
            break;
         case "movdq2q":
            this.cR.q(var1, 64, 0);
            break;
         case "pslldq":
            this.PY.q(var1, 128, OperationType.SHL, null, 3);
            break;
         case "psrldq":
            this.PY.q(var1, 128, OperationType.SHR, null, 3);
            break;
         case "punpcklqdq":
            this.PY.RF(var1, false, 64);
            break;
         case "punpckhqdq":
            this.PY.RF(var1, true, 64);
            break;
         case "maskmovdqu":
            this.cR.zz(var1);
            break;
         case "movntdqa":
            this.xK(var1);
            break;
         case "in":
            this.fw.lm(var1);
            break;
         case "out":
            this.fw.zz(var1);
            break;
         case "ins":
         case "insb":
         case "insw":
         case "insd":
            this.fw.HF(var1);
            break;
         case "outs":
         case "outsb":
         case "outsw":
         case "outsd":
            this.fw.io(var1);
            break;
         case "arpl":
            this.fw.za(var1);
            break;
         case "les":
            this.fw.q(var1, this.ND, "les");
            break;
         case "lds":
            this.fw.q(var1, this.Jf, "lds");
            break;
         case "lfs":
            this.fw.q(var1, this.vC, "lfs");
            break;
         case "lgs":
            this.fw.q(var1, this.of, "lgs");
            break;
         case "lss":
            this.fw.q(var1, this.jh, "lss");
            break;
         case "int3":
            this.fw.q(var1, "int3");
            break;
         case "int1":
            this.fw.q(var1, "int1");
            break;
         case "int":
            this.fw.q(var1, "int");
            break;
         case "into":
            this.fw.qa(var1);
            break;
         case "iret":
         case "iretd":
         case "iretq":
            this.fw.Hk(var1);
            break;
         case "verr":
         case "verw":
            this.fw.Me(var1);
            break;
         case "rdmsr":
            this.fw.PV(var1);
            break;
         case "wrmsr":
            this.fw.oQ(var1);
            break;
         case "rdtsc":
            this.fw.xW(var1);
            break;
         case "rdtscp":
            this.fw.xW(var1);
            break;
         case "cpuid":
            this.fw.Ef(var1);
            break;
         case "syscall":
            this.fw.cC(var1);
            break;
         case "rdpmc":
            this.fw.Gf(var1);
            break;
         case "xgetbv":
            this.fw.sH(var1);
            break;
         case "xsetbv":
            this.fw.CE(var1);
            break;
         case "swapgs":
            this.fw.If(var1);
            break;
         case "lar":
            this.fw.Dz(var1);
            break;
         case "lsl":
            this.fw.mI(var1);
            break;
         case "sldt":
         case "str":
         case "sgdt":
         case "sidt":
         case "smsw":
            this.q(var1, var2, 2);
            break;
         case "sfence":
         case "lfence":
         case "mfence":
         case "clflush":
         case "hlt":
         case "bound":
         case "pause":
         case "lldt":
         case "ltr":
         case "lgdt":
         case "lidt":
         case "lmsw":
         case "invd":
         case "invlpg":
         case "wbinvd":
         case "rsm":
         case "prefetchnta":
         case "prefetcht0":
         case "prefetcht1":
         case "prefetcht2":
         case "prefetchw":
         case "monitor":
         case "mwait":
         case "clts":
            this.q(var1, var2, 0);
            break;
         case "pclmulqdq":
         case "pclmullqlqdq":
         case "pclmulhqlqdq":
         case "pclmullqhqdq":
         case "pclmulhqhqdq":
            this.q(var1, var2, 1);
            break;
         case "lzcnt":
         case "popcnt":
            this.q(var1, var2, 2, this.KF, this.GC, this.Cl, this.WX, this.C, this.CB);
            break;
         case "andn":
         case "bextr":
         case "blsi":
         case "blsmsk":
         case "blsr":
         case "tzcnt":
            this.q(var1, var2, 2, this.KF, this.GC, this.Cl, this.WX, this.C, this.CB);
            break;
         case "bzhi":
            this.q(var1, var2, 2, this.KF, this.GC, this.Cl, this.WX, this.C, this.CB);
            break;
         case "pdep":
         case "pext":
         case "rorx":
         case "sarx":
         case "shrx":
         case "shlx":
            this.q(var1, var2, 2);
            break;
         case "mulx":
            this.fw.ui(var1);
            break;
         case "blcfill":
         case "blci":
         case "blcic":
         case "blcmsk":
         case "blcs":
         case "t1msk":
         case "tzmsk":
            this.q(var1, var2, 2, this.KF, this.GC, this.Cl, this.WX, this.C, this.CB);
            break;
         case "aesenc":
         case "aesenclast":
         case "aesdec":
         case "aesdeclast":
            this.q(var1, var2, 1);
            break;
         case "aeskeygenassist":
         case "aesimc":
            this.q(var1, var2, 2);
            break;
         case "sha1rnds4":
         case "sha1nexte":
         case "sha1msg1":
         case "sha1msg2":
         case "sha256msg1":
         case "sha256msg2":
            this.q(var1, var2, 1);
            break;
         case "sha256rnds2":
            this.fw.jq(var1);
            break;
         case "rdsspd":
         case "rdsspq":
         case "wrssd":
         case "wrssq":
         case "wrussd":
         case "wrussq":
            this.q(var1, var2, 2);
            break;
         case "incsspd":
         case "incsspq":
            this.q(var1, var2, 0);
            break;
         case "setssbsy":
         case "saveprevssp":
         case "rstorssp":
         case "clrssbsy":
            this.q(var1, var2, 0);
            break;
         case "bndmk":
         case "bndmov":
         case "bndldx":
         case "bndstx":
            this.q(var1, var2, 2);
            break;
         case "bndcl":
         case "bndcn":
         case "bndcu":
            this.q(var1, var2, 0);
            break;
         case "vmxon":
         case "vmxoff":
         case "vmptrld":
         case "vmclear":
         case "vmwrite":
         case "vmcall":
         case "vmlaunch":
         case "vmresume":
            this.q(var1, var2, 0);
            break;
         case "vmptrst":
         case "vmread":
            this.q(var1, var2, 2);
            break;
         default:
            long var9 = var4.io.xK;
            if ((var9 & 131072L) == 0L) {
               return false;
            }

            boolean var12 = this.PY.HF(var1);
            if (!var12) {
               return false;
            }
      }

      return true;
   }

   IEUntranslatedInstruction RF(ConverterInstructionEntry var1) {
      return this.q(var1, null, 0);
   }

   IEUntranslatedInstruction q(ConverterInstructionEntry var1, String var2, int var3, IEVar... var4) {
      if (var2 == null) {
         var2 = ((ctc)var1.insn).getMnemonic();
      }

      Assert.a(var3 >= 0 && var3 <= 2);
      int var5 = ((ctc)var1.insn).Dw().length;
      ArrayList var6 = new ArrayList(var5);

      for (int var7 = var3 == 2 ? 1 : 0; var7 < var5; var7++) {
         IEGeneric var8 = this.q((ctc)var1.insn, var7, 0, var1.address);
         var6.add(var8);
      }

      IEUntranslatedInstruction var9 = this.ctx.createUntranslatedInstruction(var1.address, var2, (IEGeneric[])var6.toArray(new IEGeneric[var6.size()]));
      if (var3 != 0) {
         Assert.a(var5 > 0);
         IEGeneric var10 = this.q((ctc)var1.insn, 0, 0, var1.address);
         var9.setReturnExpression(var10);
      }

      if (var4.length > 0) {
         var9.addSideEffectDefinedVariable(var4);
      }

      var9.setBreakingFlow(((ctc)var1.insn).getBreakingFlow(var1.address));
      var9.setRoutineCall(((ctc)var1.insn).getRoutineCall(var1.address));
      var1.r.add(var9);
      return var9;
   }

   void xK(ConverterInstructionEntry var1) {
      IEGeneric var2 = this.q((ctc)var1.insn, 0, 0, var1.address);
      IEGeneric var3 = this.q((ctc)var1.insn, 1, 0, var1.address);
      Assert.a(var2.getBitsize() >= var3.getBitsize());
      var1.r.add(this.ctx.createAssign(var2.part(var3.getBitsize()), var3));
   }

   boolean q(ConverterInstructionEntry var1, long var2) {
      if (!this.GO) {
         return false;
      } else {
         this.GO = false;

         boolean var4;
         try {
            var4 = this.attemptCallInliningByExtension(var1, var2);
         } finally {
            this.GO = true;
         }

         return var4;
      }
   }

   IEGeneric q(ctc var1, int var2, int var3, long var4) {
      cqj var6 = var1.Dw()[var2];
      return this.q(var1, var6, var3, var4);
   }

   IEGeneric q(ctc var1, cqj var2, int var3, long var4) {
      IEGeneric var6 = null;
      boolean var7 = false;
      if (var3 < 0) {
         var3 = -var3;
         var7 = true;
      }

      if (var2 instanceof cte var8) {
         int var9 = var8.getOperandType();
         switch (var9) {
            case 0:
               var6 = this.q(var8.getOperandValue());
               if (ctf.RF(var8.getOperandValue()) == 2) {
                  var7 = false;
               }
               break;
            case 1:
            case 2:
            case 3:
               if (var3 == 0 || var8.getOperandBitsize() >= var3) {
                  if (var9 == 1) {
                     var6 = this.ctx.createImm(var8.getOperandValue(), var8.getOperandBitsize());
                  } else {
                     if (var9 != 3) {
                        if (var9 == 2) {
                           throw new RuntimeException();
                        }

                        throw new RuntimeException();
                     }

                     var6 = this.ctx.createImm(var8.getOperandValue(var4), this.rV.getBitsize());
                  }

                  return var6;
               }

               long var29 = var8.getOperandValue();
               if (!var7) {
                  switch (var8.getOperandBitsize()) {
                     case 8:
                        var29 &= 255L;
                        break;
                     case 16:
                        var29 &= 65535L;
                        break;
                     case 32:
                        var29 &= 4294967295L;
                     case 64:
                        break;
                     default:
                        throw new RuntimeException();
                  }
               } else {
                  switch (var8.getOperandBitsize()) {
                     case 8:
                        var29 = (byte)var29;
                        break;
                     case 16:
                        var29 = (short)var29;
                        break;
                     case 32:
                        var29 = (int)var29;
                     case 64:
                        break;
                     default:
                        throw new RuntimeException();
                  }
               }

               var6 = this.ctx.createImm(var29, var3);
               break;
            case 4:
               IEGeneric var28 = this.q(var8.getOperandValue());
               int var31 = ctf.q(var8.q(var1));
               var6 = this.q(var31, var28, var8.getOperandBitsize());
               break;
            case 5:
               IEImm var27 = this.ctx.createImm(var8.getOperandValue(), var1.lm());
               int var11 = ctf.q(var8.q(var1));
               var6 = this.q(var11, var27, var8.getOperandBitsize());
               break;
            case 4097:
               int var10 = var8.Dw();
               return EUtil.imm(var10, var1.lm());
         }
      } else if (var2 instanceof ctd var25) {
         IEGeneric var26 = null;
         IEGeneric var30 = null;
         IEImm var32 = null;
         IEImm var12 = null;
         int var13 = var1.lm();
         long var14 = var25.getMemoryBaseRegister();
         long var16 = var25.getMemoryIndexRegister();
         int var18 = var25.getMemoryScale();
         long var19 = var25.getMemoryDisplacement();
         if (var14 != -1L) {
            int var21 = ctf.RF(var14);
            if (var21 == 10) {
               Assert.a(var1.gO == 64);
               var19 = var19 + var4 + var1.getSize();
            } else {
               var26 = this.q(var14);
               Assert.a(var26.getBitsize() == var13);
            }
         }

         if (var16 != -1L) {
            var30 = this.q(var16);
            Assert.a(var30.getBitsize() == var13);
         }

         if (var18 != 0) {
            var32 = this.ctx.createImm(var18, var13);
         }

         if (var19 != 0L) {
            var12 = this.ctx.createImm(var19, var13);
         }

         Object var33 = null;
         if (var30 != null && var32 != null) {
            if (var18 == 1) {
               var33 = var30;
            } else {
               var33 = this.ctx.createOperation(OperationType.MUL, var32, var30);
            }
         }

         if (var12 != null) {
            if (var33 == null) {
               var33 = var12;
            } else {
               var33 = this.ctx.createOperation(OperationType.ADD, (IEGeneric)var33, var12);
            }
         }

         if (var26 != null) {
            if (var33 == null) {
               var33 = var26;
            } else {
               var33 = this.ctx.createOperation(OperationType.ADD, var26, (IEGeneric)var33);
            }
         }

         if (var33 == null) {
            var33 = this.ctx.createImm(var19, var13);
         }

         int var22 = ctf.q(var25.q(var1));
         int var23 = var25.getOperandBitsize();
         if (var23 == 0) {
            return (IEGeneric)var33;
         }

         var6 = this.q(var22, (IEGeneric)var33, var23);
      }

      if (var3 != 0 && var2.getOperandBitsize() < var3) {
         if (var7) {
            var6 = var6.signExtend(var3);
         } else {
            var6 = var6.zeroExtend(var3);
         }
      }

      if (var6 == null) {
         throw new RuntimeException(Strings.ff("Cannot convert operand: \"%s\" (t%s)", var2, var2.getClass().getName()));
      } else {
         return var6;
      }
   }

   IEGeneric RF(ctc var1, int var2, int var3, long var4) {
      cqj var6 = var1.Dw()[var2];
      int var7;
      if (var6 instanceof cte && ((cte)var6).getOperandType() == 1) {
         var7 = -var3;
      } else {
         var7 = var3;
      }

      return this.q(var1, var2, var7, var4);
   }

   IEGeneric q(long var1) {
      int var3 = ctf.q(var1);
      int var4 = ctf.RF(var1);
      int var5 = ctf.xK(var1);
      int var6 = ctf.Dw(var1);
      if (var4 == 0) {
         String var9 = ctf.RF(var3, var4, this.regNormalBitsize, 0);
         IEVar var8 = this.gCtx.createRegister(0 + var3 * 64, var9, this.regNormalBitsize);
         return var8.slice(var6, var6 + var5);
      } else if (var4 == 2) {
         String var7 = ctf.RF(var3, var4, var5, var6);
         return this.gCtx.createRegister(1280 + var3 * 32, var7, var5);
      } else if (var4 == 3) {
         Assert.a(var5 == 80);
         return this.gCtx.createGroupElt(this.cO, EUtil.add(this.Gg, EUtil.imm(var3, 3)));
      } else if (var4 == 4) {
         return this.fG[var3].part(var5);
      } else if (var4 == 5) {
         return this.Xz[var3].part(var5);
      } else {
         throw new RuntimeException("Intel register group not converted yet: " + var4);
      }
   }

   void q(IEGeneric var1, IEGeneric var2, ConverterInstructionEntry var3) {
      this.q(var1, var2, var3, true);
   }

   void q(IEGeneric var1, IEGeneric var2, ConverterInstructionEntry var3, boolean var4) {
      if (var4
         && ((ctc)var3.insn).getProcessorMode() == 64
         && var1 instanceof IESlice var5
         && var5.getBitsize() == 32
         && var5.getBitStart() == 0
         && var5.getWholeExpression() instanceof IEVar) {
         IEVar var6 = (IEVar)var5.getWholeExpression();
         if (var6.getId() >= 0 && var6.getId() <= 960) {
            var1 = var6;
            var2 = this.ctx.createCompose((IEGeneric)var2, this.Oj);
         }
      }

      IEAssign var7 = this.ctx.createAssign((IEGeneric)var1, (IEGeneric)var2);
      var3.r.add(var7);
   }

   void q(IEGeneric var1, List var2) {
      var2.add(this.Dw(var1));
      var2.add(this.Uv(var1));
      var2.add(this.oW(var1));
   }

   private IEAssign Dw(IEGeneric var1) {
      IECond var2 = this.gCtx.createCond(var1, this.Eq, this.hP);
      return this.ctx.createAssign(this.GC, var2);
   }

   private IEAssign Uv(IEGeneric var1) {
      IEGeneric var2 = var1.msb();
      return this.ctx.createAssign(this.KF, var2);
   }

   private IEAssign oW(IEGeneric var1) {
      IEGeneric var2 = var1.slice(0, 8);
      IEOperation var3 = this.gCtx.createOperation(OperationType.PAR, var2);
      return this.ctx.createAssign(this.CB, var3);
   }

   void q(IEGeneric var1, IEGeneric var2, IEGeneric var3, List var4) {
      IEOperation var5 = this.gCtx.createOperation(OperationType.XOR, this.gCtx.createOperation(OperationType.XOR, var1, var2), var3);
      var4.add(this.ctx.createAssign(this.C, var5.slice(4, 5)));
   }

   void q(IEGeneric var1, IEGeneric var2, List var3) {
      var3.add(this.q(var1, var2));
      var3.add(this.RF(var1, var2));
      var3.add(this.xK(var1, var2));
   }

   private IEAssign q(IEGeneric var1, IEGeneric var2) {
      IECond var3 = this.gCtx.createCond(var1, this.Eq, this.hP);
      return this.ctx.createAssign(this.GC, this.gCtx.createCond(var2, var3, this.GC));
   }

   private IEAssign RF(IEGeneric var1, IEGeneric var2) {
      IEGeneric var3 = var1.msb();
      return this.ctx.createAssign(this.KF, this.gCtx.createCond(var2, var3, this.KF));
   }

   private IEAssign xK(IEGeneric var1, IEGeneric var2) {
      IEGeneric var3 = var1.slice(0, 8);
      IEOperation var4 = this.gCtx.createOperation(OperationType.PAR, var3);
      return this.ctx.createAssign(this.CB, this.gCtx.createCond(var2, var4.lsb(), this.CB));
   }

   @Override
   public IEBranchDetails getDefaultBranchToRoutineSideEffects(INativeMethodItem var1) {
      int var2 = this.regNormalBitsize / 8;
      Integer var3 = null;
      if (var1 != null && var1.getData() != null) {
         var3 = var1.getData().getSPDeltaOnReturn();
      }

      ArrayList var4 = Lists.createArrayList();
      Lists.addNonNulls(var4, this.Sz);
      ArrayList var5 = Lists.createArrayList();
      Lists.addNonNulls(var5, this.os);
      ArrayList var6 = Lists.createArrayList();
      Lists.addNonNulls(var6, this.fn, this.iu);
      IEBranchDetails var7 = this.gCtx.createBranchDetails(var5, var4, var6, var2);
      if (var3 != null) {
         var7.getStackPointerDeltaDeterminer().add(var2 + var3, 30, 30);
      }

      return var7;
   }

   @Override
   public void customInitStateRegisters(EState var1, Long var2) {
      var1.setValue(1034, 0L);
   }

   public static boolean q(IEGeneric... var0) {
      return true;
   }

   @Override
   public Integer determineStackPointerDeltaFromSimulation(SimulationPointInformation var1) {
      EState var2 = var1.state;
      if (!var1.instructionEmulationFailed) {
         long var3 = var2.getValueAsLong(this.RW);
         if (this.GH.contains(var3)) {
            return null;
         }

         INativeMethodItem var5 = this.getNativeContext().getRoutine(var3);
         if (var5 != null) {
            String var6 = var5.getName();
            if (var6 != null) {
               switch (var6) {
                  case "___allocStackInternalPNF":
                     if (this.BY != null) {
                        return null;
                     }

                     long var13 = var2.getValueAsLong(this.YR);

                     try {
                        int var11 = var2.getMemory().readInt(var13 + this.fN);
                        this.BY = var11;
                        return -var11 + this.fN;
                     } catch (MemoryException var12) {
                        return null;
                     }
                  case "___freeStackInternalPNF":
                     if (this.BY == null) {
                        return null;
                     }

                     int var9 = this.BY;
                     this.BY = null;
                     return var9 + this.fN + this.fN;
                  default:
                     this.GH.add(var3);
                     return null;
               }
            }
         }
      }

      return null;
   }

   @Override
   public String formatStatistics() {
      StringBuilder var1 = new StringBuilder(super.formatStatistics());
      var1.append("\n\n");
      Strings.ff(var1, "Converted instructions: %d (incl. failed=%d, untranslated=%d)\n", this.Jh, this.iO, this.Qe);
      if (this.ZE != null && !this.ZE.isEmpty()) {
         var1.append("Failed instructions: ").append(this.ZE.formatTopReferences(-1));
      }

      return var1.toString();
   }
}
