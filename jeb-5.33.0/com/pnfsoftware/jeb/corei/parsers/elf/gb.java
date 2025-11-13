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
public class gb implements IELFSectionEntry {
   private static final ILogger fI = GlobalLog.getLogger(gb.class);
   @SerId(1)
   public int pC;
   @SerId(2)
   public int A;
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
   public int ys;
   @SerId(9)
   public long ld;
   @SerId(10)
   public long gp;
   @SerId(11)
   public String oT = "";

   @Override
   public String getName() {
      return this.oT;
   }

   @Override
   public int getType() {
      return this.A;
   }

   @Override
   public long getFlags() {
      return this.kS;
   }

   @Override
   public long getAddress() {
      return this.wS;
   }

   @Override
   public long getOffset() {
      return this.UT;
   }

   @Override
   public long getSize() {
      return this.E;
   }

   @Override
   public long getEntrySize() {
      return this.gp;
   }

   @Override
   public int getLink() {
      return this.sY;
   }

   @Override
   public int getInfo() {
      return this.ys;
   }

   @Override
   public long getAlignment() {
      return this.ld;
   }

   public static gb pC(ByteBuffer var0, boolean var1) {
      gb var2 = new gb();
      if (!var1) {
         if (var0.remaining() < 40) {
            throw new RuntimeException();
         }

         var2.pC = var0.getInt();
         var2.A = var0.getInt();
         var2.kS = var0.getInt() & 4294967295L;
         var2.wS = var0.getInt() & 4294967295L;
         var2.UT = var0.getInt() & 4294967295L;
         var2.E = var0.getInt() & 4294967295L;
         var2.sY = var0.getInt();
         var2.ys = var0.getInt();
         var2.ld = var0.getInt() & 4294967295L;
         var2.gp = var0.getInt() & 4294967295L;
      } else {
         if (var0.remaining() < 64) {
            throw new RuntimeException();
         }

         var2.pC = var0.getInt();
         var2.A = var0.getInt();
         var2.kS = var0.getLong();
         var2.wS = var0.getLong();
         var2.UT = var0.getLong();
         var2.E = var0.getLong();
         var2.sY = var0.getInt();
         var2.ys = var0.getInt();
         var2.ld = var0.getLong();
         var2.gp = var0.getLong();
      }

      return var2;
   }

   public void pC(String var1) {
      this.oT = Strings.safe(var1);
   }

   @Override
   public String toString() {
      return Strings.ff(
         "[%s]%s|f=%s|o=%X,s=%X(mem@%X)|link=%X,info=%X,align=%X",
         this.oT,
         ELF.getSHTString(this.A),
         ELF.getSHFStringFlags((int)this.kS),
         this.UT,
         this.E,
         this.wS,
         this.sY,
         this.ys,
         this.ld
      );
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
