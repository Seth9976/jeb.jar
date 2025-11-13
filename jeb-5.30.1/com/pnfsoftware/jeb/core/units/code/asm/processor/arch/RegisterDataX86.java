package com.pnfsoftware.jeb.core.units.code.asm.processor.arch;

import com.pnfsoftware.jeb.util.io.Endianness;

public class RegisterDataX86 extends AbstractRegisterData {
   public RegisterDataX86() {
      super(RegisterBankX86.getInstance(), Endianness.LITTLE_ENDIAN);
   }
}
