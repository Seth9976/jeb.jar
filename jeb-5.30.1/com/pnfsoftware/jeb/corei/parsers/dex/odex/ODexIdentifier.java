package com.pnfsoftware.jeb.corei.parsers.dex.odex;

import com.pnfsoftware.jeb.client.S;
import com.pnfsoftware.jeb.core.IPluginInformation;
import com.pnfsoftware.jeb.core.IUnitCreator;
import com.pnfsoftware.jeb.core.PluginInformation;
import com.pnfsoftware.jeb.core.Version;
import com.pnfsoftware.jeb.core.input.IInput;
import com.pnfsoftware.jeb.core.units.AbstractUnitIdentifier;
import com.pnfsoftware.jeb.core.units.IUnit;
import com.pnfsoftware.jeb.core.units.IUnitProcessor;
import com.pnfsoftware.jeb.util.logging.GlobalLog;
import com.pnfsoftware.jeb.util.logging.ILogger;
import java.util.Map;

public class ODexIdentifier extends AbstractUnitIdentifier {
   private static final ILogger logger = GlobalLog.getLogger(ODexIdentifier.class);
   public static final String TYPE = "odex";

   public ODexIdentifier() {
      super("odex", 0.0);
   }

   @Override
   public IPluginInformation getPluginInformation() {
      return new PluginInformation(S.L("ODEX parser"), S.L("Android parser for legacy optimized DEX files (dey)"), "PNF Software", Version.create(0, 1, 0));
   }

   @Override
   public boolean canIdentify(IInput var1, IUnitCreator var2, String var3, Map var4) {
      return checkBytes(var1, 0, 100, 101, 121, 10);
   }

   @Override
   public IUnit prepare(String var1, IInput var2, IUnitProcessor var3, IUnitCreator var4, Map var5) {
      return new eo(var1, var2, var3, var4, this.pdm);
   }
}
