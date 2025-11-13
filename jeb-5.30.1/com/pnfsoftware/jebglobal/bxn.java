package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.units.code.java.JavaOperatorType;

public enum bxn {
   q,
   RF(true),
   xK,
   Dw(true, true, JavaOperatorType.ADD),
   Uv(JavaOperatorType.SUB),
   oW(true, true, JavaOperatorType.MUL),
   gO(JavaOperatorType.DIV),
   nf(JavaOperatorType.REM),
   gP(JavaOperatorType.NEG),
   za(JavaOperatorType.NOT),
   lm(true, JavaOperatorType.AND),
   zz(true, JavaOperatorType.OR),
   JY(true, JavaOperatorType.XOR),
   HF(JavaOperatorType.LOG_OR),
   LK(JavaOperatorType.LOG_AND),
   io(JavaOperatorType.LOG_NOT),
   qa(true, JavaOperatorType.EQ),
   Hk(true, JavaOperatorType.NE),
   Me(JavaOperatorType.GT),
   PV(JavaOperatorType.GE),
   oQ(JavaOperatorType.LT),
   xW(JavaOperatorType.LE),
   KT(JavaOperatorType.SHL),
   Gf(JavaOperatorType.USHR),
   Ef(JavaOperatorType.SHR);

   private final boolean cC;
   private final boolean sH;
   private final JavaOperatorType CE;

   private bxn() {
      this(false);
   }

   private bxn(boolean var3) {
      this(var3, null);
   }

   private bxn(JavaOperatorType var3) {
      this(false, var3);
   }

   private bxn(boolean var3, JavaOperatorType var4) {
      this(var3, false, var4);
   }

   private bxn(boolean var3, boolean var4, JavaOperatorType var5) {
      this.cC = var3;
      this.sH = var4;
      this.CE = var5;
   }

   public boolean q() {
      return this.cC;
   }

   public boolean RF() {
      return this.sH;
   }

   public JavaOperatorType xK() {
      return this.CE;
   }

   public JavaOperatorType Dw() {
      return this.CE;
   }

   public boolean Uv() {
      return this.CE != null;
   }
}
