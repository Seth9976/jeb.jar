package com.pnfsoftware.jeb.corei.parsers.dex.cdex;

import com.pnfsoftware.jeb.client.S;
import com.pnfsoftware.jeb.core.IPluginInformation;
import com.pnfsoftware.jeb.core.IUnitCreator;
import com.pnfsoftware.jeb.core.PluginInformation;
import com.pnfsoftware.jeb.core.Version;
import com.pnfsoftware.jeb.core.input.IInput;
import com.pnfsoftware.jeb.core.units.AbstractUnitIdentifier;
import com.pnfsoftware.jeb.core.units.IUnit;
import com.pnfsoftware.jeb.core.units.IUnitProcessor;
import java.util.Map;

public class CdexIdentifier extends AbstractUnitIdentifier {
   public CdexIdentifier() {
      super("cdex", 0.0);
   }

   @Override
   public IPluginInformation getPluginInformation() {
      return new PluginInformation(
         S.L("cdex pseudo-plugin (identifier only)"), S.L("Identifier for Android compact dex (cdex) files"), "PNF Software", Version.create(1, 0, 0)
      );
   }

   @Override
   public boolean canIdentify(IInput var1, IUnitCreator var2, String var3, Map var4) {
      return !checkBytes(var1, 0, 99, 100, 101, 120) ? false : checkBytes(var1, 7, 0);
   }

   @Override
   public IUnit prepare(String var1, IInput var2, IUnitProcessor var3, IUnitCreator var4, Map var5) {
      return new Av(var1, var2, var3, var4, this.pdm);
   }
}
