package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.client.S;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ast.CIdentifierClass;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ast.ICDecl;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ast.ICIdentifierManager;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ast.ICNamingEngine;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ast.ICType;
import com.pnfsoftware.jeb.util.base.Assert;
import com.pnfsoftware.jeb.util.format.Strings;
import com.pnfsoftware.jeb.util.logging.StructuredLogger;
import com.pnfsoftware.jeb.util.serialization.annotations.Ser;
import com.pnfsoftware.jeb.util.serialization.annotations.SerId;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

@Ser
public class agj implements ICIdentifierManager {
   private static final StructuredLogger Uv = aeg.q(agj.class);
   public static final String q = "__var_";
   @SerId(1)
   ICNamingEngine RF;
   @SerId(2)
   Map xK = new HashMap();
   @SerId(3)
   Map Dw = new HashMap();

   public agj(ICNamingEngine var1) {
      this.RF = var1;
   }

   @Override
   public ICNamingEngine getNamingEngine() {
      return this.RF;
   }

   @Override
   public void setNamingEngine(ICNamingEngine var1) {
      this.RF = var1;
   }

   public void q() {
      this.RF(null);
   }

   public void q(Collection var1) {
      HashSet var2 = new HashSet();

      for (ICDecl var4 : var1) {
         var2.add(var4.getIdentifier().getId());
      }

      this.RF(var2);
   }

   private void RF(Collection var1) {
      for (Integer var4 : new HashSet(this.xK.keySet())) {
         if (var1 == null || !var1.contains(var4)) {
            Assert.a(this.xK.remove(var4) != null);
            Assert.a(this.Dw.remove(var4) != null);
         }
      }
   }

   @Override
   public ICDecl create(int var1, ICType var2, String var3, CIdentifierClass var4, Integer var5, Long var6, Integer var7) {
      afz var8 = (afz)this.Dw.get(var1);
      if (var8 == null) {
         if (var3 != null && var3.startsWith("__var_")) {
            Uv.error(S.L("Illegal C variable name starts with reserved prefix %s: %s"), "__var_", var3);
            var3 = null;
         }

         var3 = this.q(var3, var1);
         agh var9 = new agh(var1, var2, var3, var4, var5, var6, var7);
         this.xK.put(var1, var9);
         var8 = new afz(var9);
         this.Dw.put(var1, var8);
      }

      return var8;
   }

   public afz q(int var1) {
      return (afz)this.Dw.get(var1);
   }

   public agh RF(int var1) {
      return (agh)this.xK.get(var1);
   }

   public agh q(long var1) {
      for (agh var4 : this.xK.values()) {
         if (var4.getAddress() != null && var4.getAddress() == var1) {
            return var4;
         }
      }

      return null;
   }

   @Override
   public Collection getDeclarations() {
      return Collections.unmodifiableCollection(this.Dw.values());
   }

   @Override
   public Collection getIdentifiers() {
      return Collections.unmodifiableCollection(this.xK.values());
   }

   private String q(String var1, int var2) {
      if (var1 != null) {
         for (agh var4 : this.xK.values()) {
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
