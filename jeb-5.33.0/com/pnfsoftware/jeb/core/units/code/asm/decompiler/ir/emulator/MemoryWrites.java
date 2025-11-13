package com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.emulator;

import com.pnfsoftware.jeb.util.collect.BytePipe;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MemoryWrites {
   private List records = new ArrayList();
   private BytePipe storage = new BytePipe();
   private byte[] data;

   public void record(long var1, byte[] var3) {
      if (var3.length != 0) {
         int var4 = this.storage.available();
         this.storage.append(var3);
         this.records.add(new MemoryWrites.E(var1, var4, var3.length));
      }
   }

   public void complete() {
      this.data = this.storage.getAll();
      this.storage = null;
   }

   public boolean isComplete() {
      return this.data != null;
   }

   private void verifyClosed() {
      if (this.data == null) {
         throw new IllegalStateException("Recording is not complete");
      }
   }

   public int getCountOfRecords() {
      return this.records.size();
   }

   public MemoryWrites.Record getRecord(int var1) {
      this.verifyClosed();
      MemoryWrites.E var2 = (MemoryWrites.E)this.records.get(var1);
      return new MemoryWrites.Record(var2.addr, Arrays.copyOfRange(this.data, var2.offset, var2.offset + var2.size));
   }

   public byte[] getAggregatedWrittenBytes() {
      this.verifyClosed();
      return this.data;
   }

   private static class E {
      long addr;
      int offset;
      int size;

      E(long var1, int var3, int var4) {
         this.addr = var1;
         this.offset = var3;
         this.size = var4;
      }
   }

   public static class Record {
      long address;
      byte[] data;

      Record(long var1, byte[] var3) {
         this.address = var1;
         this.data = Arrays.copyOf(var3, var3.length);
      }

      public long getAddress() {
         return this.address;
      }

      public byte[] getData() {
         return this.data;
      }
   }
}
