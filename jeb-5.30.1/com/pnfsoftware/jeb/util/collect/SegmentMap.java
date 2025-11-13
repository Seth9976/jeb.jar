package com.pnfsoftware.jeb.util.collect;

import com.pnfsoftware.jeb.util.base.Couple;
import com.pnfsoftware.jeb.util.format.Strings;
import com.pnfsoftware.jeb.util.serialization.annotations.Ser;
import com.pnfsoftware.jeb.util.serialization.annotations.SerConstructor;
import com.pnfsoftware.jeb.util.serialization.annotations.SerId;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.SortedMap;
import java.util.TreeMap;
import java.util.AbstractMap.SimpleEntry;
import java.util.Map.Entry;
import java.util.concurrent.ConcurrentNavigableMap;
import java.util.concurrent.ConcurrentSkipListMap;
import java.util.function.BiFunction;
import java.util.function.Predicate;

@Ser
public class SegmentMap extends ConcurrentSkipListMap implements ISegmentMap {
   private static final long serialVersionUID = 1L;
   @SerId(1)
   boolean removeSegmentsOnOverlap;

   @SerConstructor
   public SegmentMap() {
   }

   public SegmentMap(SegmentMap var1) {
      super((SortedMap)var1);
      this.removeSegmentsOnOverlap = var1.removeSegmentsOnOverlap;
   }

   protected SegmentMap(Comparator var1) {
      super(var1);
   }

   public void setRemoveSegmentsOnOverlap(boolean var1) {
      this.removeSegmentsOnOverlap = var1;
   }

   public boolean isRemoveSegmentsOnOverlap() {
      return this.removeSegmentsOnOverlap;
   }

   @Override
   public ISegment add(ISegment var1) {
      this.put(var1.getBegin(), var1);
      return var1;
   }

   @Override
   public ISegment addAndMerge(ISegment var1, BiFunction var2) {
      HashSet var3 = new HashSet(this.getOverlappingSegmentsMap(var1.getBegin(), true, var1.getEnd(), true).keySet());
      if (var3.isEmpty()) {
         return this.add(var1);
      } else {
         Comparable var4 = var1.getBegin();
         Comparable var5 = var1.getEnd();
         ArrayList var6 = new ArrayList();
         var6.add(var1);

         for (Comparable var8 : var3) {
            var4 = var8.compareTo(var4) < 0 ? var8 : var4;
            Comparable var9 = ((ISegment)this.get(var8)).getEnd();
            var5 = var9.compareTo(var5) > 0 ? var9 : var5;
            var6.add((ISegment)this.get(var8));
         }

         ISegment var10 = (ISegment)var2.apply(new Couple(var4, var5), var6);
         if (var10 == null) {
            return null;
         } else {
            for (Comparable var12 : var3) {
               this.remove(var12);
            }

            return this.add(var10);
         }
      }
   }

   @Override
   public void putAll(Map var1) {
      for (Entry var3 : var1.entrySet()) {
         this.put((Comparable)var3.getKey(), (ISegment)var3.getValue());
      }
   }

   public int compareKeys(Comparable var1, Comparable var2) {
      Comparator var3 = this.comparator();
      return var3 != null ? var3.compare(var1, var2) : var1.compareTo(var2);
   }

   @Override
   public ISegment put(Comparable var1, ISegment var2) {
      if (!var1.equals(var2.getBegin())) {
         throw new IllegalArgumentException(String.format("The provided key differs from the segment's begin value: %s != %s", var1, var2.getBegin()));
      } else if (!this.isValidKey(var1)) {
         throw new IllegalArgumentException("Invalid key: " + var1);
      } else if (!this.isValidKey(var2.getEnd())) {
         throw new IllegalArgumentException("Invalid end key: " + var2.getEnd());
      } else if (!this.isValidSegment(var2)) {
         throw new IllegalArgumentException("Invalid segment: " + var2);
      } else {
         Entry var3 = this.lowerEntry(var2.getEnd());
         if (var3 != null && this.compareKeys(((ISegment)var3.getValue()).getEnd(), var1) > 0) {
            if (!this.removeSegmentsOnOverlap) {
               throw new IllegalArgumentException("Attempt to insert the given segment overlaps existing segment(s)");
            }

            for (Comparable var6 : new HashSet(this.getOverlappingSegmentsMap(var1, true, var2.getEnd(), true).keySet())) {
               this.remove(var6);
            }
         }

         return (ISegment)super.put(var1, var2);
      }
   }

   public boolean isValidKey(Comparable var1) {
      return true;
   }

   public boolean isValidSegment(ISegment var1) {
      return this.compareKeys(var1.getBegin(), var1.getEnd()) < 0;
   }

   @Override
   public ISegment getSegmentContaining(Comparable var1) {
      Entry var2 = this.floorEntry(var1);
      if (var2 == null) {
         return null;
      } else {
         ISegment var3 = (ISegment)var2.getValue();
         return this.compareKeys(var1, var3.getEnd()) < 0 ? var3 : null;
      }
   }

   @Override
   public ISegment getSegmentAfter(Comparable var1) {
      Entry var2 = this.higherEntry(var1);
      return var2 == null ? null : (ISegment)var2.getValue();
   }

   @Override
   public ISegment getSegmentBefore(Comparable var1) {
      ISegment var2 = this.getSegmentContaining(var1);
      if (var2 != null) {
         var1 = var2.getBegin();
      }

      Entry var3 = this.lowerEntry(var1);
      return var3 == null ? null : (ISegment)var3.getValue();
   }

   @Override
   public SortedMap getOverlappingSegmentsMap(Comparable var1, boolean var2, Comparable var3, boolean var4) {
      return this.getOverlappingSegmentsMap(var1, var2, var3, var4, null);
   }

   @Override
   public SortedMap getOverlappingSegmentsMap(Comparable var1, boolean var2, Comparable var3, boolean var4, Predicate var5) {
      TreeMap var6 = new TreeMap();
      boolean var7 = true;
      if (var2) {
         ISegment var8 = this.getSegmentContaining(var1);
         if (var8 != null && (var5 == null || var5.test(new SimpleEntry(var8.getBegin(), var8)))) {
            var6.put(var8.getBegin(), var8);
         }

         var7 = false;
      }

      ConcurrentNavigableMap var11 = this.subMap(var1, var7, var3, false);
      if (var5 == null) {
         var6.putAll(var11);
      } else {
         for (Entry var10 : var11.entrySet()) {
            if (var5.test(var10)) {
               var6.put((Comparable)var10.getKey(), (ISegment)var10.getValue());
            }
         }
      }

      Entry var12 = var6.lastEntry();
      if (var12 != null && !var4 && this.compareKeys(((ISegment)var12.getValue()).getEnd(), var3) > 0) {
         var6.remove(var12.getKey());
      }

      return var6;
   }

   @Override
   public boolean isEmptyRange(Comparable var1, Comparable var2) {
      if (this.getSegmentContaining(var1) != null) {
         return false;
      } else {
         ISegment var3 = this.getSegmentAfter(var1);
         return var3 == null ? true : this.compareKeys(var3.getBegin(), var2) >= 0;
      }
   }

   @Override
   public SortedMap contiguousSubMap(Comparable var1, Comparable var2, ISegmentFactory var3) {
      List var4 = this.generateGapItems(var1, true, var2, true, var3, false);
      TreeMap var5 = new TreeMap((SortedMap)this.subMap(var1, var2));

      for (ISegment var7 : var4) {
         var5.put(var7.getBegin(), var7);
      }

      return var5;
   }

   @Override
   public List fillGaps(Comparable var1, Comparable var2, ISegmentFactory var3) {
      return this.generateGapItems(var1, true, var2, true, var3, true);
   }

   @Override
   public List generateGapItems(Comparable var1, boolean var2, Comparable var3, boolean var4, ISegmentFactory var5, boolean var6) {
      ArrayList var7 = new ArrayList();
      ISegment var8 = this.getSegmentContaining(var1);
      if (var8 == null) {
         var8 = this.getSegmentAfter(var1);
         if (var8 == null || this.compareKeys(var8.getBegin(), var3) > 0) {
            if (var2 && var4) {
               var7.add(this.add((ISegment)var5.create(var1, var3)));
            }

            return var7;
         }

         if (var2) {
            var7.add((ISegment)var5.create(var1, var8.getBegin()));
         }
      }

      ConcurrentNavigableMap var9 = this.subMap(var8.getBegin(), false, var3, false);

      for (Comparable var11 : var9.keySet()) {
         ISegment var12 = (ISegment)var9.get(var11);
         Comparable var13 = var8.getEnd();
         int var14 = this.compareKeys(var12.getBegin(), var13);
         if (var14 > 0) {
            var7.add((ISegment)var5.create(var13, var12.getBegin()));
         }

         var8 = var12;
      }

      if (var4) {
         Comparable var15 = var8.getEnd();
         int var17 = this.compareKeys(var3, var15);
         if (var17 > 0) {
            var7.add((ISegment)var5.create(var15, var3));
         }
      }

      if (var6) {
         for (ISegment var18 : var7) {
            this.put(var18.getBegin(), var18);
         }
      }

      return var7;
   }

   private boolean verify(ISegmentGapVerifier var1, Comparable var2, Comparable var3, List var4) {
      ISegmentGapVerifier.VerificationCode var5 = var1 == null ? ISegmentGapVerifier.VerificationCode.INCLUDE_AND_CONTINUE : var1.verify(var2, var3);
      switch (var5) {
         case INCLUDE_AND_CONTINUE:
            var4.add(new Couple(var2, var3));
            return true;
         case INCLUDE_AND_EXIT:
            var4.add(new Couple(var2, var3));
            return false;
         case SKIP_AND_CONTINUE:
            return true;
         case SKIP_AND_EXIT:
            return false;
         default:
            throw new RuntimeException();
      }
   }

   @Override
   public List generateGaps(Comparable var1, boolean var2, Comparable var3, boolean var4, ISegmentGapVerifier var5) {
      ArrayList var6 = new ArrayList();
      ISegment var7 = this.getSegmentContaining(var1);
      if (var7 == null) {
         var7 = this.getSegmentAfter(var1);
         if (var7 == null || this.compareKeys(var7.getBegin(), var3) > 0) {
            return var2 && var4 && !this.verify(var5, var1, var3, var6) ? var6 : var6;
         }

         if (var2 && !this.verify(var5, var1, var7.getBegin(), var6)) {
            return var6;
         }
      }

      ConcurrentNavigableMap var8 = this.subMap(var7.getBegin(), false, var3, false);

      for (Comparable var10 : var8.keySet()) {
         ISegment var11 = (ISegment)var8.get(var10);
         Comparable var12 = var7.getEnd();
         int var13 = this.compareKeys(var11.getBegin(), var12);
         if (var13 > 0 && !this.verify(var5, var12, var11.getBegin(), var6)) {
            return var6;
         }

         var7 = var11;
      }

      if (var4) {
         Comparable var14 = var7.getEnd();
         int var15 = this.compareKeys(var3, var14);
         if (var15 > 0 && !this.verify(var5, var14, var3, var6)) {
            return var6;
         }
      }

      return var6;
   }

   @Override
   public List generateGaps(Comparable var1, boolean var2, Comparable var3, boolean var4) {
      return this.generateGaps(var1, var2, var3, var4, null);
   }

   public ConcurrentNavigableMap subMap(Comparable var1, boolean var2, Comparable var3, boolean var4) {
      try {
         return super.subMap(var1, var2, var3, var4);
      } catch (IllegalArgumentException | NullPointerException var7) {
         String var6 = Strings.ff("Bad SegmentMap subMap() query: from=%s to=%s", var1, var3);
         throw new RuntimeException(var6, var7);
      }
   }

   @Override
   public int hashCode() {
      int var1 = super.hashCode();
      return 31 * var1 + (this.removeSegmentsOnOverlap ? 1231 : 1237);
   }

   @Override
   public boolean equals(Object var1) {
      if (this == var1) {
         return true;
      } else if (!super.equals(var1)) {
         return false;
      } else if (this.getClass() != var1.getClass()) {
         return false;
      } else {
         SegmentMap var2 = (SegmentMap)var1;
         return this.removeSegmentsOnOverlap == var2.removeSegmentsOnOverlap;
      }
   }

   @Override
   public String toString() {
      return Strings.ff("smap:%d[%s]", this.size(), super.toString());
   }
}
