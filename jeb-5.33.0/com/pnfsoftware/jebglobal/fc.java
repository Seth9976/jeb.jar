package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.units.code.asm.items.INativeMethodDataItem;
import com.pnfsoftware.jeb.util.format.Strings;
import com.pnfsoftware.jeb.util.logging.GlobalLog;
import com.pnfsoftware.jeb.util.logging.ILogger;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;

public class fc implements Iterator {
   private static final ILogger pC = GlobalLog.getLogger(fc.class);
   private Tw A;
   private List kS = new ArrayList();
   private Map wS = new HashMap();

   public fc(Tw var1, Collection var2) {
      this.A = var1;
      bj var3 = (bj)var1.getCallGraphManager().getGlobalCallGraph();

      for (INativeMethodDataItem var5 : var2) {
         int var6 = var3.pC(var5, false).size();
         List var7 = var3.A(var5, false);
         fc.Av var8 = new fc.Av(var5, var6, var7);
         this.kS.add(var8);
         this.wS.put(var5.getMemoryAddress(), var8);
      }

      Collections.sort(this.kS);
   }

   @Override
   public boolean hasNext() {
      return !this.kS.isEmpty();
   }

   public INativeMethodDataItem pC() {
      if (this.kS.isEmpty()) {
         throw new NoSuchElementException();
      } else {
         fc.Av var1 = (fc.Av)this.kS.remove(0);

         for (long var3 : var1.wS) {
            for (long var6 : this.A.getContainedRoutineAddresses(var3)) {
               fc.Av var8 = (fc.Av)this.wS.get(var6);
               if (var8 != null) {
                  var8.kS--;
               }
            }
         }

         Collections.sort(this.kS);
         return var1.pC;
      }
   }

   @Override
   public void remove() {
      throw new RuntimeException();
   }

   static class Av implements Comparable {
      INativeMethodDataItem pC;
      long A;
      int kS;
      List wS;

      Av(INativeMethodDataItem var1, int var2, List var3) {
         this.pC = var1;
         this.A = var1.getMemoryAddress();
         this.kS = var2;
         this.wS = var3;
      }

      public int pC(fc.Av var1) {
         int var2 = this.kS - var1.kS;
         return var2 != 0 ? var2 : -(this.wS.size() - var1.wS.size());
      }

      @Override
      public String toString() {
         StringBuilder var1 = new StringBuilder();
         Strings.ff(var1, "%Xh:%d,%d{", this.A, this.kS, this.wS.size());

         for (long var3 : this.wS) {
            var1.append(Long.toHexString(var3).toUpperCase()).append("h,");
         }

         var1.append("}");
         return var1.toString();
      }
   }
}
