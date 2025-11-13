package com.pnfsoftware.jeb.core.units.code.asm.processor.arch;

import com.pnfsoftware.jeb.util.io.Endianness;

public class RegisterDataArm extends AbstractRegisterData {
   public RegisterDataArm(Endianness var1) {
      super(RegisterBankArm.getInstance(), var1);
   }
}
