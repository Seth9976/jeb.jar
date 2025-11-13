package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.units.code.IInstruction;
import com.pnfsoftware.jeb.core.units.code.Pointer;
import com.pnfsoftware.jeb.core.units.code.asm.analyzer.INativeCodeAnalyzer;
import java.util.ArrayList;
import java.util.List;

public abstract class ade implements adf {
   private static final int RF = 2;
   protected final INativeCodeAnalyzer q;

   public ade(INativeCodeAnalyzer var1) {
      this.q = var1;
   }

   @Override
   public List RF(long var1) {
      long var3 = var1;
      ArrayList var5 = new ArrayList();

      while (true) {
         IInstruction var6 = this.q(var3);
         if (var6 == null) {
            if (var5.size() <= 2) {
               return null;
            } else {
               ArrayList var10 = new ArrayList();

               for (long var8 : var5) {
                  var10.add(new Pointer(var8));
               }

               return var10;
            }
         }

         var5.add(var3);
         var3 += var6.getSize();
      }
   }

   protected abstract IInstruction q(long var1);
}
