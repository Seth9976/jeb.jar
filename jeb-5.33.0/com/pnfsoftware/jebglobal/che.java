package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.units.code.asm.memory.IVirtualMemory;
import com.pnfsoftware.jeb.core.units.code.asm.memory.MemoryException;
import com.pnfsoftware.jeb.core.units.code.asm.type.INativeType;
import com.pnfsoftware.jeb.core.units.code.asm.type.IStructureType;
import com.pnfsoftware.jeb.core.units.code.asm.type.ITypeManager;

public class che {
   private long pC;
   private int A;
   private int kS;
   private int wS;

   public static che pC(long var0, chg var2) throws MemoryException {
      C var3 = var2.pC;
      IVirtualMemory var4 = var3.getMemory();
      che var5 = new che();
      var5.pC = var0;
      var5.A = var4.readInt(var0);
      long var6 = var0 + 4L;
      if ((var2.pC() || var5.A == 0) && (!var2.pC() || var5.A == 1)) {
         var6 += 4L;
         var6 += 4L;
         var5.kS = var4.readInt(var6);
         var6 += 4L;
         var5.wS = var4.readInt(var6);
         return var5;
      } else {
         return null;
      }
   }

   public static INativeType pC(ITypeManager var0, boolean var1) {
      IStructureType var2 = (IStructureType)var0.getType("_RTTICompleteObjectLocator");
      if (var2 == null) {
         var2 = var0.createStructure("_RTTICompleteObjectLocator");
         var0.addStructureField(var2, "signature", var0.getType("int"));
         var0.addStructureField(var2, "offset", var0.getType("int"));
         var0.addStructureField(var2, "cdOffset", var0.getType("int"));
         chh.pC(var0, var2, "pTypeDescriptor", var1);
         chh.pC(var0, var2, "pClassDescriptor", var1);
         if (var1) {
            chh.pC(var0, var2, "pSelf", var1);
         }
      }

      return var2;
   }

   public long pC() {
      return this.pC;
   }

   public int A() {
      return this.kS;
   }

   public int kS() {
      return this.wS;
   }
}
