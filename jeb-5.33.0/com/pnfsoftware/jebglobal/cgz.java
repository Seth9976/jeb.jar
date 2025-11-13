package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.units.code.asm.memory.IVirtualMemory;
import com.pnfsoftware.jeb.core.units.code.asm.memory.MemoryException;
import com.pnfsoftware.jeb.core.units.code.asm.type.IStructureType;
import com.pnfsoftware.jeb.core.units.code.asm.type.ITypeManager;

public class cgz extends cgt {
   public long UT;

   public static cgz A(long var0, cgv var2) throws MemoryException {
      C var3 = var2.A;
      IVirtualMemory var4 = var3.getMemory();
      cgz var5 = new cgz();
      if (!pC(var5, var0, var2)) {
         return null;
      } else {
         long var6 = var0 + cgt.pC(var2);
         var5.UT = var2.pC() ? var4.readLong(var6) : var4.readInt(var6) & 4294967295L;
         return var5;
      }
   }

   public static boolean A(ITypeManager var0, boolean var1) {
      if (var0.getType("__si_class_type_info") == null) {
         IStructureType var2 = var0.createStructure("__si_class_type_info");
         pC(var2, var0);
         var0.addStructureField(var2, "__base_type", var0.getVoidReference());
         return true;
      } else {
         return false;
      }
   }

   @Override
   public String UT() {
      return "__si_class_type_info";
   }
}
