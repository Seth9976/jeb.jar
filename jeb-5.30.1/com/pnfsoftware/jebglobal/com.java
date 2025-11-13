package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.units.code.asm.memory.IVirtualMemory;
import com.pnfsoftware.jeb.core.units.code.asm.memory.MemoryException;
import com.pnfsoftware.jeb.core.units.code.asm.type.INativeType;
import com.pnfsoftware.jeb.core.units.code.asm.type.IStructureType;
import com.pnfsoftware.jeb.core.units.code.asm.type.ITypeManager;

public class com {
   public static final String q = "PMD";
   public static final int RF = 12;
   public int xK;
   public int Dw;
   public int Uv;

   public static com q(long var0, IVirtualMemory var2) throws MemoryException {
      com var3 = new com();
      var3.xK = var2.readInt(var0);
      long var4 = var0 + 4L;
      var3.Dw = var2.readInt(var4);
      var4 += 4L;
      var3.Uv = var2.readInt(var4);
      return var3;
   }

   public static INativeType q(ITypeManager var0) {
      IStructureType var1 = (IStructureType)var0.getType("PMD");
      if (var0.getType("PMD") == null) {
         var1 = var0.createStructure("PMD");
         INativeType var2 = var0.getType("int");
         var0.addStructureField(var1, "mdisp", var2);
         var0.addStructureField(var1, "pdisp", var2);
         var0.addStructureField(var1, "vdisp", var2);
      }

      return var1;
   }
}
