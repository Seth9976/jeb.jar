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
public class akw {
   private static final ILogger pC = aco.pC(akw.class);
   @SerId(1)
   private int A;
   @SerId(2)
   private boolean kS;
   @SerId(3)
   private SegmentMap wS = new SegmentMap();

   public akw(int var1, boolean var2) {
      if (var1 >= 0 && (var1 != 0 || var2)) {
         this.A = var1;
         this.kS = var2;
      } else {
         throw new IllegalArgumentException("Illegal start of range: " + var1);
      }
   }

   public IEVar pC(int var1) {
      Assert.a(var1 >= this.A);
      return (IEVar)this.wS.remove(var1);
   }

   public IEVar pC(String var1) {
      var1 = this.pC(var1, false);

      for (IEVar var3 : this.wS.values()) {
         if (var1.equals(var3.getName())) {
            return var3;
         }
      }

      return null;
   }

   public IEVar A(int var1) {
      Assert.a(var1 >= this.A);
      return (IEVar)this.wS.get(var1);
   }

   public IEVar kS(int var1) {
      Assert.a(var1 >= this.A);
      return (IEVar)this.wS.getSegmentContaining(var1);
   }

   public Collection pC(int var1, int var2) {
      return Collections.unmodifiableCollection(this.wS.subMap(var1, var2).values());
   }

   public IEVar A(int var1, int var2) {
      Integer var3 = (Integer)this.wS.ceilingKey(var1);
      return var3 != null && var3 < var2 ? (IEVar)this.wS.get(var3) : null;
   }

   public IEVar kS(int var1, int var2) {
      Integer var3 = (Integer)this.wS.lowerKey(var2);
      return var3 != null && var3 >= var1 ? (IEVar)this.wS.get(var3) : null;
   }

   public Collection pC() {
      return Collections.unmodifiableCollection(this.wS.values());
   }

   public boolean wS(int var1, int var2) {
      return this.wS.isEmptyRange(var1, var1 + var2);
   }

   public IEVar pC(int var1, int var2, String var3, int var4, Long var5) {
      int var6 = this.pC(var1, var2, var4);
      return this.pC(var6, var3, var4, var5, false);
   }

   public IEVar pC(int var1, String var2, int var3, Long var4, boolean var5) {
      Assert.a(var1 >= this.A);
      IEVar var6 = (IEVar)this.wS.get(var1);
      if (var6 != null) {
         if (var5) {
            return null;
         } else if (var6.getBitsize() != var3) {
            throw new RuntimeException(Strings.ff("Variable \"%s\" was already defined with a different bitsize (%d != %d)", var2, var3, var6.getBitsize()));
         } else {
            return var6;
         }
      } else if (!this.wS.isEmptyRange(var1, var1 + var3)) {
         long var7 = 0L;
         if (var4 != null) {
            var7 = var4;
            if (var7 < 0L) {
               var7 = -var7;
            }
         }

         throw new RuntimeException(
            Strings.ff("Variables exist in the range [%d,%d): 0x%X (%s)", var1, var1 + var3, var7, this.wS.ceilingEntry(var1).getValue())
         );
      } else {
         if (var2 != null) {
            var2 = this.pC(var2, true);
         }

         aku var9 = new aku(this.kS ? var1 : -var1, var2, var3);
         if (var4 != null) {
            var9.pC(var4);
         }

         this.wS.put((int)var1, (ISegment)var9);
         return var9;
      }
   }

   private int pC(int var1, int var2, int var3) {
      Integer var4 = (Integer)this.wS.lowerKey(var2);
      if (var4 != null && var4 >= var1) {
         var4 = var4 + ((IEVar)this.wS.get(var4)).getBitsize();
         if (var4 + var3 > var2) {
            List var5 = this.wS.generateGaps(var1, true, var2, true, new akx(this, var3));
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

   private String pC(String var1, boolean var2) {
      if (var1 != null && !var1.isEmpty()) {
         var1 = Strings.replaceWhitespaces(var1, '_');
      } else {
         var1 = "anon";
      }

      if (var2) {
         Set var3 = this.A();
         String var4 = var1;

         for (int var5 = 1; var3.contains(var4); var5++) {
            var4 = var1 + "_" + var5;
         }

         var1 = var4;
      }

      return var1;
   }

   private Set A() {
      HashSet var1 = new HashSet(this.wS.size());

      for (IEVar var3 : this.wS.values()) {
         String var4 = var3.getName();
         if (var4 != null) {
            var1.add(var4);
         }
      }

      return var1;
   }
}
