package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.units.code.asm.memory.MemoryException;
import com.pnfsoftware.jeb.core.units.codeobject.dwarf.Dwarf;
import com.pnfsoftware.jeb.util.logging.GlobalLog;
import com.pnfsoftware.jeb.util.logging.ILogger;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

class cdl {
   private static final ILogger pC = GlobalLog.getLogger(cdl.class);
   private Map A = new TreeMap();

   public cdl() {
   }

   public cdl.Av pC(long var1) {
      return (cdl.Av)this.A.get(var1);
   }

   public static cdl pC(cdu var0) throws MemoryException {
      cdl var1 = new cdl();

      while (var0.gp() > 0) {
         long var2 = var0.ld();
         long var4 = var0.sY();
         if (var4 == 0L) {
            break;
         }

         int var6 = (int)var0.sY();
         Dwarf.DwarfTag var7 = Dwarf.DwarfTag.getByValue(var6);
         int var8 = var0.pC();
         cdl.Av var9 = new cdl.Av(var2, var6, var7, var8 == 1);

         while (true) {
            long var10 = var0.sY();
            long var12 = var0.sY();
            if (var10 == 0L && var12 == 0L) {
               var1.A.put(var4, var9);
               break;
            }

            if (var12 == Dwarf.DwarfForm.DW_FORM_implicit_const.getValue()) {
               long var14 = var0.E();
               var9.wS.add(new cdl.Sv(var10, var12, var14));
            } else {
               var9.wS.add(new cdl.Sv(var10, var12));
            }
         }
      }

      return var1;
   }

   public static class Av {
      private long pC;
      private int A;
      private Dwarf.DwarfTag kS;
      private List wS = new ArrayList();
      private boolean UT;

      public Av(long var1, int var3, Dwarf.DwarfTag var4, boolean var5) {
         this.pC = var1;
         this.A = var3;
         this.kS = var4;
         this.UT = var5;
      }

      public boolean pC() {
         return this.UT;
      }

      public int A() {
         return this.A;
      }

      public List kS() {
         return this.wS;
      }

      @Override
      public String toString() {
         return "AbbrevEntry [tag="
            + (this.kS != null ? this.kS.name() : Long.toHexString(this.A) + "h")
            + ", children="
            + this.UT
            + ", attributeSize="
            + this.wS.size()
            + "]";
      }
   }

   public static class Sv {
      private long pC;
      private long A;
      private Long kS;

      public Sv(long var1, long var3) {
         this(var1, var3, null);
      }

      public Sv(long var1, long var3, Long var5) {
         this.pC = var1;
         this.A = var3;
         this.kS = var5;
      }

      public long pC() {
         return this.pC;
      }

      public long A() {
         return this.A;
      }

      public Long kS() {
         return this.kS;
      }
   }
}
