package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.client.S;
import com.pnfsoftware.jeb.core.AbstractEnginesPlugin;
import com.pnfsoftware.jeb.core.IEnginesContext;
import com.pnfsoftware.jeb.core.IPluginInformation;
import com.pnfsoftware.jeb.core.PluginInformation;
import com.pnfsoftware.jeb.core.Version;
import java.util.Map;

public class bcr extends AbstractEnginesPlugin {
   @Override
   public IPluginInformation getPluginInformation() {
      return new PluginInformation(S.L("Arduino helper plugin"), S.L("Help analyze Arduino fimrware binaries"), "PNF Software", Version.create(0, 1));
   }

   @Override
   public void execute(IEnginesContext var1, Map var2) {
   }
}
