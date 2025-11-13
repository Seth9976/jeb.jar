package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.units.code.asm.memory.IVirtualMemory;
import com.pnfsoftware.jeb.core.units.code.asm.memory.MemoryException;

public interface cdu {
   int pC() throws MemoryException;

   int A() throws MemoryException;

   long kS() throws MemoryException;

   byte wS() throws MemoryException;

   long UT() throws MemoryException;

   long pC(int var1) throws MemoryException;

   long E() throws MemoryException;

   long sY() throws MemoryException;

   byte[] A(int var1) throws MemoryException;

   void pC(long var1);

   long ys();

   long ld();

   int gp();

   IVirtualMemory oT();

   boolean A(long var1);
}
