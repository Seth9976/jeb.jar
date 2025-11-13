package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.units.INativeCodeUnit;
import com.pnfsoftware.jeb.core.units.code.asm.analyzer.ICompiler;
import com.pnfsoftware.jeb.core.units.codeobject.ICodeObjectUnit;
import com.pnfsoftware.jeb.core.units.codeobject.ProcessorType;
import com.pnfsoftware.jeb.util.collect.CollectionOrder;
import com.pnfsoftware.jeb.util.collect.MultiMap;

public class bK {
   private static MultiMap pC = new MultiMap(CollectionOrder.NATURAL);

   public static MultiMap pC(INativeCodeUnit var0) {
      MultiMap var1 = new MultiMap(CollectionOrder.NATURAL);
      ProcessorType var2 = var0.getProcessor().getType();
      ICodeObjectUnit var3 = var0.getCodeObjectContainer();
      String var4 = var3 != null ? var3.getFormatType() : "generic";
      ICompiler var5 = var0.getCodeAnalyzer().getDetectedCompiler();
      if (var5 == null) {
         var5 = ICompiler.COMP_UNKNOWN;
      }

      for (Integer var7 : pC.keySet()) {
         for (RP var9 : pC.get(var7)) {
            if ((var9.kS().contains(ICompiler.COMP_UNKNOWN) || var9.kS().contains(var5))
               && (var9.A().contains(ProcessorType.UNKNOWN) || var9.A().contains(var2))
               && (var9.pC().contains("generic") || var9.pC().contains(var4))
               && var9.pC(var0)) {
               var1.put(var7, var9);
            }
         }
      }

      return var1;
   }

   static {
      pC.put(0, new aad());
      pC.put(1, new aae());
      pC.put(1, new aaf());
      pC.put(1, new aak());
      pC.put(1, new aai());
      pC.put(1, new aal());
      pC.put(1, new aam());
      pC.put(1, new aan());
      pC.put(1, new aao());
      pC.put(1, new aap());
      pC.put(1, new aar());
      pC.put(1, new aas());
      pC.put(1, new aat());
      pC.put(1, new aav());
      pC.put(1, new aaw());
      pC.put(1, new aau());
      pC.put(1, new aaq());
      pC.put(1, new abc());
      pC.put(2, new aag());
      pC.put(2, new aah());
      pC.put(2, new aaj());
      pC.put(2, new aax());
      pC.put(2, new aay());
      pC.put(2, new aba());
      pC.put(2, new abb());
      pC.put(2, new aaz());
   }
}
