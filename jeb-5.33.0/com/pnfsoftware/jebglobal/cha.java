package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.units.code.asm.memory.IVirtualMemory;
import com.pnfsoftware.jeb.core.units.code.asm.memory.MemoryException;
import com.pnfsoftware.jeb.core.units.code.asm.type.IStructureType;
import com.pnfsoftware.jeb.core.units.code.asm.type.ITypeManager;
import java.util.ArrayList;
import java.util.List;

public class cha extends cgt {
   public int UT;
   public int E;
   public long sY;
   public List ys = new ArrayList();

   public static cha A(long var0, cgv var2) throws MemoryException {
      C var3 = var2.A;
      IVirtualMemory var4 = var3.getMemory();
      cha var5 = new cha();
      if (!pC(var5, var0, var2)) {
         return null;
      } else {
         long var6 = var0 + cgt.pC(var2);
         var5.UT = var4.readInt(var6);
         var6 += 4L;
         var5.E = var4.readInt(var6);
         var6 += 4L;
         var5.sY = var6;

         for (int var8 = 0; var8 < var5.E; var8++) {
            cgs var9 = cgs.pC(var6, var2);
            if (var9 == null) {
               return null;
            }

            var5.ys.add(var9);
            var6 += cgs.pC(var2);
         }

         return var5;
      }
   }

   public static boolean A(ITypeManager var0, boolean var1) {
      if (var0.getType("__vmi_class_type_info") == null) {
         IStructureType var2 = var0.createStructure("__vmi_class_type_info");
         pC(var2, var0);
         var0.addStructureField(var2, "__flags", var0.getType("int"));
         var0.addStructureField(var2, "__base_count", var0.getType("int"));
         return true;
      } else {
         return false;
      }
   }

   @Override
   public String UT() {
      return "__vmi_class_type_info";
   }
}
