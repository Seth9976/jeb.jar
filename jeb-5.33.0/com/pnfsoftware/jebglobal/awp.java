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
public class awp extends awo {
   @SerId(1)
   Map UT = new HashMap();
   @SerId(2)
   private int E;
   @SerId(3)
   private int sY;
   @SerId(5)
   private Endianness ys;
   @SerId(6)
   private int ld;
   @SerTransient
   private long gp;
   @SerTransient
   private int oT;
   @SerTransient
   private long fI;

   @SerCustomInit
   private void kS() {
      this.gp = MathUtil.makeMask(this.E);
      this.oT = 1 << this.sY;
      long var1 = this.oT - 1L;
      this.fI = ~var1;
   }

   public awp() {
      this(64, 12);
   }

   public awp(int var1) {
      this(var1, Math.min(12, var1));
   }

   public awp(int var1, int var2) {
      this(var1, var2, Endianness.LITTLE_ENDIAN);
   }

   public awp(int var1, int var2, Endianness var3) {
      if (var1 <= 0) {
         throw new IllegalArgumentException("Illegal space size, must be >= 1");
      } else {
         if (var1 <= 64) {
            this.E = var1;
         } else {
            this.E = 64;
            if (var1 % 8 != 0) {
               throw new IllegalArgumentException("The memory space requested is greater than 64-bit, it must be a multiple of 8");
            }

            this.ld = var1;
         }

         if (var2 >= 1 && var2 <= 24 && var2 <= this.E) {
            this.sY = var2;
            if (var3 == null) {
               throw new NullPointerException("Standard endianness must be provided");
            } else {
               this.ys = var3;
               this.kS();
            }
         } else {
            throw new IllegalArgumentException("Page size is too large for this virtual memory");
         }
      }
   }

   public awp A() {
      awp var1 = new awp(this.E, this.sY, this.ys);
      var1.ld = this.ld;

      for (Entry var3 : this.UT.entrySet()) {
         long var4 = (Long)var3.getKey();
         awn var6 = (awn)var3.getValue();
         var1.UT.put(var4, new awn(var6));
      }

      return var1;
   }

   @Override
   public int getPageSize() {
      return this.oT;
   }

   @Override
   public int getPageBits() {
      return this.sY;
   }

   @Override
   public int getSpaceBits() {
      return this.ld != 0 ? this.ld : this.E;
   }

   @Override
   public Endianness getStandardEndianess() {
      return this.ys;
   }

   @Override
   public void setStandardEndianness(Endianness var1) {
      if (this.ys == null) {
         throw new NullPointerException("Standard endianness must be provided");
      } else {
         this.ys = var1;

         for (IMemoryPropertyListener var3 : this.getPropertyListeners()) {
            var3.onEndiannessChangeEvent(new MemoryPropertyEvent());
         }
      }
   }

   @Override
   public boolean isValidAddress(long var1) {
      return (var1 | this.gp) == this.gp;
   }

   @Override
   public long roundToPage(long var1) {
      return var1 & this.fI;
   }

   @Override
   public long roundToSize(long var1) {
      return var1 + this.oT - 1L & this.fI;
   }

   @Override
   protected synchronized awn pC(long var1, boolean var3, boolean var4, boolean var5) throws MemoryException {
      long var6 = var1;
      if (!var3) {
         var6 = this.roundToPage(var1);
         this.pC(var6);
      }

      awn var8 = (awn)this.UT.get(var6);
      if (var8 == null && !var4) {
         throw new MemoryException("Page is not allocated");
      } else {
         return var8;
      }
   }

   @Override
   protected synchronized boolean pC(long var1, awn var3, boolean var4) {
      boolean var5 = this.UT.put(var1, var3) == null;
      if (var4) {
         for (IMemoryAllocListener var7 : this.getAllocListeners()) {
            var7.onAllocEvent(new MemoryAllocEvent(var1, this.getPageSize(), 0));
         }
      }

      return var5;
   }

   @Override
   protected synchronized boolean pC(long var1, boolean var3) {
      boolean var4 = this.UT.remove(var1) != null;
      if (var3) {
         for (IMemoryFreeListener var6 : this.getFreeListeners()) {
            var6.onFreeEvent(new MemoryFreeEvent(var1, this.getPageSize(), 0));
         }
      }

      return var4;
   }

   @Override
   public synchronized int getAllocatedPageCount() {
      return this.UT.size();
   }

   @Override
   public synchronized Collection getAllocatedPageBases() {
      return new TreeSet(this.UT.keySet());
   }

   @Override
   public int getAproximateFootprint() {
      long var1 = 0L;

      for (awn var4 : this.UT.values()) {
         if (var4.pC != null) {
            var1 += var4.pC.length;
         }
      }

      return (int)(var1 / 1024L);
   }

   @Override
   public String toString() {
      TextBuilder var1 = new TextBuilder();
      var1.append((CharSequence)"VirtualMemory [spaceBits=");
      var1.append(this.E);
      var1.append((CharSequence)", pageBits=");
      var1.append(this.sY);
      if (this.UT != null) {
         var1.appendLine(", map=");
         if (!this.UT.isEmpty()) {
            var1.indent();

            for (Entry var3 : this.UT.entrySet()) {
               Strings.ff(var1, "%x -> %s%n", var3.getKey(), var3.getValue());
            }

            var1.unindent();
         }
      }

      var1.append((CharSequence)"]");
      return var1.toString();
   }
}
