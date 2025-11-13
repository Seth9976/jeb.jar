package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.units.code.asm.memory.IVirtualMemory;
import com.pnfsoftware.jeb.core.units.code.asm.memory.MemoryException;
import com.pnfsoftware.jeb.core.units.code.asm.type.IArrayType;
import com.pnfsoftware.jeb.core.units.code.asm.type.INativeType;
import com.pnfsoftware.jeb.core.units.code.asm.type.IStructureType;
import com.pnfsoftware.jeb.core.units.code.asm.type.ITypeManager;
import java.util.ArrayList;
import java.util.List;

public class chb {
   private long pC;
   private List A = new ArrayList();

   public static chb pC(long var0, int var2, chg var3) throws MemoryException {
      C var4 = var3.pC;
      IVirtualMemory var5 = var4.getMemory();
      chb var6 = new chb();
      var6.pC = var0;

      for (int var7 = 0; var7 < var2; var7++) {
         var6.A.add(var3.pC(var5.readInt(var0 + var7 * 4)));
      }

      return var6;
   }

   public INativeType pC(ITypeManager var1, boolean var2) {
      String var3 = "_RTTIBaseClassArray_" + this.A.size();
      IStructureType var4 = (IStructureType)var1.getType(var3);
      if (var4 == null) {
         var4 = var1.createStructure(var3);
         Object var5 = var2 ? var1.getType("int") : var1.getVoidReference();
         IArrayType var6 = var1.createArray((INativeType)var5, this.A.size());
         var1.addStructureField(var4, "arrayOfBaseClassDescriptors", var6, -1, 0, 0, 1);
      }

      return var4;
   }

   public long pC() {
      return this.pC;
   }

   public List A() {
      return this.A;
   }
}
