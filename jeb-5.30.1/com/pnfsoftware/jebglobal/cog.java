package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.units.code.asm.memory.IVirtualMemory;
import com.pnfsoftware.jeb.core.units.code.asm.memory.MemoryException;
import com.pnfsoftware.jeb.core.units.code.asm.type.INativeType;
import com.pnfsoftware.jeb.core.units.code.asm.type.IStructureType;
import com.pnfsoftware.jeb.core.units.code.asm.type.ITypeManager;

public class cog {
   public static final String q = "_RTTIBaseClassDescriptor";
   private static final int RF = 1;
   private static final int xK = 2;
   private static final int Dw = 4;
   private static final int Uv = 8;
   private static final int oW = 16;
   private static final int gO = 32;
   private static final int nf = 64;
   private long gP;
   private int za;
   private conn lm;
   private int zz;
   private com JY;
   private int HF;
   private int LK;

   public static cog q(long var0, cok var2) throws MemoryException {
      abg var3 = var2.q;
      IVirtualMemory var4 = var3.getMemory();
      cog var5 = new cog();
      var5.gP = var0;
      var5.za = var4.readInt(var0);
      long var6 = var0 + 4L;
      var5.lm = conn.q(var2.q(var5.za), var2);
      if (var5.lm == null) {
         return null;
      } else {
         var5.zz = var4.readInt(var6);
         var6 += 4L;
         var5.JY = com.q(var6, var4);
         if (var5.JY == null) {
            return null;
         } else {
            var6 += 12L;
            var5.HF = var4.readInt(var6);
            var6 += 4L;
            if ((var5.HF & 64) != 0) {
               var5.LK = var4.readInt(var6);
            }

            return var5;
         }
      }
   }

   public boolean q() {
      return this.JY.Dw != -1;
   }

   public boolean RF() {
      return (this.HF & 5) == 0;
   }

   public static INativeType q(ITypeManager var0, boolean var1) {
      IStructureType var2 = (IStructureType)var0.getType("_RTTIBaseClassDescriptor");
      if (var2 == null) {
         var2 = var0.createStructure("_RTTIBaseClassDescriptor");
         INativeType var3 = var0.getType("int");
         col.q(var0, var2, "pTypeDescriptor", var1);
         var0.addStructureField(var2, "numContainedBases", var3);
         var0.addStructureField(var2, "where", var0.getType("PMD"));
         var0.addStructureField(var2, "attributes", var3);
         col.q(var0, var2, "pClassDescriptor", var1);
      }

      return var2;
   }

   public long xK() {
      return this.gP;
   }

   public int Dw() {
      return this.za;
   }

   public conn Uv() {
      return this.lm;
   }

   public int oW() {
      return this.zz;
   }

   public int gO() {
      return this.LK;
   }
}
