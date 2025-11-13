package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.units.IUnitLock;
import com.pnfsoftware.jeb.core.units.PassthroughUnitLock;
import com.pnfsoftware.jeb.core.units.code.asm.items.INativeItem;
import com.pnfsoftware.jeb.core.units.code.asm.items.INativeItemListener;
import com.pnfsoftware.jeb.core.units.code.asm.items.NativeItemEvent;
import com.pnfsoftware.jeb.core.units.code.asm.items.NativeItemEventSubType;
import com.pnfsoftware.jeb.core.units.code.asm.items.NativeItemEventType;
import com.pnfsoftware.jeb.util.concurrent.ACLock;
import com.pnfsoftware.jeb.util.logging.GlobalLog;
import com.pnfsoftware.jeb.util.logging.ILogger;
import com.pnfsoftware.jeb.util.serialization.annotations.Ser;
import com.pnfsoftware.jeb.util.serialization.annotations.SerId;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;

@Ser
public abstract class auo implements INativeItem, INativeItemListener {
   private static final ILogger pC = GlobalLog.getLogger(auo.class);
   @SerId(1)
   private long A = 0L;
   @SerId(2)
   private int kS = -1;
   @SerId(3)
   private volatile int wS;
   @SerId(4)
   protected String E;
   @SerId(5)
   protected String sY;
   @SerId(6)
   protected Map ys;
   @SerId(7)
   private boolean UT;
   @SerId(8)
   private volatile List ld;
   @SerId(9)
   private volatile boolean gp;
   @SerId(10)
   private volatile int oT;
   @SerId(11)
   private IUnitLock fI;

   public auo(int var1, String var2) {
      this.wS = var1 | 256 | 1;
      this.E = var2;
   }

   public final void pC(IUnitLock var1) {
      this.vP();
      if (var1 == null) {
         throw new NullPointerException("The lock cannot be null");
      } else if (this.fI != null) {
         throw new IllegalStateException("Once set, the lock cannot be reset");
      } else {
         this.fI = var1;
      }
   }

   public final IUnitLock gp() {
      return (IUnitLock)(this.fI != null ? this.fI : PassthroughUnitLock.getInstance());
   }

   public final void pC(long var1) {
      this.vP();
      this.A = var1;
   }

   @Override
   public final long getItemId() {
      return this.A;
   }

   public final void wS(int var1) {
      this.vP();
      this.kS = var1;
   }

   @Override
   public final int getIndex() {
      return this.kS;
   }

   @Override
   public final String getAddress() {
      return this.getAddress(true);
   }

   @Override
   public String getAddress(boolean var1) {
      return null;
   }

   @Override
   public final String getSignature() {
      return this.getSignature(true);
   }

   @Override
   public String getSignature(boolean var1) {
      return this.getName(var1);
   }

   @Override
   public final String getSignature(boolean var1, boolean var2) {
      return this.getSignature(var1);
   }

   @Override
   public String getName(boolean var1) {
      if (!var1) {
         return this.E;
      } else {
         return this.sY != null ? this.sY : this.E;
      }
   }

   @Override
   public final String getName() {
      return this.getName(true);
   }

   @Override
   public void setName(String var1) {
      this.vP();

      try (ACLock var2 = this.gp().a()) {
         if (var1 != null && var1.length() != 0) {
            if (this.E != null && this.E.equals(var1)) {
               return;
            }

            if (this.sY != null && this.sY.equals(var1)) {
               return;
            }
         } else {
            if (this.sY == null) {
               return;
            }

            var1 = null;
         }

         String var3 = this.sY;
         this.sY = var1;
         this.pC(new NativeItemEvent(NativeItemEventType.UPDATED, this, NativeItemEventSubType.RENAMED, var3));
      }
   }

   @Override
   public final boolean isRenamed() {
      return this.sY != null && !this.sY.equals(this.E);
   }

   @Override
   public final boolean isInternal() {
      return (this.wS & -2147483648) != 0;
   }

   @Override
   public final boolean isArtificial() {
      return (this.wS & 1073741824) != 0;
   }

   @Override
   public final int getGenericFlags() {
      return this.wS;
   }

   @Override
   public boolean hasFlag(int var1) {
      return (this.wS & var1) != 0;
   }

   @Override
   public final void setFlags(int var1) {
      this.pC(var1, true);
   }

   public final void pC(int var1, boolean var2) {
      this.vP();
      if (var1 != this.wS) {
         try (ACLock var3 = this.gp().a()) {
            this.wS = var1;
            this.wS(var2);
         }
      }
   }

   @Override
   public final void addFlags(int var1) {
      this.A(var1, true);
   }

   public final void A(int var1, boolean var2) {
      this.vP();

      try (ACLock var3 = this.gp().a()) {
         int var4 = this.wS | var1;
         if (var4 == this.wS) {
            return;
         }

         this.wS = var4;
         this.wS(var2);
      }
   }

   @Override
   public final void removeFlags(int var1) {
      this.kS(var1, true);
   }

   public final void kS(int var1, boolean var2) {
      this.vP();

      try (ACLock var3 = this.gp().a()) {
         int var4 = this.wS & ~var1;
         if (var4 == this.wS) {
            return;
         }

         this.wS = var4;
         this.wS(var2);
      }
   }

   public void UT(int var1) {
      this.oT = var1;
   }

   public boolean oT() {
      return (this.oT & 1) == 0;
   }

   public boolean fI() {
      return (this.oT & 2) == 0;
   }

   @Override
   public synchronized boolean setAttribute(String var1, Object var2) {
      if (var2 == null) {
         return this.removeAttribute(var1);
      } else {
         this.vP();
         if (var1 == null) {
            throw new IllegalArgumentException("An attribute must have a non-null name");
         } else {
            try (ACLock var3 = this.gp().a()) {
               if (this.ys == null) {
                  this.ys = new ConcurrentHashMap();
               }

               Object var4 = this.ys.put(var1, var2);
               if (var2 != var4) {
                  this.pC(new NativeItemEvent(NativeItemEventType.UPDATED, this, NativeItemEventSubType.ATTRIBUTE_SET, var1));
               }
            }

            return true;
         }
      }
   }

   @Override
   public synchronized boolean removeAttribute(String var1) {
      this.vP();
      if (this.ys == null) {
         return false;
      } else {
         boolean var4;
         try (ACLock var2 = this.gp().a()) {
            boolean var3 = this.ys.remove(var1) != null;
            if (var3) {
               this.pC(new NativeItemEvent(NativeItemEventType.UPDATED, this, NativeItemEventSubType.ATTRIBUTE_REMOVED, var1));
            }

            var4 = var3;
         }

         return var4;
      }
   }

   @Override
   public synchronized boolean hasAttribute(String var1) {
      return this.ys == null ? false : this.ys.containsKey(var1);
   }

   @Override
   public boolean hasTrueAttribute(String var1) {
      if (this.ys == null) {
         return false;
      } else {
         Object var2 = this.ys.get(var1);
         return var2 instanceof Boolean ? (Boolean)var2 : false;
      }
   }

   @Override
   public synchronized Object getAttribute(String var1, Class var2) {
      if (this.ys == null) {
         return null;
      } else {
         Object var3 = this.ys.get(var1);
         return var2.isInstance(var3) ? var3 : null;
      }
   }

   @Override
   public synchronized Map getAttributes() {
      return this.ys == null ? Collections.emptyMap() : Collections.unmodifiableMap(this.ys);
   }

   @Override
   public void setAutoGenerated(boolean var1) {
      this.setAttribute("AutoGenerated", var1);
   }

   @Override
   public boolean isAutoGenerated() {
      Boolean var1 = (Boolean)this.getAttribute("AutoGenerated", Boolean.class);
      return var1 == null ? false : var1;
   }

   @Override
   public void onNativeItemEvent(NativeItemEvent var1) {
      this.pC(var1);
   }

   protected void WR() {
      this.gp = false;
   }

   protected void NS() {
      this.gp = true;
   }

   public boolean kS(boolean var1) {
      if (this.gp == var1) {
         return var1;
      } else {
         boolean var3;
         try (ACLock var2 = this.gp().a()) {
            this.gp = var1;
            var3 = !var1;
         }

         return var3;
      }
   }

   protected void vP() {
      if (this.isDisposed()) {
         throw new IllegalStateException("Item is disposed");
      }
   }

   public final void xC() {
      this.dispose(true);
   }

   @Override
   public final void dispose(boolean var1) {
      if (!this.UT) {
         if (!this.ED()) {
            pC.error("Cannot dispose item: %s", this.toString());
         } else {
            try (ACLock var2 = this.gp().a()) {
               this.A();
               this.UT = true;
               this.c_();
               if (var1) {
                  this.pC(new NativeItemEvent(NativeItemEventType.DISPOSED, this));
               }
            }
         }
      }
   }

   protected boolean ED() {
      return true;
   }

   protected void A() {
      this.gp().verifyLocked();
   }

   protected void c_() {
      this.gp().verifyLocked();
   }

   @Override
   public boolean isDisposed() {
      return this.UT;
   }

   @Override
   public void addListener(INativeItemListener var1) {
      if (var1 != null) {
         if (this.ld == null) {
            synchronized (this) {
               if (this.ld == null) {
                  this.ld = new CopyOnWriteArrayList();
               }
            }
         }

         this.ld.add(var1);
      }
   }

   @Override
   public void removeListener(INativeItemListener var1) {
      if (var1 != null) {
         if (this.ld != null) {
            this.ld.remove(var1);
         }
      }
   }

   public List Sc() {
      return this.ld == null ? Collections.emptyList() : this.ld;
   }

   public void pC(NativeItemEvent var1) {
      if (!this.gp) {
         if (this.ld != null) {
            for (INativeItemListener var3 : this.ld) {
               if (var3 != var1.getItem()) {
                  var3.onNativeItemEvent(var1);
               }
            }
         }
      }
   }

   public final void pC(NativeItemEventType var1) {
      this.pC(new NativeItemEvent(var1, this));
   }

   protected final void wS(boolean var1) {
      if (var1) {
         this.pC(new NativeItemEvent(NativeItemEventType.UPDATED, this));
      }
   }

   protected final void ah() {
      this.wS(true);
   }

   protected final void UT(boolean var1) {
      if (var1) {
         this.pC(new NativeItemEvent(NativeItemEventType.MODIFIED, this));
      }
   }

   protected final void eP() {
      this.UT(true);
   }

   @Override
   public abstract String toString();
}
