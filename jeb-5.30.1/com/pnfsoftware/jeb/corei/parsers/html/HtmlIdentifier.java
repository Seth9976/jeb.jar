package com.pnfsoftware.jeb.corei.parsers.html;

import com.pnfsoftware.jeb.client.S;
import com.pnfsoftware.jeb.core.IPluginInformation;
import com.pnfsoftware.jeb.core.IUnitCreator;
import com.pnfsoftware.jeb.core.PluginInformation;
import com.pnfsoftware.jeb.core.Version;
import com.pnfsoftware.jeb.core.input.IInput;
import com.pnfsoftware.jeb.core.units.AbstractUnitIdentifier;
import com.pnfsoftware.jeb.core.units.IUnit;
import com.pnfsoftware.jeb.core.units.IUnitProcessor;
import com.pnfsoftware.jeb.util.format.Strings;
import com.pnfsoftware.jeb.util.logging.GlobalLog;
import com.pnfsoftware.jeb.util.logging.ILogger;
import java.util.Map;

public class HtmlIdentifier extends AbstractUnitIdentifier {
   private static final ILogger logger = GlobalLog.getLogger(HtmlIdentifier.class);
   public static final String TYPE = "html";
   static final boolean allowXml = true;

   public HtmlIdentifier() {
      super("html", 0.0);
   }

   @Override
   public IPluginInformation getPluginInformation() {
      return new PluginInformation(
         S.L("Generic HTML/XML reader"), S.L("HTML, non-strict XML reformatter and beautifyer based on Jsoup"), "PNF Software", Version.create(0, 1, 0)
      );
   }

   @Override
   public boolean canIdentify(IInput var1, IUnitCreator var2, String var3, Map var4) {
      if (var1 == null) {
         return false;
      } else {
         byte[] var5 = getNonWhitespaceHeader(var1, 15, true, '\f');
         if (var5 != null && var5.length != 0) {
            int var6 = 0;
            byte var7 = var5[var6++];
            if (var7 != 60) {
               return false;
            } else if (checkBytes(var5, var6, false, 63, 120, 109, 108)) {
               return true;
            } else if (checkBytes(var5, var6, false, 104, 116, 109, 108)) {
               return true;
            } else if (checkBytes(var5, var6, false, 115, 118, 103)) {
               return true;
            } else {
               if (checkBytes(var5, var6, false, 33, 100, 111, 99, 116, 121, 112, 101)) {
                  var6 += 8;
                  int var8 = skipSpaces(var5, var6);
                  if (var8 != var6 && var8 < var5.length) {
                     return checkBytes(var5, var8, false, 104, 116, 109, 108);
                  }
               }

               String[] var11 = new String[]{".html", ".htm", ".svg"};
               return var3 != null && Strings.endsWith(var3.toLowerCase(), var11);
            }
         } else {
            return false;
         }
      }
   }

   static int skipSpaces(byte[] var0, int var1) {
      while (var1 < var0.length) {
         byte var2 = var0[var1];
         if (Strings.isAsciiWhitespace(var2, '\f')) {
            var1++;
            continue;
         }
         break;
      }

      return var1;
   }

   @Override
   public IUnit prepare(String var1, IInput var2, IUnitProcessor var3, IUnitCreator var4, Map var5) {
      return new nI(var1, var2, var3, var4, this.pdm);
   }
}
