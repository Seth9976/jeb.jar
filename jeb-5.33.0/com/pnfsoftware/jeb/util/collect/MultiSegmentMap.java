package com.pnfsoftware.jeb.util.collect;

import com.pnfsoftware.jeb.util.serialization.annotations.Ser;
import com.pnfsoftware.jeb.util.serialization.annotations.SerId;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.TreeMap;

@Ser
public class MultiSegmentMap implements IMultiSegmentMap {
   @SerId(1)
   private TreeMap map;
   @SerId(2)
   private int cachedSize;

   public MultiSegmentMap() {
      this.map = new TreeMap();
   }

   protected MultiSegmentMap(Comparator var1) {
      this.map = new TreeMap(var1);
   }

   public int compareKeys(Comparable var1, Comparable var2) {
      Comparator var3 = this.map.comparator();
      return var3 != null ? var3.compare(var1, var2) : var1.compareTo(var2);
   }

   @Override
   public void clear() {
      this.map.clear();
      this.cachedSize = 0;
   }

   @Override
   public boolean isEmpty() {
      return this.cachedSize == 0;
   }

   @Override
   public int size() {
      return this.cachedSize;
   }

   @Override
   public ISegment add(ISegment var1) {
      Comparable var2 = var1.getBegin();
      Maps.putMulti(this.map, var2, var1);
      this.cachedSize++;
      return var1;
   }

   @Override
   public ISegment getFirstSegmentContaining(Comparable var1) {
      if (!this.map.isEmpty()) {
         Comparable var2 = (Comparable)this.map.firstKey();
         if (this.compareKeys(var2, var1) <= 0) {
            for (List var4 : this.map.subMap(var2, var1).values()) {
               for (ISegment var6 : var4) {
                  if (this.compareKeys(var6.getEnd(), var1) > 0) {
                     return var6;
                  }
               }
            }
         }
      }

      return null;
   }

   @Override
   public List getSegmentsContaining(Comparable var1) {
      ArrayList var2 = new ArrayList();
      if (!this.map.isEmpty()) {
         Comparable var3 = (Comparable)this.map.firstKey();
         if (this.compareKeys(var3, var1) <= 0) {
            for (List var5 : this.map.subMap(var3, var1).values()) {
               for (ISegment var7 : var5) {
                  if (this.compareKeys(var7.getEnd(), var1) > 0) {
                     var2.add(var7);
                  }
               }
            }
         }
      }

      return var2;
   }
}
