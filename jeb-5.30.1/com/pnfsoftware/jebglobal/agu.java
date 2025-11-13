package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ast.COperatorType;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ast.ICOperatorFactory;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ast.ICType;
import com.pnfsoftware.jeb.util.base.Assert;
import com.pnfsoftware.jeb.util.format.Strings;
import com.pnfsoftware.jeb.util.logging.StructuredLogger;
import com.pnfsoftware.jeb.util.serialization.annotations.Ser;
import com.pnfsoftware.jeb.util.serialization.annotations.SerId;
import java.util.HashMap;
import java.util.IdentityHashMap;
import java.util.Map;

@Ser
public class agu implements ICOperatorFactory {
   private static final StructuredLogger q = aeg.q(agu.class);
   @SerId(1)
   private ahd RF;
   @SerId(2)
   private Map xK;
   @SerId(3)
   private IdentityHashMap Dw;
   @SerId(4)
   private Map Uv;

   public agu(ahd var1) {
      this.RF = var1;
      this.xK = new HashMap();

      for (COperatorType var5 : COperatorType.values()) {
         if (var5 != COperatorType.CAST && var5 != COperatorType.CUSTOM) {
            this.xK.put(var5, new ags(var5, this));
         }
      }

      this.Dw = new IdentityHashMap();
      this.Uv = new HashMap();
   }

   public ahd q() {
      return this.RF;
   }

   public ags q(COperatorType var1) {
      return (ags)this.xK.get(var1);
   }

   public ags q(ICType var1) {
      Assert.a(var1 != null);
      ags var2 = (ags)this.Dw.get(var1);
      if (var2 == null) {
         var2 = new ags(var1, this);
         this.Dw.put(var1, var2);
      }

      return var2;
   }

   public ags q(String var1, int var2) {
      Assert.a(var1 != null && !var1.isEmpty() && var2 >= 1 && var2 <= 3);
      ags var3 = (ags)this.Uv.get(var1);
      if (var3 == null) {
         var3 = new ags(var1, var2, this);
         this.Uv.put(var1, var3);
      } else {
         Assert.a(var3.getOperandCount() == var2, Strings.ff("Operator %s already exists and accepts %d operands, not %d", var1, var3.getOperandCount(), var2));
      }

      return var3;
   }
}
