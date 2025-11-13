package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.input.IInput;
import com.pnfsoftware.jeb.core.units.code.asm.memory.AbstractVirtualMemory;
import com.pnfsoftware.jeb.core.units.code.asm.memory.IVirtualMemory;
import com.pnfsoftware.jeb.core.units.code.asm.memory.MemoryException;
import com.pnfsoftware.jeb.util.io.Endianness;
import com.pnfsoftware.jeb.util.serialization.annotations.Ser;
import com.pnfsoftware.jeb.util.serialization.annotations.SerId;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.SeekableByteChannel;
import java.util.Collection;
import java.util.TreeMap;

@Ser
public class cjb extends AbstractVirtualMemory {
   @SerId(1)
   private int q;
   @SerId(2)
   private Endianness RF;
   @SerId(4)
   private SeekableByteChannel xK;
   @SerId(5)
   private TreeMap Dw = new TreeMap();

   public cjb(int var1, Endianness var2, IInput var3) throws IOException {
      this(var1, var2, var3.getChannel());
   }

   public cjb(int var1, Endianness var2, SeekableByteChannel var3) {
      if (var1 <= 64) {
         this.q = var1;
      } else {
         this.q = 64;
         if (var1 % 8 != 0) {
            throw new IllegalArgumentException("The memory space requested is greater than 64-bit, it must be a multiple of 8");
         }
      }

      this.RF = var2;
      this.xK = var3;
   }

   public void q() throws IOException {
      this.xK.close();
   }

   @Override
   public int getSpaceBits() {
      return this.q;
   }

   @Override
   public Endianness getStandardEndianess() {
      return this.RF;
   }

   @Override
   public void setStandardEndianness(Endianness var1) {
      this.RF = var1;
   }

   @Override
   public boolean isValidAddress(long var1) {
      try {
         return var1 < this.xK.size();
      } catch (IOException var3) {
         return false;
      }
   }

   @Override
   public int read(long var1, int var3, byte[] var4, int var5, boolean var6) throws MemoryException {
      try {
         this.xK.position(var1);
         ByteBuffer var7 = ByteBuffer.allocate(var3);
         int var8 = this.xK.read(var7);
         var7.rewind();
         var7.get(var4, var5, var8);

         for (Long var9 = (Long)this.Dw.lowerKey(var1 + var3);
            var9 != null && var9 + ((byte[])this.Dw.get(var9)).length > var1;
            var9 = (Long)this.Dw.lowerKey(var9)
         ) {
            byte[] var10 = (byte[])this.Dw.get(var9);

            for (int var11 = 0; var11 < var10.length; var11++) {
               if (var9 + var11 >= var1 && var9 + var11 < var1 + var3) {
                  var4[(int)(var9 + var11 - var1)] = var10[var11];
               }
            }
         }

         return var8;
      } catch (IOException var12) {
         throw new MemoryException(var12);
      }
   }

   public boolean q(long var1) {
      Long var3 = (Long)this.Dw.floorKey(var1);
      return var3 != null && var3 + ((byte[])this.Dw.get(var3)).length > var1;
   }

   @Override
   public int write(long var1, int var3, byte[] var4, int var5, boolean var6) throws MemoryException {
      byte[] var7 = new byte[var5 + var3];
      System.arraycopy(var4, var5, var7, 0, var3);
      this.Dw.put(var1, var7);
      return var7.length;
   }

   @Override
   public IVirtualMemory duplicate() {
      return new cjb(this.q, this.RF, this.xK);
   }

   @Override
   public int getPageSize() {
      return 0;
   }

   @Override
   public int getAllocatedPageCount() {
      return 0;
   }

   @Override
   public Collection getAllocatedPageBases() {
      return null;
   }

   @Override
   public void allocate(long var1, int var3, int var4) throws MemoryException {
   }

   @Override
   public void free(long var1, int var3) throws MemoryException {
   }

   @Override
   public void setPageProtection(long var1, int var3) throws MemoryException {
   }

   @Override
   public int getPageProtection(long var1) throws MemoryException {
      return 0;
   }

   @Override
   public int check(long var1, int var3, int var4) {
      try {
         return this.xK.size() - var1 >= var3 ? var3 : (int)(this.xK.size() - var1);
      } catch (IOException var5) {
         return 0;
      }
   }

   public int q(long var1, ByteBuffer var3) throws IOException {
      this.xK.position(var1);
      return this.xK.read(var3);
   }
}
