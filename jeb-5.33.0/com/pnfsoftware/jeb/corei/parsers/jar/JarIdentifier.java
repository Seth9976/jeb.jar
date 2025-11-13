package com.pnfsoftware.jeb.corei.parsers.jar;

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
import java.io.IOException;
import java.util.Map;

public class JarIdentifier extends AbstractUnitIdentifier {
   private static final ILogger pC = GlobalLog.getLogger(JarIdentifier.class);

   public JarIdentifier() {
      super("jar", 0.0);
   }

   @Override
   public IPluginInformation getPluginInformation() {
      return new PluginInformation(S.L("JAR parser"), S.L("JAR archive handler"), "PNF Software", Version.create(1, 0, 0));
   }

   @Override
   public boolean canIdentify(IInput var1, IUnitCreator var2, String var3, Map var4) {
      if (!checkBytes(var1, 0, 80, 75, 3, 4)) {
         return false;
      } else {
         try {
            return new Av(this, var1).kS();
         } catch (IOException var6) {
            pC.error(S.L("JAR identification failed (%s)"), var6);
            pC.catchingSilent(var6);
            return false;
         }
      }
   }

   @Override
   public IUnit prepare(String var1, IInput var2, IUnitProcessor var3, IUnitCreator var4, Map var5) {
      return new Sv(var1, var2, var3, var4, this.pdm);
   }
}
