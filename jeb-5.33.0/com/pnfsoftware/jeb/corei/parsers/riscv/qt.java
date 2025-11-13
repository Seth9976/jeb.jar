package com.pnfsoftware.jeb.corei.parsers.riscv;

import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ACS;
import com.pnfsoftware.jeb.util.base.Assert;
import java.util.Map;
import java.util.TreeMap;

public class qt {
   public static Map pC = new TreeMap();
   public static final qt.Av A = pC("fence", 16, 2, 1, 1);
   public static final qt.Av kS = pC("fence.tso", 16, 2, 1, 1);
   public static final qt.Av wS = pC("ecall", 16, 0);
   public static final qt.Av UT = pC("ebreak", 16, 0);
   public static final qt.Av E = pC("lb", 16, 3, 4, 4, 2).pC(ACS.make(ACS.OPS.LOAD, 1, 2, 4).operationBitsize(8).extensionMode(1));
   public static final qt.Av sY = pC("lh", 16, 3, 4, 4, 2).pC(ACS.make(ACS.OPS.LOAD, 1, 2, 4).operationBitsize(16).extensionMode(1));
   public static final qt.Av ys = pC("lw", 16, 3, 4, 4, 2).pC(ACS.make(ACS.OPS.LOAD, 1, 2, 4).operationBitsize(32).extensionMode(1));
   public static final qt.Av ld = pC("lbu", 16, 3, 4, 4, 2).pC(ACS.make(ACS.OPS.LOAD, 1, 2, 4).operationBitsize(8).extensionMode(0));
   public static final qt.Av gp = pC("lhu", 16, 3, 4, 4, 2).pC(ACS.make(ACS.OPS.LOAD, 1, 2, 4).operationBitsize(16).extensionMode(0));
   public static final qt.Av oT = pC("lwu", 17, 3, 4, 4, 2).pC(ACS.make(ACS.OPS.LOAD, 1, 2, 4).operationBitsize(32).extensionMode(0));
   public static final qt.Av fI = pC("ld", 17, 3, 4, 4, 2).pC(ACS.make(ACS.OPS.LOAD, 1, 2, 4).operationBitsize(64).extensionMode(1));
   public static final qt.Av[] WR = new qt.Av[]{E, sY, ys, fI, ld, gp, oT, null};
   public static final qt.Av NS = pC("sb", 16, 3, 4, 4, 2).pC(ACS.make(ACS.OPS.STORE, 2, 4, 8).operationBitsize(8));
   public static final qt.Av vP = pC("sh", 16, 3, 4, 4, 2).pC(ACS.make(ACS.OPS.STORE, 2, 4, 8).operationBitsize(16));
   public static final qt.Av xC = pC("sw", 16, 3, 4, 4, 2).pC(ACS.make(ACS.OPS.STORE, 2, 4, 8).operationBitsize(32));
   public static final qt.Av ED = pC("sd", 17, 3, 4, 4, 2).pC(ACS.make(ACS.OPS.STORE, 2, 4, 8).operationBitsize(64));
   public static final qt.Av[] Sc = new qt.Av[]{NS, vP, xC, ED, null, null, null, null};
   public static final qt.Av ah = pC("beq", 16777232, 3, 4, 4, 3).pC(ACS.OPS.JUMP_IF_EQ, 2, 4, 8);
   public static final qt.Av eP = pC("bne", 16777232, 3, 4, 4, 3).pC(ACS.OPS.JUMP_IF_NE, 2, 4, 8);
   public static final qt.Av UO = pC("blt", 16777232, 3, 4, 4, 3).pC(ACS.OPS.JUMP_IF_LT_S, 2, 4, 8);
   public static final qt.Av Ab = pC("bge", 16777232, 3, 4, 4, 3).pC(ACS.OPS.JUMP_IF_GE_S, 2, 4, 8);
   public static final qt.Av rl = pC("bltu", 16777232, 3, 4, 4, 3).pC(ACS.OPS.JUMP_IF_LT_U, 2, 4, 8);
   public static final qt.Av z = pC("bgeu", 16777232, 3, 4, 4, 3).pC(ACS.OPS.JUMP_IF_GE_U, 2, 4, 8);
   public static final qt.Av[] Ek = new qt.Av[]{ah, eP, null, null, UO, Ab, rl, z};
   public static final qt.Av hK = pC("addi", 16, 3, 4, 4, 2).pC(ACS.OPS.ADD, 1, 2, 4);
   public static final qt.Av Er = pC("slti", 16, 3, 4, 4, 2).pC(ACS.OPS.SET_IF_LT_S, 1, 2, 4);
   public static final qt.Av FE = pC("sltiu", 16, 3, 4, 4, 2).pC(ACS.OPS.SET_IF_LT_U, 1, 2, 4);
   public static final qt.Av Aj = pC("xori", 16, 3, 4, 4, 2).pC(ACS.OPS.XOR, 1, 2, 4);
   public static final qt.Av EX = pC("ori", 16, 3, 4, 4, 2).pC(ACS.OPS.OR, 1, 2, 4);
   public static final qt.Av LM = pC("andi", 16, 3, 4, 4, 2).pC(ACS.OPS.AND, 1, 2, 4);
   public static final qt.Av[] mv = new qt.Av[]{hK, null, Er, FE, Aj, null, EX, LM};
   public static final qt.Av sO = pC("slli", 16, 3, 4, 4, 1).pC(ACS.OPS.SHL, 1, 2, 4);
   public static final qt.Av os = pC("srli", 16, 3, 4, 4, 1).pC(ACS.OPS.SHR, 1, 2, 4);
   public static final qt.Av Cu = pC("srai", 16, 3, 4, 4, 1).pC(ACS.OPS.SAR, 1, 2, 4);
   public static final qt.Av hZ = pC("lui", 16, 2, 4, 2).pC(ACS.OPS.CUSTOM);
   public static final qt.Av UW = pC("auipc", 16, 2, 4, 2).pC(ACS.OPS.CUSTOM);
   public static final qt.Av PR = pC("jal", 33554448, 2, 4, 3).pC(ACS.OPS.JUMP, 1, 2);
   public static final qt.Av cX = pC("jalr", 33554448, 2, 4, 4, 2).pC(ACS.make(ACS.OPS.JUMP, 1, 2, 4).maskOnSource(-2L));
   public static final qt.Av DQ = pC("add", 16, 3, 4, 4, 4).pC(ACS.OPS.ADD, 1, 2, 4);
   public static final qt.Av ZN = pC("sub", 16, 3, 4, 4, 4).pC(ACS.OPS.SUB, 1, 2, 4);
   public static final qt.Av OB = pC("sll", 16, 3, 4, 4, 4).pC(ACS.OPS.SHL, 1, 2, 4);
   public static final qt.Av pF = pC("slt", 16, 3, 4, 4, 4).pC(ACS.OPS.SET_IF_LT_S, 1, 2, 4);
   public static final qt.Av Bc = pC("sltu", 16, 3, 4, 4, 4).pC(ACS.OPS.SET_IF_LT_U, 1, 2, 4);
   public static final qt.Av OI = pC("xor", 16, 3, 4, 4, 4).pC(ACS.OPS.XOR, 1, 2, 4);
   public static final qt.Av Bf = pC("srl", 16, 3, 4, 4, 4).pC(ACS.OPS.SHR, 1, 2, 4);
   public static final qt.Av Pe = pC("sra", 16, 3, 4, 4, 4).pC(ACS.OPS.SAR, 1, 2, 4);
   public static final qt.Av ck = pC("or", 16, 3, 4, 4, 4).pC(ACS.OPS.OR, 1, 2, 4);
   public static final qt.Av RW = pC("and", 16, 3, 4, 4, 4).pC(ACS.OPS.AND, 1, 2, 4);
   public static final qt.Av e = pC("addiw", 17, 3, 4, 4, 2).pC(ACS.make(ACS.OPS.ADD, 1, 2, 4).operationBitsizeAndSignExtendToDest(32));
   public static final qt.Av[] xM = new qt.Av[]{e, null, null, null, null, null, null, null};
   public static final qt.Av kU = pC("slliw", 17, 3, 4, 4, 1).pC(ACS.make(ACS.OPS.SHL, 1, 2, 4).operationBitsizeAndSignExtendToDest(32));
   public static final qt.Av Kq = pC("srliw", 17, 3, 4, 4, 1).pC(ACS.make(ACS.OPS.SHR, 1, 2, 4).operationBitsizeAndSignExtendToDest(32));
   public static final qt.Av go = pC("sraiw", 17, 3, 4, 4, 1).pC(ACS.make(ACS.OPS.SAR, 1, 2, 4).operationBitsizeAndSignExtendToDest(32));
   public static final qt.Av JF = pC("addw", 17, 3, 4, 4, 4).pC(ACS.make(ACS.OPS.ADD, 1, 2, 4).operationBitsizeAndSignExtendToDest(32));
   public static final qt.Av Nq = pC("subw", 17, 3, 4, 4, 4).pC(ACS.make(ACS.OPS.SUB, 1, 2, 4).operationBitsizeAndSignExtendToDest(32));
   public static final qt.Av pg = pC("sllw", 17, 3, 4, 4, 4).pC(ACS.make(ACS.OPS.SHL, 1, 2, 4).operationBitsizeAndSignExtendToDest(32));
   public static final qt.Av gj = pC("srlw", 17, 3, 4, 4, 4).pC(ACS.make(ACS.OPS.SHR, 1, 2, 4).operationBitsizeAndSignExtendToDest(32));
   public static final qt.Av ZD = pC("sraw", 17, 3, 4, 4, 4).pC(ACS.make(ACS.OPS.SAR, 1, 2, 4).operationBitsizeAndSignExtendToDest(32));
   public static final qt.Av DL = pC("fence.i", 32, 0);
   public static final qt.Av UH = pC("csrrw", 64, 3, 4, 1, 4);
   public static final qt.Av VD = pC("csrrs", 64, 3, 4, 1, 4);
   public static final qt.Av Xs = pC("csrrc", 64, 3, 4, 1, 4);
   public static final qt.Av KV = pC("csrrwi", 64, 3, 4, 1, 1);
   public static final qt.Av FK = pC("csrrsi", 64, 3, 4, 1, 1);
   public static final qt.Av Bi = pC("csrrci", 64, 3, 4, 1, 1);
   public static final qt.Av wQ = pC("mul", 128, 3, 4, 4, 4).pC(ACS.OPS.MUL, 1, 2, 4);
   public static final qt.Av PZ = pC("mulh", 128, 3, 4, 4, 4);
   public static final qt.Av Ip = pC("mulhsu", 128, 3, 4, 4, 4);
   public static final qt.Av Fm = pC("mulhu", 128, 3, 4, 4, 4);
   public static final qt.Av FM = pC("div", 128, 3, 4, 4, 4).pC(ACS.OPS.DIV_S, 1, 2, 4);
   public static final qt.Av Wn = pC("divu", 128, 3, 4, 4, 4).pC(ACS.OPS.DIV_U, 1, 2, 4);
   public static final qt.Av gy = pC("rem", 128, 3, 4, 4, 4).pC(ACS.OPS.REM_S, 1, 2, 4);
   public static final qt.Av pt = pC("remu", 128, 3, 4, 4, 4).pC(ACS.OPS.REM_U, 1, 2, 4);
   public static final qt.Av uE = pC("mulw", 129, 3, 4, 4, 4).pC(ACS.make(ACS.OPS.MUL, 1, 2, 4).operationBitsizeAndSignExtendToDest(32));
   public static final qt.Av Um = pC("divw", 129, 3, 4, 4, 4).pC(ACS.make(ACS.OPS.DIV_S, 1, 2, 4).operationBitsizeAndSignExtendToDest(32));
   public static final qt.Av Ta = pC("divuw", 129, 3, 4, 4, 4).pC(ACS.make(ACS.OPS.DIV_U, 1, 2, 4).operationBitsizeAndSignExtendToDest(32));
   public static final qt.Av So = pC("remw", 129, 3, 4, 4, 4).pC(ACS.make(ACS.OPS.REM_S, 1, 2, 4).operationBitsizeAndSignExtendToDest(32));
   public static final qt.Av tH = pC("remuw", 129, 3, 4, 4, 4).pC(ACS.make(ACS.OPS.REM_U, 1, 2, 4).operationBitsizeAndSignExtendToDest(32));
   public static final qt.Av Gm = pC("lr.w", 256, 2, 4, 4);
   public static final qt.Av Br = pC("sc.w", 256, 3, 4, 4, 4);
   public static final qt.Av IE = pC("amoswap.w", 256, 3, 4, 4, 4);
   public static final qt.Av AU = pC("amoadd.w", 256, 3, 4, 4, 4);
   public static final qt.Av jS = pC("amoxor.w", 256, 3, 4, 4, 4);
   public static final qt.Av KK = pC("amoand.w", 256, 3, 4, 4, 4);
   public static final qt.Av oB = pC("amoor.w", 256, 3, 4, 4, 4);
   public static final qt.Av Rq = pC("amomin.w", 256, 3, 4, 4, 4);
   public static final qt.Av LL = pC("amomax.w", 256, 3, 4, 4, 4);
   public static final qt.Av rC = pC("amominu.w", 256, 3, 4, 4, 4);
   public static final qt.Av be = pC("amomaxu.w", 256, 3, 4, 4, 4);
   public static final qt.Av[] Xh = new qt.Av[]{
      AU,
      IE,
      Gm,
      Br,
      jS,
      null,
      null,
      null,
      oB,
      null,
      null,
      null,
      KK,
      null,
      null,
      null,
      Rq,
      null,
      null,
      null,
      LL,
      null,
      null,
      null,
      rC,
      null,
      null,
      null,
      be,
      null,
      null,
      null
   };
   public static final qt.Av sz = pC("lr.d", 257, 2, 4, 4);
   public static final qt.Av QQ = pC("sc.d", 257, 3, 4, 4, 4);
   public static final qt.Av eE = pC("amoswap.d", 257, 3, 4, 4, 4);
   public static final qt.Av dM = pC("amoadd.d", 257, 3, 4, 4, 4);
   public static final qt.Av EM = pC("amoxor.d", 257, 3, 4, 4, 4);
   public static final qt.Av fD = pC("amoand.d", 257, 3, 4, 4, 4);
   public static final qt.Av ii = pC("amoor.d", 257, 3, 4, 4, 4);
   public static final qt.Av Gu = pC("amomin.d", 257, 3, 4, 4, 4);
   public static final qt.Av hw = pC("amomax.d", 257, 3, 4, 4, 4);
   public static final qt.Av qG = pC("amominu.d", 257, 3, 4, 4, 4);
   public static final qt.Av yi = pC("amomaxu.d", 257, 3, 4, 4, 4);
   public static final qt.Av[] zK = new qt.Av[]{
      dM,
      eE,
      sz,
      QQ,
      EM,
      null,
      null,
      null,
      ii,
      null,
      null,
      null,
      fD,
      null,
      null,
      null,
      Gu,
      null,
      null,
      null,
      hw,
      null,
      null,
      null,
      qG,
      null,
      null,
      null,
      yi,
      null,
      null,
      null
   };
   public static final qt.Av LA = pC("flw", 512, 3, 5, 4, 2);
   public static final qt.Av ve = pC("fsw", 512, 3, 5, 4, 2);
   public static final qt.Av yv = pC("fmadd.s", 512, 4, 5, 5, 5, 5);
   public static final qt.Av MZ = pC("fmsub.s", 512, 4, 5, 5, 5, 5);
   public static final qt.Av fH = pC("fnmsub.s", 512, 4, 5, 5, 5, 5);
   public static final qt.Av ET = pC("fnmadd.s", 512, 4, 5, 5, 5, 5);
   public static final qt.Av kk = pC("fadd.s", 512, 3, 5, 5, 5);
   public static final qt.Av Rh = pC("fsub.s", 512, 3, 5, 5, 5);
   public static final qt.Av vv = pC("fmul.s", 512, 3, 5, 5, 5);
   public static final qt.Av fn = pC("fdiv.s", 512, 3, 5, 5, 5);
   public static final qt.Av AS = pC("fsqrt.s", 512, 2, 5, 5);
   public static final qt.Av wF = pC("fsgnj.s", 512, 3, 5, 5, 5);
   public static final qt.Av hF = pC("fsgnjn.s", 512, 3, 5, 5, 5);
   public static final qt.Av FA = pC("fsgnjx.s", 512, 3, 5, 5, 5);
   public static final qt.Av IK = pC("fmin.s", 512, 3, 5, 5, 5);
   public static final qt.Av DM = pC("fmax.s", 512, 3, 5, 5, 5);
   public static final qt.Av IQ = pC("fcvt.w.s", 512, 2, 4, 5);
   public static final qt.Av zR = pC("fcvt.wu.s", 512, 2, 4, 5);
   public static final qt.Av Ft = pC("fmv.x.w", 512, 2, 4, 5);
   public static final qt.Av kt = pC("feq.s", 512, 3, 4, 5, 5);
   public static final qt.Av Yw = pC("flt.s", 512, 3, 4, 5, 5);
   public static final qt.Av uD = pC("fle.s", 512, 3, 4, 5, 5);
   public static final qt.Av ZY = pC("fclass.s", 512, 2, 4, 5);
   public static final qt.Av mK = pC("fcvt.s.w", 512, 2, 5, 4);
   public static final qt.Av pW = pC("fcvt.s.wu", 512, 2, 5, 4);
   public static final qt.Av Gg = pC("fmv.w.x", 512, 2, 5, 4);
   public static final qt.Av kQ = pC("fcvt.l.s", 513, 2, 4, 5);
   public static final qt.Av te = pC("fcvt.lu.s", 513, 2, 4, 5);
   public static final qt.Av B = pC("fcvt.s.l", 513, 2, 5, 4);
   public static final qt.Av RR = pC("fcvt.s.lu", 513, 2, 5, 4);
   public static final qt.Av CK = pC("fld", 1024, 3, 5, 4, 2);
   public static final qt.Av Eq = pC("fsd", 1024, 3, 5, 4, 2);
   public static final qt.Av y = pC("fmadd.d", 1024, 4, 5, 5, 5, 5);
   public static final qt.Av JP = pC("fmsub.d", 1024, 4, 5, 5, 5, 5);
   public static final qt.Av jY = pC("fnmsub.d", 1024, 4, 5, 5, 5, 5);
   public static final qt.Av ee = pC("fnmadd.d", 1024, 4, 5, 5, 5, 5);
   public static final qt.Av ho = pC("fadd.d", 1024, 3, 5, 5, 5);
   public static final qt.Av VE = pC("fsub.d", 1024, 3, 5, 5, 5);
   public static final qt.Av lt = pC("fmul.d", 1024, 3, 5, 5, 5);
   public static final qt.Av uw = pC("fdiv.d", 1024, 3, 5, 5, 5);
   public static final qt.Av QP = pC("fsqrt.d", 1024, 2, 5, 5);
   public static final qt.Av hM = pC("fsgnj.d", 1024, 3, 5, 5, 5);
   public static final qt.Av MJ = pC("fsgnjn.d", 1024, 3, 5, 5, 5);
   public static final qt.Av OA = pC("fsgnjx.d", 1024, 3, 5, 5, 5);
   public static final qt.Av kT = pC("fmin.d", 1024, 3, 5, 5, 5);
   public static final qt.Av x = pC("fmax.d", 1024, 3, 5, 5, 5);
   public static final qt.Av un = pC("fcvt.s.d", 1024, 2, 5, 5);
   public static final qt.Av JV = pC("fcvt.d.s", 1024, 2, 5, 5);
   public static final qt.Av Iq = pC("feq.d", 1024, 3, 4, 5, 5);
   public static final qt.Av mV = pC("flt.d", 1024, 3, 4, 5, 5);
   public static final qt.Av Gh = pC("fle.d", 1024, 3, 4, 5, 5);
   public static final qt.Av HG = pC("fclass.d", 1024, 2, 4, 5);
   public static final qt.Av yC = pC("fcvt.w.d", 1024, 2, 4, 5);
   public static final qt.Av uu = pC("fcvt.wu.d", 1024, 2, 4, 5);
   public static final qt.Av Tq = pC("fcvt.d.w", 1024, 2, 5, 4);
   public static final qt.Av HO = pC("fcvt.d.wu", 1024, 2, 5, 4);
   public static final qt.Av Is = pC("fcvt.l.d", 1024, 2, 4, 5);
   public static final qt.Av BP = pC("fcvt.lu.d", 1024, 2, 4, 5);
   public static final qt.Av Wm = pC("fmv.x.d", 1024, 2, 4, 5);
   public static final qt.Av TP = pC("fcvt.d.l", 1024, 2, 5, 4);
   public static final qt.Av gd = pC("fcvt.d.lu", 1024, 2, 5, 4);
   public static final qt.Av eI = pC("fmv.d.x", 1024, 2, 5, 4);
   public static final qt.Av lZ = pC("addi4spn", 8208, 2, 4, 2);
   public static final qt.Av AQ = pC("nop", 8208, 0).pC(ACS.OPS.NOP);
   public static final qt.Av BX = pC("li", 8208, 2, 4, 2).pC(ACS.make(ACS.OPS.MOVE, 1, 2).operationBitsizeAndSignExtendToDest(32));
   public static final qt.Av xg = pC("mv", 8208, 2, 4, 4).pC(ACS.OPS.MOVE, 1, 2);
   public static final qt.Av NN = pC("addi16sp", 8208, 1, 2).pC(ACS.OPS.ADD, 4, 50331650, 50331649);
   public static final qt.Av np = pC("beqz", 16785424, 2, 4, 3).pC(ACS.OPS.JUMP_IF_EQ, 2, 8, 65540);
   public static final qt.Av ik = pC("bnez", 16785424, 2, 4, 3).pC(ACS.OPS.JUMP_IF_NE, 2, 8, 65540);
   public static final qt.Av aK = pC("j", 16785424, 1, 3).pC(ACS.OPS.JUMP, 2);
   public static final qt.Av gR = pC("jr", 16785424, 1, 4).pC(ACS.OPS.JUMP, 2);
   public static final qt.Av Ig = pC("lwsp", 8208, 2, 4, 2).pC(ACS.make(ACS.OPS.LOAD, 1, 4, 50331650).operationBitsize(32).extensionMode(1));
   public static final qt.Av TV = pC("ldsp", 8209, 2, 4, 2).pC(ACS.make(ACS.OPS.LOAD, 1, 4, 50331650).operationBitsize(64).extensionMode(1));
   public static final qt.Av pY = pC("swsp", 8208, 2, 4, 2).pC(ACS.make(ACS.OPS.STORE, 2, 8, 50331652).operationBitsize(32).extensionMode(1));
   public static final qt.Av l = pC("sdsp", 8209, 2, 4, 2).pC(ACS.make(ACS.OPS.STORE, 2, 8, 50331652).operationBitsize(64).extensionMode(1));
   public static final qt.Av Tr = pC("sqsp", 8210, 2, 4, 2);
   public static final qt.Av Op = pC("flwsp", 8704, 2, 5, 2);
   public static final qt.Av yB = pC("fswsp", 8704, 2, 5, 2);
   public static final qt.Av Mi = pC("fldsp", 9216, 2, 5, 2);
   public static final qt.Av TD = pC("fsdsp", 9216, 2, 5, 2);

   private static qt.Av pC(String var0, int var1, int var2) {
      return new qt.Av(var0, var1, var2, 0, 0, 0, 0);
   }

   private static qt.Av pC(String var0, int var1, int var2, int var3) {
      return new qt.Av(var0, var1, var2, var3, 0, 0, 0);
   }

   private static qt.Av pC(String var0, int var1, int var2, int var3, int var4) {
      return new qt.Av(var0, var1, var2, var3, var4, 0, 0);
   }

   private static qt.Av pC(String var0, int var1, int var2, int var3, int var4, int var5) {
      return new qt.Av(var0, var1, var2, var3, var4, var5, 0);
   }

   private static qt.Av pC(String var0, int var1, int var2, int var3, int var4, int var5, int var6) {
      return new qt.Av(var0, var1, var2, var3, var4, var5, var6);
   }

   public static class Av {
      public String pC;
      public int A;
      public int kS;
      public int wS;
      ACS UT;

      Av(String var1, int var2, int var3, int var4, int var5, int var6, int var7) {
         this.pC = var1;
         this.A = var2;
         this.kS = var3;
         this.wS = var4 | var5 << 4 | var6 << 8 | var7 << 12;
         Assert.a(qt.pC.put(var1, this) == null);
      }

      public qt.Av pC(ACS.OPS var1, int... var2) {
         this.UT = new ACS(var1, var2);
         return this;
      }

      public qt.Av pC(ACS var1) {
         this.UT = var1;
         return this;
      }
   }
}
