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
public class afb implements ICOperatorFactory {
   private static final StructuredLogger pC = aco.pC(afb.class);
   @SerId(1)
   private afk A;
   @SerId(2)
   private Map kS;
   @SerId(3)
   private IdentityHashMap wS;
   @SerId(4)
   private Map UT;

   public afb(afk var1) {
      this.A = var1;
      this.kS = new HashMap();

      for (COperatorType var5 : COperatorType.values()) {
         if (var5 != COperatorType.CAST && var5 != COperatorType.CUSTOM) {
            this.kS.put(var5, new aez(var5, this));
         }
      }

      this.wS = new IdentityHashMap();
      this.UT = new HashMap();
   }

   public aez pC(COperatorType var1) {
      return (aez)this.kS.get(var1);
   }

   public aez pC(ICType var1) {
      Assert.a(var1 != null);
      aez var2 = (aez)this.wS.get(var1);
      if (var2 == null) {
         var2 = new aez(var1, this);
         this.wS.put(var1, var2);
      }

      return var2;
   }

   public aez pC(String var1, int var2) {
      Assert.a(var1 != null && !var1.isEmpty() && var2 >= 0);
      aez var3 = (aez)this.UT.get(var1);
      if (var3 == null) {
         var3 = new aez(var1, var2, this);
         this.UT.put(var1, var3);
      } else {
         Assert.a(var3.getOperandCount() == var2, Strings.ff("Operator %s already exists and accepts %d operands, not %d", var1, var3.getOperandCount(), var2));
      }

      return var3;
   }
}
