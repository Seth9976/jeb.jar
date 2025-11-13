package com.pnfsoftware.jeb.corei.parsers.ethereum;

import com.pnfsoftware.jeb.core.units.code.asm.processor.RegisterType;
import com.pnfsoftware.jeb.core.units.code.asm.processor.arch.AbstractRegisterBank2;

public class LR extends AbstractRegisterBank2 {
   public static final LR q = new LR();

   public LR() {
      this.add(256, "pc").grp(1, 0).typ(RegisterType.ProgramCounter);
      this.add(256, "sp").grp(1, 1).typ(RegisterType.StackPointer);
   }
}
