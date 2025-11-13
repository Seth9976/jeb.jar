package com.pnfsoftware.jeb.corei.parsers.elf;

import com.pnfsoftware.jeb.core.units.codeobject.ELF;
import com.pnfsoftware.jeb.core.units.codeobject.IELFSectionEntry;
import com.pnfsoftware.jeb.core.units.codeobject.IELFUnit;
import com.pnfsoftware.jeb.util.format.Strings;
import com.pnfsoftware.jeb.util.logging.GlobalLog;
import com.pnfsoftware.jeb.util.logging.ILogger;
import com.pnfsoftware.jeb.util.serialization.annotations.Ser;
import com.pnfsoftware.jeb.util.serialization.annotations.SerId;
import java.nio.ByteBuffer;

@Ser
public class eM implements IELFSectionEntry {
   private static final ILogger zz = GlobalLog.getLogger(eM.class);
   @SerId(1)
   int q;
   @SerId(2)
   int RF;
   @SerId(3)
   long xK;
   @SerId(4)
   long Dw;
   @SerId(5)
   long Uv;
   @SerId(6)
   long oW;
   @SerId(7)
   int gO;
   @SerId(8)
   int nf;
   @SerId(9)
   long gP;
   @SerId(10)
   long za;
   @SerId(11)
   String lm = "";

   @Override
   public String getName() {
      return this.lm;
   }

   @Override
   public int getType() {
      return this.RF;
   }

   @Override
   public long getFlags() {
      return this.xK;
   }

   @Override
   public long getAddress() {
      return this.Dw;
   }

   @Override
   public long getOffset() {
      return this.Uv;
   }

   @Override
   public long getSize() {
      return this.oW;
   }

   @Override
   public long getEntrySize() {
      return this.za;
   }

   @Override
   public int getLink() {
      return this.gO;
   }

   @Override
   public int getInfo() {
      return this.nf;
   }

   @Override
   public long getAlignment() {
      return this.gP;
   }

   public static eM q(ByteBuffer var0, boolean var1) {
      eM var2 = new eM();
      if (!var1) {
         if (var0.remaining() < 40) {
            throw new RuntimeException();
         }

         var2.q = var0.getInt();
         var2.RF = var0.getInt();
         var2.xK = var0.getInt() & 4294967295L;
         var2.Dw = var0.getInt() & 4294967295L;
         var2.Uv = var0.getInt() & 4294967295L;
         var2.oW = var0.getInt() & 4294967295L;
         var2.gO = var0.getInt();
         var2.nf = var0.getInt();
         var2.gP = var0.getInt() & 4294967295L;
         var2.za = var0.getInt() & 4294967295L;
      } else {
         if (var0.remaining() < 64) {
            throw new RuntimeException();
         }

         var2.q = var0.getInt();
         var2.RF = var0.getInt();
         var2.xK = var0.getLong();
         var2.Dw = var0.getLong();
         var2.Uv = var0.getLong();
         var2.oW = var0.getLong();
         var2.gO = var0.getInt();
         var2.nf = var0.getInt();
         var2.gP = var0.getLong();
         var2.za = var0.getLong();
      }

      return var2;
   }

   public void q(String var1) {
      this.lm = Strings.safe(var1);
   }

   @Override
   public String toString() {
      return Strings.ff(
         "[%s]%s|f=%s|o=%X,s=%X(mem@%X)|link=%X,info=%X,align=%X",
         this.lm,
         ELF.getSHTString(this.RF),
         ELF.getSHFStringFlags((int)this.xK),
         this.Uv,
         this.oW,
         this.Dw,
         this.gO,
         this.nf,
         this.gP
      );
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
         "name:%-20s type:%-14s addr:0x%08X offset:0x%08X size:0x%08X es:0x%02X link:0x%02X info:0x%02X align:0x%08X",
         this.getName(),
         ELF.getSHTString(this.getType()),
         this.getAddress(),
         this.getOffset(),
         this.getSize(),
         this.getEntrySize(),
         this.getLink(),
         this.getInfo(),
         this.getAlignment()
      );
   }
}
