package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.client.S;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ast.CIdentifierClass;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ast.ICDecl;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ast.ICIdentifierManager;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ast.ICNamingEngine;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ast.ICType;
import com.pnfsoftware.jeb.util.format.Strings;
import com.pnfsoftware.jeb.util.logging.StructuredLogger;
import com.pnfsoftware.jeb.util.serialization.annotations.Ser;
import com.pnfsoftware.jeb.util.serialization.annotations.SerId;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

@Ser
public class aeq implements ICIdentifierManager {
   private static final StructuredLogger wS = aco.pC(aeq.class);
   @SerId(1)
   ICNamingEngine pC;
   @SerId(2)
   Map A = new HashMap();
   @SerId(3)
   Map kS = new HashMap();

   public aeq(ICNamingEngine var1) {
      this.pC = var1;
   }

   @Override
   public ICNamingEngine getNamingEngine() {
      return this.pC;
   }

   @Override
   public void setNamingEngine(ICNamingEngine var1) {
      this.pC = var1;
   }

   @Override
   public ICDecl create(int var1, ICType var2, String var3, CIdentifierClass var4, Integer var5, Long var6, Integer var7) {
      aeg var8 = (aeg)this.kS.get(var1);
      if (var8 == null) {
         if (var3 != null && var3.startsWith("__var_")) {
            wS.error(S.L("Illegal C variable name starts with reserved prefix %s: %s"), "__var_", var3);
            var3 = null;
         }

         var3 = this.pC(var3, var1);
         aeo var9 = new aeo(var1, var2, var3, var4, var5, var6, var7);
         this.A.put(var1, var9);
         var8 = new aeg(var9);
         this.kS.put(var1, var8);
      }

      return var8;
   }

   public aeg pC(int var1) {
      return (aeg)this.kS.get(var1);
   }

   public aeo A(int var1) {
      return (aeo)this.A.get(var1);
   }

   public aeo pC(long var1) {
      for (aeo var4 : this.A.values()) {
         if (var4.getAddress() != null && var4.getAddress() == var1) {
            return var4;
         }
      }

      return null;
   }

   @Override
   public Collection getDeclarations() {
      return Collections.unmodifiableCollection(this.kS.values());
   }

   @Override
   public Collection getIdentifiers() {
      return Collections.unmodifiableCollection(this.A.values());
   }

   private String pC(String var1, int var2) {
      if (var1 != null) {
         for (aeo var4 : this.A.values()) {
            if (var1.equals(var4.getName())) {
               var1 = null;
               break;
            }
         }
      }

      if (var1 == null || var1.length() == 0) {
         var1 = Strings.ff("%s%X", "__var_", var2);
      }

      return var1;
   }
}
