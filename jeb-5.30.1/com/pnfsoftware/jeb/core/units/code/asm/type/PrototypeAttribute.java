package com.pnfsoftware.jeb.core.units.code.asm.type;

import com.pnfsoftware.jeb.core.units.codeobject.CompilerType;
import com.pnfsoftware.jeb.util.serialization.annotations.Ser;

@Ser
public enum PrototypeAttribute {
   VarArg,
   NoReturn,
   NoThrow,
   Leaf,
   Pure,
   Const,
   Volatile;

   public String format() {
      return this.format(null);
   }

   public String format(CompilerType var1) {
      if (var1 == null) {
         var1 = CompilerType.UNKNOWN;
      }

      String var2 = null;
      switch (this) {
         case Const:
            var2 = "const";
            break;
         case Volatile:
            var2 = "volatile";
            break;
         case Leaf:
            var2 = "leaf";
            break;
         case NoReturn:
            var2 = "noreturn";
            break;
         case NoThrow:
            var2 = "nothrow";
            break;
         case Pure:
            var2 = "pure";
            break;
         case VarArg:
            return null;
      }

      return var1 == CompilerType.MSVC ? "__declspec(" + var2 + ")" : "__attribute__((" + var2 + "))";
   }
}
