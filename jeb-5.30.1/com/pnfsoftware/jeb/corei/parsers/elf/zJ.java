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

public class zJ {
   protected static final ILogger q = GlobalLog.getLogger(zJ.class);
   private IELFRelocationApplicator.RelocatedFile RF;
   private vb xK;
   private Map Dw;
   private ILinkInfoProvider Uv;
   private int oW;
   private boolean gO;

   zJ(IELFRelocationApplicator.RelocatedFile var1, Collection var2) {
      this.RF = var1;
      this.xK = (vb)this.RF.elfUnit;
      this.Dw = (Map)var2.stream().collect(Collectors.toMap(IELFRelocationApplicator::getType, Function.identity()));
   }

   public void q(ILinkInfoProvider var1) {
      this.Uv = var1;
   }

   public ILinkInfoProvider q() {
      return this.Uv;
   }

   public boolean RF() {
      return this.q(null);
   }

   public boolean q(Predicate var1) {
      if (this.xK.Dw() != null && this.xK.Dw().q() != null) {
         boolean var2 = true;

         for (CI var4 : this.xK.Dw().q()) {
            if ((var1 == null || var1.test(var4)) && !this.q(var4)) {
               var2 = false;
            }
         }

         return var2;
      } else {
         return false;
      }
   }

   public boolean q(CI var1) {
      qa var2 = this.xK.xK().q(var1.getSymbolSectionIndex());
      if (var1.getSymbolSectionIndex() != 0 && var2 == null) {
         this.q("reloc: missing symbol table");
         return false;
      } else {
         if (var1.getSymbolSectionIndex() == 0) {
            this.xK.JY = true;
         }

         for (Xa var4 : var1.getEntries()) {
            LR var5 = null;
            if (var2 != null) {
               var5 = this.q(var4, var2);
            }

            try {
               this.q(var1, var4, var5);
            } catch (Exception var8) {
               String var7 = "relocation: " + var8.getMessage();
               this.q(var7);
               this.oW++;
            }
         }

         return this.oW == 0;
      }
   }

   private LR q(Xa var1, qa var2) {
      if (var1.getSymbolIndex() > 0 && var1.getSymbolIndex() < var2.getCountOfEntries()) {
         return var2.q(var1.getSymbolIndex());
      } else {
         if (var1.getSymbolIndex() != 0) {
            this.q(Strings.ff("Invalid or unsupported symbol entry index: %Xh", var1.getSymbolIndex()));
         }

         return null;
      }
   }

   private void q(CI var1, Xa var2, LR var3) throws MemoryException {
      IELFRelocationApplicator var4 = (IELFRelocationApplicator)this.Dw.get(var2.getType());
      if (var4 != null) {
         IELFRelocationApplicator.RelocInstance var5 = new IELFRelocationApplicator.RelocInstance(var1, var2, var3);
         if (var4.canApply(var5)) {
            var4.apply(this.RF, var5);
         }

         if (this.Uv != null && var3 != null && var3.zz && var3.gP != null && var2.q != 0L && (var3.getType() == 1 || var3.getType() == 2)) {
            long var6 = this.Uv.resolveImportedSymbol(var3.gP, 0, this.RF.mem, this.RF.elfUnit);
            if (var6 != 0L) {
               long var8 = this.RF.actualImageBase + var2.q;
               this.RF.mem.writePointer(var8, var6);
            }
         }
      }
   }

   public int xK() {
      return this.oW;
   }

   protected void q(String var1) {
      if (!this.gO) {
         this.gO = true;
         var1 = var1 + Strings.ff(" (eMachine=0x%X)", this.xK.q.gO);
         JebCoreService.notifySilentExceptionToClient(new vn(var1), this.xK);
      }
   }
}
