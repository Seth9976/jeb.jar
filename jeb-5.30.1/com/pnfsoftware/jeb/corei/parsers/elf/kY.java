package com.pnfsoftware.jeb.corei.parsers.elf;

import com.pnfsoftware.jeb.core.units.codeobject.ELF;
import com.pnfsoftware.jeb.core.units.codeobject.IELFProgramEntry;
import com.pnfsoftware.jeb.core.units.codeobject.IELFUnit;
import com.pnfsoftware.jeb.util.format.Strings;
import com.pnfsoftware.jeb.util.serialization.annotations.Ser;
import com.pnfsoftware.jeb.util.serialization.annotations.SerId;
import java.nio.ByteBuffer;

@Ser
public class kY implements IELFProgramEntry {
   @SerId(1)
   int q;
   @SerId(2)
   long RF;
   @SerId(3)
   long xK;
   @SerId(4)
   private long nf;
   @SerId(5)
   long Dw;
   @SerId(6)
   long Uv;
   @SerId(7)
   int oW;
   @SerId(8)
   long gO;

   @Override
   public int getType() {
      return this.q;
   }

   @Override
   public long getOffset() {
      return this.RF;
   }

   @Override
   public long getVirtualAddress() {
      return this.xK;
   }

   @Override
   public long getPhysicalAddress() {
      return this.nf;
   }

   @Override
   public long getSize() {
      return this.Dw;
   }

   @Override
   public long getVirtualSize() {
      return this.Uv;
   }

   @Override
   public long getAlignment() {
      return this.gO;
   }

   @Override
   public int getFlags() {
      return this.oW;
   }

   public static kY q(ByteBuffer var0, boolean var1) {
      kY var2 = new kY();
      if (!var1) {
         if (var0.remaining() < 32) {
            throw new RuntimeException();
         }

         var2.q = var0.getInt();
         var2.RF = var0.getInt() & 4294967295L;
         var2.xK = var0.getInt() & 4294967295L;
         var2.nf = var0.getInt() & 4294967295L;
         var2.Dw = var0.getInt() & 4294967295L;
         var2.Uv = var0.getInt() & 4294967295L;
         var2.oW = var0.getInt();
         var2.gO = var0.getInt() & 4294967295L;
      } else {
         if (var0.remaining() < 56) {
            throw new RuntimeException();
         }

         var2.q = var0.getInt();
         var2.oW = var0.getInt();
         var2.RF = var0.getLong();
         var2.xK = var0.getLong();
         var2.nf = var0.getLong();
         var2.Dw = var0.getLong();
         var2.Uv = var0.getLong();
         var2.gO = var0.getLong();
      }

      return var2;
   }

   @Override
   public String toString() {
      return Strings.ff("%s,%s|o=%X,s=%X|va=%X,vs=%X", ELF.getPTString(this.q), ELF.getPFString(this.oW), this.RF, this.Dw, this.xK, this.Uv);
   }

   @Override
   public String format(IELFUnit var1) {
      StringBuilder var2 = new StringBuilder();
      this.q(var1, var2);
      return var2.toString();
   }

   public void q(IELFUnit var1, StringBuilder var2) {
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
