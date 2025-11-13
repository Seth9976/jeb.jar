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
public class amg {
   private static final StructuredLogger pC = aco.pC(amg.class);
   @SerId(1)
   private IERoutineContext A;
   @SerId(2)
   private int kS;
   @SerId(3)
   private int wS;
   @SerId(4)
   private Set UT = new TreeSet();
   @SerId(5)
   private TreeMap E = new TreeMap();
   @SerId(6)
   private TreeMap sY = new TreeMap();
   @SerId(7)
   private TreeMap ys = new TreeMap();

   @SerCustomInitPostGraph
   private void wS() {
      if (this.ys.isEmpty()) {
         for (Entry var2 : this.sY.entrySet()) {
            this.ys.put((Long)var2.getKey(), new Couple((TreeSet)var2.getValue(), null));
         }
      }
   }

   public amg(IERoutineContext var1, int var2) {
      this.A = var1;
      this.kS = var2;
      if (this.kS % 8 != 0) {
         throw new IllegalArgumentException("The normal slot bitsize must be a multiple of 8");
      } else {
         this.wS = this.kS / 8;
      }
   }

   public amg(IERoutineContext var1) {
      this(var1, var1.getWildcardTypeManager().getSlotBitsize());
   }

   public boolean pC(long var1, int var3) {
      return this.pC(var1, var3, null);
   }

   public boolean pC(long var1, int var3, Boolean var4) {
      if (var3 < 0 || var3 % 8 != 0) {
         throw new IllegalArgumentException(Strings.ff("Cannot record access for: offset=%Xh bitsize=%d", var1, var3));
      } else if (var3 == 0) {
         this.UT.add(var1);
         return false;
      } else {
         this.A(var1, var3, var4);
         return true;
      }
   }

   private void A(long var1, int var3, Boolean var4) {
      Couple var5 = (Couple)this.ys.get(var1);
      if (var5 == null) {
         var5 = new Couple(null, null);
         this.ys.put(var1, var5);
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

   private Set UT() {
      return this.ys.keySet();
   }

   private int pC(long var1) {
      Couple var3 = (Couple)this.ys.get(var1);
      return var3.getFirst() != null ? (Integer)((TreeSet)var3.getFirst()).first() : (Integer)((TreeSet)var3.getSecond()).first();
   }

   private Long A(long var1) {
      return (Long)this.ys.higherKey(var1);
   }

   private Long kS(long var1) {
      return (Long)this.ys.ceilingKey(var1);
   }

   private int pC(long var1, TreeSet var3) {
      Couple var4 = (Couple)this.ys.get(var1);
      int var5;
      if (var4.getFirst() != null) {
         var5 = (Integer)((TreeSet)var4.getFirst()).first();
      } else {
         var5 = (Integer)((TreeSet)var4.getSecond()).first();
      }

      long var6 = Math.min(0L, var1 + var5 / 8);
      Long var8 = this.A(var1);
      TreeSet var9 = new TreeSet();

      while (var8 != null && var8 < var6) {
         if (var4.getFirst() != null && var5 <= 64) {
            Couple var10 = (Couple)this.ys.get(var8);
            if (var10.getFirst() == null) {
               var9.add(var8);
               var8 = this.A(var8);
               continue;
            }
         }

         var9.clear();
         if (var4.getFirst() == null || var4.getSecond() == null || (Integer)((TreeSet)var4.getSecond()).first() >= var5) {
            return 8;
         }

         var5 = (Integer)((TreeSet)var4.getSecond()).first();
         var6 = Math.min(0L, var1 + var5 / 8);
         var8 = this.A(var1);
      }

      var3.addAll(var9);
      return var5;
   }

   public TreeMap pC() {
      TreeMap var1 = new TreeMap();
      TreeSet var2 = new TreeSet();

      for (long var4 : this.UT()) {
         if (var4 >= 0L) {
            break;
         }

         if (!var2.contains(var4)) {
            int var6 = this.pC(var4, var2);
            var1.put(var4, var6);
         }
      }

      long var12 = 0L;

      while (true) {
         Long var5 = this.kS(var12);
         if (var5 == null) {
            return var1;
         }

         long var13 = var5 / this.wS * this.wS;
         long var8 = var13 + this.wS;
         int var10;
         if (var5 != var13) {
            var5 = var13;
            var10 = this.kS;
         } else {
            Long var11 = this.A(var5);
            if (var11 != null && var11 < var8) {
               var10 = this.kS;
            } else {
               var10 = this.pC(var5);
               if (var11 != null && var5 + var10 / 8 > var11) {
                  var10 = this.kS;
               }
            }
         }

         var1.put(var5, var10);
         var12 = var8;
      }
   }

   public List A() {
      IEStackManager var1 = this.A.getStackManager();
      TreeMap var2 = this.pC();
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

   public String kS() {
      StringBuilder var1 = new StringBuilder();

      for (Long var3 : this.ys.descendingKeySet()) {
         if (var3 >= 0L) {
            Strings.ff(var1, "+%04X:", var3);
         } else {
            Strings.ff(var1, "-%04X:", -var3);
         }

         Couple var4 = (Couple)this.ys.get(var3);
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
