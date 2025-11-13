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

public class aaq implements Iterator {
   private static final ILogger q = GlobalLog.getLogger(aaq.class);
   private aaf RF;
   private List xK = new ArrayList();
   private Map Dw = new HashMap();

   public aaq(aaf var1, Collection var2) {
      this.RF = var1;
      aau var3 = (aau)var1.getCallGraphManager().getGlobalCallGraph();

      for (INativeMethodDataItem var5 : var2) {
         int var6 = var3.q(var5, false).size();
         List var7 = var3.RF(var5, false);
         aaq.eo var8 = new aaq.eo(var5, var6, var7);
         this.xK.add(var8);
         this.Dw.put(var5.getMemoryAddress(), var8);
      }

      Collections.sort(this.xK);
   }

   @Override
   public boolean hasNext() {
      return !this.xK.isEmpty();
   }

   public INativeMethodDataItem q() {
      if (this.xK.isEmpty()) {
         throw new NoSuchElementException();
      } else {
         aaq.eo var1 = (aaq.eo)this.xK.remove(0);

         for (long var3 : var1.Dw) {
            for (long var6 : this.RF.getContainedRoutineAddresses(var3)) {
               aaq.eo var8 = (aaq.eo)this.Dw.get(var6);
               if (var8 != null) {
                  var8.xK--;
               }
            }
         }

         Collections.sort(this.xK);
         return var1.q;
      }
   }

   @Override
   public void remove() {
      throw new RuntimeException();
   }

   public List RF() {
      ArrayList var1 = new ArrayList();

      while (this.hasNext()) {
         var1.add(this.q());
      }

      return var1;
   }

   static class eo implements Comparable {
      INativeMethodDataItem q;
      long RF;
      int xK;
      List Dw;

      eo(INativeMethodDataItem var1, int var2, List var3) {
         this.q = var1;
         this.RF = var1.getMemoryAddress();
         this.xK = var2;
         this.Dw = var3;
      }

      public int q(aaq.eo var1) {
         int var2 = this.xK - var1.xK;
         return var2 != 0 ? var2 : -(this.Dw.size() - var1.Dw.size());
      }

      @Override
      public String toString() {
         StringBuilder var1 = new StringBuilder();
         Strings.ff(var1, "%Xh:%d,%d{", this.RF, this.xK, this.Dw.size());

         for (long var3 : this.Dw) {
            var1.append(Long.toHexString(var3).toUpperCase()).append("h,");
         }

         var1.append("}");
         return var1.toString();
      }
   }
}
