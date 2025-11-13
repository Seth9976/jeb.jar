package com.pnfsoftware.jeb.corei.parsers.elf;

import com.pnfsoftware.jeb.core.JebCoreService;
import com.pnfsoftware.jeb.core.units.code.asm.memory.MemoryException;
import com.pnfsoftware.jeb.core.units.codeobject.IELFRelocationApplicator;
import com.pnfsoftware.jeb.core.units.codeobject.ILinkInfoProvider;
import com.pnfsoftware.jeb.util.format.Strings;
import com.pnfsoftware.jeb.util.logging.GlobalLog;
import com.pnfsoftware.jeb.util.logging.ILogger;
import java.util.Collection;
import java.util.Map;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class Pj {
   protected static final ILogger pC = GlobalLog.getLogger(Pj.class);
   private IELFRelocationApplicator.RelocatedFile A;
   private sy kS;
   private Map wS;
   private ILinkInfoProvider UT;
   private int E;
   private boolean sY;

   Pj(IELFRelocationApplicator.RelocatedFile var1, Collection var2) {
      this.A = var1;
      this.kS = (sy)this.A.elfUnit;
      this.wS = (Map)var2.stream().collect(Collectors.toMap(IELFRelocationApplicator::getType, Function.identity()));
   }

   public void pC(ILinkInfoProvider var1) {
      this.UT = var1;
   }

   public boolean pC() {
      return this.pC(null);
   }

   public boolean pC(Predicate var1) {
      if (this.kS.wS() != null && this.kS.wS().pC() != null) {
         boolean var2 = true;

         for (ma var4 : this.kS.wS().pC()) {
            if ((var1 == null || var1.test(var4)) && !this.pC(var4)) {
               var2 = false;
            }
         }

         return var2;
      } else {
         return false;
      }
   }

   public boolean pC(ma var1) {
      m var2 = this.kS.kS().pC(var1.getSymbolSectionIndex());
      if (var1.getSymbolSectionIndex() != 0 && var2 == null) {
         this.pC("reloc: missing symbol table");
         return false;
      } else {
         if (var1.getSymbolSectionIndex() == 0) {
            this.kS.WR = true;
         }

         for (Tb var4 : var1.getEntries()) {
            nA var5 = null;
            if (var2 != null) {
               var5 = this.pC(var4, var2);
            }

            try {
               this.pC(var1, var4, var5);
            } catch (Exception var8) {
               String var7 = "relocation: " + var8.getMessage();
               this.pC(var7);
               this.E++;
            }
         }

         return this.E == 0;
      }
   }

   private nA pC(Tb var1, m var2) {
      if (var1.getSymbolIndex() > 0 && var1.getSymbolIndex() < var2.getCountOfEntries()) {
         return var2.pC(var1.getSymbolIndex());
      } else {
         if (var1.getSymbolIndex() != 0) {
            this.pC(Strings.ff("Invalid or unsupported symbol entry index: %Xh", var1.getSymbolIndex()));
         }

         return null;
      }
   }

   private void pC(ma var1, Tb var2, nA var3) throws MemoryException {
      IELFRelocationApplicator var4 = (IELFRelocationApplicator)this.wS.get(var2.getType());
      if (var4 != null) {
         IELFRelocationApplicator.RelocInstance var5 = new IELFRelocationApplicator.RelocInstance(var1, var2, var3);
         if (var4.canApply(var5)) {
            var4.apply(this.A, var5);
         }

         if (this.UT != null && var3 != null && var3.fI && var3.ld != null && var2.pC != 0L && (var3.getType() == 1 || var3.getType() == 2)) {
            long var6 = this.UT.resolveImportedSymbol(var3.ld, 0, this.A.mem, this.A.elfUnit);
            if (var6 != 0L) {
               long var8 = this.A.actualImageBase + var2.pC;
               this.A.mem.writePointer(var8, var6);
            }
         }
      }
   }

   public int A() {
      return this.E;
   }

   protected void pC(String var1) {
      if (!this.sY) {
         this.sY = true;
         var1 = var1 + Strings.ff(" (eMachine=0x%X)", this.kS.pC.sY);
         JebCoreService.notifySilentExceptionToClient(new yt(var1), this.kS);
      }
   }
}
