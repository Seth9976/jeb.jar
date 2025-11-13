package com.pnfsoftware.jeb.corei.parsers.asm.nativesig;

import com.pnfsoftware.jeb.core.units.code.asm.analyzer.INativeCodeAnalyzer;
import com.pnfsoftware.jeb.core.units.code.asm.items.INativeMethodItem;
import com.pnfsoftware.jeb.core.units.code.asm.sig.NativeAttributeSignerID;
import com.pnfsoftware.jeb.core.units.code.asm.sig.NativeSignatureScopeException;
import com.pnfsoftware.jeb.util.logging.GlobalLog;
import com.pnfsoftware.jeb.util.logging.ILogger;
import com.pnfsoftware.jebglobal.a;
import com.pnfsoftware.jebglobal.auu;
import com.pnfsoftware.jebglobal.aws;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class cq {
   private static final ILogger pC = GlobalLog.getLogger(cq.class);
   private a A;
   private Map kS;

   public cq(INativeCodeAnalyzer var1) {
      this.A = (a)var1;
      this.kS = Ws.pC(this.A);
   }

   public List pC(INativeMethodItem var1, Set var2) {
      ArrayList var3 = new ArrayList();

      for (NativeAttributeSignerID var5 : var2) {
         aws var6 = (aws)this.kS.get(var5);

         try {
            if (var6 == null) {
               throw new NativeSignatureScopeException("unknown signer");
            }

            var6.pC((auu)var1);
            var3.addAll(var6.pC());
            var6.A();
         } catch (NativeSignatureScopeException var7) {
            pC.error("fail during routine signing");
            var3 = null;
         }
      }

      return var3;
   }
}
