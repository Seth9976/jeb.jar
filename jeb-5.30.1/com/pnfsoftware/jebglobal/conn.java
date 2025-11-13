package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.units.code.asm.memory.IVirtualMemory;
import com.pnfsoftware.jeb.core.units.code.asm.memory.MemoryException;
import com.pnfsoftware.jeb.core.units.code.asm.memory.VirtualMemoryUtil;
import com.pnfsoftware.jeb.core.units.code.asm.type.INativeType;
import com.pnfsoftware.jeb.core.units.code.asm.type.IStructureType;
import com.pnfsoftware.jeb.core.units.code.asm.type.ITypeManager;

public class conn {
   public static final String q = "TypeDescriptor";
   private long RF;
   private long xK;
   private String Dw;

   public static conn q(long var0, cok var2) throws MemoryException {
      IVirtualMemory var3 = var2.q.getMemory();
      return q(var3, var0, var2.q());
   }

   public static conn q(IVirtualMemory var0, long var1, boolean var3) throws MemoryException {
      conn var4 = new conn();
      if (!var3) {
         var4.RF = var1;
         long var5 = var1 + 4L;
         var5 += 4L;
         var4.xK = var5;
         var4.Dw = VirtualMemoryUtil.readNullTerminatedStringSafe(var0, var4.xK, 2048);
         if (var4.Dw == null) {
            return null;
         }
      } else {
         var4.RF = var1;
         long var8 = var1 + 8L;
         var8 += 8L;
         var4.xK = var8;
         var4.Dw = VirtualMemoryUtil.readNullTerminatedStringSafe(var0, var4.xK, 2048);
         if (var4.Dw == null) {
            return null;
         }
      }

      return var4;
   }

   public static INativeType q(ITypeManager var0, boolean var1) {
      IStructureType var2 = (IStructureType)var0.getType("TypeDescriptor");
      if (var2 == null) {
         var2 = var0.createStructure("TypeDescriptor");
         var0.addStructureField(var2, "pVFTable", var0.getVoidReference());
         var0.addStructureField(var2, "spare", var0.getVoidReference());
      }

      return var2;
   }

   public long q() {
      return this.RF;
   }

   public long RF() {
      return this.xK;
   }

   public String xK() {
      return this.Dw;
   }
}
