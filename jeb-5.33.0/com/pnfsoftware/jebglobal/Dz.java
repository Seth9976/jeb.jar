package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.units.code.CodePointer;
import com.pnfsoftware.jeb.core.units.code.asm.analyzer.INativeCodeAnalyzerExtension;
import com.pnfsoftware.jeb.core.units.code.asm.analyzer.MemoryRanges;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Dz extends kx {
   INativeCodeAnalyzerExtension ys;

   public Dz(a var1) {
      super(var1);
      this.ys = var1.UT();
      this.pC = "code-prologues";
      MemoryRanges var2 = var1.pC();
      if (var2 != null && var2.count() != 0) {
         this.E = var2.asList();
      }
   }

   protected CodePointer kS(long var1, long var3, boolean var5) {
      return (CodePointer)this.ys.getPrologueLooking(var1, var3).getResult();
   }

   @Override
   protected List A(long var1, long var3, boolean var5) {
      CodePointer var6 = this.kS(var1, var3, var5);
      if (var6 != null) {
         ArrayList var7 = new ArrayList();
         var7.add(var6);
         return var7;
      } else {
         return Collections.emptyList();
      }
   }
}
