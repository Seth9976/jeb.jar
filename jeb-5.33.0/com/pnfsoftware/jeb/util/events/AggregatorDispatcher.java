package com.pnfsoftware.jeb.util.events;

import com.pnfsoftware.jeb.util.concurrent.ThreadUtil;
import com.pnfsoftware.jeb.util.logging.GlobalLog;
import com.pnfsoftware.jeb.util.logging.ILogger;
import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

public abstract class AggregatorDispatcher implements IEventListener {
   private static final ILogger logger = GlobalLog.getLogger(AggregatorDispatcher.class, Integer.MAX_VALUE);
   private Set list = new LinkedHashSet();
   private Object lockNotify = new Object();
   private int capacity;
   private long resolution;

   public AggregatorDispatcher() {
      this(100, 250L);
   }

   public AggregatorDispatcher(int var1, long var2) {
      if (var1 > 0 && var2 >= 0L) {
         this.capacity = var1;
         this.resolution = var2;
         if (var2 != 0L) {
            ThreadUtil.start("jeb-aggregator-dispatcher", new AggregatorDispatcher$1(this));
         }
      } else {
         throw new IllegalArgumentException();
      }
   }

   private void enqueue(IEvent var1) {
      boolean var2;
      synchronized (this.list) {
         boolean var4 = this.list.add(var1);
         if (!var4) {
            return;
         }

         int var5 = this.list.size();
         if (var5 == 1) {
            this.list.notify();
         }

         var2 = var5 >= this.capacity;
      }

      if (var2) {
         this.trigger();
      }
   }

   private void trigger() {
      ArrayList var1 = null;
      synchronized (this.list) {
         if (!this.list.isEmpty()) {
            var1 = new ArrayList(this.list);
            this.list.clear();
         }
      }

      if (var1 != null) {
         synchronized (this.lockNotify) {
            Object[] var10000 = new Object[]{var1.size(), this.capacity};
            this.onMultipleEvents(var1);
         }
      }
   }

   @Override
   public void onEvent(IEvent var1) {
      this.enqueue(var1);
   }

   public int unattended() {
      synchronized (this.list) {
         return this.list.size();
      }
   }

   public int capacity() {
      return this.capacity;
   }

   public long resolution() {
      return this.resolution;
   }

   public abstract void onMultipleEvents(List var1);
}
