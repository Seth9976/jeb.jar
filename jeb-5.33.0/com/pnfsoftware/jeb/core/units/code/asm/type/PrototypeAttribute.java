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

   // $VF: Unable to simplify switch on enum
   // Please report this to the Vineflower issue tracker, at https://github.com/Vineflower/vineflower/issues with a copy of the class file (if you have the rights to distribute it!)
   public String format(CompilerType var1) {
      if (var1 == null) {
         var1 = CompilerType.UNKNOWN;
      }

      String var2 = null;
      switch (PrototypeAttribute$1.$SwitchMap$com$pnfsoftware$jeb$core$units$code$asm$type$PrototypeAttribute[this.ordinal()]) {
         case 1:
            var2 = "const";
            break;
         case 2:
            var2 = "volatile";
            break;
         case 3:
            var2 = "leaf";
            break;
         case 4:
            var2 = "noreturn";
            break;
         case 5:
            var2 = "nothrow";
            break;
         case 6:
            var2 = "pure";
            break;
         case 7:
            return null;
      }

      return var1 == CompilerType.MSVC ? "__declspec(" + var2 + ")" : "__attribute__((" + var2 + "))";
   }
}
