package com.pnfsoftware.jeb.util.collect;

import com.pnfsoftware.jeb.util.events.Event;
import com.pnfsoftware.jeb.util.events.EventSource;
import com.pnfsoftware.jeb.util.format.Strings;
import com.pnfsoftware.jeb.util.logging.GlobalLog;
import com.pnfsoftware.jeb.util.logging.ILogger;
import com.pnfsoftware.jeb.util.serialization.annotations.SerDisabled;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@SerDisabled
public class ItemHistory extends EventSource {
   private static final ILogger logger = GlobalLog.getLogger(ItemHistory.class, Integer.MAX_VALUE);
   int capacity;
   boolean cleanHistoryAfterIndex;
   private List history = new ArrayList();
   private int index = 0;

   public ItemHistory() {
      this(100, true);
   }

   public ItemHistory(int var1, boolean var2) {
      this.capacity(var1);
      this.cleanHistoryAfterIndex = var2;
   }

   public void reset() {
      this.history.clear();
      this.index = 0;
      this.notifyListeners(new Event());
   }

   public int size() {
      return this.history.size();
   }

   public int capacity() {
      return this.capacity;
   }

   public void capacity(int var1) {
      if (var1 <= 0) {
         throw new IllegalArgumentException("Invalid capacity: " + var1);
      } else {
         this.capacity = var1;
         this.trim();
      }
   }

   private void trim() {
      if (this.history.size() > this.capacity) {
         int var1;
         for (var1 = 0; this.history.size() > this.capacity; var1++) {
            this.history.remove(0);
         }

         this.index = Math.max(0, this.index - var1);
         this.notifyListeners(new Event());
      }
   }

   public int position() {
      return this.index;
   }

   public void add(Object var1) {
      if (this.cleanHistoryAfterIndex) {
         while (this.index < this.history.size()) {
            this.history.remove(this.index);
         }
      }

      this.history.add(var1);
      this.index = this.history.size();
      this.notifyListeners(new Event());
      this.trim();
   }

   public void addAll(Collection var1) {
      if (this.cleanHistoryAfterIndex) {
         while (this.index < this.history.size()) {
            this.history.remove(this.index);
         }
      }

      this.history.addAll(var1);
      this.index = this.history.size();
      this.notifyListeners(new Event());
      this.trim();
   }

   public boolean remove(Object var1) {
      for (int var2 = this.history.size() - 1; var2 >= 0; var2--) {
         if (this.history.get(var2).equals(var1)) {
            this.history.remove(var2);
            if (var2 < this.index) {
               this.index--;
            }

            this.notifyListeners(new Event());
            return true;
         }
      }

      return false;
   }

   public Object getMostRecent() {
      return this.history.isEmpty() ? null : this.history.get(this.history.size() - 1);
   }

   public List get() {
      return new ArrayList(this.history);
   }

   public Object peekPrevious() {
      return this.index == 0 ? null : this.history.get(this.index - 1);
   }

   public Object peekNext() {
      return this.index == this.history.size() ? null : this.history.get(this.index);
   }

   public Object getPrevious() {
      return this.getPrevious(null);
   }

   public Object getPrevious(Object var1) {
      if (this.index == 0) {
         return null;
      } else {
         this.index--;
         Object var2 = this.history.get(this.index);
         if (var1 != null) {
            this.history.set(this.index, var1);
         }

         this.notifyListeners(new Event());
         return var2;
      }
   }

   public Object getAt(int var1, Object var2) {
      if (this.index >= 0 && var1 <= this.history.size() && this.index != var1) {
         this.index = var1;
         Object var3 = null;
         if (this.index != this.history.size()) {
            var3 = this.history.get(this.index);
         }

         if (var2 != null) {
            this.history.set(this.index, var2);
         }

         this.notifyListeners(new Event());
         return var3;
      } else {
         return null;
      }
   }

   public Object getNext() {
      return this.getNext(null);
   }

   public Object getNext(Object var1) {
      if (this.index == this.history.size()) {
         return null;
      } else {
         Object var2 = this.history.get(this.index);
         if (var1 != null) {
            this.history.set(this.index, var1);
         }

         this.index++;
         this.notifyListeners(new Event());
         return var2;
      }
   }

   public boolean hasPrevious() {
      return this.index > 0;
   }

   public boolean hasNext() {
      return this.index < this.history.size();
   }

   @Override
   public String toString() {
      StringBuilder var1 = new StringBuilder();

      for (int var2 = 0; var2 < this.history.size(); var2++) {
         String var3 = var2 == this.position() ? "->" : "  ";
         Strings.ff(var1, "%s %s\n", var3, this.history.get(var2));
      }

      if (this.position() == this.history.size()) {
         var1.append("->\n");
      }

      return var1.toString();
   }
}
