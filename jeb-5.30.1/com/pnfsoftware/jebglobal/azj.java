package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.units.code.asm.memory.AbstractVirtualMemory;
import com.pnfsoftware.jeb.core.units.code.asm.memory.IVirtualMemory;
import com.pnfsoftware.jeb.core.units.code.asm.memory.MemoryException;
import com.pnfsoftware.jeb.util.collect.ArrayUtil;
import com.pnfsoftware.jeb.util.io.Endianness;
import com.pnfsoftware.jeb.util.primitives.Longs;
import com.pnfsoftware.jeb.util.serialization.annotations.SerDisabled;
import java.io.File;
import java.io.IOException;
import java.util.Collection;

@SerDisabled
public class azj extends AbstractVirtualMemory {
   byte[] q;
   long RF;
   long xK;
   Endianness Dw;

   public azj(long var1, File var3) throws IOException {
      this.q = com.pnfsoftware.jeb.util.io.IO.readFile(var3);
      this.RF = var1;
      this.xK = var1 + this.q.length;
      this.Dw = Endianness.LITTLE_ENDIAN;
   }

   @Override
   public int getPageSize() {
      return 4096;
   }

   @Override
   public int getSpaceBits() {
      return 64;
   }

   @Override
   public Endianness getStandardEndianess() {
      return this.Dw;
   }

   @Override
   public void setStandardEndianness(Endianness var1) {
      this.Dw = var1;
   }

   @Override
   public IVirtualMemory duplicate() {
      throw new UnsupportedOperationException();
   }

   @Override
   public boolean isValidAddress(long var1) {
      return true;
   }

   @Override
   public void allocate(long var1, int var3, int var4) throws MemoryException {
      throw new MemoryException("Not supported");
   }

   @Override
   public void free(long var1, int var3) throws MemoryException {
      throw new MemoryException("Not supported");
   }

   @Override
   public void setPageProtection(long var1, int var3) throws MemoryException {
      throw new MemoryException("Not supported");
   }

   @Override
   public int getPageProtection(long var1) throws MemoryException {
      return 7;
   }

   @Override
   public int check(long var1, int var3, int var4) {
      return var3;
   }

   @Override
   public int write(long var1, int var3, byte[] var4, int var5, boolean var6) throws MemoryException {
      throw new MemoryException("Cannot write to the VM");
   }

   @Override
   public int read(long var1, int var3, byte[] var4, int var5, boolean var6) throws MemoryException {
      if (var3 < 0) {
         throw new IllegalArgumentException();
      } else if (Longs.compareUnsigned(var1, this.RF) >= 0 && Longs.compareUnsigned(var1 + var3, this.xK) <= 0) {
         ArrayUtil.copyBytes(var4, var5, this.q, (int)(var1 - this.RF), var3);
         return var3;
      } else {
         throw new RuntimeException("Attempt to read out of file contents is illegal");
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
