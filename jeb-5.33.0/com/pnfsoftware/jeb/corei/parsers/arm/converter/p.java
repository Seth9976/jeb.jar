package com.pnfsoftware.jeb.corei.parsers.arm.converter;

import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.OperationType;
import com.pnfsoftware.jebglobal.asg;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class p extends asg {
   public static final asg.K pC = new asg.K(0, false);
   public static final asg.K A = new asg.K(1, false);
   public static final asg.K kS = new asg.K(2, false);
   public static final asg.K wS = new asg.K(0, true);
   public static final asg.K UT = new asg.K(1, true);
   public static final asg.K E = new asg.K(2, true);
   public static final asg.K sY = pC;
   public static final asg.K ys = A;
   public static final asg.K ld = kS;
   public static final asg.K gp = wS;
   public static final asg.K oT = UT;
   public static final asg.K fI = E;
   public static final asg.K WR = pC;
   public static final asg.K NS = wS;
   public static final asg.K vP = WR;
   public static final asg.K xC = NS;
   public static final asg.K ED = A;
   public static final asg.K Sc = E;
   public static final asg.K[] ah = new asg.K[]{pC};
   public static final asg.K[] eP = new asg.K[]{wS};
   public static final asg.K[] UO = new asg.K[]{sY};
   public static final asg.K[] Ab = new asg.K[]{gp};
   public static final asg.K[] rl = new asg.K[]{pC, A};
   public static final asg.K[] z = new asg.K[]{wS, E};
   public static final asg.K[] Ek = new asg.K[]{sY, ys};
   public static final asg.K[] hK = new asg.K[]{gp, fI};
   private static final asg.Sv Er = new asg.Sv(rl, OperationType.DIV_S, WR);
   private static final asg.Sv FE = new asg.Sv(rl, OperationType.DIV_U, WR);
   private static final asg.Sv Aj = new asg.Sv(rl, OperationType.REM_S, ED);
   private static final asg.Sv EX = new asg.Sv(rl, OperationType.REM_U, ED);
   private static final asg.Sv LM = new asg.Sv(z, OperationType.DIV_S, NS);
   private static final asg.Sv mv = new asg.Sv(z, OperationType.DIV_U, NS);
   private static final asg.Sv sO = new asg.Sv(z, OperationType.REM_S, Sc);
   private static final asg.Sv os = new asg.Sv(z, OperationType.REM_U, Sc);
   private static final asg.Sv Cu = new asg.Sv(Ek, OperationType.FADD, vP);
   private static final asg.Sv hZ = new asg.Sv(hK, OperationType.FADD, xC);
   private static final asg.Sv UW = new asg.Sv(Ek, OperationType.FSUB, vP);
   private static final asg.Sv PR = new asg.Sv(hK, OperationType.FSUB, xC);
   private static final asg.Sv cX = new asg.Sv(new asg.K[]{ys, sY}, OperationType.FSUB, vP);
   private static final asg.Sv DQ = new asg.Sv(new asg.K[]{fI, gp}, OperationType.FSUB, xC);
   private static final asg.Sv ZN = new asg.Sv(Ek, OperationType.FMUL, vP);
   private static final asg.Sv OB = new asg.Sv(hK, OperationType.FMUL, xC);
   private static final asg.Sv pF = new asg.Sv(Ek, OperationType.FDIV, vP);
   private static final asg.Sv Bc = new asg.Sv(hK, OperationType.FDIV, xC);
   private static final asg.Sv OI = new asg.Sv(Ek, OperationType.FEQ, WR);
   private static final asg.Sv Bf = new asg.Sv(Ek, OperationType.FLT, WR);
   private static final asg.Sv Pe = new asg.Sv(Ek, OperationType.FLE, WR);
   private static final asg.Sv ck = new asg.Sv(Ek, OperationType.FGT, WR);
   private static final asg.Sv RW = new asg.Sv(Ek, OperationType.FGE, WR);
   private static final asg.Sv e = new asg.Sv(hK, OperationType.FEQ, WR);
   private static final asg.Sv xM = new asg.Sv(hK, OperationType.FLT, WR);
   private static final asg.Sv kU = new asg.Sv(hK, OperationType.FLE, WR);
   private static final asg.Sv Kq = new asg.Sv(hK, OperationType.FGT, WR);
   private static final asg.Sv go = new asg.Sv(hK, OperationType.FGE, WR);
   private static final asg.Sv JF = new asg.Sv(Ab, OperationType.FP2INT, WR);
   private static final asg.Sv Nq = new asg.Sv(Ab, OperationType.FP2INT, NS);
   private static final asg.Sv pg = new asg.Sv(UO, OperationType.FP2INT, WR);
   private static final asg.Sv gj = new asg.Sv(UO, OperationType.FP2INT, NS);
   private static final asg.Sv ZD = new asg.Sv(ah, OperationType.INT2FP, xC);
   private static final asg.Sv DL = new asg.Sv(eP, OperationType.INT2FP, xC);
   private static final asg.Sv UH = new asg.Sv(ah, OperationType.INT2FP, vP);
   private static final asg.Sv VD = new asg.Sv(eP, OperationType.INT2FP, vP);
   private static final asg.Sv Xs = new asg.Sv(Ab, OperationType.FP2UINT, WR);
   private static final asg.Sv KV = new asg.Sv(Ab, OperationType.FP2UINT, NS);
   private static final asg.Sv FK = new asg.Sv(UO, OperationType.FP2UINT, WR);
   private static final asg.Sv Bi = new asg.Sv(UO, OperationType.FP2UINT, NS);
   private static final asg.Sv wQ = new asg.Sv(ah, OperationType.UINT2FP, xC);
   private static final asg.Sv PZ = new asg.Sv(eP, OperationType.UINT2FP, xC);
   private static final asg.Sv Ip = new asg.Sv(ah, OperationType.UINT2FP, vP);
   private static final asg.Sv Fm = new asg.Sv(eP, OperationType.UINT2FP, vP);
   private static final asg.Sv FM = new asg.Sv(Ab, OperationType.FP2FP, vP);
   private static final asg.Sv Wn = new asg.Sv(UO, OperationType.FP2FP, xC);
   private static final List gy = new ArrayList();

   @Override
   public List pC() {
      return gy;
   }

   static {
      gy.addAll(
         Arrays.asList(
            new asg.Av("__aeabi_idiv", "__divsi3", Er),
            new asg.Av("__aeabi_uidiv", "__udivsi3", FE),
            new asg.Av("__aeabi_idivmod", Er, Aj),
            new asg.Av("__aeabi_uidivmod", FE, EX),
            new asg.Av("__aeabi_ldivmod", "__divdi3", LM, sO),
            new asg.Av("__aeabi_uldivmod", "__udivdi3", mv, os),
            new asg.Av("__aeabi_dadd", "__adddf3", hZ),
            new asg.Av("__aeabi_ddiv", "__divdf3", Bc),
            new asg.Av("__aeabi_dmul", "__muldf3", OB),
            new asg.Av("__aeabi_dsub", "__subdf3", PR),
            new asg.Av("__aeabi_drsub", DQ),
            new asg.Av("__aeabi_dcmpeq", e),
            new asg.Av("__aeabi_dcmplt", xM),
            new asg.Av("__aeabi_dcmple", kU),
            new asg.Av("__aeabi_dcmpge", go),
            new asg.Av("__aeabi_dcmpgt", Kq),
            new asg.Av("__aeabi_fadd", "__addsf3", Cu),
            new asg.Av("__aeabi_fdiv", "__divsf3", pF),
            new asg.Av("__aeabi_fmul", "__mulsf3", ZN),
            new asg.Av("__aeabi_fsub", "__subsf3", UW),
            new asg.Av("__aeabi_frsub", cX),
            new asg.Av("__aeabi_fcmpeq", OI),
            new asg.Av("__aeabi_fcmplt", Bf),
            new asg.Av("__aeabi_fcmple", Pe),
            new asg.Av("__aeabi_fcmpge", RW),
            new asg.Av("__aeabi_fcmpgt", ck),
            new asg.Av("__aeabi_d2iz", "__fixdfsi", JF),
            new asg.Av("__aeabi_d2uiz", "__fixunsdfsi", Xs),
            new asg.Av("__aeabi_d2lz", "__fixdfdi", Nq),
            new asg.Av("__aeabi_d2ulz", "__fixunsdfdi", KV),
            new asg.Av("__aeabi_f2iz", "__fixsfsi", pg),
            new asg.Av("__aeabi_f2uiz", "__fixunssfsi", FK),
            new asg.Av("__aeabi_f2lz", "__fixsfdi", gj),
            new asg.Av("__aeabi_f2ulz", "__fixunssfdi", Bi),
            new asg.Av("__aeabi_d2f", "__truncdfsf2", FM),
            new asg.Av("__aeabi_f2d", "__extendsfdf2", Wn),
            new asg.Av("__aeabi_i2d", "__floatsidf", ZD),
            new asg.Av("__aeabi_ui2d", "__floatunsidf", wQ),
            new asg.Av("__aeabi_l2d", "__floatdidf", DL),
            new asg.Av("__aeabi_ul2d", "__floatundidf", PZ),
            new asg.Av("__aeabi_i2f", "__floatsisf", UH),
            new asg.Av("__aeabi_ui2f", "__floatunsisf", Ip),
            new asg.Av("__aeabi_l2f", "__floatdisf", VD),
            new asg.Av("__aeabi_ul2f", "__floatundisf", Fm)
         )
      );
   }
}
