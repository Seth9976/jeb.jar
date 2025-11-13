package com.pnfsoftware.jeb.corei.parsers.asm.nativesig.codeless;

import com.pnfsoftware.jeb.core.units.INativeCodeUnit;
import com.pnfsoftware.jeb.core.units.code.asm.sig.codeless.CodelessSignaturePackageEntry;
import com.pnfsoftware.jeb.core.units.code.asm.sig.codeless.LibraryIdentificationResults;
import com.pnfsoftware.jeb.util.logging.GlobalLog;
import com.pnfsoftware.jeb.util.logging.ILogger;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

public class Av {
   private static final ILogger pC = GlobalLog.getLogger(Av.class);

   public LibraryIdentificationResults pC(List var1, INativeCodeUnit var2) {
      List var3 = this.pC(var1);
      if (var3 == null) {
         return null;
      } else {
         Ws var4 = new Ws();
         yt var5 = new yt();
         var5.pC(var2, var4, false, false);
         LibraryIdentificationResults var6 = new LibraryIdentificationResults();

         for (Ws var8 : var3) {
            Stream var9 = var8.A().keySet().stream().filter(var1x -> var1x.pC() == DH.kS && (var4.A().containsKey(var1x) || var4.gp().contains(var1x)));
            Stream var10 = var8.gp().stream().filter(var1x -> var1x.pC() == DH.kS && (var4.A().containsKey(var1x) || var4.gp().contains(var1x)));
            Stream var11 = Stream.concat(var9, var10);
            var6.getHitsMap().put(var8.oT(), var11.count());
         }

         return var6;
      }
   }

   private List pC(List var1) {
      ArrayList var2 = new ArrayList();

      for (CodelessSignaturePackageEntry var4 : var1) {
         File var5 = var4.getFile();
         Object[] var10000 = new Object[]{var5};
         Ws var6 = Ws.A(var5);
         if (var6 == null) {
            pC.error("cannot deser %s", var5);
            return null;
         }

         var10000 = new Object[0];
         var2.add(var6);
         var6.pC(var4);
      }

      return var2;
   }
}
