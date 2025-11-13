package com.pnfsoftware.jeb.corei.parsers.elf;

import com.pnfsoftware.jeb.util.format.Strings;
import com.pnfsoftware.jeb.util.logging.GlobalLog;
import com.pnfsoftware.jeb.util.logging.ILogger;
import com.pnfsoftware.jeb.util.serialization.annotations.Ser;
import com.pnfsoftware.jeb.util.serialization.annotations.SerId;
import java.util.ArrayList;
import java.util.List;

@Ser
public class eW {
   private static final ILogger pC = GlobalLog.getLogger(eW.class);
   @SerId(1)
   private List A = new ArrayList();

   public boolean pC(long var1) {
      for (m var4 : this.A) {
         if (var4.wS == var1) {
            return true;
         }
      }

      return false;
   }

   public boolean pC(m var1, boolean var2) {
      if (this.A.contains(var1)) {
         return false;
      } else {
         if (var1.kS) {
            for (int var3 = 0; var3 < this.A.size(); var3++) {
               if (((m)this.A.get(var3)).kS) {
                  if (!var2) {
                     pC.debug("Cannot register symbol table! A dynsym table is already present, and replacement is forbidden!");
                     return false;
                  }

                  this.A.set(var3, var1);
                  return true;
               }
            }
         }

         this.A.add(var1);
         return true;
      }
   }

   public List pC() {
      return this.A;
   }

   public m pC(int var1) {
      for (m var3 : this.A) {
         if (var3.A == var1) {
            return var3;
         }
      }

      return null;
   }

   @Override
   public String toString() {
      return Strings.ff("%d tables:\n%s", this.A.size(), this.A);
   }
}
