package com.pnfsoftware.jeb.core.units.code.asm.sig;

import com.pnfsoftware.jeb.core.units.code.asm.analyzer.INativeCodeAnalyzer;
import com.pnfsoftware.jeb.core.units.code.asm.items.INativeMethodItem;
import com.pnfsoftware.jeb.corei.parsers.asm.nativesig.EE;
import com.pnfsoftware.jeb.corei.parsers.asm.nativesig.Nt;
import com.pnfsoftware.jeb.corei.parsers.asm.nativesig.qV;
import com.pnfsoftware.jeb.util.base.Assert;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class NativeSignatureGenerator {
   private ISigningStrategy defaultStrategy;
   private final Map featureSignersManagerDB;
   private final Map attributeSignersManagerDB;
   private static NativeSignatureGenerator instance;

   private NativeSignatureGenerator(ISigningStrategy var1) {
      this.defaultStrategy = var1;
      this.featureSignersManagerDB = new HashMap();
      this.attributeSignersManagerDB = new HashMap();
   }

   public static NativeSignatureGenerator getInstance(ISigningStrategy var0) {
      if (instance == null) {
         instance = new NativeSignatureGenerator(var0);
      }

      return instance;
   }

   public INativeSignature generateSignature(INativeCodeAnalyzer var1, INativeMethodItem var2, Set var3, Set var4) {
      Assert.a(var3 == null && var4 == null, "TBI: client provided features/attributes");
      Set var5 = this.defaultStrategy.getFeatureSigners(var1, var2);
      EE var6 = (EE)this.featureSignersManagerDB.get(var1);
      if (var6 == null) {
         var6 = new EE(var1);
         this.featureSignersManagerDB.put(var1, var6);
      }

      List var7 = var6.q(var2, var5);
      Set var8 = this.defaultStrategy.getAttributeSigners(var1, var2);
      Nt var9 = (Nt)this.attributeSignersManagerDB.get(var1);
      if (var9 == null) {
         var9 = new Nt(var1);
         this.attributeSignersManagerDB.put(var1, var9);
      }

      List var10 = var9.q(var2, var8);
      return new qV(var2.getName(true), var7, var10, new NativeSignatureFlags(SignatureTargetType.ROUTINE));
   }

   public void setDefaultStrategy(ISigningStrategy var1) {
      this.defaultStrategy = var1;
   }
}
