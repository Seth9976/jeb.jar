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

class LI {
   private static final ILogger xK = GlobalLog.getLogger(LI.class);
   private static final boolean Dw = false;
   private static final int Uv = 200;
   private static final int oW = 8;
   private static final int gO = 100;
   private final long nf;
   private Map gP;
   private Map za;
   private List lm = new ArrayList();
   boolean q = false;
   int RF = 0;

   public LI(long var1, Map var3, Map var4) {
      this.nf = var1;
      this.za = var3;
      this.gP = var4;
      this.lm.add(var1);
   }

   public LI q() {
      LI var1 = new LI(this.nf, this.za, this.gP);
      var1.lm = new ArrayList(this.lm);
      var1.q = this.q;
      var1.RF = this.RF;
      return var1;
   }

   public boolean RF() {
      if (this.lm.size() > 200) {
         return false;
      } else if (this.q) {
         return this.RF < this.lm.size();
      } else {
         long var1 = (Long)this.lm.get(this.lm.size() - 1);
         List var3 = this.q(var1);
         return !var3.isEmpty() && !this.lm.contains(var3.get(0));
      }
   }

   public long xK() {
      if (this.q) {
         if (this.RF < this.lm.size()) {
            long var4 = (Long)this.lm.get(this.RF);
            this.RF++;
            return var4;
         } else {
            return -1L;
         }
      } else {
         long var1 = (Long)this.lm.get(this.lm.size() - 1);
         List var3 = this.q(var1);
         if (var3.isEmpty()) {
            return -1L;
         } else if (var3.size() == 1) {
            if (this.lm.contains(var3.get(0))) {
               return -1L;
            } else {
               this.lm.add((Long)var3.get(0));
               return (Long)var3.get(0);
            }
         } else {
            this.RF = this.lm.size();
            this.Dw();
            this.q = true;
            return this.xK();
         }
      }
   }

   private void Dw() {
      long var1 = this.Uv();
      ArrayList var3 = new ArrayList();
      var3.add(this.lm);
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
                  List var12 = this.q(var10);
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
                              var6 = this.q(var15, (List)var6);
                           }

                           if (this.xK((List)var6) && this.RF((List)var6) == 0) {
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

      this.lm = (List)var5;
   }

   private List q(long var1) {
      TreeSet var3 = new TreeSet();

      for (Entry var5 : this.gP.entrySet()) {
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

   private long Uv() {
      Long var1 = null;

      for (Long var3 : this.gP.keySet()) {
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

   private void q(List var1) {
   }

   private List q(List var1, List var2) {
      boolean var3 = this.xK(var2);
      boolean var4 = this.xK(var1);
      if (var3 != var4) {
         return var3 ? var2 : var1;
      } else {
         int var5 = this.RF(var2);
         int var6 = this.RF(var1);
         if (var5 < var6) {
            return var2;
         } else {
            return var6 < var5 ? var1 : var2;
         }
      }
   }

   private int RF(List var1) {
      int var2 = 0;

      for (int var3 = 0; var3 < var1.size() - 1; var3++) {
         if ((Long)var1.get(var3) < (Long)var1.get(var3 + 1)) {
            var2++;
         }
      }

      return var2;
   }

   private boolean xK(List var1) {
      for (int var2 = 0; var2 < var1.size() - 1; var2++) {
         if (this.q((Long)var1.get(var2 + 1), (Long)var1.get(var2))) {
            return true;
         }
      }

      return false;
   }

   private boolean q(long var1, long var3) {
      List var5 = (List)this.za.get(var1);
      fA var6 = (fA)var5.get(var5.size() - 1);
      long var7 = this.q(var5, var1);
      return var6.q().isPCUpdated() && var6.Uv().gO() && (OC.RF(var6.Uv().RF()) && var7 != var3 || OC.q(var6.Uv().RF()) && var7 == var3);
   }

   private long q(List var1, long var2) {
      long var4 = var2;

      for (fA var7 : var1) {
         var4 += var7.getSize();
      }

      return var4;
   }
}
