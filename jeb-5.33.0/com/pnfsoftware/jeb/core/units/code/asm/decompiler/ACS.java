package com.pnfsoftware.jeb.core.units.code.asm.decompiler;

import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.OperationType;
import com.pnfsoftware.jeb.util.base.Assert;
import com.pnfsoftware.jeb.util.math.MathUtil;

public class ACS {
   public static final int dst = 1;
   public static final int src1 = 2;
   public static final int src2 = 4;
   public static final int src3 = 8;
   public static final int dst_src1 = 3;
   public static final int IMM0 = 65536;
   public static final int GPR_MASK = 251658240;
   public static final int GPR0 = 16777216;
   public static final int GPR1 = 33554432;
   public static final int GPR2 = 50331648;
   public static final int GPR3 = 67108864;
   public static final int GPR4 = 83886080;
   public static final int GPR5 = 100663296;
   public static final int GPR6 = 117440512;
   public static final int GPR7 = 134217728;
   private ACS.OPS opcodeSem;
   private int[] opndSemFlags;
   private Integer operationBitsize;
   private Integer extensionMode;
   private Long maskOnSource;

   public static int regSlice(int var0, int var1) {
      Assert.a(var0 >= 0 && var1 > var0);
      int var2 = 0;
      if (var0 != 0) {
         var2 = MathUtil.log2(var0);
         Assert.a(var0 == 1 << var2 && var2 <= 15);
      }

      int var3 = 0;
      if (var1 != 0) {
         var3 = MathUtil.log2(var1);
         Assert.a(var1 == 1 << var3 && var3 <= 15);
      }

      return var2 << 8 | var3 << 12;
   }

   public static ACS make(ACS.OPS var0, int... var1) {
      return new ACS(var0, var1);
   }

   public ACS(ACS.OPS var1, int[] var2) {
      if (var1 == null) {
         throw new RuntimeException();
      } else {
         this.opcodeSem = var1;
         this.opndSemFlags = var2;
      }
   }

   public ACS(ACS.OPS var1, Integer var2, Integer var3, int[] var4) {
      if (var1 == null) {
         throw new RuntimeException();
      } else {
         this.opcodeSem = var1;
         this.opndSemFlags = var4;
         this.operationBitsize = var2;
         this.extensionMode = var3;
      }
   }

   public ACS.OPS getOpcodeSemantic() {
      return this.opcodeSem;
   }

   public int getOperandSemanticFlags(int var1) {
      return this.opndSemFlags[var1];
   }

   public int findOperandIndexByFlag(int var1) {
      for (int var2 = 0; var2 < this.opndSemFlags.length; var2++) {
         if ((this.opndSemFlags[var2] & var1) == var1) {
            return var2;
         }
      }

      return -1;
   }

   public ACS operationBitsize(Integer var1) {
      this.operationBitsize = var1;
      return this;
   }

   public ACS operationBitsizeAndSignExtendToDest(Integer var1) {
      this.operationBitsize = var1;
      this.extensionMode = 1;
      return this;
   }

   public ACS operationBitsizeAndZeroExtendToDest(Integer var1) {
      this.operationBitsize = var1;
      this.extensionMode = 0;
      return this;
   }

   public Integer operationBitsize() {
      return this.operationBitsize;
   }

   public int getBitsize(int var1) {
      return this.operationBitsize == null ? var1 : this.operationBitsize;
   }

   public ACS extensionMode(Integer var1) {
      this.extensionMode = var1;
      return this;
   }

   public Integer extensionMode() {
      return this.extensionMode;
   }

   public boolean isSignedExtension(boolean var1) {
      if (this.extensionMode == null) {
         return var1;
      } else if (this.extensionMode == 0) {
         return false;
      } else {
         return this.extensionMode == 1 ? true : var1;
      }
   }

   public ACS maskOnSource(Long var1) {
      this.maskOnSource = var1;
      return this;
   }

   public Long maskOnSource() {
      return this.maskOnSource;
   }

   public static enum ExtensionMode {
      NONE,
      SIGN_EXTEND,
      ZERO_EXTEND;
   }

   public static enum OPS {
      CUSTOM,
      NOP,
      MOVE,
      STORE,
      LOAD,
      ADD(OperationType.ADD),
      SUB(OperationType.SUB),
      MUL(OperationType.MUL),
      DIV_S(OperationType.DIV_S),
      DIV_U(OperationType.DIV_U),
      REM_S(OperationType.REM_S),
      REM_U(OperationType.REM_U),
      AND(OperationType.AND),
      OR(OperationType.OR),
      XOR(OperationType.XOR),
      NOT(OperationType.NOT),
      SHL(OperationType.SHL),
      SHR(OperationType.SHR),
      SAR(OperationType.SAR),
      SET_IF_EQ,
      SET_IF_NE,
      SET_IF_LT_S,
      SET_IF_LT_U,
      SET_IF_LE_S,
      SET_IF_LE_U,
      SET_IF_GT_S,
      SET_IF_GT_U,
      SET_IF_GE_S,
      SET_IF_GE_U,
      JUMP_IF_EQ,
      JUMP_IF_NE,
      JUMP_IF_LT_S,
      JUMP_IF_LT_U,
      JUMP_IF_LE_S,
      JUMP_IF_LE_U,
      JUMP_IF_GT_S,
      JUMP_IF_GT_U,
      JUMP_IF_GE_S,
      JUMP_IF_GE_U,
      JUMP;

      private final OperationType op;

      private OPS() {
         this.op = null;
      }

      private OPS(OperationType var3) {
         this.op = var3;
      }

      public OperationType getDirectConversionOperationType() {
         return this.op;
      }
   }
}
