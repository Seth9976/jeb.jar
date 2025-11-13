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

public class cka implements ILazyMemoryProvider {
   private cjn q;
   private List RF = new ArrayList();
   private AddressSegmentMap xK = new AddressSegmentMap(64);

   public cka(cjn var1) {
      if (var1 == null) {
         throw new IllegalArgumentException();
      } else {
         this.q = var1;

         for (cjn.CU var3 : var1.q(3)) {
            long var4 = var3.Uv;
            int var6 = var3.Dw;
            this.RF.add(new Range(var4, var4 + var6, 7));
            this.xK.add(new cka.eo(var4, var6, var3));
         }
      }
   }

   @Override
   public List getRanges() {
      return this.RF;
   }

   @Override
   public void getData(long var1, int var3, byte[] var4, int var5) throws MemoryException {
      cka.eo var6 = (cka.eo)this.xK.getSegmentContaining(var1);
      int var7 = (int)(var1 - var6.getBegin());

      try {
         int var8 = this.q.q(var6.q, var7, var3, var4, var5);
         if (var8 != var3) {
            throw new IOException(Strings.ff("Read only %d bytes, expected %d", var8, var3));
         }
      } catch (IOException var9) {
         throw new MemoryException(var9);
      }
   }

   @SerDisabled
   static class eo extends LongSegment {
      cjn.CU q;

      public eo(long var1, long var3, cjn.CU var5) {
         super(var1, var3);
         this.q = var5;
      }
   }
}
