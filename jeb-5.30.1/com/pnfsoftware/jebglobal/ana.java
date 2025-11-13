package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.IEVar;
import com.pnfsoftware.jeb.util.base.Assert;
import com.pnfsoftware.jeb.util.base.Couple;
import com.pnfsoftware.jeb.util.collect.ISegment;
import com.pnfsoftware.jeb.util.collect.SegmentMap;
import com.pnfsoftware.jeb.util.format.Strings;
import com.pnfsoftware.jeb.util.logging.ILogger;
import com.pnfsoftware.jeb.util.serialization.annotations.Ser;
import com.pnfsoftware.jeb.util.serialization.annotations.SerId;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Ser
public class ana {
   private static final ILogger q = aeg.q(ana.class);
   @SerId(1)
   private int RF;
   @SerId(2)
   private boolean xK;
   @SerId(3)
   private SegmentMap Dw = new SegmentMap();

   public static ana q() {
      return new ana(0, true);
   }

   public static ana RF() {
      return new ana(1, false);
   }

   public ana(int var1, boolean var2) {
      if (var1 >= 0 && (var1 != 0 || var2)) {
         this.RF = var1;
         this.xK = var2;
      } else {
         throw new IllegalArgumentException("Illegal start of range: " + var1);
      }
   }

   public IEVar q(int var1) {
      Assert.a(var1 >= this.RF);
      return (IEVar)this.Dw.remove(var1);
   }

   public IEVar q(String var1) {
      var1 = this.q(var1, false);

      for (IEVar var3 : this.Dw.values()) {
         if (var1.equals(var3.getName())) {
            return var3;
         }
      }

      return null;
   }

   public IEVar RF(int var1) {
      Assert.a(var1 >= this.RF);
      return (IEVar)this.Dw.get(var1);
   }

   public IEVar xK(int var1) {
      Assert.a(var1 >= this.RF);
      return (IEVar)this.Dw.getSegmentContaining(var1);
   }

   public Collection q(int var1, int var2) {
      return Collections.unmodifiableCollection(this.Dw.subMap(var1, var2).values());
   }

   public IEVar RF(int var1, int var2) {
      Integer var3 = (Integer)this.Dw.ceilingKey(var1);
      return var3 != null && var3 < var2 ? (IEVar)this.Dw.get(var3) : null;
   }

   public IEVar xK(int var1, int var2) {
      Integer var3 = (Integer)this.Dw.lowerKey(var2);
      return var3 != null && var3 >= var1 ? (IEVar)this.Dw.get(var3) : null;
   }

   public Collection xK() {
      return Collections.unmodifiableCollection(this.Dw.values());
   }

   public boolean Dw(int var1, int var2) {
      return this.Dw.isEmptyRange(var1, var1 + var2);
   }

   public IEVar q(int var1, int var2, String var3, int var4) {
      return this.q(var1, var2, var3, var4, null);
   }

   public IEVar q(int var1, int var2, String var3, int var4, Long var5) {
      int var6 = this.q(var1, var2, var4);
      return this.q(var6, var3, var4, var5, false);
   }

   public IEVar q(int var1, String var2, int var3, Long var4, boolean var5) {
      Assert.a(var1 >= this.RF);
      IEVar var6 = (IEVar)this.Dw.get(var1);
      if (var6 != null) {
         if (var5) {
            return null;
         } else if (var6.getBitsize() != var3) {
            throw new RuntimeException(Strings.ff("Variable \"%s\" was already defined with a different bitsize (%d != %d)", var2, var3, var6.getBitsize()));
         } else {
            return var6;
         }
      } else if (!this.Dw.isEmptyRange(var1, var1 + var3)) {
         long var7 = 0L;
         if (var4 != null) {
            var7 = var4;
            if (var7 < 0L) {
               var7 = -var7;
            }
         }

         throw new RuntimeException(Strings.ff("Variables exist in the range [%d,%d): 0x%X", var1, var1 + var3, var7));
      } else {
         if (var2 != null) {
            var2 = this.q(var2, true);
         }

         amy var9 = new amy(this.xK ? var1 : -var1, var2, var3);
         if (var4 != null) {
            var9.q(var4);
         }

         this.Dw.put((int)var1, (ISegment)var9);
         return var9;
      }
   }

   private int q(int var1, int var2, int var3) {
      Integer var4 = (Integer)this.Dw.lowerKey(var2);
      if (var4 != null && var4 >= var1) {
         var4 = var4 + ((IEVar)this.Dw.get(var4)).getBitsize();
         if (var4 + var3 > var2) {
            List var5 = this.Dw.generateGaps(var1, true, var2, true, new anb(this, var3));
            if (var5.isEmpty()) {
               throw new RuntimeException("Not enough space to allocate var");
            }

            return (Integer)((Couple)var5.get(0)).getFirst();
         }
      } else {
         var4 = var1;
         if (var4 + var3 > var2) {
            throw new RuntimeException("Not enough space to allocate var");
         }
      }

      return var4;
   }

   private String q(String var1, boolean var2) {
      if (var1 != null && !var1.isEmpty()) {
         var1 = Strings.replaceWhitespaces(var1, '_');
      } else {
         var1 = "anon";
      }

      if (var2) {
         Set var3 = this.Dw();
         String var4 = var1;

         for (int var5 = 1; var3.contains(var4); var5++) {
            var4 = var1 + "_" + var5;
         }

         var1 = var4;
      }

      return var1;
   }

   private Set Dw() {
      HashSet var1 = new HashSet(this.Dw.size());

      for (IEVar var3 : this.Dw.values()) {
         String var4 = var3.getName();
         if (var4 != null) {
            var1.add(var4);
         }
      }

      return var1;
   }

   public static boolean RF(String var0) {
      int var1 = 0;
      char var2 = var0.charAt(var1++);
      if (var2 != '_' && var2 != '$' && !Character.isLetter(var2)) {
         return false;
      } else {
         while (var1 < var0.length()) {
            var2 = var0.charAt(var1++);
            if (var2 != '_' && var2 != '$' && !Character.isLetterOrDigit(var2)) {
               return false;
            }
         }

         return true;
      }
   }
}
