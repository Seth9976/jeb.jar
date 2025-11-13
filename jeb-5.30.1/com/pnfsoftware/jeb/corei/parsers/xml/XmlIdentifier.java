package com.pnfsoftware.jeb.corei.parsers.xml;

import com.pnfsoftware.jeb.client.S;
import com.pnfsoftware.jeb.core.IPluginInformation;
import com.pnfsoftware.jeb.core.IUnitCreator;
import com.pnfsoftware.jeb.core.PluginInformation;
import com.pnfsoftware.jeb.core.Version;
import com.pnfsoftware.jeb.core.input.IInput;
import com.pnfsoftware.jeb.core.properties.IPropertyDefinitionManager;
import com.pnfsoftware.jeb.core.units.AbstractUnitIdentifier;
import com.pnfsoftware.jeb.core.units.IUnit;
import com.pnfsoftware.jeb.core.units.IUnitProcessor;
import com.pnfsoftware.jeb.util.format.Strings;
import com.pnfsoftware.jeb.util.logging.GlobalLog;
import com.pnfsoftware.jeb.util.logging.ILogger;
import java.util.Map;

public class XmlIdentifier extends AbstractUnitIdentifier {
   private static final ILogger logger = GlobalLog.getLogger(XmlIdentifier.class);
   public static final String TYPE = "xml";

   public XmlIdentifier() {
      super("xml", 0.0);
   }

   @Override
   public IPluginInformation getPluginInformation() {
      return new PluginInformation(S.L("XML reader"), S.L("XML reader with re-formatting/beautifying"), "PNF Software", Version.create(1, 0, 0));
   }

   @Override
   public void initialize(IPropertyDefinitionManager var1) {
      super.initialize(var1);
      CU.q(this.pdm);
   }

   @Override
   public boolean canIdentify(IInput var1, IUnitCreator var2, String var3, Map var4) {
      if (var1 == null) {
         return false;
      } else {
         String[] var5 = new String[]{".html", ".htm", ".svg"};
         if (var3 != null && Strings.endsWith(var3.toLowerCase(), var5)) {
            return false;
         } else {
            byte[] var6 = getNonWhitespaceHeader(var1, 5, true);
            if (var6 == null || var6.length == 0) {
               return false;
            } else {
               return isXMLHeader(var6) ? true : var3 != null && var3.endsWith(".xml") && var6[0] == 60;
            }
         }
      }
   }

   static boolean isXMLHeader(byte[] var0) {
      return checkBytes(var0, 0, false, 60, 63, 120, 109, 108);
   }

   @Override
   public IUnit prepare(String var1, IInput var2, IUnitProcessor var3, IUnitCreator var4, Map var5) {
      return new Nt(var1, var2, var3, var4, this.pdm);
   }
}
