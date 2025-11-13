package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.units.code.asm.memory.IVirtualMemory;
import com.pnfsoftware.jeb.core.units.code.asm.memory.MemoryException;
import com.pnfsoftware.jeb.core.units.code.asm.memory.VirtualMemoryUtil;
import com.pnfsoftware.jeb.core.units.code.asm.type.IStructureType;
import com.pnfsoftware.jeb.core.units.code.asm.type.ITypeManager;

public class cnx {
   public static final String q = "__class_type_info";
   public long RF;
   public long xK;
   public long Dw;
   public String Uv;

   public static cnx q(long var0, cnz var2) throws MemoryException {
      cnx var3 = new cnx();
      return !q(var3, var0, var2) ? null : var3;
   }

   protected static boolean q(cnx var0, long var1, cnz var3) throws MemoryException {
      abg var4 = var3.RF;
      IVirtualMemory var5 = var4.getMemory();
      var0.RF = var1;
      var0.xK = var3.q() ? var5.readLong(var1) : var5.readInt(var1) & 4294967295L;
      long var6 = var1 + (var3.q() ? 8L : 4L);
      var0.Dw = var3.q() ? var5.readLong(var6) : var5.readInt(var6) & 4294967295L;
      if (var0.Dw == 0L) {
         return false;
      } else {
         var0.Uv = VirtualMemoryUtil.readNullTerminatedStringSafe(var5, var0.Dw, 2048);
         return var0.Uv != null;
      }
   }

   public static int q(cnz var0) {
      return var0.q() ? 16 : 8;
   }

   public static boolean q(ITypeManager var0, boolean var1) {
      if (var0.getType("__class_type_info") == null) {
         IStructureType var2 = var0.createStructure("__class_type_info");
         q(var2, var0);
         return true;
      } else {
         return false;
      }
   }

   protected static void q(IStructureType var0, ITypeManager var1) {
      var1.addStructureField(var0, "vfptr", var1.getVoidReference());
      var1.addStructureField(var0, "__type_name", var1.getVoidReference());
   }

   public String q() {
      return this.Uv;
   }

   public String RF() {
      return this.Uv.startsWith("*") ? "_Z" + this.Uv.substring(1) : "_Z" + this.Uv;
   }

   public String xK() {
      return this.Uv.startsWith("*") ? "_ZTI" + this.Uv.substring(1) : "_ZTI" + this.Uv;
   }

   public String Dw() {
      return this.Uv.startsWith("*") ? "_ZTS" + this.Uv.substring(1) : "_ZTS" + this.Uv;
   }

   public cnz.eo Uv() {
      return cnz.eo.q;
   }

   public String oW() {
      return "__class_type_info";
   }

   public long gO() {
      return this.Dw;
   }
}
