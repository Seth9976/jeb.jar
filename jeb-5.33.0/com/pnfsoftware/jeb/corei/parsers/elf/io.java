package com.pnfsoftware.jeb.corei.parsers.elf;

import com.pnfsoftware.jeb.core.units.code.asm.memory.IVirtualMemory;
import com.pnfsoftware.jeb.core.units.codeobject.ELFPluginsService;
import com.pnfsoftware.jeb.core.units.codeobject.ELFRelocationContext;
import com.pnfsoftware.jeb.core.units.codeobject.IELFRelocationApplicator;
import com.pnfsoftware.jeb.core.units.codeobject.IELFUnit;
import com.pnfsoftware.jeb.util.logging.GlobalLog;
import com.pnfsoftware.jeb.util.logging.ILogger;
import java.util.Collection;
import java.util.HashSet;

public class io {
   protected static final ILogger pC = GlobalLog.getLogger(io.class);
   IELFUnit A;
   IVirtualMemory kS;
   long wS;

   public io(IELFUnit var1, IVirtualMemory var2, long var3) {
      this.A = var1;
      this.kS = var2;
      this.wS = var3;
   }

   public Pj pC() {
      HashSet var1 = new HashSet();

      for (ELFRelocationContext var3 : ELFPluginsService.getInstance().getRelocationContexts()) {
         if (var3.canApply(this.A, this.wS)) {
            var1.addAll(var3.getApplicableRelocations());
         }
      }

      return this.pC(var1);
   }

   private Pj pC(Collection var1) {
      if (var1 != null && !var1.isEmpty()) {
         IELFRelocationApplicator.RelocatedFile var2 = new IELFRelocationApplicator.RelocatedFile(this.A, this.kS, this.wS);
         return new Pj(var2, var1);
      } else {
         return null;
      }
   }
}
