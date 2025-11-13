package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.units.code.asm.memory.AbstractVirtualMemory;
import com.pnfsoftware.jeb.core.units.code.asm.memory.IVirtualMemory;
import com.pnfsoftware.jeb.core.units.code.asm.memory.MemoryException;
import com.pnfsoftware.jeb.core.units.code.debug.IDebuggerVirtualMemory;
import com.pnfsoftware.jeb.util.format.Strings;
import com.pnfsoftware.jeb.util.io.Endianness;
import com.pnfsoftware.jeb.util.logging.GlobalLog;
import com.pnfsoftware.jeb.util.logging.ILogger;
import com.pnfsoftware.jeb.util.serialization.annotations.SerDisabled;
import java.io.IOException;
import java.util.Collection;
import java.util.List;

@SerDisabled
public class xO extends AbstractVirtualMemory implements IDebuggerVirtualMemory {
   private static final ILogger pC = GlobalLog.getLogger(xO.class);
   private aI A;
   private XE kS;
   private Endianness wS;
   private com.pnfsoftware.jeb.corei.debuggers.linux.K UT;
   private long E;

   public xO(aI var1) {
      this.A = var1;
      this.kS = var1.rl();
      this.wS = var1.ys();
   }

   @Override
   public int getPageSize() {
      return this.kS.A();
   }

   @Override
   public int getSpaceBits() {
      return this.kS.pC();
   }

   @Override
   public Endianness getStandardEndianess() {
      return this.wS;
   }

   @Override
   public void setStandardEndianness(Endianness var1) {
      this.wS = var1;
   }

   @Override
   public IVirtualMemory duplicate() {
      throw new UnsupportedOperationException();
   }

   private com.pnfsoftware.jeb.corei.debuggers.linux.K pC() {
      Gg var1 = this.A.ld();
      if (var1 == null) {
         return null;
      } else {
         long var2 = System.currentTimeMillis();
         if (var2 - this.E >= 30000L) {
            this.UT = var1.pC();
            this.E = var2;
         }

         return this.UT;
      }
   }

   private int pC(int var1) {
      byte var2 = 0;
      if ((var1 & 4) != 0) {
         var2 |= 1;
      }

      if ((var1 & 2) != 0) {
         var2 |= 2;
      }

      if ((var1 & 1) != 0) {
         var2 |= 4;
      }

      return var2;
   }

   private int A(int var1) {
      byte var2 = 0;
      if ((var1 & 1) != 0) {
         var2 |= 4;
      }

      if ((var1 & 2) != 0) {
         var2 |= 2;
      }

      if ((var1 & 4) != 0) {
         var2 |= 1;
      }

      return var2;
   }

   @Override
   public boolean isValidAddress(long var1) {
      return true;
   }

   @Override
   public void allocate(long var1, int var3, int var4) throws MemoryException {
      throw new MemoryException("This VM does not support allocating memory");
   }

   @Override
   public void free(long var1, int var3) throws MemoryException {
      throw new MemoryException("This VM does not support freeing memory");
   }

   @Override
   public void setPageProtection(long var1, int var3) throws MemoryException {
      throw new MemoryException("This VM does not support modifying page protections");
   }

   @Override
   public int getPageProtection(long var1) throws MemoryException {
      com.pnfsoftware.jeb.corei.debuggers.linux.K var3 = this.pC();
      if (var3 != null) {
         com.pnfsoftware.jeb.corei.debuggers.linux.Ws var4 = var3.pC(var1);
         if (var4 != null) {
            return this.pC(var4.kS);
         }
      }

      throw new MemoryException(Strings.ff("Cannot retrieve page protection at address %Xh", var1));
   }

   @Override
   public int check(long var1, int var3, int var4) {
      com.pnfsoftware.jeb.corei.debuggers.linux.K var5 = this.pC();
      if (var5 != null) {
         int var6 = this.A(var4);
         return (int)var5.pC(var1, var3, var6);
      } else {
         return 0;
      }
   }

   @Override
   public int read(long var1, int var3, byte[] var4, int var5, boolean var6) throws MemoryException {
      try {
         int var7 = this.kS.pC(var1, var4, var5, var3);
         if (var7 < 0) {
            throw new IOException("Read error: " + var7);
         } else {
            return var7;
         }
      } catch (IOException var8) {
         throw new MemoryException(Strings.ff("An error occurred when attempting to read bytes: %Xh, %d", var1, var3), var8);
      }
   }

   @Override
   public int write(long var1, int var3, byte[] var4, int var5, boolean var6) throws MemoryException {
      try {
         int var7 = this.kS.A(var1, var4, var5, var3);
         if (var7 < 0) {
            throw new IOException("Write error: " + var7);
         } else {
            return var7;
         }
      } catch (IOException var8) {
         throw new MemoryException(Strings.ff("An error occurred when attempting to write bytes: %Xh, %d", var1, var3), var8);
      }
   }

   @Override
   public long findBytes(long var1, long var3, byte[] var5) {
      com.pnfsoftware.jeb.corei.debuggers.linux.K var6 = this.pC();
      if (var6 == null) {
         long var19 = this.kS.pC(var1, var3, var5);
         return var19 < 0L ? -1L : var19;
      } else {
         List var7 = var6.pC();
         long var8 = var1 + var3;
         int var10 = 0;

         for (com.pnfsoftware.jeb.corei.debuggers.linux.Ws var12 : var7) {
            if (var1 < var12.A && var8 > var12.pC) {
               break;
            }

            var10++;
         }

         for (long var20 = var1; var20 <= var8 - var5.length; var10++) {
            if (var10 >= var7.size()) {
               return -1L;
            }

            com.pnfsoftware.jeb.corei.debuggers.linux.Ws var13 = (com.pnfsoftware.jeb.corei.debuggers.linux.Ws)var7.get(var10);
            if (var20 >= var13.A || var8 <= var13.pC) {
               return -1L;
            }

            var20 = Math.max(var20, var13.pC);
            long var14 = Math.min(var8, var13.A);
            int var16 = (int)(var14 - var20);
            if (var16 >= var5.length) {
               long var17 = this.kS.pC(var20, var16, var5);
               if (var17 >= 0L) {
                  return var20 - var1 + var17;
               }
            }

            var20 = var13.A;
         }

         return -1L;
      }
   }

   @Override
   public int getAllocatedPageCount() {
      throw new UnsupportedOperationException();
   }

   @Override
   public Collection getAllocatedPageBases() {
      throw new UnsupportedOperationException();
   }
}
