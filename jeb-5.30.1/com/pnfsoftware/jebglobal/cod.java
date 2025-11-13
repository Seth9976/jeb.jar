package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.units.code.asm.memory.IVirtualMemory;
import com.pnfsoftware.jeb.core.units.code.asm.memory.MemoryException;
import com.pnfsoftware.jeb.core.units.code.asm.type.IStructureType;
import com.pnfsoftware.jeb.core.units.code.asm.type.ITypeManager;

public class cod extends cnx {
   public static final String oW = "__si_class_type_info";
   public long gO;

   public static cod RF(long var0, cnz var2) throws MemoryException {
      abg var3 = var2.RF;
      IVirtualMemory var4 = var3.getMemory();
      cod var5 = new cod();
      if (!q(var5, var0, var2)) {
         return null;
      } else {
         long var6 = var0 + cnx.q(var2);
         var5.gO = var2.q() ? var4.readLong(var6) : var4.readInt(var6) & 4294967295L;
         return var5;
      }
   }

   public static boolean RF(ITypeManager var0, boolean var1) {
      if (var0.getType("__si_class_type_info") == null) {
         IStructureType var2 = var0.createStructure("__si_class_type_info");
         q(var2, var0);
         var0.addStructureField(var2, "__base_type", var0.getVoidReference());
         return true;
      } else {
         return false;
      }
   }

   @Override
   public cnz.eo Uv() {
      return cnz.eo.RF;
   }

   @Override
   public String oW() {
      return "__si_class_type_info";
   }
}
