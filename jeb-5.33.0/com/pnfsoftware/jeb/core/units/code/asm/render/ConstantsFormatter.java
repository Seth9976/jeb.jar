package com.pnfsoftware.jeb.core.units.code.asm.render;

import com.pnfsoftware.jeb.core.units.code.asm.type.CodeConstant;
import com.pnfsoftware.jeb.util.serialization.annotations.Ser;
import com.pnfsoftware.jeb.util.serialization.annotations.SerId;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Ser
public class ConstantsFormatter {
   @SerId(1)
   private List constants;
   @SerId(2)
   private Object leftOver;

   public ConstantsFormatter(CodeConstant var1) {
      if (var1 == null) {
         throw new IllegalArgumentException();
      } else {
         this.constants = new ArrayList(1);
         this.constants.add(var1);
      }
   }

   public ConstantsFormatter(Collection var1) {
      this(var1, null);
   }

   public ConstantsFormatter(Collection var1, Object var2) {
      if (var1 != null && !var1.isEmpty()) {
         this.constants = new ArrayList(var1.size());
         this.constants.addAll(var1);
         this.leftOver = var2;
      } else {
         throw new IllegalArgumentException();
      }
   }

   public List getConstants() {
      return this.constants;
   }

   public String format() {
      StringBuilder var1 = new StringBuilder();
      int var2 = 0;

      for (CodeConstant var4 : this.constants) {
         if (var2 >= 1) {
            var1.append("|");
         }

         var1.append(var4.getName());
         var2++;
      }

      if (this.leftOver != null) {
         var1.append("|").append(this.leftOver);
      }

      return var1.toString();
   }
}
