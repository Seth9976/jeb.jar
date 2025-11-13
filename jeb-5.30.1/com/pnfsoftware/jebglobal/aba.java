package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.units.INativeCodeUnit;
import com.pnfsoftware.jeb.core.units.code.asm.analyzer.ICompiler;
import com.pnfsoftware.jeb.core.units.codeobject.ICodeObjectUnit;
import com.pnfsoftware.jeb.core.units.codeobject.ProcessorType;
import com.pnfsoftware.jeb.util.collect.CollectionOrder;
import com.pnfsoftware.jeb.util.collect.MultiMap;

public class aba {
   private static MultiMap q = new MultiMap(CollectionOrder.NATURAL);

   public static MultiMap q(INativeCodeUnit var0) {
      MultiMap var1 = new MultiMap(CollectionOrder.NATURAL);
      ProcessorType var2 = var0.getProcessor().getType();
      ICodeObjectUnit var3 = var0.getCodeObjectContainer();
      String var4 = var3 != null ? var3.getFormatType() : "generic";
      ICompiler var5 = var0.getCodeAnalyzer().getDetectedCompiler();
      if (var5 == null) {
         var5 = ICompiler.COMP_UNKNOWN;
      }

      for (Integer var7 : q.keySet()) {
         for (aao var9 : q.get(var7)) {
            if ((var9.xK().contains(ICompiler.COMP_UNKNOWN) || var9.xK().contains(var5))
               && (var9.RF().contains(ProcessorType.UNKNOWN) || var9.RF().contains(var2))
               && (var9.q().contains("generic") || var9.q().contains(var4))
               && var9.q(var0)) {
               var1.put(var7, var9);
            }
         }
      }

      return var1;
   }

   static {
      q.put(0, new abv());
      q.put(1, new abw());
      q.put(1, new abx());
      q.put(1, new acc());
      q.put(1, new aca());
      q.put(1, new acd());
      q.put(1, new ace());
      q.put(1, new acf());
      q.put(1, new acg());
      q.put(1, new ach());
      q.put(1, new acj());
      q.put(1, new ack());
      q.put(1, new acl());
      q.put(1, new acn());
      q.put(1, new aco());
      q.put(1, new acm());
      q.put(1, new aci());
      q.put(1, new acu());
      q.put(2, new aby());
      q.put(2, new abz());
      q.put(2, new acb());
      q.put(2, new acp());
      q.put(2, new acq());
      q.put(2, new acs());
      q.put(2, new act());
      q.put(2, new acr());
   }
}
