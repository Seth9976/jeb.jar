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
public class ago implements ICLabelFactory {
   @SerId(1)
   Map q = new HashMap();
   @SerId(2)
   int RF;

   public void q() {
      this.q.clear();
   }

   @Override
   public ICLabel create(long var1, String var3) {
      if (var3 == null) {
         var3 = Strings.ff("label_%X", var1);
      }

      Object var4 = (ICLabel)this.q.get(var1);
      if (var4 == null) {
         var4 = new agn(var1, var3);
         this.q.put(var1, var4);
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
      this.RF++;
      long var1 = -this.RF;
      return this.create(var1, "alab" + this.RF);
   }

   @Override
   public ICLabel get(String var1) {
      for (ICLabel var3 : this.q.values()) {
         if (var1.equals(var3.getName())) {
            return var3;
         }
      }

      return null;
   }

   @Override
   public ICLabel get(long var1) {
      return (ICLabel)this.q.get(var1);
   }

   @Override
   public Collection getLabels() {
      return Collections.unmodifiableCollection(this.q.values());
   }
}
