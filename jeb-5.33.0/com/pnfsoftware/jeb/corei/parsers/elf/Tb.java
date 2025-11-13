package com.pnfsoftware.jeb.corei.parsers.elf;

import com.pnfsoftware.jeb.core.units.codeobject.ELF;
import com.pnfsoftware.jeb.core.units.codeobject.IELFRelocationEntry;
import com.pnfsoftware.jeb.core.units.codeobject.IELFSymbolEntry;
import com.pnfsoftware.jeb.core.units.codeobject.IELFSymbolTable;
import com.pnfsoftware.jeb.core.units.codeobject.IELFUnit;
import com.pnfsoftware.jeb.util.format.Strings;
import com.pnfsoftware.jeb.util.serialization.annotations.Ser;
import com.pnfsoftware.jeb.util.serialization.annotations.SerId;
import java.nio.ByteBuffer;

@Ser
public class Tb implements IELFRelocationEntry {
   @SerId(1)
   long pC;
   @SerId(2)
   long A;
   @SerId(3)
   long kS;
   @SerId(4)
   int wS;
   @SerId(5)
   int UT;
   @SerId(6)
   boolean E;

   public static Tb pC(ByteBuffer var0, boolean var1, boolean var2) {
      Tb var3 = new Tb();
      if (!var1) {
         var3.pC = var0.getInt() & 4294967295L;
         var3.A = var0.getInt() & 4294967295L;
         if (var2) {
            var3.E = true;
            var3.kS = var0.getInt();
         }

         var3.wS = (int)(var3.A >> 8 & 16777215L);
         var3.UT = (int)(var3.A & 255L);
      } else {
         var3.pC = var0.getLong();
         var3.A = var0.getLong();
         if (var2) {
            var3.E = true;
            var3.kS = var0.getLong();
         }

         var3.wS = (int)(var3.A >> 32 & 4294967295L);
         var3.UT = (int)(var3.A & 4294967295L);
      }

      return var3;
   }

   static Tb pC(int var0, int var1, long var2, Long var4, boolean var5) {
      Tb var6 = new Tb();
      var6.UT = var0;
      var6.wS = var1;
      var6.pC = var2;
      if (var4 != null) {
         var6.E = true;
         var6.kS = var4;
      }

      if (!var5) {
         var6.A = var0 & 255L | var1 << 8 & 4294967040L;
      } else {
         var6.A = var0 & 4294967295L | var1 << 32 & -4294967296L;
      }

      return var6;
   }

   @Override
   public String toString() {
      String var1 = Strings.ff("type=%d|symIndex=%d|offset=0x%X", this.UT, this.wS, this.pC);
      if (this.kS != 0L) {
         var1 = var1 + Strings.ff("|addend=0x%X", this.kS);
      }

      return var1;
   }

   @Override
   public long getOffset() {
      return this.pC;
   }

   @Override
   public long getInfo() {
      return this.A;
   }

   @Override
   public long getAddend() {
      return this.kS;
   }

   @Override
   public int getSymbolIndex() {
      return this.wS;
   }

   @Override
   public int getType() {
      return this.UT;
   }

   @Override
   public boolean isAddendSet() {
      return this.E;
   }

   @Override
   public String format(IELFUnit var1, IELFSymbolTable var2) {
      StringBuilder var3 = new StringBuilder();
      this.pC(var1, var2, var3);
      return var3.toString();
   }

   public void pC(IELFUnit var1, IELFSymbolTable var2, StringBuilder var3) {
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
