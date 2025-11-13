package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.units.IPriorityBasedHooks;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map.Entry;

public class bvn {
   private int pC = 1;
   private LinkedHashMap A = new LinkedHashMap();
   private List kS = new ArrayList();

   public boolean pC() {
      return !this.kS.isEmpty();
   }

   public Collection A() {
      return Collections.unmodifiableCollection(this.kS);
   }

   public IPriorityBasedHooks pC(int var1) {
      return (IPriorityBasedHooks)this.A.get(var1);
   }

   public int pC(IPriorityBasedHooks var1) {
      return this.pC(var1, false);
   }

   public synchronized int pC(IPriorityBasedHooks var1, boolean var2) {
      if (var1 == null) {
         throw new IllegalArgumentException();
      } else {
         for (Entry var4 : this.A.entrySet()) {
            if (var4.getValue() == var1) {
               return (Integer)var4.getKey();
            }
         }

         int var7 = -1;
         int var5 = var1.getPriority();

         int var8;
         for (var8 = 0; var8 < this.kS.size(); var8++) {
            int var6 = ((IPriorityBasedHooks)this.kS.get(var8)).getPriority();
            if (var6 < var5) {
               break;
            }

            if (var6 == var5 && var7 == -1) {
               var7 = var8;
            }
         }

         if (var2 && var7 >= 0) {
            this.kS.add(var7, var1);
         } else {
            this.kS.add(var8, var1);
         }

         int var9 = this.pC++;
         this.A.put(var9, var1);
         return var9;
      }
   }

   public boolean A(IPriorityBasedHooks var1) {
      if (var1 == null) {
         return false;
      } else {
         int var2 = 0;

         for (Entry var4 : this.A.entrySet()) {
            if (var4.getValue() == var1) {
               var2 = (Integer)var4.getKey();
               break;
            }
         }

         return this.A(var2);
      }
   }

   public synchronized boolean A(int var1) {
      IPriorityBasedHooks var2 = (IPriorityBasedHooks)this.A.remove(var1);
      if (var2 == null) {
         return false;
      } else {
         this.kS.remove(var2);
         return true;
      }
   }
}
