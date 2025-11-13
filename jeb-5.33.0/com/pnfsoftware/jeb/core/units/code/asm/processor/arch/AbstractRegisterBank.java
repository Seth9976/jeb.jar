package com.pnfsoftware.jeb.core.units.code.asm.processor.arch;

import com.pnfsoftware.jeb.core.units.code.asm.processor.IRegisterBank;
import com.pnfsoftware.jeb.core.units.code.asm.processor.RegisterDescriptionEntry;
import com.pnfsoftware.jeb.core.units.code.asm.processor.RegisterEncoding;
import com.pnfsoftware.jeb.core.units.code.asm.processor.RegisterType;
import com.pnfsoftware.jeb.util.format.Strings;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public abstract class AbstractRegisterBank implements IRegisterBank {
   public static final int REG_GRP_GP = 0;
   public static final int REG_GRP_SPECIAL = 1;
   private volatile Map namemap;
   private volatile Map idmap;

   protected static RegisterDescriptionEntry add(Map var0, int var1, String var2, String var3, RegisterEncoding var4, RegisterType var5) {
      int var6 = var0.size();
      RegisterDescriptionEntry var7 = new RegisterDescriptionEntry(var6, var2, var1, null, var3, var5, -1);
      if (var0.put(var7.getNumber(), var7) != null) {
         throw new RuntimeException("Duplicate RegisterDescriptionEntry for number " + var7.getNumber());
      } else {
         return var7;
      }
   }

   protected static RegisterDescriptionEntry add(Map var0, int var1, String var2, String var3) {
      return add(var0, var1, var2, var3, null, null);
   }

   protected static RegisterDescriptionEntry add(Map var0, int var1, String var2) {
      return add(var0, var1, var2, null, null, null);
   }

   @Override
   public Collection getDescriptionEntries() {
      return Collections.unmodifiableCollection(this.getDescriptionEntryMap().values());
   }

   public abstract Map getDescriptionEntryMap();

   @Override
   public RegisterDescriptionEntry getDescriptionEntry(int var1) {
      return (RegisterDescriptionEntry)this.getDescriptionEntryMap().get(var1);
   }

   @Override
   public int getCountOfDescriptionEntries() {
      return this.getDescriptionEntries().size();
   }

   @Override
   public Collection getAllDescriptionEntries() {
      ArrayList var1 = new ArrayList();

      for (RegisterDescriptionEntry var3 : this.getDescriptionEntries()) {
         var1.add(var3);
         var1.addAll(var3.getSlices());
      }

      return Collections.unmodifiableList(var1);
   }

   @Override
   public RegisterDescriptionEntry getDescriptionEntryByName(String var1) {
      return (RegisterDescriptionEntry)this.getByNameCache().get(var1.toLowerCase());
   }

   @Override
   public RegisterDescriptionEntry getDescriptionEntryByName(Collection var1) {
      Map var2 = this.getByNameCache();

      for (String var4 : var1) {
         RegisterDescriptionEntry var5 = (RegisterDescriptionEntry)var2.get(var4.toLowerCase());
         if (var5 != null) {
            return var5;
         }
      }

      return null;
   }

   private Map getByNameCache() {
      if (this.namemap == null) {
         synchronized (this) {
            if (this.namemap == null) {
               HashMap var2 = new HashMap();

               for (RegisterDescriptionEntry var4 : this.getDescriptionEntries()) {
                  var4.getNames().forEach(var2x -> var2.put(var2x.toLowerCase(), var4));

                  for (RegisterDescriptionEntry var6 : var4.getSlices()) {
                     var6.getNames().forEach(var2x -> var2.put(var2x.toLowerCase(), var6));
                  }
               }

               this.namemap = var2;
            }
         }
      }

      return this.namemap;
   }

   @Override
   public RegisterDescriptionEntry getDescriptionEntryById(long var1) {
      return (RegisterDescriptionEntry)this.getByIdCache().get(var1);
   }

   private Map getByIdCache() {
      if (this.idmap == null) {
         synchronized (this) {
            if (this.idmap == null) {
               HashMap var2 = new HashMap();

               for (RegisterDescriptionEntry var4 : this.getDescriptionEntries()) {
                  var2.put(var4.getId(), var4);
                  var2.put(var4.getPureId(), var4);

                  for (RegisterDescriptionEntry var6 : var4.getSlices()) {
                     var2.put(var6.getId(), var6);
                  }
               }

               this.idmap = var2;
            }
         }
      }

      return this.idmap;
   }

   @Override
   public RegisterDescriptionEntry getDescriptionEntryByType(RegisterType var1) {
      for (RegisterDescriptionEntry var3 : this.getDescriptionEntries()) {
         if (var3.getType() == var1) {
            return var3;
         }
      }

      return null;
   }

   @Override
   public String toString() {
      return this.format(0);
   }

   @Override
   public String format(int var1) {
      if (var1 == 1) {
         StringBuilder var8 = new StringBuilder();
         int var9 = 0;

         for (RegisterDescriptionEntry var5 : this.getDescriptionEntries()) {
            Strings.ff(var8, "#%d: %s %d-bit", var9, var5.getName(), var5.getBitsize());
            if (var5.getType() != RegisterType.Unspecified) {
               Strings.ff(var8, " %s", var5.getType());
            }

            if (var5.getEncoding() != RegisterEncoding.unknown) {
               Strings.ff(var8, " %s", var5.getEncoding());
            }

            var8.append("\n");

            for (RegisterDescriptionEntry var7 : var5.getSlices()) {
               Strings.ff(var8, "  %s [%d:%d[\n", var7.getName(), var7.getBitstart(), var7.getBitend());
            }

            var9++;
         }

         return var8.toString();
      } else {
         StringBuilder var2 = new StringBuilder();
         Strings.ff(var2, "layout(%d):", this.getCountOfDescriptionEntries());

         for (RegisterDescriptionEntry var4 : this.getDescriptionEntries()) {
            Strings.ff(var2, "%s,", var4.getName());
         }

         return var2.toString();
      }
   }
}
