package com.pnfsoftware.jeb.core.units.code.android.dex;

import com.pnfsoftware.jeb.core.units.code.android.IDexUnit;
import java.util.Collection;

public class DexDebugVariableBundle {
   private Collection vars;

   public DexDebugVariableBundle(Collection var1) {
      this.vars = var1;
   }

   public static DexDebugVariableBundle from(Collection var0) {
      return new DexDebugVariableBundle(var0);
   }

   public boolean hasVariable(int var1) {
      return this.findVariable(var1) != null;
   }

   public IDexDebugVariable findVariable(int var1) {
      for (IDexDebugVariable var3 : this.vars) {
         if (var3.getRegister() == var1) {
            return var3;
         }
      }

      return null;
   }

   public String format(IDexUnit var1) {
      StringBuilder var2 = new StringBuilder();
      int var3 = 0;

      for (IDexDebugVariable var5 : this.vars) {
         if (var3 >= 1) {
            var2.append(", ");
         }

         var2.append(var5.format(var1));
         var3++;
      }

      return var2.toString();
   }
}
