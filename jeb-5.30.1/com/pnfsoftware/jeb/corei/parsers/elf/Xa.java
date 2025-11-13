package com.pnfsoftware.jeb.corei.parsers.elf;

import com.pnfsoftware.jeb.core.units.codeobject.ELF;
import com.pnfsoftware.jeb.core.units.codeobject.IELFRelocationEntry;
import com.pnfsoftware.jeb.core.units.codeobject.IELFSymbolEntry;
import com.pnfsoftware.jeb.core.units.codeobject.IELFSymbolTable;
import com.pnfsoftware.jeb.core.units.codeobject.IELFUnit;
import com.pnfsoftware.jeb.core.units.codeobject.ProcessorType;
import com.pnfsoftware.jeb.util.format.Strings;
import com.pnfsoftware.jeb.util.serialization.annotations.Ser;
import com.pnfsoftware.jeb.util.serialization.annotations.SerId;
import java.nio.ByteBuffer;

@Ser
public class Xa implements IELFRelocationEntry {
   @SerId(1)
   long q;
   @SerId(2)
   long RF;
   @SerId(3)
   long xK;
   @SerId(4)
   int Dw;
   @SerId(5)
   int Uv;
   @SerId(6)
   boolean oW;

   public static Xa q(ByteBuffer var0, boolean var1, boolean var2) {
      Xa var3 = new Xa();
      if (!var1) {
         var3.q = var0.getInt() & 4294967295L;
         var3.RF = var0.getInt() & 4294967295L;
         if (var2) {
            var3.oW = true;
            var3.xK = var0.getInt();
         }

         var3.Dw = (int)(var3.RF >> 8 & 16777215L);
         var3.Uv = (int)(var3.RF & 255L);
      } else {
         var3.q = var0.getLong();
         var3.RF = var0.getLong();
         if (var2) {
            var3.oW = true;
            var3.xK = var0.getLong();
         }

         var3.Dw = (int)(var3.RF >> 32 & 4294967295L);
         var3.Uv = (int)(var3.RF & 4294967295L);
      }

      return var3;
   }

   public String q(ProcessorType var1) {
      String var2 = Strings.ff("type=%s|symIndex=%d|offset=0x%X", ELF.getRTString(var1, this.Uv), this.Dw, this.q);
      if (this.xK != 0L) {
         var2 = var2 + Strings.ff("|addend=0x%X", this.xK);
      }

      return var2;
   }

   @Override
   public String toString() {
      String var1 = Strings.ff("type=%d|symIndex=%d|offset=0x%X", this.Uv, this.Dw, this.q);
      if (this.xK != 0L) {
         var1 = var1 + Strings.ff("|addend=0x%X", this.xK);
      }

      return var1;
   }

   @Override
   public long getOffset() {
      return this.q;
   }

   @Override
   public long getInfo() {
      return this.RF;
   }

   @Override
   public long getAddend() {
      return this.xK;
   }

   @Override
   public int getSymbolIndex() {
      return this.Dw;
   }

   @Override
   public int getType() {
      return this.Uv;
   }

   @Override
   public boolean isAddendSet() {
      return this.oW;
   }

   @Override
   public String format(IELFUnit var1, IELFSymbolTable var2) {
      StringBuilder var3 = new StringBuilder();
      this.q(var1, var2, var3);
      return var3.toString();
   }

   public void q(IELFUnit var1, IELFSymbolTable var2, StringBuilder var3) {
      Strings.ff(var3, "offset:0x%08X type:%-10s", this.getOffset(), ELF.getRTString(var1.getLoaderInformation().getTargetProcessor(), this.getType()));
      if (this.isAddendSet()) {
         Strings.ff(var3, " addend:0x%08X", this.getAddend());
      }

      if (this.getSymbolIndex() != 0) {
         Strings.ff(var3, " symbol:0x%04X", this.getSymbolIndex());
         if (var2 != null) {
            int var4 = this.getSymbolIndex();
            if (var4 >= 1) {
               if (var4 >= 0 && var4 < var2.getCountOfEntries()) {
                  IELFSymbolEntry var5 = var2.getEntry(this.getSymbolIndex());
                  Strings.ff(var3, "(name:'%s',value:0x%X,visi:%d)", var5.getName(), var5.getValue(), var5.getVisibility());
               } else {
                  Strings.ff(var3, "(bad index for symbol entry: %d - count is: %d)", var4, var2.getCountOfEntries());
               }
            }
         }
      }
   }
}
