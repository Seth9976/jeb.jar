package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.units.code.asm.processor.RegisterDescriptionEntry;
import com.pnfsoftware.jeb.core.units.code.asm.processor.RegisterType;
import com.pnfsoftware.jeb.core.units.code.asm.processor.arch.AbstractRegisterBank2;
import com.pnfsoftware.jeb.util.base.Assert;

public class cnp extends AbstractRegisterBank2 {
   public static final int q = 2;
   public static final String[] RF = new String[]{
      "zero",
      "ra",
      "sp",
      "gp",
      "tp",
      "t0",
      "t1",
      "t2",
      "s0",
      "s1",
      "a0",
      "a1",
      "a2",
      "a3",
      "a4",
      "a5",
      "a6",
      "a7",
      "s2",
      "s3",
      "s4",
      "s5",
      "s6",
      "s7",
      "s8",
      "s9",
      "s10",
      "s11",
      "t3",
      "t4",
      "t5",
      "t6"
   };
   public static final String[] xK = new String[]{
      "ft0",
      "ft1",
      "ft2",
      "ft3",
      "ft4",
      "ft5",
      "ft6",
      "ft7",
      "fs0",
      "fs1",
      "fa0",
      "fa1",
      "fa2",
      "fa3",
      "fa4",
      "fa5",
      "fa6",
      "fa7",
      "fs2",
      "fs3",
      "fs4",
      "fs5",
      "fs6",
      "fs7",
      "fs8",
      "fs9",
      "fs10",
      "fs11",
      "ft8",
      "ft9",
      "ft10",
      "ft11"
   };
   public static final cnp Dw = new cnp(32, 64);
   public static final cnp Uv = new cnp(64, 64);

   public cnp(int var1, int var2) {
      Assert.a(var1 == 32 || var1 == 64 || var2 == 128);
      Assert.a(var2 == 32 || var2 == 64 || var2 == 128);

      for (int var3 = 0; var3 <= 31; var3++) {
         RegisterDescriptionEntry var4 = add(this.entries, var1, "x" + var3, RF[var3]).grp(0, var3);
         if (var3 == 2) {
            var4.typ(RegisterType.StackPointer);
         }
      }

      for (int var5 = 0; var5 <= 31; var5++) {
         this.add(var2, "f" + var5, xK[var5]).grp(2, var5);
      }

      this.add(var1, "pc").grp(1, 0).typ(RegisterType.ProgramCounter);
      this.add(var2, "fcsr").grp(1, 1);
   }
}
