package com.pnfsoftware.jeb.corei.parsers.crx;

import com.pnfsoftware.jeb.client.S;
import com.pnfsoftware.jeb.core.IPluginInformation;
import com.pnfsoftware.jeb.core.IUnitCreator;
import com.pnfsoftware.jeb.core.PluginInformation;
import com.pnfsoftware.jeb.core.Version;
import com.pnfsoftware.jeb.core.input.IInput;
import com.pnfsoftware.jeb.core.properties.IPropertyDefinitionManager;
import com.pnfsoftware.jeb.core.properties.impl.PropertyTypeBoolean;
import com.pnfsoftware.jeb.core.units.AbstractUnitIdentifier;
import com.pnfsoftware.jeb.core.units.IUnit;
import com.pnfsoftware.jeb.core.units.IUnitProcessor;
import java.util.Map;

public class CrxIdentifier extends AbstractUnitIdentifier {
   public static final String TYPE = "crx";
   public static final String propnameProcessEntriesLazily = "ProcessEntriesLazily";

   public CrxIdentifier() {
      super("crx", 0.0);
   }

   @Override
   public IPluginInformation getPluginInformation() {
      return new PluginInformation(S.L("CRX handler"), S.L("Chrome Extension plugin"), "PNF Software", Version.create(1, 0, 0));
   }

   @Override
   public void initialize(IPropertyDefinitionManager var1) {
      super.initialize(var1);
      this.pdm
         .addDefinition(
            "ProcessEntriesLazily",
            PropertyTypeBoolean.create(false),
            S.L("If enabled, the zipped entries in the CRX will only be processed when required (i.e. when requested by the user)")
         );
   }

   @Override
   public boolean canIdentify(IInput var1, IUnitCreator var2, String var3, Map var4) {
      return var1 != null && var1.getCurrentSize() >= 16L && checkBytes(var1, 0, 67, 114, 50, 52) && checkBytes(var1, 5, 0, 0, 0);
   }

   @Override
   public IUnit prepare(String var1, IInput var2, IUnitProcessor var3, IUnitCreator var4, Map var5) {
      return new eo(var1, var2, var3, var4, this.pdm);
   }
}
