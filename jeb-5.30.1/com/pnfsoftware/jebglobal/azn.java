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
public class azn extends azl implements IVirtualMemoryShim {
   @SerId(1)
   azl Uv;
   @SerId(2)
   Map oW = new HashMap();
   @SerId(3)
   Set gO = new HashSet();
   @SerTransient
   private volatile byte[] nf;

   public azn(azl var1) {
      this.Uv = var1;
      this.RF = var1.RF;
      this.RF = var1.RF;
      this.Dw = var1.Dw;
   }

   @Override
   public IVirtualMemory getUnderlyingMemory() {
      return this.Uv;
   }

   @Override
   public int getAproximateFootprint() {
      long var1 = 0L;

      for (azk var4 : this.oW.values()) {
         if (var4.q != null) {
            var1 += var4.q.length;
         }
      }

      return (int)(var1 / 1024L);
   }

   @Override
   public int getPageSize() {
      return this.Uv.getPageSize();
   }

   @Override
   public int getSpaceBits() {
      return this.Uv.getSpaceBits();
   }

   @Override
   public Endianness getStandardEndianess() {
      return this.Uv.getStandardEndianess();
   }

   @Override
   public void setStandardEndianness(Endianness var1) {
      throw new RuntimeException("A memory shim is using the Endianness specification of its master");
   }

   @Override
   public IVirtualMemoryShim duplicate() {
      azn var1 = new azn(this.Uv);

      for (long var3 : this.oW.keySet()) {
         azk var5 = new azk((azk)this.oW.get(var3));
         var1.oW.put(var3, var5);
      }

      var1.gO.addAll(this.gO);
      return var1;
   }

   @Override
   public IVirtualMemory duplicate(boolean var1) {
      if (!var1) {
         return this.duplicate();
      } else if (!(this.Uv instanceof azm)) {
         throw new UnsupportedOperationException();
      } else {
         azm var2 = ((azm)this.Uv).Dw();

         for (long var4 : this.oW.keySet()) {
            azk var6 = new azk((azk)this.oW.get(var4));
            var2.Uv.put(var4, var6);
         }

         for (long var8 : this.gO) {
            var2.Uv.remove(var8);
         }

         return var2;
      }
   }

   @Override
   public boolean isValidAddress(long var1) {
      return this.Uv.isValidAddress(var1);
   }

   @Override
   public long roundToPage(long var1) {
      return this.Uv.roundToPage(var1);
   }

   @Override
   public long roundToSize(long var1) {
      return this.Uv.roundToSize(var1);
   }

   @Override
   protected synchronized azk q(long var1, boolean var3, boolean var4, boolean var5) throws MemoryException {
      long var6 = var1;
      if (!var3) {
         var6 = this.roundToPage(var1);
         this.q(var6);
      }

      azk var8;
      if (this.gO.contains(var6)) {
         var8 = null;
      } else {
         var8 = (azk)this.oW.get(var6);
         if (var8 == null) {
            var8 = this.Uv.q(var6, true, true, var5);
            if (var8 != null && var5) {
               var8 = new azk(var8);
               this.q(var6, var8, false);
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
   protected synchronized boolean q(long var1, azk var3, boolean var4) {
      boolean var5 = this.oW.put(var1, var3) == null;
      this.gO.remove(var1);
      if (var4) {
         for (IMemoryAllocListener var7 : this.getAllocListeners()) {
            var7.onAllocEvent(new MemoryAllocEvent(var1, this.getPageSize(), 0));
         }
      }

      return var5;
   }

   @Override
   protected synchronized boolean q(long var1, boolean var3) {
      boolean var4 = this.oW.remove(var1) != null;
      this.gO.add(var1);
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
      Collection var1 = this.Uv.getAllocatedPageBases();

      for (long var3 : this.oW.keySet()) {
         var1.add(var3);
      }

      for (long var6 : this.gO) {
         var1.remove(var6);
      }

      return var1;
   }

   @Override
   public int commitChanges(boolean var1, boolean var2, boolean var3) {
      int var4 = 0;
      if (var1 || var2) {
         Iterator var5 = this.oW.keySet().iterator();

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
                  azk var8 = this.Uv.q(var6, true, true, false);
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

            this.Uv.q(var6, (azk)this.oW.get(var6), false);
            var4++;
         }
      }

      if (var3) {
         for (long var11 : this.gO) {
            this.Uv.q(var11, false);
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
         for (long var6 : this.oW.keySet()) {
            try {
               azk var8 = this.Uv.q(var6, true, true, false);
               if (var8 != null) {
                  if (var1) {
                     var3.add(this.q(var6, (azk)this.oW.get(var6)));
                  }
               } else if (var2) {
                  var4.add(this.q(var6, (azk)this.oW.get(var6)));
               }
            } catch (MemoryException var9) {
            }
         }
      }

      return new MemoryChanges(var4, var3);
   }

   private Page q(long var1, azk var3) {
      byte[] var4 = var3.q;
      if (var4 == null) {
         if (this.nf == null) {
            synchronized (this) {
               if (this.nf == null) {
                  this.nf = new byte[this.Uv.getPageSize()];
               }
            }
         }

         var4 = this.nf;
      }

      return new Page(var1, var3.RF(), var4);
   }

   @Override
   public String toString() {
      return "VM_Shim_for:" + this.Uv;
   }
}
