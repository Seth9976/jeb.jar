package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.units.code.asm.memory.AbstractVirtualMemory;
import com.pnfsoftware.jeb.core.units.code.asm.memory.IVirtualMemory;
import com.pnfsoftware.jeb.core.units.code.asm.memory.MemoryException;
import com.pnfsoftware.jeb.core.units.code.debug.IDebuggerVirtualMemory;
import com.pnfsoftware.jeb.util.io.Endianness;
import com.pnfsoftware.jeb.util.serialization.annotations.SerDisabled;
import java.util.Collection;

@SerDisabled
public class vV extends AbstractVirtualMemory implements IDebuggerVirtualMemory {
   ia pC;
   IDebuggerVirtualMemory A;

   vV(ia var1) {
      this.pC = var1;
      this.A = var1.A().z();
   }

   @Override
   public int getPageSize() {
      return this.A.getPageSize();
   }

   @Override
   public int getSpaceBits() {
      return this.A.getSpaceBits();
   }

   @Override
   public Endianness getStandardEndianess() {
      return this.A.getStandardEndianess();
   }

   @Override
   public void setStandardEndianness(Endianness var1) {
      this.A.setStandardEndianness(var1);
   }

   @Override
   public IVirtualMemory duplicate() {
      return this.A.duplicate();
   }

   @Override
   public boolean isValidAddress(long var1) {
      return this.A.isValidAddress(var1);
   }

   @Override
   public int getAllocatedPageCount() {
      return this.A.getAllocatedPageCount();
   }

   @Override
   public Collection getAllocatedPageBases() {
      return this.A.getAllocatedPageBases();
   }

   @Override
   public void allocate(long var1, int var3, int var4) throws MemoryException {
      this.A.allocate(var1, var3, var4);
   }

   @Override
   public void free(long var1, int var3) throws MemoryException {
      this.A.free(var1, var3);
   }

   @Override
   public void setPageProtection(long var1, int var3) throws MemoryException {
      this.A.setPageProtection(var1, var3);
   }

   @Override
   public int getPageProtection(long var1) throws MemoryException {
      return (Integer)this.pC.pC(new vW(this, var1));
   }

   @Override
   public int check(long var1, int var3, int var4) {
      return (Integer)this.pC.pC(new sR(this, var1, var3, var4));
   }

   @Override
   public int read(long var1, int var3, byte[] var4, int var5, boolean var6) throws MemoryException {
      return (Integer)this.pC.pC(new xD(this, var1, var3, var4, var5, var6));
   }

   @Override
   public int write(long var1, int var3, byte[] var4, int var5, boolean var6) throws MemoryException {
      return (Integer)this.pC.pC(new E(this, var1, var3, var4, var5, var6));
   }

   @Override
   public long findBytes(long var1, long var3, byte[] var5) {
      return (Long)this.pC.pC(new Dq(this, var1, var3, var5));
   }
}
