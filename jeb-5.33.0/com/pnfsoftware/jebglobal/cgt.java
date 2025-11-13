package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.units.code.asm.memory.IVirtualMemory;
import com.pnfsoftware.jeb.core.units.code.asm.memory.MemoryException;
import com.pnfsoftware.jeb.core.units.code.asm.memory.VirtualMemoryUtil;
import com.pnfsoftware.jeb.core.units.code.asm.type.IStructureType;
import com.pnfsoftware.jeb.core.units.code.asm.type.ITypeManager;

public class cgt {
   public long pC;
   public long A;
   public long kS;
   public String wS;

   public static cgt pC(long var0, cgv var2) throws MemoryException {
      cgt var3 = new cgt();
      return !pC(var3, var0, var2) ? null : var3;
   }

   protected static boolean pC(cgt var0, long var1, cgv var3) throws MemoryException {
      C var4 = var3.A;
      IVirtualMemory var5 = var4.getMemory();
      var0.pC = var1;
      var0.A = var3.pC() ? var5.readLong(var1) : var5.readInt(var1) & 4294967295L;
      long var6 = var1 + (var3.pC() ? 8L : 4L);
      var0.kS = var3.pC() ? var5.readLong(var6) : var5.readInt(var6) & 4294967295L;
      if (var0.kS == 0L) {
         return false;
      } else {
         var0.wS = VirtualMemoryUtil.readNullTerminatedStringSafe(var5, var0.kS, 2048);
         return var0.wS != null;
      }
   }

   public static int pC(cgv var0) {
      return var0.pC() ? 16 : 8;
   }

   public static boolean pC(ITypeManager var0, boolean var1) {
      if (var0.getType("__class_type_info") == null) {
         IStructureType var2 = var0.createStructure("__class_type_info");
         pC(var2, var0);
         return true;
      } else {
         return false;
      }
   }

   protected static void pC(IStructureType var0, ITypeManager var1) {
      var1.addStructureField(var0, "vfptr", var1.getVoidReference());
      var1.addStructureField(var0, "__type_name", var1.getVoidReference());
   }

   public String pC() {
      return this.wS;
   }

   public String A() {
      return this.wS.startsWith("*") ? "_Z" + this.wS.substring(1) : "_Z" + this.wS;
   }

   public String kS() {
      return this.wS.startsWith("*") ? "_ZTI" + this.wS.substring(1) : "_ZTI" + this.wS;
   }

   public String wS() {
      return this.wS.startsWith("*") ? "_ZTS" + this.wS.substring(1) : "_ZTS" + this.wS;
   }

   public String UT() {
      return "__class_type_info";
   }

   public long E() {
      return this.kS;
   }
}
