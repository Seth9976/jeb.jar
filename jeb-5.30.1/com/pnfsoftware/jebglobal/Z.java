package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.units.codeobject.IELFSymbolEntry;
import com.pnfsoftware.jeb.core.units.codeobject.IELFSymbolProcessor;
import com.pnfsoftware.jeb.core.units.codeobject.ISymbolInformation;
import com.pnfsoftware.jeb.core.units.codeobject.SymbolInformation;
import com.pnfsoftware.jeb.core.units.codeobject.SymbolType;
import java.util.ArrayList;
import java.util.List;

public class Z implements IELFSymbolProcessor {
   private final long RF;
   private final long xK;
   private final boolean Dw;
   List q = new ArrayList();

   public Z(long var1, long var3, boolean var5) {
      this.RF = var1;
      this.xK = var3;
      this.Dw = var5;
   }

   @Override
   public ISymbolInformation processSymbol(IELFSymbolEntry var1) {
      if (var1.getType() == 0 && var1.getBinding() == 0) {
         String var2 = var1.getName();
         if (var2.startsWith("$")) {
            long var5 = var1.getValue();
            if (var5 != 0L && var5 >= this.RF && var5 < this.xK) {
               long var3 = var5 - this.RF;
               SymbolInformation var7 = null;
               if (!this.Dw) {
                  if (var2.equals("$a") || var2.startsWith("$a.")) {
                     var7 = new SymbolInformation(SymbolType.CODE, 16, 0L, this.q(var2), 0L, var3, 0L);
                  } else if (var2.equals("$t") || var2.startsWith("$t.")) {
                     var7 = new SymbolInformation(SymbolType.CODE, 16, 0L, this.q(var2), 0L, var3 + 1L, 0L);
                  }
               } else if (var2.equals("$x") || var2.startsWith("$x.")) {
                  var7 = new SymbolInformation(SymbolType.CODE, 16, 0L, this.q(var2), 0L, var3, 0L);
               }

               if (var2.equals("$d") || var2.startsWith("$d.")) {
                  var7 = new SymbolInformation(SymbolType.SECTION, 16, 0L, this.q(var2), 0L, var3, 0L);
               }

               if (var7 != null) {
                  this.q.add(var7);
               }

               return var7;
            }

            return null;
         }
      }

      return null;
   }

   private String q(String var1) {
      return var1.length() == 2 ? var1 : var1.substring(3);
   }

   @Override
   public boolean canImmediatelyUseSymbol() {
      return false;
   }

   @Override
   public Iterable getSymbols(Iterable var1) {
      ArrayList var2 = new ArrayList();

      for (ISymbolInformation var4 : this.q) {
         boolean var5 = false;

         for (ISymbolInformation var7 : var1) {
            if (var7.getSymbolRelativeAddress() == var4.getSymbolRelativeAddress() && !var4.getName().startsWith("$d")) {
               var5 = true;
               var2.add(new SymbolInformation(SymbolType.NOTYPE, 16, 0L, var4.getName(), 0L, var4.getSymbolRelativeAddress(), 0L));
               break;
            }
         }

         if (!var5) {
            var2.add(var4);
         }
      }

      return var2;
   }
}
