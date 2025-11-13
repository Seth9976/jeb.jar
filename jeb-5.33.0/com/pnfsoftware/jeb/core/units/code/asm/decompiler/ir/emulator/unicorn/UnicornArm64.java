package com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.emulator.unicorn;

import com.sun.jna.Structure;
import com.sun.jna.Structure.FieldOrder;

public class UnicornArm64 {
   public static final UnicornArm64.SysReg MIDR_EL1 = new UnicornArm64.SysReg(3, 0, 0, 0, 0);
   public static final UnicornArm64.SysReg MPIDR_EL1 = new UnicornArm64.SysReg(3, 0, 0, 0, 5);
   public static final UnicornArm64.SysReg SCTLR_EL1 = new UnicornArm64.SysReg(3, 0, 1, 0, 0);
   public static final UnicornArm64.SysReg CPACR_EL1 = new UnicornArm64.SysReg(3, 0, 1, 0, 2);
   public static final UnicornArm64.SysReg TTBR0_EL1 = new UnicornArm64.SysReg(3, 0, 2, 0, 0);
   public static final UnicornArm64.SysReg TTBR1_EL1 = new UnicornArm64.SysReg(3, 0, 2, 0, 1);
   public static final UnicornArm64.SysReg TCR_EL1 = new UnicornArm64.SysReg(3, 0, 2, 0, 2);
   public static final UnicornArm64.SysReg VBAR_EL1 = new UnicornArm64.SysReg(3, 0, 12, 0, 0);
   public static final UnicornArm64.SysReg TPIDR_EL0 = new UnicornArm64.SysReg(3, 3, 13, 0, 2);
   public static final UnicornArm64.SysReg TPIDR_EL1 = new UnicornArm64.SysReg(3, 0, 13, 0, 4);
   public static final UnicornArm64.SysReg CTR_EL0 = new UnicornArm64.SysReg(3, 3, 0, 0, 1);
   public static final UnicornArm64.SysReg DCZID_EL0 = new UnicornArm64.SysReg(3, 3, 0, 0, 7);
   public static final UnicornArm64.SysReg FPCR = new UnicornArm64.SysReg(3, 3, 4, 4, 0);
   public static final UnicornArm64.SysReg FPSR = new UnicornArm64.SysReg(3, 3, 4, 4, 1);
   public static final UnicornArm64.SysReg NZCV = new UnicornArm64.SysReg(3, 3, 4, 2, 0);
   public static final int UC_ARM64_REG_CP_REG = 24576;

   public static UnicornArm64.SysReg createSysReg(int var0, int var1, int var2, int var3, int var4) {
      return new UnicornArm64.SysReg(var0, var1, var2, var3, var4);
   }

   public static UnicornArm64.SysReg parseSysRegName(String var0) {
      String[] var1 = var0.toUpperCase().split("_");
      if (var1.length == 5 && var1[0].startsWith("S") && var1[2].startsWith("C") && var1[3].startsWith("C")) {
         int var2 = Integer.parseInt(var1[0].substring(1));
         int var3 = Integer.parseInt(var1[1]);
         int var4 = Integer.parseInt(var1[2].substring(1));
         int var5 = Integer.parseInt(var1[3].substring(1));
         int var6 = Integer.parseInt(var1[4]);
         return new UnicornArm64.SysReg(var2, var3, var4, var5, var6);
      } else {
         throw new IllegalArgumentException("Invalid system register format: " + var0);
      }
   }

   public static long readSysReg(UnicornEngine var0, UnicornArm64.SysReg var1) {
      UnicornArm64.SysReg var2 = new UnicornArm64.SysReg(var1.op0, var1.op1, var1.crn, var1.crm, var1.op2);
      var2.write();
      byte[] var3 = var2.getPointer().getByteArray(0L, var2.size());
      var0.regRead(290, var3);
      var2.getPointer().write(0L, var3, 0, var3.length);
      var2.read();
      return var2.value;
   }

   public static void writeSysReg(UnicornEngine var0, UnicornArm64.SysReg var1, long var2) {
      UnicornArm64.SysReg var4 = new UnicornArm64.SysReg(var1.op0, var1.op1, var1.crn, var1.crm, var1.op2);
      var4.value = var2;
      var4.write();
      byte[] var5 = var4.getPointer().getByteArray(0L, var4.size());
      var0.regWrite(290, var5);
   }

   @FieldOrder({"crn", "crm", "op0", "op1", "op2", "value"})
   public static class SysReg extends Structure {
      public int crn;
      public int crm;
      public int op0;
      public int op1;
      public int op2;
      public long value;

      public SysReg() {
      }

      public SysReg(int var1, int var2, int var3, int var4, int var5) {
         this.crn = var3;
         this.crm = var4;
         this.op0 = var1;
         this.op1 = var2;
         this.op2 = var5;
         this.value = 0L;
      }

      public String toString() {
         return String.format("S%d_%d_C%d_C%d_%d (value: 0x%016x)", this.op0, this.op1, this.crn, this.crm, this.op2, this.value);
      }
   }
}
