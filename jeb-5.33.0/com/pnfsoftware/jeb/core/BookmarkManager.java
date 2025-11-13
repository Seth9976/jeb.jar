package com.pnfsoftware.jeb.core;

import com.pnfsoftware.jeb.core.events.J;
import com.pnfsoftware.jeb.core.events.JebEvent;
import com.pnfsoftware.jeb.core.units.IAddressableUnit;
import com.pnfsoftware.jeb.core.units.IInteractiveUnit;
import com.pnfsoftware.jeb.core.units.IUnit;
import com.pnfsoftware.jeb.core.units.impl.AbstractCommentManager;
import com.pnfsoftware.jeb.core.units.impl.MetaComment;
import com.pnfsoftware.jeb.util.collect.WeakIdentityHashMap;
import com.pnfsoftware.jeb.util.events.IEvent;
import com.pnfsoftware.jeb.util.events.IEventListener;
import com.pnfsoftware.jeb.util.format.Strings;
import com.pnfsoftware.jeb.util.logging.GlobalLog;
import com.pnfsoftware.jeb.util.logging.ILogger;
import com.pnfsoftware.jeb.util.serialization.annotations.Ser;
import com.pnfsoftware.jeb.util.serialization.annotations.SerId;
import com.pnfsoftware.jeb.util.serialization.annotations.SerTransient;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

@Ser
public class BookmarkManager implements IEventListener {
   private static final ILogger logger = GlobalLog.getLogger(BookmarkManager.class);
   @SerId(1)
   private WeakIdentityHashMap map = new WeakIdentityHashMap();
   @SerTransient
   private volatile boolean cleanedUp;

   @Override
   public void onEvent(IEvent var1) {
      if (var1 instanceof JebEvent && var1.getType() == J.UnitDestroyed && var1.getData() instanceof IUnit) {
         IUnit var2 = (IUnit)var1.getData();
         Object[] var10000 = new Object[]{var2};
         this.remove(var2);
      }
   }

   public void set(IUnit var1, String var2, String var3) {
      this.set(var1, var2, var3, false);
   }

   public void set(IUnit var1, String var2, String var3, boolean var4) {
      this.cleanupDanglingRefs();
      if (var1 == null) {
         throw new IllegalArgumentException();
      } else {
         if (var1 instanceof IAddressableUnit) {
            var2 = ((IAddressableUnit)var1).getCanonicalAddress(var2);
         }

         if (var3 == null) {
            var3 = "";
         }

         boolean var5 = false;
         synchronized (this.map) {
            Object var7 = (Collection)this.map.get(var1);
            if (var7 == null) {
               var7 = new ArrayList();
               this.map.put(var1, var7);
            }

            for (BookmarkManager.Bookmark var9 : var7) {
               if (Strings.equals(var2, var9.address)) {
                  var9.description = var3;
                  var5 = true;
               }
            }

            if (!var5) {
               BookmarkManager.Bookmark var14 = new BookmarkManager.Bookmark(var1, var2, var3);
               var7.add(var14);
            }
         }

         if (!var4
            && var2 != null
            && var1 instanceof IInteractiveUnit
            && !((IInteractiveUnit)var1).isDisposed()
            && ((IInteractiveUnit)var1).getCommentManager() != null) {
            AbstractCommentManager var12 = ((IInteractiveUnit)var1).getCommentManager();
            if (var5) {
               for (MetaComment var16 : var12.getMetaComments(var2, 4096, 0)) {
                  var12.removeMetaComment(var2, var16, false);
               }
            }

            var12.addMetaComment(var2, new MetaComment(var3, 4096), true);
         }
      }
   }

   public String get(IUnit var1, String var2) {
      this.cleanupDanglingRefs();
      if (var1 == null) {
         throw new IllegalArgumentException();
      } else {
         if (var1 instanceof IAddressableUnit) {
            var2 = ((IAddressableUnit)var1).getCanonicalAddress(var2);
         }

         Collection var3;
         synchronized (this.map) {
            var3 = (Collection)this.map.get(var1);
         }

         if (var3 == null) {
            return null;
         } else {
            for (BookmarkManager.Bookmark var5 : var3) {
               if (Strings.equals(var2, var5.address)) {
                  return var5.description;
               }
            }

            return null;
         }
      }
   }

   public Collection get(IUnit var1) {
      this.cleanupDanglingRefs();
      if (var1 == null) {
         throw new IllegalArgumentException();
      } else {
         Collection var2;
         synchronized (this.map) {
            var2 = (Collection)this.map.get(var1);
         }

         return (Collection)(var2 == null ? Collections.emptyList() : var2);
      }
   }

   public List getAll() {
      this.cleanupDanglingRefs();
      ArrayList var1 = new ArrayList();
      synchronized (this.map) {
         for (Collection var4 : this.map.values()) {
            var1.addAll(var4);
         }

         return var1;
      }
   }

   public boolean remove(IUnit var1, String var2) {
      this.cleanupDanglingRefs();
      if (var1 == null) {
         throw new IllegalArgumentException();
      } else {
         if (var1 instanceof IAddressableUnit) {
            var2 = ((IAddressableUnit)var1).getCanonicalAddress(var2);
         }

         String var3;
         synchronized (this.map) {
            Collection var5 = (Collection)this.map.get(var1);
            if (var5 == null) {
               return false;
            }

            BookmarkManager.Bookmark var6 = null;

            for (BookmarkManager.Bookmark var8 : var5) {
               if (Strings.equals(var2, var8.address)) {
                  var6 = var8;
                  break;
               }
            }

            if (var6 == null) {
               return false;
            }

            var3 = var6.description;
            var5.remove(var6);
            if (var5.isEmpty()) {
               this.map.remove(var1);
            }
         }

         if (var2 != null && var1 instanceof IInteractiveUnit && ((IInteractiveUnit)var1).getCommentManager() != null) {
            ((IInteractiveUnit)var1).getCommentManager().removeMetaComment(var2, new MetaComment(var3, 4096), true);
         }

         return true;
      }
   }

   public boolean remove(IUnit var1) {
      this.cleanupDanglingRefs();
      if (var1 == null) {
         throw new IllegalArgumentException();
      } else {
         synchronized (this.map) {
            Collection var3 = (Collection)this.map.get(var1);
            if (var3 == null) {
               return false;
            }

            for (BookmarkManager.Bookmark var5 : new ArrayList(var3)) {
               this.remove(var1, var5.address);
            }
         }

         this.map.remove(var1);
         return true;
      }
   }

   private void cleanupDanglingRefs() {
      if (!this.cleanedUp) {
         synchronized (this.map) {
            if (!this.cleanedUp) {
               this.cleanedUp = true;

               try {
                  ArrayList var2 = new ArrayList();

                  for (IUnit var4 : this.map.keySet()) {
                     if (var4.isDisposed() || var4.getPropertyManager() == null) {
                        var2.add(var4);
                     }
                  }

                  for (IUnit var9 : var2) {
                     this.remove(var9);
                  }
               } catch (Exception var6) {
               }
            }
         }
      }
   }

   @Ser
   public static class Bookmark {
      @SerId(1)
      private IUnit unit;
      @SerId(2)
      private String address;
      @SerId(3)
      private String description;

      public Bookmark(IUnit var1, String var2, String var3) {
         if (var1 == null) {
            throw new IllegalArgumentException("Null unit");
         } else {
            this.unit = var1;
            this.address = var2;
            this.description = var3;
         }
      }

      public IUnit getUnit() {
         return this.unit;
      }

      public String getAddress() {
         return this.address;
      }

      public String getDescription() {
         return this.description;
      }

      @Override
      public String toString() {
         return Strings.ff("%s:%s:%s", this.unit.getName(), this.address, this.description);
      }
   }
}
