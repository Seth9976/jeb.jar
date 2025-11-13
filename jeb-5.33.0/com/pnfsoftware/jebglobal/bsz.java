package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.units.code.java.JavaOperatorType;

public enum bsz {
   pC,
   A(true),
   kS,
   wS(true, true, JavaOperatorType.ADD),
   UT(JavaOperatorType.SUB),
   E(true, true, JavaOperatorType.MUL),
   sY(JavaOperatorType.DIV),
   ys(JavaOperatorType.REM),
   ld(JavaOperatorType.NEG),
   gp(JavaOperatorType.NOT),
   oT(true, JavaOperatorType.AND),
   fI(true, JavaOperatorType.OR),
   WR(true, JavaOperatorType.XOR),
   NS(JavaOperatorType.LOG_OR),
   vP(JavaOperatorType.LOG_AND),
   xC(JavaOperatorType.LOG_NOT),
   ED(true, JavaOperatorType.EQ),
   Sc(true, JavaOperatorType.NE),
   ah(JavaOperatorType.GT),
   eP(JavaOperatorType.GE),
   UO(JavaOperatorType.LT),
   Ab(JavaOperatorType.LE),
   rl(JavaOperatorType.SHL),
   z(JavaOperatorType.USHR),
   Ek(JavaOperatorType.SHR);

   private final boolean hK;
   private final boolean Er;
   private final JavaOperatorType FE;

   private bsz() {
      this(false);
   }

   private bsz(boolean var3) {
      this(var3, null);
   }

   private bsz(JavaOperatorType var3) {
      this(false, var3);
   }

   private bsz(boolean var3, JavaOperatorType var4) {
      this(var3, false, var4);
   }

   private bsz(boolean var3, boolean var4, JavaOperatorType var5) {
      this.hK = var3;
      this.Er = var4;
      this.FE = var5;
   }

   public boolean pC() {
      return this.hK;
   }

   public boolean A() {
      return this.Er;
   }

   public JavaOperatorType kS() {
      return this.FE;
   }
}
