package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.units.code.asm.memory.AbstractVirtualMemory;
import com.pnfsoftware.jeb.core.units.code.asm.memory.IVirtualMemory;
import com.pnfsoftware.jeb.core.units.code.asm.memory.MemoryException;
import com.pnfsoftware.jeb.core.units.code.debug.IDebuggerVirtualMemory;
import com.pnfsoftware.jeb.util.io.Endianness;
import com.pnfsoftware.jeb.util.serialization.annotations.SerDisabled;
import java.util.Collection;

@SerDisabled
public class Rz extends AbstractVirtualMemory implements IDebuggerVirtualMemory {
   um q;
   IDebuggerVirtualMemory RF;

   Rz(um var1) {
      this.q = var1;
      this.RF = var1.RF().Ef();
   }

   @Override
   public int getPageSize() {
      return this.RF.getPageSize();
   }

   @Override
   public int getSpaceBits() {
      return this.RF.getSpaceBits();
   }

   @Override
   public Endianness getStandardEndianess() {
      return this.RF.getStandardEndianess();
   }

   @Override
   public void setStandardEndianness(Endianness var1) {
      this.RF.setStandardEndianness(var1);
   }

   @Override
   public IVirtualMemory duplicate() {
      return this.RF.duplicate();
   }

   @Override
   public boolean isValidAddress(long var1) {
      return this.RF.isValidAddress(var1);
   }

   @Override
   public int getAllocatedPageCount() {
      return this.RF.getAllocatedPageCount();
   }

   @Override
   public Collection getAllocatedPageBases() {
      return this.RF.getAllocatedPageBases();
   }

   @Override
   public void allocate(long var1, int var3, int var4) throws MemoryException {
      this.RF.allocate(var1, var3, var4);
   }

   @Override
   public void free(long var1, int var3) throws MemoryException {
      this.RF.free(var1, var3);
   }

   @Override
   public void setPageProtection(long var1, int var3) throws MemoryException {
      this.RF.setPageProtection(var1, var3);
   }

   @Override
   public int getPageProtection(long var1) throws MemoryException {
      return (Integer)this.q.q(new Kn(this, var1));
   }

   @Override
   public int check(long var1, int var3, int var4) {
      return (Integer)this.q.q(new gM(this, var1, var3, var4));
   }

   @Override
   public int read(long var1, int var3, byte[] var4, int var5, boolean var6) throws MemoryException {
      return (Integer)this.q.q(new wN(this, var1, var3, var4, var5, var6));
   }

   @Override
   public int write(long var1, int var3, byte[] var4, int var5, boolean var6) throws MemoryException {
      return (Integer)this.q.q(new iY(this, var1, var3, var4, var5, var6));
   }

   @Override
   public long findBytes(long var1, long var3, byte[] var5) {
      return (Long)this.q.q(new zp(this, var1, var3, var5));
   }
}
