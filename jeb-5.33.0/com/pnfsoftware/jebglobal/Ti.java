package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.units.code.asm.processor.IRegisterBank;
import com.pnfsoftware.jeb.core.units.code.asm.processor.RegisterDescriptionEntry;
import com.pnfsoftware.jeb.core.units.code.asm.processor.RegisterEncoding;
import com.pnfsoftware.jeb.core.units.code.asm.processor.RegisterType;
import com.pnfsoftware.jeb.util.format.Strings;
import com.pnfsoftware.jeb.util.logging.GlobalLog;
import com.pnfsoftware.jeb.util.logging.ILogger;
import java.util.Collection;
import java.util.Map;
import java.util.TreeMap;

public class Ti implements IRegisterBank {
   private static final ILogger pC = GlobalLog.getLogger(Ti.class);
   private boolean A;
   private boolean kS;
   private int wS;
   private Map UT = new TreeMap();

   public Ti(boolean var1) {
      this.kS = var1;
   }

   RegisterDescriptionEntry pC(int var1, int var2, String var3, String var4, RegisterEncoding var5) {
      if (this.A) {
         throw new IllegalStateException("The layout was verified and additional entries can no longer be added");
      } else if (this.kS) {
         throw new IllegalStateException("Entries must be added by offset");
      } else {
         if (var1 < 0) {
            var1 = this.wS;
         }

         RegisterDescriptionEntry var6 = new RegisterDescriptionEntry(var1, var3, var2, var5, var4, null, -1);
         if (this.UT.put(var6.getNumber(), var6) != null) {
            throw new RuntimeException("Duplicate GDB register for number " + var6.getNumber());
         } else {
            this.wS = var6.getNumber() + 1;
            return var6;
         }
      }
   }

   RegisterDescriptionEntry A(int var1, int var2, String var3, String var4, RegisterEncoding var5) {
      if (this.A) {
         throw new IllegalStateException("The layout was verified and additional entries can no longer be added");
      } else if (!this.kS) {
         throw new IllegalStateException("Entries must be added by regnum");
      } else {
         if (var1 < 0) {
            var1 = this.wS;
         }

         RegisterDescriptionEntry var6 = new RegisterDescriptionEntry(this.UT.size(), var3, var2, var5, var4, null, var1);
         if (this.UT.put(var6.getNumber(), var6) != null) {
            throw new RuntimeException("Duplicate GDB register for number " + var6.getNumber());
         } else {
            this.wS = var6.getOffset() + var6.getSize();
            return var6;
         }
      }
   }

   public void pC() throws Ae {
      if (this.A) {
         throw new IllegalStateException("The layout was already verified");
      } else {
         if (!this.kS) {
            int var1 = 0;

            for (RegisterDescriptionEntry var3 : this.UT.values()) {
               var3.setOffset(var1);
               var1 += var3.getSize();
            }
         }

         this.A = true;
      }
   }

   @Override
   public Collection getDescriptionEntries() {
      return this.UT.values();
   }

   @Override
   public RegisterDescriptionEntry getDescriptionEntry(int var1) {
      return (RegisterDescriptionEntry)this.UT.get(var1);
   }

   @Override
   public int getCountOfDescriptionEntries() {
      return this.UT.size();
   }

   public boolean A() {
      return this.UT.isEmpty();
   }

   public Collection kS() {
      return this.UT.keySet();
   }

   @Override
   public RegisterDescriptionEntry getDescriptionEntryByName(String var1) {
      for (RegisterDescriptionEntry var3 : this.UT.values()) {
         if (var3.hasName(var1)) {
            return var3;
         }
      }

      return null;
   }

   @Override
   public RegisterDescriptionEntry getDescriptionEntryByName(Collection var1) {
      throw new UnsupportedOperationException("TBD");
   }

   @Override
   public RegisterDescriptionEntry getDescriptionEntryById(long var1) {
      throw new UnsupportedOperationException("TBD");
   }

   @Override
   public RegisterDescriptionEntry getDescriptionEntryByType(RegisterType var1) {
      throw new UnsupportedOperationException("TBD");
   }

   @Override
   public Collection getAllDescriptionEntries() {
      throw new UnsupportedOperationException("TBD");
   }

   @Override
   public String format(int var1) {
      StringBuilder var2 = new StringBuilder();
      Strings.ff(var2, "(%d entries)", this.UT.size());

      for (RegisterDescriptionEntry var4 : this.UT.values()) {
         var2.append("\n- " + var4);
      }

      return var2.toString();
   }

   @Override
   public String toString() {
      return this.format(0);
   }
}
