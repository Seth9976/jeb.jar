package com.pnfsoftware.jeb.corei.parsers.zip;

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

public class ZipIdentifier extends AbstractUnitIdentifier {
   public static final String TYPE = "zip";
   public static final String propnameProcessEntriesLazily = "ProcessEntriesLazily";

   public ZipIdentifier() {
      super("zip", 0.0);
   }

   @Override
   public IPluginInformation getPluginInformation() {
      return new PluginInformation(
         S.L("ZIP archive handler"), S.L("ZIP file inflater based on Apache commons-compress"), "PNF Software", Version.create(0, 3, 0)
      );
   }

   @Override
   public void initialize(IPropertyDefinitionManager var1) {
      super.initialize(var1);
      this.pdm
         .addDefinition(
            "ProcessEntriesLazily",
            PropertyTypeBoolean.create(true),
            S.L(
               "The zipped entries will only be processed when required (i.e. when requested by the user). To allow recovery mode for truncated archives, this setting must be enabled."
            )
         );
   }

   @Override
   public boolean canIdentify(IInput var1, IUnitCreator var2, String var3, Map var4) {
      return checkBytes(var1, 0, 80, 75, 3, 4) || checkBytes(var1, 0, 80, 75, 5, 6);
   }

   @Override
   public IUnit prepare(String var1, IInput var2, IUnitProcessor var3, IUnitCreator var4, Map var5) {
      return new CU(var1, var2, var3, var4, this.pdm);
   }
}
