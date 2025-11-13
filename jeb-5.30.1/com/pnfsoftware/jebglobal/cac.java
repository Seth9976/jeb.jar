package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.units.IPriorityBasedHooks;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map.Entry;

public class cac {
   private int q = 1;
   private LinkedHashMap RF = new LinkedHashMap();
   private List xK = new ArrayList();

   public boolean q() {
      return !this.xK.isEmpty();
   }

   public Collection RF() {
      return Collections.unmodifiableCollection(this.xK);
   }

   public IPriorityBasedHooks q(int var1) {
      return (IPriorityBasedHooks)this.RF.get(var1);
   }

   public int q(IPriorityBasedHooks var1) {
      return this.q(var1, false);
   }

   public synchronized int q(IPriorityBasedHooks var1, boolean var2) {
      if (var1 == null) {
         throw new IllegalArgumentException();
      } else {
         for (Entry var4 : this.RF.entrySet()) {
            if (var4.getValue() == var1) {
               return (Integer)var4.getKey();
            }
         }

         int var7 = -1;
         int var5 = var1.getPriority();

         int var8;
         for (var8 = 0; var8 < this.xK.size(); var8++) {
            int var6 = ((IPriorityBasedHooks)this.xK.get(var8)).getPriority();
            if (var6 < var5) {
               break;
            }

            if (var6 == var5 && var7 == -1) {
               var7 = var8;
            }
         }

         if (var2 && var7 >= 0) {
            this.xK.add(var7, var1);
         } else {
            this.xK.add(var8, var1);
         }

         int var9 = this.q++;
         this.RF.put(var9, var1);
         return var9;
      }
   }

   public boolean RF(IPriorityBasedHooks var1) {
      if (var1 == null) {
         return false;
      } else {
         int var2 = 0;

         for (Entry var4 : this.RF.entrySet()) {
            if (var4.getValue() == var1) {
               var2 = (Integer)var4.getKey();
               break;
            }
         }

         return this.RF(var2);
      }
   }

   public synchronized boolean RF(int var1) {
      IPriorityBasedHooks var2 = (IPriorityBasedHooks)this.RF.remove(var1);
      if (var2 == null) {
         return false;
      } else {
         this.xK.remove(var2);
         return true;
      }
   }
}
