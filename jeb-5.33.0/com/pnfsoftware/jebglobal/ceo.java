package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.units.code.asm.memory.ILazyMemoryProvider;
import com.pnfsoftware.jeb.core.units.code.asm.memory.MemoryException;
import com.pnfsoftware.jeb.util.collect.AddressSegmentMap;
import com.pnfsoftware.jeb.util.collect.LongSegment;
import com.pnfsoftware.jeb.util.format.Strings;
import com.pnfsoftware.jeb.util.serialization.annotations.SerDisabled;
import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.List;

public class ceo implements ILazyMemoryProvider {
   List pC;
   AddressSegmentMap A;

   @Override
   public List getRanges() {
      return this.pC;
   }

   @Override
   public void getData(long var1, int var3, byte[] var4, int var5) throws MemoryException {
      ceo.Av var6 = (ceo.Av)this.A.getSegmentContaining(var1);
      int var7 = (int)(var1 - var6.getBegin());

      try {
         try (RandomAccessFile var8 = new RandomAccessFile(var6.pC, "r")) {
            var8.seek(var7);
            int var9 = var8.read(var4, var5, var3);
            if (var9 != var3) {
               throw new IOException(Strings.ff("Read only %d bytes, expected %d", var9, var3));
            }
         }
      } catch (IOException var13) {
         throw new MemoryException(var13);
      }
   }

   @SerDisabled
   static class Av extends LongSegment {
      File pC;
   }
}
