package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.util.format.Strings;
import com.pnfsoftware.jeb.util.logging.GlobalLog;
import com.pnfsoftware.jeb.util.logging.ILogger;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.IdentityHashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;

public class cje implements Iterator {
   private static final ILogger q = GlobalLog.getLogger(cje.class);
   private List RF = new LinkedList();
   private Map xK = new IdentityHashMap();

   public cje(Collection var1) {
      for (cjf var3 : var1) {
         int var4 = var3.RF().size();
         List var5 = var3.xK();
         cje.eo var6 = new cje.eo(var3, var4, var5);
         this.RF.add(var6);
         this.xK.put(var3, var6);
      }

      Collections.sort(this.RF);
   }

   @Override
   public boolean hasNext() {
      return !this.RF.isEmpty();
   }

   public cjf q() {
      if (this.RF.isEmpty()) {
         throw new NoSuchElementException();
      } else {
         cje.eo var1 = (cje.eo)this.RF.remove(0);

         for (cjf var3 : var1.xK) {
            cje.eo var4 = (cje.eo)this.xK.get(var3);
            var4.RF--;
         }

         Collections.sort(this.RF);
         return var1.q;
      }
   }

   @Override
   public void remove() {
      throw new RuntimeException("Do not use remove() on this object");
   }

   public List RF() {
      ArrayList var1 = new ArrayList();

      while (this.hasNext()) {
         var1.add(this.q());
      }

      return var1;
   }

   public class eo implements Comparable {
      cjf q;
      int RF;
      List xK;

      eo(cjf var2, int var3, List var4) {
         this.q = var2;
         this.RF = var3;
         this.xK = var4;
      }

      public int q(cje.eo var1) {
         int var2 = this.RF - var1.RF;
         return var2 != 0 ? var2 : -(this.xK.size() - var1.xK.size());
      }

      @Override
      public String toString() {
         StringBuilder var1 = new StringBuilder();
         Strings.ff(var1, "%s:%d,%d{", this.q, this.RF, this.xK.size());

         for (cjf var3 : this.xK) {
            Strings.ff(var1, "%s,", var3);
         }

         var1.append("}");
         return var1.toString();
      }
   }
}
