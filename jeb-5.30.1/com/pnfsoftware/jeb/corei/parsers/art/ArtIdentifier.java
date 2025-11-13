package com.pnfsoftware.jeb.corei.parsers.art;

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

public class ArtIdentifier extends AbstractUnitIdentifier {
   public static final String TYPE = "art";

   public ArtIdentifier() {
      super("art", 0.0);
   }

   @Override
   public IPluginInformation getPluginInformation() {
      return new PluginInformation(
         S.L("ART pseudo-plugin (identifier only)"), S.L("Identifier for Android Runtime (ART) files"), "PNF Software", Version.create(1, 0, 0)
      );
   }

   @Override
   public boolean canIdentify(IInput var1, IUnitCreator var2, String var3, Map var4) {
      return !checkBytes(var1, 0, 97, 114, 116, 10) ? false : checkBytes(var1, 7, 0);
   }

   @Override
   public IUnit prepare(String var1, IInput var2, IUnitProcessor var3, IUnitCreator var4, Map var5) {
      return new eo(var1, var2, var3, var4, this.pdm);
   }
}
