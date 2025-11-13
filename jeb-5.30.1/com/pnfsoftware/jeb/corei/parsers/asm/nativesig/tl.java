package com.pnfsoftware.jeb.corei.parsers.asm.nativesig;

import com.pnfsoftware.jeb.core.units.code.asm.analyzer.INativeCodeAnalyzer;
import com.pnfsoftware.jeb.core.units.code.asm.items.INativeItem;
import com.pnfsoftware.jeb.core.units.code.asm.sig.ISigningStrategy;
import com.pnfsoftware.jeb.core.units.code.asm.sig.NativeAttributeSignerID;
import com.pnfsoftware.jeb.core.units.code.asm.sig.NativeFeatureSignerID;
import com.pnfsoftware.jeb.util.base.Assert;
import com.pnfsoftware.jeb.util.serialization.annotations.Ser;
import com.pnfsoftware.jebglobal.axp;
import java.util.HashSet;
import java.util.Set;

@Ser
public class tl implements ISigningStrategy {
   public static final String q = "STRICT";
   private static final Set RF = new HashSet();
   private static final Set xK = new HashSet();

   @Override
   public String getName() {
      return "STRICT";
   }

   @Override
   public Set getFeatureSigners(INativeCodeAnalyzer var1, INativeItem var2) {
      Assert.a(var2 instanceof axp, "only routines can be checked with this strategy");
      axp var3 = (axp)var2;
      Assert.a(var3.oW() != null, "only internal routines can be checked with this strategy");
      return RF;
   }

   @Override
   public Set getAttributeSigners(INativeCodeAnalyzer var1, INativeItem var2) {
      Assert.a(var2 instanceof axp, "only routines can be checked with this strategy");
      axp var3 = (axp)var2;
      Assert.a(var3.oW() != null, "only internal routines can be checked with this strategy");
      return xK;
   }

   static {
      RF.add(NativeFeatureSignerID.ROUTINE_CODE_HASH);
      RF.add(NativeFeatureSignerID.ROUTINE_SIZE);
      RF.add(NativeFeatureSignerID.CALLED_ROUTINE_NAME_ONLY_EXTERN);
      xK.add(NativeAttributeSignerID.COMMENT);
      xK.add(NativeAttributeSignerID.LABEL);
   }
}
