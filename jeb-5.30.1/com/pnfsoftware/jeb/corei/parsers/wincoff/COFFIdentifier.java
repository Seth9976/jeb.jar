package com.pnfsoftware.jeb.corei.parsers.wincoff;

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

public class COFFIdentifier extends AbstractUnitIdentifier {
   public static final String TYPE = "wincoff";

   public COFFIdentifier() {
      super("wincoff", 0.0);
   }

   @Override
   public IPluginInformation getPluginInformation() {
      return new PluginInformation(S.L("Microsoft COFF loader"), S.L("Windows Common Object File Format parser"), "PNF Software", Version.create(0, 0, 1));
   }

   @Override
   public boolean canIdentify(IInput var1, IUnitCreator var2, String var3, Map var4) {
      return checkBytes(var1, 0, 76, 1) || checkBytes(var1, 0, 100, 134);
   }

   @Override
   public IUnit prepare(String var1, IInput var2, IUnitProcessor var3, IUnitCreator var4, Map var5) {
      return new nI(var1, var2, var3, var4, this.pdm);
   }
}
