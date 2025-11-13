package com.pnfsoftware.jeb.corei.parsers.elf;

import com.pnfsoftware.jeb.core.units.codeobject.IELFRelocationEntry;
import com.pnfsoftware.jeb.core.units.codeobject.IELFRelocationTable;
import com.pnfsoftware.jeb.core.units.codeobject.IELFUnit;
import com.pnfsoftware.jeb.util.format.Strings;
import com.pnfsoftware.jeb.util.serialization.annotations.Ser;
import com.pnfsoftware.jeb.util.serialization.annotations.SerId;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.List;

@Ser
public class ma implements IELFRelocationTable {
   @SerId(1)
   List pC = new ArrayList();
   @SerId(2)
   int A;
   @SerId(3)
   int kS;
   @SerId(4)
   int wS;
   @SerId(5)
   long UT;

   public static ma pC(ByteBuffer var0, boolean var1, boolean var2) {
      ma var3 = new ma();

      while (var0.remaining() > 0) {
         var3.pC.add(Tb.pC(var0, var1, var2));
      }

      return var3;
   }

   @Override
   public int getCountOfEntries() {
      return this.pC.size();
   }

   @Override
   public List getEntries() {
      return this.pC;
   }

   @Override
   public IELFRelocationEntry getEntry(int var1) {
      return (IELFRelocationEntry)this.pC.get(var1);
   }

   @Override
   public int getSectionIndex() {
      return this.A;
   }

   @Override
   public int getSymbolSectionIndex() {
      return this.kS;
   }

   @Override
   public int getTargetSectionIndex() {
      return this.wS;
   }

   @Override
   public long getFileOffset() {
      return this.UT;
   }

   @Override
   public String toString() {
      StringBuilder var1 = new StringBuilder();
      Strings.ff(var1, "link index (symbols section)= %d\n", this.kS);
      Strings.ff(var1, "info index (target section for relos)= %d\n", this.wS);
      int var2 = 0;

      for (Tb var4 : this.pC) {
         if (var2 >= 1) {
            var1.append('\n');
         }

         var1.append("  ");
         var1.append(var4);
         var2++;
      }

      return var1.toString();
   }

   @Override
   public String format(IELFUnit var1) {
      StringBuilder var2 = new StringBuilder();
      this.pC(var1, var2);
      return var2.toString();
   }

   public void pC(IELFUnit var1, StringBuilder var2) {
      Strings.ff(var2, "Relocation table has %d symbols:", this.pC.size());
      Strings.ff(var2, " section:0x%X('%s')", this.A, var1.getSectionName(this.A));
      Strings.ff(var2, " offset:0x%X\n", this.UT);
      m var3 = ((sy)var1).ys.pC(this.getSymbolSectionIndex());
      int var4 = 0;

      for (Tb var6 : this.pC) {
         Strings.ff(var2, "- 0x%04X: ", var4);
         var6.pC(var1, var3, var2);
         var2.append('\n');
         var4++;
      }
   }

   public static ma pC(ByteBuffer var0, boolean var1, int var2) {
      ma var3 = new ma();
      long var4 = -1L;
      int var6 = var1 ? 8 : 4;

      while (var0.remaining() > 0) {
         long var7 = var1 ? var0.getLong() : var0.getInt() & 4294967295L;
         if ((var7 & 1L) == 0L) {
            var3.pC.add(Tb.pC(var2, 0, var7, null, var1));
            var4 = var7 + var6;
         } else {
            while (var7 != 0L) {
               if ((var7 & 1L) != 0L) {
                  var3.pC.add(Tb.pC(var2, 0, var4, null, var1));
               }

               var7 >>>= 1;
               var4 += var6;
            }
         }
      }

      return var3;
   }
}
