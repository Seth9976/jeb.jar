package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.units.code.asm.memory.ILazyMemoryProvider;
import com.pnfsoftware.jeb.core.units.code.asm.memory.MemoryException;
import com.pnfsoftware.jeb.core.units.code.asm.memory.Range;
import com.pnfsoftware.jeb.util.collect.AddressSegmentMap;
import com.pnfsoftware.jeb.util.collect.LongSegment;
import com.pnfsoftware.jeb.util.format.Strings;
import com.pnfsoftware.jeb.util.serialization.annotations.SerDisabled;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class cep implements ILazyMemoryProvider {
   private cdy pC;
   private List A = new ArrayList();
   private AddressSegmentMap kS = new AddressSegmentMap(64);

   public cep(cdy var1) {
      if (var1 == null) {
         throw new IllegalArgumentException();
      } else {
         this.pC = var1;

         for (cdy.Sv var3 : var1.pC(3)) {
            long var4 = var3.UT;
            int var6 = var3.wS;
            this.A.add(new Range(var4, var4 + var6, 7));
            this.kS.add(new cep.Av(var4, var6, var3));
         }
      }
   }

   @Override
   public List getRanges() {
      return this.A;
   }

   @Override
   public void getData(long var1, int var3, byte[] var4, int var5) throws MemoryException {
      cep.Av var6 = (cep.Av)this.kS.getSegmentContaining(var1);
      int var7 = (int)(var1 - var6.getBegin());

      try {
         int var8 = this.pC.pC(var6.pC, var7, var3, var4, var5);
         if (var8 != var3) {
            throw new IOException(Strings.ff("Read only %d bytes, expected %d", var8, var3));
         }
      } catch (IOException var9) {
         throw new MemoryException(var9);
      }
   }

   @SerDisabled
   static class Av extends LongSegment {
      cdy.Sv pC;

      public Av(long var1, long var3, cdy.Sv var5) {
         super(var1, var3);
         this.pC = var5;
      }
   }
}
