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
public class qa implements IELFSymbolTable {
   private static final ILogger Uv = GlobalLog.getLogger(qa.class);
   @SerId(1)
   List q = new ArrayList();
   @SerId(2)
   int RF = -1;
   @SerId(3)
   boolean xK = false;
   @SerId(5)
   long Dw;

   public static qa q(ByteBuffer var0, boolean var1) {
      qa var2 = new qa();

      while (var0.remaining() > 0) {
         LR var3 = LR.q(var0, var1);
         if (var3 == null) {
            break;
         }

         var2.q.add(var3);
      }

      return var2;
   }

   @Override
   public int getSectionIndex() {
      return this.RF;
   }

   @Override
   public boolean isDynamic() {
      return this.xK;
   }

   @Override
   public int getCountOfEntries() {
      return this.q.size();
   }

   @Override
   public List getEntries() {
      return this.q;
   }

   public LR q(int var1) {
      return (LR)this.q.get(var1);
   }

   @Override
   public String toString() {
      StringBuilder var1 = new StringBuilder();
      var1.append(this.q.size()).append(" symbols:");
      if (this.xK) {
         var1.append(" (SHT_DYNSYM)");
      }

      var1.append("\n");
      int var2 = 0;

      for (LR var4 : this.q) {
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
      Strings.ff(var2, "Symbol table has %d symbols:", this.q.size());
      Strings.ff(var2, " section:0x%X('%s')", this.RF, var1.getSectionName(this.RF));
      Strings.ff(var2, " offset:0x%X", this.Dw);
      Strings.ff(var2, " dynamic:%b\n", this.xK);
      int var3 = 0;

      for (LR var5 : this.q) {
         Strings.ff(var2, "- 0x%04X: ", var3);
         var5.q(var1, var2);
         var2.append('\n');
         var3++;
      }
   }
}
