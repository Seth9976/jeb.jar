package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.units.code.asm.memory.AbstractVirtualMemory;
import com.pnfsoftware.jeb.core.units.code.asm.memory.ILazyMemoryProvider;
import com.pnfsoftware.jeb.core.units.code.asm.memory.IMemoryAllocListener;
import com.pnfsoftware.jeb.core.units.code.asm.memory.IMemoryFreeListener;
import com.pnfsoftware.jeb.core.units.code.asm.memory.IMemoryPropertyListener;
import com.pnfsoftware.jeb.core.units.code.asm.memory.IMemoryProtectionListener;
import com.pnfsoftware.jeb.core.units.code.asm.memory.IMemoryWriteListener;
import com.pnfsoftware.jeb.core.units.code.asm.memory.MemoryAllocEvent;
import com.pnfsoftware.jeb.core.units.code.asm.memory.MemoryException;
import com.pnfsoftware.jeb.core.units.code.asm.memory.MemoryFreeEvent;
import com.pnfsoftware.jeb.core.units.code.asm.memory.MemoryProtectionEvent;
import com.pnfsoftware.jeb.core.units.code.asm.memory.MemoryWriteEvent;
import com.pnfsoftware.jeb.core.units.code.asm.memory.Range;
import com.pnfsoftware.jeb.util.base.Assert;
import com.pnfsoftware.jeb.util.collect.ArrayUtil;
import com.pnfsoftware.jeb.util.format.Strings;
import com.pnfsoftware.jeb.util.serialization.annotations.Ser;
import com.pnfsoftware.jeb.util.serialization.annotations.SerCustomInit;
import com.pnfsoftware.jeb.util.serialization.annotations.SerId;
import com.pnfsoftware.jeb.util.serialization.annotations.SerTransient;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Ser
public abstract class azl extends AbstractVirtualMemory {
   static final int q = 65536;
   @SerId(1)
   protected boolean RF;
   @SerId(2)
   protected boolean xK;
   @SerTransient
   private List Uv;
   @SerTransient
   private List oW;
   @SerTransient
   private List gO;
   @SerTransient
   private List nf;
   @SerTransient
   private List gP;
   @SerTransient
   private List za;
   @SerTransient
   protected ILazyMemoryProvider Dw;

   @SerCustomInit
   private void Dw() {
      this.Uv = new ArrayList();
      this.oW = new ArrayList();
      this.gO = new ArrayList();
      this.nf = new ArrayList();
      this.gP = new ArrayList();
      this.za = new ArrayList();
   }

   public azl() {
      this.Dw();
   }

   public void q(boolean var1) {
      this.RF = var1;
   }

   public boolean q() {
      return this.RF;
   }

   public void RF(boolean var1) {
      this.xK = var1;
   }

   public boolean RF() {
      return this.xK;
   }

   public void q(long var1) throws MemoryException {
      if (!this.isValidAddress(var1)) {
         throw new MemoryException(Strings.ff("Invalid address: 0x%X", var1));
      }
   }

   private long q(long var1, int var3) {
      long var4 = var3 & 4294967295L;
      return this.roundToSize(var1 + var4);
   }

   @Override
   public synchronized void allocate(long var1, int var3, int var4) throws MemoryException {
      long var5 = this.roundToPage(var1);
      long var7 = this.q(var1, var3);
      this.q(var5);
      this.q(var7 - 1L);

      for (long var11 = var5; var11 != var7; var11 += this.getPageSize()) {
         if (this.q(var11, true, true, true) != null) {
            throw new MemoryException(Strings.ff("Page 0x%X is already allocated", var11));
         }
      }

      for (long var12 = var5; var12 != var7; var12 += this.getPageSize()) {
         this.q(var12, new azk(var4), false);
      }

      for (IMemoryAllocListener var10 : this.getAllocListeners()) {
         var10.onAllocEvent(new MemoryAllocEvent(var5, (int)(var7 - var5), var4));
      }
   }

   @Override
   public synchronized void free(long var1, int var3) throws MemoryException {
      long var4 = this.roundToPage(var1);
      long var6 = this.q(var1, var3);
      this.q(var4);
      this.q(var6 - 1L);

      for (long var10 = var4; var10 != var6; var10 += this.getPageSize()) {
         if (this.q(var10, true, true, false) == null) {
            throw new MemoryException(Strings.ff("Page 0x%X is not allocated", var10));
         }
      }

      for (long var11 = var4; var11 != var6; var11 += this.getPageSize()) {
         this.q(var11, false);
      }

      for (IMemoryFreeListener var9 : this.getFreeListeners()) {
         var9.onFreeEvent(new MemoryFreeEvent(var4, (int)(var6 - var4), 0));
      }
   }

   @Override
   public void setPageProtection(long var1, int var3) throws MemoryException {
      long var4 = this.roundToPage(var1);
      this.q(var4);
      this.q(var4, true, false, true).RF = var3;

      for (IMemoryProtectionListener var7 : this.getProtectionListeners()) {
         var7.onProtectionEvent(new MemoryProtectionEvent(var4, this.getPageSize(), var3));
      }
   }

   @Override
   public int getPageProtection(long var1) throws MemoryException {
      return this.q(var1, false, false, false).RF;
   }

   @Override
   public int check(long var1, int var3, int var4) {
      try {
         return this.q(0, 0, var4, true, var1, var3, null, 0);
      } catch (MemoryException var6) {
         throw new IllegalStateException(var6);
      }
   }

   @Override
   public synchronized int read(long var1, int var3, byte[] var4, int var5, boolean var6) throws MemoryException {
      int var7 = var6 ? 0 : 1;
      this.q(0, 1, var7, false, var1, var3, null, 0);

      try {
         return this.q(1, 1, var7, false, var1, var3, var4, var5);
      } catch (MemoryException var10) {
         throw new IllegalStateException(var10);
      }
   }

   @Override
   public synchronized int write(long var1, int var3, byte[] var4, int var5, boolean var6) throws MemoryException {
      int var7 = var6 ? 0 : 2;

      for (IMemoryWriteListener var9 : this.getPreWriteListeners()) {
         var9.onMemoryWriteEvent(new MemoryWriteEvent(var1, var3, var4, var5));
      }

      this.q(0, 2, var7, false, var1, var3, null, 0);

      int var12;
      try {
         var12 = this.q(2, 2, var7, false, var1, var3, var4, var5);
      } catch (MemoryException var11) {
         throw new IllegalStateException(var11);
      }

      for (IMemoryWriteListener var10 : this.getWriteListeners()) {
         var10.onMemoryWriteEvent(new MemoryWriteEvent(var1, var3, var4, var5));
      }

      return var12;
   }

   protected int q(int var1, int var2, int var3, boolean var4, long var5, int var7, byte[] var8, int var9) throws MemoryException {
      if (var1 >= 0 && var1 <= 3) {
         int var10 = this.getPageSize();
         long var11 = var7 & 4294967295L;
         long var13 = var11;
         long var15 = this.roundToPage(var5);
         int var17 = (int)(var5 - var15);

         for (long var18 = (long)var10 - var17; var11 > 0L; var18 = var10) {
            if (!this.isValidAddress(var15)) {
               if (var4) {
                  return (int)(var13 - var11);
               }

               throw new MemoryException(Strings.ff("Invalid address for memory space: 0x%X", var5));
            }

            azk var20 = this.q(var15, true, true, var1 == 2);
            if (var20 == null) {
               if ((!this.RF || var2 != 1) && (!this.xK || var2 != 2)) {
                  if (var4) {
                     return (int)(var13 - var11);
                  }

                  throw new MemoryException(Strings.ff("Invalid page at address 0x%X", var15));
               }

               this.allocatePage(var15, 7);
               var20 = this.q(var15, true, true, var1 == 2);
               Assert.a(var20 != null);
            }

            if ((var20.RF & var3) != var3) {
               if (var4) {
                  return (int)(var13 - var11);
               }

               throw new MemoryException(Strings.ff("Invalid page access at address 0x%X", var15));
            }

            long var21 = var11 <= var18 ? var11 : var18;
            if (this.Dw != null && (var1 == 1 || var1 == 2) && var20.q == null && (var20.RF & 65536) != 0) {
               var20.RF &= -65537;
               byte[] var23 = new byte[var10];

               try {
                  this.Dw.getData(var15, var10, var23, 0);
               } catch (IOException var25) {
                  throw new MemoryException(var25);
               }

               if (var1 != 1 || !q(var23)) {
                  var20.q = var23;
               }
            }

            if (var1 == 1) {
               if (var20.q == null) {
                  Arrays.fill(var8, var9, (int)(var9 + var21), (byte)0);
               } else {
                  ArrayUtil.copyBytes(var8, var9, var20.q, var17, (int)var21);
               }
            } else if (var1 == 2) {
               if (var20.q == null) {
                  var20.q = new byte[var10];
               }

               ArrayUtil.copyBytes(var20.q, var17, var8, var9, (int)var21);
            }

            var9 += (int)var21;
            var11 -= var21;
            var15 += var10;
            var17 = 0;
         }

         return (int)var13;
      } else {
         throw new IllegalArgumentException();
      }
   }

   private static boolean q(byte[] var0) {
      for (byte var4 : var0) {
         if (var4 != 0) {
            return false;
         }
      }

      return true;
   }

   protected abstract azk q(long var1, boolean var3, boolean var4, boolean var5) throws MemoryException;

   protected abstract boolean q(long var1, azk var3, boolean var4);

   protected abstract boolean q(long var1, boolean var3);

   @Override
   public List getPropertyListeners() {
      return this.Uv;
   }

   @Override
   public List getAllocListeners() {
      return this.oW;
   }

   @Override
   public List getFreeListeners() {
      return this.gO;
   }

   @Override
   public List getProtectionListeners() {
      return this.nf;
   }

   @Override
   public List getPreWriteListeners() {
      return this.gP;
   }

   @Override
   public List getWriteListeners() {
      return this.za;
   }

   @Override
   public void addPropertyListener(IMemoryPropertyListener var1) {
      synchronized (this.Uv) {
         this.Uv.add(var1);
      }
   }

   @Override
   public void removePropertyListener(IMemoryPropertyListener var1) {
      synchronized (this.Uv) {
         this.Uv.remove(var1);
      }
   }

   @Override
   public void addAllocListener(IMemoryAllocListener var1) {
      synchronized (this.oW) {
         this.oW.add(var1);
      }
   }

   @Override
   public void removeAllocListener(IMemoryAllocListener var1) {
      synchronized (this.oW) {
         this.oW.remove(var1);
      }
   }

   @Override
   public void addFreeListener(IMemoryFreeListener var1) {
      synchronized (this.gO) {
         this.gO.add(var1);
      }
   }

   @Override
   public void removeFreeListener(IMemoryFreeListener var1) {
      synchronized (this.gO) {
         this.gO.remove(var1);
      }
   }

   @Override
   public void addProtectionListener(IMemoryProtectionListener var1) {
      synchronized (this.nf) {
         this.nf.add(var1);
      }
   }

   @Override
   public void removeProtectionListener(IMemoryProtectionListener var1) {
      synchronized (this.nf) {
         this.nf.remove(var1);
      }
   }

   @Override
   public void addPreWriteListener(IMemoryWriteListener var1) {
      synchronized (this.gP) {
         this.gP.add(var1);
      }
   }

   @Override
   public void removePreWriteListener(IMemoryWriteListener var1) {
      synchronized (this.gP) {
         this.gP.remove(var1);
      }
   }

   @Override
   public void addWriteListener(IMemoryWriteListener var1) {
      synchronized (this.za) {
         this.za.add(var1);
      }
   }

   @Override
   public void removeWriteListener(IMemoryWriteListener var1) {
      synchronized (this.za) {
         this.za.remove(var1);
      }
   }

   public azn xK() {
      return new azn(this);
   }

   @Override
   public void setLazyMemoryProvider(ILazyMemoryProvider var1, boolean var2) throws MemoryException {
      if (var1 == null) {
         throw new NullPointerException();
      } else if (this.Dw != null) {
         throw new IllegalStateException("A lazy memory provider is already set!");
      } else {
         this.Dw = var1;

         for (Range var4 : this.Dw.getRanges()) {
            try {
               this.allocate(var4.start, (int)(var4.end - var4.start), var4.protection | 65536);
            } catch (MemoryException var6) {
               if (!var2) {
                  throw var6;
               }
            }
         }
      }
   }
}
