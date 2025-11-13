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

public class KZ {
   protected static final ILogger q = GlobalLog.getLogger(KZ.class);
   IELFUnit RF;
   IVirtualMemory xK;
   long Dw;

   public KZ(IELFUnit var1, IVirtualMemory var2, long var3) {
      this.RF = var1;
      this.xK = var2;
      this.Dw = var3;
   }

   public zJ q() {
      HashSet var1 = new HashSet();

      for (ELFRelocationContext var3 : ELFPluginsService.getInstance().getRelocationContexts()) {
         if (var3.canApply(this.RF, this.Dw)) {
            var1.addAll(var3.getApplicableRelocations());
         }
      }

      return this.q(var1);
   }

   private zJ q(Collection var1) {
      if (var1 != null && !var1.isEmpty()) {
         IELFRelocationApplicator.RelocatedFile var2 = new IELFRelocationApplicator.RelocatedFile(this.RF, this.xK, this.Dw);
         return new zJ(var2, var1);
      } else {
         return null;
      }
   }
}
