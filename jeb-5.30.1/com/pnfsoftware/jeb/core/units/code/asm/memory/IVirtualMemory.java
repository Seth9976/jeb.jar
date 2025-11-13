package com.pnfsoftware.jeb.core.units.code.asm.memory;

import com.pnfsoftware.jeb.util.io.Endianness;
import com.pnfsoftware.jeb.util.serialization.annotations.Ser;
import java.util.Collection;

@Ser
public interface IVirtualMemory {
   int ACCESS_NONE = 0;
   int ACCESS_READ = 1;
   int ACCESS_WRITE = 2;
   int ACCESS_EXECUTE = 4;
   int ACCESS_RW = 3;
   int ACCESS_RX = 5;
   int ACCESS_RWX = 7;

   int getPageSize();

   default int getPageBits() {
      int var1 = 0;

      for (int var2 = this.getPageSize(); var2 != 0; var2 >>>= 1) {
         var1++;
      }

      return var1;
   }

   int getSpaceBits();

   Endianness getStandardEndianess();

   void setStandardEndianness(Endianness var1);

   IVirtualMemory duplicate();

   boolean isValidAddress(long var1);

   long roundToPage(long var1);

   long roundToSize(long var1);

   int getAllocatedPageCount();

   Collection getAllocatedPageBases();

   void allocate(long var1, int var3, int var4) throws MemoryException;

   void free(long var1, int var3) throws MemoryException;

   void allocatePage(long var1, int var3) throws MemoryException;

   void freePage(long var1) throws MemoryException;

   void setPageProtection(long var1, int var3) throws MemoryException;

   int getPageProtection(long var1) throws MemoryException;

   default boolean isAllocatedPage(long var1) {
      try {
         this.getPageProtection(var1);
         return true;
      } catch (MemoryException var3) {
         return false;
      }
   }

   int check(long var1, int var3, int var4);

   int read(long var1, int var3, byte[] var4, int var5, boolean var6) throws MemoryException;

   default int read(long var1, int var3, byte[] var4, int var5) throws MemoryException {
      return this.read(var1, var3, var4, var5, false);
   }

   int write(long var1, int var3, byte[] var4, int var5, boolean var6) throws MemoryException;

   default int write(long var1, int var3, byte[] var4, int var5) throws MemoryException {
      return this.write(var1, var3, var4, var5, false);
   }

   byte readByte(long var1) throws MemoryException;

   void writeByte(long var1, byte var3) throws MemoryException;

   short readLEShort(long var1) throws MemoryException;

   void writeLEShort(long var1, short var3) throws MemoryException;

   int readLEInt(long var1) throws MemoryException;

   void writeLEInt(long var1, int var3) throws MemoryException;

   long readLELong(long var1) throws MemoryException;

   void writeLELong(long var1, long var3) throws MemoryException;

   short readBEShort(long var1) throws MemoryException;

   void writeBEShort(long var1, short var3) throws MemoryException;

   int readBEInt(long var1) throws MemoryException;

   void writeBEInt(long var1, int var3) throws MemoryException;

   long readBELong(long var1) throws MemoryException;

   void writeBELong(long var1, long var3) throws MemoryException;

   short readShort(long var1) throws MemoryException;

   void writeShort(long var1, short var3) throws MemoryException;

   int readInt(long var1) throws MemoryException;

   void writeInt(long var1, int var3) throws MemoryException;

   long readLong(long var1) throws MemoryException;

   void writeLong(long var1, long var3) throws MemoryException;

   short readShort(long var1, Endianness var3) throws MemoryException;

   void writeShort(long var1, short var3, Endianness var4) throws MemoryException;

   int readInt(long var1, Endianness var3) throws MemoryException;

   void writeInt(long var1, int var3, Endianness var4) throws MemoryException;

   long readLong(long var1, Endianness var3) throws MemoryException;

   void writeLong(long var1, long var3, Endianness var5) throws MemoryException;

   long readPointer(long var1) throws MemoryException;

   void writePointer(long var1, long var3) throws MemoryException;

   void addPropertyListener(IMemoryPropertyListener var1);

   void removePropertyListener(IMemoryPropertyListener var1);

   void addAllocListener(IMemoryAllocListener var1);

   void removeAllocListener(IMemoryAllocListener var1);

   void addFreeListener(IMemoryFreeListener var1);

   void removeFreeListener(IMemoryFreeListener var1);

   void addProtectionListener(IMemoryProtectionListener var1);

   void removeProtectionListener(IMemoryProtectionListener var1);

   void addPreWriteListener(IMemoryWriteListener var1);

   void removePreWriteListener(IMemoryWriteListener var1);

   void addWriteListener(IMemoryWriteListener var1);

   void removeWriteListener(IMemoryWriteListener var1);

   void setLazyMemoryProvider(ILazyMemoryProvider var1, boolean var2) throws MemoryException, UnsupportedOperationException;

   int getAproximateFootprint();
}
