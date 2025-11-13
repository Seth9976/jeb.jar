package com.pnfsoftware.jeb.corei.parsers.dexdec;

import com.pnfsoftware.jeb.client.S;
import com.pnfsoftware.jeb.core.units.IAddressableUnit;
import com.pnfsoftware.jeb.core.units.IUnit;
import com.pnfsoftware.jeb.core.units.UnitUtil;
import com.pnfsoftware.jeb.core.units.code.java.IJavaElement;
import com.pnfsoftware.jeb.util.base.TypedContent;
import com.pnfsoftware.jeb.util.format.Formatter;
import com.pnfsoftware.jeb.util.format.Strings;
import com.pnfsoftware.jeb.util.logging.GlobalLog;
import com.pnfsoftware.jeb.util.logging.ILogger;
import com.pnfsoftware.jebglobal.bls;
import com.pnfsoftware.jebglobal.bmu;
import com.pnfsoftware.jebglobal.ct;
import java.util.List;

public class CU extends ct {
   private static final ILogger RF = GlobalLog.getLogger(CU.class);
   ej q;

   CU(ej var1) {
      super(var1, "Dex Decompiler");
      this.q = var1;
   }

   @Override
   public boolean isTarget(IUnit var1) {
      return super.isTarget(var1) ? true : var1 instanceof bls && ((bls)var1).q() == this.getPrimaryTarget();
   }

   @Override
   public TypedContent getItemInformation(IAddressableUnit var1, long var2, String var4, List var5) {
      if (!this.isTarget(var1)) {
         return null;
      } else {
         StringBuilder var6 = new StringBuilder();
         Object var7 = var1.getItemObject(var2);
         if (var7 instanceof bmu) {
            String var8 = ((bmu)var7).getOrigin();
            if (var8 != null) {
               var6.append(S.L("This value was not present in the original binary.\n"));
               var6.append(S.L("ORIGIN:")).append(" ").append(var8);
            }
         }

         if (var5 != null && !var5.isEmpty() && this.q.getPropertyManager().getBoolean(UnitUtil.unitProperty(this.q, "DisplayASTElementStackOnHover"))) {
            StringBuilder var14 = new StringBuilder();
            int var9 = 0;

            for (Object var11 : var5) {
               var14.append(Strings.spaces(var9 * 2));
               if (var11 instanceof IJavaElement var12) {
                  var14.append(var12.getElementType());
                  String var13 = var12.toShortString();
                  var13 = Formatter.escapeString(Strings.truncateWithSuffix(var13, 80, "..."));
                  var14.append(": ").append(var13);
               } else {
                  var14.append(var11.toString());
               }

               var14.append("\n");
               var9++;
            }

            if (var6.length() != 0) {
               var6.append("\n\n");
            }

            var6.append(S.L("[AST Objects Stack]\n")).append((CharSequence)var14);
         }

         return var6.length() == 0 ? null : this.q(var6.toString(), 70);
      }
   }
}
