package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.exceptions.JebRuntimeException;
import com.pnfsoftware.jeb.core.units.IUnitLock;
import com.pnfsoftware.jeb.core.units.code.asm.analyzer.IMemoryModel;
import com.pnfsoftware.jeb.core.units.code.asm.analyzer.IMemoryModelListener;
import com.pnfsoftware.jeb.core.units.code.asm.analyzer.MemoryModelEvent;
import com.pnfsoftware.jeb.core.units.code.asm.analyzer.MemoryModelEventType;
import com.pnfsoftware.jeb.core.units.code.asm.items.INativeContinuousItem;
import com.pnfsoftware.jeb.util.base.Assert;
import com.pnfsoftware.jeb.util.collect.AddressSegmentMap;
import com.pnfsoftware.jeb.util.collect.SegmentMap;
import com.pnfsoftware.jeb.util.concurrent.ACLock;
import com.pnfsoftware.jeb.util.serialization.annotations.Ser;
import com.pnfsoftware.jeb.util.serialization.annotations.SerCustomInit;
import com.pnfsoftware.jeb.util.serialization.annotations.SerId;
import com.pnfsoftware.jeb.util.serialization.annotations.SerTransient;
import java.util.Collections;
import java.util.List;
import java.util.Set;
import java.util.SortedMap;
import java.util.Map.Entry;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.function.Predicate;

@Ser
public abstract class ZH implements IMemoryModel {
   @SerId(1)
   private SegmentMap A;
   @SerId(2)
   private IMemoryModelListener kS;
   @SerId(3)
   protected IUnitLock pC;
   @SerId(4)
   private int wS;
   @SerTransient
   private List UT;
   @SerTransient
   private AtomicBoolean E;

   @SerCustomInit
   private void wS() {
      this.UT = new CopyOnWriteArrayList();
      this.E = new AtomicBoolean(true);
   }

   public ZH(int var1, boolean var2, IMemoryModelListener var3, IUnitLock var4) {
      if (var1 > 0 && var4 != null) {
         this.wS = var1;
         this.kS = var3;
         this.pC = var4;
         this.A = (SegmentMap)(var2 ? new SegmentMap() : new AddressSegmentMap(var1));
         this.wS();
      } else {
         throw new NullPointerException();
      }
   }

   public IMemoryModelListener pC() {
      return this.kS;
   }

   @Override
   public IUnitLock getLock() {
      return this.pC;
   }

   @Override
   public int getBitsize() {
      return this.wS;
   }

   @Override
   public void addListener(IMemoryModelListener var1) {
      if (var1 != null) {
         this.UT.add(var1);
      }
   }

   @Override
   public void removeListener(IMemoryModelListener var1) {
      if (var1 != null) {
         this.UT.remove(var1);
      }
   }

   public void pC(MemoryModelEvent var1) {
      if (this.E.get()) {
         if (this.kS != null) {
            this.kS.onModelUpdate(var1);
         }

         for (IMemoryModelListener var3 : this.UT) {
            var3.onModelUpdate(var1);
         }
      }
   }

   @Override
   public void notifyListenersOfModelChange(MemoryModelEventType var1, Object var2) {
      MemoryModelEvent var3 = new MemoryModelEvent(var1, this, var2);
      this.pC(var3);
   }

   public boolean pC(boolean var1) {
      return this.E.getAndSet(var1);
   }

   @Override
   public boolean isEmpty() {
      return this.A.isEmpty();
   }

   @Override
   public int size() {
      return this.A.size();
   }

   public void pC(aul var1) {
      try (ACLock var2 = this.pC.a()) {
         synchronized (this) {
            this.A.add(var1);
         }

         var1.pC(this.pC);
         var1.pC(this);
         this.notifyListenersOfModelChange(MemoryModelEventType.ITEM_ADDED, var1);
      }
   }

   public void pC(INativeContinuousItem var1) {
      try (ACLock var2 = this.pC.a()) {
         synchronized (this) {
            auo var4 = (auo)this.A.remove(var1.getBegin());
            Assert.a(var4 == var1);
         }

         this.notifyListenersOfModelChange(MemoryModelEventType.ITEM_REMOVED, var1);
      }
   }

   public void pC(long var1) {
   }

   public void pC(long var1, boolean var3) {
   }

   @Override
   public INativeContinuousItem getFirstItem() {
      Entry var1 = this.A.firstEntry();
      return var1 == null ? null : (INativeContinuousItem)var1.getValue();
   }

   @Override
   public INativeContinuousItem getLastItem() {
      Entry var1 = this.A.lastEntry();
      return var1 == null ? null : (INativeContinuousItem)var1.getValue();
   }

   @Override
   public SortedMap getView() {
      return Collections.unmodifiableSortedMap(this.A);
   }

   @Override
   public SortedMap getView(Long var1, Long var2) {
      Object var3;
      if (var1 == null && var2 != null) {
         var3 = this.A.headMap(var2);
      } else if (var1 != null && var2 == null) {
         var3 = this.A.tailMap(var1);
      } else if (var1 != null && var2 != null) {
         var3 = this.A.subMap(var1, var2);
      } else {
         var3 = this.A;
      }

      return Collections.unmodifiableSortedMap((SortedMap)var3);
   }

   @Override
   public INativeContinuousItem getItemAt(long var1) {
      return (INativeContinuousItem)this.A.get(var1);
   }

   @Override
   public INativeContinuousItem getItemOver(long var1) {
      return (INativeContinuousItem)this.A.getSegmentContaining(var1);
   }

   @Override
   public INativeContinuousItem getNextItem(long var1) {
      Entry var3 = this.A.ceilingEntry(var1 + 1L);
      return var3 == null ? null : (INativeContinuousItem)var3.getValue();
   }

   @Override
   public INativeContinuousItem getPreviousItem(long var1) {
      INativeContinuousItem var3 = (INativeContinuousItem)this.A.getSegmentContaining(var1);
      if (var3 != null) {
         var1 = var3.getMemoryAddress();
      }

      Entry var4 = this.A.floorEntry(var1 - 1L);
      return var4 == null ? null : (INativeContinuousItem)var4.getValue();
   }

   @Override
   public INativeContinuousItem getItemOverOrGap(long var1, long var3, long var5) {
      INativeContinuousItem var7 = (INativeContinuousItem)this.A.getSegmentContaining(var1);
      if (var7 != null) {
         return var7;
      } else if (this.A.compareKeys(var1, var3) >= 0 && this.A.compareKeys(var1, var5) < 0 && this.A.compareKeys(var3, var5) <= 0) {
         synchronized (this) {
            Long var9 = (Long)this.A.floorKey(var1);
            if (var9 == null) {
               var9 = var3;
            } else {
               INativeContinuousItem var10 = (INativeContinuousItem)this.A.get(var9);
               if (var10 == null) {
                  throw new JebRuntimeException("Memory model modified while looking for item over gap");
               }

               long var11 = (Long)var10.getEnd();
               var9 = this.A.compareKeys(var3, var11) > 0 ? var3 : var11;
            }

            Long var16 = (Long)this.A.ceilingKey(var1);
            if (var16 == null || this.A.compareKeys(var16, var5) > 0) {
               var16 = var5;
            }

            return (INativeContinuousItem)this.getGapFactory().create(var9, var16);
         }
      } else {
         return null;
      }
   }

   @Override
   public SortedMap getContinuousItemsInRange(long var1, long var3) {
      return this.A.contiguousSubMap(var1, var3, this.getGapFactory());
   }

   @Override
   public SortedMap getItemsInRange(long var1, boolean var3, long var4, boolean var6) {
      return this.A.getOverlappingSegmentsMap(var1, var3, var4, var6);
   }

   @Override
   public SortedMap getItemsInRange(long var1, boolean var3, long var4, boolean var6, Predicate var7) {
      return this.A.getOverlappingSegmentsMap(var1, var3, var4, var6, var1x -> var7.test((INativeContinuousItem)var1x.getValue()));
   }

   @Override
   public boolean isEmptyRange(long var1, int var3) {
      return this.A.isEmptyRange(var1, var1 + (var3 & 4294967295L));
   }

   protected Set A(boolean var1) {
      return var1 ? this.A.descendingKeySet() : this.A.keySet();
   }

   protected void A() {
      for (Entry var2 : this.A.entrySet()) {
         ((INativeContinuousItem)var2.getValue()).dispose(false);
      }
   }

   protected void kS() {
      this.A = null;
      this.kS = null;
      this.pC = null;
   }
}
