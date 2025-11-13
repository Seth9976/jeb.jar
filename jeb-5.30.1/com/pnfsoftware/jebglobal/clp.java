package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.OperationType;
import com.pnfsoftware.jeb.core.units.code.asm.processor.arch.RegisterUtil;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class clp extends auxx {
   public static final auxx.nI q = new auxx.nI(4, false);
   public static final auxx.nI RF = new auxx.nI(5, false);
   public static final auxx.nI xK = new auxx.nI(6, false);
   public static final auxx.nI Dw = new auxx.nI(4, true);
   public static final auxx.nI Uv = new auxx.nI(5, true);
   public static final auxx.nI oW = new auxx.nI(6, true);
   public static final auxx.nI gO = new auxx.nI((int)RegisterUtil.createPureRegisterId(12, 3), false);
   public static final auxx.nI nf = new auxx.nI((int)RegisterUtil.createPureRegisterId(14, 3), false);
   public static final auxx.nI gP = new auxx.nI((int)RegisterUtil.createPureRegisterId(12, 3), true);
   public static final auxx.nI za = new auxx.nI((int)RegisterUtil.createPureRegisterId(14, 3), true);
   public static final auxx.nI lm = new auxx.nI(2, false);
   public static final auxx.nI zz = new auxx.nI(2, true);
   public static final auxx.nI JY = new auxx.nI(0, false);
   public static final auxx.nI HF = new auxx.nI(0, true);
   public static final auxx.nI LK = new auxx.nI(3, false);
   public static final auxx.nI[] io = new auxx.nI[]{q};
   public static final auxx.nI[] qa = new auxx.nI[]{Dw};
   public static final auxx.nI[] Hk = new auxx.nI[]{gO};
   public static final auxx.nI[] Me = new auxx.nI[]{gP};
   public static final auxx.nI[] PV = new auxx.nI[]{q, RF};
   public static final auxx.nI[] oQ = new auxx.nI[]{Dw, oW};
   public static final auxx.nI[] xW = new auxx.nI[]{gO, nf};
   public static final auxx.nI[] KT = new auxx.nI[]{gP, za};
   private static final auxx.CU Gf = new auxx.CU(oQ, OperationType.DIV_S, zz);
   private static final auxx.CU Ef = new auxx.CU(oQ, OperationType.DIV_U, zz);
   private static final auxx.CU cC = new auxx.CU(oQ, OperationType.REM_S, zz);
   private static final auxx.CU sH = new auxx.CU(oQ, OperationType.REM_U, zz);
   private static final List CE = new ArrayList();

   @Override
   public List q() {
      return CE;
   }

   static {
      CE.addAll(Arrays.asList(new auxx.eo("__divdi3", Gf), new auxx.eo("__udivdi3", Ef), new auxx.eo("__moddi3", cC), new auxx.eo("__umoddi3", sH)));
   }
}
