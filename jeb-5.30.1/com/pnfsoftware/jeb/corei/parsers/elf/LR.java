package com.pnfsoftware.jeb.corei.parsers.elf;

import com.pnfsoftware.jeb.core.units.codeobject.ELF;
import com.pnfsoftware.jeb.core.units.codeobject.IELFSymbolEntry;
import com.pnfsoftware.jeb.core.units.codeobject.IELFUnit;
import com.pnfsoftware.jeb.util.format.Strings;
import com.pnfsoftware.jeb.util.serialization.annotations.Ser;
import com.pnfsoftware.jeb.util.serialization.annotations.SerId;
import java.nio.ByteBuffer;

@Ser
public class LR implements IELFSymbolEntry {
   @SerId(1)
   int q;
   @SerId(2)
   long RF;
   @SerId(3)
   long xK;
   @SerId(4)
   int Dw;
   @SerId(5)
   int Uv;
   @SerId(11)
   int oW;
   @SerId(6)
   int gO;
   @SerId(7)
   int nf;
   @SerId(8)
   String gP;
   @SerId(9)
   long za;
   @SerId(12)
   int lm;
   @SerId(10)
   boolean zz;

   public static LR q(ByteBuffer var0, boolean var1) {
      if (var0.remaining() < (var1 ? 24 : 16)) {
         return null;
      } else {
         LR var2 = new LR();
         if (!var1) {
            var2.q = var0.getInt();
            var2.RF = var0.getInt() & 4294967295L;
            var2.xK = var0.getInt() & 4294967295L;
            var2.Dw = var0.get() & 255;
            var2.oW = var0.get() & 255;
            var2.Uv = var0.getShort() & '\uffff';
         } else {
            var2.q = var0.getInt();
            var2.Dw = var0.get() & 255;
            var2.oW = var0.get() & 255;
            var2.Uv = var0.getShort() & '\uffff';
            var2.RF = var0.getLong();
            var2.xK = var0.getLong();
         }

         var2.gO = var2.Dw & 15;
         var2.nf = var2.Dw >> 4 & 15;
         var2.gP = "@" + Integer.toHexString(var2.q);
         var2.lm = var2.oW & 3;
         return var2;
      }
   }

   @Override
   public long getValue() {
      return this.RF;
   }

   @Override
   public long getSize() {
      return this.xK;
   }

   @Override
   public int getType() {
      return this.gO;
   }

   @Override
   public int getBinding() {
      return this.nf;
   }

   @Override
   public int getVisibility() {
      return this.lm;
   }

   @Override
   public String getName() {
      return this.gP;
   }

   public void q(String var1) {
      this.gP = Strings.safe(var1);
   }

   @Override
   public long getAddress() {
      return this.za;
   }

   @Override
   public boolean isExternal() {
      return this.zz;
   }

   @Override
   public int getShIndex() {
      return this.Uv;
   }

   @Override
   public String toString() {
      return Strings.ff(
         "name=%s|value=0x%X|size=0x%X|type=%s|bnd=%s|shIndex=%d|address=%x",
         this.gP,
         this.RF,
         this.xK,
         ELF.getSTTString(this.gO),
         ELF.getSTBString(this.nf),
         this.Uv,
         this.za
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
