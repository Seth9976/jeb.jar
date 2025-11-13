package com.pnfsoftware.jeb.corei.parsers.aar;

import com.pnfsoftware.jeb.client.S;
import com.pnfsoftware.jeb.core.IPluginInformation;
import com.pnfsoftware.jeb.core.IUnitCreator;
import com.pnfsoftware.jeb.core.PluginInformation;
import com.pnfsoftware.jeb.core.Version;
import com.pnfsoftware.jeb.core.input.FileInput;
import com.pnfsoftware.jeb.core.input.IInput;
import com.pnfsoftware.jeb.core.units.AbstractUnitIdentifier;
import com.pnfsoftware.jeb.core.units.IUnit;
import com.pnfsoftware.jeb.core.units.IUnitProcessor;
import com.pnfsoftware.jeb.corei.parsers.zip.CU;
import com.pnfsoftware.jeb.util.collect.Sets;
import com.pnfsoftware.jeb.util.logging.GlobalLog;
import com.pnfsoftware.jeb.util.logging.ILogger;
import com.pnfsoftware.jebglobal.cvq;
import java.io.IOException;
import java.util.Map;

public class AarIdentifier extends AbstractUnitIdentifier {
   private static final ILogger logger = GlobalLog.getLogger(AarIdentifier.class);
   public static final String TYPE = "aar";

   public AarIdentifier() {
      super("aar", 0.0);
   }

   @Override
   public IPluginInformation getPluginInformation() {
      return new PluginInformation(S.L("Android Archive parser"), S.L("Android Archive (aar) parser"), "PNF Software", Version.create(1, 0, 0));
   }

   @Override
   public boolean canIdentify(IInput var1, IUnitCreator var2, String var3, Map var4) {
      if (!(var1 instanceof FileInput)) {
         return false;
      } else if (!checkBytes(var1, 0, 80, 75, 3, 4)) {
         return false;
      } else {
         try {
            return new cvq.eo(var1, Sets.newHashSet("AndroidManifest.xml", "classes.jar", "R.txt"), Sets.newHashSet("classes.dex")).xK();
         } catch (IOException var6) {
            logger.catchingSilent(var6);
            return false;
         }
      }
   }

   @Override
   public IUnit prepare(String var1, IInput var2, IUnitProcessor var3, IUnitCreator var4, Map var5) {
      logger.warn(S.L("A ZIP unit was created to represent an Android AAR library package. For easier analysis, consider converting it to a standard APK."));
      CU var6 = new CU(var1, var2, var3, var4, this.pdm, "aar");
      var6.q(true);
      return var6;
   }
}
