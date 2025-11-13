package com.pnfsoftware.jeb.corei.parsers.elf;

import com.pnfsoftware.jeb.core.units.codeobject.IELFSymbolTable;
import com.pnfsoftware.jeb.core.units.codeobject.IELFUnit;
import com.pnfsoftware.jeb.util.format.Strings;
import com.pnfsoftware.jeb.util.logging.GlobalLog;
import com.pnfsoftware.jeb.util.logging.ILogger;
import com.pnfsoftware.jeb.util.serialization.annotations.Ser;
import com.pnfsoftware.jeb.util.serialization.annotations.SerId;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.List;

@Ser
public class m implements IELFSymbolTable {
   private static final ILogger UT = GlobalLog.getLogger(m.class);
   @SerId(1)
   List pC = new ArrayList();
   @SerId(2)
   int A = -1;
   @SerId(3)
   boolean kS = false;
   @SerId(5)
   long wS;

   public static m pC(ByteBuffer var0, boolean var1) {
      m var2 = new m();

      while (var0.remaining() > 0) {
         nA var3 = nA.pC(var0, var1);
         if (var3 == null) {
            break;
         }

         var2.pC.add(var3);
      }

      return var2;
   }

   @Override
   public int getSectionIndex() {
      return this.A;
   }

   @Override
   public boolean isDynamic() {
      return this.kS;
   }

   @Override
   public int getCountOfEntries() {
      return this.pC.size();
   }

   @Override
   public List getEntries() {
      return this.pC;
   }

   public nA pC(int var1) {
      return (nA)this.pC.get(var1);
   }

   @Override
   public String toString() {
      StringBuilder var1 = new StringBuilder();
      var1.append(this.pC.size()).append(" symbols:");
      if (this.kS) {
         var1.append(" (SHT_DYNSYM)");
      }

      var1.append("\n");
      int var2 = 0;

      for (nA var4 : this.pC) {
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
      Strings.ff(var2, "Symbol table has %d symbols:", this.pC.size());
      Strings.ff(var2, " section:0x%X('%s')", this.A, var1.getSectionName(this.A));
      Strings.ff(var2, " offset:0x%X", this.wS);
      Strings.ff(var2, " dynamic:%b\n", this.kS);
      int var3 = 0;

      for (nA var5 : this.pC) {
         Strings.ff(var2, "- 0x%04X: ", var3);
         var5.pC(var1, var2);
         var2.append('\n');
         var3++;
      }
   }
}
