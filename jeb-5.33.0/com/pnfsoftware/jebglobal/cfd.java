package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.OperationType;
import com.pnfsoftware.jeb.core.units.code.asm.processor.arch.RegisterUtil;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class cfd extends asg {
   public static final asg.K pC = new asg.K(4, false);
   public static final asg.K A = new asg.K(5, false);
   public static final asg.K kS = new asg.K(6, false);
   public static final asg.K wS = new asg.K(4, true);
   public static final asg.K UT = new asg.K(5, true);
   public static final asg.K E = new asg.K(6, true);
   public static final asg.K sY = new asg.K((int)RegisterUtil.createPureRegisterId(12, 3), false);
   public static final asg.K ys = new asg.K((int)RegisterUtil.createPureRegisterId(14, 3), false);
   public static final asg.K ld = new asg.K((int)RegisterUtil.createPureRegisterId(12, 3), true);
   public static final asg.K gp = new asg.K((int)RegisterUtil.createPureRegisterId(14, 3), true);
   public static final asg.K oT = new asg.K(2, false);
   public static final asg.K fI = new asg.K(2, true);
   public static final asg.K WR = new asg.K(0, false);
   public static final asg.K NS = new asg.K(0, true);
   public static final asg.K vP = new asg.K(3, false);
   public static final asg.K[] xC = new asg.K[]{pC};
   public static final asg.K[] ED = new asg.K[]{wS};
   public static final asg.K[] Sc = new asg.K[]{sY};
   public static final asg.K[] ah = new asg.K[]{ld};
   public static final asg.K[] eP = new asg.K[]{pC, A};
   public static final asg.K[] UO = new asg.K[]{wS, E};
   public static final asg.K[] Ab = new asg.K[]{sY, ys};
   public static final asg.K[] rl = new asg.K[]{ld, gp};
   private static final asg.Sv z = new asg.Sv(UO, OperationType.DIV_S, fI);
   private static final asg.Sv Ek = new asg.Sv(UO, OperationType.DIV_U, fI);
   private static final asg.Sv hK = new asg.Sv(UO, OperationType.REM_S, fI);
   private static final asg.Sv Er = new asg.Sv(UO, OperationType.REM_U, fI);
   private static final List FE = new ArrayList();

   @Override
   public List pC() {
      return FE;
   }

   static {
      FE.addAll(Arrays.asList(new asg.Av("__divdi3", z), new asg.Av("__udivdi3", Ek), new asg.Av("__moddi3", hK), new asg.Av("__umoddi3", Er)));
   }
}
