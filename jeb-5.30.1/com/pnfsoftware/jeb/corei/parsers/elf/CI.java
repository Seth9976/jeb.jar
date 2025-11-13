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
public class CI implements IELFRelocationTable {
   @SerId(1)
   List q = new ArrayList();
   @SerId(2)
   int RF;
   @SerId(3)
   int xK;
   @SerId(4)
   int Dw;
   @SerId(5)
   long Uv;

   public static CI q(ByteBuffer var0, boolean var1, boolean var2) {
      CI var3 = new CI();

      while (var0.remaining() > 0) {
         var3.q.add(Xa.q(var0, var1, var2));
      }

      return var3;
   }

   @Override
   public int getCountOfEntries() {
      return this.q.size();
   }

   @Override
   public List getEntries() {
      return this.q;
   }

   @Override
   public IELFRelocationEntry getEntry(int var1) {
      return (IELFRelocationEntry)this.q.get(var1);
   }

   @Override
   public int getSectionIndex() {
      return this.RF;
   }

   @Override
   public int getSymbolSectionIndex() {
      return this.xK;
   }

   @Override
   public int getTargetSectionIndex() {
      return this.Dw;
   }

   @Override
   public long getFileOffset() {
      return this.Uv;
   }

   @Override
   public String toString() {
      StringBuilder var1 = new StringBuilder();
      Strings.ff(var1, "link index (symbols section)= %d\n", this.xK);
      Strings.ff(var1, "info index (target section for relos)= %d\n", this.Dw);
      int var2 = 0;

      for (Xa var4 : this.q) {
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
      this.q(var1, var2);
      return var2.toString();
   }

   public void q(IELFUnit var1, StringBuilder var2) {
      Strings.ff(var2, "Relocation table has %d symbols:", this.q.size());
      Strings.ff(var2, " section:0x%X('%s')", this.RF, var1.getSectionName(this.RF));
      Strings.ff(var2, " offset:0x%X\n", this.Uv);
      qa var3 = ((vb)var1).nf.q(this.getSymbolSectionIndex());
      int var4 = 0;

      for (Xa var6 : this.q) {
         Strings.ff(var2, "- 0x%04X: ", var4);
         var6.q(var1, var3, var2);
         var2.append('\n');
         var4++;
      }
   }
}
