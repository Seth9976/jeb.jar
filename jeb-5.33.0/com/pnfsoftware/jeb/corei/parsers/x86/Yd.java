package com.pnfsoftware.jeb.corei.parsers.x86;

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
import com.pnfsoftware.jebglobal.acj;
import com.pnfsoftware.jebglobal.ajp;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

@Ser
public class Yd extends AbstractConverter {
   private static final ILogger zR = GlobalLog.getLogger(Yd.class);
   private static final Object[] Ft = new Object[]{
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
   public static boolean pC = true;
   private static final double kt = 1.0 / Math.log10(2.0);
   private static final double Yw = 1.0 / Math.log(2.0);
   private static final double uD = Math.log10(2.0);
   private static final double ZY = Math.log(2.0);
   @SerTransient
   eW A;
   @SerTransient
   Sb kS;
   @SerTransient
   Hv wS;
   @SerTransient
   jM UT;
   @SerTransient
   Sf E;
   @SerTransient
   B sY;
   @SerTransient
   co ys;
   @SerTransient
   m ld;
   @SerTransient
   dE gp;
   @SerId(7)
   final int oT;
   @SerId(8)
   final IEVar fI;
   @SerId(9)
   final IEVar WR;
   @SerId(10)
   final IEVar NS;
   @SerId(11)
   final IEVar vP;
   @SerId(12)
   final IEVar xC;
   @SerId(13)
   final IEVar ED;
   @SerId(15)
   final IEVar Sc;
   @SerId(16)
   final IEVar ah;
   @SerId(17)
   final IEVar eP;
   @SerId(18)
   final IEVar UO;
   @SerId(19)
   final IEVar Ab;
   @SerId(20)
   final IEVar rl;
   @SerId(21)
   final IEVar z;
   @SerId(22)
   final IEVar Ek;
   @SerId(23)
   final IEVar hK;
   @SerId(24)
   final IEVar Er;
   @SerId(25)
   final IEVar FE;
   @SerId(26)
   final IEVar Aj;
   @SerId(27)
   final IEVar EX;
   @SerId(28)
   final IEVar LM;
   @SerId(29)
   final IEVar mv;
   @SerId(30)
   final IEVar sO;
   @SerId(31)
   final IEVar os;
   @SerId(32)
   final IEVar Cu;
   @SerId(33)
   final IEVar hZ;
   @SerId(34)
   final IEVar UW;
   @SerId(35)
   final IEVar PR;
   @SerId(36)
   final IEVar cX;
   @SerId(37)
   final IEVar DQ;
   @SerId(38)
   final IEVar ZN;
   @SerId(39)
   final IEVar OB;
   @SerId(40)
   final IEVar pF;
   @SerId(41)
   private final IEVar mK;
   @SerId(42)
   final IEImm Bc;
   @SerId(43)
   final IEImm OI;
   @SerId(44)
   final IEImm Bf;
   @SerId(45)
   final IEImm Pe;
   @SerId(46)
   final IEImm ck;
   @SerId(47)
   final IEImm RW;
   @SerId(48)
   final IEImm e;
   @SerId(49)
   final IEImm xM;
   @SerId(50)
   final IEImm kU;
   @SerId(51)
   final IEImm Kq;
   @SerId(52)
   final IEImm go;
   @SerId(53)
   final IEImm JF;
   @SerId(54)
   final IEImm Nq;
   @SerId(55)
   final IEImm pg;
   @SerId(56)
   final IEImm gj;
   @SerId(57)
   final IEImm ZD;
   @SerId(58)
   final IEImm DL;
   @SerId(59)
   final IEGeneric UH;
   @SerId(60)
   final IEGeneric VD;
   @SerId(61)
   final IEGeneric Xs;
   @SerId(62)
   final IEGeneric KV;
   @SerId(63)
   final IEGeneric FK;
   @SerId(64)
   final IEGeneric Bi;
   @SerId(65)
   final IEGeneric wQ;
   @SerId(66)
   final IEGeneric PZ;
   @SerId(67)
   final IEGeneric Ip;
   @SerId(68)
   final IEGeneric Fm;
   @SerId(69)
   final IEGeneric FM;
   @SerId(70)
   final IEGeneric Wn;
   @SerId(76)
   final IEVar gy;
   @SerId(77)
   final IEVar[] pt;
   @SerId(78)
   final ajp uE;
   @SerId(79)
   final IEVar[] Um;
   @SerId(80)
   final ajp Ta;
   @SerId(81)
   final IEVar So;
   @SerId(82)
   final IEVar tH;
   @SerId(83)
   final IEVar Gm;
   @SerId(84)
   final IEVar Br;
   @SerId(85)
   final IEVar IE;
   @SerId(86)
   final IEVar AU;
   @SerId(87)
   final IEVar jS;
   @SerId(88)
   final IEVar KK;
   @SerId(89)
   final IEVar oB;
   @SerId(90)
   final IEVar Rq;
   @SerId(91)
   final IEVar LL;
   @SerId(92)
   final IEVar rC;
   @SerId(93)
   final IEVar be;
   @SerId(94)
   final IEVar Xh;
   @SerId(95)
   final IEVar sz;
   @SerId(96)
   final IEVar QQ;
   @SerId(97)
   final IEVar eE;
   @SerId(98)
   final IEVar dM;
   @SerId(99)
   final IEVar EM;
   @SerId(100)
   final IEVar fD;
   @SerId(101)
   final IEVar ii;
   @SerId(102)
   final IEVar Gu;
   @SerId(103)
   final IEVar hw;
   @SerId(104)
   final IEVar qG;
   @SerId(105)
   final IEVar yi;
   @SerId(106)
   final IEVar zK;
   @SerId(110)
   final IEGeneric[] LA;
   @SerId(128)
   final IEVar ve;
   @SerId(120)
   private final IEVar pW;
   @SerId(200)
   ReferenceCounter yv;
   @SerId(201)
   int MZ;
   @SerId(202)
   int fH;
   @SerId(203)
   int ET;
   @SerId(204)
   final IEVar kk;
   @SerId(205)
   final IEVar Rh;
   @SerId(206)
   final IEVar vv;
   @SerId(207)
   final IEVar fn;
   @SerId(208)
   final IEVar AS;
   @SerId(209)
   final IEVar wF;
   @SerId(210)
   final IEVar[] hF;
   @SerId(211)
   final IEVar[] FA;
   @SerId(212)
   final IEVar[] IK;
   @SerId(213)
   final IEVar[] DM;
   @SerTransient
   boolean IQ;
   @SerTransient
   private int Gg;
   @SerTransient
   private int kQ;
   @SerTransient
   private int te;
   @SerTransient
   private Set B;
   @SerTransient
   private Integer RR;

   public Yd(Or var1) {
      super(var1, var1.getMode());

      for (byte var2 = 0; var2 < Ft.length; var2 += 3) {
         String var3 = (String)Ft[var2];
         int var4 = (Integer)Ft[var2 + 1];
         int var5 = (Integer)Ft[var2 + 2];
         if (this.regNormalBitsize >= var5 && (this.regNormalBitsize >= 64 || var4 < 512 || var4 > 960) && this.gCtx.canCreateVariable(var4, var5)) {
            this.gCtx.createRegister(var4, var3, var5);
         }
      }

      this.oT = 16;
      this.Sc = this.gCtx.getVar(0);
      this.ah = this.gCtx.getVar(64);
      this.eP = this.gCtx.getVar(128);
      this.UO = this.gCtx.getVar(192);
      this.Ab = this.gCtx.getVar(256);
      this.rl = this.gCtx.getVar(320);
      this.z = this.gCtx.getVar(384);
      this.Ek = this.gCtx.getVar(448);
      this.fI = this.gCtx.getVar(1280);
      this.WR = this.gCtx.getVar(1312);
      this.NS = this.gCtx.getVar(1344);
      this.vP = this.gCtx.getVar(1376);
      this.xC = this.gCtx.getVar(1408);
      this.ED = this.gCtx.getVar(1440);
      this.hK = this.gCtx.getVar(1088);
      this.UH = this.Sc.part(8);
      this.VD = this.Sc.slice(8, 16);
      this.Xs = this.Sc.part(16);
      this.KV = this.ah.part(8);
      this.FK = this.ah.slice(8, 16);
      this.Bi = this.ah.part(16);
      this.wQ = this.eP.part(8);
      this.PZ = this.eP.slice(8, 16);
      this.Ip = this.eP.part(16);
      this.Fm = this.UO.part(8);
      this.FM = this.UO.slice(8, 16);
      this.Wn = this.UO.part(16);
      this.Er = this.gCtx.getVar(1024);
      this.FE = this.gCtx.getVar(1026);
      this.Aj = this.gCtx.getVar(1028);
      this.EX = this.gCtx.getVar(1030);
      this.LM = this.gCtx.getVar(1031);
      this.mv = this.gCtx.getVar(1032);
      this.sO = this.gCtx.getVar(1033);
      this.os = this.gCtx.getVar(1034);
      this.Cu = this.gCtx.getVar(1035);
      this.hZ = this.gCtx.getVar(1036);
      this.UW = this.gCtx.getVar(1038);
      this.PR = this.gCtx.getVar(1040);
      this.cX = this.gCtx.getVar(1041);
      this.DQ = this.gCtx.getVar(1042);
      this.ZN = this.gCtx.getVar(1043);
      this.OB = this.gCtx.getVar(1044);
      this.pF = this.gCtx.getVar(1045);
      this.mK = this.gCtx.createVirtualRegister(65536, "tmp", this.regNormalBitsize);
      this.pW = this.gCtx.createVirtualRegister("tmp2x", 2 * this.regNormalBitsize);
      this.kk = this.gCtx.createVirtualRegister("tmp1", 1);
      this.Rh = this.gCtx.createVirtualRegister("tmp8", 8);
      this.vv = this.gCtx.createVirtualRegister("tmp16", 16);
      this.fn = this.gCtx.createVirtualRegister("tmp32", 32);
      this.AS = this.gCtx.createVirtualRegister("tmp64", 64);
      this.wF = this.gCtx.createVirtualRegister("tmp128", 128);
      this.hF = new IEVar[16];
      this.hF[0] = this.Rh;

      for (int var6 = 1; var6 < this.hF.length; var6++) {
         this.hF[var6] = this.gCtx.createVirtualRegister("tmp8_" + var6, 8);
      }

      this.FA = new IEVar[16];
      this.FA[0] = this.vv;

      for (int var7 = 1; var7 < this.FA.length; var7++) {
         this.FA[var7] = this.gCtx.createVirtualRegister("tmp16_" + var7, 16);
      }

      this.IK = new IEVar[8];
      this.IK[0] = this.fn;

      for (int var8 = 1; var8 < this.IK.length; var8++) {
         this.IK[var8] = this.gCtx.createVirtualRegister("tmp32_" + var8, 32);
      }

      this.DM = new IEVar[8];
      this.DM[0] = this.AS;

      for (int var9 = 1; var9 < this.DM.length; var9++) {
         this.DM[var9] = this.gCtx.createVirtualRegister("tmp64_" + var9, 64);
      }

      this.Bc = this.gCtx.createImm(0L, 1);
      this.OI = this.gCtx.createImm(1L, 1);
      this.Bf = this.gCtx.createImm(0L, this.regNormalBitsize);
      this.Pe = this.gCtx.createImm(1L, this.regNormalBitsize);
      this.ck = this.gCtx.createImm(-1L, this.regNormalBitsize);
      this.RW = this.gCtx.createImm(0L, 8);
      this.e = this.gCtx.createImm(1L, 8);
      this.xM = this.gCtx.createImm(-1L, 8);
      this.kU = this.gCtx.createImm(0L, 16);
      this.Kq = this.gCtx.createImm(1L, 16);
      this.go = this.gCtx.createImm(-1L, 16);
      this.JF = this.gCtx.createImm(0L, 32);
      this.Nq = this.gCtx.createImm(1L, 32);
      this.pg = this.gCtx.createImm(-1L, 32);
      this.gj = this.gCtx.createImm(0L, 64);
      this.ZD = this.gCtx.createImm(1L, 64);
      this.DL = this.gCtx.createImm(-1L, 64);
      this.gy = this.gCtx.createVirtualRegister(73728, "fpuTmp", 80);
      this.pt = new IEVar[8];

      for (int var10 = 0; var10 < 8; var10++) {
         this.pt[var10] = this.gCtx.createRegister(1536 + var10 * 80, "st" + var10, 80);
      }

      this.uE = new ajp("st", 80, this.pt);
      this.Um = new IEVar[8];

      for (int var11 = 0; var11 < 8; var11++) {
         this.Um[var11] = this.gCtx.createRegister(2592 + var11 * 2, "tw" + var11, 2);
      }

      this.Ta = new ajp("tw", 2, this.Um);
      this.tH = this.gCtx.createRegister(2576, "swIE", 1);
      this.Gm = this.gCtx.createRegister(2577, "swDE", 1);
      this.Br = this.gCtx.createRegister(2578, "swZE", 1);
      this.IE = this.gCtx.createRegister(2579, "swOE", 1);
      this.AU = this.gCtx.createRegister(2580, "swUE", 1);
      this.jS = this.gCtx.createRegister(2581, "swPE", 1);
      this.KK = this.gCtx.createRegister(2582, "swSF", 1);
      this.oB = this.gCtx.createRegister(2583, "swES", 1);
      this.Rq = this.gCtx.createRegister(2584, "swC0", 1);
      this.LL = this.gCtx.createRegister(2585, "swC1", 1);
      this.rC = this.gCtx.createRegister(2586, "swC2", 1);
      this.So = this.gCtx.createRegister(2587, "swTop", 3);
      this.be = this.gCtx.createRegister(2590, "swC3", 1);
      this.Xh = this.gCtx.createRegister(2591, "swB", 1);
      this.sz = this.gCtx.createRegister(2560, "cwIM", 1);
      this.QQ = this.gCtx.createRegister(2561, "cwDM", 1);
      this.eE = this.gCtx.createRegister(2562, "cwZM", 1);
      this.dM = this.gCtx.createRegister(2563, "cwOM", 1);
      this.EM = this.gCtx.createRegister(2564, "cwUM", 1);
      this.fD = this.gCtx.createRegister(2565, "cwPM", 1);
      this.ii = this.gCtx.createRegister(2568, "cwPC", 2);
      this.Gu = this.gCtx.createRegister(2570, "cwRC", 2);
      this.hw = this.gCtx.createRegister(2572, "cwX", 1);
      this.qG = this.gCtx.createRegister(2608, "fpuLastInsn", 48);
      this.yi = this.gCtx.createRegister(2656, "fpuLastData", 48);
      this.zK = this.gCtx.createRegister(2704, "fpuOpcode", 11);
      this.ve = this.gCtx.createRegister(20480, "mxcsr", 32);
      if (var1.getMode() != 32 && var1.getMode() != 64) {
         this.LA = new IEGeneric[0];
      } else {
         byte var12 = 32;
         this.LA = new IEGeneric[var12];

         for (int var13 = 0; var13 < var12; var13++) {
            this.LA[var13] = this.gCtx.createRegister(4096 + var13 * 512, Strings.f("xmm%d", var13), 128);
         }
      }

      this.pC();
      this.yv = new ReferenceCounter();
   }

   public IEVar pC(int var1) {
      return (IEVar)this.A(var1, null);
   }

   public IEVar[] A(int var1) {
      switch (var1) {
         case 8:
            return this.hF;
         case 16:
            return this.FA;
         case 32:
            return this.IK;
         case 64:
            return this.DM;
         default:
            throw new RuntimeException();
      }
   }

   public IEVar pC(int var1, List var2) {
      if (var1 < this.regNormalBitsize) {
         var2.add(this.ctx.createAssign(this.mK, EUtil.zero(this.regNormalBitsize)));
      }

      return this.mK;
   }

   public IEGeneric A(int var1, List var2) {
      if (var1 == this.regNormalBitsize) {
         return this.mK;
      } else if (var1 == 2 * this.regNormalBitsize) {
         return this.pW;
      } else {
         switch (var1) {
            case 1:
               return this.kk;
            case 8:
               return this.Rh;
            case 16:
               return this.vv;
            case 32:
               return this.fn;
            case 64:
               return this.AS;
            case 128:
               return this.wF;
            default:
               if (var2 == null) {
                  throw new RuntimeException();
               } else {
                  IEVar var3;
                  if (var1 < this.regNormalBitsize) {
                     var3 = this.mK;
                  } else {
                     if (var1 >= 2 * this.regNormalBitsize) {
                        throw new RuntimeException();
                     }

                     var3 = this.pW;
                  }

                  var2.add(this.ctx.createAssign(var3, EUtil.zero(var3.getBitsize())));
                  return var3.part(var1);
               }
         }
      }
   }

   IEVar kS(int var1) {
      switch (var1) {
         case 1:
            return this.kk;
         case 8:
            return this.Rh;
         case 16:
            return this.vv;
         case 32:
            return this.fn;
         case 64:
            return this.AS;
         default:
            throw new RuntimeException();
      }
   }

   IEVar wS(int var1) {
      switch (var1) {
         case 8:
            return this.hF[1];
         case 16:
            return this.FA[1];
         case 32:
            return this.IK[1];
         case 64:
            return this.DM[1];
         default:
            throw new RuntimeException();
      }
   }

   IEVar UT(int var1) {
      switch (var1) {
         case 8:
            return this.hF[2];
         case 16:
            return this.FA[2];
         case 32:
            return this.IK[2];
         case 64:
            return this.DM[2];
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
            return this.gCtx.createGroupElt(this.uE, this.So);
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
   private void pC() {
      this.IQ = true;
      this.A = new eW(this);
      this.kS = new Sb(this);
      this.wS = new Hv(this);
      this.UT = new jM(this);
      this.E = new Sf(this);
      this.sY = new B(this);
      this.ys = new co(this);
      this.ld = new m(this);
      this.gp = new dE(this);
      this.Gg = this.getProgramCounter().getId();
      this.kQ = this.getStackPointer().getId();
      this.te = this.gCtx.getAddressBitsize() / 8;
      this.B = new HashSet();
   }

   public IEGeneric pC(IEGeneric var1) {
      switch (var1.getBitsize()) {
         case 1:
            return this.Bc;
         case 8:
            return this.RW;
         case 16:
            return this.kU;
         case 32:
            return this.JF;
         default:
            return this.gj.part(var1.getBitsize());
      }
   }

   public IEGeneric A(IEGeneric var1) {
      switch (var1.getBitsize()) {
         case 1:
            return this.OI;
         case 8:
            return this.e;
         case 16:
            return this.Kq;
         case 32:
            return this.Nq;
         default:
            return this.ZD.part(var1.getBitsize());
      }
   }

   @Override
   public IEMem createStackMemoryAccess(IEGeneric var1, int var2) {
      return this.gCtx.createMem(this.NS, var1, var2);
   }

   public IEMem pC(int var1, IEGeneric var2, int var3) {
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

   public void pC(ConverterInstructionEntry var1) {
      this.kS.pC(var1, false, false);
   }

   @Override
   public IEVar getProgramCounter() {
      return this.hK;
   }

   @Override
   public IEVar getStackPointer() {
      return this.Ab;
   }

   @Override
   public void setCurrentContext(IERoutineContext var1) {
      super.setCurrentContext(var1);
      this.A.A = var1;
      this.kS.A = var1;
      this.wS.A = var1;
      this.UT.A = var1;
      this.E.A = var1;
      this.sY.A = var1;
      this.ys.A = var1;
      this.ld.A = var1;
      this.gp.A = var1;
   }

   @Override
   protected void convertBlock(BasicBlock var1, List var2) {
      long var3 = var1.getFirstAddress();
      long var5 = var3;
      ArrayList var7 = new ArrayList();
      ConverterInstructionEntry var8 = new ConverterInstructionEntry();
      var8.r = var7;
      vh var9 = null;

      try {
         int var10 = 0;

         for (int var11 = var1.size(); var10 < var11; var10++) {
            var9 = (vh)var1.get(var10);
            int var12 = var2.size();
            var8.insn = var9;
            var8.address = var5;
            var8.irAddress = var12;
            var7.clear();
            var8.r = var7;
            var8.ph1Insn = var10 < var11 - 1 ? (vh)var1.get(var10 + 1) : null;
            this.convertInstruction(var8);
            EUtil.setLowerLevelAddress(var5, var7);
            var2.addAll(var7);
            var5 += var9.getSize();
         }
      } catch (Throwable var16) {
         zR.error(S.L("Error: Instruction cannot be converted: %s %s"), Formatter.byteArrayToHex(var9.getCode()), var9.format(var5));
         zR.catchingSilent(var16);
         throw var16;
      } finally {
         ;
      }
   }

   @Override
   public void convertInstruction(ConverterInstructionEntry var1) {
      this.MZ++;

      try {
         if (!this.attemptConversionByExtension(var1) && !this.pC(var1, null, true)) {
            this.A(var1);
            this.ET++;
            UnsupportedConversionException var2 = new UnsupportedConversionException("Cannot convert instruction: " + var1.insn);
            if (Licensing.isDebugBuild()) {
               throw var2;
            }

            acj.pC(var2);
         }
      } catch (RuntimeException var4) {
         if (Licensing.isDebugBuild()) {
            throw var4;
         }

         String var3 = Strings.ff("%s @ 0x%X: %s", ((vh)var1.insn).getMnemonic(), var1.address, Formatter.byteArrayToHex(((vh)var1.insn).getCode()));
         JebCoreService.notifySilentExceptionToClient(var4, Maps.toMap("instruction", var3));
         this.fH++;
         if (this.yv != null) {
            this.yv.inc(((vh)var1.insn).getMnemonic());
         }

         this.A(var1);
      }
   }

   private int pC(String var1) {
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

   private boolean pC(ConverterInstructionEntry var1, String var2, boolean var3) {
      vh var4 = (vh)var1.insn;
      if (var2 == null) {
         var2 = var4.getMnemonic();
      }

      boolean var5 = false;
      if (pC && var1.ph1Insn != null) {
         int var6 = this.pC(var2);
         if (var6 >= 0) {
            String var7 = ((vh)var1.ph1Insn).getMnemonic();
            int var8 = this.pC(var7);
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
               this.E.pC(var1);
            }
            break;
         case "add":
            this.A.pC(var1, var5);
            break;
         case "sub":
            this.A.kS(var1, var5);
            break;
         case "adc":
            this.A.A(var1, var5);
            break;
         case "sbb":
            this.A.wS(var1, var5);
            break;
         case "inc":
            this.A.E(var1, var5);
            break;
         case "dec":
            this.A.sY(var1, var5);
            break;
         case "neg":
            this.A.ys(var1, var5);
            break;
         case "cmp":
            this.A.UT(var1, var5);
            break;
         case "mul":
            this.A.ld(var1, var5);
            break;
         case "imul":
            this.A.gp(var1, var5);
            break;
         case "div":
            this.A.oT(var1, var5);
            break;
         case "idiv":
            this.A.fI(var1, var5);
            break;
         case "xadd":
            this.A.pC(var1);
            break;
         case "cmpxchg":
            this.A.A(var1);
            break;
         case "aaa":
            this.A.kS(var1);
            break;
         case "aas":
            this.A.wS(var1);
            break;
         case "aam":
            this.A.UT(var1);
            break;
         case "aad":
            this.A.E(var1);
            break;
         case "daa":
            this.A.sY(var1);
            break;
         case "das":
            this.A.ys(var1);
            break;
         case "sal":
         case "shl":
            this.sY.pC(var1);
            break;
         case "shr":
            this.sY.A(var1);
            break;
         case "sar":
            this.sY.kS(var1);
            break;
         case "rol":
            this.sY.wS(var1);
            break;
         case "ror":
            this.sY.E(var1);
            break;
         case "rcl":
            this.sY.UT(var1);
            break;
         case "rcr":
            this.sY.sY(var1);
            break;
         case "shld":
            this.sY.ys(var1);
            break;
         case "shrd":
            this.sY.ld(var1);
            break;
         case "or":
            this.UT.A(var1, var5);
            break;
         case "xor":
            this.UT.kS(var1, var5);
            break;
         case "and":
            this.UT.pC(var1, var5);
            break;
         case "not":
            this.UT.pC(var1);
            break;
         case "test":
            this.UT.wS(var1, var5);
            break;
         case "mov":
            this.wS.pC(var1);
            break;
         case "lea":
            this.wS.A(var1);
            break;
         case "movsx":
         case "movsxd":
            this.wS.kS(var1);
            break;
         case "movzx":
            this.wS.wS(var1);
            break;
         case "xchg":
            this.wS.UT(var1);
            break;
         case "cwd":
            this.wS.E(var1);
            break;
         case "cdq":
            this.wS.sY(var1);
            break;
         case "cqo":
            this.wS.ys(var1);
            break;
         case "cbw":
            this.wS.ld(var1);
            break;
         case "cwde":
            this.wS.gp(var1);
            break;
         case "cdqe":
            this.wS.oT(var1);
            break;
         case "clc":
            this.wS.fI(var1);
            break;
         case "stc":
            this.wS.WR(var1);
            break;
         case "cli":
            this.wS.xC(var1);
            break;
         case "sti":
            this.wS.ED(var1);
            break;
         case "cld":
            this.wS.NS(var1);
            break;
         case "std":
            this.wS.vP(var1);
            break;
         case "cmc":
            this.wS.Sc(var1);
            break;
         case "movsb":
         case "movsw":
         case "movsq":
         case "movs":
            this.wS.ah(var1);
            break;
         case "stosb":
         case "stosw":
         case "stosd":
         case "stosq":
         case "stos":
            this.wS.UO(var1);
            break;
         case "lodsb":
         case "lodsw":
         case "lodsd":
         case "lodsq":
         case "lods":
            this.wS.eP(var1);
            break;
         case "cmpsb":
         case "cmpsw":
         case "cmpsd":
         case "cmpsq":
         case "cmps":
            this.wS.Ab(var1);
            break;
         case "scasb":
         case "scasw":
         case "scasd":
         case "scasq":
         case "scas":
            this.wS.rl(var1);
            break;
         case "bsf":
            this.wS.z(var1);
            break;
         case "bsr":
            this.wS.Ek(var1);
            break;
         case "bswap":
            this.wS.hK(var1);
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
            this.wS.Er(var1);
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
            this.wS.FE(var1);
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
            this.kS.pC(var1);
            break;
         case "loop":
         case "loope":
         case "loopne":
            this.kS.A(var1);
            break;
         case "jmp":
            this.kS.kS(var1);
            break;
         case "call":
            this.kS.wS(var1);
            break;
         case "ret":
            this.kS.UT(var1);
            break;
         case "push":
            this.ys.pC(var1);
            break;
         case "pop":
            this.ys.A(var1);
            break;
         case "pusha":
         case "pushad":
            this.ys.kS(var1);
            break;
         case "popa":
         case "popad":
            this.ys.wS(var1);
            break;
         case "pushf":
         case "pushfd":
         case "pushfq":
            this.ys.UT(var1);
            break;
         case "popf":
         case "popfd":
         case "popfq":
            this.ys.E(var1);
            break;
         case "enter":
            this.ys.sY(var1);
            break;
         case "leave":
            this.ys.ys(var1);
            break;
         case "lahf":
            this.E.A(var1);
            break;
         case "sahf":
            this.E.kS(var1);
            break;
         case "bt":
            this.E.wS(var1);
            break;
         case "btc":
            this.E.UT(var1);
            break;
         case "btr":
            this.E.E(var1);
            break;
         case "bts":
            this.E.sY(var1);
            break;
         case "xlat":
         case "xlatb":
            this.E.ys(var1);
            break;
         case "salc":
            this.E.ld(var1);
            break;
         case "fnop":
         case "fwait":
            this.ld.pC(var1);
            break;
         case "fld":
            this.ld.wS(var1);
            break;
         case "fst":
            this.ld.UT(var1);
            break;
         case "fstp":
            this.ld.E(var1);
            break;
         case "fild":
            this.ld.sY(var1);
            break;
         case "fist":
            this.ld.ys(var1);
            break;
         case "fistp":
            this.ld.ld(var1);
            break;
         case "fxch":
            this.ld.gp(var1);
            break;
         case "fldz":
            this.ld.pC(var1, 0.0);
            break;
         case "fld1":
            this.ld.pC(var1, 1.0);
            break;
         case "fldpi":
            this.ld.pC(var1, Math.PI);
            break;
         case "fldl2t":
            this.ld.pC(var1, kt);
            break;
         case "fldl2e":
            this.ld.pC(var1, Yw);
            break;
         case "fldlg2":
            this.ld.pC(var1, uD);
            break;
         case "fldln2":
            this.ld.pC(var1, ZY);
            break;
         case "fadd":
            this.ld.pC(var1, OperationType.FADD, false, false, false);
            break;
         case "faddp":
            this.ld.pC(var1, OperationType.FADD, true, false, false);
            break;
         case "fiadd":
            this.ld.pC(var1, OperationType.FADD, false, true, false);
            break;
         case "fsub":
            this.ld.pC(var1, OperationType.FSUB, false, false, false);
            break;
         case "fsubp":
            this.ld.pC(var1, OperationType.FSUB, true, false, false);
            break;
         case "fisub":
            this.ld.pC(var1, OperationType.FSUB, false, true, false);
            break;
         case "fmul":
            this.ld.pC(var1, OperationType.FMUL, false, false, false);
            break;
         case "fmulp":
            this.ld.pC(var1, OperationType.FMUL, true, false, false);
            break;
         case "fimul":
            this.ld.pC(var1, OperationType.FMUL, false, true, false);
            break;
         case "fdiv":
            this.ld.pC(var1, OperationType.FDIV, false, false, false);
            break;
         case "fdivp":
            this.ld.pC(var1, OperationType.FDIV, true, false, false);
            break;
         case "fidiv":
            this.ld.pC(var1, OperationType.FDIV, false, true, false);
            break;
         case "fsubr":
            this.ld.pC(var1, OperationType.FSUB, false, false, true);
            break;
         case "fsubrp":
            this.ld.pC(var1, OperationType.FSUB, true, false, true);
            break;
         case "fisubr":
            this.ld.pC(var1, OperationType.FSUB, false, true, true);
            break;
         case "fdivr":
            this.ld.pC(var1, OperationType.FDIV, false, false, true);
            break;
         case "fdivrp":
            this.ld.pC(var1, OperationType.FDIV, true, false, true);
            break;
         case "fidivr":
            this.ld.pC(var1, OperationType.FDIV, false, true, true);
            break;
         case "fdecstp":
            this.ld.A(var1);
            break;
         case "fincstp":
            this.ld.kS(var1);
            break;
         case "fptan":
            this.ld.oT(var1);
            break;
         case "fxtract":
            this.ld.fI(var1);
            break;
         case "fsincos":
            this.ld.fI(var1);
            break;
         case "fxsave":
         case "fxsave64":
            this.pC(var1, var2, 2);
            break;
         case "fxrstor":
         case "fxrstor64":
            this.pC(var1, var2, 0);
            break;
         case "emms":
            this.ld.NS(var1);
            break;
         case "movd":
            this.ld.A(var1, 32);
            break;
         case "movq":
            this.ld.A(var1, 64);
            break;
         case "packsswb":
            this.ld.pC(var1, true, 16);
            break;
         case "packssdw":
            this.ld.pC(var1, true, 32);
            break;
         case "packuswb":
            this.ld.pC(var1, false, 16);
            break;
         case "packusdw":
            this.ld.pC(var1, false, 32);
            break;
         case "paddb":
            this.ld.pC(var1, 8, OperationType.ADD);
            break;
         case "paddw":
            this.ld.pC(var1, 16, OperationType.ADD);
            break;
         case "paddd":
            this.ld.pC(var1, 32, OperationType.ADD);
            break;
         case "paddq":
            this.ld.pC(var1, 64, OperationType.ADD);
            break;
         case "paddsb":
            this.ld.pC(var1, 8, OperationType.ADD_SSAT);
            break;
         case "paddsw":
            this.ld.pC(var1, 16, OperationType.ADD_SSAT);
            break;
         case "paddusb":
            this.ld.pC(var1, 8, OperationType.ADD_USAT);
            break;
         case "paddusw":
            this.ld.pC(var1, 16, OperationType.ADD_USAT);
            break;
         case "psubb":
            this.ld.pC(var1, 8, OperationType.SUB);
            break;
         case "psubw":
            this.ld.pC(var1, 16, OperationType.SUB);
            break;
         case "psubd":
            this.ld.pC(var1, 32, OperationType.SUB);
            break;
         case "psubq":
            this.ld.pC(var1, 64, OperationType.SUB);
            break;
         case "psubsb":
            this.ld.pC(var1, 8, OperationType.SUB_SSAT);
            break;
         case "psubsw":
            this.ld.pC(var1, 16, OperationType.SUB_SSAT);
            break;
         case "psubusb":
            this.ld.pC(var1, 8, OperationType.SUB_USAT);
            break;
         case "psubusw":
            this.ld.pC(var1, 16, OperationType.SUB_USAT);
            break;
         case "pand":
            this.ld.pC(var1, 0);
            break;
         case "pandn":
            this.ld.pC(var1, 1);
            break;
         case "por":
            this.ld.pC(var1, 2);
            break;
         case "pxor":
            this.ld.pC(var1, 3);
            break;
         case "pcmpeqb":
            this.ld.pC(var1, 8, OperationType.LOG_EQ);
            break;
         case "pcmpeqw":
            this.ld.pC(var1, 16, OperationType.LOG_EQ);
            break;
         case "pcmpeqd":
            this.ld.pC(var1, 32, OperationType.LOG_EQ);
            break;
         case "pcmpgtb":
            this.ld.pC(var1, 8, OperationType.GT_S);
            break;
         case "pcmpgtw":
            this.ld.pC(var1, 16, OperationType.GT_S);
            break;
         case "pcmpgtd":
            this.ld.pC(var1, 32, OperationType.GT_S);
            break;
         case "pmaddwd":
            this.pC(var1, var2, 1);
            break;
         case "pmulhw":
            this.ld.pC(var1, 16, OperationType.MUL2_S, true);
            break;
         case "pmullw":
            this.ld.pC(var1, 16, OperationType.MUL2_S, false);
            break;
         case "pmulhuw":
            this.ld.pC(var1, 16, OperationType.MUL2_U, true);
            break;
         case "psllw":
            this.ld.pC(var1, 16, OperationType.SHL);
            break;
         case "pslld":
            this.ld.pC(var1, 32, OperationType.SHL);
            break;
         case "psllq":
            this.ld.pC(var1, 64, OperationType.SHL);
            break;
         case "psraw":
            this.ld.pC(var1, 16, OperationType.SAR);
            break;
         case "psrad":
            this.ld.pC(var1, 32, OperationType.SAR);
            break;
         case "psraq":
            this.ld.pC(var1, 64, OperationType.SAR);
            break;
         case "psrlw":
            this.ld.pC(var1, 16, OperationType.SHR);
            break;
         case "psrld":
            this.ld.pC(var1, 32, OperationType.SHR);
            break;
         case "psrlq":
            this.ld.pC(var1, 64, OperationType.SHR);
            break;
         case "punpckhbw":
            this.ld.A(var1, true, 8);
            break;
         case "punpckhwd":
            this.ld.A(var1, true, 16);
            break;
         case "punpckhdq":
            this.ld.A(var1, true, 32);
            break;
         case "punpcklbw":
            this.ld.A(var1, false, 8);
            break;
         case "punpcklwd":
            this.ld.A(var1, false, 16);
            break;
         case "punpckldq":
            this.ld.A(var1, false, 32);
            break;
         case "maskmovq":
            this.ld.vP(var1);
            break;
         case "movntq":
            this.kS(var1);
            break;
         case "pshufw":
            this.pC(var1, var2, 2);
            break;
         case "pinsrw":
            this.pC(var1, var2, 2);
            break;
         case "pextrw":
            this.pC(var1, var2, 2);
            break;
         case "pmovmskb":
            this.pC(var1, var2, 2);
            break;
         case "pminub":
            this.pC(var1, var2, 1);
            break;
         case "pminuw":
            this.pC(var1, var2, 1);
            break;
         case "pminsb":
            this.pC(var1, var2, 1);
            break;
         case "pminsw":
            this.pC(var1, var2, 1);
            break;
         case "pmaxub":
            this.pC(var1, var2, 1);
            break;
         case "pmaxuw":
            this.pC(var1, var2, 1);
            break;
         case "pmaxsb":
            this.pC(var1, var2, 1);
            break;
         case "pmaxsw":
            this.pC(var1, var2, 1);
            break;
         case "pmaxsd":
            this.pC(var1, var2, 1);
            break;
         case "pmaxsq":
            this.pC(var1, var2, 1);
            break;
         case "pavgb":
            this.pC(var1, var2, 1);
            break;
         case "pavgw":
            this.pC(var1, var2, 1);
            break;
         case "psadbw":
            this.pC(var1, var2, 1);
            break;
         case "pmuludq":
            this.ld.pC(var1, 32, OperationType.MUL2_U);
            break;
         case "psignb":
         case "psignw":
         case "psignd":
            this.pC(var1, var2, 1);
            break;
         case "pshufb":
            this.pC(var1, var2, 1);
            break;
         case "pmulhrsw":
            this.pC(var1, var2, 1);
            break;
         case "pmaddubsw":
            this.pC(var1, var2, 1);
            break;
         case "phsubw":
         case "phsubd":
         case "phsubsw":
         case "phaddw":
         case "phaddd":
         case "phaddsw":
            this.pC(var1, var2, 1);
            break;
         case "palignr":
            this.pC(var1, var2, 1);
            break;
         case "pabsb":
         case "pabsw":
         case "pabsd":
         case "pabsq":
            this.pC(var1, var2, 2);
            break;
         case "movss":
            this.gp.pC(var1);
            break;
         case "movups":
            this.gp.pC(var1, 128, 128);
            break;
         case "movlps":
            this.gp.pC(var1, 64, 128);
            break;
         case "movhlps":
            this.gp.pC(var1, 64);
            break;
         case "movhps":
         case "movlhps":
            this.gp.A(var1, 64);
            break;
         case "movaps":
            this.gp.pC(var1, 128, 128);
            break;
         case "movntps":
            this.gp.pC(var1, 128, 128);
            break;
         case "movmskps":
            this.pC(var1, var2, 2);
            break;
         case "unpcklps":
         case "unpckhps":
            this.pC(var1, var2, 1);
            break;
         case "cvtpi2ps":
            this.gp.wS(var1);
            break;
         case "cvtps2pi":
            this.gp.pC(var1, false);
            break;
         case "cvtsi2ss":
            this.gp.UT(var1);
            break;
         case "cvtss2si":
            this.gp.A(var1, false);
            break;
         case "cvttps2pi":
            this.gp.pC(var1, true);
            break;
         case "cvttss2si":
            this.gp.A(var1, true);
            break;
         case "addss":
            this.gp.pC(var1, 0, 1, 32);
            break;
         case "addps":
            this.gp.pC(var1, 0, 4, 32);
            break;
         case "subss":
            this.gp.pC(var1, 1, 1, 32);
            break;
         case "subps":
            this.gp.pC(var1, 1, 4, 32);
            break;
         case "mulss":
            this.gp.pC(var1, 2, 1, 32);
            break;
         case "mulps":
            this.gp.pC(var1, 2, 4, 32);
            break;
         case "divss":
            this.gp.pC(var1, 3, 1, 32);
            break;
         case "divps":
            this.gp.pC(var1, 3, 4, 32);
            break;
         case "andps":
            this.gp.pC(var1, 4, 4, 32);
            break;
         case "orps":
            this.gp.pC(var1, 5, 4, 32);
            break;
         case "xorps":
            this.gp.pC(var1, 6, 4, 32);
            break;
         case "andnps":
            this.gp.pC(var1, 7, 4, 32);
            break;
         case "minss":
            this.gp.pC(var1, 8, 1, 32);
            break;
         case "minps":
            this.gp.pC(var1, 8, 4, 32);
            break;
         case "maxss":
            this.gp.pC(var1, 9, 1, 32);
            break;
         case "maxps":
            this.gp.pC(var1, 9, 4, 32);
            break;
         case "sqrtps":
         case "sqrtss":
         case "rsqrtps":
         case "rsqrtss":
         case "rcpps":
         case "rcpss":
            this.pC(var1, var2, 2);
            break;
         case "cmpeqss":
            this.gp.pC(var1, 10, 1, 32);
            break;
         case "cmpeqps":
            this.gp.pC(var1, 10, 4, 32);
            break;
         case "cmpltss":
            this.gp.pC(var1, 11, 1, 32);
            break;
         case "cmpltps":
            this.gp.pC(var1, 11, 4, 32);
            break;
         case "cmpless":
            this.gp.pC(var1, 12, 1, 32);
            break;
         case "cmpleps":
            this.gp.pC(var1, 12, 4, 32);
            break;
         case "cmpunordss":
            this.gp.pC(var1, 13, 1, 32);
            break;
         case "cmpunordps":
            this.gp.pC(var1, 13, 4, 32);
            break;
         case "cmpneqss":
            this.gp.pC(var1, 14, 1, 32);
            break;
         case "cmpneqps":
            this.gp.pC(var1, 14, 4, 32);
            break;
         case "cmpnltss":
            this.gp.pC(var1, 15, 1, 32);
            break;
         case "cmpnltps":
            this.gp.pC(var1, 15, 4, 32);
            break;
         case "cmpnless":
            this.gp.pC(var1, 16, 1, 32);
            break;
         case "cmpnleps":
            this.gp.pC(var1, 16, 4, 32);
            break;
         case "cmpordss":
            this.gp.pC(var1, 17, 1, 32);
            break;
         case "cmpordps":
            this.gp.pC(var1, 17, 4, 32);
            break;
         case "comiss":
            this.gp.pC(var1, 32, true);
            break;
         case "ucomiss":
            this.gp.pC(var1, 32, false);
            break;
         case "ldmxcsr":
            this.gp.ld(var1);
            break;
         case "stmxcsr":
            this.gp.gp(var1);
            break;
         case "shufps":
            this.pC(var1, var2, 1);
            break;
         case "movnti":
         case "movntdq":
            this.kS(var1);
            break;
         case "movsd":
            this.gp.A(var1);
            break;
         case "movdqu":
         case "movdqa":
         case "movupd":
         case "movapd":
            this.gp.pC(var1, 128, 128);
            break;
         case "movntpd":
            this.kS(var1);
            break;
         case "movhpd":
            this.gp.kS(var1, 64);
            break;
         case "movlpd":
            this.gp.pC(var1, 64, 128);
            break;
         case "movmskpd":
            this.gp.kS(var1);
            break;
         case "unpcklpd":
         case "unpckhpd":
            this.pC(var1, var2, 1);
            break;
         case "addsd":
            this.gp.pC(var1, 0, 1, 64);
            break;
         case "addpd":
            this.gp.pC(var1, 0, 2, 64);
            break;
         case "subsd":
            this.gp.pC(var1, 1, 1, 64);
            break;
         case "subpd":
            this.gp.pC(var1, 1, 2, 64);
            break;
         case "mulsd":
            this.gp.pC(var1, 2, 1, 64);
            break;
         case "mulpd":
            this.gp.pC(var1, 2, 2, 64);
            break;
         case "divsd":
            this.gp.pC(var1, 3, 1, 64);
            break;
         case "divpd":
            this.gp.pC(var1, 3, 2, 64);
            break;
         case "andpd":
            this.gp.pC(var1, 4, 2, 64);
            break;
         case "orpd":
            this.gp.pC(var1, 5, 2, 64);
            break;
         case "xorpd":
            this.gp.pC(var1, 6, 2, 64);
            break;
         case "andnpd":
            this.gp.pC(var1, 7, 2, 64);
            break;
         case "minsd":
            this.gp.pC(var1, 8, 1, 64);
            break;
         case "minpd":
            this.gp.pC(var1, 8, 2, 64);
            break;
         case "maxsd":
            this.gp.pC(var1, 9, 1, 64);
            break;
         case "maxpd":
            this.gp.pC(var1, 9, 2, 64);
            break;
         case "sqrtpd":
         case "sqrtsd":
            this.pC(var1, var2, 2);
            break;
         case "cmpeqsd":
            this.gp.pC(var1, 10, 1, 64);
            break;
         case "cmpeqpd":
            this.gp.pC(var1, 10, 2, 64);
            break;
         case "cmpltsd":
            this.gp.pC(var1, 11, 1, 64);
            break;
         case "cmpltpd":
            this.gp.pC(var1, 11, 2, 64);
            break;
         case "cmplesd":
            this.gp.pC(var1, 12, 1, 64);
            break;
         case "cmplepd":
            this.gp.pC(var1, 12, 2, 64);
            break;
         case "cmpunordsd":
            this.gp.pC(var1, 13, 1, 64);
            break;
         case "cmpunordpd":
            this.gp.pC(var1, 13, 2, 64);
            break;
         case "cmpneqsd":
            this.gp.pC(var1, 14, 1, 64);
            break;
         case "cmpneqpd":
            this.gp.pC(var1, 14, 2, 64);
            break;
         case "cmpnltsd":
            this.gp.pC(var1, 15, 1, 64);
            break;
         case "cmpnltpd":
            this.gp.pC(var1, 15, 2, 64);
            break;
         case "cmpnlesd":
            this.gp.pC(var1, 16, 1, 64);
            break;
         case "cmpnlepd":
            this.gp.pC(var1, 16, 2, 64);
            break;
         case "cmpordsd":
            this.gp.pC(var1, 17, 1, 64);
            break;
         case "cmpordpd":
            this.gp.pC(var1, 17, 2, 64);
            break;
         case "comisd":
            this.gp.pC(var1, 64, true);
            break;
         case "ucomisd":
            this.gp.pC(var1, 64, false);
            break;
         case "shufpd":
            this.pC(var1, var2, 1);
            break;
         case "cvtdq2pd":
            this.gp.pC(var1, OperationType.INT2FP, 2, 32, 64, 128);
            break;
         case "cvtdq2ps":
            this.gp.pC(var1, OperationType.INT2FP, 4, 32, 32, 128);
            break;
         case "cvtpd2dq":
            this.gp.pC(var1, OperationType.FP2INT, 2, 64, 32, 128);
            break;
         case "cvtpd2pi":
            this.gp.pC(var1, OperationType.FP2INT, 2, 64, 32, 64);
            break;
         case "cvtpd2ps":
            this.gp.pC(var1, OperationType.FP2FP, 2, 64, 32, 128);
            break;
         case "cvtpi2pd":
            this.gp.pC(var1, OperationType.INT2FP, 2, 32, 64, 128);
            break;
         case "cvtps2dq":
            this.gp.pC(var1, OperationType.FP2INT, 4, 32, 32, 128);
            break;
         case "cvtps2pd":
            this.gp.pC(var1, OperationType.FP2FP, 2, 32, 64, 128);
            break;
         case "cvtsd2si":
            this.gp.kS(var1, false);
            break;
         case "cvtsd2ss":
            this.gp.sY(var1);
            break;
         case "cvtsi2sd":
            this.gp.E(var1);
            break;
         case "cvtss2sd":
            this.gp.ys(var1);
            break;
         case "cvttsd2si":
            this.gp.kS(var1, true);
            break;
         case "cvttpd2dq":
            this.gp.pC(var1, OperationType.FP2INT, 2, 64, 32, 128, true);
            break;
         case "cvttpd2pi":
            this.gp.pC(var1, OperationType.FP2INT, 2, 64, 32, 64, true);
            break;
         case "cvttps2dq":
            this.gp.pC(var1, OperationType.FP2INT, 4, 32, 32, 128, true);
            break;
         case "pshuflw":
         case "pshufhw":
         case "pshufd":
            this.pC(var1, var2, 2);
            break;
         case "movq2dq":
            this.gp.pC(var1, 64, 128);
            break;
         case "movdq2q":
            this.gp.pC(var1, 64, 0);
            break;
         case "pslldq":
            this.ld.pC(var1, 128, OperationType.SHL, null, 3);
            break;
         case "psrldq":
            this.ld.pC(var1, 128, OperationType.SHR, null, 3);
            break;
         case "punpcklqdq":
            this.ld.A(var1, false, 64);
            break;
         case "punpckhqdq":
            this.ld.A(var1, true, 64);
            break;
         case "maskmovdqu":
            this.gp.oT(var1);
            break;
         case "movntdqa":
            this.kS(var1);
            break;
         case "in":
            this.E.oT(var1);
            break;
         case "out":
            this.E.fI(var1);
            break;
         case "ins":
         case "insb":
         case "insw":
         case "insd":
            this.E.NS(var1);
            break;
         case "outs":
         case "outsb":
         case "outsw":
         case "outsd":
            this.E.xC(var1);
            break;
         case "arpl":
            this.E.gp(var1);
            break;
         case "les":
            this.E.pC(var1, this.fI, "les");
            break;
         case "lds":
            this.E.pC(var1, this.vP, "lds");
            break;
         case "lfs":
            this.E.pC(var1, this.xC, "lfs");
            break;
         case "lgs":
            this.E.pC(var1, this.ED, "lgs");
            break;
         case "lss":
            this.E.pC(var1, this.NS, "lss");
            break;
         case "int3":
            this.E.pC(var1, "int3");
            break;
         case "int1":
            this.E.pC(var1, "int1");
            break;
         case "int":
            this.E.pC(var1, "int");
            break;
         case "into":
            this.E.ED(var1);
            break;
         case "iret":
         case "iretd":
         case "iretq":
            this.E.Sc(var1);
            break;
         case "verr":
         case "verw":
            this.E.ah(var1);
            break;
         case "rdmsr":
            this.E.eP(var1);
            break;
         case "wrmsr":
            this.E.UO(var1);
            break;
         case "rdtsc":
            this.E.Ab(var1);
            break;
         case "rdtscp":
            this.E.Ab(var1);
            break;
         case "cpuid":
            this.E.z(var1);
            break;
         case "syscall":
            this.E.Ek(var1);
            break;
         case "rdpmc":
            this.E.rl(var1);
            break;
         case "xgetbv":
            this.E.hK(var1);
            break;
         case "xsetbv":
            this.E.Er(var1);
            break;
         case "swapgs":
            this.E.FE(var1);
            break;
         case "lar":
            this.E.Aj(var1);
            break;
         case "lsl":
            this.E.EX(var1);
            break;
         case "sldt":
         case "str":
         case "sgdt":
         case "sidt":
         case "smsw":
            this.pC(var1, var2, 2);
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
            this.pC(var1, var2, 0);
            break;
         case "pclmulqdq":
         case "pclmullqlqdq":
         case "pclmulhqlqdq":
         case "pclmullqhqdq":
         case "pclmulhqhqdq":
            this.pC(var1, var2, 1);
            break;
         case "lzcnt":
         case "popcnt":
            this.pC(var1, var2, 2, this.LM, this.EX, this.Cu, this.Er, this.Aj, this.FE);
            break;
         case "andn":
         case "bextr":
         case "blsi":
         case "blsmsk":
         case "blsr":
         case "tzcnt":
            this.pC(var1, var2, 2, this.LM, this.EX, this.Cu, this.Er, this.Aj, this.FE);
            break;
         case "bzhi":
            this.pC(var1, var2, 2, this.LM, this.EX, this.Cu, this.Er, this.Aj, this.FE);
            break;
         case "pdep":
         case "pext":
         case "rorx":
         case "sarx":
         case "shrx":
         case "shlx":
            this.pC(var1, var2, 2);
            break;
         case "mulx":
            this.E.mv(var1);
            break;
         case "blcfill":
         case "blci":
         case "blcic":
         case "blcmsk":
         case "blcs":
         case "t1msk":
         case "tzmsk":
            this.pC(var1, var2, 2, this.LM, this.EX, this.Cu, this.Er, this.Aj, this.FE);
            break;
         case "aesenc":
         case "aesenclast":
         case "aesdec":
         case "aesdeclast":
            this.pC(var1, var2, 1);
            break;
         case "aeskeygenassist":
         case "aesimc":
            this.pC(var1, var2, 2);
            break;
         case "sha1rnds4":
         case "sha1nexte":
         case "sha1msg1":
         case "sha1msg2":
         case "sha256msg1":
         case "sha256msg2":
            this.pC(var1, var2, 1);
            break;
         case "sha256rnds2":
            this.E.LM(var1);
            break;
         case "rdsspd":
         case "rdsspq":
         case "wrssd":
         case "wrssq":
         case "wrussd":
         case "wrussq":
            this.pC(var1, var2, 2);
            break;
         case "incsspd":
         case "incsspq":
            this.pC(var1, var2, 0);
            break;
         case "setssbsy":
         case "saveprevssp":
         case "rstorssp":
         case "clrssbsy":
            this.pC(var1, var2, 0);
            break;
         case "bndmk":
         case "bndmov":
         case "bndldx":
         case "bndstx":
            this.pC(var1, var2, 2);
            break;
         case "bndcl":
         case "bndcn":
         case "bndcu":
            this.pC(var1, var2, 0);
            break;
         case "vmxon":
         case "vmxoff":
         case "vmptrld":
         case "vmclear":
         case "vmwrite":
         case "vmcall":
         case "vmlaunch":
         case "vmresume":
            this.pC(var1, var2, 0);
            break;
         case "vmptrst":
         case "vmread":
            this.pC(var1, var2, 2);
            break;
         default:
            long var9 = var4.gp.kS;
            if ((var9 & 131072L) == 0L) {
               return false;
            }

            boolean var12 = this.ld.WR(var1);
            if (!var12) {
               return false;
            }
      }

      return true;
   }

   IEUntranslatedInstruction A(ConverterInstructionEntry var1) {
      return this.pC(var1, null, 0);
   }

   IEUntranslatedInstruction pC(ConverterInstructionEntry var1, String var2, int var3, IEVar... var4) {
      if (var2 == null) {
         var2 = ((vh)var1.insn).getMnemonic();
      }

      Assert.a(var3 >= 0 && var3 <= 2);
      int var5 = ((vh)var1.insn).A().length;
      ArrayList var6 = new ArrayList(var5);

      for (int var7 = var3 == 2 ? 1 : 0; var7 < var5; var7++) {
         IEGeneric var8 = this.pC((vh)var1.insn, var7, 0, var1.address);
         var6.add(var8);
      }

      IEUntranslatedInstruction var9 = this.ctx.createUntranslatedInstruction(var1.address, var2, (IEGeneric[])var6.toArray(new IEGeneric[var6.size()]));
      if (var3 != 0) {
         Assert.a(var5 > 0);
         IEGeneric var10 = this.pC((vh)var1.insn, 0, 0, var1.address);
         var9.setReturnExpression(var10);
      }

      if (var4.length > 0) {
         var9.addSideEffectDefinedVariable(var4);
      }

      var9.setBreakingFlow(((vh)var1.insn).getBreakingFlow(var1.address));
      var9.setRoutineCall(((vh)var1.insn).getRoutineCall(var1.address));
      var1.r.add(var9);
      return var9;
   }

   void kS(ConverterInstructionEntry var1) {
      IEGeneric var2 = this.pC((vh)var1.insn, 0, 0, var1.address);
      IEGeneric var3 = this.pC((vh)var1.insn, 1, 0, var1.address);
      Assert.a(var2.getBitsize() >= var3.getBitsize());
      var1.r.add(this.ctx.createAssign(var2.part(var3.getBitsize()), var3));
   }

   boolean pC(ConverterInstructionEntry var1, long var2) {
      if (!this.IQ) {
         return false;
      } else {
         this.IQ = false;

         boolean var4;
         try {
            var4 = this.attemptCallInliningByExtension(var1, var2);
         } finally {
            this.IQ = true;
         }

         return var4;
      }
   }

   IEGeneric pC(vh var1, int var2, int var3, long var4) {
      Av var6 = var1.A()[var2];
      return this.pC(var1, var6, var3, var4);
   }

   IEGeneric pC(vh var1, Av var2, int var3, long var4) {
      IEGeneric var6 = null;
      boolean var7 = false;
      if (var3 < 0) {
         var3 = -var3;
         var7 = true;
      }

      if (var2 instanceof lB var8) {
         int var9 = var8.getOperandType();
         switch (var9) {
            case 0:
               var6 = this.pC(var8.getOperandValue());
               if (MG.A(var8.getOperandValue()) == 2) {
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

                     var6 = this.ctx.createImm(var8.getOperandValue(var4), this.hK.getBitsize());
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
               IEGeneric var28 = this.pC(var8.getOperandValue());
               int var31 = MG.pC(var8.pC(var1));
               var6 = this.pC(var31, var28, var8.getOperandBitsize());
               break;
            case 5:
               IEImm var27 = this.ctx.createImm(var8.getOperandValue(), var1.ys());
               int var11 = MG.pC(var8.pC(var1));
               var6 = this.pC(var11, var27, var8.getOperandBitsize());
               break;
            case 4097:
               int var10 = var8.wS();
               return EUtil.imm(var10, var1.ys());
         }
      } else if (var2 instanceof QM var25) {
         IEGeneric var26 = null;
         IEGeneric var30 = null;
         IEImm var32 = null;
         IEImm var12 = null;
         int var13 = var1.ys();
         long var14 = var25.getMemoryBaseRegister();
         long var16 = var25.getMemoryIndexRegister();
         int var18 = var25.getMemoryScale();
         long var19 = var25.getMemoryDisplacement();
         if (var14 != -1L) {
            int var21 = MG.A(var14);
            if (var21 == 10) {
               Assert.a(var1.pC == 64);
               var19 = var19 + var4 + var1.getSize();
            } else {
               var26 = this.pC(var14);
               Assert.a(var26.getBitsize() == var13);
            }
         }

         if (var16 != -1L) {
            var30 = this.pC(var16);
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

         int var22 = MG.pC(var25.pC(var1));
         int var23 = var25.getOperandBitsize();
         if (var23 == 0) {
            return (IEGeneric)var33;
         }

         var6 = this.pC(var22, (IEGeneric)var33, var23);
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

   IEGeneric A(vh var1, int var2, int var3, long var4) {
      Av var6 = var1.A()[var2];
      int var7;
      if (var6 instanceof lB && ((lB)var6).getOperandType() == 1) {
         var7 = -var3;
      } else {
         var7 = var3;
      }

      return this.pC(var1, var2, var7, var4);
   }

   IEGeneric pC(long var1) {
      int var3 = MG.pC(var1);
      int var4 = MG.A(var1);
      int var5 = MG.kS(var1);
      int var6 = MG.wS(var1);
      if (var4 == 0) {
         String var9 = MG.A(var3, var4, this.regNormalBitsize, 0);
         IEVar var8 = this.gCtx.createRegister(0 + var3 * 64, var9, this.regNormalBitsize);
         return var8.slice(var6, var6 + var5);
      } else if (var4 == 2) {
         String var7 = MG.A(var3, var4, var5, var6);
         return this.gCtx.createRegister(1280 + var3 * 32, var7, var5);
      } else if (var4 == 3) {
         Assert.a(var5 == 80);
         return this.gCtx.createGroupElt(this.uE, EUtil.add(this.So, EUtil.imm(var3, 3)));
      } else if (var4 == 4) {
         return this.pt[var3].part(var5);
      } else if (var4 == 5) {
         return this.LA[var3].part(var5);
      } else {
         throw new RuntimeException("Intel register group not converted yet: " + var4);
      }
   }

   void pC(IEGeneric var1, IEGeneric var2, ConverterInstructionEntry var3) {
      this.pC(var1, var2, var3, true);
   }

   void pC(IEGeneric var1, IEGeneric var2, ConverterInstructionEntry var3, boolean var4) {
      if (var4
         && ((vh)var3.insn).getProcessorMode() == 64
         && var1 instanceof IESlice var5
         && var5.getBitsize() == 32
         && var5.getBitStart() == 0
         && var5.getWholeExpression() instanceof IEVar) {
         IEVar var6 = (IEVar)var5.getWholeExpression();
         if (var6.getId() >= 0 && var6.getId() <= 960) {
            var1 = var6;
            var2 = this.ctx.createCompose((IEGeneric)var2, this.JF);
         }
      }

      IEAssign var7 = this.ctx.createAssign((IEGeneric)var1, (IEGeneric)var2);
      var3.r.add(var7);
   }

   void pC(IEGeneric var1, List var2) {
      var2.add(this.kS(var1));
      var2.add(this.wS(var1));
      var2.add(this.UT(var1));
   }

   private IEAssign kS(IEGeneric var1) {
      IECond var2 = this.gCtx.createCond(var1, this.Bc, this.OI);
      return this.ctx.createAssign(this.EX, var2);
   }

   private IEAssign wS(IEGeneric var1) {
      IEGeneric var2 = var1.msb();
      return this.ctx.createAssign(this.LM, var2);
   }

   private IEAssign UT(IEGeneric var1) {
      IEGeneric var2 = var1.slice(0, 8);
      IEOperation var3 = this.gCtx.createOperation(OperationType.PAR, var2);
      return this.ctx.createAssign(this.FE, var3);
   }

   void pC(IEGeneric var1, IEGeneric var2, IEGeneric var3, List var4) {
      IEOperation var5 = this.gCtx.createOperation(OperationType.XOR, this.gCtx.createOperation(OperationType.XOR, var1, var2), var3);
      var4.add(this.ctx.createAssign(this.Aj, var5.slice(4, 5)));
   }

   void pC(IEGeneric var1, IEGeneric var2, List var3) {
      var3.add(this.pC(var1, var2));
      var3.add(this.A(var1, var2));
      var3.add(this.kS(var1, var2));
   }

   private IEAssign pC(IEGeneric var1, IEGeneric var2) {
      IECond var3 = this.gCtx.createCond(var1, this.Bc, this.OI);
      return this.ctx.createAssign(this.EX, this.gCtx.createCond(var2, var3, this.EX));
   }

   private IEAssign A(IEGeneric var1, IEGeneric var2) {
      IEGeneric var3 = var1.msb();
      return this.ctx.createAssign(this.LM, this.gCtx.createCond(var2, var3, this.LM));
   }

   private IEAssign kS(IEGeneric var1, IEGeneric var2) {
      IEGeneric var3 = var1.slice(0, 8);
      IEOperation var4 = this.gCtx.createOperation(OperationType.PAR, var3);
      return this.ctx.createAssign(this.FE, this.gCtx.createCond(var2, var4.lsb(), this.FE));
   }

   @Override
   public IEBranchDetails getDefaultBranchToRoutineSideEffects(INativeMethodItem var1) {
      int var2 = this.regNormalBitsize / 8;
      Integer var3 = null;
      if (var1 != null && var1.getData() != null) {
         var3 = var1.getData().getSPDeltaOnReturn();
      }

      ArrayList var4 = Lists.createArrayList();
      Lists.addNonNulls(var4, this.Ab);
      ArrayList var5 = Lists.createArrayList();
      Lists.addNonNulls(var5, this.Sc);
      ArrayList var6 = Lists.createArrayList();
      Lists.addNonNulls(var6, this.eP, this.ah);
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

   public static boolean pC(IEGeneric... var0) {
      return true;
   }

   @Override
   public Integer determineStackPointerDeltaFromSimulation(SimulationPointInformation var1) {
      EState var2 = var1.state;
      if (!var1.instructionEmulationFailed) {
         long var3 = var2.getValueAsLong(this.Gg);
         if (this.B.contains(var3)) {
            return null;
         }

         INativeMethodItem var5 = this.getNativeContext().getRoutine(var3);
         if (var5 != null) {
            String var6 = var5.getName();
            if (var6 != null) {
               switch (var6) {
                  case "___allocStackInternalPNF":
                     if (this.RR != null) {
                        return null;
                     }

                     long var13 = var2.getValueAsLong(this.kQ);

                     try {
                        int var11 = var2.getMemory().readInt(var13 + this.te);
                        this.RR = var11;
                        return -var11 + this.te;
                     } catch (MemoryException var12) {
                        return null;
                     }
                  case "___freeStackInternalPNF":
                     if (this.RR == null) {
                        return null;
                     }

                     int var9 = this.RR;
                     this.RR = null;
                     return var9 + this.te + this.te;
                  default:
                     this.B.add(var3);
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
      Strings.ff(var1, "Converted instructions: %d (incl. failed=%d, untranslated=%d)\n", this.MZ, this.fH, this.ET);
      if (this.yv != null && !this.yv.isEmpty()) {
         var1.append("Failed instructions: ").append(this.yv.formatTopReferences(-1));
      }

      return var1.toString();
   }
}
