package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.units.code.CodePointer;
import com.pnfsoftware.jeb.core.units.code.asm.analyzer.INativeCodeAnalyzerExtension;
import com.pnfsoftware.jeb.core.units.code.asm.analyzer.MemoryRanges;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Rw extends PA {
   INativeCodeAnalyzerExtension nf;

   public Rw(aae var1) {
      super(var1);
      this.nf = var1.Uv();
      this.q = "code-prologues";
      MemoryRanges var2 = var1.q();
      if (var2 != null && var2.count() != 0) {
         this.oW = var2.asList();
      }
   }

   protected CodePointer xK(long var1, long var3, boolean var5) {
      return (CodePointer)this.nf.getPrologueLooking(var1, var3).getResult();
   }

   @Override
   protected List RF(long var1, long var3, boolean var5) {
      CodePointer var6 = this.xK(var1, var3, var5);
      if (var6 != null) {
         ArrayList var7 = new ArrayList();
         var7.add(var6);
         return var7;
      } else {
         return Collections.emptyList();
      }
   }
}
