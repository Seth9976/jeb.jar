package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.units.code.asm.memory.IVirtualMemory;
import com.pnfsoftware.jeb.core.units.code.asm.memory.MemoryException;
import com.pnfsoftware.jeb.core.units.code.asm.type.INativeType;
import com.pnfsoftware.jeb.core.units.code.asm.type.IStructureType;
import com.pnfsoftware.jeb.core.units.code.asm.type.ITypeManager;

public class coh {
   public static final String q = "_RTTIClassHierarchyDescriptor";
   private long RF;
   private int xK;
   private int Dw;
   private int Uv;
   private long oW;

   public static coh q(long var0, cok var2) throws MemoryException {
      abg var3 = var2.q;
      IVirtualMemory var4 = var3.getMemory();
      coh var5 = new coh();
      var5.RF = var0;
      var5.xK = var4.readInt(var0);
      if (var5.xK != 0) {
         return null;
      } else {
         long var6 = var0 + 4L;
         var5.Dw = var4.readInt(var6);
         var6 += 4L;
         var5.Uv = var4.readInt(var6);
         if (var5.Uv == 0) {
            return null;
         } else {
            var6 += 4L;
            var5.oW = var2.q(var4.readInt(var6));
            return var5;
         }
      }
   }

   public boolean q() {
      return (this.Dw & 1L) != 0L;
   }

   public boolean RF() {
      return (this.Dw & 2L) != 0L;
   }

   public static INativeType q(ITypeManager var0, boolean var1) {
      IStructureType var2 = (IStructureType)var0.getType("_RTTIClassHierarchyDescriptor");
      if (var0.getType("_RTTIClassHierarchyDescriptor") == null) {
         var2 = var0.createStructure("_RTTIClassHierarchyDescriptor");
         INativeType var3 = var0.getType("int");
         var0.addStructureField(var2, "signature", var3);
         var0.addStructureField(var2, "attributes", var3);
         var0.addStructureField(var2, "numBaseClasses", var3);
         col.q(var0, var2, "pBaseClassArray", var1);
      }

      return var2;
   }

   public long xK() {
      return this.RF;
   }

   public int Dw() {
      return this.Uv;
   }

   public long Uv() {
      return this.oW;
   }
}
