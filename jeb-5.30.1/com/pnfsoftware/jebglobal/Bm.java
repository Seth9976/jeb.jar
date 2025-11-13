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
public abstract class Bm implements IMemoryModel {
   @SerId(1)
   private SegmentMap RF;
   @SerId(2)
   private IMemoryModelListener xK;
   @SerId(3)
   protected IUnitLock q;
   @SerId(4)
   private int Dw;
   @SerTransient
   private List Uv;
   @SerTransient
   private AtomicBoolean oW;

   @SerCustomInit
   private void oW() {
      this.Uv = new CopyOnWriteArrayList();
      this.oW = new AtomicBoolean(true);
   }

   public Bm(int var1, boolean var2, IMemoryModelListener var3, IUnitLock var4) {
      if (var1 > 0 && var4 != null) {
         this.Dw = var1;
         this.xK = var3;
         this.q = var4;
         this.RF = (SegmentMap)(var2 ? new SegmentMap() : new AddressSegmentMap(var1));
         this.oW();
      } else {
         throw new NullPointerException();
      }
   }

   public IMemoryModelListener q() {
      return this.xK;
   }

   @Override
   public IUnitLock getLock() {
      return this.q;
   }

   @Override
   public int getBitsize() {
      return this.Dw;
   }

   @Override
   public void addListener(IMemoryModelListener var1) {
      if (var1 != null) {
         this.Uv.add(var1);
      }
   }

   @Override
   public void removeListener(IMemoryModelListener var1) {
      if (var1 != null) {
         this.Uv.remove(var1);
      }
   }

   public void q(MemoryModelEvent var1) {
      if (this.oW.get()) {
         if (this.xK != null) {
            this.xK.onModelUpdate(var1);
         }

         for (IMemoryModelListener var3 : this.Uv) {
            var3.onModelUpdate(var1);
         }
      }
   }

   @Override
   public void notifyListenersOfModelChange(MemoryModelEventType var1, Object var2) {
      MemoryModelEvent var3 = new MemoryModelEvent(var1, this, var2);
      this.q(var3);
   }

   public boolean q(boolean var1) {
      return this.oW.getAndSet(var1);
   }

   public boolean RF() {
      return this.oW.get();
   }

   @Override
   public boolean isEmpty() {
      return this.RF.isEmpty();
   }

   @Override
   public int size() {
      return this.RF.size();
   }

   public void q(axg var1) {
      try (ACLock var2 = this.q.a()) {
         synchronized (this) {
            this.RF.add(var1);
         }

         var1.q(this.q);
         var1.q(this);
         this.notifyListenersOfModelChange(MemoryModelEventType.ITEM_ADDED, var1);
      }
   }

   public void q(INativeContinuousItem var1) {
      try (ACLock var2 = this.q.a()) {
         synchronized (this) {
            axj var4 = (axj)this.RF.remove(var1.getBegin());
            Assert.a(var4 == var1);
         }

         this.notifyListenersOfModelChange(MemoryModelEventType.ITEM_REMOVED, var1);
      }
   }

   public void q(long var1) {
   }

   public void q(long var1, boolean var3) {
   }

   @Override
   public INativeContinuousItem getFirstItem() {
      Entry var1 = this.RF.firstEntry();
      return var1 == null ? null : (INativeContinuousItem)var1.getValue();
   }

   @Override
   public INativeContinuousItem getLastItem() {
      Entry var1 = this.RF.lastEntry();
      return var1 == null ? null : (INativeContinuousItem)var1.getValue();
   }

   @Override
   public SortedMap getView() {
      return Collections.unmodifiableSortedMap(this.RF);
   }

   @Override
   public SortedMap getView(Long var1, Long var2) {
      Object var3;
      if (var1 == null && var2 != null) {
         var3 = this.RF.headMap(var2);
      } else if (var1 != null && var2 == null) {
         var3 = this.RF.tailMap(var1);
      } else if (var1 != null && var2 != null) {
         var3 = this.RF.subMap(var1, var2);
      } else {
         var3 = this.RF;
      }

      return Collections.unmodifiableSortedMap((SortedMap)var3);
   }

   @Override
   public INativeContinuousItem getItemAt(long var1) {
      return (INativeContinuousItem)this.RF.get(var1);
   }

   @Override
   public INativeContinuousItem getItemOver(long var1) {
      return (INativeContinuousItem)this.RF.getSegmentContaining(var1);
   }

   @Override
   public INativeContinuousItem getNextItem(long var1) {
      Entry var3 = this.RF.ceilingEntry(var1 + 1L);
      return var3 == null ? null : (INativeContinuousItem)var3.getValue();
   }

   @Override
   public INativeContinuousItem getPreviousItem(long var1) {
      INativeContinuousItem var3 = (INativeContinuousItem)this.RF.getSegmentContaining(var1);
      if (var3 != null) {
         var1 = var3.getMemoryAddress();
      }

      Entry var4 = this.RF.floorEntry(var1 - 1L);
      return var4 == null ? null : (INativeContinuousItem)var4.getValue();
   }

   @Override
   public INativeContinuousItem getItemOverOrGap(long var1, long var3, long var5) {
      INativeContinuousItem var7 = (INativeContinuousItem)this.RF.getSegmentContaining(var1);
      if (var7 != null) {
         return var7;
      } else if (this.RF.compareKeys(var1, var3) >= 0 && this.RF.compareKeys(var1, var5) < 0 && this.RF.compareKeys(var3, var5) <= 0) {
         synchronized (this) {
            Long var9 = (Long)this.RF.floorKey(var1);
            if (var9 == null) {
               var9 = var3;
            } else {
               INativeContinuousItem var10 = (INativeContinuousItem)this.RF.get(var9);
               if (var10 == null) {
                  throw new JebRuntimeException("Memory model modified while looking for item over gap");
               }

               long var11 = (Long)var10.getEnd();
               var9 = this.RF.compareKeys(var3, var11) > 0 ? var3 : var11;
            }

            Long var16 = (Long)this.RF.ceilingKey(var1);
            if (var16 == null || this.RF.compareKeys(var16, var5) > 0) {
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
      return this.RF.contiguousSubMap(var1, var3, this.getGapFactory());
   }

   @Override
   public SortedMap getItemsInRange(long var1, boolean var3, long var4, boolean var6) {
      return this.RF.getOverlappingSegmentsMap(var1, var3, var4, var6);
   }

   @Override
   public SortedMap getItemsInRange(long var1, boolean var3, long var4, boolean var6, Predicate var7) {
      return this.RF.getOverlappingSegmentsMap(var1, var3, var4, var6, var1x -> var7.test((INativeContinuousItem)var1x.getValue()));
   }

   @Override
   public boolean isEmptyRange(long var1, int var3) {
      return this.RF.isEmptyRange(var1, var1 + (var3 & 4294967295L));
   }

   protected Set RF(boolean var1) {
      return var1 ? this.RF.descendingKeySet() : this.RF.keySet();
   }

   protected void xK() {
      for (Entry var2 : this.RF.entrySet()) {
         ((INativeContinuousItem)var2.getValue()).dispose(false);
      }
   }

   protected void Dw() {
      this.RF = null;
      this.xK = null;
      this.q = null;
   }

   public void Uv() {
      this.xK();
      this.Dw();
   }
}
