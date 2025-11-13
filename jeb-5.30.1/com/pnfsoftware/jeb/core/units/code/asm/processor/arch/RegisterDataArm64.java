package com.pnfsoftware.jeb.core.units.code.asm.processor.arch;

import com.pnfsoftware.jeb.util.io.Endianness;

public class RegisterDataArm64 extends AbstractRegisterData {
   public RegisterDataArm64(Endianness var1) {
      super(RegisterBankArm64.getInstance(), var1);
   }
}
