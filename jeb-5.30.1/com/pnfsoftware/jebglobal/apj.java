package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.units.code.asm.decompiler.IERoutineContext;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.IEStackManager;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.IEVar;
import com.pnfsoftware.jeb.util.base.Couple;
import com.pnfsoftware.jeb.util.format.Strings;
import com.pnfsoftware.jeb.util.logging.StructuredLogger;
import com.pnfsoftware.jeb.util.serialization.annotations.Ser;
import com.pnfsoftware.jeb.util.serialization.annotations.SerCustomInitPostGraph;
import com.pnfsoftware.jeb.util.serialization.annotations.SerId;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.TreeMap;
import java.util.TreeSet;
import java.util.Map.Entry;

@Ser
public class apj {
   private static final StructuredLogger q = aeg.q(apj.class);
   @SerId(1)
   private IERoutineContext RF;
   @SerId(2)
   private int xK;
   @SerId(3)
   private int Dw;
   @SerId(4)
   private Set Uv = new TreeSet();
   @SerId(5)
   private TreeMap oW = new TreeMap();
   @SerId(6)
   private TreeMap gO = new TreeMap();
   @SerId(7)
   private TreeMap nf = new TreeMap();

   @SerCustomInitPostGraph
   private void Uv() {
      if (this.nf.isEmpty()) {
         for (Entry var2 : this.gO.entrySet()) {
            this.nf.put((Long)var2.getKey(), new Couple((TreeSet)var2.getValue(), null));
         }
      }
   }

   public apj(IERoutineContext var1) {
      this.RF = var1;
      this.xK = var1.getGlobalContext().getAddressBitsize();
      if (this.xK % 8 != 0) {
         throw new IllegalArgumentException("The normal slot bitsize must be a multiple of 8");
      } else {
         this.Dw = this.xK / 8;
      }
   }

   public IERoutineContext q() {
      return this.RF;
   }

   public boolean q(long var1, int var3) {
      return this.q(var1, var3, null);
   }

   public boolean q(long var1, int var3, Boolean var4) {
      if (var3 < 0 || var3 % 8 != 0) {
         throw new IllegalArgumentException(Strings.ff("Cannot record access for: offset=%Xh bitsize=%d", var1, var3));
      } else if (var3 == 0) {
         this.Uv.add(var1);
         return false;
      } else {
         this.RF(var1, var3, var4);
         return true;
      }
   }

   private void RF(long var1, int var3, Boolean var4) {
      Couple var5 = (Couple)this.nf.get(var1);
      if (var5 == null) {
         var5 = new Couple(null, null);
         this.nf.put(var1, var5);
      }

      TreeSet var6;
      if (var4 != null && !var4) {
         var6 = (TreeSet)var5.getFirst();
         if (var6 == null) {
            var6 = new TreeSet();
            var5.setFirst(var6);
         }
      } else {
         var6 = (TreeSet)var5.getSecond();
         if (var6 == null) {
            var6 = new TreeSet();
            var5.setSecond(var6);
         }
      }

      var6.add(var3);
   }

   private Set oW() {
      return this.nf.keySet();
   }

   private int q(long var1) {
      Couple var3 = (Couple)this.nf.get(var1);
      return var3.getFirst() != null ? (Integer)((TreeSet)var3.getFirst()).first() : (Integer)((TreeSet)var3.getSecond()).first();
   }

   private Long RF(long var1) {
      return (Long)this.nf.higherKey(var1);
   }

   private Long xK(long var1) {
      return (Long)this.nf.ceilingKey(var1);
   }

   private int q(long var1, TreeSet var3) {
      Couple var4 = (Couple)this.nf.get(var1);
      int var5;
      if (var4.getFirst() != null) {
         var5 = (Integer)((TreeSet)var4.getFirst()).first();
      } else {
         var5 = (Integer)((TreeSet)var4.getSecond()).first();
      }

      long var6 = Math.min(0L, var1 + var5 / 8);
      Long var8 = this.RF(var1);
      TreeSet var9 = new TreeSet();

      while (var8 != null && var8 < var6) {
         if (var4.getFirst() != null && var5 <= 64) {
            Couple var10 = (Couple)this.nf.get(var8);
            if (var10.getFirst() == null) {
               var9.add(var8);
               var8 = this.RF(var8);
               continue;
            }
         }

         var9.clear();
         if (var4.getFirst() == null || var4.getSecond() == null || (Integer)((TreeSet)var4.getSecond()).first() >= var5) {
            return 8;
         }

         var5 = (Integer)((TreeSet)var4.getSecond()).first();
         var6 = Math.min(0L, var1 + var5 / 8);
         var8 = this.RF(var1);
      }

      var3.addAll(var9);
      return var5;
   }

   public TreeMap RF() {
      TreeMap var1 = new TreeMap();
      TreeSet var2 = new TreeSet();

      for (long var4 : this.oW()) {
         if (var4 >= 0L) {
            break;
         }

         if (!var2.contains(var4)) {
            int var6 = this.q(var4, var2);
            var1.put(var4, var6);
         }
      }

      long var12 = 0L;

      while (true) {
         Long var5 = this.xK(var12);
         if (var5 == null) {
            return var1;
         }

         long var13 = var5 / this.Dw * this.Dw;
         long var8 = var13 + this.Dw;
         int var10;
         if (var5 != var13) {
            var5 = var13;
            var10 = this.xK;
         } else {
            Long var11 = this.RF(var5);
            if (var11 != null && var11 < var8) {
               var10 = this.xK;
            } else {
               var10 = this.q(var5);
               if (var11 != null && var5 + var10 / 8 > var11) {
                  var10 = this.xK;
               }
            }
         }

         var1.put(var5, var10);
         var12 = var8;
      }
   }

   public List xK() {
      IEStackManager var1 = this.RF.getStackManager();
      TreeMap var2 = this.RF();
      ArrayList var3 = new ArrayList(var2.size());

      for (Entry var5 : var2.entrySet()) {
         long var6 = (Long)var5.getKey();
         int var8 = (Integer)var5.getValue();
         IEVar var9 = var1.createStackItem(var6, var8);
         if (var9 != null) {
            var3.add(var9);
         }
      }

      return var3;
   }

   public String Dw() {
      StringBuilder var1 = new StringBuilder();

      for (Long var3 : this.nf.descendingKeySet()) {
         if (var3 >= 0L) {
            Strings.ff(var1, "+%04X:", var3);
         } else {
            Strings.ff(var1, "-%04X:", -var3);
         }

         Couple var4 = (Couple)this.nf.get(var3);
         if (var4.getFirst() != null) {
            for (Integer var6 : (TreeSet)var4.getFirst()) {
               var1.append(" ").append(var6);
            }
         }

         if (var4.getSecond() != null) {
            for (Integer var8 : (TreeSet)var4.getSecond()) {
               var1.append(" ").append(var8);
            }
         }

         Strings.ff(var1, "\n");
      }

      return var1.toString();
   }
}
