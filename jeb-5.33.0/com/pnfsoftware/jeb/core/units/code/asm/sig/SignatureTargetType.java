package com.pnfsoftware.jeb.core.units.code.asm.sig;

import com.pnfsoftware.jeb.util.serialization.annotations.Ser;

@Ser
public enum SignatureTargetType {
   ROUTINE,
   BASIC_BLOCK,
   INSTRUCTION,
   DATA;
}
