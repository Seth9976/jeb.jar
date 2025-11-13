package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.units.code.asm.memory.IVirtualMemory;
import com.pnfsoftware.jeb.core.units.code.asm.memory.MemoryException;
import com.pnfsoftware.jeb.core.units.code.asm.type.IStructureType;
import com.pnfsoftware.jeb.core.units.code.asm.type.ITypeManager;

public class cgs {
   public long pC;
   public long A;

   public static cgs pC(long var0, cgv var2) throws MemoryException {
      C var3 = var2.A;
      IVirtualMemory var4 = var3.getMemory();
      cgs var5 = new cgs();
      var5.pC = var2.pC() ? var4.readLong(var0) : var4.readInt(var0) & 4294967295L;
      long var6 = var0 + (var2.pC() ? 8L : 4L);
      var5.A = var2.pC() ? var4.readLong(var6) : var4.readInt(var6) & 4294967295L;
      return var5;
   }

   public boolean pC() {
      return (this.A & 255L & 1L) != 0L;
   }

   public boolean A() {
      return (this.A & 255L & 2L) != 0L;
   }

   public static int pC(cgv var0) {
      return var0.pC() ? 16 : 8;
   }

   public static boolean pC(ITypeManager var0, boolean var1) {
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
