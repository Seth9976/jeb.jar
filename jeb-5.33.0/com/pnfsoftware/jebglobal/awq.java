package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.units.code.asm.memory.IMemoryAllocListener;
import com.pnfsoftware.jeb.core.units.code.asm.memory.IMemoryFreeListener;
import com.pnfsoftware.jeb.core.units.code.asm.memory.IVirtualMemory;
import com.pnfsoftware.jeb.core.units.code.asm.memory.IVirtualMemoryShim;
import com.pnfsoftware.jeb.core.units.code.asm.memory.MemoryAllocEvent;
import com.pnfsoftware.jeb.core.units.code.asm.memory.MemoryChanges;
import com.pnfsoftware.jeb.core.units.code.asm.memory.MemoryException;
import com.pnfsoftware.jeb.core.units.code.asm.memory.MemoryFreeEvent;
import com.pnfsoftware.jeb.core.units.code.asm.memory.Page;
import com.pnfsoftware.jeb.util.io.Endianness;
import com.pnfsoftware.jeb.util.serialization.annotations.Ser;
import com.pnfsoftware.jeb.util.serialization.annotations.SerId;
import com.pnfsoftware.jeb.util.serialization.annotations.SerTransient;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

@Ser
public class awq extends awo implements IVirtualMemoryShim {
   @SerId(1)
   awo UT;
   @SerId(2)
   Map E = new HashMap();
   @SerId(3)
   Set sY = new HashSet();
   @SerTransient
   private volatile byte[] ys;

   public awq(awo var1) {
      this.UT = var1;
      this.pC = var1.pC;
      this.pC = var1.pC;
      this.wS = var1.wS;
   }

   @Override
   public IVirtualMemory getUnderlyingMemory() {
      return this.UT;
   }

   @Override
   public int getAproximateFootprint() {
      long var1 = 0L;

      for (awn var4 : this.E.values()) {
         if (var4.pC != null) {
            var1 += var4.pC.length;
         }
      }

      return (int)(var1 / 1024L);
   }

   @Override
   public int getPageSize() {
      return this.UT.getPageSize();
   }

   @Override
   public int getSpaceBits() {
      return this.UT.getSpaceBits();
   }

   @Override
   public Endianness getStandardEndianess() {
      return this.UT.getStandardEndianess();
   }

   @Override
   public void setStandardEndianness(Endianness var1) {
      throw new RuntimeException("A memory shim is using the Endianness specification of its master");
   }

   @Override
   public IVirtualMemoryShim duplicate() {
      awq var1 = new awq(this.UT);

      for (long var3 : this.E.keySet()) {
         awn var5 = new awn((awn)this.E.get(var3));
         var1.E.put(var3, var5);
      }

      var1.sY.addAll(this.sY);
      return var1;
   }

   @Override
   public IVirtualMemory duplicate(boolean var1) {
      if (!var1) {
         return this.duplicate();
      } else if (!(this.UT instanceof awp)) {
         throw new UnsupportedOperationException();
      } else {
         awp var2 = ((awp)this.UT).A();

         for (long var4 : this.E.keySet()) {
            awn var6 = new awn((awn)this.E.get(var4));
            var2.UT.put(var4, var6);
         }

         for (long var8 : this.sY) {
            var2.UT.remove(var8);
         }

         return var2;
      }
   }

   @Override
   public boolean isValidAddress(long var1) {
      return this.UT.isValidAddress(var1);
   }

   @Override
   public long roundToPage(long var1) {
      return this.UT.roundToPage(var1);
   }

   @Override
   public long roundToSize(long var1) {
      return this.UT.roundToSize(var1);
   }

   @Override
   protected synchronized awn pC(long var1, boolean var3, boolean var4, boolean var5) throws MemoryException {
      long var6 = var1;
      if (!var3) {
         var6 = this.roundToPage(var1);
         this.pC(var6);
      }

      awn var8;
      if (this.sY.contains(var6)) {
         var8 = null;
      } else {
         var8 = (awn)this.E.get(var6);
         if (var8 == null) {
            var8 = this.UT.pC(var6, true, true, var5);
            if (var8 != null && var5) {
               var8 = new awn(var8);
               this.pC(var6, var8, false);
            }
         }
      }

      if (var8 == null && !var4) {
         throw new MemoryException("Page is not allocated");
      } else {
         return var8;
      }
   }

   @Override
   protected synchronized boolean pC(long var1, awn var3, boolean var4) {
      boolean var5 = this.E.put(var1, var3) == null;
      this.sY.remove(var1);
      if (var4) {
         for (IMemoryAllocListener var7 : this.getAllocListeners()) {
            var7.onAllocEvent(new MemoryAllocEvent(var1, this.getPageSize(), 0));
         }
      }

      return var5;
   }

   @Override
   protected synchronized boolean pC(long var1, boolean var3) {
      boolean var4 = this.E.remove(var1) != null;
      this.sY.add(var1);
      if (var3) {
         for (IMemoryFreeListener var6 : this.getFreeListeners()) {
            var6.onFreeEvent(new MemoryFreeEvent(var1, this.getPageSize(), 0));
         }
      }

      return var4;
   }

   @Override
   public synchronized int getAllocatedPageCount() {
      return this.getAllocatedPageBases().size();
   }

   @Override
   public synchronized Collection getAllocatedPageBases() {
      Collection var1 = this.UT.getAllocatedPageBases();

      for (long var3 : this.E.keySet()) {
         var1.add(var3);
      }

      for (long var6 : this.sY) {
         var1.remove(var6);
      }

      return var1;
   }

   @Override
   public int commitChanges(boolean var1, boolean var2, boolean var3) {
      int var4 = 0;
      if (var1 || var2) {
         Iterator var5 = this.E.keySet().iterator();

         label49:
         while (true) {
            long var6;
            while (true) {
               if (!var5.hasNext()) {
                  break label49;
               }

               var6 = (Long)var5.next();
               if (var1 && var2) {
                  break;
               }

               try {
                  awn var8 = this.UT.pC(var6, true, true, false);
                  if (var8 != null) {
                     if (!var1) {
                        continue;
                     }
                     break;
                  }
               } catch (MemoryException var9) {
                  break;
               }

               if (var2) {
                  break;
               }
            }

            this.UT.pC(var6, (awn)this.E.get(var6), false);
            var4++;
         }
      }

      if (var3) {
         for (long var11 : this.sY) {
            this.UT.pC(var11, false);
            var4++;
         }
      }

      return var4;
   }

   @Override
   public MemoryChanges getChanges(boolean var1, boolean var2) {
      ArrayList var3 = new ArrayList();
      ArrayList var4 = new ArrayList();
      if (var1 || var2) {
         for (long var6 : this.E.keySet()) {
            try {
               awn var8 = this.UT.pC(var6, true, true, false);
               if (var8 != null) {
                  if (var1) {
                     var3.add(this.pC(var6, (awn)this.E.get(var6)));
                  }
               } else if (var2) {
                  var4.add(this.pC(var6, (awn)this.E.get(var6)));
               }
            } catch (MemoryException var9) {
            }
         }
      }

      return new MemoryChanges(var4, var3);
   }

   private Page pC(long var1, awn var3) {
      byte[] var4 = var3.pC;
      if (var4 == null) {
         if (this.ys == null) {
            synchronized (this) {
               if (this.ys == null) {
                  this.ys = new byte[this.UT.getPageSize()];
               }
            }
         }

         var4 = this.ys;
      }

      return new Page(var1, var3.pC(), var4);
   }

   @Override
   public String toString() {
      return "VM_Shim_for:" + this.UT;
   }
}
