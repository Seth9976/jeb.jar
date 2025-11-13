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

public class eo {
   private static final ILogger q = GlobalLog.getLogger(eo.class);

   public LibraryIdentificationResults q(List var1, INativeCodeUnit var2) {
      List var3 = this.q(var1);
      if (var3 == null) {
         return null;
      } else {
         ej var4 = new ej();
         vn var5 = new vn();
         var5.q(var2, var4, false, false);
         LibraryIdentificationResults var6 = new LibraryIdentificationResults();

         for (ej var8 : var3) {
            Stream var9 = var8.RF().keySet().stream().filter(var1x -> var1x.q() == iZ.xK && (var4.RF().containsKey(var1x) || var4.za().contains(var1x)));
            Stream var10 = var8.za().stream().filter(var1x -> var1x.q() == iZ.xK && (var4.RF().containsKey(var1x) || var4.za().contains(var1x)));
            Stream var11 = Stream.concat(var9, var10);
            var6.getHitsMap().put(var8.zz(), var11.count());
         }

         return var6;
      }
   }

   private List q(List var1) {
      ArrayList var2 = new ArrayList();

      for (CodelessSignaturePackageEntry var4 : var1) {
         File var5 = var4.getFile();
         Object[] var10000 = new Object[]{var5};
         ej var6 = ej.RF(var5);
         if (var6 == null) {
            q.error("cannot deser %s", var5);
            return null;
         }

         var10000 = new Object[0];
         var2.add(var6);
         var6.q(var4);
      }

      return var2;
   }
}
