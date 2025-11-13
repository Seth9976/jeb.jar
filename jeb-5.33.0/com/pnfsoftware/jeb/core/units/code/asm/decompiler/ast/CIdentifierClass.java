package com.pnfsoftware.jeb.core.units.code.asm.decompiler.ast;

import com.pnfsoftware.jeb.util.serialization.annotations.Ser;

@Ser
public enum CIdentifierClass {
   GLOBAL,
   LOCAL,
   BUILTIN,
   SYNTHETIC,
   SPECIAL;

   public boolean isGlobal() {
      return this == GLOBAL || this == BUILTIN;
   }

   public boolean isLocal() {
      return this == LOCAL || this == SYNTHETIC;
   }
}
