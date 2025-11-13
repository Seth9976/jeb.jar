package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.units.code.asm.memory.IMemoryAllocListener;
import com.pnfsoftware.jeb.core.units.code.asm.memory.IMemoryFreeListener;
import com.pnfsoftware.jeb.core.units.code.asm.memory.IMemoryPropertyListener;
import com.pnfsoftware.jeb.core.units.code.asm.memory.MemoryAllocEvent;
import com.pnfsoftware.jeb.core.units.code.asm.memory.MemoryException;
import com.pnfsoftware.jeb.core.units.code.asm.memory.MemoryFreeEvent;
import com.pnfsoftware.jeb.core.units.code.asm.memory.MemoryPropertyEvent;
import com.pnfsoftware.jeb.util.format.Strings;
import com.pnfsoftware.jeb.util.format.TextBuilder;
import com.pnfsoftware.jeb.util.io.Endianness;
import com.pnfsoftware.jeb.util.math.MathUtil;
import com.pnfsoftware.jeb.util.serialization.annotations.Ser;
import com.pnfsoftware.jeb.util.serialization.annotations.SerCustomInit;
import com.pnfsoftware.jeb.util.serialization.annotations.SerId;
import com.pnfsoftware.jeb.util.serialization.annotations.SerTransient;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeSet;
import java.util.Map.Entry;

@Ser
public class azm extends azl {
   @SerId(1)
   Map Uv = new HashMap();
   @SerId(2)
   private int oW;
   @SerId(3)
   private int gO;
   @SerId(5)
   private Endianness nf;
   @SerId(6)
   private int gP;
   @SerTransient
   private long za;
   @SerTransient
   private int lm;
   @SerTransient
   private long zz;

   @SerCustomInit
   private void Uv() {
      this.za = MathUtil.makeMask(this.oW);
      this.lm = 1 << this.gO;
      long var1 = this.lm - 1L;
      this.zz = ~var1;
   }

   public azm() {
      this(64, 12);
   }

   public azm(int var1) {
      this(var1, Math.min(12, var1));
   }

   public azm(int var1, int var2) {
      this(var1, var2, Endianness.LITTLE_ENDIAN);
   }

   public azm(int var1, int var2, Endianness var3) {
      if (var1 <= 0) {
         throw new IllegalArgumentException("Illegal space size, must be >= 1");
      } else {
         if (var1 <= 64) {
            this.oW = var1;
         } else {
            this.oW = 64;
            if (var1 % 8 != 0) {
               throw new IllegalArgumentException("The memory space requested is greater than 64-bit, it must be a multiple of 8");
            }

            this.gP = var1;
         }

         if (var2 >= 1 && var2 <= 24 && var2 <= this.oW) {
            this.gO = var2;
            if (var3 == null) {
               throw new NullPointerException("Standard endianness must be provided");
            } else {
               this.nf = var3;
               this.Uv();
            }
         } else {
            throw new IllegalArgumentException("Page size is too large for this virtual memory");
         }
      }
   }

   public azm Dw() {
      azm var1 = new azm(this.oW, this.gO, this.nf);
      var1.gP = this.gP;

      for (Entry var3 : this.Uv.entrySet()) {
         long var4 = (Long)var3.getKey();
         azk var6 = (azk)var3.getValue();
         var1.Uv.put(var4, new azk(var6));
      }

      return var1;
   }

   synchronized void q(azn var1) {
      this.Uv.putAll(var1.oW);

      for (Long var3 : var1.gO) {
         this.Uv.remove(var3);
      }
   }

   @Override
   public int getPageSize() {
      return this.lm;
   }

   @Override
   public int getPageBits() {
      return this.gO;
   }

   @Override
   public int getSpaceBits() {
      return this.gP != 0 ? this.gP : this.oW;
   }

   @Override
   public Endianness getStandardEndianess() {
      return this.nf;
   }

   @Override
   public void setStandardEndianness(Endianness var1) {
      if (this.nf == null) {
         throw new NullPointerException("Standard endianness must be provided");
      } else {
         this.nf = var1;

         for (IMemoryPropertyListener var3 : this.getPropertyListeners()) {
            var3.onEndiannessChangeEvent(new MemoryPropertyEvent());
         }
      }
   }

   @Override
   public boolean isValidAddress(long var1) {
      return (var1 | this.za) == this.za;
   }

   @Override
   public long roundToPage(long var1) {
      return var1 & this.zz;
   }

   @Override
   public long roundToSize(long var1) {
      return var1 + this.lm - 1L & this.zz;
   }

   @Override
   protected synchronized azk q(long var1, boolean var3, boolean var4, boolean var5) throws MemoryException {
      long var6 = var1;
      if (!var3) {
         var6 = this.roundToPage(var1);
         this.q(var6);
      }

      azk var8 = (azk)this.Uv.get(var6);
      if (var8 == null && !var4) {
         throw new MemoryException("Page is not allocated");
      } else {
         return var8;
      }
   }

   @Override
   protected synchronized boolean q(long var1, azk var3, boolean var4) {
      boolean var5 = this.Uv.put(var1, var3) == null;
      if (var4) {
         for (IMemoryAllocListener var7 : this.getAllocListeners()) {
            var7.onAllocEvent(new MemoryAllocEvent(var1, this.getPageSize(), 0));
         }
      }

      return var5;
   }

   @Override
   protected synchronized boolean q(long var1, boolean var3) {
      boolean var4 = this.Uv.remove(var1) != null;
      if (var3) {
         for (IMemoryFreeListener var6 : this.getFreeListeners()) {
            var6.onFreeEvent(new MemoryFreeEvent(var1, this.getPageSize(), 0));
         }
      }

      return var4;
   }

   @Override
   public synchronized int getAllocatedPageCount() {
      return this.Uv.size();
   }

   @Override
   public synchronized Collection getAllocatedPageBases() {
      return new TreeSet(this.Uv.keySet());
   }

   @Override
   public int getAproximateFootprint() {
      long var1 = 0L;

      for (azk var4 : this.Uv.values()) {
         if (var4.q != null) {
            var1 += var4.q.length;
         }
      }

      return (int)(var1 / 1024L);
   }

   @Override
   public String toString() {
      TextBuilder var1 = new TextBuilder();
      var1.append((CharSequence)"VirtualMemory [spaceBits=");
      var1.append(this.oW);
      var1.append((CharSequence)", pageBits=");
      var1.append(this.gO);
      if (this.Uv != null) {
         var1.appendLine(", map=");
         if (!this.Uv.isEmpty()) {
            var1.indent();

            for (Entry var3 : this.Uv.entrySet()) {
               Strings.ff(var1, "%x -> %s%n", var3.getKey(), var3.getValue());
            }

            var1.unindent();
         }
      }

      var1.append((CharSequence)"]");
      return var1.toString();
   }
}
