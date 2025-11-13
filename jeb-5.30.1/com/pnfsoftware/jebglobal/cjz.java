package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.units.code.asm.memory.ILazyMemoryProvider;
import com.pnfsoftware.jeb.core.units.code.asm.memory.MemoryException;
import com.pnfsoftware.jeb.core.units.code.asm.memory.Range;
import com.pnfsoftware.jeb.util.collect.AddressSegmentMap;
import com.pnfsoftware.jeb.util.collect.LongSegment;
import com.pnfsoftware.jeb.util.format.Strings;
import com.pnfsoftware.jeb.util.serialization.annotations.SerDisabled;
import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.ArrayList;
import java.util.List;

public class cjz implements ILazyMemoryProvider {
   List q = new ArrayList();
   AddressSegmentMap RF = new AddressSegmentMap(64);

   public cjz(File var1) {
      for (File var3 : com.pnfsoftware.jeb.util.io.IO.listFiles(var1)) {
         String var4 = var3.getName();
         if (var4.endsWith(".bin")) {
            long var5 = Long.parseLong(var4.substring(0, var4.length() - 4), 16);
            int var7 = (int)var3.length();
            this.q.add(new Range(var5, var5 + var7, 7));
            this.RF.add(new cjz.eo(var5, var7, var3));
         }
      }
   }

   @Override
   public List getRanges() {
      return this.q;
   }

   @Override
   public void getData(long var1, int var3, byte[] var4, int var5) throws MemoryException {
      cjz.eo var6 = (cjz.eo)this.RF.getSegmentContaining(var1);
      int var7 = (int)(var1 - var6.getBegin());

      try {
         try (RandomAccessFile var8 = new RandomAccessFile(var6.q, "r")) {
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
   static class eo extends LongSegment {
      File q;

      public eo(long var1, long var3, File var5) {
         super(var1, var3);
         this.q = var5;
      }
   }
}
