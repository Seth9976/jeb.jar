package com.pnfsoftware.jeb.corei.parsers.elf;

import com.pnfsoftware.jeb.core.units.codeobject.ELF;
import com.pnfsoftware.jeb.core.units.codeobject.IELFSymbolEntry;
import com.pnfsoftware.jeb.core.units.codeobject.IELFUnit;
import com.pnfsoftware.jeb.util.format.Strings;
import com.pnfsoftware.jeb.util.serialization.annotations.Ser;
import com.pnfsoftware.jeb.util.serialization.annotations.SerId;
import java.nio.ByteBuffer;

@Ser
public class nA implements IELFSymbolEntry {
   @SerId(1)
   int pC;
   @SerId(2)
   long A;
   @SerId(3)
   long kS;
   @SerId(4)
   int wS;
   @SerId(5)
   int UT;
   @SerId(11)
   int E;
   @SerId(6)
   int sY;
   @SerId(7)
   int ys;
   @SerId(8)
   String ld;
   @SerId(9)
   long gp;
   @SerId(12)
   int oT;
   @SerId(10)
   boolean fI;

   public static nA pC(ByteBuffer var0, boolean var1) {
      if (var0.remaining() < (var1 ? 24 : 16)) {
         return null;
      } else {
         nA var2 = new nA();
         if (!var1) {
            var2.pC = var0.getInt();
            var2.A = var0.getInt() & 4294967295L;
            var2.kS = var0.getInt() & 4294967295L;
            var2.wS = var0.get() & 255;
            var2.E = var0.get() & 255;
            var2.UT = var0.getShort() & '\uffff';
         } else {
            var2.pC = var0.getInt();
            var2.wS = var0.get() & 255;
            var2.E = var0.get() & 255;
            var2.UT = var0.getShort() & '\uffff';
            var2.A = var0.getLong();
            var2.kS = var0.getLong();
         }

         var2.sY = var2.wS & 15;
         var2.ys = var2.wS >> 4 & 15;
         var2.ld = "@" + Integer.toHexString(var2.pC);
         var2.oT = var2.E & 3;
         return var2;
      }
   }

   @Override
   public long getValue() {
      return this.A;
   }

   @Override
   public long getSize() {
      return this.kS;
   }

   @Override
   public int getType() {
      return this.sY;
   }

   @Override
   public int getBinding() {
      return this.ys;
   }

   @Override
   public int getVisibility() {
      return this.oT;
   }

   @Override
   public String getName() {
      return this.ld;
   }

   public void pC(String var1) {
      this.ld = Strings.safe(var1);
   }

   @Override
   public long getAddress() {
      return this.gp;
   }

   @Override
   public boolean isExternal() {
      return this.fI;
   }

   @Override
   public int getShIndex() {
      return this.UT;
   }

   @Override
   public String toString() {
      return Strings.ff(
         "name=%s|value=0x%X|size=0x%X|type=%s|bnd=%s|shIndex=%d|address=%x",
         this.ld,
         this.A,
         this.kS,
         ELF.getSTTString(this.sY),
         ELF.getSTBString(this.ys),
         this.UT,
         this.gp
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
         "val:0x%08X, size:0x%08X type:%-10s bind:%-10s visi:%-10s sect:0x%02X('%s') name:'%s'",
         this.getValue(),
         this.getSize(),
         ELF.getSTTString(this.getType()),
         ELF.getSTBString(this.getBinding()),
         ELF.getSTVString(this.getVisibility()),
         this.getShIndex(),
         var1.getSectionName(this.getShIndex()),
         this.getName()
      );
   }
}
