package com.pnfsoftware.jeb.util.events;

import com.pnfsoftware.jeb.util.logging.GlobalLog;
import com.pnfsoftware.jeb.util.logging.ILogger;
import com.pnfsoftware.jeb.util.serialization.annotations.Ser;
import com.pnfsoftware.jeb.util.serialization.annotations.SerCustomInit;
import com.pnfsoftware.jeb.util.serialization.annotations.SerTransient;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

@Ser
public class EventSource implements IEventSource {
   private static final ILogger logger = GlobalLog.getLogger(EventSource.class);
   @SerTransient
   private List listeners;
   @SerTransient
   private volatile IEventSource parentSource;

   @SerCustomInit
   private void init() {
      this.listeners = new CopyOnWriteArrayList();
   }

   public EventSource() {
      this(null);
   }

   public EventSource(EventSource var1) {
      this.parentSource = var1;
      this.init();
   }

   @Override
   public void setParentSource(IEventSource var1) {
      this.parentSource = var1;
   }

   @Override
   public IEventSource getParentSource() {
      return this.parentSource;
   }

   @Override
   public int countListeners() {
      return this.listeners.size();
   }

   @Override
   public List getListeners() {
      return Collections.unmodifiableList(this.listeners);
   }

   @Override
   public void addListener(IEventListener var1) {
      if (var1 != null) {
         this.listeners.add(var1);
      }
   }

   @Override
   public void insertListener(int var1, IEventListener var2) {
      if (var2 != null) {
         this.listeners.add(var1, var2);
      }
   }

   @Override
   public void removeListener(IEventListener var1) {
      if (var1 != null) {
         this.listeners.remove(var1);
      }
   }

   @Override
   public void notifyListeners(IEvent var1) {
      if (!(var1 instanceof Event)) {
         for (IEventListener var3 : this.listeners) {
            var3.onEvent(var1);
         }
      } else {
         this.notifyListeners((Event)var1);
      }
   }

   public void notifyListeners(Event var1) {
      this.notifyListeners(var1, true);
   }

   public void notifyListeners(Event var1, boolean var2) {
      if (var1.source == null) {
         var1.source = this;
      }

      if (var2
         && this.parentSource != null
         && this.parentSource != this
         && var1.source != this.parentSource
         && !var1.notifiedParents.contains(this.parentSource)) {
         var1.notifiedParents.add(this.parentSource);
         this.parentSource.notifyListeners(var1);
      }

      for (IEventListener var4 : this.listeners) {
         if (var1.source != var4 && !var1.notifiedListeners.contains(var4)) {
            var1.notifiedListeners.add(var4);
            var4.onEvent(var1);
            if (var1.stopPropagation) {
               break;
            }
         }
      }
   }

   public static IEventListener relay(IEventSource var0, IEventSource var1) {
      if (var0 != null && var1 != null) {
         EventSource$1 var2 = new EventSource$1(var1);
         var0.addListener(var2);
         return var2;
      } else {
         throw new RuntimeException("Event source is null");
      }
   }
}
