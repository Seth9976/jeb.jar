package com.pnfsoftware.jeb.core.units.code.asm.analyzer;

import com.pnfsoftware.jeb.core.units.code.asm.memory.IVirtualMemory;
import com.pnfsoftware.jeb.util.base.Assert;
import com.pnfsoftware.jeb.util.base.Couple;
import com.pnfsoftware.jeb.util.collect.ISegment;
import com.pnfsoftware.jeb.util.collect.SegmentMap;
import com.pnfsoftware.jeb.util.format.Strings;
import com.pnfsoftware.jeb.util.primitives.Longs;
import com.pnfsoftware.jeb.util.serialization.annotations.Ser;
import com.pnfsoftware.jeb.util.serialization.annotations.SerId;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import java.util.SortedMap;
import javax.annotation.concurrent.Immutable;

@Ser
public class MemoryRanges {
   @SerId(1)
   private SegmentMap map = new SegmentMap();
   @SerId(2)
   private BigInteger endaddr;
   @SerId(3)
   private boolean cacheValid;
   @SerId(4)
   private long cacheRangeBegin;
   @SerId(5)
   private long cacheRangeEnd;

   public MemoryRanges(int var1) {
      if (var1 <= 0) {
         throw new IllegalArgumentException();
      } else {
         this.endaddr = BigInteger.ONE.shiftLeft(var1);
      }
   }

   public MemoryRanges() {
      this(64);
   }

   public MemoryRanges(IVirtualMemory var1) {
      this(var1.getSpaceBits());
      long var2 = var1.getPageSize();

      for (Long var5 : var1.getAllocatedPageBases()) {
         this.add(var5, var5 + var2);
      }
   }

   private void verifyRange(BigInteger var1, BigInteger var2) {
      if (var1.compareTo(var2) >= 0) {
         throw new IllegalArgumentException(
            Strings.ff("Wrong range [%s,%s[ : begin address must be < than the end addres", var1.toString(16), var2.toString(16))
         );
      } else if (var2.compareTo(this.endaddr) >= 0) {
         throw new IllegalArgumentException(
            Strings.ff("Invalid end address %s: The end address must be stricly < %s", var2.toString(16), this.endaddr.toString(16))
         );
      }
   }

   private void updateCache() {
      if (this.count() == 1) {
         this.cacheRangeBegin = this.min();
         this.cacheRangeEnd = this.max();
         if (this.cacheRangeEnd > 0L) {
            this.cacheValid = true;
            return;
         }
      }

      this.cacheValid = false;
   }

   public void add(long var1, long var3) {
      BigInteger var5 = Longs.toUnsignedBigInteger(var1);
      BigInteger var6 = Longs.toUnsignedBigInteger(var3);
      this.verifyRange(var5, var6);
      SortedMap var7 = this.map.getOverlappingSegmentsMap(var5, true, var6, true);
      if (!var7.isEmpty()) {
         ArrayList var8 = new ArrayList(var7.values());
         MemoryRanges.Range var9 = (MemoryRanges.Range)var8.get(0);
         if (var9.getBegin().compareTo(var5) < 0) {
            var5 = var9.getBegin();
         }

         var9 = (MemoryRanges.Range)var8.get(var8.size() - 1);
         if (var9.getEnd().compareTo(var6) > 0) {
            var6 = var9.getEnd();
         }

         for (BigInteger var11 : var7.keySet()) {
            this.map.remove(var11);
         }
      }

      MemoryRanges.Range var12 = new MemoryRanges.Range(var5, var6);
      this.map.add(var12);
      boolean var14 = false;
      MemoryRanges.Range var15 = (MemoryRanges.Range)this.map.get(var6);
      if (var15 != null) {
         var6 = var15.getEnd();
         var14 = true;
         this.map.remove(var15.getBegin());
      }

      var15 = (MemoryRanges.Range)this.map.getSegmentContaining(var5.subtract(BigInteger.ONE));
      if (var15 != null) {
         var5 = var15.getBegin();
         var14 = true;
         this.map.remove(var15.getBegin());
      }

      if (var14) {
         this.map.remove(var12.getBegin());
         this.map.add(new MemoryRanges.Range(var5, var6));
      }

      this.updateCache();
   }

   public void remove(long var1, long var3) {
      BigInteger var5 = Longs.toUnsignedBigInteger(var1);
      BigInteger var6 = Longs.toUnsignedBigInteger(var3);
      this.verifyRange(var5, var6);
      SortedMap var7 = this.map.getOverlappingSegmentsMap(var5, true, var6, true);
      if (!var7.isEmpty()) {
         MemoryRanges.Range var8 = null;
         MemoryRanges.Range var9 = null;
         ArrayList var10 = new ArrayList(var7.values());
         MemoryRanges.Range var11 = (MemoryRanges.Range)var10.get(0);
         if (var11.getBegin().compareTo(var5) < 0) {
            var8 = new MemoryRanges.Range(var11.getBegin(), var5);
         }

         var11 = (MemoryRanges.Range)var10.get(var10.size() - 1);
         if (var11.getEnd().compareTo(var6) > 0) {
            var9 = new MemoryRanges.Range(var6, var11.getEnd());
         }

         for (BigInteger var13 : var7.keySet()) {
            this.map.remove(var13);
         }

         if (var8 != null) {
            this.map.add(var8);
         }

         if (var9 != null) {
            this.map.add(var9);
         }

         this.updateCache();
      }
   }

   public void clear() {
      this.map.clear();
      this.cacheValid = false;
   }

   public Long min() {
      return this.map.isEmpty() ? null : ((BigInteger)this.map.firstKey()).longValue();
   }

   public Long max() {
      return this.map.isEmpty() ? null : ((MemoryRanges.Range)this.map.lastEntry().getValue()).getEnd().longValue();
   }

   public int count() {
      return this.map.size();
   }

   public List asList() {
      ArrayList var1 = new ArrayList(this.map.size());

      for (MemoryRanges.Range var3 : this.map.values()) {
         var1.add(new Couple(var3.getBegin().longValue(), var3.getEnd().longValue()));
      }

      return var1;
   }

   public boolean contains(long var1) {
      if (this.cacheValid && var1 >= 0L) {
         return var1 >= this.cacheRangeBegin && var1 < this.cacheRangeEnd;
      } else {
         BigInteger var3 = Longs.toUnsignedBigInteger(var1);
         return this.map.getSegmentContaining(var3) != null;
      }
   }

   public boolean intersects(long var1, long var3) {
      return !this.map.getOverlappingSegmentsMap(Longs.toUnsignedBigInteger(var1), true, Longs.toUnsignedBigInteger(var3), false).isEmpty();
   }

   public Long getLocalBegin(long var1) {
      BigInteger var3 = Longs.toUnsignedBigInteger(var1);
      MemoryRanges.Range var4 = (MemoryRanges.Range)this.map.getSegmentContaining(var3);
      return var4 == null ? null : var4.getBegin().longValue();
   }

   public Long getLocalEnd(long var1) {
      BigInteger var3 = Longs.toUnsignedBigInteger(var1);
      MemoryRanges.Range var4 = (MemoryRanges.Range)this.map.getSegmentContaining(var3);
      return var4 == null ? null : var4.getEnd().longValue();
   }

   public Couple getNextRange(long var1) {
      BigInteger var3 = Longs.toUnsignedBigInteger(var1);
      MemoryRanges.Range var4 = (MemoryRanges.Range)this.map.getSegmentAfter(var3);
      return var4 == null ? null : new Couple(var4.getBegin().longValue(), var4.getEnd().longValue());
   }

   public Couple getPreviousRange(long var1) {
      BigInteger var3 = Longs.toUnsignedBigInteger(var1);
      MemoryRanges.Range var4 = (MemoryRanges.Range)this.map.getSegmentBefore(var3);
      return var4 == null ? null : new Couple(var4.getBegin().longValue(), var4.getEnd().longValue());
   }

   public Couple getLocalRange(long var1) {
      BigInteger var3 = Longs.toUnsignedBigInteger(var1);
      MemoryRanges.Range var4 = (MemoryRanges.Range)this.map.getSegmentContaining(var3);
      return var4 == null ? null : new Couple(var4.getBegin().longValue(), var4.getEnd().longValue());
   }

   public long spanSize() {
      if (this.map.isEmpty()) {
         return 0L;
      } else {
         BigInteger var1 = ((MemoryRanges.Range)this.map.lastEntry().getValue()).getEnd().subtract((BigInteger)this.map.firstKey());
         return var1.longValue();
      }
   }

   public long aggregatedRangesSize() {
      BigInteger var1 = BigInteger.ZERO;

      for (MemoryRanges.Range var3 : this.map.values()) {
         BigInteger var4 = var3.getEnd().subtract(var3.getBegin());
         var1 = var1.add(var4);
      }

      return var1.longValue();
   }

   @Override
   public String toString() {
      StringBuilder var1 = new StringBuilder(this.count() + " ranges: ");
      int var2 = 0;

      for (MemoryRanges.Range var4 : this.map.values()) {
         if (var2 >= 1) {
            var1.append(", ");
         }

         Strings.ff(var1, "%X-%X", var4.getBegin().longValue(), var4.getEnd().longValue());
         var2++;
      }

      return var1.toString();
   }

   @Ser
   @Immutable
   public static class Range implements ISegment {
      @SerId(1)
      BigInteger begin;
      @SerId(2)
      BigInteger end;

      Range(BigInteger var1, BigInteger var2) {
         Assert.a(var1.compareTo(var2) < 0);
         this.begin = var1;
         this.end = var2;
      }

      public BigInteger getBegin() {
         return this.begin;
      }

      public BigInteger getEnd() {
         return this.end;
      }
   }
}
