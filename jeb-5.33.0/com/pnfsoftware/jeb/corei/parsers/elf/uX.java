package com.pnfsoftware.jeb.corei.parsers.elf;

import com.pnfsoftware.jeb.core.units.codeobject.ELF;
import com.pnfsoftware.jeb.core.units.codeobject.IELFProgramEntry;
import com.pnfsoftware.jeb.core.units.codeobject.IELFUnit;
import com.pnfsoftware.jeb.util.format.Strings;
import com.pnfsoftware.jeb.util.serialization.annotations.Ser;
import com.pnfsoftware.jeb.util.serialization.annotations.SerId;
import java.nio.ByteBuffer;

@Ser
public class uX implements IELFProgramEntry {
   @SerId(1)
   public int pC;
   @SerId(2)
   public long A;
   @SerId(3)
   public long kS;
   @SerId(4)
   public long wS;
   @SerId(5)
   public long UT;
   @SerId(6)
   public long E;
   @SerId(7)
   public int sY;
   @SerId(8)
   public long ys;

   @Override
   public int getType() {
      return this.pC;
   }

   @Override
   public long getOffset() {
      return this.A;
   }

   @Override
   public long getVirtualAddress() {
      return this.kS;
   }

   @Override
   public long getPhysicalAddress() {
      return this.wS;
   }

   @Override
   public long getSize() {
      return this.UT;
   }

   @Override
   public long getVirtualSize() {
      return this.E;
   }

   @Override
   public long getAlignment() {
      return this.ys;
   }

   @Override
   public int getFlags() {
      return this.sY;
   }

   public static uX pC(ByteBuffer var0, boolean var1) {
      uX var2 = new uX();
      if (!var1) {
         if (var0.remaining() < 32) {
            throw new RuntimeException();
         }

         var2.pC = var0.getInt();
         var2.A = var0.getInt() & 4294967295L;
         var2.kS = var0.getInt() & 4294967295L;
         var2.wS = var0.getInt() & 4294967295L;
         var2.UT = var0.getInt() & 4294967295L;
         var2.E = var0.getInt() & 4294967295L;
         var2.sY = var0.getInt();
         var2.ys = var0.getInt() & 4294967295L;
      } else {
         if (var0.remaining() < 56) {
            throw new RuntimeException();
         }

         var2.pC = var0.getInt();
         var2.sY = var0.getInt();
         var2.A = var0.getLong();
         var2.kS = var0.getLong();
         var2.wS = var0.getLong();
         var2.UT = var0.getLong();
         var2.E = var0.getLong();
         var2.ys = var0.getLong();
      }

      return var2;
   }

   @Override
   public String toString() {
      return Strings.ff("%s,%s|o=%X,s=%X|va=%X,vs=%X", ELF.getPTString(this.pC), ELF.getPFString(this.sY), this.A, this.UT, this.kS, this.E);
   }

   @Override
   public String format(IELFUnit var1) {
      StringBuilder var2 = new StringBuilder();
      this.pC(var1, var2);
      return var2.toString();
   }

   public void pC(IELFUnit var1, StringBuilder var2) {
      Strings.ff(
         var2,
         "type:%-14s off:0x%08X va:0x%08X pa:0x%08X size:0x%08X memsize:0x%08X align:0x%08X flags:%s",
         ELF.getPTString(this.getType()),
         this.getOffset(),
         this.getVirtualAddress(),
         this.getPhysicalAddress(),
         this.getSize(),
         this.getVirtualSize(),
         this.getAlignment(),
         ELF.getPFString(this.getFlags())
      );
   }
}
