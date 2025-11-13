package com.pnfsoftware.jeb.corei.parsers.dex;

import com.pnfsoftware.jeb.client.S;
import com.pnfsoftware.jeb.core.units.UnitUtil;
import com.pnfsoftware.jeb.core.units.code.android.dex.DalvikParserErrorType;
import com.pnfsoftware.jeb.util.format.Strings;
import com.pnfsoftware.jebglobal.bel;
import com.pnfsoftware.jebglobal.bex;
import com.pnfsoftware.jebglobal.bfu;

class GK implements bex {
   GK(vi var1) {
      this.pC = var1;
   }

   @Override
   public void pC(DalvikParserErrorType var1, int var2, int var3, int var4, int var5, bel.Av var6) {
      bfu var9 = (bfu)this.pC.fI().pC(var5);
      String var7;
      String var8;
      if (var9 != null) {
         var7 = Strings.ff(S.L("Parsing error at %s (+%Xh/@%Xh): %s"), var9.pC(false), var4, var4 / 2, var1);
         var8 = Strings.ff("%s+%Xh", var9.getSignature(false), var4);
      } else {
         var7 = Strings.ff(S.L("Parsing error at %Xh (+%Xh/@%Xh) : %s"), var2, var4, var4 / 2, var1);
         var8 = null;
      }

      if (var6 != null) {
         var7 = var7 + Strings.ff(" (%s)", var6.kS);
      }

      UnitUtil.logError(this.pC, var8, true, vi.hK, var7);
   }
}
