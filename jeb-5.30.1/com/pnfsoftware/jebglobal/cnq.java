package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ACS;
import com.pnfsoftware.jeb.util.base.Assert;
import java.util.Map;
import java.util.TreeMap;

public class cnq {
   public static final int q = 0;
   public static final int RF = 1;
   public static final int xK = 2;
   public static final int Dw = 3;
   public static final int Uv = 16;
   public static final int oW = 32;
   public static final int gO = 64;
   public static final int nf = 128;
   public static final int gP = 256;
   public static final int za = 512;
   public static final int lm = 1024;
   public static final int zz = 2048;
   public static final int JY = 4096;
   public static final int HF = 8192;
   public static final int LK = 16384;
   public static final int io = 32768;
   public static final int qa = 65536;
   public static final int Hk = 131072;
   public static final int Me = 262144;
   public static final int PV = 524288;
   public static final int oQ = 1048576;
   static final int xW = 16;
   static final int KT = 17;
   static final int Gf = 18;
   static final int Ef = 256;
   static final int cC = 257;
   static final int sH = 128;
   static final int CE = 129;
   static final int wF = 512;
   static final int If = 513;
   static final int Dz = 1024;
   static final int mI = 1025;
   private static final int pU = 8192;
   static final int jq = 16777216;
   static final int ui = 33554432;
   public static final int TX = 0;
   public static final int Rr = 1;
   public static final int EB = 3;
   public static final int Xo = 4;
   public static final int Bu = 5;
   public static final int IN = 6;
   public static final int rL = 8;
   public static final int eJ = 9;
   public static final int YN = 11;
   public static final int Rv = 12;
   public static final int zx = 13;
   public static final int ZT = 14;
   public static final int Ri = 16;
   public static final int GY = 17;
   public static final int Wx = 18;
   public static final int AB = 19;
   public static final int CY = 20;
   public static final int WI = 24;
   public static final int Tq = 25;
   public static final int Yp = 27;
   public static final int Gu = 28;
   static final int nY = 1;
   static final int lF = 2;
   static final int nq = 3;
   static final int NX = 4;
   static final int br = 5;
   public static Map tW = new TreeMap();
   public static final cnq.eo ZA = q("fence", 16, 2, 1, 1);
   public static final cnq.eo Ov = q("fence.tso", 16, 2, 1, 1);
   public static final cnq.eo Lj = q("ecall", 16, 0);
   public static final cnq.eo nv = q("ebreak", 16, 0);
   public static final cnq.eo LL = q("lb", 16, 3, 4, 4, 2).q(ACS.make(ACS.OPS.LOAD, 1, 2, 4).operationBitsize(8).extensionMode(1));
   public static final cnq.eo PQ = q("lh", 16, 3, 4, 4, 2).q(ACS.make(ACS.OPS.LOAD, 1, 2, 4).operationBitsize(16).extensionMode(1));
   public static final cnq.eo fQ = q("lw", 16, 3, 4, 4, 2).q(ACS.make(ACS.OPS.LOAD, 1, 2, 4).operationBitsize(32).extensionMode(1));
   public static final cnq.eo fi = q("lbu", 16, 3, 4, 4, 2).q(ACS.make(ACS.OPS.LOAD, 1, 2, 4).operationBitsize(8).extensionMode(0));
   public static final cnq.eo bl = q("lhu", 16, 3, 4, 4, 2).q(ACS.make(ACS.OPS.LOAD, 1, 2, 4).operationBitsize(16).extensionMode(0));
   public static final cnq.eo jb = q("lwu", 17, 3, 4, 4, 2).q(ACS.make(ACS.OPS.LOAD, 1, 2, 4).operationBitsize(32).extensionMode(0));
   public static final cnq.eo pQ = q("ld", 17, 3, 4, 4, 2).q(ACS.make(ACS.OPS.LOAD, 1, 2, 4).operationBitsize(64).extensionMode(1));
   public static final cnq.eo[] kf = new cnq.eo[]{LL, PQ, fQ, pQ, fi, bl, jb, null};
   public static final cnq.eo GM = q("sb", 16, 3, 4, 4, 2).q(ACS.make(ACS.OPS.STORE, 2, 4, 8).operationBitsize(8));
   public static final cnq.eo TQ = q("sh", 16, 3, 4, 4, 2).q(ACS.make(ACS.OPS.STORE, 2, 4, 8).operationBitsize(16));
   public static final cnq.eo Yw = q("sw", 16, 3, 4, 4, 2).q(ACS.make(ACS.OPS.STORE, 2, 4, 8).operationBitsize(32));
   public static final cnq.eo IY = q("sd", 17, 3, 4, 4, 2).q(ACS.make(ACS.OPS.STORE, 2, 4, 8).operationBitsize(64));
   public static final cnq.eo[] qR = new cnq.eo[]{GM, TQ, Yw, IY, null, null, null, null};
   public static final cnq.eo YA = q("beq", 16777232, 3, 4, 4, 3).q(ACS.OPS.JUMP_IF_EQ, 2, 4, 8);
   public static final cnq.eo fw = q("bne", 16777232, 3, 4, 4, 3).q(ACS.OPS.JUMP_IF_NE, 2, 4, 8);
   public static final cnq.eo Wp = q("blt", 16777232, 3, 4, 4, 3).q(ACS.OPS.JUMP_IF_LT_S, 2, 4, 8);
   public static final cnq.eo cY = q("bge", 16777232, 3, 4, 4, 3).q(ACS.OPS.JUMP_IF_GE_S, 2, 4, 8);
   public static final cnq.eo PY = q("bltu", 16777232, 3, 4, 4, 3).q(ACS.OPS.JUMP_IF_LT_U, 2, 4, 8);
   public static final cnq.eo cR = q("bgeu", 16777232, 3, 4, 4, 3).q(ACS.OPS.JUMP_IF_GE_U, 2, 4, 8);
   public static final cnq.eo[] eC = new cnq.eo[]{YA, fw, null, null, Wp, cY, PY, cR};
   public static final cnq.eo ND = q("addi", 16, 3, 4, 4, 2).q(ACS.OPS.ADD, 1, 2, 4);
   public static final cnq.eo Qu = q("slti", 16, 3, 4, 4, 2).q(ACS.OPS.SET_IF_LT_S, 1, 2, 4);
   public static final cnq.eo jh = q("sltiu", 16, 3, 4, 4, 2).q(ACS.OPS.SET_IF_LT_U, 1, 2, 4);
   public static final cnq.eo Jf = q("xori", 16, 3, 4, 4, 2).q(ACS.OPS.XOR, 1, 2, 4);
   public static final cnq.eo vC = q("ori", 16, 3, 4, 4, 2).q(ACS.OPS.OR, 1, 2, 4);
   public static final cnq.eo of = q("andi", 16, 3, 4, 4, 2).q(ACS.OPS.AND, 1, 2, 4);
   public static final cnq.eo[] os = new cnq.eo[]{ND, null, Qu, jh, Jf, null, vC, of};
   public static final cnq.eo iu = q("slli", 16, 3, 4, 4, 1).q(ACS.OPS.SHL, 1, 2, 4);
   public static final cnq.eo fn = q("srli", 16, 3, 4, 4, 1).q(ACS.OPS.SHR, 1, 2, 4);
   public static final cnq.eo ZU = q("srai", 16, 3, 4, 4, 1).q(ACS.OPS.SAR, 1, 2, 4);
   public static final cnq.eo Sz = q("lui", 16, 2, 4, 2).q(ACS.OPS.CUSTOM);
   public static final cnq.eo fq = q("auipc", 16, 2, 4, 2).q(ACS.OPS.CUSTOM);
   public static final cnq.eo mJ = q("jal", 33554448, 2, 4, 3).q(ACS.OPS.JUMP, 1, 2);
   public static final cnq.eo Bs = q("jalr", 33554448, 2, 4, 4, 2).q(ACS.make(ACS.OPS.JUMP, 1, 2, 4).maskOnSource(-2L));
   public static final cnq.eo rV = q("add", 16, 3, 4, 4, 4).q(ACS.OPS.ADD, 1, 2, 4);
   public static final cnq.eo WX = q("sub", 16, 3, 4, 4, 4).q(ACS.OPS.SUB, 1, 2, 4);
   public static final cnq.eo CB = q("sll", 16, 3, 4, 4, 4).q(ACS.OPS.SHL, 1, 2, 4);
   public static final cnq.eo C = q("slt", 16, 3, 4, 4, 4).q(ACS.OPS.SET_IF_LT_S, 1, 2, 4);
   public static final cnq.eo GC = q("sltu", 16, 3, 4, 4, 4).q(ACS.OPS.SET_IF_LT_U, 1, 2, 4);
   public static final cnq.eo KF = q("xor", 16, 3, 4, 4, 4).q(ACS.OPS.XOR, 1, 2, 4);
   public static final cnq.eo rk = q("srl", 16, 3, 4, 4, 4).q(ACS.OPS.SHR, 1, 2, 4);
   public static final cnq.eo cy = q("sra", 16, 3, 4, 4, 4).q(ACS.OPS.SAR, 1, 2, 4);
   public static final cnq.eo jk = q("or", 16, 3, 4, 4, 4).q(ACS.OPS.OR, 1, 2, 4);
   public static final cnq.eo Cl = q("and", 16, 3, 4, 4, 4).q(ACS.OPS.AND, 1, 2, 4);
   public static final cnq.eo hM = q("addiw", 17, 3, 4, 4, 2).q(ACS.make(ACS.OPS.ADD, 1, 2, 4).operationBitsizeAndSignExtendToDest(32));
   public static final cnq.eo[] kv = new cnq.eo[]{hM, null, null, null, null, null, null, null};
   public static final cnq.eo oS = q("slliw", 17, 3, 4, 4, 1).q(ACS.make(ACS.OPS.SHL, 1, 2, 4).operationBitsizeAndSignExtendToDest(32));
   public static final cnq.eo FG = q("srliw", 17, 3, 4, 4, 1).q(ACS.make(ACS.OPS.SHR, 1, 2, 4).operationBitsizeAndSignExtendToDest(32));
   public static final cnq.eo Al = q("sraiw", 17, 3, 4, 4, 1).q(ACS.make(ACS.OPS.SAR, 1, 2, 4).operationBitsizeAndSignExtendToDest(32));
   public static final cnq.eo Kn = q("addw", 17, 3, 4, 4, 4).q(ACS.make(ACS.OPS.ADD, 1, 2, 4).operationBitsizeAndSignExtendToDest(32));
   public static final cnq.eo vh = q("subw", 17, 3, 4, 4, 4).q(ACS.make(ACS.OPS.SUB, 1, 2, 4).operationBitsizeAndSignExtendToDest(32));
   public static final cnq.eo Rd = q("sllw", 17, 3, 4, 4, 4).q(ACS.make(ACS.OPS.SHL, 1, 2, 4).operationBitsizeAndSignExtendToDest(32));
   public static final cnq.eo Eq = q("srlw", 17, 3, 4, 4, 4).q(ACS.make(ACS.OPS.SHR, 1, 2, 4).operationBitsizeAndSignExtendToDest(32));
   public static final cnq.eo hP = q("sraw", 17, 3, 4, 4, 4).q(ACS.make(ACS.OPS.SAR, 1, 2, 4).operationBitsizeAndSignExtendToDest(32));
   public static final cnq.eo wN = q("fence.i", 32, 0);
   public static final cnq.eo Uc = q("csrrw", 64, 3, 4, 1, 4);
   public static final cnq.eo TB = q("csrrs", 64, 3, 4, 1, 4);
   public static final cnq.eo dg = q("csrrc", 64, 3, 4, 1, 4);
   public static final cnq.eo hw = q("csrrwi", 64, 3, 4, 1, 1);
   public static final cnq.eo gm = q("csrrsi", 64, 3, 4, 1, 1);
   public static final cnq.eo uY = q("csrrci", 64, 3, 4, 1, 1);
   public static final cnq.eo sc = q("mul", 128, 3, 4, 4, 4).q(ACS.OPS.MUL, 1, 2, 4);
   public static final cnq.eo wQ = q("mulh", 128, 3, 4, 4, 4);
   public static final cnq.eo Oj = q("mulhsu", 128, 3, 4, 4, 4);
   public static final cnq.eo VW = q("mulhu", 128, 3, 4, 4, 4);
   public static final cnq.eo ap = q("div", 128, 3, 4, 4, 4).q(ACS.OPS.DIV_S, 1, 2, 4);
   public static final cnq.eo RL = q("divu", 128, 3, 4, 4, 4).q(ACS.OPS.DIV_U, 1, 2, 4);
   public static final cnq.eo hy = q("rem", 128, 3, 4, 4, 4).q(ACS.OPS.REM_S, 1, 2, 4);
   public static final cnq.eo Xi = q("remu", 128, 3, 4, 4, 4).q(ACS.OPS.REM_U, 1, 2, 4);
   public static final cnq.eo Ag = q("mulw", 129, 3, 4, 4, 4).q(ACS.make(ACS.OPS.MUL, 1, 2, 4).operationBitsizeAndSignExtendToDest(32));
   public static final cnq.eo rp = q("divw", 129, 3, 4, 4, 4).q(ACS.make(ACS.OPS.DIV_S, 1, 2, 4).operationBitsizeAndSignExtendToDest(32));
   public static final cnq.eo CW = q("divuw", 129, 3, 4, 4, 4).q(ACS.make(ACS.OPS.DIV_U, 1, 2, 4).operationBitsizeAndSignExtendToDest(32));
   public static final cnq.eo qm = q("remw", 129, 3, 4, 4, 4).q(ACS.make(ACS.OPS.REM_S, 1, 2, 4).operationBitsizeAndSignExtendToDest(32));
   public static final cnq.eo LR = q("remuw", 129, 3, 4, 4, 4).q(ACS.make(ACS.OPS.REM_U, 1, 2, 4).operationBitsizeAndSignExtendToDest(32));
   public static final cnq.eo Uz = q("lr.w", 256, 2, 4, 4);
   public static final cnq.eo dF = q("sc.w", 256, 3, 4, 4, 4);
   public static final cnq.eo kk = q("amoswap.w", 256, 3, 4, 4, 4);
   public static final cnq.eo Rc = q("amoadd.w", 256, 3, 4, 4, 4);
   public static final cnq.eo jz = q("amoxor.w", 256, 3, 4, 4, 4);
   public static final cnq.eo MT = q("amoand.w", 256, 3, 4, 4, 4);
   public static final cnq.eo bY = q("amoor.w", 256, 3, 4, 4, 4);
   public static final cnq.eo LS = q("amomin.w", 256, 3, 4, 4, 4);
   public static final cnq.eo fG = q("amomax.w", 256, 3, 4, 4, 4);
   public static final cnq.eo cO = q("amominu.w", 256, 3, 4, 4, 4);
   public static final cnq.eo wr = q("amomaxu.w", 256, 3, 4, 4, 4);
   public static final cnq.eo[] pe = new cnq.eo[]{
      Rc,
      kk,
      Uz,
      dF,
      jz,
      null,
      null,
      null,
      bY,
      null,
      null,
      null,
      MT,
      null,
      null,
      null,
      LS,
      null,
      null,
      null,
      fG,
      null,
      null,
      null,
      cO,
      null,
      null,
      null,
      wr,
      null,
      null,
      null
   };
   public static final cnq.eo Gg = q("lr.d", 257, 2, 4, 4);
   public static final cnq.eo CK = q("sc.d", 257, 3, 4, 4, 4);
   public static final cnq.eo PW = q("amoswap.d", 257, 3, 4, 4, 4);
   public static final cnq.eo zm = q("amoadd.d", 257, 3, 4, 4, 4);
   public static final cnq.eo Wn = q("amoxor.d", 257, 3, 4, 4, 4);
   public static final cnq.eo eG = q("amoand.d", 257, 3, 4, 4, 4);
   public static final cnq.eo Id = q("amoor.d", 257, 3, 4, 4, 4);
   public static final cnq.eo Dk = q("amomin.d", 257, 3, 4, 4, 4);
   public static final cnq.eo dS = q("amomax.d", 257, 3, 4, 4, 4);
   public static final cnq.eo cb = q("amominu.d", 257, 3, 4, 4, 4);
   public static final cnq.eo BU = q("amomaxu.d", 257, 3, 4, 4, 4);
   public static final cnq.eo[] xG = new cnq.eo[]{
      zm,
      PW,
      Gg,
      CK,
      Wn,
      null,
      null,
      null,
      Id,
      null,
      null,
      null,
      eG,
      null,
      null,
      null,
      Dk,
      null,
      null,
      null,
      dS,
      null,
      null,
      null,
      cb,
      null,
      null,
      null,
      BU,
      null,
      null,
      null
   };
   public static final cnq.eo wS = q("flw", 512, 3, 5, 4, 2);
   public static final cnq.eo Oz = q("fsw", 512, 3, 5, 4, 2);
   public static final cnq.eo yn = q("fmadd.s", 512, 4, 5, 5, 5, 5);
   public static final cnq.eo es = q("fmsub.s", 512, 4, 5, 5, 5, 5);
   public static final cnq.eo o = q("fnmsub.s", 512, 4, 5, 5, 5, 5);
   public static final cnq.eo gl = q("fnmadd.s", 512, 4, 5, 5, 5, 5);
   public static final cnq.eo tX = q("fadd.s", 512, 3, 5, 5, 5);
   public static final cnq.eo Qt = q("fsub.s", 512, 3, 5, 5, 5);
   public static final cnq.eo JW = q("fmul.s", 512, 3, 5, 5, 5);
   public static final cnq.eo Ub = q("fdiv.s", 512, 3, 5, 5, 5);
   public static final cnq.eo tb = q("fsqrt.s", 512, 2, 5, 5);
   public static final cnq.eo yW = q("fsgnj.s", 512, 3, 5, 5, 5);
   public static final cnq.eo JF = q("fsgnjn.s", 512, 3, 5, 5, 5);
   public static final cnq.eo uz = q("fsgnjx.s", 512, 3, 5, 5, 5);
   public static final cnq.eo Xz = q("fmin.s", 512, 3, 5, 5, 5);
   public static final cnq.eo iK = q("fmax.s", 512, 3, 5, 5, 5);
   public static final cnq.eo ZE = q("fcvt.w.s", 512, 2, 4, 5);
   public static final cnq.eo Jh = q("fcvt.wu.s", 512, 2, 4, 5);
   public static final cnq.eo iO = q("fmv.x.w", 512, 2, 4, 5);
   public static final cnq.eo Qe = q("feq.s", 512, 3, 4, 5, 5);
   public static final cnq.eo dW = q("flt.s", 512, 3, 4, 5, 5);
   public static final cnq.eo HK = q("fle.s", 512, 3, 4, 5, 5);
   public static final cnq.eo uw = q("fclass.s", 512, 2, 4, 5);
   public static final cnq.eo fe = q("fcvt.s.w", 512, 2, 5, 4);
   public static final cnq.eo Kl = q("fcvt.s.wu", 512, 2, 5, 4);
   public static final cnq.eo So = q("fmv.w.x", 512, 2, 5, 4);
   public static final cnq.eo AG = q("fcvt.l.s", 513, 2, 4, 5);
   public static final cnq.eo er = q("fcvt.lu.s", 513, 2, 4, 5);
   public static final cnq.eo SM = q("fcvt.s.l", 513, 2, 5, 4);
   public static final cnq.eo bj = q("fcvt.s.lu", 513, 2, 5, 4);
   public static final cnq.eo GO = q("fld", 1024, 3, 5, 4, 2);
   public static final cnq.eo QZ = q("fsd", 1024, 3, 5, 4, 2);
   public static final cnq.eo Up = q("fmadd.d", 1024, 4, 5, 5, 5, 5);
   public static final cnq.eo HO = q("fmsub.d", 1024, 4, 5, 5, 5, 5);
   public static final cnq.eo cv = q("fnmsub.d", 1024, 4, 5, 5, 5, 5);
   public static final cnq.eo lk = q("fnmadd.d", 1024, 4, 5, 5, 5, 5);
   public static final cnq.eo sa = q("fadd.d", 1024, 3, 5, 5, 5);
   public static final cnq.eo WJ = q("fsub.d", 1024, 3, 5, 5, 5);
   public static final cnq.eo pL = q("fmul.d", 1024, 3, 5, 5, 5);
   public static final cnq.eo aH = q("fdiv.d", 1024, 3, 5, 5, 5);
   public static final cnq.eo yc = q("fsqrt.d", 1024, 2, 5, 5);
   public static final cnq.eo eb = q("fsgnj.d", 1024, 3, 5, 5, 5);
   public static final cnq.eo zj = q("fsgnjn.d", 1024, 3, 5, 5, 5);
   public static final cnq.eo aV = q("fsgnjx.d", 1024, 3, 5, 5, 5);
   public static final cnq.eo Qo = q("fmin.d", 1024, 3, 5, 5, 5);
   public static final cnq.eo lN = q("fmax.d", 1024, 3, 5, 5, 5);
   public static final cnq.eo gT = q("fcvt.s.d", 1024, 2, 5, 5);
   public static final cnq.eo qr = q("fcvt.d.s", 1024, 2, 5, 5);
   public static final cnq.eo IJ = q("feq.d", 1024, 3, 4, 5, 5);
   public static final cnq.eo Of = q("flt.d", 1024, 3, 4, 5, 5);
   public static final cnq.eo AN = q("fle.d", 1024, 3, 4, 5, 5);
   public static final cnq.eo RW = q("fclass.d", 1024, 2, 4, 5);
   public static final cnq.eo YR = q("fcvt.w.d", 1024, 2, 4, 5);
   public static final cnq.eo fN = q("fcvt.wu.d", 1024, 2, 4, 5);
   public static final cnq.eo GH = q("fcvt.d.w", 1024, 2, 5, 4);
   public static final cnq.eo BY = q("fcvt.d.wu", 1024, 2, 5, 4);
   public static final cnq.eo fK = q("fcvt.l.d", 1024, 2, 4, 5);
   public static final cnq.eo ou = q("fcvt.lu.d", 1024, 2, 4, 5);
   public static final cnq.eo DP = q("fmv.x.d", 1024, 2, 4, 5);
   public static final cnq.eo lA = q("fcvt.d.l", 1024, 2, 5, 4);
   public static final cnq.eo yu = q("fcvt.d.lu", 1024, 2, 5, 4);
   public static final cnq.eo lz = q("fmv.d.x", 1024, 2, 5, 4);
   public static final cnq.eo Nu = q("addi4spn", 8208, 2, 4, 2);
   public static final cnq.eo YT = q("nop", 8208, 0).q(ACS.OPS.NOP);
   public static final cnq.eo AY = q("li", 8208, 2, 4, 2).q(ACS.make(ACS.OPS.MOVE, 1, 2).operationBitsizeAndSignExtendToDest(32));
   public static final cnq.eo Ld = q("mv", 8208, 2, 4, 4).q(ACS.OPS.MOVE, 1, 2);
   public static final cnq.eo XV = q("addi16sp", 8208, 1, 2).q(ACS.OPS.ADD, 4, 50331650, 50331649);
   public static final cnq.eo NY = q("beqz", 16785424, 2, 4, 3).q(ACS.OPS.JUMP_IF_EQ, 2, 8, 65540);
   public static final cnq.eo xf = q("bnez", 16785424, 2, 4, 3).q(ACS.OPS.JUMP_IF_NE, 2, 8, 65540);
   public static final cnq.eo Ua = q("j", 16785424, 1, 3).q(ACS.OPS.JUMP, 2);
   public static final cnq.eo jT = q("jr", 16785424, 1, 4).q(ACS.OPS.JUMP, 2);
   public static final cnq.eo AD = q("lwsp", 8208, 2, 4, 2).q(ACS.make(ACS.OPS.LOAD, 1, 4, 50331650).operationBitsize(32).extensionMode(1));
   public static final cnq.eo ZY = q("ldsp", 8209, 2, 4, 2).q(ACS.make(ACS.OPS.LOAD, 1, 4, 50331650).operationBitsize(64).extensionMode(1));
   public static final cnq.eo Cw = q("swsp", 8208, 2, 4, 2).q(ACS.make(ACS.OPS.STORE, 2, 8, 50331652).operationBitsize(32).extensionMode(1));
   public static final cnq.eo bH = q("sdsp", 8209, 2, 4, 2).q(ACS.make(ACS.OPS.STORE, 2, 8, 50331652).operationBitsize(64).extensionMode(1));
   public static final cnq.eo rw = q("sqsp", 8210, 2, 4, 2);
   public static final cnq.eo sE = q("flwsp", 8704, 2, 5, 2);
   public static final cnq.eo BR = q("fswsp", 8704, 2, 5, 2);
   public static final cnq.eo wh = q("fldsp", 9216, 2, 5, 2);
   public static final cnq.eo iY = q("fsdsp", 9216, 2, 5, 2);

   private static cnq.eo q(String var0, int var1, int var2) {
      return new cnq.eo(var0, var1, var2, 0, 0, 0, 0);
   }

   private static cnq.eo q(String var0, int var1, int var2, int var3) {
      return new cnq.eo(var0, var1, var2, var3, 0, 0, 0);
   }

   private static cnq.eo q(String var0, int var1, int var2, int var3, int var4) {
      return new cnq.eo(var0, var1, var2, var3, var4, 0, 0);
   }

   private static cnq.eo q(String var0, int var1, int var2, int var3, int var4, int var5) {
      return new cnq.eo(var0, var1, var2, var3, var4, var5, 0);
   }

   private static cnq.eo q(String var0, int var1, int var2, int var3, int var4, int var5, int var6) {
      return new cnq.eo(var0, var1, var2, var3, var4, var5, var6);
   }

   public static class eo {
      public String q;
      public int RF;
      public int xK;
      public int Dw;
      ACS Uv;

      eo(String var1, int var2, int var3, int var4, int var5, int var6, int var7) {
         this.q = var1;
         this.RF = var2;
         this.xK = var3;
         this.Dw = var4 | var5 << 4 | var6 << 8 | var7 << 12;
         Assert.a(cnq.tW.put(var1, this) == null);
      }

      public cnq.eo q(ACS.OPS var1, int... var2) {
         this.Uv = new ACS(var1, var2);
         return this;
      }

      public cnq.eo q(ACS var1) {
         this.Uv = var1;
         return this;
      }
   }
}
