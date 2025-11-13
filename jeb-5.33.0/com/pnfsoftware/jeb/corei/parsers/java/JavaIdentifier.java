package com.pnfsoftware.jeb.corei.parsers.java;

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

public class JavaIdentifier extends AbstractUnitIdentifier {
   public JavaIdentifier() {
      super("java", 0.0);
   }

   @Override
   public IPluginInformation getPluginInformation() {
      return new PluginInformation(S.L("Java parser"), S.L("Java parser and syntax highlighting"), "PNF Software", Version.create(0, 1, 0));
   }

   @Override
   public boolean canIdentify(IInput var1, IUnitCreator var2, String var3, Map var4) {
      return var3 != null && var3.toLowerCase().endsWith(".java");
   }

   @Override
   public IUnit prepare(String var1, IInput var2, IUnitProcessor var3, IUnitCreator var4, Map var5) {
      return new bO(var1, var2, var3, var4, this.pdm);
   }
}
