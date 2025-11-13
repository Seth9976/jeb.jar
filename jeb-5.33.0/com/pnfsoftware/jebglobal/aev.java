package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ast.ICLabel;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ast.ICLabelFactory;
import com.pnfsoftware.jeb.util.format.Strings;
import com.pnfsoftware.jeb.util.serialization.annotations.Ser;
import com.pnfsoftware.jeb.util.serialization.annotations.SerId;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

@Ser
public class aev implements ICLabelFactory {
   @SerId(1)
   Map pC = new HashMap();
   @SerId(2)
   int A;

   @Override
   public ICLabel create(long var1, String var3) {
      if (var3 == null) {
         var3 = Strings.ff("label_%X", var1);
      }

      Object var4 = (ICLabel)this.pC.get(var1);
      if (var4 == null) {
         var4 = new aeu(var1, var3);
         this.pC.put(var1, var4);
      } else if (!((ICLabel)var4).getName().equals(var3)) {
         throw new RuntimeException("Label at offset already exists under a different name");
      }

      return (ICLabel)var4;
   }

   @Override
   public ICLabel create(long var1) {
      return this.create(var1, null);
   }

   @Override
   public ICLabel create() {
      this.A++;
      long var1 = -this.A;
      return this.create(var1, "alab" + this.A);
   }

   @Override
   public ICLabel get(String var1) {
      for (ICLabel var3 : this.pC.values()) {
         if (var1.equals(var3.getName())) {
            return var3;
         }
      }

      return null;
   }

   @Override
   public ICLabel get(long var1) {
      return (ICLabel)this.pC.get(var1);
   }

   @Override
   public Collection getLabels() {
      return Collections.unmodifiableCollection(this.pC.values());
   }
}
