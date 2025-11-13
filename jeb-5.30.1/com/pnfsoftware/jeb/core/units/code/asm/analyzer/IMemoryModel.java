package com.pnfsoftware.jeb.core.units.code.asm.analyzer;

import com.pnfsoftware.jeb.core.units.IUnitLock;
import com.pnfsoftware.jeb.core.units.code.asm.items.INativeContinuousItem;
import com.pnfsoftware.jeb.util.collect.ISegmentFactory;
import com.pnfsoftware.jeb.util.serialization.annotations.Ser;
import java.util.SortedMap;
import java.util.function.Predicate;

@Ser
public interface IMemoryModel {
   IUnitLock getLock();

   void addListener(IMemoryModelListener var1);

   void removeListener(IMemoryModelListener var1);

   void notifyListenersOfModelChange(MemoryModelEventType var1, Object var2);

   boolean isEmpty();

   int size();

   INativeContinuousItem getFirstItem();

   INativeContinuousItem getLastItem();

   SortedMap getView();

   SortedMap getView(Long var1, Long var2);

   INativeContinuousItem getItemAt(long var1);

   INativeContinuousItem getItemOver(long var1);

   INativeContinuousItem getNextItem(long var1);

   default INativeContinuousItem getNextItem(INativeContinuousItem var1) {
      return var1 == null ? null : this.getNextItem(var1.getMemoryAddress());
   }

   default INativeContinuousItem getPreviousItem(INativeContinuousItem var1) {
      return var1 == null ? null : this.getPreviousItem(var1.getMemoryAddress());
   }

   INativeContinuousItem getPreviousItem(long var1);

   INativeContinuousItem getItemOverOrGap(long var1, long var3, long var5);

   SortedMap getContinuousItemsInRange(long var1, long var3);

   SortedMap getItemsInRange(long var1, boolean var3, long var4, boolean var6);

   SortedMap getItemsInRange(long var1, boolean var3, long var4, boolean var6, Predicate var7);

   boolean isEmptyRange(long var1, int var3);

   ISegmentFactory getGapFactory();

   ILabelManager getLabelManager();

   ICommentManager getCommentManager();

   int getBitsize();
}
