package com.pnfsoftware.jeb.core.units.code.asm.decompiler.ast;

import com.pnfsoftware.jeb.util.serialization.annotations.Ser;
import java.util.Collection;

@Ser
public interface ICIdentifierManager {
   default ICDecl create(int var1, ICType var2, String var3, CIdentifierClass var4) {
      return this.create(var1, var2, var3, var4, null, null, null);
   }

   ICDecl create(int var1, ICType var2, String var3, CIdentifierClass var4, Integer var5, Long var6, Integer var7);

   ICDecl getDeclaration(int var1);

   ICIdentifier getIdentifier(int var1);

   ICIdentifier getIdentifierAt(long var1);

   Collection getDeclarations();

   Collection getIdentifiers();

   ICNamingEngine getNamingEngine();

   void setNamingEngine(ICNamingEngine var1);
}
