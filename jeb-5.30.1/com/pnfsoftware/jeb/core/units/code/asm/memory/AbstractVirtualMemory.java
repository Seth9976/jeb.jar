package com.pnfsoftware.jeb.core.units.code.asm.memory;

import com.pnfsoftware.jeb.util.io.Endianness;
import com.pnfsoftware.jeb.util.serialization.annotations.Ser;
import com.pnfsoftware.jeb.util.serialization.annotations.SerCustomInit;
import com.pnfsoftware.jeb.util.serialization.annotations.SerTransient;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.util.Collections;
import java.util.List;

@Ser
public abstract class AbstractVirtualMemory implements IVirtualMemory {
   @SerTransient
   private byte[] data = new byte[8];

   @SerCustomInit
   private void init() {
      this.data = new byte[8];
   }

   @Override
   public long roundToPage(long var1) {
      int var3 = this.getPageSize();
      return var1 / var3 * var3;
   }

   @Override
   public long roundToSize(long var1) {
      int var3 = this.getPageSize();
      return (var1 + var3 - 1L) / var3 * var3;
   }

   @Override
   public void allocatePage(long var1, int var3) throws MemoryException {
      this.allocate(var1, 1, var3);
   }

   @Override
   public void freePage(long var1) throws MemoryException {
      this.free(var1, 1);
   }

   @Override
   public synchronized byte readByte(long var1) throws MemoryException {
      this.read(var1, 1, this.data, 0);
      return this.data[0];
   }

   @Override
   public synchronized void writeByte(long var1, byte var3) throws MemoryException {
      this.data[0] = var3;
      this.write(var1, 1, this.data, 0);
   }

   @Override
   public synchronized short readLEShort(long var1) throws MemoryException {
      this.read(var1, 2, this.data, 0);
      return ByteBuffer.wrap(this.data).order(ByteOrder.LITTLE_ENDIAN).getShort();
   }

   @Override
   public synchronized void writeLEShort(long var1, short var3) throws MemoryException {
      ByteBuffer.wrap(this.data).order(ByteOrder.LITTLE_ENDIAN).putShort(var3);
      this.write(var1, 2, this.data, 0);
   }

   @Override
   public synchronized int readLEInt(long var1) throws MemoryException {
      this.read(var1, 4, this.data, 0);
      return ByteBuffer.wrap(this.data).order(ByteOrder.LITTLE_ENDIAN).getInt();
   }

   @Override
   public synchronized void writeLEInt(long var1, int var3) throws MemoryException {
      ByteBuffer.wrap(this.data).order(ByteOrder.LITTLE_ENDIAN).putInt(var3);
      this.write(var1, 4, this.data, 0);
   }

   @Override
   public synchronized long readLELong(long var1) throws MemoryException {
      this.read(var1, 8, this.data, 0);
      return ByteBuffer.wrap(this.data).order(ByteOrder.LITTLE_ENDIAN).getLong();
   }

   @Override
   public synchronized void writeLELong(long var1, long var3) throws MemoryException {
      ByteBuffer.wrap(this.data).order(ByteOrder.LITTLE_ENDIAN).putLong(var3);
      this.write(var1, 8, this.data, 0);
   }

   @Override
   public synchronized short readBEShort(long var1) throws MemoryException {
      this.read(var1, 2, this.data, 0);
      return ByteBuffer.wrap(this.data).order(ByteOrder.BIG_ENDIAN).getShort();
   }

   @Override
   public synchronized void writeBEShort(long var1, short var3) throws MemoryException {
      ByteBuffer.wrap(this.data).order(ByteOrder.BIG_ENDIAN).putShort(var3);
      this.write(var1, 2, this.data, 0);
   }

   @Override
   public synchronized int readBEInt(long var1) throws MemoryException {
      this.read(var1, 4, this.data, 0);
      return ByteBuffer.wrap(this.data).order(ByteOrder.BIG_ENDIAN).getInt();
   }

   @Override
   public synchronized void writeBEInt(long var1, int var3) throws MemoryException {
      ByteBuffer.wrap(this.data).order(ByteOrder.BIG_ENDIAN).putInt(var3);
      this.write(var1, 4, this.data, 0);
   }

   @Override
   public synchronized long readBELong(long var1) throws MemoryException {
      this.read(var1, 8, this.data, 0);
      return ByteBuffer.wrap(this.data).order(ByteOrder.BIG_ENDIAN).getLong();
   }

   @Override
   public synchronized void writeBELong(long var1, long var3) throws MemoryException {
      ByteBuffer.wrap(this.data).order(ByteOrder.BIG_ENDIAN).putLong(var3);
      this.write(var1, 8, this.data, 0);
   }

   @Override
   public synchronized short readShort(long var1) throws MemoryException {
      this.read(var1, 2, this.data, 0);
      return ByteBuffer.wrap(this.data).order(this.getStandardEndianess().toByteOrder()).getShort();
   }

   @Override
   public synchronized void writeShort(long var1, short var3) throws MemoryException {
      ByteBuffer.wrap(this.data).order(this.getStandardEndianess().toByteOrder()).putShort(var3);
      this.write(var1, 2, this.data, 0);
   }

   @Override
   public synchronized int readInt(long var1) throws MemoryException {
      this.read(var1, 4, this.data, 0);
      return ByteBuffer.wrap(this.data).order(this.getStandardEndianess().toByteOrder()).getInt();
   }

   @Override
   public synchronized void writeInt(long var1, int var3) throws MemoryException {
      ByteBuffer.wrap(this.data).order(this.getStandardEndianess().toByteOrder()).putInt(var3);
      this.write(var1, 4, this.data, 0);
   }

   @Override
   public synchronized long readLong(long var1) throws MemoryException {
      this.read(var1, 8, this.data, 0);
      return ByteBuffer.wrap(this.data).order(this.getStandardEndianess().toByteOrder()).getLong();
   }

   @Override
   public synchronized void writeLong(long var1, long var3) throws MemoryException {
      ByteBuffer.wrap(this.data).order(this.getStandardEndianess().toByteOrder()).putLong(var3);
      this.write(var1, 8, this.data, 0);
   }

   @Override
   public synchronized short readShort(long var1, Endianness var3) throws MemoryException {
      this.read(var1, 2, this.data, 0);
      return ByteBuffer.wrap(this.data).order(var3.toByteOrder()).getShort();
   }

   @Override
   public synchronized void writeShort(long var1, short var3, Endianness var4) throws MemoryException {
      ByteBuffer.wrap(this.data).order(var4.toByteOrder()).putShort(var3);
      this.write(var1, 2, this.data, 0);
   }

   @Override
   public synchronized int readInt(long var1, Endianness var3) throws MemoryException {
      this.read(var1, 4, this.data, 0);
      return ByteBuffer.wrap(this.data).order(var3.toByteOrder()).getInt();
   }

   @Override
   public synchronized void writeInt(long var1, int var3, Endianness var4) throws MemoryException {
      ByteBuffer.wrap(this.data).order(var4.toByteOrder()).putInt(var3);
      this.write(var1, 4, this.data, 0);
   }

   @Override
   public synchronized long readLong(long var1, Endianness var3) throws MemoryException {
      this.read(var1, 8, this.data, 0);
      return ByteBuffer.wrap(this.data).order(var3.toByteOrder()).getLong();
   }

   @Override
   public synchronized void writeLong(long var1, long var3, Endianness var5) throws MemoryException {
      ByteBuffer.wrap(this.data).order(var5.toByteOrder()).putLong(var3);
      this.write(var1, 8, this.data, 0);
   }

   @Override
   public long readPointer(long var1) throws MemoryException {
      switch (this.getSpaceBits()) {
         case 32:
            return this.readInt(var1) & 4294967295L;
         case 64:
            return this.readLong(var1);
         default:
            throw new MemoryException();
      }
   }

   @Override
   public void writePointer(long var1, long var3) throws MemoryException {
      switch (this.getSpaceBits()) {
         case 32:
            this.writeInt(var1, (int)var3);
            break;
         case 64:
            this.writeLong(var1, var3);
            break;
         default:
            throw new MemoryException();
      }
   }

   protected List getPropertyListeners() {
      return Collections.emptyList();
   }

   protected List getAllocListeners() {
      return Collections.emptyList();
   }

   protected List getFreeListeners() {
      return Collections.emptyList();
   }

   protected List getProtectionListeners() {
      return Collections.emptyList();
   }

   protected List getPreWriteListeners() {
      return Collections.emptyList();
   }

   protected List getWriteListeners() {
      return Collections.emptyList();
   }

   @Override
   public void addPropertyListener(IMemoryPropertyListener var1) {
      throw new UnsupportedOperationException();
   }

   @Override
   public void removePropertyListener(IMemoryPropertyListener var1) {
      throw new UnsupportedOperationException();
   }

   @Override
   public void addAllocListener(IMemoryAllocListener var1) {
      throw new UnsupportedOperationException();
   }

   @Override
   public void removeAllocListener(IMemoryAllocListener var1) {
      throw new UnsupportedOperationException();
   }

   @Override
   public void addFreeListener(IMemoryFreeListener var1) {
      throw new UnsupportedOperationException();
   }

   @Override
   public void removeFreeListener(IMemoryFreeListener var1) {
      throw new UnsupportedOperationException();
   }

   @Override
   public void addProtectionListener(IMemoryProtectionListener var1) {
      throw new UnsupportedOperationException();
   }

   @Override
   public void removeProtectionListener(IMemoryProtectionListener var1) {
      throw new UnsupportedOperationException();
   }

   @Override
   public void addPreWriteListener(IMemoryWriteListener var1) {
      throw new UnsupportedOperationException();
   }

   @Override
   public void removePreWriteListener(IMemoryWriteListener var1) {
      throw new UnsupportedOperationException();
   }

   @Override
   public void addWriteListener(IMemoryWriteListener var1) {
      throw new UnsupportedOperationException();
   }

   @Override
   public void removeWriteListener(IMemoryWriteListener var1) {
      throw new UnsupportedOperationException();
   }

   @Override
   public void setLazyMemoryProvider(ILazyMemoryProvider var1, boolean var2) throws MemoryException, UnsupportedOperationException {
      throw new UnsupportedOperationException();
   }

   @Override
   public int getAproximateFootprint() {
      throw new UnsupportedOperationException();
   }
}
