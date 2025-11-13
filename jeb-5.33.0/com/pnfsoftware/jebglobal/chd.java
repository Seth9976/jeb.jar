package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.units.code.asm.memory.IVirtualMemory;
import com.pnfsoftware.jeb.core.units.code.asm.memory.MemoryException;
import com.pnfsoftware.jeb.core.units.code.asm.type.INativeType;
import com.pnfsoftware.jeb.core.units.code.asm.type.IStructureType;
import com.pnfsoftware.jeb.core.units.code.asm.type.ITypeManager;

public class chd {
   private long pC;
   private int A;
   private int kS;
   private int wS;
   private long UT;

   public static chd pC(long var0, chg var2) throws MemoryException {
      C var3 = var2.pC;
      IVirtualMemory var4 = var3.getMemory();
      chd var5 = new chd();
      var5.pC = var0;
      var5.A = var4.readInt(var0);
      if (var5.A != 0) {
         return null;
      } else {
         long var6 = var0 + 4L;
         var5.kS = var4.readInt(var6);
         var6 += 4L;
         var5.wS = var4.readInt(var6);
         if (var5.wS == 0) {
            return null;
         } else {
            var6 += 4L;
            var5.UT = var2.pC(var4.readInt(var6));
            return var5;
         }
      }
   }

   public static INativeType pC(ITypeManager var0, boolean var1) {
      IStructureType var2 = (IStructureType)var0.getType("_RTTIClassHierarchyDescriptor");
      if (var0.getType("_RTTIClassHierarchyDescriptor") == null) {
         var2 = var0.createStructure("_RTTIClassHierarchyDescriptor");
         INativeType var3 = var0.getType("int");
         var0.addStructureField(var2, "signature", var3);
         var0.addStructureField(var2, "attributes", var3);
         var0.addStructureField(var2, "numBaseClasses", var3);
         chh.pC(var0, var2, "pBaseClassArray", var1);
      }

      return var2;
   }

   public long pC() {
      return this.pC;
   }

   public int A() {
      return this.wS;
   }

   public long kS() {
      return this.UT;
   }
}
