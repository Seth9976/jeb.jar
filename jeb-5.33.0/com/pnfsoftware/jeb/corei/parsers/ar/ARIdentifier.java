package com.pnfsoftware.jeb.corei.parsers.ar;

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

public class ARIdentifier extends AbstractUnitIdentifier {
   public ARIdentifier() {
      super("ar", 0.0);
   }

   @Override
   public IPluginInformation getPluginInformation() {
      return new PluginInformation(S.L("AR archive parser"), S.L("AR archive parser"), "PNF Software", Version.create(0, 0, 1));
   }

   @Override
   public boolean canIdentify(IInput var1, IUnitCreator var2, String var3, Map var4) {
      return checkBytes(var1, 0, 33, 60, 97, 114, 99, 104, 62, 10);
   }

   @Override
   public IUnit prepare(String var1, IInput var2, IUnitProcessor var3, IUnitCreator var4, Map var5) {
      return new Ws(var1, var2, var3, var4, this.pdm);
   }
}
