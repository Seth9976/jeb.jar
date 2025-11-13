package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.units.code.asm.memory.IVirtualMemory;
import com.pnfsoftware.jeb.core.units.code.asm.memory.MemoryException;
import com.pnfsoftware.jeb.core.units.code.asm.type.IStructureType;
import com.pnfsoftware.jeb.core.units.code.asm.type.ITypeManager;

public class cnw {
   public static final String q = "__base_class_type_info";
   private static final long Dw = 1L;
   private static final long Uv = 2L;
   public long RF;
   public long xK;

   public static cnw q(long var0, cnz var2) throws MemoryException {
      abg var3 = var2.RF;
      IVirtualMemory var4 = var3.getMemory();
      cnw var5 = new cnw();
      var5.RF = var2.q() ? var4.readLong(var0) : var4.readInt(var0) & 4294967295L;
      long var6 = var0 + (var2.q() ? 8L : 4L);
      var5.xK = var2.q() ? var4.readLong(var6) : var4.readInt(var6) & 4294967295L;
      return var5;
   }

   public boolean q() {
      return (this.xK & 255L & 1L) != 0L;
   }

   public boolean RF() {
      return (this.xK & 255L & 2L) != 0L;
   }

   public static int q(cnz var0) {
      return var0.q() ? 16 : 8;
   }

   public static boolean q(ITypeManager var0, boolean var1) {
      if (var0.getType("__base_class_type_info") == null) {
         IStructureType var2 = var0.createStructure("__base_class_type_info");
         var0.addStructureField(var2, "__base_type", var0.getVoidReference());
         var0.addStructureField(var2, "__offset_flags", var0.getExactInteger(var1 ? 8 : 4, false));
         return true;
      } else {
         return false;
      }
   }
}
