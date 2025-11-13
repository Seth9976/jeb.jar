package com.pnfsoftware.jeb.core.events;

import com.pnfsoftware.jeb.util.events.IEvent;
import com.pnfsoftware.jeb.util.events.IEventSource;
import com.pnfsoftware.jeb.util.format.Strings;
import java.util.ArrayList;
import java.util.List;

public class JebEvent implements IEvent {
   private final J type;
   private final Object data;
   private IEventSource source;
   private final long timestamp = System.currentTimeMillis();
   private boolean stopPropagation;
   List notifiedListeners = new ArrayList();
   List notifiedParents = new ArrayList();

   public JebEvent(J var1) {
      this(var1, null, null);
   }

   public JebEvent(J var1, Object var2) {
      this(var1, var2, null);
   }

   public JebEvent(J var1, Object var2, IEventSource var3) {
      this.type = var1;
      this.data = var2;
      this.source = var3;
   }

   public J getType() {
      return this.type;
   }

   @Override
   public Object getData() {
      return this.data;
   }

   void setSource(IEventSource var1) {
      this.source = var1;
   }

   @Override
   public IEventSource getSource() {
      return this.source;
   }

   @Override
   public long getTimestamp() {
      return this.timestamp;
   }

   @Override
   public boolean shouldStopPropagation() {
      return this.stopPropagation;
   }

   public void setStopPropagation(boolean var1) {
      this.stopPropagation = var1;
   }

   @Override
   public String toString() {
      return Strings.ff("type=%s,data=%s,from=%s", this.type, this.data, this.source);
   }

   @Override
   public int hashCode() {
      int var1 = 1;
      var1 = 31 * var1 + (this.data == null ? 0 : this.data.hashCode());
      var1 = 31 * var1 + (this.source == null ? 0 : this.source.hashCode());
      return 31 * var1 + (this.type == null ? 0 : this.type.hashCode());
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
         JebEvent var2 = (JebEvent)var1;
         if (this.data == null) {
            if (var2.data != null) {
               return false;
            }
         } else if (this.data != var2.data) {
            return false;
         }

         if (this.source == null) {
            if (var2.source != null) {
               return false;
            }
         } else if (this.source != var2.source) {
            return false;
         }

         return this.type == var2.type;
      }
   }
}
