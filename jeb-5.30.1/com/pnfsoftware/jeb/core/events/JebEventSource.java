package com.pnfsoftware.jeb.core.events;

import com.pnfsoftware.jeb.util.events.IEvent;
import com.pnfsoftware.jeb.util.events.IEventListener;
import com.pnfsoftware.jeb.util.events.IEventSource;
import com.pnfsoftware.jeb.util.logging.GlobalLog;
import com.pnfsoftware.jeb.util.logging.ILogger;
import com.pnfsoftware.jeb.util.serialization.annotations.Ser;
import com.pnfsoftware.jeb.util.serialization.annotations.SerCustomInit;
import com.pnfsoftware.jeb.util.serialization.annotations.SerTransient;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

@Ser
public class JebEventSource implements IEventSource {
   private static final ILogger logger = GlobalLog.getLogger(JebEventSource.class);
   @SerTransient
   private List listeners;
   @SerTransient
   private volatile IEventSource parentSource;

   @SerCustomInit
   private void init() {
      this.listeners = new CopyOnWriteArrayList();
   }

   public JebEventSource() {
      this(null);
   }

   public JebEventSource(IEventSource var1) {
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
      if (!(var1 instanceof JebEvent)) {
         for (IEventListener var3 : this.listeners) {
            var3.onEvent(var1);
         }
      } else {
         this.notifyListeners((JebEvent)var1);
      }
   }

   public void notifyListeners(JebEvent var1) {
      this.notifyListeners(var1, true);
   }

   public void notifyListeners(JebEvent var1, boolean var2) {
      if (var1.getSource() == null) {
         var1.setSource(this);
      }

      if (var2
         && this.parentSource != null
         && this.parentSource != this
         && var1.getSource() != this.parentSource
         && !var1.notifiedParents.contains(this.parentSource)) {
         var1.notifiedParents.add(this.parentSource);
         this.parentSource.notifyListeners(var1);
      }

      for (IEventListener var4 : this.listeners) {
         if (var1.getSource() != var4 && !var1.notifiedListeners.contains(var4)) {
            var1.notifiedListeners.add(var4);
            var4.onEvent(var1);
            if (var1.shouldStopPropagation()) {
               break;
            }
         }
      }
   }
}
