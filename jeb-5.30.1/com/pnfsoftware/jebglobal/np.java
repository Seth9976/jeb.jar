package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.OperationType;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class np extends auxx {
   public static final auxx.nI q = new auxx.nI(0, false);
   public static final auxx.nI RF = new auxx.nI(1, false);
   public static final auxx.nI xK = new auxx.nI(2, false);
   public static final auxx.nI Dw = new auxx.nI(0, true);
   public static final auxx.nI Uv = new auxx.nI(1, true);
   public static final auxx.nI oW = new auxx.nI(2, true);
   public static final auxx.nI gO = q;
   public static final auxx.nI nf = RF;
   public static final auxx.nI gP = xK;
   public static final auxx.nI za = Dw;
   public static final auxx.nI lm = Uv;
   public static final auxx.nI zz = oW;
   public static final auxx.nI JY = q;
   public static final auxx.nI HF = Dw;
   public static final auxx.nI LK = JY;
   public static final auxx.nI io = HF;
   public static final auxx.nI qa = RF;
   public static final auxx.nI Hk = oW;
   public static final auxx.nI[] Me = new auxx.nI[]{q};
   public static final auxx.nI[] PV = new auxx.nI[]{Dw};
   public static final auxx.nI[] oQ = new auxx.nI[]{gO};
   public static final auxx.nI[] xW = new auxx.nI[]{za};
   public static final auxx.nI[] KT = new auxx.nI[]{q, RF};
   public static final auxx.nI[] Gf = new auxx.nI[]{Dw, oW};
   public static final auxx.nI[] Ef = new auxx.nI[]{gO, nf};
   public static final auxx.nI[] cC = new auxx.nI[]{za, zz};
   private static final auxx.CU sH = new auxx.CU(KT, OperationType.DIV_S, JY);
   private static final auxx.CU CE = new auxx.CU(KT, OperationType.DIV_U, JY);
   private static final auxx.CU wF = new auxx.CU(KT, OperationType.REM_S, qa);
   private static final auxx.CU If = new auxx.CU(KT, OperationType.REM_U, qa);
   private static final auxx.CU Dz = new auxx.CU(Gf, OperationType.DIV_S, HF);
   private static final auxx.CU mI = new auxx.CU(Gf, OperationType.DIV_U, HF);
   private static final auxx.CU jq = new auxx.CU(Gf, OperationType.REM_S, Hk);
   private static final auxx.CU ui = new auxx.CU(Gf, OperationType.REM_U, Hk);
   private static final auxx.CU TX = new auxx.CU(Ef, OperationType.FADD, LK);
   private static final auxx.CU Rr = new auxx.CU(cC, OperationType.FADD, io);
   private static final auxx.CU EB = new auxx.CU(Ef, OperationType.FSUB, LK);
   private static final auxx.CU Xo = new auxx.CU(cC, OperationType.FSUB, io);
   private static final auxx.CU Bu = new auxx.CU(new auxx.nI[]{nf, gO}, OperationType.FSUB, LK);
   private static final auxx.CU IN = new auxx.CU(new auxx.nI[]{zz, za}, OperationType.FSUB, io);
   private static final auxx.CU rL = new auxx.CU(Ef, OperationType.FMUL, LK);
   private static final auxx.CU eJ = new auxx.CU(cC, OperationType.FMUL, io);
   private static final auxx.CU YN = new auxx.CU(Ef, OperationType.FDIV, LK);
   private static final auxx.CU Rv = new auxx.CU(cC, OperationType.FDIV, io);
   private static final auxx.CU zx = new auxx.CU(Ef, OperationType.FEQ, JY);
   private static final auxx.CU ZT = new auxx.CU(Ef, OperationType.FLT, JY);
   private static final auxx.CU Ri = new auxx.CU(Ef, OperationType.FLE, JY);
   private static final auxx.CU GY = new auxx.CU(Ef, OperationType.FGT, JY);
   private static final auxx.CU Wx = new auxx.CU(Ef, OperationType.FGE, JY);
   private static final auxx.CU AB = new auxx.CU(cC, OperationType.FEQ, JY);
   private static final auxx.CU CY = new auxx.CU(cC, OperationType.FLT, JY);
   private static final auxx.CU WI = new auxx.CU(cC, OperationType.FLE, JY);
   private static final auxx.CU Tq = new auxx.CU(cC, OperationType.FGT, JY);
   private static final auxx.CU Yp = new auxx.CU(cC, OperationType.FGE, JY);
   private static final auxx.CU Gu = new auxx.CU(xW, OperationType.FP2INT, JY);
   private static final auxx.CU nY = new auxx.CU(xW, OperationType.FP2INT, HF);
   private static final auxx.CU lF = new auxx.CU(oQ, OperationType.FP2INT, JY);
   private static final auxx.CU nq = new auxx.CU(oQ, OperationType.FP2INT, HF);
   private static final auxx.CU NX = new auxx.CU(Me, OperationType.INT2FP, io);
   private static final auxx.CU br = new auxx.CU(PV, OperationType.INT2FP, io);
   private static final auxx.CU tW = new auxx.CU(Me, OperationType.INT2FP, LK);
   private static final auxx.CU ZA = new auxx.CU(PV, OperationType.INT2FP, LK);
   private static final auxx.CU Ov = new auxx.CU(xW, OperationType.FP2UINT, JY);
   private static final auxx.CU Lj = new auxx.CU(xW, OperationType.FP2UINT, HF);
   private static final auxx.CU nv = new auxx.CU(oQ, OperationType.FP2UINT, JY);
   private static final auxx.CU LL = new auxx.CU(oQ, OperationType.FP2UINT, HF);
   private static final auxx.CU PQ = new auxx.CU(Me, OperationType.UINT2FP, io);
   private static final auxx.CU fQ = new auxx.CU(PV, OperationType.UINT2FP, io);
   private static final auxx.CU fi = new auxx.CU(Me, OperationType.UINT2FP, LK);
   private static final auxx.CU bl = new auxx.CU(PV, OperationType.UINT2FP, LK);
   private static final auxx.CU jb = new auxx.CU(xW, OperationType.FP2FP, LK);
   private static final auxx.CU pQ = new auxx.CU(oQ, OperationType.FP2FP, io);
   private static final List kf = new ArrayList();

   @Override
   public List q() {
      return kf;
   }

   static {
      kf.addAll(
         Arrays.asList(
            new auxx.eo("__aeabi_idiv", "__divsi3", sH),
            new auxx.eo("__aeabi_uidiv", "__udivsi3", CE),
            new auxx.eo("__aeabi_idivmod", sH, wF),
            new auxx.eo("__aeabi_uidivmod", CE, If),
            new auxx.eo("__aeabi_ldivmod", "__divdi3", Dz, jq),
            new auxx.eo("__aeabi_uldivmod", "__udivdi3", mI, ui),
            new auxx.eo("__aeabi_dadd", "__adddf3", Rr),
            new auxx.eo("__aeabi_ddiv", "__divdf3", Rv),
            new auxx.eo("__aeabi_dmul", "__muldf3", eJ),
            new auxx.eo("__aeabi_dsub", "__subdf3", Xo),
            new auxx.eo("__aeabi_drsub", IN),
            new auxx.eo("__aeabi_dcmpeq", AB),
            new auxx.eo("__aeabi_dcmplt", CY),
            new auxx.eo("__aeabi_dcmple", WI),
            new auxx.eo("__aeabi_dcmpge", Yp),
            new auxx.eo("__aeabi_dcmpgt", Tq),
            new auxx.eo("__aeabi_fadd", "__addsf3", TX),
            new auxx.eo("__aeabi_fdiv", "__divsf3", YN),
            new auxx.eo("__aeabi_fmul", "__mulsf3", rL),
            new auxx.eo("__aeabi_fsub", "__subsf3", EB),
            new auxx.eo("__aeabi_frsub", Bu),
            new auxx.eo("__aeabi_fcmpeq", zx),
            new auxx.eo("__aeabi_fcmplt", ZT),
            new auxx.eo("__aeabi_fcmple", Ri),
            new auxx.eo("__aeabi_fcmpge", Wx),
            new auxx.eo("__aeabi_fcmpgt", GY),
            new auxx.eo("__aeabi_d2iz", "__fixdfsi", Gu),
            new auxx.eo("__aeabi_d2uiz", "__fixunsdfsi", Ov),
            new auxx.eo("__aeabi_d2lz", "__fixdfdi", nY),
            new auxx.eo("__aeabi_d2ulz", "__fixunsdfdi", Lj),
            new auxx.eo("__aeabi_f2iz", "__fixsfsi", lF),
            new auxx.eo("__aeabi_f2uiz", "__fixunssfsi", nv),
            new auxx.eo("__aeabi_f2lz", "__fixsfdi", nq),
            new auxx.eo("__aeabi_f2ulz", "__fixunssfdi", LL),
            new auxx.eo("__aeabi_d2f", "__truncdfsf2", jb),
            new auxx.eo("__aeabi_f2d", "__extendsfdf2", pQ),
            new auxx.eo("__aeabi_i2d", "__floatsidf", NX),
            new auxx.eo("__aeabi_ui2d", "__floatunsidf", PQ),
            new auxx.eo("__aeabi_l2d", "__floatdidf", br),
            new auxx.eo("__aeabi_ul2d", "__floatundidf", fQ),
            new auxx.eo("__aeabi_i2f", "__floatsisf", tW),
            new auxx.eo("__aeabi_ui2f", "__floatunsisf", fi),
            new auxx.eo("__aeabi_l2f", "__floatdisf", ZA),
            new auxx.eo("__aeabi_ul2f", "__floatundisf", bl)
         )
      );
   }
}
