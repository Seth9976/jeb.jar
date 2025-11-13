package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.units.code.asm.memory.IVirtualMemory;
import com.pnfsoftware.jeb.core.units.code.asm.memory.MemoryException;
import com.pnfsoftware.jeb.core.units.code.asm.type.IArrayType;
import com.pnfsoftware.jeb.core.units.code.asm.type.INativeType;
import com.pnfsoftware.jeb.core.units.code.asm.type.IStructureType;
import com.pnfsoftware.jeb.core.units.code.asm.type.ITypeManager;
import java.util.ArrayList;
import java.util.List;

public class cof {
   public static final String q = "_RTTIBaseClassArray_";
   private long RF;
   private List xK = new ArrayList();

   public static cof q(long var0, int var2, cok var3) throws MemoryException {
      abg var4 = var3.q;
      IVirtualMemory var5 = var4.getMemory();
      cof var6 = new cof();
      var6.RF = var0;

      for (int var7 = 0; var7 < var2; var7++) {
         var6.xK.add(var3.q(var5.readInt(var0 + var7 * 4)));
      }

      return var6;
   }

   public INativeType q(ITypeManager var1, boolean var2) {
      String var3 = "_RTTIBaseClassArray_" + this.xK.size();
      IStructureType var4 = (IStructureType)var1.getType(var3);
      if (var4 == null) {
         var4 = var1.createStructure(var3);
         Object var5 = var2 ? var1.getType("int") : var1.getVoidReference();
         IArrayType var6 = var1.createArray((INativeType)var5, this.xK.size());
         var1.addStructureField(var4, "arrayOfBaseClassDescriptors", var6, -1, 0, 0, 1);
      }

      return var4;
   }

   public long q() {
      return this.RF;
   }

   public List RF() {
      return this.xK;
   }
}
