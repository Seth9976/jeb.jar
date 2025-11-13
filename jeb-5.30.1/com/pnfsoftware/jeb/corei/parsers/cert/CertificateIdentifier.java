package com.pnfsoftware.jeb.corei.parsers.cert;

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
import java.util.Map;

public class CertificateIdentifier extends AbstractUnitIdentifier {
   public static final String TYPE = "cert";

   public CertificateIdentifier() {
      super("cert", 0.0);
   }

   @Override
   public IPluginInformation getPluginInformation() {
      return new PluginInformation(S.L("Certificate parser"), S.L("X509 certificate (binary) parser"), "PNF Software", Version.create(1, 0, 0));
   }

   @Override
   public boolean canIdentify(IInput var1, IUnitCreator var2, String var3, Map var4) {
      return var3 == null || !Strings.endsWith(var3.toLowerCase(), ".cer", ".der", ".rsa", ".dsa") ? false : var1.getCurrentSize() >= 4L;
   }

   @Override
   public IUnit prepare(String var1, IInput var2, IUnitProcessor var3, IUnitCreator var4, Map var5) {
      return new CU(var1, var2, var3, var4, this.pdm);
   }
}
