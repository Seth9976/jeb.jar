package com.pnfsoftware.jeb.core.units.code.asm.processor.arch;

import com.pnfsoftware.jeb.util.io.Endianness;

public class RegisterDataMips64 extends AbstractRegisterData {
   public RegisterDataMips64(Endianness var1) {
      super(RegisterBankMips64.getInstance(), var1);
   }
}
