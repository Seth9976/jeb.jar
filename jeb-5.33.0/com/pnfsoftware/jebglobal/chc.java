package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.units.code.asm.memory.IVirtualMemory;
import com.pnfsoftware.jeb.core.units.code.asm.memory.MemoryException;
import com.pnfsoftware.jeb.core.units.code.asm.type.INativeType;
import com.pnfsoftware.jeb.core.units.code.asm.type.IStructureType;
import com.pnfsoftware.jeb.core.units.code.asm.type.ITypeManager;

public class chc {
   private long pC;
   private int A;
   private chj kS;
   private int wS;
   private chi UT;
   private int E;
   private int sY;

   public static chc pC(long var0, chg var2) throws MemoryException {
      C var3 = var2.pC;
      IVirtualMemory var4 = var3.getMemory();
      chc var5 = new chc();
      var5.pC = var0;
      var5.A = var4.readInt(var0);
      long var6 = var0 + 4L;
      var5.kS = chj.pC(var2.pC(var5.A), var2);
      if (var5.kS == null) {
         return null;
      } else {
         var5.wS = var4.readInt(var6);
         var6 += 4L;
         var5.UT = chi.pC(var6, var4);
         if (var5.UT == null) {
            return null;
         } else {
            var6 += 12L;
            var5.E = var4.readInt(var6);
            var6 += 4L;
            if ((var5.E & 64) != 0) {
               var5.sY = var4.readInt(var6);
            }

            return var5;
         }
      }
   }

   public boolean pC() {
      return this.UT.A != -1;
   }

   public boolean A() {
      return (this.E & 5) == 0;
   }

   public static INativeType pC(ITypeManager var0, boolean var1) {
      IStructureType var2 = (IStructureType)var0.getType("_RTTIBaseClassDescriptor");
      if (var2 == null) {
         var2 = var0.createStructure("_RTTIBaseClassDescriptor");
         INativeType var3 = var0.getType("int");
         chh.pC(var0, var2, "pTypeDescriptor", var1);
         var0.addStructureField(var2, "numContainedBases", var3);
         var0.addStructureField(var2, "where", var0.getType("PMD"));
         var0.addStructureField(var2, "attributes", var3);
         chh.pC(var0, var2, "pClassDescriptor", var1);
      }

      return var2;
   }

   public long kS() {
      return this.pC;
   }

   public int wS() {
      return this.A;
   }

   public chj UT() {
      return this.kS;
   }

   public int E() {
      return this.wS;
   }

   public int sY() {
      return this.sY;
   }
}
