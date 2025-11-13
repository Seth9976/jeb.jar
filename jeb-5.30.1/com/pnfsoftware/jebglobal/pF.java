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

public class pF implements IRegisterBank {
   private static final ILogger q = GlobalLog.getLogger(pF.class);
   private boolean RF;
   private boolean xK;
   private int Dw;
   private Map Uv = new TreeMap();

   public pF(boolean var1) {
      this.xK = var1;
   }

   RegisterDescriptionEntry q(int var1, int var2, String var3, String var4, RegisterEncoding var5) {
      if (this.RF) {
         throw new IllegalStateException("The layout was verified and additional entries can no longer be added");
      } else if (this.xK) {
         throw new IllegalStateException("Entries must be added by offset");
      } else {
         if (var1 < 0) {
            var1 = this.Dw;
         }

         RegisterDescriptionEntry var6 = new RegisterDescriptionEntry(var1, var3, var2, var5, var4, null, -1);
         if (this.Uv.put(var6.getNumber(), var6) != null) {
            throw new RuntimeException("Duplicate GDB register for number " + var6.getNumber());
         } else {
            this.Dw = var6.getNumber() + 1;
            return var6;
         }
      }
   }

   RegisterDescriptionEntry RF(int var1, int var2, String var3, String var4, RegisterEncoding var5) {
      if (this.RF) {
         throw new IllegalStateException("The layout was verified and additional entries can no longer be added");
      } else if (!this.xK) {
         throw new IllegalStateException("Entries must be added by regnum");
      } else {
         if (var1 < 0) {
            var1 = this.Dw;
         }

         RegisterDescriptionEntry var6 = new RegisterDescriptionEntry(this.Uv.size(), var3, var2, var5, var4, null, var1);
         if (this.Uv.put(var6.getNumber(), var6) != null) {
            throw new RuntimeException("Duplicate GDB register for number " + var6.getNumber());
         } else {
            this.Dw = var6.getOffset() + var6.getSize();
            return var6;
         }
      }
   }

   public void q() throws WI {
      if (this.RF) {
         throw new IllegalStateException("The layout was already verified");
      } else {
         if (!this.xK) {
            int var1 = 0;

            for (RegisterDescriptionEntry var3 : this.Uv.values()) {
               var3.setOffset(var1);
               var1 += var3.getSize();
            }
         }

         this.RF = true;
      }
   }

   @Override
   public Collection getDescriptionEntries() {
      return this.Uv.values();
   }

   @Override
   public RegisterDescriptionEntry getDescriptionEntry(int var1) {
      return (RegisterDescriptionEntry)this.Uv.get(var1);
   }

   @Override
   public int getCountOfDescriptionEntries() {
      return this.Uv.size();
   }

   public boolean RF() {
      return this.Uv.isEmpty();
   }

   public Collection xK() {
      return this.Uv.keySet();
   }

   @Override
   public RegisterDescriptionEntry getDescriptionEntryByName(String var1) {
      for (RegisterDescriptionEntry var3 : this.Uv.values()) {
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
      Strings.ff(var2, "(%d entries)", this.Uv.size());

      for (RegisterDescriptionEntry var4 : this.Uv.values()) {
         var2.append("\n- " + var4);
      }

      return var2.toString();
   }

   @Override
   public String toString() {
      return this.format(0);
   }
}
