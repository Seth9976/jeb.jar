package com.pnfsoftware.jeb.corei.parsers.simatic;

import com.pnfsoftware.jeb.core.units.code.asm.processor.RegisterType;
import com.pnfsoftware.jeb.core.units.code.asm.processor.arch.AbstractRegisterBank2;

public class HE extends AbstractRegisterBank2 {
   public static final HE pC = new HE();

   public HE() {
      this.add(32, "PC").grp(1, 0).typ(RegisterType.ProgramCounter);
      this.add(32, "STW").grp(1, 1).typ(RegisterType.Flags);
      this.add(32, "rZ").grp(0, 0);
      this.add(32, "rI").grp(0, 1);
      this.add(32, "rQ").grp(0, 2);
      this.add(32, "rM").grp(0, 3);
      this.add(32, "rDB").grp(0, 4);
      this.add(32, "rDI").grp(0, 5);
      this.add(32, "SP").addName("rL").grp(0, 6).typ(RegisterType.StackPointer);
      this.add(32, "ACCU1").grp(0, 8);
      this.add(32, "ACCU2").grp(0, 9);
      this.add(32, "ACCU3").grp(0, 10);
      this.add(32, "ACCU4").grp(0, 11);
      this.add(32, "AR1").grp(0, 12);
      this.add(32, "AR2").grp(0, 13);

      for (int var1 = 0; var1 < 16; var1++) {
         this.add(32, "PAR" + var1).grp(0, 16 + var1);
      }

      this.add(32, "LR").grp(0, 32);
   }
}
