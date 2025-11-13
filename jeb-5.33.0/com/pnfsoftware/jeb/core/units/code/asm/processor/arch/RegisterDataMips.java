package com.pnfsoftware.jeb.core.units.code.asm.processor.arch;

import com.pnfsoftware.jeb.util.io.Endianness;

public class RegisterDataMips extends AbstractRegisterData {
   public RegisterDataMips(Endianness var1) {
      super(RegisterBankMips.getInstance(), var1);
   }
}
