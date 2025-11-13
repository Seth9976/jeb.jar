package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.units.code.asm.memory.IVirtualMemory;
import com.pnfsoftware.jeb.core.units.code.asm.memory.MemoryException;
import com.pnfsoftware.jeb.core.units.code.asm.type.IStructureType;
import com.pnfsoftware.jeb.core.units.code.asm.type.ITypeManager;
import java.util.ArrayList;
import java.util.List;

public class coe extends cnx {
   public static final String oW = "__vmi_class_type_info";
   public int gO;
   public int nf;
   public long gP;
   public List za = new ArrayList();

   public static coe RF(long var0, cnz var2) throws MemoryException {
      abg var3 = var2.RF;
      IVirtualMemory var4 = var3.getMemory();
      coe var5 = new coe();
      if (!q(var5, var0, var2)) {
         return null;
      } else {
         long var6 = var0 + cnx.q(var2);
         var5.gO = var4.readInt(var6);
         var6 += 4L;
         var5.nf = var4.readInt(var6);
         var6 += 4L;
         var5.gP = var6;

         for (int var8 = 0; var8 < var5.nf; var8++) {
            cnw var9 = cnw.q(var6, var2);
            if (var9 == null) {
               return null;
            }

            var5.za.add(var9);
            var6 += cnw.q(var2);
         }

         return var5;
      }
   }

   public static boolean RF(ITypeManager var0, boolean var1) {
      if (var0.getType("__vmi_class_type_info") == null) {
         IStructureType var2 = var0.createStructure("__vmi_class_type_info");
         q(var2, var0);
         var0.addStructureField(var2, "__flags", var0.getType("int"));
         var0.addStructureField(var2, "__base_count", var0.getType("int"));
         return true;
      } else {
         return false;
      }
   }

   @Override
   public cnz.eo Uv() {
      return cnz.eo.xK;
   }

   @Override
   public String oW() {
      return "__vmi_class_type_info";
   }
}
