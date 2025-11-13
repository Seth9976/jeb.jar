package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.units.code.android.controlflow.BasicBlock;
import com.pnfsoftware.jeb.core.units.code.android.controlflow.CFG;
import com.pnfsoftware.jeb.util.collect.Sets;
import java.util.ArrayDeque;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class bru {
   private CFG pC;
   private long A;
   private Set kS;
   private boolean wS;
   private boolean UT;
   private Set E;
   private Set sY;
   private LinkedHashSet ys = new LinkedHashSet();
   private Map ld = new HashMap();

   public bru(CFG var1, long var2, Collection var4) {
      if (var1 != null && var4 != null) {
         this.pC = var1;
         this.A = var2;
         this.kS = new HashSet(var4);
      } else {
         throw new IllegalArgumentException();
      }
   }

   public bru(CFG var1, long var2, long var4) {
      if (var1 == null) {
         throw new IllegalArgumentException();
      } else {
         this.pC = var1;
         this.A = var2;
         this.kS = Sets.newHashSet(var4);
      }
   }

   public void pC(boolean var1) {
      this.wS = var1;
   }

   public void A(boolean var1) {
      this.UT = var1;
   }

   public void pC(Set var1) {
      this.E = var1;
   }

   public void A(Set var1) {
      this.sY = var1;
   }

   protected bru.Av pC(BasicBlock var1) {
      return null;
   }

   protected List A(BasicBlock var1) {
      return var1.getInputs();
   }

   protected List kS(BasicBlock var1) {
      return var1.getIrregularInputs();
   }

   protected List wS(BasicBlock var1) {
      return var1.getOutputs();
   }

   protected List UT(BasicBlock var1) {
      return var1.getIrregularOutputs();
   }

   public Set pC() {
      return this.ys;
   }

   public Set A() {
      return this.kS;
   }

   public boolean kS() {
      if (this.kS.contains(this.A)) {
         return false;
      } else {
         ArrayDeque var1 = new ArrayDeque();
         var1.add(this.A);
         if (this.sY != null) {
            if (!this.UT) {
               for (long var3 : this.sY) {
                  if (this.kS.contains(var3)) {
                     return false;
                  }
               }
            }

            var1.addAll(this.sY);
         }

         LinkedHashSet var15 = new LinkedHashSet();
         int var16 = 0;

         while (true) {
            HashSet var4 = new HashSet();

            while (!var1.isEmpty()) {
               long var5 = (Long)var1.remove();
               if (var15.add(var5)) {
                  boolean var7 = false;
                  List var8 = null;
                  List var9 = null;
                  if (this.kS.contains(var5)) {
                     var7 = true;
                  }

                  BasicBlock var10 = this.pC.getBlockAt(var5);
                  bru.Av var11 = (bru.Av)this.ld.get(var5);
                  if (var11 == null) {
                     var11 = this.pC(var10);
                     this.ld.put(var5, var11);
                  }

                  if (var11 != null) {
                     if (var11.pC == 0) {
                        var8 = var11.A;
                        var9 = var11.kS;
                     } else {
                        if (var11.pC != 1) {
                           throw new RuntimeException();
                        }

                        var7 = true;
                        this.kS.add(var5);
                        var8 = var11.A;
                        var9 = null;
                     }
                  }

                  if (!var7 || this.UT) {
                     if (var5 == 0L && this.A != 0L) {
                        return false;
                     }

                     this.ys.add(var5);
                     if (var5 == this.A) {
                        if (var16 > 0 && !this.wS) {
                           return false;
                        }
                     } else {
                        for (BasicBlock var14 : var8 == null ? this.A(var10) : var8) {
                           var4.add(var14.getAddress());
                        }

                        for (BasicBlock var22 : this.kS(var10)) {
                           var4.add(var22.getAddress());
                        }
                     }

                     if (!var7) {
                        for (BasicBlock var23 : var9 == null ? this.wS(var10) : var9) {
                           var1.add(var23.getAddress());
                        }

                        if (this.E != null) {
                           for (BasicBlock var24 : this.UT(var10)) {
                              if (this.E.contains(var24.getAddress())) {
                                 var1.add(var24.getAddress());
                              }
                           }
                        }
                     }

                     var16++;
                  }
               }
            }

            var4.removeAll(var15);
            if (var4.isEmpty()) {
               return true;
            }

            Set var17 = this.kS(var4);
            if (var17 == null) {
               return false;
            }

            var1.addAll(var17);
         }
      }
   }

   private Set kS(Set var1) {
      ArrayDeque var2 = new ArrayDeque(var1);
      LinkedHashSet var3 = new LinkedHashSet();

      while (!var2.isEmpty()) {
         long var4 = (Long)var2.remove();
         if (!this.ys.contains(var4)) {
            if (this.kS.contains(var4)) {
               return null;
            }

            if (var3.add(var4)) {
               BasicBlock var6 = this.pC.getBlockAt(var4);
               int var7 = var2.size();

               for (BasicBlock var9 : this.A(var6)) {
                  var2.add(var9.getBase());
               }

               for (BasicBlock var11 : this.kS(var6)) {
                  var2.add(var11.getBase());
               }

               if (var2.size() == var7) {
                  return null;
               }
            }
         }
      }

      return var3;
   }

   public static class Av {
      int pC;
      public List A;
      public List kS;

      private Av(int var1) {
         this.pC = var1;
      }

      public static bru.Av pC() {
         return new bru.Av(0);
      }

      public static bru.Av A() {
         return new bru.Av(1);
      }
   }
}
