package com.pnfsoftware.jeb.core.units.code.asm.processor.arch;

import com.pnfsoftware.jeb.util.io.Endianness;

public class RegisterDataX64 extends AbstractRegisterData {
   public RegisterDataX64() {
      super(RegisterBankX64.getInstance(), Endianness.LITTLE_ENDIAN);
   }
}
