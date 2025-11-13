package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.units.code.asm.memory.IVirtualMemory;
import com.pnfsoftware.jeb.core.units.code.asm.memory.MemoryException;
import com.pnfsoftware.jeb.core.units.code.asm.type.INativeType;
import com.pnfsoftware.jeb.core.units.code.asm.type.IStructureType;
import com.pnfsoftware.jeb.core.units.code.asm.type.ITypeManager;

public class coi {
   public static final String q = "_RTTICompleteObjectLocator";
   private long RF;
   private int xK;
   private int Dw;
   private int Uv;

   public static coi q(long var0, cok var2) throws MemoryException {
      abg var3 = var2.q;
      IVirtualMemory var4 = var3.getMemory();
      coi var5 = new coi();
      var5.RF = var0;
      var5.xK = var4.readInt(var0);
      long var6 = var0 + 4L;
      if ((var2.q() || var5.xK == 0) && (!var2.q() || var5.xK == 1)) {
         var6 += 4L;
         var6 += 4L;
         var5.Dw = var4.readInt(var6);
         var6 += 4L;
         var5.Uv = var4.readInt(var6);
         return var5;
      } else {
         return null;
      }
   }

   public static INativeType q(ITypeManager var0, boolean var1) {
      IStructureType var2 = (IStructureType)var0.getType("_RTTICompleteObjectLocator");
      if (var2 == null) {
         var2 = var0.createStructure("_RTTICompleteObjectLocator");
         var0.addStructureField(var2, "signature", var0.getType("int"));
         var0.addStructureField(var2, "offset", var0.getType("int"));
         var0.addStructureField(var2, "cdOffset", var0.getType("int"));
         col.q(var0, var2, "pTypeDescriptor", var1);
         col.q(var0, var2, "pClassDescriptor", var1);
         if (var1) {
            col.q(var0, var2, "pSelf", var1);
         }
      }

      return var2;
   }

   public long q() {
      return this.RF;
   }

   public int RF() {
      return this.Dw;
   }

   public int xK() {
      return this.Uv;
   }
}
