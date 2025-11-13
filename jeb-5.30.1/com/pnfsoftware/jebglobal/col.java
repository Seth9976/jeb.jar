package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.units.code.asm.type.INativeType;
import com.pnfsoftware.jeb.core.units.code.asm.type.IStructureType;
import com.pnfsoftware.jeb.core.units.code.asm.type.IStructureTypeField;
import com.pnfsoftware.jeb.core.units.code.asm.type.ITypeManager;

class col {
   static IStructureTypeField q(ITypeManager var0, IStructureType var1, String var2, boolean var3) {
      if (var3) {
         INativeType var4 = var0.getType("int");
         return var0.addStructureField(var1, var2, var4, -1, 0, 0, 1);
      } else {
         return var0.addStructureField(var1, var2, var0.getVoidReference());
      }
   }
}
