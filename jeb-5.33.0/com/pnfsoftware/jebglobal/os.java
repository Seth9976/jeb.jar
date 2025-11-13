package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.util.logging.GlobalLog;
import com.pnfsoftware.jeb.util.logging.ILogger;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.TreeSet;
import java.util.Map.Entry;

class os {
   private static final ILogger kS = GlobalLog.getLogger(os.class);
   private final long wS;
   private Map UT;
   private Map E;
   private List sY = new ArrayList();
   boolean pC = false;
   int A = 0;

   public os(long var1, Map var3, Map var4) {
      this.wS = var1;
      this.E = var3;
      this.UT = var4;
      this.sY.add(var1);
   }

   public os pC() {
      os var1 = new os(this.wS, this.E, this.UT);
      var1.sY = new ArrayList(this.sY);
      var1.pC = this.pC;
      var1.A = this.A;
      return var1;
   }

   public boolean A() {
      if (this.sY.size() > 200) {
         return false;
      } else if (this.pC) {
         return this.A < this.sY.size();
      } else {
         long var1 = (Long)this.sY.get(this.sY.size() - 1);
         List var3 = this.pC(var1);
         return !var3.isEmpty() && !this.sY.contains(var3.get(0));
      }
   }

   public long kS() {
      if (this.pC) {
         if (this.A < this.sY.size()) {
            long var4 = (Long)this.sY.get(this.A);
            this.A++;
            return var4;
         } else {
            return -1L;
         }
      } else {
         long var1 = (Long)this.sY.get(this.sY.size() - 1);
         List var3 = this.pC(var1);
         if (var3.isEmpty()) {
            return -1L;
         } else if (var3.size() == 1) {
            if (this.sY.contains(var3.get(0))) {
               return -1L;
            } else {
               this.sY.add((Long)var3.get(0));
               return (Long)var3.get(0);
            }
         } else {
            this.A = this.sY.size();
            this.wS();
            this.pC = true;
            return this.kS();
         }
      }
   }

   private void wS() {
      long var1 = this.UT();
      ArrayList var3 = new ArrayList();
      var3.add(this.sY);
      boolean var4 = true;
      Object var5 = null;
      Object var6 = null;

      label59:
      while (var4) {
         var4 = false;
         ArrayList var7 = new ArrayList();
         Iterator var8 = var3.iterator();

         label57:
         while (true) {
            if (var8.hasNext()) {
               List var9 = (List)var8.next();
               if (var7.size() <= 100) {
                  long var10 = (Long)var9.get(var9.size() - 1);
                  List var12 = this.pC(var10);
                  if (var12.isEmpty()) {
                     var7.add(var9);
                     continue;
                  }

                  Iterator var13 = var12.iterator();

                  while (true) {
                     if (!var13.hasNext()) {
                        continue label57;
                     }

                     Long var14 = (Long)var13.next();
                     if (!var9.contains(var14)) {
                        ArrayList var15 = new ArrayList(var9);
                        var15.add(var14);
                        if (var14 == var1) {
                           if (var6 == null) {
                              var6 = var15;
                           } else {
                              var6 = this.pC(var15, (List)var6);
                           }

                           if (this.A((List)var6) && this.pC((List)var6) == 0) {
                              var5 = var6;
                              break label59;
                           }
                        }

                        var4 = true;
                        var7.add(var15);
                     }
                  }
               }
            }

            if (!var7.isEmpty()) {
               var3 = var7;
            }
            break;
         }
      }

      if (var5 == null) {
         if (var6 != null) {
            var5 = var6;
         } else {
            var5 = (List)var3.get(0);
         }
      }

      this.sY = (List)var5;
   }

   private List pC(long var1) {
      TreeSet var3 = new TreeSet();

      for (Entry var5 : this.UT.entrySet()) {
         if (((List)var5.getValue()).contains(var1)) {
            var3.add((Long)var5.getKey());
         }
      }

      if (var3.size() == 1) {
         return new ArrayList(var3);
      } else {
         ArrayList var6 = new ArrayList();
         var6.addAll(var3.headSet(var1));
         Collections.reverse(var6);
         var6.addAll(var3.tailSet(var1));

         while (var6.size() > 8) {
            var6.remove(var6.size() - 1);
         }

         return var6;
      }
   }

   private long UT() {
      Long var1 = null;

      for (Long var3 : this.UT.keySet()) {
         if (var3 != null) {
            if (var1 == null) {
               var1 = var3;
            } else if (var3 < var1) {
               var1 = var3;
            }
         }
      }

      return var1;
   }

   private List pC(List var1, List var2) {
      boolean var3 = this.A(var2);
      boolean var4 = this.A(var1);
      if (var3 != var4) {
         return var3 ? var2 : var1;
      } else {
         int var5 = this.pC(var2);
         int var6 = this.pC(var1);
         if (var5 < var6) {
            return var2;
         } else {
            return var6 < var5 ? var1 : var2;
         }
      }
   }

   private int pC(List var1) {
      int var2 = 0;

      for (int var3 = 0; var3 < var1.size() - 1; var3++) {
         if ((Long)var1.get(var3) < (Long)var1.get(var3 + 1)) {
            var2++;
         }
      }

      return var2;
   }

   private boolean A(List var1) {
      for (int var2 = 0; var2 < var1.size() - 1; var2++) {
         if (this.pC((Long)var1.get(var2 + 1), (Long)var1.get(var2))) {
            return true;
         }
      }

      return false;
   }

   private boolean pC(long var1, long var3) {
      List var5 = (List)this.E.get(var1);
      com.pnfsoftware.jeb.corei.parsers.arm.rQ var6 = (com.pnfsoftware.jeb.corei.parsers.arm.rQ)var5.get(var5.size() - 1);
      long var7 = this.pC(var5, var1);
      return var6.pC().isPCUpdated() && var6.UT().sY() && (PU.A(var6.UT().A()) && var7 != var3 || PU.pC(var6.UT().A()) && var7 == var3);
   }

   private long pC(List var1, long var2) {
      long var4 = var2;

      for (com.pnfsoftware.jeb.corei.parsers.arm.rQ var7 : var1) {
         var4 += var7.getSize();
      }

      return var4;
   }
}
