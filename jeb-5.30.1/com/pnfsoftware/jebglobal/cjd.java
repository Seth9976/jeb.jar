package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.units.code.asm.memory.IVirtualMemory;
import com.pnfsoftware.jeb.core.units.code.asm.memory.MemoryException;
import com.pnfsoftware.jeb.util.encoding.LEB128;

public class cjd implements cjc {
   private cjb q;
   private long RF = 0L;
   private long xK = 0L;
   private int Dw = 0;

   public cjd(cjb var1, long var2, int var4) {
      this.q = var1;
      this.RF = var2;
      this.xK = var2;
      this.Dw = var4;
   }

   @Override
   public int q() throws MemoryException {
      int var1 = this.q.readByte(this.xK) & 255;
      this.xK++;
      return var1;
   }

   @Override
   public int RF() throws MemoryException {
      int var1 = this.q.readShort(this.xK) & '\uffff';
      this.xK += 2L;
      return var1;
   }

   @Override
   public long xK() throws MemoryException {
      long var1 = this.q.readInt(this.xK) & -1;
      this.xK += 4L;
      return var1;
   }

   @Override
   public byte Dw() throws MemoryException {
      byte var1 = this.q.readByte(this.xK);
      this.xK++;
      return var1;
   }

   @Override
   public short Uv() throws MemoryException {
      short var1 = this.q.readShort(this.xK);
      this.xK += 2L;
      return var1;
   }

   @Override
   public int oW() throws MemoryException {
      int var1 = this.q.readInt(this.xK);
      this.xK += 4L;
      return var1;
   }

   @Override
   public long gO() throws MemoryException {
      long var1 = this.q.readLong(this.xK);
      this.xK += 8L;
      return var1;
   }

   @Override
   public long q(int var1) throws MemoryException {
      switch (var1) {
         case 1:
            return this.Dw();
         case 2:
            return this.Uv();
         case 3:
         case 5:
         case 6:
         case 7:
         default:
            throw new MemoryException("Illegal size " + var1);
         case 4:
            return this.oW();
         case 8:
            return this.gO();
      }
   }

   @Override
   public long RF(int var1) throws MemoryException {
      switch (var1) {
         case 1:
            return this.q();
         case 2:
            return this.RF();
         case 3:
         case 5:
         case 6:
         case 7:
         default:
            throw new MemoryException("Illegal size " + var1);
         case 4:
            return this.xK();
         case 8:
            return this.gO();
      }
   }

   @Override
   public long nf() throws MemoryException {
      return this.q(true);
   }

   @Override
   public long gP() throws MemoryException {
      return this.q(false);
   }

   private long q(boolean var1) throws MemoryException {
      int var2 = Math.min(10, this.zz());
      byte[] var3 = new byte[var2];
      this.q.read(this.xK, var2, var3, 0);
      LEB128.Value var4 = var1 ? LEB128.read_int64(var3, 0) : LEB128.read_uint64(var3, 0);
      this.xK = this.xK + var4.getEncodingLength();
      return var4.get();
   }

   @Override
   public byte[] xK(int var1) throws MemoryException {
      byte[] var2 = new byte[var1];
      this.q.read(this.xK, var1, var2, 0);
      this.xK += var1;
      return var2;
   }

   @Override
   public long za() {
      return this.xK;
   }

   @Override
   public long lm() {
      return this.xK - this.RF;
   }

   @Override
   public int zz() {
      return Math.abs((int)(this.RF + this.Dw - this.xK));
   }

   @Override
   public void q(long var1) {
      this.xK = this.RF + var1;
   }

   @Override
   public void RF(long var1) throws MemoryException {
      if (var1 < 0L) {
         throw new MemoryException("Illegal size " + var1);
      } else {
         this.xK += var1;
         if (this.xK < 0L) {
            throw new MemoryException("Lost address");
         }
      }
   }

   @Override
   public IVirtualMemory JY() {
      return this.q;
   }

   @Override
   public boolean xK(long var1) {
      return this.q.q(this.RF + var1);
   }
}
