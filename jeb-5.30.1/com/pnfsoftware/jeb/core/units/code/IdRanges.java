package com.pnfsoftware.jeb.core.units.code;

import com.pnfsoftware.jeb.util.collect.ISegment;
import com.pnfsoftware.jeb.util.collect.IntegerSegment;
import com.pnfsoftware.jeb.util.collect.SegmentMap;
import com.pnfsoftware.jeb.util.format.Strings;
import com.pnfsoftware.jeb.util.serialization.annotations.Ser;
import com.pnfsoftware.jeb.util.serialization.annotations.SerId;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.SortedMap;
import java.util.Map.Entry;

@Ser
public class IdRanges {
   @SerId(1)
   private Map fullVars = new HashMap();
   @SerId(2)
   private Map rangesFull = new HashMap();

   public IdRanges() {
   }

   public IdRanges(IdRanges var1) {
      this.fullVars.putAll(var1.fullVars);

      for (Integer var3 : var1.rangesFull.keySet()) {
         SegmentMap var4 = (SegmentMap)var1.rangesFull.get(var3);
         if (var4 == null) {
            this.rangesFull.put(var3, null);
         } else {
            this.rangesFull.put(var3, new SegmentMap(var4));
         }
      }
   }

   public void clear() {
      this.fullVars.clear();
      this.rangesFull.clear();
   }

   public int bsize() {
      int var1 = 0;

      for (SegmentMap var3 : this.rangesFull.values()) {
         for (IntegerSegment var5 : var3.values()) {
            var1 += var5.getSize();
         }
      }

      return var1;
   }

   public int size() {
      return this.fullVars.size();
   }

   public boolean isEmpty() {
      return this.fullVars.isEmpty();
   }

   public void remove(IVariable var1) {
      this.remove(var1.getId());
   }

   public void remove(int var1) {
      this.fullVars.remove(var1);
      this.rangesFull.remove(var1);
   }

   public void addAll(Collection var1, Collection var2) {
      for (IVariable var4 : var1) {
         if (!var2.contains(var4)) {
            this.add(var4);
         }
      }
   }

   public void addAll(Collection var1, IdRanges var2) {
      for (IVariable var4 : var1) {
         if (!var2.fullVars.containsKey(var4.getId())) {
            this.add(var4);
         }
      }
   }

   public void addAll(Collection var1) {
      for (IVariable var3 : var1) {
         this.add(var3);
      }
   }

   public void add(IVariable var1) {
      this.add(var1.getId(), var1.getBitsize(), var1.getId(), var1.getBitsize());
   }

   public void add(IVariable var1, int var2, int var3) {
      this.add(var1.getId(), var1.getBitsize(), var2, var3);
   }

   public void add(int var1, int var2) {
      this.add(var1, var2, 0, var2);
   }

   private void add(int var1, int var2, int var3, int var4) {
      SegmentMap var5 = (SegmentMap)this.rangesFull.get(var1);
      if (var5 == null) {
         var5 = new SegmentMap();
         var5.setRemoveSegmentsOnOverlap(true);
         this.rangesFull.put(var1, var5);
      }

      if (var3 < 0) {
         var3 = var3 - var4 + 1;
      }

      int var6 = var3 + var4;
      SortedMap var7 = var5.getOverlappingSegmentsMap(var3, true, var6, true);
      if (!var7.isEmpty()) {
         ArrayList var8 = new ArrayList(var7.values());
         IntegerSegment var9 = (IntegerSegment)var8.get(0);
         if (var9.getBegin().compareTo(var3) < 0) {
            var3 = var9.getBegin();
         }

         var9 = (IntegerSegment)var8.get(var8.size() - 1);
         if (var9.getEnd().compareTo(var6) > 0) {
            var6 = var9.getEnd();
         }

         for (Integer var11 : var7.keySet()) {
            var5.remove(var11);
         }
      }

      IntegerSegment var12 = new IntegerSegment(var3, var6 - var3);
      var5.add(var12);
      this.fullVars.put(var1, var2);
   }

   public boolean containsVarPart(IVariable var1) {
      SegmentMap var2 = (SegmentMap)this.rangesFull.get(var1.getId());
      return var2 != null && !var2.isEmpty();
   }

   public boolean containsVarFull(int var1, int var2) {
      SegmentMap var3 = (SegmentMap)this.rangesFull.get(var1);
      return var3 != null && var3.size() == 1 && ((IntegerSegment)var3.values().iterator().next()).getSize() == var2;
   }

   public boolean containsVarFull(int var1) {
      Integer var2 = (Integer)this.fullVars.get(var1);
      return var2 != null ? this.containsVarFull(var1, var2) : false;
   }

   public List getVarIds() {
      ArrayList var1 = new ArrayList();
      this.collectVarIds(var1);
      return var1;
   }

   public void collectVarIds(Collection var1) {
      var1.addAll(this.fullVars.keySet());
   }

   public List getBits() {
      ArrayList var1 = new ArrayList();
      this.collectBits(var1);
      return var1;
   }

   public void collectBits(List var1) {
      for (Entry var3 : this.rangesFull.entrySet()) {
         for (IntegerSegment var5 : ((SegmentMap)var3.getValue()).values()) {
            int var6 = var5.getBegin();
            int var7 = var5.getEnd();

            while (var6 < var7) {
               var1.add(var6++);
            }
         }
      }
   }

   public IdRanges intersection(IdRanges var1) {
      Map var2 = this.intersect(this.rangesFull, var1.rangesFull);
      if (var2.size() == 0) {
         return null;
      } else {
         IdRanges var3 = new IdRanges();

         for (Entry var5 : var2.entrySet()) {
            int var6 = (Integer)var5.getKey();
            Integer var7 = (Integer)this.fullVars.get(var6);
            if (var7 == null) {
               throw new RuntimeException(Strings.ff("Error with intersection of var %d", var5.getKey()));
            }

            for (IntegerSegment var9 : ((SegmentMap)var5.getValue()).values()) {
               int var10 = var9.getBegin();
               if (var9.getBegin() < 0) {
                  var10 = var10 + var9.getSize() - 1;
               }

               var3.add(var6, var7, var10, var9.getSize());
            }
         }

         return var3;
      }
   }

   private Map intersect(Map var1, Map var2) {
      HashMap var3 = new HashMap();

      for (Entry var5 : var1.entrySet()) {
         int var6 = (Integer)var5.getKey();
         SegmentMap var7 = (SegmentMap)var5.getValue();
         SegmentMap var8 = (SegmentMap)var2.get(var6);
         if (var7 != null && var8 != null) {
            SegmentMap var9 = new SegmentMap();
            var9.setRemoveSegmentsOnOverlap(true);

            for (IntegerSegment var11 : var7.values()) {
               for (IntegerSegment var13 : var8.values()) {
                  if (var11.getBegin() < var13.getEnd() && var13.getBegin() < var11.getEnd()) {
                     int var14 = Math.max(var11.getBegin(), var13.getBegin());
                     int var15 = Math.min(var11.getEnd(), var13.getEnd());
                     var9.put((int)var14, (ISegment)(new IntegerSegment(var14, var15 - var14)));
                  }
               }
            }

            if (!var9.isEmpty()) {
               var3.put(var6, var9);
            }
         }
      }

      return var3;
   }

   public boolean hasIntersection(IdRanges var1) {
      return this.intersection(var1) != null;
   }

   @Override
   public int hashCode() {
      int var1 = 1;
      var1 = 31 * var1 + (this.fullVars == null ? 0 : this.fullVars.hashCode());
      return 31 * var1 + (this.rangesFull == null ? 0 : this.rangesFull.hashCode());
   }

   @Override
   public boolean equals(Object var1) {
      if (this == var1) {
         return true;
      } else if (var1 == null) {
         return false;
      } else if (this.getClass() != var1.getClass()) {
         return false;
      } else {
         IdRanges var2 = (IdRanges)var1;
         if (this.fullVars == null) {
            if (var2.fullVars != null) {
               return false;
            }
         } else if (!this.fullVars.equals(var2.fullVars)) {
            return false;
         }

         if (this.rangesFull == null) {
            if (var2.rangesFull != null) {
               return false;
            }
         } else if (!this.rangesFull.equals(var2.rangesFull)) {
            return false;
         }

         return true;
      }
   }

   @Override
   public String toString() {
      return this.fullVars.keySet().toString();
   }
}
