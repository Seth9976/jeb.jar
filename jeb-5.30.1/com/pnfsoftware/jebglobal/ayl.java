package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.units.code.asm.mangling.IManglingEngine;
import com.pnfsoftware.jeb.core.units.code.asm.mangling.IUnmangledData;
import com.pnfsoftware.jeb.core.units.code.asm.type.CallingConventionName;

public class ayl implements IManglingEngine {
   @Override
   public IUnmangledData unmangle(String var1) {
      String var2 = null;
      String var3 = null;
      if (var1.startsWith("_")) {
         if (var1.matches(".*@[0-9]+")) {
            var3 = CallingConventionName.STDCALL.toString();
            var2 = var1.substring(1, var1.lastIndexOf("@"));
         } else {
            var3 = CallingConventionName.CDECL.toString();
            var2 = var1.substring(1);
         }
      } else if (var1.startsWith("@") && var1.matches(".*@[0-9]+")) {
         var3 = CallingConventionName.FASTCALL.toString();
         var2 = var1.substring(1, var1.lastIndexOf("@"));
      } else if (var1.matches(".*@@[0-9]+")) {
         var3 = CallingConventionName.VECTORCALL.toString();
         var2 = var1.substring(0, var1.lastIndexOf("@@"));
      }

      if (var2 == null) {
         return null;
      } else {
         ayp var4 = new ayp(var2);
         var4.RF = var3;
         return var4;
      }
   }
}
