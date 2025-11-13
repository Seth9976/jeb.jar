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

public class bwe {
   private CFG q;
   private long RF;
   private Set xK;
   private boolean Dw;
   private boolean Uv;
   private Set oW;
   private Set gO;
   private LinkedHashSet nf = new LinkedHashSet();
   private Map gP = new HashMap();

   public bwe(CFG var1, long var2, Collection var4) {
      if (var1 != null && var4 != null) {
         this.q = var1;
         this.RF = var2;
         this.xK = new HashSet(var4);
      } else {
         throw new IllegalArgumentException();
      }
   }

   public bwe(CFG var1, long var2, long var4) {
      if (var1 == null) {
         throw new IllegalArgumentException();
      } else {
         this.q = var1;
         this.RF = var2;
         this.xK = Sets.newHashSet(var4);
      }
   }

   public void q(boolean var1) {
      this.Dw = var1;
   }

   public void RF(boolean var1) {
      this.Uv = var1;
   }

   public void q(Set var1) {
      this.oW = var1;
   }

   public void RF(Set var1) {
      this.gO = var1;
   }

   protected bwe.eo q(BasicBlock var1) {
      return null;
   }

   protected List RF(BasicBlock var1) {
      return var1.getInputs();
   }

   protected List xK(BasicBlock var1) {
      return var1.getIrregularInputs();
   }

   protected List Dw(BasicBlock var1) {
      return var1.getOutputs();
   }

   protected List Uv(BasicBlock var1) {
      return var1.getIrregularOutputs();
   }

   public Set q() {
      return this.nf;
   }

   public Set RF() {
      return this.xK;
   }

   public boolean xK() {
      if (this.xK.contains(this.RF)) {
         return false;
      } else {
         ArrayDeque var1 = new ArrayDeque();
         var1.add(this.RF);
         if (this.gO != null) {
            if (!this.Uv) {
               for (long var3 : this.gO) {
                  if (this.xK.contains(var3)) {
                     return false;
                  }
               }
            }

            var1.addAll(this.gO);
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
                  if (this.xK.contains(var5)) {
                     var7 = true;
                  }

                  BasicBlock var10 = this.q.getBlockAt(var5);
                  bwe.eo var11 = (bwe.eo)this.gP.get(var5);
                  if (var11 == null) {
                     var11 = this.q(var10);
                     this.gP.put(var5, var11);
                  }

                  if (var11 != null) {
                     if (var11.q == 0) {
                        var8 = var11.RF;
                        var9 = var11.xK;
                     } else {
                        if (var11.q != 1) {
                           throw new RuntimeException();
                        }

                        var7 = true;
                        this.xK.add(var5);
                        var8 = var11.RF;
                        var9 = null;
                     }
                  }

                  if (!var7 || this.Uv) {
                     if (var5 == 0L && this.RF != 0L) {
                        return false;
                     }

                     this.nf.add(var5);
                     if (var5 == this.RF) {
                        if (var16 > 0 && !this.Dw) {
                           return false;
                        }
                     } else {
                        for (BasicBlock var14 : var8 == null ? this.RF(var10) : var8) {
                           var4.add(var14.getAddress());
                        }

                        for (BasicBlock var22 : this.xK(var10)) {
                           var4.add(var22.getAddress());
                        }
                     }

                     if (!var7) {
                        for (BasicBlock var23 : var9 == null ? this.Dw(var10) : var9) {
                           var1.add(var23.getAddress());
                        }

                        if (this.oW != null) {
                           for (BasicBlock var24 : this.Uv(var10)) {
                              if (this.oW.contains(var24.getAddress())) {
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

            Set var17 = this.xK(var4);
            if (var17 == null) {
               return false;
            }

            var1.addAll(var17);
         }
      }
   }

   private Set xK(Set var1) {
      ArrayDeque var2 = new ArrayDeque(var1);
      LinkedHashSet var3 = new LinkedHashSet();

      while (!var2.isEmpty()) {
         long var4 = (Long)var2.remove();
         if (!this.nf.contains(var4)) {
            if (this.xK.contains(var4)) {
               return null;
            }

            if (var3.add(var4)) {
               BasicBlock var6 = this.q.getBlockAt(var4);
               int var7 = var2.size();

               for (BasicBlock var9 : this.RF(var6)) {
                  var2.add(var9.getBase());
               }

               for (BasicBlock var11 : this.xK(var6)) {
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

   public static class eo {
      int q;
      public List RF;
      public List xK;

      private eo(int var1) {
         this.q = var1;
      }

      public static bwe.eo q() {
         return new bwe.eo(0);
      }

      public static bwe.eo RF() {
         return new bwe.eo(1);
      }
   }
}
