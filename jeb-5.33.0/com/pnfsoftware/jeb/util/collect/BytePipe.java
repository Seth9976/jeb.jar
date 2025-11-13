package com.pnfsoftware.jeb.util.collect;

import com.pnfsoftware.jeb.util.format.Strings;

public class BytePipe {
   private static final int pageSize = 4096;
   private byte[] buffer;
   private volatile int capacity;
   private volatile int position;
   private volatile int limit;
   private Object consumer = new Object();
   private int waitingOn = 0;

   public BytePipe() {
      this(4096);
   }

   public BytePipe(int var1) {
      if (var1 <= 0) {
         throw new IllegalArgumentException("Capacity cannot be negative");
      } else {
         this.capacity = var1;
         this.buffer = new byte[this.capacity];
         this.position = 0;
         this.limit = 0;
      }
   }

   private int round(int var1) {
      return (var1 + 4096 - 1) / 4096 * 4096;
   }

   public int capacity() {
      return this.capacity;
   }

   public int available() {
      return this.limit - this.position;
   }

   public int position() {
      return this.position;
   }

   public int limit() {
      return this.limit;
   }

   public synchronized void reset() {
      this.limit = 0;
      this.position = 0;
   }

   public synchronized void append(byte[] var1, int var2, int var3) {
      if (this.limit + var3 > this.capacity) {
         int var4 = this.available();
         if (var4 + var3 > this.capacity) {
            int var5 = this.round(2 * (var4 + var3));
            byte[] var6 = new byte[var5];
            this.copyBytes(var6, 0, this.buffer, this.position, this.limit);
            this.buffer = var6;
            this.capacity = var5;
         } else {
            this.copyBytes(this.buffer, 0, this.buffer, this.position, this.limit);
         }

         this.position = 0;
         this.limit = var4;
      }

      this.copyBytes(this.buffer, this.limit, var1, var2, var2 + var3);
      this.limit += var3;
      this.makeAvailable();
   }

   public synchronized void append(byte[] var1) {
      this.append(var1, 0, var1.length);
   }

   public synchronized void append(byte var1) {
      if (this.limit >= this.capacity) {
         int var2 = this.available();
         if (var2 >= this.capacity) {
            int var3 = this.round(2 * (var2 + 1));
            byte[] var4 = new byte[var3];
            this.copyBytes(var4, 0, this.buffer, this.position, this.limit);
            this.buffer = var4;
            this.capacity = var3;
         } else {
            this.copyBytes(this.buffer, 0, this.buffer, this.position, this.limit);
         }

         this.position = 0;
         this.limit = var2;
      }

      this.buffer[this.limit] = var1;
      this.limit++;
      this.makeAvailable();
   }

   public synchronized void append(int var1) {
      this.append((byte)var1);
   }

   public synchronized void get(byte[] var1, int var2, int var3) {
      this.verifyAvailable(var3);
      this.copyBytes(var1, var2, this.buffer, this.position, this.position + var3);
      this.position += var3;
   }

   public synchronized void get(byte[] var1) {
      this.get(var1, 0, var1.length);
   }

   public synchronized int get() {
      this.verifyAvailable(1);
      return this.buffer[this.position++] & 0xFF;
   }

   public synchronized byte[] getAll() {
      byte[] var1 = new byte[this.available()];
      this.get(var1);
      return var1;
   }

   public synchronized void peek(byte[] var1, int var2, int var3) {
      this.verifyAvailable(var3);
      this.copyBytes(var1, var2, this.buffer, this.position, this.position + var3);
   }

   public synchronized void peek(byte[] var1) {
      this.peek(var1, 0, var1.length);
   }

   public synchronized byte peek() {
      this.verifyAvailable(1);
      return this.buffer[this.position];
   }

   public synchronized void skip(int var1) {
      this.verifyAvailable(var1);
      this.position += var1;
   }

   private void verifyAvailable(int var1) {
      int var2 = this.available();
      if (var2 < var1) {
         throw new RuntimeException("Not enough bytes are available: requesting " + var1 + ", has only " + var2);
      }
   }

   private void copyBytes(byte[] var1, int var2, byte[] var3, int var4, int var5) {
      if (var1 == var3 && var2 >= var4) {
         throw new IllegalArgumentException(Strings.ff("Illegal offsets for idem-buffers: src:%d dst:%d", var4, var2));
      } else {
         System.arraycopy(var3, var4, var1, var2, var5 - var4);
      }
   }

   public int readWait(long var1) {
      if (this.available() < 1) {
         int var3 = this.blockUntilAvailable(1, var1);
         if (var3 < 1) {
            return -1;
         }
      }

      return this.get();
   }

   public int blockUntilAvailable(int var1, long var2) {
      if (var1 > 0) {
         synchronized (this.consumer) {
            this.waitingOn = var1;

            try {
               long var5 = System.nanoTime();

               while (this.available() < this.waitingOn) {
                  this.consumer.wait(var2);
                  if (var2 != 0L) {
                     long var7 = (System.nanoTime() - var5) / 1000000L;
                     if (var7 >= var2) {
                        break;
                     }
                  }
               }
            } catch (InterruptedException var10) {
            }

            this.waitingOn = 0;
         }
      }

      return this.available();
   }

   private void makeAvailable() {
      synchronized (this.consumer) {
         if (this.waitingOn > 0 && this.available() >= this.waitingOn) {
            this.consumer.notify();
         }
      }
   }
}
