package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.units.code.asm.memory.IVirtualMemory;
import com.pnfsoftware.jeb.core.units.code.asm.memory.MemoryException;
import com.pnfsoftware.jeb.core.units.code.asm.memory.VirtualMemoryUtil;
import com.pnfsoftware.jeb.core.units.code.asm.type.INativeType;
import com.pnfsoftware.jeb.core.units.code.asm.type.IStructureType;
import com.pnfsoftware.jeb.core.units.code.asm.type.ITypeManager;

public class chj {
   private long pC;
   private long A;
   private String kS;

   public static chj pC(long var0, chg var2) throws MemoryException {
      IVirtualMemory var3 = var2.pC.getMemory();
      return pC(var3, var0, var2.pC());
   }

   public static chj pC(IVirtualMemory var0, long var1, boolean var3) throws MemoryException {
      chj var4 = new chj();
      if (!var3) {
         var4.pC = var1;
         long var5 = var1 + 4L;
         var5 += 4L;
         var4.A = var5;
         var4.kS = VirtualMemoryUtil.readNullTerminatedStringSafe(var0, var4.A, 2048);
         if (var4.kS == null) {
            return null;
         }
      } else {
         var4.pC = var1;
         long var8 = var1 + 8L;
         var8 += 8L;
         var4.A = var8;
         var4.kS = VirtualMemoryUtil.readNullTerminatedStringSafe(var0, var4.A, 2048);
         if (var4.kS == null) {
            return null;
         }
      }

      return var4;
   }

   public static INativeType pC(ITypeManager var0, boolean var1) {
      IStructureType var2 = (IStructureType)var0.getType("TypeDescriptor");
      if (var2 == null) {
         var2 = var0.createStructure("TypeDescriptor");
         var0.addStructureField(var2, "pVFTable", var0.getVoidReference());
         var0.addStructureField(var2, "spare", var0.getVoidReference());
      }

      return var2;
   }

   public long pC() {
      return this.pC;
   }

   public long A() {
      return this.A;
   }

   public String kS() {
      return this.kS;
   }
}
