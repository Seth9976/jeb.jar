package com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.compiler;

import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.OperationType;

public enum O {
   SLICE,
   SLICE_FIRSTBIT,
   SLICE_LASTBIT,
   SLICE_HALFBIT,
   SLICE_FIRST32,
   SLICE_HALF1,
   SLICE_HALF2,
   COMPOSE_2,
   COMPOSE_2EQ,
   COND,
   NEG,
   DIV,
   REM,
   NCADD(true),
   NCSUB,
   TRN,
   TRN8(8),
   TRN16(16),
   TRN32(32),
   TRN64(64),
   TRN128(128),
   EXT,
   EXT8(8),
   EXT16(16),
   EXT32(32),
   EXT64(64),
   EXT128(128),
   ADD(true, true, OperationType.ADD, 0),
   SUB(OperationType.SUB),
   MUL(true, true, OperationType.MUL, 0),
   DIV_S(OperationType.DIV_S),
   DIV_U(OperationType.DIV_U),
   REM_S(OperationType.REM_S),
   REM_U(OperationType.REM_U),
   NOT(OperationType.NOT),
   AND(true, OperationType.AND),
   OR(true, OperationType.OR),
   XOR(true, OperationType.XOR),
   LOR(OperationType.LOG_OR),
   LAND(OperationType.LOG_AND),
   LNOT(OperationType.LOG_NOT),
   EQ(true, OperationType.LOG_EQ),
   NE(true, OperationType.LOG_NEQ),
   GT_S(OperationType.GT_S),
   GE_S(OperationType.GE_S),
   LT_S(OperationType.LT_S),
   LE_S(OperationType.LE_S),
   GT_U(OperationType.GT_U),
   GE_U(OperationType.GE_U),
   LT_U(OperationType.LT_U),
   LE_U(OperationType.LE_U),
   SHL(OperationType.SHL),
   SHR(OperationType.SHR),
   SAR(OperationType.SAR),
   PARITY(OperationType.PAR),
   CARRY(true, OperationType.CARRY),
   POW(OperationType.POW);

   private final boolean commutative;
   private final boolean associative;
   private final OperationType optype;
   private final int resultingBitsize;

   private O() {
      this(false, null);
   }

   private O(int var3) {
      this(false, false, null, var3);
   }

   private O(boolean var3) {
      this(var3, null);
   }

   private O(OperationType var3) {
      this(false, var3);
   }

   private O(boolean var3, OperationType var4) {
      this(var3, false, var4, 0);
   }

   private O(boolean var3, boolean var4, OperationType var5, int var6) {
      this.commutative = var3;
      this.associative = var4;
      this.optype = var5;
      this.resultingBitsize = var6;
   }

   public boolean isCommutative() {
      return this.commutative;
   }

   public boolean isAssociative() {
      return this.associative;
   }

   public OperationType getOperationType() {
      return this.optype;
   }

   public boolean isNormal() {
      return this.optype != null;
   }

   public int getResultingBitsize() {
      return this.resultingBitsize;
   }
}
