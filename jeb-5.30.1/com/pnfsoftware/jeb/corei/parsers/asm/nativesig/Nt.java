package com.pnfsoftware.jeb.corei.parsers.asm.nativesig;

import com.pnfsoftware.jeb.core.units.code.asm.analyzer.INativeCodeAnalyzer;
import com.pnfsoftware.jeb.core.units.code.asm.items.INativeMethodItem;
import com.pnfsoftware.jeb.core.units.code.asm.sig.NativeAttributeSignerID;
import com.pnfsoftware.jeb.core.units.code.asm.sig.NativeSignatureScopeException;
import com.pnfsoftware.jeb.util.logging.GlobalLog;
import com.pnfsoftware.jeb.util.logging.ILogger;
import com.pnfsoftware.jebglobal.aae;
import com.pnfsoftware.jebglobal.axp;
import com.pnfsoftware.jebglobal.azp;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class Nt {
   private static final ILogger q = GlobalLog.getLogger(Nt.class);
   private aae RF;
   private Map xK;

   public Nt(INativeCodeAnalyzer var1) {
      this.RF = (aae)var1;
      this.xK = ej.q(this.RF);
   }

   public List q(INativeMethodItem var1, Set var2) {
      ArrayList var3 = new ArrayList();

      for (NativeAttributeSignerID var5 : var2) {
         azp var6 = (azp)this.xK.get(var5);

         try {
            if (var6 == null) {
               throw new NativeSignatureScopeException("unknown signer");
            }

            var6.q((axp)var1);
            var3.addAll(var6.q());
            var6.RF();
         } catch (NativeSignatureScopeException var7) {
            q.error("fail during routine signing");
            var3 = null;
         }
      }

      return var3;
   }
}
