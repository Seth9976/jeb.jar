package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.units.code.asm.memory.IVirtualMemory;
import com.pnfsoftware.jeb.core.units.code.asm.memory.MemoryException;
import com.pnfsoftware.jeb.util.encoding.LEB128;

public class cdv implements cdu {
   private cdt pC;
   private long A = 0L;
   private long kS = 0L;
   private int wS = 0;

   public cdv(cdt var1, long var2, int var4) {
      this.pC = var1;
      this.A = var2;
      this.kS = var2;
      this.wS = var4;
   }

   @Override
   public int pC() throws MemoryException {
      int var1 = this.pC.readByte(this.kS) & 255;
      this.kS++;
      return var1;
   }

   @Override
   public int A() throws MemoryException {
      int var1 = this.pC.readShort(this.kS) & '\uffff';
      this.kS += 2L;
      return var1;
   }

   @Override
   public long kS() throws MemoryException {
      long var1 = this.pC.readInt(this.kS) & -1;
      this.kS += 4L;
      return var1;
   }

   @Override
   public byte wS() throws MemoryException {
      byte var1 = this.pC.readByte(this.kS);
      this.kS++;
      return var1;
   }

   @Override
   public long UT() throws MemoryException {
      long var1 = this.pC.readLong(this.kS);
      this.kS += 8L;
      return var1;
   }

   @Override
   public long pC(int var1) throws MemoryException {
      switch (var1) {
         case 1:
            return this.pC();
         case 2:
            return this.A();
         case 3:
         case 5:
         case 6:
         case 7:
         default:
            throw new MemoryException("Illegal size " + var1);
         case 4:
            return this.kS();
         case 8:
            return this.UT();
      }
   }

   @Override
   public long E() throws MemoryException {
      return this.pC(true);
   }

   @Override
   public long sY() throws MemoryException {
      return this.pC(false);
   }

   private long pC(boolean var1) throws MemoryException {
      int var2 = Math.min(10, this.gp());
      byte[] var3 = new byte[var2];
      this.pC.read(this.kS, var2, var3, 0);
      LEB128.Value var4 = var1 ? LEB128.read_int64(var3, 0) : LEB128.read_uint64(var3, 0);
      this.kS = this.kS + var4.getEncodingLength();
      return var4.get();
   }

   @Override
   public byte[] A(int var1) throws MemoryException {
      byte[] var2 = new byte[var1];
      this.pC.read(this.kS, var1, var2, 0);
      this.kS += var1;
      return var2;
   }

   @Override
   public long ys() {
      return this.kS;
   }

   @Override
   public long ld() {
      return this.kS - this.A;
   }

   @Override
   public int gp() {
      return Math.abs((int)(this.A + this.wS - this.kS));
   }

   @Override
   public void pC(long var1) {
      this.kS = this.A + var1;
   }

   @Override
   public IVirtualMemory oT() {
      return this.pC;
   }

   @Override
   public boolean A(long var1) {
      return this.pC.pC(this.A + var1);
   }
}
